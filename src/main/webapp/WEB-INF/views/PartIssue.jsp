<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
table {
	table-layout: auto;
}

form.widd{
    width:200px;

}



.al   {
    border: none;
    background-color: #0754a4;
    color: #fff;
    padding: 7px 9px;
    border-radius: 0px;
   text-decoration: none;

}
.al:hover {
          color:#FFF;
          text-decoration: none;
}


input[type="submit"] {
	border: none;
	background-color: #0754a4;
	color: #fff;
	padding: 7px 9px;
	border-radius: 0px;
}

.tabs td,th {
	white-space: nowrap;
	word-wrap: break-word;
	text-overflow: ellipsis;
	overflow: hidden;
	max-width: 1px;
	text-align: justify;
}
</style>
<body class="appear-animate">
	<!-- Head Section -->
	
	<section class="small-section bg-dark-alfa-30 parallax-2"
		style="margin-bottom:-70px;position:relative"
		data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
	<div class="relative container align-left">
		<div class="row">
			<div class="col-md-8">
				<h1 class="hs-line-11 font-alt mb-10 mb-xs-0"
					style="margin-top: 5px">Issue Part</h1>
			</div>

			<div class="col-md-4 mt-30">
				<div class="mod-breadcrumbs font-alt align-right">
					<a class="label-click"
						href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a>&nbsp;/&nbsp;<a
						class="label-click" href=""> Issue Part</a>
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
				<div class="clearfix">
					<div>
					
						<h2 class="section-title font-alt align-left mb-70 mb-sm-40"
							style="margin-bottom: 20px; position: relative; border-bottom: 1px dashed #808080">
							Issue Part</h2>
							 <c:if test="${not empty success}">
                                         <div class="success">${success}</div>
										</c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
                   
						<div class="form-group">
							<table class="table table-stripped ftable tabs" id="tblResult">
								<thead>
									<tr>
										
										<th  data-toggle="tooltip" data-placement="bottom" title="Warehouse">Warehouse</th>
										<th  data-toggle="tooltip" data-placement="bottom" title="Part Name">Part Name</th>
										<th data-breakpoints="all" data-toggle="tooltip" data-placement="bottom" title="Part Description" >Part Desc.</th>
										<th  data-toggle="tooltip" data-placement="bottom" title="Manufacturer" data-breakpoints="all">Manufacturer</th>
										<th data-toggle="tooltip" data-placement="bottom" title="Vendor" data-breakpoints="all">Vendor</th>
										<th  data-toggle="tooltip" data-placement="bottom" title="Fault Report/ PM Task">Fault Code / PM Task</th>
										<th  data-toggle="tooltip" data-placement="bottom" title="Reserved By">Reserved By</th>
										<th  data-toggle="tooltip" data-placement="bottom" title="Reserved Qty">Reserved Qty</th>
										<th  data-toggle="tooltip" data-placement="bottom" title="Reserved Date">Reserved Date</th>
										<th  data-toggle="tooltip" data-placement="bottom" title="Issued Qty">Issued Qty</th>
										<th  data-toggle="tooltip" data-placement="bottom" title="Pending Qty">Pending Qty</th>
										<th  data-toggle="tooltip" data-placement="bottom" title="Status">Status</th>
										<th  data-toggle="tooltip" data-placement="bottom" title="Actions">Actions</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${reservedList}" var="partTransaction">
										<tr>
											
											<td data-toggle="tooltip" data-placement="bottom" title="${partTransaction.partsInWarehouse.warehouse.name}">${partTransaction.partsInWarehouse.warehouse.name}</td>
											<td data-toggle="tooltip" data-placement="bottom" title="${partTransaction.partsInWarehouse.part.partType.partName}">${partTransaction.partsInWarehouse.part.partType.partName}</td>
											<td data-toggle="tooltip" data-placement="bottom" title="${partTransaction.partsInWarehouse.part.description}">${partTransaction.partsInWarehouse.part.description}</td>
											<td data-toggle="tooltip" data-placement="bottom" title="${partTransaction.partsInWarehouse.part.manufacturer.name}">${partTransaction.partsInWarehouse.part.manufacturer.name}</td>
											<td data-toggle="tooltip" data-placement="bottom" title="${partTransaction.partsInWarehouse.part.vendor.vendorName}">${partTransaction.partsInWarehouse.part.vendor.vendorName}</td>
											<td data-toggle="tooltip" data-placement="bottom" title="${partTransaction.reportTaskId}">${partTransaction.reportTaskId}</td>
											
											<td data-toggle="tooltip" data-placement="bottom" title="${partTransaction.reservedBy.firstName}&nbsp;${partTransaction.reservedBy.lastName}">${partTransaction.reservedBy.firstName}&nbsp;${partTransaction.reservedBy.lastName}</td>
											<td data-toggle="tooltip" data-placement="bottom" title="${partTransaction.reservedQuantity}">${partTransaction.reservedQuantity}</td>
											<fmt:formatDate value="${partTransaction.reservedDate}"
												var="reservedDate" pattern="dd-MM-yyyy" />
											<td data-toggle="tooltip" data-placement="bottom" title="${reservedDate}">${reservedDate}</td>
											<td ><c:if
													test="${partTransaction.issuedQuantity ne null}">${partTransaction.issuedQuantity}</c:if>
												<c:if test="${partTransaction.issuedQuantity eq null}">0.0</c:if></td>
											<td data-toggle="tooltip" data-placement="bottom" title="${partTransaction.pendingQuantity}">${partTransaction.pendingQuantity}</td>
											<td data-toggle="tooltip" data-placement="bottom" title="${partTransaction.pendingQuantity}">${partTransaction.status}</td>
											<td><a type="button" id="issue"
												quantity="${partTransaction.reservedQuantity}"
												issuedQuantity="${partTransaction.issuedQuantity}"
												pendingQuantity="${partTransaction.pendingQuantity}"
												value="${partTransaction.id}" 
												
												class="iss al">Issue</a></td>
												
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<div id="issuepart" class="form-group">
							<table class="table table-stripped tablee ap" id="tbl2"
								style="display: none;">
								<thead>
									<tr>
										<th data-toggle="tooltip" data-placement="bottom" title="Part Name">Part Name</th>
										<th data-toggle="tooltip" data-placement="bottom" title="Part Desc" >Part Desc</th>
										<th data-toggle="tooltip" data-placement="bottom" title="Batch No">Batch No</th>
										<th data-breakpoints="all" data-toggle="tooltip" data-placement="bottom" title="Available Qty">Available Qty</th>
										<th data-toggle="tooltip" data-placement="bottom" title="Unit Cost">Unit Cost</th>
										<th data-toggle="tooltip" data-placement="bottom" title="Reserved Qty">Reserved Qty</th>
										<th data-toggle="tooltip" data-placement="bottom" title="Issued Qty">Issued Qty</th>
										<th data-toggle="tooltip" data-placement="bottom" title="Pending Qty">Pending Qty</th>
										<th data-toggle="tooltip" data-placement="bottom" title="Status">Status</th>
										<th  data-toggle="tooltip" data-placement="bottom" title="Qty to be Issued"> Qty to be Issued</th>
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- End Contact Form -->
	</div>
	<script type="text/javascript">
	   $(document).ready(function(){
		   $(document).on('click','[id=issue]',
					function(e) {
						var i = e.target;
						var is = $(e.target);
						var quantity = $(this).attr('quantity');
						var issuedQuantity = $(this).attr('issuedQuantity');
						if (issuedQuantity == "")
							issuedQuantity = 0.0;
						var pendingQuantity = $(this).attr(
								'pendingQuantity');
						var id = $(this).attr('value');
						var url = "${pageContext.servletContext.contextPath}/inventory/parttransaction/"
								+ id;
						$
								.get(
										url,
										function(data) {
											$("#tbl2").hide();
											$("#tbl2").show();
											//	is.attr('disabled',true);
											console.log(is);
											is.css({
												'pointer-events' : 'none'
											});
											//  $("#issuepart").append('<table class="table table-stripped" id="tbl2"><thead><tr><th>Part Name</th><th>Batch No</th><th>Available Quantity</th><th>Unit Cost</th><th>Quantity to be Issued</th><th>Actions</th>');
											for (i = 0; i < data.length; i++) {
												$("#tbl2 tbody")
														.append(
																"<tr><td data-toggle='tooltip' data-placement='bottom' title='"+data[i].partsInWarehouse.part.partType.partName+"'>"
																		+ data[i].partsInWarehouse.part.partType.partName
																		+ "</td><td data-toggle='tooltip' data-placement='bottom' title='"+data[i].partsInWarehouse.part.description+"'>"
																		+ data[i].partsInWarehouse.part.description
																		+ "</td><td data-toggle='tooltip' data-placement='bottom' title='"+data[i].batchNo+"'>"
																		+ data[i].batchNo
																		+ "</td><td data-toggle='tooltip' data-placement='bottom' title='"+data[i].quantity+"'>"
																		+ data[i].quantity
																		+ "</td><td data-toggle='tooltip' data-placement='bottom' title='"+data[i].unitCost+"'>"
																		+ data[i].unitCost
																		+ "</td><td data-toggle='tooltip' data-placement='bottom' title=''>"
																		+ quantity
																		+ "</td><td  data-toggle='tooltip' data-placement='bottom' title=''>"
																		+ issuedQuantity
																		+ "</td><td data-toggle='tooltip' data-placement='bottom' title=''>"
																		+ pendingQuantity
																		+ "</td>"
																		+"<td> Status"
																		+"</td>"
																		+ "<td  ><form class='widd' data-bvalidator-validate='true' novalidate='novalidate' action='${pageContext.servletContext.contextPath}/inventory/issue' method='post'>"
																		+ "<input data-bvalidator='required' style='width:90px' type='text' name='issuedQuantity'/> &nbsp; &nbsp;<input type='hidden' name='batch' value='"+data[i].id+"'/> <input type='hidden' name='id' value='"+id+"'/>"
																		+ "<input  type='submit' value='issue' >"
																		+ "<form>"
																		+ "</td>"
																		+ "</tr>");

											}
											  console.log($('form').bValidator());
										});
					
						
							
							
					
						
						
					});
	
		   
	   });
	
	
		
		
		

		jQuery(function() {
			$('.ftable').footable();
		});
		
		 $(document).ready(function () {
				// alert("YYY")
				 console.log("YYY");
			        console.log($('form').bValidator());
			    });
	</script>
</body>
</html>
