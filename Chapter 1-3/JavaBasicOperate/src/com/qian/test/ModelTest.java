package com.qian.test;
import java.util.ArrayList;
import java.util.List;
public class ModelTest {
	public static void main(String[] args) {
		JdInfoModel product1 = new JdInfoModel();  //创建对象
		//对象值的设置
		product1.setId(1);  
		product1.setProduct_name("华为p20");
		product1.setPrice(4800.50);
		JdInfoModel product2 = new JdInfoModel(); //对象的创建
		//对象值的设置
		product2.setId(2);
		product2.setProduct_name("华为nova2s");
		product2.setPrice(2300.60);
		//由于所爬数据包含多个，可以封装到集合中存储
		List<JdInfoModel> productList = new ArrayList<JdInfoModel>(); 
		productList.add(product1);
		productList.add(product2);
		//获取一个对象的产品名称
		System.out.println(productList.get(0).getProduct_name());
	}
}
