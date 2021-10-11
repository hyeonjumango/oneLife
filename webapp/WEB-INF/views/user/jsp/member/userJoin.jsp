<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원정보입력</title>

<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>

<style>
.container img {
	height: auto;
	margin-top: 0;
}
</style>

</head>
<body>
	<%-- 공통 menuBar.jsp --%>

	<div class="bottom_wrap1">
		<p></p>
		<span>> 회원가입 > 회원가입 약관동의 > 회원정보</span>
		<h1>회원정보입력</h1>
		<div class="bottom_wrap2"></div>
	</div>

	<div class="container border_test2">
		<form name="join_form"
			action="<%=request.getContextPath()%>/userJoin" method="post"
			onsubmit="return validate();">
			<div class="condition_list border_test2">
				<div class="check step2_first_div border_test2">
					<div class="step2_span_left">아이디</div>
					<div class="step2_span_right">
						<input type="text" id="id" name="userId" class="step2_input_text"
							placeholder="영문자 및 숫자 포함 5자 이상 입력" required> <span><a
							href="javascript:;" id="idCheck" class="ol_btn" role="button">중복확인</a>
						</span>
					</div>
				</div>
				<div>
					<hr>
				</div>
				<div class="check step2_first_div border_test2">
					<div class="step2_span_left">이름</div>
					<div class="step2_span_right">
						<input type="text" id="name" name="name" class="step2_input_text"
							required>
					</div>
				</div>
				<div>
					<hr>
				</div>
				<div class="check step2_first_div border_test2">
					<div class="step2_span_left">닉네임</div>
					<div class="step2_span_right">
						<input type="text" class="step2_input_text" name="nickName"
							id="nickname" placeholder="한글 및 숫자 포함 2자 이상 입력" required>
						<span><a href="javascript:;" id="nickCheck" class="ol_btn"
							role="button">중복확인</a></span>
					</div>
				</div>
				<div>
					<hr>
				</div>
				<div class="input check step2_first_div border_test2">
					<div class="step2_span_left">비밀번호</div>
					<div class="step2_span_right">
						<input type="password" id="pwd1" name="userPwd"
							class="pwd1 step2_input_text " placeholder="영문 및 숫자 포함 7자 이상 입력"
							required> <span class="eyes"> <i
							class="fa fa-eye-slash fa-lg"></i>
						</span><span id="pwd_result"></span>
					</div>
				</div>
				<div>
					<hr>
				</div>
				<div class="check step2_first_div border_test2">
					<div class="step2_span_left">비밀번호 확인</div>
					<div class="step2_span_right">
						<input type="password" id="pwd2" class="pwd2 step2_input_text"
							placeholder="영문 및 숫자 포함 7자 이상 입력" required>
					</div>
				</div>
				<div>
					<hr>
				</div>
				<div class="check step2_first_div border_test2">
					<div class="step2_span_left">연락처</div>
					<div class="step2_span_right">
						<input type="text" id="phone" name="phone"
							class="step2_input_text" maxlength="11" required>
					</div>
				</div>
				<hr>
				<div class="check step2_first_div border_test2">
					<div class="step2_span_left">이메일</div>
					<div class="step2_span_right">
						<input id="emailAddress" name="email" type="email"
							class="step2_input_text" placeholder="'@'필수 입력" required>
						<span class="email_result">※관리사무소에 제출한 이메일을 적어주세요.</span> <span>
							<button id="mailBtn" class="numberChkbtn" type="button">인증번호
								전송</button>
						</span>
					</div>
				</div>
				<div>
					<hr>
				</div>

				<div class="check step2_first_div border_test2">
					<div class="step2_span_left">이메일 인증번호</div>
					<div class="step2_span_right">
						<input type="text" id="numberCheck"
							class="step2_input_text_emailNum" placeholder="이메일 인증번호를 입력해주세요"
							required> <span>
							<button type="button" id="numberBtn" class="numberChkbtn">
								인증번호 확인</button>
						</span>
					</div>
				</div>
				<hr>
			</div>
	<!--체크박스 코멘트 끝-->
	<div class="step01_btn_box border_test2"></div>
	<div class="step01_btn_box border_test2">
		<span><a href="${ contextPath }" id="cancelBtn"
			class="cancelBtn" role="button">취소</a> </span> <span>
			<button id="agreeBtn" class="agreeBtn" type="submit" disabled>다음</button>
		</span>
	</div>
	</form>
	</div>

	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>

	<!-- 비밀번호 일치 불일치 -->
	<script>
    pwd1.addEventListener('input',()=>{
        if(pwd1.value != pwd2.value) {// 일치안했다
            pwd1.classList.add('invalid');
            pwd2.classList.add('invalid');
            pwd_result.innerHTML = '비밀번호가 일치하지 않습니다.';
        } else {                      // 일치했다?
            pwd1.classList.remove('invalid');
            pwd2.classList.remove('invalid');
            pwd_result.innerHTML = '';
        }
    });

    pwd2.addEventListener('input', ()=>{ 
        if(pwd1.value != pwd2.value) { 
            pwd1.classList.add('invalid');
            pwd2.classList.add('invalid');
            pwd_result.innerHTML = '비밀번호가 일치하지 않습니다.';
        } else { 
            pwd1.classList.remove('invalid');
            pwd2.classList.remove('invalid');
            pwd_result.innerHTML = '';
        }
    });
