
	  $(document).ready(function(){
		  
		  var context=$("#contextPath").val();
		  
		 $("#AssetType").change(function(){
			
				var id = $(this).val();
			  
				var url = context+"/asset/assetSubtype/"+id;
				
				$.get(url, function(data){
					
					$('#assetSubType').empty();
					$('#assetSubType').append('<option  value="-1" >Select Asset SubType</option>');
						$.each(data,function(k,v){
						var option = $('<option/>');
						option.attr({ 'value': v.id }).text(v.assetSubTypeName);
						$('#assetSubType').append(option);
						
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
	