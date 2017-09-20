package com.light.moon.context;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

public class CacheContext {

	private static Map<CacheKey, Object> cacheMap = Maps.newHashMap();

	private static Map<CacheQueueKey, ArrayBlockingQueue<Date>> queueCache = Maps.newHashMap();

	private CacheContext() {
	}

	public static void put(String key, Object value, int timeout) {
		CacheKey cacheKey = validKey(key);
		CacheKey newKey = buildCacheKey(key, timeout);
		cacheMap.put(newKey, value);
		if (!newKey.equals(cacheKey)) {
			cacheMap.remove(cacheKey);
		}
	}

	public static Object get(String key) {
		CacheKey cacheKey = validKey(key);
		if (null != cacheKey) {
			return cacheMap.get(cacheKey);
		}

		return null;
	}

	public static boolean addQueueKey(String key, int timeRange, int maxNum) {
		if (maxNum < 1 || timeRange < 1) {
			return false;
		}

		CacheQueueKey cacheQueueKey = new CacheQueueKey(key, timeRange);
		ArrayBlockingQueue<Date> queue = queueCache.get(cacheQueueKey);
		if (null == queue) {
			queue = new ArrayBlockingQueue<Date>(maxNum);
			queue.add(new Date());
			queueCache.put(cacheQueueKey, queue);
			return true;
		}

		validQueueCache(queue, timeRange);
		boolean offer = queue.offer(new Date());
		if (!offer) {
			return false;
		}

		queueCache.put(cacheQueueKey, queue);
		return true;
	}
	
	public static boolean validQueueCacheable(String key, int timeRange, int maxNum) {
		if (maxNum < 1 || timeRange < 1) {
			return false;
		}

		CacheQueueKey cacheQueueKey = new CacheQueueKey(key, timeRange);
		ArrayBlockingQueue<Date> queue = queueCache.get(cacheQueueKey);
		if (null == queue) {
			return true;
		}

		validQueueCache(queue, timeRange);
		return queue.size() < maxNum;
	}

	private static void validQueueCache(ArrayBlockingQueue<Date> queue, int timeRange) {
		if (queue.size() < 1) {
			return;
		}

		Date top = queue.peek();
		if (new Date().getTime() - top.getTime() > timeRange * 1000) {
			queue.poll();
			validQueueCache(queue, timeRange);
		}
	}

	private static CacheKey validKey(String key) {
		CacheKey cacheKey = getCacheKey(key);
		if (null != cacheKey) {
			long timeOffset = cacheKey.getTimeout() * 1000;
			if (new Date().getTime() - cacheKey.getCreateTime().getTime() < timeOffset) {
				return cacheKey;
			} else {
				cacheMap.remove(cacheKey);
			}
		}
		return null;
	}

	private static CacheKey getCacheKey(String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}

		Set<CacheKey> keySet = cacheMap.keySet();
		for (CacheKey _key : keySet) {
			if (_key.getKey().equals(key)) {
				return _key;
			}
		}

		return null;
	}

	private static CacheKey buildCacheKey(String key, int timeout) {
		return new CacheKey(key, timeout);
	}

}

class CacheKey {

	private String key;

	private int timeout;

	private Date createTime;

	public CacheKey(String key, int timeout) {
		this.key = key;
		this.timeout = timeout;
		this.createTime = new Date();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public int hashCode() {
		return getKey().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		CacheKey other = (CacheKey) obj;
		if (StringUtils.isBlank(other.key) || StringUtils.isBlank(key)) {
			return false;
		} else if (!key.equals(other.key)) {
			return false;
		}

		return true;
	}

}

class CacheQueueKey {

	private String key;

	private int timeRange;

	public CacheQueueKey(String key, int timeRange) {
		this.key = key;
		this.timeRange = timeRange;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public int getTimeRange() {
		return timeRange;
	}

	public void setTimeRange(int timeRange) {
		this.timeRange = timeRange;
	}

	@Override
	public int hashCode() {
		return getKey().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		CacheQueueKey other = (CacheQueueKey) obj;
		if (StringUtils.isBlank(other.key) || StringUtils.isBlank(key)) {
			return false;
		} else if (!key.equals(other.key)) {
			return false;
		}

		return true;
	}

}