package soongmile.soongmileback.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/test")
    public String test(){

        return "<h1>test 통과</h1>";
    }

    @GetMapping("/jwtTest")
    public String jwtTest(@RequestHeader("User-Agent") String userAgent){
        return userAgent;
    }
}
