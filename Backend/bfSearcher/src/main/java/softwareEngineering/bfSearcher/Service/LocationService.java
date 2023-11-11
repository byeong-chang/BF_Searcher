package softwareEngineering.bfSearcher.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softwareEngineering.bfSearcher.DTO.LocationDto;
import softwareEngineering.bfSearcher.Entity.Location;
import softwareEngineering.bfSearcher.Repository.LocationRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationService {
    public final LocationRepository locationRepository;
    public final UserService userService;

    public List<LocationDto> getRecommendLocation (String token) {
        var user = userService.Access(token);
        var region = user.getLocationCategory().getLocation();
        var disabledName = user.getDisabledCategory().getDisabled();
        var hobby = user.getHobby();

        List<LocationDto> recommendLocations = filterByDisabled(disabledName)
                .stream()
                .filter(location -> location.getAddress().contains(region))
                .filter(location -> location.getHobbyCategory().getHobby().contains(hobby))
                .map(this::entityToDto)
                .collect(Collectors.toList());

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
 }
