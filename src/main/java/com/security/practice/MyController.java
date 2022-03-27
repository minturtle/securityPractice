package com.security.practice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MyController {

    @GetMapping("/")
    @ResponseBody
    public String index(Model model){

        return "index";
    }

    @GetMapping("/user/login")
    public String loginForm(){
        return "../static/loginForm.html";
    }


    @GetMapping("/user")
    public String userData(Model model){
        OAuth2User user = (OAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Map<String, String> userMap = (Map<String, String>) user.getAttributes().get("properties");

        model.addAttribute("user",userMap);
        return "user";
    }
}
