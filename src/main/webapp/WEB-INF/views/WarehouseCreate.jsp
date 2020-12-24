<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<style> 
  .table tr.footable-filtering>th {
	background-color: #FFF;
}

.btn-primary {
	color: #fff;
	background-color: #0754a4;
	border-color: #0754a4;
}

.dropdown-toggle{
          display:none;
}
</style>
        <!-- Head Section -->
      <section class="small-section bg-dark-alfa-30 parallax-2"
		style="margin-bottom:-70px;position:relative"
		data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
	<div class="relative container align-left">
		<div class="row">
			<div class="col-md-8">
				<h1 class="hs-line-11 font-alt mb-10 mb-xs-0"
					style="margin-top: 5px">Create Warehouse</h1>
			</div>

			<div class="col-md-4 mt-30">
				<div class="mod-breadcrumbs font-alt align-right">
					<a class="label-click"
						href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a>&nbsp;/&nbsp;<a
						class="label-click" href=""> Create Warehouse</a>
				</div>
			</div>
		</div>
	</div>
	</section>
        <!-- End Head Section -->
        <!---MENU-->
        <div style="height:100px;position:relative"></div>
        <div class="container" style="width:100%;">
            <div class="row">
                <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
                      <jsp:include page="/WEB-INF/views/menu.jsp" />
                </div>
            <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">

                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                        Add  Warehouse
                    </h2>
					<c:if test="${not empty success}">
                            <div class="success">${success}</div>
						</c:if>
						<c:if test="${not empty fail}">
                            <div class="error">${fail}</div>
                        </c:if>
                    <!-- Contact Form -->
                    		<c:url var="action" value="/warehouse"/>
                            <form:form action="${action}" commandName="warehouse" method="post" class="form contact-form" id="contact_form" novalidate="novalidate">
                                <div class="clearfix">
                                    <div>
                                        <div class="form-group">
                                            <form:input type="text" placement="left" data-bvalidator="required" path="name" id="warehouse_name" class="input-md round form-control" placeholder="Warehouse Name" />
                                       		<form:errors path="name" class="err"/>
                                        </div>
                                        <div class="form-group">
                                            <form:textarea path="description" data-bvalidator="required" class="input-md round form-control col-lg-3" style="margin-bottom:10px" placeholder="Warehouse Description"/>
                                        </div>
                                        <div class="form-group">
                                            <form:textarea path="location" data-bvalidator="required" class="input-md round form-control col-lg-3" style="margin-bottom:10px" placeholder="Location Description" />
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" id="btnSave" value="Add" />
                                        </div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Add Users to Warehouse
                                        </h2>
                                        <form:errors path="storeKeepers" class="err"/>
                                        <c:choose>
                                        <c:when test="${not empty userList}">
                                        <div class="form-group">
                                            <table class="table table-stripped" id="tblResult" data-paging="true" data-filtering="true">
                                                <thead>
                                                    <tr>
                                                        <th><input type="checkbox" /></th>
                                                        <th>Name</th>
                                                        <th>User Id</th>
                                                        <th>Department</th>
                                                        <th>Designation</th>
                                                        <th>Status</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                	<c:forEach items="${userList}" var="user" varStatus="loop"> 
                                                    <tr> <td><input type="checkbox" name="users" value="${user.id}"/></td>
                                                        <td>${user.firstName} ${user.lastName}</td>
                                                        <td>${user.username}</td>
                                                        <td>${user.department}</td>
                                                        <td>${user.designation}</td>
                                                        <td>${user.userTypeFlag}</td>
                                                    </tr>
                                            		</c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        </c:when>
                                        <c:otherwise>
                                       		<label class="form-group">No User to be added</label>
                                       		<input type="hidden" name="users" value=""/>
                                        </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </form:form>
                        
                    <!-- End Contact Form -->
                </div>
          
        </div>

        <!--End Form-->
      <script>
            jQuery(function($) {
                $('.table').footable();
            });
            
            
            $(document).ready(function () {
    			// alert("YYY")
    			// console.log("YYY");
    		        console.log($('form').bValidator({placement:"left"}));
    		        
    		      
    		    });
            
            
            

        </script>
</body>
</html>
