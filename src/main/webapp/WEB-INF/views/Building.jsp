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


.bac{
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

       <!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Building</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
        	
                      	<c:url value="/building/delete" var="delete"/>
                            <form:form action="${delete}" class="form contact-form">
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            Building Details
                                        </h2>
                                        <div class="form-group">

                                        <c:if test="${not empty success}">
                                         <div class="success">${success}</div>
										</c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
                                            <table class="table table-striped" id="tblResult">
                                               <thead>
                                                    <tr>
                                                        <th><input id="checkall" type="checkbox" /></th>
                                                        <th>Building</th>
                                                        <th>Description</th>
                                                        <th>Modify</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:url value="/building/edit" var="editlink"/>
                                               <c:forEach items="${buildingList}" var="bldng">
                                                    <tr>
                                                    	<td><input type="checkbox" value="${bldng.id }" name="id"/></td>
                                                    	<td>${bldng.name }</td>
                                                        <td>${bldng.description}</td>
                                                        
                                                        <td><a id="edit" href="${editlink}/${bldng.id }/${page}" style="cursor:pointer"><i class=" fa fa-edit"></i></a></td>
                                                    </tr>
                                                 </c:forEach> 
                                                 <tr > 
                                                 <td colspan="100%" class="bac" style="border-bottom: 1px solid #FFF" >
                                                 <div >
                                                 <input  type="submit" id="submit" class="btn" value="Delete" />
                                                 </div>
                                                 </td>
                                                 </tr>
                                                </tbody>
                                                <tfoot align="center"> <div>
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
															href="<c:url value="/building/pageno=1"/>"><<</a> </li>

										            <li>	<a class="page-left"
														   style="color:green; cursor:pointer; text-decoration:none;" href="<c:url value="/building/pageno=${page-1}"/>">&nbsp; < </a> </li>

													</c:if>
													
													 
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																	<li class="active" ><a>${i}</a></li>
																</c:when>
																<c:otherwise>
																<li><a href="<c:url value="/building/pageno=${i}"/>">${i}</a></li>
																	
																</c:otherwise>
															</c:choose>
														</c:forEach>
														
													<%--  <c:if test="${page<total}">
														<a class="page-right"
															href="<c:url value="/building/pageno=${page+1}"/>"></a> --%>

														 <c:if test="${page<total}">
											<li>				<a class="page-right" style="color:green;cursor:pointer;text-decoration:none;"
															 	href="<c:url value="/building/pageno=${page+1}"/>">&nbsp;> </a> </li>
	
											<li>				<a class="page-far-right"
														
																 href="<c:url value="/building/pageno=${total}"/>" >>></a> </li>
														
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
                            <div class="clearfix"> </div>
                                        <c:choose>
											<c:when test="${not empty edit}">
												<c:set value="/building/update" var="action"/>
												<c:set var="caption" value="Update" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
		                                            Edit Building
		                                        </h2>
											</c:when>
											<c:otherwise>
												 <c:set value="/building/add" var="action"/>
												 <c:set var="caption" value="Add" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            		Add Building
                                        		</h2>
											</c:otherwise>
										</c:choose>
                                       <form:errors path="building"/>
                                       <c:url value="${action}" var="submit"/>
                                 	 <form:form  action="${submit}"  commandName="building" method="post" class="form contact-form">  
                                  	    <form:hidden path="id"/>  
                                  	    <input type="hidden" name="p" value="${page}"/>   
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Building ID</span>
                                           <form:input type="text" path="name" class="input-md round form-control" placeholder="Building ID" />
                                           <form:errors  class="err" path="name"></form:errors>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Description</span>
                                           <form:textarea path="description" class="input-md round form-control" placeholder="Building Description" />
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
