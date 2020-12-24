

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/projectjs/EqCr.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/datetimepicker_css.js"></script>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/assets/js/jquery.ajaxchimp.min.js"></script>
 

<style>#prop input[type="text"]{border:1px dotted #000000;margin-bottom:5px;margin-left:5px;}  tr td {
      padding-top:10px;
 }
 
 </style>


 <body class="appear-animate">
 

        <jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Equipment Create</li>
			  </ol>
			</nav>
			
        <div class="container">
<div class="row">
        <div class="col-md-12">
        
        <div class="col-md-10 jumbotron offset-box">
        
		                 <h2 class="section-title font-alt align-left mb-70 mb-sm-40" >
		                   Create Equipment
		                   <c:url value="/equipment/add" var="create"/>
		                 </h2>
		                 <c:if test="${not empty success}">
                             <div class="success">${success}</div>
						</c:if>
						<c:if test="${not empty fail}">
                             <div class="error">${fail}</div>
                        </c:if>
			       			<form:form action="${create}" commandName="equipment" method="POST"  class="form contact-form" id="contact_form"  enctype="multipart/form-data">
		                                <div class="clearfix">
		                                    <div>
		                                    
		                                        <div class="form-group input-group">
                                        			<span class="input-group-addon label-left" id="basic-addon2">Asset Type</span>
		                                            <form:select  data-bvalidator="required" path="assetType"  id="eId"   class="input-md round form-control"  >
		                                               <form:option value=""> Select Asset Type:</form:option>
		                                               <c:forEach items="${listSystem}" var="equip">
											            <form:option value="${equip.id}">${equip.assetTypeName}</form:option>
										                 </c:forEach>
	                                           		 </form:select> 
	                                            </div> 
                                            	<div class="form-group input-group">
                                        			<span class="input-group-addon label-left" id="basic-addon2">Asset Sub type</span>
		                                        	 <form:select  id="sId"  path="assetSubtype" class="input-md round form-control" >
		                                               <form:option value="">Asset Sub Type</form:option>
		                                               <form:options items="${subSystems}" itemLabel="assetSubTypeName"
													itemValue="id" />
		                                            </form:select><form:errors path="assetSubtype" class="err"/>
		                                        </div>
		                                      
		                                        <div class="form-group input-group">
                                        			<span class="input-group-addon label-left" id="basic-addon2">Equipment Name</span>
		                                            <form:input type="text" path="name" class="input-md round form-control" placeholder="Equipment Name" /><form:errors path="name" class="err"/>
		                                        </div>
		                                        <input type="hidden" name="id" value="${acmv.id}"/>
		                                        <form:input type="hidden" path="workspace"/>
		                                        <c:if test="${not empty edit}">
		                                        <div>
		                                        <form:input path="equipmentCode" readOnly="true"/>
		                                        </div>
		                                        </c:if>
		                                        <div class="form-group input-group">
                                        			<span class="input-group-addon label-left" id="basic-addon2">Select Building</span>
		                                            <form:select path="building" id="bId" class="input-md round form-control" >
		                                               <form:option value="">  Building</form:option>
		                                               <c:forEach items="${buildingList}" var="building">
													<form:option value="${building.id}">${building.name}</form:option>
										                 </c:forEach>
		                                            </form:select><form:errors path="building" class="err"/>
		                                        </div>
		                                        <div class="form-group input-group">
                                        			<span class="input-group-addon label-left" id="basic-addon2">Select Location</span>
		                                            <form:select  path="location" id="loc" class="input-md round form-control" placeholder="Location">
		                                              <form:option value="">Select any  Location</form:option>
		                                               <form:options items="${locationList}" itemLabel="name" itemValue="id"/>
		                                            </form:select><form:errors path="location" class="err"/>
		                                        </div>
		                                      
		                                          <div class="form-group input-group">
                                        			<span class="input-group-addon label-left" id="basic-addon2">Date of Inspection</span>
                                            <form:input type="text"  path="dateOfInspection" id="installationDate"
                                            class="input-md round form-control" placeholder="Date of Inspection" style="width:95%"/> 
                                            <img src="${pageContext.request.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('installationDate','ddMMyyyy')" style="cursor:pointer;width:30px" />
                                        </div>
		                                        
		                                 <div class="form-group input-group">
                                        			<span class="input-group-addon label-left" id="basic-addon2">Model</span>
                                             <form:input type="text"   path ="modelNo"                                           
                                              class="input-md round form-control" placeholder="Model" /> 
                                              <form:errors path="modelNo" class="err"/>
                                    	</div>
                                        
                                     <div class="form-group input-group">
                                        			<span class="input-group-addon label-left" id="basic-addon2">Serial No.</span>
                                            <form:input type="text"  path="serialNo" class="input-md round form-control"
                                             placeholder="Serial No." /> 
                                             <form:errors path="serialNo" class="err"/>
                                             
                                     </div>
                                     
                                      <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Capacity</span>
                                            <form:input type="text" path="capacity"
                                             class="input-md round form-control" placeholder="Capacity" /> 
                                             <form:errors path="capacity" class="err"/>
                                      </div>
                                      <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Contract Quantity</span>
                                            <form:input type="number" min="0" path="contractQty"
                                             class="input-md round form-control" placeholder="Contract Quantity" /> 
                                             <form:errors path="contractQty" class="err"/>
                                        </div>
                                        
                                         <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Site Quantity</span>
                                            <form:input type="number" min="0" path="siteQty"
                                             class="input-md round form-control" placeholder="Site Quantity" /> 
                                             <form:errors path="siteQty" class="err"/>
                                      </div>
                                      <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Unit Of Measurement</span>
                                            <form:input type="text" path="unitOfMeasurement"
                                             class="input-md round form-control" placeholder="Unit Of Measurement" /> 
                                             <form:errors path="unitOfMeasurement" class="err"/>
                                        </div>
                                        
                                           <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Re-Certification On</span>
                                            <form:input type="text"  path="reCertificatedOn" id="reCertification_Date"
                                            class="input-md round form-control" placeholder="Re-Certification On" style="width:95%"/> 
                                            <img src="${pageContext.request.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('reCertification_Date','ddMMyyyy')" style="cursor:pointer;width:30px" />
                                       <form:errors path="reCertificatedOn" class="err"/>
                                        </div>
                                           
                                            
                                        
                                        
                                           <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">No Of Extinguishers</span>
                                            <form:input type="text" path="noOfExtinguishers"
                                             class="input-md round form-control" placeholder="No Of Extinguishers" /> 
                                             <form:errors path="noOfExtinguishers" class="err"/>
                                        </div>
                                        
                                            <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Expiry Month</span>
                                            <form:input type="text"  path="monthOfExpiry" id="monthOfExpiry_Date"
                                            class="input-md round form-control" placeholder="Month_of_Expiry" style="width:95%"/> 
                                            <img src="${pageContext.request.contextPath}/resources/assets/date/cal.png" onclick="javascript: NewCssCal('monthOfExpiry_Date','ddMMyyyy')" style="cursor:pointer;width:30px" />
                                       <form:errors path="monthOfExpiry" class="err"/>
                                        </div>
                                             
                                        
                                        
                                           <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Quantity</span>
                                            <form:input type="text" path="quantity"
                                             class="input-md round form-control" placeholder="Quantity" /> 
                                             <form:errors path="quantity" class="err"/>
                                        </div>
                                        
                                           <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Description</span>
                                            <form:input type="text" path="description"
                                             class="input-md round form-control" placeholder="Description" /> 
                                             <form:errors path="description" class="err"/>
                                        </div>
                                        
                                           <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Brand</span>
                                            <form:input type="text" path="brand"
                                             class="input-md round form-control" placeholder="Brand" /> 
                                             <form:errors path="brand" class="err"/>
                                        </div>
                                        
                                         <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">FCU Model</span>
                                            <form:input type="text" path="fcuModel"
                                             class="input-md round form-control" placeholder="FCU_Model" /> 
                                             <form:errors path="fcuModel" class="err"/>
                                        </div>
                                        
                                       
                                        
                                         <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">CU Model</span>
                                            <form:input type="text" path="cuModel"
                                             class="input-md round form-control" placeholder="CU Model" /> 
                                             <form:errors path="cuModel" class="err"/>
                                        </div>
                                        
                                         <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Rating</span>
                                            <form:input type="text" path="rating"
                                             class="input-md round form-control" placeholder="Rating" /> 
                                             <form:errors path="rating" class="err"/>
                                        </div>
                                        
                                        
		                                    <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Remarks</span>
                                            <form:textarea  path="remarks" class="input-md round form-control" placeholder="Remarks"/>
                                        </div>    
		                                        <div class="clearfix"></div>
                                       <div class="form-group">
										<div id="dynamicFields">


											<!-- dynamic content goes here... -->

										</div>
                                       </div>
 
		                          </div>
		                       </div>
                                    <div class="form-group float-right">
                                            <input type="submit" id="submit" class="btn" value="Add" />
                                     </div>
		             </form:form>
		        </div>
            </div>
       </div> 
      </div>  
              
