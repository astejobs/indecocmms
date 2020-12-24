<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
<body class="appear-animate"> 
<style> 
.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}

tr:nth-child(even) {
    background-color: #f9f9f9;
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
.pagination {
    display: inline-block;
    padding-left: 0;
    margin: -24px 0;
    border-radius: 4px;
}

</style>
        <!-- Head Section -->
        <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">DASHBOARD</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="../CMMS-Dashboard.html">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="../forms_iCMMS/configurator-civil-Level-3.html">Civil Level 3</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        <!-- End Head Section -->
        <div  style="height:100px;position:relative"></div>
      		  <div class="container" style="width:100%;">
		         <div class="row">
		            <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
		    		   <jsp:include page="/WEB-INF/views/menu.jsp" />
		           </div>
		           <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">
                                  <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                      Civil Level 3 Details
                                  </h2>
                           
                               <c:if test="${not empty success}">
                                 <div class="success">${success}</div>
								</c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
                           
				                        <div class="form-group">
				                      		  <table class="table table-striped" id="tblResult">
				                        		<thead>
			                                      <tr>
			                                           <th>Civil Level 3 Name</th>
			                                           <th>Asset Subtype</th>
			                                           <th>Actions</th>
			                                      </tr>
                        						</thead>
                                                <tbody>
					                                    <c:url value="/civilLevel3/edit/${page}" var="edit_link"></c:url>
														<c:url value="/civilLevel3/delete/${page}" var="delete_link"></c:url>
														<c:forEach items="${level3list}" var="sub">
														
														
															<tr>
																<td class="minwidth-1">${sub.civilLevel3Name}</td>
																<td> ${sub.assetSubType.assetSubTypeName}</td>
																<td ><span style="font-size: 15px; cursor: pointer;"><a title="Click to Edit" href="${edit_link}/${sub.id}"><img src="${pageContext.servletContext.contextPath}/resources/assets/images/edit.png" style="width:25px"></a>&nbsp;&nbsp;</span>
																<span><a title="Click to Delete"	onclick="return confirm('Are you sure You want to DELETE this?')" href="${delete_link}/${sub.id}"><img src="${pageContext.servletContext.contextPath}/resources/assets/images/trash..png" style="width:20px"></a></span></td>
															</tr>
														</c:forEach>
				                               </tbody>
				                               <tfoot>
				                               <tr>
				                               		<td></td>
				                               		<td style="width:100px">
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
									<ul class="pagination pagination-sm" style=" position: relative; left:-34px; ">							 
															 <c:if test="${page>1}">
				
														<li>				<a class="page-far-left"
																			href="<c:url value="/civilLevel3/pageno=1"/>"> <<</a>  </li>
												<li>						<a class="page-left"
																		    href="<c:url value="/civilLevel3/pagenoo=${page-1}"/>">&nbsp;<</a> </li>
																	</c:if>
																	
																	
																		<c:forEach var="i" begin="${from}" end="${to}">
																			
																			<c:choose>
																				<c:when test="${i==page}">
																				<li class="active" ><a>${i}</a></li>
																				</c:when>
																				<c:otherwise>
																			<li>		<a class=""
																						href="<c:url value="/civilLevel3/pageno=${i}"/>"
																						>${i}</a> </li>
																				</c:otherwise>
																			</c:choose>
																		</c:forEach>
																	 <c:if test="${page<total}">
																<li>		<a class="page-right" 
																		 	href="<c:url value="/civilLevel3/pageno=${page+1}"/>">> </a>  </li>
															<li>			<a class="page-far-right"
																		    href="<c:url value="/civilLevel3/pageno=${total}"/>" >>></a> </li>
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
		                        <c:choose>
									<c:when test="${not empty edit}">
										<c:set var="var_url" value="/civilLevel3/update" />
										<c:set var="submit_caption" value="Update" />
										<h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
		                                      Edit Civil Level 3
		                                  </h2>
									</c:when>
		
									<c:otherwise>
										<c:set var="var_url" value="/civilLevel3/Add" />
										<c:set var="submit_caption" value="Add" />
										<h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
		                                      Add New Civil Level 3
		                                  </h2>
									</c:otherwise>
								</c:choose>
                                        
                        	   <c:url value="${var_url}" var="actionsubmit"></c:url> 
                           <form:form class="form contact-form" action="${actionsubmit}" method="post" id="validatee" commandName="level3">
                                        <div class="form-group">
                                        
                                        <form:hidden path="id"/>
                                        <input type="hidden" name="p" value="${page}"/> 
                                            <form:input type="text" class="input-md round form-control" path="civilLevel3Name" placeholder="Civil Level 3 Name"/>
                                        
                                            <form:errors  class="err" path="civilLevel3Name"/>
                                        </div>
                                        
                                        <div class="form-group">
                                            <form:select class="input-md round form-control" path="assetSubtype">
                                                                                             
                                                <form:option value="">Select Asset Subtype</form:option>
    
                                                <form:options items="${assetSubType}" itemValue="id" itemLabel="assetSubTypeName"/>
                                                
                                            </form:select>
                                            <form:errors class="err" path="assetSubtype"/>
                                        </div>
                                        <div class="form-group">
                                        <input type="hidden" name="p" value="${page}">
                                            <input type="submit" id="submit" value="${submit_caption}" />                                          
                                        </div>
    						</form:form>
    					</div>
   				 </div>
    	</div>
  </body>
