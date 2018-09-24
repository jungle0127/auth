package com.ps.saa.core.validate.code;

public enum ValidateCodeType {
    SMS{
        @Override
        public String getParameterNameOnValidate() {
            return "SMS";
        }
    },
    IMAGE_CODE{
        @Override
        public String getParameterNameOnValidate() {
            return "IMAGE";
        }
    };
    public abstract String getParameterNameOnValidate();
}
