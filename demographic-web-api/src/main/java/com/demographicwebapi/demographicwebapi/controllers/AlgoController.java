package com.demographicwebapi.demographicwebapi.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

public class AlgoController {
	@RequestMapping("/Algo")
    public String test(){
        return "HelloWorld";
    }

}
