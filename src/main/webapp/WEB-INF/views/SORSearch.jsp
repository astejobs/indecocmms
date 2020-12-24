<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
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
    
    table td, th{
    white-space: nowrap; 
    text-overflow:ellipsis; 
    overflow: hidden;
     max-width:1px;
   
    }
</style>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>




<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/SOR.js"></script>

<body class="appear-animate">

       <!-- Head Section -->
        <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">SOR SEARCH</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="">SOR Search</a>
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
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                        Search Criteria
                    </h2>
             <c:url value="/SOR/search" var="submit"></c:url>
                         <form:form  action="${submit}" method="post"  class="form contact-form" modelAttribute="SORSearch" commandName="SORSearch" id="contact_form">
                             	 <div class="form-group">

                                        <c:if test="${not empty success}">
                                         <div class="success">${success}</div>
										</c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
										
                   				</div>
                             	 <input type="hidden" value="${pageContext.request.contextPath}" id="contextPath"/>
                             	
                                <div class="form-group">
           		                     <form:select path="equipmentType" id="EqType" class="input-md round form-control" style="width:49%">
                                     <form:option value="">Select Equipment Type</form:option>
                                     <form:options items="${AssetTypeList}"  itemValue="assetTypeName" itemLabel="assetTypeName"></form:options>
        	                         </form:select>    
        	                    </div>
                             	<div class="form-group">   
                                    <form:select path="equipmentName"  id="EqName" class="input-md round form-control" style="width:49%">
                          			<form:option value="">Select Equipment </form:option>
                                    <form:options items="${eqpNameList}" itemValue="name" itemLabel="name"></form:options>                   
                                    </form:select> 
                               </div>
                               <div class="form-group">
                                     <form:input type="text" path="itemCode" name="item_code" id="item_code" class="input-md round form-control" placeholder="Item Code" />
                               </div>
                               <div class="form-group">
                                    <form:input type="text" path="unit" name="unit" id="unit" class="input-md round form-control" placeholder="Unit" />
                               </div>
                               <div class="form-group">
                                    <form:input type="text" path="rate" name="rate" id="rate" class="input-md round form-control" placeholder="Rate" />
                                </div>
                               <div class="form-group">
                                     <input type="submit" value="Search" />
                              </div>
 			                  <div class="form-group">
            		            <c:url value="/SOR/edit" var="editlink"/>
	                            <table class="table table-stripped" id="tblResult">
	                            <thead>
	                                <tr>
	                                                       
	                                    <th data-toggle="tooltip" data-placement="bottom" title="Equip Type" >Equip Type</th>
	                                    <th data-toggle="tooltip" data-placement="bottom" title="Equip Name">Equip Name</th>
	                                    <th data-toggle="tooltip" data-placement="bottom" title="Item Code">Item Code</th>
	                                    <th data-toggle="tooltip" data-placement="bottom" title="Description">Description</th>
	                                    <th data-toggle="tooltip" data-placement="bottom" title="Unit">Unit</th>
	                                    <th data-toggle="tooltip" data-placement="bottom" title="JRate">Rate</th>                         
	                                    <th data-toggle="tooltip" data-placement="bottom" title="Actions">Actions</th>
	                                </tr>
	                            </thead>
	                            <tbody>
	                                
	                                <c:forEach items="${SORList}" var="SORList">
	                                <tr>
	                          
	                                <td data-toggle="tooltip" data-placement="bottom" title="Actions"><input type="hidden" name="p" value="${page}"/>
	                                ${SORList.equipmentType}</td>
					
												
											<td data-toggle="tooltip" data-placement="bottom" title="${SORList.equipmentName}">${SORList.equipmentName}</td>
											 <td data-toggle="tooltip" data-placement="bottom" title="${SORList.itemCode}">${SORList.itemCode}</td>	
											<td data-toggle="tooltip" data-placement="bottom" title="${SORList.description}">	${SORList.description}</td>
	
												
												<td data-toggle="tooltip" data-placement="bottom" title="${SORList.unit}">${SORList.unit}</td>
											 	<td data-toggle="tooltip" data-placement="bottom" title="${SORList.rate}">${SORList.rate}</td> 
												
												
											
												<c:url value="/SOR/delete" var="deletelink"/>
	               
									<td><a id="delete" href="${deletelink}/${SORList.id }" style="cursor:pointer"><i class="fa fa-trash" ></i></a>
							
											
												<a id="edit" href="${editlink}/${SORList.id }" style="cursor:pointer"><i class="fa fa-edit" ></i></a></td>
										  </tr>
									</c:forEach>
									<tfoot>
									
	                          
	                            </tbody>
	                            
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
					<ul class="pagination pagination-sm" style=" position: relative; left:340px; ">							 
												 <c:if test="${page>1}">
	
												<li>			<a class="page-far-left"
																href="<c:url value="/SOR/pageno=1"/>"><<</a>  </li>
	
		<li>	<a class="page-left"  href="<c:url value="/SOR/pageno=${page-1}"/>">< </a> </li>
	
														</c:if>
														
														
															<c:forEach var="i" begin="${from}" end="${to}">
																
																<c:choose>
																	<c:when test="${i==page}">
																			<li class="active" ><a>${i}</a></li>
																	</c:when>
																	<c:otherwise>
								<li>	<a class=""href="<c:url value="/SOR/pageno=${i}"/>">${i}</a> </li>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
	
														
														
	
															 <c:if test="${page<total}">
															<li>	<a class="page-right" 
																 	href="<c:url value="/SOR/pageno=${page+1}"/>">>> </a> </li>
		
												<li>				<a class="page-far-right"
																	 href="<c:url value="/SOR/pageno=${total}"/>" >></a>
															</c:if>
														
														</ul>
														</td>
	
													</tr>
													<tfoot>
											</tbody>
										</table>
										</c:if>
										</table>
								</div>
						</form:form>
					</div>
			</div>
	</div>
</body>
</html>