$(document).ready(function(){
    
$('#EqType').on('change', function() {
var url=$("#contextPath").val();
      var value = $(this).val();    
 
      url=url+"/SOR/"+value;
      
      $.getJSON(url,{id:$(this).val()},function(data){
    	   var add=$("#EqName");
           add.find('option').remove();
    	   $('#EqName').append('<option  value="" >SELECT Equipment </option>');
    	   for(var i=0;i<data.length;i++){
    	       $("#EqName").append('<option value="' + data[i].name+ '">' + data[i].name+ '</option>');
          
    	  }
      });
});
   
 



$('#AssetType').on('change', function() {

	var url=$("#contextPath").val();
	      var value = $(this).val();    
	
	      url=url+"/SOR/assetType/"+value;
	     
	      $.getJSON(url,{id:$(this).val()},function(data){
	    	  
	    	  var eqtype=$("#EqName");
	    	    
	    	  eqtype.find('option').remove();
	    	  
	    	  var add=$("#EqType");
	    
	    	  add.find('option').remove();
	    	


	    	  $('#EqType').append('<option  value="-1" >SELECT Asset Sub Type </option>');


	    	  $('#EqName').append('<option  value="-1" >SELECT Equipment </option>');
	    	  for(var i=0;i<data.length;i++){
	    		$("#EqType").append('<option value="' + data[i].id+ '">' + data[i].assetSubTypeName+ '</option>');
	          
	    	  }
	  
	    	
	    	 
	        });

	     
	         
	         
	    });
	   
	 

	});
	    




    

