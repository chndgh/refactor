package com.company.project.client.util;

import java.net.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class UumaiProperties {
	private static Properties properties;

	// public static String configRootPath;

	private static Map<String, String> uumai_properties = new HashMap<String, String>();

	// public static String getConfigRootPath(){
	// if(configRootPath==null)
	// init();
	// return configRootPath;
	// }d

	public static String getUUmaiHome() {
		if (System.getProperty("uumai.home") != null)
			return System.getProperty("uumai.home");
		return System.getProperty("user.home") + "/uumai";
	}

	public static String getFengchaoHome() {
		return System.getProperty("fengchao.home");
	}

	public static String getLocalIP(String name) {
		String ip = "";
		try {
			Enumeration<?> e1 = (Enumeration<?>) NetworkInterface.getNetworkInterfaces();
			while (e1.hasMoreElements()) {
				NetworkInterface ni = (NetworkInterface) e1.nextElement();
				if (!ni.getName().equals(name)) {
					continue;
				} else {
					Enumeration<?> e2 = ni.getInetAddresses();
					while (e2.hasMoreElements()) {
						InetAddress ia = (InetAddress) e2.nextElement();
						if (ia instanceof Inet6Address)
							continue;
						ip = ia.getHostAddress();
					}
					break;
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
			// System.exit(-1);
		}
		return ip;
	}

	public static String getIpaddress() {
		return getLocalIP("eth0");
	}

	public static String getHostName() {
		InetAddress address = null;
		try {
			address = InetAddress.getLocalHost();
			return address.getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}
	// public static void init(String filePath){
	// properties = new Properties();
	// InputStream input = null;
	// try {
	//// String path=System.getenv("UUMAI_HOME");
	//// if(path==null){
	// //System.getProperty("uumai.home");
	//// }
	//
	// input = new BufferedInputStream(new FileInputStream(filePath));
	//
	// // input= UumaiProperties.class.getClassLoader()
	//// .getResourceAsStream("uumai.properties");
	// properties.load(input);// 加载属性文件
	//// System.out.println("url:" + properties.getProperty("url"));
	//// System.out.println("username:" + properties.getProperty("username"));
	//// System.out.println("password:" + properties.getProperty("password"));
	//// System.out.println("database:" + properties.getProperty("database"));
	// } catch (IOException io) {
	// io.printStackTrace();
	// } finally {
	// if (input != null) {
	// try {
	// input.close();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// }
	//
	// configRootPath=new File(filePath).getParent();

	// }
	// public static void init(){

	// String path=System.getProperty("uumai.home");
	//
	// if(path==null){
	// //path=System.getenv("UUMAI_HOME");
	// path="/home/rock/kanxg/Dropbox/mysourcecode/uumai/bitbucket/build";
	// }
	//
	//// if(path==null){
	//// System.out.println("no uumai.properties found, will use default value.");
	//// properties = new Properties();
	//// return;
	//// }
	//
	// configRootPath=path;
	//
	// String filePath = path + File.separator
	// + "uumai.properties";
	//
	// init(filePath);
	// }

	public static void main(String[] args) throws Exception {
	}
}
