package com.security.practice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {


    @GetMapping("/")
    public ResponseEntity index(){
        ResponseEntity<String> resp = new ResponseEntity<String>("hello world", HttpStatus.OK);

        return resp;
    }

}
