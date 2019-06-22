package com.cilicili.advertisement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	@RequestMapping("hello")
	public String hello(Model model) {
        String name = "lmr";
        model.addAttribute("name", name);
        return "hello";
	}

}
