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
				<input type="hidden" name="menuId" value="${menu1.menuId}">
				<div class="container" >
					<div class="form-group" >
						<label class="control-label col-sm-2 col-md-2">菜单名称:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="menuName" required value="${menu1.menuName}" placeholder="请输入菜单名称" onblur="validUserName(this)"/>
						</div>	
					</div>
					<div class="form-group" >
						<label class="control-label col-sm-2 col-md-2">父菜单名称:</label>
						<c:if test="${menu1==null }">
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="parMenuName" readonly="readonly" required value="${parMenuName.menuName }" placeholder="" onblur="validUserName(this)"/>
						</div>
						</c:if>
						<c:if test="${menu1!=null }">
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="parMenuName" required value="${menu1.parMenuName}" placeholder="" onblur="validUserName(this)"/>
						</div>
						</c:if>
					</div>
					<div class="form-group" >
						<label class="control-label col-sm-2 col-md-2">菜单路径:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="menuUrl" required value="${menu1.menuUrl}" placeholder="请输入菜单路径" onblur="validUserName(this)"/>
						</div>	
					</div>
					<div class="form-group" >
						<label class="control-label col-sm-2 col-md-2">菜单级别:</label>
						<c:if test="${menu1==null }">
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="menuIslink" readonly="readonly" required value="${parMenuName.menuIslink+1 }" placeholder="" onblur="validUserName(this)"/>
						</div>
						</c:if>
						<c:if test="${menu1!=null }">
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="menuIslink" required value="${menu1.menuIslink}" placeholder="" onblur="validUserName(this)"/>
						</div>
						</c:if>
					</div>
					<div class="form-group" >
						<label class="control-label col-sm-2 col-md-2">菜单序列:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="menuSequ" required value="${menu1.menuSequ}" placeholder="请输入菜单序列" onblur="validUserName(this)"/>
						</div>	
					</div>
					<c:if test="${menu!=null}">
						<div class="form-group">
							<label class="control-label col-sm-2 col-md-2">菜单状态:</label>
							<div class="col-md-6 col-sm-6">
								<div class="col-md-5 col-sm-5">
									<input type="radio"  ${menu1.menuState=="A"?"checked":"" } class="col-md-1 col-sm-1"  name="menuState"  value="A" /><span class="col-md-8 col-sm-8">可用</span>&nbsp;&nbsp;
								</div>
								<div class="col-md-5 col-sm-5">
									<input type="radio" ${menu1.menuState=="N"?"checked":"" } class="col-md-1 col-sm-1"  name="menuState"  value="N" /><span class="col-md-8 col-sm-8">不可用</span>
								</div>	
							</div>
						</div>
					</c:if>
					<div class="form-group" >
						<label class="control-label col-sm-2 col-md-2">菜单说明:</label>
						<div class="col-md-4 col-sm-4">
							<input class="form-control" name="menuInfo" required value="${menu1.menuInfo}" placeholder="请输入菜单说明" onblur="validUserName(this)"/>
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
		/* function validUserName(inputElement){
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
		} */
		//提交方法
		function doSub(){
			bootbox.confirm("确定要提交吗?", function(result) {
				if(result){
					$("form[name='infoForm']").attr("action","<%=request.getContextPath()%>/sys/saveOrUpdateMenu");
					$("form[name='infoForm']").submit();
				}
			})
		}
		function goBack(){
			history.go(-1);
		}
	</script>
</html>