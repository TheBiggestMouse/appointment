package com.yunzhong.appointment.statement.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunzhong.appointment.entity.Appointmentorder;
import com.yunzhong.appointment.statement.service.IStatementService;
import com.yunzhong.appointment.statement.service.IAppointmentorderService;
import com.yunzhong.appointment.statement.service.impl.StatementServiceImpl;
import com.yunzhong.appointment.util.ExportExcelUtil;
import com.yunzhong.appointment.util.ExportImageToExcel;


/**
 * 
* @Title: StatementContorller.java
* @Package com.yunzhong.appointment.statement.controller
* @Description: TODO(报表控制器)
* @author 郎国峰
* @date 2017年10月30日 下午1:28:03
* @version V1.0
 */
@RequestMapping("/data")
@Controller
public class StatementContorller {
	/***
	 * 赵星宇 查询省人数信息
	 */
	@Autowired
    private	IAppointmentorderService appointmentorderService;
	/**
	 * 作者 :郎国峰
	 * 描述: 报表明细的模型层
	 * 时间: 2017年11月2日18:24:53
	 */
	@Autowired
	private IStatementService service ;
	
	/**
	 * 
	 * @方法名: data   
	 * @描述: 跳转
	 * @作者: 郎国峰
	 * @时间: 2017年10月30日 下午1:38:46
	 * @return
	 */
	@RequestMapping("data")
	public String data(){
		return "statement/statement";
	}
	/**
	 * 
	 * @方法名: pataddr   
	 * @描述: 患者预约地址统计的控制器
	 * @作者: 郎国峰
	 * @时间: 2017年10月31日 上午10:24:40
	 * @return
	 */
	@RequestMapping("/pataddr")
	public String pataddr(){
		
		return "statement/PatientAppointmentAddressStatistics";
	}
	/**
	 * 
	 * @方法名: patientsite   
	 * @描述: 根据传入的年份查询订单中每个地址的预约人数
	 * @作者: 赵星宇
	 * @时间: 2017年11月1日 下午2:46:28
	 * @param request
	 * @param response
	 * @param year 传入的年份
	 * @return
	 */
	@RequestMapping("/patientsite")
	@ResponseBody
	public HashMap<String,ArrayList<HashMap<String,String>>> patientsite(String year){

		//把查询出来所有数据存进来
		HashMap<String,ArrayList<HashMap<String,String>>> mapData = new HashMap<String, ArrayList<HashMap<String,String>>>();
		//存省人数量
		ArrayList<HashMap<String,String>> dataList = new ArrayList<HashMap<String,String>>();
		//存最大
		ArrayList<HashMap<String,String>> parmList = new ArrayList<HashMap<String,String>>();
		//查询之后存入list从预约订单获取地址和人数 	
		List<Appointmentorder> appList = appointmentorderService.queryProvinceCount(year);	
		//取最大和最小值
		List<Integer> num = new ArrayList<Integer>();
		//遍历把所有省份人数数据存到MAP中
		for (int i = 0; i < appList.size(); i++) {
			HashMap<String,String> procont = new HashMap<String, String>();
			procont.put("name", appList.get(i).getNm());
			procont.put("value", appList.get(i).getCountmnn());
			dataList.add(procont);
			num.add(Integer.parseInt(appList.get(i).getCountmnn()));
		}
		//取最大值
		int max = Collections.max(num);
		//取最小值
		int min =Collections.min(num);
		//遍历,省取出预约订单人数最多的省
		for (int i = 0; i < appList.size(); i++) {
			if( max == Integer.parseInt(appList.get(i).getCountmnn())){
				HashMap<String,String> maxMap = new HashMap<String, String>();
				maxMap.put("max", appList.get(i).getCountmnn());
				maxMap.put("name", appList.get(i).getNm());
				parmList.add(maxMap);
			}
		}
		//把人数最多的省份存入
		mapData.put("data", dataList);
		//把所有数据存到map套list套map中
		mapData.put("parm",parmList );
		return mapData;
	}
	
