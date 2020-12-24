<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<style>
  span.sp{
  display:inline-block;position: relative;left: 494px;
  
  }
  
  span.js{
  display:inline-block; position:relative;left:400px; 
  }
   table {
       table-layout:auto;
   }
   
    table th, td {
       width:auto;
    }
    img.cl{
 
    width: 150px;
    height: 150px;
 
 }
  .printt{
   float:right;
   position:relative;
   bottom:20px;
    border: none;
    background-color: #0754a4;
    color: #fff;
    padding: 7px 9px;
    border-radius: 0px;
}

div.clss{
      display:none;
 }
 
 div.sig{
       display:none;
 }
 
 div.navv{
              display:none;
 }

div.in {
       display:inline-block;
}

table th:LAST-CHILD {
  
   width:auto;
}


@media print {
/* insert your style declarations here */
        div.col-xs-3 {
                  display:none;
        }
        div.col-xs-9{
              width:100%
        }
         table {
       table-layout:auto;
   }
        
         div.navbar{
                  display:none;
        }
        
        section.small-section{
             display:none;
        }
        
        footer {
                  display:none;
        }
     a {
               display:none;
     }

div.in{
           display: inline-block;
}
        
        @page {
          size: A4;
          size: auto;
          margin: 0;
         
          
}

 table th:LAST-CHILD {
   display:none;
   width:auto;
} 
 
 div.sig{
            display: block;
            position:fixed;
            bottom:0px;
            left:25%;
 }
 img.cl{
 
    width: 150px;
    height: 150px;
 
 }
 
  div.navv{
              display:block;
 }
 
  div.pri{
              postion:relative;
              top:-130px;
              
 }
 
 h2.prin{
 
        position:relative ;
        bottom:100px; 
 }
 
 h2.prinh{
 
        position:relative ;
        top:0px; 
 }
 
 div.prinn{
       position:relative;
       top:0px; 
      
 }
 div.clss{
      display:block;
 }
 
 
 
/*  @page :last {
        div.sig{
            display: block;
            position:fixed;
            bottom:0px;
 }
} */
      .printt{
   display:none;
   
}

/* .brr: after{
     content:"<br> <br> <br>  <br>"
} */



span.fl{
   position:relative;
   bottom:40px;
   display:block;
   visibility:visible;
}

span.sp{
       display:inline-block;position: relative;left: 250px;
}

span.js{
  display:inline-block; position:relative;left:180px; 
  }

div.fg {
     position:relative ;
        bottom:100px; 
}

div.che {
     position:relative ;
        bottom:100px; 
}
div.na {
     position:relative ;
     top:100px;
}

p.pp {
     position:relative ;
     top:10px;
}

p{
       margin: 0 0 0 0
}

}
</style>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>
 <script>
 $(document).ready(function () {
	    $("#urgentwork").hide();
	    $("#leadtime").hide();
	    $(".visibility").hide();
	    $("#urgentworkyes").click(function () {
	        $("#urgentwork").show();
	    });
	    $("#urgentworkno").click(function () {
	        $("#urgentwork").hide();
	    });
	    $("#cod").click(function () {
	        $("#leadtime").show();
	    });
	    $("#CreditTerm").click(function () {
	        $("#leadtime").hide();
	    });
	    $("#AdvancePayment").click(function () {
	        $("#leadtime").hide();
	    });
	    
	    if($('#urgentwork').val()=='')
			  $("#urgentworkno").prop('checked', true);
		 else{
			  $("#urgentwork").show();
			  $("#urgentworkyes").prop('checked', true);
		 }
	    
	    if($('#cod').is(':checked'))
		  {
		  $("#leadtime").show();
		  }
	   
	});
	
    </script>
