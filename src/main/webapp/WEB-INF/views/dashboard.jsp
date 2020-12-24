<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>

       
       <!-- Head Section -->
          <div style="margin-top:15px;"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page"></li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
       <div class="col-md-12"> 
        <div class="row">
        	<div class=" jumbotron" style="height:100%">
                <div class="hs-line-4 font-alt">
                    <p>List of sites with description</p>
                </div>
                <c:if test="${not empty noworkspace}">
                <div class="error"> ${noworkspace}</div>
                </c:if>
                
                 <c:forEach items="${listofworkspaces}" var="w">
                <div class="col-sm-12 col-lg-2 col-md-3 d-tile">
				      <a  href="${pageContext.request.contextPath}/dashboard/workspace/${w.workspaceId}" class="quick-menu-btn mat-elevation-z8">
				        <!-- <mat-icon class="quick-menu-icon">home</mat-icon> <br> -->
				        <img src="${pageContext.request.contextPath}/resources/assets/images/dashboard/monitor.png"  class="quick-menu-icon"  alt="Work" />
					    <div class="work-description">
					          <span>${w.description}</span> <br>
					          <div class="site-title">${w.workspaceId}</div>
				         </div>
				       </a>
				 </div>
				 </c:forEach>
				 
				 
				
                <!-- Works Grid -->
                <%-- <ul class="works-grid work-grid-5 work-grid-gut clearfix font-alt hover-white " style="position:relative;left:50px;" id="work-grid">
                    <!-- Work Item -->
                    <c:forEach items="${listofworkspaces}" var="w">
                    <li class="work-item mix photography dashboard-outer-line" >
                        <a href="${pageContext.request.contextPath}/dashboard/workspace/${w.workspaceId}">
                            <div class="work-img">
                                <img src="${pageContext.request.contextPath}/resources/assets/images/dashboard/monitor.png"  class="dashboard-tile"  alt="Work" />
                            </div>
                            <div class="work-intro" >
                                <h3 class="work-title">CMMS</h3>
                                <div class="work-descr" style="color:#000">
                                    ${w.workspaceId}
                                </div>
                            </div>
                        </a>
                    </li>
                    </c:forEach>
   				</ul>  --%>
   				</div>
                      
   </div> 
   </div>
   </div>
   </div>
  

</html>
