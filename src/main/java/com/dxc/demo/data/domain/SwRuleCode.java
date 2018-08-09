package com.dxc.demo.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name="SW_RULE_CODE")
@Data
public class SwRuleCode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="ID")
    private String id;

    @Column(name="CODE")
    private String code;

    @Column(name="RANDOM")
    private Integer random;

    @Column(name="TOTAL")
    private Integer total;

    @Column(name="AVAILABLE")
    private Integer available;

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
        return "SwRuleCode{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", random=" + random +
                ", total=" + total +
                ", available=" + available +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}