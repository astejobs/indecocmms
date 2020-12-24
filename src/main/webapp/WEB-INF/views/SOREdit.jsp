<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>
<script src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/SOR.js"></script>

<body class="appear-animate">

       <!-- Head Section -->
        <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">SOR EDIT</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="">Edit SOR</a>
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
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                        Edit SOR 
                    </h2>
                           <c:url value="/SOR/update" var="submit"/>
                            <form:form  action="${submit}" method="post"  class="form contact-form" modelAttribute="SOR" id="contact_form">
	                                 <div class="form-group">
											 <c:if test="${not empty success}">
                                         <div class="success">${success}</div>
										</c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
      						 			
      								</div>		
                                    <div class="form-group">
                                        <form:input path="id" type="hidden"/>
                        				<input type="hidden" value="${pageContext.request.contextPath}" id="contextPath"/>
	                                 
	                            	    <form:select path="equipmentType" id="EqType" class="input-md round form-control" style="width:49%">
	        							<form:options items="${AssetTypeList}"  itemValue="assetTypeName" itemLabel="assetTypeName"></form:options>
	                                    </form:select><form:errors path="equipmentType"></form:errors>
                                     </div>
                                     <div>
                                       	<form:select path="equipmentName"  id="EqName" class="input-md round form-control" style="width:49%">
  										<form:option value=""> Select Equipment</form:option>
								    	<form:options items="${eqpNameList}" itemValue="name" itemLabel="name"></form:options>
								    	</form:select>
                                       <form:errors path="equipmentName"></form:errors>
                                    </div>
                                        <div class="form-group">
                                            <form:input type="text" path="itemCode" name="item_code" id="item_code" class="input-md round form-control" placeholder="Item Code" /><form:errors  class="err" path="itemCode"/>
                                        </div>
                                        <div class="form-group">
                                            <form:textarea class="input-md round form-control" path="description" placeholder="Description"></form:textarea>
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="unit" name="unit" id="unit" class="input-md round form-control" placeholder="Unit" /><form:errors  class="err" path="unit"/>
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="rate" name="rate" id="rate" class="input-md round form-control" placeholder="Rate" /><form:errors  class="err" path="rate"/>
                                        </div>
                                    <div class="form-group">
                                        <input type="submit" id="Create" value="Update" />
                                    </div>
                                </form:form>
                                </div>
                                
       			</div>
       </div>
       
</body>

</html>

