package com.bpr.bprbackend2.unit.utility;

import com.bpr.bprbackend2.utility.MD5Utils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MD5UtilsTest {

    @Test
    void generateSaltPassword() {
        String password = "123456";
        String saltPass = MD5Utils.generateSaltPassword(password);
        System.out.println(saltPass);
    }

    @Test
    void verifySaltPassword() {
        String password = "123456";
        String saltPass = MD5Utils.generateSaltPassword(password);
        boolean result = MD5Utils.verifySaltPassword(password, saltPass);
        Assertions.assertTrue(result);
    }
}