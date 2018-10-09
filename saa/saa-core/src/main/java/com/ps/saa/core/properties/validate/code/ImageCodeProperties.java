package com.ps.saa.core.properties.validate.code;

public class ImageCodeProperties {
    private Integer width = 67;
    private Integer height = 23;
    private Integer codeLength = 4;
    private Integer expiredInMinutes = 5;

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

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
