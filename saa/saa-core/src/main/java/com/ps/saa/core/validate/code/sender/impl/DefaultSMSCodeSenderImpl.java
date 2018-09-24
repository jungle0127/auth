package com.ps.saa.core.validate.code.sender.impl;

import com.ps.saa.core.validate.code.sender.SMSCodeSender;

public class DefaultSMSCodeSenderImpl implements SMSCodeSender {
    @Override
    public void send(String phoneNumber, String code) {
        System.out.println(String.format("Send SMS code %s to phone %s",code,phoneNumber));
    }
}
