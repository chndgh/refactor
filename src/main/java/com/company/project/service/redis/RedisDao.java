package com.company.project.service.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by kanxg on 14-11-24.
 */
public class RedisDao {
	static JedisPool pool;
	static String redisserverip = RedisConstant.default_redis_server_ip;
	static String redisPassword;

	public RedisDao() {
		init();
	}

	private void init() {
		if (pool == null) {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(100);
			config.setMaxIdle(8);
			config.setMinIdle(0);
			config.setMaxWaitMillis(15000);
			config.setMinEvictableIdleTimeMillis(300000);
			config.setSoftMinEvictableIdleTimeMillis(-1);
			config.setNumTestsPerEvictionRun(3);
			config.setTestOnBorrow(false);
			config.setTestOnReturn(false);
			config.setTestWhileIdle(false);
			config.setTimeBetweenEvictionRunsMillis(60000);// 一分钟
			loadserverip();
			if (redisPassword != null) {
				pool = new JedisPool(config, redisserverip, Protocol.DEFAULT_PORT, Protocol.DEFAULT_TIMEOUT,
						redisPassword);
			} else {
				pool = new JedisPool(config, redisserverip);
			}
		}
	}

	private void loadserverip() {
		try {
			String home = System.getProperty("uumai.home");
			InputStream in = null;
			if (home == null) {
				in = this.getClass().getClassLoader().getResourceAsStream("jdbc.properties");
			} else {
				in = new BufferedInputStream(new FileInputStream(home + File.separator + "jdbc.properties"));
			}

			Properties p = new Properties();
			p.load(in);
			if (p.containsKey("redis.server.ip")) {
				redisserverip = p.getProperty("redis.server.ip");
				System.out.println("redis.server.ip:" + redisserverip);
			}
			if (p.containsKey("redis.server.password")) {
				redisPassword = p.getProperty("redis.server.password");
				System.out.println("redis.server.password:" + redisPassword);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}

	public Jedis getRedis() {
		// init();
		return pool.getResource();
	}

	public void returnResource(Jedis jedis) {
		// pool.returnResource(jedis);
		jedis.close();
	}

	public void destroy() {
		if (pool != null) {
			pool.destroy();
			pool = null;
		}
	}

}
