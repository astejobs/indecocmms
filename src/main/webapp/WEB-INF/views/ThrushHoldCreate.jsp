<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--  start nav-outer-repeat................................................... END -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/assets/js/datetimepicker_css.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>

<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#add_param")
								.click(
										function() {

											var counter = $("#product_table  tbody  tr").length - 1;

											$("#product_table")
													.append(
															'<tr id="tr'+counter+'"><td><input type="text" class="baselineName" name="parameterList['+counter+'].parameter"></td><td><input class="baselineMin" name="parameterList['+counter+'].fieldUnit" type="text"></td><td style="width:20%"><input class="baselineMin" name="parameterList['+counter+'].fieldValue" type="text"></td> <td style="padding-left:10px;position:relative"><a href="javascript:void(0);" id="remCF" style="color:black"><img style="width:20px;height:20px;" src="${pageContext.servletContext.contextPath}/resources/assets/images/trash..png" /></a></td></tr>');

										});

						$("#product_table").on('click', '#remCF', function() {
							$(this).parent().parent().remove();
						});

						$("#monitor_points")
								.click(
										function() {

											var counter = $("#product_table_monitor  tbody  tr").length - 1;

											$("#product_table_monitor")
													.append(
															'<tr id="tr'+counter+'" ><td><input style="width: 100px;" type="text" class="baselineName" required="required" name="monitorPointList['+counter+'].monitorType"></td><td><select style="width: 85px;" class="MonitorClass"  name="monitorPointList[' + counter + '].predictiveMonClass" /></td><td ><input style="width: 85px;"  id="monitorPointList['+counter+'].date" name="monitorPointList['+counter+'].date" class="datepickerr" required="required" type="text"> <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal(\'monitorPointList['
																	+ counter
																	+ '].date\',\'ddMMyyyy\')" style="cursor:pointer;width:25px" /></td><td ><input style="width: 85px;" class="baselineMin" name="monitorPointList['+counter+'].unit" required="required" type="text"></td><td><input style="width: 85px;" class="baselineMin" required="required" name="monitor PointList['+counter+'].lowValue" type="text"></td><td><input style="width: 85px;" class="baselineMin" name="monitorPointList['+counter+'].highValue" required="required" type="text"></td><td ><input style="width: 85px;" class="baselineMin" name="monitorPointList['+counter+'].actualValue" required="required" type="text"></td><td><input style="width: 85px;" class="baselineMin" name="monitorPointList['+counter+'].readingDelta" required="required" type="text"></td><td style="padding-left:10px;position:relative;"><a href="javascript:void(0);" id="remCFM" style="color:black"><i style="font-size:12px;"class="fa fa-trash"></i></a></td></tr>');

											url = "${pageContext.servletContext.contextPath}/monitorclass/monitorclasslist";
											$
													.getJSON(
															url,
															{
																id : $(this)
																		.val()
															},
															function(data) {

																for (var i = 0; i < data.length; i++) {

																	$(
																			'.MonitorClass')
																			.append(
																					'<option value="' + data[i].id+ '">'
																							+ data[i].className
																							+ '</option>');

																}

															});

										});
						$("#product_table_monitor").on('click', '#remCFM',
								function() {
									$(this).parent().parent().remove();
								});

					});
</script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {

						$('#getlist')
								.click(

										function() {
											var sysid = $("#sysId").val();
											var subsysid = $("#subsysId").val();
											var builid = $("#builId").val();
											var locid = $("#locId").val();
											childWin = window
													.open(
															'${pageContext.servletContext.contextPath}/predictive/equipmentSearch/',
															'child',
															"width=500, height=600, location=yes, left=800, menubar=no, scrollbars=1, status=no, toolbar=no");
											$.triggerWindowEvent(secondwindow,
													"parentMsg1", value);
											if (window.focus) {
												childWin.focus();
											}
											('#sysidforcontroller').value = $(
													"#sysId").val();
										});

					});
</script>



