<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부대시설 > 내 예약 확인 > 독서실 예약 확인</title>

 <style>
        table {
          border: 1px solid #000;
          border-collapse: collapse;
        }
        th, td {
          border: 1px solid #000;
        }
 </style>

<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>

</head>
<body>

<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>
	
	<div class="bottom_wrap1">
            <p></p><span>> 부대시설 > 내 예약 확인 > 독서실 예약 확인</span>
            <h1>독서실 예약 내역</h1>
            <div class="bottom_wrap2">
            </div>
        </div>

        <div class="amenities_container">
           <nav id="amenities_nav">
                <div class="amenities_nav">
                    <ul class="nav_outer">
                        <li>
                            <p>독서실</p>
                            <ul class="nav_inner">
                                <li>
                                    <a href="<%= request.getContextPath() %>/srIntro">시설소개</a>
                                    <a href="<%= request.getContextPath() %>/srRes">예약신청</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <p>멀티코트</p>
                            <ul class="nav_inner">
                                <li>
                                    <a href="<%= request.getContextPath() %>/mcIntro">시설소개</a>
                                    <a href="<%= request.getContextPath() %>/mcRes">예약신청</a>
                                </li>
                            </ul>
                        </li>
                        <li>
                            <p>내 예약 확인</p>
                            <ul class="nav_inner">
                                <li>
                                    <a href="<%= request.getContextPath() %>/srHistory">독서실</a>
                                    <a href="<%= request.getContextPath() %>/mcHistory">멀티코트</a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>

            <section id="amenities_content">
                <div class="amenities_content">
                    <div class="amenities_search">
                        <form method="get" action="${contextPath}/srHistory">
                            <input type="hidden" name="u_no" value="${loginUser.u_NO}">
                            <label> 이용기간 </label>
                            <input type="month" name="mydate">
                            <input class="submit" type="submit" value="조회">
                        </form>
                    </div>

                    <div class="amenities_table">
                        <table width="1000px" height="500px">
                            <thead class="table_head">
                                <th>신청 일자</th>
                                <th>좌석 번호</th>
                                <th>사용 상태</th>
                            </thead>
                            <tbody>
                                <c:forEach var="s" items="${studyRoomList}">
                                    <tr>
                                        <th>${s.fc_start}</th>
                                        <th>${s.fc_seat_no}</th>
                                        <th>${s.fc_status}</th>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>

                    <ul class="board_paging">
                        <!-- 검색 결과 화면인 경우 넘겨줄 searchParam 정의 -->
                        <c:if test="${ !empty param.searchCondition && !empty param.searchValue }">
                            <c:set var="searchParam" value="&searchCondition=${ param.searchCondition }&searchValue=${ param.searchValue }"/>
                        </c:if>
                        
                            <!-- 맨 처음으로 (<<) -->
                                <li><a href="${ contextPath }/srHistory?page=1${ searchParam }">&lt;&lt;</a></li>
                                
                            <!-- 이전 페이지 (<) -->
                            <li>
                            <c:choose>
                                 <c:when test="${ pi.page > 1 }">
                                 <a href="${ contextPath }/srHistory?page=${ pi.page - 1}${ searchParam }" class="btn_prev">&lt;</a>
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
                                           <a href="${ contextPath }/srHistory?page=${ p }${ searchParam }">${ p }</a>
                                   </c:otherwise>
                                </c:choose>
                            </li>
                            </c:forEach>
                            
                            
                            <!-- 다음 페이지 (>) -->
                            <li>
                            <c:choose>
                                 <c:when test="${ pi.page < pi.maxPage }">
                                 <a href="${ contextPath }/srHistory?page=${ pi.page + 1}${ searchParam }" class="btn_next">&gt;</a>
                                 </c:when>
                                 <c:otherwise>
                                 <a href="#">&gt;</a>
                                 </c:otherwise>
                            </c:choose>	
                            </li>		
                                
                            <!-- 맨 끝으로 (>>) -->
                                <li><a href="${ contextPath }/srHistory?page=${ pi.maxPage }${ searchParam }">&gt;&gt;</a></li>	
                                
                    </ul>

                </div>
            </section>
        </div>
        
        <%-- 공통 footer --%>
		<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>
	

</body>
</html>