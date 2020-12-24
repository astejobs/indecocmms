<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

       <!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Access Control</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                <ul class="works-grid work-grid-5 work-grid-gut clearfix font-alt hover-white " style="position:relative;left:8%" id="work-grid">
                    <!-- Work Item -->
                    <li class="work-item mix photography dashboard-outer-line">
                        <a href='<c:url value="/access/userpriviledges"/>'>
                            <div class="work-img">
                                <img src="${pageContext.servletContext.contextPath}/resources/assets/images/dashboard/booking.png" class="dashboard-tile" alt="Work" />
                            </div>
                            <div class="work-intro">
                                <h3 class="work-title">UP</h3>
                                <div class="work-descr">
                                    User Privileges
                                </div>
                            </div>
                        </a>
                    </li>
                    <!-- End Work Item -->
                    <!-- Work Item -->
                    <li class="work-item mix photography dashboard-outer-line">
                        <a href='<c:url value="/access/siteaccess"/>'>
                            <div class="work-img">
                                <img src="${pageContext.servletContext.contextPath}/resources/assets/images/dashboard/booking.png" class="dashboard-tile" alt="Work" />
                            </div>
                            <div class="work-intro">
                                <h3 class="work-title">SA</h3>
                                <div class="work-descr">
                                    Site Access
                                </div>
                            </div>
                        </a>
                    </li>
                    <!-- End Work Item -->
                    <!-- Work Item -->
                    <li class="work-item mix photography dashboard-outer-line">
                        <a href='<c:url value="/access/changepassword"/>'>
                            <div class="work-img">
                                <img src="${pageContext.servletContext.contextPath}/resources/assets/images/dashboard/booking.png" class="dashboard-tile" alt="Work" />
                            </div>
                            <div class="work-intro">
                                <h3 class="work-title">Change Password</h3>
                                <div class="work-descr">
                                    Change Password
                                </div>
                            </div>
                        </a>
                    </li>
                    <!-- End Work Item -->
                </ul>
                <!-- End Works Grid -->
            </div>
     </div>
     </div>
     </div>
     
      
</html>
