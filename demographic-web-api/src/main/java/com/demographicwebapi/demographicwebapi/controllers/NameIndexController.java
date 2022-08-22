package com.demographicwebapi.demographicwebapi.controllers;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.demographicwebapi.demographicwebapi.services.NameIndexService;
import com.demographicwebapi.helper.Excelhelper;

import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/NameIndex")
public class NameIndexController {

    private NameIndexService nameIndexService;

    @RequestMapping("/")
    public String test() {
        return "HelloWorld";
    }

    @GetMapping("/get/{algo}/{name}")
    public String fetchSearchResults(
            @PathVariable("algo") String algo,
            @PathVariable("name") String name) {
        return name;
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) {
        if (Excelhelper.checkExcelFormat(file)) {
            this.nameIndexService.save(file);
            return ResponseEntity.ok(Map.of("message", "File Uploaded Successfully"));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Upload CSV File Only");

    }

}
