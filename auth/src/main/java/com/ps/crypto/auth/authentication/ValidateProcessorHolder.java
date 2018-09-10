package com.ps.crypto.auth.authentication;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class ValidateProcessorHolder {
    @Autowired
    private Map<String,ValidateProcessor> validateProcessorMap;

}
