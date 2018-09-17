package com.ps.saa.core.validate.code.sms;

public interface SMSCodeSender {
    void send(String phoneNumber,String code);

}
