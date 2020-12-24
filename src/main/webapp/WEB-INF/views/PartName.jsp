<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<body class="appear-animate">

        <!-- Head Section -->
        <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="../assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">DASHBOARD</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="../CMMS-Dashboard.html">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="../forms_iCMMS/configurator-part-name.html">Part Name</a>
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
            <!-- Contact Form -->


                <div class="clearfix">
                    <div>
                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                            Part Details
                        </h2>
                        <c:url var="delete" value="/partname/delete"/>
                        <form:form  action="${delete}" method="post" class="form contact-form" id="contact_form">
                        <div class="form-group">
                        <c:if test="${not empty success}">
                            <div class="success">${success}</div>
						</c:if>
						<c:if test="${not empty fail}">
                            <div class="error">${fail}</div>
                        </c:if>
                            <table class="table table-stripped" id="tblResult">
                                <thead>
                                    <tr>
                                        <th><input type="checkbox" /></th>
                                        <th>Part Name</th>
                                        <th>Modify</th>
                                    </tr>
                                </thead>
                                 <tbody>
                                 	<c:url value="/partname/update/" var="edit1"/>
                                    <c:forEach items="${partNameList}" var="partName">
                                    <tr>
                                        <td>
                                           <input type="checkbox" name="id" value="${partName.id}"/>
                                        </td>
                                        <td>${partName.partName}</td>
                                        <td><a href="${edit1}/${partName.id}" style="cursor: pointer"><i class="fa fa-edit"></i></a></td>
                                    </tr>
                                    </c:forEach>
                                </tbody> 
                            </table>
                        </div>
                        <div class="form-group">
                            <input type="submit" id="submit" value="Delete" />
                        </div>
                           </form:form>
                        <c:choose>
											<c:when test="${not empty edit}">
												<c:set var="caption" value="Update" />
												<c:url var="action" value="/partname/update/${partName.id }"/>
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
		                                            Edit Part Name
		                                        </h2>
											</c:when>
											<c:otherwise>
												 <c:set var="caption" value="Add" />
												 <c:url var="action" value="/partname"/>
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            		Add Part Name
                                        		</h2>
											</c:otherwise>
										</c:choose>
	                        <form:form action="${action}" commandName="partName" method="POST" >
	                        <div class="form-group">
	                        	<form:hidden path="id"/>
 	                            <form:input type="text" path="partName" id="partname" class="input-md round form-control" placeholder="Part Name"/>
	                            <form:errors path="partName" class="err"/>
	                        </div>
	                        <div class="form-group">
	                            <input type="submit" id="submit" value="${caption}" />
	                        </div>
	                        </form:form>
                    </div>
                </div>
         


            <!-- End Contact Form -->
        </div>
        <!--End Form-->

</body>

</html>
