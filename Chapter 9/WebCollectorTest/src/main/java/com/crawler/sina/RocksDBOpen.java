package com.crawler.sina;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksIterator;
public class RocksDBOpen {
	static{
		RocksDB.loadLibrary();
	}
	
	static RocksDB rocksDB; 
	static String path = "sinaNewsCrawler/crawldb";
//	static String path = "sinaNewsCrawler/fetch";
//	static String path = "sinaNewsCrawler/link";
	public static void main(String[] args) throws Exception {
		Options options = new Options();  
		options.setCreateIfMissing(true);  
		//打开RocksDB
		rocksDB = RocksDB.open(options, path); 
		//迭代,输出内容
		int i = 1;
		RocksIterator iter = rocksDB.newIterator();
		for(iter.seekToFirst(); iter.isValid(); iter.next()) {  
			System.out.println("key:" + new String(iter.key()) + 
					",value:" + new String(iter.value()));  
			i++; 
		}
		System.out.println(i);
	}
}
