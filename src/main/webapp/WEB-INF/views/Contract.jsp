<!DOCTYPE html>
<html>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<style> 
.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}


        h1 {
            font-size: 1.9em;
        }
        
        /* table {
            border-collapse: collapse;
            width: 100%;
        } */
        
        p,
        h1 {
            margin: 2em 0;
        }
        
        td,
        th {
            text-align: center;
          
            padding: .5em 5px;
            font-size: 1.2em;
            overflow: hidden; text-overflow: ellipsis;
            vertical-align: middle !important;
        }
        table th {
              background-color: #0754a4;
                color: #fff;
              padding: 6px;
              margin: 0;
		}
        
        tr:nth-child(even) {
		    background-color: #f9f9f9;
		}
        
        th {
            background-color: #f4f4f4;
            font-weight: normal;
        }
        
       caption {
            margin: 0;
            font-weight: bold;
            font-size: 1.3em;
            background: #eee;
            padding: 10px;
            border: 1px solid #ddd;
        } 
        
        .cap {
            text-align: left;
            background-color: azure;
            margin: 0;
            font-weight: bold;
            font-size: 1.3em;
            padding: 10px;
            border: 1px solid #ddd;
        } 
        /* queries */
        
</style>
        
       <!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Contract</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                                
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            Contract Details
                                        </h2>
                                         <c:if test="${not empty success}">
                                         <div class="btn-success">${success}</div>
										</c:if> 
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
                                         <br>
                                    <div>
                                        <form:form action="${delete}" method="post" class="form contact-form">
                                        	<c:url value="/contract/delete" var="delete"/>
                                        <div class="table-responsive">
                                        	
                                            <table class="table emp-sales  table-stripped table-hover"  id="tblResult">
                                                <thead>
                                                    <tr class="roww">
                                                        <th scope="col"  style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;"><input type="checkbox"  id="checkall"/></th>
                                                        <th scope="col"  data-toggle="tooltip" data-placement="bottom" title="Contract Id"   style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;">Contract Id</th>
                                                        <th scope="col"  data-toggle="tooltip" data-placement="bottom" title="Contract Desc" style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;">Contract Desc</th>
                                                        <th scope="col" data-toggle="tooltip" data-placement="bottom" title="Building Owner"  style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;">Building Owner</th>
                                                        <th scope="col"  style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;">Cont</th>
                                                        <th scope="col"  data-toggle="tooltip" data-placement="bottom" title="Building Desc" style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;">Building Desc</th>
                                                        <th scope="col"  data-toggle="tooltip" data-placement="bottom" title="Owner Pay Amt" style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;">Owner Pay Amt</th>
                                                        <th scope="col"  data-toggle="tooltip" data-placement="bottom" title="Start Date" style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;">Start Date</th>
                                                        <th scope="col"  data-toggle="tooltip" data-placement="bottom" title="End Date" style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;">End Date</th>
                                                        <th scope="col"  style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;">Modify</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <c:url value="/contract/edit" var="editlink"/>
                                                <c:forEach var="wrkspc" items="${workspaceList}">
                                                    <tr class="roww">
                                                    	<td  style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;"><input type="checkbox" name="id" value="${wrkspc.id}"></td>
	                                                    <td data-toggle="tooltip" data-placement="bottom" title="${wrkspc.workspaceId}"  style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;">${wrkspc.workspaceId}</td>
	                                                    <td data-toggle="tooltip" data-placement="bottom" title="${wrkspc.description}"  style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;">${wrkspc.description}</td>
	                                                    <td  style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;">${wrkspc.buildingDescription}</td>
	                                                    <td  style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;">${wrkspc.owner}</td>
	                                                    <td  data-toggle="tooltip" data-placement="bottom" title="${wrkspc.contractor}" style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;">${wrkspc.contractor}</td>
	                                                    <td  style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;">${wrkspc.bldngOwnerPayAmt}</td>
	                                                    <fmt:formatDate value="${wrkspc.startDate}" var="startDate" pattern="dd-MM-yyyy"/>
	                                                    <td  style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;">${startDate}</td>
  	                                                    <fmt:formatDate value="${wrkspc.endDate}" var="endDate" pattern="dd-MM-yyyy"/>
	                                                    <td  style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;">${endDate}</td>
	                                                    <td  style="white-space: nowrap; text-overflow:ellipsis; overflow: hidden; max-width:1px;"><a id="edit" href="${editlink}/${wrkspc.workspaceId }/${page}" style="cursor:pointer"><img style="width:20px" src="${pageContext.servletContext.contextPath}/resources/assets/images/edit.png" /></a></td>
													</tr>
                                                </c:forEach>    
                                                </tbody>
                                            </table>
                                            
                                        </div>
                                        <div class="form-group float-right">
                                            <input type="submit" id="Delete" class="btn" value="Delete" />
                                        </div>
                                        </form:form>
                                         <c:if test="${1<total}">
									<c:choose>
										<c:when test="${total>10}">
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
				 <ul class="pagination pagination-sm" style=" position: relative; left: -60px; " >								 
											 <c:if test="${page>1}">

													<li>	<a class="page-far-left"
															href="<c:url value="/contract/pageno=1"/>"><<</a>  </li>
									<li>					<a class="page-left"
														   href="<c:url value="/contract/pageno=${page-1}"/>"><</a> </li>
													</c:if>
													
													
														<c:forEach var="i" begin="${from}" end="${to}">
															
															<c:choose>
																<c:when test="${i==page}">
																	<li class="active" ><a>${i}</a></li>
																</c:when>
																<c:otherwise>
														<li>			<a class=""
																		href="<c:url value="/contract/pageno=${i}"/>"
																		>${i}</a>  </li>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													 <c:if test="${page<total}">
												<li>		<a class="page-right"
														 	href="<c:url value="/contract/pageno=${page+1}"/>">> </a> </li>
											<li>			<a class="page-far-right"
														    href="<c:url value="/contract/pageno=${total}"/>" >>></a>  </li>
													</c:if>
													</ul>
													</td>
												</tr>
										</tbody>

									</table>

								</c:if>
                                        
                                        <c:choose>
											<c:when test="${not empty edit}">
												<c:set value="/contract/update" var="action"/>
												<c:set var="caption" value="Update" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
		                                            Edit Contract
		                                        </h2>
											</c:when>
											<c:otherwise>
												 <c:set value="/contract/add" var="action"/>
												 <c:set var="caption" value="Add" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            		Add Contract
                                        		</h2>
											</c:otherwise>
										</c:choose>
                                        <c:url value="${action}" var="actionsubmit"/>
                                       <form:form action="${actionsubmit}" commandName="workspace" method="post" enctype="multipart/form-data" class="form contact-form" id="contact_form">
										 <input type="hidden" value="${page}" name="p"/>
										 <form:hidden path="id"/>
                                         <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Contract ID</span>
                                            <form:input type="text" path="workspaceId" id="contract_ID" class="input-md round form-control" placeholder="Contract ID" />
                                        	<form:errors path="workspaceId" class="err"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Contract Description</span>
                                           <form:textarea path="description" class="input-md round form-control" placeholder="Contract Description"></form:textarea>
                                           <form:errors path="description" class="err"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Building Description</span>
                                            <form:textarea path="buildingDescription" class="input-md round form-control" placeholder="Building Description"></form:textarea>
	                                        <form:errors path="buildingDescription" class="err"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Building Owner</span>
                                            <form:input type="text" path="owner" id="buildingowner" class="input-md round form-control" placeholder="Building Owner" />
 	                                        <form:errors path="owner" class="err"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Service Provider</span>
                                            <form:input type="text" path="contractor" id="serviceprovider" class="input-md round form-control" placeholder="Service Provider" />
 	                                        <form:errors path="contractor" class="err"/>
                                        </div>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Building Owner Pay Amount</span>
                                            <form:input type="text" path="bldngOwnerPayAmt" id="buildingownerpayamount" class="input-md round form-control" placeholder="Building Owner pay Amount" />
 	                                        <form:errors path="bldngOwnerPayAmt" class="err"/>
                                        </div>
                                       
	                                        <div class="form-group input-group">
	                                        	<span class="input-group-addon label-left" id="basic-addon2">From Date</span>
	                                            <form:input type="text" path="startDate" id="From_date" class="input-md round form-control" placeholder="From Date" autocomplete="off"/>
	                                            <%-- <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('From_date','ddMMyyyy')" style="cursor:pointer;width:30px" /> --%>
	 	                                        <form:errors path="startDate" class="err"/>
	                                        </div>
	                                        <div class="form-group input-group">
	                                        	<span class="input-group-addon label-left" id="basic-addon2">To Date</span>
	                                            <form:input type="text" path="endDate" id="To_date" class="input-md round form-control" placeholder="To Date" autocomplete="off"/>
	                                            <%-- <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('To_date','ddMMyyyy')" style="cursor:pointer;width:30px" /> --%>
	 	                                        <form:errors path="endDate" class="err"/>
	                                        </div>
                                        
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Image</span>
                                            <input type="file" name="pimage" id="pimage" class="input-md round form-control" placeholder="Project image (jpg/png)" />
                                        	<c:if test="${not empty workspace.projectImage}">
	                                        	<c:url value="/contract/getimage/${workspace.projectImage}" var="img"/>
	                                        	<img src="${img}" />
	                                        	<form:hidden path="projectImage"/>
                                        	</c:if>
                                        </div> 
                                        <div class="form-group float-right">
                                            <input type="submit" id="submit" class="btn" value="${caption}" />
                                        </div>
                                       </form:form> 
							</div>
					</div>
			</div>
		</div>
		</div>
		
		   <script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/checkall.js"></script>

 <!--   <script>$(window).resize(function() {
	    $('.roww').each(function() {
	        var row_width = $(this).width();
	        var cols = $(this).find('td');
	        var left = cols[0];
	        var lcell_width = $(left).width();
	        var lspan_width = $(left).find('span').width();
	        var right = cols[1];
	        var rcell_width = $(right).width();
	        var rspan_width = $(right).find('span').width();

	        if (lcell_width < lspan_width) {
	            $(left).width(row_width - rcell_width);
	        } else if (rcell_width > rspan_width) {
	            $(left).width(row_width / 2);
	        }
	    });
	});</script> -->
</body>
</html>
