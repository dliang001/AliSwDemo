package com.dxc.demo.data.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
@Data
public class MetadataInfo implements Serializable {
    
    private static final long serialVersionUID = 7900940543823132724L;
    
    @ApiModelProperty(value = "ID", required = true)
    private String id;
    
    @NotNull
    @ApiModelProperty(value = "名称", required = true)
    private String name;
    
    @NotNull
    @ApiModelProperty(value = "代码", required = true)
    private String code;
    
    @NotNull
    @ApiModelProperty(value = "属性", required = true)
    private String attributes;
    
    @ApiModelProperty(value = "属性名称", required = false)
    private String attributesName;
    
    @ApiModelProperty(value = "描述", required = false)
    private String description;
    
    @ApiModelProperty(value = "创建时间", required = false)
    private LocalDateTime createDate;
    
    @NotNull
    @ApiModelProperty(value = "创建人", required = true)
    private String creatorId;
    
    // @Convert(converter = LocalDateTimeConverter.class)
    @ApiModelProperty(value = "更新时间", required = false)
    private LocalDateTime updateDate;
    
    @NotNull
    @ApiModelProperty(value = "更新人", required = true)
    private String updateId;
}