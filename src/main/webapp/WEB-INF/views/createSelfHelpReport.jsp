<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<body class="appear-animate">

    <!-- Page Loader -->
    <div class="page-loader">
        <div class="loader">Loading...</div>
    </div>
    <!-- End Page Loader -->
    <!-- Page Wrap -->
   

        <!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Self Report</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                        My Report Creation
                    </h2>
                    
                               <c:if test="${not empty success}">
                                         <div class="success">${success}</div>
										</c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>

                    <!-- Contact Form -->
                    <div class="">
                        <div class="">
                            <form:form action=""  method="post" commandName="adhocreport" class="form contact-form" id="contact_form">
                                <div class="clearfix">
                                    <div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Report From</span>
                                            <form:select required="true"  path="adhocReportForm"   class="input-md round form-control">
                                                <form:option value="">Select Report Form</form:option>
												<form:options items="${reporttype}" itemValue="id" itemLabel="formType"/>
                                            </form:select>
                                     </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Report Name</span>
                                            <form:input type="text"  path="reportName" name="report_name" id="report_name" class="input-md round form-control" placeholder="Report Name"/>
                                            <form:errors path="reportName"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Description</span>
                                            <form:textarea   path="description" class="input-md round form-control col-lg-3" placeholder="Description"></form:textarea>
                                            <form:errors path="description"/>
                                        </div>
                                        <div class="clearfix"></div><br />
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Requestor Name</span>
                                            <form:input type="text"  path="requestorName"  name="requestor_name" id="requestor_name" class="input-md round form-control" placeholder="Requestor Name"/>
                                             <form:errors path="requestorName"/>
                                        </div>
                                        <div class="align-right pt-10">
                                            <input type="submit" value="Next" class="submit_btn btn btn-mod btn-medium btn-round" />
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                </div>
                <!-- End Contact Form -->
           <!--  </section> -->
        </div>               
   

        <!--End Form-->
        <br />
        <!-- Foter -->
        <div class="clearfix"></div>
        <br /><br />
    

    </div>
    </div>
   
</body>
</html>