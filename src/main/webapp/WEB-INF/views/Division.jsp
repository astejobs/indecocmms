<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

   

<body class="appear-animate">
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

.bac{
          background-color: #FFF;
          border-top: 0px solid #FFF;
}
table{
     table-layout:auto;
}


</style>
   		<!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Division</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
   
<div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                         			<h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            Division Details
                                    </h2>
                                       
                                    <c:url value="/division/delete" var="delete"/>
                          			<form:form action="${delete}" class="form contact-form" id="contact_form">
                        				<c:if test="${not empty success}">
                                         <div class="success">${success}</div>
										</c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>  
                                
                                        <div class="table-responsive">
                                       
                                            <table class="table table-striped table-hover" id="tblResult">
                                                <thead>
                                                    <tr>
	                                                    <th><input  id="checkall" type="checkbox" /></th>
                                                        <th>Division ID</th>
                                                        <th>Division Desc</th>
                                                        <th>Modify</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                     <c:url value="/division/edit" var="edit_link"></c:url>
				           							 <c:forEach items="${divisionList}" var="list">
										             <tr>
                                            			 <td><input type="checkbox" value="${list.id}" name="id"/></td>                                                        
                                                	     <td>${list.name}</td>
                                                    	 <td>${list.description}</td>
                                                         <td><a id="edit" href="${edit_link}/${list.id}/${page}" style="cursor:pointer"><i class="fa fa-edit"> </i></a></td>
                                                    </tr>
                                                    </c:forEach>
 <tr><td colspan="100%" class="bac" style="border-bottom: 1px solid #FFF"><div class="form-group"><input type="submit" id="submit" class="btn" value="Delete" /> </div></td> </tr>
												</tbody>
										     <tfoot>
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
												<table cellspacing="0" cellpadding="0" border="0"
													id="paging-table">
													<tbody>
														<tr>
														 <td>
								  <ul class="pagination pagination-sm pagination-sm-left" style=" position: relative;" >
														
														 <c:if test="${page>1}">
			
																<li>	<a class="page-far-left"
																		href="<c:url value="/division/pageno=1"/>"><<</a> </li>
															<li>		<a class="page-left"
																	    href="<c:url value="/division/pageno=${page-1}"/>">&nbsp;<</a> </li>
																</c:if>
																
																
																	<c:forEach var="i" begin="${from}" end="${to}">
																		
																		<c:choose>
																			<c:when test="${i==page}">
																					<li class="active" ><a>${i}</a></li>
																			</c:when>
																			<c:otherwise>
																	<li>			<a class=""
																					href="<c:url value="/division/pageno=${i}"/>"
																					>${i}</a> </li>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																 <c:if test="${page<total}">
															<li>		<a class="page-right" 
																	 	href="<c:url value="/division/pageno=${page+1}"/>">> </a> </li>
															<li>		<a class="page-far-right"
																	    href="<c:url value="/division/pageno=${total}"/>" >>></a> </li>
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
										
                                       </form:form> 
                                       <div> 
    									 <c:choose>
 											<c:when test="${not empty edit}">
												<c:set value="/division/update" var="action"/>
												<c:set var="caption" value="Update" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
		                                            Edit Division
		                                        </h2>
											</c:when>
											<c:otherwise>
												 <c:set value="/division/add" var="action"/>
												 <c:set var="caption" value="Add" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            		Add Division
                                        		</h2>
											</c:otherwise>
										</c:choose>
   
                                       <c:url value="${action}" var="submit"/>
	                                   <form:form   method="post" action="${submit}" commandName="division" class="form contact-form" id="contact_form">
                                      	<form:hidden path="id"/>
	                                      <input type="hidden" name="p" value="${page}"/>    
	                                           <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Division ID</span>
	                                            <form:input type="text" path="name"  class="input-md round form-control" placeholder="Division ID"  />
	                                                      <form:errors  class="err" path="name"/>
	                                        </div>
	                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Description</span>
	                                            <form:textarea   path="description" class="input-md round form-control" placeholder="Division Description" />
	                                            <form:errors class="err" path="description"/>
	                                        </div>
	                                        
	                                         <div class="form-group">
	                                            <input type="submit" id="submit" class="btn" value="${caption}" />
	                                        </div>
	                                     </form:form>              
				</div>
			</div>
		</div>
	</div>
	</div>
	  <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/checkall.js"></script>
</body>
</html>
