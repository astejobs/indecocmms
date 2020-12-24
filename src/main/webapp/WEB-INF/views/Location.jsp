<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
function printImg() {
  pwin = window.open(document.getElementById("mainImg").src,'_blank','height=500,width=600');
  pwin.onload = function () {pwin.print();

 }
}
</script>
<body class="appear-animate">
<style> 
.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}


.pagination {
    display: inline-block;
    padding-left: 0;
    margin: -24px 0;
    border-radius: 4px;
}

table{

   table-layout:auto;
}

.bac{
          background-color: #FFF;
          border-top: 0px solid #FFF;
}

</style>

       <!-- Head Section -->
       <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Location</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
        
  <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                            Location Details
                                        </h2>
                                        <c:url value="/location/delete" var="delete"/>
                          			  <form:form action="${delete}" class="form contact-form" id="contact_form">
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
                                                        <th ><input  id="checkall" type="checkbox" /></th>
                                                        <th >Location</th>
                                                        <th >Building ID</th>
                                                        <th >Location Desc</th>
                                                        <th >Modify</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                   <c:url value="/location/edit" var="editlink"/>
                                              	   <c:forEach items="${locationList}" var="location">
                                                    <tr>
                                                    	<td><input type="checkbox" value="${location.id }" name="id"/></td>
                                                    	<td>${location.name}</td>
                                                    	<td>${location.building.name }</td>
                                                        <td>${location.description}</td>
                                                        
                                                        <td><a id="edit" href="${editlink}/${location.id }/${page}" style="cursor:pointer"><img style="width:20px" src="${pageContext.servletContext.contextPath}/resources/assets/images/edit.png" /></a></td>
                                                    </tr>
                                                 </c:forEach> 
                                                 <tr><td colspan="100" class="bac" style="border-bottom: 1px solid #FFF"> <div class="form-group">
                                            <input type="submit" id="submit" class="btn" value="Delete" />
                                        </div> </td> </tr>
                                                </tbody>
                                                       <tfoot>
                               <tr>
                               		
                               		<td colspan="100">
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
											 <ul class="pagination pagination-sm" style="
    position: relative;
    left: 350px;
" >
											 <c:if test="${page>1}">
												

										<li><a class="page-far-left"
															href="<c:url value="/location/pageno=1"/>"><<</a>  </li>
									<li>					<a class="page-left"
														  href="<c:url value="/location/pageno=${page-1}"/>">&nbsp;<</a> </li>
													</c:if>
													
													
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																		<li class="active" ><a>${i}</a></li>
																</c:when>
																<c:otherwise>
															<li> <a class=""
																		href="<c:url value="/location/pageno=${i}"/>"
																		>${i}</a> </li>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													 <c:if test="${page<total}">
													<li>			<a class="page-right" style="
														 	href="<c:url value="/location/pageno=${page+1}"/>">&nbsp;> </a> </li>
											<li>				<a class="page-far-right"
														    href="<c:url value="/location/pageno=${total}"/>">>></a> </li>
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
                                       
                                        <div>
                               
								
                                  </div>
                                        </form:form>
                                      <c:choose>
											<c:when test="${not empty edit}">
												<c:set value="/location/update" var="action"/>
												<c:set var="caption" value="Update" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
		                                            Edit Location
		                                        </h2>
											</c:when>
											<c:otherwise>
												 <c:set value="/location/add" var="action"/>
												 <c:set var="caption" value="Add" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                            		Add Location
                                        		</h2>
											</c:otherwise>
										</c:choose>
										<c:url value="${action}" var="submit"/>
                                       <form:form action="${submit}" commandName="location" method="post">
                                        <form:hidden path="id"/> 
                                        <input type="hidden" name="p" value="${page}"/>
                                          <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Location ID</span>
                                            <form:input type="text" path="name" id="locationid" class="input-md round form-control" placeholder="Location ID"/>
                                        	<form:errors class="err" path="name" />
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Building ID</span>
                                            <form:select path="building"  class="input-md round form-control">
                                                <form:option value="">Select Building ID</form:option>
                                                <form:options items="${buildingList}" itemValue="id" itemLabel="name"/>
                                            </form:select>
                                            <form:errors class="err" path="building"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Location Description</span>
                                            <form:textarea path="description" class="input-md round form-control" placeholder="Location Description" />
                                             <form:errors class="err" path="description"/>
                                        </div>
                                        
                                        <c:if test="${not empty edit}">
                                          <div class="form-group">
                                          <span class="input-group-addon label-left" id="basic-addon2">Location QR Code</span>
                                      	<form:input type="hidden" path="locationCode"></form:input>
											 <img id="mainImg"
											src="${pageContext.servletContext.contextPath}/location/getqrimage/${location.locationCode}"
											style="height: 250px; width: 250px;  position: relative;top: 10px;" />
                                            &nbsp;&nbsp;&nbsp;
                                      <div class="form-group" style="margin-top:10px">
                                            <a href="${pageContext.servletContext.contextPath}/location/getqrimage/${location.locationCode}"  download="${location.name}" class="btn-qr-code" >
                                            	<img style="width:40px" src="${pageContext.servletContext.contextPath}/resources/assets/images/download-qr.png" />
                                            </a>
                                       
											<a onclick="printImg()" class="btn-qr-code">
												<img style="width:40px" src="${pageContext.servletContext.contextPath}/resources/assets/images/print-qr.png" />
											</a>
                                       </div>
                                        </div>  	
                                        </c:if>
                                        <div class="form-group float-right">
                                            <input type="submit" class="btn" id="submit" value="${caption}" />
                                        </div>
                                        </form:form>
                                      </div>
							</div>
						</div>
				</div>
							
        <!--End Form-->
         
 
            <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/checkall.js"></script>
</body>
</html>
