package com.wanru.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * Created by FangWei on 2020-03-08.
 */
@Component
@Data
public class AccessTokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
