<%@page import="com.yunzhong.appointment.config.SessionConst"%>
<%@page import="org.apache.shiro.SecurityUtils"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../../common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="UTF-8">
		<title>角色菜单权限</title>
	</head>
	<body style="background:linear-gradient(white,#ebebeb,white);">
		<div class="container">
			<hr>
			<form class="form-horizontal text-center" name="infoForm" method="post">
					<div class="container ">
						<div class="form-group">
							<label class="control-label col-sm-2 col-md-2">角色名称:</label>
							<div class="col-md-4 col-sm-4">
								<input class="form-control" value="${role.roleName }"  readonly="readonly"/>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2 col-md-2">可设置权限:</label>	
							<div class="col-md-4 col-sm-4 ">
								<select class="form-control" id="left" name="right" size="20" multiple>
									<c:forEach items="${menuList}" var="menu">
										<c:if test="${menu.selected==false}">
											<option value="${menu.label}" ondblclick="moveSide(this)">${menu.value}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
							<label class="control-label col-sm-2 col-md-2">已拥有权限:</label>	
							<div class="col-md-4 col-sm-4 ">
								<select class="form-control" id="right" name="left" size="20">
									<c:forEach items="${menuList}" var="menu">
										<c:if test="${menu.selected==true}">
											<option value="${menu.label}" ondblclick="moveSide(this)">${menu.value}</option>
										</c:if>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-3 col-md-3 col-sm-2 col-xs-2">
								
							</div>
							<div class=" col-lg-6 col-md-6 col-sm-8 col-xs-8" >
								<div class="col-md-6 col-sm-6">
									<input class="form-control btn-primary" type="button" onclick="doSub()" value="提交" />
								</div>
								<div class="col-md-6 col-sm-6">
									<input class="form-control btn-primary" type="button" onclick="goBack()" value="返回" />
								</div>
							</div>
						</div>
					</div>
			</form>
				<hr />
		</div>
		<!-- 模态框 -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
		   aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
		      <div class="modal-content">
		         <div class="modal-header" style="background-color: rgba(0,0,0,0.1)">
		            <button type="button" class="close" data-dismiss="modal" 
		               aria-hidden="true">×
		            </button>
		            <h4 class="modal-title" id="myModalLabel">
		              	提交信息
		            </h4>
		         </div>
		         <div class="modal-body text-center" id="modalBody">
		           	确认提交?
		         </div>
		         <div class="modal-footer" id="modalFooter">
		            <button type="button" class="btn btn-primary" onclick="tijiao()" data-dismiss="modal">确定</button>
		            <button type="button" class="btn btn-primary" data-dismiss="modal">关闭</button>
		         </div>
		      </div>
		   </div>
		</div>
	</body>
</html>
<script type="text/javascript">
	function moveSide(opt){
		var par = opt.parentNode;
		par.removeChild(opt);
		document.getElementById(par.getAttribute("name")).appendChild(opt);
	}
	
	function doSub(){
		bootbox.confirm("确定要修改吗？",function(result){
			if(result){
				tijiao();
			}
		})
	}
	
	function tijiao(){
		var opts = $("#right option");
		var rv = "";
		for(var i=0;i<opts.length;i++){
			rv+=opts[i].value+"@";
		}
		if(rv.length>0){
			rv = rv.substring(0, rv.length-1);
		}
		location.href = "<%=request.getContextPath()%>/role/updateAuthRole?menuIds="+rv+"&roleId=${role.roleId}";
	}
	
	function goBack(){
		window.history.go(-1);
	}
</script>