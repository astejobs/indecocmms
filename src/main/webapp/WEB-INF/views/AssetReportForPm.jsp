<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">

function populateme(){

	 $.ajax({
	        type: "GET",
	        url : $('#ajxUrl1').val()+"/1",
	        dataType: "json",
	        success: function (data) {

	            $('#EquipmentSubsys').append('<option value="" >Select SubSystem</option>');
	        	 
	            $.each(data, function(K, V)
	            {
	            	
	             
	            	  var option = $('<option/>');
	                  option.attr({
	                      'value' : V.id
	                  }).text(V.assetSubTypeName);
	                 
	                  $('#EquipmentSubsys').append(option);
	              
	            }); 
	             
	            }
	      });


	
}


$(document).ready(function() 
{

	populateme();


	
$('#Eq').change(function() {
	
  
	$('#EquipmentSubsys').empty();
   
	  $('#EquipmentSubsys').append('<option value="" >Select SubSystem</option>');
    $.ajax({
        type: "GET",
        url : $('#ajxUrl1').val()+"/"+$('#Eq').val(),
        dataType: "json",
        success: function (data) {
            
          
        	 
            $.each(data, function(K, V)
            {
            	
             
            	  var option = $('<option/>');
                  option.attr({
                      'value' : V.id
                  }).text(V.assetSubTypeName);
                 
                  $('#EquipmentSubsys').append(option);
              
            }); 
             
            }
      });
    
    
    });
    
$('#EquipmentSubsys').change(function() {
	

	$('#Equipment').empty();
    
	 $('#Equipment').append('<option value="" >Select Equipment</option>');
    $.ajax({
        type: "GET",
        url : $('#ajxUrl2').val()+"/"+$('#EquipmentSubsys').val(),
        dataType: "json",
        success: function (data) {
        	
        	
            $.each(data, function(K,V)
            {
             
            	 var option = $('<option/>');
                 option.attr({
                     'value' : V.id
                 }).text(V.name);
                
                 $('#Equipment').append(option);
             
            });  
            }
      });
    
    
    });
    
$('.buildingajax').change(function() {
	

	$('.locationlist').empty();
    
	$('.locationlist').append('<option value="" >Select Location</option>');
    $.ajax({
        type: "GET",
        url : $('#ajxUrl').val()+"/"+$('.buildingajax').val(),
        dataType: "json",
        success: function (data) {
        	
        	 
            $.each(data, function(K,V)
            {
             
            	 var option = $('<option/>');
                 option.attr({
                     'value' : V.id
                 }).text(V.name);
                
                 $('.locationlist').append(option);
             
            });  
            }
      });
    
    
    });
    
})

