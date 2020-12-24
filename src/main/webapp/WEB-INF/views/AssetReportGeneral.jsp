<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/EqCr.js"></script>
<script type="text/javascript">
$(document).ready(function() 
	{
	
	if( $('#eId').has('option').length > 0 ) {
		
		var id = $('#eId').val();
		 var url = "${pageContext.request.contextPath}/addproperty/getsubsystems/"+id;
		  
		  
	      $.get(url, function(data){
	         
	    	 
	         $('#sId').empty();
	          $('#sId').append('<option  value="-1" >Select sub System</option>');
	              $.each(data,function(k,v){
	              var option = $('<option/>');
	              option.attr({ 'value': v.id }).text(v.assetSubTypeName);
	              $('#sId').append(option);
	              
	              }); 
		
	});     
	}
	
	
	$( "#eId" ).change(function() {
	
	     var id = $(this).val();
	   
	     
	   
	  var url = "${pageContext.request.contextPath}/addproperty/getsubsystems/"+id;
	  
	  
      $.get(url, function(data){
         
    	 
         $('#sId').empty();
          $('#sId').append('<option  value="-1" >Select sub System</option>');
              $.each(data,function(k,v){
              var option = $('<option/>');
              option.attr({ 'value': v.id }).text(v.assetSubTypeName);
              $('#sId').append(option);
              
              }); 
	      
});
});     
		$( "#bId" ).change(function() {
			
		     var id = $(this).val();
		     var url = "${pageContext.request.contextPath}/equipment/locations/"+id;
			 $.get(url, function(data){
		      $('#loc').empty();
		          $('#loc').append('<option  value="-1" >Select Location</option>');
		              $.each(data,function(k,v){
			              var option = $('<option/>');
			              option.attr({ 'value': v.id }).text(v.name);
			              $('#loc').append(option);
		              }); 
			  });
		});
});
    
</script>
<style>
	/* table {
       table-layout:auto;
	} */
</style>

