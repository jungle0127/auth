package com.ps.saa.core.properties;

public class SMSCodeProperties {
    private Integer codeLength = 6;
    private Integer expiredInMinutes = 5;

    public Integer getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(Integer codeLength) {
        this.codeLength = codeLength;
    }

    public Integer getExpiredInMinutes() {
        return expiredInMinutes;
    }

    public void setExpiredInMinutes(Integer expiredInMinutes) {
        this.expiredInMinutes = expiredInMinutes;
    }
}
