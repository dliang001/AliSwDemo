package com.dxc.demo.data.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="SW_RULE")
@Data
public class SwRule implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="ID")
    private String id;

    @Column(name="RULE_NAME")
    private String ruleName;

	@Column(name="CREATE_DATE", insertable = false, updatable = false)
	private Timestamp createDate;

	@Column(name="UPDATE_DATE", insertable = false, updatable = false)
	private Timestamp updateDate;

    @OneToMany(mappedBy="swRule", cascade = CascadeType.ALL)
    private List<SwRuleDetail> swRuleDetails;

    public SwRuleDetail addSwRuleDetail(SwRuleDetail swRuleDetail) {
        if(null == swRuleDetails) swRuleDetails = new ArrayList<>();
        getSwRuleDetails().add(swRuleDetail);
        swRuleDetail.setSwRule(this);
        return swRuleDetail;
    }

    public SwRuleDetail removeSwRuleDetail(SwRuleDetail swRuleDetail) {
        if(null == swRuleDetails) swRuleDetails = new ArrayList<>();
        getSwRuleDetails().remove(swRuleDetail);
        swRuleDetail.setSwRule(null);
        return swRuleDetail;
    }
}