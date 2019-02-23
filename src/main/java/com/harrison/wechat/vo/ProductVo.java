package com.harrison.wechat.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductVo {

    @JsonProperty("name") // 返回给前端的字段名
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;
}
