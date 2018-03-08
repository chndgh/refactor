package com.company.project.server.download;

import org.apache.http.HttpHost;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * Created by kanxg on 14-12-21.
 */
public class CrawlerProxy implements Serializable {

	private String ip;
	private int port;
	private String type;

	public CrawlerProxy(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public CrawlerProxy(String ip, int port, String type) {
		this.ip = ip;
		this.port = port;
		this.type = type;
	}

	public Proxy getproxy() {
		if (type != null && "socks".equalsIgnoreCase(type)) {
			return new Proxy(Proxy.Type.SOCKS, new InetSocketAddress(ip, port));
		}
		return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port));
	}

	public HttpHost gethttpclientproxy() throws Exception {
		if (type != null && "socks".equalsIgnoreCase(type)) {
			throw new Exception("httpclient didn't support sock5!");
		}
		return new HttpHost(ip, port, "http");
	}

	// public String getProxyIpAndPortString(){
	// return ip+":"+port;
	// }

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	// @Override
	// public String toString() {
	// return "CrawlerProxy{" +
	// "ip='" + ip + '\'' +
	// ", port=" + port +
	// ", type='" + type + '\'' +
	// '}';
	// }

	@Override
	public String toString() {
		return ip + ':' + port + ':' + type;
	}
}
