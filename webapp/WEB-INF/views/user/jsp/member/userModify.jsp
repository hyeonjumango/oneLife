<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="user.member.model.vo.Member"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
	// session 객체에 담긴 loginUser 정보 변수에 담기
	Member loginUser = (Member)session.getAttribute("loginUser");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원정보수정</title>

<style>
.container img {
	height: auto;
	margin-top: 0;
}

.carInfo {
display : flex;
}

.carInfoDetail ul:last-of-type{
margin-bottom : 10px;
}

.carInfoul li {
/* display : inline-block; */
display: table-cell;
  vertical-align: middle;
font-size : 15px;
text-align : left;
vertical-align: middle;
padding-top : 8px;
padding-bottom : 8px;
}

.carInfoul:not(:last-of-type) {
border-bottom : 1px solid lightgray;
}

.carInfoNo {
width : 60px;
}

.carInfoNo + li {
width : 30px;
}

.carInfoCarNo {
width : 80px;
}

.carInfoCarNo + li {
width : 80px;
}

.carInfoApplicant {
width : 60px;
}

.carInfoApplicant + li{
width : 60px;
}
.carInfoPhone {
width : 80px;
}
.carInfoPhone + li{
width : 100px;
}

</style>

<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>

</head>
<body>
	<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>

	<div class="bottom_wrap1">
		<p></p>
		<span>> 회원정보 </span>
		<h1>회원정보</h1>
		<div class="bottom_wrap2"></div>
	</div>
	<div class="step6_container border_test6">
		<form name="modify_form" id="modify_form" class="modify_form"
		 action="<%= request.getContextPath() %>/userModify" method="post" onsubmit="return validate();">
			<div class="condition_list border_test6">
				<div class="check step2_first_div border_test6">
					<div class="step2_span_left">아이디</div>
					<div class="step2_span_right">
						<input type="text" name="userId" value="<%= loginUser.getU_ID() %>" class="step2_input_text_n" readonly required>
					</div>
				</div>
				<div>
					<hr>
				</div>
				<div class="check step2_first_div border_test6">
					<div class="step2_span_left">이름</div>
					<div class="step2_span_right">
						<input type="text" name="name" value="<%= loginUser.getR_NAME() %>"class="step2_input_text_n" readonly required>
					</div>
				</div>
				<div>
					<hr>
				</div>
				<div class="check step2_first_div border_test6">
						<div class="step2_span_left">닉네임</div>
					<div class="step2_span_right">
						<input type="text" value="<%= loginUser.getU_NICKNAME() %>" class="step2_input_text" name="nickName"
							name="nickName" id="nickname" oninput="buttonCheck()" placeholder="한글 및 숫자 포함 2자 이상 입력" required>
						<span><a href="javascript:;" id="nickCheck" class="ol_btn"
							role="button">중복확인</a></span>
					</div>
				</div>
				<div>
					<hr>
				</div>
				<div class="input check step2_first_div border_test6">
					<div class="step2_span_left">비밀번호</div>
					<div class="step2_span_right">
						<input type="password" value="<%= loginUser.getU_PW() %>" name="userPwd" id="pwd1" class="pwd1 step2_input_text"
							placeholder="영문 및 숫자 포함 7자 이상 입력" readonly required>
							<span class="eyes">
								<i class="fa fa-eye-slash fa-lg"></i>
							</span>
							<button class="ol_btn" id="pwdUpdateBtn" type="button"
							onclick="openPopup('<%= request.getContextPath() %>/pwdModify', 'pwdModify', 500, 400);">
							비밀번호 변경
						</button>
					</div>
				</div>
				<div></div>

				<div>
					<hr>
				</div>
				<div class="check step2_first_div border_test6">
					<div class="step2_span_left">연락처</div>
					<div class="step2_span_right">
						<input type="text" name="phone" id="phone" value="<%= loginUser.getU_PHONE() %>" class="step2_input_text" required>
					</div>
				</div>
				<div>
					<hr>
				</div>
				<div class="check step2_first_div border_test6">
					<div class="step2_span_left">이메일</div>
					<div class="step2_span_right">
						<input type="text" name="email" value="<%= loginUser.getR_EMAIL() %>"class="step2_input_text_n" readonly required>
					</div>
				</div>
				<div>
					<hr>
					<div class="check step2_first_div border_test6 carInfo">
						<div class="step2_span_left">등록차량정보</div>
						<div class="step2_span_right carInfoDetail">
						
						<c:if test="${fn:length(memberCarList) eq 0}">
							<p>등록된 차량 정보가 없습니다.</p>
						</c:if>
						<c:forEach var="v" items="${memberCarList}">
						<ul class="carInfoul">
							<li class="carInfoNo">등록번호</li> 
							<li>${v.mcId}</li>
							<li class="carInfoCarNo">차량번호</li>
							<li>${v.mcNo}</li>
							<li class="carInfoApplicant">신청인</li>
							<li>${v.rName}</li>
							<li class="carInfoPhone">비상연락처</label>
							<li>${v.cPhone}</li>
						</ul>
						</c:forEach>
							<span class="step6_span_office"> 
							<img src="${contextPath}/resources/user/images/call.png" alt="re" class="re">
								입주차량등록은 관리사무소로 연락주시기 바랍니다.
							</span>
						</div>
					</div>
					<div>
						<hr>
					</div>
					<div class="check step2_first_div border_test6">
					<div class="step2_span_left">거주세대</div>
						<input type="text" name="dong" value="<%= loginUser.getR_DONG() %>"class="step2_input_text_dong_n" readonly
							required>
						<div class="step2_span_dongho">동</div>
						<input type="text" name="ho" value="<%= loginUser.getR_HO() %>"class="step2_input_text_ho_n" readonly required>
						<div class="step2_span_dongho">호</div>
					</div>
					<div>
						<hr>
					</div>
				</div>
				<!-- 체크박스 코멘트 끝-->
				<div class="step01_btn_box border_test6"></div>
				<div class="step01_btn_box border_test6">
					<span >
						<button id="modifyBtn" class="step6_updateBtn" type="submit" disabled>수정하기</button>
					</span> <span>
						<button type="button" id="delete" class="step6_deleteBtn"
							onclick="userDelete()">탈퇴하기</button>
					</span>
				</div>
		</form>
	</div>
	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>


