<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.github.pagehelper.PageInfo"%>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<title>查询排班信息</title>
	</head>
	<body style="background:linear-gradient(white,#ebebeb,white);">
		<!-- 
			作者: 郎国峰
			时间: 2017年11月8日08:47:43
			描述: 医生排班信息条件查询
		 -->
		<div class="container">
			<hr />
			<form class="form-horizontal text-center" action="<%=request.getContextPath()%>/pp/docsche.action" method="post">
				<div class="container ">
					<div class="form-group">
						<label class="control-label col-md-2 col-sm-2">医生姓名</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="ppName" value="${pd.ppName}" placeholder="医生姓名"/>
						</div>
						<label class="control-label col-sm-2 col-md-2">所属科室:</label>
						<div class="col-md-4 col-sm-4">
							<select class="form-control" name="deptId" >
								<option value="">===请选择科室===</option>
								<option value="">神经内科</option>
							</select>
						</div>
						<label class="control-label col-sm-2 col-md-2">排班日期</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="ckNo" value="<fmt:formatDate value='${pd.schedulingDate}' pattern='yyyy-MM-dd' />" placeholder="请选择日期"/>
						</div>
					</div>
					<div class="form-group">
						<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12" >
							<div class="col-md-4 col-sm-4">
								<input class="form-control btn-primary" type="submit" value="查询" />
							</div>
							<div class="col-md-4 col-sm-4">
								<input class="form-control btn-primary" type="button" onclick="page('')" value="增加" />
							</div>
							<div class="col-md-4 col-sm-4">
								<input class="form-control btn-primary" type="button" onclick="autoDocsche()"  value="查看自动排班表" />
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<!-- 
			作者: 郎国峰
			时间: 2017年11月8日08:48:33
			描述: 医生排班信息展示
		 -->
		<form>
		<div class="container ">
			<table class="table table-hover table-striped  text-center" >
				<thead >
					<tr class="bg-primary">
						<td>序号</td>
						<td>医生姓名</td>
						<td>医生科室</td>
						<td>日期</td>
						<td>班次A</td>
						<td>班次B</td>
						<td>班次C</td>
						<td>班次D</td>
						<td>班次E</td>
						<td>班次F</td>
						<td>操作</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${page.list }" var="t" varStatus="i">
						<tr>
							<td>${i.count }</td>
							<td>${t.ppName }</td>
							<td>${t.departmentName }</td>
							<td><fmt:formatDate value="${t.schedulingDate }" pattern="yyyy-MM-dd" /></td>
							<td><input size="8" value="${t.time1 }" readonly="readonly"/><br/><input size="8" value="出诊${t.count1}次" readonly="readonly"/></td>
							<td><input size="8" value="${t.time2 }" readonly="readonly"/><br/><input size="8" value="出诊${t.count2}次" readonly="readonly"/></td>
							<td><input size="8" value="${t.time3 }" readonly="readonly"/><br/><input size="8" value="出诊${t.count3}次" readonly="readonly"/></td>
							<td><input size="8" value="${t.time4 }" readonly="readonly"/><br/><input size="8" value="出诊${t.count4}次" readonly="readonly"/></td>
							<td><input size="8" value="${t.time5 }" readonly="readonly"/><br/><input size="8" value="出诊${t.count5}次" readonly="readonly"/></td>
							<td><input size="8" value="${t.time6 }" readonly="readonly"/><br/><input size="8" value="出诊${t.count6}次" readonly="readonly"/></td>
							<td>
								<a href="javascript:page('${t.schedulingId }')">修&nbsp;改</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>	
		</div>
		</form>
		<!-- 
			作者: 郎国峰
			时间: 2017年11月8日08:49:39
			描述: 查询结果分页
		 -->
		<div align="center">
			<page:page pageName="pageInfo" uri="/cangKu/query.action"></page:page>
		</div>
	</body>
	<script type="text/javascript">
		/* 
			作者: 郎国峰
			时间: 2017年11月9日22:42:53
			描述: 跳转到修改页面       
		*/
		function page(id){
			
			window.location.href="<%=request.getContextPath()%>/pp/docschePage.action?id="+id;	
				
			//抓取到父窗口的iframe标签,修改iframe的src属性,实现重新发送请求,跳转到修改页
			<%-- $('iframe', window.parent.document).attr('src',"<%=request.getContextPath()%>/pp/docschePage.action?id="+id); --%>
		}
		/*
			作者: 郎国峰
			时间: 2017年11月9日23:57:56
			描述: 跳转到自动排班查询
		*/
		function autoDocsche(){
			window.location.href="<%=request.getContextPath()%>/pp/autoDocsche.action";	
		}
	</script>
</html>
