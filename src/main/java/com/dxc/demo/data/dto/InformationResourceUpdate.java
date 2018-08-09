package com.dxc.demo.data.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
public class InformationResourceUpdate extends InformationResourceInfo {

    private static final long serialVersionUID = -1667210464838828530L;

    @Override
    @JsonIgnore
    public String getId() {
        return super.getId();
    }

    @Override
    @JsonIgnore
    public String getDatabaseTypeName() {
        return super.getDatabaseTypeName();
    }
//
//    @Override
//    @JsonIgnore
//    public String getRuleName() {
//        return super.getRuleName();
//    }

    @Override
    @JsonIgnore
    public LocalDateTime getUpdateDate() {
        return super.getUpdateDate();
    }

    @Override
    @JsonIgnore
    public LocalDateTime getCreateDate() {
        return super.getCreateDate();
    }
}
