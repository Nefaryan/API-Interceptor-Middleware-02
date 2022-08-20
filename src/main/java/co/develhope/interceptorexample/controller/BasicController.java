package co.develhope.interceptorexample.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @GetMapping("")
    private String welcomeUser(){
        return "Welcome User!!!!";
    }
}
