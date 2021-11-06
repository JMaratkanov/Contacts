package com.Johann_M;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControl {

    @GetMapping("")
    public String showHomePage(){
        return "index";
    }
}
