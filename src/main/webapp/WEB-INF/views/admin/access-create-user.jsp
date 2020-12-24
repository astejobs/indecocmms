<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--  start nav-outer-repeat................................................... END -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
 
         <c:choose>
				<c:when test="${not empty edit}">
				<c:url value="/usermanagement/update" var="updateUrl"/>
				<c:set var="var_url" value="${updateUrl}" />
			    <c:set var="submit_caption" value="Update" />
			   
				</c:when>
	
				<c:otherwise>
				<c:url value="/usermanagement/adduser" var="addurl" />
			   <c:set var="var_url" value="${addurl}" />
			   <c:set var="submit_caption" value="Add" />
			  
			  </c:otherwise>
		</c:choose>

             
       <!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">${submit_caption} User</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                            <form:form method="post" action="${var_url}" class="form contact-form" id="contact_form" commandName="user" enctype="multipart/form-data">
                             <form:hidden path="id"/>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            ${submit_caption} User
                                        </h2>
                                        <c:if test="${not empty success}">
                                         <div class="success">${success}</div> 
                                         </c:if>
                                         <c:if test="${not empty fail}">
                                         <div class="error"> ${fail}</div>
                                         </c:if>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Name</span>
                                            <form:input type="text" path="username" name="name" id="name" class="input-md round form-control" placeholder="Name"/>
                                            <form:errors path="username" class="text-danger"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Password</span>
                                            <form:input type="password"  path="password"  name="pwd" id="pwd" class="input-md round form-control" placeholder="Password"/>
                                             <form:errors path="password" class="text-danger"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Company Name</span>
                                            <form:input type="text"  path="company"  name="pwd" id="pwd" class="input-md round form-control" placeholder="Company Name"/>
                                             <form:errors path="company" class="text-danger"/>
                                         </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">First Name</span>
                                            <form:input type="text" path="firstName" name="fname" id="fname" class="input-md round form-control" placeholder="First Name"/>
                                             <form:errors path="firstName" class="text-danger"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Last Name</span>
                                            <form:input type="text" path="lastName"  name="lname" id="lname" class="input-md round form-control" placeholder="Last Name"/>
                                              <form:errors path="lastName" class="text-danger"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Email</span>
                                            <form:input type="email" path="email" name="email" id="email" class="input-md round form-control" placeholder="Email"/>
                                             <form:errors path="email" class="text-danger"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Department</span>
                                            <form:input type="text" path="department" name="department" id="department" class="input-md round form-control" placeholder="Department"/>
                                             <form:errors path="department" class="text-danger"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Designation</span>
                                            <form:input type="text" path="designation" name="designation" id="designation" class="input-md round form-control" placeholder="Designation"/>
                                             <form:errors path="designation" class="text-danger"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Phone Number</span>
                                            <form:input type="text" path="phoneNo" name="pnumber" id="pnumber" class="input-md round form-control" placeholder="Phone Number"/>
                                             <form:errors path="phoneNo" class="text-danger"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Status</span>
                                            <form:select path="userTypeFlag" class="input-md round form-control">
                                                <form:option value="Active">Active</form:option>
                                                 <form:option value="In Active">In Active</form:option>
                                            </form:select>
                                             <form:errors path="userTypeFlag" class="text-danger"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Group</span>
                                            <form:select path="usergroup" class="input-md round form-control">
                                               <option value="">Choose Any Group</option>
                                               <form:options items="${groupList}" itemValue="id" itemLabel="userGroupName"/>
                                            </form:select>
                                             <form:errors path="usergroup" class="text-danger"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">User Image</span>
                                            <input type="file" name="image" id="photo" class="input-md round form-control" placeholder="Photo">
                                            <form:errors path="userImage" class="text-danger"/>
                                        </div>
                                        <div class="form-group float-right">
                                            <input type="submit" id="submit" class="btn" value="${submit_caption}" />
                                            <input type="submit" id="cancel" class="btn" value="Cancel" />
                                        </div>
                            </form:form>
                        </div>
                    </div>
                    <!-- End Contact Form -->
                </div>
                </div>
       

</body>
</html>
