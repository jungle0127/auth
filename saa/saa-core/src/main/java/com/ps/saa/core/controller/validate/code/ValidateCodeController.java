package com.ps.saa.core.controller.validate.code;

import com.ps.saa.core.validate.processor.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/validate/code")
public class ValidateCodeController {
    @Autowired
    private Map<String, ValidateCodeProcessor> validateCodeProcessorMap;
    @GetMapping("/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws IOException {
        ValidateCodeProcessor codeProcessor =  validateCodeProcessorMap.get(type + "CodeProcessor");
        codeProcessor.create(new ServletWebRequest(request));
    }
//    @Autowired
//    private ValidateCodeGenerator imageCodeGenerator;
//    @Autowired
//    private ValidateCodeGenerator smsCodeGenerator;
//    @Autowired
//    private SMSCodeSender smsCodeSender;
//    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
//    @GetMapping("/image")
//    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        ImageCode imageCode = (ImageCode) this.imageCodeGenerator.generateValidateCode(new ServletWebRequest(request));
//        this.sessionStrategy.setAttribute(new ServletWebRequest(request), SAAConstants.IMAGE_CODE_SESSION_KEY,imageCode);
//        ImageIO.write(imageCode.getImage(),"PNG",response.getOutputStream());
//    }
//    @GetMapping("/sender")
//    public void sendSMSCode(HttpServletRequest request, HttpServletResponse response) throws ServletRequestBindingException {
//        SMSCode smsCode = (SMSCode) this.smsCodeGenerator.generateValidateCode(new ServletWebRequest(request));
//        this.sessionStrategy.setAttribute(new ServletWebRequest(request),SAAConstants.SMS_CODE_SESSION_KEY,smsCode);
//        String phoneNumber = ServletRequestUtils.getStringParameter(request,SAAConstants.DEFAULT_PHONENUMBER_PARAMETER_NAME);
//        this.smsCodeSender.send(phoneNumber,smsCode.getCode());
//    }
}
