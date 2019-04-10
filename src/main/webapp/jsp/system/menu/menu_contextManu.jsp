<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>云众科技网上预约挂号系统</title>
</head>
<body>
	<%-- 菜单的右键菜单 --%>
	<div id="menu_contextMenu"  style="width:120px;">
		<div onclick="menu_contextMenu_append()" data-options="iconCls:'icon-add'">添加子菜单</div>
		<div onclick="menu_contextMenu_update()" data-options="iconCls:'icon-edit'">修改菜单</div>
		<div onclick="menu_contextMenu_remove()" data-options="iconCls:'icon-remove'">删除菜单</div>
	</div>
	<script type="text/javascript">
	$(function(){
		$('#menu_contextMenu').menu();
	});
	function menu_contextMenu_append(){
		menu_add();
	}
	function menu_contextMenu_update(){
		menu_edit();
	}
	function menu_contextMenu_remove(){
		menu_delete();
	}
	</script>
</body>
</html>