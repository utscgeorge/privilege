package com.utstar.springsecurityDemo;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {

    public final String SALT="123456";

    @Override
    public String encode(CharSequence charSequence) {
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        return md5PasswordEncoder.encodePassword(charSequence.toString(),SALT);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        Md5PasswordEncoder md5PasswordEncoder = new Md5PasswordEncoder();
        return md5PasswordEncoder.isPasswordValid(s,charSequence.toString(),SALT);
    }
}
