<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>에러페이지</title>
<jsp:include page="/WEB-INF/views/admin/common/link.jsp"></jsp:include>
</head>
<body>
	 <div id="wrap">
    	<%-- header 공통 적용 --%>
        <jsp:include page="/WEB-INF/views/admin/common/header.jsp"></jsp:include>
        
        <div class="container">
        <%-- menubar 공통 적용  --%>
        <jsp:include page="/WEB-INF/views/admin/common/menubar.jsp"></jsp:include>
        
        <h1>${msg}</h1>
	</div>
</body>
</html>