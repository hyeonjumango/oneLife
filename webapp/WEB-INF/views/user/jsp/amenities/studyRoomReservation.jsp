<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부대시설 > 독서실 > 예약신청</title>

<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>

</head>
<body>

<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>


<div class="bottom_wrap1">
            <p></p><span>> 부대시설 > 독서실 > 예약신청</span>
            <h1>예약신청</h1>
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
                    <ul class="subject">
                        <li>
                            <p>신청자 정보를 입력해 주세요.</p>
                            <ul class="explanation">
                                <li>신청 확인시 입력하신 정보로 확인 가능하오니 정확하게 입력해주시기 바랍니다.</li>
                            </ul>
                        </li>
                    </ul>
                    <hr>
                    	<form action="${contextPath}/srRes" method="post" name="srFrm">
	                        <div class="reservation_wrap1">
	                            <label class="selectDate">날짜 선택</label> 
	                            <br>
	                            <input type="text" id="dayInput" name="dayInput" class="cal_today" readonly >
	                         </div>
	                         <div class="reservation_wrap2">
	                         	<label class="selectSeat">좌석 선택</label>
	                         	<br>
	                         	<input type="text" id="seatNumber" name="seatNumber" class="seatNumber" readonly>
	                            <button type="button" onclick="showPopup();">자리보기</button>
	                         </div>
	                        <div class="reservation_submit">
	                            <input class="submit" type="button" onclick="resFrm()" value="신청하기">
	                            <input class="reset" type="reset" value="취소">
	                        </div>
                        </form>
                </div>
            </section>
        </div>
        
        <%-- 공통 footer --%>
		<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>
		
		<%-- 팝업창 불러오기 --%>
	    <script>
	        function showPopup(){
	        	
	        	if($('#dayInput').val().length == 0){
	        		alert("날짜를 선택해주세요!");
	        		$('#dayInput').focus();
	        		
	        		return;
	        	}
	        	
	            window.open("${contextPath}/srPopup?today=" + $('#dayInput').val() + "","좌석선택","width=1300, height=850,left=10,top=50");
	        }
	        
	        function resFrm(){
	        	if($('#dayInput').val().length == 0){
	        		alert("날짜를 선택해주세요!");
	        		$('#dayInput').focus();
	        		return;
	        	}
	        	
	        	if($('#seatNumber').val().length == 0){
	        		alert('좌석 선택해주세요!');
	        		showPopup();
	        		return;
	        	}
	        	
	        	document.forms.srFrm.submit();
	        	
	        }
	        
	        // 로그인한 유저의 예약한 날짜 가져오기
	        // 기능은 calendar.js 에서 함수에서 작동함
	        let disabledDays  = [];
	        
	    </script>
	    <c:forEach var="n" items="${dateList}">
	    	<script>
	    		disabledDays.push('${n}');
	    	</script>
	    </c:forEach>
</body>
</html>