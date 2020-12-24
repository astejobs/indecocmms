  <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>  
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <style> 
.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}

table th{
   padding: 4px 1px;
}


table {
           table-layout:auto;
}

tbody:last-child {  
            border-top: 0px solid #FFF;
            background-color:#FFF;
}

.bac {
          background-color: #FFF;
          border-top: 0px solid #FFF;
}


.pagination {
    display: inline-block;
    padding-left: 0;
    margin: -24px 0;
    border-radius: 4px;
}

</style>
  <!-- Head Section -->
            <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Fault Category</li>
			  </ol>
			</nav>
			
        <!-- End Head Section -->
 <div class="container">
<div class="row">
        <div class="col-md-12">
        
        <div class="col-md-10 jumbotron offset-box">

                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                            Fault Category Details
                                        </h2>
                                               <c:url var="delete" value="/faultCategory/delete"></c:url>
                                           <form:form class="form contact-form" id="contact_form" action="${delete}">
                                       <c:if test="${not empty success}">
                                         <div class="success">${success}</div>
										</c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
                                        <div class="table-responsive">
                                            <table class="table table-striped table-hover ssh-table" id="tblResult">
                                                <thead>
                                                    <tr>
                                                        <th><input  id="checkall" type="checkbox" /></th>
                                                        <th>Fault Category</th>
                                                        <th>Fault Desc</th>
                                                        <th>Priority</th>
                                                        <th>Modify</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                
                                                   <c:url value="/faultCategory/edit" var="edit_link"></c:url>
                                              

                                                    <c:forEach  items="${faultCategoryList}" var="list">
		                                                    <tr>
		                                                    <td><input type="checkbox" value="${list.id}" name="id"/></td>
		                                                    <td>
		                                                    <c:out value="${list.name}"></c:out>
		                                                    </td>
		                                                    <td> <c:out value="${list.description}"></c:out>
		                                                    </td>
		                                                    <td>
		                                                    <c:out value="${list.priorty.name}"></c:out>
		                                                    </td>
		                                                   
		                                                     <td>
		                                                     <a id="edit" href="${edit_link}/${list.id }/${page}" style="cursor:pointer"><i class="fa fa-edit"> </i></a></td>
		                                                     </tr>
                                                    </c:forEach>
  <tr style="background-color: #FFF;"> <td colspan="100%"><div class="form-group"> <input type="submit" id="submit" class="btn" value="Delete" /> </div> </td></tr>
                                                </tbody>
                                                                            <tfoot>
                               <tr>
                               	
                               		<td colspan="100%"  style="border-top: 1px solid #fff;">
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
							<ul class="pagination pagination-sm pagination-sm-left" style="position: relative;" >				 
											 <c:if test="${page>1}">

					<li>									<a class="page-far-left"
															href="<c:url value="/faultCategory/pageno=1"/>"><<</a> </li>
					<li>									<a class="page-left"
														    href="<c:url value="/faultCategory/pageno=${page-1}"/>"><</a> </li>
													</c:if>
													
													
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																	<li class="active" ><a>${i}</a></li>
																</c:when>
																<c:otherwise>
															<li>		<a class=""
																		href="<c:url value="/faultCategory/pageno=${i}"/>"
																		>${i}</a>  </li>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													 <c:if test="${page<total}">
													<li>	<a class="page-right" 
														 	href="<c:url value="/faultCategory/pageno=${page+1}"/>">> </a>  </li>
									<li>					<a class="page-far-right"
														    href="<c:url value="/faultCategory/pageno=${total}"/>" > >></a> </li>
													</c:if>
													</ul>
													</td>
												</tr>
										</tbody>

									</table>

								</c:if>
                       
                               		</td>
                               		<td></td>
                               </tr>
                               </tfoot>
                                            </table>
                                        </div>
                                      
                                       
                                          </form:form>
                                          <c:choose>
                                        <c:when test="${not empty edit}">
												<c:set value="${pageContext.servletContext.contextPath}/faultCategory/update" var="action"/>
												<c:set var="caption" value="Update" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
		                                            Edit Category
		                                        </h2>
											</c:when>
											<c:otherwise>
												 <c:set value="${pageContext.servletContext.contextPath}/faultCategory/add" var="action"/>
												 <c:set var="caption" value="Add" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
                                            		Add Category
                                        		</h2>
											</c:otherwise>
											</c:choose>
<!--                                         <h2 class="section-title font-alt align-left mb-70 mb-sm-40" > -->
<!--                                             Add new Fault Category -->
<!--                                         </h2> -->
<%--                                         <c:url value="${action}" var= faultAction></c:url> --%>
                                           <form:form  class="form contact-form" id="contact_form" method="post" modelAttribute="faultCategory" action ="${action}">
                                    
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Fault Category</span>
                                            <form:hidden path="id" value="${faultCategory.id}"/> 
                                               <input type="hidden" name="p" value="${page}"/> 
                                            <form:input path="name"  name="faultcategoryid" id="faultcategoryid" class="input-md round form-control" placeholder="Fault Category"/>
                                         <form:errors class="err" path="name"></form:errors>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Fault Description</span>
                                           <form:textarea path="description"  class="input-md round form-control" placeholder="Fault Description" ></form:textarea>
                                          <form:errors class="err" path="description" ></form:errors>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Priority ID</span>
                                           <form:select path="priorty"  class="input-md round form-control">
                                                <option>Priority ID</option>
                                                 <form:options items="${priortyList}" itemValue="id" itemLabel="name"/>
                                                 <form:errors class="err" path="priorty" ></form:errors>
                                                
                                          </form:select>
                                        </div>
                                        <div class="form-group float-right">
                                            <input type="submit" id="submit" class="btn" value="${caption}"/>
                                        </div>
                                         </form:form>
                                    </div>
                                </div>
                        
                        </div>
                        </div>
                  
                     
                    <!-- End Contact Form -->
               

        <!--End Form-->
        
          <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/checkall.js"></script>