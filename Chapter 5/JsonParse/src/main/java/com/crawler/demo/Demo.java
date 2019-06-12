package com.crawler.demo;

import java.io.IOException;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSON;
import com.model.CommentModel;

public class Demo {
	private static HttpClient httpClient = HttpClients.custom().build(); //初始化httpclient
	public static void main(String[] args) throws Exception {
		String url = "http://www.haodou.com/comment.php?do=list&callback=jQuery183016721538977115902_1531563599327&channel=recipe&item=853171&sort=desc&page=1&size=5&comment_id=0&cate=0&purify=common&_=1531563599599";
		//获取JSON数据
		String jsonstring = getJson(url);
		//解析JSON数据
		List<CommentModel> datalist = parseData(jsonstring);
		//输出数据
		for (CommentModel comm : datalist) {
			System.out.println(comm.getCommentId() + "\t" + comm.getItemId() + "\t" + comm.getContent());
		}
	}
	//获取JSON内容
	public static String getJson(String url) throws ClientProtocolException, IOException{
		HttpGet httpget = new HttpGet(url); //使用的请求方法
		//发出get请求
		HttpResponse response = httpClient.execute(httpget); 
		//获取网页内容流
		HttpEntity httpEntity = response.getEntity();  
		//以字符串的形式(需设置编码)
		String entity = EntityUtils.toString(httpEntity, "gbk");  
		//关闭内容流     
		EntityUtils.consume(httpEntity);     
		return entity;   //返回JSON
	}
	//解析Json内容
	public static List<CommentModel> parseData (String json) throws Exception{
		json = decode(json);  //将uncode码转化为中文
		//使用分割以及正则取代，处理成标准化JSON数组
		String jsondata  = "{"+json.split("data\":\\{")[2].split("\"avatar")[0].replaceAll("\"_\\d*[0-9]\":", "");
		String jsonStr = jsondata.substring(0, jsondata.length()-2);
		//将json数组解析成对象集合
		List<CommentModel>  datalis = JSON.parseArray("["+jsonStr.substring(1,jsonStr.length())+"]", CommentModel.class);
		return datalis;
	}
	//将uncode码转化为中文
	public static String decode(String unicodeStr) {
		if (unicodeStr == null) {
			return null;
		}
		StringBuffer retBuf = new StringBuffer();
		int maxLoop = unicodeStr.length();
		for (int i = 0; i < maxLoop; i++) {
			if (unicodeStr.charAt(i) == '\\') {
				if ((i < maxLoop - 5)
						&& ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr
								.charAt(i + 1) == 'U')))
					try {
						retBuf.append((char) Integer.parseInt(
								unicodeStr.substring(i + 2, i + 6), 16));
						i += 5;
					} catch (NumberFormatException localNumberFormatException) {
						retBuf.append(unicodeStr.charAt(i));
					}
				else
					retBuf.append(unicodeStr.charAt(i));
			} else {
				retBuf.append(unicodeStr.charAt(i));
			}
		}
		return retBuf.toString();
	}
}
