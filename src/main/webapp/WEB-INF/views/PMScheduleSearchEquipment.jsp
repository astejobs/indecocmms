<!DOCTYPE html>
<html>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/FaultReport.js"></script>
<script type="text/javascript">
       function changeParent(id) {
        	var option = document.createElement("option");
        	option.selected = true;
        	option.text = document.getElementById(id).innerHTML;
        	option.value = id;
        	var select =  window.opener.document.getElementById('equipment');
        	for (i = 0; i < select.length; ++i) 
        	{
        		   if (select.options[i].value == id) 
        		   {
        		      return;
        		   }
        	}
        	
        	select.appendChild(option);
        	
           
        }
</script>

       <div class="vertical-spacer"></div> 
        <!-- Head Section -->
      <%--  <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">PM Equipment Search </li>
			  </ol>
			</nav> --%>
        <!-- End Head Section -->
        
        <!--Form-->
<div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                            	<input type="hidden" id="context" value="${pageContext.servletContext.contextPath}"/>
                            	<c:url value="/schedule/equipments" var="action" />
                                <form:form  action="${action}" modelAttribute="equipmentSearch" method="post"  class="form contact-form" id="contact_form">
                                	<h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                        Select Equipment List
                                    </h2>
                                    
                                    <input type="hidden" value="1" name="p" id="p" />
                                    
                                    <div class="form-group input-group">
	                                     <span class="input-group-addon label-left" id="basic-addon2">Asset Type</span>
	                                    <form:select path="assetType" id="assetType" class="input-md round form-control">
	                                        <form:option value="">Asset Type</form:option>
	                                        <form:options items="${assetTypeList}" itemLabel="assetTypeName" itemValue="assetTypeName"/>
	                                    </form:select>
                                	</div>
                                	
                                <div class="form-group input-group">
                                    <span class="input-group-addon label-left" id="basic-addon2">Asset Sub Type</span>
                                    <form:select path="assetSubtype" id="assetSubtype" class="input-md round form-control" onchange="SetName();">
                                        <form:option value="">Asset Sub Type</form:option>
                                        <form:options items="${assetSubtypeList}" itemLabel="assetSubTypeName" itemValue="id"/>
                                    </form:select>
                                </div>
                               <div class="form-group input-group">
                                    <span class="input-group-addon label-left" id="basic-addon2">Equipment Name</span>
                                    <form:input type="text" path="name" id="Equip_Name" class="input-md round form-control" placeholder="Equipment Name"/>
                                </div>
                                <div class="form-group input-group">
                                    <span class="input-group-addon label-left" id="basic-addon2">Select Building</span>
                                    <form:select path="building" class="input-md round form-control">
                                        <form:option value="">Select Building</form:option>
                                        <form:options items="${buildingList}" itemLabel="name"  itemValue="id"/>
                                    </form:select>
                                </div>
                                <div class="form-group input-group">
                                    <span class="input-group-addon label-left" id="basic-addon2">Select Location</span>
                                    <form:select path="location" class="input-md round form-control">
                                        <form:option value="">Select Location</form:option>
                                        <form:options items="${locationList}" itemLabel="name"  itemValue="id"/>
                                    </form:select>
                                </div>
                                <div class="form-group float-right">
                                    <input type="submit" name="btnSub" id="btnSub" class="input-md btn" placeholder="Submit">
                                </div>
                                </form:form>
                                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                        Equipment List
                                    </h2>
                                     <div class="form-group">
                                        <table class="table table-stripped">
                                            <thead>
                                                <tr>
                                                    <th>Equip Name</th>
                                                    <th>EquipType</th>
                                                    <th>Equip SubType</th>
                                                    <th>Building</th>
                                                    <th>Location</th>
                                                    <th>Serving Area</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            	<c:forEach items="${equipmentList}" var="equip">
                                            	   <tr>	
	                                                 <td><a href="#" id="${equip.id}" onclick="javascript:changeParent(this.id)" >${equip.name}</a></td>
	                                               <td>${equip.type}</td>
													<td>${equip.assetSubtype.assetSubTypeName}</td>
													<td>${equip.building.name}</td>
													<td>${equip.location.name}</td>
													<td>${equip.servingArea}</td>		
			                                       
													
                                                 <tr>
                                                </c:forEach>
                                                
                          
                                                
                                            </tbody>
                                        </table>
                                    </div>
                                    
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
											 <td><c:if test="${page>1}">

														<a class="page-far-left"
															href="<c:url value="/schedule/pageno=1"/>"><img style="width:10px;height:12px" src="${pageContext.servletContext.contextPath}/resources/assets/images/pre.png"></a>

														<a class="page-left"
														   style="color:#fff;cursor:pointer;text-decoration:none;" href="<c:url value="/schedule/pageno=${page-1}"/>">&nbsp; << </a>

													</c:if>
													
													
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																	<strong  style="color:#11adc1 !important">${i}</strong>
																</c:when>
																<c:otherwise>
																	<a class=""
																		href="<c:url value="/schedule/pageno=${i}"/>"
																		style="color:#c8cccd;text-decoration:none;font-size:15px">${i}</a> 
																</c:otherwise>
															</c:choose>
														</c:forEach>

													 <c:if test="${page<total}">
														<a class="page-right"
															href="<c:url value="/schedule/pageno=${page+1}"/>"></a>

														 <c:if test="${page<total}">
															<a class="page-right" style="color:#fff;cursor:pointer;text-decoration:none;"
															 	href="<c:url value="/schedule/pageno=${page+1}"/>">&nbsp;>> </a>
	
															<a class="page-far-right"
																 href="<c:url value="/schedule/pageno=${total}"/>" ><img style="width:10px;height:12px;top:-1px;position:relative" src="${pageContext.servletContext.contextPath}/resources/assets/images/next.png"></a>
														</c:if>
													</c:if>
													</td>

												</tr>
										</tbody>
										</table>
										</c:if>
                                    
                                    
                                    
							  </div>
							</div>
                    </div>
                  </div>
       <!--End Form-->
</body>
</html>
