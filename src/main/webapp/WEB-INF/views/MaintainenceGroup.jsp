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

table tbody tr:last-child {  border-top: 0px solid #FFF;}


</style>

        <!-- End Navigation panel -->
        <!-- Head Section -->
        <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Maintenance Group</li>
			  </ol>
			</nav>
        <!-- End Head Section -->

  <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
              	<c:url value="/maintainenceGrp/delete" var="delete"/>
                <form:form action="${delete}" class="form contact-form" id="contact_form">
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            Maintenance Group Details
                                        </h2>
                                        <div class="table-responsive">
                                            <table class="table table-stripped table-hover" id="tblResult">
                                                <thead>
                                                    <tr>
                                                        <th style="width:5%"><input   id="checkall" type="checkbox" /></th>
                                                        <th>Maintenance Group ID</th>
                                                        <th>Maintenance Group Desc</th>
                                                        <th>Status</th>
                                                        <th>Modify</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                    
						                    <c:url value="/maintainenceGrp/edit" var="editlink" />
						                    <c:forEach items="${mainGrpList}" var="maingrp">
                                                    <tr>
                                                    	<td><input type="checkbox" value="${maingrp.id }" name="id"/></td>
                                                    	<td>${maingrp.name }</td>
                                                        <td>${maingrp.description}</td>
                                                        <td>${maingrp.status}</td>
                                                        <td><a id="edit" href="${editlink}/${maingrp.id }/${page}" style="cursor:pointer"><i class="fa fa-edit"> </i></a></td>
                                                    </tr>
                                                 </c:forEach> 
 <tr><td colspan="100%" ><div class="form-group"> <input type="submit" id="submit" value="Delete" /> </div> </td>    </tr>                                     
                                     </tbody>
                                     <tfoot>
                                    <tr>
                               		
                               		<td style="border-top:1px solid #FFF; " colspan="100%">
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
															href="<c:url value="/maintainenceGrp/pageno=1"/>"><<</a> <li>

										<li>				<a class="page-left"
														  href="<c:url value="/maintainenceGrp/pagenoo=${page-1}"/>">< </a> </li>

													</c:if>
													<c:forEach var="i" begin="${from}" end="${to}">
														<c:choose>
																<c:when test="${i==page}">
																		<li class="active" ><a>${i}</a></li>
																</c:when>
																<c:otherwise>
															<li>		<a class=""
																		href="<c:url value="/maintainenceGrp/pageno=${i}"/>"
																		>${i}</a> </li>
																</c:otherwise>
															</c:choose>
														</c:forEach>

														
													  <c:if test="${page<total}">
													

											<li>			 <c:if test="${page<total}">
															<a class="page-right" 
															 	href="<c:url value="/maintainenceGrp/pageno=${page+1}"/>">&nbsp;> </a>  </li>
	
													<li>		<a class="page-far-right"
																 href="<c:url value="/maintainenceGrp/pageno=${total}"/>" >>></a> </li>
														</c:if>
													</c:if>
													</ul>
													</td>
											</tr>
											</tbody>
											</table>
											</c:if>
												</tr>
												</tfoot>
                                            </table>
                                            
                                        </div>
                                        
                                   </form:form>
                                    <div class="clearfix"> 
                                       <div>
                                        <c:choose>
											<c:when test="${not empty edit}">
												<c:set value="/maintainenceGrp/update" var="action"/>
												<c:set var="caption" value="Update" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
		                                            Edit Maintainence Group
		                                        </h2>
											</c:when>
											<c:otherwise>
												 <c:set value="/maintainenceGrp/add" var="action"/>
												 <c:set var="caption" value="Add" />
										
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            Add new Maintenance Group
                                        </h2>
                                        </c:otherwise>
                                        </c:choose>
                                              ${success}${fail}                        
                                        <form:errors path="maintainenceGrp"/>
                                       <c:url value="${action}" var="submit"/>
                                 	 <form:form  action="${submit}"  commandName="maintainenceGrp" method="post" class="form contact-form">  
                                  	    <form:hidden path="id"/> 
                                   	    <input type="hidden" name="p" value="${page}"/>   
                                  	      
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Maintenance ID</span>
                                           <form:input type="text" path="name" class="input-md round form-control" placeholder="Maintenance ID" />
                                           <form:errors class="err" path="name"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Maintenance Description</span>
                                           <form:textarea path="description" class="input-md round form-control" placeholder="Maintenance Description" />
                                        	<form:errors class="err" path="description"/>
                                        </div>
                                        
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Status</span>
                                            <form:select path="status" required="required" class="input-md round form-control">
                                             
                                                <form:option value="ACTIVE">ACTIVE</form:option>
                                                <form:option value="INACTIVE">INACTIVE</form:option>
                                            </form:select>
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
            </div>
             	
        <!--End Form-->
        
        <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/checkall.js"></script>
</body>
</html>