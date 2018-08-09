package com.dxc.demo.data.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 */
@Entity
@Table(name = "SW_METADATA")
@Data
@DynamicUpdate
public class Metadata implements Serializable {

    private static final long serialVersionUID = -171385502609610776L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "ID")
    private String id;

    /**
     * 名称
     */
    @Column(name = "NAME")
    private String name;

    /**
     * 代码
     */
    @Column(name = "CODE")
    private String code;

    /**
     * 属性
     */
    @Column(name = "ATTRIBUTES")
    private String attributes;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "DESCRIPTION", nullable = false, columnDefinition = "Text")
    private String description;

    @Column(name = "CREATE_DATE", insertable = false, updatable = false)
    private LocalDateTime createDate;

    @Column(name = "CREATOR_ID")
    private String creatorId;

    @Column(name = "UPDATE_DATE", insertable = false, updatable = false)
    private LocalDateTime updateDate;

    @Column(name = "UPDATE_ID")
    private String updateId;
}