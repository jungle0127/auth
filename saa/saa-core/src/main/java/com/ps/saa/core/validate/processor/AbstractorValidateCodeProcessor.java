package com.ps.saa.core.validate.processor;

import com.ps.saa.core.validate.code.ValidateCode;
import com.ps.saa.core.validate.generator.ValidateCodeGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;
import java.util.Map;

public abstract class AbstractorValidateCodeProcessor<T extends ValidateCode> implements ValidateCodeProcessor {
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGeneratorMap;

    protected abstract void send(ServletWebRequest request, T validateCode) throws IOException;

    @Override
    public void create(ServletWebRequest request) throws IOException {
        T validateCode = generate(request);
        save(request, validateCode);
        send(request,validateCode);
    }

    private T generate(ServletWebRequest request) {
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGeneratorMap.get(type + "CodeGenerator");
        return (T) validateCodeGenerator.generateValidateCode(request);
    }

    private String getProcessorType(ServletWebRequest request) {
        return StringUtils.substringAfter(request.getRequest().getRequestURI(),"/code/");
    }

    private void save(ServletWebRequest request, T validateCode) {
        String sessionKey = SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase();
        sessionStrategy.setAttribute(request,sessionKey,validateCode);
    }


}
