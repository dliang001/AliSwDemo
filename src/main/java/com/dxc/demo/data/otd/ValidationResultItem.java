package com.dxc.demo.data.otd;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ValidationResultItem {
	private String id;
	private String resName;
	private String record;
    private Timestamp createDate;
    private Timestamp updateDate;
}

