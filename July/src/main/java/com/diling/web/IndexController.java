package com.diling.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String homePage() {
        System.out.println("leontest");
        return "home";
    }
}