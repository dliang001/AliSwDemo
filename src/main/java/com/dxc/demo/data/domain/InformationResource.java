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
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

/**
 * The persistent class for the SW_INFORMATION_RESOURCE database table.
 *
 */
@Data
@Entity
@Table(name = "SW_INFORMATION_RESOURCE")
@NamedQuery(name = "InformationResource.findAll", query = "SELECT s FROM InformationResource s order by s.name, s.system")
@DynamicUpdate
public class InformationResource implements Serializable {

    private static final long serialVersionUID = 3495472043143392844L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SYSTEM")
    private String system;

    @Column(name = "DATASOURCE_DRIVER")
    private String datasourceDriver;

    @Column(name = "DATASOURCE_PASSWORD")
    private String datasourcePassword;

    @Column(name = "DATASOURCE_URL")
    private String datasourceUrl;

    @Column(name = "DATASOURCE_USERNAME")
    private String datasourceUsername;

    @Column(name = "DATABASE_NAME")
    private String databaseName;

    @Column(name = "DATABASE_TYPE")
    private String databaseType;

    @Column(name = "TABLE_NAME")
    private String tableName;

    @Column(name = "TABLE_FIELD")
    private String tableField;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "DESCRIPTION", nullable = false, columnDefinition = "Text")
    private String description;

    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;

    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;

    @Column(name = "CREATOR_ID")
    private String creatorId;

    @Column(name = "UPDATE_ID")
    private String updateId;
//
//    // bi-directional one-to-one association to SwRule
//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "RULE_ID")
//    private SwRule swRule;

}