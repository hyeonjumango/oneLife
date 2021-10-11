<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0,minimum-scale=1.0,target-densitydpi=medium-dpi" />
    <title>멀티코트장</title>
    <jsp:include page="/WEB-INF/views/admin/common/link.jsp"></jsp:include>
</head>
<body>
    <div id="wrap">
        <div class="view_wrap multi_pop">
            <h2 class="sub_tit">멀티코트장 예약 현황</h2>
            <div class="view_items">
                <dl class="items">
                    <dt>예약일자</dt>
                    <dd>
                        <div class="input">
                           <input type="text" name="dayInput" id="dayInput" class="cal_today" value="${param.day}" readonly>
                        </div>
                    </dd>
                </dl>

                <%-- <dl class="items">
                    <dt>예약 시간</dt>
                    <dd>    
                        <div class="select">
                           <select name="multiTime" id="multiTime" style="width: 160px;">
                                <option value="09" <c:if test="${param.time == '09'}">selected</c:if>>09:00 ~ 10:00</option>
                                <option value="10" <c:if test="${param.time == '10'}">selected</c:if>>10:00 ~ 11:00</option>
                                <option value="11" <c:if test="${param.time == '11'}">selected</c:if>>11:00 ~ 12:00</option>
                                <option value="12" <c:if test="${param.time == '12'}">selected</c:if>>12:00 ~ 13:00</option>
                                <option value="13" <c:if test="${param.time == '13'}">selected</c:if>>13:00 ~ 14:00</option>
                                <option value="14" <c:if test="${param.time == '14'}">selected</c:if>>14:00 ~ 15:00</option>
                                <option value="15" <c:if test="${param.time == '15'}">selected</c:if>>15:00 ~ 16:00</option>
                                <option value="16" <c:if test="${param.time == '16'}">selected</c:if>>16:00 ~ 17:00</option>
                                <option value="17" <c:if test="${param.time == '17'}">selected</c:if>>17:00 ~ 18:00</option>
                                <option value="18" <c:if test="${param.time == '18'}">selected</c:if>>18:00 ~ 19:00</option>
                                <option value="19" <c:if test="${param.time == '19'}">selected</c:if>>19:00 ~ 20:00</option>
                                <option value="20" <c:if test="${param.time == '20'}">selected</c:if>>20:00 ~ 21:00</option>
                           </select>
                         </div>
                    </dd>
                </dl> --%>
                <dl class="items">
                    <dt></dt>
                    <dd>
                        <div class="court_items">
                            <c:set var="num" value="${0}"/>
                            <c:forEach var="ct" begin="${1}" end="${3}" varStatus="vs">
                            	<c:choose>
                            		<c:when test="${fList.size() == 0}">
                            			<div class="c_items">
			                                <h4>멀티코트</h4>
			                                <div class="inner">
			                                    <img src="${contextPath}/resources/admin/images/court.png" alt="멀티코트A">
			                                </div>
			                            </div>
                            		</c:when>
                            		<c:when test="${fList.size() > num && fList.size() > 0 && (fList.get(num).fcSeatType eq 'A' || fList.get(num).fcSeatType eq 'B' || fList.get(num).fcSeatType eq 'C')}">
                            			
                            			<div class="c_items">
			                                <h4>멀티코트</h4>
			                                <div class="inner">
			                                    <img src="${contextPath}/resources/admin/images/court.png" alt="멀티코트A">
			                                    <div class="c_complte">
			                                        <p>
			                                            사용중인 코트장 입니다.<br/>
			                                            예약자 : ${fn:substring(fList.get(num).rName,0,1)}
			                                            <c:forEach var='shap' begin="1" end="${fn:length(fList.get(num).rName) - 1}">
			                                            	${'*'}
			                                            </c:forEach>
			                                        </p>
			                                        <input type="hidden" name="fcNo" value="${fList.get(num).fcNo}">
			                                    </div>
			                                </div>
			                            </div>
			                            <c:set var="num" value="${num + 1}"/>
                            		</c:when>
                            		<c:otherwise>
                            			<div class="c_items">
			                                <h4>멀티코트</h4>
			                                
			                                <div class="inner">
			                                    <img src="${contextPath}/resources/admin/images/court.png" alt="멀티코트A">
			                                </div>
			                            </div>
                            		</c:otherwise>
                            	</c:choose>
                            </c:forEach>
                            
                            <%-- <div class="c_items">
                                <h4>멀티코트B</h4>
                                <div class="inner">
                                     <img src="${contextPath}/resources/admin/images/court.png" alt="멀티코트B">
                                </div>
                            </div>
                            <div class="c_items">
                                <h4>멀티코트C</h4>
                                <div class="inner">
                                     <img src="${contextPath}/resources/admin/images/court.png" alt="멀티코트C">
                                </div>
                            </div> --%>


                        </div>
                    </dd>
                </dl>

            </div>

        </div>
        
    </div>

    <div class="popup_wrap" id="multiInfo">
        <div class="dim"></div>
        <div class="item">
            <h3 class="tit">예약 내역 조회</h3>
            <ul>
               <li>
                    <span>예약번호 : </span>
                    <p class="txt1"></p>
               </li>
                <li>
                    <span>시설명 : </span>
                    <p class="txt2"></p>
               </li>
               <li>
                    <span>타입 : </span>
                    <p class="txt3">A</p>
                </li>
                <li>
                    <span>이름 : </span>
                    <p class="txt4"></p>
               </li>
               <li>
                    <span>휴대폰번호 : </span>
                    <p class="txt5"></p>
                </li>
                <li>
                    <span>예약 일시 : </span>
                    <p class="txt6"></p>
                </li>
                <li>
                    <span>예약 마감 : </span>
                    <p class="txt7"></p>
                </li>
            </ul>

            <div class="btn_box">
                <a href="javascript:popHide('multiInfo');" class="ok">확인</a>
            </div>
        </div>
    </div>
    <script>
    $(function(){
    	//날짜 변경했을시 날짜파라미터값 전송
        $('#dayInput').change(function(){
        	location.href = '${contextPath}/admin/facil/multicourt?day=' + $(this).val();
        });
    	
    	$('#multiTime').change(function(){
    		location.href = '${contextPath}/admin/facil/multicourt?day=' + $('#dayInput').val() + '&time=' + $(this).find('option:selected').val();
    	});
    	
    	// 사용중인 코트 클릭시
    	$('.c_complte').click(function(){
    		$.ajax({
            	url : "${contextPath}/admin/facil/multiInfo",
            	type : "post",
            	data : {"fcNo" : $(this).find('[type=hidden]').val(), "dayInput" : $('#dayInput').val(), "time" : $('#multiTime option:selected').val()},
            	success : function(item){
            		console.log(item);
            		$('.txt1').text(item.fcNo);
            		$('.txt2').text(item.fcName);
            		$('.txt3').text(item.fcSeatType);
            		$('.txt4').text(item.rName);
            		$('.txt5').text(item.uPhone);
            		$('.txt6').text(item.faDate);
            		$('.txt7').text(item.fcEnd);
            		
            		popShow('multiInfo');
            	},
            	error : function(e){
            		console.log(e);
            	}
            	
            });	
    	})
    	
    });
    
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
