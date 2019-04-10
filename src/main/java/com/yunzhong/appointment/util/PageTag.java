package com.yunzhong.appointment.util;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageInfo;
import com.yunzhong.appointment.config.Const;
/**
 * @description 分页标签实现类
 * @author 石洪刚
 * @time 2017年8月16日20:05:26
 */
public class PageTag extends TagSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//分页对象名称
	private String pageName;
	//请求路径
	private String url;
	
	
	@Override
	public int doStartTag() throws JspException {
		//获取web资源
		HttpServletRequest request = (HttpServletRequest)super.pageContext.getRequest();
		JspWriter out = super.pageContext.getOut();
		//获取项目名
		String path = request.getContextPath();
		//得到分页对象
		PageInfo page = (PageInfo) request.getAttribute(pageName);
		int pageNum = page.getPageNum();
		long pageCount = page.getPages();
		long rowCount = page.getTotal();
		if(url.substring(0, 1).equals("/") == false  ){
			url = "/"+url;
		}
		StringBuffer tagStr = new StringBuffer("<ul class=\"pagination\">\n");
		tagStr.append("<input id='yunzhongpageul9527' type=\"hidden\">\n");
		tagStr.append("<li><a href=\"javascript:nextPage(1)\">&laquo;</a></li>\n");
		tagStr.append("<li><a href=\"javascript:nextPage("+(pageNum<=1?1:pageNum-1)+")\">上一页</a></li>\n");
		tagStr.append("<li><a href=\"javascript:nextPage("+(pageNum>=pageCount?pageCount:pageNum+1)+")\">下 一页</a></li>\n");
		tagStr.append("<li><a href=\"javascript:nextPage("+pageCount+")\">&raquo;</a></li>\n");
		tagStr.append("<li><a href=\"#\">pages:"+pageNum+"/"+pageCount+" rows:"+rowCount+"</a></li>\n");
		tagStr.append("</ul>\n");
		tagStr.append("<script type=\"text/javascript\">\n");
		//转页函数
		tagStr.append("function nextPage(pageNum){\n");
		tagStr.append("     var url = \""+path+url+"?"+Const.PAGE_NUM+"=\"+pageNum;\n");
		tagStr.append("     var ipt = document.getElementById(\"yunzhongpageul9527\");\n");
		tagStr.append("     console.info(ipt);\n");
		tagStr.append("		ipt.form.action = url;\n");
		tagStr.append("		ipt.form.submit();\n");
		tagStr.append("	}\n");
		tagStr.append("function getFormByObj(obj){\n");
		tagStr.append("console.info(obj.nodeName);\n");
		tagStr.append("console.info('--------');\n");
		
		tagStr.append("      if (obj.parentNode.nodeName.toLowerCase() == \"form\") {\n");
		tagStr.append("      console.info(obj.nodeName);\n");
		
		tagStr.append("      	return obj.parentNode;\n");
		tagStr.append("      } else {\n");
		tagStr.append("      	getFormByObj(obj.parentNode);\n");
		tagStr.append("      }\n");
		tagStr.append("	}\n");
		tagStr.append("</script>\n");
		try {
			out.print(tagStr);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.doStartTag();
	}
	
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

}
