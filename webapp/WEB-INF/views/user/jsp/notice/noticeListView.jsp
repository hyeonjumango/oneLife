<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="user.member.model.vo.Member" import="admin.manager.model.vo.Manager"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
	<!-- css -->
<!--      <link rel="stylesheet" href="/oneLife/resources/user/fonts/fonts.css">
     <link rel="stylesheet" href="/oneLife/resources/user/css/reset.css">
     <link rel="stylesheet" href="/oneLife/resources/user/css/jquery-ui.min.css">
     <link rel="stylesheet" href="/oneLife/resources/user/css/noticestyle.css">
     <link rel="stylesheet" href="/oneLife/resources/user/css/style.css"> -->
<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>
<style>
.list_no > ul {height: 50px;}
.list_no { margin-top: 50px;}
.list_no > ul > li > p {font-weight: bold; font-size: 27px; color: #9EA0A5; }
</style>
</head>
<body>
	<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>
	
	<div class="bottom_wrap1">
		<p></p><span>> 아파트소식 > 공지사항</span>
		<h1>공지사항</h1>
		<div class="bottom_wrap2">
		</div>
	</div>
	<div class="wrap">
		<div class="notice_area">
			<div class="notice_title">
				<h1>KH PARK의 <b>공지 및<br>새로운 소식</b>을 전해 드립니다.</h1>
			</div>
			<div class="search_area">
				<form method="get" action="${ contextPath }/notice/list">
				   <select id="searchCondition" name="searchCondition">
							<option value="title" <c:if test="${ param.searchCondition == 'title' }">selected</c:if>>제목</option>
					</select>
					<span class="input_area"> 
					<input type="search" name="searchValue" placeholder="검색" value="${ param.searchValue }">
					<button type="submit" id="btn1"><img src="/oneLife/resources/user/images/Search.png"></button>
					</span>
				</form>
			</div>
			<div class="notice_list">
				<ul class="notice_header">
					<li class="no">번호</li>
					<li class="title">제목</li>
					<li class="nick">작성자</li>
					<li class="date">작성일</li>
					<li class="count">조회</li>
				</ul>
				
			<!-- 검색시 값이 있는지 없는지 -->	
			<c:choose>
				<c:when test="${ noticeList.size() > 0 }">
				<c:forEach var="n" items="${ noticeList }">
				<ul class="notice_ul" onclick="detailView(${ n.n_no })">
					<li class="no">${ n.n_no }</li>
					<li class="title">${ n.n_title }</li>
					<li class="nick">${ n.m_nick }</li>
					<li class="date">${ n.enroll_date }</li>
					<li class="count">${ n.n_count }</li>
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
				<button type="button" id="btn2" onclick="location.href='${ contextPath }/notice/list'">목록</button>
				<!-- 관리자만 버튼 보이게-->
				<c:if test="${ !empty loginManager }">
				<button type="button" id="btn3" onclick="location.href='${ contextPath }/notice/insert'">작성하기</button>
				</c:if>  
			</form>
		</div>

		<ul class="board_paging">
				<!-- 검색 결과 화면인 경우 넘겨줄 searchParam 정의 -->
				<c:if test="${ !empty param.searchCondition && !empty param.searchValue }">
					<c:set var="searchParam" value="&searchCondition=${ param.searchCondition }&searchValue=${ param.searchValue }"/>
				</c:if>
				
					<!-- 맨 처음으로 (<<) -->
						<li><a href="${ contextPath }/notice/list?page=1${ searchParam }">&lt;&lt;</a></li>
						
					<!-- 이전 페이지 (<) -->
					<li>
					<c:choose>
						 <c:when test="${ pi.page > 1 }">
						 <a href="${ contextPath }/notice/list?page=${ pi.page - 1}${ searchParam }" class="btn_prev">&lt;</a>
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
						   		<a href="${ contextPath }/notice/list?page=${ p }${ searchParam }">${ p }</a>
						   </c:otherwise>
						</c:choose>
					</li>
					</c:forEach>
					
					
					<!-- 다음 페이지 (>) -->
					<li>
					<c:choose>
						 <c:when test="${ pi.page < pi.maxPage }">
						 <a href="${ contextPath }/notice/list?page=${ pi.page + 1}${ searchParam }" class="btn_next">&gt;</a>
						 </c:when>
						 <c:otherwise>
						 <a href="#">&gt;</a>
						 </c:otherwise>
					</c:choose>	
					</li>		
						
					<!-- 맨 끝으로 (>>) -->
						<li><a href="${ contextPath }/notice/list?page=${ pi.maxPage }${ searchParam }">&gt;&gt;</a></li>	
						
				</ul>
	</div>
	
	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>

	<script>
		// 게시글 목록에 mouseover/mouseout 시 onmouseover 클래스 추가/제서 처리
		const noticeList = document.querySelector('.notice_list');
		
		noticeList.addEventListener('mouseover', function(){
			
			if(event.target.classList.contains('notice_ul'))
				event.target.classList.add('onmouseover');
			else if(event.target.parentNode.classList.contains('notice_ul'))
				event.target.parentNode.classList.add('onmouseover');
		});
		
		noticeList.addEventListener('mouseout', function(){
			
			if(event.target.classList.contains('notice_ul'))
				event.target.classList.remove('onmouseover');
			else if(event.target.parentNode.classList.contains('notice_ul'))
				event.target.parentNode.classList.remove('onmouseover');
			
		});
		
		function detailView(n_no){
	 		// nno를 쿼리스트링에 데이터로 넘김
	 		location.href='${contextPath}/notice/detail?n_no='+n_no;
	 	}
		
   </script>
	
</body>
</html>