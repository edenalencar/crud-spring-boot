package br.com.bytescorp.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

public class PasswordEncoder {
    public static void main(String[] args) {
//        org.springframework.security.crypto.password.PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//        System.out.println(encoder.encode("192325ED$"));

        BCryptPasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("192325ED$"));
    }
}
