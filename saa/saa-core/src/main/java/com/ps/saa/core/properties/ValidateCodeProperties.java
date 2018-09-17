package com.ps.saa.core.properties;

public class ValidateCodeProperties {
    private ImageCodeProperties imageCode;
    private SMSCodeProperties smsCode;
    public ImageCodeProperties getImageCode() {
        return imageCode;
    }

    public void setImageCode(ImageCodeProperties imageCode) {
        this.imageCode = imageCode;
    }

    public SMSCodeProperties getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(SMSCodeProperties smsCode) {
        this.smsCode = smsCode;
    }
}
