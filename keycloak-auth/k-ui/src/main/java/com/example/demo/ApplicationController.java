package com.example.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;

@RestController
public class ApplicationController {
    @RequestMapping("/user")
    public Principal user(Principal user){
        return user;
    }

    @RequestMapping("/token")
    public Map<String, String> token(HttpSession session){
        return Collections.singletonMap("token", session.getId());
    }
}
