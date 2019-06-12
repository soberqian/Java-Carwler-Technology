package com.util;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
//实现X509TrustManager接口
public class SSL509TrustManager implements X509TrustManager {
	//检查客户端证书
	public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
		//do nothing 接受任意客户端证书
	}
	//检查服务器端证书  
	public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
		 //do nothing  接受任意服务端证书
	}
	//返回受信任的X509证书
	public X509Certificate[] getAcceptedIssuers() {
		return new X509Certificate[0];
	}
}
