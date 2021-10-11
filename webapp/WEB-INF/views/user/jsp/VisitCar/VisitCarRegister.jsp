<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>방문차량 등록</title>
    
    <%-- 공통css/js --%>
	<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>
	<script src="/oneLife/resources/user/js/visitCar/visit_car.js"></script>
	<style>
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


.modal_button > button.close{font-size:13px;}
	</style>
  </head>
  <body>
  	<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>
	
	<div class="bottom_wrap1">
		<p></p>
		<span>> 방문차량 > 방문차량 등록</span>
		<h1>방문차량 등록</h1>
		<div class="bottom_wrap2"></div>
	</div>
  
    <div class="wrap wrap2 contentFull">
      <div class="register_left">
        <img class="blue_car" src="/oneLife/resources/user/images/blue_car.png" />
      </div>
      <div class="register_right">
        <div class="div_desc">
          <p class="register_check">확인하세요!</p>
          <p>
            방문 후 익일 또는 그 이후까지 아파트 내에 주차를 할 예정이면
            해당되는 날짜에 모두 예약하시기 바랍니다
          </p>
        </div>
        <hr />
        <form class="visit_form" action="${contextPath }/visitCarRegister" method="post">
          <div class="div_input">
            <label>방문일</label>
            <input id="date" type="date" name="date" placeholder="방문일을 선택해주세요" required/>
          </div>
          <div class="div_input">
            <label>차량번호</label>
            <input name="carNo" type="text" placeholder="ex)35로5432 띄어쓰기 없이 입력해주세요"  maxlength="11" required />
          </div>
          <div class="div_input">
            <label>방문목적</label>
            <input maxlength="20" name="purpose" type="text" placeholder="ex)에어컨 수리 업체 방문" required/>
          </div>
          <div class="div_input">
            <label>비상연락처</label>
            <input name="phone" type="tel" placeholder="차주의 휴대폰 번호를 입력해주세요(숫자만)" maxlength="15" required/>
          </div>
          <hr />
          <div class="register_button">
            <button class="register_btn myBtn" id="myBtn" type="button">
              등록하기
            </button>
            <div id="myModal" class="modal">
              <!-- Modal content -->
              <div class="modal-content">
                <p class="register_confirm">정말로 등록하시겠습니까?</p>
                <div class="modal_button">
                  <button class="register_ok">확인</button>
                  <button class="close" type="button">취소</button>
                </div>
              </div>
            </div>
            <button class="register_cancel" id="cancle_btn" type="button">
              취소하기
            </button>
            <div id="myModal2" class="modal">
              <!-- Modal content -->
              <div class="modal-content">
                <p class="register_confirm">정말로 취소하시겠습니까?</p>
                <div class="modal_button">
                  <button type="button" class="register_ok" onclick="cancleRegister()">취소</button>
                  <button id="back" class="close" type="button">돌아가기</button>
                </div>
              </div>
            </div>
          </div>
        </form>
      </div>
    </div>
    <script type="text/javascript">
    	function cancleRegister() {
    		location.href = "${contextPath}/visitCar";
    	}
    	
    	
    </script>
    
    <%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>
    
    
  </body>
</html>
