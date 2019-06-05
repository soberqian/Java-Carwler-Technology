# Java-Carwler-Technology
网络数据采集技术—Java网络爬虫

&ensp;&ensp; 本书将'**电子工业出版社**出版，另外本人的[我的博客](https://qianyang-hfut.blog.csdn.net/)介绍了大量的网络爬虫知识，有兴趣的可以学习。 为及时更正书中的不恰当的内容，笔者在CSDN博客中创建了一个页面：https://qianyang-hfut.blog.csdn.net/article/details/90483177。 读者可以将书中的问题，以评论的方式反馈给我，笔者针对这些问题进行勘误。
  

# 书稿的完整目录

第一章：网络爬虫概述与原理	10

&ensp;&ensp; 1.1 网络爬虫简介	10
&ensp;&ensp; 1.2 网络爬虫分类	11
&ensp;&ensp; 1.3 网络爬虫流程	13
&ensp;&ensp; 1.4 网络爬虫的采集策略	13
&ensp;&ensp; 1.5 学习网络爬虫的建议	14
&ensp;&ensp; 1.6 本章小结	15
第二章：网络爬虫涉及的Java基础知识	16
&ensp;&ensp; 2.1 开发环境的搭建	16
&ensp;&ensp; &ensp;&ensp; 2.1.1 JDK的安装及环境变量配置	16
&ensp;&ensp; &ensp;&ensp; 2.1.2 Eclipse下载	17
&ensp;&ensp; 2.2 基本数据类型	18
&ensp;&ensp; 2.3 数组	19
&ensp;&ensp; 2.4 条件判断与循环	20
&ensp;&ensp; 2.5 集合	22
&ensp;&ensp; &ensp;&ensp; 2.5.1 List 和 Set 集合	22
&ensp;&ensp; &ensp;&ensp; 2.5.2 Map集合	23
&ensp;&ensp; &ensp;&ensp; 2.5.3 Queue集合	23
&ensp;&ensp; 2.6 对象与类	25
&ensp;&ensp; 2.7 String类	27
&ensp;&ensp; 2.8 日期和时间处理	28
&ensp;&ensp; 2.7 正则表达式	32
&ensp;&ensp; 2.8 Maven工程创建	34
&ensp;&ensp; 2.9 log4j的使用	38
&ensp;&ensp; 2.10 本章小结	43
第三章：HTTP协议基础与网络抓包	44
&ensp;&ensp; 3.1 HTTP协议简介	44
&ensp;&ensp; 3.2 URL	45
&ensp;&ensp; 3.3 报文	47
&ensp;&ensp; 3.4 HTTP请求方法	47
&ensp;&ensp; 3.5 HTTP状态码	48
&ensp;&ensp; &ensp;&ensp; 3.5.1 状态码2XX	48
&ensp;&ensp; &ensp;&ensp; 3.5.2 状态码3XX	48
&ensp;&ensp; &ensp;&ensp; 3.5.3 状态码4XX	49
&ensp;&ensp; &ensp;&ensp; 3.5.4 状态码5XX	49
&ensp;&ensp; 3.6 HTTP信息头	50
&ensp;&ensp; &ensp;&ensp; 3.6.1 通用头	50
&ensp;&ensp; &ensp;&ensp; 3.6.2 请求头	52
&ensp;&ensp; &ensp;&ensp; 3.6.3 响应头	55
&ensp;&ensp; &ensp;&ensp; 3.6.4 实体头	55
&ensp;&ensp; 3.7 HTTP响应正文	56
&ensp;&ensp; &ensp;&ensp; 3.7.1 HTML	56
&ensp;&ensp; &ensp;&ensp; 3.7.2 XML	59
&ensp;&ensp; &ensp;&ensp; 3.7.3 JSON	59
&ensp;&ensp; 3.8 网络抓包	61
&ensp;&ensp; &ensp;&ensp; 3.8.1 简介	61
&ensp;&ensp; &ensp;&ensp; 3.8.2 使用情境	62
&ensp;&ensp; &ensp;&ensp; 3.8.3 浏览器实现网络抓包	62
&ensp;&ensp; &ensp;&ensp; 3.8.4其他网络抓包工具推荐	66
&ensp;&ensp; 3.9 本章小结	67
第四章：网页内容获取	68
&ensp;&ensp; 4.1 Jsoup的使用	68
&ensp;&ensp; &ensp;&ensp; 4.1.1 jar的下载	68
&ensp;&ensp; &ensp;&ensp; 4.1.2 请求URL	68
&ensp;&ensp; &ensp;&ensp; 4.1.3 设置头信息	71
&ensp;&ensp; &ensp;&ensp; 4.1.4 提交请求参数	74
&ensp;&ensp; &ensp;&ensp; 4.1.5 超时设置	76
&ensp;&ensp; &ensp;&ensp; 4.1.6 代理服务器的使用	76
&ensp;&ensp; &ensp;&ensp; 4.1.7 响应转输出流(图片、PDF等下载)	78
&ensp;&ensp; &ensp;&ensp; 4.1.8 HTTPS请求认证	79
&ensp;&ensp; &ensp;&ensp; 4.1.9 大文件内容获取问题	83
&ensp;&ensp; 4.2 HttpClient的使用	84
&ensp;&ensp; &ensp;&ensp; 4.2.1 jar的下载	84
&ensp;&ensp; &ensp;&ensp; 4.2.2 请求URL	85
&ensp;&ensp; &ensp;&ensp; 4.2.3 EntityUtils类	89
&ensp;&ensp; &ensp;&ensp; 4.2.4 设置头信息	89
&ensp;&ensp; &ensp;&ensp; 4.2.5 POST提交表单	91
&ensp;&ensp; &ensp;&ensp; 4.2.6 超时设置	94
&ensp;&ensp; &ensp;&ensp; 4.2.7 代理服务器的使用	95
&ensp;&ensp; &ensp;&ensp; 4.2.8 文件下载	96
&ensp;&ensp; &ensp;&ensp; 4.2.9 HTTPS请求认证	97
&ensp;&ensp; &ensp;&ensp; 4.2.10 请求重试	100
&ensp;&ensp; &ensp;&ensp; 4.2.11 多线程执行请求	102
&ensp;&ensp; 4.3 URLConnection与HttpURLConnection	105
&ensp;&ensp; &ensp;&ensp; 4.3.1 实例化	105
&ensp;&ensp; &ensp;&ensp; 4.3.2获取网页内容	105
&ensp;&ensp; &ensp;&ensp; 4.3.3 GET请求	106
&ensp;&ensp; &ensp;&ensp; 4.3.4 模拟提交表单（POST请求）	107
&ensp;&ensp; &ensp;&ensp; 4.3.5 设置头信息	107
&ensp;&ensp; &ensp;&ensp; 4.3.6 连接超时设置	108
&ensp;&ensp; &ensp;&ensp; 4.3.7 代理服务器的使用	108
&ensp;&ensp; &ensp;&ensp; 4.3.8 HTTPS请求认证	108
&ensp;&ensp; 4.4 本章小结	110
第五章 网页内容解析	111
&ensp;&ensp; 5.1 HTML解析	111
&ensp;&ensp; &ensp;&ensp; 5.1.1 CSS选择器	111
&ensp;&ensp; &ensp;&ensp; 5.1.2 Xpath语法	112
&ensp;&ensp; &ensp;&ensp; 5.1.3 Jsoup解析HTML	113
&ensp;&ensp; &ensp;&ensp; 5.1.4 HtmlCleaner解析HTML	119
&ensp;&ensp; &ensp;&ensp; 5.1.5 Htmlparser解析HTML	122
&ensp;&ensp; 5.2 XML解析	127
&ensp;&ensp; 5.3 JSON解析	128
&ensp;&ensp; &ensp;&ensp; 5.3.1 JSON校正	128
&ensp;&ensp; &ensp;&ensp; 5.3.2 org.json解析JSON	129
&ensp;&ensp; &ensp;&ensp; 5.3.3 Gson解析JSON	134
&ensp;&ensp; &ensp;&ensp; 5.3.4 Fastjson解析JSON	138
&ensp;&ensp; &ensp;&ensp; 5.3.5 网络爬虫实战演练	139
&ensp;&ensp; 5.4 本章小结	144
第六章 网络爬虫数据存储	144
&ensp;&ensp; 6.1 输入流与输出流	144
&ensp;&ensp; &ensp;&ensp; 6.1.1 简介	145
&ensp;&ensp; &ensp;&ensp; 6.1.2 File类	145
&ensp;&ensp; &ensp;&ensp; 6.1.3 文件字节流	147
&ensp;&ensp; &ensp;&ensp; 6.1.4 文件字符流	150
&ensp;&ensp; &ensp;&ensp; 6.1.5 缓冲流	153
&ensp;&ensp; &ensp;&ensp; 6.1.6 网络爬虫下载图片实战	157
&ensp;&ensp; &ensp;&ensp; 6.1.7 网络爬虫文本存储实战	160
&ensp;&ensp; 6.2 Excel存储	163
&ensp;&ensp; &ensp;&ensp; 6.2.1 Jxl的使用	163
&ensp;&ensp; &ensp;&ensp; 6.2.2 POI的使用	166
&ensp;&ensp; &ensp;&ensp; 6.2.3 爬虫案例	171
&ensp;&ensp; 6.3 MySQL数据存储	175
&ensp;&ensp; &ensp;&ensp; 6.3.1 数据库基本概念	176
&ensp;&ensp; &ensp;&ensp; 6.3.2 SQL语句基础	176
&ensp;&ensp; &ensp;&ensp; 6.3.3 Java操作数据库	179
&ensp;&ensp; &ensp;&ensp; 6.3.4 爬虫案例	188
&ensp;&ensp; 6.4 本章小结	190
第七章 网络爬虫实战项目	191
&ensp;&ensp; 7.1 新闻数据采集	191
&ensp;&ensp; &ensp;&ensp; 7.1.1 采集的网页	191
&ensp;&ensp; &ensp;&ensp; 7.1.2 框架介绍	193
&ensp;&ensp; &ensp;&ensp; 7.1.3 程序编写	193
&ensp;&ensp; 7.2 信用中国信息采集	202
&ensp;&ensp; &ensp;&ensp; 7.2.1 采集的网页	202
&ensp;&ensp; &ensp;&ensp; 7.2.2 框架介绍	204
&ensp;&ensp; &ensp;&ensp; 7.2.3 第一层信息采集	205
&ensp;&ensp; &ensp;&ensp; 7.2.4 第二层信息采集	212
&ensp;&ensp; 7.3 股票信息采集	220
&ensp;&ensp; &ensp;&ensp; 7.3.1 采集的网页	220
&ensp;&ensp; &ensp;&ensp; 7.3.2 框架内容	221
&ensp;&ensp; &ensp;&ensp; 7.3.3 程序设计	222
&ensp;&ensp; &ensp;&ensp; 7.3.4 Quartz实现定时调度任务	229
&ensp;&ensp; 7.4 本章小结	232
第八章 Selenium的使用	233
&ensp;&ensp; 8.1 简介	233
&ensp;&ensp; 8.2 Java Selenium环境搭建	233
&ensp;&ensp; 8.3 浏览器操控	235
&ensp;&ensp; 8.4 元素定位	236
&ensp;&ensp; &ensp;&ensp; 8.4.1 id定位	236
&ensp;&ensp; &ensp;&ensp; 8.4.2 name定位	237
&ensp;&ensp; &ensp;&ensp; 8.4.3 class定位	237
&ensp;&ensp; &ensp;&ensp; 8.4.4 tag name定位	237
&ensp;&ensp; &ensp;&ensp; 8.4.5 link text定位	238
&ensp;&ensp; &ensp;&ensp; 8.4.6 Xpath定位	238
&ensp;&ensp; &ensp;&ensp; 8.4.7 CSS选择器定位	238
8&ensp;&ensp; .5 模拟登陆	238
&ensp;&ensp; 8.6 动态加载JavaScript数据 (操作滚动条)	241
&ensp;&ensp; 8.7 隐藏浏览器	243
&ensp;&ensp; 8.8 截取验证码	244
&ensp;&ensp; 8.9 本章小结	248
第九章 网络爬虫开源框架	249
&ensp;&ensp; 9.1 Crawler4j的使用	249
&ensp;&ensp; &ensp;&ensp; 9.1.1 简介	249
&ensp;&ensp; &ensp;&ensp; 9.1.2 jar的下载	249
&ensp;&ensp; &ensp;&ensp; 9.1.3 入门案例	250
&ensp;&ensp; &ensp;&ensp; 9.1.4 相关配置	253
&ensp;&ensp; &ensp;&ensp; 9.1.5 图片的采集	255
&ensp;&ensp; &ensp;&ensp; 9.1.6 数据采集入库	259
&ensp;&ensp; 9.2 WebCollector的使用	266
&ensp;&ensp; &ensp;&ensp; 9.2.1 简介	266
&ensp;&ensp; &ensp;&ensp; 9.2.2 jar的下载	266
&ensp;&ensp; &ensp;&ensp; 9.2.3 入门案例	266
&ensp;&ensp; &ensp;&ensp; 9.2.4 相关配置	270
&ensp;&ensp; &ensp;&ensp; 9.2.5 HTTP请求扩展	271
&ensp;&ensp; &ensp;&ensp; 9.2.6 翻页数据采集	278
&ensp;&ensp; &ensp;&ensp; 9.2.7 图片的采集	281
&ensp;&ensp; &ensp;&ensp; 9.2.8 数据采集入库	284
&ensp;&ensp; 9.3 WebMagic的使用	294
&ensp;&ensp; &ensp;&ensp; 9.3.1 简介	294
&ensp;&ensp; &ensp;&ensp; 9.3.2 jar的下载	294
&ensp;&ensp; &ensp;&ensp; 9.3.3 入门案例(翻页数据采集)	295
&ensp;&ensp; &ensp;&ensp; 9.3.4 相关配置	297
&ensp;&ensp; &ensp;&ensp; 9.3.5 数据存储方式	298
&ensp;&ensp; &ensp;&ensp; 9.3.6 数据采集入库	300
&ensp;&ensp; &ensp;&ensp; 9.3.7 图片的采集	308
&ensp;&ensp; &ensp;&ensp; 9.4 本章小结	311
附录	312

