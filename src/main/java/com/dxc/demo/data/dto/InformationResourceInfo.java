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
public class InformationResourceInfo implements Serializable {

    private static final long serialVersionUID = 8036489887509265579L;

    @ApiModelProperty(value = "ID", required = true)
    private String id;

    @NotNull
    @ApiModelProperty(value = "资源名称", required = true)
    private String name;

    @NotNull
    @ApiModelProperty(value = "系统名称", required = true)
    private String system;

    @NotNull
    @ApiModelProperty(value = "数据源驱动", required = true)
    private String datasourceDriver;

    @NotNull
    @ApiModelProperty(value = "数据源密码", required = true)
    private String datasourcePassword;

    @NotNull
    @ApiModelProperty(value = "数据源URL", required = true)
    private String datasourceUrl;

    @NotNull
    @ApiModelProperty(value = "数据源用户名", required = true)
    private String datasourceUsername;

    @ApiModelProperty(value = "数据库名", required = true)
    private String databaseName;

    @NotNull
    @ApiModelProperty(value = "数据库类型", required = true)
    private String databaseType;

    @ApiModelProperty(value = "数据库类型名称", required = false)
    private String databaseTypeName;

    @NotNull
    @ApiModelProperty(value = "字段名", required = true)
    private String tableField;

    @NotNull
    @ApiModelProperty(value = "表名", required = true)
    private String tableName;
//
//    @NotNull
//    @ApiModelProperty(value = "编码规则ID", required = true)
//    private String ruleId;
//
//    @ApiModelProperty(value = "编码规则名称", required = false)
//    private String ruleName;

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