package com.scm.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class Helper {

    public static String getEmailofLoggedInUser(Authentication authentication) {

        if (authentication instanceof OAuth2AuthenticationToken) {
            var oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientID = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            var oAuth2User = (OAuth2User) authentication.getPrincipal();

            if (clientID.equalsIgnoreCase("google")) {
                System.out.println("Getting email from Google");
                return oAuth2User.getAttribute("email").toString();
            } else if (clientID.equalsIgnoreCase("github")) {
                System.out.println("Getting email from Github");
                return oAuth2User.getAttribute("email").toString() != null
                        ? oAuth2User.getAttribute("email").toString()
                        : oAuth2User.getAttribute("login").toString() + "@github.com";
            }
        } else {
            System.out.println("Getting email from local storage");
            return authentication.getName();
        }
        return "";
    }
}
