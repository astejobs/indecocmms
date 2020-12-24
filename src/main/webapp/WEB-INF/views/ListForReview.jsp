<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<body class="appear-animate">
<style> 
.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}



</style>

      
       <!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Schedule List</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
					                        List Of Schedules
					                    </h2>
                                        
                                        <div class="table-responsive">
                                       
                                            <table class="table table-striped table-hover" id="tblResult">
                                                <thead>
                                                    <tr>
                                                        <th>Schedule Number</th>
                                                        <th>Equipment</th>
                                                        <th>Start Schedule Date</th>
                                                        <th>End Schedule Date </th>
                                                        <th>Review</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                   <c:url value="/schedule/updateForReview" var="review_url"/>
                                              	   <c:forEach items="${listforreview}" var="schedule">
                                                    <tr>
                                                    	<td>
                                                    	${schedule.scheduleNumber }
                                                    	</td>
                                                    	
                                                    	<td>
                                                    	<c:forEach items="${schedule.equipment}" var="equip">
							   								 ${equip.name}
							 							 </c:forEach>
                                                    	</td>
                                                    	
                                                     <td><fmt:formatDate value="${schedule.startDate}" pattern="dd-MM-yyyy"/></td>
							  						 <td><fmt:formatDate value="${schedule.endDate}" pattern="dd-MM-yyyy"/></td>
                                                        
                                                        <td><a id="edit" href="${review_url}/${schedule.id }" style="cursor:pointer"><img style="width:20px" src="${pageContext.servletContext.contextPath}/resources/assets/images/edit.png" /></a></td>
                                                    </tr>
                                                 </c:forEach> 
                                                </tbody>
                            
                                            </table>
                                        </div>
                                       
                                       
                                       
                                    
										
                                      </div>
							</div>
						</div>
						</div>
							
         
 
          
</body>
</html>