</head>

        <!-- End Navigation panel -->
        <!-- Head Section -->
        <section class="small-section bg-dark-alfa-30 parallax-2" style="margin-bottom:-70px;position:relative" data-background="${pageContext.servletContext.contextPath}/resources/assets/images/full-width-images/section-bg-18.jpg">
            <div class="relative container align-left">
                <div class="row">
                    <div class="col-md-8">
                        <h1 class="hs-line-11 font-alt mb-10 mb-xs-0" style="margin-top:5px">VIEW RFA</h1>
                    </div>

                    <div class="col-md-4 mt-30">
                        <div class="mod-breadcrumbs font-alt align-right">
                            <a class="label-click" href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a>&nbsp;/&nbsp;<a class="label-click" href=""> VIEW RFA</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        <div class="navv">
            <div class="in" style="position: relative; top:-10px; left:20px;" >
           
               <img alt="" src="${pageContext.servletContext.contextPath}/resources/logoImage/logo.png" >
            
                  <p style="position:relative;bottom:0px; font-size:18px;">Reg.No 199801722W </p>
            </div>
            
            <div  class="in"   style="position:relative; left:70px; text-align:center; width:400px;">
               <h1 style="position:relative;top:5px;" >   LIAN SOON M&E PTE LTD</h1>
               <h6 style="position:relative;top:-25px;" >  21A, SENOKO LOOP #03-00, </h6>
               <h6  style="position:relative;top:-25px;" > LIAN SOON INDUSTRIAL BUILDING  S(758174), Singapore </h6>
            
             </div>
            
            <div class="in" style="position:relative; left:80px;top:-60px;" >
                  <img  alt="" src="${pageContext.servletContext.contextPath}/resources/logoImage/bizsafe.jpg" height="120px" width="120px" >
            </div>
        
         </div>
        <!-- End Head Section -->
     
  		<div  style="height:100px;position:relative"></div>
      		  <div class="container" style="width:100%;">
		         <div class="row">
		            <div class="col-xs-3 col-sm-3 col-lg-3 col-md-3">
		    		   <jsp:include page="/WEB-INF/views/menu.jsp" />
		           </div>
		           	<div class="col-xs-9 col-sm-9 col-lg-9 col-md-9 pri">
                            <form:form method="post" commandName="req" class="form contact-form" id="contact_form" enctype="multipart/form-data">
                                       <button class="printt" onclick="event.preventDefault();window.print();">Print</button>
                                       <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            View Request for Approval
                                        </h2>
                                        
                                         <c:if test="${not empty success }">
                                        <div class="success">${success}</div>
                                        </c:if>
                                        <c:if test="${not empty fail }">
                                        <div class="error">${fail}</div>
                                        </c:if>
                                        <div class="form-group">
                                        <span  style="display:inline-block;position: relative;left: 14px;" > Ref. No.:</span>
                                            <form:input style="position: relative;left: 35px;" type="text" readonly="true" path="refnumber" id="refno" placeholder="Ref. No.:" />
                                        <span  class="js">Job Site.:</span>
                                            <form:input type="text"  readonly="true"  path="jobSite"  id="jobsite" placeholder="Job Ste.:" style="float:right;width:300px;" />
                                        </div>
                                        <div class="form-group">
                                            <span class="col-lg-5">Job Chargeable</span>&nbsp;&nbsp;
                                                <form:radiobutton  path="jobChargable"  value="Yes"  name="jobchargeable" />&nbsp;Yes &nbsp;&nbsp;
                                                <form:radiobutton  path="jobChargable"  value="No"   name="jobchargeable" />&nbsp;No <br />
                                                <c:if test="${req.jobChargable == 'Yes'}" >
                                                <span id="de" class="col-lg-5">PO Number</span> <input readonly="readonly" id="de"  value="${req.poNumber}" type="text" name="poNumber" value="PO Number">
                                                <c:url value="/requestForApproval/downloadPodocument" var="d"><c:param name="id" value="${req.id}"></c:param></c:url>
                                                <div style="margin-top:4px;" id="de" class="form-group"><a style="margin-left:14px;" href='${d}'>Download Po Document </a> </div>
                                                </c:if>
                                            <span class="col-lg-5">Urgent Work/Purchase: (Within 14 Days)</span>&nbsp;&nbsp;
                                                <input type="radio"  id="urgentworkyes"  name="urgentwork" />&nbsp;Yes &nbsp;&nbsp;
                                                <input type="radio"  id="urgentworkno"  name="urgentwork" />&nbsp;No<br />
                                                <div class="form-group">
                                               <form:input id="urgentwork" path="workDate"/>
                                                </div>
                                            <span class="col-lg-5">Partial Delivery</span>&nbsp;&nbsp;
                                                <form:radiobutton  path="partialDelivery"  value="Yes"  name="partialdelivery" />&nbsp;Yes &nbsp;&nbsp;
                                                <form:radiobutton  path="partialDelivery"  value="No"    name="partialdelivery" />&nbsp;No<br />
                                            <span class="col-lg-5">Partial Payment Required</span>&nbsp;&nbsp;
                                                <form:radiobutton  path="partialPaymentRequired"  value="Yes"  name="partialpaymentrequired" />&nbsp;Yes &nbsp;&nbsp;
                                                <form:radiobutton  path="partialPaymentRequired" value="No"  name="partialpaymentrequired" />&nbsp;No<br />
                                            <span class="col-lg-5">Payment Terms by Cheque</span>&nbsp;&nbsp;
                                            <form:radiobutton  path="paymentTerm" id="CreditTerm" value="Credit Term"  name="paymenttermsbycheque" />&nbsp;Credit Term &nbsp;&nbsp;
                                            <form:radiobutton  path="paymentTerm" id="cod"   value="COD"  name="paymenttermsbycheque" />&nbsp;COD&nbsp;&nbsp;
                                            <form:radiobutton  path="paymentTerm" id="AdvancePayment"   value="Advance Payment"   name="paymenttermsbycheque" />&nbsp;Advance Payment<br />
                                        </div>
                                         <div class="form-group">
                                            <form:input readonly="true" id="leadtime" path="leadTime"  placeholder="Lead Time" />
                                        </div>
                                         <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Quotation
                                        </h2>
                                         <div class="form-group">
                                            <c:forEach items="${req.quotationheader}" var="q">
                                               ${q.quotationCode} <br/>
                                            </c:forEach>
                                        </div>
                                        <div class="form-group">
                                            <form:textarea  readonly="true" path="reasonForRequistion" class="input-md round form-control col-lg-3" style="margin-bottom:10px;height: 44px;" placeholder="Reason for Requisition" required="required"/>
                                        </div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Purpose of Materials/Services
                                        </h2>
                                        <div class="form-group">
                                            <form:checkbox  path="purposeOfMaterialService" value="Spares/Stocks" />&nbsp;&nbsp;&nbsp;<span>Spares/Stocks</span>&nbsp;&nbsp;
                                            <form:checkbox  path="purposeOfMaterialService" value="Consumables" />&nbsp;&nbsp;&nbsp;<span>Consumables</span>&nbsp;&nbsp;
                                            <form:checkbox  path="purposeOfMaterialService" value="Tools" />&nbsp;&nbsp;&nbsp;<span>Tools</span>&nbsp;&nbsp;
                                            <form:checkbox  path="purposeOfMaterialService" value="Service Rendered" />&nbsp;&nbsp;&nbsp;<span>Service Rendered</span>&nbsp;&nbsp;
                                            <form:checkbox id="Others"  path="purposeOfMaterialService" value="Others" />&nbsp;&nbsp;<span>Others</span>&nbsp;&nbsp;
                                             <div class="oth" ><input type="text"  value="${req.othermaterial}"></input></div>
                                        </div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Summary of Quotation
                                        </h2>
                                        <p style="margin-bottom:-5px;position:relative;color:#b6b1b1">The written or formal request for quotation can be made either through facsimile or email.</p>
                                        <p style="color:#b6b1b1">* Quotation submitted must be less than 3 months from the date of RFA recieved</p>
                                        <div class="form-group brr">
                                            <span><b>Value per Requisition</b></span>
                                            <span style="float:right"><b>Minimum Sourcing Requirements</b></span>
                                            <br/>
                                            <span><input type="radio"  id="rdo01" checked="checked" style="position:relative;left:30px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<= $500</span>
                                            <span style="float:right;right:50px;position:relative">1 Written Quote</span>
                                            <br/>
                                            <span  ><input type="radio"  id="rdo02"  style="position:relative;left:30px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;> $500 & <= $1000 </span>
                                            <span style="float:right;right:50px;position:relative">2 Written Quotes</span>
                                             <br/>	
                                            <span class="" ><input type="radio"  id="rdo03"  style="position:relative;left:30px" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;> $1000 </span>
                                            <span style="float:right;right:50px;position:relative">3 Written Quotes</span>
                                            </div>
                          				
                          				   <!-- <div class="clss"  > 
                          				<br><br>  
                          				</div>    -->
                                            
                                            <div class="form-group" >
                                                <table id="myTable" >
                                                    <thead>
                                                        <tr>
                                                            <th>S No.</th>
                                                            <th>Comp. Name</th>
                                                            <th>Price Qtd(w/o GST)</th>
                                                            <th>Ref Nos.</th>
                                                            <th>Date of Quotation</th>
                                                            <th>Validity Date</th>
                                                            <th>Supporting Document</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach items="${req.quotation}" var="q" varStatus="c">
                                                    <tr>         
                                                            <td>${c.count}</td>
                                                    	    <td><input type="text" readonly="readonly" style="width:120px" value="${q.companyName}" /></td>
                                                    	    <td><input type="text" readonly="readonly" style="width:120px" value="${q.priceQuoted}" /></td>
                                                    	    <td><input type="text" readonly="readonly" style="width:120px" value="${q.refNumbers}" /></td>
                                                    	    <fmt:formatDate value="${q.dateOfQuotation}" pattern="dd-MM-yyyy"  var="qdate"/>
                                                    	    <td><input type="text" readonly="readonly" value="${qdate}" /></td>
                                                    	     <fmt:formatDate value="${q.validityDate}" pattern="dd-MM-yyyy"  var="vdate"/>
                                                    	    <td><input type="text" readonly="readonly" value="${vdate}" /></td>
                                                    	    <td ><c:url value="/requestForApproval/download" var="d"><c:param name="id" value="${q.id}"></c:param></c:url><a style=" margin-left: 43%;" href="${d}"><i class="fa fa-download"></i></a></td>
                                                    </tr>
                                                    
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                           </div>
                                             <div class="clss"  > 
                          				<br><br> <br><br> 
                          				</div>   
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40 prin" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            For chargeable Job
                                        </h2>
                                        <div class="form-group fg">
                                            <span  style="display:inline-block;position: relative;left: 14px;" > Selling Price.:</span>
                                            <form:input style="position: relative;left: 20px;" path="sellingPrice" type="text" id="sellingprice" placeholder="Selling Price (Quotation to Client)" />
                                              <span class="sp"  > Cost Price.:</span>
                                            <form:input path="costPrice"  type="text" id="costprice" placeholder="Cost Price (Supplier's Price)" style="float:right" />
                                            <br />
                                            <span style="margin-left: 375px;" class="col-md-4 perc">Profit:</span>
                                            <br />
                                        </div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40 prinh" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Waiver of Competition
                                        </h2>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40 prinh" style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Applicable
                                        </h2>
                                        <div class="form-group ">
                                            <form:checkbox  path="guidelinesAreMet" value="Yes" />&nbsp;&nbsp;&nbsp;<span>Purchasing procedure and guidelines are met</span><br />
                                            <form:checkbox  path="purchaseOfrecurrentItems"  value="Yes" />&nbsp;&nbsp;&nbsp;<span>Purchases of recurrent items at the last bought price, with prior waiver of competitive approval</span><br />
                                            <form:checkbox  path="contractualSupplyOfItems"  value="Yes" />&nbsp;&nbsp;&nbsp;<span>Contractual supply of items or services during the term of contract</span>
                                        </div>
                                        <h2 class="section-title font-alt align-left mb-70 mb-sm-40 " style="margin-bottom:20px;position:relative;border-bottom:1px dashed #808080">
                                            Not Applicable
                                        </h2>
                                        <div class="form-group ">
                                            <form:checkbox  path="soleAgentOEM"   value="Yes"/>&nbsp;&nbsp;&nbsp;<span>Sole agent O.E.M. (To attach solo Agent /O.E.M letter as supporting document)</span><br />
                                            <form:checkbox  path="timeCriticalPurchase" value="Yes" />&nbsp;&nbsp;&nbsp;<span>Time critical purchase of extreme urgency (To indicate reason for urgency under "Remarks")</span><br />
                                            <input type="checkbox" />&nbsp;&nbsp;&nbsp;<span>Others (To indicate under remarks)</span>
                                        </div>
                                        <div class="form-group ">
                                            <form:textarea path="remarks"   class="input-md round form-control col-lg-3" style="margin-bottom:10px" placeholder="Remarks" required="required"></form:textarea>
                                        </div>
                                        <div class="form-group in prinn">
                                        <p class="pp">Requestor Signature: </p>
                                    <p class="pp">    <fmt:formatDate value="${req.submittionDate }" pattern="dd-MM-yyyy"/>  </p>
                                        <c:url value="/requestForApproval/getsigniture" var="imgurl"> <c:param name="imageName" value="${req.signiture}"/></c:url>
                                        <img  alt=""  class="dashboard-tile cl"  src="${imgurl}">
                                        </div>
                                         <c:if test="${not empty req.siteApproverSig}">
                                        <div class="form-group in prinn">
                                        <p class="pp">Site Reviewer Signature: </p>
                                      <p class="pp">  <fmt:formatDate value="${req.siteApproverDate}" pattern="dd-MM-yyyy"/>  </p>
                                        <c:url value="/requestForApproval/getsigniture" var="imgurl"> <c:param name="imageName" value="${req.siteApproverSig}"/></c:url>
                                        <img   alt=""   class="dashboard-tile cl"   src="${imgurl}">
                                        </div>
                                        </c:if>
                                        <c:if test="${not empty req.costCenterSig}">
                                        <div class="form-group in prinn">
                                        <p class="pp">HQ Reviewer Signature: </p>
                                      <p class="pp">  <fmt:formatDate value="${req.costCenterdate}" pattern="dd-MM-yyyy"/>  </p>
                                        <c:url value="/requestForApproval/getsigniture" var="imgurl"> <c:param name="imageName" value="${req.costCenterSig}"/></c:url>
                                        <img   alt=""   class="dashboard-tile cl"   src="${imgurl}">
                                        </div>
                                        </c:if>
                                        <c:if test="${not empty  req.approverSig}">
                                        <div class="form-group in prinn">
                                       <p class="pp"> Approver Signature:</p>
                                    <p class="pp">     <fmt:formatDate value="${req.approverDate}" pattern="dd-MM-yyyy"/>   </p>
                                        <c:url value="/requestForApproval/getsigniture" var="imgurl"> <c:param name="imageName" value="${req.approverSig}"/></c:url>
                                        <img   alt=""  class="dashboard-tile cl"    src="${imgurl}">
                                        </div>
                                        </c:if>
                                         <c:if test="${not empty req.financerSig}">
                                        <div class="form-group in prinn">
                                       <p class="pp"> Financer Signature: </p>
                        <p class="pp">                <fmt:formatDate value="${req.financerDate}" pattern="dd-MM-yyyy"/>  </p>
                                        <c:url value="/requestForApproval/getsigniture" var="imgurl"> <c:param name="imageName" value="${req.financerSig}"/></c:url>
                                        <img  alt=""  class="dashboard-tile cl"    src="${imgurl}">
                                        </div>
                                        </c:if>
                                         <c:if test="${not empty req.adminSig}">
                                        <div class="form-group in prinn">
                                       <p class="pp"> Admin Signature: </p>
                                   <p class="pp">    <fmt:formatDate value="${req.adminDate}" pattern="dd-MM-yyyy"/>  </p>
                                       <c:url value="/requestForApproval/getsigniture" var="imgurl"> <c:param name="imageName" value="${req.adminSig}"/></c:url>
                                        <img alt=""   class="dashboard-tile cl"    src="${imgurl}">
                                        </div>
                                        </c:if>
             							</form:form>
             							<c:set value="siteApprover" var="sa"></c:set>
                                         <c:set value="costCenter" var="cc"></c:set>
                                          <c:set value="approver" var="ap"></c:set>
                                           <c:set value="PO" var="po"></c:set>
                                           <c:set value="Admin" var="ad"></c:set>
                                           
                                        <c:if test="${user == sa && req.siteApproverStatus=='Pending'}">
                                        <form action="<c:url value="/requestForApproval/updaterequestforsiteapprover"/>" method="post" enctype="multipart/form-data">
                                        <input type="hidden" name="id" value="${id}">
                                         Site Reviewer Signature
                                         <div class="form-group">
                                            <input type="file" name="siteapproversigniture">
                                            
                                        </div>
                                        <div class="form-group">
                                        <input type="submit" id="submit"  name="value" value="Accept" />
                                         <input type="submit" id="submit" name="value"  value="Reject" />
                                            
                                        </div>
                                        </form>
                                        </c:if>
                                        <c:if test="${user == cc && req.costCenterStatus=='Pending'}">
                                        <form action="<c:url value="/requestForApproval/updaterequestforcostcenter"/>" method="post" enctype="multipart/form-data">
                                        <input type="hidden" name="id" value="${id}">
                                         HQ Reviewer Signature
                                         <div class="form-group">
                                            <input type="file" name="costcentersigniture">
                                            
                                        </div>
                                        <div class="form-group">
                                        <input type="submit" id="submit"  name="value" value="Accept" />
                                         <input type="submit" id="submit" name="value"  value="Reject" />
                                            
                                        </div>
                                        </form>
                                        </c:if>
                                        <c:if test="${user == ap && req.appStatus =='Pending'}">
                                         <form action="<c:url value="/requestForApproval/updaterequestforapprover"/>" method="post"  enctype="multipart/form-data">
                                          <input type="hidden" name="id" value="${id}">
                                         Approver Signature
                                         <div class="form-group">
                                            <input type="file" name="approversigniture">
                                        </div>
                                        <div class="form-group">
                                        <input type="submit" id="submit"  name="value" value="Accept" />
                                        <input type="submit" id="submit" name="value"  value="Reject" />
                                            
                                        </div>
                                        </form>
                                        </c:if>
                                          <c:if test="${user == po && req.finStatus =='Pending'}">
                                            <form action="<c:url value="/requestForApproval/updaterequestforpo"/>" method="post"  enctype="multipart/form-data">
                                            <input type="hidden" name="id" value="${id}">
                                         PO Signature
                                         <div class="form-group">
                                            <input type="file" name="porsigniture">
                                        </div>
                                        <div class="form-group">
                                        <input type="submit" id="submit"  name="value" value="Accept" />
                                        <input type="submit" id="submit" name="value"  value="Reject" />
                                            
                                        </div>
                                        </form>
                                        </c:if>
                                         
          						  </div>
           					 </div>
            			</div>
            			
            			<div class="sig" style="text-align: center;"><small>Disclaimer:This is a Computer Generated Document. No Signature required.</small> </div>
         <script type="text/javascript">
         
         
         
         $( document ).ready(function() {
        	 var rowCount = $('#myTable >tbody >tr').length;
        	 if(rowCount == 1)
        		 $('#rdo01').prop('checked', true);
 			else if(rowCount == 2)
 				$('rdo02').prop('checked', true);
 			else
 				$('rdo03').prop('checked', true);
        	});
        
        
         
         (function(){
        	 var cp=parseFloat($('#costprice').val());
   			var sp=parseFloat($('#sellingprice').val());
   			var pro="Profit: ";
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
   				$('.perc').append(pro+profit+p);
   				}
   				}
   			
   		/* 	$('input:radio:checked')
   		 $('rdo01')
   		var isChecked = $('#rdSelect').prop('checked'); */
   	  $( document ).ready(function() {
   		 $('input:radio:not(:checked)',".brr").each(function(i) {
   			console.log($(this).parent().next().next().remove());
        	 console.log($(this).parent().next().remove()); 
		       console.log($(this).parent().remove());
		       
		       console.log("ss");
		      
   		 });
		    });  

         	 
        	})();
         
        /*  $("input[type=radio][checked]").each(function(i) {
				
			       console.log(this)
		
			    }
		
			); */
         
         
		    (function(){
		    	console.log("jbjb")
	    		var len=$('#myTable tr:visible').length-1;
	    		console.log(len)
	    		if(len==1){
	    			jQuery("#rdo01").prop('checked', true);
	    			jQuery("#rdo02").prop('checked', false);
	    			jQuery("#rdo03").prop('checked', false);
	    			
	    		}
	            if(len==2){
	            	jQuery("#rdo01").prop('checked', false);
	            	jQuery("#rdo02").prop('checked', true);
	    			jQuery("#rdo03").prop('checked', false);
	    			
	    		}
	            if(len==3){
	            	jQuery("#rdo01").prop('checked', false);
	            	jQuery("#rdo02").prop('checked', false);
	    			jQuery("#rdo03").prop('checked', true);
	    		}
		    	})(); 
		/*     $(document).on('change', 'input:radio:checked',".brr", function(e){
	    		console.log("jbjb")
	    		var len=$('#quotationtable tr:visible').length-1;
	    		console.log(len)
	    		if(len==1){
	    			jQuery("#rdo01").prop('checked', true);
	    			jQuery("#rdo02").prop('checked', false);
	    			jQuery("#rdo03").prop('checked', false);
	    			
	    		}
	            if(len==2){
	            	jQuery("#rdo01").prop('checked', false);
	            	jQuery("#rdo02").prop('checked', true);
	    			jQuery("#rdo03").prop('checked', false);
	    			
	    		}
	            if(len==3){
	            	jQuery("#rdo01").prop('checked', false);
	            	jQuery("#rdo02").prop('checked', false);
	    			jQuery("#rdo03").prop('checked', true);
	    		}
	    	}); */
		    
	    	/*  $('#others').change(function() {
	    		 isCheckedC = $(this).prop('checked')
	    		 if(isCheckedC){
	    			 $(".oth").show();
	    		 } 
	    		 else{
	    			 $(".oth").hide();
	    		 }
	    	  
	    	});  */
	    	
	    	(function(){
	    		
	    		/*  $('#others').trigger("change"); */
	    		
	    		 isCheckedC = $('#Others').prop('checked');
	    		 
	    		 if(isCheckedC){
	    			 $(".oth").show();
	    		 } 
	    		 else{
	    			 $(".oth").hide();
	    		 }
	    		})();
         
         </script>   

</html>
