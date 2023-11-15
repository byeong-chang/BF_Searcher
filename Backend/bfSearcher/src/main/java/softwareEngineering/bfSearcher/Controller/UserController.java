package softwareEngineering.bfSearcher.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import softwareEngineering.bfSearcher.DTO.UserDto;
import softwareEngineering.bfSearcher.Entity.Review;
import softwareEngineering.bfSearcher.Entity.User;
import softwareEngineering.bfSearcher.Service.UserService;

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
    @PostMapping("Review")
    public Review WriteReview(@RequestBody Review review){
        return review;
    }
    @GetMapping("bucket/{userId}")
    public User showBucket(@PathVariable User user){
        return user;
    }
}
