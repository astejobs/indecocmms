  

$(document).ready(function(){
	
	
		  $("#building").change(function(){
		      var id = $("#building").val();
		      var context = $("#context").val();	    
		      var url = context+"/equipment/locations/"+id;
		      $("#location").empty();
		     
		      $("#location").append("<option value=''>Select Location</option>");
		           $.get(url,function(data){
			        	for(i=0;i<data.length;i++){
			          	  $("#location").append("<option value="+data[i].id+">"+data[i].name+"</option>");
			             } 
		      	  }); 
		           
		           if(id!=""&& $("#location").val()!="")
		        	   $("#selectEquip").removeAttr("disabled");
		        	   
		 });
		  
		 
		  
		  
		  $("#assetType").change(function(){
		      var id = $("#assetType").val();
		      var context = $("#context").val();
			  $("#assetSubtype").empty();
	    	  $("#assetSubtype").append("<option value=''>Select Equipment SubType</option>");
		      var url = context+"/faultReport/assetSubtype/"+id;
		           $.get(url,function(data){
			        	for(i=0;i<data.length;i++){
			          	  $("#assetSubtype").append("<option value="+data[i].id+">"+data[i].assetSubTypeName+"</option>");
			             } 
		      	  }); 
		 });
		  
		  
		 $("#location,#building").change(function(){
			
			 var locationId=$("#location").val();
			 var context = $("#context").val();
			 var buildingId = $("#building").val();
			 $("#equipment").empty();
		/*	 var url = context+"/equipment/building/"+buildingId+"/location/"+locationId;
			 $("#equipment").empty();
	    	  $("#equipment").append("<option value=''>Select Equipment</option>");
	    	  $.get(url,function(data){
		        	for(i=0;i<data.length;i++){
		          	  $("#equipment").append("<option value="+data[i].id+">"+data[i].name+"</option>");
		             } 
	      	  }); 
	      	  
	      	  */
	    	  if(buildingId!="" && locationId!="")
	        	   $("#selectEquip").removeAttr("disabled");
	    	  else
	    		  $("#selectEquip").attr("disabled", "disabled");
        	   
		 })
	});