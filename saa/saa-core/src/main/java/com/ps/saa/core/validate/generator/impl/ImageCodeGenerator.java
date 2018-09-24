package com.ps.saa.core.validate.generator.impl;

import com.ps.saa.core.properties.SAAProperties;
import com.ps.saa.core.validate.code.ValidateCode;
import com.ps.saa.core.validate.generator.ValidateCodeGenerator;
import com.ps.saa.core.validate.code.ImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

@Component(value = "imageCodeGenerator")
public class ImageCodeGenerator implements ValidateCodeGenerator {
    @Autowired
    private SAAProperties saaProperties;

    @Override
    public ValidateCode generateValidateCode(ServletWebRequest request) {
        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", this.saaProperties.getValidateCode().getImageCode().getWidth());
        int height = ServletRequestUtils.getIntParameter(request.getRequest(),"height",this.saaProperties.getValidateCode().getImageCode().getHeight());

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        for (int i = 0; i < this.saaProperties.getValidateCode().getImageCode().getCodeLength(); i++) {
            String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 16);
        }

        g.dispose();

        return new ImageCode(image,sRand,this.saaProperties.getValidateCode().getImageCode().getExpiredInMinutes());
    }

    /**
     * Generate random ribbon for background of the image
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
