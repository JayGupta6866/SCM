package com.scm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.entities.User;
import com.scm.forms.UserForm;
import com.scm.helpers.Message;
import com.scm.helpers.MessageType;
import com.scm.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        // System.out.println("Home Page");
        model.addAttribute("name", "User : Jay Gupta");
        model.addAttribute("role", "Developer");
        model.addAttribute("github", "https://github.com/JayGupta6866");
        return "home";
    }

    @RequestMapping("/about")
    public String aboutPage() {
        return "about";
    }

    @RequestMapping("/services")
    public String servicePage() {
        return "services";
    }

    @RequestMapping("/contact")
    public String contactPage() {
        return "contact";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage(Model model) {
        UserForm userForm = new UserForm();
        model.addAttribute("userForm", userForm);
        return "signup";
    }

    // processing the sign up
    @PostMapping("/register")
    public String processRegister(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, HttpSession session) {
        // fetch the form data
        // create class of Userform
        // check the modelAttribute
        System.out.println(userForm);
        // validate
        if(bindingResult.hasErrors()){
            return "signup";
        }

        // save todb
        // userService
        // here we have converted the data from userForm to Object User
        // commented this because this was not helping to pass default vaues
        // User user = User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .phoneNumber(userForm.getPhoneNumber())
        // .about(userForm.getAbout())
        // .profilePic("https://static-00.iconduck.com/assets.00/profile-default-icon-2048x2045-u3j7s5nj.png")
        // .build();

        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setAbout(userForm.getAbout());
        user.setProfilePic("https://static-00.iconduck.com/assets.00/profile-default-icon-2048x2045-u3j7s5nj.png");

        User savedUser = userService.saveUser(user);

        System.out.println("User Saved");

        // message success
        Message msg = Message.builder().content("Registration Successful !").type(MessageType.green).build();
        session.setAttribute("message", msg);

        // redirect to login
        return "redirect:/signup";
    }
}
