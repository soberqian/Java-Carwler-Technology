package com.crawler.test;

import java.io.BufferedWriter;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
/**
 * 实现将采集的数据存储到一个文件中
 */
public class HFUTNewsOutPutFilePipeline implements Pipeline {
	private Logger logger = LoggerFactory.getLogger(getClass());
	public BufferedWriter w;
	public HFUTNewsOutPutFilePipeline(BufferedWriter writer) {
        this.w = writer;
    }
	public void process(ResultItems resultItems, Task task) {
		try {
			for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
				w.append(entry.getKey() + ":\t" + entry.getValue() + "\n");
	        }
			w.append("\n");
		} catch (Exception e) {
			logger.warn("write file error", e);
		}
	}
}
