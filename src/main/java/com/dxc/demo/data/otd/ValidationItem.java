package com.dxc.demo.data.otd;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ValidationItem {
	private String id;
	private String resName;
	private String ruleName;
    private Timestamp createDate;
    private Timestamp updateDate;
}

