 <%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <style>
    table {
        table-layout:auto;
    }
</style>
   <!-- Head Section -->
         <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Manufacturer</li>
			  </ol>
			</nav>
        
        <!-- End Head Section -->
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                        Configurator
                    </h2>

                    <!-- Contact Form -->
                    
                    
                                <div class="clearfix">
                                    <div>
                                    
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                            Manufacturer Details
                                        </h2>
                                        
                                        <c:if test="${not empty success}">
                                        	 <div class="success">${success}</div>
										</c:if>
										
										<c:if test="${not empty fail}">
                                      		<div class="error">${fail}</div>
                                        </c:if>
                                        
            <c:url value="/manufacturer/delete" var="delete"/>
            <form:form action="${delete}" >
                                        
                                        <div class="form-group">
                                            <table class="table table-stripped" id="tblResult">
                                                <thead>
                                                    <tr>
                                                        <th><input type="checkbox" id="checkall"/></th>
                                                        <th>Manufacturer ID</th>
                                                        <th>Manufacturer Desc</th>
                                                        <th>Modify</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                	
                                                	<c:forEach items="${manufacturerList}" var="man">
                                                    <tr>
                                                    
                                                    <td><input type="checkbox" value="${man.id}" name="id"/></td>
                                                        <td>${man.name}</td>
                                                        <td>${man.description}</td>
                                                        <td><a href="${pageContext.servletContext.contextPath}/manufacturer/${man.id}/${page}"><img style="width:20px" src="${pageContext.servletContext.contextPath}/resources/assets/images/edit.png" /></a>
															&nbsp;&nbsp;&nbsp;
														</td>
                                                        
                                                    </tr>
                                                	</c:forEach>  
                                                
                                                </tbody>
                                                
                                                <tfoot align="center"> 
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
									<table id="paging-table">
										<tbody>
											<tr>
											 <td>
											<ul class="pagination pagination-sm" style=" position: relative;left:337px">
											 <c:if test="${page>1}">
                                                
                                                 
													<li>	<a class="page-far-left"
															href="<c:url value="/manufacturer/pageno=1"/>"></a> </li>

										            <li>	<a class="page-left"
														   style="color:green; cursor:pointer; text-decoration:none;" href="<c:url value="/manufacturer/pageno=${page-1}"/>">&nbsp;  </a> </li>

													</c:if>
													
													 
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																	<li class="active" ><a>${i}</a></li>
																</c:when>
																<c:otherwise>
																<li><a href="<c:url value="/manufacturer/pageno=${i}"/>">${i}</a></li>
																	
																</c:otherwise>
															</c:choose>
														</c:forEach>
														
								
														 <c:if test="${page<total}">
											<li>				<a class="page-right" style="color:green;cursor:pointer;text-decoration:none;"
															 	href="<c:url value="/manufacturer/pageno=${page+1}"/>">&nbsp;> </a> </li>
	
											<li>				<a class="page-far-right"
														
																 href="<c:url value="/manufacturer/pageno=${total}"/>" >>></a> </li>
														
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
                                        
                                          <div class="form-group">
                                            <input type="submit" id="submit" class="btn" value="Delete" />
                                       	 </div>
                                       	 
                             </form:form>
                                       	 
                                       	  
                                       	 
                                       	   <c:choose>
											<c:when test="${not empty edit}">
												<c:set value="/manufacturer/update" var="url"/>
												<c:set var="caption" value="Update" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
		                                            Edit Manufacturer
		                                        </h2>
											</c:when>
											<c:otherwise>
												 <c:set value="/manufacturer" var="url"/>
												 <c:set var="caption" value="Add" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                            		Add Manufacturer
                                        		</h2>
											</c:otherwise>
										</c:choose>
                                       	 
                                        
                                        <c:url value="${url}" var="url" />
                                        
                                       <form:form class="form contact-form" id="contact_form" modelAttribute="manufacturer" method="post" action="${url}">
                                        
                                                                          
                                        <form:hidden path="id"/> 
                                  	    <input type="hidden" name="p" value="${page}"/> 
                                        
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Manufacturer ID</span>
                                            <form:input type="text" path="name" id="manufacturerid" class="input-md round form-control" placeholder="Manufacturer ID"/>
                                       		<form:errors path="name"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Manufacturer Description</span>
                                            <form:textarea path="description" class="input-md round form-control col-lg-3" style="margin-bottom:10px" placeholder="Manufacturer Description" />
                                       		<form:errors path="description"/>
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" id="submit" class="btn" value="${caption}" />
                                        </div>
                                        
                                        </form:form>
                                    </div>
                                </div>
                            
                        </div>
                    </div>
                    <!-- End Contact Form -->
                </div>
                </div>
                
                
       <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/checkall.js"></script>          
           