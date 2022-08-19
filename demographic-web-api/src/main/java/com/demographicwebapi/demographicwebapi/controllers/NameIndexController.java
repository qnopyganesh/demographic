package com.demographicwebapi.demographicwebapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/NameIndex")
public class NameIndexController {
    @RequestMapping("/")
    public String test(){
        return "HelloWorld";
    }

    @GetMapping("/get/{algo}/{name}")
    public String fetchSearchResults(
        @PathVariable("algo") String algo,
        @PathVariable("name") String name
    ){
        return name;
    }
}
