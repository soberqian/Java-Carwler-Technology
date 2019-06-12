package com.parse;

import java.util.List;
import com.model.DetailInfoModel;
import com.model.ListInfoModel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;



public class Parse {
	//红名单企业
	public static List<ListInfoModel> getCodeData(String jsondata){
		//采用fastjson解析json数据
		JSONObject json = JSONObject.parseObject(jsondata);
		String arrayData = JSONObject.parseObject(json.get("data").toString())
				.getJSONArray("results").toString();
		List<ListInfoModel> jsonlist = JSON.parseArray(arrayData,ListInfoModel.class);
		//返回所爬数据
		return jsonlist;
	}
	//解析每个企业的详细信息
	public static List<DetailInfoModel> getBasicInfoData(String jsondata){
		//采用fastjson解析json数据
		JSONObject json = JSONObject.parseObject(jsondata);
		String arrayData = "[" + json.get("result").toString() + "]";
		List<DetailInfoModel> jsonlist = JSON.parseArray(arrayData,DetailInfoModel.class);
		//返回所爬数据
		return jsonlist;
	}
}
