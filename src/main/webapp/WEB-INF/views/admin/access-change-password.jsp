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
			    <li class="breadcrumb-item active" aria-current="page">Change User Password</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
       
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                            <c:url value="/access/changepassword" var="url"/>
                            <form:form method="post"  action="${url}" commandName="password"  class="form contact-form" id="contact_form">
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            Change User Password
                                        </h2>
                                        <c:if test="${not empty nouserfound }">
                                        <div class="error">${nouserfound}</div>
                                        </c:if>
                                        <c:if test="${not empty wrongpassword}">
                                        <div class="error">${wrongpassword}</div>
                                        </c:if>
                                        <c:if test="${not empty nomatchpasswords}">
                                        <div class="error">${nomatchpasswords}</div>
                                        </c:if>
                                        <c:if test="${not empty success}">
                                        <div class="success">${success}</div>
                                        </c:if>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">User Name</span>
                                            <form:input path="userName" type="text" name="name" id="uname" class="input-md round form-control" placeholder="User Name"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Password</span>
                                            <form:input path="oldpassword" type="password" name="pwd" id="pwd" class="input-md round form-control" placeholder="Password"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">New Password</span>
                                            <form:input  path="newpassword"  type="password" name="npwd" id="npwd" class="input-md round form-control" placeholder="New Password"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Confirm Password</span>
                                            <form:input  path="confirmNewpassowrd"  type="password" name="cpwd" id="cpwd" class="input-md round form-control" placeholder="Confirm Password"/>
                                        </div>
                                        <div class="form-group float-right">
                                            <input type="submit" id="submit" class="btn" value="Save" />
                                            <input type="submit" id="cancel" class="btn" value="Cancel" />
                                        </div>
                            </form:form>
                        </div>
                    </div>
				 </div>
				 </div>
 			

</body>
</html>
