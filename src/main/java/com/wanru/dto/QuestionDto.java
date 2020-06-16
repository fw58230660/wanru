package com.wanru.dto;

import com.wanru.model.User;
import lombok.Data;

@Data
public class QuestionDto {
  private Integer id;

  private String title;

  private Long createTime;

  private Long modifiedTime;

  private Integer creator;

  private Integer commentCount;

  private Integer viewCount;

  private Integer likeCount;

  private String tag;

  private String description;

  private User user;

}
