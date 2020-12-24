
$(document).ready(function(){
$('#building').on('change', function() {

     var u=$("#contextPath").val();
      var value = $(this).val();    
    	 // alert("In" + $(this).val() );
      url=u+"/mechanical/"+value;
  
      $.getJSON(url,{id:$(this).val()},function(data){
    	
    	  
       JSON.stringify(data);
    
    	  var add=$("#location");
    
    	  add.find('option').remove();
    	  

    	  $('#location').append('<option  value="-1" >SELECT Location </option>');
    	  
    	  for(var i=0;i<data.length;i++){
    	 
    		$("#location").append('<option value="' + data[i].id+ '">' + data[i].name+ '</option>');
          
    	  }
  
    	
    	 
        });

     
         
         
    });
});   
