package com.demographicwebapi.demographicwebapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import com.demographicwebapi.demographicwebapi.services.AlgoService;

@RestController
@RequestMapping("/encode")
public class EncodeController {

    @Autowired
    private AlgoService algo;

    @PostMapping("/get")
    public ResponseEntity<?> fetchSearchResults(
            @RequestParam("algorithm") String algoName,
            @RequestParam("name") String name) {

        return ResponseEntity.ok(algo.encodeString(name, algoName));
    }
}
