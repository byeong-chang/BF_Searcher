package softwareEngineering.bfSearcher.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import softwareEngineering.bfSearcher.DTO.LocationForMapDto;
import softwareEngineering.bfSearcher.DTO.ReviewDto;
import softwareEngineering.bfSearcher.DTO.UserDto;
import softwareEngineering.bfSearcher.Entity.Review;
import softwareEngineering.bfSearcher.Entity.User;
import softwareEngineering.bfSearcher.Service.UserService;

import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("sign_up")
    public User SignUp(@RequestBody UserDto userDto){
        return userService.SignUp(userDto);
    }
    @PostMapping("Login")
    public User Login(@RequestBody UserDto userDto){
        return userService.Login(userDto);
    }
    @GetMapping("Acess/{token}")
    public User Acecss(@PathVariable String token){
        return userService.Access(token);
    }
    @PostMapping("writeReview")
    public Review writeReview(@RequestBody ReviewDto reviewDto){return userService.writeReview(reviewDto);}
    @GetMapping("showAllLikeLocation/{userToken}")
    public List<LocationForMapDto> showAllLikeLocation(@PathVariable String userToken){
        return userService.showAllLikeLocation(userToken);
    }
}
