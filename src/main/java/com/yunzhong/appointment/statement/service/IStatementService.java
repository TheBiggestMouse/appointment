package com.yunzhong.appointment.statement.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 
* 标题: IStatementService.java
* 路径: com.yunzhong.appointment.statement.service
* 描述: 报表明细的模型层接口
* 作者: 郎国峰
* 时间: 2017年11月2日 下午6:25:18
* 版本: @version V1.0
 */
public interface IStatementService {
	/**
	 * @方法名: queryDoctimesData   
	 * @描述: TOOD根据传入的年份,查询医生被预约次数
	 * @作者: 郎国峰
	 * @时间: 2017年11月2日 下午6:29:08
	 * @param year 传入的年份
	 * @return
	 */
	HashMap<String, ArrayList<String>> queryDoctimesData(String year);
	/**
	 * @方法名: queryDoctimesData   
	 * @描述: 查询医生被预约压力
	 * @作者: 郎国峰
	 * @时间: 2017年11月2日 下午8:54:32
	 * @return
	 */
	ArrayList<Map<String,String>> queryDoctimesData();

}