	@RequestMapping("/appointment")
	public String appointment(ModelMap mm){
		List<Appointmentorder> nyytjlist = appointmentorderService.queryYeartimes();
		System.out.println(nyytjlist);
//		每一年的预约次数
		ArrayList <String> yeartimeslist = new ArrayList<String>();
//		 每一年
		 ArrayList <String> yearlist = new ArrayList<String>();
		for (Appointmentorder yy : nyytjlist) {
			yearlist.add(yy.getNian());
			yeartimeslist.add(yy.getYycs());
		}
		mm.put("nyytjlist",nyytjlist);
		mm.put("yearlist",yearlist);
		System.err.println(yearlist);
		mm.put("yeartimeslist",yeartimeslist);

		return "statement/Yearstatistics";
	}
	
	   
	   
	
	/**
	 * @方法名: importExcel   
	 * @描述: 导出到excel
	 * @作者: 郎国峰
	 * @时间: 2017年11月1日 上午4:48:13
	 * @param String img 图片转换成base64 后的字符串
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/imgImportExcel")
	public void imgImportExcel(String img, HttpServletResponse response) throws ServletException, IOException {
	    try {
	    	String userName = System.getProperty("user.name");//获取系统用户名
	    	System.out.println("userName:"+userName);
	    	
	    	String filePath = "C:\\Users\\"+userName+"\\Desktop\\chart"; //拼接文件路径
	    	File file = new File(filePath); //根据路径创建文件
	    	if(!file.exists()){  //file.exists()   测试此抽象路径名表示的文件或目录是否存在。 存在返回true 否则false
	    		file.mkdir(); //创建文件夹
	    	}
	    	String fileName = filePath +"\\"+ System.currentTimeMillis()+".png";  //返回以毫秒为单位的当前时间 拼接文件名
	    	//根据data创建图片到桌面
	    	ExportImageToExcel.createImage(fileName, img);
	    	//根据桌面的图片创建excel
	    	ExportImageToExcel.createExcel(response, fileName);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	/**
	 * 
	 * @方法名: importDataForExcel   
	 * @描述: 将传入的患者预约地址统计数据导出为Excel表格的控制器
	 * @作者: 郎国峰
	 * @时间: 2017年11月2日 下午1:23:35
	 * @param provinceNames
	 * @param countPersons
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/dataImportExcel")
	public void importDataForExcel(String[] yearlist,String[] yeartimeslist,HttpServletResponse response ) throws IOException{
		//要导出的数据   行集合
		ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>(); 
		//遍历得到的数据 存储到dataList中
		for (int i = 0; i < yearlist.length; i++) {
			//要导出的数据   列集合
			ArrayList<String> columnList = new ArrayList<String>();
			columnList.add(yearlist[i]);//得到省名存入字段集合中
			columnList.add(yeartimeslist[i]);//得到预约人数存到字段集合中
			dataList.add(columnList);//将列集合放入行中
		}
		// 导出的文件名
		String fileName = "导出Excel.xls";  
		// 定义单元格报头  
        String worksheetTitle = "患者预约地址统计";  
        // 工作表名   字段名
        String[] columnNames = { "序号","省名称","预约患者总数"};
        ExportExcelUtil.exportExcel(response, dataList, fileName, worksheetTitle, columnNames);  
	}

	/**
	 *年度科室预约统计跳页
	* @Title: deptapp
	* @author 康磊 
	* @return
	* String
	* @throws
	* @date 2017年11月3日 下午12:31:57
	 */
	@RequestMapping("deptapp")
	public String deptapp(){
		
		return "statement/dept";
	}
	/**
	 * 年度科室预约统计  AJXA  请求
	* @Title: ajaxDeptQuery
	* @author 康磊 
	* @param year
	* @return
	* HashMap<String,ArrayList<String>>
	* @throws
	* @date 2017年11月3日 下午12:32:54
	 */
	@RequestMapping("/ajaxDeptQuery")
	@ResponseBody
	public HashMap<String,ArrayList<String>> ajaxDeptQuery(String year){
		HashMap<String,ArrayList<String>> map= appointmentorderService.ajaxDeptQuery(year);
		return map;
	}
	/**
	 * 导出 excel
	* @Title: dataImportExcel
	* @author 康磊 
	* @param provinceNames
	* @param countPersons
	* @param year
	* @param response
	* @throws IOException
	* void
	* @throws
	* @date 2017年11月3日 下午2:04:16
	 */
	@RequestMapping("/dataDeptImportExcel")
	public void dataImportExcel(String[] provinceNames,String[] countPersons,String year, HttpServletResponse response ) throws IOException{
		//要导出的数据   行集合
		System.out.println(year);
		ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>(); 
		//遍历得到的数据 存储到dataList中
		for (int i = 0; i < countPersons.length; i++) {
			//要导出的数据   列集合
			ArrayList<String> columnList = new ArrayList<String>();
			columnList.add(provinceNames[i]);//得到省名存入字段集合中
			columnList.add(countPersons[i]);//得到预约人数存到字段集合中
			dataList.add(columnList);//将列集合放入行中
		}
		// 导出的文件名
		String fileName = "导出Excel.xls";  
		// 定义单元格报头  
        String worksheetTitle = year+"年度科室预约统计";  
        // 工作表名   字段名
        String[] columnNames = { "序号","科室名称","预约总数"};
        ExportExcelUtil.exportExcel(response, dataList, fileName, worksheetTitle, columnNames);  
	}

