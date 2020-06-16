package com.wanru.Controller;

import com.wanru.dto.QuestionDto;
import com.wanru.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by FangWei on 2020-06-11.
 */
@Controller
public class QuestionController {
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id, Model model) {
        QuestionDto questionDto = questionService.getById(id);
        questionService.incView(id);
        model.addAttribute("questionDto",questionDto);
        return "question";
    }
}
