<!DOCTYPE html>
<html>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%-- <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script> --%>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/FaultReport.js"></script>
        <!-- Head Section -->
        <style>
   table{
      table-layout:auto;
   }

table th{
     padding:5px;
}

table td {
     padding:5px;
}

tr:nth-child(even) {
    background-color: #f9f9f9;
}
 
.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}

</style>
        <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Search</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
        <!---MENU-->
  
        <div class="container">
<div class="row">
        <div class="col-md-12">
        
        <div class="col-md-10 jumbotron offset-box">

                    
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                        Download Report
                    </h2>

                    <!-- Contact Form -->
                   
                       <input type="hidden" value="${pageContext.servletContext.contextPath}" id="context"/>
                    	<c:url value="/faultReport/report/search" var="report"/>
                            <form:form action="${report}" commandName="FRSearchExcel" method="post" class="form contact-form" id="contact_form">
                                <div class="clearfix">
                                    <div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">FR Number</span>
                                            <form:input type="text" path="frId" id="frnumber" class="input-md round form-control" placeholder="FR Number*"/>
                                        </div>
                                        <div class="form-group">
                                            <span>Choose Status*</span>&nbsp;&nbsp;&nbsp;&nbsp;
                                            <form:checkbox path="status" value="Open" />&nbsp;<a class="a">Open</a>
                                            <form:checkbox path="status" value="Closed" />&nbsp;<a class="a">Closed</a>
                                            <form:checkbox path="status" value="InProgress" />&nbsp;<a class="a">In Progress</a>
                                            <form:checkbox path="status" value="KIV" />&nbsp;<a class="a">KIV</a>
                                        </div>
                                        
	                                        <div class="form-group input-group">
	                                        	<span class="input-group-addon label-left" id="basic-addon2">From Date</span>
	                                            <form:input type="text" path="fromDate" id="From_date" readonly="true" class="input-md round form-control" placeholder="From Date" pattern=".{5,100}"/>
	                                            <%-- <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('From_date','ddMMyyyy')" style="cursor:pointer;width:30px" /> --%>
	                                        </div>
	                                        <div class="form-group input-group">
	                                        	<span class="input-group-addon label-left" id="basic-addon2">To Date</span>
	                                            <form:input type="text" path="toDate" id="To_date" readonly="true" class="input-md round form-control" placeholder="To Date" pattern=".{5,100}"/>
	                                            <%-- <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('To_date','ddMMyyyy')" style="cursor:pointer;width:30px" /> --%>
											</div>
										
                                        <div class="form-group input-group">
	                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Equipment</span>
                                            <form:select path="equipment" class="input-md round form-control">
                                                <form:option value="">Select any Equipment</form:option>
                                                <form:options items="${equipmentList}" itemLabel="name" itemValue="id"/>
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
	                                        	<span class="input-group-addon label-left" id="basic-addon2">Maintenance Category</span>
                                            <form:select path="maintGrp" class="input-md round form-control">
		                                        <form:option value="">Select any Maintenance Category*</form:option>
		                                        <form:options items="${maintenanceGroupList}" itemLabel="name" itemValue="id"/>
		                                    </form:select>
                                        </div>
                                        <div class="form-group input-group">
	                                        	<span class="input-group-addon label-left" id="basic-addon2">Select building</span>
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
                                        <div class="form-group">
                                            <input type="radio" name="groupBy" value="maintenanceGroup"/>&nbsp;Maintenance Group &nbsp;&nbsp;
                                            <input type="radio" name="groupBy" value="faultCategory"/>&nbsp;Fault Category Group &nbsp;&nbsp;
                         					<input type="radio" name="groupBy" value="none" checked="checked"/>&nbsp;None&nbsp;&nbsp;                      
                                        </div>
                                        <div class="form-group float-right">
                                            <input type="submit" id="submit" name="view" value="Submit"  class="btn"/>
                                            <input type="submit" id="btnexport" name="excel" value="Export to Excel" class="btn"/>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                            <div class="table-responsive">
                            <table class="table table-stripped table-hover" id="tblResult" style="border-bottom:1px dashed #808080">
                                                <thead>
                                                    <tr>
                                                        <th>FR.No.</th>
                                                        <th>Activation Time</th>
                                                        <th>Fault Category</th>
                                                        <th>Requester Name</th>
                                                        <th>Maintenance Group</th>
                                                        <th>Status</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                	<c:url value="/faultReport/update"  var="edit"/>
                                                	<c:forEach items="${faultReportList}"  var="faultReport">
                                                    <tr>
                                                    	<td>${faultReport.frId}<a href="${edit}/${faultReport.frId}"><i class="fa fa-edit" style="margin-left:3px;" ></i></a></td>
                                                 <fmt:parseDate value="${faultReport.activationTime}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />

	<fmt:formatDate value="${parsedDate}" var="repDate" type="date" pattern="dd-MM-yyyy" />   

                                                    	<td>${repDate}</td>
                                                    	<td>${faultReport.faultCategory.name }</td>
                                                    	<td>${faultReport.requestorName}</td>
                                                    	<td>${faultReport.maintGrp.name}</td>
                                                    	<td>${faultReport.status}</td>
                                                    </tr>
                                                    </c:forEach>
                                                </tbody>
                                                <tfoot>
                                                <tr>
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
																<ul class="pagination pagination-sm" style="margin-left:37%; margin-bottom:44px;">	 
																 <c:if test="${page>1}">
					
																<li>			<a class="page-far-left"
																				href="<c:url value="/faultReport/pageno=1"/>"><<</a> </li>
																<li>			<a class="page-left"
																			  href="<c:url value="/faultReport/pageno=${page-1}"/>"><</a> </li>
																		</c:if>
																		
																		
																			<c:forEach var="i" begin="${from}" end="${to}">
																				
																				<c:choose>
																					<c:when test="${i==page}">
																					<li class="active" ><a>${i}</a></li>
																					</c:when>
																					<c:otherwise>
																	<li>					<a class=""
																							href="<c:url value="/faultReport/pageno=${i}"/>"
																							>${i}</a>   </li>
																					</c:otherwise> 
																				</c:choose>
																			</c:forEach>
																		 <c:if test="${page<total}">
																	<li>		<a class="page-right" 
																			 	href="<c:url value="/faultReport/pageno=${page+1}"/>">> </a>  </li>
															<li>				<a class="page-far-right"
																			    href="<c:url value="/faultReport/pageno=${total}"/>" >>></a> <li>
																		</c:if>
																		</ul>
																		</td>
																		</tr>
																		
																
															</tbody>
														
														</table>
													
													</c:if>
               		                  			 </table>  
               		                  			 </div>                            
                            
                        </div>
                        
                        
                    </div>
                </div>
              </div>
                <!-- End Contact Form -->
			
			        <!--End Form-->

</body>
</html>
