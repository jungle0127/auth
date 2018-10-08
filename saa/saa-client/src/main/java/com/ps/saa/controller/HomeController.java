package com.ps.saa.controller;

import com.ps.saa.core.dto.RestResponse;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/home")
public class HomeController {
    @GetMapping("/appname")
    public RestResponse<String> getApplicationName(){
        return new RestResponse<String>("Security Authentication and Authorization");
    }
    @GetMapping("/me")
    public RestResponse<Authentication> getCurrentUser(Authentication authentication, HttpServletRequest request){
        return new RestResponse<>(authentication);
    }
}
