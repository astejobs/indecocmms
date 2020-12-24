
	  $(document).ready(function(){
		  
		  var context=$("#contextPath").val();
		  
		 $("#AssetSubtype").change(function(){
				
				var id = $(this).val();
			  
				var url = context+"/civil/civilLevel3/"+id;
				
				$.get(url, function(data){
			       
					$('#civil3').empty();
					$('#civil3').append('<option  value="-1" >SELECT CIVIL LEVEL3</option>');
						$.each(data,function(k,v){
						var option = $('<option/>');
						option.attr({ 'value': v.id }).text(v.civilLevel3Name);
						$('#civil3').append(option);
						
						});
			});
		 });	
		 
		 $("#civil3").change(function(){
				
				var id = $(this).val();
				var url = context+"/civil/civilLevel4/"+id;
				$.get(url, function(data){
			       
					$('#civil4').empty();
					$('#civil4').append('<option  value="-1" >SELECT CIVIL LEVEL4</option>');
						$.each(data,function(k,v){
						var option = $('<option/>');
						option.attr({ 'value': v.id }).text(v.civilLevel4Name);
						$('#civil4').append(option);
						
						});
			});
		 });	
		 
		 
		 $("#Building").change(function(){
				
				var id = $(this).val();
				var url = context+"/civil/locations/"+id;
				$.get(url, function(data){
					
					$('#Location').empty();
					$('#Location').append('<option  value="-1" >SELECT LOCATION</option>');
						$.each(data,function(k,v){
							
						var option = $('<option/>');
						option.attr({ 'value': v.id }).text(v.name);
						$('#Location').append(option);
						
						});
			});
		 });
		 
		 
	 });
	