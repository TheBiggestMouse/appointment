package com.yunzhong.appointment.statement.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yunzhong.appointment.entity.Appointmentorder;
import com.yunzhong.appointment.entity.Person;
import com.yunzhong.appointment.mapper.AppointmentorderMapper;
import com.yunzhong.appointment.mapper.PersonMapper;
import com.yunzhong.appointment.statement.service.IStatementService;
/**
 * 
* 标题: StatementServiceImpl.java
* 路径: com.yunzhong.appointment.statement.service.impl
* 描述: 报表明细的模型层实现类
* 作者: 郎国峰
* 时间: 2017年11月2日 下午6:25:53
* 版本: @version V1.0
 */
@Service
public class StatementServiceImpl implements IStatementService {
	/**
	 * 预约订单表mapper
	 */
	@Autowired
	private AppointmentorderMapper appointmentorderMapper ;
	/**
	 * 人员信息表mapper
	 */
	@Autowired
	private PersonMapper personMapper ;
	
	
	
	@Override
	public HashMap<String, ArrayList<String>> queryDoctimesData(String year) {
		//根据传入的年份,查询医生被预约次数
		List<Appointmentorder> list = appointmentorderMapper.listDoctimesDataByYear(year);
		//声明一个医生姓名list 
		ArrayList<String> listName = new ArrayList<String>();
		//声明一个被预约次数list
		ArrayList<String> listcount = new ArrayList<String>();
		//遍历查询返回的list,得到医生姓名和被预约次数,分别存储到医生姓名list和被预约次数list中
		for (int i = 0; i < list.size(); i++) {
			Appointmentorder apto = list.get(i);//得到一条数据
			String name = apto.getDoctorName(); //得到医生姓名
			String count = apto.getDocTimesCount().toString(); //得到被预约次数
			listName.add(name);  //存储到医生姓名list
			listcount.add(count);  //存储到被预约次数list
		}
		//声明一个存储医生姓名list和被预约次数list的map
		HashMap<String,ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
		map.put("listName", listName);  //将医生姓名list存储到map中
		map.put("listcount", listcount);  //将被预约次数list存储到map中
		return map;
	}

	@Override
	public ArrayList<Map<String,String>> queryDoctimesData() {
		//查询所有的医生,然后将数据存储到一个map里
		List<Person> listDoctorName = personMapper.listAllDoctor();
		System.out.println("========================================="+listDoctorName);
		HashMap<String,String> mapDoctorName = new HashMap<String, String>();
		for (int i = 0; i < listDoctorName.size(); i++) {
			Person person = listDoctorName.get(i);
			mapDoctorName.put(person.getPpId(), person.getPpName());
		}
		//查询医生被预约次数,然后将数据存储到一个map里
		List<Appointmentorder> listDoctimes = appointmentorderMapper.listDoctimesData();
		System.out.println("========================================="+listDoctimes);
		HashMap<String,BigDecimal> mapDoctimes = new HashMap<String, BigDecimal>();
		for (int i = 0; i < listDoctimes.size(); i++) {
			 Appointmentorder appointmentorder = listDoctimes.get(i);
			 mapDoctimes.put(appointmentorder.getDoctorId(), appointmentorder.getDocTimesCount());
		}
		//查询医生排班出诊次数,然后将数据存储到一个map里
		List<Person> listOutCallCount = personMapper.listOutCallCount();
		System.out.println("========================================="+listOutCallCount);
		HashMap<String,BigDecimal> mapOutCallCount = new HashMap<String, BigDecimal>();
		for (int i = 0; i < listOutCallCount.size(); i++) {
			  Person  person = listOutCallCount.get(i);
			  mapOutCallCount.put(person.getPpId(),person.getOutCallCount());
		}
		
		/**
		 * 简单的,只传回医生压力数据
		 */
		//声明一个list套map, map的数据结构为  name : 医生名    value = 压力值
		ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
		//遍历mapDoctorName,得到所有的key值,根据key值,得到其他数据,存入map中
		for ( String key : mapDoctorName.keySet()) {
			String doctorName = mapDoctorName.get(key);//得到医生名字
			//得到医生排班出诊次数,如果没有排班,默认为零
			BigDecimal outCallCount = BigDecimal.ZERO;
			if(!(mapOutCallCount.get(key)== null)){
				outCallCount = mapOutCallCount.get(key);
			}
			//得到医生预约次数,如果没有被预约过,默认为零
			BigDecimal doctimes = BigDecimal.ZERO;
			if(!(mapDoctimes.get(key)==null)){
				doctimes = mapDoctimes.get(key);
			}
			//如果医生没有被安排出诊,那么他的预约压力就是零,否则进行计算得到压力比值
			BigDecimal doctorStress = BigDecimal.ZERO;
			if(!outCallCount.equals(BigDecimal.ZERO)){
				doctorStress = doctimes.divide(outCallCount,BigDecimal.ROUND_HALF_UP);//计算出医生压力比值
			}
			//声明一个map,用来存储医生姓名和压力值
			Map<String,String> map = new HashMap<String, String>();
			map.put("name", mapDoctorName.get(key));//存入医生姓名
			map.put("value",doctorStress.toString());
			//将数据存入list中
			list.add(map);
		}
		
		
		
		
		/**
		 * 复杂的,传入前台所有数据
		 */
//		//声明一个map<String,String[4]>  map的key值存储医生id,数组依次存储 [0]医生姓名  [1]医生排班出诊次数  [2]被预约次数 [3]医生被预约次数与安排出诊次数的比值
//		HashMap<String,String[]> map = new HashMap<String, String[]>();
//		//遍历mapDoctorName,得到所有的key值,根据key值,得到其他数据,存入map中
//		for ( String key : mapDoctorName.keySet()) {
//			String doctorName = mapDoctorName.get(key);//得到医生名字
//			//得到医生排班出诊次数,如果没有排班,默认为零
//			BigDecimal outCallCount = BigDecimal.ZERO;
//			if(!(mapOutCallCount.get(key)== null)){
//				outCallCount = mapOutCallCount.get(key);
//			}
//			//得到医生预约次数,如果没有被预约过,默认为零
//			BigDecimal doctimes = BigDecimal.ZERO;
//			if(!(mapDoctimes.get(key)==null)){
//				doctimes = mapDoctimes.get(key);
//			}
//			//如果医生没有被安排出诊,那么他的预约压力就是零,否则进行计算得到压力比值
//			BigDecimal doctorStress = BigDecimal.ZERO;
//			if(!outCallCount.equals(BigDecimal.ZERO)){
//				doctorStress = doctimes.divide(outCallCount,BigDecimal.ROUND_HALF_UP);//计算出医生压力比值
//			}
//			//创建一个数组,用来存储这个医生的对应数据
//			String[] strs = {doctorName,outCallCount.toString(),doctimes.toString(),doctorStress.toString()};
//			//将医生key值  id  ,和医生对应数据的数组  存储到map中
//			map.put(key, strs);
//		}
		return list;
	}

}
