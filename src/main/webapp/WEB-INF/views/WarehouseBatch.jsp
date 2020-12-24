<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<style>



input[type="text"] {
    display: inline-block;
    height: 27px;
    vertical-align: middle;
    font-size: 11px;
    font-weight: 400;
    text-transform: uppercase;
    letter-spacing: 1px;
    color: #000000;
    border: 1px dashed #0754a4;
    padding-left: 7px;
    padding-right: 7px;
    -webkit-border-radius: 0;
    -moz-border-radius: 0;
    border-radius: 0px;
    -webkit-box-shadow: none;
    -moz-box-shadow: none;
    box-shadow: none;
    -moz-box-sizing: border-box;
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
    -webkit-transition: all 0.2s cubic-bezier(0.000, 0.000, 0.580, 1.000);
    -moz-transition: all 0.2s cubic-bezier(0.000, 0.000, 0.580, 1.000);
    -o-transition: all 0.2s cubic-bezier(0.000, 0.000, 0.580, 1.000);
    -ms-transition: all 0.2s cubic-bezier(0.000, 0.000, 0.580, 1.000);
    transition: all 0.2s cubic-bezier(0.000, 0.000, 0.580, 1.000);
}

input[type="submit"] {
    border: none;
    background-color: #0754a4;
    color: #fff;
    padding: 7px 9px;
    border-radius: 0px;
    margin-bottom:50px;
}
</style>
<body class="appear-animate">
<!-- End Navigation panel -->
	<!-- Head Section -->
	 <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">Warehouse Batch</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="#">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="../forms_iCMMS/configurator-civil-Level-3.html">Warehouse Batch</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!---MENU-->
        <div style="height:100px;position:relative"></div>
        <div class="container" style="width:100%;">
            <div class="row">
                <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
                    <jsp:include page="/WEB-INF/views/menu.jsp" />
                </div>
                <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">

                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                        Add New Quantity Of Current Part in Warehouse
                    </h2>
                    
                    
                     <c:if test="${not empty success}">
                                         <div class="success">${success}</div>
										</c:if>
										<c:if test="${not empty fail}">
                                         <div class="error">${fail}</div>
                                         </c:if>
                   
                   <c:url var="action" value="/warehouse/batch"/> 
				   <form:form action="${action}" method="post" modelAttribute="batch" novalidate="novalidate" >
                    <div class="form-group">
                        <label class="col-md-3">Warehouse Name:</label>
                        <label> ${partsInWarehouse.warehouse.name}</label>
                    </div>
                    <div>
                        <label class="col-md-3">Part Name:</label>
                        <label>${partsInWarehouse.part.partType.partName}</label>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3">Part Description:</label>
                        <label>${partsInWarehouse.part.description}</label>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3">Manufacturer::</label>
                        <label>${partsInWarehouse.part.manufacturer.name}</label>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3">Vendor:</label>
                        <label>${partsInWarehouse.part.vendor.vendorName}</label>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3">Available Quantity::</label>
                        <label>${partsInWarehouse.totalPartQuantity}</label>
                    </div>

                    <div class="form-group">
                        <label class="col-md-3">Batch No:</label>
                        <form:input  type="text" path="batchNo" style="width:25%" data-bvalidator="required" class="input-md round form-control"/>
                    	<form:errors path="batchNo" class="err"/>
                    </div>
                    <div class="clearfix"> </div>

                    <div class="form-group">
                        <label class="col-md-3">New Quantity:</label>
                        <form:input  type="text" path="quantity" data-bvalidator="required" style="width:25%" class="input-md round form-control"/>
                   		<form:errors path="quantity" class="err"/>
                    </div>
                    <div class="clearfix"> </div>
                    <div class="form-group">
                        <label class="col-md-3">Unit Cost:</label>
                        <form:input type="text" path="unitCost" data-bvalidator="required" style="width:25%" class="input-md round form-control"/>
                   		<form:errors path="unitCost" class="err"/>
                    </div>
                    <div class="clearfix"> </div>
                    <div class="form-group">
                        <label class="col-md-3">Location:</label>
                        <form:input type="text" path="location" data-bvalidator="required"  style="width:25%" class="input-md round form-control"/>
                        <form:errors path="location" class="err"/>              
                    </div>
                    <div class="clearfix"> </div>
                    <form:hidden path="partsInWarehouse" value="${partsInWarehouse.id}"/>
                    <br>
                    <div class="form-group">
                        <label class="col-md-3"></label>
                        <input type="submit" id="submit" value="Save" />

                    </div>
                    </form:form>
				</div>
			</div>

            <!--End Form-->
            
            <script type="text/javascript">
            $(document).ready(function () {
    			// alert("YYY")
    			// console.log("YYY");
    		        console.log($('form').bValidator());
    		    });
            </script>
</body>

</html>