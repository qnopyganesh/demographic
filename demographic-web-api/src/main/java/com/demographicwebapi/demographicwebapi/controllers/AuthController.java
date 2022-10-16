package com.demographicwebapi.demographicwebapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demographicwebapi.demographicwebapi.services.LoginService;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AuthController {

    @Autowired
    LoginService loginService;

    @PostMapping(value = "/login")
    @CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
    @ResponseBody
    public ResponseEntity<?> login(@RequestParam("username") String username,
            @RequestParam("password") String password) {
        return ResponseEntity.ok(loginService.login(username, password));
    }

    @PostMapping(value = "/signUp")
    @CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
    @ResponseBody
    public ResponseEntity<?> signUp(@RequestParam("username") String username,
            @RequestParam("password") String password) {
        return ResponseEntity.ok(loginService.signUp(username, password));
    }
}
