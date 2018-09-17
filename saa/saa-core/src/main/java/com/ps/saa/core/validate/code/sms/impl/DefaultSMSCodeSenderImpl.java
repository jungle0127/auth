package com.ps.saa.core.validate.code.sms.impl;

import com.ps.saa.core.validate.code.sms.SMSCodeSender;
import org.springframework.stereotype.Component;

public class DefaultSMSCodeSenderImpl implements SMSCodeSender {
    @Override
    public void send(String phoneNumber, String code) {
        System.out.println(String.format("Send SMS code %s to phone %s",code,phoneNumber));
    }
}
