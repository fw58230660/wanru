package com.wanru.Controller;

import com.wanru.dto.PageDTO;
import com.wanru.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by fw on 2020-03-03.
 */

@Controller
public class IndexController {
    @Autowired
    private QuestionService questionService;

    @GetMapping("/index")
    public String index( Model model
            , @RequestParam(name = "page", defaultValue = "1") Integer page
            , @RequestParam(name = "size", defaultValue = "5") Integer size) {
        PageDTO pageDTO = questionService.list(page,size);
        model.addAttribute("pageDTO", pageDTO);
        return "index";
    }
}
