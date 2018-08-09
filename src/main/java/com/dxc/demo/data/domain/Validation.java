package com.dxc.demo.data.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="VALIDATION")
@Data
public class Validation implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name="ID")
    private String id;

    @Column(name="A")
    private String a;

    @Column(name="B")
    private String b;

    @Column(name="C")
    private String c;
}