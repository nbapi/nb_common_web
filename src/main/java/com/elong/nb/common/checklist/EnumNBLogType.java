package com.elong.nb.common.checklist;

/**
 * nb日志类型
 * 
 * 记checklist日志的位置不同，所对应的类型也不同
 * 
 * @author lei.fang
 *
 */
public enum EnumNBLogType {

	OUTER_CONTROLLER,	//与外部接口一一对应的Controller层接口的日志，如booking.data;order.create;每一条日志，可以体现一次客户调用。
	INNER_CONTROLLER,	//内部接口，也处于Controller层，如web_rule服务、web_ms、web_user等。被其他服务调用。
	JOB_CONTROLLER,		//job的controller层日志类型。
	DAO;				//非Controller层的日志，访问后端依赖端的日志，目前没有细分，统一叫DAO层，如访问thrift、wcf、soa、db、redis等
}
