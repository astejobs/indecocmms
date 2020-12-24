<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="forms"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>





<style>
table {
	table-layout: auto;
}

label {
	font-family: Dosis, arial, sans-serif;
	font-size: 16px;
}
</style>


<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/resources/assets/js/datetimepicker_css.js"></script>
<link rel="stylesheet"
	href="${pageContext.servletContext.contextPath}/resources/assets/timepicker/wickedpicker.css">
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/assets/timepicker/wickedpicker.min.js"></script>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/assets/js/DateTimePicker.js"></script>

<body class="appear-animate">

	<!-- Page Loader -->
	<div class="page-loader">
		<div class="loader">Loading...</div>
	</div>
	<!-- End Page Loader -->
	<!-- Page Wrap -->
	<!-- <div class="page" id="top"> -->

		<!-- Navigation panel -->

		<!-- End Navigation panel -->
		<!-- Head Section -->
		<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Modify PM Task</li>
			  </ol>
			</nav>
			
 
		<!-- End Head Section -->
		<!---MENU-->

		<!--End Menu-->
		<!--Form-->
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

				
					<h2 class="section-title font-alt align-left mb-70 mb-sm-40">Modify PM Task</h2>
					
					<c:url value="/task/updateTask" var="updatePmTaskurl"></c:url>
					<forms:form modelAttribute="taskToUpdate"
						action="${updatePmTaskurl}" id="validate" method="POST">

						<forms:hidden path="id" />
						<forms:hidden path="task_number" />
						<forms:hidden path="schedule" />
						<forms:hidden path="scheduleDate" />
						<forms:hidden path="endDate" />
						<forms:hidden path="equipment" />
						<forms:hidden path="dueDate" />
						<forms:hidden path="comments" />
						<forms:hidden path="srvc_chkd_sign1" />
						<forms:hidden path="srvc_verify_sign1" />
						<forms:hidden path="beforeImage" />
						<forms:hidden path="afterImage" />
						<forms:hidden path="srvc_acknwldg_sign" />
						<forms:hidden path="actualScheduleDate" />
						<forms:hidden path="userDetail.id" />


						<div class="form-group">
							<label class="col-md-3">Status:</label> <label>${taskToUpdate.status}</label>
						</div class="form-group">
						<div>
							<label class="col-md-3">PM-Task-No:</label> <label>${taskToUpdate.task_number}</label>
						</div>
						<div class="form-group">
							<label class="col-md-3">PM-Schedule-No:</label> <label>${taskToUpdate.schedule.scheduleNumber}</label>
						</div>
						<div class="form-group">
							<label class="col-md-3">Brief Description:</label> <label>${taskToUpdate.schedule.briefDescription}</label>
						</div>
						<div class="form-group">
							<label class="col-md-3">Additional Description:</label> <label>${taskToUpdate.schedule.detailedDescription}</label>
						</div>


						<h2 class="section-title font-alt align-left mb-70 mb-sm-40"
							>
							Equipments to be maintained</h2>

						<div class="form-group">
							<label class="col-md-3">Equipment code:</label> <label>${taskToUpdate.equipment.name}</label>
						</div>
						<div class="form-group">
							<label class="col-md-3">Equipment Location:</label> <label>${taskToUpdate.equipment.location.name}</label>
						</div>
						<div class="form-group">
							<label class="col-md-3">Equipment Building:</label> <label>${taskToUpdate.equipment.building.name}</label>
						</div>
						<h2 class="section-title font-alt align-left mb-70 mb-sm-40"
							>
							Schedule Criteria</h2>

						<div class="form-group">
							<label class="col-md-3">Task to be performed:</label> <label>${taskToUpdate.schedule.taskPerformed}</label>
						</div>
						<%-- <div class="form-group">
							<label class="col-md-3">Perform:</label> <label>${freq}</label>
						</div> --%>

						<h2 class="section-title font-alt align-left mb-70 mb-sm-40">Change Status to</h2>
						<div class="form-group input-group col-md-3">
                            <span class="input-group-addon label-left" id="basic-addon2">Status</span>
							<forms:select data-bvalidator="required" path="status"
								id="taskstatus" class="form-control round input-md">
								<forms:option value="OPEN">OPEN</forms:option>
								<forms:option value="InProgress">IN PROGRESS</forms:option>
								<forms:option value="KIV">KIV</forms:option>
								<forms:option value="CLOSED">CLOSED</forms:option>
							</forms:select>

						</div>


						<h2 class="section-title font-alt align-left mb-70 mb-sm-40"
							>
							Task Details</h2>
						<div class="form-group">
							<label class="col-md-3">Schedule Date:</label> <label><fmt:formatDate
									value="${taskToUpdate.scheduleDate}" pattern="dd/MM/yyyy" /></label>
						</div>
						<div class="form-group">
							<label class="col-md-3">Actual Schedule Date:</label> <label><fmt:formatDate
									value="${taskToUpdate.actualScheduleDate}" pattern="dd/MM/yyyy" /></label>
						</div>

						<div class="form-group completed">
							<label class="col-md-3" style="display: inline-block">Completed
								Date:</label> <input type="text" name="completed_date"
								id="completedDate" style="display: inline-block; width: 40%;"
								class="input-md round form-control"
								value='<fmt:formatDate value="${taskToUpdate.completedDate}" pattern="dd-MM-yyyy"/>'
								style="width: 100%; display: block;" name="completed_date" />
							&nbsp;&nbsp;&nbsp; <img
								src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png"
								onclick="javascript: NewCssCal('completedDate','ddMMyyyy')"
								style="cursor: pointer; width: 30px" />

						</div>


						<div class="form-group completed">
							<label style="display: inline-block" class="col-md-3">Completed
								Time:</label>

							<fmt:formatDate value="${taskToUpdate.completedDate}"
								pattern="HH:mm" var="comtime" />



							<div class="form-control input-group">
                                <span class="input-group-addon label-left" id="basic-addon2">Completed Time</span>
								<input type="text" name="completed_time" id="timepicker-12-hr"
									class="input-md round form-control timepicker-12-hr"
									value='<fmt:formatDate value="${taskToUpdate.completedDate}" pattern="HH:mm"/>'>
							</div>

							<c:if test="${empty comtime}">

								<script type="text/javascript">
									var twelveHour = $('#timepicker-12-hr')
											.wickedpicker({
												twentyFour : true
											});
								</script>


							</c:if>


							<c:if test="${not empty comtime}">
								<script type="text/javascript">
									var twelveHour2 = $('#timepicker-12-hr')
											.wickedpicker({
												twentyFour : true,
												now : "${comtime}"
											});
								</script>
							</c:if>



						</div>
						<br>
						<br>
						<%-- <div class="form-group">
							<label class="col-md-3">Due Date:</label> <label><fmt:formatDate
									value="${taskToUpdate.dueDate}" pattern="dd/MM/yyyy" /> </label>
						</div> --%>
						<br>
						<br>

						<div id="enddatesection">
							<label class="grey" for="BLID">End Date:</label>

							<div style="display: block; width: 40%;">

								<label class="grey" for="reportedDate"><fmt:formatDate
										value="${taskToUpdate.endDate}" pattern="dd/MM/yyyy HH:mm a" /></label>

							</div>
							

							<div class="clearfix"></div>
							<div>
								<label class="col-md-3">Completed By</label>
								<forms:input class="col-md-6" path="completedBy"
									data-bvalidator="required" type="text" />

							</div>

