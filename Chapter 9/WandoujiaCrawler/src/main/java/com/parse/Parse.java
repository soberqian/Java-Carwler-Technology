package com.parse;
import com.model.AppModel;
import us.codecraft.webmagic.Page;
public class Parse {
	/**
	 * 针对每款app的页面,解析获取app的信息
	 * 
	 * 使用CSS选择器以及Xpath语法解析数据
	 */
	public static void getAppData(Page page, AppModel model) {
		String id = page.getUrl()
				.replace("https:\\//www.wandoujia.com\\/apps\\/", "").toString();
		String name = page.getHtml()
				.$("p[class=app-name]").xpath("span//text()").get();
		String user_downloads =  page.getHtml()
				.$("span[class=item install]").xpath("i//text()").get();
		String update_time = page.getHtml()
				.xpath("//span[@class='update-time']/text()").get();
		String developer = page.getHtml()
				.xpath("//span[@class='dev-sites']/text()").get();
		String size = page.getHtml()
				.xpath("//dl[@class='infos-list']/dd[1]/text()").get();
		String tag = page.getHtml()
				.xpath("//div[@class='side-tags clearfix']/allText()").get();
		model.setId(id);
		model.setName(name);
		model.setUser_downloads(user_downloads);
		model.setUpdate_time(update_time);
		model.setDeveloper(developer);
		model.setSize(size);
		model.setTag(tag);
	}
}
