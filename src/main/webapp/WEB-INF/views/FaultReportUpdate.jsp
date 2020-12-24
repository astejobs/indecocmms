<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>

 	<!-- Css files-->
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/crop/css/style.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/crop/css/style-example.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/crop/css/jquery.Jcrop.css" />
	<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/assets/timepicker/wickedpicker.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/assets/timepicker/wickedpicker.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/assets/css/DateTimePicker.css">
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/assets/css/sol.css">
    
    <!-- Js files-->
  <!--    <script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/assets/crop/scripts/jquery-1.10.2.min.js"></script>-->
    <script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/assets/crop/scripts/jquery.Jcrop.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/assets/crop/scripts/jquery.SimpleCropper.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/timepicker/wickedpicker.min.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/DateTimePicker.js"></script>
<!--  <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/FaultReport.js"></script>-->
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/timepicker/wickedpicker.min.js"></script>
    <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/sol.js"></script>
	<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/FaultReport.js"></script>
	
	<style>#AMD td{ width:800px; } #AMD textarea{ width:100%;} #cont { width:100%; display: flex; flex-direction: row; flex-wrap: nowrap; justify-content: space-between; margin-top:100px; position:relative } #cont > div { width: 33.33%; height: 300px; margin-bottom:-100px } .div-center { display: table; margin-right: auto; margin-left: auto; } .btnforImg{ float:left !important; position:relative } #tblAfterImage tr td img{ height:50px;width:50px } #tblBeforeImage tr td img{ height:50px;width:50px }#tblAfterImage tr { display: block; float: left; } #tblAfterImage  tr td img{ height:50px;width:50px } #tblAfterImage  td { display: block; } #tblBeforeImage tr td img{ height:50px;width:50px } #tblBeforeImage tr { display: block; float: left; } #tblBeforeImage td { display: block; }
	

.grow { transition: all .2s ease-in-out; }
.grow:hover { transform: scale(6); }

span{
  margin-left:10px
}
#AMD td {
    width: 869px;
}

