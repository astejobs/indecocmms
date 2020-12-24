$("#bId").change(function() {
	console.log("goood")
    var context=$("#contextPath").val();
    var id = $(this).val();
    var url = context+"/electrical/locationlist/"+id;
	  
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
	



