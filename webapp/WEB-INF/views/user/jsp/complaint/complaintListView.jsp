<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아파트 민원</title>

<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>
<script src="/oneLife/resources/user/js/cookie.js"></script>
<style>
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
  
}

.modal .bg {
  width: 100%;
  height: 100%;
  background-color: rgba(115, 115, 116, 0.27);
}

.modalBox {
  position: absolute;
  background-color: #fff;
  width: 460px;
  height: 350px;
  padding: 30px;
  border: 1px solid rgba(63, 63, 68, 0.005);
  box-shadow: 0px 1px 0px rgba(63, 63, 68, 0.05),
    0px 1px 3px rgba(63, 63, 68, 0.15);
  border-radius: 10px;
}

.modalBox h2 {
  font-weight: bold;
  font-size: 28px;
  line-height: 24px;
  justify-content: center;
  text-align: left;
  letter-spacing: 0.15px;
  position: relative;
  top: 8%;
  left: 1%;
}

.modalBox span {
  font-size: 21px;
  line-height: 35px;
  letter-spacing: 0.15px;
  color: #5b5b5b;
  position: relative;
  top: 20%;
  left: 1%;
  right: 100px;
  display: block;
  text-align: justify;
}

.closeBtn {
  background: none;
  padding: 20px;
  margin: 10px;
  border-radius: 4px;
  cursor: pointer;
  display: block;
  font-weight: bold;
  font-size: 24px;
  line-height: 24px;
  text-align: center;
  letter-spacing: 1px;
  color: #797979;
  width: 200px;
}

.container1 {
  display: flex;
  justify-content: space-between;
  margin-top: 25%;
  border-top: 1px solid #b8b8b8;
}

.container1 .btn2 {
  color: #137af3;
}

.container1 img {
  height: 50px;
  margin-top: 5%;
}

.hidden {
  display: none;
}

input[type='checkbox']+label:before {
margin-right: 10px;}

.input_area {
  border-bottom: none;
  padding: 0;
  background: none;
}

#searchCondition {
 text-align: center;
 padding: 7px;
 color: #66788a;
 border-radius: 5px;
/*  background:#66788a; */
}
.title span {
 color: #68a2fa;
}

.list_no > ul {height: 50px;}
.list_no { margin-top: 50px;}
.list_no > ul > li > p {font-weight: bold; font-size: 27px; color: #9EA0A5; }

button#mylist {
    margin-right: 750px;
    font-size: 14px;
    color: #ffffff;
    background: #647389;
    padding: 8px;
    border-radius: 4px;
    cursor: pointer;
    margin-top: 5px;
    box-shadow: 0px 1px 1px rgb(0 0 0 / 14%), 0px 2px 1px rgb(0 0 0 / 12%), 0px 1px 3px rgb(0 0 0 / 20%);
}
</style>

