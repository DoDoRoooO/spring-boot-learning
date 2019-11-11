package com.spring.boot.example.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonPropertyOrder(value = {"content", "title"}) // 排序
public class Article {

    //@JsonIgnore
    //忽略字段
    private Long id;

//    @JsonProperty("auther")
    //修改字段名称
    private String author;
    private String title;
    @NotEmpty(message = "文章内容不能为空，请检查您的输入")
    private String content;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    //非空的时候包含字段
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private List<Reader> reader;
}
