<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
input[type="submit"] {
    border: none;
    background-color: #0754a4;
    color: #fff;
    padding: 7px 9px;
    border-radius: 0px;
}

input[type="button"] {
    border: none;
    background-color: #0754a4;
    color: #fff;
    padding: 7px 9px;
    border-radius: 0px;
}
</style>
<body class="appear-animate">

   
        <!-- Head Section -->
       <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">LABOUR RATE CREATE</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="">Create Labour Rate</a>
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
                        Create New Rate
                    </h2>
                    
                     
                                        <c:if test="${not empty success}">
                                         <div class="success">${success}</div>
										</c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                        </c:if>


                         <c:url value="/labourRate" var="create"/>
                             
                            <form:form class="form contact-form" id="contact_form" commandName="labourrate" method="POST" action="${create}">
                                <div class="clearfix">
                                    <div>
                                        <div class="form-group">
                                            <form:input type="text" path="itemCode" name="item_code" id="item_code" class="input-md round form-control" placeholder="Item Code" required="required"/>
                                             <form:errors path="itemCode" class="errors"/>
                                        </div>
                                        <div class="form-group">
                                            <form:textarea  path="description" class="input-md round form-control" placeholder="Description" required="required"/>
                                             <form:errors path="description" class="errors"/>
                                        </div>
                                        <div class="form-group">
                                            <form:input  path="unit" type="text" name="unit" id="unit" class="input-md round form-control" placeholder="Unit" required="required" />
                                             <form:errors path="unit" class="errors"/>
                                        </div>
                                        <div class="form-group">
                                            <form:input  path="rate" type="text" name="rate" id="rate" class="input-md round form-control" placeholder="Rate" required="required" />
                                             <form:errors path="rate" class="errors"/>
                                        </div>

                                    </div>
                                    <div class="form-group">
                                        <input type="submit" id="Create" value="Create" />
                                    </div>
                                </div>
                                <div>
                                        <input type="button" onclick="location.href='${pageContext.servletContext.contextPath}/labourRate/search';" value="Search"/>
                                                </div>
                            </form:form>
                            
						</div>
					</div>
				</div>
			</div>
		</div>
</body>