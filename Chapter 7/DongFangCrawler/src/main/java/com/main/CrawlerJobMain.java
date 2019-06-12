package com.main;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import com.job.CarStockJob;
public class CrawlerJobMain {
	public void run() throws Exception { 
		// 实例化任务调度器Scheduler
		SchedulerFactory sf = new StdSchedulerFactory(); 
		Scheduler sched = sf.getScheduler(); 
		//描述Job实现类及其它相关的静态信息
		JobDetail job = newJob(CarStockJob.class).
				withIdentity("crawlerJob", "g").build(); 
		//每周一到周五9点、10点、11点、13点、14点以及15点分别执行一次程序
		CronTrigger trigger = newTrigger().withIdentity("crawlerTrigger", "g").
				withSchedule(cronSchedule("0 0 9,10,11,13,14,15 ? * MON-FRI")).build(); 
		Date ft = sched.scheduleJob(job, trigger); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS"); 
		System.out.println(job.getKey() + " 已被安排执行于: " 
				+ sdf.format(ft) + "，并且以如下重复规则重复执行: " + trigger.getCronExpression()); 
		//启动Scheduler
		sched.start(); 
	} 
	public static void main(String[] args) throws Exception { 
		CrawlerJobMain crawler = new CrawlerJobMain(); 
		crawler.run(); 
	} 
}
