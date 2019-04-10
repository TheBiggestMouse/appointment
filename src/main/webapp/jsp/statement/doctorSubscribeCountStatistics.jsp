<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<!-- 
	作者: 郎国峰
	时间: 2017年11月2日15:53:26
	描述: 引入echarts文件 
-->
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/ECharts/echarts.js"></script>
<!-- 
	作者: 郎国峰
	时间: 2017年11月2日15:53:32
	描述: 引入地图文件 
-->
<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/ECharts/china.js"></script>
</head>
<body>
	<!-- 
		作者: 郎国峰
		时间: 2017年11月1日12:02:17
		描述: 将数据传入后台,然后在导出Excel
	 -->
	<form id="dataImportExcel" action="<%=request.getContextPath() %>/data/doctimesDataImportExcel" method="post">
		<input type="hidden" name="doctorName"  />
		<input type="hidden" name="doctorCount"  />
	</form>
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
	<div id="main" style="width: 100%;  height:100%;" ></div>
	<!-- 
    	作者: 郎国峰
    	时间: 2017年11月2日16:59:47
    	描述: 点击调用导出图片到excel中的js方法
     -->
    <div style="margin-top:20px;">
		<input type="button" style="padding-bottom;'5%';" value="导出数据到Excel中" onclick="importDataForExcel()" />
	</div>
</body>

<script type="text/javascript">
	/* 
	作者: 郎国峰
	时间: 2017年11月2日15:56:23
	描述: 基于准备好的dom，初始化echarts实例
	*/
	var myChart = echarts.init(document.getElementById('main'));
	/* 
	作者: 郎国峰
	时间: 2017年11月2日15:57:14
	描述: 声明导出数据
	*/
	var importData = null;
	/* 
	作者: 郎国峰
	时间: 2017年11月2日15:57:26
	描述: 页面加载时调用mapShow()方法,显示统计地图
	*/
	$(function(){mapShow('2017')});
	/* 
		作者: 郎国峰
		时间: 2017年11月2日15:57:37
		描述: 用ajax查询医生年度预约统计信息,然后用echarts做柱状图展示
	*/
	function mapShow(year){
			//声明ajax的请求路径
		    var url = "${pageContext.request.contextPath }/data/doctimesData?sjs="+Math.random();
			var data = {"year":year};
			//用ajax查询医生年度预约统计信息
			$.post(url,data,function(result){
				console.log(result)
				//将查询到的数据赋值给导出数据
				importData = result;
	//====================================================ajax请求成功后的回调函数=======================================================================================  
	        /* 
	        	作者: 郎国峰
	        	时间: 2017年11月2日15:59:09
	        	描述: 指定 医生年度预约数量信息的统计图配置
	        */
	        /* 
	        var dataAxis = ['点', '击', '柱', '子', '或', '者', '两', '指', '在', '触', '屏', '上', '滑', '动', '能', '够', '自', '动', '缩', '放'];
	        var Countdata = [220, 182, 191, 234, 290, 330, 310, 123, 442, 321, 90, 149, 210, 122, 133, 334, 198, 123, 125, 220];
	         */
	        var dataAxis = result.listName;
	        var Countdata = result.listcount;
	        var yMax = 200;
	        var dataShadow = [];
	        for (var i = 0; i < Countdata.length; i++) {
	            dataShadow.push(yMax);
	        }
	        option = {
	        	    title: {
	        	    	 text: year+'医生年度预约次数统计图:',    //主标题文本，支持使用 \n 换行
		        	     subtext: '测试数据',        //副标题文本，支持使用 \n 换行
	        	    },
	        	    tooltip: {//提示框组件
	                    trigger: 'item',  //触发类型  item 数据项图形触发，主要在散点图，饼图等无类目轴的图表中使用  axis  坐标轴触发，主要在柱状图，折线图等会使用类目轴的图表中使用
	                    formatter: function(params) {    //tooltip.formatter    提示框浮层内容格式器，支持字符串模板和回调函数两种形式   
	                        var res = params.name+':'+year+' 年被预约 '+params.value+'次<br/>';
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
	        	    xAxis: {
	        	        data: dataAxis,
	        	        axisLabel: {
	        	            inside: true,
	        	            textStyle: {
	        	                color: '#fff'
	        	            }
	        	        },
	        	        axisTick: {
	        	            show: false
	        	        },
	        	        axisLine: {
	        	            show: false
	        	        },
	        	        z: 10
	        	    },
	        	    yAxis: {
	        	        axisLine: {
	        	            show: false
	        	        },
	        	        axisTick: {
	        	            show: false
	        	        },
	        	        axisLabel: {
	        	            textStyle: {
	        	                color: '#999'
	        	            }
	        	        }
	        	    },
	        	    dataZoom: [
	        	        {
	        	            type: 'inside'
	        	        }
	        	    ],
	        	    series: [
	        	        { // For shadow
	        	            type: 'bar',
	        	            itemStyle: {
	        	                normal: {color: 'rgba(0,0,0,0.05)'}
	        	            },
	        	            barGap:'-100%',
	        	            barCategoryGap:'40%',
	        	            data: dataShadow,
	        	            animation: false
	        	        },
	        	        {
	        	            type: 'bar',
	        	            itemStyle: {
	        	                normal: {
	        	                    color: new echarts.graphic.LinearGradient(
	        	                        0, 0, 0, 1,
	        	                        [
	        	                            {offset: 0, color: '#83bff6'},
	        	                            {offset: 0.5, color: '#188df0'},
	        	                            {offset: 1, color: '#188df0'}
	        	                        ]
	        	                    )
	        	                },
	        	                emphasis: {
	        	                    color: new echarts.graphic.LinearGradient(
	        	                        0, 0, 0, 1,
	        	                        [
	        	                            {offset: 0, color: '#2378f7'},
	        	                            {offset: 0.7, color: '#2378f7'},
	        	                            {offset: 1, color: '#83bff6'}
	        	                        ]
	        	                    )
	        	                }
	        	            },
	        	            data: Countdata
	        	        }
	        	    ]
	        	};
	     // 当用户单击bar时启用数据缩放。
	        var zoomSize = 6;
	        myChart.on('click', function (params) {
	            console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
	            myChart.dispatchAction({
	                type: 'dataZoom',
	                startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
	                endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
	            });
	        });
	 	// 使用刚指定的配置项和数据显示图表。
	    myChart.setOption(option);
	//===========================================================================================================================================    	    			
			},"json");
		}
	/* 
	作者: 郎国峰
	时间: 2017年11月1日12:34:44
	描述: 将要导出的数据动态添加到id=dataImportExcel的 form表单里,作为要导出的excel的参数,传入后台
	*/		
	function importDataForExcel(){
		alert("导出按钮");
		//jQuery抓取到form表单
		var form = $("#dataImportExcel");
		//清空form表单
		form.empty();
		//得到存储医生名,被预约次数的list
		var listName = importData.listName;
		var listcount = importData.listcount;
		console.log(listName);
		console.log(listcount);
		//遍历importData,将数据添加到form表单中
		for(var i = 0;i < listName.length;i++){
			//得到医生的名字
			var doctorName = listName[i];
			//得到预约次数
			var doctorCount = listcount[i];
			//声明要添加的form表单里的表单控件
			var inputStr = "<input type='hidden'  name='doctorName' value='"+doctorName+"' /><input  type='hidden'  name='doctorCount' value='"+doctorCount+"'  />";
			form.append(inputStr);
		}
		//调用提交按钮
		alert("准备提交");
		form.submit(); 
	} 		
</script>

</html>