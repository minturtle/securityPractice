package com.security.practice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {


    @GetMapping("/")
    @ResponseBody
    public ResponseEntity index(){
        ResponseEntity<String> resp = new ResponseEntity<String>("hello world", HttpStatus.OK);

        return resp;
    }

    @GetMapping("/login-page")
    public String loginForm(){
        return "../static/loginForm.html";
    }

}
