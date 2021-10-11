<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String message = (String)request.getAttribute("msg");
%>
<html>
<head>
<meta charset="UTF-8">
<title>errorpage</title>
<style>
	#imageArea {
		width : 500px;
		margin : 100px auto;
		display : flex;
		justify-content : center;
		align-itmes : center;
	}
	
	#imageArea img{
		width: 100%;
	}
</style>
</head>
<body>
	<!-- 모든 페이지에 include 할 menubar.jsp 생성 -->
	<h1 align="center"><%=message%></h1>
</body>
</html>