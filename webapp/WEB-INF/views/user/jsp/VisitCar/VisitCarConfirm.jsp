<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<%
	String message = (String)request.getAttribute("msg");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/1be9c91961.js"
	crossorigin="anonymous"></script>
<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>
<style>
.confirm_wrap {
	width: 1200px;
	display: flex;
	flex-direction: column;
	justify-content : center;
	align-items: center;
	margin: 0 auto;
	min-height: calc(100vh - 284px);
}

.cofirm_notice {
	display: flex;
	flex-direction: column;
	justify-content: center;
}

.fa-check-circle {
	margin: 0 auto;
	font-size: 110pt;
	color: var(--main-color--);
}

.fa-check-circle+p {
	margin: 0 auto;
	font-size: 20pt;
	color: var(--font--color--);
	font-weight: 600;
	margin-top: 10px;
}

.confirm_btn>button {
	background-color: var(--main-color--);
	border-width: 0;
	color: white;
	width: 120px;
	height: 40px;
	border-radius: 7px;
	margin: 3px 5px;
	box-shadow: inset 0 0 0 0 #3c90f2;
	transition: ease-out 0.3s;
	font-weight: 600;
}

.confirm_btn>button:hover {
	box-shadow: inset 150px 0 0 0 #3c90f2;
	cursor: pointer;
}

.confirm_information {
	display: flex;
}

.info_title {
	font-weight: 600;
	margin-bottom: 0;
	color: var(--desc--font--);
	margin-top: 16px;
}

.info_content {
	margin-top: 0;
}

.registerNO {
	color: var(--main-color--);
	margin-top: 0;
}

.confirm_registerNO {
	width: 380px;
	border-bottom: 1px solid lightgray;
}

.confirm_information {
	width: 380px;
}

.info_right {
	margin-left: 95px;
}

.confirm_content {
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0px 0px 15px lightgray;
	margin: 30px auto;
}

.confirm_content p {
	font-size: 13pt;
	margin-bottom: 16px;
}

.info_margin_bottom {
	margin-bottom: 0;
}
</style>
</head>
<body>
	<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>

	<div class="confirm_wrap">
		<div class="cofirm_notice">
			<i class="far fa-check-circle"></i>
			<p><%=message %></p>
		</div>
		<div class="confirm_content">
			<div class="confirm_registerNO">
				<p class="info_title registerNO">등록번호</p>
				<p class="info_content">${visitCarDetail.VC_ID}</p>
			</div>
			<div class="confirm_information">
				<div class="info_left">
					<p class="info_title">방문일</p>
					<p class="info_content">${visitCarDetail.VC_DATE}</p>
					<p class="info_title">차량번호</p>
					<p class="info_content info_margin_bottom">${visitCarDetail.VC_NO}</p>
				</div>
				<div class="info_right">
					<p class="info_title">방문목적</p>
					<p class="info_content">${visitCarDetail.VC_PURPOSE}</p>
					<p class="info_title">비상연락처</p>
					<p class="info_content info_margin_bottom">${visitCarDetail.VC_PHONE}</p>
				</div>
			</div>
		</div>
		<div class="confirm_btn">
			<button type="button" onclick="goRegister()">추가 등록하기</button>
			<button type="button" onclick="fix(${visitCarDetail.VC_ID})">변경 및 삭제하기</button>
			<button type="button" onclick="goList()">내역 보기</button>
		</div>
	</div>
	<script>
    	function goRegister(){
    			location.href = "${contextPath}/visitCarRegister";
    		};
    		
    		function goList(){
    			location.href = "${contextPath}/visitCarList";
    		};
    		
    		function fix(vid){
    			location.href = '${contextPath}/visitCarFix?vid=' + vid;
    		}
    	
    </script>
	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>
</body>
</html>