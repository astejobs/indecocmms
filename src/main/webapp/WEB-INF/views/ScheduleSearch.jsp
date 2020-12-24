<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
table th {
       padding:5px;
}

table {
       table-layout:auto;
}

.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover 
{
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}

tr:nth-child(even) {
    background-color: #f9f9f9;
}

a{
    text-decoration:none;
}


</style>
<html>
<style> 
/* table th {
	white-space: nowrap;
	word-wrap: break-word;
    text-overflow: ellipsis;
	overflow: hidden; 
	max-width: 1px; 
	text-align: justify;
}
table td {
	white-space: nowrap;
	word-wrap: break-word;
    text-overflow: ellipsis;
	overflow: hidden; 
	max-width: 100px; 
	text-align: justify;
} */

</style>


        <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Schedule Search</li>
			  </ol>
			</nav>
 
<div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                        Search PM Schedule Details
                    </h2>

                    <!-- Contact Form -->
                   
						
						<c:url value="/schedule/search" var="actionUrl" />
                        	
                        
                            <form:form action="${actionUrl}" commandName="scheduleSearch" method="post" class="form contact-form" >
                                
                                	<div class="form-group">
	                     					<c:if test="${not empty success}">
	                                         <div class="success">${success}</div>
											</c:if>
											<c:if test="${not empty fail}">
	                                         <div class="error">${fail}</div>
	                                         </c:if>
                    					</div>
                                
                                <div class="clearfix">
                                    <div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">PM Schedule No.</span>
                                            <form:input type="text" path="schedule_number" class="input-md round form-control" placeholder="PM Schedule No" />
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">User Reference No.</span>
                                            <form:input type="text" path="UserReferenceNo" class="input-md round form-control" placeholder="User Ref No" />
                                        </div>
                                        
											<div class="form-group input-group">
									             <span class="input-group-addon label-left" id="basic-addon2">Start Date</span>
												<form:input type="text" path="startDate" id="From_date"
													class="input-md round form-control" placeholder="Start Date" autocomplete="off"/>
												<%-- <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png"
													onclick="javascript: NewCssCal('From_date','ddMMyyyy')"
													style="cursor: pointer; width: 30px" /> --%>
											</div>
											<div class="form-group input-group">
									             <span class="input-group-addon label-left" id="basic-addon2">End Date</span>
												<form:input type="text" path="endDate" id="To_date"
													class="input-md round form-control" placeholder="End Date"  autocomplete="off"/>
												<%-- <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png"
													onclick="javascript: NewCssCal('To_date','ddMMyyyy')"
													style="cursor: pointer; width: 30px" /> --%>
											</div>
										
                                        
                                        <div class="clearfix">
                                        
                                            <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Equipment.</span>
                                            <form:select id="equipment"  path="equipment"  class="input-md round form-control">
                                                <option>Select Equipment</option>
                                            </form:select>
                                        </div>
                                        
                                        <div class="form-group">
    	                                    <input type="button" onclick="SelectName()" value="Search Equipment" class="submit_btn btn btn-mod btn-medium btn-round" />
                                                 <script type="text/javascript">
                                                var popup;
                                                function SelectName() {
                                                popup = window.open("equipments/scheduled", "Popup", "width=700,height=550,left=300");
                                                popup.focus();
                                                return false
                                                }					
                                             </script>
                                        </div>
                                        
                                            <div class="cf-left-col">
                                                <!-- Inform Tip -->
                                                <div class="form-tip pt-20">
                                                    <i class="fa fa-info-circle"></i> all fields with (*) are required.
                                                </div>
                                            </div>
                                            <br />
                                            <div class="cf-right-col">
                                            
                                            <input type="hidden" value="1" name="p" id="p" />
                                            
                                                <div class="align-right pt-10">
                                                    <input type="submit" value="Submit" class="submit_btn btn btn-mod btn-medium btn-round" />
                                                </div>
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                              
                            </form:form>      
                                      <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            Search Result
                                        </h2>
                                        <div class="table-responsive">
                                            <table class="table table-stripped table-hover" id="tblResult">
                                                <thead>
 <tr>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="PM Schedule No.">PM Schedule No.</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="User Ref No.">User Ref No.</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="System">System</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Sub System">Sub System</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="frequency">frequency</th>
                                                    	<th data-toggle="tooltip" data-placement="bottom" title="Equipment">Equipment</th>
                                                    	<th data-toggle="tooltip" data-placement="bottom" title="Start Schedule Date">Start Schedule Date</th>
                                                    	<th data-toggle="tooltip" data-placement="bottom" title="End Date">End Date</th>
                                                    	<th data-toggle="tooltip" data-placement="bottom" title="Description">Description</th>
                                                    	
                                                    	</tr>
                                                </thead>
                                                <tbody>
                                                	<c:url value="/faultReport/update"  var="edit"/>
                                                	<c:forEach items="${pmschedules}"  var="schedule">
                             <tr>
                            <td data-toggle="tooltip" data-placement="bottom" title="PM Schedule No." > <a href="${pageContext.servletContext.contextPath}/schedule/${schedule.id}"><i class="fa fa-edit "></i></a>
								
									
									<span style="font-size: 15px; cursor: pointer;"><a
												title="Click to Delete" href="${pageContext.servletContext.contextPath}/schedule/delete/${schedule.id}"
												onclick="return confirm('Are you sure You want to DELETE this?')"><i class="fa fa-trash"></i></a></span> 
									
									<a href="${pageContext.servletContext.contextPath}/schedule/emailTask/${schedule.scheduleNumber}"><i class="fa fa-envelope "></i> </a>
									${schedule.scheduleNumber}
									</td>
									
									
										<td data-toggle="tooltip" data-placement="bottom" title="${schedule.userRefNumber}">${schedule.userRefNumber}</td>
									<td >
									<c:forEach items="${schedule.equipment}" var="equip">
									<p data-toggle="tooltip" data-placement="bottom" title="${equip.assetSubtype.assetType.assetTypeName}">${equip.assetSubtype.assetType.assetTypeName} </p><br>
									</c:forEach>
									</td>
									
									<td >
									<c:forEach items="${schedule.equipment}" var="equip">
									<p data-toggle="tooltip" data-placement="bottom" title="${equip.assetSubtype.assetSubTypeName}">${equip.assetSubtype.assetSubTypeName}</p><br>
									</c:forEach>
									</td>
									
									<td>
									<c:choose>
											<c:when test="${schedule.frequency == 1}">Daily</c:when>
											<c:when test="${schedule.frequency == 2}">Weekly</c:when>
											<c:when test="${schedule.frequency == 3}">Fortnightly</c:when>
											<c:when test="${schedule.frequency == 4}">Monthly</c:when>
											<c:when test="${schedule.frequency == 5}">Quarterly</c:when>
											<c:when test="${schedule.frequency == 6}">HalfYearly</c:when>
											<c:when test="${schedule.frequency == 7}">Yearly</c:when>
										</c:choose>
									</td>
									
									<td >
									<c:forEach items="${schedule.equipment}" var="equip">
									<p data-toggle="tooltip" data-placement="bottom" title="${equip.name}">${equip.name} </p><br>
									</c:forEach>
									</td>
									
									<td data-toggle="tooltip" data-placement="bottom" title="${schedule.startDate}">
									<fmt:formatDate value="${schedule.startDate}" pattern="dd-MM-yyyy" />
									</td>
									
									<td data-toggle="tooltip" data-placement="bottom" title="${schedule.endDate}">
									<fmt:formatDate value="${schedule.endDate}"	pattern="dd-MM-yyyy" />
									</td>
									
									<td data-toggle="tooltip" data-placement="bottom" title="${schedule.briefDescription}">${schedule.briefDescription}</td>
                         </tr>
                                        
                                        </c:forEach>
                                        
                                        
                          <tfoot align="center"> 
                             	  <tr>
                               		<td colspan="100%" >
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
									<table id="paging-table">
										<tbody>
											<tr>
											 <td>
											<ul class="pagination pagination-sm" style=" position: relative;left:337px">
											 <c:if test="${page>1}">
                                                
                                                 
													<li>	<a class="page-far-left"
															href="<c:url value="/schedule/pageno=1"/>"><<</a> </li>

										            <li>	<a class="page-left"
														   style="color:green; cursor:pointer; text-decoration:none;" href="<c:url value="/schedule/pageno=${page-1}"/>">&nbsp; < </a> </li>

													</c:if>
													
													 
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																	<li class="active" ><a>${i}</a></li>
																</c:when>
																<c:otherwise>
																<li><a href="<c:url value="/schedule/pageno=${i}"/>">${i}</a></li>
																	
																</c:otherwise>
															</c:choose>
														</c:forEach>
														
												
														 <c:if test="${page<total}">
											<li>				<a class="page-right" style="color:green;cursor:pointer;text-decoration:none;"
															 	href="<c:url value="/schedule/pageno=${page+1}"/>">&nbsp;> </a> </li>
	
											<li>				<a class="page-far-right"
														
																 href="<c:url value="/schedule/pageno=${total}"/>" >>></a> </li>
														
														</c:if>
														</ul>
														
													</td>
											

												</tr>
										</tbody>
										</table>
										</c:if>
										
									</td>
								</tr>
								</tfoot>
                                        
                                                               
                      </table>
                                        </div>
                       
                    <!-- End Contact Form -->
                </div>
	        </div>
		</div>	
		</div>
		
        <!--End Form-->
</body>
<%-- <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script> --%>

</html>