	/**
	 * 
	 * @方法名: doctimes   
	 * @描述: 医生预约次数控制器
	 * @作者: 郎国峰
	 * @时间: 2017年11月2日15:39:20
	 * @return 返回到医生被预约次数页面
	 */
	@RequestMapping("/doctimes")
	public String doctimes(){
	return "statement/doctorSubscribeCountStatistics";
	}
	
	/**
	 * 
	 * @方法名: doctimesData   
	 * @描述: 根据传入的年份,在订单表中查询医生被预约次数信息
	 * @作者: 郎国峰
	 * @时间: 2017年11月2日 下午3:41:23
	 * @param year 传入的年份
	 * @return
	 */
	@RequestMapping("/doctimesData")
	@ResponseBody
	public HashMap<String,ArrayList<String>> doctimesData(String year){
		//调用模型层,根据年份,查询医生被预约次数
		HashMap<String,ArrayList<String>> map = service.queryDoctimesData(year);
		return map;
	}
	/**
	 * 
	 * @方法名: importDataForExcel   
	 * @描述: 将传入的医生被预约次数统计数据导出为Excel表格的控制器
	 * @作者: 郎国峰
	 * @时间: 2017年11月2日16:45:51
	 * @param provinceNames
	 * @param countPersons
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping("/doctimesDataImportExcel")
	public void doctimesDataImportExcel(String[] doctorName,String[] doctorCount,HttpServletResponse response ) throws IOException{
		//要导出的数据   行集合
		ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>(); 
		//遍历得到的数据 存储到dataList中
		for (int i = 0; i < doctorName.length; i++) {
			//要导出的数据   列集合
			ArrayList<String> columnList = new ArrayList<String>();
			columnList.add(doctorName[i]);//得到医生名存入字段集合中
			columnList.add(doctorCount[i]);//得到预约次数存到字段集合中
			dataList.add(columnList);//将列集合放入行中
		}
		// 导出的文件名
		String fileName = "导出Excel.xls";  
		// 定义单元格报头  
        String worksheetTitle = "医生被预约次数统计";  
        // 工作表名   字段名
        String[] columnNames = { "序号","医生姓名","被预约次数"};
        ExportExcelUtil.exportExcel(response, dataList, fileName, worksheetTitle, columnNames);  
	}
	
	/**
	 * 
	 * @方法名: docpre   
	 * @描述: 医生预约压力控制器
	 * @作者: 郎国峰
	 * @时间: 2017年11月2日20:49:54
	 * @return 返回到医生预约压力统计页面
	 */
	@RequestMapping("/docpre")
	public String docpre(){
	return "statement/doctorStressStatistics";
	}
	/**
	 * 
	 * @方法名: docpreData   
	 * @描述: 查询医生被预约压力控制器
	 * @作者: 郎国峰
	 * @时间: 2017年11月2日 下午3:41:23
	 * @param 
	 * @return
	 */
	@RequestMapping("/docpreData")
	@ResponseBody
	public ArrayList<Map<String,String>> docpreData(){
		//调用模型层,查询医生被预约压力
		ArrayList<Map<String,String>> list = service.queryDoctimesData();
		return list;
	}
	

	
	/**
	 * 年度疾病统计 查询年份的
	 * 赵星宇
	 * 2017年11月3日10:00:17
	 * 
	 */
	
