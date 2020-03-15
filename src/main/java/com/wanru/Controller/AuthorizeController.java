package com.wanru.Controller;

import com.wanru.dto.AccessTokenDTO;
import com.wanru.dto.GithubUser;
import com.wanru.mapper.UserMapper;
import com.wanru.model.User;
import com.wanru.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * Created by fw on 2020-03-08.
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.Redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                            HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        System.out.println(githubUser.toString());
        if (githubUser != null) {
            User user = new User();
            user.setName(githubUser.getName());
            user.setToken(UUID.randomUUID().toString());
            user.setAccount_id(String.valueOf(githubUser.getId()));
            user.setCreate_time(System.currentTimeMillis());
            user.setModified_time(user.getCreate_time());
            userMapper.insert(user);
            //登录成功  记录cookie和session
            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        } else {
            //登录失败,返回到登录页面
            return "redirect:/";
        }

    }
}