</div>
							<div class="clearfix"></div>
							<div class="form-group">
								<label style="display: inline-block" class="col-md-3">Remarks
									/ Issue:</label>
								<forms:textarea class="col-md-6" placeholder="" path="remarks" />
							</div>
							<br> <br> <br> <br>

							<div class="form-group">
								<label class="col-md-3"> Checklist:</label> <a
									class="default-button"
									href="${pageContext.servletContext.contextPath}/task/checklist/${taskToUpdate.id}">View</a>

							</div>

										<%-- <div class="form-group">
                                            Add Parts&nbsp; <a class="al" href="${pageContext.servletContext.contextPath}/faultReport/${taskToUpdate.id}/parts">Reserve Part</a>
                                         </div> --%>

							<div class="form-group float-right">
								<input type="submit" value="Update"
									class="submit_btn btn btn-mod btn-medium btn-round"> 
								<input
									type="submit" value="Create Predictive Task"
									class="submit_btn btn btn-mod btn-medium btn-round"
									id="reservebutton">
							</div>
					</forms:form>

					<!-- End Contact Form -->
				</div>

			</div>


			<!-- End Footer -->

		</div>
		</div>


		<script type="text/javascript">
			$(function() {
				$('.timepicker').wickedpicker({
					now : "12:35"
				});
				$(".completed").hide();
				$('#enddatesection').hide();

				$(document).on('change', '#taskstatus', function() {

					if ($('#taskstatus').val() == "CLOSED") {

						$('#reservebutton').hide();
						$(".completed").show();
						$('#enddatesection').show();

					} else {
						console.log($(".completed"));
						$(".completed").hide();
						$('#enddatesection').hide();
					}

				});

				(function() {
					$(".completed").hide();
					$('#enddatesection').hide();

					$('#taskstatus').trigger('change');

				})();

			});
		</script>