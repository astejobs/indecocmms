
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body class="appear-animate">
<style> 
.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #56c759;
    border-color: #56c759;
}

input[type="button"] {
    border: none;
    background-color: #56c759;
    color: #fff;
    padding: 7px 9px;
  
    
}

.bac{
          background-color: #FFF;
          border-top: 0px solid #FFF;
}

input[type="submit"] {
    border: none;
    background-color: #56c759;
    color: #fff;
    padding: 7px 9px;
    border-radius: 0px;
  
    
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

        <!-- End Navigation panel -->
        <!-- Head Section -->
        <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">DASHBOARD</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="../CMMS-Dashboard.html">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="${pageContext.servletContext.contextPath}/building">Building</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
       <div  style="height:100px;position:relative"></div>
      		  <div class="container" style="width:100%;">
		         <div class="row">
		            <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
		    		   <jsp:include page="/WEB-INF/views/menu.jsp" />
		           </div>


				
                            
                         
                                <div class="clearfix">
                                    <div>

		           <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">

                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Vendor Details
                                        </h2>
                                        
                                          <c:if test="${not empty success}">
                                         <div class="success">${success}</div>
										</c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
                                        
            <c:url value="/vendor/delete" var="delete"/>
            <form:form action="${delete}" >
                                        <div class="form-group">
                                            <table class="table table stripped" id="tblResult">
                                                <thead>
                                                    <tr>
                                                        <th><input type="checkbox" id="checkall"/></th>
                                                        <th>Vendor Name</th>
                                                        <th>Vendor ID</th>
                                                        <th>Vendor Desc</th>
                                                        <th>Phone No.</th>
                                                        <th>Email</th>
                                                        <th>Fax</th>
                                                        <th>Address</th>
                                                        <th>Modify</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach items="${vendorList}" var="ven">
                                                    <tr>
                                                    	 
                                                    
                                                        <td><input type="checkbox" value="${ven.id}" name="id"/></td>
                                                        <td>${ven.vendorName}</td>
                                                        <td>${ven.id}</td>
                                                        <td>${ven.description }</td>
                                                        <td>${ven.phoneNumber }</td>
                                                        <td>${ven.email }</td>
                                                        <td>${ven.faxNumber}</td>
                                                        <td>${ven.address }</td>
                                                        <td><a href="${pageContext.servletContext.contextPath}/vendor/${ven.id}/${page}"><img style="width:20px" src="${pageContext.servletContext.contextPath}/resources/assets/images/edit.png" /></a>
															&nbsp;&nbsp;&nbsp;
														</td>
                                                        
                                                       
                                                    </tr>
                                                     </c:forEach>
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
															href="<c:url value="/vendor/pageno=1"/>"></a> </li>

										            <li>	<a class="page-left"
														   style="color:green; cursor:pointer; text-decoration:none;" href="<c:url value="/vendor/pageno=${page-1}"/>">&nbsp;  </a> </li>

													</c:if>
													
													 
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																	<li class="active" ><a>${i}</a></li>
																</c:when>
																<c:otherwise>
																<li><a href="<c:url value="/vendor/pageno=${i}"/>">${i}</a></li>
																	
																</c:otherwise>
															</c:choose>
														</c:forEach>
														
								
														 <c:if test="${page<total}">
											<li>				<a class="page-right" style="color:green;cursor:pointer;text-decoration:none;"
															 	href="<c:url value="/vendor/pageno=${page+1}"/>">&nbsp;> </a> </li>
	
											<li>				<a class="page-far-right"
														
																 href="<c:url value="/vendor/pageno=${total}"/>" >>></a> </li>
														
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
                                            <input type="submit" id="submit" value="Delete" />
                                        </div>
                                        
         </form:form>
                                        
                                        
                                      
                                     
                                       
                                        
                                        
                                         <c:choose>
											<c:when test="${not empty edit}">
												<c:set value="/vendor/update" var="url"/>
												<c:set var="caption" value="Update" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
		                                            Edit Vendor
		                                        </h2>
											</c:when>
											<c:otherwise>
												 <c:set value="/vendor" var="url"/>
												 <c:set var="caption" value="Add" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            		Add Vendor
                                        		</h2>
											</c:otherwise>
										</c:choose>
                                        
                                        
                                        <c:url value="${url}" var="url"/>
                                        
                                        <form:form class="form contact-form" id="contact_form" modelAttribute="vendor" method="post" action="${url}">
                                        
                                        <form:hidden path="id"/> 
                                  	    <input type="hidden" name="p" value="${page}"/> 
                                        
                                        <div class="form-group">
                                            <form:input type="text" path="vendorName" id="vendorname" class="input-md round form-control" placeholder="Vendor Name"/>
                                       		<form:errors class="err" path="vendorName"/>
                                        </div>
                                        <div class="form-group">
                                            <form:textarea path="description" class="input-md round form-control col-lg-3" style="margin-bottom:10px" placeholder="Vendor Description" />
                                        	<form:errors class="err" path="description"/>
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="phoneNumber" id="phonenumber" class="input-md round form-control" placeholder="Phone Number"/>
                                       			<form:errors class="err" path="phoneNumber"/>
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="email" id="email" class="input-md round form-control" placeholder="Email ID"/>
                                       			<form:errors class="err" path="email"/>
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="faxNumber" id="faxnumber" class="input-md round form-control" placeholder="Fax Number"/>
                                       			<form:errors class="err" path="faxNumber"/>
                                        </div>
                                        <div class="form-group">
                                            <form:textarea path="address" class="input-md round form-control col-lg-3" style="margin-bottom:10px" placeholder="Address"/>
                                      			<form:errors class="err" path="address"/>
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" id="submit" value="${caption}" >
                                        </div>
                                         
                                         </form:form>
                                         
                                    </div>
                                     
                                </div>
                          
                            
                    </div>

       			</div>
      		 
      		 
      		  <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/checkall.js"></script>
      		 

                        