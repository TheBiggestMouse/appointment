<%@page import="com.github.pagehelper.PageInfo"%>
<%@page import="com.yunzhong.appointment.entity.SysRole"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp" %>
<%@page import="com.yunzhong.appointment.config.SessionConst"%>
<%@page import="com.yunzhong.appointment.entity.SysMenu"%>
<%@page import="com.yunzhong.appointment.config.Const"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>疾病医生</title>
	</head>
	<style>
		html body{
			width: 100%;
		}
		.zhuanjiajianjie{
			border: 1px soli #2AABD2;
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
						<!-- 下面的代码需要登录后才能显示 -->
						<shiro:authenticated>
						<%
						 	Subject currentUser = SecurityUtils.getSubject();
							String userName = (String)currentUser.getSession().getAttribute(SessionConst.SESSION_USERNAME);

							SysMenu personalMenu = (SysMenu)currentUser.getSession().getAttribute(SessionConst.SESSION_PERSONAL_MENU);
						%>
							<!-- 跳转到个人中心 )/personalCenter/query -->
							<!-- <li><a href="<%=request.getContextPath() %>/login">登录</a></li> -->
							<li><a href="javascript:showMenu('<%=personalMenu.getMenuId()%>')">个人中心</a></li>
							<li><a href="#"><span class="glyphicon glyphicon-user"></span>欢迎您：<%=userName %></a></li>
						</shiro:authenticated>
						
					</ul>
					<!-- 导航尾 -->
					<shiro:notAuthenticated>
						<div class="nav navbar-right">
							<ul class="nav navbar-nav">
								<li><a href="<%=request.getContextPath() %>/login">登录</a></li>
								<li><a href="<%=request.getContextPath() %>/register">注册</a></li>
							</ul>
						</div>
					</shiro:notAuthenticated>
				</div>
			</nav>
		</div>
		<!--
        	作者：赵刚
        	时间：2017-01-06
        	描述：logo 项目名称  搜索框
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
					      	<input type="text" id="chaxuntiaojian" class="form-control" placeholder="请输入搜索内容">
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
        	描述：导航条   游客可见(在dashboard页) ：按科室挂号  按疾病挂号  最新公告
        -->
		<div class="container" style="margin-top: 10px;">
			<ul class="nav nav-pills guahao" role="tablist" style="background-color:lightcyan">
			  	<li class="active"><a href="#">首页</a></li>
			  	<%
			  		Subject currentUser = SecurityUtils.getSubject();
			  		List menuList = (ArrayList)currentUser.getSession().getAttribute(SessionConst.SESSION_MENULIST);
			  		SysMenu currentMenu = (SysMenu)currentUser.getSession().getAttribute(SessionConst.SESSION_MENU);
			  		if(currentMenu==null){
			  			currentMenu = new SysMenu();
			  		}
			  		for(int i=0;i<menuList.size();i++){
			  			SysMenu topMenu = (SysMenu)menuList.get(i);
			  	%>
					<li class="<%=topMenu.getMenuId().equals(currentMenu.getMenuId())?"active":"" %>">
						<a href="javascript:showMenu('<%=topMenu.getMenuId()%>')">
							<%=topMenu.getMenuName() %>
						</a>
					</li>
				<%
			  		}
				%>
			</ul>
		</div>
		<!-- 当前所在科室 -->
		<div class="container" style="margin-top: 20px;">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h4>
						<label>${illness.illName }：</label><br>
						<span>疾病简介:${illness.illInfo }</span><br>
						<span><a href="<%=request.getContextPath() %>/appointment/listIllness">选择其他疾病</a></span>
					</h4>
				</div>
			</div>
		</div>
		<!--
        	作者：赵刚
        	时间：2017-01-07
        	描述：本科室专家
        -->
        <div class="container zhuanjiajianjie">
        	<table width="100%" class="table table-hover">
        		<c:forEach items="${listPerson }" var="person">
	        		<tr>
	        			<td width="15%">
	        				<img src="img/zhaogang.jpg" width="150px" height="200px;"/>
	        			</td>
	        			<td width="70%">
	        				<h4 class="list-group-item-heading">${person.ppName }	<span style="color: #D58512;">${person.professionalName }</span></h4>
						    <h4 class="list-group-item-text">${person.departmentName }</h4>
						    <h4 class="list-group-item-text">出诊时间：周一，周二，周五</h4>
						    <h4 class="list-group-item-text">医生简介：${person.ppInfo }</h4>
						    <h4 class="list-group-item-text">擅长领域：${person.doctorDomain }</h4>
						    <h4 class="list-group-item-text">
						    	<a href="javascript:yueyue_datatime('1')">更多介绍...</a>
						    </h4>
	        			</td>
	        			<td width="20%" style="vertical-align: middle;">
	        				<button type="button" class="btn btn-primary" onclick="yueyue_datatime('1')">现在预约</button>
	        			</td>
	        		</tr>
        		</c:forEach>
        		
        		
        		
        		
        	</table>
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
			var chaxuntiaojian = $("#chaxuntiaojian").val();
			if (tiaojian.indexOf("科室")>-1) {
				location.href = "<%=request.getContextPath() %>/appointment/queryPerson?dpName="+chaxuntiaojian;
			} else{
				location.href = "<%=request.getContextPath() %>/appointment/queryPersonByIllName?illName="+chaxuntiaojian;
				alert(chaxuntiaojian)
			}
		}
		//更多介绍和现在预约 点击事件
		//id是专家的主键
		function yueyue_datatime(id){
			location.href = "yuyue_datatime.html";
		}
	</script>
</html>
