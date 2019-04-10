<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

	<style >
		
	</style>
	<body>
		<div class="container" style="margin-top: 10px;">
        
					<form class="form-horizontal text-center" name="infoForm" action="javascript:doSub()" method="post">
<!--   	<form class="form-horizontal text-center" name="infoForm" action="/personal/editsave" method="post"> -->
        	<input type="hidden" value="${patient.userId }" name="userId"  >
        	<input type="hidden" value="${patient.patientId }" name="patientId"  >
        	<input type="hidden" value="${patient.userName }" name="userName"  >
        		<div class="form-group">
					<label class="control-label col-xs-2">手机号码：</label>
					<div class="col-xs-5">
						<input class="form-control" type="text" name="" value="${telnum }" readonly="readonly"/>
						<input class="form-control" type="hidden" name="patientTel" value="${patient.patientTel }" readonly="readonly"/>
					</div>
					 <div class="col-xs-5">
					 <%--
						<div id="preview" style="width: 75px;height: 100px;border: 0px solid red;">
								<img id="image" class="img-responsive" src="${patient.patientPic }" width="75" height="100">
					</div>
    					<input class="form-control" type="file" name="pic"  width="75" onchange="yuLan(this,'image','preview')"/>
					
					--%>
					</div>
							 
				</div>
				<div class="form-group">
					<label class="control-label col-xs-2">姓名：</label>
					<div class="col-xs-5">
						<input class="form-control" type="text" name="patientName" value="${patient.patientName}" placeholder="输入您的真实姓名" />
					</div>
					<!--  -->
					
					
				
				
				<!--  -->
				</div>
				<div class="form-group">
					<label class="control-label col-xs-2">性别：</label>
					<div class="col-xs-5">
						<div class="radio">
						   <label>
						      <input type="radio" name="patientSex"  value="1"${patient.patientSex =='1'?'checked':'' } > 男
						   </label>
						   &nbsp;&nbsp;
						   <label>
						      <input type="radio" name="patientSex"  value="0"${patient.patientSex =='0'?'checked':'' }  > 女
						   </label>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-2">身份证号：</label>
					<div class="col-xs-5">
						<input class="form-control" type="text" name="" value="${uid }" readonly="readonly" />
						<input class="form-control" type="hidden" name="patientUid" value="${patient.patientUid }" readonly="readonly" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-2">出生日期：</label>
					<div class="col-xs-5">
						<input class="form-control" type="text" id="mydate" name="birth" value="2017-01-10"  placeholder="点击选择时间"   readonly="readonly" data-beatpicker="true" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-2">现居住地：</label>
					<div class="col-xs-3">
						<select class="form-control" name="provinceId"  id="provinceId" onchange="listCity(this.value)">
							<c:forEach items="${provinceList }" var="list" >		
										
								<option value="${list.provinceId}" ${patient.provinceId==list.provinceId?"selected":"" } >${list.provinceName}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-xs-3">
					<input type="hidden" name="cityName" />
						<select class="form-control"  id="city" name="cityId" onchange="listArea(this.value)" >
					<c:forEach items="${cityList }" var="l" >	
								<option value="${l.cityId}" ${patient.cityId==l.cityId?"selected":"" } >${l.cityName}</option>
					</c:forEach>
						</select>
					</div>
					<div class="col-xs-3">
						<input type="hidden"  name="areaName"  />
						<select class="form-control" id="area"onchange="areaNameAdd()">
								<c:forEach items="${areaList}"  var="area"  >
								<option value="${area.areaId}"${patient.areaId==area.cityId?"selected":"" }  >${area.areaName}</option>
								</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group text-center">
					<label class="control-label col-xs-2"></label>
					<div class="col-xs-5">
		  	<button class="btn btn-lg btn-warning" type="submit" >修&nbsp;&nbsp;改</button>    
							
							
							
					</div>
				</div>
        	</form>
        </div>
    <!--     //莫泰框 -->
        		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" 
		   aria-labelledby="myModalLabel" aria-hidden="true">
		   <div class="modal-dialog">
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
		            <button type='button' class='btn btn-primary' data-dismiss='modal' onclick='editsave()'>提交</button>
		            <button type='button' class='btn btn-default' data-dismiss='modal' onclick='goBack()'  >不提交</button>
		         </div>
		      </div>
		   </div>
		</div>
        
	</body>
	<script type="text/javascript">
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
	        language: 'zh-CN'
	    });
		
		//抓取手机号码input框中的value值
		// 匹配手机号首尾，以类似“123****8901”的形式输出
	//	var str = $('input[name="tel"]').val();
	//	str = str.replace(reg, function(a, b, c, d) {
	//	    return b + c.replace(/\d/g, "*") + d;
	//	});
		//console.log(str);
	//	$('input[name="tel"]').val(str);
			
	    /*
	    	作者:郎国峰
	    	时间:2017年10月27日10:25:13
	    	描述:用ajax根据省id,查询所有的市以json形式返回
	    */
	    function listCity(value){
    		var url = "<%=request.getContextPath()%>/listCity";
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
    	}
	    /*  
	    	作者: 郎国峰
	    	时间: 2017年10月27日13:50:09
	    	描述: 用ajax根据市id,查询所有的区县以json形式返回
	    */
	    function listArea(value){
    		var url = "<%=request.getContextPath()%>/listArea";
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
		
    	
   
    	//修改 
    		function doSub(){
		$("#myModal").modal('show');
	}
    	
    	
    	function editsave(){
    		
   			 <%-- window.location.href = "<%=request.getContextPath()%>/personal/editsave";   --%>
   			$("form[name='infoForm']").attr("action","<%=request.getContextPath()%>/personal/editsave");
   			$("form").submit();
   			 
   			} 
    	function goBack(){
    	 window.location.href = "<%=request.getContextPath()%>/personal/editPage";   
    	}
    			
		
		
		
	</script>
</html>

