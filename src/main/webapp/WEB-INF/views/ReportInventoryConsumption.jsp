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
        <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">DASHBOARD</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="../CMMS-Dashboard.html">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="../forms_iCMMS/configurator-civil-Level-3.html">Civil Level 3</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>

            <div style="height:100px;position:relative"></div>
  			<div class="container" style="width:100%">
           <div class="row">
              <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
           <jsp:include page="/WEB-INF/views/menu.jsp" />
             </div>
             <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">
			     <div class="hs-line-4 font-alt">
                    <p style="color:#000;left:7%;position:relative">Inventory Consumption Reports</p>
                </div>
                <c:url value="/inventory/reports/parttransaction" var="partTransaction"></c:url>
                <c:url value="/inventory/reports/parttransfer" var="parttransfer"></c:url>
                <!-- Works Grid -->
                <ul class="works-grid work-grid-5 work-grid-gut clearfix font-alt hover-white " style="position:relative;left:8%" id="work-grid">
                    <!-- Work Item -->
                    <li class="work-item mix photography dashboard-outer-line">
                        <a href="${partTransaction}">
                            <div class="work-img">
                                <img src="${pageContext.servletContext.contextPath}/resources/assets/images/dashboard/reports.png" class="dashboard-tile" alt="Work" />
                            </div>
                            <div class="work-intro">
                                <h3 class="work-title">Part Transaction</h3>
                                <div class="work-descr">
                                </div>
                            </div>
                        </a>
                    </li>
                    <!-- End Work Item -->
                    <!-- Work Item -->
                    <li class="work-item mix photography dashboard-outer-line">
                        <a href="${parttransfer}">
                            <div class="work-img">
                                <img src="${pageContext.servletContext.contextPath}/resources/assets/images/dashboard/reports.png" class="dashboard-tile" alt="Work" />
                            </div>
                            <div class="work-intro">
                                <h3 class="work-title">Part Transfer</h3>
                                <div class="work-descr">
                                </div>
                            </div>
                        </a>
                    </li>
                    <!-- End Work Item -->
                   </ul>
			</div>
		</div>
			
    </div>

</body>
