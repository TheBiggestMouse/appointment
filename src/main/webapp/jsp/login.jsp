<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@include file="common/common.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>云众科技网上预约挂号系统</title>
	</head>
	<style>
		html body{
			width: 100%;
		}
		.piaoyikuang{
			border: 1px solid #66AFE9;
			border-radius: 10px;
		}
		.piaoyi{
			float: left;
			width: 100px;
			height: 30px;
			border: 0px solid #66AFE9;
			text-align: center;
		}
		.ks-left{
			border: 1px solid #66AFE9;
			border-radius: 10px;
			margin-right: 0px;
		}
		.ks-right{
			border: 0px solid #66AFE9;
			border-radius: 10px;
		}
	</style>
	<body>
		<!--
        	作者：石洪刚
        	时间：2017年8月10日14:12:50
        	描述：个人中心	联系我们	登录	注册
        -->
		<div class="container">
			<nav class="nav navbar-default navbar-fixed-top container-fluid" role="navigation" style="background-color:aquamarine;">
				<div class="nav navbar-header">
					<!-- 导航头 -->
					<a class="navbar-brand" href="#">云众科技</a>
					<button class="navbar-toggle" data-toggle= "collapse" data-target="#mynav">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>
				<div class="collapse navbar-collapse" id="mynav">
					<!-- 导航内容 -->
					<ul class="nav navbar-nav">
						<li><a href="">联系电话：0451-87362836</a></li>
					</ul>
					<!-- 导航尾，登录后不再显示 -->
					<div class="nav navbar-right">
						<ul class="nav navbar-nav">
							<li><a href="<%=request.getContextPath() %>/login">登录</a></li>
							<li><a href="huanzhe_zhuce.html">注册</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
		<!--
        	作者：石洪刚
        	时间：2017年8月10日14:32:38
        	描述：项目名称  搜索框
        -->
		<div class="container" style="margin-top: 50px;">
			<div class="col-sm-6" >
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
        	作者：石洪刚
        	时间：2017年8月10日17:04:47
        	描述：导航条
        -->
		<div class="container" style="margin-top: 10px;">
			<ul class="nav nav-pills guahao" role="tablist" style="background-color:lightcyan">
			  	<li class="active"><a href="#">首页</a></li>
			  	<li><a href="#">按科室挂号</a></li>
			  	<li><a href="#">按疾病挂号</a></li>
			  	<li><a href="#">最新公告</a></li>
			</ul>
		</div>
		<!--
        	作者：石洪刚 
        	时间：2017年8月10日18:14:02
        	描述：登录 和 验证登录
        -->
        <div class="container" style="margin-top: 10px;">
        	
        	<ul class="nav nav-tabs" role="tablist" id="myTab">
			  <li class="active"><a href="#huanze_login" role="tab" data-toggle="tab">登录</a></li>
			</ul>
			
			<div class="tab-content">
			  	<div class="tab-pane active" id="huanze_login">
			  		<form class="form form-horizontal" name="loginForm1" action="${ctx }/success" method="post" style="margin-top: 10px;">
			  			<input style="display: none;"/>
        				<input type="hidden" style="display: none;"/>
			  			<div class="form-group">
							<label class="control-label col-sm-3 col-sm-offset-1">手机号码：</label>
							<div class="col-sm-4">
								<input class="form-control" name="userName" type="text" placeholder="请您输入有效手机号码"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3 col-sm-offset-1">密码：</label>
							<div class="col-sm-4">
								<input class="form-control" name="userPass" type="password" />
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-3 col-sm-offset-1"></label>
							<div class="col-sm-2">
								<button  class="btn btn-success btn-lg" type="submit" >登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</button>
							</div>
							<div class="col-sm-2">
								<button class="btn btn-primary btn-lg" type="button" onclick="huanzhe_zhuce()">注册新用户</button>
							</div>
						</div>
						
						<div class="form-group">
							<label class="control-label col-sm-3 col-sm-offset-1"></label>
							<div class="col-sm-4 text-right">
								<a href="#" >忘记密码</a>
							</div>
						</div>
						
						<h4 class="text-center text-danger" >
				            <c:if test="${!empty message}">${message}</c:if>
				        </h4>
			  		</form>
			  	</div>
			  	
			</div>
        </div>
	</body>
	<script type="text/javascript">
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
		//患者登录
		function huanzhe_login(){
			location.href = "index.html";
		}
		//注册新用户
		function huanzhe_zhuce(){
			location.href = "huanzhe_zhuce.html";
		}
	</script>
</html>
