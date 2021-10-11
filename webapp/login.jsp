<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="contextPath" value="${pageContext.servletContext.contextPath }" scope="application"/>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>로그인</title>

<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>

<%-- 로그인 되어있을 상태일시 --%>
<c:if test="${!empty loginUser || !empty loginManager}">
	<script>
		alert('이미 로그인 상태입니다. 메인페이지로 이동합니다.');
		location.href = '${contextPath}/main';
	</script>
</c:if>
<%
	if (session.getAttribute("msg") != null) {
%>
<script>
	alert('<%=session.getAttribute("msg")%>');
</script>
<%
	session.removeAttribute("msg");
}
%>
</head>
<body>
	<div id="wrap">
		<div class="login_wrap">
			<div class="items slide">
				<div class="slide_wrap">
					<div class="swiper-wrapper">
						<div class="swiper-slide">
							<img src="resources/user/images/slide1.jpg" width="100%" alt="광고">
						</div>
						<div class="swiper-slide">
							<img src="resources/user/images/slide2.jpg" width="100%" alt="광고">
						</div>
						<div class="swiper-slide">
							<img src="resources/user/images/slide3.jpg" width="100%" alt="광고">
						</div>
						<div class="swiper-slide">
							<img src="resources/user/images/slide4.jpg" width="100%" alt="광고">
						</div>
						<div class="swiper-slide">
							<img src="resources/user/images/slide5.jpg" width="100%" alt="광고">
						</div>
					</div>
					<div class="swiper-pagination"></div>
				</div>
			</div>
			<div class="items">
				<div class="inner">
					<h1>
						<span class="ir_so">ONE LIFE KH Apartment Community</span>
					</h1>
					<p class="error">아이디 또는 비밀번호를 다시 확인해주세요.</p>
					<form action="<%=request.getContextPath()%>/login" method="post">
						<div class="form_item">
							<div class="input">
								<input type="text" name="userId" id="userId">
								<p class="tip">아이디를 입력해주세요.</p>
							</div>
							<div class="input">
								<input type="password" name="userPwd" id="userPwd">
								<p class="tip">비밀번호를 입력해주세요.</p>
							</div>
							<div class="checkbox">
								<input type="checkbox" name="idSave" id="idSave"> <label
									for="idSave">ID 기억하기</label>
							</div>
							<div class="btn">
								<button onclick="return loginBtn();" class="login_btn">로그인</button>
							</div>
							<ul class="link">
								<li><a href="<%=request.getContextPath()%>/findIdPwd">아이디/비밀번호 찾기</a></li>
								<li><a href="<%=request.getContextPath()%>/userAgree">회원가입</a></li>
							</ul>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="${contextPath}/resources/user/js/cookie.js"></script>
	<script src="${contextPath}/resources/user/js/swiper-bundle.min.js"></script>
	<script>
		// 아이디저장하기 쿠키
		// userId input 태그
		// const userId = document.querySelector("#userId");

		// remember checkbox 태그
		const remember = document.querySelector("#idSave");
		// 저장 된 쿠키 값 가져오기(cookieName : rememberId)
		let rememberId = getCookie("rememberId");
		console.log(rememberId);

		// input 태그에 해당 값 넣기 (없을 경우 공백)
		userId.value = rememberId;

		// 위의 코드를 통해 input 태그에 값이 들어간 경우 아이디 저장하기 기능을 사용하고 있었으므로
		if (userId.value != '') {
			// 아이디 저장하기 체크 박스를 선택한 상태로 만들기
			remember.checked = true;
		}

		// 체크박스에 변화가 있을 경우
		remember.addEventListener('change', function() {
			if (this.checked) {
				// ID 저장하기 체크시
				// id에 입력 된 값 가져와서 쿠키 7일 동안 보관 
				setCookie("rememberId", userId.value, 7);

			} else {
				// ID 저장하기 체크 해제 시
				deleteCookie("rememberId");
			}
		})

		// 체크 박스에 체크한 상태에서 userid 입력할 경우
		userId.addEventListener('keyup', function() {
			// ID 저장하기 체크 시
			if (remember.checked) {
				// id에 입력 된 값 가져와서 쿠키 7일 동안 보관
				setCookie("rememberId", userId.value, 7);
			}
		})

		// 아이디,비밀번호 포커스 이벤트
		const inputs = document.querySelectorAll('.input input');
		for (let i = 0; i < inputs.length; i++) {
			inputs[i].addEventListener('focus', function() {
				inputs[i].classList.add('hide');
			});

			inputs[i].addEventListener('blur', function() {
				inputCheck(this);
			});

			// 첫 로딩시 검사
			inputCheck(inputs[i]);
		};

		function inputCheck(input) {
			let size = input.value.length;
			if (size > 0) {
				input.classList.add('hide');
			} else {
				input.classList.remove('hide');
			}
		}

		// 로그인 체크
		function loginBtn() {
			let userPw = document.querySelector("#userPwd");
			//let userId = document.querySelector("#userId");

			// 아이디 체크
			if (userId.value.trim().length <= 0) {
				alert("아이디를 입력해주세요.");
				userId.focus();
				return false;
			} else if (userPw.value.trim().length <= 0) {
				alert("비밀번호를 입력해주세요.");
				userPw.focus();
				return false;
			}
			return true;

		}

		// 로그인 슬라이드
		let swiper = new Swiper(".slide_wrap", {
			loop : true,
			autoplay: true,
			delay : 3000,	
			pagination : {
				el : ".swiper-pagination",
				clickable : true,
			},
		});
	</script>
	<script>
		$(function() {
			$("#mailBtn").click(function() {
				$.ajax({
					url : "${contextPath}/mailVerification",
					data : {
						email : $("#emailAddress").val()
					},
					type : "post",
					success : function(result) {
						$('#emailAddress').attr('readonly', true);
					},
					error : function(e) { //에러 발생시 에러 매개변수 전달
						console.log(e);
					}
				});
			});
			
			$('#numberBtn').click(function(){
				$.ajax({
					url : "${contextPath}/numberCheck",
					data : {
						number : $("#numberCheck").val()
					},
					type : "post",
					success : function(result) {
						if (result == "success") {
			                $("#joinBtn").removeAttr("disabled");
			                alert("인증번호 확인이 완료되었습니다.")
			              } else {
			            	  alert("인증번호를 다시 확인하시기 바랍니다.")
			              }
					},
					error : function(e) { 
						console.log(e);
					}
				});
			})
		});
	</script>
</body>
</html>