.al   {
    border: none;
    background-color: #0754a4;
    color: #fff;
    padding: 7px 9px;
    border-radius: 0px;
   text-decoration: none;

}
.al:hover {
          color:#FFF;
          text-decoration: none;
}
	
	</style>
	
	<script type="text/javascript">
     $(document).ready(function () {
        $('#demonstration').searchableOptionList({ maxHeight: '300px', showSelectAll: true });
        var imgcounter = 0;
      /*   $("#tblBeforeImage").find('img.sno').each(function(i) {
			 imgcounter=i;
	           console.log(i);
	        }); */
    	$("#showImg").click(function () {
    		
              var counter = $("#tblBeforeImage tbody tr").length - 1;
              var inbase64 = $('.cropme').html();
              var widthBase64 = inbase64.replace('<img src="', '');
              var img = widthBase64.replace(',','@');
            
            /*   $("#tblBeforeImage").find('img.sno').each(function(i) {
            	  if(i=="undefined"){
      			 imgcounter=i;
      	           console.log(i); }
      	        }); */
      	        
      	        if(counter=="undefined"){
      	        	counter=0;
      	        }
      	        else{
      	        	counter++;
      	        }
              if(inbase64==""){
           	   
              }
              else{
           	   if(counter<5) {
              $("#tblBeforeImage").append(
                  '<tr> <td><img  class="sno grow" name="BeforeImage['+counter+']" src="' + widthBase64 +
                      '<a class="btn-remove-tr" style="color:black;position:relative;left:-39px;top:45px;"> \
                        <img style="width:20px;height:20px;" src="${pageContext.servletContext.contextPath }/resources/assets/images/trash..png" /> \
                      </a> \
                        </td> \
                        <td><input type="hidden" class="sno" name="imageBefore[]" value="'+img+'</td> \
                  </tr>');
            /*   $("#tblBeforeImage").find('img.sno').each(function(i) {
     			 imgcounter=i;
     	           console.log(i);
     	        }); */
             
           	   }
              }
          });
    	
    	
    	$('#tblBeforeImage').on('click', '.btn-remove-tr', function (e) {
              e.preventDefault();
              if ($('#tblBeforeImage tr').length > 1) {
                  $(this).closest('tr').remove();
                  $('td img.sno').attr("id", function (i) {
                	  return ('BeforeImage['+i+']');
                  })
                   $('td img.sno').attr("name", function (i) {
                  	 imgcounter=i+1;
                	  return ('BeforeImage['+i+']');
                	 
                  })
               }
              return false;										              
        });
          
        jQuery(document).ready(function ($) {
          $('#image').on('change', function (e) {
              $(this).base64img({
                  url: e.target.files[0],
                  result: '#result'
              });
          });
      });

    });
    $(function () {
			$("#showImageAfter").click(function () {
			var countere = $("#tblAfterImage tbody tr").length - 1;
        	var inbase64r = $('#cropafter').html();
        	var widthBase64r = inbase64r.replace('<img src="', '');
        	  var img = widthBase64r.replace(',','@');
        	 if(countere=="undefined"){
   	        	counter=0;
   	        }
   	        else{
   	        	countere++;
   	        }
        	if(inbase64r==""){
            	   
            }
            else{
            	if(countere<5){
        	$("#tblAfterImage").append(
        		'<tr> <td><img class="grow" class="snoo" name="AfterImage['+countere+']" src="' + widthBase64r +
        	      '<a class="btn-remove-tr" style="color:black;position:relative;left:-30px;top:-5px;"> \
        	           <img style="width:20px;height:20px;position:relative;top:52px;left:-7px" src="${pageContext.servletContext.contextPath }/resources/assets/images/trash..png" /> \
        	       </a> \
        	       </td> \
        	       <td><input type="hidden" class="snoo" name="imageAfter[]" value="'+img+'</td> \
        	    </tr>');
            	}
        	
            }
        	});
        	$('#tblAfterImage').on('click', '.btn-remove-tr', function (e) {
        	    e.preventDefault();
        	   /*  if ($('#tblAfterImage tr').length > 1) {
        	        $(this).closest('tr').remove();
        	        $('td img.snoo').attr("name",  function (i) {
        	            return ('AfterImage['+i+']');
        	        })
        	     } */
        	     console.log($(e.target))
        	    if ($('#tblAfterImage tr').length > 1) {
                    $(this).closest('tr').remove();
                    $('td img.snoo').attr("id", function (i) {
                  	  return ('AfterImage['+i+']');
                    })
                     $('td img.snoo').attr("name", function (i) {
                    	 imgcounter=i+1;
                  	  return ('AfterImage['+i+']');
                  	 
                    })
                 }
        	    return false;		
        	 });
        	jQuery(document).ready(function ($) {
        	    $('#image').on('change', function (e) {
        	        $(this).base64img({
        	            url: e.target.files[0],
        	            result: '#resultAfter'
        	        });
        	    });
        	});
			function fTime(oTime){
				  var d = oTime.replace(/\s+/g, '');    
				  var s = d.substring(0,d.length - 2);
				  var t = s+':'+'00';
				  return t;
			  }
        	$("#startDate").focusin(function() {
	        	  var dt = $("#startDate").val();
	        	  var date= dt;
	        	  var d=new Date(date.split("-").reverse().join("-"));
	        	  var dd=d.getDate();
	        	  var mm=d.getMonth()+1;
	        	  var yy=d.getFullYear();
	        	  var newdate=yy+"-"+mm+"-"+dd;
	        	  $('#txtstartDate').val(newdate +'T'+fTime($('#timepicker-12').val()));
	        	  
	          });
        	$("#endDate").focusin(function() {
     		   	  var dt = $("#endDate").val();
	        	  var date= dt;
	        	  var d=new Date(date.split("-").reverse().join("-"));
	        	  var dd=d.getDate();
	        	  var mm=d.getMonth()+1;
	        	  var yy=d.getFullYear();
	        	  var newdate=yy+"-"+mm+"-"+dd;
	        	  $('#txtendDate').val(newdate +'T'+fTime($('#timepicker-12-hr').val()));
	        });
        	$("#txtendDate").focusin(function(){
         	   var eD = $('#txtendDate').val();
                var sD = $('#txtstartDate').val();
         	   $('#lblLabelHours').html(getCal(eD,sD)).replace('_','');
            })
           function alet(){
        	   var eD = $('#txtendDate').val();
               var sD = $('#txtstartDate').val();
        	   $('#lblLabelHours').html(getCal(eD,sD)).replace('_','');
           }
           function getCal(old_date,new_date){
        		var ONE_DAY = 1000 * 60 * 60 * 24;
                var ONE_HOUR = 1000 * 60 * 60;
                var ONE_MINUTE = 1000 * 60;
                var old_date_obj = new Date(old_date).getTime();
                var new_date_obj = new Date(new_date).getTime();
                var difference_ms = Math.abs(new_date_obj - old_date_obj)
                var days = Math.round(difference_ms / ONE_DAY);
                var hours = Math.round(difference_ms / ONE_HOUR) - (days * 24) - 1;
                var minutes = Math.round(difference_ms / ONE_MINUTE) - (days * 24 * 60) - (hours * 60);
                return  days + ' days, ' + hours + ' hours and ' + minutes + ' minutes.';
        	}
           var startTime = $('.timepicker-12-hr').val();
           console.log("BJBJ"+ startTime)
           var endTime = $('.timepicker-12').val();
          
           
           (function () {
        	 
        	   if (startTime==""){
                  	var twelveHour = $('.timepicker-12-hr').wickedpicker({twentyFour: true});
                  	}
                  if (endTime==""){
                  	var twelveHour = $('.timepicker-12').wickedpicker({twentyFour: true});
                  	
                  	}
        	}());
         
        $( ".timepicker-12-hr" ).focus(function() {
        	if (startTime!=""){
        	var twelveHour = $('.timepicker-12-hr').wickedpicker({twentyFour: true,now:startTime});
        	}
        	else{
        		var twelveHour = $('.timepicker-12-hr').wickedpicker({twentyFour: true});
        	}
              });
        $( ".timepicker-12" ).focus(function() {
        	if (endTime!=""){
        	var twelveHour = $('.timepicker-12').wickedpicker({twentyFour: true,now:endTime});
        	
        	}
        	else{
        		var twelveHour = $('.timepicker-12').wickedpicker({twentyFour: true});
        	}
              });
        
           $('#timepicker-12-hr').change(function(){
        	  
        	   console.log($('.timepicker-12-hr').val().split(":"));
        	   var hourmin=$('.timepicker-12-hr').val().split(":")
        	   var hour=hourmin[0];
        	   var min=hourmin[1]
        	   console.log(min);
        	   var tim=hour+':'+min;
        	   console.log("tim"+tim)
           	   var showstartTime =$('.timepicker-12-hr').val().split(" ").join("");
               console.log("vhvhjv "+showstartTime)
           	   $('#startTime').val(showstartTime);
           	
        	 })
           /* var endTime = $('#endTime').val();
	       var twelveHour = $('.timepicker-12').wickedpicker({twentyFour: true,now:endTime}); */
	       $('#timepicker-12').change(function(){
			   	var showendTime =$('.timepicker-12').val().split(" ").join("");
	           	$("#endTime").val(showendTime);
	       })
	       $("#AddMoreRemarks").click(function () {
               var counter = $("#AMD  tbody  tr").length;
               $("#AMD").append('<tr><td><textarea placeholder="Remarks" name="remarks" class="ids" style="padding:5px;margin-bottom:3px;-moz-box-shadow: none;box-shadow:none;border: 1px solid #808080 " id=' + counter + ' ></textarea>&nbsp;<a class="btn-tr" style="color:black"><i class="fa fa-trash"></i></a></td></tr>');
           });
           $('#AMD').on('click', '.btn-tr', function (e) {
               e.preventDefault();
               if ($('#AMD tr').length > 1) {
                   $(this).closest('tr').remove();
                   $('textarea[class="ids"]').attr("id", function (i) {
                       return (i + 1);
                   })
               }
               return false;
           });
          });
    </script>
