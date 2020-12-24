<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style>
table {
	table-layout: auto;
}
input[type="submit"] {
	border: none;
	background-color: #0754a4;
	color: #fff;
	padding: 7px 9px;
	border-radius: 0px;
}

.marbot {
	position: relative;
	bottom: 50;
}

.table th {
	white-space: nowrap;
	word-wrap: break-word;
	text-overflow: ellipsis;
	overflow: hidden;
	max-width: 1px;
	text-align: justify;
}


	


.al {
	border: none;
	background-color: #0754a4;
	color: #fff;
	padding: 7px 9px;
	border-radius: 0px;
	text-decoration: none;
}

.al:hover {
	color: #FFF;
	text-decoration: none;
}

.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}


.back {
	background-color: #0754a4;
	border: medium none;
	border-radius: 0;
	color: #fff;
	padding: 7px 9px;
	text-decoration: none;
}
</style>
<body class="appear-animate">

	<!-- Head Section -->

	<section class="small-section bg-dark-alfa-30 parallax-2"
		style="margin-bottom: -70px; position: relative"
		data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
		<div class="relative container align-left">
			<div class="row">
				<div class="col-md-8">
					<h1 class="hs-line-11 font-alt mb-10 mb-xs-0"
						style="margin-top: 5px">Reserve Parts</h1>
				</div>

				<div class="col-md-4 mt-30">
					<div class="mod-breadcrumbs font-alt align-right">
						<a class="label-click"
							href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a>&nbsp;/&nbsp;<a
							class="label-click" href="">Reserve Parts</a>
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
			<div class="col-xs-12 col-sm-12 col-lg-12 col-md-12">

				<h2 class="section-title font-alt align-left mb-70 mb-sm-40"
					style="margin-bottom: 20px; position: relative; border-bottom: 1px dashed #808080">
					Reserve Parts</h2>

				<!-- Contact Form -->

				<div class="clearfix">
					<div>

						<div class="form-group">
							<table class="table table-stripped" id="tblResult"
								data-paging="true">
								<thead>
									<tr>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Part Name">Part Name</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Part Description">Part Desc</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Warehouse">Warehouse</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Manufacturer" data-breakpoints="all">Manufacturer</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Vendor" data-breakpoints="all">Vendor</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Fault Report/PM Task">Fault Report/PM Task</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Reserved Qty">Reserved Qty</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Reserved Date">Reserved Date</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Status">Status</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Action">Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${reservedList}" var="partTransaction">
										<tr>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.partsInWarehouse.part.partType.partName}">${partTransaction.partsInWarehouse.part.partType.partName}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.partsInWarehouse.part.description}">${partTransaction.partsInWarehouse.part.description}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.partsInWarehouse.warehouse.name}">${partTransaction.partsInWarehouse.warehouse.name}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.partsInWarehouse.part.manufacturer.name}">${partTransaction.partsInWarehouse.part.manufacturer.name}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.partsInWarehouse.part.vendor.vendorName}">${partTransaction.partsInWarehouse.part.vendor.vendorName}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.reportTaskId}">${partTransaction.reportTaskId}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.reservedQuantity}">${partTransaction.reservedQuantity}</td>
											<fmt:formatDate value="${partTransaction.reservedDate}"
												var="reservedDate" pattern="dd-MM-yyyy" />
											<td data-toggle="tooltip" data-placement="bottom"
												title="${reservedDate}">${reservedDate}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.status}">${partTransaction.status}</td>
											<td><a
												href="${pageContext.servletContext.contextPath}/inventory/unreserve/${partTransaction.reportTaskId}/${partTransaction.id}"
												class="al">Unreserve</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
						<h2 class="section-title font-alt align-left mb-70 mb-sm-40"
							style="margin-bottom: 20px; position: relative; border-bottom: 1px dashed #808080">
							Issued Parts</h2>

						<div class="form-group">
							<table class="table table-stripped" id="tblResult"
								data-paging="true">
								<thead>
									<tr>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Part Name">Part Name</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Part Description">Part Desc</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Warehouse">Warehouse</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Manufacturer" data-breakpoints="all">Manufacturer</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Vendor" data-breakpoints="all">Vendor</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Fault Report/PM Task">Fault Report/PM Task</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Reserved Quantity">Reserved Quantity</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Reserved Date">Reserved Date</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Issued Quantity">Issued Quantity</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Issued Date">Issued Date</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Pending Qty">Pending Qty</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Status">Status</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Action">Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${issuedList}" var="partTransaction">
										<tr>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.partsInWarehouse.part.partType.partName}">${partTransaction.partsInWarehouse.part.partType.partName}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.partsInWarehouse.part.description}">${partTransaction.partsInWarehouse.part.description}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.partsInWarehouse.warehouse.name}">${partTransaction.partsInWarehouse.warehouse.name}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.partsInWarehouse.part.manufacturer.name}">${partTransaction.partsInWarehouse.part.manufacturer.name}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.partsInWarehouse.part.vendor.vendorName}">${partTransaction.partsInWarehouse.part.vendor.vendorName}</td>
											<td data-toggle="tooltip" data-placement="bottom" title="">${partTransaction.reportTaskId}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.reservedQuantity}">${partTransaction.reservedQuantity}</td>
											<fmt:formatDate value="${partTransaction.reservedDate}"
												var="reservedDate" pattern="dd-MM-yyyy" />
											<td data-toggle="tooltip" data-placement="bottom"
												title="${reservedDate}">${reservedDate}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.issuedQuantity}">${partTransaction.issuedQuantity}</td>
											<fmt:formatDate value="${partTransaction.issuedDate}"
												var="issuedDate" pattern="dd-MM-yyyy" />
											<td data-toggle="tooltip" data-placement="bottom"
												title="${issuedDate}">${issuedDate}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.pendingQuantity}">${partTransaction.pendingQuantity}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.status}">${partTransaction.status}</td>
											<td><a
												href="${pageContext.servletContext.contextPath}/inventory/recieve/${partTransaction.reportTaskId}/${partTransaction.id}"
												style="padding: 4px; background-color: green; color: #fff; text-decoration: none;">Recieve</a></td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>

						<h2 class="section-title font-alt align-left mb-70 mb-sm-40"
							style="margin-bottom: 20px; position: relative; border-bottom: 1px dashed #808080">
							Received Parts</h2>

						<div class="form-group">
							<c:url value="/inventory/return" var="action2" />
							<table class="table table-stripped " id="tblResult"
								data-paging="true">
								<thead>
									<tr>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Part Name">Part Name</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Part Description">Part Desc</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Warehouse">Warehouse</th>
										<th  data-toggle="tooltip" data-placement="bottom"
											title="Manufacturer" data-breakpoints="all">Manufacturer</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Vendor" data-breakpoints="all">Vendor</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Fault Report/PM Task">Fault Report/PM Task</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Reserved Quantity">Reserved Quantity</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Reserved Date">Reserved Date</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Issued Quantity">Issued Quantity</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Pending Qty">Pending Qty</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Status">Status</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Action">Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${recievedList}" var="partTransaction">
										<tr>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.partsInWarehouse.part.partType.partName}">${partTransaction.partsInWarehouse.part.partType.partName}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.partsInWarehouse.part.description}">${partTransaction.partsInWarehouse.part.description}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.partsInWarehouse.warehouse.name}">${partTransaction.partsInWarehouse.warehouse.name}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.partsInWarehouse.part.manufacturer.name}">${partTransaction.partsInWarehouse.part.manufacturer.name}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.partsInWarehouse.part.vendor.vendorName}">${partTransaction.partsInWarehouse.part.vendor.vendorName}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.reportTaskId}">${partTransaction.reportTaskId}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.reservedQuantity}">${partTransaction.reservedQuantity}</td>
											<fmt:formatDate value="${partTransaction.reservedDate}"
												var="reservedDate" pattern="dd-MM-yyyy" />
											<td data-toggle="tooltip" data-placement="bottom"
												title="${reservedDate}">${reservedDate}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.issuedQuantity}">${partTransaction.issuedQuantity}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.pendingQuantity}">${partTransaction.pendingQuantity}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partTransaction.status}">${partTransaction.status}</td>
											<td><form:form action="${action2}" method="post"
													modelAttribute="partTransaction" style="width:200px;">
													<form:hidden path="id" value="${partTransaction.id}" />
													<form:input path="returnedQuantity" type="text"
														style="float: left; width:90px" />
													<form:errors path="reservedQuantity" class="err" />
													&nbsp;&nbsp;
													<input type="submit" value="Return" />
												</form:form></td>
										</tr>
									</c:forEach>

								</tbody>
							</table>
						</div>

						<h2 class="section-title font-alt align-left mb-70 mb-sm-40"
							style="margin-bottom: 20px; position: relative; border-bottom: 1px dashed #808080">
							Reserve More Parts</h2>

						<div class="form-group">
							<c:url var="action" value="/inventory/reserve" />
							<table class="table table-stripped tabla" id="tblResult"
								data-paging="true">
								<thead>
									<tr>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Part Name">Part Name</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Part Description">Part Desc</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Warehouse">Warehouse</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Manufacturer">Manufacturer</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Vendor">Vendor</th>
										<th data-toggle="tooltip" data-placement="bottom" title="Unit">Unit</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Available Quantity">Available Quantity</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Reserve Quantity">Reserve Qty</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${partsInWarehouseList}"
										var="partsInWarehouse">
										<tr>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partsInWarehouse.part.partType.partName}">${partsInWarehouse.part.partType.partName}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partsInWarehouse.part.description}">${partsInWarehouse.part.description}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partsInWarehouse.warehouse.name}">${partsInWarehouse.warehouse.name}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partsInWarehouse.part.manufacturer.name}">${partsInWarehouse.part.manufacturer.name}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partsInWarehouse.part.vendor.vendorName}">${partsInWarehouse.part.vendor.vendorName}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partsInWarehouse.part.unitOfMeasure.unitOfMeasure}">${partsInWarehouse.part.unitOfMeasure.unitOfMeasure}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="${partsInWarehouse.totalPartQuantity}">${partsInWarehouse.totalPartQuantity}</td>
											<td data-toggle="tooltip" data-placement="bottom"
												title="Action"><form:form
													data-bvalidator-validate="true" novalidate="novalidate"
													action="${action}" method="post"
													modelAttribute="partTransaction" style="width: 200px;">
													<form:hidden path="reportTaskId" value="${reportTaskId}" />
													<form:hidden path="partsInWarehouse"
														value="${partsInWarehouse.id}" />
													<form:input path="reservedQuantity" type="text"
														style="float: left;width:100px;"
														data-bvalidator="required" />
													<form:errors path="reservedQuantity" class="err" /> &nbsp;
													<input type="submit" value="Reserve" />
												</form:form></td>
										</tr>
									</c:forEach>
									
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<!-- End Contact Form -->
			</div>

		</div>
		<c:choose>
			<c:when test="${fn:contains(reportTaskId,'PMTASK')}">
				<c:set var="link" value="${pageContext.servletContext.contextPath}/task/${reportTaskId}/parts"/>
				<c:set var="Back" value="Back to PM Task"/>
			</c:when>
			<c:otherwise>
			  <c:set var="link" value="${pageContext.servletContext.contextPath}/faultReport/update/${reportTaskId}"/>
				<c:set var="Back" value="Back to Fault Report"/>
			</c:otherwise>
		</c:choose>
		<a href="${link}" class="back marbot">${Back}</a>

		<script>
			jQuery(function($) {
				$('.table').footable();
			});

			$(document).ready(function() {
				// alert("YYY")
				console.log("YYY");
				console.log($('form').bValidator());

			});
		</script>
</body>

</html>