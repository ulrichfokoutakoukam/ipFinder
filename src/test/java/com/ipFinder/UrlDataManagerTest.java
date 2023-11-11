package com.ipFinder;

import junit.framework.TestCase;

public class UrlDataManagerTest extends TestCase {
	
	private String m_strUrl = "https://web.whatsapp.com/";
	private String m_strDomainName = "web.whatsapp.com";
	private String m_strDomainNameIp = "157.240.221.60";

	public void testGetProtocol() {
		String strProtocol = UrlDataManager.getProtocol(m_strUrl);
		
		assertEquals(strProtocol, "https");
	}
	
	public void testGetIp() {
		String strIp = UrlDataManager.getIp(m_strDomainName);
		
		assertEquals(strIp, m_strDomainNameIp);
	}
	
	public void testGetHostName() {
		String strHostName = UrlDataManager.getHostName(m_strDomainName);
		
		assertEquals(strHostName, m_strDomainName);
	}
}
