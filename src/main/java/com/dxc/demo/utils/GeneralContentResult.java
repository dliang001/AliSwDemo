
package com.dxc.demo.utils;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName:GeneralContentRsult <br/>
 * Function: 带普通结果数据的返回结果. <br/>
 * Reason:	 TODO ADD REASON. <br/>
 * Date:     Oct 17, 2016 1:09:15 PM <br/>
 * @author   chenxj
 * @version  
 * @since    JDK 1.8
 * @see 	 
 */
@Data
public class GeneralContentResult<T> implements Serializable {

	/**
	 * serialVersionUID:TODO Description.
	 */
	private static final long serialVersionUID = -8104955278209569617L;
	//添加默认成功的放回code
	private String resultCode="100";
	private String detailDescription;
	private T resultContent;
}

