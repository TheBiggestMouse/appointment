<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../../common/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8" />
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
	<body >
		<div class="container" style="margin-top: 10px;">
		<form name="delFrom" action="<%=request.getContextPath() %>/menu/delete" method="post">
			<div class=" col-lg-6 col-md-6 col-sm-20 col-xs-20" >
			<div class="col-sm-4" style="margin-top: 20px;">
										<div class="input-group">
											<input type="text" class="form-control" name="menuName" placeholder="请输入菜单姓名" value="${pd.menuName }">
									      	<div class="input-group-btn">
					        					<button type="button" name="a" class="btn btn-warning" onclick="doSearch()"><span class="glyphicon glyphicon-search"></span></button>
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
		<%-- 载入菜单的右键菜单 --%>
		<jsp:include page="menu_contextManu.jsp"></jsp:include>
		<script type="text/javascript">
			$("#menutree").tree({
				url:'<%=request.getContextPath() %>/tree/querySysMenu.action',
				lines:true,
				onContextMenu: function(e, node){
					e.preventDefault();
					// 选择节点
					$('#menutree').tree('select', node.target);
					//判断是几级菜单
					$('#menu_contextMenu').menu('show', {left: e.pageX,top: e.pageY});
				}
			});
			//添加菜单
			function menu_add(){
		        var treenode = $("#menutree").tree("getSelected");
		        //console.info(treenode);
		        if(treenode==null || "3"==treenode.attributes.level){
		        	$.messager.alert('提示','请先选择一个1级或2级菜单!','info');
		        }else{
		        	var id = treenode.id;
		        	var level=parseInt(treenode.attributes.level)+1;
		        	var dd = $('<div/>').dialog({
						href:"${pageContext.request.contextPath}/tree/addpage.action",
						width : 400,
						height: 240,
						modal : true,
						title : '添加信息',
						buttons : [ {
							text : '增加',
							iconCls : 'icon-add',
							handler : function() {
								$('#sys_tree_addForm').form('submit', {
									url : '${pageContext.request.contextPath}/tree/addsave.action?menuPare='+id+'&menuIslink='+level,
									onSubmit: function(){       
										 return $(this).form("validate");
									},    
									success : function(result) {
										try {
											var r = $.parseJSON(result);
											if (r.success) {
												//添加一些节点到选择的节点
												var selected = $('#menutree').tree('getSelected');
												$('#menutree').tree('append', {
													parent: selected.target,
													data:[{
														id:r.obj.menuId,
														text:r.obj.menuName,
														state:'open',
														attributes:{level:r.obj.menuIslink}
													}]
												});
												dd.dialog('destroy');
											}
											$.messager.show({
												title : '提示',
												msg : r.msg
											});
										} catch (e) {
											$.messager.alert('提示', result);
										}
									}
								});
							}
						} ],
						onClose : function() {
							$(this).dialog('destroy');
						}
					});
		        }
		    }
		    //修改菜单
		    function menu_edit(){  
		     
		        var treenode = $("#menutree").tree("getSelected");
		        if(treenode!=null){
		        	var id = treenode.id;
		        	var dd = $('<div/>').dialog({
						href:"${pageContext.request.contextPath}/tree/editpage.action?id="+id,
						width : 400,
						height: 240,
						modal : true,
						title : '编辑信息',
						buttons : [ {
							text : '修改保存',
							iconCls : 'icon-add',
							handler : function() {
								$('#sys_tree_editForm').form('submit', {
									url : '${pageContext.request.contextPath}/tree/editsave.action',
									onSubmit: function(){       
										 return $(this).form("validate");
									},    
									success : function(result) {
										try {
											var r = $.parseJSON(result);
											if (r.success) {
												//更新选择的节点文本
												$('#menutree').tree('update', {
													target: treenode.target,
													text:r.obj.menuName,
													attributes:{url:r.obj.menuUrl,level:r.obj.menuIslink}
												});
												dd.dialog('destroy');
											}
											$.messager.show({
												title : '提示',
												msg : r.msg
											});
										} catch (e) {
											$.messager.alert('提示', result);
										}
									}
								});
							}
						} ],
						onClose : function() {
							$(this).dialog('destroy');
						}
					});
		        }else{
		        	$.messager.alert('提示','请先选择一个菜单!','info');
		        }
		    }
		    function menu_delete(){
		    	var treenode = $("#menutree").tree("getSelected");
		    	if(treenode!=null){
		    		var id = treenode.id;
		    		if( treenode.children != null && treenode.children.length>0 ){
		    			$.messager.alert('提示','请先删除子菜单再删除当前菜单!','info');
		    			return ;
		    		}
		    		$.messager.confirm('确认提示','确定删除选中的菜单吗？',function(r){   
					    if (r){
					    	$.post("${pageContext.request.contextPath}/tree/delete.action","id="+id,function(data){
					    		if(data.success){
					    			$.messager.show({ title:'提示', msg:data.msg });
					    			$("#menutree").tree("remove",treenode.target);
					    		}else{
					    			
					    		}
					    	},"json");
					    }   
					});
		    	}else{
		    		$.messager.alert('提示','请先选择一个要删除的菜单!','info');
		    	}
		    }
		</script>
	</body>
	
</html>
