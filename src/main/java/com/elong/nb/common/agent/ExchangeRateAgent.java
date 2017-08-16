/**   
 * @(#)ExchangeRateAgent.java	2017年8月7日	下午5:14:53	   
 *     
 * Copyrights (C) 2017艺龙旅行网保留所有权利
 */
package com.elong.nb.common.agent;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.elong.nb.cache.RedisManager;
import com.elong.nb.common.model.EnumCurrencyCode;
import com.elong.nb.common.model.ExchangeRate;
import com.elong.nb.common.model.RedisKeyConst;
import com.elong.nb.common.util.CommonsUtil;

/**
 * 汇率数据访问代理
 *
 * <p>
 * 修改历史:											<br>  
 * 修改日期    		修改人员   	版本	 		修改内容<br>  
 * -------------------------------------------------<br>  
 * 2017年8月7日 下午5:14:53   suht     1.0    	初始化创建<br>
 * </p> 
 *
 * @author		suht  
 * @version		1.0  
 * @since		JDK1.7
 */
public class ExchangeRateAgent {

	private static final Log logger = LogFactory.getLog(ExchangeRateAgent.class);

	private static RedisManager redisManager = RedisManager.getInstance("redis_shared", "redis_shared");

	private static ExchangeRateCache exchangeRateCache = new ExchangeRateCache();

	static {
		long delay = 1;
		String delayStr = CommonsUtil.CONFIG_PROVIDAR.getProperty("exchangerate_update_delay");
		if (!StringUtils.isEmpty(delayStr))
			delay = Long.parseLong(delayStr);

		ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
		service.scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {
				boolean result = checkAndUpdate();
				if (result) {
					logger.info("Update exchangerate cache success!" + new Date());
				} else {
					logger.error("Update exchangerate cache failed!" + new Date());
				}
			}

		}, delay, delay, TimeUnit.MINUTES);
		if (!checkAndUpdate())
			throw new RuntimeException("Exchangerate cache init failed!");
	}

	public static Double getExchangeRate(String currencyCodeStr) {
		if (StringUtils.isEmpty(currencyCodeStr)) {
			throw new IllegalArgumentException("the parameter[currencyCodeStr] must not be null or empty.");
		}
		EnumCurrencyCode currencyCode = EnumCurrencyCode.forValue(currencyCodeStr);
		if (currencyCode == null) {
			throw new IllegalArgumentException("error parameter[currencyCodeStr] = " + currencyCodeStr
					+ ",please see the enum[EnumCurrencyCode].");
		}
		return getExchangeRate(currencyCode);
	}

	public static Double getExchangeRate(EnumCurrencyCode currencyCode) {
		return exchangeRateCache.getExchangeRate(currencyCode);
	}

	public static List<ExchangeRate> getAllExchangeRate() {
		return exchangeRateCache.getAllExchangeRate();
	}

	private static boolean checkAndUpdate() {
		try {
			Map<String, String> currencyRateMap = redisManager.hashGetAll(RedisManager.getCacheKey(RedisKeyConst.KEY_CURRENCY));
			int redisDataCount = currencyRateMap == null ? 0 : currencyRateMap.size();
			int memoryDataCount = exchangeRateCache.getExchangeRateCount();
			logger.info("redisDataCount = " + redisDataCount + ",memoryDataCount = " + memoryDataCount);
			if (redisDataCount == 0)
				return false;
			return exchangeRateCache.update(currencyRateMap);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return false;
		}
	}

	private static class ExchangeRateCache {

		private Map<EnumCurrencyCode, ExchangeRate> exchangeRateCache = new HashMap<EnumCurrencyCode, ExchangeRate>();

		public Double getExchangeRate(EnumCurrencyCode currencyCode) {
			ExchangeRate exchangeRate = exchangeRateCache.get(currencyCode);
			return exchangeRate == null ? 1d : exchangeRate.getRate();
		}

		public List<ExchangeRate> getAllExchangeRate() {
			List<ExchangeRate> exchangeRateList = new ArrayList<ExchangeRate>();
			for (Map.Entry<EnumCurrencyCode, ExchangeRate> entry : exchangeRateCache.entrySet()) {
				exchangeRateList.add(entry.getValue());
			}
			return exchangeRateList;
		}

		public int getExchangeRateCount() {
			return exchangeRateCache.size();
		}

		public boolean update(Map<String, String> currencyRateMap) {
			if (currencyRateMap == null || currencyRateMap.size() == 0) {
				logger.error("exchangeRate from redis is null or empty!");
				return false;
			}
			for (Map.Entry<String, String> entry : currencyRateMap.entrySet()) {
				String currencyCode = entry.getKey();
				String exchangeRateStr = entry.getValue();
				EnumCurrencyCode enumCurrencyCode = EnumCurrencyCode.valueOf(currencyCode);
				ExchangeRate exchangeRate = new ExchangeRate();
				exchangeRate.setCurrencyCode(currencyCode);
				exchangeRate.setRate(Double.valueOf(exchangeRateStr));
				exchangeRateCache.put(enumCurrencyCode, exchangeRate);
			}
			return true;
		}
	}

}
