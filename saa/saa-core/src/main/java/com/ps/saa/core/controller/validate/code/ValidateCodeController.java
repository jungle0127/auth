package com.ps.saa.core.controller.validate.code;

import com.ps.saa.core.properties.SAAConstants;
import com.ps.saa.core.validate.code.ValidateCode;
import com.ps.saa.core.validate.code.ValidateCodeGenerator;
import com.ps.saa.core.validate.code.image.ImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/validate/code")
public class ValidateCodeController {
    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @GetMapping("/image")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = (ImageCode) this.imageCodeGenerator.generateValidateCode(new ServletWebRequest(request));
        this.sessionStrategy.setAttribute(new ServletWebRequest(request), SAAConstants.IMAGE_CODE_SESSION_KEY,imageCode);
        ImageIO.write(imageCode.getImage(),"PNG",response.getOutputStream());
    }
}
