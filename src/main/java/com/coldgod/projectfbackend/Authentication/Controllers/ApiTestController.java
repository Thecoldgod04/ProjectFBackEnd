package com.coldgod.projectfbackend.Authentication.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiTestController {

    @GetMapping("/api")
    public String testApi()
    {
        return "This is only accessible by authenticated!";
    }

    @GetMapping("/")
    public String home()
    {
        return "Home :))";
    }

}
