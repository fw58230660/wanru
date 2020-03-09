package com.wanru.dto;

import org.springframework.stereotype.Component;

/**
 * Created by FangWei on 2020-03-08.
 */
@Component
public class GithubUser {
    private Long id;
    private String name;
    private String bio ;
    private String login ;
    private String node_id ;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNode_id() {
        return node_id;
    }

    public void setNode_id(String node_id) {
        this.node_id = node_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
