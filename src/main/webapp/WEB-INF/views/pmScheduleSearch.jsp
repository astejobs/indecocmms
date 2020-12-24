<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<body class="appear-animate">
<style> 
.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}

.bac
{
          background-color: #FFF;
          border-top: 0px solid #FFF;
}

table{
        table-layout:auto;
}

.pagination {
    display: inline-block;
    padding-left: 0;
    margin: -24px 0;
    border-radius: 4px;
}




</style>

        <!-- End Navigation panel -->
        <!-- Head Section -->
        <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">PM Schedule Search</li>
			  </ol>
			</nav>
        
<div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                        PM Report
                    </h2>
                            <form:form class="form contact-form" id="contact_form" method="post" commandName="pmsCriteria">
                                         
                                         <div class="form-group input-group">
                                        	 <span class="input-group-addon label-left" id="basic-addon2">PM Schedule No.</span>
				                             <form:select path="schedule" class="form-control input-md round">
												<form:option value="">Select PM Schedule Number</form:option>
												<form:options items="${pmschedules}" itemLabel="scheduleNumber"	itemValue="id"></form:options>
											 </form:select>
                                        </div>
                                        
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">PM Task No.</span>
                                         	<form:input type="text" path="task_number" id="pm_task_No" class="input-md round form-control" placeholder="PM Task Number"/>
                                        </div>
                                        
                                        <div class="form-group">
                                            <form:checkbox value="OPEN" path="status"/>&nbsp;Open &nbsp;&nbsp;
                                            <form:checkbox value="INPROGRESS" path="status"/>&nbsp;In progress &nbsp;&nbsp;
                                            <form:checkbox value="KIV" path="status"/>&nbsp;KIV &nbsp;&nbsp;
                                            <form:checkbox value="CLOSED" path="status"/>&nbsp;Closed
                                        </div>
                                       
                                        
	                                        <div class="form-group input-group">
	                                        	<span class="input-group-addon label-left" id="basic-addon2">From Date</span>
	                                            <form:input type="text" path="startDueDate"  id="From_date" class="input-md round form-control" placeholder="Due Date From" autocomplete="off" />
	                                            <%-- <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('due_date-from')" style="cursor:pointer;width:30px" /> --%>
	                                         </div>
	                                         <div class="form-group input-group">
	                                        	<span class="input-group-addon label-left" id="basic-addon2">To Date</span>
	                                            <input type="text" path="endDueDate" id="To_date" class="input-md round form-control" placeholder="Due Date To" autocomplete="off" />
	                                            <%-- <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('due_date-to')" style="cursor:pointer;width:30px" /> --%>
	                                        </div>
	                                    
	                                  <!-- <div class="row"> -->
	                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Completed</span>                                           
	                                            <form:select path = "elapse" class="form-control">
	                                            <form:option value="Within">Within</form:option>
												<form:option value="GreaterThan">Greater Than</form:option>
												<form:option value="EqualTo">Equal To</form:option>
	                                            </form:select>	                                            
	                                         </div>
	                                         <div class="form-group col-md-6">
	                                            <form:input  path="value" type="text" class="form-control" />
	                                         </div>
	                                            
	                                       <div class="form-group col-md-6">     
		                                     <form:select class="form-control" path="slot">
												<option value="Mins">Min</option>
												<option  value="Hours">Hours</option>
												<option  value="Days">Days</option>
												<option  value="Months">Months</option>
											 </form:select>   
	                                       </div>
	                                  <!--  </div> -->
                                        
                                       
                                        <div class="form-group input-group">
		             						<span class="input-group-addon label-left" id="basic-addon2">Select Equipment</span> 
                                            <form:select id="equipment"  path="equipment"  class="input-md round form-control">
                                                <option>Select Equipment</option>
                                            </form:select>
                                        </div>
                                        <div class="form-group">
    	                                    <input type="button" onclick="SelectName()" value="Search Equipment" class="submit_btn btn btn-mod btn-medium btn-round" />
                                                 <script type="text/javascript">
                                                var popup;
                                                function SelectName() 
                                                {
                                                popup = window.open("equipments/scheduled", "Popup", "width=700,height=550,left=300");
                                                popup.focus();
                                                return false
                                                }
                                             </script>
                                        </div>
                                        
                                        
                       
                                
            
                                <!-- <div class="clearfix"></div>
                                <br /> -->
                                
                                
                                <input type="hidden" value="1" name="p" id="p" />

                                <div class="form-group float-right">
                                    <input type="submit" id="submit" class="btn" value="Submit" name="_search"/>
                                    <input type="submit" id="btnexport" class="btn" value="Export to Excel" name ="_xls"/>
                                </div>
                                <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                    Search Result
                                </h2>
                                <div class="table-responsive" style="clear:both">
                                    <table class="table table-stripped table-hover" id="tblResult">
                                        <thead>
                                            <tr>
                                                <th>PM Task No.</th>
                                                <th>Equipment</th>
                                                <th>Actual Schedule Date</th>
                                                <th>Schedule Date</th>
                                                <th>Status</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            
                                            
                             <c:forEach items="${pmTasks}" var="task">
								<tr>
									<td><a
										href="${pageContext.servletContext.contextPath}/task/${task.id}">${task.task_number}</a>
									</td>
									<td>${task.equipment.name}</td>
									<td><fmt:formatDate value="${task.actualScheduleDate}"
											pattern="dd-MM-yyyy" /></td>
									<td><fmt:formatDate value="${task.scheduleDate}"
											pattern="dd-MM-yyyy" /></td>
									<td>${task.status}</td>
								</tr>

							</c:forEach>
                                           
                                           <tr>
                                           
                                           <td></td><td></td><td></td><td></td>
                                           
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
									</tbody>
									</table>
									<table cellspacing="0" cellpadding="0" border="0"
										id="paging-table">
										<tbody>
											<tr>
											 <td style="border-style:hidden;">
											  <ul class="pagination pagination-sm" style="margin-left: 36%;">
												<c:if test="${page>1}">
												<li>
													<a class="page-far-left"
													href="<c:url value="/task/pageno=1"/>"></a> 
												</li>
												<li>	
													<a class="page-left"
												    href="<c:url value="/task/pageno=${page-1}"/>"> </a> 
												</li>

													</c:if>
													
													
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																		<li class="active" ><a>${i}</a></li>
																</c:when>
																<c:otherwise>
									<li>								<a class=""
																		href="<c:url value="/task/pageno=${i}"/>"
																		>${i}</a> </li>
																</c:otherwise>
															</c:choose>
														</c:forEach>

													
													    <c:if test="${page<total}">
									

														 <c:if test="${page<total}">
												<li>			<a class="page-right" 
															 	href="<c:url value="/task/pageno=${page+1}"/>">> </a>  </li>
	
									<li>						<a class="page-far-right"
																 href="<c:url value="/task/pageno=${total}"/>" >>></a> </li>
														</c:if>
													</c:if>
												
												
                                           </ul>
                                           </td>
                                           
                                           </tr> 
                                           
                                        </tbody>
                                    </table>
                                    </c:if>
                                </div>
                            </form:form>
                        </div>
                        <div id="result"></div>
                    </div>
                </div>
        </div>