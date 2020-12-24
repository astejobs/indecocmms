
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
<body class="appear-animate">
        <!-- Head Section -->
        <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Report</li>
			  </ol>
			</nav>
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
				<c:url value="/asset/assetReport" var="assetReportUrl"></c:url>
				<c:url value="/inventory/standardReport" var="inventoryReportUrl"></c:url>
				
                <div class="hs-line-4 font-alt">
                    <p style="color:#000;left:7%;position:relative">Standard Reports</p>
                </div>
                <!-- Works Grid -->
                <ul class="works-grid work-grid-5 work-grid-gut clearfix font-alt hover-white " style="position:relative;left:8%" id="work-grid">
                    <!-- Work Item -->
                    <li class="work-item mix photography dashboard-outer-line">
                      	<c:url value="/faultReport/report" var="faultReport"/>
                        <a href="${faultReport}">
                            <div class="work-img">
                                <img src="${pageContext.servletContext.contextPath}/resources/assets/images/dashboard/reports.png" class="dashboard-tile" alt="Work" />
                            </div>
                            <div class="work-intro">
                                <h3 class="work-title">Corrective</h3>
                                <div class="work-descr">
                                    Maintenance
                                </div>
                            </div>
                        </a>
                    </li>
                    <!-- End Work Item -->
                    <!-- Work Item -->
                    <li class="work-item mix photography dashboard-outer-line">
                        <a href="${pageContext.servletContext.contextPath}/task/pmReport">
                            <div class="work-img">
                                <img src="${pageContext.servletContext.contextPath}/resources/assets/images/dashboard/reports.png" class="dashboard-tile" alt="Work" />
                            </div>
                            <div class="work-intro">
                                <h3 class="work-title">PM</h3>
                                <div class="work-descr">
                                    Report
                                </div>
                            </div>
                        </a>
                    </li>
                    <!-- End Work Item -->
                    <!-- Work Item -->
                    <li class="work-item mix photography dashboard-outer-line">
                        <a href="${assetReportUrl}">
                            <div class="work-img">
                                <img src="${pageContext.servletContext.contextPath}/resources/assets/images/dashboard/reports.png" class="dashboard-tile" alt="Work" />
                            </div>
                            <div class="work-intro">
                                <h3 class="work-title">Asset</h3>
                                <div class="work-descr">
                                    Report
                                </div>
                            </div>
                        </a>
                    </li>
                    <!-- End Work Item -->
                    <!-- Work Item -->
<!--                     <li class="work-item mix photography dashboard-outer-line"> -->
<%--                         <a href="${inventoryReportUrl}"> --%>
<!--                             <div class="work-img"> -->
<%--                                 <img src="${pageContext.servletContext.contextPath}/resources/assets/images/dashboard/reports.png" class="dashboard-tile" alt="Work" /> --%>
<!--                             </div> -->
<!--                             <div class="work-intro"> -->
<!--                                 <h3 class="work-title">Inventory</h3> -->
<!--                                 <div class="work-descr"> -->
<!--                                     Report -->
<!--                                 </div> -->
<!--                             </div> -->
<!--                         </a> -->
<!--                     </li> -->
                    <!-- End Work Item -->
                </ul>
			</div>
		</div>
			
    </div>
    </div>

</body>
