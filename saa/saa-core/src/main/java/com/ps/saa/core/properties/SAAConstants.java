package com.ps.saa.core.properties;

public interface SAAConstants {
    String DEFAULT_LOGIN_PAGE = "/default-form-image-code-sign-in.html";
    String DEFAULT_FORM_LOGIN_PROCESSING_URL = "/authentication/form";
    String DEFAULT_SMS_LGOIN_PROCESSING_URL = "/authentication/sms";
    String AUTHENTICATE_URL = "/authentication/require";

    String VALIDATE_CODE_IMAGE_URL = "/validate/code/image";
    String VALIDATE_CODE_SMS_URL = "/validate/code/sms";
    String IMAGE_CODE_SESSION_KEY = "SESSION_KEY_CODE_IMAGE";
    String SMS_CODE_SESSION_KEY = "SESSION_KEY_CODE_SMS";
    String DEFAULT_PHONENUMBER_PARAMETER_NAME = "phoneNumber";
    String DEFAULT_SMSCODE_PARAMETER_NAME = "smsCode";



}
