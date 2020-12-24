<!DOCTYPE html>
<html>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<body class="appear-animate">


        <!-- Head Section -->
        <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Fault Report</li>
			  </ol>
			</nav>
			
       <div class="container">
<div class="row">
        <div class="col-md-12">
        
        <div class="col-md-10 jumbotron offset-box">
				<c:url value="/asset/assetReport" var="assetReportUrl"></c:url>
                <div class="hs-line-4 font-alt">
                    <p style="color:#000;left:7%;position:relative">FR Fault History</p>
                </div>
                <!-- Works Grid -->
                <ul class="works-grid work-grid-5 work-grid-gut clearfix font-alt hover-white " style="position:relative;left:8%" id="work-grid">
                    <!-- Work Item -->
                    <li class="work-item mix photography dashboard-outer-line">
                    	<c:url value="/faultReport/report/search" var="report"/>
                        <a href="${report}"> <div class="work-img">
                                <img src="${pageContext.servletContext.contextPath}/resources/assets/images/dashboard/reports.png" class="dashboard-tile" alt="Work" />
                            </div>
                            <div class="work-intro">
                                <h3 class="work-title">FR</h3>
                                <div class="work-descr">
                                    Fault Report
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
        
        <!-- End Dashboard Item List Section -->

</body>
</html>
