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
				<input type="hidden" name="userId" value="${user.userId}">
				<div class="container" >
					<div class="form-group" >
						<label class="control-label col-sm-2 col-md-2">登录名称:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="userName" required value="${user.userName}" placeholder="请输入登录名称" onblur="validUserName(this)"/>
						</div>	
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">登录密码:</label>
						<div class="col-md-4 col-sm-4">
							<c:if test="${user==null }">
								<input class="form-control" name="userPass" type="password"  required pattern="(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[@#$%^z&*?]+$).{6,20}"  placeholder="请输入登录密码" oninvalid="validatelt(this,'密码必须由数字、字母、特殊字符中至少两种组成，长度至少为6位，最多20位')"/>
							</c:if>
							<c:if test="${user!=null }">
								<input class="form-control" name="userPass" type="password"  pattern="(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[@#$%^z&*?]+$).{6,20}"  placeholder="请输入登录密码" oninvalid="validatelt(this,'密码必须由数字、字母、特殊字符中至少两种组成，长度至少为6位，最多20位')"/>
							</c:if>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">用户昵称:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="userNickname" value="${user.userNickname}" placeholder="请输入用户昵称" />
						</div>
					</div>
					<c:if test="${user!=null}">
						<div class="form-group">
							<label class="control-label col-sm-2 col-md-2">用户状态:</label>
							<div class="col-md-6 col-sm-6">
								<div class="col-md-5 col-sm-5">
									<input type="radio"  ${user.userState=="A"?"checked":"" } class="col-md-1 col-sm-1"  name="userState"  value="A" /><span class="col-md-8 col-sm-8">可用</span>&nbsp;&nbsp;
								</div>
								<div class="col-md-5 col-sm-5">
									<input type="radio" ${user.userState=="N"?"checked":"" } class="col-md-1 col-sm-1"  name="userState"  value="N" /><span class="col-md-8 col-sm-8">不可用</span>
								</div>	
							</div>
						</div>
					</c:if>
					<div class="form-group">
						<label class="control-label col-sm-2 col-md-2">用户信息:</label>
						<div class="col-md-6 col-sm-6">
							<textarea class="form-control" name="userInfo" rows="3" placeholder="请输用户信息" >${user.userInfo }</textarea>
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
		//对验证的提示
		function validatelt(inputElement,err){
	        if(inputElement.validity.patternMismatch){
	        	inputElement.setCustomValidity(err);  
	        }else{  
	        	inputElement.setCustomValidity("");
	     	}
		}
		//对登录名进行验证
		function validUserName(inputElement){
			var userName = inputElement.value;
			var url = "/sys/checkUserName";
			var data = {userName:userName};
			$.ajax({
				async:false,
				url:url,
				data:data,
				dataType:"json",
				success:function(msg){
					if(msg==true){
						inputElement.validity.patternMismatch = false;
						inputElement.setCustomValidity("");
					}else{
						inputElement.validity.patternMismatch = true;
						inputElement.setCustomValidity("登录名称已被占用!");
					}
				}
			});
		}
		//提交方法
		function doSub(){
			bootbox.confirm("确定要提交吗?", function(result) {
				if(result){
					$("form[name='infoForm']").attr("action","<%=request.getContextPath()%>/sys/saveOrUpdateUser");
					$("form[name='infoForm']").submit();
				}
			})
		}
		function goBack(){
			history.go(-1);
		}
	</script>
</html>