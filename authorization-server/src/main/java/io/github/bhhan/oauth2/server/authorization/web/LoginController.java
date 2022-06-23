package io.github.bhhan.oauth2.server.authorization.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage(HttpServletRequest httpRequest, Model model){
        if(Objects.nonNull(httpRequest.getQueryString())){
            model.addAttribute("failed", true);
        }
        return "login";
    }
}
