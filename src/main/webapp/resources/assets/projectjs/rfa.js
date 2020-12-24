

$(document).ready(function () {
	 
	 
	 $('#no1').hide();
	 $('#no2').hide();
	 $('#no3').hide();
	 $('#no1').hide('fast');
	    $("#urgentwork").hide();
	    $("#leadtime").hide();
	    $(".visibility").hide();
	 var rows=$('#quotationtable tr:visible').length-1;
	 
	 if(rows==1) 
		   {
		     $('#rdo01').prop('checked', true);
		    
		   }
	   else if(rows==2)
		   {
		     
		     $('#rdo02').prop('checked', true);
		    
		   }
	   else if(rows==3)
		   {
		     
		     $('#rdo03').prop('checked', true);
		   
		    
		   }
	  if($('#urgentwork').val()=='')
		  $("#urgentworkno").prop('checked', true);
	  else
		  {
		  $("#urgentwork").show();
		  $("#urgentworkyes").prop('checked', true);
		  }
	  
	  

	    $('#no1').hide('fast');
	    $("#urgentwork").hide();
	    $("#im").hide();
	    $("#leadtime").hide();
	    $(".visibility").hide();


	    $("#urgentworkyes").click(function () {
	        $("#urgentwork").show();
	        $("#im").show();
	    });
	    $("#urgentworkno").click(function () {
	        $("#urgentwork").hide();
	        $("#im").hide();
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
	    
	    $('#rdo01').change(function () {
	    	
	    	$('#rdo02').prop('checked', false);
 		    $('#rdo03').prop('checked', false);
            $('#001').attr('name', 'quotation[0].companyName');
            $('#001a').attr('name', 'quotation[0].priceQuoted');
            $('#001b').attr('name', 'quotation[0].refNumbers');
            $('.001c').attr('name', 'quotation[0].dateOfQuotation');
            $('.001d').attr('name', 'quotation[0].validityDate');
            $('#001e').attr('name', 'supportingdoc');
            $('#no1').show('fast');
            $('#no2').hide('fast');
            
            $('#002').removeAttr('name');
            $('#002a').removeAttr('name');
            $('#002b').removeAttr('name');
            $('.002c').removeAttr('name');
            $('.002d').removeAttr('name');
            $('#002e').removeAttr('name');
            $('#no3').hide('fast');
            $('#003').removeAttr('name');
            $('#003a').removeAttr('name');
            $('#003b').removeAttr('name');
            $('.003c').removeAttr('name');
            $('.003d').removeAttr('name');
            $('#003e').removeAttr('name');
          
            $('.001c').datepicker({ dateFormat: 'dd-mm-yy' });
            $('.001d').datepicker({ dateFormat: 'dd-mm-yy' });
           

        });
 	   
        $('#rdo02').change(function () {
        	
    	    var rowrd02=$('#quotationtable tr:visible').length-1;
        	if(rowrd02==1)
        		{
        		 $('#rdo01').prop('checked', false);
     		     $('#rdo03').prop('checked', false);
        		 $('#002').attr('name', 'quotation[1].companyName');
                 $('#002a').attr('name', 'quotation[1].priceQuoted');
                 $('#002b').attr('name', 'quotation[1].refNumbers');
                 $('.002c').attr('name', 'quotation[1].dateOfQuotation');
                 $('.002d').attr('name', 'quotation[1].validityDate');
                 $('#002e').attr('name', 'supportingdoc');
                 $('#no2').show('fast');
                 $('#003').removeAttr('name');
                 $('#003a').removeAttr('name');
                 $('#003b').removeAttr('name');
                 $('.003c').removeAttr('name');
                 $('.003d').removeAttr('name');
                 $('#003e').removeAttr('name');
                 return;
        		
        		}
     	    $('#rdo01').prop('checked', false);
 		    $('#rdo03').prop('checked', false);
 		    $('#001').attr('name', 'quotation[0].companyName');
            $('#001a').attr('name', 'quotation[0].priceQuoted');
            $('#001b').attr('name', 'quotation[0].refNumbers');
            $('.001c').attr('name', 'quotation[0].dateOfQuotation');
            $('.001d').attr('name', 'quotation[0].validityDate');
            $('#001e').attr('name', 'supportingdoc');
            $('#002').attr('name', 'quotation[1].companyName');
            $('#002a').attr('name', 'quotation[1].priceQuoted');
            $('#002b').attr('name', 'quotation[1].refNumbers');
            $('.002c').attr('name', 'quotation[1].dateOfQuotation');
            $('.002d').attr('name', 'quotation[1].validityDate');
            $('#002e').attr('name', 'supportingdoc');
            $('#no1').show('fast');
            $('#no2').show('fast');
            $('#no3').hide('fast');
            $('#003').removeAttr('name');
            $('#003a').removeAttr('name');
            $('#003b').removeAttr('name');
            $('.003c').removeAttr('name');
            $('.003d').removeAttr('name');
            $('#003e').removeAttr('name');
            $('.003c').datepicker({ dateFormat: 'dd-mm-yy' });
            $('.003d').datepicker({ dateFormat: 'dd-mm-yy' });
            $('.002c').datepicker({ dateFormat: 'dd-mm-yy' });
            $('.002d').datepicker({ dateFormat: 'dd-mm-yy' });

        });

        $('#rdo03').change(function () {
        	
    	    var rowrd03=$('#quotationtable tr:visible').length-1;
        	if(rowrd03==1)
    		{
        	$('#rdo01').prop('checked', false);
       		$('#rdo02').prop('checked', false);
        	$('#002').attr('name', 'quotation[1].companyName');
            $('#002a').attr('name', 'quotation[1].priceQuoted');
            $('#002b').attr('name', 'quotation[1].refNumbers');
            $('.002c').attr('name', 'quotation[1].dateOfQuotation');
            $('.002d').attr('name', 'quotation[1].validityDate');
            $('#002e').attr('name', 'supportingdoc');	
    		$('#003').attr('name', 'quotation[2].companyName');
            $('#003a').attr('name', 'quotation[2].priceQuoted');
            $('#003b').attr('name', 'quotation[2].refNumbers');
            $('.003c').attr('name', 'quotation[2].dateOfQuotation');
            $('.003d').attr('name', 'quotation[2].validityDate');
            $('#003e').attr('name', 'supportingdoc');
            $('#no2').show('fast');
            $('#no3').show('fast');
             return;
    		
    		}
        	if(rowrd03==2)
        		{
        		$('#rdo01').prop('checked', false);
       		    $('#rdo02').prop('checked', false);
        		$('#003').attr('name', 'quotation[2].companyName');
                $('#003a').attr('name', 'quotation[2].priceQuoted');
                $('#003b').attr('name', 'quotation[2].refNumbers');
                $('.003c').attr('name', 'quotation[2].dateOfQuotation');
                $('.003d').attr('name', 'quotation[2].validityDate');
                $('#003e').attr('name', 'supportingdoc');
                $('#no3').show('fast');
                 return;
        		
        		}
     	    $('#rdo01').prop('checked', false);
 		    $('#rdo02').prop('checked', false);
 		    $('#001').attr('name', 'quotation[0].companyName');
            $('#001a').attr('name', 'quotation[0].priceQuoted');
            $('#001b').attr('name', 'quotation[0].refNumbers');
            $('.001c').attr('name', 'quotation[0].dateOfQuotation');
            $('.001d').attr('name', 'quotation[0].validityDate');
            $('#001e').attr('name', 'supportingdoc');
            $('#002').attr('name', 'quotation[1].companyName');
            $('#002a').attr('name', 'quotation[1].priceQuoted');
            $('#002b').attr('name', 'quotation[1].refNumbers');
            $('.002c').attr('name', 'quotation[1].dateOfQuotation');
            $('.002d').attr('name', 'quotation[1].validityDate');
            $('#002e').attr('name', 'supportingdoc');
            $('#003').attr('name', 'quotation[2].companyName');
            $('#003a').attr('name', 'quotation[2].priceQuoted');
            $('#003b').attr('name', 'quotation[2].refNumbers');
            $('.003c').attr('name', 'quotation[2].dateOfQuotation');
            $('.003d').attr('name', 'quotation[2].validityDate');
            $('#003e').attr('name', 'supportingdoc');
            $('#no1').show('fast');
            $('#no2').show('fast');
            $('#no3').show('fast');
            $('.003c').datepicker({ dateFormat: 'dd-mm-yy' });
            $('.003d').datepicker({ dateFormat: 'dd-mm-yy' });



        });

	    
	    $("#contact_form").validate({
	        rules: {
	        	refnumber: "required",
	        	jobSite:"required",
	        	'jobChargable': {
	                required: true
	               
	            },
	           'urgentwork':{
	        	  required :true
	            },
	            'partialDelivery':{
		        	  required :true
		        },
		        'partialPaymentRequired':{
		        	  required :true
	            
	        	},
	        	'paymentTerm':{
		        	  required :true
	            
	        	},
	        	'quotationheader':{
		        	  required :true
	            
	        	},
	        	'reasonForRequistion':{
		        	  required :true
	            
	        	},
	        	'purposeOfMaterialService':{
		        	  required :true
	            
	        	},
	        	'quotation[0].companyName':{
		        	  required :true
	            
	        	},
	        	'quotation[0].priceQuoted':{
		        	  required :true
	            
	        	},
	        	'quotation[0].refNumbers':{
		        	  required :true
	            
	        	},
	        	'quotation[0].dateOfQuotation':{
		        	  required :true
	            
	        	},
	        	'quotation[0].validityDate':{
		        	  required :true
	            
	        	},
	        	'quotation[1].companyName':{
		        	  required :true
	            
	        	},
	        	'quotation[1].priceQuoted':{
		        	  required :true
	            
	        	},
	        	'quotation[1].refNumbers':{
		        	  required :true
	            
	        	},
	        	'quotation[1].dateOfQuotation':{
		        	  required :true
	            
	        	},
	        	'quotation[1].validityDate':{
		        	  required :true
	            
	        	},
	        	'quotation[2].companyName':{
		        	  required :true
	            
	        	},
	        	'quotation[2].priceQuoted':{
		        	  required :true
	            
	        	},
	        	'quotation[2].refNumbers':{
		        	  required :true
	            
	        	},
	        	'quotation[2].dateOfQuotation':{
		        	  required :true
	            
	        	},
	        	'quotation[2].validityDate':{
		        	  required :true
	            
	        	},
	        	
	        	'sellingPrice':{
		        	  required :true
	            
	        	},
	        	'supportingdoc':{
		        	  required :true
	            
	        	},
	        	'costPrice':{
		        	  required :true
	            
	        	},
	        	'remarks':{
		        	  required :true
	            
	        	},
	        	'requestorsigniture':{
		        	  required :true
	            
	        	},
	        	'poNumber':{
		        	required :true
	        	},
	        	'poDoc':{
	        		required :true
	        	}
	        	
	        	
	         },
	         errorElement:"span",
	         errorClass: 'newError'

	    });
	    
	    $(".form").submit(function(e) {
	   var chck =$('.require-one');
	   var chckk =$('.require-two');
	   console.log(chck);
	   console.log(chck.filter(':checked').length);
	   if(chck.filter(':checked').length>0 || chckk.filter(':checked').length>0){
		  /* $('input[type=checkbox]','.require-two').each(function () {
			   $(this).prop('checked', false);
		   });*/
		   $('.appl').empty();
		   return true;
	   }
	   else{
		  /* alert("VGHVHVHG");*/
		   $('.appl').empty();
		   $('.appl').append("Enter one checkbox among the Applicable Or not Applicable");
		   return false;
	   }
	   
	   /*if(chckk.filter(':checked').length>0){
		   $('input[type=checkbox]','.require-one').each(function () {
			   $(this).prop('checked', false);
		   });
		   return true;
	   }
	   else{
		   alert("two");
		   return false;
	   }*/
	    });
	   /* $.validator.addMethod('.require-one', function (value) {
	    	alert(chck.filter(':checked').length);
	        return (chck.filter(':checked').length > 0);
	    }, 'Check at least one checkbox');*/
	    
	   
	    $(document).on('change','.require-one,.require-two',function(){
	    	
	    	if($('.require-one').filter(':checked').length>0){
	    	/*	console.log($('.require-one').filter(':checked').length);
	    		$('.require-two').each(function () {
	    			console.log($(this));
	 			   $(this).attr('disabled', true);
	 		   });*/
	    		
	    		$('.appl').empty();
	    		
	    	}
	    	if($('.require-two').filter(':checked').length>0){
		    	/*	console.log($('.require-one').filter(':checked').length);
		    		$('.require-two').each(function () {
		    			console.log($(this));
		 			   $(this).attr('disabled', true);
		 		   });*/
	    		$('.appl').empty();
		    		
		    	}
	    });
	   /* $(document).on('change','.require-two',function(){
	    	if($('.require-one').filter(':checked').length>0){
	    		$('input[type=checkbox]','.require-one').each(function () {
	 			   $(this).prop('checked', false);
	 		   });
	    		
	    	}
	    });*/
	   
	   
	});
