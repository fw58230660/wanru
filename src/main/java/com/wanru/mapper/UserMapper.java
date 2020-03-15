package com.wanru.mapper;

import com.wanru.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by FangWei on 2020-03-14.
 */
@Mapper
public interface UserMapper {
    @Insert("insert into User (account_id,name,token,create_time,modified_time) values(#{account_id},#{name},#{token},#{create_time},#{modified_time})")
    void insert(User user);
}
