package softwareEngineering.bfSearcher.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softwareEngineering.bfSearcher.DTO.UserDto;
import softwareEngineering.bfSearcher.Entity.User;
import softwareEngineering.bfSearcher.Repository.DisabledCategoryRepository;
import softwareEngineering.bfSearcher.Repository.LocationCategoryRepository;
import softwareEngineering.bfSearcher.Repository.UserRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    public final UserRepository userRepository;
    public final LocationCategoryRepository locationCategoryRepository;
    public final DisabledCategoryRepository disabledCategoryRepository;

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

    public String SignUp(UserDto userDto){
        User user = User.builder()
                .userId(userDto.getUserId())
                .hobby(userDto.getHobby())
                .likeLocation("")
                .locationCategory(locationCategoryRepository.findByLocation(userDto.getLocationCategory()))
                .disabledCategory(disabledCategoryRepository.findByDisabled(userDto.getDisabledCategory()))
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .passwd(userDto.getPasswd())
                .disabledValidate(0L)
                .token(userDto.getToken())
                .build();
        userRepository.save(user);
        return user.getToken();
    }

    public User Login(UserDto userDto){
        User user = userRepository.findByUserIdAndPasswd(userDto.getUserId(),userDto.getPasswd())
                .orElseThrow(()->new NoSuchElementException("해당하는 사용자가 없습니다."));
        user.setToken(userDto.getToken());
        userRepository.save(user);
        return  user;
    }
    public User Access(String token){
        User user = userRepository.findByToken(token)
                .orElseThrow(() -> new NoSuchElementException("해당하는 사용자가 없습니다."));
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
}