</script>

	<script>
    document.forms.join_form.onsubmit = () => {
        // 1. 아이디 검사
        // 첫 글자는 반드시 영문 소문자로 시작하고
        // 영문자 및 숫자 포함 5자 이상
        if(!check(/^[a-z\d]{5,}$/, document.getElementById('id'),
            "아이디는 영문 소문자로 시작하여 영문과 숫자로만 5자 이상 입력하세요"))
        return false;   
        
        // 2. 닉네임 검사
        // 한글 및 숫자 포함 2자 이상 입력
        if(!check(/[가-힣\d]{2,}$/, document.getElementById('nickname'),
        "한글 및 숫자 포함 2자 이상 입력하세요"))
        return false;
        

        // 3. 비밀번호 검사
        // 영문 및 숫자 포함 7자 이상 입력
        if(!check(/^[a-z\d]{7,}$/,
        document.getElementById('pwd1'),
        "비밀번호는 영문 및 숫자 포함 7자 이상 입력하세요"))
        return false;
        
        if($('#pwd1').val()!=$('#pwd2').val()){
            alert('비밀번호가 일치하지 않습니다.');
            $('#pwd1').focus();
            return   false;
        }
            

        // 4. 연락처
        if(!check(/^[0-9]{8,11}$/,
        document.getElementById('phone'),
        "연락처를 입력하세요"))
        return false;
       
        // 5. 이메일 검사
        // '@' 필수 입력
        if(!check(/^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i,
        document.getElementById('email'),
        "이메일 주소에 '@'를 포함하세요"))
        return false;

    };
    
    // 유효성 검사용 함수 세미
    function check(regExp, input, msg) {
        // 전달 받은 정규 표현식과 사용자 입력 양식의 값이 패턴 일치하면 true 리턴
        if(regExp.test(input.value))
        return true;
        // 전달 받은 정규 표현식과 사용자 입력 양식의 값이 패턴 일치하지 않으면
        alert(msg);
        input.value=''; // 다시 입력하라고 비워줌
        input.focus(); 
        return false;
    }
    
    var emailcheck = false;
	var isUsable = false;
    
     // 아이디 중복 
     $("#idCheck").click(function(){
		 // input userId 변수
		 var userId = $("[name=userId]");
		 // 아이디 중복 시 false, 아이디 사용 가능 시 true
		 
		 if(!userId || userId.val().length < 4) {
			 alert('아이디는 최소 4자리 이상이어야 합니다.');
			 userId.focus();
		 } else {
			 // 4자리 이상의 userId 값을 입력 했을 경우 중복 확인을 위해 ajax 통신 요청
			 $.ajax({
				 url : "${ contextPath }/idCheck",
				 type : "post",
				 data : { userId : userId.val() },
				 success : function(result) {
					 console.log(result);
					 if(result == "fail") {
						 alert("사용할 수 없는 아이디입니다.");
						 userId.focus();
					 } else {
						 if(confirm('사용 가능한 아이디입니다. 사용하시겠습니까?')) {
							 // 더 이상 id 입력 공간을 수정할 수 없도록 readonly 처리
							 userId.attr('readonly', true);
							 isUsable = true; // 사용 가능한 아이디라는 flag 값
						 } else {
							// confirm 창에서 취소를 누를 경우 (처음, 또는 반복해서) 다시 id 수정 가능하도록
							userId.attr('readonly', false);
							userId.focus();
							isUsable = false;	// 사용 불가능한 아이디라는 flag 값
						 }
					 }
					 // 아이디 중복 체크 후 중복이 아니며 사용하겠다고 선택한 경우에만
					 // joinBtn disabled 제거
					 console.log(isUsable);
					 console.log(emailcheck);
					 console.log(nickUsable);
					 if(isUsable && emailcheck && nickUsable) {
						 $("#agreeBtn").removeAttr("disabled"); // 사용하겠다고 선택한 경우 가입하기 버튼 활성화 !
					 } else {
						 $("#agreeBtn").attr("disabled", true); // 비활성화!
					 }
						 
				 },
				 error : function(e) {
					 console.log(e);
				 }
			 });
		 }
	 });
