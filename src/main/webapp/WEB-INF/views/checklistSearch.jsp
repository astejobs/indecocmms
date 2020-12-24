<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<style>  
    table th,td {
                      white-space: nowrap; 
                      text-overflow:ellipsis; 
                     /*  overflow: hidden; */
                      max-width:1px;
    }
   
   
</style>

       <!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Checklist Search</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">

                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                        Search Criteria
                    </h2>

                           <c:url value="/checklist/search"  var="search_url"/>
                            <form:form  method="post" action="${search_url}"  commandName="checklistsearch"  class="form contact-form" id="contact_form">
                                         <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Equipment</span>
                                            <form:select id="equipment"  path="equipment"  class="input-md round form-control">
                                                <option>Select Equipment</option>
                                            </form:select>
                                        </div>
                                        <div class="form-group">
    	                                    <input type="button" onclick="SelectName()" value="Search Equipment" class="submit_btn btn btn-mod btn-medium btn-round" />
                                                 <script type="text/javascript">
                                                var popup;
                                                function SelectName() {
                                                popup = window.open("equipment", "Popup", "width=700,height=550,left=300");
                                                popup.focus();
                                                return false
                                                }
                                             </script>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Checklist Name</span>
                                          <form:input type="text" path="checklistname"  placeholder="CheckList Name" class="input-md round form-control"/>
                                        </div>
                                        <div class="form-group">
                                               <input type="submit" value="Search"  class="submit_btn btn btn-mod btn-medium btn-round" />
                                        </div>
                                <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                    Search Result
                                </h2>
                                <div class="form-group table-responsive">
                                    <table class="table table-stripped table-hover">
                                        <thead>
                                            <tr>
                                                <th>Checklist Name</th>
                                                 <th>Description</th>
                                                  <th>Equipments</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach items="${listofChecklist}" var="checklists">
                                            <tr>
                                                <c:url value="/checklist/edit/${checklists.id}" var="edit_url"/>
                                                <td data-toggle="tooltip" title="${checklists.checklistName}"><a href="${edit_url}" >${checklists.checklistName}</a></td>
                                                <td data-toggle="tooltip" title="${checklists.prerequisite}">${checklists.prerequisite}</td>
                                                 <td>
                                                <c:forEach items="${checklists.equipment}" var="equipments">
                                                <p  data-toggle="tooltip" title=" ${equipments.name} " > ${equipments.name}  </p>
                                                </c:forEach>
                                                 </td>
                                                
                                            </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>

                            </form:form>
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
											 <td><c:if test="${page>1}">

														<a class="page-far-left"
															href="<c:url value="/checklist/pageno=1"/>"><img style="width:10px;height:12px" src="${pageContext.servletContext.contextPath}/resources/assets/images/pre.png"></a>

														<a class="page-left"
														   style="color:#fff;cursor:pointer;text-decoration:none;" href="<c:url value="/checklist/pageno=${page-1}"/>">&nbsp; << </a>

													</c:if>
													
													
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																	<strong  style="color:#11adc1 !important">${i}</strong>
																</c:when>
																<c:otherwise>
																	<a class=""
																		href="<c:url value="/checklist/pageno=${i}"/>"
																		style="color:#c8cccd;text-decoration:none;font-size:15px">${i}</a>
																</c:otherwise>
															</c:choose>
														</c:forEach>

													 <c:if test="${page<total}">
														<a class="page-right"
															href="<c:url value="/checklist/pageno=${page+1}"/>"></a>

														 <c:if test="${page<total}">
															<a class="page-right" style="color:#fff;cursor:pointer;text-decoration:none;"
															 	href="<c:url value="/checklist/pageno=${page+1}"/>">&nbsp;>> </a>
	
															<a class="page-far-right"
																 href="<c:url value="/checklist/pageno=${total}"/>" ><img style="width:10px;height:12px;top:-1px;position:relative" src="${pageContext.servletContext.contextPath}/resources/assets/images/next.png"></a>
														</c:if>
													</c:if>
													</td>

												</tr>
										</tbody>
										</table>
										</c:if>
   </div>
   </div>
   </div>
   </div>
   
