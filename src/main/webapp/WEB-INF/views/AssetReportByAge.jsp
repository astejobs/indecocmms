<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>     
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/AssetReport.js"></script>
<body class="appear-animate">
        <!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Technician</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                     Asset Reports
                    </h2>
                  	<c:url value="/asset/search" var="actionUrl"/>
 			               <form:form action="${actionUrl}" class="form contact-form" id="contact_form" commandName="equipmentSearch" method="post">
                                <input type="hidden" value="${pageContext.request.contextPath}" id="contextPath"/>
                                <input type="hidden" value="assetReportByAge" name="param"/>
                                 <div class="form-group">
		                                 <c:if test="${not empty success}">
		                                 <div class="success">${success}</div>
								  				</c:if>
												<c:if test="${not empty fail}">
		                                         <div class="error">${fail}</div>
		                                         </c:if>
		                                </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Asset Type</span>
                                            <select name="equipmentType" required class="input-md round form-control" id="AssetType">
                                                
                                                <option value="acmv">ACMV</option>
												<option value="mechanical">MECHANICAL</option>
												<option value="electrical">ELECTRICAL</option>
												<option value="fire">FIRE</option>
												<option value="civil">Civil</option>
                                            </select>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Asset Sub Type</span>
                                            <select name="" required class="input-md round form-control" id="assetSubType">
                                                <option>Select Asset Sub Type</option>
                                            </select>
                                        </div>
                                      
										 <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Building</span>
										 <form:select path="building" data-bvalidator="required" class="input-md round form-control" id="Building">
                                            
                                                <form:option value="-1">Select Building</form:option>
                                                <form:options items="${buildingList}" itemValue="id" itemLabel="name"/>
                                                                                             
                                         </form:select>
										</div>

                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Location</span>
                                               <form:select path="location" data-bvalidator="required" class="input-md round form-control" id="Location" >                                                     class="input-md round form-control" id="Location">
                                                 <form:option value="-1">Select Location</form:option>
                                                  <form:options items="${locationList}" itemLabel="name" itemValue="id"></form:options>
                                              </form:select>
                                            
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Service Area</span>
                                            <input type="text" name="servingArea" id="service-area" class="input-md round form-control" placeholder="Service Area" pattern=".{3,100}">
                                        </div>
                                        
                                        <div class="form-group">
	                                        <input type="radio" id="checkout" checked="checked" name="checker" style="display:inline-block;" value="from"/> &nbsp;<label>from age</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	                                        <input type="radio" name="checker" id="checkin" value="to"/>&nbsp;<label>upto age</label>
                                        </div>
                                        
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">From Age</span>
                                            <input type="number" name="fromAge" id="fromAge" class="input-md round form-control" placeholder="From age" required="required" pattern=".{3,100}">
                                        </div>
                                        <script>
                                        $(document).ready(function(){
                                        	$('#toAge').fadeOut('fast');
                                        	$('#toAgeGroup').fadeOut('fast');
                                        })
                                        $('#checkin').change(function(){
                                        	  if (this.checked) {
                                        	    $('#toAge').fadeIn('fast');
                                        	    $('#toAgeGroup').fadeIn('fast');
                                        	  }               
                                        });
                                        $('#checkout').change(function(){
                                      	  if (this.checked) {
                                      	    $('#toAge').fadeOut('fast');
                                      	  $('#toAgeGroup').fadeOut('fast');
                                      	  }               
                                      	});
                                        </script>
                                        
                                         <div class="form-group input-group" id="toAgeGroup">
                                        	<span class="input-group-addon label-left" id="basic-addon2">To Age</span>
                                            <input type="number"  name="toAge" id="toAge" class="input-md round form-control" placeholder="To Age" required="required" pattern=".{3,100}">
                                        </div>

                                        <div class="form-group float-right">
                                            <input type="submit" id="submit" name="submit" class="btn" value="Submit" />
                                            <input type="submit" id="btnexport" name="excel" class="btn" value="Export to Excel" />
                                        </div>
                                       
                          </form:form>
                            <br>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            Search Result
                                        </h2>
                                        <div class="table-responsive">
                                            <table class="table table-stripped table-hover ssh-table" id="tblResult">
                                                <thead>
                                                    <tr>
                                                        <th>Equipment Type</th>
                                                        <th>Sub Type</th>
                                                        <th>Name</th>
                                                        <th>Building</th>
                                                        <th>Location</th>
                                                        <th>Model</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr></tr>
                                                </tbody>
                                                
                                                <tfoot>
                                   <c:forEach  var="List" items="${listSearch}" >
                               <tr>
                               		 <td></td>
                                <td>${List.assetSubtype.assetSubTypeName}</td>
                                <td>${List.name}</td>
                                <td>${List.building.name}</td>
                                <td>${List.location.name}</td>
                                <td>${List.modelNo}</td>                             		
                               		
                               		</tr>
                               		  </c:forEach>
                               		
                               		<%-- <td> 
                               		
                               		
                               		
                               		 <c:if test="${1<total}">
									<c:choose>
										<c:when test="${total>10}">
											<div class="clearfix">
												Showing 10 pages out of ${total} pages.
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
											Showing ${total} page(s) out of ${total} page(s).
											<c:set var="from" value="1" />
											<c:set var="to" value="${total}" />
										</c:when>
									</c:choose>
									<table cellspacing="0" cellpadding="0" border="0"
										id="paging-table">
										<tbody>
											<tr>
											 <td><c:if test="${page>1}">

														<a class="page-far-left"
															href="<c:url value="/asset/pageno=1"/>"><img style="width:10px;height:12px" src="${pageContext.servletContext.contextPath}/resources/assets/images/pre.png"></a>

														<a class="page-left"
														   style="color:#fff;cursor:pointer;text-decoration:none;" href="<c:url value="/asset/pagenoo=${page-1}"/>">&nbsp; </a>

													</c:if>
													
													
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																	<strong  style="color:#11adc1 !important">${i}</strong>
																</c:when>
																<c:otherwise>
																	<a class=""
																		href="<c:url value="/asset/pageno=${i}"/>"
																		style="color:#c8cccd;text-decoration:none;font-size:15px">${i}</a>
																</c:otherwise>
															</c:choose>
														</c:forEach>

														
													 <c:if test="${page<total}">
														<a class="page-right"
															href="<c:url value="/asset/pageno=${page+1}"/>"></a>

														 <c:if test="${page<total}">
															<a class="page-right" style="color:#fff;cursor:pointer;text-decoration:none;"
															 	href="<c:url value="/asset/pageno=${page+1}"/>">&nbsp; </a>
	
															<a class="page-far-right"
																 href="<c:url value="/asset/pageno=${total}"/>" ><img style="width:10px;height:12px;top:-1px;position:relative" src="${pageContext.servletContext.contextPath}/resources/assets/images/next.png"></a>
														</c:if>
													</c:if>
													</td>

												</tr>
										</tbody>
									</table>
							</c:if></td> --%>
                              <!--  </tr> -->
                               </tfoot>
                                                
                                            </table>
</div>
</div>
</div>
</div>
</div>
</body>