</script>
</head>
<body class="appear-animate">

    <!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Asset Report For PM</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                        Download Reports
                    </h2>
                           <form:form  method="post" id="validate"  commandName="criterea">
							 <form:hidden path="workspace"/>
							 <input type="hidden" value="1" name="p" id="p" />
							 
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Equipment Type</span>
	                                         <spring:url value="/task/getSubsystem" var="ul" />
								             <input type="hidden" id="ajxUrl1" value="${ul}" />
							
                                             <form:select required="true" id="Eq" path="EquipmentTypeid" class="input-md round form-control">
                                                <option>Select Equipment Type</option>
                                                <c:forEach items="${equipment}" var="e">
												<form:option value="${e.id}">${e.assetTypeName}</form:option>
												</c:forEach>
                                             </form:select>                                            
                                        </div>
                                        
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Sub Type</span>
	                                         <spring:url value="/task/getEquipment" var="ul" />
								             <input type="hidden" id="ajxUrl2" value="${ul}" />
                                            <form:select   id="EquipmentSubsys" path="EquipmentSubtypeid" class="input-md round form-control">
                                                <form:option value="">Select Equipment Sub Type</form:option>
                                                
                                            </form:select>
                                        </div>
                                        <div class="form-group input-group">
                                        	  <span class="input-group-addon label-left" id="basic-addon2">Select Equipment</span>
			                                  <form:select   id="Equipment" path="Equipmentid" class="input-md round form-control">
                                              <form:option value="">Select Equipment</form:option>
                                            </form:select>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Building</span>
	                                        <spring:url value="/task/ajaxLocationList" var="ul" />
								            <input type="hidden" id="ajxUrl" value="${ul}" />
								            <form:select path="Buildingid"  class="buildingajax input-md round form-control">
								                <form:option value="">Select Building / Zone</form:option>
										       <c:forEach items="${buildingList}" var="build">
										       <form:option value="${build.id}">${build.name}</form:option>
									            </c:forEach>
								             </form:select>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Location</span>
                                            <form:select path="Locationid" class="locationlist input-md round form-control">
								             <form:option value="">Select Location / Sub Zone</form:option>
												<c:forEach items="${locations}" var="loc">
												<form:option value="${loc.id}">${loc.name}</form:option>
			
											</c:forEach>
											</form:select>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">In Service/Out of Service</span>
	                                        <form:select path="TaskToBePerformed" class="input-md round form-control">
												<form:option value="">Select</form:option>
												<form:option value="InService">InService</form:option>
												<form:option value="OutOfService">OutOfService</form:option>
												</form:select>
										</div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Frequency</span>
						                     <form:select path="FrequencyOfService"  class="input-md round form-control">
											<form:option value="">Select</form:option>
											<form:option value="1">DAILY</form:option>
											<form:option value="2">WEEKLY</form:option>
											<form:option value="3">FORTNIGHTLY</form:option>
											<form:option value="4">MONTHLY</form:option>
											<form:option value="5">QUARTERLY</form:option>
											<form:option value="6">HALFYEARLY</form:option>
											<form:option value="7">YEARLY</form:option>
											</form:select>
                                       </div>
                                        <div class="form-group">
                                            <span>Choose Status</span> &nbsp;
                                            <form:checkbox  value="open" path="status" />
												Open <form:checkbox value="Closed" path="status"/>
												Closed <form:checkbox value="InProgress" path="status"/>
												In Progress <form:checkbox value="KIV" path="status"/>
												KIV
                                        </div>
                                        
                                       <!--  <div class="row" style="margin:0px"> -->
	                                        <div class="form-group input-group">
	                                        	<span class="input-group-addon label-left" id="basic-addon2">From Date</span>
	                                            <form:input type="text" required="required" name="reported_date-from" id="date-from" style="width:80%" class="input-md round " placeholder="Date From" path="From"  onclick="javascript: NewCssCal('date-from','ddMMyyyy')" />
	                                            <%-- <img src="${pageContext.servletContext.contextPath}/resources/assets/images/date/cal.png" onclick="javascript: NewCssCal('date-from','ddMMyyyy')" style="cursor:pointer;width:30px" /> --%>
	                                            </div>
	                                            <div class="form-group input-group">
	                                        	<span class="input-group-addon label-left" id="basic-addon2">From Date</span>
	                                            <form:input type="text"  required="required"  name="reported_date-to" id="date-to" style="width:80%" class="input-md round" placeholder="Date To" path="To" onclick="javascript: NewCssCal('date-to','ddMMyyyy')" />
	                                            <%-- <img src="${pageContext.servletContext.contextPath}/resources/assets/images/date/cal.png" onclick="javascript: NewCssCal('date-to','ddMMyyyy')" style="cursor:pointer;width:30px" /> --%>
	                                        </div>
                                       <!--  </div> -->
                                        <div class="form-group float-right">
                                            <input type="submit" id="submit" value="Submit" class="btn"  name="submit"  />
                                            <input type="submit" id="btnexport" name="_xls" class="btn" value="Export to Excel" />
                                        </div>
                                        <div class="form-group">
                                            <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                            Search Result
                                        </h2>
                                        </div>
                                        <div class="table-responsive">
                                            <table id="table table-stripped table-hover tblResult">
                                                <thead>
                                                    <tr>
                                                        <th>PM Task No.</th>
                                                        <th>PM Schedule No.</th>
                                                        <th>Status</th>
                                                        <th>Freq. Services</th>
                                                        <th>Schedule Date</th>
                                                        <th>type</th>
                                                        <th>Sub Type</th>
                                                        <th>Name</th>
                                                        <th>Buildng</th>
                                                        <th>Location</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                   <c:forEach items="${tasks}" var="item">
									
									
										<tr>
									<td>${item.task_number}</td>	
									
										<td>${item.schedule.scheduleNumber}</td> 
										
										<td>${item.status}</td>
										
										<td>
										

										<c:set var="string1" value="${item.schedule.frequency}"/>
										
	<c:if test="${1<=item.schedule.frequency}">
						<c:choose>
							<c:when test="${item.schedule.frequency==1}">	
							<c:set var="string2" value="${fn:replace(string1, 
                                '1', 'DAILY')}" />
							</c:when>
							<c:when test="${item.schedule.frequency==2}">	
							<c:set var="string2" value="${fn:replace(string1, 
                                '2', 'WEEKLY')}" />
							</c:when>
							<c:when test="${item.schedule.frequency==3}">	
							<c:set var="string2" value="${fn:replace(string1, 
                                '3', 'FORTNIGHTLY')}" />
							</c:when>
							
							<c:when test="${item.schedule.frequency==4}">	
							<c:set var="string2" value="${fn:replace(string1, 
                                '4', 'MONTHLY')}" />
							</c:when>
							<c:when test="${item.schedule.frequency==5}">	
							<c:set var="string2" value="${fn:replace(string1, 
                                '5', 'QUARTERLY')}" />
							</c:when>
							<c:when test="${item.schedule.frequency==6}">	
							<c:set var="string2" value="${fn:replace(string1, 
                                '6', 'HALFYEARLY')}" />
							</c:when>
							<c:when test="${item.schedule.frequency==7}">	
							<c:set var="string2" value="${fn:replace(string1, 
                                '7', 'YEARLY')}" />
							</c:when>
							</c:choose>
									
									</c:if>
															

                                

                                          ${string2}
										
										
                                
										</td> 
										<td>${item.scheduleDate}</td>
										
										
										<td>${fn:toUpperCase(fn:substring(item.equipment.assetSubtype.assetType.assetTypeName,0,1))}${fn:toLowerCase(fn:substring(item.equipment.assetSubtype.assetType.assetTypeName,1,-1)) }</td>
										
										<td>${item.equipment.assetSubtype.assetSubTypeName}</td>
											<td>${item.equipment.name}</td>
											
											<td>${item.equipment.building.name}</td>
											<td>${item.equipment.location.name}</td>
										
											
											
										</tr>
									</c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="clearfix">&nbsp;</div>


					<c:if test="${1<total}">
						<c:choose>
							<c:when test="${total>10}">
								<div class="clearfix">
									<%-- Showing 10 pages out of ${total} pages. --%>
								</div>
								<c:choose>
									<c:when test="${page<=5}">
										<c:set var="from" value="1" />
										<c:choose>
											<c:when test="${total>from + 9}">
												<c:set var="to" value="${from + 9}" />
											</c:when>
											<c:otherwise>
												<c:set var="to" value="${total}" />
											</c:otherwise>
										</c:choose>
									</c:when>

									<c:otherwise>
										<c:set var="from" value="${page-5}" />
										<c:choose>
											<c:when test="${total>page+5}">
												<c:set var="to" value="${page+4}" />
											</c:when>
											<c:otherwise>
												<c:set var="from" value="${total-9}" />
												<c:set var="to" value="${total}" />
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</c:when>

							<c:when test="${total>0}">
								<br>
								<%-- Showing ${total} page(s) out of ${total} page(s). --%>
								<c:set var="from" value="1" />
								<c:set var="to" value="${total}" />
							</c:when>
						</c:choose>
						<table cellspacing="0" cellpadding="0" border="0"
							id="paging-table">
							<tbody>
								<tr>
									<td><c:if test="${page>1}">

											<a class="page-far-left" id="leftFirst" href="#" alt="1"></a>
											<a class="page-left" id="leftNext" href="#" alt="${page-1}" /></a>
										</c:if>
										<div id="page-info">

											<c:forEach var="i" begin="${from}" end="${to}">
												<%-- 
													<c:url value="engrproject/cmms/build/building"
														var="page_link">
														<c:param name="pageno" value="${i}" />
													</c:url> --%>
												<c:choose>
													<c:when test="${i==page}">
														<strong style="color: red;">${i}</strong>
													</c:when>
													<c:otherwise>
														<a class="pageLink" href="#" style="color: green;">${i}</a>
													</c:otherwise>
												</c:choose>
											</c:forEach>
										</div> <c:if test="${page<total}">
											<a class="page-right" id="rightNext" href="#" alt="${page+1}" /></a>
											<a class="page-far-right" id="rightLast" href="#"
												alt="${total}" /></a>
										</c:if></td>
								</tr>
							</tbody>

						</table>

					</c:if>
                            </form:form>
                            </div>
                            
         <!--End Form-->
        <!-- Foter -->
        <div class="clearfix"></div>
        <br /><br />
       
        <!-- End Footer -->

    </div>
    </div>
    </div>
    <!-- End Page Wrap -->
    <!-- JS -->
   

</body>
</html>