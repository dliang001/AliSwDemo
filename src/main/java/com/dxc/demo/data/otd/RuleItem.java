package com.dxc.demo.data.otd;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RuleItem {
	private String id;
	private String ruleName;
	private String ruleDetail;
    private Timestamp createDate;
    private Timestamp updateDate;
}

