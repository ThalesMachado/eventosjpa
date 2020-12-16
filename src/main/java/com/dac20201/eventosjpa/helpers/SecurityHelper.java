package com.dac20201.eventosjpa.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityHelper {

    public SecurityHelper() {
    }

    public static SecurityHelper instance = null;

    public static SecurityHelper getInstance() {
        if (instance == null)
            return new SecurityHelper();
        return instance;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String passwordHasher(String textoAsClaras) {
        return passwordEncoder.encode(textoAsClaras);
    }

}
