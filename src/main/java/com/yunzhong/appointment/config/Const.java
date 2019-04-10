package com.yunzhong.appointment.config;

/**
 * 
 * @className Const
 * @description 常量类
 * @author 石洪刚
 * @time 2017年10月2日 下午7:16:13
 */
public class Const {
	public static final String UNAUTHORIZED_PAGE = "/error/unauthorized";                   //无权限跳转的页面 
 	public static final String TRUE = "T";
	public static final String FALSE = "F";
	public static final String MESSAGE = "message";
	public static final Integer PAGE_SIZE	= 5;				                            //分页每页最多显示条数
	public static final String PAGE_NUM = "PageNum";                                        //当前页号
	public static final String PIC_FILE = "D:/upload";                                      //存储头像路径
	public static final String DB_PREFIX = "spring.datasource";                            //连库信息前缀
	public static final String ROLE_PATIENT = "patient";                                    //患者角色关键词
	public static final String SALT = "342dsasoi453ef-wefsdvc";                            //用于加密的盐
}
