package com.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.helpers.Helper;
import com.scm.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;


    // dashboard
    @RequestMapping(value = "/dashboard")
    public String userDashboard(Model model, Authentication authentication) {
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
