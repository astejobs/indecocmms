<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<body class="appear-animate">

    <!-- Page Loader -->
    <div class="page-loader">
        <div class="loader">Loading...</div>
    </div>
    <!-- End Page Loader -->
    <!-- Page Wrap -->
    
        <!-- Navigation panel -->
        
        <!-- End Navigation panel -->
        <!-- Head Section -->
        <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Report</li>
			  </ol>
			</nav>
             
        <!-- End Head Section -->
        <!-- Dashboard Item List Section -->
<div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                                  <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                   Report
                                  </h2>
                <!-- Works Grid -->
                <ul class="works-grid work-grid-5 work-grid-gut clearfix font-alt hover-white " style="position:relative;left:50px;"  id="work-grid">
                    <!-- Work Item -->
                    <li class="work-item mix photography dashboard-outer-line">
                        <a href="${pageContext.servletContext.contextPath}/selfHelp/CreateReport">
                            <div class="work-img">
                                <img src="${pageContext.servletContext.contextPath}/resources/assets/images/dashboard/2.jpg" class="dashboard-tile" alt="Work" />
                            </div>
                            <div class="work-intro">
                                <h3 class="work-title">Create</h3>
                                <div class="work-descr">
                                    Self Service Report
                                </div>
                            </div>
                        </a>
                    </li>
                    <!-- End Work Item -->
                    <!-- Work Item -->
                    <li class="work-item mix photography dashboard-outer-line">
                        <a href="${pageContext.servletContext.contextPath}/selfHelp/viewAdhocReports">
                            <div class="work-img">
                                <img src="${pageContext.servletContext.contextPath}/resources//assets/images/dashboard/3.jpg" class="dashboard-tile" alt="Work" />
                            </div>
                            <div class="work-intro">
                                <h3 class="work-title">View</h3>
                                <div class="work-descr">
                                    Self Service Report
                                </div>
                            </div>
                        </a>
                    </li>
                    <!-- End Work Item -->
                </ul>
                
                <!-- End Works Grid -->
            </div>
            </div>
            
            
       
        <!-- End Dashboard Item List Section -->
        <!-- Foter -->
     
        <!-- End Footer -->

    </div>
    </div>
  
</body>
</html>