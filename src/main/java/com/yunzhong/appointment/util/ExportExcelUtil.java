package com.yunzhong.appointment.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
/**
 * 
* @Title: ExportExcelUtil.java
* @Package com.yunzhong.appointment.util
* @Description: TODO(导出Excel)
* @author 郎国峰
* @date 2017年11月1日 下午3:06:40
* @version V1.0
 */
public class ExportExcelUtil {
	/**
	 * 
	 * @方法名: exportExcel   
	 * @描述:
	 * @作者: 郎国峰
	 * @时间: 2017年11月2日 下午1:14:29
	 * @param response
	 * @param dataList
	 * @param fileName
	 * @param worksheetTitle
	 * @param columnNames
	 * @throws IOException
	 */
	public static void exportExcel(HttpServletResponse response,
			ArrayList<ArrayList<String>> dataList, String fileName,
			String worksheetTitle, String[] columnNames) throws IOException {
		response.reset();  
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);// 指定下载的文件名  
        response.setContentType("application/vnd.ms-excel");  
        response.setHeader("Pragma", "no-cache");  
        response.setHeader("Cache-Control", "no-cache");  
        response.setDateHeader("Expires", 0);  
        OutputStream output = response.getOutputStream(); // 抛出文件流提供下载功能的
        BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);  
        // 创建通用的工作簿
        HSSFWorkbook wb = new HSSFWorkbook();  
        // 创建单元格样式  
        HSSFCellStyle cellStyleTitle = wb.createCellStyle();  
        // 指定单元格居中对齐  
        cellStyleTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        // 指定单元格垂直居中对齐  
        cellStyleTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        // 指定当单元格内容显示不下时自动换行  
        cellStyleTitle.setWrapText(true);  
        // ------------------------------------------------------------------  
        HSSFCellStyle cellStyle = wb.createCellStyle();  
        // 指定单元格居中对齐  
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
        // 指定单元格垂直居中对齐  
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
        // 指定当单元格内容显示不下时自动换行  
        cellStyle.setWrapText(true);  
        // ------------------------------------------------------------------  
        // 设置单元格字体  
        HSSFFont font = wb.createFont();  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
        font.setFontName("宋体");  
        font.setFontHeight((short) 200);  
        cellStyleTitle.setFont(font);  
        //根据通用工作簿,创建excel工作表 
        HSSFSheet sheet = wb.createSheet(); 
        // 定义第一行  
        HSSFRow row0 = sheet.createRow(0);  //用工作表创建一行
        HSSFCell cell0 = row0.createCell(0);//用行创建列
        cell0.setCellStyle(cellStyleTitle);   //设置第一行列标题样式
        cell0.setCellValue(worksheetTitle); //设置第一行列的值
        //合并单元格   参数依次  开始行   开始列  结束行   结束列
        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short)(columnNames.length-1)));
        
        /**
         * 给第二行添加字段
         */
        HSSFRow row1 = sheet.createRow(1);  //用工作表创建一行
        for (int i = 0; i < columnNames.length; i++) {//第二行的每一列
        	 HSSFCell cell1 = row1.createCell(i);//用行创建列
             cell1.setCellStyle(cellStyleTitle);   //设置第二行列标题样式
             cell1.setCellValue(new HSSFRichTextString(columnNames[i])); //设置第二行列的值
		}
        /**
         * 遍历数据依次添加到excel工作表中
         */
        HSSFRow row = null;  //声明一行
        HSSFCell cell = null; //声明一列
        for (int i = 0; i < dataList.size(); i++) { //遍历行集合,得到一列
        	row = sheet.createRow(i + 2);  //创建行
        	cell = row.createCell(0);   //创建第一列
            cell.setCellStyle(cellStyle);  
            cell.setCellValue(i+1);//给第一列填入序号
        	ArrayList<String> columnList = dataList.get(i);//得到列集合
        	for (int j = 0; j < columnList.size(); j++) {//遍历列集合,得到每个字段
        		cell = row.createCell(j+1);   //创建列
                cell.setCellStyle(cellStyle);  
                cell.setCellValue(columnList.get(j));//给列赋值
			}
        }  
        try {  
            bufferedOutPut.flush();  //刷新此缓冲的输出流。强制使所有缓冲的输出字节被写出到底层输出流中。 
            wb.write(bufferedOutPut);//将此工作簿写入到OutputStream
            bufferedOutPut.close();  //关闭缓存
        } catch (IOException e) {  
            e.printStackTrace();  
            System.out.println("Output   is   closed ");  
        } finally {  
        	dataList.clear(); //清空数据,以便下次使用 
        }
	}

	
