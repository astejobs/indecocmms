<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>%>
<<style>
table {
           table-layout:auto;
           margin-top:10px;
}
</style>

<script src="${pageContext.servletContext.contextPath}/resources/assets/js/datetimepicker_css.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#sysId").change(function() {
        var value = $(this).val();  
		
				      url="${pageContext.servletContext.contextPath}/predictive/getsubtype/"+value;
				      
				      
				      
				      $.getJSON(url,{id:$(this).val()},function(data){
				    	
				    	  
				    	  var add=$("#subsysId");
				    
				    	  add.find('option').remove();

				    	  $('#subsysId').append('<option  value="-1" >SELECT Equipment Subtype </option>');
				    	  
				    	  for(var i=0;i<data.length;i++){
				    	 
				    		$("#subsysId").append('<option value="' + data[i].id+ '">' + data[i].assetSubTypeName+ '</option>');
				          
				    	  }
				  
				    	
				    	 
				        });
	});  
				      
				      $("#builId").change(function() {
							
					      var value = $(this).val();    
					      url="${pageContext.servletContext.contextPath}/predictive/getLocation/"+value;
					  
					     
					      $.getJSON(url,{id:$(this).val()},function(data){
					    	
					    	  
					       
					    
					    	  var add=$("#locId");
					    
					    	  add.find('option').remove();
					    	  

					    	  $('#locId').append('<option  value="-1" >SELECT Location </option>');
					    	  
					    	  for(var i=0;i<data.length;i++){
					    	 
					    		$("#locId").append('<option value="' + data[i].id+ '">' + data[i].name+ '</option>');
					          
					    	  }
					  
					    	
					    	 
					        });
				      });
				      

});
</script>

		<script type="text/javascript">

        function changeParent(id) {
        	//alert('ayaaa');
        	var option = document.createElement("option");
        	option.text = document.getElementById(id).innerHTML;
        	option.value = id;
        	var select =  window.opener.document.getElementById('equipment');
        	select.remove(0);
        	select.appendChild(option);
        	window.close();
           
        }
</script>



<style>tr td {
      padding-top:10px;
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
 
 </style>
<html>
<body class="appear-animate">
  <!-- End Navigation panel -->
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
        <!-- End Head Section -->
        
       <%--  <div style="height:100px;position:relative"></div>
			 <div class="container" style="width:100%;">
			    <div class="row">
			        <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
				  		   <jsp:include page="/WEB-INF/views/menu.jsp" />
				     </div> --%>
			    <div class="col-xs-12 col-sm-12 col-lg-12 col-md-12" style="margin-top:100px;">
               
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                     Search Equipment
                    </h2>
                   
		              <c:if test="${not empty success}">
		                   <div class="success">${success}</div>
		              </c:if>
					  <c:if test="${not empty fail}">
		                    <div class="error">${fail}</div>
		              </c:if>
		              
		                <c:url value="/predictive/equipmentSearch" var="Search"/>
		              
		            <form:form action ="${Search}"  commandName="equipmentSearchCriteria" class="form contact-form" id="contact_form">
		              <div class="form-group">
		              <form:hidden path="workspace"/>
		              <form:select path="equipmentType" id="sysId" name="sysId" class="input-md round form-control" style="width:49%">
                                                
								          		< <form:option value="">Select Equipment</form:option>
                                     <form:options items="${types}"  itemValue="assetTypeName" itemLabel="assetTypeName"></form:options>
        	                         </form:select> 
                                       </div>
                                        <div class="form-group">
                                         
                                            <form:select path="assetSubtype" id="subsysId" name="subsysId" class="input-md round form-control" style="width:49%">
                                                <form:option  value="">Select Equipment Sub Type</form:option>
                                            
								          		<form:option value="">Select Sub-System</form:option>
					                 <form:options items="${subTypeList}"  itemValue="id" itemLabel="assetSubTypeName"></form:options>
								
						     	</form:select> 
                                        </div>
                                        
                                       
                                        <div class="form-group">
                                            <form:select path="building" id="builId" class="input-md round form-control">
                                               <form:option value="">Select Building</form:option>
								<c:forEach items="${buildings}" var="building">
									<form:option value="${building.id}">${building.name}</form:option>
								</c:forEach>
							</form:select>
                                        </div>
                                        <div class="form-group">
                                            <form:select  class="input-md round form-control"  path="location" id="locId" name="locId">
                                            <form:option value="">Select Location</form:option>
								
								 <form:options items="${locationList}"  itemValue="id" itemLabel="name"></form:options>
								
                                        </form:select>
                                        </div>
                                        
                                        <div class="form-group"></div>
							<input type="submit" name="submit" id="submit"  value="Search"
								id="submitbutton">
								
									<div class="form-group">
            		            <c:url value="/SOR/edit" var="editlink"/>
	                            <table class="table table-stripped" id="tblResult">
	                            <thead>
	                                <tr>
	                                    <th>Equipment Name</th>                 
	                                    <th>Equipment Type</th>
	                                    <th>Equipment SubType</th>
	                                    <th>Building</th>
	                                    <th>Location</th>
	                                                            
	                                    <th>Actions</th>
	                                </tr>
	                            </thead>
	                            <tbody>
	                                
	                                <c:forEach items="${equipmentList}" var="equipment">
	                                <tr>
	          <input type="hidden" value="${equipment.id}"name="id" /><td><a href="#" id="${equipment.id}" onclick="javascript:changeParent(this.id)" >${equipment.name}</a>                
	                          
	 
										
	                                <td>${equipment.type}</td>
	                              <td>  ${equipment.assetSubtype.assetSubTypeName}</td>
					
												
											
											 <td>${equipment.building.name}</td>	
											<td>	${equipment.location.name}</td>
	
												
												
											 	
												
												
											
												<c:url value="/SOR/delete" var="deletelink"/>
	               
									<td><a id="delete" href="${deletelink}/${equipment.id }" style="cursor:pointer"><i class="fa fa-trash"> </i></a>
	
												<a id="edit" href="${editlink}/${equipment.id }" style="cursor:pointer"><i class="fa fa-edit"> </i></a></td>
										  </tr>
									</c:forEach>
	                               <td><input type="hidden" name="p" value="${page}"/>
	                              
	                            </tbody>
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
						 <ul class="pagination pagination-sm" style=" position: relative; left: 300px; " >						 
												 <c:if test="${page>1}">
	
											<li>				<a class="page-far-left"
																href="<c:url value="/SOR/pageno=1"/>"><<</a>  </li>
	
															<a class="page-left"
															   href="<c:url value="/SOR/pageno=${page-1}"/>">< </a>
	
														</c:if>
														
														
															<c:forEach var="i" begin="${from}" end="${to}">
																
																<c:choose>
																	<c:when test="${i==page}">
																			<li class="active" ><a>${i}</a></li>
																	</c:when>
																	<c:otherwise>
																	<li>	<a class=""
																			href="<c:url value="/SOR/pageno=${i}"/>"
																			>${i}</a>   </li>
																	</c:otherwise>
																</c:choose>
															</c:forEach>
	
															
														
															 <c:if test="${page<total}">
															<li>	<a class="page-right" 
																 	href="<c:url value="/SOR/pageno=${page+1}"/>">> </a>   </li>
		
															<li>	<a class="page-far-right"
																	 href="<c:url value="/SOR/pageno=${total}"/>" >>></a> </li>
															</c:if>
														</ul>
														</td>
	
													</tr>
											</tbody>
										</table>
										</c:if>
										</table>
								</div>
						
					</div>
					
				
						</form:form>
					</body>
</html>