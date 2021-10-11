<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0,minimum-scale=1.0,target-densitydpi=medium-dpi" />
    <title>독서실 예약 현황</title>
    <jsp:include page="/WEB-INF/views/admin/common/link.jsp"></jsp:include>

</head>
<body>
    <div id="wrap">
        <div class="view_wrap library_pop">
            <h2 class="sub_tit">독서실 예약 현황</h2>
            <div class="library_wrap">
                <div class="library_head">
                    <div class="input">
                        <input type="text" name="dayInput" id="dayInput" value="${param.day}" class="cal_today" readonly>
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
                            		<c:when test="${fList.size() > 0 && fList.get(num).fcSeatNo eq status.index}">
                            			<c:set var="num" value="${num + 1}"/>
                            			<li class="use">
			                                <a href="javascript:;">
			                                    <p class="status">On</p>
			                                    <h3 class="seatNum">${item}</h3>
			                                    <!-- <p class="endDate">~08.26</p> -->
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

            </div>
        </div>
        
    </div>

    <div class="popup_wrap" id="libraryInfo">
        <div class="dim"></div>
        <div class="item">
            <h3 class="tit">예약 내역 조회</h3>
            <ul>
              <li>
                    <span>예약번호 : </span>
                    <p class="txt1">1</p>
               </li>
               <li>
                    <span>시설명 : </span>
                    <p class="txt2">멀티코트장</p>
               </li>
               <li>
                    <span>타입 : </span>
                    <p class="txt3">A</p>
                </li>
                 <li>
                    <span>좌석 번호 : </span>
                    <p class="txt4">1</p>
                </li>
                <li>
                    <span>이름 : </span>
                    <p class="txt5">홍길동</p>
               </li>
               <li>
                    <span>휴대폰번호 : </span>
                    <p class="txt6">010-1234-5678</p>
                </li>
                <li>
                    <span>예약 일시 : </span>
                    <p class="txt7">2021년 08월 16일  11시00분</p>
                </li>
                <li>
                    <span>예약 마감 : </span>
                    <p class="txt8">2021년 08월 16일  11시00분</p>
                </li>
            </ul>

            <div class="btn_box">
                <a href="javascript:popHide('libraryInfo');" class="ok">확인</a>
            </div>
        </div>
    </div>
    <script>
    $(function(){
    	// 좌석선택했을시 on 클래스 추가
        $('.library_pop .library_wrap .library_body .seat_item ul li a').click(function(){
        	$('.library_pop .library_wrap .library_body .seat_item ul li').removeClass('on');
        	$(this).parent('li').addClass('on');
        });
        
        //날짜 변경했을시 날짜파라미터값 전송
        $('#dayInput').change(function(){
        	location.href = '${contextPath}/admin/facil/library?day=' + $(this).val();
        });
        
        // 사용중인 좌석클릭시 정보
        $("li.use a").click(function(){
        	$.ajax({
            	url : "${contextPath}/admin/facil/libraryInfo",
            	type : "post",
            	data : {"fcSeatNo" : $(this).find('.seatNum').text(), "dayInput" : $('#dayInput').val()},
            	success : function(item){
            		console.log(item);
            		$('.txt1').text(item.fcNo);
            		$('.txt2').text(item.fcName);
            		$('.txt3').text(item.fcSeatType);
            		$('.txt4').text(item.fcSeatNo);
            		$('.txt5').text(item.rName);
            		$('.txt6').text(item.uPhone);
            		$('.txt7').text(item.faDate);
            		$('.txt8').text(item.fcEnd);
            		
            		popShow('libraryInfo');
            		
            		
            	},
            	error : function(e){
            		console.log(e);
            	}
            	
            });	
        });	
    	
    })
    
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
    
    
    
    </script>
</body>

</html>