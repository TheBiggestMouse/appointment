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
<!-- 
	作者: 郎国峰
	时间: 2017年10月31日10:36:48
	描述: 引入地图文件 
-->
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/ECharts/china.js"></script>
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
				<select class="form-control" id="year" onchange="mapShow(this.value)" >
				
					<option value="2017">2017年</option>
					<option value="2016">2016年</option>
					<option value="2015">2015年</option>
				</select>
			</div>
		</div>
    </form>
   
	<!-- 
		作者: 郎国峰
		时间: 2017年11月1日04:30:08
		描述: 将图片传入后台,然后在导出
	 -->
	<form id="exportForm" action="<%=request.getContextPath() %>/data/imgImportExcel" method="post">
		<input type="hidden" name="img" id="img" />
	</form>
	<!-- 
		作者: 郎国峰
		时间: 2017年11月1日12:02:17
		描述: 将数据传入后台,然后在导出Excel
	 -->
	<form id="dataImportExcel" action="<%=request.getContextPath() %>/data/dataImportExcel" method="post">
		<input type="hidden" name="provinceName"  />
		<input type="hidden" name="countPerson"  />
	</form>
	 <!-- 
	 	作者: 郎国峰
	 	时间: 2017年10月31日10:37:38
	 	描述:  为 ECharts 准备一个具备大小（宽高）的 DOM 
	 -->
    <div id="main" style="width: 100%;  height:100%;  "></div>
     <!-- 
    	作者: 郎国峰
    	时间: 2017年11月1日04:28:57
    	描述: 点击调用导出图片到excel中的js方法
     -->
    <div style="margin-top:20px;">
		<input type="button" style="padding-bottom;'5%';" value="导出图片到Excel中" onclick="openImage()" />
		<input type="button" style="padding-bottom;'5%';" value="导出数据到Excel中" onclick="importDataForExcel()" />
	</div>
    <script type="text/javascript">
	    /* 
			作者: 郎国峰
			时间: 2017年10月31日10:45:24
			描述: 基于准备好的dom，初始化echarts实例
		*/
		var myChart = echarts.init(document.getElementById('main'));
    	/* 
    		作者: 郎国峰
    		时间: 2017年11月1日12:37:45
    		描述: 声明导出数据
    	*/
    	var importData = null;
    	/* 
    		作者: 郎国峰
    		时间: 2017年10月31日13:59:43
    		描述: 页面加载时调用mapShow()方法,显示统计地图
    	*/
    	$(function(){mapShow('2017')});
   	 	/* 
   	 		作者: 郎国峰
   	 		时间: 2017年10月31日13:43:58
   	 		描述: 用ajax查询患者年度注册信息,然后用echarts做地图统计展示
   	 	*/
   	 	function mapShow(year){
   	 			//声明ajax的请求路径
   	 		    var url = "${pageContext.request.contextPath }/data/patientsite?sjs="+Math.random();
   	    		var data = {"year":year};
   	    		//用ajax查询患者年度注册信息
   	    		$.post(url,data,function(result){
   	    			//将查询到的数据赋值给导出数据
   	    			importData = result;
//====================================================ajax请求成功后的回调函数=======================================================================================  
					var max = result.parm[0].max;
				
					
			        /* 
			        	作者: 郎国峰
			        	时间: 2017年10月31日10:46:12
			        	描述: 指定 患者预约地址统计 的地图的配置
			        */
			        var option = {
			        	    title : { //标题组件
			        	        text: year+'年患者预约地址统计:',    //主标题文本，支持使用 \n 换行
			        	        subtext: '测试数据',        //副标题文本，支持使用 \n 换行
			        	        top:'2%',
			        	        left: '2%',			   //grid 组件离容器左侧的距离
			        	       	backgroundColor:'#E9FF79'  //标题背景颜色,默认透明
			        	    },
			        	    legend: { //图例组件
			        	        orient: 'vertical',
			        	        top: '5%',
			        	        left: '2%',
			        	        data:[year+'年度患者预约统计']
			        	    },
			        	    tooltip: {//提示框组件
			                    trigger: 'item',  //触发类型  item 数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用  axis  坐标轴触发，主要在柱状图，折线图等会使用类目轴的图表中使用
			                    formatter: function(params) {    //tooltip.formatter    提示框浮层内容格式器，支持字符串模板和回调函数两种形式   
			                        var res = params.name+'<br/>';
			                        var myseries = option.series;
			                        for (var i = 0; i < myseries.length; i++) {
			                            for(var j=0;j<myseries[i].data.length;j++){
			                                if(myseries[i].data[j].name==params.name){
			                                    res+=myseries[i].name +' : '+myseries[i].data[j].value+'人</br>';
			                                }
			                            }
			                        }
			                        return res;
			                    }
			                },
			        	    visualMap: {//视觉映射组件
			        	        max: max,
			        	        min: 0,
			        	        left: 'left',
			        	        top: 'center',
			        	        text:['高','低'],           // 文本，默认为数值文本
			        	        calculable : true,
			        	        color: ['orangered','yellow','lightskyblue']
			        	    },
			        	    toolbox: { //工具栏
			        	        show: true, //是否显示工具栏组件 默认true
			        	        orient : 'vertical', //工具栏 icon 的布局朝向  可选 horizontal横向   vertical纵向
			        	        left: '90%',
			        	        top: 'center',
			        	        feature : {  //各工具配置项。
			        	            mark : {show: true},
			        	            dataView : {show: true, readOnly: false},
			        	            restore : {show: true},   //配置项还原
			        	            saveAsImage : {show: true}   //导出图片
			        	        }
			        	    }, 
			        	    series :[ {
			       	            name: year+'年度患者预约统计',
			       	            type: 'map',
			       	            mapType: 'china',
			       	            roam: false,
			       	            label: {
			       	                normal: {
			       	                    show: true
			       	                },
			       	                emphasis: {
			       	                    show: true
			       	                }
			       	            },
			       	            data:result.data
			       	        }]
			        	};
			     	
			    	 	// 使用刚指定的配置项和数据显示图表。
			    	    myChart.setOption(option);
//===========================================================================================================================================    	    			
   	    		},"json");
   	 		}
    
    /* 
    	作者: 郎国峰
    	时间: 2017年11月1日04:34:59
    	描述: 将报表图片的数据添加到传入后台的隐藏域中,作为要导出的excel的参数
    */
   	 function openImage(){
   		var data = myChart.getDataURL("png"); //导出联动的图表图片，返回一个 base64 的 url   导出的格式，可选 png, jpeg
   	    $("#img").val(data); //将data添加到form表单
   	    $("#exportForm").submit(); //调用提交按钮
   	}
   	/* 
 	作者: 郎国峰
 	时间: 2017年11月1日12:34:44
 	描述: 将要导出的数据动态添加到id=dataImportExcel的 form表单里,作为要导出的excel的参数,传入后台
 */		
   	function importDataForExcel(){
 		//jQuery抓取到form表单
   		var form = $("#dataImportExcel");
 		//清空form表单
 		form.empty();
 		//得到存储省名,人数的list
 		var provinceList = importData.data;
 		//遍历importData,将数据添加到form表单中
 		for(var i = 0;i < provinceList.length;i++){
 			//得到每一组省名称,和省患者预约次数
 			var provinceMap = provinceList[i];
 			//得到省名称
 			var provinceName = provinceMap.name;
 			//得到省患者预约次数
 			var countPerson = provinceMap.value;
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