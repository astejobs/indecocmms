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
			    <li class="breadcrumb-item active" aria-current="page">Access</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
		         
		            <!-- Works Grid -->
                    <ul class="works-grid work-grid-5 work-grid-gut clearfix font-alt hover-white " style="position:relative;left:8%" id="work-grid">
                        <!-- Work Item -->
                        <c:if test="${not empty flagusermanagement}">
                        
                        <li class="work-item mix photography dashboard-outer-line">
                            <a href='<c:url value ="/dashboard/user" />'>
                                <div class="work-img">
                                    <img src="${pageContext.request.contextPath}/resources/assets/images/dashboard/usermgnt.png" class="dashboard-tile" alt="Work" />
                                </div>
                                <div class="work-intro">
                                    <h3 class="work-title">User</h3>
                                    <div class="work-descr">
                                        Management
                                    </div>
                                </div>
                            </a>
                        </li>
                        </c:if>
                        <!-- End Work Item -->
                        <!-- Work Item -->
                        <c:if test="${not empty flaggroupmanagement}">
                        <li class="work-item mix photography dashboard-outer-line">
                            <a  href='<c:url value ="/dashboard/group" />'>
                                <div class="work-img">
                                    <img src="${pageContext.request.contextPath}/resources/assets/images/dashboard/grpmgmnt.png" class="dashboard-tile" alt="Work" />
                                </div>
                                <div class="work-intro">
                                    <h3 class="work-title">Group</h3>
                                    <div class="work-descr">
                                        Management
                                    </div>
                                </div>
                            </a>
                        </li>
                        </c:if>
                        <!-- End Work Item -->
                        <!-- Work Item -->
                        <c:if test="${not empty flagaccesscontrol}">
                        <li class="work-item mix photography dashboard-outer-line">
                            <a  href='<c:url value ="/dashboard/priviledge" />'>
                                <div class="work-img">
                                    <img src="${pageContext.request.contextPath}/resources/assets/images/dashboard/acesscontrol.png" class="dashboard-tile" alt="Work" />
                                </div>
                                <div class="work-intro">
                                    <h3 class="work-title">Access</h3>
                                    <div class="work-descr">
                                        Control
                                    </div>
                                </div>
                            </a>
                        </li>
                        </c:if>
                        <!-- End Work Item -->
                        <!-- Work Item -->
<!--                         <li class="work-item mix photography dashboard-outer-line"> -->
<!--                             <a href="access-control-inventory-management.html"> -->
<!--                                 <div class="work-img"> -->
<%--                                     <img src="${pageContext.request.contextPath}/resources/assets/images/dashboard/inventory.png" class="dashboard-tile" alt="Work" /> --%>
<!--                                 </div> -->
<!--                                 <div class="work-intro"> -->
<!--                                     <h3 class="work-title">Inventory</h3> -->
<!--                                     <div class="work-descr"> -->
<!--                                         Management -->
<!--                                     </div> -->
<!--                                 </div> -->
<!--                             </a> -->
<!--                         </li> -->
                        <!-- End Work Item -->
                    </ul>
     </div>
     </div>
     </div>
     </div>
     
     
     

</body>
</html>