<body class="appear-animate">
        <!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Asset Report</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                     Asset Reports
                    </h2>

                    <!-- Contact Form -->
                    
                       
                       
                        <c:if test="${not empty success}">
                                 <div class="success">${success}</div>
						  </c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
                       
                       
                        	<c:url value="/asset/search" var="actionUrl"/>
                        
                            <form:form action="${actionUrl}" class="form contact-form" id="contact_form" commandName="equipmentSearch" method="post">
                            
                            <input type="hidden" value="${pageContext.request.contextPath}" id="contextPath"/>
                            <input type="hidden" value="assetReportGeneral" name="param"/>
                            <input type="hidden" value="" name="checker"/>
                            
                                <div class="clearfix">
                                    <div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Asset Type</span>
                                         
                                          <form:select  path="assetType" class="input-md round form-control" id="eId" required="required">
                                  <option value="">Select Asset  Type</option>
                                          
                                               <form:options items="${assetTypeList}" itemLabel="assetTypeName"
											itemValue="id" />
											</form:select>
                                            
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Asset Sub Type</span>
                                            <select name="assetSubtype" required class="input-md round form-control" id="sId">
                                                <option>Select Asset Sub Type</option>
                                            </select>
                                        </div>
                                       
                                        
										<div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Building</span>
											 <form:select path="building" data-bvalidator="required" class="input-md round form-control" id="bId">	                                            
	                                                <form:option value="-1">Select Building</form:option>
	                                                <form:options items="${buildingList}" itemValue="id" itemLabel="name"/>	                                                                                             
	                                         </form:select>
	                                   </div>
										
										
									</div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Location</span>
                                               <form:select path="location" data-bvalidator="required" id="loc" class="input-md round form-control">                                                     class="input-md round form-control" id="Location">
                                                 <form:option value="-1">Select Location</form:option>
                                                  <form:options items="${locationList}" itemLabel="name" itemValue="id"></form:options>
                                              </form:select>
                                            
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Equipment Name</span>
                                            <input type="text" name="name" id="service-area" class="input-md round form-control" placeholder="Equipment Name" pattern=".{3,100}">
                                        </div>
                                       
                                       <!--  </div> -->
                                        <div class="clearfix"></div><br />
                                        <div class="form-group float-right">
                                            <input type="submit" name="submit" id="submit" class="btn" value="Submit" />
                                            <input type="submit" id="btnexport" name="excel" class="btn" value="Export to Excel" />
                                        </div>
                          </form:form>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            Search Result
                                        </h2>
                                        <div class="table-responsive">
                                            <table class="table table-hover table-stripped ssh-table" id="tblResult">
                                                <thead>
                                                    <tr>
                                                         <th>Equipment Type</th>
                                                        <th>Equipment SubType</th>
                                                        <th>Equipment Name</th>
                                                        <th>Building</th>
                                                        <th>Location</th>
                                                        <th>Model</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr></tr>
                                                </tbody>
                                                
                                                <tfoot>
                                   <c:forEach  var="civilList" items="${listSearch}" >
                               <tr>
                               		 <td>${civilList.assetType.assetTypeName}</td>
                                <td>${civilList.assetSubtype.assetSubTypeName}</td>
                                <td>${civilList.name}</td>
                                <td>${civilList.building.name}</td>
                                <td>${civilList.location.name}</td>
                                <td>${civilList.modelNo}</td>                             		
                               		
                               		</tr>
                               		  </c:forEach>
                               		
                               		 <td> 
                               		
                               		
                               		
                               		 <c:if test="${1<total}">
									<c:choose>
										<c:when test="${total>10}">
											<div class="clearfix">
												Showing 10 pages out of ${total} pages.
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
											Showing ${total} page(s) out of ${total} page(s).
											<c:set var="from" value="1" />
											<c:set var="to" value="${total}" />
										</c:when>
									</c:choose>
									<table cellspacing="0" cellpadding="0" border="0"
										id="paging-table">
										<tbody>
											<tr>
											 <td><c:if test="${page>1}">

														<a class="page-far-left"
															href="<c:url value="/asset/pageno=1"/>"><img style="width:10px;height:12px" src="${pageContext.servletContext.contextPath}/resources/assets/images/pre.png"></a>

														<a class="page-left"
														   style="color:#fff;cursor:pointer;text-decoration:none;" href="<c:url value="/asset/pagenoo=${page-1}"/>">&nbsp; </a>

													</c:if>
													
													
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																	<strong  style="color:#11adc1 !important">${i}</strong>
																</c:when>
																<c:otherwise>
																	<a class=""
																		href="<c:url value="/asset/pageno=${i}"/>"
																		style="color:#c8cccd;text-decoration:none;font-size:15px">${i}</a>
																</c:otherwise>
															</c:choose>
														</c:forEach>

														
													 <c:if test="${page<total}">
														<a class="page-right"
															href="<c:url value="/asset/pageno=${page+1}"/>"></a>

														 <c:if test="${page<total}">
															<a class="page-right" style="color:#fff;cursor:pointer;text-decoration:none;"
															 	href="<c:url value="/asset/pageno=${page+1}"/>">&nbsp; </a>
	
															<a class="page-far-right"
																 href="<c:url value="/asset/pageno=${total}"/>" ><img style="width:10px;height:12px;top:-1px;position:relative" src="${pageContext.servletContext.contextPath}/resources/assets/images/next.png"></a>
														</c:if>
													</c:if>
													</td>

												</tr>
										</tbody>
									</table>
							</c:if></td> 
                               </tr>
                               </tfoot>
                                                
                                            </table>
                                        </div>
                                    </div>
                                    
                               
                    </div>
                   </div>
                   </div>
                   
                    <!-- End Contact Form -->
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40"></h2>
                   
           
    
	
        <!--End Form-->
        
<script src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/AssetReport.js"></script>
        
        </body>