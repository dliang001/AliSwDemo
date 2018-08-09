package com.dxc.demo.data.mapping;

import com.dxc.demo.data.domain.*;
import com.dxc.demo.data.dto.RuleCreationItem;
import com.dxc.demo.data.dto.RuleCreationItemDetail;
import com.dxc.demo.data.dto.ValidationCreationItem;
import com.dxc.demo.data.otd.CodeItem;
import com.dxc.demo.data.otd.RuleItem;
import com.dxc.demo.data.otd.ValidationItem;
import com.dxc.demo.data.otd.ValidationResultItem;
import com.dxc.demo.utils.ListUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RuleMapper {
    public static RuleItem mapToDomain(SwRule a) {
        if(a == null) return null;
        StringBuilder sb = new StringBuilder(""); // 组装规则详情string
        List<SwRuleDetail> swRuleDetails = a.getSwRuleDetails();
        if(ListUtils.isNotEmpty(swRuleDetails)){
            // 按orderNum升序
            swRuleDetails = swRuleDetails.stream().sorted(Comparator.comparing(SwRuleDetail::getOrderNum)).collect(Collectors.toList());
            for(int i = 0; i < swRuleDetails.size(); i ++){
                SwRuleDetail s = swRuleDetails.get(i);
                String key = s.getAttrKey();
                String value = "";
                // 1. 如果是年份、区、街道、供应商：转化成value
                // 2. 如果是随机数：转化成x位随机数
                if("attribute".equals(key)){
                    value = s.getAttrName();
                } else if("random".equals(key)){
                    value = s.getAttrValue() + "位随机数";
                } else {
                    value = s.getAttrValue();
                }
                sb.append(value);
                if(i != swRuleDetails.size() - 1){
                    sb.append("+");
                }
            }
        }
        RuleItem b = new RuleItem();
        b.setId(a.getId());
        b.setRuleName(a.getRuleName());
        b.setRuleDetail(sb.toString());
        b.setCreateDate(a.getCreateDate());
        b.setUpdateDate(a.getUpdateDate());
        return b;
    }

    public static SwRule mapToDomain(RuleCreationItem a) {
        if(a == null) return null;
        SwRule b = new SwRule();
        b.setId(a.getId());
        b.setRuleName(a.getRuleName());
        List<RuleCreationItemDetail> ruleCreationItemDetails = a.getRuleCreationItemDetails();
        if(ListUtils.isNotEmpty(ruleCreationItemDetails)){
            for(RuleCreationItemDetail r : ruleCreationItemDetails){
                b.addSwRuleDetail(mapToSpmAlterRuleItem(r));
            }
        }
        return b;
    }

    private static SwRuleDetail mapToSpmAlterRuleItem(RuleCreationItemDetail a) {
        if(a == null) return null;
        SwRuleDetail b = new SwRuleDetail();
        b.setAttrKey(a.getAttrKey());
        b.setAttrValue(a.getAttrValue());
        b.setAttrName(a.getAttrName());
        b.setOrderNum(a.getOrderNum());
        return b;
    }

    public static CodeItem mapToDomain(SwRuleCode a) {
        if(a == null) return null;
        CodeItem b = new CodeItem();
        b.setId(a.getId());
        b.setCode(a.getCode());
        b.setRuleName(a.getSwRule().getRuleName());
        b.setRandom(a.getRandom());
        b.setTotal(a.getTotal());
        b.setAvailable(a.getAvailable());
        b.setCreateDate(a.getCreateDate());
        b.setUpdateDate(a.getUpdateDate());
        return b;
    }

    public static ValidationItem mapToDomain(SwRuleValidation a) {
        if(a == null) return null;
        ValidationItem b = new ValidationItem();
        b.setId(a.getId());
        b.setResName(a.getResName());
        b.setRuleName(a.getRuleName());
        b.setCreateDate(a.getCreateDate());
        b.setUpdateDate(a.getUpdateDate());
        return b;
    }

    public static SwRuleValidation mapToDomain(ValidationCreationItem a) {
        if(a == null) return null;
        SwRuleValidation b = new SwRuleValidation();
        b.setId(a.getId());
        b.setResName(a.getResName());
        b.setRuleName(a.getRuleName());
        return b;
    }

    public static SwRuleValidationResult mapToDomain(String a, String resName) {
        SwRuleValidationResult b = new SwRuleValidationResult();
        b.setResName(resName);
        b.setRecord(a);
        return b;
    }

    public static ValidationResultItem mapToDomain(SwRuleValidationResult a) {
        if(a == null) return null;
        ValidationResultItem b = new ValidationResultItem();
        b.setId(a.getId());
        b.setResName(a.getResName());
        b.setRecord(a.getRecord());
        b.setCreateDate(a.getCreateDate());
        b.setUpdateDate(a.getUpdateDate());
        return b;
    }
}