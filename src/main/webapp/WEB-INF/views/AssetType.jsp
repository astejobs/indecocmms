 <!--Form-->
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
			    <li class="breadcrumb-item active" aria-current="page">Asset Type</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">

                       <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                           Asset Type Details
                               </h2>
                                        <c:if test="${not empty success}">
                                         <div class="success">${success}</div>
										</c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
                                        <div class="table-responsive">
                                            <table class="table table-striped table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>Asset Type</th>
                                                        <th>Actions</th>
                                                    </tr>
                                                    </thead>
                                                    <tr>
                                                    </tr>
                                              
                                                <tbody>
                                                    <c:url value="/assetType/edit" var="edit_link"></c:url>
                                                    <c:url value="/assetType/delete" var="delete_link"></c:url>
                                                	<c:forEach items="${assetTypeList}" var="list">
                                                    <tr>
                                                   <td> <c:out value="${list.assetTypeName}"></c:out></td>
                                                     <td><span style="font-size: 15px; cursor: pointer;"><a title="Click to Edit" href="${edit_link}/${list.id}/${page}"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;</span>
												     <span><a title="Click to Delete"	onclick="return confirm('Are you sure You want to DELETE this?')" href="${delete_link}/${list.id}"><i class="fa fa-trash"></i></a></span></td>
                                                    </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div>
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
								
				 							<ul class="pagination pagination-sm pagination-sm-left" style="position: relative;">
													<c:if test="${page>1}">
	
													<li>		<a class="page-far-left"
																href="<c:url value="/assetType/pageno=1"/>"><<</a>  </li>
													<li>		<a class="page-left"
																href="<c:url value="/assetType/pageno=${page-1}"/>"><</a>  </li>
														</c:if>
														
	
															<c:forEach var="i" begin="${from}" end="${to}">
																
																<c:choose>
																	<c:when test="${i==page}">
																	<li class="active" ><a>${i}</a></li>
																	</c:when>
																	<c:otherwise>
															<li>			<a 
																			href="<c:url value="/assetType/pageno=${i}"/>"
																			">${i}</a>  </li>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
														 <c:if test="${page<total}">
													<li>		<a class="page-right"
																href="<c:url value="/assetType/pageno=${page+1}"/>">></a>  </li>
													<li>		<a class="page-far-right"
																href="<c:url value="/assetType/pageno=${total}"/>" >>></a>   </li>
														</c:if>
														</ul>
													</td>
												</tr>
											</tbody>
	
										</table>
								</c:if>
                                  </div>
                                         <c:choose>
											<c:when test="${not empty edit}">
												<c:set value="/assetType/update" var="action"/>
												<c:set var="caption" value="Update" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
		                                            Edit Asset Type
		                                        </h2>
											</c:when>
											<c:otherwise>
												 <c:set value="/assetType/add" var="action"/>
												 <c:set var="caption" value="Add" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            		Add  Asset Type
                                        		</h2>
											</c:otherwise>
										</c:choose>
                          
                                         <c:url value="${action}" var="actionsubmit"/>
                                        <form:form   method="post" action="${actionsubmit}" modelAttribute="assetType" class="form contact-form" id="contact_form">
	                                        <div class="form-group input-group">
                                        		<span class="input-group-addon label-left" id="basic-addon2">Asset Type Name</span>
	                                        	<input type="hidden" name="p" value="${page}">
	                                        	<form:hidden path="id"/>
	                                            <form:input path="assetTypeName" id="assetname" class="input-md round form-control" placeholder="Asset Type Name"></form:input>
	                                       		<form:errors class="err" path="assetTypeName"/>	
	                                        </div>
	                                        <div class="form-group">
	                                            <input type="submit" class="btn" id="submit" value="${caption}" />
	                                        </div>
      				 </form:form>
      			</div>
       		</div>
     </div>
     </div>
       

        <!--End Form-->