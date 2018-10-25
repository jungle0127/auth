package com.ps.saa.core.validate.code;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class ValidateCodeTest {
    private ValidateCode validateCode;
    @Before
    public void setUp(){
        this.validateCode = new ValidateCode("123456", LocalDateTime.now().plusSeconds(-1L));
    }
    @Test
    public void isExpired() {
        Assert.assertTrue(this.validateCode.isExpired());
    }
}