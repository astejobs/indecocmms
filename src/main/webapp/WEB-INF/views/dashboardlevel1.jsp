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
			    <li class="breadcrumb-item"><a href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">${workspaceid}</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
       <div class="col-md-12"> 
       <div class="row">
        	<div class="col-md-12 jumbotron" style="height:100%">
        	
        	<div class="col-sm-6 col-lg-2 col-md-3 d-tile">
				      <a  href='<c:url value ="/dashboard/overview"/>' class="quick-menu-btn mat-elevation-z8">
				        <!-- <mat-icon class="quick-menu-icon">home</mat-icon> <br> -->
				        <img src="${pageContext.request.contextPath}/resources/assets/images/dashboard/monitor.png"  class="quick-menu-icon"  alt="Work" />
					     <div class="work-description">
					          <span>CMMS </span> <br>
					          <div class="site-title">Computer Maintenance Management System</div>
				         </div>
				       </a>
				 </div>
				 <c:if test="${not empty flagadmin}">
					 <div class="col-sm-6 col-lg-2 col-md-3 d-tile">
					      <a  href='<c:url value ="/dashboard/access"/>' class="quick-menu-btn mat-elevation-z8">
					        <!-- <mat-icon class="quick-menu-icon">home</mat-icon> <br> -->
					        <img src="${pageContext.request.contextPath}/resources/assets/images/dashboard/acess.png"  class="quick-menu-icon"  alt="Work" />
						    <div class="work-description">
						          <span>ADMINISTRATION</span>  <br>
						          <div class="site-title"> Administration & Access Control</div>
					         </div>
					       </a>
					 </div>
				 </c:if>
				  
                <%-- <ul class="works-grid work-grid-5 work-grid-gut clearfix font-alt hover-white " style="position:relative;left:8%" id="work-grid">
                    <!-- Work Item -->
                    <li class="work-item mix photography dashboard-outer-line">
                        <a href='<c:url value ="/dashboard/overview"/>'>
                            <div class="work-img">
                                <img src="${pageContext.request.contextPath}/resources/assets/images/dashboard/monitor.png" class="dashboard-tile" alt="Work" />
                            </div>
                            <div class="work-intro">
                                <h3 class="work-title">CMMS</h3>
                                <div class="work-descr" >
                                    Computer Maintenance Management System
                                </div>
                            </div>
                        </a>
                    </li>
                    <!-- End Work Item -->
                    <!-- Work Item -->
                    <c:if test="${not empty flagadmin}">
                    <li class="work-item mix photography dashboard-outer-line">
                        <a href='<c:url value ="/dashboard/access"/>'>
                            <div class="work-img">
                                <img src="${pageContext.request.contextPath}/resources/assets/images/dashboard/acess.png" class="dashboard-tile" alt="Work" />
                            </div>
                            <div class="work-intro">
                                <h3 class="work-title">Adminstration</h3>
                                <div class="work-descr">
                                    Administration & Access Control
                                </div>
                            </div>
                        </a>
                    </li>
                    </c:if> --%>
                    <!-- End Work Item -->
                    <!-- Work Item -->
<!--                     <li class="work-item mix photography dashboard-outer-line"> -->
<!--                         <a href="forms_iCMMS/Facility-Booking.html"> -->
<!--                             <div class="work-img"> -->
<%--                                 <img src="${pageContext.request.contextPath}/resources/assets/images/dashboard/booking.png" class="dashboard-tile" alt="Work" /> --%>
<!--                             </div> -->
<!--                             <div class="work-intro"> -->
<!--                                 <h3 class="work-title">CMMS</h3> -->
<!--                                 <div class="work-descr"> -->
<!--                                     Facility Bookings -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </a> -->
<!--                     </li> -->
                    <!-- End Work Item -->
                <!-- </ul> -->
                <!-- End Works Grid -->
                </div>
                </div>
            </div>
           </div>
   <!--  </div>' -->
    </div>
       
</html>
