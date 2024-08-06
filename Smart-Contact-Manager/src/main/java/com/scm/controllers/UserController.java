package com.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.helpers.Helper;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    
    // dashboard
    @RequestMapping(value = "/dashboard")
    public String userDashboard(Authentication authentication) {
        String username = Helper.getEmailofLoggedInUser(authentication);
        logger.info("User Logged in : {}", username);
        return "user/dashboard";
    }

    // profile page
    @RequestMapping(value = "/profile")
    public String userProfile() {
        return "user/profile";
    }

    // user add contact page

    // view contacts

    // edit contact page

    // delete contact page

}
