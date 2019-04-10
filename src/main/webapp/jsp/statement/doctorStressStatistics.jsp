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
	<div id="main" style="width: 100%;  height:100%;" ></div>
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
		    var url = "${pageContext.request.contextPath }/data/docpreData?sjs="+Math.random();
			var data = {"year":year};
			//用ajax查询医生年度预约统计信息
			$.post(url,data,function(result){
				console.log(result);
				//将查询到的数据赋值给导出数据
				importData = result;
	//====================================================ajax请求成功后的回调函数=======================================================================================  
			option = {
			    backgroundColor: '#2c343c',
			    title: {
			        text: 'Customized Pie',
			        left: 'center',
			        top: 20,
			        textStyle: {
			            color: '#ccc'
			        }
			    },
			
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			
			    visualMap: {
			        show: false,
			        min: 80,
			        max: 600,
			        inRange: {
			            colorLightness: [0, 1]
			        }
			    },
			    series : [
			        {
			            name:'访问来源',
			            type:'pie',
			            radius : '55%',
			            center: ['50%', '50%'],
			            data:result.sort(function (a, b) { return a.value - b.value; }),
			            roseType: 'radius',
			            label: {
			                normal: {
			                    textStyle: {
			                        color: 'rgba(255, 255, 255, 0.3)'
			                    }
			                }
			            },
			            labelLine: {
			                normal: {
			                    lineStyle: {
			                        color: 'rgba(255, 255, 255, 0.3)'
			                    },
			                    smooth: 0.2,
			                    length: 10,
			                    length2: 20
			                }
			            },
			            itemStyle: {
			                normal: {
			                    color: '#c23531',
			                    shadowBlur: 200,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            },
			
			            animationType: 'scale',
			            animationEasing: 'elasticOut',
			            animationDelay: function (idx) {
			                return Math.random() * 200;
			            }
			        }
			    ]
			};
			// 使用刚指定的配置项和数据显示图表。
		    myChart.setOption(option);
	//===========================================================================================================================================    	    			
			},"json");
		}

</script>

</html>