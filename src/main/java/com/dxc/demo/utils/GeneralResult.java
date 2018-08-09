
package com.dxc.demo.utils;

import java.io.Serializable;

import lombok.Data;

/**
 * ClassName:GeneralResult <br/>
 * Function: 不带内容的返回结果. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     Oct 17, 2016 1:32:04 PM <br/>
 * @author   chenxj
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Data
public class GeneralResult implements Serializable {

	/**
	 * serialVersionUID:TODO Description.
	 */
	private static final long serialVersionUID = 908203438033789670L;

	private String resultCode;
	private String detailDescription;
}

