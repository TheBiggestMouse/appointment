package com.yunzhong.appointment.util;

import java.util.*;

/**
 * @description 集合工具类
 * @author 石洪刚
 * @time 2017年8月28日20:21:43
 */
public class CollectionUtil {
	/**
	 * @description 根据key值对Map进行分组
	 * @author 石洪刚
	 * @time 2017年8月28日21:38:22
	 * @param coll
	 * @param key
	 * @return
	 */
	public static List<List<Map>> divideMapByKey(List<Map> coll,Object key){
		List list = new ArrayList();
		while(coll.size()>0){
			Map o = coll.get(0);
			List mList = new ArrayList();
			for (Map m : coll) {
				if(o.get(key).equals(m.get(key))){
					mList.add(m);
				}
			}
			list.add(mList);
			coll.removeAll(mList);
		}
		return list;
	}

}
