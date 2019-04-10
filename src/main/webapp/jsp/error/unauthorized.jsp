<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="../common/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/js/remodal/remodal.css"/>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/static/js/remodal/remodal-default-theme.css"/>
		<script type="text/javascript" src="<%=request.getContextPath() %>/static/js/remodal/remodal.min.js" ></script>
	</head>
	<body style="background:linear-gradient(white,#ebebeb,white);">
		<!--
        	作者：石洪刚
        	时间：2017年8月13日20:27:08
        	描述：remodal的模态框
        -->
        
        <div class="remodal" data-remodal-id="modal" role="dialog" aria-labelledby="modal1Title" aria-describedby="modal1Desc">
			<button data-remodal-action="close" class="remodal-close" aria-label="Close"></button>
		  	<div>
		    	<h2 id="modal1Title">Remodal</h2>
		    	<p id="modal1Desc">
		     	 Responsive, lightweight, fast, synchronized with CSS animations, fully customizable modal window plugin
		    	  with declarative state notation and hash tracking.
		    	</p>
		  	</div>
		  	<br>
		  	<button data-remodal-action="confirm" class="remodal-confirm" onclick="dashBoard()">确定</button>
        </div>
	</body>
	<script type="text/javascript">
		$(function(){
			var inst = $('[data-remodal-id=modal]').remodal();
			$("#modal1Title").html("警告！");
			$("#modal1Desc").html("您无权进行此操作！");
			inst.open();
		});
		
		function dashBoard(){
			location.href = "/";
		}
	</script>
</html>
