<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>云众科技网上预约挂号系统</title>
	</head>

	<body>
		<div class="container">
			<form class="form-horizontal text-center" style="position:relative;"  name="infoForm" action="javascript:doSub()" method="post" enctype="multipart/form-data">
				<input type="hidden" name="roleId" value="${role.roleId}">
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">角色名称:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control"  name="roleName" onblur="verifyName(this.value)" value="${role.roleName}" placeholder="请输入角色名称" />
						</div>
					</div>
					
				
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">角色信息:</label>
						<div class="col-md-6 col-sm-6">
							<textarea class="form-control" name="roleInfo"  placeholder="请输角色信息" >${role.roleInfo }</textarea>
						</div>
					</div>
					<div class="form-group">
						<div class="col-lg-4 col-md-4 col-sm-2 col-xs-2">
							
						</div>
						<div class=" col-lg-4 col-md-4 col-sm-8 col-xs-8" >
							<div class="col-md-6 col-sm-6">
								<input class="form-control btn-primary" type="submit" value="提交" />
							</div>
							<div class="col-md-6 col-sm-6">
								<input class="form-control btn-primary" type="button" onclick="goBack()" value="返回" />
							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-2 col-xs-2">
							
						</div>
					</div>
				</div>
			</form>
			<hr/>
		</div>
	</body>
	<script type="text/javascript">
	
		
		//提交方法
		function doSub(){
			if($.trim($("input[name='roleName']").val())==""){
				bootbox.alert("请输入角色名称");
				return;
			}
			bootbox.confirm("确定要提交吗?", function(result) {
				if(result){
					$("form[name='infoForm']").attr("action","<%=request.getContextPath()%>/role/saveOrUpdateRole");
					$("form[name='infoForm']").submit();
				}
			})
		}
		function goBack(){
			history.go(-1);
		}
		function verifyName(v){
			$("span[name=span]").remove();
			var url  = "<%=request.getContextPath()%>/role/verifyName?t="+Math.random();
			var data = {roleName:v}
			$.post(url , data, function(r){
				if (r != "" && r !="${role.roleName}" ) {
					$("input[name=roleName]").after("<span name = span ><font color = red  >角色名称不可用</Font></span>");
				}
			},"text")
		}
	</script>
</html>