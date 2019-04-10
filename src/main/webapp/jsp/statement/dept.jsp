<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>患者预约地址统计</title>
<!-- 
	作者: 郎国峰
	时间: 2017年10月31日10:36:33
	描述: 引入echarts文件 
-->
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/ECharts/echarts.js"></script>
</head>
<body>
	<!-- 
		作者: 郎国峰
		时间: 2017年10月31日11:48:32
		描述: 根据选择年度进行条件查询
	 -->
	<form class="form form-horizontal  method="post">
	    <div class="form-group" style="margin-top: 10px; margin-right:15px;" >
			<label class="control-label col-sm-10 ">请选择要查询的年度：</label>
			<div class="col-sm-2">
				<select class="form-control" name="years" id="year" onchange="mapShow(this.value)" >
					<option value="2017">2017年</option>
					<option value="2016">2016年</option>
					<option value="2015">2015年</option>
				</select>
			</div>
		</div>
		<!-- 
			将数据传入后台  到出excel
			康磊 
			2017年11月3日 12:53:26
		 -->
    </form>
    	<form id="dataImportExcel" action="<%=request.getContextPath() %>/data/dataDeptImportExcel" method="post">
		<input type="hidden" name="provinceName"  />
		<input type="hidden" name="countPerson"  />
		<input type="hidden" name="yearImport"  />
	</form>
		
       <div style="margin-top:18px; margin-left:80%"  >
		<input type="button" style="padding-bottom;'5%';" value="导出数据到Excel中" onclick="importDataForExcel()" />
	</div>
	
   <div id="chartmain" style="width: 100%;  height:80%;  "></div>
	
	
	
    <script type="text/javascript">
    var importData = null;
    $(function(){mapShow('2017')});
    
    function mapShow(year){
		var url = "${pageContext.request.contextPath }/data/ajaxDeptQuery?sjs="+Math.random();
   		var data = {"year":year};
   		$.post(url,data,function(result){
   			importData = result;
   			var names = result.listName;
   			var counts = result.listCount;
   			var option = {
   	        	    title: {
   	        	        text: '年度科室预约统计',
   	        	        subtext: 'hello'
   	        	    },
   	        	    tooltip: {
   	        	        trigger: 'axis',
   	        	        axisPointer: {
   	        	            type: 'shadow'
   	        	        }
   	        	    },
   	        	    grid: {
   	        	        left: '8%',
   	        	        right: '4%',
   	        	        bottom: '3%',
   	        	        containLabel: true
   	        	    },
   	        	    xAxis: {
   	        	        type: 'value',
   	        	        boundaryGap: [0, 0.01]
   	        	    },
   	        	    yAxis: {
   	        	        type: 'category',
   	        	        data: names
   	        	    },
   	        	    series: [
   	        	      
   	        	        {
   	        	            name: '2012年',
   	        	            type: 'bar',
   	        	            data: counts
   	        	        }
   	        	    ]
   	        	};
   	        //初始化echarts实例
   	        var myChart = echarts.init(document.getElementById('chartmain'));
   	        //使用制定的配置项和数据显示图表
   	        myChart.setOption(option);
   			},"json");
   		}

    	function importDataForExcel(){
    		//得到当前年度
    		alert("*************")
    		var year = $("select[name='years'] :selected").val();
     		//jQuery抓取到form表单
       		var form = $("#dataImportExcel");
       		alert("****=------***")
     		//清空form表单
     		form.empty();
       		form.append("<input type='hidden' name='year' value='"+year+"' >");
     		//得到存储省名,人数的list
     		var listName = importData.listName;
     		var listCount = importData.listCount;
     		//遍历importData,将数据添加到form表单中
     		for(var i = 0;i < listName.length;i++){
     			//得到每一组省名称,和省患者预约次数
     			var provinceName = listName[i];
     			//得到省患者预约次数
     			var countPerson = listCount[i];
     			//声明要添加的form表单里的表单控件
     			var provinceInput = "<input type='hidden'  name='provinceNames' value='"+provinceName+"' /><input  type='hidden'  name='countPersons' value='"+countPerson+"'  />";
     			form.append(provinceInput);
     		}
     		//调用提交按钮
     		form.submit(); 
       	} 		
    
        
    </script>
</body>
</html>