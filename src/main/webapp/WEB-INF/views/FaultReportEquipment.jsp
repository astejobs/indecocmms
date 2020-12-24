<!DOCTYPE html>
<html>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
     table th{
         padding:4px;
     }
</style>
<script type="text/javascript">
function changeParent(id) {
	
	var option = document.createElement("option");
	option.text = document.getElementById(id).innerHTML;
	option.value = id;
	var select =  window.opener.document.getElementById('equipment');
	select.remove(0);
	select.appendChild(option);
	window.close();
   
}
</script>
       <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Equipment</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
        <!--Form-->

       <div class="container">
<div class="row">
        <div class="col-md-12">
        
        <div class="col-md-10 jumbotron offset-box">
                        <!-- Form -->
                        <div class="row">
                            <div class="col-md-12">
                            	<input type="hidden" id="context" value="${pageContext.servletContext.contextPath}"/>
                            	<c:url value="/faultReport/equipment" var="action" />
                                <form:form  action="${action}" commandName="equipmentSearch" method="post"  class="form contact-form" id="contact_form">
                                	<h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                        Select Equipment List
                                    </h2>
                                    <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Equipment Type</span>
                                    <form:select path="assetType" id="assetType" class="input-md round form-control">
                                        <form:option value="">Equipment Type</form:option>
                                        <form:options items="${assetTypeList}" itemLabel="assetTypeName" itemValue="id"/>
                                    </form:select>
                                </div>
                                <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Equipment Sub Type</span>
                                    <form:select path="assetSubtype" id="assetSubtype" class="input-md round form-control" onchange="SetName();">
                                        <form:option value="">Equipment Sub Type</form:option>
                                        <form:options items="${assetSubtypeList}" itemLabel="assetSubTypeName" itemValue="id"/>
                                    </form:select>
                                </div>
                               <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Equipment Name</span>
                                    <form:input type="text" path="name" id="Equip_Name" class="input-md round form-control" placeholder="Equipment Name"/>
                                </div>
                                <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Building</span>
                                    <form:select path="building" class="input-md round form-control" id="building" readonly="true">
                                        <form:option value="${building.id}">${building.name}</form:option>
                                    </form:select>
                                </div>
                                <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Location</span>
                                    <form:select path="location" class="input-md round form-control" id="location" readonly="true">
                                        <form:option value="${location.id}">${location.name}</form:option>
                                    </form:select>
                                </div>
                                <div class="form-group">
                                    <input type="submit" name="btnSub" id="btnSub" class="input-md btn" placeholder="Submit">
                                </div>
                                </form:form>
                                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                        Equipment List
                                    </h2>
                                     <div class="table-responsive">
                                        <table class="ssh-table table table-stripped" id="tblResult">
                                            <thead>
                                                <tr>
                                                    <th>Name</th>
                                                    <th>Building</th>
                                                    <th>Location</th>                                                 
                                                    <th>Type</th>
                                                    <th>Sub-Type</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                            	<c:forEach items="${equipmentList}" var="equip">
                                            	   <tr>	
                                            	   
	                                                 <td><a href="#" id="${equip.id}" onclick="javascript:changeParent(this.id)" >${equip.name}</a></td>
	                                                 <td>${equip.building.name}</td>
	                                                 <td>${equip.location.name}</td>	                                                
	                                                 <td>${equip.assetType.assetTypeName}</td>
 	                                                 <td>${equip.assetSubtype.assetSubTypeName}</td>
                                                   <tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
							  </div>
							</div>
                    </div>
                    <!-- End Form -->
        </div>
        <!--End Form-->
        </div>

</body>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/FaultReport.js"></script>

</html>
