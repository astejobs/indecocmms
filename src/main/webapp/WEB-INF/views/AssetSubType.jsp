<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style> 
.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}


tr:last-child {
    border-bottom: 1px solid #ddd;
}

.pagination {
    display: inline-block;
    padding-left: 0;
    margin: -24px 0;
    border-radius: 4px;
}

</style>

<body class="appear-animate">
   <!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Asset Sub Type</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                      <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                            Asset SubType Details
                      </h2>
					                   <c:if test="${not empty success}">
					                    <div class="success">${success}</div>
					                   </c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
                  					 <div class="table-responsive">
                                         <form:form   method="post" action="" modelAttribute="assetSubtype" class="form contact-form" id="contact_form">
                                            <table class="table table-striped table-hover">
                                                <thead>
                                                    <tr>
                                                        <th>Asset Type</th>
                                                        <th>Asset Sub Type</th>
                                                        <th>Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:url value="/assetSubtype/edit" var="edit_link"></c:url>
                                                               <c:url value="/assetSubtype/delete" var="delete_link"></c:url>

                                                    <c:forEach  items="${assetSubtypeList}" var="list">
                                                    <tr>
                                                    <td>
                                                    <c:out value="${list.assetType.assetTypeName}"></c:out>
                                                    </td>
                                                    <td> <c:out value="${list.assetSubTypeName}"></c:out>
                                                    </td>
                                                    <td><span style="font-size: 15px; cursor: pointer;"><a title="Click to Edit" href="${edit_link}/${list.id}/${page}"><i class="fa fa-edit"></i></a>&nbsp;&nbsp;</span>
												<span><a title="Click to Delete"	onclick="return confirm('Are you sure You want to DELETE this?')" href="${delete_link}/${list.id}"><i class="fa fa-trash"></i></a></span></td>
                                                     </tr>
                                                    </c:forEach>
                                                </tbody>

                                                  <tfoot>
                               
                               </tfoot>
                                  </table>
                                      </form:form>
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
					<ul class="pagination pagination-sm" style=" position: relative; left: 400px; ">		
												<c:if test="${page>1}">

											<li>			<a class="page-far-left"
															href="<c:url value="/assetSubtype/pageno=1"/>"></a>   </li>
										<li>				<a class="page-left"
														    href="<c:url value="/assetSubtype/pageno=${page-1}"/>"></a> </li>
													</c:if>
													
													
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																		<li class="active" ><a>${i}</a></li>
																</c:when>
																<c:otherwise>
														<li>			<a class=""
																		href="<c:url value="/assetSubtype/pageno=${i}"/>"
																		>${i}</a>  </li>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													 <c:if test="${page<total}">
												<li>		<a class="page-right" 
														 	href="<c:url value="/assetSubtype/pageno=${page+1}"/>">&nbsp;> </a>  </li>
										<li>				<a class="page-far-right"
														    href="<c:url value="/assetSubtype/pageno=${total}"/>" ></a> </li>
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
												<c:set value="/assetSubtype/update" var="action"/>
												<c:set var="caption" value="Update" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
		                                            Edit Asset SubType
		                                        </h2>
											</c:when>
											<c:otherwise>
												 <c:set value="/assetSubtype/add" var="action"/>
												 <c:set var="caption" value="Add" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            		Add  Asset SubType
                                        		</h2>
											</c:otherwise>
											</c:choose>
										 <c:url value="${action}" var="actionsubmit"></c:url>
                                        <form:form method="post" action="${actionsubmit}" modelAttribute="assetSubtype" class="form contact-form" id="contact_form"  >
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Asset Type</span>
												<input type="hidden" name="p" value="${page}">
	                                        	<form:hidden path="id"/>  
                                               <form:select  path="assetType" class="input-md round form-control">
                                        			<form:option value="">Select Asset Type</form:option>
                                     				 <form:options items="${assetTypeList}" itemValue="id" itemLabel="assetTypeName"/>
                                           </form:select>
                                           <form:errors class="err" path="assetType"/>
                                      	</div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Asset Sub Type</span>
                                           <form:input path="assetSubTypeName"  id="assetsubtypename" class="input-md round form-control" placeholder="Asset Sub Type"></form:input>
                                       	   <form:errors class="err" path="assetSubTypeName"/>	
                                        </div>
                                        <div class="form-group float-right">
                                            <input type="submit" id="submit" class="btn" value="${caption}" />
                                  </div>
                        </form:form>
                   </div>
              </div>
  		 </div>
  		 </div>
   </body>
   