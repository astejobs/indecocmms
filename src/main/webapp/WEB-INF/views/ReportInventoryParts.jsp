<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link href="https://fonts.googleapis.com/css?family=Josefin+Sans" rel="stylesheet">
<style>
 .gr tr:nth-child(odd) {
    background-color: #f9f9f9;
}
.table {
          table-layout:auto;
          font-size: 14px; 
     }
</style>
<body class="appear-animate">
        <!-- Head Section -->
        <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">DASHBOARD</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="../CMMS-Dashboard.html">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="../forms_iCMMS/configurator-civil-Level-3.html">Civil Level 3</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>

       <div style="height:100px;position:relative"></div>
  			<div class="container" style="width:100%">
           <div class="row">
              <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
           <jsp:include page="/WEB-INF/views/menu.jsp" />
             </div>
             <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">
				
                     <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                        Stock Level Summary
                    </h2>

                    <!-- Contact Form -->
                    <div class="row">
                        <div class="col-md-8">
                        
                        	<c:url value="/inventory/reports/parts" var="url"/>
                        
                            <form:form class="form contact-form" commandName="InventoryPartSearch" id="contact_form" method="post" action="${url}">
                                <div class="clearfix">
                                    <div>
                                        <div class="form-group">
                                          
		                                            
		                                <form:select path="warehouse"  class="input-md round form-control">
										<option value="">select warehouse</option>
										<c:forEach items="${warehouseList}" var="w">
										<form:option value="${w.id}">${w.name}</form:option>
										
										</c:forEach>
										
										</form:select>
                                            
                                        </div>
                                      
                                        <div class="form-group">
                                            <form:input type="text" path="partName" id="part-name" class="input-md round form-control" placeholder="Part Name"/>
                                        </div>
                                        
                                         <div class="form-group">
                                            <form:input type="text" path="batchNumber" id="batch_number" style="width:49%" class="input-md round form-control" placeholder="Batch Number"/>&nbsp;&nbsp;&nbsp;
                                            <form:input type="text" path="quantity" id="quantity" style="width:49%" class="input-md round form-control" placeholder="Quantity"/>
                                        </div>
                                        <div class="form-group">
                                       
                                       <form:select path="manufacturer" class="input-md round form-control" >
										<option value="">select manufacturer</option>
										<c:forEach items="${manufacturer}" var="m">
										<form:option value="${m.id}">${m.name}</form:option>
										
										</c:forEach>
										
										</form:select>
                                            
                                        </div>
                                        <div class="form-group">
                                       
                                       <form:select path="vendor"  class="input-md round form-control" >
										<option value="">select vendor</option>
										<c:forEach items="${vendor}" var="v">
										<form:option value="${m.id}">${m.vendorName}</form:option>
										
										</c:forEach>
										
										</form:select>
                                        </div>
                                        <div class="clearfix"></div><br />
                                        <div class="form-group">
                                            <input type="submit" name="search" id="submit" value="Submit" />
                                            <input type="submit" name="excel" id="btnexport" value="Export to Excel" />
                                        </div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Search Result
                                        </h2>
                                        <div class="form-group">
                                            <table class="table table-striped" id="tblResult">
                                                <thead>
                                                    <tr>
                                                        <th>Warehouse</th>
                                                        <th>Part Name</th>
                                                        <th>Part Description</th>
                                                        <th>Manufacturer</th>
                                                        <th>Vendor</th>
                                                        <th>Reorder Level</th>
                                                        <th style="text-align:centre !important">Details</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                	<c:forEach items="${partsInWarehouseList}" var="partsInWarehouse">
                                                    <tr><td>${partsInWarehouse.warehouse.name}</td>
                                                    	<td>${partsInWarehouse.part.partType.partName}</td>
                                                    	<td>${partsInWarehouse.part.description}</td>
                                                    	<td>${partsInWarehouse.part.manufacturer.name}</td>
                                                    	<td>${partsInWarehouse.part.vendor.vendorName}</td>
                                                    	<td>${partsInWarehouse.part.reorderLevel}</td>
                                                    	<td>
                                                    	<div class="form-group">
                                                    	<table class="table gr" id="tblResult" border>
                                              			<thead>
                                                  		<tr>
                                                  		<th>Unit Cost</th>
                                                        <th>Quantity on Hand</th>
                                                        <th> Batch No.</th>
                                                        <th>Total Cost (SGD)</th>
                                                  		</tr>
                                                  		</thead>
                                                  		<tbody>
                                                    	<c:forEach items="${partsInWarehouse.partBatchs}" var="partBatchs">
                                                    	<tr>
                                                    	<td>${partBatchs.unitCost}</td>
                                                    	<td>${partBatchs.quantity}</td>
                                                    	<td>${partBatchs.batchNo}</td>
                                                    	<td></tr>
                                                    	</c:forEach>
                                                    	</tbody>
                                                    	</table>
                                                    	</div>
                                                    	</td>
                                                    </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                    <!-- End Contact Form -->
                </div>
        </div>

       </div>

</body>