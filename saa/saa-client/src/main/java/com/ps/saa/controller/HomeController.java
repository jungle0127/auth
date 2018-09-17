package com.ps.saa.controller;

import com.ps.saa.core.dto.RestResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/appname")
    public RestResponse<String> getApplicationName(){
        return new RestResponse<String>("Security Authentication and Authorization");
    }
}
