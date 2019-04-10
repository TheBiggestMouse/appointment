<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html>
<html style="height: 100%">
   <head>
       <meta charset="utf-8">
   </head>
   <body style="height: 100%; margin: 0">
       <div id="container" style="height: 100%" ></div>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
       <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
       <button type="button" class="btn" onclick="importDataForExcel()">导出表格</button>
        	
        	
        	
        	
     <form id="dataImportExcel" action="<%=request.getContextPath() %>/data/ndtjdataImportExcel" method="post">
		<input type="hidden" name="provinceName"  />
		<input type="hidden" name="countPerson"  />
	</form>  	

   </body>
<script type="text/javascript">
//抓取div 
var dom = document.getElementById("container");
var myChart = echarts.init(dom);
var app = {};
option = null;
app.title = '坐标轴刻度与标签对齐';

option = {
    color: ['#3398DB'],
    tooltip : {
        trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : ${yearlist}, //人数
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'直接访问',
            type:'bar',
            barWidth: '60%',
            data:${yeartimeslist} //年度
        }
    ]
};
;
if (option && typeof option === "object") {
    myChart.setOption(option, true);
}





function importDataForExcel(){

		//jQuery抓取到form表单
		var form = $("#dataImportExcel");
		//清空form表单
		form.empty();
		//把值穿到年度list和人数list中
		var ndlist =  ${yeartimeslist};
		var rslist =  ${yearlist};
		//遍历importData,将数据添加到form表单中
		for(var i = 0;i <ndlist.length;i++){
			//得到年度
			var year = ndlist[i];
			//得到人数
			var people = rslist[i];
			//声明要添加的form表单里的表单控件
			var provinceInput = "<input type='hidden'  name='year' value='"+year+"' /><input  type='hidden'  name='people' value='"+people+"'  />";
			form.append(provinceInput);
		}
		//调用提交按钮
		form.submit(); 
	} 		
       </script>
</html>