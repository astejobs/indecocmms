 $(document).ready(function () {
	 
	 if($('#gst').val()==""){
	 $('#gst').val(7);
	 }
	 if( $( ".target option:selected" ).val()=='Nothing'){
	 $('#emerper').attr("disabled",true);
	 }
	        recalculatesortotal();
	        recalculatelabourtotal();
            recalculateothervendoreqtotal();
            recalculateothervendorlablisttotal();
            emerdis();
		    recalculate();
	        
        	  $('#addtoquot').click(function() {
        		  
        		   var  ids=[];
        		//	$('#tblRes2 tbody tr td input[type="checkbox"]:checked').each(function(){console.log($(this).val())});
        			$('#tblRes2 tbody tr td input[type="checkbox"]:checked').each(function() {
        				 console.log("YESS"); 
        				 console.log($(this)); 
        				 console.log($(this).val()); 
        				 console.log("pushing ids "+ids.push($(this).val()));
        				console.log($('#sorurl').val()); 
        				console.log(ids); 
        				 
        			});
        				 
        			$.ajax({
        					  type: "GET",
        					  url: $('#sorurl').val(),
        					  data: "value="+ids,
        					  cache: false,
        					
        					     success: function(response){
        						
        						 $.each(response,function(data,k){
        					
        							 console.log("GVGHV")
        							 var count = $('#sortbody').children().length ;
        							 console.log("hbvjb"+count)
        			$('#sortable').find('tbody')
          					       .append($('<tr class="tblRoot">')
          					    		    .append($('<td>')
          					    		    	  .append($('<input style="width: 35px;">')
          					    		    	     .attr('class','equipmentSerialno')		  
          					    		    		 .attr('type','text')
          					    		    		 .attr('name','equipmentList['+count+'].serialno')
          					    		    		 .attr('value',count+1)	 
          					    		    		 .attr('readonly', 'true')
          					    		        )		
          						    	            
          						    	    )
          					    	        .append($('<td>')
          					    	        	 .append($('<input style="width: 110px;">')
          					    	        		.attr('readonly', 'true')
          					    	        	    .attr('class','equipmentType')	
          					    	        	    .attr('type','text')
          					    	                .attr('name','equipmentList['+count+'].equipType')
          					    	                .attr('value',k.equipmentType)
          					    	           )
          					    	        )
          					    	         .append($('<td>')
          					    	        	 .append($('<input  style="width: 96px;">')
          					    	        		.attr('readonly', 'true')
          					    	        		.attr('class','equipmentItemCode')	
          					    	        	    .attr('type','text')
          					    	        	    .attr('name','equipmentList['+count+'].itemCode')
          					    	                .attr('value',k.itemCode)
          					    	           )
          					    	        )
          					    	        .append($('<td>')
          					    	        	 .append($('<input>')
          					    	        		.attr('readonly', 'true')	 
          					    	        		.attr('class','equipmentName')	
          					    	        	    .attr('type','text')
          								    	    .attr('name','equipmentList['+count+'].equipName')
          					    	                .attr('value',k.equipmentName)
          					    	           )
          					    	        )
          					    	        .append($('<td>')
          					    	        	 .append($('<input>')
          					    	        		 .attr('readonly', 'true') 
          					    	        		 .attr('class','equipmentDescription')	
          					    	        		 .attr('type','text')
          										     .attr('name','equipmentList['+count+'].description')
          					    	                 .attr('value',k.description)
          					    	           )
          					    	        )
          					    	        .append($('<td>')
          					    	        	 .append($('<input style="width: 44px;">')
          					    	        		 .attr('readonly', 'true')
          					    	        		 .attr('class','equipmentunitOfMeasurement')	
          					    	        		 .attr('type','text')
          											 .attr('name','equipmentList['+count+'].unitOfMeasure')
          					    	                 .attr('value',k.unit)
          					    	          )
          					    	        )
          					    	        .append($('<td>')
          					    	        	 .append($('<input style="width: 100px;">')
          					    	        		 .attr('readonly', 'true')
          					    	        		 .attr('class','equipmentUnitCost')	
          					    	        		 .attr('type','text')
          											 .attr('name','equipmentList['+count+'].unitCost')
          					    	                 .attr('value',k.rate)
          					    	          )
          					    	        )
          					    	        .append($('<td>')
          					    	            .append($('<input style="width: 44px;">')
          					    	            		.attr('class','equipmentQuantity')	
          					    	            		.attr('type','text')
          					    	            		.attr('value','1')
          					    	            		.attr('name','equipmentList['+count+'].quantity')
          					    	            )
          					    	        )
          					    	        .append($('<td>')
          					    	        		 .append($('<input style="width: 100px;">')
          					    	        			.attr('readonly', 'true')
          							    	            .attr('type','text')
          												.attr('name','sgd')
          												.attr('value',k.rate)
          							    	            .attr('class','sgdd')
          							    	          
          					    	           )  
          					    	        )
          					    	        .append($('<td>')
          					    	            .append($('<input>')
          					    	            		.attr('type','text')
          					    	            		.attr('name','equipmentList['+count+'].remarks')
          					    	            		.attr('style','width: 90px')
          					    	            		
          					    	            )
          					    	        )    
          					    	        .append($('<td>')
          					    	            .append($('<a style=" margin-left: 34px;">')
          					    	            		.attr('class','btn-remove-tr')
          					    	            		.append($('<i>')
          					    	            		.attr('class','fa fa-trash')		
          					    	            		)
          					    	            		
          					    	            		
          					    	            		
          					    	            	
          					                ) 
          					            )    
          					        );
        							 
        							 
        							 
        							 
        						 })
        						 
        						 recalculatesortotal();
        						  emerdis();
        						 recalculate();
        						 $( "#actualamount" ).trigger( "change" );
        					    
        					     }
        					
        				
        		        }).fail(function() {
        		            alert( "error" );
        		        });
        	 
        			$('#tblRes2 tbody tr td input[type="checkbox"]').each(function() {
        	            $(this).prop('checked', false);
        	        });
        			 
        			 
        			
        	       });
        	  
        	  
        	     
        	  
        	  $(document).on('click', ".btn-remove-tr", function (e) {
        		  console.log("Delete "+this);
        		 /* alert("this")*/
                  e.preventDefault();
                  if ($('#sortable tr').length > 1) {
                      $(this).closest('tr').remove();
                      $('input[class="equipmentSerialno"]').attr("value", function (i) {
                         var getResult = (i+1);
                      	return (getResult);
                      });
                      $('input[class="equipmentSerialno"]').attr("name", function (i) {
                          var getResult = (i+1)-1;
                       	return ("equipmentList["+getResult+"].serialno");
                       });
                      $('input[class="equipmentType"]').attr("name", function (i) {
                         var getResult = (i+1)-1;
                          return ("equipmentList["+getResult+"].equipType");
                      });
                      $('input[class="equipmentItemCode"]').attr("name", function (i) {
                          var getResult = (i+1)-1;
                           return ("equipmentList["+getResult+"].itemCode");
                       });
                      $('input[class="equipmentName"]').attr("name", function (i) {
                          var getResult = (i+1)-1;
                          return ("equipmentList["+getResult+"].equipName");
                       });
                      $('input[class="equipmentDescription"]').attr("name", function (i) {
                          var getResult = (i+1)-1;
                       	return ("equipmentList["+getResult+"].description");
                       });
                       $('input[class="equipmentunitOfMeasurement"]').attr("name", function (i) {
                          var getResult = (i+1)-1;
                           return ("equipmentList["+getResult+"].unitOfMeasure");
                       });
                       $('input[class="equipmentUnitCost"]').attr("name", function (i) {
                           var getResult = (i+1)-1;
                            return ("equipmentList["+getResult+"].unitCost");
                        });
                       $('input[class="equipmentQuantity"]').attr("name", function (i) {
                           var getResult = (i+1)-1;
                           return ("equipmentList["+getResult+"].quantity");
                        });
                       $('input[class="equipmentRemarks"]').attr("name", function (i) {
                           var getResult = (i+1)-1;
                           return ("equipmentList["+getResult+"].remarks");
                        });
                       
                     recalculatesortotal();
                     emerdis();
       				 recalculate();
       			  $( "#actualamount" ).trigger( "change" );
                  }
                 
               });
        	  
        	  
        		 $(document).on("blur change keypress keyup",".equipmentQuantity",function(e) {
        				$(this).parent().parent().find(".sgdd").val(Number(
        						$(this).parent().parent().find(".equipmentUnitCost").val()
        								* $(this).parent().parent().find(".equipmentQuantity").val()).toFixed(2));
        				recalculatesortotal();
        				  emerdis();
        				recalculate();
        				
        			});
        		 
        		 function recalculatesortotal() {
        				var sorequipmentssubtotal = 0;
        				$('#sortable tbody tr').find('.sgdd').each(function() {
        				    sorequipmentssubtotal = Number(sorequipmentssubtotal)
        											+ Number($(this).val());
        								});
        				
        				$('#sorsubtotal').val(sorequipmentssubtotal.toFixed(2));
        				$('#sortotal').val(parseFloat(Number($('#sorsubtotal').val())
        								- (Number($('#sorsubtotal').val() / 100) * Number($('#sordiscount').val())).toFixed(2)));

        			}
        		 
        		 $(document).on("blur change keypress keyup","#sordiscount",function(e) {
        				$('#sortotal').val(parseFloat(Number($('#sorsubtotal').val())
        												- (Number($('#sorsubtotal').val() / 100) * Number($(
        														'#sordiscount').val()))).toFixed(2));
        				  emerdis();
        				recalculate();
        			});
        	  
        	  $('#addtolabour').click(function() {
        		  
        		  var  ids=[];
      			
        		  $('#tblRes1 tbody tr td input[type="checkbox"]:checked').each(function() {
        				 ids.push($(this).val());
        			    });
        				$.ajax({
        					  type: "GET",
        					  url: $('#laburl').val(),
        					  data: "id="+ids,
        					  cache: false,
        					  success: function(response){
        						  $.each(response,function(data,k){
        						 var count = $('#labourbody').children().length; 
        					     $('#labourtable').find('tbody')
        					       .append($('<tr class="tblRoot">')
        					    		    .append($('<td>')
        					    		    		 .append($('<input style="width: 35px;">')
        							    		    		 .attr('type','text')
        							    		    		 .attr('readonly', 'true')
        							    		    		 .attr('class','labourSerialno')
        							    		    		 .attr('name','labourRateList['+count+'].serialno')
        							    		    		 .attr('value',count+1)	 
        							    		    		 .attr('readonly', 'true')
        							    		 )   		 
        						    	    )
        					    	        .append($('<td>')
        					    	        	 .append($('<input>')
        					    	        	    .attr('type','text')
        					    	        	    .attr('readonly', 'true')
        					    	        	    .attr('class','labouritemCode')
        					    	                .attr('name','labourRateList['+count+'].itemCode')
        					    	                .attr('value',k.itemCode)
        					    	           )
        					    	        )
        					    	         .append($('<td>')
        					    	        	 .append($('<input>')
        					    	        	    .attr('type','text')
        					    	        	    .attr('readonly', 'true')
        					    	        	    .attr('class','labourDescription')
        					    	        	    .attr('name','labourRateList['+count+'].description')
        					    	                .attr('value',k.description)
        					    	           )
        					    	        )
        					    	        .append($('<td>')
        					    	        	 .append($('<input style="width: 100px;">')
        					    	        	    .attr('type','text')
        					    	        	    .attr('readonly', 'true')
        					    	        	    .attr('class','labourUnit') 
        								    	    .attr('name','labourRateList['+count+'].unitOfMeasure')
        					    	                .attr('value',k.unit)
        					    	           )
        					    	        )
        					    	        .append($('<td>')
        					    	        	 .append($('<input>')
        					    	        		 .attr('type','text')
        					    	        		 .attr('readonly', 'true')
        					    	        		 .attr('class','labourcost')
        										     .attr('name','labourRateList['+count+'].unitCost')
        					    	                 .attr('value',k.rate)
        					    	           )
        					    	        )
        					    	        .append($('<td>')
        					    	        	 .append($('<input style="width: 44px;">')
        					    	        		 .attr('type','text')
        					    	        		 .attr('value',1)
        					    	        		 .attr('class','labourQuantity')
        											 .attr('name','labourRateList['+count+'].qty')
        					    	              
        					    	          )
        					    	        )
        					    	        .append($('<td>')
        					    	            .append($('<input >')
        					    	            		.attr('type','text')
        					    	            		.attr('value',k.rate)
        					    	            		.attr('readonly', 'true')
        					    	            		.attr('class','labourtotal')
        					    	            		.attr('name','total')
        					    	            )
        					    	        )    
        					    	        .append($('<td>')
        					    	        		.append($('<a style=" margin-left: 34px;">')
              					    	            		.attr('class','btn-remove-tr')
              					    	            		.append($('<i>')
              					    	            		.attr('class','fa fa-trash')		
              					    	            		)
            					    	            	   
        					    	            ) 
        					    	        )    
        					    	    );
        						  })
        						  
        						 recalculatelabourtotal();
        						  emerdis();
                  				 recalculate();
                  				 $( "#actualamount" ).trigger( "change" );
        					     }
        					});
        				
        				$('#tblRes1 tbody tr td input[type="checkbox"]').each(function() {
            	            $(this).prop('checked', false);
            	        });
        		        });
        	 
                 $('#labourtable').on('click', '.btn-remove-tr', function (e) {
                                                e.preventDefault();
                                              
                                                if ($('#labourtable tr').length > 1) {
                                                    $(this).closest('tr').remove();
                                                    $('input[class="labourSerialno"]').attr("value", function (i) {
                                                       var getResult = (i+1);
                                                    	return (getResult);
                                                    });
                                                    $('input[class="labourSerialno"]').attr("name", function (i) {
                                                        var getResult = (i+1)-1;
                                                     	return ("labourRateList["+getResult+"].serialno");
                                                     });
                                                    $('input[class="labouritemCode"]').attr("name", function (i) {
                                                       var getResult = (i+1)-1;
                                                        return ("labourRateList["+getResult+"].itemCode");
                                                    });
                                                    $('input[class="labourDescription"]').attr("name", function (i) {
                                                        var getResult = (i+1)-1;
                                                         return ("labourRateList["+getResult+"].description");
                                                     });
                                                    $('input[class="labourUnit"]').attr("name", function (i) {
                                                        var getResult = (i+1)-1;
                                                        return ("labourRateList["+getResult+"].unitOfMeasure");
                                                     });
                                                    $('input[class="labourcost"]').attr("name", function (i) {
                                                        var getResult = (i+1)-1;
                                                     	return ("labourRateList["+getResult+"].unitCost");
                                                     });
                                                     $('input[class="labourQuantity"]').attr("name", function (i) {
                                                        var getResult = (i+1)-1;
                                                         return ("labourRateList["+getResult+"].qty");
                                                     });
                                                     $('input[class="labourQuantity"]').attr("name", function (i) {
                                                         var getResult = (i+1)-1;
                                                          return ("labourRateList["+getResult+"].qty");
                                                      });
                                                  
                                                     
                                                     recalculatelabourtotal();
                                                     emerdis();
                                     				 recalculate();
                                     				  $( "#actualamount" ).trigger( "change" );
                                                }
                                                return false;
                                             });
                 
                 $(document).on("blur change keypress keyup",".labourQuantity",function(e) {
     				$(this).parent().parent().find(".labourtotal").val(Number(
     						$(this).parent().parent().find(".labourcost").val()
     								* $(this).parent().parent().find(".labourQuantity").val()).toFixed(2));
     				recalculatelabourtotal();
     				  emerdis();
     				recalculate();
     				
     			});
     		 
     		 function recalculatelabourtotal() {
     				var laboursubtotal = 0;
     				$('#labourtable tbody tr').find('.labourtotal').each(function() {
     					laboursubtotal = Number(laboursubtotal)
     											+ Number($(this).val());
     								});
     				$('#laboursubtotal').val(laboursubtotal.toFixed(2));
     				$('#labourtotal').val(parseFloat(Number($('#laboursubtotal').val())
     								- (Number($('#laboursubtotal').val() / 100) * Number($('#labourdiscount').val())).toFixed(2)));

     			}
     		 
     		 $(document).on("blur change keypress keyup","#labourdiscount",function(e) {
     				$('#labourtotal').val(parseFloat(Number($('#laboursubtotal').val())
     												- (Number($('#laboursubtotal').val() / 100) * Number($(
     														'#labourdiscount').val()))).toFixed(2));
     				emerdis();
     				recalculate();
     			});
     	  
                 
                 $("#btnResult").click(function () {
                     var counter = $("#tblResult  tbody  tr").length;
                     var count = counter;
                     $("#tblResult").append('<tr><td><input  type="text" readonly  name="otherVendorEquipmentList['+count+'].serialno"   value='+(count+1)+' class="othervendorserialno" required ></td><td><textarea   class="othervendordescription"   name="otherVendorEquipmentList['+count+'].description"  id='+count+' required ></textarea></td><td><input  type="text" name="otherVendorEquipmentList['+count+'].unitOfMeasure" id='+count+' class="othervendorunit" required></td><td><input type="text" class="othervendorprice"   name="otherVendorEquipmentList['+count+'].unitPrice" required></td><td><input  type="text"  class="othervendorquantity"  name="otherVendorEquipmentList['+count+'].qty" required></td><td><input  type="text"  class="othervendorcost"  name="otherVendorEquipmentList['+count+'].cost" readonly="true" required></td><td><a class="btn-tr-remove" style="color:black;margin-left: 25px;"><i class="fa fa-trash"> </i></a></td></tr>');
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
          				  $( "#othervendotlablistsubtotal" ).trigger( "change" );
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
                     $(this).parent().parent().find(".othervendorcost").val(
       						$(this).parent().parent().find(".othervendorprice").val()
       								* $(this).parent().parent().find(".othervendorquantity").val());
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
      		 
      		 $(document).on("blur change keypress keyup","#otherveneqdiscount",function(e) {
      				$('#otherveneqtotal').val(parseFloat(Number($('#otherveneqsubtotal').val())
      												- (Number($('#otherveneqsubtotal').val() / 100) * Number($(
      														'#otherveneqdiscount').val()))).toFixed(2));
      			    emerdis();
      				recalculate();
      			});
           	  
                 
                 $("#btnResul").click(function () {
                     var counter = $("#tblResul  tbody  tr").length;
                     var count = counter;
                     $("#tblResul").append('<tr><td><input  type="text" readonly name="otherVendorLabourList['+count+'].serialno" value='+(counter+1)+' class="othervendorlablistserialno" required></td><td><textarea  class="othervendorlablistdescription"    name="otherVendorLabourList['+count+'].description" id='+count+'  required ></textarea></td><td><input  type="text" name="otherVendorLabourList['+count+'].unitOfMeasure" id='+count+' class="othervendorlablistunit" required></td><td><input type="text"  class="othervendorlablistprice"  name="otherVendorLabourList['+count+'].unitPrice" required></td><td><input style="width: 60px;"  type="text" class="othervendorlablistqty"  name="otherVendorLabourList['+count+'].qty" required></td><td><input  type="text" class="othervendorlablistcost"  name="otherVendorLabourList['+count+'].cost" readonly="true" required></td><td><a style="margin-left:24;" class="btn-removeee" ><i class="fa fa-trash"></i></a></td></tr>');
                     recalculateothervendorlablisttotal();
                     emerdis();
      				 recalculate();
                 });

                 $(document).on('click', '.btn-removeee', function (e) {
                     e.preventDefault();
                    
                     if ($('#tblResul tr').length > 1) {
                         $(this).closest('tr').remove();
                         $('input[class="othervendorlablistserialno"]').attr("value", function (i) {
                            var getResult = (i+1);
                         	return (getResult);
                         });
                         $('input[class="othervendorlablistserialno"]').attr("name", function (i) {
                             var getResult = (i+1)-1;
                            
                          	return ("otherVendorLabourList["+getResult+"].serialno");
                          });
                         console.log($('input[class="othervendorlablistdescription"]'));
                         $('textarea[class="othervendorlablistdescription"]').attr("name", function (i) {
                        	
                        	 var getResult = (i+1)-1;   
                          
                             return ("otherVendorLabourList["+getResult+"].description");
                         });
                         $('input[class="othervendorlablistunit"]').attr("name", function (i) {
                             var getResult = (i+1)-1;
                              return ("otherVendorLabourList["+getResult+"].unitOfMeasure");
                          });
                         $('input[class="othervendorlablistprice"]').attr("name", function (i) {
                             var getResult = (i+1)-1;
                             return ("otherVendorLabourList["+getResult+"].unitPrice");
                          });
                         $('input[class="othervendorlablistqty"]').attr("name", function (i) {
                             var getResult = (i+1)-1;
                          	return ("otherVendorLabourList["+getResult+"].qty");
                          });
                         $('input[class="othervendorlablistcost"]').attr("name", function (i) {
                             var getResult = (i+1)-1;
                          	return ("otherVendorLabourList["+getResult+"].cost");
                          });
                        
                          
                         recalculateothervendorlablisttotal();
                         emerdis();
          				 recalculate();
          				  $( "#actualamount" ).trigger( "change" );
          				  $( "#othervendotlablistsubtotal" ).trigger( "change" );
          				
                     }
                     return false;
                  });
                 
                 $(document).on("blur change keypress keyup propertychange",".othervendorlablistqty",function(e) {
       				$(this).parent().parent().find(".othervendorlablistcost").val(Number(
       						$(this).parent().parent().find(".othervendorlablistprice").val()
       								* $(this).parent().parent().find(".othervendorlablistqty").val()).toFixed(2));
       				
       				recalculateothervendorlablisttotal();
       			    emerdis();
       				recalculate();
       				
       			});
                 
                 $(document).on("blur change keypress keyup propertychange",".othervendorlablistprice",function(e) {
        				$(this).parent().parent().find(".othervendorlablistcost").val(number(
        						$(this).parent().parent().find(".othervendorlablistprice").val()
        								* $(this).parent().parent().find(".othervendorlablistqty").val()).toFixed(2));
        				
        				
        				recalculateothervendorlablisttotal();
        				  emerdis();
        				recalculate();
        				
        			});
       		 
       		 function recalculateothervendorlablisttotal() {
       				var othervendolablistsubtotal = 0;
       			 
       		
       				$('#tblResul tbody tr').find('.othervendorlablistcost').each(function() {
       					console.log($(this).val())
       					othervendolablistsubtotal = Number(othervendolablistsubtotal)
       											+ Number($(this).val());
       								});
       				
       			 console.log(othervendolablistsubtotal)
       				$('#othervendotlablistsubtotal').val(othervendolablistsubtotal.toFixed(2));
       				$('#othervendotlablisttotal').val(parseFloat(Number($('#othervendotlablistsubtotal').val())
       								- (Number($('#othervendotlablistsubtotal').val() / 100) * Number($('#othervendotlablistdiscount').val())).toFixed(2)));

       			}
       		 
       		 $(document).on("blur change keypress keyup","#othervendotlablistdiscount",function(e) {
       				$('#othervendotlablisttotal').val(parseFloat(Number($('#othervendotlablistsubtotal').val())
       												- (Number($('#othervendotlablistsubtotal').val() / 100) * Number($(
       														'#othervendotlablistdiscount').val()))).toFixed(2));
       			  emerdis();
       				recalculate();
       			});
            	  
                 
       		$(document).on("blur change keypress keyup","#gst", function(e) {    
						recalculate();
						console.log("INS GST")
					}); 
       		$(document).on("blur change keypress keyup","#emerper", function(e) {
       			console.log("Inside EMER");
       			        emerdis();
						recalculate();
					});
       		$(document).on("blur change keypress keyup",".othervendorlablistqty", function(e) {
       			
       			        emerdis();
						recalculate();
					});
       		$(document).on("blur change keypress keyup",".othervendorlablistprice", function(e) {
       			
       			        emerdis();
						recalculate();
					});
       		$(document).on("blur change keypress keyup",".othervendorprice", function(e) {
       			
       			        emerdis();
						recalculate();
					});
       		$(document).on("blur change keypress keyup",".othervendorquantity", function(e) {
       			
       			        emerdis();
						recalculate();
					});
       		
     
       	
       		
       		$(document).on("change",".target", function(e) {
       			
       			emerdis();
				/* var vall= $( ".target option:selected" ).val();
				 
				switch(vall) {
			    case 'Emergency':
			    	console.log("Selecting Emergency");
			    	 $('#emerper').attr("disabled",false);
			    	var v=  Number($('#actualamount').val())+Number(($('#actualamount').val()*($('#emerper').val()/100)));
    			console.log("AMOUBV" + $('#actualamount').val());
    			console.log("per"+$('#actualamount').val()*($('#emerper').val()/100));
    			console.log("V" +v.toFixed(2));
    			
    			$('#emerafterr').val(v.toFixed(2));
    			 var amm =Number($('#actualamount').val()*($('#emerper').val()/100));
    			$('#emeramt').val(amm);
    			recalculate();
	        	
	        //	 console.log( $( ".target option:selected" ).val());
			        break;
			    case 'Discount':
			    	console.log("Selecting Discount");
			    	 $('#emerper').attr("disabled",false);
			    	console.log( $( ".target option:selected" ).val());
		    	 
			    	var v=  Number($('#actualamount').val())-Number(($('#actualamount').val()*($('#emerper').val()/100)));
    			console.log("AMOUBV" + $('#actualamount').val());
    			console.log("per"+$('#actualamount').val()*($('#emerper').val()/100));
    			console.log("V" +v.toFixed(2));
    			
    			$('#emerafterr').val(v.toFixed(2));
    			 var amm =Number($('#actualamount').val()*($('#emerper').val()/100));
    			$('#emeramt').val(amm);
    			recalculate();
			       
			        break;
			   case 'Nothing':
				  $('#emerper').val("");
			       $('#emerper').attr("disabled",true);
			      $('#emeramt').val("");
			     $('#emerafterr').val("");
			     recalculate();
		        break;
			    default:
			       
			}*/	   
	        	});
       		
       		$(document).on("blur change keypress keyup propertychange","#actualamount", function(e) {
       			recalculateothervendorlablisttotal();
       			emerdis();		
      			recalculate();
             });
       		$(document).on("blur change keypress keyup propertychange","#othervendotlablistsubtotal", function(e) {
       		
       			recalculateothervendorlablisttotal();
       			emerdis();		
      			recalculate();
             });
       		function emerdis(){
       			var vall= $( ".target option:selected" ).val();
				 
				switch(vall) {
			    case 'Emergency':
			    	console.log("Selecting Emergency");
			    	$('#labper').empty();
			    	$('#labamount').empty();
			    	$('#aae').empty();
			    	$('#labper').append('Emergency %');
			    	$('#labamount').append('Emergency Amount');
			    	$('#aae').append('Amount After Emergency');
			    	
			    	 $('#emerper').attr("disabled",false);
			    	var v=  Number($('#actualamount').val())+Number(($('#actualamount').val()*($('#emerper').val()/100)));
    			console.log("V" +v.toFixed(2));
    			
    			$('#emerafterr').val(v.toFixed(2));
    			 var amm =Number($('#actualamount').val()*($('#emerper').val()/100));
    			$('#emeramt').val(amm.toFixed(2));
    			recalculate();
	        	 console.log( $( ".target option:selected" ).val());
			        break;
			    case 'Discount':
			    	console.log("Selecting Discount");
			    	$('#labper').empty();
			    	$('#labamount').empty();
			    	$('#aae').empty();
			    	$('#labper').append('Discount %');
			    	$('#labamount').append('Discount Amount');
			    	$('#aae').append('Amount After Discount');
			    	
			    	 $('#emerper').attr("disabled",false);
			    	console.log( $( ".target option:selected" ).val());
		    	 
			    	var v=  Number($('#actualamount').val())-Number(($('#actualamount').val()*($('#emerper').val()/100)));
    			console.log("V" +v.toFixed(2));
    			
    			$('#emerafterr').val(v.toFixed(2));
    			 var amm =Number($('#actualamount').val()*($('#emerper').val()/100).toFixed(2));
    			$('#emeramt').val(amm);
    			recalculate();
			        break;
			   case 'Nothing':
				  $('#emerper').val("");
			       $('#emerper').attr("disabled",true);
			      $('#emeramt').val("");
			     $('#emerafterr').val("");
			     recalculate();
			   
		       
			   
			}
       		}
       		
                 
                 function recalculate() {
                	
         			if ($('#sorsubtotal').val() == "") {
         				$('#sorsubtotal').val(0);
         			}
         			if ($('#otherveneqsubtotal').val() == "") {
         				$('#otherveneqsubtotal').val(0);
         			}
         			if ($('#laboursubtotal').val() == "") {
         				$('#laboursubtotal').val(0);
         			}
         			if ($('#othervendotlablistsubtotal').val() == "") {
         				$('#othervendotlablistsubtotal').val(0);
         			}
         			
         			

                    
         			var actualamountcalc = Number($('#sorsubtotal').val())
         					+ Number($('#otherveneqsubtotal').val())
         					+ Number($('#laboursubtotal').val())
         					+ Number($('#othervendotlablistsubtotal').val());

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