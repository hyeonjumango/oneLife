<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>		
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>투표하기</title>

<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>

<style>
.fi span {
 background: #DFF4FF;
 border: 1px solid #00A7FF;
 color: #00A8FF;
 padding: 5px 11px;
 
} 
.go span {
    -webkit-animation: blink 1s linear infinite;
} 
@-webkit-keyframes blink {
    0% { color: red; }
    50% { color: white; }
    100% { color: red; }

}

.list_no > ul {height: 50px;}
.list_no { margin-top: 50px;}
.list_no > ul > li > p {font-weight: bold; font-size: 27px; color: #9EA0A5; }


</style>
</head>
<body>
	<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>
	
	<div class="bottom_wrap1">
		<p></p><span>> 아파트소식 > 투표하기</span>
		<h1>투표하기</h1>
		<div class="bottom_wrap2">
		</div>
	</div>
	<div class="wrap">
		<div class="vote_area">
			<div class="vote_title">
				<h1>비대면으로 안전하게, <b>언제 어디서든<br>투표</b>를 진행해보세요!</h1>
			</div>
			<div class="search_area">
				<form method="get" action="${ contextPath }/vote/list">
					<select id="searchCondition" name="searchCondition">
							<option value="title" <c:if test="${ param.searchCondition == 'title' }">selected</c:if>>제목</option>
							<option value="content" <c:if test="${ param.searchCondition == 'content' }">selected</c:if>>내용</option>
					</select>
					<span class="input_area"> 
					<input type="search" name="searchValue" placeholder="검색">
					<button type="submit" id="btn1"><img src="/oneLife/resources/user/images/Search.png"></button>
					</span>
				</form>
			</div>
			<div class="vote_list">
				<ul class="vote_header">
					<li class="no">번호</li>
					<li class="title">제목</li>
					<li class="nick">작성자</li>
					<li class="date">기간</li>
					<li class="count">조회</li>
				</ul>
				
			<!-- 검색시 값이 있는지 없는지 -->	
			<c:choose>
				<c:when test="${ voteList.size() > 0 }">
				<c:forEach var="v" items="${ voteList }">
				<c:if test="${ !empty loginManager }">
				<ul class="vote_ul" onclick="detailView(${ v.v_no })">
				</c:if>
				<c:if test="${ !empty loginUser }">
				<ul class="vote_ul" onclick="detailUserView(${ v.v_no })">
				</c:if>
					<fmt:parseDate value='${v.v_modify_date}' var='modify_day' pattern='yyyy-MM-dd' scope="page"/>
					<fmt:formatDate value="${modify_day}" pattern="yyyy-MM-dd" var="deadline"/> 
					<fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd" var="today"/>
					<li class="no">${ v.v_no }</li>
					<c:choose> 
					<c:when test="${ today <= deadline }">
					<li class="state go"><span>투표 진행중</span></li>
					</c:when>
					<c:otherwise>
					<li class="state fi"><span>투표 완료</span></li>
					</c:otherwise>
					</c:choose>
					<li class="title">${ v.v_title }</li>
					<li class="nick">${ v.m_nick }</li>
					<li class="date">
					<fmt:parseDate value='${v.v_enroll_date}' var='enroll_day' pattern='yyyy-MM-dd' scope="page"/>
					<fmt:formatDate value="${enroll_day}" pattern="yyyy-MM-dd"/> ~ ${ deadline }
					</li>
					<li class="count">${ v.v_count }</li>
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
				<button type="button" id="btn2">목록</button>
				<!-- 관리자만 버튼 보이게-->
				<c:if test="${ !empty loginManager }">
				<button type="button" id="btn3" onclick="location.href='${ contextPath }/vote/insert'">작성하기</button>
				</c:if> 
			</form>
		</div>
		<ul class="board_paging">
				<!-- 검색 결과 화면인 경우 넘겨줄 searchParam 정의 -->
				<c:if test="${ !empty param.searchCondition && !empty param.searchValue }">
					<c:set var="searchParam" value="&searchCondition=${ param.searchCondition }&searchValue=${ param.searchValue }"/>
				</c:if>
				
					<!-- 맨 처음으로 (<<) -->
						<li><a href="${ contextPath }/vote/list?page=1${ searchParam }">&lt;&lt;</a></li>
						
					<!-- 이전 페이지 (<) -->
					<li>
					<c:choose>
						 <c:when test="${ pi.page > 1 }">
						 <a href="${ contextPath }/vote/list?page=${ pi.page - 1}${ searchParam }" class="btn_prev">&lt;</a>
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
						   		<a href="${ contextPath }/vote/list?page=${ p }${ searchParam }">${ p }</a>
						   </c:otherwise>
						</c:choose>
					</li>
					</c:forEach>
					
					
					<!-- 다음 페이지 (>) -->
					<li>
					<c:choose>
						 <c:when test="${ pi.page < pi.maxPage }">
						 <a href="${ contextPath }/vote/list?page=${ pi.page + 1}${ searchParam }" class="btn_next">&gt;</a>
						 </c:when>
						 <c:otherwise>
						 <a href="#">&gt;</a>
						 </c:otherwise>
					</c:choose>	
					</li>		
						
					<!-- 맨 끝으로 (>>) -->
						<li><a href="${ contextPath }/vote/list?page=${ pi.maxPage }${ searchParam }">&gt;&gt;</a></li>	
						
				</ul>
	</div>
	
	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>

	<script>
		// 게시글 목록에 mouseover/mouseout 시 onmouseover 클래스 추가/제서 처리
		const voteList = document.querySelector('.vote_list');
		
		voteList.addEventListener('mouseover', function(){
			
			if(event.target.classList.contains('vote_ul'))
				event.target.classList.add('onmouseover');
			else if(event.target.parentNode.classList.contains('vote_ul'))
				event.target.parentNode.classList.add('onmouseover');
		});
		
		voteList.addEventListener('mouseout', function(){
			
			if(event.target.classList.contains('vote_ul'))
				event.target.classList.remove('onmouseover');
			else if(event.target.parentNode.classList.contains('vote_ul'))
				event.target.parentNode.classList.remove('onmouseover');
			
		});
		
   </script>
   
    <script>
    	
     		function detailView(v_no){    			 
     				location.href='${contextPath}/vote/detail?v_no='+v_no;			
				 }
     		function detailUserView(v_no){    			 
				location.href='${contextPath}/vote/userdetail?v_no='+v_no;
			 }
     </script> 
	
</body>
</html>