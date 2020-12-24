<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script>
$(document).ready(function() {
(function(document) {

    var domelement = {

        count: 0,
        bcount: 0,
        init: function() {
            this.cacheDom();
            this.bindEvents();
        },
        cacheDom: function() {
            this.$addsection = $("#addnewsection");
            this.$cloneelem = $("#clone");
            this.$doc = $(document);

        },
        bindEvents: function() {
            this.$doc.on('click', '.addnewworkdescription', this.addNewDesc.bind(this));
            this.$doc.on('click', '.del', this.deleteDesc.bind(this));
            this.$doc.on('click', '#addnewsection', this.addNewSection.bind(this));
            this.$doc.on('click', '.dellete', this.deleteSection.bind(this));
            this.$doc.on('click', '.adfi', this.adddollar.bind(this));
        },
        addNewSection: function(e) {
        	
        	   var $tab = $(e.target).parent().next().children('.propertyTable').toArray();
               var len = $tab.length;
               if (len == 0) {
                   this.count = 0;
               }
               if (len == 1) {
                   len = $tab.length;
                   this.count = len;
               }

               this.$cloneelem.append('<div id="' + this.count + '" class="form-group input-group">' + '<span class="input-group-addon label-left" id="basic-addon2">Title</span>' +'<textarea name="propertyTitles[' + this.count + '].title" class="input-md round form-control" placeholder="Title"></textarea>' + '</div>' + '<div id="' + this.count + '" class="form-group propertyTable">' + ' <table class="table table-stripped">' + '<thead>' + '<tr>' + '<th>S.No</th>' + '<th>Work Description</th>' + '<th>Delete</th>' + '</tr>' + '</thead>' + '<tbody>' + '<tr>' + '<td>1</td>' + '<td>' + '<div class="form-group">' + '<textarea name="propertyTitles[' + this.count + '].properties[0].description" class="input-md round form-control" placeholder=""></textarea>' + '</div>' + '</td>'+ '<td><input style="padding: 4px 9px;margin-bottom: 2px;" type="button" id="' + this.bcount + '" value="Del" class="del submit_btn btn btn-mod btn-medium btn-round"><input style="padding: 4px 7px;" type="button" id="' + this.bcount + '" value="Add" class="adfi submit_btn btn btn-mod btn-medium btn-round"></td>' + '</tr>' + '</tbody>' + '</table>' + '</div>' + '<div id="' + this.count + '" class="form-group parent">' + '<input id="' + this.count + '" type="button" value="Add New Work Description" class="submit_btn btn btn-mod btn-medium btn-round addnewworkdescription"/>' + '</div>' + ' <div id="' + this.count + '" class="form-group">' + ' <input id="' + this.count + '" type="button" value="Delete" class="dellete submit_btn btn btn-mod btn-medium btn-round" />' + '  </div>');
               var $tabl = $(e.target).parent().next().children('.propertyTable').toArray();
               if (this.count > 0) {
                   this.count = $tabl.length;

               }

        },
        addNewDesc: function(e) {
            var $tab = $(e.target).parent().prev('.propertyTable').find("table:first");
            var counter = $tab.find('tr').length;
            var ccounter = counter - 1;

            console.log($tab.find('tbody').append('<tr><td>' + counter + '</td><td><div class="form-group"><textarea class="input-md round form-control"  name="propertyTitles[' + $(e.target).attr("id") + '].properties[' + ccounter + '].description" placeholder=""></textarea></div></td><td><input style="padding: 4px 9px;margin-bottom: 2px;" type="button" id="' + ccounter + '" value="Del" class="del submit_btn btn btn-mod btn-medium btn-round"><input style="padding: 4px 7px;" type="button" id="' + ccounter + '" value="Add" class="adfi submit_btn btn btn-mod btn-medium btn-round"></td></tr>'));

        },
        deleteDesc: function(e) {
            var sno = 1;
            var butid = sno - 1;
            var sibbl = $(e.target).parents(".propertyTable").attr("id");
            var descrrows = $(e.target).parent().parent().siblings().toArray();
            console.log(descrrows);
            var remove = $(e.target).closest('tr').remove();
            for (var sib in descrrows) {
                console.log(descrrows[sib].children[0].innerHTML = sno);
                console.log($(descrrows[sib].children[1]).children().children().attr("name", 'propertyTitles[' + sibbl + '].properties[' + butid + '].description'));
                console.log($(descrrows[sib].children[2]).children().attr("id", butid));
                sno++;
                butid++;
            }
        },
        deleteSection: function(e) {
            var sno = 1;
            var cun = 0;
            var cunn = 0;

            comp = $(e.target).attr("id");
            var chil = $(e.target).parent().parent().children().toArray();
            console.log($(e.target).parent().parent().children("[id !=" + comp + "  ]"));
            console.log($(e.target).parent().parent().children("[id=" + comp + "  ]").remove());

            var cnt = 0;
            var ount = 0;
            for (var cc in chil) {
                if (cnt == 4) {
                    cnt = 0;
                    ount = ount + 1;
                }
                if (comp !== $(chil[cc]).attr("id")) {
                    $(chil[cc]).attr("id", ount)
                    if (cnt == 0) {
                        var s = $(chil[cc]).attr("id");
                        $(chil[cc]).children().attr("name", 'propertyTitles[' + s + '].title');
                    }
                    var sec = 0;
                    if (cnt == 1) {
                        var ssb = $(chil[cc]).attr("id");
                        var sibarr = $(chil[cc]).children().children("tbody").children().toArray();
                        for (var sa in sibarr) {
                            $(sibarr[sa].children[1]).children().children().attr("name", 'propertyTitles[' + ssb + '].properties[' + sec + '].description');
                            sec++;
                        }
                    }
                    if (cnt == 2) {
                        var ii = $(chil[cc]).attr("id");
                        $(chil[cc]).children().attr("id", ii);
                    }
                    if (cnt == 3) {
                        var ss = $(chil[cc]).attr("id");
                        $(chil[cc]).children().attr("id", ss);
                        ss++;
                        this.count = ss;
                    }
                    cnt++;

                }

            }


        },
        adddollar: function(e) {
        	console.log( $(e.target).parent().prev().children().children());
        	
            $(e.target).parent().prev().children().children().val($(e.target).parent().prev().children().children().val() + '__');
        	
        }


    };

    domelement.init();

})(document)

});       
    </script>
    <style>
	     @media all and (max-width: 500px) {
	    	/* .adfi {
	    		margin-right:20px !important
	    		
	    	} 
	        .del {
	    		margin-right:20px !important;
	    	}  */
	    } 
    </style>

     
         <!-- Head Section -->
        	<jsp:include page="/WEB-INF/views/navbar.jsp" /> <div class="vertical-spacer"></div> 
            <nav aria-label="breadcrumb ml-5">
			  <ol class="breadcrumb">
			    <li class="breadcrumb-item"><a href="../dashboard.html">Dashboard</a></li>
			    <li class="breadcrumb-item active" aria-current="page">Checklist Create</li>
			  </ol>
			</nav>
        <!-- End Head Section -->
			
 <div class="container">
	<div class="row">
        <div class="col-md-12">
        	<div class="col-md-10 jumbotron offset-box">

                    <h2 class="section-title font-alt align-left mb-70 mb-sm-40">
                        Create Checklist
                    </h2>
                     <c:if test="${not empty success}">
                                        <div class="success">${success}</div>
                                        </c:if>
                                        <c:if test="${not empty fail}">
                                        <div class="error">${fail}</div>
                                        </c:if>
                    <h5>Equipment to be Maintained</h5>
                            <form:form method="post" class="form contact-form" id="contact_form" commandName="checklistheader">
                                        <div class="form-group input-group">
                                        	<span class="input-group-addon label-left" id="basic-addon2">Select Equipment</span>
                                            <form:select id="equipment" path="equipment"   class="input-md round form-control" style="height:100px" placeholder="Select Equipment"  multiple="multiple">
                                            </form:select>
                                        </div>
                                  <div class="form-group">
                                        <c:url value="/faultReport/equipment" var="equipmentlist"/>
                                        <input type="button" onclick="SelectName()" value="Select Equipment" class="submit_btn btn btn-mod btn-medium btn-round" id="select_equipment" />
                                        <script type="text/javascript">
                                                var popup;
                                                function SelectName() {
                                                popup = window.open("equipment", "Popup", "width=700,height=550,left=300");
                                                popup.focus();
                                                return false
                                                }
                                             </script>
                                </div>
                                <div class="form-group input-group">
                                    <span class="input-group-addon label-left" id="basic-addon2">Checklist Name</span>
                                    <form:input type="text" path="checklistName" name="Checklist_name" id="Checklist_name" class="input-md round form-control" placeholder="Checklist Name"/>
                                </div>
                                <div class="form-group input-group">
                                    <span class="input-group-addon label-left" id="basic-addon2">Description</span>
                                    <form:input type="text"  path="prerequisite"  name="Brief_Description" id="Brief_Description" class="input-md round form-control" placeholder="Brief Description"/>
                                </div>
                                  <div class="form-group">
							        <input type="button" value="Add New Section" class="submit_btn btn btn-mod btn-medium btn-round" id="addnewsection" name="addnewsection">
							    </div>
    <div id="clone" class="form-group">
        <div id="0" class="form-group input-group">
             <span class="input-group-addon label-left" id="basic-addon2">Title</span>
            <textarea name="propertyTitles[0].title" class="input-md round form-control" placeholder="Title"></textarea>
        </div>

        <div id="0" class="form-group propertyTable " style=" margin-top:10px;">
            <table class="table table-stripped table-hover">
                <thead>
                    <tr>
                        <th>S.No</th>
                        <th>Work Description</th>
                       
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                    <tr id="0">
                        <td>1</td>
                        <td>
                            <div class="form-group">
                                <textarea name="propertyTitles[0].properties[0].description" class="input-md round form-control col-lg-3" placeholder=""></textarea>
                            </div>
                        </td>
                        
                        
                      
                        <td>
                            <input style="padding: 4px 9px;margin-bottom: 2px;" id="0" type="button" value="Del" class="del btn btn-mod btn-medium btn-round td">
                            <%-- <img alt="" src="${pageContext.servletContext.contextPath}/resources/assets/images/trash-solid.svg" style="width:20px"> --%>
                            <input style="padding: 4px 7px;" id="0" type="button" value="Add" class="adfi submit_btn btn btn-mod btn-medium btn-round">
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>

        <div id="0" class="form-group parent">
            <input id="0" type="button" value="Add New Work Description" class="submit_btn btn btn-mod btn-medium btn-round  addnewworkdescription tb">
        </div>
        <div id="0" class="form-group" style=" display: inline-block;">
            <input id="0" type="button" value="Delete Section" class="dellete submit_btn btn btn-mod btn-medium btn-round">
        </div>
    </div>  				  <div class="form-group float-right">
                                   <input type="submit" value="Create" class="submit_btn btn btn-mod btn-medium btn-round" />
                              </div>
                     </form:form>
     			</div>
     		</div>
    	</div>
    	</div>

       
