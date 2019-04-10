<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="../common/common.jsp" %>
<!DOCTYPE html>
<html>
	
	<style type="text/css">
		html body{
			width: 100%;
		}
		.kuang{
			border:1px solid gainsboro;
			/*圆角*/
			border-radius: 10px ;
			margin: 0px;
		}
		.info_left{
			border: 1px solid gainsboro;
			/*圆角*/
			border-radius: 10px ;
		}
	</style>
	<body>
		<!--
        	作者：赵刚
        	时间：2017-01-06
        	描述：个人中心	联系我们	登录	注册
        -->
		<div class="container">
			<nav class="nav navbar-default navbar-fixed-top container-fluid" role="navigation" style="background-color:aquamarine;">
				<div class="nav navbar-header">
					<!-- 导航头 -->
					<a class="navbar-brand" href="#">Turing</a>
					<button class="navbar-toggle" data-toggle= "collapse" data-target="#mynav">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>
				<div class="collapse navbar-collapse" id="mynav">
					<!-- 导航内容 -->
					<ul class="nav navbar-nav">
						<li><a href="index.jsp">首页</a></li>
						<li><a href="">个人中心</a></li>
						<li><a href="about.html">联系我们</a></li>
						<!-- 下面的代码需要登录后才能显示 -->
						<li><a href="#"><span class="glyphicon glyphicon-user"></span>欢迎您：${patient.patientName } </a></li>
					</ul>
					<!-- 导航尾 -->
					<div class="nav navbar-right">
						<ul class="nav navbar-nav">
						
							<li><a href="#">退出</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
		<!--
        	作者：赵刚
        	时间：2017-01-06
        	描述：logo 项目名称  搜索框
        -->
		<div class="container" style="margin-top: 50px;">
			<div class="col-sm-6" style="margin-left: -15px;">
				<h2 class="text-primary" >云众科技网上预约挂号系统</h2>
			</div>
			<div class="col-sm-6" style="margin-top: 15px;">
				<form method="post" name="form1" class="form form-horizontal">
					<div class="input-group">
				      	<div class="input-group-btn">
					        <button id="index_search_btn" type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown">科室 <span class="caret"></span></button>
					        <ul class="dropdown-menu" role="menu">
					          <li><a href="javascript:search_yuyue('科室')">科室</a></li>
					          <li><a href="javascript:search_yuyue('疾病')">疾病</a></li>
					        </ul>
				      	</div><!-- /btn-group -->
				      	<input type="text" class="form-control" placeholder="请输入搜索内容">
				      	<div class="input-group-btn">
        					<button type="button" class="btn btn-warning" onclick="tiaojian_search()"><span class="glyphicon glyphicon-search"></span></button>
				    	</div>
				      	</div><!-- /input-group -->
			    </form>
			</div>
		</div>
		<!--
        	作者：赵刚
        	时间：2017-01-06
        	描述：导航条
        -->
		<div class="container" style="margin-top: 10px;">
			<ul class="nav nav-pills guahao" role="tablist" style="background-color:lightcyan">
			 	<li class="active"><a href="../dashboard.jsp">首页</a></li>
			  	<li><a href="../3.appointment/guahao_department.html">按科室挂号</a></li>
			  	<li><a href="#">按疾病挂号</a></li>
			  	<li><a href="../5.notice/notice.html">最新公告</a></li>
			</ul>
		</div>
		<!--
        	作者：赵刚
        	时间：2017-01-09
        	描述：个人中心
        -->
        <div class="container" >
        	<div class="col-sm-3">
        		<div class="panel">
        			<div class="col-sm-12" style="margin-top: 10px;">
						<ul class="nav nav-tabs nav-stacked info_left">
							<li ><a href="javascript:info_qiehuan('editPage')">个人信息</a></li>
							<li ><a href="javascript:info_qiehuan('patient_order.html')">订单详情</a></li>
							<li ><a href="javascript:info_qiehuan('patient_order_weifukuan.html')">未付款订单</a></li>
							<li ><a href="javascript:info_qiehuan('patient_updatepassword.html')">修改密码</a></li>
							<li ><a href="javascript:info_qiehuan('avatarquery')">修改头像</a></li>
						</ul>
					</div>
        		</div>
        	</div>
        	<div class="col-sm-9" >
        		<div class="panel" >
        			<div class="col-sm-12 col-xs-12 info_left" style="margin-top: 10px;">
        				<iframe name="info_iframe" id="info"  style="width:100%;height: 500px;border: none;" ></iframe>
        			</div>
        		</div>
        	</div>
        </div>
		<!--
        	作者：赵刚
        	时间：2017-01-06
        	描述：logo 联系方式		微信		官网		
        -->
		<div class="container-fluid" style="background-color: #595959;">
			<div class="col-sm-4">
				<img src="img/logo_wei.jpg"/>
			</div>
			<div class="col-sm-4">
				<h6 class="text-center">作者：赵刚</h6>
				<h6 class="text-center">版权所有：哈尔滨图灵云教育 地址：哈尔滨市南岗区西大直街318号6、7楼</h6>
				<h6 class="text-center">技术支持：图灵云教育 技术支持团队</h6>
			</div>
			<div class="col-sm-4">
				<div class="col-sm-6">
					<img src="img/turing_weixin.jpg" style="width: 100px;"/>
				</div>
				<div class="col-sm-6">
					<img src="img/turing_location.png" style="width: 100px;"/>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		//进入这个页面最先运行的方法
	/* 	$(function (){
			//写请求查询个人信息的路径
			
			}
			
		})  */
		
		//搜索框
		function search_yuyue(tiaojian){
			$("#index_search_btn").html(tiaojian+" <span class=\"caret\"></span>");
		}
		//挂号导航
		$(".guahao a").bind("click",function(){
			if ("首页"==$(this).html()) {
				location.href = "index.html";
			} else if("按科室挂号"==$(this).html()){
				location.href = "guahao_keshi.html";
			}else if("按疾病挂号"==$(this).html()){
				location.href = "guahao_jibing.html";
			}else if("最新公告"==$(this).html()){
				location.href = "guahao_gonggao.html";
			}
		});
		//搜索框条件查询
		function tiaojian_search(){
			var tiaojian = $("#index_search_btn").html();
			if (tiaojian.indexOf("科室")>-1) {
				location.href = "yuyue_keshi.html";
			} else{
				location.href = "yuyue_jibing.html";
			}
		}
		//个人信息
		function info_qiehuan(data){
			$("#info").attr("src",data);
		}
		//判断是否是预约页面跳转到本页面，如果是的话，则默认显示未付款订单
		$(function(){
			var order = 1;
			$('iframe').attr('src','patient_message.html');
		});
	</script>
</html>
