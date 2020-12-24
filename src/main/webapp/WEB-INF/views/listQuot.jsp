<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
  <style> 
     table {
        table-layout:auto;
     }
     
     
     .pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}

  
  </style>
   <script type="text/javascript">
        function changeParent(id) {
        	var option = document.createElement("option");
        	option.selected = true;
        	option.text = document.getElementById(id).innerHTML;
        	option.value = id;
        	var select =  window.opener.document.getElementById('quotation');
        	select.appendChild(option);
      }
    </script>
   
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
                            <a class="label-click" href="../CMMS-Dashboard.html">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="">List Quote</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Head Section -->

    
        <div  style="height:100px;position:relative"></div>
      		  <div class="container" style="width:100%;">
		         <div class="row">
		           
		           <div class="col-xs-12 col-sm-12 col-lg-12 col-md-12">
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                        Civil Asset Details
                    </h2>
                        <c:url value="/requestForApproval/listquotation" var="url"></c:url>
                            <form:form method="post" action="${url}"  class="form contact-form" commandName="searchquotation"    id="contact_form">
                                <div class="clearfix">
                                 Total No of Quotations : ${fn:length(listofQuotation)} 
                                    <div>
                                         <div class="form-group">
                                            <form:input type="text"  path="quotationCode"  name="Quotation_Code" id="Quotation_Code" class="input-md round form-control" placeholder="Quotation Code" />
                                        </div>
                                        <div class="form-group">
                                            <form:select type="text" path="type"   name="Quotation_type" id="Quotation_type" class="input-md round form-control" placeholder="Quotation Type" >
                                             <form:option value="">Type</form:option>
                                            <form:option value="Normal">General Quotation</form:option>
                                             <form:option value="StarRate">Star Rate Quotation</form:option>
                                            </form:select>
                                        </div>
                                         <div class="form-group">
                                            <form:select type="text" path="value"   name="Value" id="Value" class="input-md round form-control" placeholder="Value" >
                                            <form:option value="">Criterea</form:option>
                                            <form:option value="Discount">Discount</form:option>
                                            <form:option value="Emergency">Emergency</form:option>
                                            </form:select>
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="clientName"   name="Client_Name" id="Client_Name" class="input-md round form-control" placeholder="Client Name" />
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text"  path="referenceNumber"   name="SITE_Refrence_No" id="SITE_Refrence_No" class="input-md round form-control" placeholder="SITE Refrence No" />
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="from"  name="fromDate" id="fromDate" class="input-md round form-control" placeholder="From Date" style="width:25%" />
                                            <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('fromDate','ddMMyyyy')" style="cursor:pointer;width:30px" />
                                            <form:input type="text" path="to"   name="toDate" id="toDate" class="input-md round form-control" placeholder="To Date" style="width:25%" />
                                            <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('toDate','ddMMyyyy')" style="cursor:pointer;width:30px" />
                                        </div>
                                      
                                        <div class="form-group">
                                            <input type="submit" id="submit" value="Search" />
                                        </div>
                                    </div>
                                </div>
                            </form:form>
 
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080"></h2>
                    <div class="form-group">
                        <table class=" table table striped">
                            <thead>
                                <tr>
                                    <th ></th>
                                    <th>Quot. Code</th>
                                    <th>Quot. Date</th>
                                    <th>Quot. For</th>
                                    <th>Ref. NO</th>
                                </tr>
                            </thead>
                            
                            <tbody>
                            <c:forEach items="${listofQuotation}" var="quotlist">
                            <tr>
                                <td><input type="hidden" value="${quotlist.id}"></td>
                                <td><a id="${quotlist.id}" onclick="javascript:changeParent(this.id)" >${quotlist.quotationCode}</a></td>
                                 <td><fmt:formatDate value="${quotlist.quotationDate}" pattern="dd-MM-yyyy"/> </td>
                                  <td>${quotlist.quotationFor}</td>
                                   <td>${quotlist.refrenceno}</td>
                            </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                </div>
                <c:if test="${1<total}">
									<c:choose>
										<c:when test="${total>10}">
											<div class="clearfix">
												<%-- Showing 10 pages out of ${total} pages. --%>
											</div>
											<c:choose>
												<c:when test="${page<=5}">
													<c:set var="from" value="1" />
													<c:choose>
														<c:when test="${total>from + 9}">
															<c:set var="to" value="${from + 9}" />
														</c:when>
														<c:otherwise>
															<c:set var="to" value="${total}" />
														</c:otherwise>
													</c:choose>
												</c:when>

												<c:otherwise>
													<c:set var="from" value="${page-5}" />
													<c:choose>
														<c:when test="${total>page+5}">
															<c:set var="to" value="${page+4}" />
														</c:when>
														<c:otherwise>
															<c:set var="from" value="${total-9}" />
															<c:set var="to" value="${total}" />
														</c:otherwise>
													</c:choose>
												</c:otherwise>
											</c:choose>
										</c:when>

										<c:when test="${total>0}">
											<br>
											<%-- Showing ${total} page(s) out of ${total} page(s). --%>
											<c:set var="from" value="1" />
											<c:set var="to" value="${total}" />
										</c:when>
									</c:choose>
									<table id="paging-table">
										<tbody>
											<tr>
											 <td>
	<ul class="pagination pagination-sm" style=" position: relative; left:600px;bottom:50px; ">					 
											 <c:if test="${page>1}">

											<li>			<a class="page-far-left"
															href="<c:url value="/requestForApproval/pageno=1"/>"><<</a> <li>

										<li>				<a class="page-left"
														    href="<c:url value="/requestForApproval/pageno=${page-1}"/>">< </a>  </li>

													</c:if>
													
													
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																	<li class="active" ><a>${i}</a></li>
																</c:when>
																<c:otherwise>
										<li>							<a class=""
																		href="<c:url value="/requestForApproval/pageno=${i}"/>"
																		>${i}</a> </li>
																</c:otherwise>
															</c:choose>
														</c:forEach>

													

														 <c:if test="${page<total}">
													<li>		<a class="page-right" 
															 	href="<c:url value="/requestForApproval/pageno=${page+1}"/>">> </a>  </li>
	
									<li>						<a class="page-far-right"
																 href="<c:url value="/requestForApproval/pageno=${total}"/>" >>></a> </li>
														</c:if>
															</ul>	
													
													</td>

												</tr>
										</tbody>
										</table>
										</c:if>
           </div>
     </div>
 </div>
</html>