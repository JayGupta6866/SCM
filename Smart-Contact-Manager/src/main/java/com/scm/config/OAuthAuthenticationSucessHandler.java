package com.scm.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.entities.Providers;
import com.scm.entities.User;
import com.scm.helpers.AppConstants;
import com.scm.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSucessHandler implements AuthenticationSuccessHandler {

    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSucessHandler.class);

    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logger.info("onAuthenticationSuccess Handler");

        // identify the provider
        var OAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String authorizedClientRegistraionId = OAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
        logger.info(authorizedClientRegistraionId);

        var oAuthUUser = (DefaultOAuth2User) authentication.getPrincipal();
        oAuthUUser.getAttributes().forEach((key, value) -> {
            logger.info("{} => {}", key, value);
        });

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        user.setEnabled(true);
        user.setEmailVerified(true);

        if (authorizedClientRegistraionId.equalsIgnoreCase("google")) {

            user.setEmail(oAuthUUser.getAttribute("email").toString());
            user.setProfilePic(oAuthUUser.getAttribute("picture").toString());
            user.setName(oAuthUUser.getAttribute("name").toString());
            user.setProvider(Providers.Gooogle);
            user.setProviderUserId(oAuthUUser.getName());
            user.setAbout("This user is set using google");
            user.setPassword("password");

        } else if (authorizedClientRegistraionId.equalsIgnoreCase("github")) {

            String email = oAuthUUser.getAttribute("email").toString() != null
                    ? oAuthUUser.getAttribute("email").toString()
                    : oAuthUUser.getAttribute("login").toString() + "@github.com";
            user.setEmail(email);
            user.setProfilePic(oAuthUUser.getAttribute("avatar_url").toString());
            user.setName(oAuthUUser.getAttribute("name").toString());
            user.setProvider(Providers.Github);
            user.setProviderUserId(oAuthUUser.getAttribute("name"));
            user.setAbout("This user is set using github");
            user.setPassword("password");

        } else {
            logger.info("Unsupported OAuth2 provider: " + authorizedClientRegistraionId);
            // return if unsupported provider
        }

        /*
         * DefaultOAuth2User user = (DefaultOAuth2User) authentication.getPrincipal();
         * 
         * // logger.info(user.getName());
         * // user.getAttributes().forEach((key,value)->{
         * // logger.info("{} => {}",key,value);
         * // logger.info(user.getAuthorities().toString());
         * // });
         * String email = user.getAttribute("email").toString();
         * String name = user.getAttribute("name").toString();
         * String picture = user.getAttribute("picture").toString();
         * 
         * // create user and save to database
         * 
         * 
         * user1.setEmail(email);
         * user1.setName(name);
         * user1.setProfilePic(picture);
         * 
         * 
         * user1.setProvider(Providers.Gooogle);
         * user1.setEnabled(true);
         * user1.setEmailVerified(true);
         * user1.setProviderUserId(user.getName());
         * 
         * user1.setAbout("This user is created by Google");
         * 
         * 
         */

        User user2 = userRepo.findByEmail(user.getEmail()).orElse(null);
        if (user2 == null) {
            userRepo.save(user);
            logger.info("User created successfully: " + user.getEmail());
        }
        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/dashboard");
    }

}
