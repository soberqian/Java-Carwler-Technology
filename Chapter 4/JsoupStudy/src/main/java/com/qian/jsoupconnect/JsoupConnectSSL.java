package com.qian.jsoupconnect;

import java.io.IOException;
import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupConnectSSL {

	public static void main(String[] args) throws IOException {
		Connection connect = Jsoup.connect("https://www.creditchina.gov.cn/xinyongfuwu/?navPage=5")
				.validateTLSCertificates(false);
		Document document = connect.get();
		System.out.println(document);
	}

}
