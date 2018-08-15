package com.zc.common.core.properties;

import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @description ：读取配置文件工具类
 * @Created by  : Moya
 * @Creation Date ： 2018/1/24 11:50
 * @version
 * @param :
 */

public class InitParamc {

	private Properties properties = new Properties();

	private String uri = "";

	public InitParamc(String uri){
		this.uri = uri;
	}

	/**
	 * 加载配置文件
	 * @throws IOException
	 */
	public void init() throws Exception {
		Properties proper = new Properties();
		proper.load(this.getClass().getClassLoader().getResourceAsStream("flag.properties"));
		proper.load(new FileReader(proper.getProperty("project")));
		Map<String,String> map= Maps.newHashMap();
		 Iterator<Object> keys = proper.keySet().iterator();
	     while(keys.hasNext()) {
			 String key= (String)keys.next();
			 if(proper.getProperty(key)!=null){
				 map.put(key, proper.getProperty(key).toString());
			 }
	    }
		proper.putAll(map);
		properties.putAll(proper);

	}
	public Properties getProperties() {
		try {
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	private final static Base64 BASE64=new Base64();

}
