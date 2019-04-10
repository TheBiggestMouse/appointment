<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>云众科技网上预约挂号系统</title>
</head>
<body>
	<div align="center" style="padding: 5px;">
		<form id="sys_tree_addForm" method="post">
			<table>
				<tr>
					<td align="right">菜单名称：</td>
					<td><input name="menuName" class="easyui-validatebox" data-options="required:true"></td>
				</tr>
				<tr>
					<td align="right">菜单顺序号：</td>
					<td><input name="menuSequ" ></td>
				</tr>
				<tr>
					<td align="right">菜单URL：</td>
					<td><input name="menuUrl" ></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>