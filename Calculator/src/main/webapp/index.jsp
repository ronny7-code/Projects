<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Calculator</title>
<style>
img{
	height:17%;
	width:17%;
	margin-top:20px;
}
body{
text-align:center;
background: linear-gradient(to right, #2b40ff, #07121a);
}
input, button{
font-size:23px;
font-family:MV Boli;
color:green;
border-radius:5%;
background-color:black;
border: 2px solid white;
}
h1, h3{
color:white;
margin:15px;
}
img:hover{
-ms-transform: scale(1.1);
-webkit-transform: scale(1.1);
transform: (1.1);
}


</style>

</head>
<body>

<img src="images\cat.gif">
<h1>Hey 😉</h1>

<form action="${pageContext.request.contextPath}/answer" method="POST">
<input name="num1" placeholder="Enter first number">
<input name="num2" placeholder="Enter second number">

<button name="operator" value="+">+</button>
<button name="operator" value="-">-</button>
<button name="operator" value="*">*</button>
<button name="operator" value="/">/</button>
</form>

</body>
</html>