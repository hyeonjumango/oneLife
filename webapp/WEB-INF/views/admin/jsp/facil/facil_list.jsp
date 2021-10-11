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
    <title>부대시설 예약 목록</title>
	<jsp:include page="/WEB-INF/views/admin/common/link.jsp"></jsp:include>

</head>
<body>
    <div id="wrap">
	 <%-- header 공통 적용 --%>
     <jsp:include page="/WEB-INF/views/admin/common/header.jsp"></jsp:include>
        
        <div class="container">
	        <%-- menubar 공통 적용  --%>
	        <jsp:include page="/WEB-INF/views/admin/common/menubar.jsp"></jsp:include>
        
            <section id="content">
                <div class="content">
                    <div class="list_wrap">
                        <h2 class="sub_tit">부대시설 예약관리</h2>
                        <form action="${contextPath}/admin/facil/list" onsubmit="return regFunc()">
	                        <div class="search_box">
	                            <div class="search_top clearfix">
	                                <div class="items clearfix">
	                                    <label for="reser_fac1">예약시설</label>
	                                    <div class="select">
	                                        <select name="facilName" id="reser_fac1">
	                                            <option value="all">전체</option>
	                                            <option value="독서실" <c:if test="${param.facilName eq '독서실'}">selected</c:if>>독서실</option>
	                                            <option value="멀티코트장" <c:if test="${param.facilName eq '멀티코트장'}">selected</c:if>>멀티코트장</option>
	                                        </select>
	                                    </div>
	                                </div>
	                                <div class="items clearfix">
	                                    <label for="reser_fac2">예약타입</label>
	                                    <div class="select">
	                                        <select name="facilType" id="reser_fac2">
	                                            <option value="all">전체</option>
	                                            <option value="일일권" <c:if test="${param.facilType eq '일일권'}">selected</c:if>>일일권</option>
	                                            <option value="A" <c:if test="${param.facilType eq 'A'}">selected</c:if>>A</option>  
	                                            <option value="B" <c:if test="${param.facilType eq 'B'}">selected</c:if>>B</option>  
	                                            <option value="C" <c:if test="${param.facilType eq 'C'}">selected</c:if>>C</option>  
	                                        </select>
	                                    </div>
	                                </div>
	                                <div class="items clearfix">
	                                    <label for="reser_fac3">예약상태</label>
	                                    <div class="select">
	                                        <select name="facilStatus" id="reser_fac3">
	                                            <option value="all">전체</option>
	                                            <option value="예약취소" <c:if test="${param.facilStatus eq '예약취소'}">selected</c:if>>예약취소</option>
	                                            <option value="사용전">사용전</option>
	                                            <option value="사용중">사용중</option>
	                                            <option value="사용완료">사용완료</option>
	                                        </select>
	                                    </div>
	                                </div>
	                                <div class="items clearfix">
	                                    <label for="">예약 시작 일자</label>
	                                    <div class="calendar clearfix">
	                                    	<c:choose>
	                                    		<c:when test="${!empty param.facilDay}">
	                                    			<input type="text" name="facilDay" id="facilDay" class="cal" value="${param.facilDay}" readonly>	
	                                    		</c:when>
	                                    		<c:otherwise>
	                                    			<c:set var="now" value="<%=new java.util.Date()%>" />
													<c:set var="sysDate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></c:set>
	                                    			<input type="text" name="facilDay" id="facilDay" class="cal" value="${sysDate}" readonly>
	                                    		</c:otherwise>
	                                    	</c:choose>
	                                        <div class="checkbox">
												<input type="checkbox" name="allDay" id="allDay" <c:if test="${param.allDay eq 'on'}">checked</c:if>>
												<label for="allDay">전체조회</label>
											</div>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="search_bot clearfix">
	                                <div class="items clearfix">
	                                    <label for="searchName">검색조건</label>
	                                    <div class="select">
	                                        <select name="searchName" id="searchName">
	                                            <option value="id" <c:if test="${param.searchName eq 'id'}">selected</c:if>>아이디</option>
	                                            <option value="name" <c:if test="${param.searchName eq 'name'}">selected</c:if>>이름</option>
	                                            <option value="phone" <c:if test="${param.searchName eq 'phone'}">selected</c:if>>핸드폰번호</option>
	                                        </select>
	                                    </div>
	                                    <input type="text" id="searchValue" class="input" name="searchValue" value="${param.searchValue}">
	                                    <button type="submit" class="btn">검색</button>
	                                </div>
	                            </div>
	                        </div>
						</form>

                        <div class="list_content">
                            <div class="table_wrap">
                                <table class="table">
                                    <colgroup>
                                        <col width="60px">
                                        <col width="8%">
                                        <col width="8%">
                                        <col width="8%">
                                        <col width="8%">
                                        <col width="8%">
                                        <col width="115px">
                                        <col width="20%">
                                        <col width="20%">
                                        <col width="20%">
                                        <col width="8%">
                                    </colgroup>
                                    <caption class="ir_so">부대시설 예약목록</caption>
                                    <thead>
                                        <tr>
                                            <th>
                                                <div class="checkbox">
                                                    <input type="checkbox" name="checkAll" id="checkAll">
                                                    <label for="checkAll"></label>
                                                </div>
                                            </th>
                                            <th>No.</th>
                                            <th>예약 시설</th>
                                            <th>예약 타입(좌석)</th>
                                            <th>아이디</th>
                                            <th>이름</th>
                                            <th>핸드폰 번호</th>
                                            <th>예약 일자</th>
                                            <th>예약 시작시간</th>
                                            <th>예약 마감시간</th>
                                            <th>예약 상태</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:choose>
                                    		<c:when test="${fList.size() > 0}">
                                    			<c:forEach var="f" items="${fList}">
			                                    	<tr>
			                                            <td>
			                                                <div class="checkbox">
			                                                    <input type="checkbox" name="facilCheck" id="facil${f.fcNo}" value="${f.fcNo}" <c:if test="${f.fcStatus eq '예약취소' || f.fcStatus eq '사용완료'}">disabled</c:if>>
			                                                    <label for="facil${f.fcNo}"></label>
			                                                </div>
			                                            </td>
			                                            <td>${f.fcNo}</td>
			                                            <td>${f.fcName}</td>
			                                            <td>${f.fcSeatType} <c:if test="${f.fcName eq '독서실'}">(${f.fcSeatNo})</c:if></td>
			                                            <td>${f.uId}</td>
			                                            <td>${f.rName}</td>
			                                            <td>${f.uPhone}</td>
			                                            <td>
			                                            	<fmt:formatDate value="${f.faDate}" pattern="yyyy년 MM월 dd일 HH시mm분"/>
			                                            </td>
			                                            <td>
			                                            	<fmt:formatDate value="${f.fcStart}" pattern="yyyy년 MM월 dd일 HH시mm분"/>
			                                            </td>
			                                            <td>
			                                            	<fmt:formatDate value="${f.fcEnd}" pattern="yyyy년 MM월 dd일 HH시mm분"/>
			                                            </td>
			                                            <td>
			                                            	<c:choose>
			                                            		<c:when test="${f.fcStatus eq '예약취소'}">
			                                            			<p class="status_after">예약취소</p>
			                                            		</c:when>
			                                            		<c:when test="${f.fcStatus eq '사용전'}">
			                                            			<p class="status_before">사용전</p>
			                                            		</c:when>
			                                            		<c:when test="${f.fcStatus eq '사용중'}">
			                                            			<p class="status_ing">사용중</p>
			                                            		</c:when>
			                                            		<c:when test="${f.fcStatus eq '사용완료'}">
			                                            			<p class="status_after">사용완료</p>
			                                            		</c:when>
			                                            	</c:choose>
			                                            </td>
			                                        </tr>
			                                    </c:forEach>
                                    		</c:when>
                                    		<c:otherwise>
                                    			<tr>
													<td>
														<div class="list_nodate">
															<img src="/oneLife/resources/admin/images/list_nodate.png" alt="NODATE">
															<p>조회된 목록이 존재하지 않습니다.</p>
														</div>
													</td>
												</tr>
												<%-- nodata시 colspan 값 자동 적용 --%>
												<script>
										    		let thSize = $('.table_wrap .table thead tr th').length;
										    		$('.content .list_nodate').parent('td').attr('colspan', thSize);
												</script>
                                    			
                                    		</c:otherwise>
                                    	
                                    	</c:choose>
                                    </tbody>
                                </table>
                            </div>
                            <div class="btn_box">
                                <a href="javascript:remove_popOpen();" class="remove">삭제</a>
                            </div>
                            
                            <%-- 검색결과에대한 파라미터값 저장 --%>
                          	<c:if test="${!empty param.facilName && !empty param.facilType}">
                          		<c:set var="searchParam" value="&facilName=${param.facilName}&facilType=${param.facilType}&facilStatus=${param.facilStatus}&facilDay=${param.facilDay}&searchName=${param.searchName}&searchValue=${param.searchValue}"/>
                          	</c:if>
                          	<jsp:include page="/WEB-INF/views/admin/common/paging.jsp"	flush="false">
								<jsp:param name="listSize" value="${fList.size()}" />
								<jsp:param name="piPage" value="${pi.page}" />
								<jsp:param name="startPage" value="${pi.startPage}" />
								<jsp:param name="endPage" value="${pi.endPage}" />
								<jsp:param name="maxPage" value="${pi.maxPage}" />
								<jsp:param name="pageUrl"
									value="${pageContext.request.requestURL}" />
								<jsp:param name="search" value="${searchParam}" />
							</jsp:include>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>

    <!-- 팝업영역 -->
    <!-- 삭제 팝업 -->
    <div class="popup_wrap" id="remove_step01">
        <div class="dim"></div>
        <div class="item">
            <h3 class="tit">예약 내역 삭제</h3>
            <p class="txt">총 <span class="nub">?</span>건의 예약내역을 삭제하시겠습니까?</p>
            <div class="btn_box">
                <a href="javascript:removeStep01();" class="ok">삭제</a>
                <a href="javascript:popHide('remove_step01');" class="cancle">취소</a>
            </div>
        </div>
    </div>

     <!-- 삭제완료 팝업 -->
     <div class="popup_wrap" id="remove_step02">
        <div class="dim"></div>
        <div class="item">
            <h3 class="tit">예약 내역 삭제</h3>
            <p class="txt">총 <span class="nub">?</span>건의 예약내역이 삭제되었습니다.</p>
            <div class="btn_box">
                <a href="javascript:facilLast();" class="ok">확인</a>
            </div>
        </div>
    </div>
    <!-- 팝업영역 -->
    
    <script>
    $(function(){
    	// 전체조회 버튼
        check = $('input[name=allDay]').is(':checked');
           if(check){
          	 $('#facilDay').removeAttr('readonly');
          	 $('#facilDay').attr('disabled', true);
           }else{
          	 $('#facilDay').removeAttr('disabled');
          	 $('#facilDay').attr('readonly', true);
           }
        
        $('input[name=allDay]').change(function(){
       	 check = $('input[name=allDay]').is(':checked');
       	 
       	 if(check){
           	 $('#facilDay').removeAttr('readonly');
           	 $('#facilDay').attr('disabled', true);
            }else{
           	 $('#facilDay').removeAttr('disabled');
           	 $('#facilDay').attr('readonly', true);
            }	
        })
        
        //체크박스
     // 체크박스
        let checkboxs = [];
        $('#checkAll').change(function(){
       	 let checked = $(this).is(':checked');
       	 if(checked){
       		 $('input[name=facilCheck]').prop('checked', true);
       		 $('input[name=facilCheck]:disabled').prop('checked', false);
       	 }else{
       		 $('input[name=facilCheck]').prop('checked', false);
       	 }
       	 
       	 checkArr();
       	 
       	 
        });
        
        $('input[name=facilCheck]').change(function(){
       	// 체크박스 갯수
       	let checkSize = $('input[name=facilCheck]').length;
       	// 체크갯수
       	let checkOnSize = $('input[name=facilCheck]:checked').length;
       	// disalbled 갯수
       	let checkDis = $('input[name=facilCheck]:disabled').length;
       	
       	if(checkSize - checkDis === checkOnSize){
       		// 전체 체크됐을시
       		$('#checkAll').prop('checked', true);
       	}else{
       		// 체크가 하나라도 풀려있을시
       		$('#checkAll').prop('checked', false);
       	}
       	
       	checkArr();
       	
        });
        
        
    })
    
    	// 체크박스 배열로 변환
        function checkArr(){
       	 checkboxs = [];
       	 for(let i = 0; i < $('input[name=facilCheck]').length; i++){
       		 if($('input[name=facilCheck]').eq(i).is(':checked')){
       			 checkboxs.push($('input[name=facilCheck]').eq(i).val());
       		 }
       	 }
        }
    
     // 게시글 삭제
        function remove_popOpen(){
       	 let check = $('input[name=facilCheck]:checked').length;
       	 if(check > 0){
       		 $('.nub').text(check);
       		 popShow('remove_step01');
       	 }else{
       		 alert('체크박스를 1개이상 선택해주세요.');
       	 }
        }
    
    	// 게시글 삭제 여부
	    function removeStep01(){
	   	 $.ajax({
	   		 url : "${contextPath}/admin/facil/remove",
	   		 traditional : true,
	   		 type: "post",
	   		 data : {"facilCheck" : checkboxs},
	   		 success : function(item){
	   			 if(item >= 0){
	   				 $('#remove_step02 .item .txt').text("총 " + item + "건의 예약 내역이 삭제되었습니다.");
	   			 }else{
	   				 $('#remove_step02 .item .txt').text("총 " + item + "건의 예약 내역이 삭제 실패 하였습니다.");
	   			 }
	   		 }
	   		 
	   	 })
	   	 
	   	$('#remove_step01').removeClass('pop_on');
	   	popShow('remove_step02');
	   	
	    }
    	
	    function facilLast(){
	    	 location.href = "${contextPath}/admin/facil/list?allDay=on"
	     }
	    </script>
	    <!-- 검색 유효성 검사 -->
		<script>
			function regFunc(){	
				
				const search_reg = /^[가-힣]{1,4}$/;
				const search_reg2 = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
				const search_reg3 = /^[a-zA-Z0-9]$/;
				
				// 검색조건 검사
				if($('#searchValue').val().length != 0){
					// 이름 검색 일시
					if($('#searchName').val() == 'name'){
						if(!search_reg.test($('#searchValue').val())){
							alert("1~4자리의 한글로 입력해주세요!");
							$('#searchValue').select();
							return false;
						}
						
					}else if($('#searchName').val() == 'phone'){
						// 이메일 검색 일시
						if(!search_reg2.test($('#searchValue').val())){
							alert("예) 010-1234-5678 양식으로 입력해주세요!");
							$('#searchValue').select();
							return false;
						}
					}else if($('#searchName').val() == 'id'){
						// 아이디 검색일시
						if(!search_reg3.test($('#searchValue').val())){
							alert("영문과 숫자로 입력해주세요!");
							$('#searchValue').select();
							return false;
						}
					}
					
				}
			}
		</script>
    

</body>

</html>