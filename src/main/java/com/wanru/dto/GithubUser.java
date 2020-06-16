package com.wanru.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by FangWei on 2020-03-08.
 */
@Component
@Data
public class GithubUser {
    private Long id;
    private String name;
    private String bio ;
    private String login ;
    private String node_id ;
    private String avatar_url ;

}
