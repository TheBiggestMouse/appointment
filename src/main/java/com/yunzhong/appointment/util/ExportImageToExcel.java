package com.yunzhong.appointment.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import sun.misc.BASE64Decoder;
/**
 * 
* @Title: ExportToExcel.java
* @Package com.yunzhong.appointment.util
* @Description: TODO(ECharts报表生成Excel工具类)
* @author 郎国峰
* @date 2017年11月1日 上午5:28:04
* @version V1.0
 */
public class ExportImageToExcel {
	/**
	 * @方法名: createImage   
	 * @描述: 根据传入的data 按 filename 创建图片
	 * @作者: 郎国峰
	 * @时间: 2017年11月1日 上午5:29:04
	 * @param fileName 
	 * @param data 根据ECharts 报表转换的base64码
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void createImage(String fileName, String data) 
			throws ServletException, IOException {
		try {
	        String[] url = data.split(",");//以,分割成两个数组,[0]位置是  头信息     data:image/png;base64
	        String u = url[1]; //[1]位置是 图片信息    iVBORw0KGgoAAAANSUhEUg...
	        // Base64解码  创建base64编码的字节数组 用作写字节流的缓存
	        byte[] b = new BASE64Decoder().decodeBuffer(u);
	        // 生成图片
	        OutputStream out = new FileOutputStream(new File(fileName));
	        out.write(b);
	        out.flush();
	        out.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @方法名: createExcel   
	 * @描述: 根据传入的图片路径,创建excel,并将图片写入excel
	 * @作者: 郎国峰
	 * @时间: 2017年11月1日 上午5:39:14
	 * @param response
	 * @param fileName 传入的图片路径
	 * @throws ServletException
	 * @throws IOException
	 */
	public static void createExcel( HttpServletResponse response, String fileName) 
			throws ServletException, IOException {
        
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("echart");
		HSSFRow row = null;
        row = sheet.createRow(60);
        
        /*HSSFCell headerCell = row.createCell(0);    
        headerCell.setCellType(HSSFCell.CELL_TYPE_BLANK);  
        headerCell.setCellValue("echarts");*/
        
        row = sheet.createRow(60);
        HSSFCell cells = row.createCell(0);
        cells.setCellType(HSSFCell.CELL_TYPE_BLANK);
        
        ByteArrayOutputStream outStream = new ByteArrayOutputStream(); // 将图片写入流中
        BufferedImage bufferImg = ImageIO.read(new File(fileName));
        ImageIO.write(bufferImg, "PNG", outStream); // 利用HSSFPatriarch将图片写入EXCEL
        HSSFPatriarch patri = sheet.createDrawingPatriarch();
        HSSFClientAnchor anchor = new HSSFClientAnchor(10, 10, 20, 20,
          (short) 10, 20, (short) 20, 45);
        
        patri.createPicture(anchor, wb.addPicture(
          outStream.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG));
        
        try {
        	OutputStream out = null;
        	response.setContentType("application/x-msdownload");
        	response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode("echarts.xls", "UTF-8"));
        	out = response.getOutputStream();	        
        	wb.write(out);
        	out.flush();
        	out.close();		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
