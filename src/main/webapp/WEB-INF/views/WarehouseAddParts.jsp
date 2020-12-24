
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<style>   
     table {
          table-layout:auto;
          font-size: 14px;
     }
     
      .table {
          table-layout:auto;
          font-size: 14px; 
     }
     
     .pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}
     
     .pagination>.active>a,.pagination>.active>a:focus,.pagination>.active>a:hover,.pagination>.active>span,.pagination>.active>span:focus,.pagination>.active>span:hover
	{
	z-index: 3;
	color: #fff;
	cursor: default;
	background-color: #0754a4;
	border-color: #0754a4;
}
     
     
     table th:LAST-CHILD {
     width: auto;
}
 table th:FIRST-CHILD {
     width: auto;
}

 .gr tr:nth-child(odd) {
    background-color: #f9f9f9;
}

.lr tr:nth-child(even) {
    background-color: #f9f9f9;
}


</style>


<body class="appear-animate">
 

	<!-- End Navigation panel -->
	<!-- Head Section -->
	 <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">Warehouse Add Part</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="#">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="#">Warehouse Add Part</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
	<!-- End Head Section -->
	<!---MENU-->
	<div style="height: 100px; position: relative"></div>
	<div class="container" style="width: 100%;">
		<div class="row">
			<div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
				 <jsp:include page="/WEB-INF/views/menu.jsp" />
			</div>
			<div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">

				<h2 class="section-title font-alt align-left mb-70 mb-sm-40"
					style="margin-bottom: 20px; position: relative; border-bottom: 1px dashed #808080">
					Add Parts To Warehouse</h2>
				        <c:if test="${not empty success}">
                            <div class="success">${success}</div>
						</c:if>
						<c:if test="${not empty fail}">
                            <div class="error">${fail}</div>
                        </c:if>
				<!-- Contact Form -->
				<div class="form-group">
					<label class="col-md-3">Warehouse Name:</label> <label>
						${warehouse.name}</label>
				</div>
				<div class="form-group">
					<label class="col-md-3">Warehouse Description:</label> <label>${warehouse.description}</label>
				</div>
				<div class="form-group">
					<label class="col-md-3">Location Description:</label> <label>${warehouse.location}</label>
				</div>

				<h2 class="section-title font-alt align-left mb-70 mb-sm-40"
					style="margin-bottom: 20px; position: relative; border-bottom: 1px dashed #808080">
					Current Parts in warehouse and their quantities</h2>

				<div class="form-group">
					<table class="table table-stripped" id="tblResult"
						data-paging="true">
						<thead>
							<tr>
								<th>Part Name</th>
								<th>Description</th>
								<th>Manufacturer</th>
								<th>Vendor</th>
								<th>Total Quantity</th>
								<th>Part Details</th>
								<th>Actions</th>
							</tr>
						</thead>
						<c:forEach items="${partsInWarehouseList}" var="partsInWarehouse">
							<tbody>
								<tr>
									<td>${partsInWarehouse.part.partType.partName}</td>
									<td>${partsInWarehouse.part.description}</td>
									<td>${partsInWarehouse.part.manufacturer.name}</td>
									<td>${partsInWarehouse.part.vendor.vendorName}</td>
									<td>${partsInWarehouse.totalPartQuantity}</td>
									<td  ><div class="form-group">
											<table class="table gr"  id="tblResult"
												data-paging="true">
												<thead>
													<tr>
														<th>Batch No</th>
														<th>Remaining Quant.</th>
														<th>Unit Cost</th>

													</tr>
												</thead>
												<tbody>
													<c:forEach items="${partsInWarehouse.partBatchs}" var="batch">
														<tr>
															<td>${batch.batchNo}</td>
															<td>${batch.quantity }</td>
															<td >${batch.unitCost}</td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div></td>
									<td><a href="${pageContext.servletContext.contextPath}/warehouse/${partsInWarehouse.id}/batch"><i class="fa fa-edit"></a></i></td>
								</tr>

							</tbody>
						</c:forEach>
					</table>
				</div>
				<h2 class="section-title font-alt align-left mb-70 mb-sm-40"
					style="margin-bottom: 20px; position: relative; border-bottom: 1px dashed #808080">
					Add More Parts to Warehouse</h2>
				<div class="clearfix">
					<div>
						<div class="form-group">
						<c:if test="${fn:length(partList) gt 0}">
							<c:url var="action" value="/warehouse/addparts" />
							<form:form action="${action}" method="post" 
								modelAttribute="warehouseParts"  data-bvalidator-validate="true" novalidate="novalidate" id="fa" >
								<table class="table lr" id="tblResult"
									data-paging="true">
									<thead>
										<tr>
											<th ></th>
											<th width="100px" >Part Name</th>
											<th>Description</th>
											<th>Manufacturer</th>
											<th>Vendor</th>
											<th>Location</th>
											<th>Unit Cost</th>
											<th>Total Quantity</th>
											<th>Batch No</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${partList}" var="part" varStatus="loop">											
											<tr id="${loop.index}" >
												<td><input type="checkbox" value="${part.id}" class="remd"  /></td>
												<td>${part.partType.partName}</td>
												<td>${part.description}</td>
												<td>${part.manufacturer.name}</td>
												<td>${part.vendor.vendorName}</td>
												<td><input type="text" data-bvalidator="required" id="location" class="input-md round form-control" /></td>
												<td><input type="text" data-bvalidator="required" id="ucost" class="input-md round form-control" /></td>
												<td><input type="text" data-bvalidator="required" id="tquantity" class="input-md round form-control" /></td>
												<td><input type="text" data-bvalidator="required" id="bno" class="input-md round form-control" /> </td>
												<td class="hidd"> <input type="hidden" value="${warehouse.id}" /> </td>
											</tr>
										</c:forEach>

									</tbody>
								</table>
								<input type="submit" value="Submit" style="position:relative;bottom:25px;" />
							  </form:form>
							</c:if>
							<c:if test="${fn:length(partList) le 0}">
									<label>No Part Available</label>
							</c:if>
						</div>
					</div>
				</div>

				<!-- End Contact Form -->
			</div>

		</div>

		<!--End Form-->
		<!-- Foter -->

	</div>
	<!-- End Page Wrap -->

	<script>
	jQuery(function($) {
		$('.lr').footable();
	});
	

		
		
		
		 $(document).ready(function(){
			
			 $(document).on("change",".remd",function(e){
				  var checked = $(this).is(':checked');
				 		
				var attrib= "partsInWarehouses["+ +"].warehouse";
		    	   console.log("change")
		    	  
		    	    var numberOfChecked = $('input:checkbox:checked').length;
		    	    console.log(numberOfChecked);
					var loc=0;
					 var roww;
					$("#tblResult tbody").children().find('input:checkbox:checked').each(function(i){
						console.log("Start");
						var id=$(this).parents('tr').attr("id");
						  console.log(id);
						//  console.log($("#warehouseParts tbody").children().children());
						  console.log("HIDDEN");
						  roww =  $(this).parents('tr');
						
						 console.log( ($(this).parents(":input([type=hidden])")));
						$(this).parent().nextAll().find(':input').each(function(i) {
							if(i==0) {
		                  console.log( $(this).prop("name","partsInWarehouses["+loc +"].partBatchs[0].location"));
							}
							if(i==1) {
				                  console.log( $(this).prop("name","partsInWarehouses["+loc +"].partBatchs[0].unitCost"));
									}
							if(i==2) {
				                  console.log( $(this).prop("name","partsInWarehouses["+loc +"].partBatchs[0].quantity"));
									}
							if(i==3) {
				                  console.log( $(this).prop("name","partsInWarehouses["+loc +"].partBatchs[0].batchNo"));
									}
							 roww.find(".hidd input[type='hidden']").each(function(i){
								 console.log("HELL YEAH");
								 console.log( $(this));
								 console.log( $(this).attr("name","partsInWarehouses["+loc +"].warehouse"));
								
									 
								 });
							 roww.find('input:checkbox:checked').each(function(i){
								 console.log("Checking");
								 console.log( $(this));
								 console.log( $(this).attr("name","partsInWarehouses["+loc +"].part"));
								
									 
								 });
							
		                  
		              });
						loc++;
						console.log("end")
					});
					
					$("#tblResult tbody").children().find('input:checkbox:not(":checked")').each(function(i){
							console.log("Start");
							$(this).parent().nextAll().find(':input').each(function(i) {
				                  console.log($(this).removeAttr("name"));
				              });
							console.log("Where am i?")
							console.log(this);
							roww =  $(this).parents('tr');
							roww.find(".hidd input[type='hidden']").each(function(i){
								 console.log("HELL YEAH");
								 console.log( $(this));
								 console.log( $(this).removeAttr("name"));
								
									 
								 });
							 roww.find('input:checkbox:not(":checked")').each(function(i){
								 console.log("Checking");
								 console.log( $(this));
								 console.log( $(this).removeAttr("name"));
								
									 
								 });
							
							
							console.log("end")
						}); 
					
				//	$("#warehouseParts tbody").children().find('input:checkbox:not(":checked")').each(function(i){
				//		  console.log( $(this).removeProp("name"));
				//	});
											
		    	   $(e.target).parent().nextAll().find(':input :checked');
		    	   if(checked==true) {
		    		  $(e.target).parent().nextAll().find(':input').each(function(i) {
		                  console.log( $(this).prop("disabled",false));
		                
		                  
		                  
		              });
		    	
		    	   
		    	   }
		    	   else{
		    		   $(e.target).parent().nextAll().find(':input').each(function() {
			                  console.log( $(this).prop("disabled",true));
			                
			                  
			              });
		    	   }
		       });
			 (function(){
					$('.remd').trigger("change");
					
				
				//	myFunction();
					function myFunction() {
					    setInterval(function(){ 
					    	console.log("Begin")
					    	$('.remd').trigger("change"); }, 2000);
					}
					 
				})();
			 
			 $(document).on("click","li a",function(){
			//	alert("YESS")
				 $('.remd').trigger("change");
				 
			 } );
			 
			
		    
		}); 
		
		 
		 $(document).ready(function () {
			// alert("YYY")
			 console.log("YYY");
		        console.log($('form').bValidator());
		                
		    });
		
		
	</script>
</body>

</html>
