package com.light.moon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/apply")
public class ApplyController {

    @RequestMapping
    public String index() {
        return "apply/index";
    }

}
