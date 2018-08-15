package com.zc.common.util.core;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;

public class ParamVerificationUtil {

    public static boolean validate(Object obj){
        if(obj==null){
            return false;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields){
            boolean access = field.isAccessible();
            if(!access) {
                field.setAccessible(true);
            }
            try {
                Object o = field.get(obj);
                if(o instanceof String){
                    String s = (String) o;
                    if(StringUtils.isEmpty(s)){
                        return false;
                    }else {
                        field.set(obj,s.trim());
                    }
                }else{
                    if(o==null){
                        return false;
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
