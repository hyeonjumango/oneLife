<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%-- 공통 css/js --%>
<title>인사말</title>
<link rel="stylesheet"
	href="/oneLife/resources/user/css/apt/aptIntro.css" />
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>
<style>
.greeting_title {
	margin-bottom: 20px;
}

.greetingContent {
	font-size: 20px;
}
</style>
</head>
<body>
	<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>
	<div class="wrap">
		<img
			src="/oneLife/resources/user/images/low-angle-shot-high-rise-buildings-clear-sky-frankfurt-germany.jpg"
			alt="아파트" class="apt_img" />
		<div class="greeting_title">
			<p class="title">
				<span class="titleEmp">KH아파트</span> 방문을 환영합니다!
			</p>
		</div>
		<div class="greetingContent">
			<p>
				<span class="contentEmp">남다른 생활 인프라로 <br /> 모두가 꿈꾸는 아파트 생활을
					만들어 가겠습니다. <br /></span> <br /> 존경하는 입주민 여러분 안녕하십니까! <br /> 이번 우리 아파트는
				주민들의 편리한 생활을 위해 <br /> 아파트 커뮤니티를 오픈하였습니다. <br /> <br /> 더 투명하고
				깨끗한 아파트 관리를 통해 주민들이 살기 좋은 <br /> 아파트를 만들어가겠습니다. <br /> 커뮤니티의 장을 통해
				더 새롭고 다양한 아파트 관리 정보와 <br /> 주민 간의 소통과 교류가 이루어지길 바라며, <br /> 살기 좋은
				KH아파트가 되겠습니다. <br /> <br /> 앞으로 아파트 관리 발전을 위해 아낌없는 성원과 지원을 부탁드립니다.
				<br /> KH아파트를 사랑해주시고 아껴주시는 입주민 여러분, <br /> 항상 건강하시고 행복이 가득하시길
				바랍니다. <br /> 감사합니다.
			</p>
		</div>
	</div>
	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>
</body>
</html>
