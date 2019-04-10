<%@page import="com.yunzhong.appointment.config.SessionConst"%>
<%@page import="com.yunzhong.appointment.entity.SysMenu"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.yunzhong.appointment.config.Const"%>
<%@page import="org.apache.shiro.subject.Subject"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="common/common.jsp" %>
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
					<!-- 导航尾，登录后不再显示 -->
					<shiro:notAuthenticated>
						<div class="nav navbar-right">
							<ul class="nav navbar-nav">
								<li><a href="<%=request.getContextPath() %>/login">登录</a></li>
								<li><a href="<%=request.getContextPath() %>/register">注册</a></li>
							</ul>
						</div>
					</shiro:notAuthenticated>
					<shiro:authenticated>
					<div class="nav navbar-right">
							<ul class="nav navbar-nav">
								<li><a href="<%=request.getContextPath() %>/logout">退出登录</a></li>
							</ul>
						</div>
					</shiro:authenticated>
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
					    </div>
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
		<!-- 图片广告宣传区 -->
		<div class="container" style="height: 100%;">
			<div style="height: 100%;">
				<img src="<%=request.getContextPath() %>/static/img/22.png" style="width: 100%;height:10%;"/>
			</div>
		</div>
		<!-- 简介区 -->
		<div class="container">
			<div class="kuang col-sm-6">
				<h3 class="text-primary" >XXX第一人民医院</h3>
				<p class="">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					我院是全球最好的医院，外星人看病 都来我院，我院的大脑移植成功率高达90%，粉碎性骨折能完美复原，国家DNA复制人指定医院，
					我院有中科院院士5800人，博士后25万多人，世界抗癌克星“一针瘤走”是我院独家秘方，我院还是全世界最好的美容医院，利用
					质子重排，想变什么样就变什么样，长出8条腿都没问题。
				</p>
				<div >
					
				</div>
				<div class="">
					<h5>我院地址<span class="glyphicon glyphicon-hand-down"></span></h5>
					<div >
						<img src="<%=request.getContextPath() %>/static/img/3.png" style="width: 100%;height: 150px;"/>
					</div>
				</div>
			</div>
			<div class="kuang col-sm-6">
				<h3 class="text-primary" >我院大事记</h3>
				<ul>
					<li>1980年：克隆人复制成功</li>
					<li>1981年：第一例大脑移植成功</li>
					<li>1982年：DNA测序完成</li>
					<li>1982年：抗癌药物研制成功，实现人类没有癌症的梦想！</li>
					<li>1983年：实现大脑记忆共享，让人类的学习进入新纪元。</li>
					<li>1984年：第一代质子重排机诞生。</li>
					<li>1985年：曲率压缩治疗结石获得突破性进展，从此，结石治疗成为常规治疗。</li>
					<li>1986年：可控辐射纳米球诞生，人类再也没有肥胖者。</li>
					<li>1987年：人造心脏实现无排斥移植，先天性心脏病得到有效治疗。</li>
					<li>1988年：大脑神经元培养系统问世，全人类都是聪明人，智商可提高到300</li>
					<li>1989年：骨头再生药物问世，号称残疾人终结者。</li>
					<li>1989年：机械与骨合成药剂问世，可使用金属替代人骨，超级战士诞生。</li>
				</ul>
				<div class="text-right">
					<a href="#">查看更多>></a>
				</div>
			</div>
		</div>
		<!-- 广告区 -->
		<div class="container">
			<div class="col-sm-4 kuang" style="height: 200px;">
				<div class="text-center" style="margin-top: 100px;">
					广告区
				</div>
			</div>
			<div class="col-sm-4 kuang" style="height: 200px;">
				<div class="text-center" style="margin-top: 100px;">
					广告区
				</div>
			</div>
			<div class="col-sm-4 kuang" style="height: 200px;">
				<div class="text-center" style="margin-top: 100px;">
					广告区
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		//搜索框
		function search_yuyue(tiaojian){
			$("#index_search_btn").html(tiaojian+" <span class=\"caret\"></span>");
		}
		
		//搜索框条件查询
		function tiaojian_search(){
			var tiaojian = $("#index_search_btn").html();
			alert(tiaojian)
			if (tiaojian.indexOf("科室")>-1) {
				location.href = "yuyue_keshi.html?content=阑尾炎";
			} else{
				location.href = "yuyue_jibing.html";
			}
		}
	</script>
</html>