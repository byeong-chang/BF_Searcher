package softwareEngineering.bfSearcher.Service;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import softwareEngineering.bfSearcher.DTO.LikeLocationDto;
import softwareEngineering.bfSearcher.DTO.LocationForMapDto;
import softwareEngineering.bfSearcher.DTO.ReviewDto;
import softwareEngineering.bfSearcher.DTO.UserDto;
import softwareEngineering.bfSearcher.Entity.Location;
import softwareEngineering.bfSearcher.Entity.Review;
import softwareEngineering.bfSearcher.Entity.User;
import softwareEngineering.bfSearcher.Repository.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;
    public final LocationCategoryRepository locationCategoryRepository;
    public final DisabledCategoryRepository disabledCategoryRepository;
    public final ReviewRepository reviewRepository;
    public final LocationRepository locationRepository;
    private final String salt = "$2a$10$IWwR3bJ7Zn3ico4qEcz/D.";

    public UserDto entityToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .hobby(user.getHobby())
                .likeLocation(user.getLikeLocation())
                .locationCategory(user.getLocationCategory().getLocation())
                .disabledCategory(user.getDisabledCategory().getDisabled())
                .username(user.getUsername())
                .email(user.getEmail())
                .userId(user.getUserId())
                .passwd(user.getPasswd())
                .disabledValidate(user.getDisabledValidate())
                .build();
    }

    public User SignUp(UserDto userDto){
        User user = User.builder()
                .userId(userDto.getUserId())
                .hobby(userDto.getHobby())
                .likeLocation("")
                .locationCategory(locationCategoryRepository.findByLocation(userDto.getLocationCategory()))
                .disabledCategory(disabledCategoryRepository.findByDisabled(userDto.getDisabledCategory()))
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .passwd(BCrypt.hashpw(userDto.getPasswd(),salt))
                .disabledValidate(0L)
                .token(userDto.getToken())
                .build();
        userRepository.save(user);
        return user;
    }

    public User Login(UserDto userDto){
        User user = userRepository.findByUserIdAndPasswd(userDto.getUserId(),BCrypt.hashpw(userDto.getPasswd(),salt))
                .orElseGet(User::new);
        if (user.getUserId() != null) {
            user.setToken(userDto.getToken());
            userRepository.save(user);
        }
        return user;
    }
    public User Access(String token){
        User user = userRepository.findByToken(token)
                .orElseGet(User::new);
        if (user.getToken().equals(token)){
            return user;
        }
        else{
            throw new RuntimeException("토큰이 잘못 되었습니다.");
        }
    }
    public List<User> AllUser(){
        return userRepository.findAll();
    }

    public Review writeReview(ReviewDto reviewDto) {
        Review review = Review.builder()
                .user(userRepository.findByToken(reviewDto.getUserToken()).orElseGet(User::new))
                .location(locationRepository.findById(reviewDto.getLocationId()).orElseGet(Location::new))
                .content(reviewDto.getContent())
                .starRating(reviewDto.getStarRating())
                .build();
        reviewRepository.save(review);
        return  review;
    }


    public List<LocationForMapDto> showAllLikeLocation(String userToken) {
        List<LocationForMapDto> likeLocations = new ArrayList<>();
        User user = userRepository.findByToken(userToken).orElseGet(User::new);
        String likeLocationString = user.getLikeLocation();

        System.out.println("--------------------");
        System.out.println(likeLocationString);
        System.out.println("--------------------");

        if (likeLocationString != null && !likeLocationString.trim().isEmpty()){
            String[] parts = likeLocationString.split(" ");
            System.out.println(parts);
            List<Long> likeLocationIds = new ArrayList<Long>();
            // 각 부분을 Long으로 변환
            for (String part : parts) {
                Long number = Long.parseLong(part);
                likeLocationIds.add(number);
            }

            for (Long locationId : likeLocationIds){
                Location location = locationRepository.findById(locationId).orElseGet(Location::new);
                LocationForMapDto locationForMapDto = LocationForMapDto.builder()
                        .locationId(locationId)
                        .locationName(location.getLocationName())
                        .hobbyCategory(location.getHobbyCategory().getHobby())
                        .latitude(location.getLatitude())
                        .longitude(location.getLongitude())
                        .build();
                likeLocations.add(locationForMapDto);
            }
        }
        return likeLocations;
    }

    public ResponseEntity<Boolean> saveLikeLocation(LikeLocationDto likeLocationDto) {
        try {
            var user = Access(likeLocationDto.getToken());

            // 기존 like_location 값을 가져옴
            String currentLikeLocation = user.getLikeLocation();

            // null 체크 및 빈 경우에는 공백 없이 추가
            String newLikeLocation;
            if (currentLikeLocation == null) {
                newLikeLocation = likeLocationDto.getLocationId();
            } else if (currentLikeLocation.isEmpty()) {
                newLikeLocation = likeLocationDto.getLocationId();
            } else {
                newLikeLocation = currentLikeLocation + " " + likeLocationDto.getLocationId();
            }

            // 업데이트된 값을 다시 저장
            user.setLikeLocation(newLikeLocation);
            userRepository.save(user);

            return ResponseEntity.ok(true);
        } catch (Exception e) {
            // 오류 발생 시 false 반환
            return ResponseEntity.ok(false);
        }
    }

}