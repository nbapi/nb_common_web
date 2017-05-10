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

	// ms关系
	public static final String KEY_ID_S_M = "data.ms.sid_mid";
	public static final ICacheKey CacheKey_KEY_ID_S_M = RedisManager.getCacheKey(KEY_ID_S_M);

	public static final String KEY_ID_M_S = "data.ms.mid_sid";
	public static final ICacheKey CacheKey_KEY_ID_M_S = RedisManager.getCacheKey(KEY_ID_M_S);

	public static final String KEY_Hotel_S_M = "data.hotel.sid_mid";
	public static final ICacheKey CacheKey_KEY_Hotel_S_M = RedisManager.getCacheKey(KEY_Hotel_S_M);

	public static final String KEY_Hotel_M_S = "data.hotel.mid_sid";
	public static final ICacheKey CacheKey_KEY_Hotel_M_S = RedisManager.getCacheKey(KEY_Hotel_M_S);

	// nb_job_incr
	public static final String StateSyncTimeKey = "Incr.State.Time";
	public static final ICacheKey CacheKey_StateSyncTimeKey = RedisManager.getCacheKey(StateSyncTimeKey);

	public static final String KEY_Inventory_LastID = "Incr.Inventory.LastID";
	public static final ICacheKey CacheKey_KEY_Inventory_LastID = RedisManager.getCacheKey(KEY_Inventory_LastID);

	public static final String KEY_Rate_LastID = "Incr.Rate.LastID";
	public static final ICacheKey CacheKey_KEY_Rate_LastID = RedisManager.getCacheKey(KEY_Rate_LastID);

	public static final String KEY_Proxy_CardNo_OrderFrom = "Proxy.CardNo.OrderFrom.{0}";

	// nb_job_rule
	public static final String BLACK_LIST_HOTEL_CODE_REDIS_PREFIX = "instant.new.black.%s";

	public static final String BLACK_LIST_RULE_REDIS_PREFIX = "instant.new.blacklist.rules";
	public static final ICacheKey CacheKey_BLACK_LIST_RULE_REDIS_PREFIX = RedisManager.getCacheKey(BLACK_LIST_RULE_REDIS_PREFIX);

	public static final String BLACK_LIST_LAST_UPDATE_TIME = "instant.new.blacklist.lastupdatetime";
	public static final ICacheKey CacheKey_BLACK_LIST_LAST_UPDATE_TIME = RedisManager.getCacheKey(BLACK_LIST_LAST_UPDATE_TIME);

	public static final String BBLACK_LIST_LAST_UPDATE_TIME = "inv.new.blacklist.lastupdate";
	public static final ICacheKey CacheKey_BBLACK_LIST_LAST_UPDATE_TIME = RedisManager.getCacheKey(BBLACK_LIST_LAST_UPDATE_TIME);

	public static final String INV_BLACK_LIST_REDIS_PREFIX = "inv.new.blacklist.%s";

	public static final String INV_BLACK_LIST_IDS_REDIS_PREFIX = "inv.new.blacklist.ids";
	public static final ICacheKey CacheKey_INV_BLACK_LIST_IDS_REDIS_PREFIX = RedisManager.getCacheKey(INV_BLACK_LIST_IDS_REDIS_PREFIX);

	// nb_job_data
	public static final String KEY_IncrSyncHotelMSCache_LastID = "data.hotel.relation.lastId";
	public static final ICacheKey CacheKey_IncrSyncHotelMSCache_LastID = RedisManager.getCacheKey(KEY_IncrSyncHotelMSCache_LastID);

	public static final String KEY_RoomType_H_MS = "data.room.shid_msid";
	public static final ICacheKey CacheKey_KEY_RoomType_H_MS = RedisManager.getCacheKey(KEY_RoomType_H_MS);

	public static final String KEY_SupplierMap = "data.hotel.supplier";
	public static final ICacheKey CacheKey_KEY_SupplierMap = RedisManager.getCacheKey(KEY_SupplierMap);

	public static final String KEY_SyncTime_MHotelBase = "data.ms.mhotelbase.time";
	public static final ICacheKey CacheKey_KEY_SyncTime_MHotelBase = RedisManager.getCacheKey(KEY_SyncTime_MHotelBase);

	public static final String KEY_SyncTime_Hotel = "data.ms.hotel.time";
	public static final ICacheKey CacheKEY_KEY_SyncTime_Hotel = RedisManager.getCacheKey(KEY_SyncTime_Hotel);
	
	// nb_web_data
	public static final String KEY_RackRateRecordsSet = "Data.RackRate.Record";
	public static final ICacheKey CacheKEY_KEY_RackRateRecordsSet = RedisManager.getCacheKey(KEY_RackRateRecordsSet);
	
	public static final String KEY_Minitor_OrderFrom_ProjectName = "Minitor.OrderFrom.ProjectName.%s";
	public static final String KEY_SupplierCooType = "SupplierCooType_%d";

	// nb_job_static
	public static final String KEY_GenerateXMLTaskHOTELID_STATUS = "GenerateXMLTaskHOTELID_STATUS";
	public static final ICacheKey CacheKey_KEY_GenerateXMLTaskHOTELID_STATUS = RedisManager.getCacheKey(KEY_GenerateXMLTaskHOTELID_STATUS);

	public static final String KEY_HOTEL_HotelXMLINDEX = "h:%s:hotelxmlindex";

	public static final String KEY_HOTEL_CITY = "data.hotel.city";
	public static final ICacheKey CacheKey_KEY_HOTEL_CITY = RedisManager.getCacheKey(KEY_HOTEL_CITY);
	
	public static final String KEY_HOTEL_CITYNAME="data.hote.cityName.%s";

	public static final String KEY_HOTEL_BASEINFO = "h:%s:baseinfo:%s";

	public static final String KEY_HOTEL_HOTELROOM = "h:%s:hotelroom:%s";

	public static final String KEY_HOTEL_HOTELIMAGES = "h:%s:hotelimages";

	public static final String KEY_HOTEL_HOTELThumbNailUrlImage = "h:%s:hotelthumbnailurlimage";

	public static final String KEY_HOTEL_RoomThumbNailUrlImage = "h:%s:room%s:thumbnailurlimage";

	public static final String KEY_HOTEL_HOTELCOMMENT = "h:%s:hotelcomment";

	public static final String KEY_HOTEL_RatePlan = "h:%s:hotelrateplan";

	public static final String KEY_HOTEL_CACHE_CHANGED_QUEUE = "HotelCacheChangedQueue";
	public static final ICacheKey CacheKey_KEY_HOTEL_CACHE_CHANGED_QUEUE = RedisManager.getCacheKey(KEY_HOTEL_CACHE_CHANGED_QUEUE);

	public static final String KEY_HOTEL_GEO = "h:geo:%s";

	// nb_job_public
	public static final String KEY_CURRENCY = "data.currency";
	public static final ICacheKey CacheKey_KEY_CURRENCY = RedisManager.getCacheKey(KEY_CURRENCY);
	
	public static final String KEY_LAST_URGE="user.urge.%s";
	
	public static final String KEY_OPENAPI_APPSERVER_CONFIG="openapi.appserver.config";
	public static final ICacheKey CacheKey_KEY_OPENAPI_APPSERVER_CONFIG = RedisManager.getCacheKey(KEY_OPENAPI_APPSERVER_CONFIG);
	
	public static final String KEY_CommisionLevel_Version="commisionlevel.version";
	public static final ICacheKey CacheKey_KEY_CommisionLevel_Version = RedisManager.getCacheKey(KEY_CommisionLevel_Version);
	
	public static final String KEY_HotelCodeBlackList="hotelcode.blacklist";
	public static final ICacheKey CacheKey_KEY_HotelCodeBlackList = RedisManager.getCacheKey(KEY_HotelCodeBlackList);
	
	public static final String KEY_KAOrBuyoutList="ka.buyout.list.%s";
	//public static final ICacheKey CacheKey_KEY_PAOrBuyoutList = RedisManager.getCacheKey(KEY_PAOrBuyoutList);
	
	public static final String KEY_KAOrBuyoutListVersion="ka.buyout.list.version";
	public static final ICacheKey CacheKey_KEY_KAOrBuyoutListVersion = RedisManager.getCacheKey(KEY_KAOrBuyoutListVersion);
	
	
	public static final String KEY_DISTRICTS_PROVINCE="data.districts.province";
	//public static final String KEY_DISTRICTS_PROVINCE="data.districts.province.%s";
	public static final ICacheKey CacheKey_KEY_DISTRICTS_PROVINCE=RedisManager.getCacheKey(KEY_DISTRICTS_PROVINCE);
}
