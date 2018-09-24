package com.ps.saa.core.validate.generator;

import com.ps.saa.core.validate.code.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {
    /**
     * Generate validate code
     * @param request
     * @return
     */
    ValidateCode generateValidateCode(ServletWebRequest request);
}
