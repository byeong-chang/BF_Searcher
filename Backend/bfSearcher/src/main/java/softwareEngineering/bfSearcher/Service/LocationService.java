package softwareEngineering.bfSearcher.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softwareEngineering.bfSearcher.DTO.LocationDto;
import softwareEngineering.bfSearcher.Entity.Location;
import softwareEngineering.bfSearcher.Repository.LocationRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {
    public final LocationRepository locationRepository;
    public final UserService userService;
    private static final int EARTH_RADIUS = 6371; // 지구 반지름 (단위: km)

    public List<LocationDto> getRecommendLocation(String token) {
        var user = userService.Access(token);
        var userLatitude = user.getLocationCategory().getLatitude();
        var userLongitude = user.getLocationCategory().getLongitude();
        var disabledName = user.getDisabledCategory().getDisabled();
        var hobby = Long.parseLong(user.getHobby());

        List<LocationDto> filteredLocations = filterByDisabled(disabledName)
                .stream()
                .filter(location -> location.getHobbyCategory().getId().equals(hobby))
                .map(this::entityToDto)
                .collect(Collectors.toList());

        List<LocationDto> recommendLocations = new ArrayList<>();

        // 랜덤 2개 추가
        Collections.shuffle(filteredLocations);
        List<LocationDto> randomRecommendations = filteredLocations.subList(0, 2);

        // 거리 가까운 순으로 정렬
        List<LocationDto> sortedByDistance = new ArrayList<>(filteredLocations);
        sortedByDistance.sort(Comparator.comparingDouble(location ->
                calculateDistance(userLatitude, userLongitude, location.getLatitude(), location.getLongitude())));
        // 거리 가까운 순으로 2개 추가
        List<LocationDto> distanceRecommendations = sortedByDistance.subList(0, 2);

        // 리뷰 좋은 순으로 정렬하여 1개 추가
        List<LocationDto> sortedByReview = new ArrayList<>(filteredLocations);
        sortedByReview.sort(Comparator.comparingDouble(LocationDto::getStarRating).reversed());
        List<LocationDto> reviewRecommendations = sortedByReview.subList(0, 1);

        // recommendLocations에 모든 추천 항목 추가
        recommendLocations.addAll(randomRecommendations);
        recommendLocations.addAll(distanceRecommendations);
        recommendLocations.addAll(reviewRecommendations);

        return recommendLocations;
    }


    public LocationDto entityToDto(Location location) {
        return LocationDto.builder()
                .id(location.getId())
                .locationName(location.getLocationName())
                .hobbyCategory(location.getHobbyCategory().getHobby())
                .locationCategory(location.getLocationCategory().getLocation())
                .latitude(location.getLatitude())
                .longitude(location.getLongitude())
                .address(location.getAddress())
                .phoneNumber(location.getPhoneNumber())
                .homepage(location.getHomepage())
                .closedDay(location.getClosedDay())
                .runtime(location.getRuntime())
                .freePark(location.getFreePark())
                .paidPark(location.getPaidPark())
                .doorForDisabled(location.getDoorForDisabled())
                .wheelchairRental(location.getWheelchairRental())
                .toiletForDisabled(location.getToiletForDisabled())
                .parkForDisabled(location.getParkForDisabled())
                .bigPark(location.getBigPark())
                .guideDogPermitted(location.getGuideDogPermitted())
                .brailleGuide(location.getBrailleGuide())
                .audioGuide(location.getAudioGuide())
                .starRating(location.getStarRating())
                .build();
    }

    public List<LocationDto> getAllLocations(){
        List<LocationDto> locationDtos = new ArrayList<>();
        locationRepository.findAll().forEach(Location -> locationDtos.add(entityToDto(Location)));

        return locationDtos;
    }

    private List<Location> filterByDisabled(String disabledName) {
        return switch (disabledName) {
            case "지체장애" -> locationRepository.findByParkForDisabledOrToiletForDisabledOrWheelchairRental(1, 1, 1);
            case "뇌병변장애" -> locationRepository.findByDoorForDisabledOrToiletForDisabled(1, 1);
            case "시각장애" -> locationRepository.findByBrailleGuideOrAudioGuideOrGuideDogPermitted(1, 1, 1);
            default -> locationRepository.findAll();
        };
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) { //두 위치간의 거리 계산
        // 위도 및 경도를 라디안 값으로 변환
        double radLat1 = Math.toRadians(lat1);
        double radLon1 = Math.toRadians(lon1);
        double radLat2 = Math.toRadians(lat2);
        double radLon2 = Math.toRadians(lon2);

        // 두 지점 간의 차이를 계산
        double deltaLat = radLat2 - radLat1;
        double deltaLon = radLon2 - radLon1;

        // Haversine formula를 사용하여 거리 계산
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(radLat1) * Math.cos(radLat2) * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // 거리 계산 후 반환 (단위: km)
        return EARTH_RADIUS * c;
    }
 }
