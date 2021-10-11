<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원약관동의</title>

<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>

</head>
<body>
	<%-- 공통 menuBar.jsp --%>

	<div class="bottom_wrap1">
		<p></p>
		<span>> 회원가입 > 회원가입 약관동의</span>
		<h1>회원가입 약관동의</h1>
		<div class="bottom_wrap2"></div>
	</div>

	<div class="container border_test">
		<form name="chk_form" id="chk_form" action="#">
			<div class="check_list border_test">
				<div class="check first_check border_test">
					<input type="checkbox" id="check_button1" name=""> <label
						for="check_button1"> <span class="input_chk_all">모두
							동의합니다.</span>
					</label>
				</div>
				<div class="check border_test">
					<input type="checkbox" id="check_button2" name="" class="normal"
						required_check> <label for="check_button2"> <span
						class="input_chk_1">회원 이용약관 동의</span><span class="terms_necessary">(필수)</span>
					</label>
				</div>
				<div class="check_comment border_test">
					<p class="chk_txt_1">
						제 1조 (목적)<br> 본 약관(이하 'Onelife')와 회원에 관한 제반사항을 규정함을 목적으로 합니다.<br>
						제 2조(약관의 효력 등)<br> 약관은 공시하고 상대방이 동의함으로써 효력을 발생합니다.<br>
					</p>
				</div>
				<div class="check border_test">
					<input type="checkbox" id="check_button3" name="" class="normal"
						required_check> <label for="check_button3"> <span
						class="input_chk_2">개인정보 수집 및 이용 동의</span><span
						class="terms_necessary">(필수) </span>
					</label>
				</div>
				<div class="check_comment border_test">
					<p class="chk_txt_2">
						개인정보보호법에 따라 회원가입 신청하시는분께 수집하는 개인 정보의 항목, 개인정보의 수집 및 이용목적,<br>
						개인정보의 보유 및 이용기간, 동의 거부권 및 동의 거부 시 불이익에 관한 사항을 안내 드리오니 자세히 읽은 후
						동의하여 주시길 바랍니다.<br>
					</p>
				</div>
				<div class="check border_test">
					<input type="checkbox" id="check_button4" name="" class="normal">
					<label for="check_button4"> <span class="input_chk_3">프로모션
							정보 수신 동의</span><span class="terms_necessary_c">(선택)</span>
					</label>
				</div>
				<div class="check_comment border_test">
					<p class="chk_txt_3">
						제공하는 이벤트/혜택 등 다양한 정보를 휴대전화(알림 또는 문자), 이메일로 받아보실 수 있습니다.<br>
						일부 서비스(별도 회원 체계로 운영하거나 가입 이후 추가 가입하여 이용하는 서비스 등)의 경우, 개별 서비스에<br>
						대해 별도 수신 동의를 받을 수 있으며, 이때에도 수신 동의에 대해 별도로 안내하고 동의를 받습니다.
					</p>
				</div>
			</div>
			<!--                체크박스 코멘트 끝-->
			<div class="step01_btn_box border_test"></div>
			<div class="step01_btn_box border_test">
				<span> <a href="${ contextPath }" id="" class="cancelBtn"
					role="button">취소</a>
				</span> 
				<span>
					<a href="${contextPath}/userJoin" id="agreeBtn"
						class="agreeBtn" role="button" onclick="return agreeBtn()">확인
					</a>
				</span>
			</div>
		</form>
	</div>

	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>

	<!-- 메뉴 슬라이드 스크립트 -->
	<script>
		$(function() {
			$('.header').mouseenter(function() {
				$('.header').addClass('on');
				$('.header .nav_wrap .nav_inner_wrap').stop().slideDown(300);
			})

			$('.header').mouseleave(function() {
				$('.header').removeClass('on');
				$('.header .nav_wrap .nav_inner_wrap').stop().slideUp(300);
			})

		})
	</script>

	<!-- 모두선택 스크립트 -->
	<script>
		// 체크박스 전체 선택
		$(".check_list").on(
				"click",
				"#check_button1",
				function() {
					var checked = $(this).is(":checked");

					if (checked) {
						$(this).parents(".check_list").find('input').prop(
								"checked", true);
					} else {
						$(this).parents(".check_list").find('input').prop(
								"checked", false);
					}
				});
	</script>

	<!-- 약관 동의 체크박스 개별선택 -->
	<script>
		$(".check_list").on("click", ".normal", function() {
			var is_checked = true;

			$(".check_list .normal").each(function() {
				is_checked = is_checked && $(this).is(":checked");
			});

			$("#check_button1").prop("checked", is_checked);
		});
	</script>

	<!-- 필수 동의! -->
	<script>
		$("#check_button2").change(function() {
			agree_CheckedChanged();
		});

		$("#check_button3").change(function() {
			agree_CheckedChanged();
		});

		//  체크박스 체크 선택 또는 해제할 때 상태 업데이트
		function agree_CheckedChanged() {
			if ($("#check_button2").prop("checked")
					&& $("#check_button3").prop("checked")) {
				console.log("체크");
				$("#agreeBtn").prop("disabled", false); // 활성화
			} else {
				console.log("언 체크");
				$("#agreeBtn").prop("disabled", true); // 비활성화
			}
		}

		function agreeBtn() {
			var chk2 = $('#check_button2').is(':checked');
			var chk3 = $('#check_button3').is(':checked');

			if (!chk2) {
				alert("필수 약관에 동의해주세요.");
				$('#check_button2').focus();
				return false;
			} else if (!chk3) {
				alert("필수 약관에 동의해주세요.");
				$('#check_button3').focus();
				return false;
			} else {
				return true;
				chk_form.submit();
			}

		}
	</script>
</body>
</html>