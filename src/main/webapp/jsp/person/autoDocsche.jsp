<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
			<hr />
			<form class="form-horizontal text-center" action="<%=request.getContextPath()%>/pp/docsche.action" method="post">
				<div class="container ">
					<div class="form-group">
						<label class="control-label col-md-2 col-sm-2">医生姓名</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="ckNo" value="${page.ckNo}" placeholder="医生姓名"/>
						</div>
						<label class="control-label col-sm-2 col-md-2">所属科室:</label>
						<div class="col-md-4 col-sm-4">
							<select class="form-control" name="deptId" >
								<option value="">===请选择科室===</option>
								<option value="">神经内科</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12" >
							<div class="col-md-4 col-sm-4">
								<input class="form-control btn-primary" type="submit" value="查询" />
							</div>
							<div class="col-md-4 col-sm-4">
								<input class="form-control btn-primary" type="button" onclick="page('','')" value="增加" />
							</div>
							<div class="col-md-4 col-sm-4">
								<input class="form-control btn-primary" type="button" onclick="autoDocsche()"  value="查看自动排班表" />
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	<form>
		<div class="container ">
			<table class="table table-hover table-striped  text-center" >
				<thead >
					<tr class="bg-primary">
						<td>序号</td>
						<td>选择</td>
						<td>医生姓名</td>
						<td>医生科室</td>
						<td>星期一</td>
						<td>星期二</td>
						<td>星期三</td>
						<td>星期四</td>
						<td>星期五</td>
						<td>星期六</td>
						<td>星期日</td>
					</tr>
				</thead>
				
				<tbody>
					<c:forEach items="${listAutoDocsche}" var="t" varStatus="i">
						<tr>
							<td>${i.count }</td>
							<td><input type="checkbox" /></td>
							<td>${t.ppName[0] }</td>
							<td>${t.departmentName[0] }</td>
							<td>
								<span title="08:30-09:30 出诊${t.count1[0] }次">A</span>
								<span title="09:30-10:30 出诊${t.count1[1] }次">B</span>
								<span title="10:30-11:30 出诊${t.count1[2] }次">C</span>
								<span title="13:30-14:30 出诊${t.count1[3] }次">D</span>
								<span title="14:30-15:30 出诊${t.count1[4] }次">E</span>
								<span title="15:30-16:30 出诊${t.count1[5] }次">F</span>
								<a href="javaScript:page('${t.id[0]}','visit_moncount')"><input type="button" value="修改"></a>
							</td>
							
							<td>
								<span title="08:30-09:30 出诊${t.count2[0] }次">A</span>
								<span title="09:30-10:30 出诊${t.count2[1] }次">B</span>
								<span title="10:30-11:30 出诊${t.count2[2] }次">C</span>
								<span title="13:30-14:30 出诊${t.count2[3] }次">D</span>
								<span title="14:30-15:30 出诊${t.count2[4] }次">E</span>
								<span title="15:30-16:30 出诊${t.count2[5] }次">F</span>
								<a href="javaScript:page('${t.id[0]}','visit_tuescount')"><input type="button" value="修改"></a>
							</td>
							
							<td>
								<span title="08:30-09:30 出诊${t.count3[0] }次">A</span>
								<span title="09:30-10:30 出诊${t.count3[1] }次">B</span>
								<span title="10:30-11:30 出诊${t.count3[2] }次">C</span>
								<span title="13:30-14:30 出诊${t.count3[3] }次">D</span>
								<span title="14:30-15:30 出诊${t.count3[4] }次">E</span>
								<span title="15:30-16:30 出诊${t.count3[5] }次">F</span>
								<a href="javaScript:page('${t.id[0]}','visit_wedcount')"><input type="button" value="修改"></a>
							</td>
							
							<td>
								<span title="08:30-09:30 出诊${t.count4[0] }次">A</span>
								<span title="09:30-10:30 出诊${t.count4[1] }次">B</span>
								<span title="10:30-11:30 出诊${t.count4[2] }次">C</span>
								<span title="13:30-14:30 出诊${t.count4[3] }次">D</span>
								<span title="14:30-15:30 出诊${t.count4[4] }次">E</span>
								<span title="15:30-16:30 出诊${t.count4[5] }次">F</span>
								<a href="javaScript:page('${t.id[0]}','visit_thurcount')"><input type="button" value="修改"></a>
							</td>
							
							<td>
								<span title="08:30-09:30 出诊${t.count5[0] }次">A</span>
								<span title="09:30-10:30 出诊${t.count5[1] }次">B</span>
								<span title="10:30-11:30 出诊${t.count5[2] }次">C</span>
								<span title="13:30-14:30 出诊${t.count5[3] }次">D</span>
								<span title="14:30-15:30 出诊${t.count5[4] }次">E</span>
								<span title="15:30-16:30 出诊${t.count5[5] }次">F</span>
								<a href="javaScript:page('${t.id[0]}','visit_fricount')"><input type="button" value="修改"></a>
							</td>
							
							<td>
								<span title="08:30-09:30 出诊${t.count6[0] }次">A</span>
								<span title="09:30-10:30 出诊${t.count6[1] }次">B</span>
								<span title="10:30-11:30 出诊${t.count6[2] }次">C</span>
								<span title="13:30-14:30 出诊${t.count6[3] }次">D</span>
								<span title="14:30-15:30 出诊${t.count6[4] }次">E</span>
								<span title="15:30-16:30 出诊${t.count6[5] }次">F</span>
								<a href="javaScript:page('${t.id[0]}','visit_satcount')"><input type="button" value="修改"></a>
							</td>
							
							<td>
								<span title="08:30-09:30 出诊${t.count7[0] }次">A</span>
								<span title="09:30-10:30 出诊${t.count7[1] }次">B</span>
								<span title="10:30-11:30 出诊${t.count7[2] }次">C</span>
								<span title="13:30-14:30 出诊${t.count7[3] }次">D</span>
								<span title="14:30-15:30 出诊${t.count7[4] }次">E</span>
								<span title="15:30-16:30 出诊${t.count7[5] }次">F</span>
								<a href="javaScript:page('${t.id[0]}','visit_suncount')"><input type="button" value="修改"></a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>	
		</div>
	</form>
</body>
<script type="text/javascript">
	/* 
		作者: 郎国峰
		时间: 2017年11月10日00:46:29
		描述: 跳转到添加修改页
	*/
	function page(id,date){
		windows.location.href="<%=request.getContextPath()%>/pp/docschePage?id="+id+"&date="+date;
	}
</script>
</html>