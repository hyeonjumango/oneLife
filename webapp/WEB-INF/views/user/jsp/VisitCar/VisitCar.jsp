<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>방문차량</title>

<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>
<script src="/oneLife/resources/user/js/visitCar/visit_car.js"></script>

</head>
<body>
	<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>
	
	
	<div class="bottom_wrap1">
		<p></p>
		<span>> 방문차량</span>
		<h1>방문차량</h1>
		<div class="bottom_wrap2"></div>
	</div>
	
	<div class="wrap wrap2">
		<div class="left">
			<img class="blue_car"
				src="/oneLife/resources/user/images/blue_car.png" />
			<p class="visit_car_intro">
				우리 아파트에 방문 예정인<br />차량의 정보를 등록해주세요!
			</p>
			<p class="visit_car_desc">차량 진입 시 더욱 편리하게 방문할 수 있어요!</p>
			<p class="visit_car_desc2">문의사항은 관리사무소로 연락주시기 바랍니다. 031-111-1234</p>
		</div>
		<div class="right">
			<div class="visit_car_button" onclick="goRegister()">
				<img class="register_img1"
					src="/oneLife/resources/user/images/edit.png" />
				<p class="register_p">방문차량 예약</p>
			</div>
			<div class="visit_car_button" onclick="goList()">
				<img class="register_img2"
					src="/oneLife/resources/user/images/clipboard.png" />
				<p class="list_p">방문예약 목록</p>
			</div>
		</div>
	</div>
	
	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>
	
	<script>
    	function goRegister(){
    			location.href = "${contextPath}/visitCarRegister";
    		};
    		
    		function goList(){
    			location.href = "${contextPath}/visitCarList";
    		};
    	
    </script>
</body>
</html>
