package com.yunzhong.test;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;

import com.redfin.sitemapgenerator.ChangeFreq;
import com.redfin.sitemapgenerator.WebSitemapGenerator;
import com.redfin.sitemapgenerator.WebSitemapUrl;

public class test {
	public static void main(String[] args) throws MalformedURLException {
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		JsoupTool jsoup = new JsoupTool();
//		ArrayList<String> list = jsoup.getAllUrls("http://192.168.1.139:8089/");
//		//遍历list
//		for (String string : list) {
//			System.out.println(string);
//		}
		
		// 压缩输出  true  不压缩输出false
	    WebSitemapGenerator sitemapGenerator = WebSitemapGenerator.builder("http://192.168.1.139:8089/", new File("src/main/webapp")).gzip(false).build();

//	    WebSitemapUrl sitemapUrl = new WebSitemapUrl.Options(
//	    	"http://www.javatips.net/blog/2011/08/findbugs-in-eclipse-java-tutorial")
//	    	.lastMod(new Date()).priority(1.0)
//	        .changeFreq(ChangeFreq.HOURLY).build();
	    // this will configure the URL with lastmod=now, priority=1.0,
	    // changefreq=hourly

	    // 可以在这里添加任意数量的url
//	    sitemapGenerator.addUrl(sitemapUrl);
	    
	    
	    //遍历list得到所有的url
//	    for (int i = 0; i < list.size(); i++) {
//			String url = list.get(i);
//			sitemapGenerator.addUrl(url);
//		}
//	    sitemapGenerator.addUrl("http://www.javatips.net/blog/2011/09/create-sitemap-using-java");
//	    sitemapGenerator.addUrl("http://www.192.168.1.139:8089/static/js/jedate/skin/jedate.css");
	    sitemapGenerator.write();
	}
}
