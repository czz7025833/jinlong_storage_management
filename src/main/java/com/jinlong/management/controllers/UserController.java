package com.jinlong.management.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserController {


    @GetMapping("/create-user")
    public String addUser() {
        return "create-user";
    }

    @GetMapping("/edit-user/{id}")
    public String editUser(@PathVariable(value = "id") Long id) {
        return "edit-user";
    }
}
