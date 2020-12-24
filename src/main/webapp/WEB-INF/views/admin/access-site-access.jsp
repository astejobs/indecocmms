<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>
 <script>
 $(document).ready(function() {
	 
	 $('#group').change(function()
			 {
		     
		     $('input:checkbox').removeAttr('checked');
		       $.ajax({
			    url: $('#aurl').val()+$(this).val(),
			    type: "GET",
			    cache: false,
			    
			    success: function(data){
			    	
			    	$.each(data, function(index, element) {
			    		
			    		$('input[type=checkbox]').each(function () {
			    			
			    			if($(this).val() == element.id){
				    			
			    				$(this).prop("checked","true");
				    		   
				    		} 
			    		   
			    		});
			    	
			           });
			      }
			});
		});
	    
	})
    </script>
</head>
<!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Admin</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Site Priviledges</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">           
                        <c:url value="/access/siteaccess" var="url"/>
                            <form:form  method="post" action="${url}" commandName="sitepriviledges"   class="form contact-form" id="contact_form">
                                <div class="clearfix">
                                       <c:url value="/access/getsitesforgroup/" var="ajaxUrl"/>
                                        <input type="hidden" id="aurl"  value="${ajaxUrl}"/>
                                    <div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            Site Access Management
                                        </h2>
                                        <c:if test="${not empty success}">
                                         <div class="success"> ${success}</div>
                                         </c:if>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Group</span>
	                                        <form:select path="userGroup" id="group"  class="input-md round form-control">
		                                        <form:option value="">Select Any Group</form:option>
		                                        <c:forEach items="${groupList}" var="groups">
		                                           <form:option value="${groups.id}">${groups.userGroupName}</form:option>
		                                         </c:forEach>
	                                        </form:select>
                                        </div>
                                        
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            Sites
                                        </h2>
                                        <div class="form-group">
                                            <c:forEach items="${workspaces}" var="w" >
                                            <form:checkbox path="wspaces" value="${w.id}" />&nbsp;<a class="a">${w.workspaceId}</a><br />
                                            </c:forEach>
                                        </div>
                                        <div class="form-group float-right">
                                            <input type="submit" id="submit" class="btn" value="Save" />
                                            <input type="submit" id="cancel" class="btn" value="Cancel" />
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                    <!-- End Contact Form -->
                </div>
        </div>


</body>
</html>
