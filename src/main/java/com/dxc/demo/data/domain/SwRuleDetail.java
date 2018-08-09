package com.dxc.demo.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="SW_RULE_DETAIL")
@Data
public class SwRuleDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="ID")
    private String id;

    @Column(name="ATTR_KEY")
    private String attrKey;

    @Column(name="ATTR_VALUE")
    private String attrValue;

    @Column(name="ATTR_NAME")
    private String attrName;

    @Column(name="ORDER_NUM")
    private Integer orderNum;

	@Column(name="CREATE_DATE", insertable = false, updatable = false)
	private Timestamp createDate;

	@Column(name="UPDATE_DATE", insertable = false, updatable = false)
	private Timestamp updateDate;

    @ManyToOne
    @JoinColumn(name="RULE_ID")
    @JsonIgnore
    private SwRule swRule;

    @Override
    public String toString() {
        return "SwRuleDetail{" +
                "id='" + id + '\'' +
                ", attrKey='" + attrKey + '\'' +
                ", attrValue='" + attrValue + '\'' +
                ", orderNum=" + orderNum +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}