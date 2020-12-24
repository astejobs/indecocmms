<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>

<style>
 table{
        table-layout: auto;
 }
 .pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}
 
</style>
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
                            <a class="label-click" href="../CMMS-Dashboard.html">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="../forms_iCMMS/configurator-technician.html">Technician</a>
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
		           <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                        Civil Asset Details
                    </h2>
                      <c:if test="${not empty success}">
                          <div class="success">${success}</div>
					  </c:if>
                      <c:if test="${not empty fail}">
                          <div class="error">${fail}</div>
                      </c:if>
                      <c:url value="/schedule/updateForReview" var="actionsubmit"></c:url>
                 
                       
                            <form:form action="${actionsubmit}" class="form contact-form" id="contact_form" commandName="equipmentSearch">
                                <input type="hidden" value="${pageContext.request.contextPath}" id="contextPath"/>
                                
         
                                      <div class="form-group">
                                           <form:select path="assetSubtype" class="input-md round form-control" style="width:49%">
                                                <form:option value="-1">Select Asset SubType</form:option>
								                <form:options items="${assetSubType}" itemLabel="assetSubTypeName" itemValue="id"/>
                                            </form:select>
                                       </div>
                                       <div class="form-group">
                                            <form:input type="text" path="name" id="asset_name" class="input-md round form-control" placeholder="Asset Name" />
                                        </div>
                                        <div class="form-group">
                                            <form:select path="building" class="input-md round form-control" id="Building">
                                                 <form:option value="-1">Select Building</form:option>
                                                <form:options items="${buildingList}" itemValue="id" itemLabel="name"/>
                                            </form:select>
                                           <form:errors path="building"/>
                                         </div>
                                        <div class="form-group">
                                             <form:select path="location" data-bvalidator="required" id="Location" >                                                     class="input-md round form-control" id="Location">
                                                 <form:option value="-1">Select Location</form:option>
                                                  <form:options items="${locationList}" itemLabel="name" itemValue="id"></form:options>
                                              </form:select>
                                            <form:errors path="location"/>
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="servingArea" id="servicearea" class="input-md round form-control" placeholder="Serving Area" />
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" id="submit" value="Search" />
                                        </div>
                            </form:form>
                    <!-- End Form -->

                    <div class="form-group" >
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Asset Type</th>
                                    <th>Asset SubType</th>
                                    <th>Building</th>
                                    <th>Location</th>
                                    <th>Make</th>
                                    <th>Servicing Area</th>
                                    <th>Image</th>
                                    <th>Drawing Title</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            
                           
                            <tbody>
                                <tr>
                                 <c:forEach  var="civilList" items="${listSearch}" >
                                <td></td>
                                <td>${civilList.assetSubtype.assetSubTypeName}</td>
                                <td>${civilList.name}</td>
                                <td>${civilList.building.name}</td>
                                <td>${civilList.location.name}</td>
                                <td>${civilList.modelNo}</td>
                                
                                <td></td>
                                <td></td>
                                 <td><a id="edit" href="${editlink}/${civilList.id}" style="cursor:pointer"><img style="width:20px" src="${pageContext.servletContext.contextPath}/resources/assets/images/edit.png" /></a>
                                 <a id="edit" href="${deletelink}/${civilList.id }" style="cursor:pointer"><img style="width:20px" src="${pageContext.servletContext.contextPath}/resources/assets/images/trash..png" /></a></td>
                                
                                
                                </tr>
                            </c:forEach>
                            
                            </tbody>
                            
                                 <tfoot >
                                    <tr>
                               		                      		
                               		<td colspan="100">   <c:if test="${1<total}">
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
											
											<%-- Showing ${total} page(s) out of ${total} page(s). --%>
											<c:set var="from" value="1" />
											<c:set var="to" value="${total}" />
										</c:when>
									</c:choose>
									<table cellspacing="0" cellpadding="0" border="0"
										id="paging-table">
										<tbody>
											<tr>
											 <td >
				      <ul class="pagination pagination-sm" style="margin-left: 36%;"  >							 
											 <c:if test="${page>1}">
<li>
														<a class="page-far-left"
															href="<c:url value="/civil/pageno=1"/>"><<</a> </li>
<li>
														<a class="page-left"
														   href="<c:url value="/civil/pagenoo=${page-1}"/>">< </a> </li>

													</c:if>
													
													
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																<li class="active" ><a>${i}</a></li>
																</c:when>
																<c:otherwise>
							<li>										<a class=""
																		href="<c:url value="/civil/pageno=${i}"/>"
																		>${i}</a>   </li>
																</c:otherwise>
															</c:choose>
														</c:forEach>

														
													 <c:if test="${page<total}">
														

														 <c:if test="${page<total}">
													<li>		<a class="page-right"
															 	href="<c:url value="/civil/pageno=${page+1}"/>">> </a> </li>
	
					<li>										<a class="page-far-right"
																 href="<c:url value="/civil/pageno=${total}"/>" >>></a>  </li>
														</c:if>
													</c:if>
													</ul>
													</td>

												</tr>
										</tbody>
									</table>
							</c:if></td>
                               </tr>
                               </tfoot>
                        </table>
                      </div>  
				</div>
			</div>
</div>
</body>