/**   
 * @(#)RedisDistributedLock.java	2017年9月29日	下午3:04:26	   
 *     
 * Copyrights (C) 2017艺龙旅行网保留所有权利
 */
package com.elong.nb.common.distributed;

import groovy.transform.Synchronized;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

/**
 * redis 分布式锁
 *
 * <p>
 * 修改历史:											<br>  
 * 修改日期    		修改人员   	版本	 		修改内容<br>  
 * -------------------------------------------------<br>  
 * 2017年9月29日 下午3:04:26   user     1.0    	初始化创建<br>
 * </p> 
 *
 * @author		zhangyang.zhu  
 * @version		1.0  
 * @since		JDK1.7
 */
public class RedisDistributedLock implements IDistributedLock{
	private static final Log logger = LogFactory.getLog(RedisDistributedLock.class);
	
	private static JedisSentinelPool pool=null;
	private String key; //加锁key
	private int timeOut; //加锁时长
	private LockedOwnerInfo lockedOwnerInfo;
	private Jedis jedis;
	
	public RedisDistributedLock(String key,int timeOut) {
		this.key=key;
		this.timeOut=timeOut;
		//初始化LockedOwnerInfo
		this.lockedOwnerInfo=new LockedOwnerInfo();
		synchronized (RedisDistributedLock.class) {
			if(pool==null){
				createJedisPool();
			}
			if(pool!=null){
				this.jedis=pool.getResource();
			}
		}
	}
	
	private static void createJedisPool(){
		try {
			Properties p=getRedisConfigProperties("redis_sentinel");
			JedisPoolConfig poolConfig=loadPoolConfig(p);
			Set<String>sentinels=new HashSet<String>();
			if(p!=null){
				String redisIP=p.getProperty("redis.sentinels");
				String masterName=p.getProperty("redis.masterName");
				String[] ips=redisIP.split(";");
				for (String ip : ips) {
					sentinels.add(ip);
				}
				pool=new JedisSentinelPool(masterName, sentinels, poolConfig);
				//jedis=sentinelPool.getResource();
			}
		} catch (Exception e) {
			throw new RuntimeException("createJedis Exception:"+e);
		}
	}
	
	/**
	 * 获取redis配置文件
	 * */
	private static Properties getRedisConfigProperties(String fileName){
		InputStream in=null;
		Properties redisConfig=new Properties();
		try {
			in=RedisDistributedLock.class.getResourceAsStream("/conf/custom/env/" + fileName + ".properties");
			redisConfig.load(in);
			in.close();
		} catch (Exception e) {
			logger.error("Read the file " + fileName + " error", e);
			throw new RuntimeException("Read the file " + fileName + " error", e);
		}finally{
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return redisConfig;
	}
	
	private  static JedisPoolConfig loadPoolConfig(Properties resources) {
		JedisPoolConfig config = new JedisPoolConfig();
		Map<String, String> prop = new HashMap<String, String>();
		try {
			if (resources != null) {
				Enumeration<?> en = resources.propertyNames();
				while (en.hasMoreElements()) {
					String name = (String) en.nextElement();
					if (name.startsWith("redis.pool.")) {
						prop.put(name.substring(11),
								resources.getProperty(name));
					}
				}
			}
			config.setMaxTotal(prop.get("maxActive") == null ? 50 : Integer
					.valueOf(prop.get("maxActive")));
			config.setMaxIdle(prop.get("maxIdle") == null ? 5 : Integer
					.valueOf(prop.get("maxIdle")));
			config.setMaxWaitMillis(prop.get("maxWait") == null ? 5000
					: Integer.valueOf(prop.get("maxWait")));
			config.setTestOnBorrow(prop.get("testOnBorrow") == null ? true
					: Boolean.valueOf(prop.get("testOnBorrow")));

		} catch (Exception e) {
			logger.error("loadPoolConfig Exception:", e);
			throw new RuntimeException("loadPoolConfig Exception:",e);
		}
		return config;
	}

	/** 
	 * 加锁
	 *
	 * @return 
	 *
	 * @see com.elong.nb.common.distributed.IDistributedLock#lock()    
	 */
	@Override
	public boolean lock() {
		boolean locked=false;
		//不存在，创建并上锁
		try {
			if(StringUtils.isNotBlank(this.jedis.set(key, LockedOwnerInfo.toString(lockedOwnerInfo), "nx", "ex", timeOut))){
				locked=true;
			}
			logger.info("lock key:"+key);
		}catch(Exception e){
			logger.error("lock Exception:", e);
			throw new RuntimeException("lock Exception:",e);
		}
		return locked;
	}

	/** 
	 *	释放锁
	 *
	 *
	 * @see com.elong.nb.common.distributed.IDistributedLock#unLock()    
	 */
	@Override
	public boolean unLock() {
		boolean unLocked=false;
		try {
			LockedOwnerInfo redisLockedOwnerInfo=LockedOwnerInfo.fromString(this.jedis.get(key));
			if(redisLockedOwnerInfo!=null&&LockedOwnerInfo.isSameOwner(redisLockedOwnerInfo)){//且是同一个客户端才能删除锁
				unLocked=this.jedis.del(this.key)==1;
			}else if (redisLockedOwnerInfo==null) {
				unLocked=true;
			}
		} catch (Exception e) {
			logger.error("unLock Exception:", e);
			throw new RuntimeException("unLock Exception:",e);
		}
		return unLocked;
	}
}
