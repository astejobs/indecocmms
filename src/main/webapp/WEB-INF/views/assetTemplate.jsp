<script id="dyamicField">

{{#items}}


<tr><td><input required="true" type="text"  class="baselineName" name="listFieldDes[{{index}}].value" value="{{value}}" style="width:90%" /></td><td><a  class='btn-remove-tr' style='color:black;'><i class="fa fa-trash"></i></a></td</tr>
{{/items}}

</script>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>



<script type="text/javascript">

var fsg;
function myFunction() {
	
	
	
    var c = document.getElementById("prop").children.length;
   c=c-1;
  
    document.getElementById("demo").value = c;
    fsg=c;
   
}
    

	$(document).ready(function () {
        
        

		var counter=0;
		
		                                           
		                                          $("#add_prop").click(function () {
		                                        	  if(fsg>counter)
		                                        		  {
		                                        	   counter=fsg;
		                                        	 
		                                        		  }
		                                        	  else
		                                        		  {
		                                        	  counter= document.getElementById('prop').rows.length;
		                                        	  counter=counter-1;
		                                        	  
		                                        		  }
		                                        			
		                                        			
		                                        	  
		                                                
		      
		        $("#prop").append('<tr><td><input  required="true" type="text" class="baselineName"  style="width: 90%;"  name="listFieldDes['+((counter)+1)+'].value"></td><td><a class="btn-remove-tr" style="color:black"><i class="fa fa-trash"> </i></a></td></tr>	');

		                                            });

		                                            $('#prop').on('click', '.btn-remove-tr', function (e) {
		                                                e.preventDefault();
		                                                if ($('#prop tr').length > 1) {
		                                                    $(this).closest('tr').remove();
		                                                    $('input[class="baselineName"]').attr("name", function (i) {
		                                                       var getResult = (i+1)-1;
		                                                      return ("listFieldDes["+getResult+"].value");
		                                                    });
		                                                    
		                                                }
		                                                return false;
		                                             });
	}) 

</script>
<script type="text/javascript">
$(document).ready(function() 
		{
$( "#loc" ).change(function() {
	
	
	
	     var id = $(this).val();
	   
	     
	   
	  var url = "${pageContext.request.contextPath}/addproperty/getsubtype/"+id;
	  
	  
      $.get(url, function(data){
    	
    	 
         
    	  var objArray = []; 
   $.each(data,function(key,value){
	   
	  
	   
	   objArray.push({index:key,value:value.value});
	   
	  
	   
   })      
	  
   var view     = {items: objArray};
  
   
  var template= $('#dyamicField').html();
   
  var html=Mustache.to_html(template,view);
  
 
      $('#prop').html(html);
  
});
});        
});
    
</script>


<script type="text/javascript">
$(document).ready(function() 
		{$( "#bId" ).change(function() {
	
	     var id = $(this).val();
	   
	     
	   
	  var url = "${pageContext.request.contextPath}/addproperty/getsubsystems/"+id;
	  
	  
      $.get(url, function(data){
         
    	 
         $('#loc').empty();
          $('#loc').append('<option  value="-1" >Select sub System</option>');
              $.each(data,function(k,v){
              var option = $('<option/>');
              option.attr({ 'value': v.id }).text(v.assetSubTypeName);
              $('#loc').append(option);
              
              }); 
	  
    
	  
      
});
});        
});
    
</script>



<body class="appear-animate">

  
        <!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Asset Template</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">
                                  <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                        Template Details
                    </h2>

                    <!-- Contact Form -->
                    
                         <c:url value="/addproperty/add" var="create"/>
                            <form:form class="form contact-form"  action="${create}"  commandName="dynamicFieldsOfEquiment" method="POST" >
                                <div class="form-group input-group">
	                                      	<span class="input-group-addon label-left" id="basic-addon2">Select Equipment</span>
										 	<select class="input-md round form-control"  data-bvalidator="required"  id="bId"   class="input-md round form-control"  >
                                               <option value=""> Select Equipment:</option>
                                               <c:forEach items="${listSystem}" var="equip">
									            <option value="${equip.id}">${equip.assetTypeName}</option>
								               </c:forEach>
                                            </select>
                                            
                                    </div>    
                                    <div class="form-group input-group">
                                        <span class="input-group-addon label-left" id="basic-addon2">Asset Sub Type</span>                          
										<form:select class="input-md round form-control" path="assetSubType" id="loc">
	                                        <form:option value="">Select Asset Sub Type</form:option>
	                                    </form:select>
                                </div>
                                
                                
                                 <table id="prop" style="width:70%;position:relative">
                            <thead>
                            	<tr>
                            	<th></th>
                            	<th></th>
                            	
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                            	<td></td>
                            	<td></td>
                            </tr>
                            </tbody>
                            </table>



                    <div id='TextBoxesGroup'>
	               <div id="TextBoxDiv1">
		
	               </div>
                    </div>

                        <input type="hidden" id="demo" value=""/>
                                
                                 <div class="form-group float-right">
	                                 <input type="submit" name="submit" class="btn" value="Add Template">
										&nbsp;&nbsp;&nbsp;&nbsp;
	                      
	                        		 <input type="button" value="Add" id="add_prop" class="btn" onclick="myFunction()"/>
                                </div>
                            </form:form>
                      
                    <!-- End Contact Form -->
                </div>
          
        </div>
   </div>
   </div>
  
        <!--End Form-->
        <!-- Foter -->
       


