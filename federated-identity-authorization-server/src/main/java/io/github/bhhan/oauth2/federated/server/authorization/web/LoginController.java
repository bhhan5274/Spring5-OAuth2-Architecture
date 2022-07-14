package io.github.bhhan.oauth2.federated.server.authorization.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        System.out.println("Hi There");
        return "login";
    }
}
