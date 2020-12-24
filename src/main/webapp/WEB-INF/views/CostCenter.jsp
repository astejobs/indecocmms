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



tbody tr:nth-child(odd) {
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
			    <li class="breadcrumb-item active" aria-current="page">Cost Center</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                     <c:url var="delete" value="/costCenter/delete"></c:url>
                         <form:form   class="form contact-form" id="contact_form" action="${delete}" >
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            Cost Center Details
                                        </h2>
                                        <div class="form-group">
	                                         <c:if test="${not empty success}">
	                                         <div class="success">${success}</div>
											 </c:if>
										</div>
										<div class="form-group">
											 <c:if test="${not empty fail}">
	                                         <div class="error">${fail}</div>
	                                         </c:if>
                                         </div>
                                         <div class="table-responsive">
                                            <table class="table table-stripped table-hover"  id="tblResult">
                                                <thead>
                                                    <tr>
                                                        <th style="width:25%"  ><input  id="checkall" type="checkbox" /></th>
                                                        <th style="width:25%">Cost Center ID</th>
                                                        <th style="width:25%">Cost Center Desc</th>
                                                        <th style="width:25%">Modify</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:url value="/costCenter/edit" var="editlink"/>
                                                <c:forEach items="${costCenterList}" var="list">
                                                    <tr>
                                                    	<td><input type="checkbox" value="${list.id}" name="id"/></td>
                                                    	<td>${list.costCenterName}</td>
                                                        <td>${list.costCenterDescription}</td>
                                                        
                                                        <td><a id="edit" href="${editlink}/${list.id }/${page}" style="cursor:pointer"><i class="fa fa-edit"></i></a></td>
                                                    </tr>
                                                  
                                                </c:forEach> 
 <tr> <td colspan="100%" style="background-color: #FFF;border-bottom: 1px solid #fff;""> <div class="form-group float-right"><input type="submit" id="submit" class="btn" value="Delete" /> </div></td> </tr>                                                 
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
				<tr style=" background-color: #FFF;">
														 <td>
								 <ul class="pagination pagination-sm pagination-sm-left" style=" position: relative;" >
												<c:if test="${page>1}">
			
															<li>		<a class="page-far-left"
																		href="<c:url value="/costCenter/pageno=1"/>"><<</a> </li>
															<li>		<a class="page-left"
																	   href="<c:url value="/costCenter/pageno=${page-1}"/>">&nbsp;<</a> </li>
																</c:if>
																<c:forEach var="i" begin="${from}" end="${to}">
																		
																		<c:choose>
																			<c:when test="${i==page}">
																					<li class="active" ><a>${i}</a></li>
																			</c:when>
																			<c:otherwise>
														<li>						<a class=""
																					href="<c:url value="/costCenter/pageno=${i}"/>"
																					>${i}</a>  </li>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																 <c:if test="${page<total}">
														<li>			<a class="page-right" 
																	 	href="<c:url value="/costCenter/pageno=${page+1}"/>">> </a> </li>
														<li>			<a class="page-far-right"
																	    href="<c:url value="/costCenter/pageno=${total}"/>">> ></a> </li>
																</c:if>
																</ul>
																</td>
																</tr>
																</tbody>
															</table>
														</c:if>
														</ul>
					                               		</td>
					                               	
					                               		</tr>
                            					   </tfoot>
                			                   </table>
    			                          </div>
                                        
                                          
                     			</form:form>
                     				      <%-- ${edit} --%>
                                          <c:choose>                                        
		                                        <c:when test="${not empty edit}">
														<c:set value="${pageContext.servletContext.contextPath}/costCenter/update" var="action"/>
														<c:set var="caption" value="Update" />
														 <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
				                                            Edit Cost Center 
				                                        </h2>
												</c:when>
												<c:otherwise>
													 <c:set value="${pageContext.servletContext.contextPath}/costCenter/add" var="action"/>
													 <c:set var="caption" value="Add" />
			                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
			                                          Add new Cost Center 
			                                        </h2>
												</c:otherwise>
											</c:choose>
                                     
                                      
                                            <form:form   class="form contact-form" id="contact_form" action="${action}" method="post"  modelAttribute="costCenter">
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Cost Center ID</span>
                                           <form:hidden path="id" value="${costCenter.id}"/> 
                                               <input type="hidden" name="p" value="${page}"/> 
                                           <form:input path="costCenterName"  id="costcenterid" class="input-md round form-control" placeholder="Cost Center ID"/>
                                        <form:errors class="err" path="costCenterName" />
                                        </div>
                                        
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Description</span>
                                            <form:textarea path="costCenterDescription" class="input-md round form-control" placeholder="Cost Center Description" ></form:textarea>
                                       <form:errors  class="err" path="costCenterDescription" />
                                        </div>
                                        <div class="form-group float-right">
                                            <input type="submit" id="submit" class="btn" value="${caption}" />
                                        </div>
                                   </form:form>
                    </div>
                    </div>
                    </div>
                    </div>
     <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/checkall.js"></script>   
        