/**   
 * @(#)AbstractValidate.java	2016年11月11日	下午2:33:31	   
 *     
 * Copyrights (C) 2016艺龙旅行网保留所有权利
 */
package com.elong.nb.common.model;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * (类型功能说明描述)
 *
 * <p>
 * 修改历史:											<br>  
 * 修改日期    		修改人员   	版本	 		修改内容<br>  
 * -------------------------------------------------<br>  
 * 2016年11月11日 下午2:33:31   user     1.0    	初始化创建<br>
 * </p> 
 *
 * @author		zhangyang.zhu  
 * @version		1.0  
 * @since		JDK1.7
 */
public class AbstractValidate {

	public <T> StringBuffer validateCondition(T t){
		ValidatorFactory vf = Validation.buildDefaultValidatorFactory();  
	    Validator validator = vf.getValidator();  
	    Set<ConstraintViolation<T>> validate = validator.validate(t); 
	    StringBuffer buffer=new StringBuffer();
	    for (ConstraintViolation<T> cv : validate) { 
	    	buffer.append(cv.getMessage()).append(" ");
	    	break;
	    }  
	   
	    return buffer;
	}
}
