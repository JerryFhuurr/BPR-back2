package com.bpr.bprbackend2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/test/users")
public class testController {
    @PostMapping("/testc/post")
    public ResponseEntity<String> testPost(@RequestParam String username, @RequestParam String password) {
        if (username.equals("admin") && password.equals("admin")) {
            return new ResponseEntity<>("success", HttpStatus.OK);
        } else return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/testc/get")
    public ResponseEntity<String> testGet(@RequestParam String username, @RequestParam String password) {
        if (username.equals("admin") && password.equals("admin")) {
            return new ResponseEntity<>("success", HttpStatus.OK);
        } else return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
    }
}
