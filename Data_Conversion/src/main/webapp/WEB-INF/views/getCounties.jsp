<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>

<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

<link href="css/main.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/font-awesome.min.css">
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<link href="css/jquery-ui.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/ajax.js"></script>
<script type="text/javascript" src="js/dataConversion.js"></script>
<script>

$(function () {
	$('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
	$('.tree li').addClass('parent_li').find('li').hide();
    $('.tree li.parent_li > span').on('click', function (e) {
        var children = $(this).parent('li.parent_li').find(' > ul > li');
        if (children.is(":visible")) {
            children.hide('fast');
            $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
        } else {
            children.show('fast');
            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
        }
        e.stopPropagation();
    });
});

//To highlight the selected county along with state and country 
$(document).ready(function() {
    $(".tree li a").click(function(){
    	$("#fieldName").val(this.id);
    	 $(".tree li a").removeClass("onClickActive");
    	 $(this).addClass("onClickActive");
    	 $(".tree li a").closest("li.parent_li").find("> span").removeClass("onClickActive");
    	 $(this).closest("li.parent_li").find("> span").addClass("onClickActive");
    	 $(".tree li a").closest("li.parent_li").parent().find(" > span").removeClass("onClickActive");
    	 $(this).closest("li.parent_li").parent().find(" > span").addClass("onClickActive");
    	 
    });
});




function collapseTheTree(){
	
	$("#tree, #collapseDiv").hide("slide", { direction: "left" }, 500);
	$("#expandDiv").show(700);
	$("#rulesFrame").css({"width":"130%","float":"left"});
	$("#filterDiv").hide("slide", { direction: "left" }, 500);
}

function expandTheTree(){
	
	$("#tree, #expandDiv").show("slide", { direction: "left" }, 500);
	$("#expandDiv").hide();
	$("#collapseDiv").show(700);
	$("#rulesFrame").css({"width":"auto","float":"none"});
	$("#filterDiv").show("slide", { direction: "left" }, 500);
}
</script>
</head>
<body>
<input type="hidden" id="countryFipsCode">
<input type="hidden" id="stateFipsCode">
<input type="hidden" id="countyFipsCode">
<input type="hidden" id="countryName">
<input type="hidden" id="stateName">
<input type="hidden" id="countyName">
<input type="hidden" id="stateCode">
<input type="hidden" id="fieldName">
<input type="hidden" id="defaultFlag">
<input type="hidden" id="mappingFieldName">
<input type="hidden" id="ruleLevel">
<input type="hidden" id="fieldNumber">
<input type="hidden" id="loginedUser" value="<%session.getAttribute("username");%>"/>


  <div id = "expandDiv" hidden = true>
  	<i class="icon icon-caret-left" aria-hidden="true"></i>
  	<button id="expand" class="form-control" onclick = "expandTheTree();" style = "height:53px;width:2%;"></button>
  </div>
  
  <div class="col-md-3 col-sm-6 col-xs-12" style = "padding-right: 4px">
    <div>
        <div class="tree well" id="tree">
        <ul>
         <li> <span><a href="javascript:getRulesFieldwise('','','','','','','global','','','','','')"><i class="icon-cog"></i>RULES</a></span></li>
        <c:forEach items="${countries}" var="countries">
        	<li> <span><i class="icon-folder-open"></i> ${countries.countryName}</span>
        	<ul>
			<li> <span><a href="javascript:getRulesFieldwise('${countries.countryCode}','','','${countries.countryName}','','','country','','','','','')"><i class="icon-cog"></i>RULES</a></span></li>
			<c:forEach items="${countries.states}" var="states">
              <li> <span><i class="icon-plus-sign"></i> ${states.stateName}</span>
                <ul>
				<li><span><a href="javascript:getRulesFieldwise('${countries.countryCode}','${states.fipsCode}','','${countries.countryName}','${states.stateName}','','state','${states.stateCode}','','','','')"><i class="icon-cog"></i>RULES</a></span></li>
					<c:forEach items="${states.counties}" var="counties">
					  <li> <span><i class="icon-plus-sign"></i> ${counties.countyName}</span>
					  <ul>
					  <li> <span><a href="javascript:getRulesFieldwise('${countries.countryCode}','${states.fipsCode}','${counties.countyFipsCode}','${countries.countryName}','${states.stateName}','${counties.countyName}','county','${states.stateCode}','','','','')"><i class="icon-cog"></i>RULES</a></span></li>
						<c:forEach items="${counties.listOfFields}" var="fields">
								<li ><span ><a id="${fields.fieldName}" href="javascript:getRulesFieldwise('${countries.countryCode}','${states.fipsCode}','${counties.countyFipsCode}','${countries.countryName}','${states.stateName}','${counties.countyName}','field','${states.stateCode}','${fields.fieldNumber}','${fields.defaultFlag}','${fields.mappingFieldName}')"><i class="icon-leaf"></i> ${fields.fieldNumber}-${fields.fieldName}-(${fields.ruleCount})</a></span></li>
						</c:forEach>
					  </ul>
		              </li>
		          	</c:forEach>
                </ul>
              </li>
              </c:forEach>
             </ul>
             </li>
        </c:forEach>
        </ul>
      </div>
    </div>
  </div>
  <div id = "collapseDiv">
  	<i class="icon icon-caret-right" aria-hidden="true"></i>
  	<button id="collapse" class="form-control" onclick = "collapseTheTree();" style = "height:53px;width:2%"></button>
  </div>
   <div id = "alertmsgdivs"></div>
  <span id="conformationDialog" class="modalDialog" ></span>
  
</body>
</html>