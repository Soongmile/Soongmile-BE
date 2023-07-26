package soongmile.soongmileback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import soongmile.soongmileback.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

}
