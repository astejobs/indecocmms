<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
<body class="appear-animate">
        <!-- Head Section -->
        <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">DASHBOARD</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="../CMMS-Dashboard.html">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="../forms_iCMMS/configurator-civil-Level-3.html">Civil Level 3</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>

       <div style="height:100px;position:relative"></div>
  			<div class="container" style="width:100%">
           <div class="row">
              <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
           <jsp:include page="/WEB-INF/views/menu.jsp" />
             </div>
             <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                        Part Quantity Used
                    </h2>
					<c:url value="/inventory/reports/parttransaction" var="search"/>
                    
                            <form:form action="${search}" class="form contact-form" id="contact_form" modelAttribute="partTransactionSearch" method="post">
                                
                                <div class="form-group">

                                        <c:if test="${not empty success}">
                                         <div class="success">${success}</div>
										</c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
										
                   				</div>
                                    <div>
                                        <div class="form-group">
                                            <form:select required ="required" class="input-md round form-control" path="warehouseId" >
                                            <form:option value="-1">Select Warehouse</form:option>
                                           <form:options items="${warehouseList}" itemLabel="name" itemValue="id"/>
                                            </form:select>
                                                                                         
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" name="part-name" id="part-name" path="partName" class="input-md round form-control" placeholder="Part Name"/>
                                        </div>
                                        <div class="form-group">
                                        
                                            <form:input type="text" path="from" name="reported_date-from" id="date-from" style="width:40%" class="input-md round form-control" placeholder="Date From"/>&nbsp;&nbsp;&nbsp;
                                            <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('date-from','ddMMyyyy')" style="cursor:pointer;width:30px" />
                                            <form:input type="text" path="to" name="reported_date-to" id="date-to" style="width:40%" class="input-md round form-control" placeholder="Date To"/>
                                            <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('date-to','ddMMyyyy')" style="cursor:pointer;width:30px" />
                                        </div>
                                        <div class="form-group">
                                            <span>Choose Status*</span>&nbsp;&nbsp;&nbsp;&nbsp;
                                            <form:checkbox path="status" value="Issued" />&nbsp;<a class="a">Issue</a>
                                            <form:checkbox path="status" value="Returned" />&nbsp;<a class="a">Return</a>
                                            <form:checkbox path="status" value="Reserved" />&nbsp;<a class="a">Reserve</a>
                                            <form:checkbox path="status" value="Completed" />&nbsp;<a class="a">Completed</a>
                                        </div>
                                          <div class="form-group">
                              				<form:input type="text"  id="part-name" path="reportTaskId" class="input-md round form-control" placeholder="Task Id/Fault Report Id"/>
                                          </div>
                                        
                                        <div class="clearfix"></div><br>
                                        <div class="form-group">
                                            <input type="submit" id="submit" name="search" value="Submit" />
                                            <input type="submit" id="btnexport" name="_xls" value="Export to Excel" />
                                        </div>
                         
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Search Result
                                        </h2>
                                        <div class="form-group">
                                            <table class="table table-striped" id="tblResult">
                                                <thead>
                                                    <tr>
                                                        <th>Part Name</th>
                                                        <th>Part Description</th>
                                                        <th>Warehouse</th>
                                                        <th>Batch No.</th>
                                                       <th>Status</th>
                                                       <th>Issued Qty</th>
                                                       <th>Returned Qty</th>
                                                        <th>Quantity Used</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                   
                                                     <c:forEach items="${partQuantityList}" var="parts">
                                                    <tr>
                                                       	<td>${parts.partsInWarehouse.part.partType.partName}</td>
                                                        <td>${parts.partsInWarehouse.part.description}</td>
                                                        <td>${parts.partsInWarehouse.warehouse.name }</td>
                                                         <td>${parts.batchNo}</td>
                                                         <td>${parts.status}</td>
                                                       <td>${parts.issuedQuantity}</td>
                                                       <td>${parts.returnedQuantity}</td>
                                                          <c:set value="${parts.issuedQuantity}" var="Issueqty"></c:set>                      
                                                          <c:set value="${parts.returnedQuantity}" var="Returnedqty"></c:set>
                                                         <c:set value="${Issueqty-Returnedqty}" var="QuantityUsed"></c:set>
                                                          <td>${QuantityUsed}</td>
                                                        </tr>
                                                 </c:forEach>
<tfoot>
									
	                          
	                            </tbody>
	                            
	                       <tr>
	                               		
	                               		<td>
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
					<ul class="pagination pagination-sm" style=" position: relative; left:340px; ">							 
												 <c:if test="${page>1}">
	
												<li>			<a class="page-far-left"
																href="<c:url value="/inventory/reports/pageno=1"/>"><<</a>  </li>
	
		<li>	<a class="page-left"  href="<c:url value="/inventory/reports/pageno=${page-1}"/>">< </a> </li>
	
														</c:if>
														
														
															<c:forEach var="i" begin="${from}" end="${to}">
																
																<c:choose>
																	<c:when test="${i==page}">
																			<li class="active" ><a>${i}</a></li>
																	</c:when>
																	<c:otherwise>
								<li>	<a class=""href="<c:url value="/inventory/reports/pageno=${i}"/>">${i}</a> </li>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
	
														
														
	
															 <c:if test="${page<total}">
															<li>	<a class="page-right" 
																 	href="<c:url value="/inventory/reports/pageno=${page+1}"/>">>> </a> </li>
		
												<li>				<a class="page-far-right"
																	 href="<c:url value="/inventory/reports/pageno=${total}"/>" >></a>
															</c:if>
														
														</ul>
														</td>
	
													</tr>
													<tfoot>
											</tbody>
										</table>
										</c:if>
										</table>
								</div>
						</form:form>
					</div>
			</div>
	</div>
</body>
</html>