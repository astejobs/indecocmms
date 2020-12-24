<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>
    
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/mechanical.js"></script>
<style>tr td {
      padding-top:10px;
 }
 
 table {
             table-layout:auto;
 }
 
 .pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}
 
 input[type="button"] {
    border: none;
    background-color: #0754a4;
    color: #fff;
    padding: 7px 9px;
  
    
}
input[type="submit"] {
    border: none;
    background-color: #0754a4;
    color: #fff;
    padding: 7px 9px;
    border-radius: 0px;
  
    
}

table td {
	white-space: wrap;
	word-wrap: break-word;
	 text-overflow: ellipsis;
	overflow: hidden;
	max-width: 1px;
	text-align: justify;
}
 
 </style>
 </head>       
<body class="appear-animate">


       <!-- Head Section -->
      <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">DASHBOARD</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="${pageContext.servletContext.contextPath}/part">Create Part</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Head Section -->
        <!---MENU-->
         <div  style="height:100px;position:relative"></div>
			 <div class="container" style="width:100%;">
		  		<div class="row">
		    		<div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
		    			<jsp:include page="/WEB-INF/views/menu.jsp" />
		    		</div>
         			<div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">            
                    
                     <div class="form-group">
                     
                     <c:if test="${not empty edit}">
                     <c:set var="submitbtn" value="Update"></c:set>
                     <c:url var="submit" value="/part/update"></c:url>
                     <c:set var="title" value=" Edit Part Details"></c:set>
                     
                     </c:if>
                     <c:if test="${empty edit }">
                 <c:set var="submitbtn" value="Create"></c:set>
                     <c:url var="submit" value="/part"></c:url>
                     <c:set var="title" value=" Add Part Details"></c:set>
                 
                    
                     </c:if>
                     
                     
                     

                                        <c:if test="${not empty success}">
                                         <div class="success">${success}</div>
										</c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
                   
                    
                        
                            <form:form   action="${submit}" class="form contact-form" id="contact_form" commandName="part" method="post">
                                <div class="clearfix">
                                    <div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            ${title}
                                        </h2>
                                        <div class="form-group">
                                          	    <input type="hidden" name="p" value="${page}"/>   
                                        <form:hidden path="id"/>
                                           <form:select class="input-md round form-control" path="partType" >
                                               <form:option value="">Select Part Type</form:option>
                                               <form:options items="${partNameList}" itemLabel="partName"
									itemValue="id" />
                                           </form:select><form:errors path="partType" class="err" ></form:errors>
                                        </div>
                                        
                                        <div class="form-group">
                                            <form:textarea path="description" class="input-md round form-control col-lg-3 err" style="margin-bottom:10px" placeholder="Part Description" ></form:textarea>
                                        </div>
                                        <div class="form-group">
                                           <form:select class="input-md round form-control" path="manufacturer" >
                                               <form:option value="">Select Manufacturer</form:option>
                                               <form:options items="${manufacturerList}" itemLabel="name"
									itemValue="id" />
                                           </form:select><form:errors class="err" path="manufacturer"></form:errors>
                                        </div>
                                        <div class="form-group">
                                           <form:select class="input-md round form-control" path="vendor" >
                                               <form:option value="">Select Vendor</form:option>
                                               <form:options items="${vendorList}" itemLabel="vendorName"
									itemValue="id" />
                                           </form:select><form:errors path="vendor" class="err" ></form:errors>
                                        </div>
                                        
                                        
                                        <div class="form-group">
                                           <form:select class="input-md round form-control" path="unitOfMeasure" >
                                               <form:option value="">Select Unit Of Measure</form:option>
                                               <form:options items="${unitMeasureList}" itemLabel="unitOfMeasure"
									itemValue="id" />
                                           </form:select><form:errors path="unitOfMeasure" class="err" ></form:errors>
                                        </div>
                          
                                      
                                        <div class="form-group">
                                            <form:input type="number" path="reorderLevel" id="recordelevel" class="input-md round form-control err" placeholder="Reorder Level"/>
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" id="submit" value="${submitbtn}" />
                                        </div>
                                        <div class="form-group">
                                            <table class="table table-stripped" id="tblResult">
                                                <thead>
                                                    <tr>
                                                        <th>Part Name</th>
                                                        <th>Part Description</th>
                                                        <th>Vendor</th>
                                                        <th>Manufacturer</th>
                                                        <th>Action</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:url value="/part/delete" var="delete_link"></c:url>
                                                   <c:url value="/part/edit" var="edit_link"></c:url>
                                                   <c:forEach items="${partList}"  var="Part">
                                                    
                                                     <tr>
                                                        <td>${Part.partType.partName}</td>
                                                        <td>${Part.description }</td>
                                                        <td>${Part.vendor.vendorName }</td>
                                                        <td>${Part.manufacturer.name}</td>
                                                        <td><a href="${delete_link}/${Part.id}">  <i class="fa fa-trash"></i></a> &nbsp
                      		                         <a href="${edit_link}/${Part.id}/${page}"><i class="fa fa-edit"></i></a></td>
                                                     </tr>
                                                    </c:forEach>
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
									<ul class="pagination pagination-sm" style=" position: relative; left:344px; ">							 
															 <c:if test="${page>1}">
				
														<li>				<a class="page-far-left"
																			href="<c:url value="/part/pageno=1"/>"> <<</a>  </li>
												<li>						<a class="page-left"
																		    href="<c:url value="/part/pageno=${page-1}"/>">&nbsp;<</a> </li>
																	</c:if>
																	
																	
																		<c:forEach var="i" begin="${from}" end="${to}">
																			
																			<c:choose>
																				<c:when test="${i==page}">
																				<li class="active" ><a>${i}</a></li>
																				</c:when>
																				<c:otherwise>
																			<li>		<a class=""
																						href="<c:url value="/part/pageno=${i}"/>"
																						>${i}</a> </li>
																				</c:otherwise>
																			</c:choose>
																		</c:forEach>
																	 <c:if test="${page<total}">
																<li>		<a class="page-right" 
																		 	href="<c:url value="/part/pageno=${page+1}"/>">> </a>  </li>
															<li>			<a class="page-far-right"
																		    href="<c:url value="/part/pageno=${total}"/>" >>></a> </li>
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
                                            </div>
                                           
                                        </div>
                                    </div>
                                    </form:form>
                                    
                                </div>
                               
                            
                        </div>
                    </div>
                    <!-- End Contact Form -->
                </div>
           


          