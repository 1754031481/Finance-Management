package com.zc.common.core.property;

import com.google.common.collect.Maps;
import org.apache.commons.codec.binary.Base64;

import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @param
 * @author 孙盼柯
 * @date $date$ $time$
 * @Title: TODO
 * @Description: TODO
 * @Version:1.0.0
 */
public class DataSourceInit {
    private Properties properties = new Properties();

    private String uri = "";

    public DataSourceInit(String uri){
        this.uri = uri;
    }

    /**
     * 加载配置文件
     * @throws IOException
     */
    public void init() throws Exception {
        Properties proper = new Properties();
        proper.load(new FileReader(uri));
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
    private  Base64 bASE64=new Base64();
}
