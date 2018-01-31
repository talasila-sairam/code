<!DOCTYPE html>
<html lang="en">
<body>
	<head>
		<script src="js/angular.js"></script>
		<script src="js/angular-touch.js"></script>
		<script src="js/angular-animate.js"></script>
		<script src="js/csv.js"></script>
		<script src="js/pdfmake.js"></script>
		<script src="js/vfs_fonts.js"></script>
		<script src="js/ui-grid.js"></script>
		<script src="js/angular-sanitize.js"></script>
		<link rel="stylesheet" href="css/ui-grid.css" type="text/css">
		<link rel="stylesheet" href="css/jquery-ui.css">
		<link rel="stylesheet" type="text/css" href="css/bootstrap-multiselect.css">
		<script src="js/dataConversion.js"></script>    
		<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
		<link href="css/main.css" rel="stylesheet" type="text/css"/>
		<link href="css/manual-style.css" rel="stylesheet" type="text/css"/>
		<link rel="stylesheet" href="css/font-awesome.min.css" type="text/css">
		<script type="text/javascript" src="js/jquery.min.js"></script>
		<script type="text/javascript" src="js/bootstrap.min.js"></script>
		<script src="js/bootstrap-multiselect.js"></script>
		<script type="text/javascript" src="js/ajax.js"></script>
		<script type="text/javascript" src="js/application.js"></script>
	</head>
<%-- <header class="header navbar"  role="banner" id="masthead">
  <div class="container" ><img src="img/AppsTek3-1.png" alt="" title="" >
     <div class="col-md-9 pull-right">
      <nav class="navbar height30">
        <div class="container">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
          </div>
          <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li class="active" id="DataConversion"><a href="#">Data Conversion</a></li>
              <li><a id="userAssignment" href="#about">Admin Task</a></li>
              <li><a id="dashBoard" href="#about">Dash Board</a></li>
            </ul>
            <ul class="nav navbar-nav pull-right">
            <li><b style="padding:5px 10px;"><%= session.getAttribute("username")%></b></li>
              <li><a href="./logoutPageValidation">Logout</a></li>
            </ul>
          </div>
          
          <!--/.nav-collapse --> 
        </div>
      </nav>
    </div>
    </div>
</header> --%>
<header class="header navbar"  role="banner" id="masthead">
  <div class="container" > <div class="col-md-2  paddingleft"><img src="img/AppsTek3-1.png" alt="" title=""></div>
    <div class="col-md-9 pull-right paddingleft">
      <nav class="navbar height30 paddingleft">
        <div class="container paddingleft">
          <div class="navbar-header paddingleft">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
          </div>
          <div id="navbar paddingleft " class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
              <li class="active" id="DataConversion"><a href="#">Data Conversion</a></li>
              <li class="dropdown"><a href="" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Reports</a>
               <ul class="dropdown-menu" id="mainmenu">
                   
                 <!-- <li id="userAssignment"><a href="#">User Assignment</a></li> -->
                </ul>
              </li>
              <li id="QcReport"><a href="#">QC Report</a></li>
            </ul>
            <ul class="nav navbar-nav pull-right">
              <li><b style="padding:5px 10px;"><%= session.getAttribute("username")%></b></li>
              <li><a href="ChangePassword.jsp">Change Password</a></li>
              <li><a href="./logoutPageValidation">Logout</a></li>
            </ul>
          </div>
          
          <!--/.nav-collapse --> 
        </div>
      </nav>
    </div>
  </div>
  <div id="project-switcher" class="container project-switcher">
    <div id="scrollbar">
      <div class="handle"> </div>
    </div>
  </div>
</header>
<body>
<div id="iframediv" style=" text-align:center;" >
         <iframe id="reportPagesDisplay" name="gridDisplay" scrolling="auto" src="" style="width: 100%;height: 540px;border:none;overflow:hidden;"></iframe>
 </div>
  <input type=hidden id="adminmenuAccess" value="<%=session.getAttribute("menuAccess")%>"/>
  <input type="hidden" id="loginedUser" value="<%=session.getAttribute("username")%>"/>
</body>
</html>