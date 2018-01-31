<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
<title></title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="css/main.css" rel="stylesheet" type="text/css"/>
<link href="css/responsive.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="css/font-awesome.min.css">
<link href='../fonts.googleapis.com/css_F307486B' rel='stylesheet' type='text/css'>
<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<script type="text/javascript" src="js/bootstrap-switch.js"></script>
<script type="text/javascript" src="js/UserAssignment.js"></script>
<script src='http://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js'></script>
<script>
function showCreateUsers() {
    document.getElementById("signUp").innerHTML;
    var createUsers=$('#signUp').val();
};

$(document).ready(function() {
	$("[name='checkbox6']").bootstrapSwitch({
		  on: 'Yes',
		  off: 'No',
		  onLabel: '&nbsp;&nbsp;&nbsp;',
		  offLabel: '&nbsp;&nbsp;&nbsp;',
		  same: false,//same labels for on/off states
		  size: 'xs',
		  onClass: 'warning',
		  offClass: 'default'

		});
	$('#checkbox').on('change',function() {
				if(this.checked){
 				var value	= this.value;
					console.log(value);
	            }
	            else{
	            	
	            	value = "off";	
	            }
	      });
});
function checkPasswordMatch() {
	
    var password = $("#password").val();
    var confirmPassword = $("#confirmPassword").val();
    if (password == confirmPassword)
        $("#confirmPassword").css("border", "1px solid green");
    else
        $("#confirmPassword").css("border", "1px solid red");

}
$(document).ready(function () {
   $("#confirmPassword").keyup(checkPasswordMatch);
});
</script>
<% String style="";%>
</head>
<div class="login-container">
  <div class="row">
    <div class="col-md-12">
      <div class="text-center m-b-md">
        <h4><b>CREATE USER FOR DATA CONVERSION</b></h4>
        <%-- <%if(session.getAttribute("message")==null){
	   style="";
   	}
  else if(session.getAttribute("message").equals("User logged out successfully.")){
	  style="color:green;";
  }
   else{ 
	   style="color:red;";
	}
   %>
   <%if(session.getAttribute("message")!=null){%>
	   <div><span style="<%=style%>"><%=session.getAttribute("message")%></span></div>
	<%}
   	else{}
   %> --%>
        </div>
      <div class="hpanel">
        <div class="panel-body">
          <form action="./SignUpPageValidation" id="loginForm" >
            <div class="form-group">
              <label class="control-label  margin-top5" for="username">Username</label>
              <input type="text" placeholder="" title="Please enter you username" required value="" name="createloginname" id="username" class="form-control" oninvalid="this.setCustomValidity('Please Enter User Name')"
    oninput="setCustomValidity('')" / >
              <span class="help-block small">Your username </span></div>
            <div class="form-group">
              <label class="control-label margin-top5" for="password">Password</label>
              <input type="password" title="Please enter your password" placeholder="******" required value="" name="createpassword" id="password" class="form-control" oninvalid="this.setCustomValidity('Please Enter Password')" oninput="setCustomValidity('')"  />
              <span class="help-block small">Your strong password</span> </div>
              
              <div class="form-group">
              <label class="control-label margin-top5" for="password">Confirm Password</label>
              <input type="password" onChange="checkPasswordMatch();" title="Please confirm your password" placeholder="******" required value=""  name="createconfirmPassword" id="confirmPassword" class="form-control" oninvalid="this.setCustomValidity('Please Enter Valid Password')"
    oninput="setCustomValidity('')"  />
              <span class="help-block small">Your strong password</span> </div>
              <div class="registrationFormAlert" id="divCheckPasswordMatch">
</div>
              <span id='message'></span>
              <label>Admin Access</label>
              
			  		<input type='checkbox' name='checkbox6' id="checkbox">  
            <!-- <button class="btn btn-warning btn-block"><a href="index.html" style="color:#ffffff;">Login</a></button> -->
            <button type="submit" id="signUp" onclick="showCreateUsers()" class="btn btn-warning btn-block" style="color:#ffffff;"> Sign Up  </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>