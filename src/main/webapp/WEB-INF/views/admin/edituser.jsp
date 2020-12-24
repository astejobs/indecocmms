<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--  start nav-outer-repeat................................................... END -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>

      <!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Edit User</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                            <form class="form contact-form" id="contact_form">
                                <div class="clearfix">
                                    <div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            Edit User
                                        </h2>
                                          <c:if test="${not empty success}">
                                         <div class="success">${success}</div> 
                                         </c:if>
                                         <c:if test="${not empty fail}">
                                         <div class="error"> ${fail}</div>
                                         </c:if>
                                        <div class="form-group">
                                            <table class="table table stripped" id="tblResult">
                                                <thead>
                                                    <tr>
                                                        <th>User Name</th>
                                                        <th>Company ID</th>
                                                        <th>Name</th>
                                                        <th>Designation</th>
                                                        <th>Phone No.</th>
                                                        <th>User Type</th>
                                                        <th>User Group</th>
                                                        <th>Modify</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach items="${UserList}" var="users">
                                                    <tr>
                                                        <td>${users.username}</td>
                                                        <td>${users.company}</td>
                                                        <td>${users.firstName}</td>
                                                        <td>${users.designation}</td>
                                                        <td>${users.phoneNo}</td>
                                                        <td>${users.userTypeFlag}</td>
                                                        <td>${users.usergroup.userGroupName}</td>
                                                        <c:url value="/usermanagement/edit/${users.id}"  var="edit_url"/>
                                                          <c:url value="/usermanagement/delete/${users.id}"  var="delete_url"/>
                                                        <td><a href="${edit_url}"><i class="fa fa-edit"></i></a><a href="${delete_url}"><i class="fa fa-trash"></i></a></td>
                                                    </tr>
                                                 </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- End Contact Form -->
                </div>
        </div>

      

</body>
</html>