<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--  start nav-outer-repeat................................................... END -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel=stylesheet type="text/css" href="//cdn.datatables.net/1.10.13/css/jquery.dataTables.min.css">
<style>
.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}
 table th,td {
                      white-space: nowrap; 
                      text-overflow:ellipsis; 
                      overflow: hidden;
                      max-width:1px;
    }


</style>
</head>

        <!-- End Navigation panel -->
        <!-- Head Section -->
        <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">QUOTATION SEARCH</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="">Search Quotation</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Head Section -->
       <div  style="height:100px;position:relative"></div>
      		  <div class="container" style="width:100%;">
		         <div class="row">
		            <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
		    		   <jsp:include page="/WEB-INF/views/menu.jsp" />
		           </div>
		           <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                           Search Quotation
                                        </h2>
                               <c:url value="/quotation/searchquotation" var="search_url"></c:url>         
                               <form:form method="get" action="${search_url}"  class="form contact-form" commandName="searchquotation"    id="contact_form">
                                <div class="clearfix">
                                    Total No of Quotations : ${fn:length(quotations)} 
                                    <div>
                                         <div class="form-group">
                                            <form:input type="text"  path="quotationCode"  name="Quotation_Code" id="Quotation_Code" class="input-md round form-control" placeholder="Quotation Code" />
                                        </div>
                                         <div class="form-group aft">
                                            <form:select type="text" path="type"   name="Quotation_type" id="Quotation_type" class="input-md round form-control" placeholder="Quotation Type" >
                                             <form:option value="">Type</form:option>
                                            <form:option value="Normal">General Quotation</form:option>
                                             <form:option value="StarRate">Star Rate Quotation</form:option>
                                            </form:select>
                                        </div>
                                         <div class="form-group rem">
                                            <form:select type="text" path="value"   name="Value" id="Value" class="input-md round form-control" placeholder="Value" >
                                            <form:option value="">Criteria</form:option>
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
                                          
                                            <form:input type="text" path="from"  name="fromDate"  id="fromDat" class="input-md round form-control" placeholder="From Date" style="width:25%" />
                                            <img class="cl" src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="NewCssCal('fromDat','dd-mm-yyyy')" style="cursor:pointer;width:30px" />
                                            <form:input type="text" path="to"   name="toDate" id="toDat" class="input-md round form-control" placeholder="To Date" style="width:25%" />
                                            <img class="cll" src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('toDat','dd-mm-yyyy')" style="cursor:pointer;width:30px" />
                                        </div>
                                      
                                        <div class="form-group">
                                            <input type="submit" id="submit" value="Search" />
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                                        <c:if test="${not empty success}">
                                         <div class="success">${success}</div> 
                                         </c:if>
                                         <c:if test="${not empty fail}">
                                         <div class="error"> ${fail}</div>
                                         </c:if>
                                        <div class="form-group">
                                            <table class="table table-stripped" id="datatable" style="margin-bottom:40px;">
                                                <thead>
                                                    <tr>
                                                        <th>Quotation For</th>
                                                        <th>Quotation Validity</th>
                                                        <th>Quotation Desc</th>
                                                        <th>Quotation Date</th>
                                                        <th>Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                   <c:forEach items="${quotations}" var="q">
                                                    <tr>
                                                    <td data-toggle="tooltip" title="${q.quotationFor}">${q.quotationFor}</td>
                                                    <td data-toggle="tooltip" title="${q.validity}">${q.validity}</td>
                                                    <td data-toggle="tooltip" title="${q.quotationDescription }">${q.quotationDescription }</td>
                                                    <td data-toggle="tooltip" title="${q.quotationDate}"><fmt:formatDate value="${q.quotationDate}" pattern="dd-MM-yyyy"/></td>
                                                    <c:url value="/quotation/editquotation/${q.id}" var="edit_url"></c:url>
                                                    <c:url value="/quotation/delete/${q.id}" var="delete_url"></c:url>
                                                    <c:url value="/quotation/view/${q.id}" var="view_url"></c:url>
                                                    <td><a href="${edit_url}"><i class="fa fa-edit" ></i></a>&nbsp;&nbsp;&nbsp;<a href="${delete_url}"><i class="fa fa-trash" ></i></a>&nbsp;&nbsp;&nbsp;<a href="${view_url}"><i class="fa fa-eye" ></i></a></td>
                                                   
                                                    
                                                    </tr>
                                                    
                                                    
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                            
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
				<ul class="pagination pagination-sm" style=" position: relative; left:320px; ">								 
											 <c:if test="${page>1}">

											<li>			<a class="page-far-left"
															href="<c:url value="/quotation/pageno=1"/>"><<</a> </li>

											<li>			<a class="page-left"
														    href="<c:url value="/quotation/pageno=${page-1}"/>"> < </a> </li>

													</c:if>
													
													
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																<li class="active" ><a>${i}</a></li>
																</c:when>
																<c:otherwise>
																<li>	<a class=""
																		href="<c:url value="/quotation/pageno=${i}"/>"
																		>${i}</a>  </li>
																</c:otherwise>
															</c:choose>
														</c:forEach>

													 
														

														 <c:if test="${page<total}">
														<li>	<a class="page-right" 
															 	href="<c:url value="/quotation/pageno=${page+1}"/>">> </a> </li>
	
											<li>				<a class="page-far-right"
																 href="<c:url value="/quotation/pageno=${total}"/>" >>></a> </li>
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
                              
                           </div>
                            

  <script>  
  (function(){
	  $('.cl').click(function(){
		  if( $('#fromDat').val("")!=undefined){
			  $('#fromDat').val("");  
		  }
		 
	  });
	  $('.cll').click(function(){
		 
		  if( $('#toDat').val("")!=undefined){
			  $('#toDat').val("");  
		  }
	  });
	
	 
	  $( "#Quotation_type" ).trigger( "change" );
	
	  
	})(); 
  
     $(document).ready(function(){
    	 $('#Quotation_type').on('change', function() {
    	//	 alert($( "#Quotation_type option:selected" ).text());
    		 if($( "#Quotation_type option:selected" ).val()=='StarRate'){
    			/*  alert($( "#Quotation_type option:selected" ).text()); */
    			/*  $('.rem').remove(); */
    			 $('.rem').hide();
    		 }
    		 else{
    			/*  $('.aft').after('<div class="form-group rem"><select id="Value" name="value" placeholder="Value" type="text" class="input-md round form-control"> <option value="">Criteria</option><option value="Discount">Discount</option><option value="Emergency">Emergency</option></select></div>'); */
    			 $('.rem').show();
    		 }
    	 });
    	 
     });
  
  </script>
</html>
