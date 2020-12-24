<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>


<style> 
          table{
              table-layout:auto;
          }

</style>

<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/SOR.js"></script>

<script type="text/javascript">
	$(function() {
		$("#sysId")
				.change(
						function() {
							var value = $(this).val();

							url = "${pageContext.servletContext.contextPath}/predictive/predictivegetsubtype/"
									+ value;

							$
									.getJSON(
											url,
											{
												id : $(this).val()
											},
											function(data) {

												var add = $("#subsysId");

												add.find('option').remove();

												$('#subsysId')
														.append(
																'<option  value="" >SELECT Equipment Subtype </option>');

												for (var i = 0; i < data.length; i++) {

													$("#subsysId")
															.append(
																	'<option value="' + data[i].id+ '">'
																			+ data[i].assetSubTypeName
																			+ '</option>');

												}

											});
						});

		$("#builId")
				.change(
						function() {

							var value = $(this).val();
							url = "${pageContext.servletContext.contextPath}/predictive/getLocation/"
									+ value;

							$
									.getJSON(
											url,
											{
												id : $(this).val()
											},
											function(data) {

												var add = $("#locId");

												add.find('option').remove();

												$('#locId')
														.append(
																'<option  value="" >SELECT Location </option>');

												for (var i = 0; i < data.length; i++) {

													$("#locId")
															.append(
																	'<option value="' + data[i].id+ '">'
																			+ data[i].name
																			+ '</option>');

												}

											});
						});

	});
</script>



<body class="appear-animate">

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
							href="../forms_iCMMS/search-fault-report.html">Search Fault
							Report</a>
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
					Search Predictive Maintainence Criteria</h2>
				<c:url value="/predictive/SearchThrushholdValues/"
					var="actionsubmit"></c:url>

				<form:form action="${actionsubmit}" method="post"
					class="form contact-form" commandName="predictiveCriteria"
					id="contact_form">

					<div class="form-group">

						<c:if test="${not empty success}">
							<div class="success">${success}</div>
						</c:if>
						<c:if test="${not empty fail}">
							<div class="error">${fail}</div>
						</c:if>

					</div>
					<input type="hidden" value="${pageContext.request.contextPath}"
						id="contextPath" />
					<div class="form-group">




						<form:select path="system" id="sysId" name="sysId"
							class="input-md round form-control">
							<form:option value="">Select AssetType</form:option>
							<form:options items="${types}" itemValue="assetTypeName"
								itemLabel="assetTypeName"></form:options>
						</form:select>

					</div>





					<div class="form-group">
						<form:select path="subsystem" id="subsysId" name="subsysId"
							class="input-md round form-control">
							<form:option value="">Select Asset SubType</form:option>
							<form:options items="${subTypeList}" itemValue="id"
								itemLabel="assetSubTypeName"></form:options>
						</form:select>
					</div>

					<div class="form-group">
						<form:select name="builId" id="builId" path="building"
							class="input-md round form-control">
							<form:option value="">Select Building</form:option>
							<form:options items="${buildings}" itemLabel="name"
								itemValue="id" />
						</form:select>
					</div>

					<div class="form-group">
						<form:select name="locId" id="locId" path="loc"
							class="input-md round form-control">
							<form:option value="">Select Location</form:option>
							<form:options items="${locationList}" itemValue="id"
								itemLabel="name"></form:options>

						</form:select>
					</div>
					<div class="form-group">
						<input type="submit" value="Search" />
					</div>
					<div class="form-group">

						<table class="table table-stripped" id="tblResult">

							<thead>
								<tr>

									<th>Equipment Type</th>
									<th>Equipment Name</th>
									<th>Comments</th>
									<th>Attachment</th>
									<th>Actions</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${predictivelist}" var="pred">
									<tr>
										<td>${pred.equipment.type}</td>
										<td><input type="hidden" name="p" value="${page}" />
											${pred.equipment.name}</td>




										<td>${pred.comments}</td>

										<td><a download="${pred.fileName}"
											href='<c:url value="/predictive/getimage/${pred.fileName}" />'>Download
												Attachment</a></td>

										<c:url value="/predictive/delete/${pred.id}" var="deletelink" />

										<td><a id="delete" href="${deletelink}"
											style="cursor: pointer"><img style="width: 20px"
												src="${pageContext.servletContext.contextPath}/resources/assets/images/trash..png" /></a>
											<c:url value="/predictive/editThrushholdValues/${pred.id}"
												var="editlink" /> <a id="edit" href="${editlink}"
											style="cursor: pointer"><img style="width: 20px"
												src="${pageContext.servletContext.contextPath}/resources/assets/images/edit.png" /></a></td>
									</tr>
								</c:forEach>

							</tbody>
							<tr>

								<td colspan="100%"><c:if test="${1<total}">
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
													<td><c:if test="${page>1}">

															<a class="page-far-left"
																href="<c:url value="/predictive/PM/pageno=1"/>"><img
																style="width: 10px; height: 12px"
																src="${pageContext.servletContext.contextPath}/resources/assets/images/pre.png"></a>

															<a class="page-left"
																style="color: #fff; cursor: pointer; text-decoration: none;"
																href="<c:url value="/PM/pageno=${page-1}"/>">&nbsp;<<
															</a>

														</c:if> <c:forEach var="i" begin="${from}" end="${to}">

															<c:choose>
																<c:when test="${i==page}">
																	<strong style="color: #11adc1 !important">${i}</strong>
																</c:when>
																<c:otherwise>
																	<a class=""
																		href="<c:url value="/predictive/PM/pageno=${i}"/>"
																		style="color: #c8cccd; text-decoration: none; font-size: 15px">${i}</a>
																</c:otherwise>
															</c:choose>
														</c:forEach> <label>&nbsp;>> </label>
														</div> <c:if test="${page<total}">
															<a class="page-right"
																href="<c:url value="/predictive/PM/pageno=${page+1}"/>"></a>

															<c:if test="${page<total}">
																<a class="page-right"
																	style="color: #fff; cursor: pointer; text-decoration: none;"
																	href="<c:url value="/predictive/PM/pageno=${page+1}"/>">&nbsp;>>
																</a>

																<a class="page-far-right"
																	href="<c:url value="/predictive/PM/pageno=${total}"/>"><img
																	style="width: 10px; height: 12px; top: -1px; position: relative"
																	src="${pageContext.servletContext.contextPath}/resources/assets/images/next.png"></a>
															</c:if>
														</c:if></td>

												</tr>
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