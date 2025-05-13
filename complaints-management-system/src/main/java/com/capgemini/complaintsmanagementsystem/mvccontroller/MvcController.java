package com.capgemini.complaintsmanagementsystem.mvccontroller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MvcController {

    @GetMapping("/user")
    public String showUser(){
        return "user";
    }

    @GetMapping("/admin")
    public String showAdmin(){
        return "admin";
    }
}