<style>
tr td {
	padding-top: 10px;
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

table {
	table-layout: auto;
}

table td {
	width: auto;
	font-size: 10px;
}

input[type="reset"] {
	border: none;
	background-color: #0754a4;
	color: #fff;
	padding: 7px 9px;
	border-radius: 0px;
	border-radius: 0px;
}
</style>
<html>
<body class="appear-animate">
	<!-- End Navigation panel -->
	<!-- Head Section -->
	<section class="small-section bg-dark-alfa-30 parallax-2"
		style="margin-bottom: -70px; position: relative"
		data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
		<div class="relative container align-left">
			<div class="row">
				<div class="col-md-8">
					<h1 class="hs-line-11 font-alt mb-10 mb-xs-0"
						style="margin-top: 5px">DASHBOARD</h1>
				</div>

				<div class="col-md-4 mt-30">
					<div class="mod-breadcrumbs font-alt align-right">
						<a class="label-click" href="../CMMS-Dashboard.html">Dashboard</a>&nbsp;/&nbsp;<a
							class="label-click"
							href="../forms_iCMMS/configurator-technician.html">THRESHOLD
							VALUES</a>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- End Head Section -->

	<div style="height: 100px; position: relative"></div>
	<div class="container" style="width: 100%;">
		<div class="row">
			<div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
				<jsp:include page="/WEB-INF/views/menu.jsp" />
			</div>
			<div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">

				<h2 class="section-title font-alt align-left mb-70 mb-sm-40"
					style="margin-bottom: 20px; position: relative; border-bottom: 1px dashed #808080">
					Predictive Maintenance Threshold values</h2>

				<c:if test="${not empty success}">
					<div class="success">${success}</div>
				</c:if>
				<c:if test="${not empty fail}">
					<div class="error">${fail}</div>
				</c:if>

				<c:url value="/predictive/AddThrushHold/" var="actionsubmit"></c:url>
				<form:form class="form contact-form" id="contact_form" method="post"
					modelAttribute="predictiveMaintenance" action="${actionsubmit}"
					enctype="multipart/form-data">
					<div class="clearfix">
						<div>

							<input type="hidden" id="sysidforcontroller" name="sysId"
								value="1" /> <input type="hidden" name="page1" value="pp"
								id="pageNumber"> <input type="hidden" name="curPag"
								value="${page}"> <input type="hidden" name="orderby"
								value="${notorderby}" id="order">

							<div class="form-group">
								<form:select id="equipment" path="equipment"
									class="input-md round form-control">
									<form:errors path="equipment"></form:errors>
									<form:option value="">Select Equipment</form:option>
								</form:select>
								<form:errors path="equipment"></form:errors>
							</div>


							<div class="form-group">
								<input type="button" value="Select Equipment"
									onclick="window.open('${pageContext.servletContext.contextPath}/predictive/equipmentSearch/', 'New', 'height=600,width=800,scrollbars=yes'); return false;"
									id="selectEquip" />
							</div>
							<div class="clearfix"></div>
							<br />
							<hr />
							<br />

							<div class="form-group">
								<input type="button" id="add_param" value="Add Parameters" />
							</div>

							<br />
							<!-- </div>
          <div class="row-fluid">-->

							<input type="hidden" id="sysidforcontroller" name="sysId"
								value="1" /> <input type="hidden" id="idfromchild"
								name="equipId" />
							<%-- <form:hidden path="electricalEquip"/>
              <form:hidden path="mechanicalEquip"/> —--%>
							<table style="border: none !important" id="product_table">
								<tbody>
									<tr>
										<th>Parameter</th>
										<th>Unit</th>
										<th>Value</th>
										<th>Action</th>

									</tr>

									<tr>
										<td class="FR_NO"><a title="Click to view Details"
											href="#"><form:input type="text"
													path="parameterList[0].parameter"
													data-bvalidator='required' /></a></td>



										<td class="REPORTED_DATE"><form:input type="text"
												path="parameterList[0].fieldUnit" data-bvalidator='required' /></td>
										<td class="MAINTENANCE_GROUP"><form:input type="text"
												path="parameterList[0].fieldValue"
												data-bvalidator='required' /></td>
										<td class="BUILDING_ID"><span
											style="font-size: 15px; cursor: pointer;"> </span></td>


									</tr>

								</tbody>
							</table>
							<br />
							<hr />
							<div class="clearfix"></div>

							<div class="form-group">
								<form:textarea path="comments" data-bvalidator="required" class="input-md round form-control" placeholder="Comment" />
								<form:errors path="comments"></form:errors>
							</div>
							<br /> <br />

							<div class="form-group">
								<span>Monitoring Points</span><br />
							</div>


							<input type="button" id="monitor_points" value="Add">
							&nbsp;&nbsp;&nbsp;&nbsp; <input type="button"
								onclick="location.href='${pageContext.servletContext.contextPath}/monitorclass/PMAddNewClass';"
								value="Add New Class" /> <br />&nbsp;

							<table style="border: none !important" id="product_table_monitor">

								<tbody>
									<tr>
										<th
											style="white-space: nowrap; text-overflow: ellipsis; overflow: hidden; max-width: 1px;">
											Monitor Type</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Monitor Class"
											style="white-space: nowrap; text-overflow: ellipsis; overflow: hidden; max-width: 1px;">Monitor
											Class</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Reading Date"
											style="white-space: nowrap; text-overflow: ellipsis; overflow: hidden; max-width: 1px;">
											Reading Date</th>
										<th
											style="white-space: nowrap; text-overflow: ellipsis; overflow: hidden; max-width: 1px;">
											Unit</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Allowed Low Level Value"
											style="white-space: nowrap; text-overflow: ellipsis; overflow: hidden; max-width: 1px;">Allowed
											Low Level Value</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Allowed High Level  Value"
											style="white-space: nowrap; text-overflow: ellipsis; overflow: hidden; max-width: 1px;">Allowed
											High Level Value</th>
										<th
											style="white-space: nowrap; text-overflow: ellipsis; overflow: hidden; max-width: 1px;">Actual
											Value</th>
										<th data-toggle="tooltip" data-placement="bottom"
											title="Reading Delta"
											style="white-space: nowrap; text-overflow: ellipsis; overflow: hidden; max-width: 1px;">Reading
											Delta</th>
										<th
											style="white-space: nowrap; text-overflow: ellipsis; overflow: hidden; max-width: 1px;">Action</th>
									</tr>


									<tr>

										<td><form:input style="width: 100px;" type="text"
												path="monitorPointList[0].monitorType"
												data-bvalidator='required' /></td>
										<td><form:select items="${monitorclasses}" itemValue="id"
												itemLabel="className"
												path="monitorPointList[0].predictiveMonClass.id"
												style='width:80px'></form:select></td>
										<td><form:input style="width: 85px;" type="text"
												path="monitorPointList[0].date" readonly="true"
												id="expecteddateofreplacement" class="datepickerr"
												name="expecteddateofreplacement" data-bvalidator="required" />
											<img
											src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png"
											onclick="javascript: NewCssCal('expecteddateofreplacement','ddMMyyyy')"
											style="cursor: pointer; width: 25px" /> <form:errors
												path="monitorPointList[0].date"></form:errors></td>
										<td><form:input style="width: 85px;" type="text"
												path="monitorPointList[0].unit" data-bvalidator='required' /></td>
										<td><form:input style="width: 85px;" type="text"
												path="monitorPointList[0].lowValue"
												data-bvalidator='required' /></td>

										<td><form:input style="width: 85px;" type="text"
												path="monitorPointList[0].highValue"
												data-bvalidator='required' /></td>

										<td><form:input style="width: 85px;" type="text"
												path="monitorPointList[0].actualValue"
												data-bvalidator='required' /></td>
										<td><form:input style="width: 85px;" type="text"
												path="monitorPointList[0].readingDelta"
												data-bvalidator='required' /></td>

										<td></td>

									</tr>
								</tbody>
							</table>
							<br />

							<div class="clearfix"></div>
                           <div>
							
							<form:textarea path="taskDescription" class="input-md round form-control" placeholder="Task Decsription" />
							</div>
							<div class="clearfix"></div>
							<form:hidden path="fileName" />
							<%--<form:hidden path="user"/> 
          <form:hidden path="createdDate"/>--%>
							<label class="grey" for="description">Attachment:</label> <input
								type="file" name="temp_image" required="required">Please
							Attach only picture files.
							<div class="clearfix"></div>
							<form:errors path="fileName">Invalid file</form:errors>
							<div class="clearfix"></div>
							<br /> <input type="submit" class="searchnext"
								id="validate-button" name="submit" value="Save">
							&nbsp;&nbsp;&nbsp;&nbsp; <input type="reset"
								class="default-button" name="" value="Cancel">
				</form:form>
			</div>
		</div>
	</div>

	<%--   <script type="text/javascript"> 
          $(document).ready(function(){
        	    $('[data-toggle="tooltip"]').tooltip();   
          });</script> --%>
</body>

</html>
