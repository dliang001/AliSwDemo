package com.dxc.demo.data.otd;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CodeItem {
    private String id;
    private String code;
    private String ruleName;
    private Integer random;
    private Integer total;
    private Integer available;
    private Timestamp createDate;
    private Timestamp updateDate;
}

