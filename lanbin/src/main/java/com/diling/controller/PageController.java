package com.diling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping(value = "/")
    public String indexPage() {
        return "home";
    }

    @GetMapping(value = "/home")
    public String homePage() {
        return "home";
    }

    @GetMapping(value = "/measure")
    public String measurePage() {
        return "measure";
    }
}
