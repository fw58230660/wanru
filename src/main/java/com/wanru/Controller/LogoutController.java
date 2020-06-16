package com.wanru.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by FangWei on 2020-06-11.
 */
@Controller
public class LogoutController {
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        Cookie cookie = new Cookie("token", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        request.getSession().removeAttribute("user");
        return "redirect:/index";
    }
}
