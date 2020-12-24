<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<style>  
   .table {
    table-layout:auto;
   }
   
   .tablee th:LAST-CHILD {
	 width: auto;
}

.ib{ 
   display:inline-block;

}

.table td,th {
	white-space: nowrap;
	word-wrap: break-word;
	text-overflow: ellipsis;
	overflow: hidden;
	max-width: 1px;
	text-align: justify;
}
   
   

</style>
<body class="appear-animate">
        <!-- Head Section -->
         <!-- Head Section -->
         <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">Issue Part Details</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="#">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="#">Issue Part Details</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Head Section -->
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
                        Issue Parts Details
                    </h2>

                    <!-- Contact Form -->
                    <div class="form-group">
                        <label class="col-md-3">Part Name:</label>
                        <label>${partTransfer.partsInWarehouse.part.partType.partName}</label>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3">Part Desc:</label>
                        <label>${partTransfer.partsInWarehouse.part.description}</label>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3">Manufacturer:</label>
                        <label>${partTransfer.partsInWarehouse.part.manufacturer.name}</label>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3">Vendor:</label>
                        <label>${partTransfer.partsInWarehouse.part.vendor.vendorName}</label>
                    </div>
					<div class="form-group">
                        <label class="col-md-3">Warehouse Requester:</label>
                        <label>${partTransfer.requestor.name}</label>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3">Warehouse Issuer:</label>
                        <label>${partTransfer.issuer.name}</label>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3">Reserved Quantity:</label>
                        <label>${partTransfer.reservedQuantity}</label>
                    </div>
                    <div class="form-group">
                        <label class="col-md-3">Batch No:</label>
                        <input type="text" name="batch" id="batch">
                        <i class="err sh" > </i> 
                       
                    </div>                    
                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                        Add More Parts to Warehouse
                    </h2>
                    <c:if test="${not empty success}">
		                   <div class="success">${success}</div>
		              </c:if>
					  <c:if test="${not empty fail}">
		                    <div class="error">${fail}</div>
		              </c:if>                    
                        <div class="clearfix">
                            <div>
                                <div class="form-group">
                                    <table class="table table-stripped tablee" id="tblResult" data-paging="true">
                                        <thead>
                                            <tr>
                                            	<th>Batch No</th>
                                                <th>Unit Cost</th>
                                                <th>Total Quantity</th>
                                                <th>Quantity Issued</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        	<c:forEach items="${batches}" var="batch">
                                            <tr>
                                            	<td>${batch.batchNo}</td>
                                                <td>${batch.unitCost}</td>
                                                <td>${batch.quantity}</td>
                                                <td >
                                                	<form id="fo" action="${pageContext.servletContext.contextPath}/inventory/transfer/issue/${partTransfer.id}" method="post" data-bvalidator-validate="true" novalidate="novalidate">
                                                	<input type="text" data-bvalidator='required'  style='width:90px;' name="issuedQuantity" id="bno" class="input-md round form-control ib">
                                                	<input type="hidden" name="batchNo" id="batchNo"/>
                                                	<input type="hidden" name="batchId" value="${batch.id}">
                                                    <input type="submit" class="cr" id="Create" value="Issue" />
                                                    </form>
                                                </td>
                                            </tr>
											</c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                        </div>

                    <!-- End Contact Form -->
                </div>

            </div>

            <!--End Form-->
            <!-- Foter -->

        </div>
        <!-- End Page Wrap -->

        <script>
        $(document).ready(function(){
        	$(document).on('focusout blur','#batch', function(){
        		var val = $(this).val();
        		 $('#tblResult').find(':input').each(function(){        			
        			if($(this).prop("id")=="batchNo"){
        				$(this).prop("value", val);
        			}
        		});
        	});
        	
        	$(document).on("click",".cr",function(){
        	//	alert("vv");
        		
        		var val = $("#batch").val();
        	//	alert(val);
        		
        		if(val==""){
        			
        			$(".sh").empty();
        			$(".sh").append("Enter Batch No");
        			return false;

        		}
        	});
        	
        });
            jQuery(function($) {
                $('.table').footable();
            });
            
            $(document).ready(function () {
				// alert("YYY")
				 console.log("YYY");
			        console.log($('form').bValidator());
			    });
        </script>
        <!-- JS -->