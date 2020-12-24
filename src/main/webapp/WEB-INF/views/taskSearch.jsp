<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="forms"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<html>
   
      <style>
/* table {
	table-layout: auto;
} */


.wid {
	display: inline-block;
	width: 40%;
}

.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}

.wi {
	display: inline-block;
	width: 10%;
	margin-bottom: -9px;
}
.table-responsive>.table>thead>tr>th {
    white-space: unset !important;
}
</style>

</head>


<body class="appear-animate">

    <!-- Page Loader -->
    <div class="page-loader">
        <div class="loader">Loading...</div>
    </div>

       <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Task Search</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
        <!---MENU-->

        <!--End Menu-->
        <!--Form-->
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                        Search PM Task Details
                    </h2>

                    <!-- Contact Form -->


				
					<c:url value="/task/search" var="pmSearchURL" />
					
						<forms:form  commandName="pmsCriteria"
							method="POST" action="${pmSearchURL}">
                        <div class="clearfix">
                            <div>
                                <div class="form-group input-group">
                                    <span class="input-group-addon label-left" id="basic-addon2">PM Schedule No.</span>
                                    <!--<input type="text" name="PM_Schedule_No" id="PM_Schedule_No" class="input-md round form-control" placeholder="PM Schedule No." pattern=".{3,100}">-->
                                       <forms:select path="schedule"
                                          id="value" name="value" 
                                           class="target input-md round form-control">
                                            <forms:option value="">Select PM Schedule No</forms:option>
                                            <forms:options items="${pmschedules}" itemValue="id"
                                            itemLabel="scheduleNumber"></forms:options>
                                        </forms:select>
                                </div>

                                <div class="form-group input-group">
                                    <span class="input-group-addon label-left" id="basic-addon2">PM Task No.</span>
                                    <forms:input type="text" path ="task_number"  name="PM_Task_No" id="PM_Task_No" class="input-md round form-control"   
                                    placeholder="PM Task No." pattern=".{3,100}" /> 
                                </div>
                                <div class="form-group">
                                   <forms:checkbox value="OPEN" path="status" />Open&nbsp;&nbsp;
                                   <forms:checkbox value="INPROGRESS" path="status" /> Inprogress&nbsp;&nbsp;
								   <forms:checkbox value="KIV" path="status" />KIV&nbsp;&nbsp;
								   <forms:checkbox value="NOTDUE" path="status" /> Not Due&nbsp;&nbsp;
								   <forms:checkbox value="CLOSED" path="status" />Closed
                                </div>

								<!-- <div class="row" style="margin:0px"> -->
	                                <div class="form-group input-group">
	                                    <span class="input-group-addon label-left" id="basic-addon2">Start Date</span>
	                                    <forms:input type="text" path="startDueDate" name="Start_date" id="From_date" class="input-md round form-control" placeholder="Start Date" />
	                                    <%-- <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('Start_date','ddMMyyyy')" style="cursor:pointer;width:30px" /> --%>
	                                 </div>
	                                 <div class="form-group input-group">
	                                    <span class="input-group-addon label-left" id="basic-addon2">End Date</span>
	                                    <forms:input type="text" path="endDueDate" name="End_date" id="To_date" class="input-md round form-control" placeholder="End Date" />
	                                    <%-- <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('End_date','ddMMyyyy')" style="cursor:pointer;width:30px" /> --%>
	                                </div>
                                <!-- </div> -->
                                
                                 
                                 <div class="form-group input-group">
	                                <span class="input-group-addon label-left" id="basic-addon2">Completed</span>
                                    <forms:select path = "elapse" class="input-md form-control" >
										<forms:option value="Within">Within</forms:option>
										<forms:option value="GreaterThan">Greater Than</forms:option>
										<forms:option value="EqualTo">Equal To</forms:option>
						             </forms:select> 
                                    
                                 </div> 
                                 
                                 <div class="row">
                                  <div class="form-group col-md-6" > 
                                   
                                     <forms:input type="text" path="value" name="PM_Schedule_No" 
                                     id="PM_Schedule_No" class="input-md round form-control"
                                      placeholder="" />
                                   </div>
                                
                                 <div class="form-group col-md-6"> 
                                   
                                      <forms:select path="slot" class="form-control input-md">
                                             
                                     	 <forms:option value="Mins">Min</forms:option> 
 										 <forms:option value="Hours">Hours</forms:option> 
 										 <forms:option value="Days">Days</forms:option> 
 										 <forms:option value="Months">Months</forms:option> 
                                    </forms:select>     
                                 </div> 
                                </div> 
                                 <br>
                                <div class="form-group input-group">
                                    <span class="input-group-addon label-left" id="basic-addon2">Select Equipment</span>
                                    <forms:select path="equipment" class="input-md round form-control">
                                        <forms:option value="">Select Equipment</forms:option>
								        <forms:option value=""></forms:option>
                                    </forms:select>
                                </div>
                                <div class="cf-right-col form-group">
                                
                                
                                    <div class="align-right pt-10">
                                        <input type="button" onclick="SelectName()" value="Select Equipment"
                                        class="submit_btn btn btn-mod btn-medium btn-round" id="select_equipment" />
                                        
                                     <script type="text/javascript">
                                     var popup;
                                     function SelectName(){
                                    	
                                    	 popup=window.open("equipment", "Popup", "width=700,height=550,left=300");
                                    	 popup.focus();
                                    	 return false;
                                     }
                                     
                                     
                                     
                                     </script>   
                                        
                                    </div>
                                    
                                    
                                    
                                </div>
                                <div class="clearfix"></div>
                                <br />
                                <div class="clearfix"> 
                                    <div class="cf-left-col">
                                        Inform Tip
                                        <div class="form-tip pt-20">
                                            <i class="fa fa-info-circle"></i> all fields with (*) are required.
                                        </div>
                                    </div>
                                    <br />
                                    <div class="cf-right-col">
                                        <div class="align-right pt-10">
                                            
                                            
                                            
                                            <input type="submit" name="search"  value="search"  id="submitbutton"
                                            class="submit_btn btn btn-mod btn-medium btn-round" />
                                          
                                        </div>
                                    </div> 

                                </div>
                            </div>
                        </div>
                        
                        </forms:form>
                        
                        
                        
                        
                        
                        
                        
                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                    Search Result
                                </h2>
                        <div class="table-responsive">
                            <table class="table table-stripped table-hover" id="tblResult">
                                <thead>
                                    <tr>
                                        <th>Task No.</th>
                                        <th>Equipment</th>
                                        <th>Actual Schedule Date</th>
                                        <th>Status</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${pmTasks}" var="task">
                                    <tr>
                                        <td>
                                        	<a href="${pageContext.servletContext.contextPath}/task/${task.id}">${task.task_number}</a>
                  						</td>
                                        <td>${task.equipment.name}</td>
                                        <td><fmt:formatDate value= "${task.actualScheduleDate}" pattern="dd-MM-yyyy" /></td>                                       
                                       
                                        <td>${task.status}</td>
                                      
                                    </tr>
                                      </c:forEach> 
                                </tbody>
                             
                            </table>
                        </div>


                    <!-- End Contact Form -->
 

						<c:if test="${1<total}">
									<c:choose>
										<c:when test="${total>10}">
											<div class="">
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
											 <td style="border-style:hidden;">
											  <ul class="pagination pagination-sm" style="margin-left: 36%;"  >
				<c:if test="${page>1}">

										<li>				<a class="page-far-left"
															href="<c:url value="/task/pageno=1"/>"><<</a> </li>

									<li>					<a class="page-left"
														    href="<c:url value="/task/pageno=${page-1}"/>"> < </a> </li>

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
     </div>
    </div>
   </div>
          
           

       
       