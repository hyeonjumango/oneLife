<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>부대시설 > 독서실 > 시설소개</title>

<%-- css/js 파일 --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>

</head>
<body>

<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>
	
	 <div class="bottom_wrap1">
            <p></p><span>> 부대시설 > 독서실 > 시설소개</span>
            <h1>시설소개</h1>
            <div class="bottom_wrap2"></div>
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
                                    <li>독서실 : 연중무휴(07:00 ~ 익일 02:00)</li>
                                    <li>좌석 신청 시간 : 06:00 ~ 24:00(월요일도 신청 가능)</li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="subject">
                            <li>
                                <p>좌석정보</p>
                                <ul class="explanation">
                                    <li>총 좌석 : 100석</li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="subject">
                            <li>
                                <p>이용방법</p>
                                <ul class="explanation">
                                    <li>예약신청 페이지에서 신청 가능합니다.</li>
                                    <li>이용현황 페이지에서 남아있는 좌석 수 확인 가능합니다.</li>
                                </ul>
                            </li>
                        </ul>
                        <ul class="subject">
                            <li>
                                <p>유의사항</p>
                                <ul class="explanation">
                                    <li>같은 세대여도 지정 사용자만 사용 가능합니다.</li>
                                    <li>소지품 분실의 위험이 있으니 반드시 자리를 비울 때 귀중품은 소지하여 주시기 바랍니다.</li>
                                    <li>분실 시 책임지지 않습니다.</li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                    <div class="amenities_img">
                        <img src="/oneLife/resources/user/images/studyroom.PNG">
                    </div>
                </div>
            </section>
        </div>
        
        <%-- 공통 footer --%>
		<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>
        

</body>
</html>