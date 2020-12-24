<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>
<style>
table th {
       padding:5px;
}

table {
       table-layout:auto;
}

.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
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
<%-- <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script> --%>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/FaultReport.js"></script>

       <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Search</li>
			  </ol>
			</nav>

 
 <div class="container">
	<div class="row">
        <div class="col-md-12">        
        	<div class="col-md-10 jumbotron offset-box">
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                        Search Criteria
                    </h2>

                    <!-- Contact Form -->
                    
                        	<c:url value="/faultReport/search" var="action"/>
                        	<input type="hidden" value="${pageContext.servletContext.contextPath}" id="context"/>
                            <form:form action="${action}" commandName="FRSearch" method="post" class="form contact-form" id="contact_form">
                                <div class="clearfix">
                                    <div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Fault Report ID</span>
                                            <form:input type="text" path="frId" id="fr_number" class="input-md round form-control" placeholder="Fault Report ID" />
                                        </div>
                                        
                                        	<%--<div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Reported Date</span>
	                                            <form:input type="text" path="fromDate" id="From_date" readonly="true" class="input-md round form-control" placeholder="From Date" pattern=".{5,100}" onclick="javascript: NewCssCal('From_date','ddMMyyyy')"/>
	                                            <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('From_date','ddMMyyyy')" style="cursor:pointer;width:30px" />
                                            </div>
                                            <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">To Date</span>
	                                            <form:input type="text" path="toDate" id="To_date" readonly="true" onclick="javascript: NewCssCal('To_date','ddMMyyyy')" class="input-md round form-control" placeholder="To Date" pattern=".{5,100}"/>
	                                            <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('To_date','ddMMyyyy')" style="cursor:pointer;width:30px" />
											</div> --%>
											
											<div class="form-group input-group">
	                                        		<span class="input-group-addon label-left" id="basic-addon2">Start Date</span>
		                                            <form:input type="text" path="fromDate" required="required" id="From_date" class="input-md round form-control" placeholder="Start Date" autocomplete="off"/>
	                                        </div>
	                                        <div class="form-group input-group">
	                                        		<span class="input-group-addon label-left" id="basic-addon2">End Date</span>
		                                            <form:input type="text" path="toDate" required="required" id="To_date" class="input-md round form-control" placeholder="End Date" autocomplete="off"/>
		                                    </div>
                                        
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Building</span>
                                            <form:select path="building" id="building" class="input-md round form-control">
                                            <form:option value="">Select any Building</form:option>
                                            <form:options items="${buildingList}" itemLabel="name" itemValue="id"/>
                                            </form:select>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Location</span>
                                            <form:select  path="location" id="location" class="input-md round form-control">
                                                <form:option value="">Select any Location</form:option>
                                                <form:options items="${locationList}" itemLabel="name" itemValue="id"/>
                                            </form:select>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Fault Category</span>
                                            <form:select path="faultCategory" class="input-md round form-control">
                                                <form:option value="">Select any Fault Category</form:option>
                                                <form:options items="${faultCategoryList}" itemLabel="name" itemValue="id"/>
                                            </form:select>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Choose Status*</span>
                                        	&nbsp;&nbsp;&nbsp;&nbsp;
                                            <form:checkbox path="status" value="Open" />&nbsp;<a class="a">Open</a>
                                            <form:checkbox path="status" value="Closed" />&nbsp;<a class="a">Closed</a>
                                            <form:checkbox path="status" value="Completed" />&nbsp;<a class="a">Completed</a>
                                            <form:checkbox path="status" value="Pause" />&nbsp;<a class="a">Pause</a>
                                        </div>
                                        <div class="clearfix">
                                            <div class="cf-left-col">
                                                <!-- Inform Tip -->
                                                <div class="form-tip pt-20">
                                                    <i class="fa fa-info-circle"></i> all fields with (*) are required.
                                                </div>
                                            </div>
                                            <br />
                                            <div class="form-group float-right">
                                                <input type="submit" value="Submit" class="submit_btn btn btn-mod btn-medium btn-round" />                                                
                                            </div>
                                        </div>
                                        </div>
                                    </div>
                              
                            </form:form>      
                                      <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                            Search Result
                                        </h2>
                                        <div class="table-responsive">
                                            <table class="table table-stripped table-hover" id="tblResult">
                                                <thead>

                                                        <th>FR ID</th>
                                                        <th>Activation Date</th>
                                                        <th>Fault Category</th>
                                                        <th>Requestor Name</th>
                                                        <th>Status</th>
                                                    
                                                </thead>
                                                <tbody>
                                                	<c:url value="/faultReport/update"  var="edit"/>
                                                	<c:forEach items="${faultReportList}"  var="faultReport">
                                                    <tr>
                                                    	<td><a href="${edit}/${faultReport.frId}">${faultReport.frId} <i class="fa fa-edit"> </i></a></td>
                                                    	<javatime:format value="${faultReport.activationTime}" var="repDate" pattern="dd-MM-yyyy"/>
                                                    	<td>${repDate}</td>
                                                    	<td>${faultReport.faultCategory.name }</td>
                                                    	<td>${faultReport.requestorName}</td>
                                                    	<td>${faultReport.status}</td>
                                                    </tr>
                                                    </c:forEach>
                                                </tbody>
                                                <td colspan="100%">
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
											 <td>
										 <ul class="pagination pagination-sm" style="margin-left: 36%;"  >		 
											 
											 <c:if test="${page>1}">

										<li>				<a class="page-far-left"
															href="<c:url value="/faultReport/pageno=1"/>"><<</a> </li>
											<li>			<a class="page-left"
														    href="<c:url value="/faultReport/pageno=${page-1}"/>"><</a>  </li>
													</c:if>
													
													
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																	<li class="active" ><a>${i}</a></li>
																</c:when>
																<c:otherwise>
														<li>			<a class=""
																		href="<c:url value="/faultReport/pageno=${i}"/>"
																		>${i}</a>  </li>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													 <c:if test="${page<total}">
												<li>		<a class="page-right" 
														 	href="<c:url value="/faultReport/pageno=${page+1}"/>">> </a> </li>
								<li>						<a class="page-far-right"
														    href="<c:url value="/faultReport/pageno=${total}"/>" >>></a> </li>
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
    <%--  <script src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.12.4.js"></script>
     <script src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-ui.js"></script>
     <script type="text/javascript">
     
    	$(document).ready(function() {
     		$("#From_date").datepicker({ dateFormat: 'dd-mm-yy' });
            $("#To_date").datepicker({ dateFormat: 'dd-mm-yy' });
            $("#Schedule_Date").datepicker({ minDate: 0, dateFormat: 'dd-mm-yy' });
            
            $("#From_date").change(function() { console.log($("#From_date").val());
            	 var fromDt = $("#From_date").val();
            	 jQuery("#To_date").datepicker("destroy");
            	 $("#To_date").datepicker({ minDate: fromDt, dateFormat: 'dd-mm-yy' });
            });
            $("#To_date").change(function() { console.log($("#To_date").val());
		       	 var toDt = $("#To_date").val();
		       	 jQuery("#From_date").datepicker("destroy");
		       	 $("#From_date").datepicker({ maxDate: toDt, dateFormat: 'dd-mm-yy' });
		    });
     	});

    </script> --%>
</body>
</html>
