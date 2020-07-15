package com.example.auth.controller;

import com.example.auth.model.User;
import com.example.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/register")
    public ModelAndView getRegisterPgge(){
        return  new ModelAndView("register","user",new User());
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user")  User user, Model model){
        if(userService.register(user)){
            model.addAttribute("message","you are registered");
            model.addAttribute("user", new User());
            return "redirect:/login";
        }else {
            model.addAttribute("message","username already exists");
            model.addAttribute("user", new User());
            return "register";
        }
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        return  new ModelAndView("login","user",new User());
    }
    @PostMapping("/login")
    public String loginUser(@ModelAttribute("user")  User user, Model model){
        String status = userService.login(user);
        switch (status) {
            case "A":
                return "homeA";
            case "B":
                return "homeB";
            case "C":
                return "homeC";
            case "D":
                model.addAttribute("message", "you have register first");
                model.addAttribute("user", new User());
                return "register";
            default:
                model.addAttribute("message", "password incorrect");
                model.addAttribute("user", new User());
                return "login";

        }
    }
}