</body>


<script id="dyamicField">
{{#items}}
<div class="example-container" style="display: block; float: left; width: 800px;">

{{#elements}}

<label  class="grey" style="width: 150px; float: left; display: block; clear: none;">{{value}}</label>
<div style="width: 25%; float: left; display: block;">
<input type="text" value="" style="width: 90%;" data-bvalidator="required" name="listData[{{index}}].value" id="operatingWeight">
<input type="hidden" value='{{value}}' name="listData[{{index}}].labelName"/>
</div>





{{/elements}}
	</div>
	<div class="clearfix"></div>
{{/items}}




</script>
 
<script type="text/javascript">
$(document).ready(function() 
		{
$( "#sId" ).change(function() {
	
	
	
	     var id = $(this).val();
	   
	     
	   
	  var url = "${pageContext.request.contextPath}/addproperty/getsubtype/"+id;
	 
	  
      $.get(url, function(data){
    	
    	 
         
    	  var objArray = []; 
    	  
    	  var nestedArray=[]
   $.each(data,function(key,value){
	   var f;
	  if(key%2==0)
		  f="left";
		  else
			  f="right";
	   
	   objArray.push({index:key,value:value.value ,"float" : f});
	   
	  
	   
   })      
	  
   
   while(objArray.length > 0){
	   
	   nestedArray.push({"elements" : objArray.splice(0,2)})
	   
   }
   
    	  
    	  console.log("new array after tranformation is " + JSON.stringify(nestedArray))
   var view     = {items: nestedArray};
  
   
  var template= $('#dyamicField').html();
   
  var html=Mustache.to_html(template,view);
  
 
      $('#dynamicFields').html(html);
  
});
});        
});
    
</script>

 
 <script type="text/javascript">
$(document).ready(function() 
		{$( "#eId" ).change(function() {
	
	     var id = $(this).val();
	   
	     
	   
	  var url = "${pageContext.request.contextPath}/addproperty/getsubsystems/"+id;
	  
	  
      $.get(url, function(data){
         
    	 
         $('#sId').empty();
          $('#sId').append('<option  value="-1" >Select sub System</option>');
              $.each(data,function(k,v){
              var option = $('<option/>');
              option.attr({ 'value': v.id }).text(v.assetSubTypeName);
              $('#sId').append(option);
              
              }); 
	      
});
});        
});
    
</script>
 
 
 
<script type="text/javascript">
$(document).ready(function() {
	$( "#bId" ).change(function() {
		
	     var id = $(this).val();
	     var url = "${pageContext.request.contextPath}/equipment/locations/"+id;
		 $.get(url, function(data){
	      $('#loc').empty();
	          $('#loc').append('<option  value="-1" >Select Location</option>');
	              $.each(data,function(k,v){
		              var option = $('<option/>');
		              option.attr({ 'value': v.id }).text(v.name);
		              $('#loc').append(option);
	              }); 
		  });
	});
	 $("#addprop").click(function () {
         $("#prop").append('<tr><th><input type="text" /></th><th><input type="text" class="code" /></th><th><input type="text" class="code"/></th><th><input type="text" class="code"/></th></td> <td style="padding-left:10px;position:relative"><a href="javascript:void(0);" id="remCF" style="color:black"><img style="width:20px;height:20px;" src="../assets/images/trash..png" /></a></td></tr>');
     });

     $("#prop").on('click', '#remCF', function () {
         $(this).parent().parent().remove();
     });

});
    
</script>
