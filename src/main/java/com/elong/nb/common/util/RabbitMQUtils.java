/**   
 * @(#)RabbitMQUtils.java	2017年11月6日	下午2:29:03	   
 *     
 * Copyrights (C) 2017艺龙旅行网保留所有权利
 */
package com.elong.nb.common.util;

import com.elong.jmsg.client.RabbitMQSender;

/**
 * 发送消息至RabbitMQ
 *
 * <p>
 * 修改历史:											<br>  
 * 修改日期    		修改人员   	版本	 		修改内容<br>  
 * -------------------------------------------------<br>  
 * 2017年11月6日 下午2:29:03   user     1.0    	初始化创建<br>
 * </p> 
 *
 * @author		zhangyang.zhu  
 * @version		1.0  
 * @since		JDK1.7
 */
public class RabbitMQUtils {
	
	/**
	 * @param message 消息对象
	 * @param customerTag  自定义消息标识，方便后续再数据库查询，最好唯一
	 * @param messageName 消息队列名称
	 * */
	public static <T> void sendMsg(T message,String customerTag,String messageName){
		RabbitMQSender sender=new RabbitMQSender();
		sender.send(message, customerTag, messageName);
	}
}
