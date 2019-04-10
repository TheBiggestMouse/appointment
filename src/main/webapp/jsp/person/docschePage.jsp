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
		<form name="infoName" class="form-horizontal text-center"  action="<%=request.getContextPath()%>/pp/docscheSave.action"  method="post">
			<div class="container ">
				<div class="form-group">
					<label class="control-label col-sm-2 col-md-2">科室类别:</label>
					<div class="col-md-4 col-sm-4">
						<select class="form-control"  onchange="departmenttype(this.value)" >
							<option value="">===请选择科室类别===</option>
							<c:forEach items="${listDepartmenttype }" var="t">
								<option value="${t.dplId }" >${t.dplName }</option>							
							</c:forEach>
						</select>
					</div>
					<label class="control-label col-sm-2 col-md-2"></label>
					<div class="col-md-4 col-sm-4">
						<select class="form-control" id="deptName"  onchange="queryDoctorName(this.value)" >
							<option value="">===请选择科室===</option>
						</select>
					</div>
					<label class="control-label col-md-2 col-sm-2">医生姓名</label>
					<div class="col-md-4 col-sm-4">
						<select class="form-control" id="doctorId"  name="doctorId">
							<option value="">===请选择医生===</option>
						</select>
					</div>
					<label class="control-label col-sm-2 col-md-2">排班日期</label>
					<div class="col-md-4 col-sm-4">
						<input class="form-control" type="text" id="mydate" name="schedulingDate"  placeholder="点击选择时间" readonly="" />
					</div>
				</div>
		
			<div class="form-group" style="margin-left: 50px;margin-right: 20px;">
				<table class="table table-hover table-striped table-condensed fuxuandanxuan" >
					<thead >
						<caption class="text-center">排班设置</caption>
					</thead>
					<tr>
						<th>出诊时间</th>
						<th>可预约次数</th>
					</tr>
					<tr>
						<td class="col-xs-2">
							<select name="time1" >
								<option value="">"===请选择时间==="</option>
								<option value="08:00-09:00">08:00-09:00</option>
								<option value="08:00-09:00">09:00-10:00</option>
								<option value="08:00-09:00">10:00-11:00</option>
								<option value="08:00-09:00">11:00-12:00</option>
								<option value="08:00-09:00">12:00-13:00</option>
								<option value="08:00-09:00">13:00-14:00</option>
								<option value="08:00-09:00">15:00-16:00</option>
								<option value="08:00-09:00">16:00-17:00</option>
								<option value="08:00-09:00">17:00-18:00</option>
							</select>
						</td>
						<td class="col-xs-2">
							<input class="form-control" name="count1" placeholder="请选择出诊次数" type="number" max="10"  min="0" size="5" value=""/>
						</td>
					</tr>
					<tr>
						<td class="col-xs-2">
							<select name="time2" >
								<option value="">"===请选择时间==="</option>
								<option value="08:00-09:00">08:00-09:00</option>
								<option value="08:00-09:00">09:00-10:00</option>
								<option value="08:00-09:00">10:00-11:00</option>
								<option value="08:00-09:00">11:00-12:00</option>
								<option value="08:00-09:00">12:00-13:00</option>
								<option value="08:00-09:00">13:00-14:00</option>
								<option value="08:00-09:00">15:00-16:00</option>
								<option value="08:00-09:00">16:00-17:00</option>
								<option value="08:00-09:00">17:00-18:00</option>
							</select>
						</td>
						<td class="col-xs-2">
							<input class="form-control" name="count2" placeholder="请选择出诊次数" type="number" max="10"  min="0" size="5" value=""/>
						</td>
					</tr>
					<tr>
						<td class="col-xs-2">
							<select name="time3" >
								<option value="">"===请选择时间==="</option>
								<option value="08:00-09:00">08:00-09:00</option>
								<option value="08:00-09:00">09:00-10:00</option>
								<option value="08:00-09:00">10:00-11:00</option>
								<option value="08:00-09:00">11:00-12:00</option>
								<option value="08:00-09:00">12:00-13:00</option>
								<option value="08:00-09:00">13:00-14:00</option>
								<option value="08:00-09:00">15:00-16:00</option>
								<option value="08:00-09:00">16:00-17:00</option>
								<option value="08:00-09:00">17:00-18:00</option>
							</select>
						</td>
						<td class="col-xs-2">
							<input class="form-control" name="count3" placeholder="请选择出诊次数" type="number" max="10" min="0"  size="5" value=""/>
						</td>
					</tr>
					<tr>
						<td class="col-xs-2">
							<select name="time4" >
								<option value="">"===请选择时间==="</option>
								<option value="08:00-09:00">08:00-09:00</option>
								<option value="08:00-09:00">09:00-10:00</option>
								<option value="08:00-09:00">10:00-11:00</option>
								<option value="08:00-09:00">11:00-12:00</option>
								<option value="08:00-09:00">12:00-13:00</option>
								<option value="08:00-09:00">13:00-14:00</option>
								<option value="08:00-09:00">15:00-16:00</option>
								<option value="08:00-09:00">16:00-17:00</option>
								<option value="08:00-09:00">17:00-18:00</option>
							</select>
						</td>
						<td class="col-xs-2">
							<input class="form-control" name="count4" placeholder="请选择出诊次数" type="number" max="10"  min="0" size="5" value=""/>
						</td>
					</tr>
					<tr>
						<td class="col-xs-2">
							<select name="time5" >
								<option value="">"===请选择时间==="</option>
								<option value="08:00-09:00">08:00-09:00</option>
								<option value="08:00-09:00">09:00-10:00</option>
								<option value="08:00-09:00">10:00-11:00</option>
								<option value="08:00-09:00">11:00-12:00</option>
								<option value="08:00-09:00">12:00-13:00</option>
								<option value="08:00-09:00">13:00-14:00</option>
								<option value="08:00-09:00">15:00-16:00</option>
								<option value="08:00-09:00">16:00-17:00</option>
								<option value="08:00-09:00">17:00-18:00</option>
							</select>
						</td>
						<td class="col-xs-2">
							<input class="form-control" name="count5" placeholder="请选择出诊次数" type="number" max="10" min="0"  size="5" value=""/>
						</td>
					</tr>
					<tr>
						<td class="col-xs-2">
							<select name="time6" >
								<option value="">"===请选择时间==="</option>
								<option value="08:00-09:00">08:00-09:00</option>
								<option value="08:00-09:00">09:00-10:00</option>
								<option value="08:00-09:00">10:00-11:00</option>
								<option value="08:00-09:00">11:00-12:00</option>
								<option value="08:00-09:00">12:00-13:00</option>
								<option value="08:00-09:00">13:00-14:00</option>
								<option value="08:00-09:00">15:00-16:00</option>
								<option value="08:00-09:00">16:00-17:00</option>
								<option value="08:00-09:00">17:00-18:00</option>
							</select>
						</td>
						<td class="col-xs-2">
							<input class="form-control" name="count6" placeholder="请选择出诊次数" type="number" max="10" min="0" size="5" value=""/>
						</td>
					</tr>
				</table>
			</div>
			
			 <div class="modal-footer" id="modalFooter2">
	            <button type="submit" class='btn btn-primary' data-dismiss='modal' " >提交</button>
	            <button type='button' class='btn btn-default' data-dismiss='modal' onclick='close1()'>返回</button>
	         </div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">

	/* 
		作者: 郎国峰
		时间: 2017年11月10日17:42:02
		描述: ajax查询科室类别,联动科室名称和医生姓名
	*/
	function departmenttype(id){
		var url = "<%=request.getContextPath()%>/pp/listDepartmentDplId.action?sjs="+Math.random();
		var data = {"id":id};
		$.post(url, data, function(msg){//ajax根据科室类别id,查询所有的科室
			$("#deptName").empty();
			for(var i = 0; i < msg.length ; i++){//遍历所有的科室,动态添加到科室上
				var dept = msg[i];
				$("#deptName").append("<option value='"+dept.dpId+"'>"+dept.dpName+"</option>");
			}
			//根据第一个科室名称,用ajax在查询所有的人员
			queryDoctorName(msg[0].dpId);
		}, "json");
	}
	/* 
		作者: 郎国峰
		时间: 2017年11月10日19:07:02
		描述: 根据科室名称,用ajax查询医生姓名
	*/
	function queryDoctorName(id){
		var url = "<%=request.getContextPath()%>/pp/queryDoctorName.action?sjs="+Math.random();
		var data = {"id":id};
		$.post(url, data, function(msg){
			$("#doctorId").empty();
			for(var i = 0; i < msg.length ; i++){//遍历所有医生,动态添加到医生下拉里
				var doc = msg[i];
				$("#doctorId").append("<option value='"+doc.ppId+"'>"+doc.ppName+"</option>");
			}
		}, "json");
	}
	
	
	/* 
		作者: 郎国峰
		时间: 2017年11月10日19:41:24
		描述: 显示日期
	*/
	$("#mydate").datetimepicker({
        minView: 'month',
        format:'yyyy-mm-dd',
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        forceParse: 0,
        showMeridian: 1,
        startDate: new Date(),
        language: 'zh-CN'
    });
	
</script>
</html>

