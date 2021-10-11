<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>아이디 찾기</title>
<% if(session.getAttribute("msg") != null) { %>
<script>
	alert('<%= session.getAttribute("msg")%>');
</script>
<% 
	session.removeAttribute("msg");
	} 
%>
<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>

</head>
<body>
	
	<div class="bottom_wrap1">
		<p></p>
		<span>> 아이디/비밀번호 찾기 </span>
		<h1>아이디 비밀번호 찾기</h1>
		<div class="bottom_wrap2"></div>
	</div>
	<div class="step4_container border_test">
		<div id="sub" class="sub login">
			<div class="login">
				<div class="size border_test">
					<div class="find_wrap border_test">
						<div class="find_tab">
							<ul class="clear">
								<li><a href="javascript:;" class='on border_test'>아이디
										찾기</a></li>
								<li><a href="javascript:;" class='border_test'>비밀번호 찾기</a>
								</li>
							</ul>
						</div>
						<div class="find_form">
							<div class="find_id">
								<form name="board" id="board" method="post" action=""
									onsubmit="return idCheck(this);">
									<fieldset>
										<input type="hidden" name="cmd" value="searchid" /> <input
											type="hidden" name="init" value="id" />
										<div class="in_size">
											<div class="login_box">
												<p class="clear">
													<em>이름</em> <span><input type="text" name="name" /></span>
												</p>
												<p class="clear">
													<em>이메일</em> <span><input type="text" id="email"
														name="email" /></span>
												</p>
											</div>
											<div class="login_btn">
												<input type="submit" id="findIdBtn" value="아이디 찾기" />
											</div>
										</div>
									</fieldset>
								</form>
							</div>
							<div class="find_pw">
								<form name="board" id="board" method="post" action=""
									onsubmit="return pwCheck(this);">
									<fieldset>
										<input type="hidden" name="cmd" id="cmd" value="searchpw" /> <input
											type="hidden" name="init" value="pw" />
										<div class="in_size">
											<div class="login_box">
												<p class="clear">
													<em>아이디</em> <span><input type="text" name="userId" /></span>
												</p>
												<p class="clear">
													<em>이름</em> <span><input type="text" name="name" /></span>
												</p>
												<p class="clear">
													<em>이메일</em> <span><input type="text" name="email" /></span>
												</p>
											</div>
											<div class="login_btn">
												<input type="submit" value="비밀번호 찾기" />
											</div>
										</div>
									</fieldset>
								</form>
							</div>
						</div>
					</div>
					<div class="login_util clear">
						<a href="<%= request.getContextPath() %>/userAgree">회원가입</a> <a
							href="<%= request.getContextPath() %>">로그인</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>
</body>
<script>
	$(function(){
	    $('.find_wrap .find_form .find_'+'id').siblings().css('display','none');
	    $('.find_wrap .find_form .find_'+'id').css('display','block');
	
	    $('.find_wrap .find_tab ul li a').on('click',function(){
	        var idx = $(this).parent().index();
	        $(this).parent().siblings().children().removeClass('on');
	        $(this).addClass('on');
	        if(idx == 0 ){
	            $('.find_wrap .find_form .find_id').siblings().css('display','none');
	            $('.find_wrap .find_form .find_id').css('display','block');
	        }else{
	            $('.find_wrap .find_form .find_pw').siblings().css('display','none');
	            $('.find_wrap .find_form .find_pw').css('display','block');
	        }
	    });
	});
</script>

<script>
	$(function() {
		$("#findIdPwd").click(function() { // 메일을 전송하는 버튼을 누를시
			$.ajax({
				url : "${contextPath}/findIdPwd", // 서블릿 url
				data : {
					email : $("#email").val()
				},
				type : "get",
				success : function(result) {
					$('#email').attr('readonly', true); // 메일이 써져있는 인풋박스
				},
				error : function(e) { //에러 발생시 에러 매개변수 전달
					console.log(e);
				}
			});
		});
	});
	
</script>
</html>