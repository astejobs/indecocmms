 <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style> 
.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}
table{
table-layout:auto;
}

tr:nth-child(even) {
    background-color: #f9f9f9;
}

.pagination {
    display: inline-block;
    padding-left: 0;
    margin: -24px 0;
    border-radius: 4px;
}


</style>

      <!-- Head Section -->
       <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Technician</li>
			  </ol>
			</nav>

    <!-- End Head Section -->
    
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                        Add new Technician
                    </h2> 
                                  <form:form class="form contact-form" id="contact_form" action="" method="post" modelAttribute="technician">
                                  		<div class="form-group">
	                     					<c:if test="${not empty success}">
	                                         <div class="success">${success}</div>
											</c:if>
											<c:if test="${not empty fail}">
	                                         <div class="error">${fail}</div>
	                                         </c:if>
                    					</div>
                                            <table class="table table-stripped">
                                                <thead>
                                                    <tr>
                                                        <th>Technician Name</th>
                                                        <th>Designation</th>
                                                        <th>Company Name</th>
                                                        <th>Designation</th>
                                                        <th >Email</th>
                                                        <th >Address</th>
                                                        <th>Status</th>
                                                        <th>Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:url value="/technician/edit" var="edit_link"></c:url>
                                                             <c:url value="/technician/delete" var="delete_link"></c:url>
		                                                    <c:forEach  items="${technicianList}" var="list">
		                                                    <tr>
		                                                    <td>
		                                                    <c:out value="${list.technicianName}"></c:out>
		                                                    </td>
		                                                    <td> <c:out value="${list.designation}"></c:out>
		                                                    </td>
		                                                     <td><c:out value="${list.companyName}"></c:out></td>
		                                                     <td><c:out value="${list.mobileNumber}"></c:out></td>
		                                                     <td><c:out value="${list.email}"></c:out></td>
		                                                      <td><c:out value="${list.address}"></c:out></td>
		                                                      <td><c:out value="${list.status}"></c:out></td>
		                                                      <td><span style="font-size: 15px; cursor: pointer;"><a title="Click to Edit" href="${edit_link}/${list.id}/${page}"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;</span>
														       <span><a title="Click to Delete"	onclick="return confirm('Are you sure You want to DELETE this?')" href="${delete_link}/${list.id}"><i class="fa fa-trash"></i></a></span></td>
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
										<ul class="pagination pagination-sm" style="position: relative;left: 350px; ">					 
														 <c:if test="${page>1}">
			            										<li>				<a class="page-far-left"
																		href="<c:url value="/technician/pageno=1"/>"><<</a> </li>
											<li>						<a class="page-left"
																	    href="<c:url value="/technician/pageno=${page-1}"/>"><</a> </li>
																</c:if>
																
																
																	<c:forEach var="i" begin="${from}" end="${to}">
																		
																		<c:choose>
																			<c:when test="${i==page}">
																		<li class="active" ><a>${i}</a></li>
																			</c:when>
																			<c:otherwise>
														<li>						<a class=""
																					href="<c:url value="/technician/pageno=${i}"/>"
																					>${i}</a>  </li>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																 <c:if test="${page<total}">
															<li>		<a class="page-right" 
																	 	href="<c:url value="/technician/pageno=${page+1}"/>">> </a>  </li>
													<li>				<a class="page-far-right"
																	    href="<c:url value="/technician/pageno=${total}"/>" >>></a> </li>
																</c:if>
														</ul>		
																</td>
															</tr>
													</tbody>
			
												</table>
			
											</c:if>
			                       
			                               		</td>
			                               		<td></td>
			                               </tr>
			                               </tfoot>         
                                         </table>
                                         </form:form>
                                   
                                        <c:choose>
                                        <c:when test="${not empty edit}">
												<c:set value="/technician/update" var="action"/>
												<c:set var="caption" value="Update" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
		                                            Edit Technician
		                                        </h2>
											</c:when>
											<c:otherwise>
												 <c:url value="/technician/add" var="action"/>
												 <c:set var="caption" value="Add" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            		Add  Technician
                                        		</h2>
											</c:otherwise>
											</c:choose>
									
                            <form:form class="form contact-form" id="contact_form" action="${action}" method="post" modelAttribute="technician">
                                                    <input type="hidden" name="id" value="${technician.id}">
                                                      <input type="hidden" name="p" value="${page}"/> 
                                <div class="clearfix">
                                    <div>
                                        <div class="form-group input-group">
                						    <span class="input-group-addon label-left" id="basic-addon2">Technician Name</span>
                                            <form:input path="technicianName" name="technician_name" id="technician_name" class="input-md round form-control" placeholder="Technician Name" />
                                       		<form:errors path="technicianName" class="err"></form:errors>
                                        </div>
                                        <div class="form-group input-group">
                						   <span class="input-group-addon label-left" id="basic-addon2">Designation</span>
                                           <form:input path="designation" name="designation" id="designation" class="input-md round form-control" placeholder="Designation" />
                                           <form:errors path="designation" class="err"></form:errors>
                                        </div>
                                        <div class="form-group input-group">
                						    <span class="input-group-addon label-left" id="basic-addon2">Company Name</span>
                                            <form:input path="companyName" name="companyname" id="companyname" class="input-md round form-control" placeholder="Company Name" />
                                           <form:errors path="companyName" class="err"></form:errors>
                                        </div>
                                        <div class="form-group input-group">
                						    <span class="input-group-addon label-left" id="basic-addon2">Mobile Number</span>
                                           <form:input path="mobileNumber" name="mobilenumber" id="mobilenumber" class="input-md round form-control" placeholder="Mobile Number" />
                                           <form:errors path="mobileNumber" class="err"></form:errors>
                                        </div>
                                        
                                        <div class="form-group input-group">
                						    <span class="input-group-addon label-left" id="basic-addon2">Email</span>
                                        <input type="text" name="email" class="input-md round form-control" placeholder="Email " id="email"/>
                                        <form:errors path="email" class="err"></form:errors>
                                        </div>
                                        
                                        <div class="form-group input-group">
                						    <span class="input-group-addon label-left" id="basic-addon2">Address</span>
                                            <form:textarea path="address" class="input-md round form-control" placeholder="Address" ></form:textarea>
                                           <form:errors path="address" class="err"></form:errors>
                                        </div>
                                        <div class="form-group input-group">
                						    <span class="input-group-addon label-left" id="basic-addon2">Status</span>
                                            <form:select path="status" class="input-md round form-control">
                                                <form:option value="">Select Status</form:option>
                                                    <form:option value="Active">Active</form:option>
                                                        <form:option value="Inactive">Inactive</form:option>
                                            </form:select>
                                                <form:errors path="status" class="err"></form:errors>
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" id="submit" class="btn" value="${caption}"/>
                                        </div>
                                    </div>
                                </div>
                           </form:form>
                        </div>
                    </div>

										
                          
                   
                    <!-- End Contact Form -->
                </div>
           
        </div>
       

        <!--End Form-->