</head>

<body>    

        <!-- Head Section -->
		    <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Update</li>
			  </ol>
			</nav>
	        
<div class="container">
	<div class="row">
        <div class="col-md-12">
        
        <div class="col-md-10 jumbotron offset-box">
		    			<div class="clearfix">
		                                <c:if test="${not empty success}">
		                                	<div class="btn-success" style="text-align:center">${success}</div>
		                                </c:if>
		                                <c:if test="${not empty fail}">
		                                	<div class="error">${fail}</div>
		                                </c:if>
	                                    <div>
		    			<c:url value="/faultReport/update" var="update"/>
		    			
					 	 <form:form action="${update}/${faultReport.frId}"  commandName="faultReport" method="post" enctype="multipart/form-data" class="form contact-form" >
	                                	<input type="hidden" value="${pageContext.servletContext.contextPath}" id="context"/>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                            Requestor Information
                                        </h2>
                                        <form:hidden path="id"/>
                                        <form:hidden path="activationTime"/>
                                        <form:hidden path="arrivalTime"/>
                                        <form:hidden path="responseTime"/>
                                        <form:hidden path="pauseTime"/>
                                        <form:hidden path="completionTime"/>
                                        <form:hidden path="acknowledgementTime"/>
                                         <form:hidden path="restartTime"/>
                                          <form:hidden path="downTime"/>
                                        
                                        
                                        
                                        
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Fault Report ID</span>
                                            <form:input type="text" path="frId" readonly="true" id="Fault_Report_ID" class="input-md round form-control" placeholder="Fault Report ID" />
                                        </div>
                                      
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Requestor Name</span>
                                            <form:input type="text" path="requestorName" readonly="true" id="Requestor_Name" class="input-md round form-control" placeholder="Requestor Name" />
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Requestor Contact No</span>
                                            <form:input type="text" path="requestorContactNo" readonly="true" id="Requestor_Contact_Number" class="input-md round form-control" placeholder="Requestor Contact Number" />
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Department</span>
                                            <form:select path="department" class="input-md round form-control">
                                            <form:option value="">Select Department</form:option>
                                            <form:options items="${departmentList}" itemLabel="name" itemValue="id"/>
                                            </form:select>
                                        </div>
                                                                              
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Priority</span>
                                            <form:select path="priority" class="input-md round form-control">
                                           	<form:option value="">Select any Priority</form:option>
                                           	<form:options items="${priorityList}" itemLabel="name" itemValue="id"/>
                                            </form:select>
                                          	<form:errors path="priority"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Building</span>
                                            <form:select path="building" id="building" class="input-md round form-control">
                                            <form:option value="">Select any Building*</form:option>
                                            <form:options items="${buildingList}" itemLabel="name" itemValue="id"/>
                                            </form:select>
                                            <form:errors path="building"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Location</span>
                                            <form:select path="location" id="location" class="input-md round form-control">
                                            <form:option value="">Select Location*</form:option>
                                            <form:options items="${locationList}" itemLabel="name" itemValue="id"/>
                                            </form:select>
                                            <form:errors path="location"/>
                                        </div>
                                         <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Location Description</span>
                                            <form:textarea path="locationDesc" class="input-md round form-control col-lg-3" placeholder="Location Description"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Division</span>
                                            <form:select path="division" class="input-md round form-control">
                                            <form:option value="">Select any Division*</form:option>
                                            <form:options items="${divisionList}" itemLabel="name" itemValue="id"/>
        	                                </form:select>
		                                </div>
		                                
		                                
		                                
		                                <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Technician(s)</span>
                                            <form:select id="demonstration"  path="attendedBy" class="selOne"  multiple="multiple">

                                         	<form:options items="${faultReport.attendedBy}" itemLabel="username" itemValue="id" selected="true"/>
                                            </form:select>

                                            <form:errors class="err" path="attendedBy"/>
                                        </div>
                                        
                                       
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                            Fault Information
                                        </h2>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Fault Category</span>
                                    		<form:select path="faultCategory" class="input-md round form-control">
                                        	<form:option value="">Select any Fault Category*</form:option>
                                        	<form:options items="${faultCategoryList}" itemLabel="name" itemValue="id"/>
		                                    </form:select>
		                                    <form:errors path="faultCategory"/>
                            		    </div>
                           			    <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Fault Description</span>
                    		                <form:textarea path="faultCategoryDesc" class="input-md round form-control col-lg-3" style="margin-bottom:10px" placeholder="Fault Description*" />
          			                    </div>
                                		<div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Maintenance Group</span>
		                                    <form:select path="maintGrp" class="input-md round form-control">
		                                        <form:option value="">Select any Maintenance Group*</form:option>
		                                        <form:options items="${maintenanceGroupList}" itemLabel="name" itemValue="id"/>
		                                    </form:select>
		                                    <form:errors path="maintGrp"/>
                               			</div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Created By</span>
                                            <form:input type="text" path="createdBy" readonly="true" id="createdBy" class="input-md round form-control" placeholder="Created By" />
                                        </div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                            Rectification Information
                                        </h2>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Equipment</span>
                                            <form:select id="equipment" path="equipment" class="input-md round form-control">
                                             <c:choose>	
                                             	<c:when test="${empty faultReport.equipment.id}">
                                            	   <form:option value="" selected="true">Select Equipment</form:option>
                                             	</c:when>
                                             	<c:otherwise>
                                            	   <form:option value="${faultReport.equipment.id}">${faultReport.equipment.name}</form:option>
                                            	</c:otherwise>
                                            </c:choose>
                                            </form:select>
                                        </div>
                                        <div class="form-group">
                                          <input type="button" value="Select Equipment"  onclick="window.open('${pageContext.servletContext.contextPath}/faultReport/equipment?buildingId='+document.getElementById('building').value+'&locationId='+document.getElementById('location').value, 'New', 'height=600,width=800,scrollbars=yes'); return false;"  id="selectEquip" />
                                        
                                          <!--   <input type="button" value="Select Equipment" onclick="window.open('${pageContext.servletContext.contextPath}/faultReport/equipment', 'New', 'height=600,width=800,scrollbars=yes'); return false;"  id="selectEquip" />
                                       -->  </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Observation</span>
                                            <form:textarea path="observation" class="input-md round form-control col-lg-3" style="margin-bottom:10px" placeholder="Observation"></form:textarea>
                                        	<form:errors path="observation"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Action Taken</span>
                                            <form:textarea path="actionTaken" class="input-md round form-control col-lg-3" style="margin-bottom:10px" placeholder="Action Taken"></form:textarea>
                                       		<form:errors class="err" path="actionTaken"/>
                                        </div>
                                        <div class="form-group">
	                                        <table id="AMD" style="border:none !important;width:100%">
	                                            <thead>
	                                            </thead>

	                                            <tbody> 
	                                            <c:forEach items="${faultReport.remarks}"></c:forEach>

	                                            <tbody>
                                    <tr>
	                                                    <td>
	                                                    <div class="form-group">
	                                                    <form:textarea path="remarks"  class="input-md round form-control col-lg-3" style="margin-bottom:10px" id="0" placeholder="Remarks"></form:textarea></div></td>
	                                                                            <input type="hidden" name="remarkids" value="${rem.id}">
	                                                    
	                                                    <td></td>
	                                                </tr>
	                                            </tbody>
	                                        </table>
                                        </div>
                                        <div class="form-group">
                                            <input type="button" value="Add More Remarks" id="AddMoreRemarks"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Reason for Outstanding</span>
                                            <form:textarea path="reasonForOutstanding" class="input-md round form-control" placeholder="Reason for Outstanding"></form:textarea>
                                        	<form:errors path="reasonForOutstanding"/>
                                        </div>
                                        <%-- <div class="form-group">
                                            <form:input type="text" path="startDate" id="startDate" class="input-md round form-control" placeholder="Start Date" style="width:50%" />
                                            <img src="${pageContext.servletContext.contextPath }/resources/assets/date/cal.png" onclick="javascript: NewCssCal('startDate','ddMMyyyy')" style="cursor:pointer;width:30px" />
                                       		<fmt:formatDate value="${faultReport.startTime}" var="startTime" pattern="HH:mm"/>
                                        	&nbsp;Start Time:&nbsp;<form:input path="startTime" type="text" id="timepicker-12-hr" class="timepicker-12-hr"/> HH:MM
										
									    <br>
									    <form:errors path="startDate" class="err"/>
                                        </div>
                                        <div class="form-group">
                                         <form:input type="text" path="endDate" id="endDate"  class="input-md round form-control" placeholder="End Date" style="width:50%" />
		                                     <img src="${pageContext.servletContext.contextPath }/resources/assets/date/cal.png" onclick="javascript: NewCssCal('endDate','ddMMyyyy');" style="cursor:pointer;width:30px" />
		                                     End Time:&nbsp;&nbsp;&nbsp;
		                                     <fmt:formatDate value="${faultReport.endTime}" var="endTime" pattern="HH:mm"/>                                            
		 									 <form:input path="endTime" type="text" id="timepicker-12"   class="timepicker-12" /> HH:MM 
		 									
     							            <form:errors path="endDate" class="err"/>
     							               
										    <input type="hidden" id="txtstartDate" />
										    <input type="hidden" id="txtendDate" />
                                        </div> --%>
                                       <%--  <div class="form-group">
                                            Labour Hours &nbsp;&nbsp;
                                            <label id="lblLabelHours">${faultReport.labourHrs} HH:MM</label>
                                        </div> --%>
                                       <%--  <div class="form-group">
                                            Add Parts&nbsp; <a class="al" href="${pageContext.servletContext.contextPath}/faultReport/${faultReport.frId}/parts">Reserve Part</a>
                                         </div> --%>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Total Part Cost</span>
                                            <form:input type="text" disabled="disabled" path="totalPartCost" id="totalpartCost" class="input-md round form-control" placeholder="Total Part Cost" />
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Cost Center</span>
                                            <form:select path="costCenter" class="input-md round form-control">
		                                        <form:option value="">Select any Cost Center</form:option>
		                                        <form:options items="${costCenterList}" itemLabel="costCenterName" itemValue="id"/>
		                                    </form:select>
		                                    <form:errors path="costCenter"/>
                                        </div>
                                       <%--  <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Technician Name</span>
                                            <form:select id="demonstration"  path="attendedBy" class="selOne"  style="border:1px dashed #0754a4;padding:5px" multiple="multiple">
                                         	<form:options items="${technicianList}" itemLabel="technicianName" itemValue="id"/>
                                            </form:select>
                                        </div> --%>
                                       <div class="form-group">
	                                       <div id="cont"> 
	                                       		<div>
	                                       			<div class="div-center">
			                                       	<h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080;width: 213px;">
                                    Before Image
                                </h2>
			                                            <div class="cropme" style="width: 200px; height: 200px;"></div>
		                                            </div>
		                                        </div>
		                                        <div></div>
		                                        <div>
		                                        	<div class="div-center">
			                                       		<h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080;width: 213px;">
                                    After Image
                                </h2>
			                                            <div class="cropme" id="cropafter" style="width: 200px; height: 200px;"></div>
		                                            </div>
		                                        </div>
	                                       </div>
	                                    </div>
								        <script>
                                                $('.cropme').simpleCropper();
										</script>
		                                <div class="form-group">
				                          	<div id="cont">
	                                       		<div>
	                                       			<div class="div-center">
			                                       		<input type="button" id="showImg" class="btnforImg"  value="Add to List" />
		                                            </div>
		                                        </div>
		                                        <div></div>
		                                        <div>
		                                        	<div class="div-center">
		                                        	<div class="clearfix"></div>
			                                       		<input type="button" id="showImageAfter"  value="Add to List" />

		                                            </div>
		                                        </div>
	                                       </div>
		                                </div>
                                        <div class="form-group">
                                   			 	<input type="hidden" id="result"/>
	                                   			        <div id="cont" style="margin-top:-100px;position:relative"><div>
											                <div class="div-center" style="width: 400px">
	     									                    <table id="tblBeforeImage" >
												                     <thead>
												                        <tr><td><h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080;">
													                                    Before Image List
													                                </h2></td></tr>
												                     </thead>
												                     <tbody>
												                      
											                        <c:forEach items="${faultReport.beforeImage}" var="befImage" varStatus="loop">
												                      <tr>
												                       <td class="sno${loop.index}">	
																           <img style="height:50px;width:50px" class="sno grow" src="${pageContext.servletContext.contextPath}/faultReport/getimage/${befImage}"><a class="btn-remove-tr" style="color:black;position:relative;top:46px;left:-48px;"><img style="width:20px;height:20px;" src="${pageContext.servletContext.contextPath }/resources/assets/images/trash..png" /></a>
																           <input type="hidden" name="beforeImage[${loop.index}]" value="${befImage}"/>
																       </td> 
																      </tr>
																    </c:forEach>
												                      
			  									                     </tbody>
			   								                    </table>
														   </div>
														</div>
														
														<div>
														<div class="div-center" style="width: 400px">
                                                         	<input type="hidden" id="resultAfter" />
					                                           	 <table id="tblAfterImage">
															           <thead>
															               <tr><td><h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080;">
													                                    After Image List
													                                </h2></td></tr>
															           </thead>
																       <tbody>
																           <c:forEach items="${faultReport.afterImage}" var="afterImage" varStatus="loop">
																			<tr>
																	           <td class="sno${loop.index}">
																		           <img style="height:50px;width:50px;"  class="grow" src="${pageContext.servletContext.contextPath}/faultReport/getimage/${afterImage}">'<a class="btn-remove-tr" style="color:black;position:relative;left:-30px;top:-5px;"><img style="width:20px;height:20px;position: relative;top: 52px;left: -7px;" src="${pageContext.servletContext.contextPath }/resources/assets/images/trash..png" /></a>
																		           <input type="hidden" name="afterImage[${loop.index}]" value="${afterImage}"/>
																	           </td>
																          </tr> 
																        </c:forEach>

													    			   </tbody>
											 			         </table>
	    										          </div>
											      </div>
   	                                        </div>
		                               </div>
		                               
                                        
                                         </div>
                                         <div class="form-group" >
                                         <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080;width: 111px;;">
													                                    Status
													                                </h2>
                                        	<form:radiobuttons path="status" items="${status}"/>
                                        </div>
                                         <div class="form-group">
                                         	<div class="div-center">
                                            <input type="button" id="print" value="Print" />
                                            <input style="margin-left: 10px;" type="submit" id="Update" value="Update" />
                                            </div>
                                        </div>
                 			</form:form>
                 			<br>
					</div>
		  		</div>
		  	</div>
		   </div>
		 </div>
		  		
	</body>
</html>

