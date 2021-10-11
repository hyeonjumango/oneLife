<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부대시설 > 멀티코트 > 시설소개</title>

<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>

</head>
<body>

<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>
	
	 <div class="bottom_wrap1">
            <p></p><span>> 부대시설 > 멀티코트 > 시설소개</span>
            <h1>시설소개</h1>
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
                    <div class="amenities_text">
                        <ul class="subject">
                            <li>
                                <p>운영시간</p>
                                <ul class="explanation">
                                    <li>멀티코트 : 연중무휴(09:00 ~ 21:00)</li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="subject">
                            <li>
                                <p>시설</p>
                                <ul class="explanation">
                                    <li>1코트 / 2코트 / 3코트</li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="subject">
                            <li>
                                <p>이용방법</p>
                                <ul class="explanation">
                                    <li>예약신청 페이지에서 신청 가능합니다.</li>
                                    <li>동시에 두 코트 이상 이용 불가능합니다.</li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="subject">
                            <li>
                                <p>유의사항</p>
                                <ul class="explanation">
                                    <li>킥보드 및 인라인 등 인조잔디를 훼손할 수 있는 장비 출입금지</li>
                                    <li>소음으로 인해 주변 세대 피해를 주는 행동은 삼가합니다.</li>
                                    <li>멀티코트 내 흡연, 화기를 가지고 들어가는 일은 삼가하여 주시기 바랍니다.</li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <div class="amenities_img">
                       <img src="/oneLife/resources/user/images/multicourt.jpg">
                    </div>
                </div>
            </section>
        </div>
        
           <%-- 공통 footer --%>
		<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>


</body>
</html>