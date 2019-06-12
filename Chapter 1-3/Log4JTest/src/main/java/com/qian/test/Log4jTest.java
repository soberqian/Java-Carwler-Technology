package com.qian.test;
import org.apache.log4j.Logger;
public class Log4jTest {
	static final Logger logger = Logger.getLogger(Log4jTest.class);
	public static void main(String[] args) {
		System.out.println("hello");  //控制台输出
		//日志信息
		logger.info("hello world");
		logger.debug("This is debug message."); 
		logger.warn("This is warn message.");
		logger.error("This is error message."); 
	}
}
