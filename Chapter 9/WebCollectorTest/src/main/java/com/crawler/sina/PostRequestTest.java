package com.crawler.sina;
import okhttp3.Request;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.net.OkHttpRequester;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
/**
 * 根据WebCollector作者的案例改编
 * 
 * 提交表单数据
 */

public class PostRequestTest extends BreadthCrawler {
	public PostRequestTest(final String crawlPath, boolean autoParse) {
		super(crawlPath, autoParse);
		addSeed(new CrawlDatum("http://www.kd185.com/ems.php")
				.meta("wen", "EH629625211CS")
				.meta("action", "ajax"));
		setRequester(new OkHttpRequester(){
			@Override
			public Request.Builder createRequestBuilder(CrawlDatum crawlDatum) {
				Request.Builder requestBuilder = super.createRequestBuilder(crawlDatum);
				RequestBody requestBody;
				String wen = crawlDatum.meta("wen");
				// 如果没有表单数据
				if(wen == null){
					requestBody = RequestBody.create(null, new byte[]{});
				}else{
					//根据meta构建POST表单数据
					requestBody = new MultipartBody.Builder()
							.setType(MultipartBody.FORM)
							.addFormDataPart("wen", wen)
							.addFormDataPart("action", crawlDatum.meta("action"))
							.build();
				}
				return requestBuilder.post(requestBody)
						.header("Connection", "keep-alive");
			}
		});
	}
	public void visit(Page page, CrawlDatums next) {
		String html = page.html();
		System.out.println("快递信息" + html);
	}

	public static void main(String[] args) throws Exception {
		PostRequestTest crawler = new PostRequestTest("post_crawler", true);
		crawler.start(1);
	}
}