<script>
buttonCheck();

		function buttonCheck(){
			var loginUser =  '<%=session.getAttribute("loginUser")%>';
			var nickName =  '<%=session.getAttribute("loginNickName")%>';
			var inputNickName = $("#nickname").val(); //현재 인풋 박스에 입력돼있는 닉네임
			if(nickName == inputNickName){
				 $("#modifyBtn").removeAttr("disabled"); // 사용하겠다고 선택한 경우 가입하기 버튼 활성화 !
				 $('#modifyBtn').css('background-color','#4094F7');
				 $("#nickCheck").hide();
			}else{
				 $("#modifyBtn").attr("disabled", true); // 비활성화!
				 $('#modifyBtn').css('background-color','#E9E9E9');
				 $("#nickCheck").show();
			}
			
		}
</script>
<!-- 정규화 -->
<script>
    document.forms.modify_form.onsubmit = () => {
    // 1. 닉네임 검사
    // 한글 및 숫자 포함 2자 이상 입력
    if(!check(/[가-힣\d]{2,}$/, document.getElementById('nickname'),
    "한글 및 숫자 포함 2자 이상 입력하세요"))
    return false;
    

    // 4. 연락처
    if(!check(/^[0-9]{11}$/,
    document.getElementById('phone'),
    "올바른 연락처를 입력하세요"))
    return false;
};

    // 유효성 검사용 함수 세미
    function check(regExp, input, msg) {
        if(regExp.test(input.value))
        return true;

        alert(msg);
        input.value=''; // 다시 입력하라고 비워줌
        input.focus(); 
        return false;
    }
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
					 console.log(nickUsable);
					 if(nickUsable) {
						 $("#modifyBtn").removeAttr("disabled"); // 사용하겠다고 선택한 경우 가입하기 버튼 활성화 !
						 $('#modifyBtn').css('background-color','#4094F7');
						 $("#nickCheck").hide();
					 } else {
						 $("#modifyBtn").attr("disabled", true); // 비활성화!
						 $('#modifyBtn').css('background-color','#E9E9E9');
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
           } else{ 
               $(this).find('.fa-eye').attr('class',"fa fa-eye-slash fa-lg");
               $('#pwd1').attr('type','password'); 
               } 
           });
</script>


<script>
    // 팝업창 호출
	function openPopup(url, title, width, height) {
		let left = (document.body.clientWidth/2) - (width/2)
		// 듀얼모니터를 위한 계산
		left += window.screenLeft;
		let top = (screen.availHeight/2) - (height/2);
		 
		let options = "width="+width+",height="+height+",left="+left+",top="+top;
		 
		window.open(url, title, options);
	}
    
    // 탈퇴 버튼 클릭 시 호출
    function userDelete(){
    	if(confirm("정말로 탈퇴하시겠습니까?"))
    		location.href='<%=request.getContextPath()%>/userDelete';
    }
</script>


</body>
</html>