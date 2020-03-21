package com.jinlong.management.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {


    @GetMapping("/")
    public String index(){
        return "index";
    }


    @GetMapping("/create-product")
    public String addProduct() {
        return "create-product";
    }

    @GetMapping("/edit-product/{id}")
    public String editProduct(@PathVariable(value = "id") Long id) {
        return "edit-product";
    }
}
