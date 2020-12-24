<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body class="appear-animate">
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
</style>

        <!-- End Navigation panel -->
        <!-- Head Section -->
      <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Priority</li>
			  </ol>
			</nav>
       
<div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                            	<c:url value="/priorty/delete" var="delete"/>
                        <form:form action="${delete}" class="form contact-form" id="contact_form">
                                       <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            Priority Details
                                        </h2>
                                        <div class="table-responsive">
                                            <table  class="table table-stripped table-hover" id="tblResult">
                                                <thead>
                                                    <tr>
                                                        <th><input  id="checkall" type="checkbox" /></th>
                                                        <th>Priority ID</th>
                                                        <th>Priority Desc</th>
                                                        <th>Modify</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:url value="/priorty/edit" var="editlink"/>
                                                
                                                    <c:forEach items="${priortyList}" var="prior">
                                                    <tr>
                                                    	<td><input type="checkbox" value="${prior.id }" name="id"/></td>
                                                    	<td>${prior.name }</td>
                                                        <td>${prior.description}</td>
                                                        
                                                        <td><a id="edit" href="${editlink}/${prior.id }/${page}" style="cursor:pointer"><i class="fa fa-edit"> </i></i></a></td>
                                                    </tr>
                                                 </c:forEach> 
			                                 	
			                                 	<tr> <td colspan="100%"> <div class="form-group">
			                                            <input type="submit" id="submit" class="btn" value="Delete" />
			                                        </div> </td> </tr>
			                               		
			                               	
			                               		<td  style="border-top:1px solid #FFF; "colspan="100%">
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
			
															<li>		<a class="page-far-left"
																		href="<c:url value="/priorty/pageno=1"/>"></a> </li>
			
															<li>		<a class="page-left"
																	    href="<c:url value="/priorty/pagenoo=${page-1}"/>"> </a> </li>
			
																</c:if>
																
																
																	<c:forEach var="i" begin="${from}" end="${to}">
																		
																		<c:choose>
																			<c:when test="${i==page}">
																					<li class="active" ><a>${i}</a></li>
																			</c:when>
																			<c:otherwise>
																		<li>		<a class=""
																					href="<c:url value="/priorty/pageno=${i}"/>"
																					>${i}</a> </li>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
			
																	
																</div> <c:if test="${page<total}">
													
			
															<li>		 <c:if test="${page<total}">
																		<a class="page-right" 
																		 	href="<c:url value="/priorty/pageno=${page+1}"/>">> </a> </li>
				
															<li>			<a class="page-far-right"
																			 href="<c:url value="/priorty/pageno=${total}"/>" >>></a> </li>
																	</c:if>
																</c:if>
																</ul>
																</td>
			
															</tr>
			                                                
			                                            </table>
			                                            </c:if>
			                                            </td>
			                                            </tr>
			                                            </tbody>
			                                            </table>
			                                        </div>
			                                       
			                                  </form:form>
                                            <c:choose>
											<c:when test="${not empty edit}">
												<c:set value="/priorty/update" var="action"/>
												<c:set var="caption" value="Update" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
		                                            Edit Priorty
		                                        </h2>
											</c:when>
											<c:otherwise>
												 <c:set value="/priorty/add" var="action"/>
												 <c:set var="caption" value="Add" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            		Add Priorty
                                        		</h2>
											</c:otherwise>
										</c:choose>
                                       ${success}${fail}
                                       <form:errors path="priorty"/>
                                       <c:url value="${action}" var="submit"/>
                                 	 <form:form  action="${submit}"  commandName="priorty" method="post" class="form contact-form">  
                                  	    <form:hidden path="id"/> 
                                  	    <input type="hidden" name="p" value="${page}"/>      
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Priority ID</span>
                                           <form:input type="text" path="name" class="input-md round form-control" placeholder="PRIORTY ID" />
                                           <form:errors  class="err" path="name"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Priority Description</span>
                                           <form:textarea path="description" class="input-md round form-control" placeholder="Priorty Description" />
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
       <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/checkall.js"></script>   
</body>
</html>