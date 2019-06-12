package com.parse;

import java.util.List;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.model.ParameterModel;
import com.model.ProductModel;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;

public class Parse {
	/**
	 * 针对每个品牌
	 * 解析得到品牌下产品的id,url,产品名称,产品价格
	 * 将产品的URL,添加到下一层需要访问的页面
	 * 并将type设置为ThirdLayer
	 */
	public static void getProductData(Page page, List<ProductModel> listPro, CrawlDatums next) {
		//解析第二层页面
		Elements results = page.select("ul[id=J_PicMode]")
				.select("li");
		for (int rank = 0; rank < results.size(); rank++) {
			Element result = results.get(rank);
			String product_id = result.attr("data-follow-id");
			String product_url = "http://detail.zol.com.cn" + result.select("h3").
					select("a").attr("href");
			String product_name = result.select("h3").select("a").text();
			String product_price = result.select("span[class~=price price-]")
					.select("b.price-type").text();
			ProductModel model = new ProductModel();
			if (product_id.length()!=0) {
				model.setProduct_id(product_id);
				model.setProduct_url(product_url);
				model.setProduct_name(product_name);
				model.setProduct_price(product_price);
				listPro.add(model);
				next.add(product_url)  //添加到下面访问的URL
				.type("ThirdLayer");  //第三层次
			}
		}
	}
	
	/**
	 * 针对每个产品
	 * 解析得到产品的具体参数信息,包括：产品id以及参数两个字段
	 */
	public static void getParData(Page page, List<ParameterModel> listPar) {
		String product_id = "p" + page.url().replaceAll("\\D", "");
		Elements results = page.
				select("ul[class=product-param-item pi-57 clearfix]").select("p");
		String parameters = ""; 
		for (int rank = 0; rank < results.size(); rank++) {
			Element result = results.get(rank);
			parameters += result.text() + "\t";
		}
		ParameterModel pModel = new ParameterModel();
		pModel.setProduct_id(product_id);
		pModel.setParameters(parameters);
		listPar.add(pModel);
	}
}
