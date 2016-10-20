/**   
 * @(#)RedisKeyConst.java	2016年10月19日	下午5:26:17	   
 *     
 * Copyrights (C) 2016艺龙旅行网保留所有权利
 */
package com.elong.nb.common.model;

import com.elong.nb.cache.ICacheKey;
import com.elong.nb.cache.RedisManager;

/**
 * 所有涉及到redis的key，都以常量形式加到这
 *
 * <p>
 * 修改历史:											<br>  
 * 修改日期    		修改人员   	版本	 		修改内容<br>  
 * -------------------------------------------------<br>  
 * 2016年10月19日 下午5:26:17   suht     1.0    	初始化创建<br>
 * </p> 
 *
 * @author		suht  
 * @version		1.0  
 * @since		JDK1.7
 */
public interface RedisKeyConst {

	// key 常量
	// ms关系
	public static final String KEY_ID_S_M = "data.ms.sid_mid";

	public static final String KEY_ID_M_S = "data.ms.mid_sid";

	public static final String KEY_Hotel_S_M = "data.hotel.sid_mid";

	public static final String KEY_Hotel_M_S = "data.hotel.mid_sid";

	// nb_job_incr
	public static final String StateSyncTimeKey = "Incr.State.Time";

	public static final String KEY_Inventory_LastID = "Incr.Inventory.LastID";

	public static final String KEY_Rate_LastID = "Incr.Rate.LastID";

	public static final String KEY_Proxy_CardNo_OrderFrom = "Proxy.CardNo.OrderFrom.{0}";

	// nb_job_rule
	public static final String BLACK_LIST_HOTEL_CODE_REDIS_PREFIX = "instant.new.black.%s";

	public static final String BLACK_LIST_RULE_REDIS_PREFIX = "instant.new.blacklist.rules";

	public static final String BLACK_LIST_LAST_UPDATE_TIME = "instant.new.blacklist.lastupdatetime";

	public static final String BBLACK_LIST_LAST_UPDATE_TIME = "inv.new.blacklist.lastupdate";

	public static final String INV_BLACK_LIST_REDIS_PREFIX = "inv.new.blacklist.%s";

	public static final String INV_BLACK_LIST_IDS_REDIS_PREFIX = "inv.new.blacklist.ids";

	// ICacheKey 常量
	public static final ICacheKey CacheKey_KEY_Rate_LastID = RedisManager.getCacheKey(KEY_Rate_LastID);

	public static final ICacheKey CacheKey_KEY_Inventory_LastID = RedisManager.getCacheKey(KEY_Inventory_LastID);

	public static final ICacheKey CacheKey_StateSyncTimeKey = RedisManager.getCacheKey(StateSyncTimeKey);

	public static final ICacheKey CacheKey_KEY_ID_S_M = RedisManager.getCacheKey(KEY_ID_S_M);

	public static final ICacheKey CacheKey_KEY_ID_M_S = RedisManager.getCacheKey(KEY_Hotel_M_S);

	public static final ICacheKey CacheKey_KEY_Hotel_S_M = RedisManager.getCacheKey(KEY_Hotel_S_M);

	public static final ICacheKey CacheKey_KEY_Hotel_M_S = RedisManager.getCacheKey(KEY_Hotel_M_S);

	public static final ICacheKey CacheKey_BLACK_LIST_RULE_REDIS_PREFIX = RedisManager.getCacheKey(BLACK_LIST_RULE_REDIS_PREFIX);

	public static final ICacheKey CacheKey_BLACK_LIST_LAST_UPDATE_TIME = RedisManager.getCacheKey(BLACK_LIST_LAST_UPDATE_TIME);

	public static final ICacheKey CacheKey_BBLACK_LIST_LAST_UPDATE_TIME = RedisManager.getCacheKey(BBLACK_LIST_LAST_UPDATE_TIME);

	public static final ICacheKey CacheKey_INV_BLACK_LIST_IDS_REDIS_PREFIX = RedisManager.getCacheKey(INV_BLACK_LIST_IDS_REDIS_PREFIX);

}
