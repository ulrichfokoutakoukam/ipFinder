package com.ipFinder;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

public class UrlDataManager {
	
	/**
	 * get protocol associated to url
	 * @param iStrUrl
	 * @return
	 */
	public static String getProtocol(String iStrUrl) {
		String strProtocol = "";
		try {
			strProtocol = new URL(iStrUrl).getProtocol();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strProtocol;
		
	}
	
	/**
	 * get Ip assocated to domain
	 * @param iStrDomain
	 * @return String
	 */
	public static String getIp(String iStrDomain) {
		String strIp = "";
		
		try {
			InetAddress oInetAddress = InetAddress.getByName(iStrDomain);
			strIp = oInetAddress.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strIp;
	}
	
	/**
	 * get host name 
	 * @param iStrDomain the domain or subdomain
	 * @return String
	 */
	public static String  getHostName(String iStrDomain) {
		String strHostName = "";
		
		try {
			InetAddress oInetAddress = InetAddress.getByName(iStrDomain);
			strHostName = oInetAddress.getHostName();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return strHostName;
	}
}
