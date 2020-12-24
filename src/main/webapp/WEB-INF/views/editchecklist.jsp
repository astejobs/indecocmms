<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
        $(document).ready(function () {
          var count =0;
          var bcount =0;
          var comp;
             
            $("#addnewsection").click(function () {
            	 console.log("THE COMP");
            	 console.log(comp);
            	console.log($(this).parent().next().children().toArray());
            	var lengg= $(this).parent().next().children().toArray().length;
            	 var ff;
            	 
            	 if(lengg==0){
            		count=0; 
            	 }
                 if(lengg==4){
                	 console.log("ENTERING ADD SECTION")
                	    lengg= $(this).parent().next().children().toArray().length;
                	   console.log(lengg)
                	   ff= lengg/4;
                	   console.log(ff);
                	   count=ff; 
                 }
                $("#clone").append('<div id="'+count+'" class="form-group input-group">'
                	   +'<span class="input-group-addon label-left" id="basic-addon2">Title</span>'
                       +'<textarea  name="propertyTitles['+count+'].title" class="input-md round form-control" placeholder="Title"></textarea>'
                       +'</div>'
                       +'<div id="'+count+'" class="form-group propertyTable">'
                       +' <table class="table table-stripped">'
                       +'<thead>'
                       +'<tr>'
                       +'<th>S.No</th>'
                       +'<th>Work Description</th>'
                     
                       +'<th>Delete</th>'
                       +'</tr>'
                       +'</thead>'
                       +'<tbody>'
                       +'<tr>'
                       +'<td>1</td>'
                       +'<td>'
                       +'<div class="form-group">'
                       +'<textarea name="propertyTitles['+count+'].properties[0].description" class="input-md round form-control" placeholder=""></textarea>'
                       +'</div>'
                       +'</td>'
                       
                    
                       
                       
                       +'<td><input style="padding: 4px 9px;margin-bottom:2px;" type="button" id="'+bcount+'" value="Del" class="delll submit_btn btn btn-mod btn-medium btn-round"><input style="padding: 4px 7px;" type="button" id="'+bcount+'" value="Add" class="adfi submit_btn btn btn-mod btn-medium btn-round"></td>'
                       +'</tr>'
                       +'</tbody>'
                       +'</table>'
                       +'</div>'
                       +'<div id="'+count+'" class="form-group parent">'
                       +'<input id="'+count+'" type="button" value="Add New Work Description" class="submit_btn btn btn-mod btn-medium btn-round  addnewworkdesc"/>'
                       +'</div>'
                       +' <div id="'+count+'" class="form-group">'
                       +' <input id="'+count+'" type="button" value="Delete" class="dellete submit_btn btn btn-mod btn-medium btn-round" />'
                       +'  </div>'
                );
                var leng= $(this).parent().next().children('.propertyTable').toArray().length;
                var forr;
                if (count>0){
                console.log($(this).parent().next().children('.propertyTable').toArray().length);
                 console.log(leng);
            	   count=leng;
            	}
            })
            
              $(".addnewworkdescription").click(function() {
             
               var counter= $(this).parent().prev('.propertyTable').find("table:first").find('tr').length;
               var ccounter= counter-1;
               $(this).parent().prev('.propertyTable').find("table:first").find('tbody').append('<tr><td>'+counter+'</td><td><div class="form-group"><textarea class="input-md round form-control"  name="propertyTitles['+$(this).attr("id")+'].properties['+ccounter+'].description" placeholder=""></textarea></div></td><td><input style="padding: 4px 9px;margin-bottom:2px;" type="button" id="'+ccounter+'" value="Del" class="del submit_btn btn btn-mod btn-medium btn-round"><input style="padding: 4px 7px;" type="button" id="'+ccounter+'" value="Add" class="adfi submit_btn btn btn-mod btn-medium btn-round"></td></tr>');
             
             }) 
              $(document).on( 'click', '.del', function() {
                 var sno=1; 
                 var 	butt=sno-1; 
                 var 	buttt=sno-1; 
                console.log(butt);
         
                	  var sibl= $(this).parent().parent().siblings();
                	  var siblarray= $(this).parent().parent().siblings().toArray();
                	  for(var sib in siblarray){
                		 
                		    console.dir(siblarray[sib].children[0].innerHTML=sno);
                		    console.log($(siblarray[sib].children[1]).children().children().attr("name",'propertyTitles['+sibbll+'].properties['+butt+'].description'));
                		    console.log($(siblarray[sib].children[2]).children().attr("id",butt));
                		    sno++;
                		    butt++;
                	  }
                	  
                	
               	   var counte= $(this).parent().parent().remove();
               	  
                });
            
             $(document).on( 'click', '.addnewworkdesc', function() {
              var counter= $(this).parent().prev('.propertyTable').find("table:first").find('tr').length;
              var ccounter=counter-1;
         
               $(this).parent().prev('.propertyTable').find("table:first").find('tbody').append('<tr><td>'+counter+'</td><td><div class="form-group"><textarea class="input-md round form-control"  name="propertyTitles['+$(this).attr("id")+'].properties['+ccounter+'].description" placeholder=""></textarea></div></td><td><input style="padding: 4px 9px;margin-bottom:2px" type="button" id="'+ccounter+'" value="Del" class="delll submit_btn btn btn-mod btn-medium btn-round"><input style="padding: 4px 7px;" type="button" id="'+ccounter+'" value="Add" class="adfi submit_btn btn btn-mod btn-medium btn-round"></td></tr>');
    
              }); 
             
             
             $(document).on( 'click', '.delll', function() {
            	 
            	 var sibblin= $(this).parent().parent().parent().parent().parent().next().children().attr("id");
             	 console.log("LAST RESORT");
            	 console.log($(this).parent().parent().parent().parent().parent().attr("id"));
            	 var sibbl=$(this).parent().parent().parent().parent().parent().attr("id");
            	 console.log("SNO"); 
                var 	butt=sno-1; 
                var 	buttt=sno-1; 
    
            	  var sibl= $(this).parent().parent().siblings();
            
            	  var siblarray= $(this).parent().parent().siblings().toArray();
            
            	  for(var sib in siblarray){
            		 
            		    console.dir(siblarray[sib].children[0].innerHTML=sno);
            		    console.log($(siblarray[sib].children[1]).children().children().attr("name",'propertyTitles['+sibbl+'].properties['+butt+'].description'));
            		    console.log($(siblarray[sib].children[2]).children().attr("id",butt));
            		    sno++;
            		    butt++;
            	  }
            	
           	   var counte= $(this).parent().parent().remove();
           	 });
           
             $(document).on( 'click', '.dellete', function() {
            	 var sno=1; 
            	 var cun=0;
            	 var cunn=0;
           
            	   comp=$(this).attr("id");
                   var chil= $(this).parent().parent().children().toArray();
           
        	       for(var c in chil){ 
            		  
            		  if(comp==$(chil[c]).attr("id")) {
                          $(chil[c]).remove();
            		  }
            		  
            	  }
        	
        	             var cnt=0;
            	         var ount=0;
                          for(var cc in chil){ 
                        	
                	 	  if(cnt==4){
                 			  cnt=0;
                 			  ount=ount+1;
                 	}  
                		   if(comp!==$(chil[cc]).attr("id")) {
              
                		 $(chil[cc]).attr("id",ount)
                		 if(cnt==0) {
         
                			var s = $(chil[cc]).attr("id");
                		 $(chil[cc]).children().attr("name",'propertyTitles['+s+'].title');
                		 }
                		 var sec=0;
                		 if(cnt==1) {
        
                			 var ssb = $(chil[cc]).attr("id");
                			var sibarr= $(chil[cc]).children().children("tbody").children().toArray();
                			 for(var sa in sibarr){
$(sibarr[sa].children[1]).children().children().attr("name",'propertyTitles['+ssb+'].properties['+sec+'].description');
                                      sec++;
                			 }
                    		 }
                		 if(cnt==2) {
                             var ii = $(chil[cc]).attr("id");
                             $(chil[cc]).children().attr("id",ii);
                		 }
                		 if(cnt==3) {
                              var ss = $(chil[cc]).attr("id");
                              $(chil[cc]).children().attr("id",ss);
                    		  ss++;
                     		count=ss;
                		 }
              
                		  cnt++;
                		  
                		   }
                		
                		  }   
        	  });
             
             $(document).on( 'click', '.adfi', function() {
            	    $(this).parent().prev().children().children().val($(this).parent().prev().children().children().val()+'__'); 
            	      
             });
             
        });
    </script>


        <jsp:include page="/WEB-INF/views/navbar.jsp" /> 
        
        <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Edit Checklist</li>
			  </ol>
			</nav>
			
  <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">

                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                        Edit Checklist
                    </h2>
                     <c:if test="${not empty success}">
                                        <div class="success">${success}</div>
                                        </c:if>
                                        <c:if test="${not empty fail}">
                                        <div class="error">${fail}</div>
                                        </c:if>
                    <h5>Equipment to be Maintained</h5>
                            <c:url value="/checklist/update" var="update_url"/>
                            <form:form method="post"  action="${update_url}" class="form contact-form" id="contact_form" commandName="checklistheader">
                                        <form:hidden path="id"/>
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Equipment</span>
                                            <form:select id="equipment" path="equipment"   class="input-md round form-control" style="height:100px" placeholder="Select Equipment"  multiple="multiple">
                                            <form:options items="${equipments}" itemValue="id" itemLabel="name" selected="selected"/>
                                            </form:select>
                                        </div>
                                  <div class="form-group">
                                        <c:url value="/faultReport/equipment" var="equipmentlist"/>
                                        <input type="button" onclick="window.open('call-forms/${equipmentlist}','name','width=600,height=600');" value="Select Equipment" class="submit_btn btn btn-mod btn-medium btn-round" id="select_equipment" />
                                </div>
                                <div class="form-group input-group">
                                    <span class="input-group-addon label-left" id="basic-addon2">Checklist Name</span>
                                    <form:input type="text" path="checklistName" name="Checklist_name" id="Checklist_name" class="input-md round form-control" placeholder="Checklist Name"/>
                                </div>
                                <div class="form-group input-group">
                                    <span class="input-group-addon label-left" id="basic-addon2">Brief Description</span>
                                    <form:input type="text"  path="prerequisite"  name="Brief_Description" id="Brief_Description" class="input-md round form-control" placeholder="Brief Description"/>
                                </div>
                                <div class="form-group">
                                        <input type="button" value="Add New Section"  class="submit_btn btn btn-mod btn-medium btn-round" id="addnewsection" name="addnewsection" />
                                </div>
                                <div id="clone"  class="form-group">
                                 <c:forEach items="${checklistheader.propertyTitles}" var="propertytitle" varStatus="titleindex">
                                       
                                        <div  id="0" class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Title</span>
                                            <textarea  name="propertyTitles[${titleindex.index}].title" class="input-md round form-control col-lg-3" placeholder="Title">${propertytitle.title}</textarea>
                                        </div>
                                   
                                  <div id="0" class="form-group propertyTable">
                                        <table class="table table-stripped" >
                                            <thead>
                                                <tr>
                                                    <th>S.No</th>
                                                    <th>Work Description</th>
                                                    
                                                    <th>Delete</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                               <c:forEach items="${propertytitle.properties}" var="properties" varStatus="propertyindex">
                                                <tr>
                                                    <td>${propertyindex.count}</td>
                                                    
                                                    <td>
                                                        <div class="form-group">
                                                            <textarea name="propertyTitles[${titleindex.index}].properties[${propertyindex.index}].description" class="input-md round form-control col-lg-3" placeholder="">${properties.description}</textarea>
                                                        </div>
                                                    </td>
                                                    
                                                    
                                                    <td><input style="padding: 4px 9px;margin-bottom:2px;" id="0"  type="button" value="Del" class="del submit_btn btn btn-mod btn-medium btn-round"><input style="padding: 4px 7px;" id="0"  type="button" value="Add" class="adfi submit_btn btn btn-mod btn-medium btn-round"></td>
                                                </tr>
                                                </c:forEach>
                                           </tbody>
                                        </table>
                                  </div>
                                         
                                  <div id="0" class="form-group parent">
                                      <input id="0" type="button" value="Add New Work Description" class="submit_btn btn btn-mod btn-medium btn-round  addnewworkdescription"  />
                                  </div>
                                  <div id="0" class="form-group">
                                            <input id="0" type="button" value="Delete" class="dellete submit_btn btn btn-mod btn-medium btn-round" />
                                    </div>
                                    
                                   </c:forEach>
                               </div>
                                     
                                    <div class="form-group">
                                            <input type="submit" value="Update" class="submit_btn btn btn-mod btn-medium btn-round" />
                                    </div>
                                    
                            </form:form>
     </div>
     </div>
     </div>
     </div>