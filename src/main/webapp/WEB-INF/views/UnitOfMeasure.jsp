<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<body class="appear-animate">
<style> 
.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}

input[type="button"] {
    border: none;
    background-color: #0754a4; 
    color: #fff;
    padding: 7px 9px;
  
    
}

.bac{
          background-color: #FFF;
          border-top: 0px solid #FFF;
}

input[type="submit"] {
    border: none;
    background-color: #0754a4;
    color: #fff;
    padding: 7px 9px;
    border-radius: 0px;
  
    
}

table{
        table-layout:auto;
}

.pagination {
    display: inline-block;
    padding-left: 0;
    margin: -24px 0;
    border-radius: 4px;
}




</style>

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
                            <a class="label-click" href="../CMMS-Dashboard.html">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="${pageContext.servletContext.contextPath}/building">Building</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        <div  style="height:100px;position:relative"></div>
      		  <div class="container" style="width:100%;">
		         <div class="row">
		            <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3"
">
		    		   <jsp:include page="/WEB-INF/views/menu.jsp" />
		           </div>
		           <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">
                      	<c:url value="/building/delete" var="delete"/>

                    <!-- Contact Form -->
<c:url  value="/measure/delete" var="action"></c:url>
           <form:form class="form contact-form" id="contact_form"  action ="${action}" >
                        <div class="clearfix">
                            <div>
                                <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                    Measurement Unit Details
                                </h2>
                               
                                <div class="form-group">
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
                                                <th><input type="checkbox" id="checkall"/></th>
                                                <th>Measurement unit ID</th>
                                                <th>Measurement unit Desc</th>
                                                <th>Modify</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                                      <c:url value="/measure/edit" var="edit_link"></c:url>
                                                      
                                         <c:forEach  items="${unitMeasureList}" var="list">
                                            <tr>
                                            
                                                <td><input type="checkbox"  name="id"value="${list.id}"/></td>
                                                <td>${list.unitOfMeasure}</td>
                                                <td>${list.description}</td>
                                                
                                                
                                           <td>     <a  href="${pageContext.servletContext.contextPath}/measure/edit/${list.id}/${page}"><i class=" fa fa-edit"> </i></a></td>
                                              
                                             
                                            </tr>
                                              </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="form-group">
                                    <input type="submit" id="submit" value="Delete" />
                                </div>
                              </div>
                        </div>
                                 </form:form> 
                                                       <div>
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
										<table cellspacing="0" cellpadding="0" border="0"
											id="paging-table">
											<tbody>
												<tr>
													<td>
					<ul class="pagination pagination-sm" style=" position: relative; left: 400px; ">		
												<c:if test="${page>1}">

											<li>			<a class="page-far-left"
															href="<c:url value="/measure/pageno=1"/>"><<</a>   </li>
										<li>				<a class="page-left"
														    href="<c:url value="/measure/pageno=${page-1}"/>"><</a> </li>
													</c:if>
													
													
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																		<li class="active" ><a>${i}</a></li>
																</c:when>
																<c:otherwise>
														<li>			<a class=""
																		href="<c:url value="/measure/pageno=${i}"/>"
																		>${i}</a>  </li>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													 <c:if test="${page<total}">
												<Li>		<a class="page-right" 
														 	href="<c:url value="/measure/pageno=${page+1}"/>">&nbsp;> </a>  </Li>
										<li>				<a class="page-far-right"
														    href="<c:url value="/measure/pageno=${total}"/>" >>></a> </li>
													</c:if>
												</ul>	
													</td>
												</tr>
											</tbody>
	
										</table>
								</c:if>
                                  </div>
                                <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                    Add new Unit of Measure
                                </h2>
                                 <c:choose>
                                        <c:when test="${not empty edit}">
												<c:set value="/measure/update" var="action"/>
												<c:set var="caption" value="Update" />
												
											</c:when>
											<c:otherwise>
												 <c:set value="/measure" var="action"/>
												 <c:set var="caption" value="Add" />
												
											</c:otherwise>
											</c:choose>
                                <c:url var="url" value="${action}"/>
                                <form:form class="form contact-form" id="contact_form" method="post"  commandName="unitmeasure"  action="${url}" >
                                <div class="form-group">
                                	<input type="hidden" name="p" value="${page}">
                                  <form:hidden path="id"/> 
                                    <form:input type="text"  path="unitOfMeasure"   id="unitofmeasureId" class="input-md round form-control" placeholder="Unit of Measure ID"/>
                               <form:errors path="unitOfMeasure"></form:errors>
                                </div>
                                <div class="form-group">
                                   <form:textarea path="description" class="input-md round form-control col-lg-3" style="margin-bottom:10px"/>
                                <form:errors path="description" ></form:errors>
                                </div>
                                <div class="form-group">
                                    <input type="submit" id="submit" value="${caption}" />
                                </div>
                           </form:form>
         
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/checkall.js"></script>          
                    <!-- End Contact Form -->