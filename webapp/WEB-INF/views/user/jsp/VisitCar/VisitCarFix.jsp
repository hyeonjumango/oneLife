<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>방문차량 내역 변경 및 삭제</title>
<script src="https://kit.fontawesome.com/1be9c91961.js"
	crossorigin="anonymous"></script>
<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>
<script src="/oneLife/resources/user/js/visitCar/fixDelete_Visit.js"></script>
<style>
.fix_wrap {
	margin: 0 auto;
	justify-content : center;
	min-height: calc(100vh - 382px);
}

	/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  /* background-color: rgb(0, 0, 0); Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 15% auto; /* 15% from the top and centered */
  padding: 15px;
  border: 1px solid #888;
  width: 400px; /* Could be more or less, depending on screen size */
  border-radius: 10px;
  display: flex;
  justify-content: center;
  flex-direction: column;
}

.modal_button {
  margin: 0 auto;
}
.modal_button > button {
  width: 100px;
  height: 30px;
  display: inline-block;
  border-radius: 8px;
  margin-top: 5px;
  font-weight: 600;
}
.register_ok {
  background-color: var(--main-color--);
  border-width: 0;
  color: white;
}

.close {
  background-color: white;
  border: 1.5px solid lightgray;
}
.register_ok:hover {
  cursor: pointer;
}

.close:hover,
.close:focus {
  cursor: pointer;
}

.register_confirm {
  font-size: 20px;
  margin: 0 auto;
  text-align: center;
}

.fa-edit + p {
text-align : center;
}
.modal_button > button.close{font-size:13px;}
</style>
</head>
<body>
	<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>

	<div class="bottom_wrap1">
		<p></p>
		<span>> 방문차량 > 방문차량 등록</span>
		<h1>방문차량 수정</h1>
		<div class="bottom_wrap2"></div>
	</div>
	<div class="fix_wrap">
		<div class="cofirm_notice">
			<i class="far fa-edit"></i>
			<p>
				등록번호 <span class="fix_reg_no">${visitCarDetail.VC_ID}</span>
			</p>
		</div>

		<form class="fix_form" action="${contextPath }/visitCarFix"
			method="post">
			<input name="vid" type="hidden" value="${visitCarDetail.VC_ID}" />
			<div class="div_input">
				<label>방문일</label> <input id="date" name="date" type="date"
					value="${visitCarDetail.VC_DATE}" required />
			</div>
			<div class="div_input">
				<label>차량번호</label> <input name="carNo" type="text"
					value="${visitCarDetail.VC_NO}" maxlength="11" required />
			</div>
			<div class="div_input">
				<label>방문목적</label> <input name="purpose" type="text" maxlength="20"
					value="${visitCarDetail.VC_PURPOSE}" required />
			</div>
			<div class="div_input">
				<label>비상연락처</label> <input name="phone" type="tel"
					value="${visitCarDetail.VC_PHONE}" maxlength="15" required />
			</div>
			<div class="fix_button">
				<button class="register_btn myBtn" id="fixBtn" type="button">
					변경하기</button>
				<div id="fixModal" class="modal">
					<div class="modal-content">
						<p class="register_confirm">정말로 변경하시겠습니까?</p>
						<div class="modal_button">
							<button class="register_ok">확인</button>
							<button type="button" class="close" id="fixCancel">취소</button>
						</div>
					</div>
				</div>
				<button class="register_cancel" id="deletebtn" type="button">
					삭제하기</button>
				<button class="register_cancel" type="button" onclick="goList()">목록으로</button>
				<div id="deleteModal" class="modal">
					<div class="modal-content">
						<p class="register_confirm">정말로 삭제하시겠습니까?</p>
						<div class="modal_button">
							<button type="button" class="register_ok"
								onclick="deleteVisitCar()">확인</button>
							<button type="button" id="deleteCancel" class="close">취소</button>
						</div>
					</div>
				</div>
			</div>
		</form>
		<form name="visitForm" method="post">
			<input type="hidden" name="vid" value="${visitCarDetail.VC_ID}">
		</form>

	</div>
	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>
	<script>
		function deleteVisitCar() {
			document.forms.visitForm.action = "${contextPath}/visitCarDelete";
			document.forms.visitForm.submit();
		}
		function goList(){
			location.href = "${contextPath}/visitCarList";
		};
	</script>
</body>
</html>
