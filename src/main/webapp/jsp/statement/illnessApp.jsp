<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>云众科技网上预约挂号系统</title>
	</head>
	<body>
	<body style="height: 100%; margin: 0">
       
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts-stat/ecStat.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/dataTool.min.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/china.js"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/map/js/world.js"></script>
       <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=ZUONbpqGBsYGXNIYHicvbAbM"></script>
       <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/extension/bmap.min.js"></script>
       <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/ECharts/echarts.js"></script>
          
           
	 <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	 	<button type="button" class="btn" onclick="yearIllnessExcle()">导出表格</button>
	 	<select name="s" onchange="getYear(this.value)">
			<option value="">选择年份</option>
			<c:forEach items="${yearList}" var="year">
			<option value="${year}">${year}</option>
			</c:forEach>
		</select>
		<div id = "main" style="width:790px; height:465px"></div>
	</body>
	<script type="text/javascript">
	//Excle保存路径 拼接一个路径 直接保存
	function yearIllnessExcle(){
		var year = $("select[name='s']").val();
		$.ajax({
			url:"/data/yearIllnessExcle?year="+year,
			success:function(msg){
				downloadFile("http://localhost:8080/"+msg);//连本地
			},
			type:"text"
		});
		
	}
	function downloadFile(url) {   
        try{ 
            var elemIF = document.createElement("iframe");   
            elemIF.src = url;   
            elemIF.style.display = "none";   
            document.body.appendChild(elemIF);   
        }catch(e){ 
 
        } 
    }
	var illness = [];
	var count = [];
	
	function getYear(getYear){
		
		if(getYear!=""){
			// 基于准备好的dom，初始化echarts实例
			var myChart = echarts.init(document.getElementById('main'));
			$.ajax({
				url:"<%=request.getContextPath()%>/data/yearIllness?getYear="+getYear+"&r="+Math.random(),
				data:getYear,
				async:false,
				success:function(msg){
					for(var i=0 ; i<msg.length ; i++){
						illness[i] = msg[i].illnessName;
						count[i] = msg[i].standby;
					}
					
				},
				type:"json"
			});		
			
			var illname = illness;
			var illcount = count;
			
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
				            data : illname,
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
				            data:illcount
				        }
				    ]
				};
			
			
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
			
			
		}
		
		
		
	}
	
	
	
	
	
	
	</script>
</html>