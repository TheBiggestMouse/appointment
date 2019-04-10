<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../common/common.jsp" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>

	
	
		<div class="container" style="margin-top: 10px;">
        
					<form class="form-horizontal text-center" name="infoForm" action="javascript:doSub()"  enctype="multipart/form-data" method="post">
<body>
		<div class="container" style="margin-top: 10px;">
        	<form class="form form-horizontal" name="patientInfoForm" method="post">
        	
					<div class="col-xs-5">
						<div id="preview" style="width: 75px;height: 100px;border: 0px solid red;">
								<img id="image" class="img-responsive" src="<%=request.getContextPath() %>/base/download?url=${patient.patientPic }"  width="75" height="100">
						</div>
    					<input class="form-control" type="file" name="pic"  width="75" onchange="yuLan(this,'image','preview')"/>
					
					
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
	</body>
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

   
    	//修改 
    		function doSub(){
		$("#myModal").modal('show');
	}
    	
    	
    	function editsave(){
    	
   			 <%-- window.location.href = "<%=request.getContextPath()%>/personal/editsave";   --%>
   			$("form[name='infoForm']").attr("action","<%=request.getContextPath()%>/personal/avatarsave");
   			$("form").submit();
   			 
   			} 
    	function goBack(){
    	 window.location.href = "<%=request.getContextPath()%>/personal/avatarquery";   
    	}
    			
		
		
		
	</script>
</html>

