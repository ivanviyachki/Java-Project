package com.sc.courseProject.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorHandlerController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public ResponseEntity<?> error() {
        return ResponseEntity.badRequest().body("Wrong cage! Nothing here.");
    }
    @Override
    public String getErrorPath() {
        return null;
    }

}
