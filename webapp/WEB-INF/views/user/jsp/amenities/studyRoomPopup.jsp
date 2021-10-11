<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자리 선택</title>

<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>

<!-- 이미 예약내역이있을시 -->
   <c:if test="${status eq 'fail'}">
   	<script>
   		opener.document.getElementById('seatNumber').value = '';
   		alert("이미 예약내역이 존재합니다. 다른날을 선택해주세요!");
   		window.close();
   	</script>
   </c:if>
</head>
<body>
<div id="wrap">
        <div class="view_wrap library_pop">
            <h2 class="sub_tit">자리 선택</h2>
            <div class="library_wrap">
                <div class="library_head">
                	<div class="input">
                        <input type="text" name="dayInput" id="dayInput" value="${param.today}" class="cal_today" disabled>
                    </div>
                    <div class="status_item">
                        <p>빈좌석</p>
                        <p>사용중인 좌석</p>
                        <p>선택한 좌석</p>
                    </div>
                </div>
                <div class="library_body">
                    <div class="seat_item">
                      <ul>
                            <!-- 사용중 or 예약석 좌석일시 -->
                           <c:set var="num" value="${0}"/>
                           <c:forEach var="item" begin="${1}" end="${140}" varStatus="status">
                            	<c:choose>
                            		<c:when test="${fList.size() > 0 && fList.get(num).fcSeatNo eq status.count}">
                            			<c:set var="num" value="${num + 1}"/>
                            			<li class="use">
			                                <a href="javascript:;">
			                                    <p class="status">On</p>
			                                    <h3 class="seatNum">${item}</h3>
			                                    <!-- <p class="endDate">~08.26</p> -->
			                                    <input type="hidden" name="seatNum" value="${item}">
			                                </a>
			                            </li>
			                            
			                            <c:if test="${fList.size() <= num}">
			                            	<c:set var="num" value="${fList.size() - 1}"/>
			                            </c:if>
                            		</c:when>
                            		<c:otherwise>
                            			<li>
			                                <a href="javascript:;">
			                                    <p class="status">Off</p>
			                                    <h3 class="seatNum">${item}</h3>
			                                    <!-- <p class="endDate">~08.26</p> -->
			                                    <input type="hidden" name="seatNum" value="${item}">
			                                </a>
			                            </li>
                            		</c:otherwise>
                            	</c:choose>
                            </c:forEach>
                        </ul>
                    </div>
                    <div class="plan_item">
                            <div class="exit_item">
                                출입구
                            </div>
                            <div class="lounge_item">
                                휴게실
                            </div>
                    </div>
                </div>
                <div class="libaray_btn">
                	<a href="javascript:sb();">신청하기</a>
                	<a href="javascript:cancle();">취소하기</a>
                </div>

            </div>
        </div>
        
    </div>
  
    <script>
 		// 좌석선택했을시 on 클래스 추가 이미사용중인 좌석메세지 표시
	    $('.library_pop .library_wrap .library_body .seat_item ul li a').click(function(){
	    	if($(this).parent().hasClass('use')){
	    		alert("이미 사용중인 좌석입니다. 다른자리를 선택해주세요!");
	    	}else{
	    		$('.library_pop .library_wrap .library_body .seat_item ul li').removeClass('on');
		    	$(this).parent('li').addClass('on');
	    	}
	    });
 		
	 	// 신청하기 버튼
 		function sb() {
 			let liClass = $('.library_pop .library_wrap .library_body .seat_item ul li');
 			
 			if(!liClass.hasClass('on')){
 				alert("좌석을 선택해주세요!");
 			}else{
 				opener.document.getElementById('seatNumber').value = $('.seat_item ul li.on').find('input[type=hidden]').val();
 				window.close();
 			}
 		}
 		
 		// 취소하기 버튼
 		function cancle() {
 			window.close();
 		}
    </script>
    
    


</body>
</html>