package com.ps.crypto.auth.authentication;

import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateProcessor {
    void validate(ServletWebRequest servletWebRequest);
}
