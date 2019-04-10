package com.yunzhong.appointment.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/** 
 * @description 日期处理类
 * @author 石洪刚
 * @time 2017年8月28日14:34:51
 */
public class DateUtil {
	
	private final static SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
	private final static SimpleDateFormat sdfDay = new SimpleDateFormat("yyyy-MM-dd");
	private final static SimpleDateFormat sdfDays = new SimpleDateFormat("yyyyMMdd");
	private final static SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat sdfTimes = new SimpleDateFormat("yyyyMMddHHmmss");
	private final static SimpleDateFormat sdfWeek = new SimpleDateFormat("EEEE");
	private final static SimpleDateFormat sdfHHmm = new SimpleDateFormat("HH:mm");
	/**
	 * @description 获取yyyyMMddHHmmss格式
	 * @author 石洪刚
	 * @time 2017年9月11日14:16:37
	 * @return
	 */
	public static String getSdfTimes() {
		return sdfTimes.format(new Date());
	}
	
	/**
	 * @description 获取yyyy格式
	 * @author 石洪刚
	 * @time 2017年9月11日14:17:11
	 * @return
	 */
	public static String getYear() {
		return sdfYear.format(new Date());
	}

	/**
	 * @description 获取yyyy-MM-dd格式
	 * @author 石洪刚
	 * @time 2017年9月11日14:17:54
	 * @return
	 */
	public static String getDay() {
		return sdfDay.format(new Date());
	}
	
	/**
	 * @description 获取yyyy-MM-dd格式
	 * @author 石洪刚
	 * @time 2017年9月11日14:17:54
	 * @return
	 */
	public static String getDay(Date d) {
		return sdfDay.format(d);
	}
	
	/**
	 * @description 获取yyyyMMdd格式
	 * @author 石洪刚
	 * @time 2017年9月11日14:17:54
	 * @return
	 */
	public static String getDays(){
		return sdfDays.format(new Date());
	}
	
	/**
	 * @description 获取yyyyMMdd格式
	 * @author 石洪刚
	 * @time 2017年9月11日14:17:54
	 * @return
	 */
	public static String getDays(Date d){
		return sdfDays.format(d);
	}

	/**
	 * @description 获取yyyy-MM-dd HH:mm:ss格式
	 * @author 石洪刚
	 * @time 2017年9月11日14:17:54
	 * @return
	 */
	public static String getTime() {
		return sdfTime.format(new Date());
	}
	
	/**
	 * @description 获取yyyy-MM-dd HH:mm:ss格式
	 * @author 石洪刚
	 * @time 2017年9月11日14:17:54
	 * @return
	 */
	public static String getTime(Date d) {
		return sdfTime.format(d);
	}
	/**
	 * @description 获取HH:mm
	 * @author 石洪刚
	 * @time 2017年9月1日16:50:17
	 * @return
	 */
	public static String getHHmm(){
		return sdfHHmm.format(new Date());
	}
	/**
	 * @description 获取HH:mm
	 * @author 石洪刚
	 * @time 2017年9月1日16:50:17
	 * @return
	 */
	public static String getHHmm(Date d){
		return sdfHHmm.format(d);
	}

	/**
	* @description 日期比较，如果s>=e 返回true 否则返回false
	* @author 石洪刚
	* @time 2017年8月28日14:36:11
	* @param s
	* @param e
	* @return boolean  
	* @throws
	 */
	public static boolean compareDate(String s, String e) {
		if(fomatDate(s)==null||fomatDate(e)==null){
			return false;
		}
		return fomatDate(s).getTime() >=fomatDate(e).getTime();
	}

	/**
	 * @description 格式化日期yyyy-MM-dd
	 * @author 石洪刚
	 * @time 2017年9月11日14:22:04
	 * @return
	 */
	public static Date fomatDate(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * @description 格式化日期yyyy-MM-dd HH:mm
	 * @author 石洪刚
	 * @time 2017年9月11日14:22:35
	 * @return
	 */
	public static Date fomatDateTime(String date) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			return fmt.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * @description 校验日期是否合法
	 * @author 石洪刚
	 * @time 2017年9月11日14:23:00
	 * @return
	 */
	public static boolean isValidDate(String s) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			fmt.parse(s);
			return true;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return false;
		}
	}
	
	/**
	 * @description 得到相差的年数
	 * @author 石洪刚
	 * @time 2017年9月11日14:23:52
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public static int getDiffYear(String startTime,String endTime) {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		try {
			//long aa=0;
			int years=(int) (((fmt.parse(endTime).getTime()-fmt.parse(startTime).getTime())/ (1000 * 60 * 60 * 24))/365);
			return years;
		} catch (Exception e) {
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			return 0;
		}
	}
	 
	/**
     * @description 时间相减得到天数
     * @author 石洪刚
     * @time 2017年9月11日14:24:19
     * @param beginDateStr
     * @param endDateStr
     * @return
     * long 
     * @author Administrator
     */
    public static long getDaySub(String beginDateStr,String endDateStr){
        long day=0;
        java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
        java.util.Date beginDate = null;
        java.util.Date endDate = null;
        
            try {
				beginDate = format.parse(beginDateStr);
				endDate= format.parse(endDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
            //System.out.println("相隔的天数="+day);
      
        return day;
    }
    
    /**
     * @description 得到n天之后的日期
     * @author 石洪刚
     * @time 2017年9月11日14:52:59
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
    	int daysInt = Integer.parseInt(days);
    	
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        
        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);
        
        return dateStr;
    }
    
    /**
     * @description 得到n天之后是周几
     * @author 石洪刚
     * @time 2017年9月11日14:53:50
     * @param days
     * @return
     */
    public static String getAfterDayWeek(String days) {
    	int daysInt = Integer.parseInt(days);
        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("E");
        String dateStr = sdf.format(date);
        return dateStr;
    }
    

    /**
     * @description 根据日期得到星期
     * @author 石洪刚
     * @time 2017年8月28日14:38:31
     * @param date
     * @return
     */
    public static String getWeekByDate(Date date){
    	return sdfWeek.format(date);
    }
    
    /**
     * @description 根据日期的集合返回星期的集合并排序
     * @author 石洪刚
     * @time 2017年9月11日14:54:33
     * @param coll
     * @return
     */
    public static String sortWeeksByDateList(Collection<Date> coll){
    	Set<String> temSet = new HashSet<String>();
    	for (Date date : coll) {
			String week = DateUtil.getWeekByDate(date);
			temSet.add(week);
		}
    	
    	return DateUtil.sortWeek(temSet);
    }
    /**
     * @description 对星期的集合进行排序
     * @author 石洪刚
     * @time 2017年8月28日15:43:18
     * @param coll
     * @return
     */
    public static String sortWeek(Collection<String> coll){
    	StringBuffer weeks = new StringBuffer("");
    	if(coll==null){
    		return null;
    	}
    	if(coll.contains("星期一")){
    		weeks.append("星期一，");
    	}
    	if(coll.contains("星期二")){
    		weeks.append("星期二，");
    	}
    	if(coll.contains("星期三")){
    		weeks.append("星期三，");
    	}
    	if(coll.contains("星期四")){
    		weeks.append("星期四，");
    	}
    	if(coll.contains("星期五")){
    		weeks.append("星期五，");
    	}
    	if(coll.contains("星期六")){
    		weeks.append("星期六，");
    	}
    	if(coll.contains("星期日")){
    		weeks.append("星期日，");
    	}
    	if(weeks.length()>0){
    		return weeks.substring(0, weeks.length()-1).toString();
    	}else{
    		return null;
    	}
    }
}
