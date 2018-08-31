package com.westone.cryptoservice.lm.controller;

import com.westone.cryptoservice.lm.dto.LMResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("authentication")
public class AuthorizeController {
    @GetMapping("/require")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public LMResponse<String> requireAuthentication(HttpServletRequest request, HttpServletResponse response){
        return new LMResponse<>("CHECK");
    }
}
