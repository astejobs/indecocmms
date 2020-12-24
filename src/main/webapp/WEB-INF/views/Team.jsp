<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


  


<body>

  <style>
        table {
            border-collapse: collapse;
            width: 100%;
           table-layout:auto;
           
        }
        
        .le{
            margin-left:300px;
            margin-top: 100px;
        }
        
        .ba{
            background-color: #0754a4;
            color:#FFF;
            padding: 10px;
            margin-top:10px;
        }
        p,
        h1 {
            margin: 2em 0;
        }
        
        td,
        th {
            text-align: center;
            border: 1px solid #ddd;
            padding: .5em 5px;
            font-size: 1.2em;
        }
        
        th {
            background-color: #0754a4;
            font-weight: normal;
            color:#FFF;
        }
        
        caption {
            margin: 0;
            font-weight: bold;
            font-size: 1.3em;
           /* background: #0754a4;*/
            color:#0754a4;
            padding: 10px;
           /* border: 1px solid #ddd;*/
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
        
       tfoot td{
            border: 1px solid #FFF;
       }
       .pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover {
    z-index: 3;
    color: #fff;
    cursor: default;
    background-color: #0754a4;
    border-color: #0754a4;
}
tr:nth-child(even) {
    background-color: #f9f9f9;
}

    </style>
    
    <!-- Head Section -->
       <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Team</li>
			  </ol>
			</nav>

    <!-- End Head Section -->
    
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                                  <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                      Team Details
                                  </h2>
                                  
                                  <div class="form-group">
	                     					<c:if test="${not empty success}">
	                                         <div class="success">${success}</div>
											</c:if>
											<c:if test="${not empty fail}">
	                                         <div class="error">${fail}</div>
	                                         </c:if>
                    					</div>
                                  
    <table class="emp-sales">
       
        <thead>
            <tr>


                <th scope="col">Name </th>
                <th scope="col">Member</th>
                <th scope="col">Action</th>

            </tr>

        </thead>
        <tbody>
          <c:url value="/team/edit" var="edit_link"></c:url>
          <c:url value="/team/delete" var="delete_link"></c:url>
            <c:forEach items="${list}" var="list">
										             <tr>
                                            			
                                            			                                                     
                                                	     <td>${list.name}</td>
                                                    	 <td>${list.technician}</td>
                                                         <td>
                <a id="edit" href="${edit_link}/${list.id}/${page}" style="cursor:pointer"><i class="fa fa-edit"> </i></a>
                                                         
<a title="Click to Delete"onclick="return confirm('Are you sure You want to DELETE this?')" href="${delete_link}/${list.id}">
    <i class="fa fa-trash"> </i></a>
                                                         
                                                         </td>
                                                    </tr>
                                                    </c:forEach>











        </tbody>
        
        
        
        
        
        
       <tfoot>  
          <tr>
			                               		
			                               		<td colspan="100%" >
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
														 <td style="border-style:hidden;">
										<ul class="pagination pagination-sm" style=" position: relative; left:-34px; ">					 
														 <c:if test="${page>1}">
			            										<li>				<a class="page-far-left"
																		href="<c:url value="/team/pageno=1"/>"><<</a> </li>
											<li>						<a class="page-left"
																	    href="<c:url value="/team/pageno=${page-1}"/>"><</a> </li>
																</c:if>
																
																
																	<c:forEach var="i" begin="${from}" end="${to}">
																		
																		<c:choose>
																			<c:when test="${i==page}">
																		<li class="active" ><a>${i}</a></li>
																			</c:when>
																			<c:otherwise>
														<li>						<a class=""
																					href="<c:url value="/team/pageno=${i}"/>"
																					>${i}</a>  </li>
																			</c:otherwise>
																		</c:choose>
																	</c:forEach>
																 <c:if test="${page<total}">
															<li>		<a class="page-right" 
																	 	href="<c:url value="/team/pageno=${page+1}"/>">> </a>  </li>
													<li>				<a class="page-far-right"
																	    href="<c:url value="/team/pageno=${total}"/>" >>></a> </li>
																</c:if>
														</ul>		
																</td>
															</tr>
													</tbody>
			
												</table>
			
											</c:if>
			                       
			                               		</td>
			                               		
			                               </tr>
			                               </tfoot>
			                                        
                                         </table>
        
        
         <c:choose>
 											<c:when test="${not empty edit}">
												<c:set value="/team/update" var="action"/>
												<c:set var="caption" value="Update" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
		                                            Edit Team
		                                        </h2>
											</c:when>
											<c:otherwise>
												 <c:set value="/team/add" var="action"/>
												 <c:set var="caption" value="Add" />
												 <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                                            		Add Team
                                        		</h2>
											</c:otherwise>
										</c:choose>
        
        
        
         <c:url value="${action}" var="submit"/>
        <form:form class="form-horizontal" method="post" action="${submit}" commandName="team">
           
           <form:hidden path="id"/>
             <input type="hidden" name="p" value="${page}"/> 
         
            <div class="form-group input-group">
                	<span class="input-group-addon label-left" id="basic-addon2">Name</span>
                    <form:input type="name" class="form-control" id="name" placeholder="Enter name" path="name" required="required"/>
                      <form:errors class="err" path="name"/>
            </div>
            <div class="form-group input-group">
                <span class="input-group-addon label-left" id="basic-addon2">Select List</span>
                <form:select class="form-control " id="sel1" path="technician" multiple="multiple" required="required">               
                   <form:options items="${tecnicianlist}" itemValue="id" itemLabel="technicianName" />
                     <form:errors class="err" path="technician"/>                   
                </form:select>
            </div>


            <div class="form-group" style="margin-bottom: 50px">
                <div class="col-sm-offset-2 col-sm-10">
                      <input type="submit" id="submit" class="btn" value="${caption}" />
                </div>                 
            </div>
        </form:form>
   
   


</div>
</div>
</div>
</div>







</body>




