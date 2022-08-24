package com.demographicwebapi.demographicwebapi.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.demographicwebapi.demographicwebapi.services.AlgoService;

@RestController
@RequestMapping("/encode")
@CrossOrigin
public class EncodeController {

    @Autowired
    private AlgoService algo;

    @PostMapping(value = "/get")
    @CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
    @ResponseBody
    public String encode(
            @RequestParam("algorithm") String algoName,
            @RequestParam("name") String name) {
        return (algo.encodeString(name, algoName));
    }
}
