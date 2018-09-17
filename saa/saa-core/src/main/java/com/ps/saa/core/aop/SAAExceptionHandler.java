package com.ps.saa.core.aop;

import com.ps.saa.core.dto.RestResponse;
import com.ps.saa.core.exception.ValidateCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SAAExceptionHandler {
    @ExceptionHandler(ValidateCodeException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    RestResponse<String> handleValidateCodeException(ValidateCodeException e){
        RestResponse<String> restResponse = new RestResponse<>(e.getMessage());
        return restResponse;
    }
}