	@RequestMapping("illnessApp")
	public String illnessApp(ModelMap mm){
		List<String> yearList = new ArrayList<String>();
		List<Appointmentorder> yearApp = appointmentorderService.yearApp();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		for (int i = 0; i < yearApp.size(); i++) {
			yearList.add(sdf.format(yearApp.get(i).getAppointmentTime()));
		}
		mm.put("yearList", yearList);
		return "statement/illnessApp";
	}
	
	
	/**
	 * 通过年份获取年度疾病预约统计
	 * 赵星宇
	 * 2017年11月3日10:00:13
	 * 
	 */
	@RequestMapping("yearIllness")
	@ResponseBody
	public List<Appointmentorder> yearIllness(String getYear){
		List<Appointmentorder> yearIllness = appointmentorderService.yearIllnessApp(getYear);
		return yearIllness;
		
	}
	/**
	 * @description 导出 疾病 年度预约次数统计 到Excle
	 * @author 赵星宇
	 * @time 2017年11月2日22:04:10
	 * @param request
	 * @param response
	 */
	@RequestMapping("/yearIllnessExcle")
	@ResponseBody
	public String yearIllnessExcle(ModelMap mm,String year,HttpServletRequest request,HttpServletResponse response){
		String fileName = null;
		if(year!=null && !"".equals(year)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			String path = request.getSession().getServletContext().getRealPath("/");

			// 创建excel
			HSSFWorkbook wb = new HSSFWorkbook();
			//创建sheet
			HSSFSheet sheet = wb.createSheet("年度疾病预约统计");
			//创建一行
			HSSFRow rowTitle = sheet.createRow(0);
			
			// 创建标题栏样式
			HSSFCellStyle styleTitle = wb.createCellStyle();
			//设置底边框
			styleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			// 设置左边框;
			styleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
	        // 设置右边框;
			styleTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
	        // 设置顶边框;
			styleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
	        // 居中
			styleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			
			
			HSSFFont fontTitle = wb.createFont();
			// 宋体加粗
			fontTitle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			fontTitle.setFontName("宋体");
			fontTitle.setFontHeight((short)200);
			styleTitle.setFont(fontTitle);
			
			
			// 在行上创建1列
			HSSFCell cellTitle = rowTitle.createCell(0);
			cellTitle.setCellValue("年度");// 列标题
			cellTitle.setCellStyle(styleTitle);// 样式
			
			// 在行上创建2列
			cellTitle = rowTitle.createCell(1);
			cellTitle.setCellValue("疾病");
			cellTitle.setCellStyle(styleTitle);
			
			// 在行上创建3列
			cellTitle = rowTitle.createCell(2);
			cellTitle.setCellValue("预约次数");
			cellTitle.setCellStyle(styleTitle);
			
			
			HSSFCellStyle styleCenter = wb.createCellStyle();
			styleCenter.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
			//设置底边框
			styleCenter.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			// 设置左边框;
			styleCenter.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			// 设置右边框;
			styleCenter.setBorderRight(HSSFCellStyle.BORDER_THIN);
			// 设置顶边框;
			styleCenter.setBorderTop(HSSFCellStyle.BORDER_THIN);
			
			
			//读取数据
			List<Appointmentorder> yearIllnessApp = appointmentorderService.yearIllnessApp(year);
			
			for (int i = 0; i < yearIllnessApp.size(); i++) {
				//创建行
				HSSFRow row = sheet.createRow(i + 1);
				//单元格1
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(sdf.format(yearIllnessApp.get(i).getAppointmentTime()).substring(0,4));
				cell.setCellStyle(styleCenter);
				//单元格2
				cell = row.createCell(1);
				cell.setCellValue(yearIllnessApp.get(i).getIllnessName());
				cell.setCellStyle(styleCenter);
				//单元格3
				cell = row.createCell(2);
				cell.setCellValue(yearIllnessApp.get(i).getStandby());
				cell.setCellStyle(styleCenter);
			}
			fileName = year+"年度疾病预约统计"+sdf.format(new Date())+".xls";
			//mm.put("fileName", fileName);
			try {
				FileOutputStream fout = new FileOutputStream(path+fileName);
				wb.write(fout);
				fout.close();
				//wb.close();
			} catch (Exception e) {
				System.err.println("路径有问题");
				e.printStackTrace();
			}
			
			System.err.println("导出完成！");
		}
		//return "system/report/illnessApp";
		return fileName;
	}
	
	/**
	 * 
	* @Title: ndtjdataImportExcel 
	* @Description: 把年度预约总数传过来的值 进行存储
	* @param @param year
	* @param @param people
	* @param @param response
	* @param @throws IOException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	@RequestMapping("/ndtjdataImportExcel")
	public void ndtjdataImportExcel(String[] year,String[] people,HttpServletResponse response ) throws IOException{
		//要导出的数据   行集合
		ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>(); 
		//遍历得到的数据 存储到dataList中
		for (int i = 0; i < year.length; i++) {
			//要导出的数据   列集合
			ArrayList<String> columnList = new ArrayList<String>();
			columnList.add(year[i]);//得到省名存入字段集合中
			columnList.add(people[i]);//得到预约人数存到字段集合中
			dataList.add(columnList);//将列集合放入行中
		}
		// 导出的文件名
		String fileName = "导出Excel.xls";  
		// 定义单元格报头  
        String worksheetTitle = "年度预约人数统计";  
        // 工作表名   字段名
        String[] columnNames = { "序号","年度","预约患者总数"};
        ExportExcelUtil.exportExcel(response, dataList, fileName, worksheetTitle, columnNames);  
	}
	

}
