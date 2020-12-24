var parameterCounter = 1;
			function deleteParameter(pparameterCounter) {
				var element = $("#tr" + pparameterCounter);
				element.remove();
				if ((parameterCounter - 1) != pparameterCounter) {
					setIds(pparameterCounter);
				}
				parameterCounter--;
			}
			function setIds(pparameterCounter) {
				var ccount = 1;
				var chname = "";
				for ( var i = 1; i < parameterCounter; i++) {
					if (i != pparameterCounter) {
						chname = "baseline[" + ccount + "].name";
						chnameu = "baseline[" + ccount + "].min";
						chnamev = "baseline[" + ccount + "].max";
						chnameunit = "baseline[" + ccount + "].unit";
						chnamebaseline = "baseline[" + ccount + "].equipmentbaseline";
						$("input[name='baseline[" + i + "].name']")
								.attr("name", chname);
						$("input[name='baseline[" + i + "].min']")
								.attr("name", chnameu);
						$("input[name='baseline[" + i + "].max']")
								.attr("name", chnamev);
						$("input[name='baseline[" + i + "].unit']")
						.attr("name", chnameunit);
						$("input[name='baseline[" + i + "].equipmentbaseline']")
						.attr("name", chnamebaseline);
						ccount++;
					}
				}
			}
			$(function() {
				$('#add_prop').click(
								function() {
									var table = document
											.getElementById("prop");
									var row = table
											.insertRow((parameterCounter + 1));
									var att = document.createAttribute("id");
									att.value = "tr" + parameterCounter;
									row.setAttributeNode(att);
									var cell1 = row.insertCell(0);
									var cell2 = row.insertCell(1);
									var cell3 = row.insertCell(2);
									var cell4 = row.insertCell(3);
									var cell5 = row.insertCell(4);
									var baselineValues = $("#baselineId").val();
									cell1.innerHTML = "<input type='text' name='baseline[" + parameterCounter + "].name' data-bvalidator='required'><input type='hidden' name='baseline[" + parameterCounter + "].equipmentbaseline' value='" + baselineValues + "'></a>";
									cell2.innerHTML = "<input type='text' name='baseline[" + parameterCounter + "].min' data-bvalidator='required'>";
									cell3.innerHTML = "<input type='text' name='baseline[" + parameterCounter + "].max' data-bvalidator='required'>";
									cell4.innerHTML = "<input type='text' name='baseline[" + parameterCounter + "].unit' data-bvalidator='required'>";
									cell5.innerHTML = "<a href='javascript:deleteParameter("
											+ parameterCounter
											+ ")' ><i class='delete'></i></a>";
									parameterCounter++;
								});
			});