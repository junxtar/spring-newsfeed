package com.sparta.springnewsfeed.domain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

}
