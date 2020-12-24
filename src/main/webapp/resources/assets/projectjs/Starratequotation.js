 $(document).ready(function () {
	 if( $('#emerper').val()==""){
	 $('#emerper').val(5);  }
	 
            recalculateothervendoreqtotal();
           
            emerdis();
		    recalculate();
	        
		    $("#btnResult").click(function () {
                var counter = $("#tblResult  tbody  tr").length;
                var count = counter;
                $("#tblResult").append('<tr><td><input type="text" readonly  name="otherVendorEquipmentList['+count+'].serialno"   value='+(count+1)+' class="othervendorserialno" required></td><td><textarea style="width: 144px;"  class="othervendordescription"   name="otherVendorEquipmentList['+count+'].description"  id='+count+' required ></textarea></td><td><input type="text" name="otherVendorEquipmentList['+count+'].unitOfMeasure" id='+count+' class="othervendorunit" required></td><td><input type="text" class="othervendorprice"   name="otherVendorEquipmentList['+count+'].unitPrice" required></td><td><input type="text"  class="othervendorquantity"  name="otherVendorEquipmentList['+count+'].qty" required></td><td><input type="text"  class="othervendorcost"  name="otherVendorEquipmentList['+count+'].cost" readonly="true" required></td><td><a class="btn-tr-remove" style="color:black;margin-left: 25px;"><i class="fa fa-trash"> </i></a></td></tr>');
                recalculateothervendoreqtotal();
                 emerdis();
 				 recalculate();
            });

            
            
            $(document).on('click', '.btn-tr-remove', function (e) {
                e.preventDefault();
                if ($('#tblResult tr').length > 1) {
                    $(this).closest('tr').remove();
                    $('input[class="othervendorserialno"]').attr("value", function (i) {
                       var getResult = (i+1);
                    	return (getResult);
                    });
                    $('input[class="othervendorserialno"]').attr("name", function (i) {
                        var getResult = (i+1)-1;
                     	return ("otherVendorEquipmentList["+getResult+"].serialno");
                     });
                    $('textarea[class="othervendordescription"]').attr("name", function (i) {
                       var getResult = (i+1)-1;
                        return ("otherVendorEquipmentList["+getResult+"].description");
                    });
                    $('input[class="othervendorunit"]').attr("name", function (i) {
                        var getResult = (i+1)-1;
                         return ("otherVendorEquipmentList["+getResult+"].unitOfMeasure");
                     });
                    $('input[class="othervendorprice"]').attr("name", function (i) {
                        var getResult = (i+1)-1;
                        return ("otherVendorEquipmentList["+getResult+"].unitPrice");
                     });
                    $('input[class="othervendorquantity"]').attr("name", function (i) {
                        var getResult = (i+1)-1;
                     	return ("otherVendorEquipmentList["+getResult+"].qty");
                     });
                    $('input[class="othervendorcost"]').attr("name", function (i) {
                        var getResult = (i+1)-1;
                     	return ("otherVendorEquipmentList["+getResult+"].cost");
                     });
                   
                     
                    recalculateothervendoreqtotal();
                     emerdis();
     				 recalculate();
     				
     					  $( "#actualamount" ).trigger( "change" );
     					
                }
                return false;
             });
            
            $(document).on("blur change keypress keyup",".othervendorquantity",function(e) {
               $(this).parent().parent().find(".othervendorcost").val(Number(
 						$(this).parent().parent().find(".othervendorprice").val()
 								* $(this).parent().parent().find(".othervendorquantity").val()).toFixed(2));
 				recalculateothervendoreqtotal();
 			    emerdis();
 				recalculate();
 				
 			});
            $(document).on("blur change keypress keyup",".othervendorprice",function(e) {
                $(this).parent().parent().find(".othervendorcost").val(Number(
  						$(this).parent().parent().find(".othervendorprice").val()
  								* $(this).parent().parent().find(".othervendorquantity").val()).toFixed(2));
  				recalculateothervendoreqtotal();
  			   emerdis();
  				recalculate();
  				
  			}); 
            
            function recalculateothervendoreqtotal() {
      			
  				var othervendoreqsubtotal = 0;
  				$('#tblResult tbody tr').find('.othervendorcost').each(function() {
  					othervendoreqsubtotal = Number(othervendoreqsubtotal)
  											+ Number($(this).val());
  								});
  				
  				$('#otherveneqsubtotal').val(othervendoreqsubtotal.toFixed(2));
  				$('#otherveneqtotal').val(parseFloat(Number($('#otherveneqsubtotal').val())
  								- (Number($('#otherveneqsubtotal').val() / 100) * Number($('#otherveneqdiscount').val())).toFixed(2)));

  			}
            
            $(document).on("blur change keypress keyup","#gst", function(e) {    
				recalculate();
				console.log("INS GST")
			}); 
            
            $(document).on("blur change keypress keyup","#emerper", function(e) {
       			console.log("Inside EMER");
       			        emerdis();
						recalculate();
					});
            $(document).on("blur change keypress keyup",".othervendorquantity", function(e) {
       			console.log("Inside EMER");
       			        emerdis();
						recalculate();
					});
       		$(document).on("blur change keypress keyup",".othervendorprice", function(e) {
       			console.log("Inside EMER");
       			        emerdis();
						recalculate();
					});
            
			$(document).on("change",".target", function(e) {
			       			
			       			emerdis();
			       			recalculate();
			       			
			       			
			});
			$(document).on("blur change keypress keyup propertychange","#actualamount", function(e) {
       			emerdis();
       			recalculate();
              });
			            
            function emerdis(){
       			var vall= "Extra Charges"
				 
				switch(vall) {
			    case 'Extra Charges':
			    	console.log("Selecting Extra Charges");
			    	 $('#emerper').attr("disabled",false);
			    	var v=  Number($('#actualamount').val())+Number(($('#actualamount').val()*($('#emerper').val()/100)));
    			console.log("V" +v.toFixed(2));
    			
    			$('#emerafterr').val(v.toFixed(2));
    			 var amm =Number($('#actualamount').val()*($('#emerper').val()/100));
    			$('#emeramt').val(amm.toFixed(2));
    			recalculate();
	        	 console.log( $( ".target option:selected" ).val());
			        break;
			   
		       
			   
			}
       		}
            
            function recalculate() {
           	
    			if ($('#otherveneqsubtotal').val() == "") {
    				$('#otherveneqsubtotal').val(0);
    			}
    			
    			
    			


    			var actualamountcalc =
    					 Number($('#otherveneqsubtotal').val())
    					

    			$('#actualamount').val(
    					parseFloat(actualamountcalc).toFixed(2));
    			var vall= $( ".target option:selected" ).val();	
    			
    			if(vall == 'Nothing')
    				{
    				var calculategst = parseFloat(Number($(
					'#actualamount').val()) / 100)
					* (Number($('#gst').val())).toFixed(2);
			      $('#gstamount').val(
					parseFloat(calculategst).toFixed(2));
			      var grandtotalcalc = parseFloat(
					Number(calculategst)+ Number($('#actualamount').val())).toFixed(2);
                  }
            else
           	   {
    			var calculategst = parseFloat(Number($(
    					'#emerafterr').val()) / 100)
    					* (Number($('#gst').val())).toFixed(2);
    			$('#gstamount').val(
    					parseFloat(calculategst).toFixed(2));
    			var grandtotalcalc = parseFloat(
    					Number(calculategst)+ Number($('#emerafterr').val())).toFixed(2);
    			
    			}
    			$('#grandamount').val(
    					parseFloat(grandtotalcalc).toFixed(2));
}  
            
   
 });