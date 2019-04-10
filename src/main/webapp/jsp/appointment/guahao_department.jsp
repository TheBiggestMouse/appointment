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
		<title>按科室挂号</title>
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
		<!--
        	作者：赵刚
        	时间：2017-01-06
        	描述：热门科室
        -->
        <div class="container">
        	<div class="text-left">
        		<h4 class="btn btn-warning">热门科室</h4>
        	</div>
        	<div class="piaoyikuang" style="padding: 5px;height: 100px;">
        	<c:forEach items="${listWelcome }" var="wd">
        
        		<div class="piaoyi"><a href="#">${wd.departmentName }</a></div> 
        	</c:forEach>	
        	</div>
        </div>
        <!-- 科室名称 及其 科室详细 -->
        <div class="container" style="margin-top: 10px;">
        	<!--
            	作者：赵刚
            	时间：2017-01-07
            	描述：列导航，科室名称
            -->
        	<div class="col-sm-3">
        		<div class="panel panel-default ks-left">
	            	<div class="panel-body">
	            		<ul class="nav nav-pills nav-stacked left_ks" role="tablist">
						 <!--  <li class="active"><a href="#neike">内科<span class="glyphicon glyphicon-chevron-right pull-right"></span></a></li> -->
						 <c:forEach items="${listPDept }" var="listPDept">
							  <li><a href="#${listPDept.dplId }">${listPDept.dplName }<span class="glyphicon glyphicon-chevron-right pull-right"></span></a></li>
						 </c:forEach>
						</ul>
	            	</div>
	            </div>
        	</div>
        	<!--
            	作者：赵刚
            	时间：2017-01-07
            	描述：科室细分
            -->
        	<div class="col-sm-9">
        		<div class="panel panel-default ks-right">
        			<div class="panel-body">
        			
        				<c:forEach items="${listPDept }" var="listPDept">
	        				<!-- panel start -->
	        				<div class="panel panel-default">
	        					<div class="panel-heading">
	        						<h6 id="${listPDept.dplId }">${listPDept.dplName }</h6>
	        					</div>
	        					<div class="panel-body">
	        						<div>
	        							<c:forEach items="${listPDept.dpList }" var="dpList">
						        			<div class="piaoyi"><a href="#" >${dpList.dpName }</a></div>
						        		</c:forEach>
						        	</div>
	        					</div>
	        				</div>
	        				<!-- panel end -->
        				</c:forEach>
        			</div>
        		</div>
        	</div>
        </div>
      
	</body>
	<script type="text/javascript">
	
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
		//点击左侧科室，安装锚点找到对应的明细
		$(".left_ks a").bind("click",function(){
			$(".left_ks li").removeClass("active");
			$(this).parent().addClass("active");
		});
		//点击科室开始预约
		$(".piaoyi a").bind("click",function(){
			var dpName = $(this).html();
			//想办法传中文
			location.href ="<%=request.getContextPath() %>/appointment/queryPerson?dpName="+dpName;
		});
		//搜索框
		function search_yuyue(tiaojian){
			$("#index_search_btn").html(tiaojian+" <span class=\"caret\"></span>");
		}
		
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
	</script>
</html>
