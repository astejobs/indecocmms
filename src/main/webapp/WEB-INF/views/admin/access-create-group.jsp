<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--  start nav-outer-repeat................................................... END -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

        <!-- End Navigation panel -->
         <c:choose>
        		<c:when test="${not empty edit}">
				<c:url value="/groupmanagement/update" var="updateUrl"/>
				<c:set var="var_url" value="${updateUrl}" />
			    <c:set var="submit_caption" value="Update" />
			   
				</c:when>
	
				<c:otherwise>
				<c:url value="/groupmanagement/addgroup" var="addurl" />
			   <c:set var="var_url" value="${addurl}" />
			   <c:set var="submit_caption" value="Add" />
			  
			  </c:otherwise>
		</c:choose>
        

 <!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">${submit_caption} Group</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                            <form:form method="post" action="${var_url}" class="form contact-form" id="contact_form" commandName="group">
                                        <form:hidden path="id"/>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                           ${submit_caption} User Groups
                                        </h2>
                                         <c:if test="${not empty success}">
                                         <div class="success">${success}</div> 
                                         </c:if>
                                         <c:if test="${not empty fail}">
                                         <div class="error"> ${fail}</div>
                                         </c:if>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Group ID</span>
                                            <form:input type="text" path="userGroupName" name="groupid" id="groupid" class="input-md round form-control" placeholder="Group ID"  required="required"/>
                                            <form:errors path="userGroupName"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Description</span>
                                            <form:textarea path="groupDesc" class="input-md round form-control" placeholder="Description" required="required"></form:textarea>
                                              <form:errors path="groupDesc"/>
                                        </div>
                                        <div class="form-group">
                                            <span>Read Only</span> &nbsp;
                                            <form:checkbox path="readOnly" class=""/>
                                              <form:errors path="readOnly"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Role</span>
                                            <form:select path="role" class="input-md round form-control">
                                            	<form:option value=""> Select Role</form:option>
                                            	<form:options items="${roles}" itemLabel="name" itemValue="id"/>
                                            </form:select>
                                              <form:errors path="role"/>
                                        </div>
                                        <div class="form-group float-right">
                                            <input type="submit" id="submit" class="btn" value="${submit_caption}" />
                                            <input type="submit" id="cancel" class="btn" value="Cancel" />
                                        </div>
                            </form:form>
                        </div>
                     </div>
 </div>
 </div>
 

</body>
</html>
