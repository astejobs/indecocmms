<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/electrical.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/EqCr.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/assets/js/datetimepicker_css.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>
<style>
        table{
           table-layout:auto;
        }
    
    

tbody tr:nth-child(even) {
    background-color: #f9f9f9;
}

table th, td {
       white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;
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
                            <a class="label-click" href="../CMMS-Dashboard.html">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="../forms_iCMMS/search-fault-report.html">Search Fault Report</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Head Section -->
        
        <div style="height:100px;position:relative"></div>
			 <div class="container" style="width:100%;">
			    <div class="row">
			        <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
				  		   <jsp:include page="/WEB-INF/views/menu.jsp" />
				     </div>
			   		 <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">
	                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
	                        Electrical Equipment Details
	                    </h2>
						<div class="form-group">
			             <c:if test="${not empty success}">
			                  <div class="success">${success}</div>
			              </c:if>
						  <c:if test="${not empty fail}">
	    	                   <div class="error">${fail}</div>
	                      </c:if>
	                      <c:url value="/electrical/search/"  var="search"></c:url>
                          <form:form  method="post" class="form contact-form" commandName="electricalSearch" action="${search}">
                          <div  class="form-group">
                          <input type="hidden" value="${pageContext.request.contextPath}" id="contextPath"/>
                          <form:input type="text" path="equipmentType"  value="Electrical"  class="input-md round form-control" placeholder="Equipment Type" readonly="true"/>
                          </div>
                          <div class="form-group">  
                          <form:input type="text" path="name" class="input-md round form-control" placeholder="Equipment Name" /><form:errors path="name" class="errors"/>
                          </div>
                          <div class="form-group">
                          <form:select  path="assetSubtype" class="input-md round form-control">
                          <form:option value="">Asset Sub Type</form:option>
                		  <c:forEach items="${subTypeList}" var="subTypeList">
                          	<form:option value="${subTypeList.id}">${subTypeList.assetSubTypeName}</form:option>
						  </c:forEach>
                          </form:select>
                          </div> 
                          <div class="form-group">
                          <form:select class="input-md round form-control" path="building" id="bId" >
                          <form:option value="">Select Building</form:option>
                          <c:forEach items="${buildingList}" var="building">
                          <form:option value="${building.id}">${building.name}</form:option>
						  </c:forEach>
                          </form:select>
                          </div>
                          <div class="form-group">
                          <form:select class="input-md round form-control" path="location" id="loc">
                          <form:option value="">Select Location</form:option>
                          <c:forEach items="${locationList}" var="location">
						  <form:option value="${location.id}">${location.name}</form:option>
						  </c:forEach>
                          </form:select>
                          </div>
                          <div class="form-group">
                          <form:input  type="text"  path="servingArea"   style="width:45%" class="input-md round form-control" placeholder="Servicing Area"/>
                          </div>
                          <div class="form-group">
                          <form:input  type="text"  path="equipmentCode"   style="width:45%" class="input-md round form-control" placeholder="Equipment Code"/>
                          </div>
                          <input type="submit"  value="Search"/>
                          
                    <!-- End Contact Form -->
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080"></h2>
                    <div class="form-group">
		                 <table class="table table-stripped"  id="tblResult">
                            <thead>
                                <tr>
                                    <th data-toggle="tooltip" data-placement="bottom" title="Equipment Type">Equipment Type</th>
                                    <th data-toggle="tooltip" data-placement="bottom" title="Equipment SubTypee">Equipment SubType</th>
                                    <th data-toggle="tooltip" data-placement="bottom" title="Equipment Name">Equipment Name</th>
                                    <th>Building</th>
                                    <th>Location</th>
                                    <th>Make</th>
                                    <th data-toggle="tooltip" data-placement="bottom" title="Servicing Area">Servicing Area</th>
                                    <th data-toggle="tooltip" data-placement="bottom" title="Image">Image</th>
                                    <th data-toggle="tooltip" data-placement="bottom" title="Drawing Title">Drawing Title</th>
                                    <th data-toggle="tooltip" data-placement="bottom" title="Actions" >Actions</th>
                                </tr>
                                
                            </thead>
                            <tbody>
                            <c:forEach items="${searchedList}" var="item">
                               <tr  >
                               <td>Electrical</td>
                               <td></td> 
                                <td data-toggle="tooltip" data-placement="bottom" title="${item.name }" >${item.name }</td>
                                <td  data-toggle="tooltip" data-placement="bottom" title="${item.building.name}" >${item.building.name}</td>
                                <td data-toggle="tooltip" data-placement="bottom" title="${item.location}">${item.location}</td>
                                <td data-toggle="tooltip" data-placement="bottom" title="${item.make}" >${item.make}</td>
                                <td data-toggle="tooltip" data-placement="bottom" title="${item.servingArea}"  >${item.servingArea}</td>
                                <td> <c:set var="eqpimage"  value="${item.image}"/>
                                       	 <c:if test="${not empty eqpimage}">
                                            <img
											src="${pageContext.servletContext.contextPath}/electrical/getimage/${item.image}"
											style="height: 50px; width: 50px;" /></c:if>    </td>
                                <td></td>
                                <c:url value="/electrical/delete" var="deleteURL">
									<c:param name="id" value="${item.id}"></c:param>
								</c:url>
           			            <td><span style="font-size: 15px; cursor: pointer;">
                    	   		<a href="${deleteURL}">  <i class=" fa fa-trash"> </i></a></span>
				                <c:url value="/electrical/update/${item.id}" var="updateURL"></c:url>       
                                <span style="font-size: 15px; cursor: pointer;">
                       			<a href="${updateURL}"> <i class=" fa fa-edit"> </i></a></span></td>                     
                                </tr>
                               </c:forEach> 
                            </tbody>
                 <tfoot>           <tr>
                               		
                               		<td  colspan=100%>
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
											 <td style="border-style:hidden;">
											  <ul class="pagination pagination-sm" style="margin-left: 36%;"  >
				<c:if test="${page>1}">

										<li>				<a class="page-far-left"
															href="<c:url value="/electrical/pageno=1"/>"><<</a> </li>

									<li>					<a class="page-left"
														    href="<c:url value="/electrical/pageno=${page-1}"/>"> < </a> </li>

													</c:if>
													
													
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																		<li class="active" ><a>${i}</a></li>
																</c:when>
																<c:otherwise>
									<li>								<a class=""
																		href="<c:url value="/electrical/pageno=${i}"/>"
																		>${i}</a> </li>
																</c:otherwise>
															</c:choose>
														</c:forEach>

													
													    <c:if test="${page<total}">
									

														 <c:if test="${page<total}">
												<li>			<a class="page-right" 
															 	href="<c:url value="/electrical/pageno=${page+1}"/>">> </a>  </li>
	
									<li>						<a class="page-far-right"
																 href="<c:url value="/electrical/pageno=${total}"/>" >>></a> </li>
														</c:if>
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
			</div>
		</div>
	</div>
</div>


        <!--End Form-->
</body>
</html>
                        