<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판</title>

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
		<p></p><span>> 아파트소식 > 도란도란</span>
		<h1>이웃끼리도란도란</h1>
		<div class="bottom_wrap2">
		</div>
	</div>
	<div class="wrap">
		<div class="board_area">
			<div class="board_title">
				<h1>무료한 일상, <b>이웃들과 함께<br>도란도란</b> 이야기해보세요!</h1>
		 	</div>
			<div class="search_area">
				<form method="get" action="${ contextPath }/board/list">
					<select id="searchCondition" name="searchCondition">
							<option value="title" <c:if test="${ param.searchCondition == 'title' }">selected</c:if>>제목</option>
						    <option value="writer" <c:if test="${ param.searchCondition == 'writer' }">selected</c:if>>닉네임</option>
					</select>
					<span class="input_area"> 
					<input type="search" name="searchValue" placeholder="검색">
					<button type="submit" id="btn1"><img src="/oneLife/resources/user/images/Search.png"></button>
					</span>
				</form>
			</div>
			<div class="board_list">
				<ul class="board_header">
					<li class="no">번호</li>
					<li class="title">제목</li>
					<li class="nick">작성자</li>
					<li class="date">작성일</li>
					<li class="count">조회</li>
					<li class="like">좋아요</li>
				</ul>
				
			<!-- 검색시 값이 있는지 없는지 -->	
			<c:choose>
				<c:when test="${ boardList.size() > 0 }">
				<c:forEach var="b" items="${ boardList }">
				<c:if test="${ !empty loginUser }">
				<ul class="board_ul" onclick="detailView(${ b.b_no })">
				</c:if>
				<c:if test="${ !empty loginManager }">
				<ul class="board_ul" onclick="detailManView(${ b.b_no })">
				</c:if>
					<li class="no">${ b.b_no }</li>
					<li class="title">${ b.b_title }
						<!-- 댓글이 달리시 -->
						<c:if test="${ b.b_reply_count > 0 }">
						<span class="comment_count">[ ${ b.b_reply_count } ]</span>
						</c:if>
					</li>
					<li class="nick">${ b.u_nickname }</li>
					<li class="date"><fmt:formatDate value="${ b.b_modify_date }" pattern="yyyy-MM-dd"/></li>
					<li class="count">${ b.b_count }</li>
					<li class="like">${ b.b_likecnt }</li>
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
				<button type="button" id="btn2" onclick="location.href='${ contextPath }/board/insert'">글쓰기</button>
				</c:if>
			</form>
		</div>

		<ul class="board_paging">
				<!-- 검색 결과 화면인 경우 넘겨줄 searchParam 정의 -->
				<c:if test="${ !empty param.searchCondition && !empty param.searchValue }">
					<c:set var="searchParam" value="&searchCondition=${ param.searchCondition }&searchValue=${ param.searchValue }"/>
				</c:if>
				
					<!-- 맨 처음으로 (<<) -->
						<li><a href="${ contextPath }/board/list?page=1${ searchParam }">&lt;&lt;</a></li>
						
					<!-- 이전 페이지 (<) -->
					<li>
					<c:choose>
						 <c:when test="${ pi.page > 1 }">
						 <a href="${ contextPath }/board/list?page=${ pi.page - 1}${ searchParam }" class="btn_prev">&lt;</a>
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
						   		<a href="${ contextPath }/board/list?page=${ p }${ searchParam }">${ p }</a>
						   </c:otherwise>
						</c:choose>
					</li>
					</c:forEach>
					
					
					<!-- 다음 페이지 (>) -->
					<li>
					<c:choose>
						 <c:when test="${ pi.page < pi.maxPage }">
						 <a href="${ contextPath }/board/list?page=${ pi.page + 1}${ searchParam }" class="btn_next">&gt;</a>
						 </c:when>
						 <c:otherwise>
						 <a href="#">&gt;</a>
						 </c:otherwise>
					</c:choose>	
					</li>		
						
					<!-- 맨 끝으로 (>>) -->
						<li><a href="${ contextPath }/board/list?page=${ pi.maxPage }${ searchParam }">&gt;&gt;</a></li>	
						
				</ul>
	</div>
	
	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>


	<script>
		// 게시글 목록에 mouseover/mouseout 시 onmouseover 클래스 추가/제서 처리
		const boardList = document.querySelector('.board_list');
		
		boardList.addEventListener('mouseover', function(){
			
			if(event.target.classList.contains('board_ul'))
				event.target.classList.add('onmouseover');
			else if(event.target.parentNode.classList.contains('board_ul'))
				event.target.parentNode.classList.add('onmouseover');
		});
		
		boardList.addEventListener('mouseout', function(){
			
			if(event.target.classList.contains('board_ul'))
				event.target.classList.remove('onmouseover');
			else if(event.target.parentNode.classList.contains('board_ul'))
				event.target.parentNode.classList.remove('onmouseover');
			
		});
		
   </script>
   
    <script>
     		 function detailView(b_no){
			  		location.href='${contextPath}/board/detail?b_no='+b_no;
			  	}
		    function detailManView(b_no){
		  		location.href='${contextPath}/board/Mandetail?b_no='+b_no;
		  	}
     </script> 
	
</body>
</html>