<%@page import="com.github.pagehelper.PageInfo"%>
<%@page import="com.yunzhong.appointment.entity.SysUser"%>
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
		<div class="main-container" id="main-container">
			<!-- /section:basics/sidebar -->
			<div class="main-content">
				<div class="main-content-inner">
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								
							<!-- 检索  -->
							<form  method="post" name="infoForm" action="/sys/listUser">
								<div class="container">
									<div class="col-sm-4" style="margin-top: 20px;">
										<div class="input-group">
											<input type="text" class="form-control" name="userName" placeholder="请输入登录名称" value="${pd.userName }">
									      	<div class="input-group-btn">
					        					<button type="button" class="btn btn-warning" onclick="doSearch()"><span class="glyphicon glyphicon-search"></span></button>
									    	</div>
									    </div>
							    	</div>
							    	<div class="col-sm-6"></div>
							    	<div class="col-sm-2" style="margin-top: 20px;">
										<div class="form-group">
											<button type="button" class="btn btn-primary" title="增加" onclick="addpage()"><span class="glyphicon glyphicon-plus"></span></button>
											<button type="button" class="btn btn-danger" title="批量删除" onclick="del()"><span class="glyphicon glyphicon-trash"></span></button>
									    </div>
							    	</div>
							    </div>
								<table id="simple-table" class="table table-striped table-hover" style="margin-top:5px;">	
									<thead>
										<tr class="bg-primary">
											<td align="center"><a href="javascript:selectAll()"><font style="color: white;">全选</font></a></td>
											<td align="center">账号</td>
											<td align="center">昵称</td>
											<td align="center">用户信息</td>
											<td align="center">操作</td>
										</tr>
									</thead>
															
									<tbody>
									<!-- 开始循环 -->
									<%
										SysUser firstObj = (SysUser)request.getAttribute("firstObj");
										if(firstObj!=null){
											String firstId = firstObj.getUserId();
											//对page.list进行调整
											PageInfo p = (PageInfo)request.getAttribute("page");
											List<SysUser> list = p.getList();
											boolean flag = false;
											for(int i = 0; i < list.size(); i++){
												SysUser temUser = list.get(i);
												if(firstId.equals(temUser.getUserId())){
													//如果增加或修改的数据本身就在第一页，调整增加的数据到第一位
													SysUser user0 = list.get(0);
													list.set(0, temUser);
													list.set(i, user0);
													flag = true;
												}
											}
											if(!flag){
												//如果增加或修改的数据不再第一页，增加或修改的数据放在第一位
												list.add(0, firstObj);
												list.remove(list.size()-1);
											}
										}
									%>
									<c:choose>
										<c:when test="${not empty page.list}">
											<c:forEach items="${page.list}" var="user" varStatus="i">
												<tr>
													<td align="center"><input type="checkbox" name="ids" value="${user.userId}"></td>
													<td align="center">${user.userName}</td>
													<td align="center">${user.userNickname}</td>
													<td align="center">${user.userInfo}</td>
													<td  align="center">
														<button type="button" class="btn btn-primary" title="修改" onclick="editpage('${user.userId}')"><span class="glyphicon glyphicon-edit"></span></button>
														<button type="button" class="btn btn-danger" title="删除" onclick="delOne('${user.userId}')"><span class="glyphicon glyphicon-minus"></span></button>
														<button type="button" class="btn btn-warning" title="授权" onclick="auth('${user.userId}')"><span class="glyphicon glyphicon glyphicon-link"></span></button>
													</td>
												</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr class="main_info">
												<td colspan="100" class="center" >没有相关数据</td>
											</tr>
										</c:otherwise>
									</c:choose>
									</tbody>
								</table>
								<div align="center">
									<yzpage:page pageName="page" url="/sys/listUser"></yzpage:page>
								</div>
							</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<script type="text/javascript">
	function editpage(id){
		location.href = "/sys/pageUser?id="+id;
	}
	function addpage(){
		location.href = "/sys/pageUser";
	}
	function auth(id){
		location.href = "/sys/authUser?id="+id;
	}
	function delOne(id){
		bootbox.confirm("确定要删除吗?", function(result) {
			if(result){
				location.href = "/sys/removeUser?ids="+id;
			}
		})
	}
	
	function doSearch(){
		$("form[name='infoForm']").attr("action","/sys/listUser");
		$("form[name='infoForm']").submit();
	}
	//全选
	function selectAll(){
		var inpts = $("input[name='ids']");
		for(var i=0;i<inpts.length;i++){
			if(inpts[i].checked!=true){
				inpts[i].checked=true;
			}else{
				inpts[i].checked=false;
			}
		}
	}
	
	//批量操作
	function del(){
		bootbox.confirm("'确定要删除选中的数据吗?", function(result) {
			if(result) {
				var checked = $("form[name='infoForm'] :input:checked");
				
				if(checked.length==0){
					bootbox.dialog({
						message: "<span class='bigger-110'>您没有选择任何内容!</span>",
						buttons: 			
						{ "button":{ "label":"确定", "className":"btn-sm btn-success"}}
					});
					$("#zcheckbox").tips({
						side:1,
			            msg:'点这里全选',
			            bg:'#AE81FF',
			            time:8
			        });
					return;
				}else{
					$("form[name='infoForm']").attr("action","/sys/removeUser");
					$("form[name='infoForm']").submit();
				}
			}
		});
	};
	//对用户进行授权
	function auth(id){
		location.href = "/sys/authUser?id="+id;
	}
	</script>
</html>