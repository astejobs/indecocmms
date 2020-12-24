<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>
<style>
    .stat {
   padding:4px;background-color:green;color:#fff;text-decoration:none;
    } 
   a.red{
     padding:4px;background-color:red;color:#fff;text-decoration:none; }
   }
   a.brown{
     padding:4px;background-color:red;color:#fff;text-decoration:none; }
   }
   a.green{
      padding:4px;background-color:green;color:#fff;text-decoration:none; 
   }
   a:hover{
    color:#fff;text-decoration:none;
   }
   
    a:focus{
    color:#fff;text-decoration:none;
   }
   
   .table{
        
        table-layout:auto;
   }
   
   table tr.footable-filtering>th {
	background-color: #FFF;
}
.btn-primary {
	color: #fff;
	background-color: #0754a4;
	border-color: #0754a4;
}

 button.dropdown-toggle {
              padding:11px;
              display:none;
}
   
  
   
</style>
        <!-- Head Section -->
      
	<section class="small-section bg-dark-alfa-30 parallax-2"
		style="margin-bottom:-70px;position:relative"
		data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
	<div class="relative container align-left">
		<div class="row">
			<div class="col-md-8">
				<h1 class="hs-line-11 font-alt mb-10 mb-xs-0"
					style="margin-top: 5px">Edit Warehouse</h1>
			</div>

			<div class="col-md-4 mt-30">
				<div class="mod-breadcrumbs font-alt align-right">
					<a class="label-click"
						href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a>&nbsp;/&nbsp;<a
						class="label-click" href=""> Edit Warehouse</a>
				</div>
			</div>
		</div>
	</div>
	</section>
        <!-- End Head Section -->
        <!---MENU-->
        <div style="height:100px;position:relative"></div>
        <div class="container" style="width:100%;">
            <div class="row">
                <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
                      <jsp:include page="/WEB-INF/views/menu.jsp" />
                </div>
            <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">

                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                        Edit Warehouse
                    </h2>
                     <c:if test="${not empty success}">
                                         <div class="success">${success}</div>
										</c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
                   

                    <!-- Contact Form -->
		                    <c:url var="action" value="/warehouse/update/${warehouse.id}"/>
                            <form:form action="${action}" commandName="warehouse" method="post" class="form contact-form" id="contact_form" data-bvalidator-validate="true" novalidate="novalidate">
                                <div class="clearfix">
                                    <div>
                                    
                                        <div class="form-group">
                                            <form:input type="text" path="name" data-bvalidator="required" id="warehouse_name" class="input-md round form-control" placeholder="Warehouse Name" />
                                       		<form:errors path="name" class="err"/>
                                        </div>
                                        
                                        <div class="form-group">
                                            <form:textarea path="description" data-bvalidator="required" class="input-md round form-control col-lg-3" style="margin-bottom:10px" placeholder="Warehouse Description"/>
                                        </div>
                                        
                                        <div class="form-group">
                                            <form:textarea path="location" data-bvalidator="required" class="input-md round form-control col-lg-3" style="margin-bottom:10px" placeholder="Location Description" />
                                        </div>
                                        
                                        <div class="form-group">
                                            <input type="submit" id="btnSave" value="Update" />
                                        </div>
                                        
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Currently Added Users
                                        </h2>
                                        <div class="form-group">
                                            <table class="table table-stripped" id="tblResult" data-paging="true">
                                                <thead>
                                                    <tr>
                                                        <th>Name</th>
                                                        <th>User Id</th>
                                                        <th>Department</th>
                                                        <th>Designation</th>
                                                        <th>Status</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                	<c:forEach items="${warehouse.storeKeepers}" var="storeKeeper" varStatus="loop">
                                                	<form:hidden path="storeKeepers[${loop.index}].userDetail"/>
                                                	<c:choose>
                                                		<c:when test="${storeKeeper.status eq true}">
                                                				<c:set var="status" value="Active"/>
                                                				<c:set value="green" var="class"/>
                                                		</c:when>
                                                		<c:otherwise>
                                                				<c:set var="status" value="InActive"/>
                                                				<c:set value="red" var="class"/>
                                                				
                                                		</c:otherwise>
                                                	</c:choose>
                                                    <tr> 
                                                        <td>${storeKeeper.userDetail.firstName} ${storeKeeper.userDetail.lastName}</td>
                                                        <td>${storeKeeper.userDetail.username}</td>
                                                        <td>${storeKeeper.userDetail.department}</td>
                                                        <td>${storeKeeper.userDetail.designation}</td>
                                                        <td>
	                                                        <c:choose>
	                                                        	<c:when test="${storeKeeper.userDetail.usergroup.id eq 1}">
	                                                           		<a class="stat"><i class="fa fa-unlock-alt"></i>${status}</a>
	                                                            </c:when>
	                                                            <c:when test="${status eq 'Active'}">
	                                                           	<a href="" class="stat" id="status"   name="${storeKeeper.id}" ><i class="fa fa-unlock-alt"></i>${status}</a>
	                                                            </c:when>
	                                                            <c:otherwise>
										                     	<a href="" id="status" class="red"   name="${storeKeeper.id}" ><i class="fa fa-unlock-alt"></i>${status}</a>
	                                                            </c:otherwise>
	                                                        </c:choose>
                                                        </td>
                                                    </tr>
                                            		</c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Add users to Warehouse
                                        </h2>
                                        <c:choose>
                                        <c:when test="${ not empty userList}">
                                        <div class="form-group">
                                            <table class="table table-stripped" id="tblResult" data-paging="true" data-filtering="true">
                                                <thead>
                                                    <tr>
                                                        <th><input type="checkbox" /></th>
                                                        <th>Name</th>
                                                        <th>User Id</th>
                                                        <th>Department</th>
                                                        <th>Designation</th>
                                                        <th>Status</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                	<c:forEach items="${userList}" var="user" varStatus="loop"> 
                                                    <tr> 
                                                    	<td><input type="checkbox" name="users" value="${user.id}"/></td>
                                                        <td>${user.firstName} ${user.lastName}</td>
                                                        <td>${user.username}</td>
                                                        <td>${user.department}</td>
                                                        <td>${user.designation}</td>
                                                        <td>${user.userTypeFlag}</td>
                                                    </tr>
                                            		</c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        </c:when>
                                        <c:otherwise>
                                       		<label class="form-group">No User to be added</label>
                                        </c:otherwise>
                                        </c:choose>
                                </div>
                            </form:form>
                        
                    <!-- End Contact Form -->
                </div>
          
        </div>

        <!--End Form-->
	   <script>
            jQuery(function($) {
                $('.table').footable();
            });
            
            $(document).on("click","#status", function(e){
            	e.preventDefault();
            	var storekeeperId=$(e.target).attr("name");
            	 console.log( $(e.target).attr("name"));
            	 var url="${pageContext.servletContext.contextPath}/warehouse/status/"+storekeeperId;
            	
            	
            	 $.ajax({
						type : 'get',
						url : url,
						 beforeSend:function(){
							$(e.target).html("<i class='fa fa-spinner'></i>&nbsp;Wait..");
							 $(e.target).removeClass("stat");	
							 $(e.target).addClass("red");
						}, 
						success : function(success) {

							if (success == "true") {
								console.log("inside true");
						      
			                    $(e.target).empty();
							   $(e.target).html("<i class='fa fa-unlock-alt'> </i>Active");
							   $(e.target).removeClass("red");
							   
		            	       $(e.target).addClass("stat");
            	            	
            	 
							} 
							else if (success == "false") {
								console.log("inside false")		      
							           $(e.target).empty();  
							           $(e.target).html("<i class='fa fa-lock'></i> Inactive ")
							           $(e.target).removeClass("stat");								
				            	       $(e.target).addClass("red");
		            	            	
								
							}
							else if (success == "uauthorized") {
								console.log(success)
								console.log("inside unauthorised")		      
							
									$(e.target).html("<i class='fa fa-lock'> </i>Unathorised")
							    $(e.target).removeClass("stat");
								console.log($(e.target).addClass("red"));
								//alert(success);
							}
							// alert("status changed
							// to" + status);
						}
					/* 	 error : function(x, e) {
							alert("FAIL")
							if (x.status == 0) {
								alert('You are offline!!\n Please Check Your Network.');
							} else if (x.status == 404) {
								alert('Requested URL not found.');
							} else if (x.status == 500) {
								alert('Internel Server Error.');
							} else if (e == 'parsererror') {
								alert('Error.\nParsing JSON Request failed.');
							} else if (e == 'timeout') {
								alert('Request Time out.');
							} else {
								alert('Unknow Error.\n'
										+ x.responseText);
							}
							

						} * */

					});
            });
            
           
            $(document).ready(function () {
    			// alert("YYY")
    			// console.log("YYY");
    		        console.log($('form').bValidator());
    		    });
           

        </script>
</body>
</html>
