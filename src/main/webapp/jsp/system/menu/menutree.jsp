<%@page import="com.github.pagehelper.PageInfo"%>
<%@page import="com.yunzhong.appointment.entity.SysRole"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

 <!--  作者: 康磊
 		时间 : 2017年10月30日 10:49:26
 		 菜单维护 查询 页面
  -->

 <script type="text/javascript">
　　$(function(){
 　　$('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
 　　$('.tree li.parent_li > span').on('click', function (e) {
 　　 var children = $(this).parent('li.parent_li').find(' > ul > li');
 　　if (children.is(":visible")) {
  　　children.hide('fast');
  　　$(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
 　　} else {
  　　children.show('fast');
  　　$(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
 　　}
 　　e.stopPropagation();
 　　});
　　});
 </script>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>云众科技网上预约挂号系统</title>
	</head>
	<body>
		<div class="tree well">
 <ul>
  <li>
  <span><i class="icon-folder-open"></i>云众科技网上预约挂号系统
   <input type="button" name="addpage" class="btn btn-primary" value="添加子菜单" onclick="addpage('0')" />
  </span> <a href=""></a>
  <c:forEach items="${menuList }" var="menu1">
  <ul>
   <!-- 所有一级菜单 -->
   <li>
   <span><i class="icon-minus-sign"></i>${menu1.menuName } 
   <input type="button" name="addpage" class="btn btn-primary" value="添加子菜单" onclick="addpage('${menu1.menuId}')" />
   <input type="button" name="editpage" class="btn btn-warning" value="修改" onclick="editpage('${menu1.menuId}','${menu1.sysMenuId}')" />
   <input type="button" name="delMenu" class="btn btn-danger" value="删除" onclick="delMenu('${menu1.menuId}')" />
   
   </span> <a href=""></a>
  <c:if test="${menu1.childrenMenuList!=null }">
   <!-- 一级菜单下的子集菜单 -->
   <c:forEach items="${menu1.childrenMenuList }" var="menu2">
   <ul>
    <li>
    <span><i class="icon-minus-sign"></i>${menu2.menuName }
    <input type="button" name="addpage" class="btn btn-primary" value="添加子菜单" onclick="addpage('${menu2.menuId}')" />
   <input type="button" name="editpage" class="btn btn-warning" value="修改" onclick="editpage('${menu2.menuId}','${menu2.sysMenuId}')" />
   <input type="button" name="delMenu" class="btn btn-danger" value="删除" onclick="delMenu('${menu2.menuId}')" />
    </span> <a href=""></a>
    <c:if test="${menu2.childrenMenuList!=null }">
    <!-- 二级菜单下的子集菜单 -->
    <c:forEach items="${menu2.childrenMenuList }" var="menu3">
    <ul>
     <li>
     <span><i class="icon-minus-sign"></i>${menu3.menuName }
   <input type="button" name="editpage" class="btn btn-warning" value="修改" onclick="editpage('${menu3.menuId}','${menu3.sysMenuId}')" />
   <input type="button" name="delMenu" class="btn btn-danger" value="删除" onclick="delMenu('${menu3.menuId}')" />
     </span> <a href=""></a>
     <ul>
     </ul>
     </li>
    </ul>
    </c:forEach>
    </c:if>
    </li>
   </ul>
   </c:forEach>
   </c:if>
   </li>
  </ul>
  </c:forEach>
  </li>
  
 </ul>
 </div>
	</body>
	<script type="text/javascript">
	function editpage(id,parMenuId){
		location.href = "/sys/pageMenu?id="+id+"&parMenuId="+parMenuId;
	}
	function addpage(parMenuId){
		location.href = "/sys/pageMenu?parMenuId="+parMenuId;
	}
	function delMenu(id){
		bootbox.confirm("确定要删除吗?", function(result) {
			if(result){
				location.href = "/sys/removeMenu?id="+id;
			}
		})
	}
	</script>
</html>
