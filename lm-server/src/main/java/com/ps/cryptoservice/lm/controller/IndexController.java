package com.ps.cryptoservice.lm.controller;

import com.ps.crypto.auth.properties.AuthProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
    @Autowired
    private AuthProperties authProperties;
    @GetMapping("/check")
    public String test(){
        return "CHECK";
    }
}
