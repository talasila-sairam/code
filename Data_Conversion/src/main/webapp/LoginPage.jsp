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

<% String style="";%>
</head>
<body style="background:url(img/loginbg.jpg) no-repeat;" class="login">
<header class="header navbar"  role="banner" id="masthead">
  <div class="container" >
    <div class="col-md-2  paddingleft"><img src="img/AppsTek3-1.png" alt="" title="" class="margin-top5"></div>
  </div>
</header>
<div class="login-container">
  <div class="row">
    <div class="col-md-12">
      <div class="text-center m-b-md">
        <h2><b>LOGIN</b></h2>
        <%if(session.getAttribute("message")==null){
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
   %>
        </div>
      <div class="hpanel">
        <div class="panel-body">
          <form action="./loginPageValidation" id="loginForm" >
            <div class="form-group">
              <label class="control-label  margin-top5" for="username">Username</label>
              <input type="text" placeholder="" title="Please enter you username" required value="" name="loginname" id="username" class="form-control">
              <span class="help-block small">Your username </span> </div>
            <div class="form-group">
              <label class="control-label margin-top5" for="password">Password</label>
              <input type="password" title="Please enter your password" placeholder="******" required value="" name="password" id="password" class="form-control">
              <span class="help-block small">Your strong password</span> </div>
            <!-- <button class="btn btn-warning btn-block"><a href="index.html" style="color:#ffffff;">Login</a></button> -->
            <button type="submit" class="btn btn-warning btn-block" style="color:#ffffff;"> Login  </button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>
