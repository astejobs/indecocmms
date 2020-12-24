
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
<body class="appear-animate">
        <!-- Head Section -->
         <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Asset Report</li>
			  </ol>
			</nav>
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
        	
	            <c:url value="/asset/assetReportGeneral" var="assetReportGenUrl"></c:url>
	            <c:url value="/asset/assetReportByAge" var="assetReportByAgeUrl"></c:url>
	            <c:url value="/asset/assetReportByLifeSpan" var="assetReportByLifeSpanUrl"></c:url>
	             <div class="hs-line-4 font-alt">
                    <p style="color:#000;left:7%;position:relative">Asset Reports</p>
                </div>
                <!-- Works Grid -->
                <ul class="works-grid work-grid-5 work-grid-gut clearfix font-alt hover-white " style="position:relative;left:8%" id="work-grid">
                    <!-- Work Item -->
                    <%-- <li class="work-item mix photography dashboard-outer-line">
                        <a href="standard-report-corrective-maintenance-fr-fault-history.html">
                            <div class="work-img">
                                <img src="${pageContext.servletContext.contextPath}/resources/assets/images/dashboard/reports.png" class="dashboard-tile" alt="Work" />
                            </div>
                            <div class="work-intro">
                                <h3 class="work-title">Asset Report</h3>
                                <div class="work-descr">
                                    For PM
                                </div>
                            </div>
                        </a>
                    </li> --%>
                    <!-- End Work Item -->
                    <!-- Work Item -->
                    <li class="work-item mix photography dashboard-outer-line">
                    
                        <a href="${assetReportGenUrl}">
                            <div class="work-img">
                                <img src="${pageContext.servletContext.contextPath}/resources/assets/images/dashboard/reports.png" class="dashboard-tile" alt="Work" />
                            </div>
                            <div class="work-intro">
                                <h3 class="work-title">General</h3>
                                <div class="work-descr">
                                   Asset Report
                                </div>
                            </div>
                        </a>
                    </li>
                    <!-- End Work Item -->
                    <!-- Work Item -->
                    <li class="work-item mix photography dashboard-outer-line">
                        <a href="${assetReportByLifeSpanUrl }">
                            <div class="work-img">
                                <img src="${pageContext.servletContext.contextPath}/resources/assets/images/dashboard/reports.png" class="dashboard-tile" alt="Work" />
                            </div>
                            <div class="work-intro">
                                <h3 class="work-title">Asset Report</h3>
                                <div class="work-descr">
                                    By Life Span
                                </div>
                            </div>
                        </a>
                    </li>
                    <!-- End Work Item -->
                    <!-- Work Item -->
                    <li class="work-item mix photography dashboard-outer-line">
                        <a href="${assetReportByAgeUrl}">
                            <div class="work-img">
                                <img src="${pageContext.servletContext.contextPath}/resources/assets/images/dashboard/reports.png" class="dashboard-tile" alt="Work" />
                            </div>
                            <div class="work-intro">
                                <h3 class="work-title">Asset Report</h3>
                                <div class="work-descr">
                                   By Age
                                </div>
                            </div>
                        </a>
                    </li>
                    <!-- End Work Item -->
                </ul>
</div>
</div>

    </div>
    </div>

</body>
