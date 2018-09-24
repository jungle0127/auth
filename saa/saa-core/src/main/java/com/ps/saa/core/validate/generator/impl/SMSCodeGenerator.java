package com.ps.saa.core.validate.generator.impl;

import com.ps.saa.core.properties.SAAProperties;
import com.ps.saa.core.validate.code.ValidateCode;
import com.ps.saa.core.validate.generator.ValidateCodeGenerator;
import com.ps.saa.core.validate.code.SMSCode;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
@Component("smsCodeGenerator")
public class SMSCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    private SAAProperties saaProperties;
    @Override
    public ValidateCode generateValidateCode(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(this.saaProperties.getValidateCode().getSmsCode().getCodeLength());
        return new SMSCode(code,this.saaProperties.getValidateCode().getSmsCode().getExpiredInMinutes());
    }
}