</script>

	<script>
	var nickUsable = false;
     // 닉네임 중복
     $("#nickCheck").click(function(){
		 // input nickName 변수
		 var nickName = $("[name=nickName]");
		 // 닉네임 중복 시 false, 닉네임 사용 가능 시 true
		 nickUsable = false;
		 
		 if(!nickName || nickName.val().length < 2) {
			 alert('닉네임는 최소 2자리 이상이어야 합니다.');
			 nickName.focus();
		 } else {
			 // 2자리 이상의 nickName 값을 입력 했을 경우 중복 확인을 위해 ajax 통신 요청
			 $.ajax({
				 url : "${ contextPath }/nickCheck",
				 type : "post",
				 data : { nickName : nickName.val() },
				 success : function(result) {
					 console.log(result);
					 if(result == "fail") {
						 alert("사용할 수 없는 닉네임입니다.");
						 nickName.focus();
					 } else {
						 if(confirm('사용 가능한 닉네임입니다. 사용하시겠습니까?')) {
							 // 더 이상 nickname 입력 공간을 수정할 수 없도록 readonly 처리
							 nickName.attr('readonly', true);
							 nickUsable = true; // 사용 가능한 닉네임이라는 flag 값
						 } else {
							// confirm 창에서 취소를 누를 경우 (처음, 또는 반복해서) 다시 id 수정 가능하도록
							nickName.attr('readonly', false);
							nickName.focus();
							nickUsable = false;	// 사용 불가능한 아이디라는 flag 값
						 }
					 }
					 console.log(isUsable);
					 console.log(emailcheck);
					 console.log(nickUsable);
					 if(isUsable && emailcheck && nickUsable) {
						 $("#agreeBtn").removeAttr("disabled"); // 사용하겠다고 선택한 경우 가입하기 버튼 활성화 !
					 } else {
						 $("#agreeBtn").attr("disabled", true); // 비활성화!
					 }
					 
						 
				 },
				 error : function(e) {
					 console.log(e);
				 }
			 });
		 }
	 });
</script>
	<!-- 비밀번호 눈 표시  -->
	<script>
	$(".eyes").on('click',function(){ 
         $('#pwd1').toggleClass('active'); 
         if( $('#pwd1').hasClass('active') == true ){ 
                $(this).find('.fa-eye-slash').attr('class',"fa fa-eye fa-lg");
                $('#pwd1').attr('type',"text");
                $('#pwd2').attr('type',"text");
            } else{ 
                $(this).find('.fa-eye').attr('class',"fa fa-eye-slash fa-lg");
                $('#pwd1').attr('type','password'); 
                $('#pwd2').attr('type','password'); 
                } 
     });
</script>

	<script>
		$(function() {
			$("#mailBtn").click(function() {
				alert("해당 메일로 인증번호를 전송하였습니다.")
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
							emailcheck = true;
			                alert("인증번호 확인이 완료되었습니다.");
			              } else {
			            	  alert("인증번호를 다시 확인하시기 바랍니다.");
			              }
						 console.log(isUsable);
						 console.log(emailcheck);
						 console.log(nickUsable);
						 if(isUsable && emailcheck && nickUsable) {
							 $("#agreeBtn").removeAttr("disabled"); // 사용하겠다고 선택한 경우 가입하기 버튼 활성화 !
						 } else {
							 $("#agreeBtn").attr("disabled", true); // 비활성화!
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