package com.spring.example.security.password;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @ClassName PasswordEncoderTest.java
 */
@SpringBootTest(classes = PasswordEncoderTest.class)
public class PasswordEncoderTest {

    @Test
    public void testPwd() {
        PasswordEncoder pe = new BCryptPasswordEncoder();
        final String encode = pe.encode("123456");
        final boolean matches = pe.matches("123456", encode);
        System.out.println(encode);
        System.out.println(matches);
    }

}
