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

<body class="appear-animate">

        <!-- Head Section -->
         <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Equipment Search</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">

	                 <c:if test="${not empty success}">
                         <div class="success">${success}</div> 
					</c:if>
					<c:if test="${not empty fail}">
                         <div class="error">${fail}</div>
                    </c:if>

	                 <c:url value="/equipment/search" var="search"/>
	                 <c:url value="/equipment/edit" var="editlink"/>
	                 <c:url value="/equipment/delete" var="deletelink"/>
	                 <input type="hidden" value="${pageContext.servletContext.contextPath}" id="context">
					<form:form action="${search}" commandName="equipmentSearch" method="POST"  class="form contact-form" id="contact_form"  >
                                
                                        
 										<div class="form-group input-group">
		                                    <span class="input-group-addon label-left" id="basic-addon2">Asset Type</span>
 											<input type="hidden" name="p" value="${page}"/>
                                            <form:select  path="assetType" class="input-md round form-control" id="assetType">
                                               <form:option value="">Asset Type</form:option>
                                               <form:options items="${assetTypeList}" itemLabel="assetTypeName"
											itemValue="id" />
                                            </form:select><form:errors path="assetSubtype" class="errors"/>
                                        </div> 
                                        <div class="form-group input-group">
		                                    <span class="input-group-addon label-left" id="basic-addon2">Asset Sub Type</span>
                                            <form:select  path="assetSubtype" class="input-md round form-control" id="assetSubtype">
                                               <form:option value="">Asset Sub Type</form:option>
                                            </form:select><form:errors path="assetSubtype" class="errors"/>
                                        </div>
	                                    <div class="form-group input-group">
		                                    <span class="input-group-addon label-left" id="basic-addon2">Equipment Code</span>
	                                    	<form:input path="equipmentCode" class="input-md round form-control" placeholder="Equipment Code" />
                                        </div>
                                        <div class="form-group input-group">
		                                    <span class="input-group-addon label-left" id="basic-addon2">Equipment Name</span>
                                            <form:input type="text" path="name" class="input-md round form-control" placeholder="Equipment Name" /><form:errors path="name" class="errors"/>
                                        </div>
                                      <div class="form-group input-group">
		                                    <span class="input-group-addon label-left" id="basic-addon2">Select Building</span>
                                            <form:select path="building" id="bId" class="input-md round form-control" >
                                               <form:option value="">Building</form:option>
                                               <c:forEach items="${buildingList}" var="building">
											<form:option value="${building.id}">${building.name}</form:option>
								                 </c:forEach>
                                            </form:select><form:errors path="building" class="errors"/>
                                        </div>
                                        <div class="form-group input-group">
		                                    <span class="input-group-addon label-left" id="basic-addon2">Select Location</span>
                                            <form:select  path="location" id="loc" class="input-md round form-control" placeholder="Location">
                                              <form:option value="">Select any  Location</form:option>
                                            </form:select><form:errors path="location" class="errors"/>
                                        </div>
                                        <div class="form-group input-group">
		                                    <span class="input-group-addon label-left" id="basic-addon2">Servicing Area</span>
                                        	 <form:input path="servingArea" class="input-md round form-control" placeholder="Servicing Area"/>
                                        </div>
                                        <input type="submit" class="btn" value="Search"/>
                                     </form:form>
                                     
                                     <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                  </h2>
                    <div class="table-responsive">
                        <table class="table table-stripped" id="tblResult">
                            <thead>
                              
                                    <th class="hed">Equipment Type</th>
                                    <th class="hed">Equipment SubType</th>
                                    <th class="hed">Equipment Name</th>
                                    <th class="hed">Building</th>
                                    <th class="hed">Location</th>
                                    <th class="hed">Model Number</th>
                                    <th class="hed">Actions</th>
                                    
                                </thead>
                                
                                <tbody>
                                <c:forEach  var="equipment" items="${equipmentList}" >
                                <tr>
                                <td>${equipment.assetSubtype.assetType.assetTypeName}</td>
                                <td>${equipment.assetSubtype.assetSubTypeName}</td>
                                <td>${equipment.name}</td>
                                <td>${equipment.building.name}</td>
                                <td>${equipment.location.name}</td>
                                <td>${equipment.modelNo}</td>
                                
                                <td>
    <a id="edit" href="${editlink}/${equipment.id }" style="cursor:pointer"><i class="fa fa-edit"> </i></a>
                                &nbsp;
   <a id="delete" href="${deletelink}/${equipment.id }" style="cursor:pointer"><i  class="fa fa-trash"/></i> </a></td>
                                </tr>
                                
                                
                                
                                
                                </c:forEach>
                           
                            </tbody>
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
											<tr style=" background-color: #fff;">
											 <td class="wid" style="border-style:hidden;">
											 
				                     <ul class="pagination pagination-sm" style="margin-left: 36%;"  >								 
											 <c:if test="${page>1}">

												<li>		<a class="page-far-left"
															href="<c:url value="/equipment/pageno=1"/>"><<</a> </li>

										<li>				<a class="page-left"
														   href="<c:url value="/equipment/pageno=${page-1}"/>">< </a> </li>

													</c:if>
											
													
													
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																<li class="active" ><a>${i}</a></li>
																</c:when>
																<c:otherwise>
															<li>		<a class=""
																		href="<c:url value="/equipment/pageno=${i}"/>"
																		>${i}</a>  </li>
																</c:otherwise>
															</c:choose>
														</c:forEach>

														
													 <c:if test="${page<total}">
														

														 <c:if test="${page<total}">
														<li>	<a class="page-right" 
															 	href="<c:url value="/equipment/pageno=${page+1}"/>">> </a> </li>
	
														<li>	<a class="page-far-right"
																 href="<c:url value="/equipment/pageno=${total}"/>" >>></a> </li>
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
	</div>
</body>
        
<!--  <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>
-->
<script type="text/javascript">
$(document).ready(function() {
			$( "#bId" ).change(function() {
			     var id = $(this).val();
			      var context = $("#context").val();
			     var url = context+"/equipment/locations/"+id;
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

			$("#assetType").change(function(){
			      var id = $("#assetType").val();
			      var context = $("#context").val();
			     
				  $("#assetSubtype").empty();
		    	  $("#assetSubtype").append("<option value=''>Select Equipment SubType</option>");
			      var url = context+"/faultReport/assetSubtype/"+id;
			           $.get(url,function(data){
				        	for(i=0;i<data.length;i++){
				          	  $("#assetSubtype").append("<option value="+data[i].id+">"+data[i].assetSubTypeName+"</option>");
				             } 
			      	  }); 
			 });        
});
</script>




