package com.yunzhong.appointment.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.yunzhong.appointment.config.Const;

/**
 * @description 用于文件的上传和删除
 * @author 石洪刚
 * @time 2017年8月21日23:26:18
 *
 */
public class FileUtils {
	
	
	/**
	 * @description 文件上传，保存在本地硬盘，返回文件请求路径
	 * @author 石洪刚
	 * @time 2017年8月21日23:28:08
	 * @param picFile
	 * @return
	 */
	public static String saveFile(MultipartFile picFile) {
		String location = null;
		if(!picFile.isEmpty()){
			//得到真实路径
			String fileName = picFile.getOriginalFilename();
			String kzm = fileName.substring(fileName.lastIndexOf("."));
			//要保存的文件名
			String destName = UUID.randomUUID().toString()+kzm;
			//取得上传文件存储路径
			String path = Const.PIC_FILE;
			//如果上传文件存储路径不存在则创建一个
			File s2 = new File(path);
			if (s2.exists()==false) {
				s2.mkdirs();
			}
			//文件路径
			location = path+"/"+destName;
			try {
				picFile.transferTo(new File(location));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return location;
	}
	/**
	 * @description 根据文件请求路径删除文件
	 * @author 石洪刚
	 * @time 2017年8月21日23:43:55
	 * @param url
	 * @param request
	 */
	public static void deleteFileByUrl(String url){
		if(url!=null){
			File file = new File(url);
			if(file.exists()){
				file.delete();
			}
		}
	}
	
	/**
	 * @description 文件的下载
	 * @author 石洪刚
	 * @time 2017年8月21日23:47:31
	 * @param response
	 * @param location
	 */
	public static void downloadFile(HttpServletResponse response,String location){
		//判断文件是否存在
		File s2 = new File(location);
		if (!s2.exists()) {
			return;
		}
		String fileName = location.substring( location.lastIndexOf("/")+1 );
		response.setHeader("content-type", "application/octet-stream");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "inline;filename=" + fileName);
		byte[] buff = new byte[1024];
		BufferedInputStream bis = null;
	    OutputStream os = null;
		try {
			os = response.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(new File(location)));
			int i = bis.read(buff);
			while (i != -1) {
				os.write(buff, 0, buff.length);
				os.flush();
				i = bis.read(buff);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (bis != null) {
		        try {
		          bis.close();
		        } catch (IOException e) {
		          e.printStackTrace();
		        }
		     }
		}
	}
	
}
