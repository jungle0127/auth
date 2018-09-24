package com.ps.saa.core.validate.processor.impl;

import com.ps.saa.core.validate.code.ImageCode;
import com.ps.saa.core.validate.processor.AbstractorValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

@Component("imageCodeProcessor")
public class ImageCodeProcessor extends AbstractorValidateCodeProcessor<ImageCode> {
    @Override
    protected void send(ServletWebRequest request, ImageCode validateCode) throws IOException {
        ImageIO.write(validateCode.getImage(),"PNG",request.getResponse().getOutputStream());
    }
}
