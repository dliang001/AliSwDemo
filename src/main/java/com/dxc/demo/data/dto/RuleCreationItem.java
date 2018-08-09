package com.dxc.demo.data.dto;

import lombok.Data;

import java.util.List;

@Data
public class RuleCreationItem {
    private String id;
    private String ruleName;
    private List<RuleCreationItemDetail> ruleCreationItemDetails;
}
