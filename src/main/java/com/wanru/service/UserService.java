package com.wanru.service;

import com.wanru.mapper.UserMapper;
import com.wanru.model.User;
import com.wanru.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FangWei on 2020-06-11.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void addOrUpdate(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> dbUser = userMapper.selectByExample(userExample);
        if (dbUser != null && dbUser.size() > 0) {//更新
            User updateUser = new User();
            updateUser.setModifiedTime(System.currentTimeMillis());
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            UserExample example = new UserExample();
            example.createCriteria().andIdEqualTo(dbUser.get(0).getId());
            userMapper.updateByExampleSelective(updateUser, example);
        } else {//添加
            user.setCreateTime(System.currentTimeMillis());
            user.setModifiedTime(user.getCreateTime());
            userMapper.insert(user);
        }
    }
}
