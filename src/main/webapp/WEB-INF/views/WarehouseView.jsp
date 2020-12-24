<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>

        <!-- Head Section -->
        <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">Warehouse View</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="#">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="#">Warehouse View</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Head Section -->
        <!---MENU-->

        <!--End Menu-->
        <!--Form-->
        <div style="height:100px;position:relative"></div>
        <div class="container" style="width:100%;">
            <div class="row">
                <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
                    <jsp:include page="/WEB-INF/views/menu.jsp" />
                </div>
                <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">

                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                        Warehouse Details
                    </h2>

 					<c:if test="${not empty success}">
                                         <div class="success">${success}</div>
										</c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
                   
                    <!-- Contact Form -->

                    <form class="form contact-form" id="contact_form">
                        <div class="clearfix">
                            <div>
                                <div class="form-group">
                                    <table class="table table-stripped" id="tblResult" data-paging="true">
                                        <thead>
                                            <tr>
                                                <th>Warehouse Name</th>
                                                <th>Description</th>
                                                <th>Location Description</th>
                                                <th>Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach items="${warehouseList}" var="warehouse">
                                            <tr>
                                                <td>${warehouse.name}</td>
                                                <td>${warehouse.description }</td>
                                                <td>${warehouse.location}</td>
                                                <td><a href="${pageContext.servletContext.contextPath}/warehouse/view/${warehouse.id}"><i class="fa fa-plus"></i></a></td>
                                            </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </form>

                    <!-- End Contact Form -->
 <script>
            jQuery(function($) {
                $('.table').footable();
            });

        </script>

</body>

</html>
