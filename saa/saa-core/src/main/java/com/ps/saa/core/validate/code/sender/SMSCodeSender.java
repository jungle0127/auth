package com.ps.saa.core.validate.code.sender;

public interface SMSCodeSender {
    void send(String phoneNumber,String code);

}