/**
 * 描述: 不太好的工具类    
 * 作者: 郎国峰
 * 时间: 2017年11月2日11:22:11	
 */
//	/**
//	 * 通用工作簿
//	 */
//	private HSSFWorkbook wb = null;  
//	/**
//	 * 创建excel工作表
//	 */
//    private HSSFSheet sheet = null;  
//    /**
//     * 作者: 郎国峰
//     * 时间: 2017年11月2日07:43:10
//     * 描述: 有参构造
//     */
//    public  ExportExcelUtil(HSSFWorkbook wb, HSSFSheet sheet) {  
//        // super();  
//        this.wb = wb;  
//        this.sheet = sheet;  
//    }  
// 
//    /**
//     * 
//     * @方法名: createNormalHead   
//     * @描述: 创建通用EXCEL头部 
//     * @作者: 郎国峰
//     * @时间: 2017年11月2日 上午7:49:21
//     * @param headString 头部名称
//     * @param colSum 列数
//     */
//    @SuppressWarnings({ "deprecation", "unused" })  
//    public void createNormalHead(String headString, int colSum) {  
//        HSSFRow row = sheet.createRow(0);  
//        // 设置第一行  
//        HSSFCell cell = row.createCell(0);  
//        // row.setHeight((short) 1000);  
//  
//        // 定义单元格为字符串类型  
//        cell.setCellType(HSSFCell.ENCODING_UTF_16);// 中文处理  
//        cell.setCellValue(new HSSFRichTextString(headString));  
//  
//        // 指定合并区域  
//        /** 
//         * public Region(int rowFrom, short colFrom, int rowTo, short colTo) 
//         */  
//        sheet.addMergedRegion(new Region(0, (short) 0, 0, (short) colSum));  
//  
//        // 定义单元格格式，添加单元格表样式，并添加到工作簿  
//        HSSFCellStyle cellStyle = wb.createCellStyle();  
//        // 设置单元格水平对齐类型  
//        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐  
//        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐  
//        cellStyle.setWrapText(true);// 指定单元格自动换行  
//  
//        // 设置单元格字体  
//        HSSFFont font = wb.createFont();  
//        // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
//        // font.setFontName("宋体");  
//        // font.setFontHeight((short) 600);  
//        // cellStyle.setFont(font);  
//        cell.setCellStyle(cellStyle);  
//    }  
//  
//    /** 
//     * 创建通用报表第二行 
//     *  
//     * @param params 
//     *            统计条件数组 
//     * @param colSum 
//     *            需要合并到的列索引 
//     */  
//    @SuppressWarnings("deprecation")  
//    public void createNormalTwoRow(String[] params, int colSum) {  
//        // 创建第二行  
//        HSSFRow row1 = sheet.createRow(1);  
//  
//        row1.setHeight((short) 400);  
//  
//        HSSFCell cell2 = row1.createCell(0);  
//  
//        cell2.setCellType(HSSFCell.ENCODING_UTF_16);  
//        cell2.setCellValue(new HSSFRichTextString("时间：" + params[0] + "至"  
//                + params[1]));  
//  
//        // 指定合并区域  
//        /** 
//         * public Region(int rowFrom, short colFrom, int rowTo, short colTo) 
//         */  
//        sheet.addMergedRegion(new Region(1, (short) 0, 1, (short) colSum));  
//  
//        HSSFCellStyle cellStyle = wb.createCellStyle();  
//        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐  
//        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐  
//        cellStyle.setWrapText(true);// 指定单元格自动换行  
//  
//        // 设置单元格字体  
//        HSSFFont font = wb.createFont();  
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
//        font.setFontName("宋体");  
//        font.setFontHeight((short) 250);  
//        cellStyle.setFont(font);  
//  
//        cell2.setCellStyle(cellStyle);  
//    }  
//  
//    /** 
//     * 设置报表标题 
//     *  
//     * @param columHeader 
//     *            标题字符串数组 
//     */  
//    public void createColumHeader(String[] columHeader) {  
//  
//        // 设置列头 在第三行  
//        HSSFRow row2 = sheet.createRow(2);  
//  
//        // 指定行高  
//        row2.setHeight((short) 600);  
//  
//        HSSFCellStyle cellStyle = wb.createCellStyle();  
//        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐  
//        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐  
//        cellStyle.setWrapText(true);// 指定单元格自动换行  
//  
//        // 单元格字体  
//        HSSFFont font = wb.createFont();  
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
//        font.setFontName("宋体");  
//        font.setFontHeight((short) 250);  
//        cellStyle.setFont(font);  
//  
//        // 设置单元格背景色  
//        cellStyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);  
//        cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);  
//  
//        HSSFCell cell3 = null;  
//  
//        for (int i = 0; i < columHeader.length; i++) {  
//            cell3 = row2.createCell(i);  
//            cell3.setCellType(HSSFCell.ENCODING_UTF_16);  
//            cell3.setCellStyle(cellStyle);  
//            cell3.setCellValue(new HSSFRichTextString(columHeader[i]));  
//        }  
//    }  
//  
//    /** 
//     * 创建内容单元格 
//     *  
//     * @param wb 
//     *            HSSFWorkbook 
//     * @param row 
//     *            HSSFRow 
//     * @param col 
//     *            short型的列索引 
//     * @param align 
//     *            对齐方式 
//     * @param val 
//     *            列值 
//     */  
//    public void cteateCell(HSSFWorkbook wb, HSSFRow row, int col, short align,  
//            String val) {  
//        HSSFCell cell = row.createCell(col);  
//        cell.setCellType(HSSFCell.ENCODING_UTF_16);  
//        cell.setCellValue(new HSSFRichTextString(val));  
//        HSSFCellStyle cellstyle = wb.createCellStyle();  
//        cellstyle.setAlignment(align);  
//        cell.setCellStyle(cellstyle);  
//    }  
//  
//    /** 
//     * 创建合计行 
//     *  
//     * @param colSum 
//     *            需要合并到的列索引 
//     * @param cellValue 
//     */  
//    @SuppressWarnings("deprecation")  
//    public void createLastSumRow(int colSum, String[] cellValue) {  
//  
//        HSSFCellStyle cellStyle = wb.createCellStyle();  
//        cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐  
//        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐  
//        cellStyle.setWrapText(true);// 指定单元格自动换行  
//  
//        // 单元格字体  
//        HSSFFont font = wb.createFont();  
//        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);  
//        font.setFontName("宋体");  
//        font.setFontHeight((short) 250);  
//        cellStyle.setFont(font);  
//        // 获取工作表最后一行  
//        HSSFRow lastRow = sheet.createRow((short) (sheet.getLastRowNum() + 1));  
//        HSSFCell sumCell = lastRow.createCell(0);  
//  
//        sumCell.setCellValue(new HSSFRichTextString("合计"));  
//        sumCell.setCellStyle(cellStyle);  
//        
//        
//        
//        
//        // 合并 最后一行的第零列-最后一行的第一列  
//        sheet.addMergedRegion(new Region(sheet.getLastRowNum(), (short) 0,  
//                sheet.getLastRowNum(), (short) colSum));// 指定合并区域  
//  
//        for (int i = 2; i < (cellValue.length + 2); i++) {  
//            // 定义最后一行的第三列  
//            sumCell = lastRow.createCell(i);  
//            sumCell.setCellStyle(cellStyle);  
//            // 定义数组 从0开始。  
//            sumCell.setCellValue(new HSSFRichTextString(cellValue[i - 2]));  
//        }  
//    }  
//  
//    /** 
//     * 输入EXCEL文件 
//     *  
//     * @param fileName 
//     *            文件名 
//     */  
//    public void outputExcel(String fileName) {  
//        FileOutputStream fos = null;  
//        try {  
//            fos = new FileOutputStream(new File(fileName));  
//            wb.write(fos);  
//            fos.close();  
//        } catch (FileNotFoundException e) {  
//            e.printStackTrace();  
//        } catch (IOException e) {  
//            e.printStackTrace();  
//        }  
//    }  
//  
//    /** 
//     * @return the sheet 
//     */  
//    public HSSFSheet getSheet() {  
//        return sheet;  
//    }  
//  
//    /** 
//     * @param sheet 
//     *            the sheet to set 
//     */  
//    public void setSheet(HSSFSheet sheet) {  
//        this.sheet = sheet;  
//    }  
//  
//    /** 
//     * @return the wb 
//     */  
//    public HSSFWorkbook getWb() {  
//        return wb;  
//    }  
//  
//    /** 
//     * @param wb 
//     *            the wb to set 
//     */  
//    public void setWb(HSSFWorkbook wb) {  
//        this.wb = wb;  
//    }  
}
