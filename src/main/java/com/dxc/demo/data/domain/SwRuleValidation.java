package com.dxc.demo.data.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="SW_RULE_VALIDATION")
@Data
public class SwRuleValidation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="ID")
    private String id;

    @Column(name="RES_NAME")
    private String resName;

    @Column(name="RULE_NAME")
    private String ruleName;

	@Column(name="CREATE_DATE", insertable = false, updatable = false)
	private Timestamp createDate;

	@Column(name="UPDATE_DATE", insertable = false, updatable = false)
	private Timestamp updateDate;
}