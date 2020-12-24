<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

        <!-- End Navigation panel -->
        <!-- Head Section -->
        <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">DASHBOARD</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="../../CMMS-Dashboard.html">Dashboard</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
       <div  style="height:100px;position:relative"></div>
      		  <div class="container" style="width:100%;">
		         <div class="row">
		            <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
		    		   <jsp:include page="/WEB-INF/views/menu.jsp" />
		           </div>
		           <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">
                            <form:form class="form contact-form" id="contact_form" commandName="req" >
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Selected RFA Approved from PO
                                        </h2>
                                        <c:if test="${not empty success }">
                                        <div class="success">${success}</div>
                                        </c:if>
                                        <c:if test="${not empty fail }">
                                        <div class="error">${fail}</div>
                                        </c:if>
                                        <div class="form-group">
                                            <input type="text" name="Requestor" id="Requestor" style="width:49%" class="input-md round form-control" value="${req.requestor.firstName}">&nbsp;&nbsp;&nbsp;
                                            <fmt:formatDate value="${req.submittionDate}" pattern="dd-MM-yyyy" var="sdate"/>
                                            <input type="text" name="date" id="date" style="width:49%" class="input-md round form-control" value="${sdate}">
                                        </div>
                                         <div class="form-group">
                                            <input type="text" name="site Approver" id="siteapprover" style="width:49%" class="input-md round form-control" value="${req.siteapprover.firstName}">&nbsp;&nbsp;&nbsp;
                                            <fmt:formatDate value="${req.siteApproverDate}" pattern="dd-MM-yyyy" var="cdate"/>
                                            <input type="text" name="date_costcenter" id="date_costcenter" style="width:49%" class="input-md round form-control" value="${cdate}">
                                        </div>
			                            <div class="form-group">
                                            <input type="text" name="costcenter" id="costcenter" style="width:49%" class="input-md round form-control" value="${req.costcenter.firstName}">&nbsp;&nbsp;&nbsp;
                                            <fmt:formatDate value="${req.costCenterdate}" pattern="dd-MM-yyyy" var="cdate"/>
                                            <input type="text" name="date_costcenter" id="date_costcenter" style="width:49%" class="input-md round form-control" value="${cdate}">
                                        </div>
                                        <div class="form-group">
                                            <input type="text" name="Approver" id="Approver" style="width:49%" class="input-md round form-control" value="${req.approver.firstName}">&nbsp;&nbsp;&nbsp;
                                             <fmt:formatDate value="${req.approverDate}" pattern="dd-MM-yyyy" var="adate"/>
                                            <input type="text" name="date_Approver" id="date_Approver" style="width:49%" class="input-md round form-control" value="${adate}">
                                        </div>
                                        <div class="form-group">
                                            <input type="text" name="Finance_Deptt" id="Finance_Deptt" style="width:49%" class="input-md round form-control" value="${req.finaceapprover.firstName}">&nbsp;&nbsp;&nbsp;
                                            <fmt:formatDate value="${req.financerDate}" pattern="dd-MM-yyyy" var="fdate"/>
                                            <input type="text" name="date_Finance" id="date_Finance" style="width:49%" class="input-md round form-control"value="${fdate}">
                                        </div>
                                 </form:form>
                                 <c:url value="/requestForApproval/updatefraforAdmin" var="url" />
                                  <c:url value="/requestForApproval/viewRequest" var="viewurl"><c:param name="id" value="${id}"></c:param></c:url>
                                      <c:if test="${req.adStatus =='Pending'}">
                                      <form method="post" action="${url}"  enctype="multipart/form-data">
                                      
                                           <input type="hidden"   name="id" value="${id}">
                                         Admin Signature
                                         <div class="form-group">
                                            <input type="file" required="required" name="adminsigniture">
                                        </div>
                                      
                                      <input type="hidden" name="id" value="${id}"/>
                                        <div class="form-group">
                                           <label>Comments</label>
                                           <textarea id="desp"  name="comments"   class="input-md round form-control">${req.adminComments}</textarea>
                                        </div>
                                        <div class="form-group">
                                            <input type="Submit" name="view_form" id="view_form"  class="input-md round" value="Submit">
                                        </div>
                                        </form>
                                         </c:if>
                                         <c:if test="${req.adStatus ne 'Pending'}">
                                          <div class="form-group">
                                           <label>Comments</label>
                                           <textarea id="desp"  name="comments"   class="input-md round form-control">${req.adminComments}</textarea>
                                        </div>
                                        </c:if>
                                        <a href="${viewurl}"><i style="margin-bottom:100px;" class="fa fa-eye" ></i></a>

                                        
                                       
                                   </div>
                           </div>
                     </div>
         
   
</html>
