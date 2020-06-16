package com.wanru.Controller;

import com.wanru.dto.PageDTO;
import com.wanru.model.User;
import com.wanru.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by FangWei on 2020-06-05.
 */
@Controller
public class ProfileController {

    @Autowired
    private QuestionService questionService;

    @RequestMapping(value = "/profile/{action}", method = RequestMethod.GET)
    public String profile(@PathVariable(name = "action") String action, Model model,
                          HttpServletRequest request
            , @RequestParam(name = "page", defaultValue = "1") Integer page
            , @RequestParam(name = "size", defaultValue = "2") Integer size) {

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/index";
        }
        if ("questions".equals(action)) {
            model.addAttribute("section", "questions");
            model.addAttribute("sectionName", "我关注的问题");
        }
        if ("replies".equals(action)) {
            model.addAttribute("section", "replies");
            model.addAttribute("sectionName", "最新回复");
        }
        PageDTO pageDTO = questionService.profileList(user.getId(), page, size);
        model.addAttribute("pageDTO", pageDTO);
        return "profile";
    }
}
