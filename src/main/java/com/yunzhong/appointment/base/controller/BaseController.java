package com.yunzhong.appointment.base.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yunzhong.appointment.util.FileUtils;


/**
 * 
 * @className BaseController
 * @description 基础控制器，用于处理共同业务
 * @author 石洪刚
 * @time 2017年10月2日 下午7:14:23
 */
@Controller
@RequestMapping("/base")
public class BaseController {

	@Autowired
	private HttpServletResponse response;
	/**
	 * 
	 * @methodName downloadByUrl
	 * @description 文件下载
	 * @author 石洪刚
	 * @time 2017年10月2日 下午7:14:41
	 * @param url
	 */
	@RequestMapping("/download")
	@ResponseBody
	public void downloadByUrl(String url){
		FileUtils.downloadFile(response,url);
	}

}
