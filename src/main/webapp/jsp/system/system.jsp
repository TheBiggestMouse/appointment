<%@page import="com.yunzhong.appointment.config.SessionConst"%>
<%@page import="com.yunzhong.appointment.entity.SysMenu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.yunzhong.appointment.config.Const"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<%@include file="../common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>云众科技网上预约挂号系统</title>
	</head>
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
						%>
							<li><a href="huanzhe_personinfo.html">个人中心</a></li>
							<li><a href="#"><span class="glyphicon glyphicon-user"></span>欢迎您：<%=userName %></a></li>
						</shiro:authenticated> 
						
					</ul>
					<!-- 导航尾，登录后不再显示 -->
					<shiro:notAuthenticated>
						<div class="nav navbar-right">
							<ul class="nav navbar-nav">
								<li><a href="<%=request.getContextPath() %>/login">登录</a></li>
								<li><a href="huanzhe_zhuce.html">注册</a></li>
							</ul>
						</div>
					</shiro:notAuthenticated>
				</div>
			</nav>
		</div>
		<!--
        	作者：石洪刚
        	时间：2017年8月10日14:32:38
        	描述：项目名称   搜索框 （搜索框只有游客或患者可见）
        -->
		<div class="container" style="margin-top: 50px;">
			<div class="col-sm-6" >
				<h2 class="text-primary" >云众科技网上预约挂号系统</h2>
			</div>
			<shiro:guest>
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
			</shiro:guest>
			<shiro:hasRole name="patient">
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
			</shiro:hasRole>
		</div>
		<!--
        	作者：石洪刚
        	时间：2017年8月10日17:04:47
        	描述：导航条   游客可见(在dashboard页) ：按科室挂号  按疾病挂号  最新公告
        -->
		<div class="container" style="margin-top: 10px;">
			<ul class="nav nav-pills guahao" role="tablist" style="background-color:lightcyan">
			  	<li ><a href="/">首页</a></li>
			  	<shiro:guest>
				  	<li><a href="#">按科室挂号</a></li>
				  	<li><a href="#">按疾病挂号</a></li>
				  	<li><a href="#">最新公告</a></li>
			  	</shiro:guest>
			  	<shiro:authenticated>
			  		<%
				  		Subject currentUser = SecurityUtils.getSubject();
				  		List menuList = (ArrayList)currentUser.getSession().getAttribute(SessionConst.SESSION_MENULIST);
				  		SysMenu currentMenu = (SysMenu)currentUser.getSession().getAttribute(SessionConst.SESSION_MENU);
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
			  	</shiro:authenticated>
			  	
			</ul>
		</div>
		<!--
        	作者：石洪刚 
        	时间：2017年8月14日21:05:08
        	描述：显示对应菜单信息
        -->
        <div class="container" >
        	<div class="col-sm-3">
        		<div class="panel">
        			<div class="col-sm-12" style="margin-top: 10px;">
        			<ul class="nav nav-tabs nav-stacked info_left">
        				<%
        					Subject currentUser = SecurityUtils.getSubject();
        					SysMenu currentMenu = (SysMenu)currentUser.getSession().getAttribute(SessionConst.SESSION_MENU);
        					List<SysMenu> secondMenuList = currentMenu.getChildrenMenuList();
        					if(secondMenuList!=null){
        						for(int i=0;i<secondMenuList.size();i++){
        							SysMenu secondMenu = secondMenuList.get(i);
        				%>
        				
        				<li><a href="#" data-toggle="collapse" data-target="#menu<%=i%>" ><span class="glyphicon glyphicon-chevron-right"></span><%=secondMenu.getMenuName() %></a><ul class="collapse nav nav-pills nav-stacked nav-inside" id="menu<%=i%>">
        				<%
        							List<SysMenu> thirdMenuList = secondMenu.getChildrenMenuList();
        							if(thirdMenuList!=null){
        								for(int j=0;j<thirdMenuList.size();j++){
        									SysMenu thirdMenu = thirdMenuList.get(j);
        				%>
        				<li><a href="javascript:info_change('<%=thirdMenu.getMenuUrl()%>')"><%=thirdMenu.getMenuName() %></a></li>
        				<%
        								}
        				%>
        				</ul></li>
        				<%
        							}
        						}
        					}
        				%>
        				</ul>
					</div>
        		</div>
        	</div>
        	<div class="col-sm-9" >
        		<div class="panel" >
        			<div class="col-sm-12 col-xs-12 info_left" style="margin-top: 10px;">
        				<iframe name="info_iframe" src="<%=request.getContextPath() %>/sys/listPerson" style="width:100%;height: 500px;border: none;" ></iframe>
        			</div>
        		</div>
        	</div>
        </div>
	</body>
	<script type="text/javascript">
		//加载信息
		function info_change(data){
			$('iframe').attr('src',data);
		}
		
	</script>
</html>