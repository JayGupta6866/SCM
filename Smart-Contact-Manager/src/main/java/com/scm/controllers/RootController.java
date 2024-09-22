package com.scm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.entities.User;
import com.scm.helpers.Helper;
import com.scm.services.UserService;

@ControllerAdvice
public class RootController {

    private Logger logger = LoggerFactory.getLogger(RootController.class);
    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUserInfo(Model model, Authentication authentication) {

        if(authentication == null){
            System.out.println("User not Authenticated");  // return if no authentication found. This might happen when accessing a secured endpoint without authentication.  // e.g., /dashboard or /user/add
            return;
        }

        String username = Helper.getEmailofLoggedInUser(authentication);

        User user = userService.getUserByEmail(username);
        logger.info("User Logged in Name : {}", user.getName());

        logger.info("User Logged in Email : {}", user.getEmail());

        model.addAttribute("LoggedinUser", user);
    }

}