</head>
<body>
	<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>
	
	<div class="bottom_wrap1">
		<p></p><span>> 아파트민원</span>
		<h1>아파트 민원</h1>
		<div class="bottom_wrap2">
		</div>
	</div>
	<div class="wrap">
		<div class="complaint_area">
			<div class="complaint_title">
				<h1>간편하게 <b>민원 접수하고<br>처리 결과</b>를 전해 드립니다.</h1>
			</div>
			<div class="search_area">
					<form method="get" action="${ contextPath }/complaint/list" >
					<span class="input_area"> 
						<input type="hidden" name="u_id" value="${ loginUser.u_ID }">
						<button type="submit" id="mylist">내 글만 보기</button>
					</span>
					</form>
					<form method="get" action="${ contextPath }/complaint/list" >
					<select id="searchCondition" name="searchCondition">
						<option value="title" <c:if test="${ param.searchCondition == 'title' }">selected</c:if>>제목</option>
						<option value="content" <c:if test="${ param.searchCondition == 'content' }">selected</c:if>>내용</option>
					</select>
					<span class="input_area2"> 
						<input type="search" name="searchValue" placeholder="검색">
						<button type="submit" id="btn1"><img src="/oneLife/resources/user/images/Search.png"></button>
					</span>
				</form>
			</div>
			<div class="complaint_list">
				<ul class="complaint_header">
					<li class="no">번호</li>
					<li class="title">문의/답변</li>
					<li class="nick">작성자</li>
					<li class="date">작성일</li>
				</ul>
			<!-- 검색시 값이 있는지 없는지 -->	
			<c:choose>
				<c:when test="${ complaintList.size() > 0 }">
				<c:forEach var="c" items="${ complaintList }">
				<ul class="complaint_ul type02">
					<li class="no" >${ c.c_no }</li>
					<c:if test="${ c.c_reply_count <= 0 }">
					<!-- 미완료 상태 -->
					<li class="state"><span>미답변</span></li>
					</c:if>
					<c:if test="${ c.c_reply_count > 0 }">
					<!-- 완료 상태 -->
					<li class="state fi"><span>답변완료</span></li>
					</c:if>
					
					<!-- 비공개 글 -->
					<c:if test="${ c.open == 'N'}">
					<c:choose>
			        <c:when test="${ !empty loginManager || !empty loginUser && loginUser.u_ID == c.u_id}"> 
				    <li class="title" onclick="detailView(${ c.c_no })">${ c.c_title } <img id="lock" src="/oneLife/resources/user/images/lock.png"></li>
					</c:when>
					<c:otherwise>
					<li class="title" onclick="detailView3()"><span>비밀글 입니다. </span><img id="lock" src="/oneLife/resources/user/images/lock.png"></li>
					</c:otherwise>
					</c:choose>
					</c:if>
					<!-- 공개 글 -->
					<c:if test="${ c.open == null }">
					<li class="title" onclick="detailView2(${ c.c_no })">${ c.c_title }</li>
					</c:if>
					<li class="nick">${ c.r_name }</li>
					<li class="date"><fmt:formatDate value="${ c.enroll_date }" pattern="yyyy-MM-dd"/></li>
				</ul>
				</c:forEach>
				</c:when>
				<c:otherwise>
    				<div class="list_no">
         				<ul class="list_noli"> 
							<li><img src="/oneLife/resources/user/images/speech.png" alt="NODATE"> </li>
						    <li><p>게시글이 존재하지 않습니다.</p></li>
                   		</ul>
			        </div>
				</c:otherwise>
				</c:choose>	
			</div>
			
		</div>
		<div class="search_area">
			<form method="get">
				<c:if test="${ !empty loginUser }">
				<button type="button" id="btn2" class="openBtn">문의하기</button>
				</c:if>
				<div class="modal hidden">
					<div class="bg"></div>
					<div class="modalBox">
						<h2>확인해주세요</h2>
						<span>
							산업안전보건법 제41조에 의하여 비속어 
							및 폭언이 포함된 글은 사전 동의 없이
							삭제될 수 있습니다
						</span>
						<div class="container1">
						<button type="button" class="closeBtn btn1" id="disagree_btn">미동의</button>
						<img src="/oneLife/resources/user/images/Line 19.png">
				        <button type="button" class="closeBtn btn2" onclick="location.href='${ contextPath }/complaint/insert'">동의</button>
						</div>
					</div>
				</div>
			</form>
		</div>

		
		<ul class="board_paging">
				<!-- 검색 결과 화면인 경우 넘겨줄 searchParam 정의 -->
				<c:if test="${ !empty param.searchCondition && !empty param.searchValue }">
					<c:set var="searchParam" value="&searchCondition=${ param.searchCondition }&searchValue=${ param.searchValue }"/>
				</c:if>
				
					<!-- 맨 처음으로 (<<) -->
						<li><a href="${ contextPath }/complaint/list?page=1${ searchParam }">&lt;&lt;</a></li>
						
					<!-- 이전 페이지 (<) -->
					<li>
					<c:choose>
						 <c:when test="${ pi.page > 1 }">
						 <a href="${ contextPath }/complaint/list?page=${ pi.page - 1}${ searchParam }" class="btn_prev">&lt;</a>
						 </c:when>
						 <c:otherwise>
						 <a href="#">&lt;</a>
						 </c:otherwise>
					</c:choose>	
					</li>
					
					<!-- 페이지 목록(최대 10개) -->
					<c:forEach var="p" begin="${ pi.startPage }" end="${ pi.endPage }">
					<li>
						<c:choose>
						   <c:when test="${ p eq pi.page }">
						   		<a href="#" class="current_page">${ p }</a>
						   </c:when>
						   <c:otherwise>
						   		<a href="${ contextPath }/complaint/list?page=${ p }${ searchParam }">${ p }</a>
						   </c:otherwise>
						</c:choose>
					</li>
					</c:forEach>
					
					
					<!-- 다음 페이지 (>) -->
					<li>
					<c:choose>
						 <c:when test="${ pi.page < pi.maxPage }">
						 <a href="${ contextPath }/complaint/list?page=${ pi.page + 1}${ searchParam }" class="btn_next">&gt;</a>
						 </c:when>
						 <c:otherwise>
						 <a href="#">&gt;</a>
						 </c:otherwise>
					</c:choose>	
					</li>		
						
					<!-- 맨 끝으로 (>>) -->
						<li><a href="${ contextPath }/complaint/list?page=${ pi.maxPage }${ searchParam }">&gt;&gt;</a></li>	
						
				</ul>
			
	</div>
	
	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>

	<script>
		// 게시글 목록에 mouseover/mouseout 시 onmouseover 클래스 추가/제거 처리
		const complaintList = document.querySelector('.complaint_list');
		
		complaintList.addEventListener('mouseover', function(){
			
			if(event.target.classList.contains('complaint_ul'))
				event.target.classList.add('onmouseover');
			else if(event.target.parentNode.classList.contains('complaint_ul'))
				event.target.parentNode.classList.add('onmouseover');
		});
		
		complaintList.addEventListener('mouseout', function(){
			
			if(event.target.classList.contains('complaint_ul'))
				event.target.classList.remove('onmouseover');
			else if(event.target.parentNode.classList.contains('complaint_ul'))
				event.target.parentNode.classList.remove('onmouseover');
			
		});
		
   </script>
   
   <!-- 모달 창 부분 -->
   <script>
	   const open = () => {
	       document.querySelector(".modal").classList.remove("hidden");
	   }
	
	   const close = () => {
	       document.querySelector(".modal").classList.add("hidden");
	   }
	
	   document.querySelector(".openBtn").addEventListener("click", open);
	   document.querySelector("#disagree_btn").addEventListener("click", close);
	   document.querySelector(".bg").addEventListener("click", close);
    </script>
    
    <script>
     		 function detailView(c_no){
			  		// cno를 쿼리스트링에 데이터로 넘김
			  		location.href='${contextPath}/complaint/detail?c_no='+c_no;
			  	}
     		 function detailView2(c_no){
			  		// cno를 쿼리스트링에 데이터로 넘김
			  		location.href='${contextPath}/complaint/detail?c_no='+c_no;
			  	}
     		 function detailView3(){
			  		alert('비밀글 입니다.');
			  	}
     		 
     	
     </script>
    
    
    
	
	
	  
	
	
</body>
</html>