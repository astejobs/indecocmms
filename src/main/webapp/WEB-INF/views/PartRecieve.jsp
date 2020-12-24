<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<style>
table {
	table-layout: auto;
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
						style="margin-top: 5px">Recieve Part</h1>
				</div>

				<div class="col-md-4 mt-30">
					<div class="mod-breadcrumbs font-alt align-right">
						<a class="label-click"
							href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a>&nbsp;/&nbsp;<a
							class="label-click"
							href="${pageContext.servletContext.contextPath}/inventory/recieve">Recieve
							part Parts</a>
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
				<form class="form contact-form" id="contact_form">
					<div class="clearfix">
						<div>
							<h2 class="section-title font-alt align-left mb-70 mb-sm-40"
								style="margin-bottom: 20px; position: relative; border-bottom: 1px dashed #808080">
								Returned Requests</h2>
							<div class="form-group">
								<table class="table table-stripped ftable" id="tblResult" data-paging="true">
									<thead>
										<tr>
											<th>Part Name</th>
											<th>Part Desc</th>
											<th>Manufacturer</th>
											<th>Vendor</th>
											<th>Fault Code / PM Task</th>
											<th>Returned Qty</th>
											<th>Returned Date</th>
											<th>Actions</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${returnedList}" var="partTransaction">
											<tr>
												<td>${partTransaction.partsInWarehouse.part.partType.partName}</td>
												<td>${partTransaction.partsInWarehouse.part.description}</td>
												<td>${partTransaction.partsInWarehouse.part.manufacturer.name}</td>
												<td>${partTransaction.partsInWarehouse.part.vendor.vendorName}</td>
												<td>${partTransaction.reportTaskId}</td>
												<td>${partTransaction.returnedQuantity}</td>
												<fmt:formatDate value="${partTransaction.returnedDate}"
													var="returnedDate" pattern="dd-MM-yyyy" />
												<td>${returnedDate}</td>
												<td><a
													href="${pageContext.servletContext.contextPath}/inventory/recieve/${partTransaction.id}"
													class="al">Recieve</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- End Contact Form -->
	</div>

        <!-- End Head Section -->
        <!---MENU-->
         




	<!-- End Footer -->


	<script>
		jQuery(function() {
			$('.ftable').footable();
		});
	</script>

</body>
</html>
