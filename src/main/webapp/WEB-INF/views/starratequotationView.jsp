<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/Starratequotation.js"></script>
	 
<style>#sortable th{ text-align: center; } .equipmentSerialno{ width:50px; } .equipmentName{ width:100px } #sortable th{ width:50%; }.equipmentunitOfMeasurement{ width:80px; } .equipmentUnitCost{ width:80px; }.equipmentQuantity{ width:80px; } .sgdd, .equipmentItemCode{ width:80px; }
  
button{
    border: none;
    background-color: #0754a4;
    color: #fff;
    padding: 7px 9px;
}  
 input[type="button"] {
    border: none;
    background-color: #0754a4;
    color: #fff;
    padding: 7px 9px;
      border-radius:0px;
   
}
input[type="submit"] {
    border: none;
    background-color: #0754a4;
    color: #fff;
    padding: 7px 9px;
    border-radius:0px;
    
}
 
</style>
         

</head>
<body class="appear-animate">


 <!-- Head Section -->
 
        <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">STAR RATE VIEW</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href="../forms_iCMMS/configurator-civil-Level-3.html">Civil Level 3</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <div class="clearfix" ></div>
        
         <!-- End Head Section -->
       <div  style="height:100px;position:relative"></div>
      		  <div class="container" style="width:100%;">
		         <div class="row">
		            <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3" >
		    		   <jsp:include page="/WEB-INF/views/menu.jsp" />
		           </div>
		           <div class="col-xs-9 col-sm-9 col-lg-9 col-md-9">
                       
                            <form:form class="form contact-form" id="contact_form" action="${var_url}" commandName="quotation">
                                <div class="clearfix">
                                    <div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                           Star Rate Quotation
                                        </h2>
                                        
                                         <c:if test="${not empty success}">
                                         <div class="success">${success}</div> 
                                         </c:if>
                                         <c:if test="${not empty fail}">
                                         <div class="error"> ${fail}</div>
                                         </c:if>
                                         </div>
                                       <div class="form-group">
                                            <form:hidden path="id"/>
                                            <form:hidden path="quotationCode"/>
                                            <form:hidden path="workspace"/>
                                            <form:hidden path="type"/>
                                            <form:input type="text" path="quotationDate" id="quotationDate" class="input-md round form-control" placeholder="Installation Date" style="width:25%" />             
                                            <img src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('quotationDate','ddMMyyyy')" style="cursor:pointer;width:30px" />
                                            <form:errors path="quotationDate"/>
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="validity" id="validity" class="input-md round form-control" placeholder="Validity" />
                                            <form:errors path="validity"/>
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="quotationFor" id="clientname" class="input-md round form-control" placeholder="Client Name" />
                                            <form:errors path="quotationFor"/>
                                        </div>
                                        <div class="form-group">
                                            <form:textarea  path="quotationDescription" name="description" class="input-md round form-control col-lg-3" style="margin-bottom:10px" placeholder="Description"></form:textarea>
                                        </div>
                                        <div class="form-group">
                                            <form:input type="text" path="refrenceno" id="refno" class="input-md round form-control" placeholder="SITE Reference No." />
                                            <form:errors path="refrenceno"/>
                                       </div>
                                        
                                        </div>
                                        
                                        <div class="clearfix"></div>
                                        <div class="clearfix"></div>
                                        <br />
                                      
                                        <div class="clearfix"></div><br />
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Star Rate Items
                                        </h2>
                                       
                                        <div class="form-group">
                                            <input type="button" id="btnResult" value="Add" />
                                        </div>
                             
                                            
                                        <div class="form-group">
                                            <table class="table"   id="tblResult">
                                                <thead>
                                                    <tr>
                                                        <th>S.No</th>
                                                        <th>Description</th>
                                                        <th>Unit</th>
                                                        <th>Unit Price</th>
                                                        <th>Qty</th>
                                                        <th>Total Cost</th>
                                                       
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                     <c:forEach items="${quotation.otherVendorEquipmentList}" var="othervendorequip" varStatus="i">
                                                    <tr>
                                                      <td><input type="text" class="othervendorserialno"   name="otherVendorEquipmentList[${i.index}].serialno" value="${othervendorequip.serialno}"></td>
                                                       <td><textarea style="width: 153px;" class="othervendordescription"    name="otherVendorEquipmentList[${i.index}].description">${othervendorequip.description}</textarea></td>
                                                       <td><input type="text"   class="othervendorunit" name="otherVendorEquipmentList[${i.index}].unitOfMeasure" value="${othervendorequip.unitOfMeasure}"></td>
                                                       <td><input type="text"   class="othervendorprice"   name="otherVendorEquipmentList[${i.index}].unitPrice" value="${othervendorequip.unitPrice}"></td>
                                                       <td><input type="text"   class="othervendorquantity"   name="otherVendorEquipmentList[${i.index}].qty" value="${othervendorequip.qty}"></td>
                                                      <td><input style="width: 127px;" type="text"   class="othervendorcost"   name="otherVendorEquipmentList[${i.index}].cost" value="${othervendorequip.cost}"></td>
                                                    </tr>
                                                   
                                                
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="clearfix"></div>
                                        <div class="form-group">
                                            <input type="text"  readonly name="subtotal" id="otherveneqsubtotal" class="input-md round col-lg-3" placeholder="Sub Total" />
                                        </div>
                                        <div class="clearfix"></div><br />
                                     
                                      
                                       
                                          <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            CALCULATIONS
                                        </h2>
                                      
                                       <!--   <div class="clearfix"></div> -->
                                      <div style="display:inline-block">
                                            <label> Actual Amount</label>
                                        </div>
                                          &nbsp;&nbsp;
                                        <div style="display:inline-block">   
                                            <form:input type="text" readonly="true" path="actualamount" id="actualamount" class="input-md round " placeholder="Actual Amount" />
                                        </div>
                                        
                                        <div style="display:inline-block;display:none;">
                                           <div >

                                             <form:select  readonly="true" style="width: 184px;height: 37px;" path="value"   class="target">
                                                          
                                                            <form:option value="Emergency">Emergency Charges</form:option>
                                                            <form:option value="Discount">Discount Charges</form:option>
                                              </form:select>              

                 
                                        </div>
                                        </div>
                                        
                                    
                                      <div style="display:inline-block">
                                            <label> Extra Charges %</label>
                                        </div>
                                       
                                        
                                        <div style="display:inline-block" >

                                            <form:input type="text"  readonly="true" style=" width: 95px;" path="extraCharges" id="emerper" class="input-md round " />

                                        </div>
                                         <div class="clearfix"></div><br />
                                      
                                    <div style="display:inline-block">
                                            <label> Extra Charges Amount</label>
                                        </div>
                                         &nbsp;&nbsp;
                                        <div style="display:inline-block">

                                            <input style="width: 282px;"  readonly type="text"  id="emeramt" class="input-md round " placeholder="Emergency or Discount Amount" readonly="true" />

                                        </div>
                                         &nbsp;&nbsp;
                                          
                                        <div style="display:inline-block">
                                            <label> Amount After Extra Charges</label>
                                        </div>
                                         &nbsp;&nbsp;
                                        <div style="display:inline-block">
                                            <form:input type="text"  readonly="true" path="amountafter" id="emerafterr" class="input-md round " placeholder="Amount after emergency or Discount"  />

                                        </div>
                                         &nbsp;&nbsp;
                                        <div class="clearfix"></div><br />
                                      
                                        <div style="display:inline-block">
                                            <label> GST %</label>
                                        </div>
                                        &nbsp;&nbsp; &nbsp;&nbsp;
                                        <div style="display:inline-block">
                                            <form:input type="text" readonly="true"  path="gst" id="gst" class="input-md round " placeholder="GST in %" />
                                        </div>
                                        &nbsp;&nbsp; &nbsp;&nbsp;
                                        <div style="display:inline-block">
                                            <label> GST Amount</label>
                                        </div>
                                        &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                                        <div style="display:inline-block">
                                            <input type="text"  readonly name="gstamount" id="gstamount" class="input-md round " placeholder="GST Amount" />
                                        </div>
                                        &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;
                                       
                                        <div style="display:inline-block">
                                            <label>Grand Amount[SGD]</label>
                                        </div>
                                         &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;
                                        <div style="display:inline-block">
                                            <form:input type="text"  readonly="true"  path="totalAmount" id="grandamount" class="input-md round " placeholder="Grand  Total Amount" />
                                        </div>
                                         <div class="clearfix"></div><br />
                                        <div class="form-group">
                                            <div class="form-group">
                                                <form:textarea  path="remarks"  class="input-md round form-control col-lg-3" style="margin-bottom:10px" placeholder="Terms & Conditions" ></form:textarea>
                                            </div>
                                        </div>
                                        <div class="clearfix"></div><br />
                                        <c:url value="/quotation/report/${quotation.id}" var="report_url"></c:url>
                                         <a  style=" background: green; color: #FFF;padding: 8px;" href="${report_url}">Download report</a>
                                         </form:form>
                                    </div>
                                </div>

                           
                              
                             
                               
                               
                           
                        </div>
                    
                    <!-- End Contact Form -->
         

</body>
</html>\