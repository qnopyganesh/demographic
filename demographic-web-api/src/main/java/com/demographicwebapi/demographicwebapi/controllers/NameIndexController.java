package com.demographicwebapi.demographicwebapi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameIndexController {
    @RequestMapping("/")
    public String test(){
        return "HelloWorld";
    }
}
