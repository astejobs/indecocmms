<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
     table {
    table-layout: auto;
   
}

tbody tr:nth-child(even) {
    background-color: #f9f9f9;
}

    
    
    .pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}
    
</style>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>

<script type="text/javascript">
$(document).ready(function() {
			$( "#bId" ).change(function() {
			     var id = $(this).val();
			     var url = "${pageContext.request.contextPath}/acmv/locationlist/"+id;
			     $.get(url, function(data){
			       $('#loc').empty();
			          $('#loc').append('<option  value="-1" >Select Location</option>');
			              $.each(data,function(k,v){
				              var option = $('<option/>');
				              option.attr({ 'value': v.id }).text(v.name);
				              $('#loc').append(option);
			              }); 
				    });
				 });        
});
</script>

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
                            <a class="label-click" href="../CMMS-Dashboard.html">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="${pageContext.servletContext.contextPath}/building">Building</a>
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

	                 <c:if test="${not empty success}">
                         <div class="success">${success}</div> 
					</c:if>
					<c:if test="${not empty fail}">
                         <div class="error">${fail}</div>
                    </c:if>

	                 <c:url value="/acmv/search" var="create"/>
	                 <c:url value="/acmv/update" var="editlink"/>
	                 <c:url value="/acmv/delete" var="deletelink"/>
					<form:form action="${create}" commandName="acmvSearch" method="POST"  class="form contact-form" id="contact_form"  >
                                
                                        <div  class="form-group">
                                        <form:input  type="text" path="equipmentType"  value="${ac}"  style="width:45%" class="input-md round form-control" placeholder="Equipment Type" readonly="true" />
                                        <form:input path="equipmentCode" class="input-md round form-control" placeholder="Equipment Code" />
                                        </div>
                                        
                                        
                                        <div class="form-group">  
                                            <form:input type="text" path="name" class="input-md round form-control" placeholder="Equipment Name" /><form:errors path="name" class="errors"/>
                                        </div>
                                        
                                       <div class="form-group">
                                            <form:select  path="assetSubtype" class="input-md round form-control">
                                               <form:option value="">Asset Sub Type</form:option>
                                               <form:options items="${subSystems}" itemLabel="assetSubTypeName"
											itemValue="id" />
                                            </form:select><form:errors path="assetSubtype" class="errors"/>
                                        </div> 
                                        
                                        
                                <div class="form-group">
                                            <form:select path="building" id="bId" class="input-md round form-control" >
                                               <form:option value="">  Building</form:option>
                                               <c:forEach items="${buildingList}" var="building">
									<form:option value="${building.id}">${building.name}</form:option>
								                 </c:forEach>
                                            </form:select><form:errors path="building" class="errors"/>
                                        </div>
                                        <div class="form-group">
                                            <form:select  path="location" id="loc" class="input-md round form-control" placeholder="Location">
                                              <form:option value="">Select any  Location</form:option>
                                               <form:options items="${locationList}" itemLabel="name" itemValue="id"/>
                                            </form:select><form:errors path="location" class="errors"/>
                                        </div>
                                        
                                      
                                        
                                         <div class="form-group">
                                         
                                         <form:input path="servingArea"   style="width:45%" class="input-md round form-control" placeholder="Servicing Area"/>
                                          
                                        </div>
                                        
                                        <input type="submit"  value="Search"/>
                                     </form:form>
                                     
                                     <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                  </h2>
                    <div class="form-group">
                        <table class="table table-stripped" id="tblResult">
                            <thead colspan="100%">
                              
                                    <th class="hed">Equipment Type</th>
                                    <th class="hed">Equipment SubType</th>
                                    <th class="hed">Equipment Name</th>
                                    <th class="hed">Building</th>
                                    <th class="hed">Location</th>
                                    <th class="hed">Model Number</th>
                                    <th class="hed">Actions</th>
                                    
                                </thead>
                                
                                <tbody>
                                <c:forEach  var="acmvlist" items="${listSearch}" >
                                <tr>
                                <td>${acmvlist.equipmentType}</td>
                                <td>${acmvlist.assetSubtype.assetSubTypeName}</td>
                                <td>${acmvlist.name}</td>
                                <td>${acmvlist.building.name}</td>
                                <td>${acmvlist.location.name}</td>
                                <td>${acmvlist.modelNo}</td>
                                
                                <td>
    <a id="edit" href="${editlink}/${acmvlist.id }" style="cursor:pointer"><i class="fa fa-edit"> </i></a>
                                &nbsp;
   <a id="delete" href="${deletelink}/${acmvlist.id }" style="cursor:pointer"><i  class="fa fa-trash"/></i> </a></td>
                                </tr>
                                
                                
                                
                                
                                </c:forEach>
                           
                            </tbody>
                            <tfoot>
                                <tr  >
                            <td colspan="100%">
                     
                               		<c:if test="${1<total}">
									<c:choose>
										<c:when test="${total>5}">
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
											<tr style=" background-color: #fff;">
											 <td class="wid" style="border-style:hidden;">
											 
				                     <ul class="pagination pagination-sm" style="margin-left: 36%;"  >								 
											 <c:if test="${page>1}">

												<li>		<a class="page-far-left"
															href="<c:url value="/acmv/pageno=1"/>"><<</a> </li>

										<li>				<a class="page-left"
														   href="<c:url value="/acmv/pageno=${page-1}"/>">< </a> </li>

													</c:if>
													
													
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																<li class="active" ><a>${i}</a></li>
																</c:when>
																<c:otherwise>
															<li>		<a class=""
																		href="<c:url value="/acmv/pageno=${i}"/>"
																		>${i}</a>  </li>
																</c:otherwise>
															</c:choose>
														</c:forEach>

														
													 <c:if test="${page<total}">
														

														 <c:if test="${page<total}">
														<li>	<a class="page-right" 
															 	href="<c:url value="/acmv/pageno=${page+1}"/>">> </a> </li>
	
														<li>	<a class="page-far-right"
																 href="<c:url value="/acmv/pageno=${total}"/>" >>></a> </li>
														</c:if>
													</c:if>
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
		     </div>
		   </div>
		</div>
</body>
        



