package com.wanru.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by fw on 2020-03-03.
 */

@Controller
public class HelloController {
    @GetMapping("/wanru")
    public String hello(@RequestParam(name = "name",required = false) String name, Model model){
        model.addAttribute("name",name);
        return "hello";
    }
}
