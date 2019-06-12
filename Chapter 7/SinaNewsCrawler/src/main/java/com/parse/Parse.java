package com.parse;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.model.SinaNewsModel;
public class Parse {
	//解析json数据
	public static List<SinaNewsModel> getData (String jsonString) {
		JSONObject json = JSONObject.parseObject(jsonString);
		//获取'data'下面的json数组
		String arrayData = JSONObject.parseObject(json.get("result").toString())
				.getJSONArray("data").toString();
		List<SinaNewsModel> dataList = parseArray(arrayData, SinaNewsModel.class);
		//返回集合
		return dataList;
	}
    /**
     * 解析JsonArray数据
     * 
     * @param jsonString
     * @param cls
     * @return
     */
    public static <T> List<T> parseArray(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            list = JSON.parseArray(jsonString, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
