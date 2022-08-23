package com.demographicwebapi.demographicwebapi.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@CrossOrigin(exposedHeaders = "Access-Control-Allow-Origin")
public class NameIndexController {

    @Autowired
    private NameIndexService nameIndexService;

    @RequestMapping("/")
    public String test() {
        return "HelloWorld";
    }

    @GetMapping("/get/{algo}/{name}/{Surname}")
    public ResponseEntity<?> fetchSearchResults(
            @PathVariable("algo") String algo,
            @PathVariable("name") String name,
            @PathVariable("Surname") String surname
            ) {
                boolean isSurname = false;
                if(surname.equals("true")){
                    isSurname = true;
                }
        return ResponseEntity.ok(nameIndexService.fetchResults(name, algo,isSurname));
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
