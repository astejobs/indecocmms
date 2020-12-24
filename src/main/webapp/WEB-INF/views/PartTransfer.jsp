<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<style>

   table {
        table-layout:auto;
   }
   
   .table th {
	white-space: nowrap;
	word-wrap: break-word;
	text-overflow: ellipsis;
	overflow: hidden;
	max-width: 1px;
	text-align: justify;
}
.table td {
	white-space: nowrap;
	word-wrap: break-word;
	text-overflow: ellipsis;
	overflow: hidden;
	max-width: 120px;
	text-align: justify;
}
.tablee th:LAST-CHILD {
	width: 200px;
}

input[type="submit"] {
	border: none;
	background-color: #0754a4;
	color: #fff;
	padding: 7px 9px;
	border-radius: 0px;
}

.al {
    border: none;
    background-color: #0754a4;
    color: #fff;
    padding: 7px 9px;
    border-radius: 0px;
    text-decoration: none;
}




</style>
<body class="appear-animate">
        <!-- Head Section -->
         <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">Transfer Part</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="#">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="#">Transfer Part</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- End Head Section -->
        <!---MENU-->

        <!--End Menu-->
        <!--Form-->
           <div style="height:100px;position:relative"></div>
        <div class="container" style="width:100%;">
            <div class="row">
                <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
                    <jsp:include page="/WEB-INF/views/menu.jsp" />
                </div>
                <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">
                                <div class="clearfix">
                                    <div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Reserved Parts
                                        </h2>
                                        <div class="form-group">
                                            <table class="table table-stripped ftable" id="tblResult">
                                                <thead>
                                                    <tr>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Part Name">Part Name</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Part Desc">Part Desc</th>
                                                        <th data-toggle="tooltip" data-placement="bottom"  title="Manufacturer">Manufacturer</th>
                                                        <th data-toggle="tooltip" data-placement="bottom"  title="Vendor">Vendor</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Warehouse Requestor">Warehouse Requestor</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Warehouse Issuer">Warehouse Issuer</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Reserved Qty">Reserved Qty</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Reserved Date">Reserved Date</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Status">Status</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Actions">Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                	<c:forEach items="${reservedList}" var="partTransfer">
                                                    <tr>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="Actions" >${partTransfer.partsInWarehouse.part.partType.partName}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.partsInWarehouse.part.description}">${partTransfer.partsInWarehouse.part.description}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.partsInWarehouse.part.manufacturer.name}">${partTransfer.partsInWarehouse.part.manufacturer.name}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.partsInWarehouse.part.vendor.vendorName}">${partTransfer.partsInWarehouse.part.vendor.vendorName}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.requestor.name}">${partTransfer.requestor.name}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.issuer.name}">${partTransfer.issuer.name}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.reservedQuantity}">${partTransfer.reservedQuantity}</td>                                                        
                                                        <fmt:formatDate value="${partTransfer.reservedDate}"
																			var="reservedDate" pattern="dd-MM-yyyy" />
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${reservedDate}">${reservedDate}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.status}">${partTransfer.status}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="Unreserve"><a class="al" href="">Unreserve</a></td>
                                                    </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Issue Parts
                                        </h2>
                                        <div class="form-group">
                                            <table class="table table-stripped ftable" id="tblResult" data-paging="true">
                                                <thead>
                                                    <tr>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Part Name" >Part Name</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Part Desc">Part Desc</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Manufacturer">Manufacturer</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Vendor">Vendor</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Warehouse Requestor">Warehouse Requestor</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Warehouse Issuer">Warehouse Issuer</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Reserved Qty">Reserved Qty</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Reserved Date">Reserved Date</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Status">Status</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Actions">Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                	<c:forEach items="${issueList}" var="partTransfer">
                                                    <tr>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.partsInWarehouse.part.partType.partName}">${partTransfer.partsInWarehouse.part.partType.partName}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.partsInWarehouse.part.description}">${partTransfer.partsInWarehouse.part.description}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.partsInWarehouse.part.manufacturer.name}">${partTransfer.partsInWarehouse.part.manufacturer.name}</td>
                                                        <td  data-toggle="tooltip" data-placement="bottom" title="${partTransfer.partsInWarehouse.part.manufacturer.name}" >${partTransfer.partsInWarehouse.part.manufacturer.name}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.requestor.name}">${partTransfer.requestor.name}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.issuer.name}">${partTransfer.issuer.name}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.reservedQuantity}">${partTransfer.reservedQuantity}</td>
                                                        <fmt:formatDate value="${partTransfer.reservedDate}"
																			var="reservedDate" pattern="dd-MM-yyyy" />
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${reservedDate}">${reservedDate}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.status}">${partTransfer.status}</td>
                                                        <td><a class="al"  href="${pageContext.servletContext.contextPath}/inventory/transfer/issue/${partTransfer.id}">Issue</a></td>
                                                    </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Receive Parts
                                        </h2>
                                        <div class="form-group">
                                            <table class="table table-stripped ftable" id="tblResult" data-paging="true">
                                                <thead>
                                                    <tr>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Part Name">Part Name</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Part Desc">Part Desc</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Manufacturer">Manufacturer</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Vendor">Vendor</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Warehouse Requestor">Warehouse Requestor</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Warehouse Issuer">Warehouse Issuer</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Reserved Qty">Reserved Qty</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Reserved Date">Reserved Date</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Issued Qty">Issued Qty</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Status">Status</th>
                                                        <th data-toggle="tooltip" data-placement="bottom" title="Actions">Actions</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                    <c:forEach items="${recievedList}" var="partTransfer">
                                                    <tr>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.partsInWarehouse.part.partType.partName}">${partTransfer.partsInWarehouse.part.partType.partName}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.partsInWarehouse.part.description}">${partTransfer.partsInWarehouse.part.description}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.partsInWarehouse.part.manufacturer.name}">${partTransfer.partsInWarehouse.part.manufacturer.name}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.partsInWarehouse.part.vendor.vendorName}">${partTransfer.partsInWarehouse.part.vendor.vendorName}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.requestor.name}">${partTransfer.requestor.name}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.issuer.name}">${partTransfer.issuer.name}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.reservedQuantity}">${partTransfer.reservedQuantity}</td>
                                                        <fmt:formatDate value="${partTransfer.reservedDate}"
																			var="reservedDate" pattern="dd-MM-yyyy" />
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.reservedQuantity}">${reservedDate}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.issuedQuantity}">${partTransfer.issuedQuantity}</td>
                                                        <td data-toggle="tooltip" data-placement="bottom" title="${partTransfer.status}">${partTransfer.status}</td>
                                                        <td><a class="al"  href="${pageContext.servletContext.contextPath}/inventory/transfer/recieve/${partTransfer.id}">Recieve</a></td>
                                                    </tr>
                                                    </c:forEach>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                        
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Reserve More Parts
                                        </h2>
                                        
                                        <div class="form-group" style="margin-bottom:50px;">
                                          <select id="warehouse" class="input-md round form-control">
                                          		<option value="svar">Select Warehouse</option>
	                                        <c:forEach items="${warehouseList}" var="warehouse">
	                                             <option value="${warehouse.id}">${warehouse.name}</option>
	                                        </c:forEach>
                                          </select>                                        
                                        </div>
                             <div id="issuepart" class="form-group">
							<table class="table table-stripped tablee ftable" id="tbl2" style="display:none;" data-paging="true">
								<thead>
									<tr>
										<th data-toggle="tooltip" data-placement="bottom" title="Part Name">Part Name</th>
										<th data-toggle="tooltip" data-placement="bottom" title="Part Desc">Part Desc</th>
										<th data-toggle="tooltip" data-placement="bottom" title="Warehouse">Warehouse</th>
										<th data-toggle="tooltip" data-placement="bottom" title="Manufacturer">Manufacturer</th>
										<th data-toggle="tooltip" data-placement="bottom" title="Vendor">Vendor</th>
										<th  data-toggle="tooltip" data-placement="bottom" title="Available Qty">Available Qty</th>
										<th colspan="2" data-toggle="tooltip" data-placement="bottom" title="Qty to be Issued">Qty to be Issued</th>
									</tr>
								</thead>
								<tbody>
								
								</tbody>
								</table>
						</div>
                                    </div>
                                </div>
                        	</div>
                    </div>
                    <!-- End Contact Form -->
                </div>
            <!--End Form-->
        <script type="text/javascript">    
			$(document).ready(function(){
				$('#warehouse').change(function(){
					var txt=  $('#warehouse :selected').text();
					if(txt=="Select Warehouse"){
						$("#tbl2").hide();
						$("#tbl2 tbody").empty();	
					}
					var id = $(this).val();
					var url = "${pageContext.servletContext.contextPath}/inventory/parts/"+id;
					$.get(url,function(data){
						$("#tbl2").hide();
						$("#tbl2").show();
						$("#tbl2 tbody").empty();
						for (i = 0; i < data.length; i++) {
							$("#tbl2 tbody")
									.append(
											"<tr><td>"
													+ data[i].part.partType.partName
													+ "</td><td>"
													+data[i].part.description
													+ "</td><td>"
													+ data[i].warehouse.name
													+ "</td><td>"
													+ data[i].part.manufacturer.name
													+ "</td><td>"
													+ data[i].part.vendor.vendorName
													+ "</td><td>"
													+ data[i].totalPartQuantity
													+ "</td>"
													+"<td colspan='2'><form action='${pageContext.servletContext.contextPath}/inventory/transfer/reserve' method='post'>"
													+"<input style='width:90px;' type='text' name='reservedQuantity'/> &nbsp; &nbsp;<input type='hidden' name='partsInWarehouse' value='"+data[i].id+"'/> <input type='hidden' name='requestor' value='"+id+"'/>"
													+"<input type='submit' value='Reserve'>"
													+"<form>"
													+"</td>"
													+"</tr>");
							
									}
					});
						
					
				});
				
				jQuery(function() {
					$('.ftable').footable();
				});
				
			});
		</script>