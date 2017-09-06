package com.hafiz.demo.controller;

import com.hafiz.demo.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Desc:
 * Created by hafiz.zhang on 2017/9/6.
 */
@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "index";
    }

    @RequestMapping("test")
    @ResponseBody
    public String test() {
        return "test hello world";
    }
}
