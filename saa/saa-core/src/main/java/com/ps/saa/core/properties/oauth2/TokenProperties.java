package com.ps.saa.core.properties.oauth2;

public class TokenProperties {
    private String storeType = "jwt";
    private String jwtSigningKey = "defaultsaajwtsigningkey";

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }
}
