package com.ps.saa.core.validate.code.sms;

import com.ps.saa.core.validate.code.ValidateCode;

import java.time.LocalDateTime;

public class SMSCode extends ValidateCode {
    public SMSCode(String code, LocalDateTime expireTime) {
        super(code, expireTime);
    }

    public SMSCode(String code, Integer expiredInMinutes) {
        super(code, expiredInMinutes);
    }
}
