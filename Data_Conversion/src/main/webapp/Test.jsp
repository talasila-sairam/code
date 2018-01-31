<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/bootstrap.min.js"></script>
<style type="text/css">
	.span3{
		background: #b3cdda;		
	}
	.span9{
		background: #d9e6ee;	
	}
	.span3, .span9{
		min-height: 250px;
	}
</style>
</head>
<body>
<div class="container">
        <!--Row with three equal columns-->
        <div class="row">
            <div class="col-md-4">
                <div class="demo-content">.col-md-4</div>
            </div>
            <div class="col-md-4">
            	<div class="demo-content bg-alt">.col-md-4</div>
            </div>
            <div class="col-md-4">
            	<div class="demo-content">.col-md-4</div>
            </div>
        </div>
        <hr>
        <!--Row with three columns divided in 1:4:1 ratio-->
        <div class="row">
            <div class="col-md-2">
            	<div class="demo-content">.col-md-2</div>
            </div>
            <div class="col-md-8">
            	<div class="demo-content bg-alt">.col-md-8</div>
            </div>
            <div class="col-md-2">
            	<div class="demo-content">.col-md-2</div>
            </div>
        </div>
        <hr>
        <!--Row with three columns divided unevenly-->
        <div class="row">
            <div class="col-md-3">
            	<div class="demo-content">.col-md-3</div>
            </div>
            <div class="col-md-7">
            	<div class="demo-content bg-alt">.col-md-7</div>
            </div>
            <div class="col-md-2">
            	<div class="demo-content">.col-md-2</div>
            </div>
        </div>
    </div>
</body>
</html>