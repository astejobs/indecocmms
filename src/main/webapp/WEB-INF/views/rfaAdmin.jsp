<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<style>  
     table {
        table-layout:auto;
     
     }
     
      table th,td {
                      white-space: nowrap; 
                      text-overflow:ellipsis; 
                      overflow: hidden;
                      max-width:1px;
    }
     table th ,td {
       padding:0px;
       text-align:center; 
     }
     
      p.mar{
         margin-bottom:0px;
     }
     
           .pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}
  
  table tr.footable-filtering>th {
         background-color:#FFF;
     } 
     
     button.dropdown-toggle {
   padding:11px;
   display:none;
}
.btn-primary {
    color: #fff;
    background-color: #0754a4;
    border-color: #0754a4;
}
       
     
     

</style>
</div>
        </nav>
        <!-- End Navigation panel -->
        <!-- Head Section -->
        <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">  LIST  OF RFA'S FROM PO</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="">RFA'S from PO</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Head Section -->
        <!---MENU-->
       <div  style="height:100px;position:relative"></div>
      		  <div class="container" style="width:100%;">
		         <div class="row">
		            <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
		    		   <jsp:include page="/WEB-INF/views/menu.jsp" />
		           </div>
		           <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">
                            <form class="form contact-form" id="contact_form">
                                <div class="clearfix">
                                    <div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            List of RFA'S from PO
                                        </h2>
                                        <div class="form-group">
                                            <table class="table table-stripped" id="datatable" data-paging="true" data-filtering="true">
                                                <thead>
                                                    <tr>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Reference No.">Ref. No.</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Job Site">Job Site</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Quotation Code">Quot. Code</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Sub. Date">Sub. Date</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Status">Status</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Approved Date By Financer">Financer Date</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Approved Date By Admin">Admin Date</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Tot. Quoted Price">Price Quoted</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Views">Views</th>
                                                    </tr>
                                                </thead>
                                                 <tbody>
                                                    <c:forEach items="${req}" var="requests">
                                                    <tr>
                                                    <td data-toggle="tooltip" data-placement="bottom" title="${requests.refnumber}">${requests.refnumber}</td>
                                                    <td  data-toggle="tooltip" data-placement="bottom" title="${requests.jobSite}">${requests.jobSite}</td>
                                                    <td>
                                                    <c:forEach items="${requests.quotationheader}" var="q">
                                                   <p class="mar" data-toggle="tooltip" data-placement="bottom" title="${q.quotationCode} "> ${q.quotationCode} </p>
                                                    </c:forEach>
                                                    </td>
                                                    <td   data-toggle="tooltip" data-placement="bottom" title="${requests.submittionDate} "><fmt:formatDate pattern="dd-MM-yyyy" 
                                                       value="${requests.submittionDate}"/></td>
                                                       <td   data-toggle="tooltip" data-placement="bottom" title="${requests.adStatus}">${requests.adStatus}</td>
                                                    <td  data-toggle="tooltip" data-placement="bottom" title="${requests.financerDate}"><fmt:formatDate pattern="dd-MM-yyyy" 
                                                       value="${requests.financerDate}"/></td>
                                                    <td data-toggle="tooltip" data-placement="bottom" title="${requests.adminDate}" ><fmt:formatDate pattern="dd-MM-yyyy" 
                                                       value="${requests.adminDate}"/></td>     
                                                     <td data-toggle="tooltip" data-placement="bottom" title="${requests.totalPriceQuoted}">${requests.totalPriceQuoted}</td>
                                                   <td><a href="<c:url value="/requestForApproval/viewRequestforAdmin"><c:param name="id" value="${requests.id}"/><c:param name="user" value="Admin"/></c:url>"
									                   id="btnView"><i class="fa fa-eye"></i></a></td>
                                                    </tr>
                                                    </c:forEach>
                                                   
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- End Contact Form -->
                </div>
            
     <script type="text/javascript">
 jQuery(function($){
		$('.table').footable();
	});
 
 </script>
      
</html>
