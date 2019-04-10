<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="common/common.jsp" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<title>Turing网上预约挂号系统</title>
	</head>
	<style>
		html body{
			width: 100%;
		}
		.piaoyikuang{
			border: 1px solid #66AFE9;
			border-radius: 10px;
		}
		.piaoyi{
			float: left;
			width: 100px;
			height: 30px;
			border: 0px solid #66AFE9;
			text-align: center;
		}
		.ks-left{
			border: 1px solid #66AFE9;
			border-radius: 10px;
			margin-right: 0px;
		}
		.ks-right{
			border: 0px solid #66AFE9;
			border-radius: 10px;
		}
	</style>
	<body>
	
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
		   aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog modal-lg">
		      <div class="modal-content">
		         <div class="modal-header" style="background-color: rgba(0,0,0,0.1)">
		            <button type="button" class="close" data-dismiss="modal" 
		               aria-hidden="true">×
		            </button>
		            <h4 class="modal-title" id="myModalLabel">
		              	确认提交
		            </h4>
		         </div>
		         <div class="modal-body text-center" id="modalBody">
		           	是否确认提交?
		         </div>
		         <div class="modal-footer" id="modalFooter2">
		            <button type='button' class='btn btn-primary' data-dismiss='modal' onclick='tijiao()'>提交</button>
		            <button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button>
		         </div>
		      </div>
		   </div>
		</div>

		<!--
        	作者：赵刚
        	时间：2017-01-06
        	描述：个人中心	联系我们	登录	注册
        -->
		<div class="container">
			<nav class="nav navbar-default navbar-fixed-top container-fluid" role="navigation" style="background-color:aquamarine;">
				<div class="nav navbar-header">
					<!-- 导航头 -->
					<a class="navbar-brand" href="#">Turing</a>
					<button class="navbar-toggle" data-toggle= "collapse" data-target="#mynav">
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
				</div>
				<div class="collapse navbar-collapse" id="mynav">
					<!-- 导航内容 -->
					<ul class="nav navbar-nav">
						<li><a href="../dashboard.html">首页</a></li>
						<li><a href="about.html">联系我们</a></li>
						<!-- 下面的代码需要登录后才能显示 -->
						<li><a href="#"><span class="glyphicon glyphicon-user"></span>欢迎您：张飞</a></li>
					</ul>
					<!-- 导航尾 -->
					<div class="nav navbar-right">
						<ul class="nav navbar-nav">
							<li><a href="login.html">登录</a></li>
							<li><a href="huanzhe_zhuce.html">注册</a></li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
		<!--
        	作者：赵刚
        	时间：2017-01-06
        	描述：logo 项目名称  搜索框
        -->
		<div class="container" style="margin-top: 50px;">
			<div class="col-sm-6" style="margin-left: -15px;">
				<h2 class="text-primary" >云众科技网上预约挂号系统</h2>
			</div>
			<div class="col-sm-6" style="margin-top: 15px;">
				<form method="post" name="form1" class="form form-horizontal">
					<div class="input-group">
				      	<div class="input-group-btn">
					        <button id="index_search_btn" type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown">科室 <span class="caret"></span></button>
					        <ul class="dropdown-menu" role="menu">
					          <li><a href="javascript:search_yuyue('科室')">科室</a></li>
					          <li><a href="javascript:search_yuyue('疾病')">疾病</a></li>
					        </ul>
				      	</div><!-- /btn-group -->
				      	<input type="text" class="form-control" placeholder="请输入搜索内容">
				      	<div class="input-group-btn">
        					<button type="button" class="btn btn-warning" onclick="tiaojian_search()"><span class="glyphicon glyphicon-search"></span></button>
				    	</div>
				      	</div><!-- /input-group -->
			    </form>
			</div>
		</div>
		<!--
        	作者：赵刚
        	时间：2017-01-06
        	描述：导航条
        -->
		<div class="container" style="margin-top: 10px;">
			<ul class="nav nav-pills guahao" role="tablist" style="background-color:lightcyan">
			  	<li class="active"><a href="../dashboard.html">首页</a></li>
			  	<li><a href="../3.appointment/guahao_department.html">按科室挂号</a></li>
			  	<li><a href="#">按疾病挂号</a></li>
			  	<li><a href="../5.notice/notice.html">最新公告</a></li>
			</ul>
		</div>
		<!--
        	作者：赵刚
        	时间：2017-01-08
        	描述：注册信息
        -->
        <div class="container" style="margin-top: 10px;">
        	<form class="form form-horizontal" id="registerForm" method="post" enctype="multipart/form-data">
        		<input style="display: none;"/>
        		<input type="hidden" style="display: none;"/>
        		<div class="form-group">
					<label class="control-label col-sm-3 col-sm-offset-1">昵称：</label>
					<div class="col-sm-4">
						<input class="form-control"  type="text"  name="nickName"   placeholder="请您输入一个昵称"/>
					</div>
				</div>
        		<div class="form-group">
					<label class="control-label col-sm-3 col-sm-offset-1">手机号码：</label>
					<div class="col-sm-4">
						<input class="form-control"  type="text"  name="patientTel"  onblur="telVerify(this.value)"  placeholder="请您输入有效手机号码"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3 col-sm-offset-1">手机验证码：</label>
					<div class="input-group col-sm-3" style=" margin-left:34.7%;  ">
						<input type="text" class="form-control" name="verificationCode" />
						<span class="input-group-addon  btn btn-primary ">获取验证码</span>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3 col-sm-offset-1">姓名：</label>
					<div class="col-sm-4">
						<input class="form-control" type="text" name="patientName"  placeholder="输入您的真实姓名"/>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3 col-sm-offset-1">性别：</label>
					<div class="col-sm-4">
						<div class="radio">
						   <label>
						      <input type="radio" name="patientSex"  value="男" checked> 男
						   </label>
						   &nbsp;&nbsp;
						   <label>
						      <input type="radio" name="patientSex"  value="女" > 女
						   </label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3 col-sm-offset-1">身份证号：</label>
					<div class="col-sm-4">
						<input class="form-control" type="text" onchange="autogenerationBirthday(this.value)"  name="patientUid"  />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3 col-sm-offset-1">出生日期：</label>
					<div class="col-sm-4">
						<input class="form-control" type="text" id="mydate" name="patientBirth"  placeholder="点击选择时间" readonly="" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3 col-sm-offset-1">设置密码：</label>
					<div class="col-sm-4">
						<input class="form-control" type="password" name="pass" type="text" onchange="passLength(this)"  />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3 col-sm-offset-1">确认密码：</label>
					<div class="col-sm-4">
						<input class="form-control" type="password" name="password" type="text" onchange="passVerify(this.value)"   />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-3 col-sm-offset-1 ">省：</label>
					<div class="col-sm-2">
						<input type="hidden" name="provinceName" />
						<select class="form-control"   name="provinceId" onchange="listCity(this.value)" >
							<option value="" selected>请选择省份</option>
							<c:forEach items="${provinceList }" var="t">
								<option value="${t.provinceId }">${t.provinceName }</option>
							</c:forEach>
						</select>
					</div>
					<label class="control-label col-sm-1 ">市：</label>
					<div class="col-sm-2">
						<input type="hidden" name="cityName" />
						<select class="form-control"  id="city"  name="cityId"  onchange="listArea(this.value)" >
							<option value="">请选择区县</option>
						</select>
					</div>
					<label class="control-label col-sm-1 ">区：</label>
					<div class="col-sm-2">
						<input type="hidden" name="areaName" />
						<select class="form-control" id="area" name=areaId onchange="areaNameAdd()" >
							<option value="">请选择区县</option>
						</select>
					</div>
				</div>
				<!-- <div class="form-group">
					<label class="control-label col-sm-3 col-sm-offset-1">详细地址：</label>
					<div class="col-sm-4">
						<input class="form-control" type="password"   type="text"/>
					</div>
				</div> -->
				<div class="form-group">
					<label class="control-label col-sm-3 col-sm-offset-1"></label>
					<div class="col-sm-4">
						<div class="checkbox">
							<label>
						      <input type="checkbox" name="agreement"  value="1" >本人同意
						   </label>
						   <span class="text-info" id="agreement"><a href="javascript:yuyue_xieyi()">《预约挂号服务协议》</span></a>
						</div>
					</div>
				</div>
				<div class="form-group text-center">
					<button class="btn btn-lg btn-warning" type="button" onclick="huanzhe_zhuce()">注&nbsp;&nbsp;册</button>
				</div>
        	</form>
        </div>
	</body>
	<script type="text/javascript">
		//搜索框
		function search_yuyue(tiaojian){
			$("#index_search_btn").html(tiaojian+" <span class=\"caret\"></span>");
		}
		//挂号导航
		$(".guahao a").bind("click",function(){
			if ("首页"==$(this).html()) {
				location.href = "index.html";
			} else if("按科室挂号"==$(this).html()){
				location.href = "guahao_keshi.html";
			}else if("按疾病挂号"==$(this).html()){
				location.href = "guahao_jibing.html";
			}else if("最新公告"==$(this).html()){
				location.href = "guahao_gonggao.html";
			}
		});
		//搜索框条件查询
		function tiaojian_search(){
			var tiaojian = $("#index_search_btn").html();
			if (tiaojian.indexOf("科室")>-1) {
				location.href = "yuyue_keshi.html";
			} else{
				location.href = "yuyue_jibing.html";
			}
		}
		//日期的显示
		$("#mydate").datetimepicker({
	        minView: 'month',
	        format:'yyyy-mm-dd',
	        weekStart: 1,
	        todayBtn: 1,
	        autoclose: 1,
	        todayHighlight: 1,
	        startView: 2,
	        forceParse: 0,
	        showMeridian: 1,
	        endDate: new Date(),
	        language: 'zh-CN'
	    });
	    //预约挂号协议
        function yuyue_xieyi(){
		   	$("#myModalLabel").html("挂号协议");
			$("#modalBody").html("<iframe src='<%=request.getContextPath()%>/static/protocol.html' width='100%' height='470px' style='border:0;'></iframe>");
			$("#modalFooter2").html("<button type='button' class='btn btn-default' data-dismiss='modal'>关闭</button>");
			$("#myModal").modal('show');
	    }
	    
	    
	    
	    
	    
	    
	   /*  function yuyue_xieyi(){
	    	alert("XIEY");
	    	bootbox.confirm("地方",function(resulet){
	    		if(result){
	    			
	    		}
	    	})
	    
	    } */
	    
	    /* 
	    	作者: 郎国峰
	    	时间: 2017年10月27日19:01:21
	    	描述: 注册页的信息校验,提交注册表单
	    */
	    function huanzhe_zhuce(){
	    	var span = $("#warn"); //抓取到所有的提示
	    	for(var i = 0;i<span.length;i++){
	    		span[i].remove();//将所有的提示删除
	    	}
	    
	    	
	    	var tel = $("input[name='patientTel']");//抓取到手机号
	    	if(tel.val().trim()==''){ //手机号非空验证
	    		tel.after("<span id='warn' style=\" color:red; \" > 手机号不能为空</span>")
	    		return;
	    	}
			if(!telVerify(tel.val())){//如果不是正确的手机号,或者手机号重复,那么久终止提交
	    		return;
	    	}
	    	var verificationCode = $("input[name='verificationCode']"); //抓取到手机验证码
	    	if(verificationCode.val().trim()==''){ //验证码非空验证
	    		verificationCode.after("<span id='warn' style=\" color:red; \" > 验证码不能为空</span>")
	    		return;
	    	}
	    	var patientName = $("input[name='patientName']"); //抓取到姓名
	    	if(patientName.val().trim()==''){ //姓名非空验证
	    		patientName.after("<span id='warn' style=\" color:red; \" > 姓名不能为空</span>")
	    		return;
	    	}
	    	var patientUid = $("input[name='patientUid']"); //抓取到身份证
	    	if(patientUid.val().trim()==''){ //身份证非空验证
	    		patientUid.after("<span id='warn' style=\" color:red; \" > 身份证不能为空</span>")
	    		return;
	    	}
	    	if(!autogenerationBirthday(patientUid.val())){//如果不是正确的身份证号,就终止提交
	    		return;
	    	}
	    	var patientBirth = $("input[name='patientBirth']"); //抓取到出生日期
	    	if(patientBirth.val().trim()==''){ //出生日期非空验证
	    		patientBirth.after("<span id='warn' style=\" color:red; \" > 出生日期不能为空</span>")
	    		return;
	    	}
	    	var password = $("input[name='password']"); //抓取到密码
	    	if(password.val().trim()==''){ //密码非空验证
	    		password.after("<span id='warn' style=\" color:red; \" > 密码不能为空</span>")
	    		return;
	    	}
	    	if(!(/^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,20}$/.test(password.val().trim()))){ //密码6-20位,
	    		password.after("<span id='warn' style=\" color:red; \" >密码过于简单</span>")
	    		return;
	    	}
	     	if(!passVerify(password.val())){//如果两次密码不一致,就终止提交
	    		return;
	    	} 
	    	var provinceId = $("select[name='provinceId']"); //抓取到省id
	    	if(provinceId.val().trim()==''){ //省非空验证
	    		provinceId.after("<span id='warn' style=\" color:red; \" > 省不能为空</span>")
	    		return;
	    	}
	    	var cityId = $("select[name='cityId']"); //抓取到市id
	    	if(cityId.val().trim()==''){ //市非空验证
	    		cityId.after("<span id='warn' style=\" color:red; \" > 市不能为空</span>")
	    		return;
	    	}
	    	var areaId = $("select[name='areaId']"); //抓取到区id
	    	if(areaId.val().trim()==''){ //区非空验证
	    		areaId.after("<span id='warn' style=\" color:red; \" > 区不能为空</span>")
	    		return;
	    	}
	    	var agreement = $("input[name='agreement']"); //抓取到协议复选框
			if(!agreement[0].checked){
				$("#agreement").after("<span id='warn' style=\" color:red; \" >请阅读并同意协议</span>");
				return;
			} 
	    	//动态提交表单
	    	var form = $("#registerForm"); //抓取到form表单
	    	var action = "<%=request.getContextPath()%>/registerUser" //声明一个请求路径  
	    	form.attr("action",action); //给form表单添加action属性
	    	form.submit(); //提交
	    }
	    /* 
	    	作者:郎国峰
	    	时间:2017年10月27日10:25:13
	    	描述:用ajax根据省id,查询所有的市以json形式返回
	    */
	    function listCity(value){
    		var url = "<%=request.getContextPath()%>/listCity?sjs="+Math.random(); //请求的控制器全路径 package com.yunzhong.appointment.system.controller  - SysRegisterController
    		var data = {"provinceId":value}
    		$.post(url, data, function(msg){
    			console.log(msg);
    			//清空市下拉下的所有信息
    			$("#city").empty();
    			//动态将所有的市信息添加到城市下拉下
    			$("#city").append("<option value=''>请选择城市</option>"); 
    			for(var i = 0; i < msg.length; i++){
    				var json = msg[i];
    				var id=json.cityId;
    				var name=json.cityName;
    				$("#city").append("<option value='"+id+"'>"+name+"</option>");
    			}
    		}, "json");
    		//动态添加选择的省名称
    		var option = $("select[name='provinceId'] option:selected"); //得到选中的option
    		$("input[name='provinceName']").val(option.text());
 			//调用查询区的AJAX方法
 			listArea(value);
    	}
	    /*  
	    	作者: 郎国峰
	    	时间: 2017年10月27日13:50:09
	    	描述: 用ajax根据市id,查询所有的区县以json形式返回
	    */
	    function listArea(value){
    		var url = "<%=request.getContextPath()%>/listArea?sjs="+Math.random(); //请求的控制器全路径 package com.yunzhong.appointment.system.controller  - SysRegisterController
    		var data = {"cityId":value}
    		$.post(url, data, function(msg){
    			console.log(msg);
    			//清空区县下拉下的所有信息
    			$("#area").empty();
    			//动态将所有的区县信息添加到取现下拉下
    			 $("#area").append("<option value=''>请选择区县</option>"); 
    			for(var i = 0; i < msg.length; i++){
    				var json = msg[i];
    				var id=json.areaId;
    				var name=json.areaName;
    				$("#area").append("<option value='"+id+"'>"+name+"</option>");
    			}
    		}, "json");
    		//动态添加选择的市名称
    		var option = $("select[name='cityId'] option:selected"); //得到选中的option
    		$("input[name='cityName']").val(option.text());
	    }
	    /* 
	    	作者: 郎国峰
	    	时间: 2017年10月27日15:10:58
	    	描述: 对注册信息手机号的验证
	    */
	    function telVerify(value){
	    	var span = $("#warn"); //抓取到所有的提示
	    	for(var i = 0;i<span.length;i++){
	    		span[i].remove();//将所有的提示删除
	    	}
	    	var tel = $("input[name='patientTel']");//抓取到手机号
	    	//验证是否是正确的手机号码	
	    	if(!(/^1[3|4|5|7|8][0-9]\d{8}$/.test(value))){ 
	    		tel.after("<span id='warn' style=\" color:red; \" >请输入正确的手机号码</span>")
	    		return false;
   			} 
	    	//用ajax验证数据库是否有此手机号
	    	var url = "<%=request.getContextPath()%>/telVerify?sjs="+Math.random();  //请求的控制器全路径 package com.yunzhong.appointment.system.controller  - SysRegisterController
	    	var data = {"tel":value};
	    	$.post(url, data, function(msg){
	    		if(msg=='1'){
	    			tel.after("<span id='warn' style=\" color:red; \" >该手机号已经注册</span>")
	    			return false;
	    		}
	    	}, "text");
	    	return true;
	    }
	    /* 
	    	作者: 郎国峰
	    	时间: 2017年10月27日21:26:03
	    	描述: 验证身份证号格式是否正确,如果出生日期为空,并跟据身份证号生成出生日期
	    */
	    function autogenerationBirthday(value){
	    	var span = $("#warn"); //抓取到所有的提示
	    	for(var i = 0;i<span.length;i++){
	    		span[i].remove();//将所有的提示删除
	    	}
	    	var patientUid = $("input[name='patientUid']"); //抓取到身份证
	    	if(!(/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/.test(value))){ 
	    		patientUid.after("<span id='warn' style=\" color:red; \" >请输入正确的身份证号</span>")
	    		return false;
  			} 
	    	
	    	//用ajax验证身份证号是否唯一
	    	var url = "<%=request.getContextPath()%>/patientUidVerify?sjs="+Math.random();  //请求的控制器全路径 package com.yunzhong.appointment.system.controller - SysRegisterController
	    	var data = {"patientUid":value};
	    	$.post(url, data, function(msg){
	    		if(msg=='1'){
	    			patientUid.after("<span id='warn' style=\" color:red; \" >该身份证号已经注册</span>")
	    			return false;
	    		}
	    	}, "text");
	    	
	    	
	    	var patientBirth = $("input[name='patientBirth']");
	    	if(patientBirth.val().trim()==""){//判断出生日期是否为空
	    		//根据身份证号进行字符串拼接,得到出生日期
	    		var birthday = patientUid.val().substr(6,4)+"-"+patientUid.val().substr(10,2)+"-"+patientUid.val().substr(12,2);
	    		patientBirth.val(birthday); //将得到的出生日期赋值给出生日期这个input
	    	}
	    	return true;
	    }
	    /* 
	    	作者: 郎国峰
	    	时间: 2017年10月27日21:43:23
	    	描述: 验证两次密码是否相同
	    */
	    function passVerify(value){
	    	var span = $("#warn"); //抓取到所有的提示
	    	for(var i = 0;i<span.length;i++){
	    		span[i].remove();//将所有的提示删除
	    	}
	    	var pass = $("input[name='pass']"); //抓取设置密码
	    	var password = $("input[name='password']"); //抓取确认密码
	    	if(!(value.trim()==pass.val().trim())){
	    		password.val("");
	    		password.after("<span id='warn' style=\" color:red; \" >两次密码不一致,请重新输入!</span>");
	    		return false;
	    	}
	    	return true;
	    }
	    /* 
	    	作者: 郎国峰
	    	时间: 2017年10月28日10:02:57
	    	描述: 验证密码长度及是否是过于简单
	    */
	    function passLength(value){
	    	if(!(/^(?![\d]+$)(?![a-zA-Z]+$)(?![^\da-zA-Z]+$).{6,20}$/.test(value.value))){ //密码6-20位,
	    		$(value).after("<span id='warn' style=\" color:red; \" >密码过于简单</span>");
	    		return false;
	    	}
	    	return true;
	    }
	    /* 
	    	作者: 郎国峰
	    	时间: 2017年10月28日05:39:10
	    	描述: 动态添加选择的区名称
	    */
	    function areaNameAdd(){
    		var option = $("select[name='areaId'] option:selected"); //得到选中的option
    		$("input[name='areaName']").val(option.text());
	    }
	</script>
</html>
