<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>
<%-- 관리자 session 이름 : loginManager --%>
	<%-- 로그인이 안된경우 로그인페이지로 이동하기위한 조건문 --%>
	<c:choose>
		<c:when test="${empty loginUser && empty loginManager}">
		<%-- 만약 로그인이 안되있는 상태일시 --%>
			<script>
				alert('로그인 먼저 진행해주세요. 로그인 페이지로 이동합니다.');
				location.href= '${contextPath}/';
			</script>
		</c:when>
		<c:when test="${!empty loginUser}">
			<%-- 만약 로그인유저가 로그인상태일시 --%>
			<script>
				alert('주민유저는 관리자 페이지를 이용하실수 없습니다. 사용자 페이지로 이동합니다.');
				location.href= '${contextPath}/main';
			</script>
		</c:when>
	</c:choose>

	<%-- 사용자 페이지 이동버튼 --%>
    <c:if test="${!empty loginManager}">
       	<style>
        	.page_wrap{position:fixed; right:20px; bottom:20px;}
        	.page_wrap a{display:block; width:60px; height:60px; border-radius:50%; line-height:60px; background:#fff; box-sizing:border-box; text-align:center; border:1px solid #333; transition: all .3s;}
        	.page_wrap a:hover{background:#3c90f2; color:#fff; border-color:#3c90f2;}
        </style>
        <div class="page_wrap">
        	<a href="${contextPath}/main">
        		사용자
        	</a>
        </div>
      </c:if>

  <%-- header  --%>
  <header id="header">
       <div class="header">
           <h1>
               <a href="${contextPath}/admin/">
                   <span class="ir_so">ONE LIFE LOGO</span>
               </a>
           </h1>
           <div>
               <p><span>${loginManager.mJobName}</span>님 반갑습니다.</p>
               <%-- 세팅버튼클릭시 개인정보 수정 팝업창 열림 --%>
               <a href="javascript:popShow('userInfo');" class="setting"></a>
               <%-- 로그아웃시 사용자 로그인페이지로 이동 --%>
               <a href="javascript:logout();" class="logout"></a>
           </div>
       </div>
   </header>
   
   <!-- 개인정보 변경팝업 -->
   <%-- 관리자 페이지에서 톱니바퀴 아이콘 클릭시 개인정보 변경할수있는 팝업창 열림 --%>
   <div class="popup_wrap" id="userInfo">
      <div class="dim"></div>
      <div class="item">
	      <form name="userInfoFrm">
	      	<input type="hidden" name="m_No" value="${loginManager.mNo}">
	         <div class="new_admin">
	             <div class="title">개인정보 변경</div>
	             <div class="new_item">
	                 <label for="m_Id">계정</label>
	                 <input type="text" name="m_Id" id="m_Id" value="${loginManager.mId}" readonly>
	             </div>
	             <div class="new_item">
	                  <label for="m_Password">현재 비밀번호</label>
	                  <input type="password" name="m_Password" id="m_Password">
	              </div>
	              <div class="new_item">
	                  <label for="m_Password2">변경 비밀번호</label>
	                  <input type="password" name="m_Password2" id="m_Password2">
	              </div>
	              <div class="new_item">
	                  <label for="m_Phone">휴대폰 번호</label>
	                  <input type="text" name="m_Phone" id="m_Phone" value="${loginManager.mPhone}">
	              </div>
	         </div>
         </form>
         <div class="new_btn_box">
            <a href="javascript:userInfoChange();">변경하기</a>
            <a href="javascript:popHide('userInfo');">취소하기</a>
         </div>
        </div>
    </div>
    
    <%-- 정보변경 후 팝업창 --%>
    <div class="popup_wrap" id="complete">
        <div class="dim"></div>
        <div class="item">
            <h3 class="tit">${msgHead}</h3>
            <p class="txt">${msgBody}</p>
            <div class="btn_box">
                <a href="javascript:popHide('complete');" class="ok">확인</a>
            </div>
        </div>
    </div>
    
    
    
    
    
    <script>	
 	// 공통 팝업창 열기
 	function popShow(tag){
    	document.querySelector('#' + tag).classList.add('pop_on');
    }
 	
 	// 공통 팝업창 닫기
 	function popHide(tag){
 		if(confirm("팝업창을 닫으시겠습니까?")){
 			document.querySelector('#' + tag).classList.remove('pop_on');
 		}
    }
 	
 	// 로그아웃
 	function logout(){
 		location.href= "${contextPath}/admin/logout";
 	}
 	
 	// 개인정보 변경
 	function userInfoChange(){
 		let userInfoFrm = document.forms.userInfoFrm;
 		let passwordOld = $('#m_Password');
 		let passwordNew = $('#m_Password2');
 		let phone = $('#m_Phone');
 		
 		// 입력했는지
 		if(passwordOld.val().length <= 0){
 			alert('현재 비밀번호를 입력해주세요!');
 			passwordOld.select();
 			return;
 		}
 		
 		if(passwordNew.val().length <= 0){
 			alert('변경 비밀번호를 입력해주세요!');
 			passwordNew.select();
 			return;
 		}
 		
 		// 현재 비밀번호와 변경 비밀번호가 같은지 같으면 retrun;
 		/* if(passwordOld.val() === passwordNew.val()){
 			alert("서로 다른 비밀번호를 입력해주세요!");
 			passwordNew.select();
 			return;
 		} */
 		
 		if(phone.val().length <= 0){
 			alert('휴대폰 번호를 입력해주세요!');
 			phone.select();
 			return;
 		}
 		
 		// 휴대폰번호 유효성 검사
 		let regPhone = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
        if (regPhone.test($('#m_Phone').val()) === false) {
            alert("예) 010-1234-5678 양식으로 입력해주세요!");
            phone.select();
 			return;
        }
 		
 		// 폼 전송
 		userInfoFrm.method = 'post';
 		userInfoFrm.action = '${contextPath}/admin/infoChange';
 		userInfoFrm.submit();
 		
 	}
    </script>
    
    <%-- msg값 출력 --%>
	<c:choose>
		<c:when test="${!empty msgHead && !empty msgBody}">
			<script>
				popShow('complete');
			</script>
			<c:remove var="msgHead" scope="session" />
			<c:remove var="msgBody" scope="session" />
		</c:when>
	</c:choose>
    
    