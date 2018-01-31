<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<script src="js/bootstrap-multiselect.js"></script>
<link rel="stylesheet" href="css/bootstrap-multiselect.css">
<script type="text/javascript">
    $(document).ready(function() {
        $('#fields').multiselect({
            /* enableFiltering: true, */
            enableCaseInsensitiveFiltering : true
            filterBehavior: 'value',
            nonSelectedText: '--Please Select--',
            disableIfEmpty: true
        });
        
    });
    
    $(document).ready(function(){ 
        $(window).scroll(function(){ 
            if ($(this).scrollTop() > 100) { 
                $('#scroll').fadeIn(); 
            } else { 
                $('#scroll').fadeOut(); 
            } 
        }); 
        $('#scroll').click(function(){ 
            $("html, body").animate({ scrollTop: 0 }, 600); 
            return false; 
        }); 
    });
</script>
</head>
<body>
<div>
  <div  class="col-md-9 col-sm-6 col-xs-12">
			<div class="widget box">
			      <div class="widget-header">
			        <h4>Rule</h4>
			      </div>
			      <div class="form-group">
			        <div class="col-md-12 headertop">
			          <div class="row">
			          <div class="tab-pane">
				          <div class="padding">
				          <c:if test="${dataConversionDto.countyFipsCode ne null }">
				          <div class="col-md-3">
			              <label class="control-label">Fields</label>
							<select id="fields" style="width: 54% !important;" class="form-control" multiple="multiple">
							    <c:forEach items="${fields}" var="fields">
							    	<option value="${fields.fieldName}">${fields.fieldName}</option>
							    </c:forEach>
							</select>
						</div>
						</c:if>
				         <div class="col-md-6 margin-top41 ">
				         	 <button id="addRule" class="btn btn-success  pull-right margin-right" onclick="getCountyTables();"> <span class="glyphicon glyphicon-plus"></span>
			 				Add Rule</button>

			             </div>
				     </div>
				     <div class="row">
		                <div class="col-md-12"> </div>
		              </div>
				       <div id="countyTableColumnsDiv">
					     <c:forEach items="${rules}" var="rule">
					     <script> $("#showRule").show();</script>
					     	<div class="form-group">
				                <label class="control-label col-md-2 text-right margin-top47">${rule.ruleMaster.ruleName} <span class="required">*</span></label>
				                <div class="col-md-4">
				                  <textarea rows="3" cols="3" name="textarea" class="auto form-control margin-top20" style="overflow: hidden; word-wrap: break-word; resize: horizontal; height: 68px;">
				                  ${fn:trim(rule.rule)}</textarea>
				                </div>
				                <div class="col-md-4">
				                  <div class="btn-group margin-top40">
				                    <button class="btn btn-default margin-right">Edit</button>
				                    <button class="delete btn btn-warning" onclick="deleteTheRule(${rule.ruleMaster.ruleId})">Delete</button>
				                  </div>
				                </div>
				              </div>
					     </c:forEach>
			           </div>
					</div>
					</div>
		        </div>
		      </div>
		      <div class="divider" style="clear:both; padding-top:20px;"></div>
			</div>
			<a href="javascript:void(0);" id="scroll" title="Scroll to Top" style="display: none;">Top<span></span></a>
   </div>
 </div>
</body>
</html>