$(document).ready(function() {
	
var context=$("#contextPath").val();

$( "#bId" ).change(function() {

	
      var id = $(this).val();
	 
	  var url = context+"/fireEquipment/locationlist/"+id;
	 
	  
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


});
    

