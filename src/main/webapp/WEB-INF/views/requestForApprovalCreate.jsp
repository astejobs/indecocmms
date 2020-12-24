<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style> 
    table  {
        table-layout:auto;
    }
    
    table th{
       padding:5px;
    }
    
    

</style>

<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/rfa.js"></script>

<style type="text/css">.newError {float: none; color: red;padding-left: .5em;vertical-align: top;display: block;}</style>

        <!-- End Navigation panel -->
        <!-- Head Section -->
        <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative;width:100%" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">CREATE RFA</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">

                            <a class="label-click" href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href=""> Create RFA</a>

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
                            <form:form method="post" commandName="req" class="form contact-form" id="contact_form" enctype="multipart/form-data">
                                <div class="clearfix">
                                    <div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Request for Approval
                                        </h2>
                                        
                                        <c:if test="${not empty success}">
                                        <div class="success">${success}</div>
                                        </c:if>
                                        <c:if test="${not empty fail}">
                                        <div class="error">${fail}</div>
                                        </c:if>
                                        <div class="form-group" style="display:inline-block;">
                                            <span  style="display:inline-block;position: relative;" > Ref. No.:</span> &nbsp;&nbsp;
                                            <form:input   type="text" path="refnumber" id="refno" />
                                            <form:errors path="refnumber"/>
                                            
                                             
                                            
                                        </div>
                                        <div  class="form-group"  style="display:inline-block;float:right;">
                                        
                                        <span  style="display:inline-block; position:relative;" >Job Site.:</span> &nbsp;
                                            <form:input  type="text"   path="jobSite"  id="jobsite"  style="float:right; width:300px;" />
                                            <form:errors path="jobSite"/>
                                        </div>
                                        <div class="form-group">
                                            <span class="col-lg-5">Job Chargeable</span>&nbsp;&nbsp;
                                                <form:radiobutton  path="jobChargable"  value="Yes"  name="jobchargeable" />&nbsp;Yes &nbsp;&nbsp;
                                                <form:radiobutton   path="jobChargable"  value="No"   name="jobchargeable" />&nbsp;No <br/>
                                                <form:errors   path="jobChargable"/>
                                                <div class="app"> </div>
                                       </br> 
                                        <div class="fil"> </div>        
                                            <span style="display:block" class="col-lg-5">Urgent Work/Purchase: (Within 14 Days)</span>&nbsp;&nbsp;
                                                <input type="radio"  id="urgentworkyes"  name="urgentwork" />&nbsp;Yes &nbsp;&nbsp;
                                                <input type="radio"  id="urgentworkno"  name="urgentwork" />&nbsp;No<br />
                                                <div class="form-group">
                                               
                                               <form:input style="position: relative;left: 14px;" id="urgentwork" path="workDate"/> <img  id="im" src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('urgentwork','ddMMyyyy')" style="cursor:pointer;width:20px; display:inline-block;position: relative;left: 11px;"  />
                                                </div>
                                            <span class="col-lg-5">Partial Delivery</span>&nbsp;&nbsp;
                                                <form:radiobutton  path="partialDelivery"  value="Yes"  name="partialdelivery" />&nbsp;Yes &nbsp;&nbsp;
                                                <form:radiobutton  path="partialDelivery"  value="No"    name="partialdelivery" />&nbsp;No<br />
                                                <form:errors path="partialDelivery"/>
                                            <span class="col-lg-5">Partial Payment Required</span>&nbsp;&nbsp;
                                                <form:radiobutton  path="partialPaymentRequired"  value="Yes"  name="partialpaymentrequired" />&nbsp;Yes &nbsp;&nbsp;
                                                <form:radiobutton  path="partialPaymentRequired" value="No"  name="partialpaymentrequired" />&nbsp;No<br />
                                                 <form:errors path="partialPaymentRequired"/>
                                            <span class="col-lg-5">Payment Terms by Cheque</span>&nbsp;&nbsp;
                                            <form:radiobutton  path="paymentTerm" id="CreditTerm" value="Credit Term"  name="paymenttermsbycheque" />&nbsp;Credit Term &nbsp;&nbsp;
                                            <form:radiobutton  path="paymentTerm" id="cod"   value="COD"  name="paymenttermsbycheque" />&nbsp;COD&nbsp;&nbsp;
                                            <form:radiobutton  path="paymentTerm" id="AdvancePayment"   value="Advance Payment"   name="paymenttermsbycheque" />&nbsp;Advance Payment<br />
                                              <form:errors path="paymentTerm"/>
                                        </div>
                                         <div class="form-group">
                                            <form:input id="leadtime" path="leadTime"  placeholder="Lead Time" />
                                        </div>
                                          <div class="form-group">
                                            <form:select id="quotation" path="quotationheader"   class="input-md round form-control col-lg-5" style="margin-bottom:10px;height:100px" placeholder="Select Quotation"  multiple="multiple">
                                            <form:options items="${quotatoinheaders}"  itemValue="id"  itemLabel="quotationCode" selected="selected"/>
                                            </form:select>
                                        </div>
                                         <form:errors path="quotationheader"/>
                                       <div class="form-group">
                                            <input type="button" id="submit" value="Select Quotation" onclick="SelectName()"/>
                                             <script type="text/javascript">
                                                var popup;
                                                function SelectName() {
                                                popup = window.open("listquotation", "Popup", "width=700,height=550,left=300");
                                                popup.focus();
                                                return false
                                                }
                                             </script>
                                        </div> 
                                        <div class="clearfix"></div>
                                        <div class="form-group">
                                            <form:textarea path="reasonForRequistion" class="input-md round form-control col-lg-3" style="margin-bottom:10px" placeholder="Reason for Requisition" />
                                        </div>
                                         <form:errors path="reasonForRequistion"/>
                                         <div class="clearfix"></div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Purpose of Materials/Services
                                        </h2>
                                        <div class="form-group">
                                            <form:checkbox  path="purposeOfMaterialService" value="Spares/Stocks" />&nbsp;&nbsp;&nbsp;<span>Spares/Stocks</span>&nbsp;&nbsp;
                                            <form:checkbox  path="purposeOfMaterialService" value="Consumables" />&nbsp;&nbsp;&nbsp;<span>Consumables</span>&nbsp;&nbsp;
                                            <form:checkbox  path="purposeOfMaterialService" value="Tools" />&nbsp;&nbsp;&nbsp;<span>Tools</span>&nbsp;&nbsp;
                                            <form:checkbox  path="purposeOfMaterialService" value="Service Rendered" />&nbsp;&nbsp;&nbsp;<span>Service Rendered</span>&nbsp;&nbsp;
                                            <form:checkbox id="others"  path="purposeOfMaterialService" value="Others" />&nbsp;&nbsp;<span>Others</span>&nbsp;&nbsp;
                                            <div class="oth" style="display:none"><input type="text" name="othermaterial"></input></div>
                                        </div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Summary of Quotation
                                        </h2>
                                        <p style="margin-bottom:-5px;position:relative;color:#b6b1b1">The written or formal request for quotation can be made either through facsimile or email.</p>
                                        <p style="color:#b6b1b1">* Quotation submitted must be less than 3 months from the date of RFA recieved</p>
                                        <div class="form-group">
                                            <span><b>Value per Requisition</b></span>
                                            <span style="float:right"><b>Minimum Sourcing Requirements</b></span>
                                            <br />
                                            <span><input type="radio"  id="rdo01"  style="position:relative;left:30px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<= $500</span>
                                            <span style="float:right;right:60px;position:relative">1 Written Quote</span>
                                            <br />
                                            <span><input type="radio"  id="rdo02"  style="position:relative;left:30px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;> $500 & <= $1000</span>
                                            <span style="float:right;right:50px;position:relative">2 Written Quotes</span>
                                            <br />
                                            <span><input type="radio"  id="rdo03"   style="position:relative;left:30px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;> $1000</span>
                                            <span style="float:right;right:50px;position:relative">3 Written Quotes</span>
                                            <br />
                                            <br />
                                         	
                                            <div  id="kip">
                                                <table id="quotationtable" >
                                                    <thead>
                                                        <tr>
                                                            <th>S No.</th>
                                                            <th>Comp. Name</th>
                                                            <th>Price Qtd(w/o GST)</th>
                                                            <th>Ref Nos.</th>
                                                            <th>Date of Quotation</th>
                                                            <th>Validity Date</th>
                                                            <th data-toggle="tooltip" data-placement="bottom" title="Supporting Document" style="white-space: nowrap;  text-overflow:ellipsis; overflow: hidden; max-width:1px;">Supporting Document</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${req.quotation}" var="q" varStatus="count">
                                                    <tr>
                                                       <td>${count.count}</td>
                                                       <td><input type="text" name="quotation[${count.index}].companyName"  value="${q.companyName}"   style="width:90px"  /></td>
                                                    	<td><input type="text" name="quotation[${count.index}].priceQuoted"  value="${q.priceQuoted}"   style="width:90" /></td>
                                                    	<td><input type="text" name="quotation[${count.index}].refNumbers"   value="${q.refNumbers}"  /></td>
                                                    	<td><input type="text" name="quotation[${count.index}].dateOfQuotation"   value='<fmt:formatDate pattern="dd-MM-yyyy" value="${q.dateOfQuotation}"/>' style="width:85px"  /></td>
                                                    	<td><input style="width:85px" type="text" name="quotation[${count.index}].validityDate"  value='<fmt:formatDate  pattern="dd-MM-yyyy"  value="${q.validityDate}"/>' /></td>
                                                    	<td><input  style="width:100px" type="file" name="supportingdoc"  /></td>
                                                    </tr>
                                                    </c:forEach>
                                                    <tr id="no1" ><td>1</td>
                                                    			  <td><input type="text"   id="001" /></td>
                                                    			  <td><input type="text" style="width:90px;"   id="001a" /></td>
                                                    			  <td><input type="text"   id="001b"/></td>
                                                    			  <td><input type="text" style="width:85px; display:inline-block" id="reported_date"  class="001c"/><img  src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('reported_date','ddMMyyyy')" style="cursor:pointer;width:20px; display:inline-block" /></td>
                                                    			  <td><input type="text" style="width:85px; display:inline-block" id="reported_dat"  class="001d"/> <img  src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('reported_dat','ddMMyyyy')" style="cursor:pointer;width:20px; display:inline-block;position: relative;left: -3px;" /></td>
                                                    			  <td><input style="width: 100px;" type="file"  id="001e" /></td></tr>
                                                    <tr id="no2" >
                                                    			<td>2</td>
                                                    			<td style=""><input type="text" id="002" /></td>
                                                    			<td style=""><input  style="width:90;" type="text" id="002a" /></td>
                                                    			<td style=""><input type="text" id="002b" /></td>
                                                    			<td ><input  style="width:85px" type="text"  id="two0" class="002c" /><img  src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('two0','ddMMyyyy')" style="cursor:pointer;width:20px; display:inline-block" /></td>
                                                    			<td ><input  style="width:85px"type="text"  id="two1" class="002d" /><img  src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('two1','ddMMyyyy')" style="cursor:pointer;width:20px; display:inline-block" /></td>
                                                    			<td style=""><input style="width: 100px;"  type="file" id="002e" /></td></tr>
                                                    <tr id="no3"><td>3</td>
                                                    			<td><input style="" type="text" id="003" /></td>
                                                    			<td><input style="width:90" type="text" id="003a" /></td>
                                                    			<td><input style="" type="text" id="003b" /></td>
                                                    			<td ><input style="width:85px"  id="three0" type="text" class="003c" /><img  src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('three0','ddMMyyyy')" style="cursor:pointer;width:20px; display:inline-block" /></td>
                                                    			<td><input style="width:85px"  id="three1" type="text" class="003d" /><img  src="${pageContext.servletContext.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('three1','ddMMyyyy')" style="cursor:pointer;width:20px; display:inline-block" /></td>
                                                    			<td><input style="width: 100px;" type="file" id="003e" /></td></tr>
                                                    
                                                    </tbody>
                                                </table>
                                        
                                            </div>
                                        </div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            For chargeable Job
                                        </h2>
                                        <div class="form-group" style="display:inline-block;">
                                          <span  style="display:inline-block;position: relative;left: 14px;" > Selling Price.:</span>
                                            <form:input style="position: relative;left: 20px;" path="sellingPrice" type="text" id="sellingprice" placeholder="" />
                                             
                                            <br />
                                            <span style="margin-left: 375px;" class="col-md-4 perc">Profit:</span>&nbsp;&nbsp;<!-- <span>%</span> -->
                                            <br />
                                        </div>
                                        
                                        <div class="form-group" style="display:inline-block;float:right;">
                                              <span  style="display:inline-block;position: relative;"  > Cost Price.:</span> &nbsp;  &nbsp; 
                                              <form:input path="costPrice"  type="text" id="costprice" placeholder="" style="float:right" />
                                        </div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Waiver of Competition
                                        </h2>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Applicable
                                        </h2>
                                         <span class="appl" style="color:red"> </span>
                                         
                                        <div class="form-group chv">
                                            <form:checkbox class="require-one" path="guidelinesAreMet" value="Yes" />&nbsp;&nbsp;&nbsp;<span>Purchasing procedure and guidelines are met</span><br />
                                            <form:checkbox class="require-one" path="purchaseOfrecurrentItems"  value="Yes" />&nbsp;&nbsp;&nbsp;<span>Purchases of recurrent items at the last bought price, with prior waiver of competitive approval</span><br />
                                            <form:checkbox class="require-one" path="contractualSupplyOfItems"  value="Yes" />&nbsp;&nbsp;&nbsp;<span>Contractual supply of items or services during the term of contract</span>
                                        </div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Not Applicable
                                        </h2>
                                        <div class="form-group chvv" >
                                            <form:checkbox class="require-two"  path="soleAgentOEM"   value="Yes"/>&nbsp;&nbsp;&nbsp;<span>Sole agent O.E.M. (To attach solo Agent /O.E.M letter as supporting document)</span><br />
                                            <form:checkbox class="require-two"  path="timeCriticalPurchase" value="Yes" />&nbsp;&nbsp;&nbsp;<span>Time critical purchase of extreme urgency (To indicate reason for urgency under "Remarks")</span><br />
                                            <input class="require-two" type="checkbox" />&nbsp;&nbsp;&nbsp;<span>Others (To indicate under remarks)</span>
                                           
                                        </div>
                                        <div class="form-group">
                                            <form:textarea path="remarks"   class="input-md round form-control col-lg-3" style="margin-bottom:10px" placeholder="Remarks" ></form:textarea>
                                             <form:errors path="remarks"/>
                                        </div>
                                        <div class="form-group">
                                            <input type="file" name="requestorsigniture" id="file" class="input-md round form-control">
                                        </div>
                                        <div class="form-group">
                                            <input type="submit" id="submit" value="Submit" />
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div>
                    <!-- End Contact Form -->
                </div>
           
