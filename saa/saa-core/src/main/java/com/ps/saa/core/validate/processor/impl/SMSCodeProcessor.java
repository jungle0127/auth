package com.ps.saa.core.validate.processor.impl;

import com.ps.saa.core.properties.SAAConstants;
import com.ps.saa.core.validate.processor.AbstractorValidateCodeProcessor;
import com.ps.saa.core.validate.code.SMSCode;
import com.ps.saa.core.validate.code.sender.SMSCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component(value = "smsCodeProcessor")
public class SMSCodeProcessor extends AbstractorValidateCodeProcessor<SMSCode> {

    @Autowired
    private SMSCodeSender smsCodeSender;
    @Override
    protected void send(ServletWebRequest request, SMSCode validateCode) {
        this.smsCodeSender.send(request.getRequest().getParameter(SAAConstants.DEFAULT_PHONENUMBER_PARAMETER_NAME),validateCode.getCode());
    }
}
