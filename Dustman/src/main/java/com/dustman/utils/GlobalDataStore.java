package com.dustman.utils;

import com.dustman.dao.PasswordDAO;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class GlobalDataStore {
    private final PasswordDAO passwordDAO;

    public GlobalDataStore(PasswordDAO passwordDAO) {
        this.passwordDAO = passwordDAO;
    }

     public Map<String, String> passwordMap = new HashMap<>();

    @PostConstruct
    public void init() {
        passwordMap.put("EmailPassword", passwordDAO.getPassword("EmailPassword"));
        passwordMap.put("EmailID", passwordDAO.getPassword("EmailID"));
//        System.out.println("Password map initialized     "+passwordMap.get("EMAIL"));
    }
}
