package com.yunzhong.appointment.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @description 进行序列化操作
 * @author 石洪刚
 * @time 2017年9月5日23:32:35
 *
 */
public class SerializeUtil {
	/**
	 * @description 进行序列化
	 * @author 石洪刚
	 * @time 2017年9月5日23:34:21
	 * @param obj
	 * @return
	 */
	public static byte [] serialize(Object obj){
        ObjectOutputStream obi=null;
        ByteArrayOutputStream bai=null;
        try {
            bai=new ByteArrayOutputStream();
            obi=new ObjectOutputStream(bai);
            obi.writeObject(obj);
            byte[] byt=bai.toByteArray();
            return byt;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * @description 进行反序列化
     * @author 石洪刚
     * @time 2017年9月5日23:34:06
     * @param byt
     * @return
     */
    public static Object unserizlize(byte[] byt){
        ObjectInputStream oii=null;
        ByteArrayInputStream bis=null;
        bis=new ByteArrayInputStream(byt);
        try {
            oii=new ObjectInputStream(bis);
            Object obj=oii.readObject();
            return obj;
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    
        
        return null;
    }
    
}
