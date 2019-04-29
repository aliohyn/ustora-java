package com.aliohyn.ustora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ProfileChecker {
    @Autowired
    private Environment environment;

    public boolean isDevProfile() {
        for (String profileName : environment.getActiveProfiles()) {
            if(profileName.equals("dev")) {
                return true;
            }
        }
        return false;
    }
}
