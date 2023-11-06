package softwareEngineering.bfSearcher.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import softwareEngineering.bfSearcher.Service.UserService;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

}