<script>  

	
	


    $(document).ready(function(){
    	
    	 $('#others').change(function() {
    		 isCheckedC = $(this).prop('checked')
    		 if(isCheckedC){
    			 $(".oth").show();
    		 } 
    		 else{
    			 $(".oth").hide();
    		 }
    	  
    	}); 
    	
    	
    	
    	
    	
    	$("#jobChargable1").on("change", function () {
    		
    	   // alert(this.value);
    	   
    	  
    		var radioValue = $("input[name='jobChargable']:checked").val();
            if(radioValue){
        $(".app").after($('<span id="de" class="col-lg-5">PO Number</span> <input id="de"  type="text" name="poNumber" >') ) ;
        $(".fil").after($('<div style="margin-top:4px;" id="de" class="form-group"><input type="file" name="poDoc" id="file" class="input-md round form-control"> </div>') );  
            }
           /*  else{
            	$("[id=de]").remove()
            } */
    	  
    			   
    	
    	});
    	$("#jobChargable2").on("change", function () {
    		/* alert(this.value); */
    		var radioValue = $("input[name='jobChargable']:checked").val();
            if(radioValue){
            	
            	var er=$("[id=de]").remove();
            	/* alert(er);
            	for(var x  in er){
            		$(x).remove();
            	} */
            	
            }
    	});
    	
    /* 	$(document).on("change focus blur keyup",'#001c', function () {
    		console.log("HVJHVJH")
    		NewCssCal('reported_date','ddMMyyyy');
    		 $('#001c').datepicker({ dateFormat: 'dd-mm-yy' });
    		
    		
    	}); */
    	
    	$(document).on("keyup","#costprice, #sellingprice", function(e) {    
			var cp=parseFloat($('#costprice').val());
			var sp=parseFloat($('#sellingprice').val());
			var pro="Profit:  ";
			var p="%"
			if(sp<cp){
				$('.perc').empty();
				$('.perc').append("selling price canot be less than cost price");
				
			}
			
			else {  
				if(cp && sp) {
				var profit=((sp - cp) / cp * 100).toFixed(2);
				console.log(profit);
				$('.perc').empty();
				$('.perc').append(pro +profit+p);
				}
				}

				
			
		}); 
    	
    	
    	
    	
    	(function(){
    		
    		var isChecked = $('#jobChargable1').prop('checked');
    		 /* alert(isChecked);  */
    		/* var isChecked = $('#jobChargable2').prop('checked'); */
    		 if(isChecked) {
    		     $('#jobChargable1').trigger("change");
    		 }
    		 else{
    			 $('#jobChargable2').trigger("change");
    		 }
    		

    		
    		
    	})();
    	( function(){
    		  if($('#cod').is(':checked'))
    			  {
    			  $("#leadtime").show();
    			  }
    		  }
    		   )();
    	
    	
    	(function(){
    		 if($('#urgentwork').val()=='')
    			  $("#urgentworkno").prop('checked', true);
    		  else
    			  {
    			  $("#urgentwork").show();
    			  $("#urgentwork").val("");
    			  $('#im').show();
    			  $("#urgentworkyes").prop('checked', true);
    			  }
    		
    	})()
    	
    	
    });


</script>