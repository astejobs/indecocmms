<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
 
<%-- <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/assets/css/DateTimePicker.css"> --%>
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/assets/timepicker/wickedpicker.css">
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/crop/css/style.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/crop/css/style-example.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath }/resources/assets/crop/css/jquery.Jcrop.css" />
<link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/assets/css/sol.css">
  

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
<%-- <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script> --%>
<%-- <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/DateTimePicker.js"></script> --%>
<%-- <script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/assets/crop/scripts/jquery-1.10.2.min.js"></script> --%>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/assets/crop/scripts/jquery.Jcrop.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/resources/assets/crop/scripts/jquery.SimpleCropper.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/timepicker/wickedpicker.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/sol.js"></script> 
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/FaultReport.js"></script>
  
  <script type="text/javascript">
  $(function(){		  
		  $('#contact_form').validate();
  }); 

 </script> 
 <script type="text/javascript">
$(document).ready(function() {
	
	
	$('#demonstration').searchableOptionList({ maxHeight: '300px', showSelectAll: true });
	
	var count =0;
	var imgcounter = 0;
	$("#showImg").click(function () {
        var counter = $("#tblBeforeImage tbody tr").length - 1;
       
        var inbase64 = $('.cropme').html();
        console.log()
        console.log("inbase" + inbase64);
        var widthBase64 = inbase64.replace('<img src="', '');
        var img = widthBase64.replace(',','@');
       
           if(inbase64==""){
        	   
           }
           else{
        	   if(imgcounter<5) {
        		$("#tblBeforeImage").append(
                        '<tr> <td><img class="grow" style=" class="sno" name="imageBefore['+counter+']" src="' + widthBase64 +
                            '<a class="btn-remove-tr" style=" color:black; position:relative; left:-40px; top:42px;"> \
                              <img style="width:20px;height:20px;" src="${pageContext.servletContext.contextPath }/resources/assets/images/trash..png" /> \
                            </a> \
                              </td> \
                              <td><input type="hidden" class="sno" name="imageBefore[]" value="'+img+'</td> \
                        </tr>');
        		  imgcounter++;
        		
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
    if (window.File && window.FileList && window.FileReader) {
        $("#files").on("change", function(e) {

            var files = e.target.files,
              filesLength = files.length;
            console.log(filesLength);
            count++;
            for (var i = 0; i < filesLength; i++) {
                var f = files[i]
                var fileReader = new FileReader();
                fileReader.onload = (function(e) {
                    var file = e.target;
                    $("<span class=\"pip\">" +
                      "<img class=\"imageThumb\" src=\"" + e.target.result + "\" title=\"" + file.name + "\" />" +
                      "<br/><span class=\"removeImg\">Remove image</span>" +
                      "</span>").insertAfter("#files");
                    $(".imageThumb").attr("id", function (i) {
                        return (i + 1) - 1;
                    })
                    $(".removeImg").click(function(){
                        $(this).parent(".pip").remove();
                        $(".imageThumb").attr("id", function (i) {
                            return (i + 1) - 1;
                        })
                    });
                });
               
              
                fileReader.readAsDataURL(f);
                
            }
        });
    } else {
        alert("Your browser doesn't support to File API")
    }
});
</script>

<style>#cont { 
width:100%; display: flex; flex-direction: row; flex-wrap: nowrap; justify-content: space-between;/*  margin-top:100px; */ position:relative } #cont > div { width: 33.33%; height: 300px; margin-bottom:-100px } .div-center { display: table; margin-right: auto; margin-left: auto; } .btnforImg{ float:left !important; position:relative } #tblAfterImage tr td img{ height:50px;width:50px } #tblBeforeImage tr td img{ height:50px;width:50px }#tblAfterImage tr { display: block; float: left; } #tblAfterImage  tr td img{ height:50px;width:50px } #tblAfterImage  td { display: block; } #tblBeforeImage tr td img{ height:50px;width:50px } #tblBeforeImage tr { display: block; float: left; } #tblBeforeImage td { display: block; }


.grow { transition: all .2s ease-in-out; }
.grow:hover { transform: scale(6); }


</style>

<style> .jim{ background-color:#000; padding:5px; color:#fff } .bodyy{ padding:10px;margin-bottom:5px; } article { width: 80%; margin:auto; margin-top:10px; } input[type="file"] { display: block; } .imageThumb { max-height: 75px; border: 2px solid; padding: 1px; cursor: pointer; } .pip { display: inline-block; margin: 10px 10px 0 0; } .remove { display: block; background: #dcd7d7; border: 1px solid black; color: white; text-align: center; cursor: pointer; } .remove:hover { background: white; color: black; } </style>
<body class="appear-animate"> 
 
 	 <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
       <!-- Head Section -->
        	 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Fault Report Create</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">

                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                        Requestor Information
                    </h2>
                        <c:if test="${not empty success}">
		                                	<div class="btn-success" style="margin-bottom:10px;text-align:center; ">${success}</div>
                        </c:if>
                        <c:if test="${not empty fail}">
                        	<div class="error">${fail}</div>
                        </c:if>
                     						<c:url value="/faultReport/add" var="actionSubmit"></c:url>
                     
                       <input type="hidden" value="${pageContext.servletContext.contextPath}" id="context"/>
                      <form:form  action="${actionSubmit}"  commandName="faultReport"   class="form contact-form" id="contact_form" enctype="multipart/form-data">
                   <div> 
                    <div class="clearfix">
                    
		                            
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Requestor Name</span>
                                            <form:input type="text" path="requestorName" id="Requestor_Name" class="input-md round form-control" placeholder="Requestor Name*" data-validation="required" />
                                        	<form:errors class="err" path="requestorName"/>
                                        </div>
                                         <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Contact Number</span>
                                            <form:input type="text" path="requestorContactNo" id="Contact_Number" class="input-md round form-control" placeholder="Contact Number*" pattern=".{5,100}" />
                                       		<form:errors class="err" path="requestorContactNo"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Department</span>
                                            <form:select path="department" class="input-md round form-control">
                                            	<form:option value="">Select any Department *</form:option>
                                            	<form:options items="${departmentList}" itemLabel="name" itemValue="id"/>
                                            </form:select>
                                            <form:errors class="err" path="department"/>
                                        </div>
                                                                               
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Priority</span>
                                            <form:select path="priority" class="input-md round form-control">
                                            	<form:option value="">Select any Priority</form:option>
                                            	<form:options items="${priorityList}" itemLabel="name" itemValue="id"/>
                                            </form:select>
                                            	<form:errors class="err" path="priority"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Building</span>
                                            <form:select path="building" id="building" class="input-md round form-control">
                                                <form:option value="">Select any Building*</form:option>
                                                <form:options items="${buildingList}" itemLabel="name" itemValue="id"/>
                                            </form:select>
                                            <form:errors class="err" path="building"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Location</span>
                                            <form:select path="location" id="location" class="input-md round form-control">
                                                <form:option value="">Select Location*</form:option>
                                                <form:options items="${locationList}" itemLabel="name" itemValue="id"/>
                                            </form:select>
                                            <form:errors class="err" path="location"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Location Description</span>
                                            <form:textarea path="locationDesc" class="input-md round form-control col-lg-3" placeholder="Location Description"/>
                                        </div>
                                        
                                                                                <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Equipment</span>
                                            <form:select id="equipment" path="equipment" class="input-md round form-control">
                                            	   <form:option value="" selected="true">Select Equipment</form:option>
                                            </form:select>
                                            
                                            
                                        </div>
                                        <div class="form-group">
                                            <input type="button" value="Select Equipment"  onclick="window.open('${pageContext.servletContext.contextPath}/faultReport/equipment?buildingId='+document.getElementById('building').value+'&locationId='+document.getElementById('location').value, 'New', 'height=600,width=700,scrollbars=yes'); return false;"  id="selectEquip" disabled="disabled"/>
                                        </div>
                                     <!--    <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Equipment</span>
                                            <form:select path="equipment" id="equipment" class="input-md round form-control">
                                                <form:option value="">Select Equipment*</form:option>
                                                <form:options items="${equipmentList}" itemLabel="name" itemValue="id"/>
                                            </form:select>
                                            <form:errors class="err" path="equipment"/>
                                        </div>-->
                                        
                                        
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Division</span>
                                            <form:select path="division" class="input-md round form-control" >
                                                <form:option value="">Select any Division*</form:option>
                                                <form:options items="${divisionList}" itemLabel="name" itemValue="id"/>
                                            </form:select>
                                             <form:errors class="err" path="division"/>
                                       </div>
 
                                    
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Technician(s)</span>
                                            <form:select id="demonstration"  path="attendedBy" class="selOne" multiple="multiple" required="required">

                                         	<form:options items="${userDetailList}" itemLabel="username" itemValue="id"/>
                                            </form:select>

                                            <form:errors class="err" path="attendedBy"/>
                                        </div>


                                        
                                        
                                    </div>
                                </div>
                                <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                    Fault Information 
                                </h2>
                                <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Fault Category</span>
                                    <form:select path="faultCategory" class="input-md round form-control">
                                        <form:option value="">Select any Fault Category*</form:option>
                                        <form:options items="${faultCategoryList}" itemLabel="name" itemValue="id"/>
                                    </form:select>
                                    <form:errors class="err" path="faultCategory"/>
                                </div>
                                <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Fault Description</span>
                              <form:textarea path="faultCategoryDesc" class="input-md round form-control" placeholder="Fault Description*" data-validation="required" />
                                    <form:errors class="err" path="faultCategoryDesc"/>
                                </div>
                                <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Maintenance Group</span>
                                    <form:select path="maintGrp" class="input-md round form-control">
                                        <form:option value="">Select any Maintenance Group*</form:option>
                                        <form:options items="${maintenanceGroupList}" itemLabel="name" itemValue="id"/>
                                    </form:select>
                                    <form:errors class="err" path="maintGrp"/>
                                </div>
                                <div class="form-group">
	                                       <div id="cont">
	                                       		<div>
	                                       			<div class="">
			                                       		<h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="width: 213px;">
                                    Before Image
                                </h2>
			                                            <div class="cropme" style="width: 200px; height: 200px;"></div>
		                                            </div>
		                                        </div>
	                                       </div>
	                                    </div>
								        <script>
                                                $('.cropme').simpleCropper();
										</script>
		                                <div class="form-group" style="margin-top: 71px;">
				                          	
	                                       		
	                                       			<div class="">
			                                       		<input type="button" id="showImg" class="btnforImg btn"  value="Add to List" />
		                                            </div>
		                                        
	                                      
		                                </div>
                                        <div class="form-group" style="margin-top:120px">
                                   			 	<input type="hidden" id="result"/>
	                                   			        <div id="cont" style="margin-top:-100px;position:relative">
											                <div class="div-center tblBeforeImageDiv">
	     									                    <table id="tblBeforeImage" style="margin-left: 0px; position: absolute; left: 0px;" >
												                     <thead>
												                        <tr><td>	<h2 class="section-title font-alt align-left mb-70 mb-sm-40">
													                                    Before Image List
													                                </h2>
										                                </td></tr>
												                     </thead>
												                     <tbody>
												                      <tr>
											                        <c:forEach items="${faultReport.beforeImage}" var="befImage" varStatus="loop">
												                       <td class="sno${loop.index}">	
																           <img class="grow" style="height:50px;width:50px" src="${pageContext.servletContext.contextPath}/faultReport/getimage/${befImage}">+'<a class="btn-remove-tr" style="color:black;position:relative;left:-30px;top:-5px;"><img style="width:20px;height:20px;" src="${pageContext.servletContext.contextPath }/resources/assets/images/trash..png" /></a>
																           <input type="hidden" name="beforeImage[${loop.index}]" value="${befImage}"/>
																       </td> 
																       </c:forEach>
												                      </tr>
			  									                     </tbody>
			   								                    </table>
														   </div>
   	                                       		 </div>
   	                                       		 
	   	                                       	<div class="form-group float-right">
	   	                                        	<input type="submit"  value="Create" class="btn" />
	   	                                        </div>
   	                                        </div>
                               </form:form>
                                    <div class="cf-left-col">
                                        <!-- Inform Tip -->
                                        <div class="form-tip pt-20">
                                            <i class="fa fa-info-circle"></i> all fields with (*) are required.
                                        </div>
                                    </div>
                          </div>
                  </div>
           </div>  
           </div>
                       
   </body>
   

</html>
