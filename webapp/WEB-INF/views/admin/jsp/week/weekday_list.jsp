<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1" />
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0,minimum-scale=1.0,target-densitydpi=medium-dpi" />
<title>주요일정 관리 목록</title>

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
						<h2 class="sub_tit">주요일정 예약관리</h2>
						<form>
							<div class="search_box">
								<div class="search_top clearfix">
									<div class="items clearfix">
										<label for="reser_fac1">일정 타입</label>
										<div class="select">
											<select name="sType" id="reser_fac1">
												<option value="all">전체</option>
												<option value="SC_CODE1"
													<c:if test="${param.sType eq 'SC_CODE1'}">selected</c:if>>공동생활</option>
												<option value="SC_CODE2"
													<c:if test="${param.sType eq 'SC_CODE2'}">selected</c:if>>주민투표</option>
											</select>
										</div>
									</div>
									<div class="items clearfix">
										<label for="reser_fac2">공개 여부</label>
										<div class="select">
											<select name="sReveal" id="reser_fac2">
												<option value="all">전체</option>
												<option value="Y"
													<c:if test="${param.sReveal eq 'Y'}">selected</c:if>>공개</option>
												<option value="N"
													<c:if test="${param.sReveal eq 'N'}">selected</c:if>>비공개</option>
											</select>
										</div>
									</div>
									<div class="items clearfix">
										<label for="searchName">일정 예정일</label>
										<div class="calendar clearfix">
										<c:set var="now" value="<%=new java.util.Date()%>" />
										<c:set var="sysDate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></c:set> 
											<c:choose>
												<c:when test="${!empty param.searchDay}">
													<input type="text" name="searchDay" id="searchDay"
															class="cal" value="${param.searchDay}" readonly>
												</c:when>
												<c:otherwise>
													<input type="text" name="searchDay" id="searchDay"
												class="cal" value="${sysDate}" readonly>
												</c:otherwise>
											</c:choose>
											
										</div>
										<div class="checkbox">
											<input type="checkbox" name="allDay" id="allDay"
												<c:if test="${param.allDay eq 'on'}">checked</c:if>>
											<label for="allDay">전체조회</label>
										</div>
									</div>
								</div>
								<div class="search_bot week clearfix">
									<div class="items clearfix">
										<label for="searchName">검색 조건</label>
										<div class="select">
											<select name="searchName" id="searchName">
												<option value="content">일정 내용</option>
											</select>
										</div>
										<input type="text" class="input" name="searchValue" id="searchValue"
											value="${param.searchValue}">
										<button type="submit" class="btn">검색</button>
									</div>

								</div>
							</div>
						</form>

						<div class="list_content">
							<div class="table_wrap">
								<table class="table">
									<colgroup>
										<col width="50px">
										<col width="100px">
										<col width="130px">
										<col width="100px">
										<col width="auto">
										<col width="130px">
									</colgroup>
									<caption class="ir_so">주요일정 관리 목록</caption>
									<thead>
										<tr>
											<th>
												<div class="checkbox">
													<input type="checkbox" name="checkAll" id="checkAll">
													<label for="checkAll"></label>
												</div>
											</th>
											<th>No.</th>
											<th>일정 예정일</th>
											<th>일정 타입</th>
											<th>일정 내용</th>
											<th>공개여부</th>
										</tr>
									</thead>
									<tbody>
									<form name="weekFrm">

										<c:choose>
											<c:when test="${wList.size() > 0}">
												<c:forEach var="w" items="${wList}">
													<tr>
														<td>
															<%-- 체크박스 번호는 글번호로 부여됨 --%>
															<div class="checkbox">
																<input type="checkbox" name="weekCheck"
																	id="${w.scNo}" value="${w.scNo}" <c:if test="${w.scStatus eq 'N'.charAt(0)}">disabled</c:if>> <label for="${w.scNo}"></label>
															</div>
														</td>
														<td>${w.scNo}</td>
														<td>${w.scOpenDate}</td>
														<td class="emp">${w.scCateName}</td>
														<td>
															<div class="tb_txt_box">
																<a href="javascript:;" class="txt"> ${w.scTitle} </a>
															</div>
														</td>
														<td>
															<c:choose>
																<c:when test="${w.scStatus eq 'Y'.charAt(0)}">
																	<p>공개</p>
																</c:when>
																<c:otherwise>
																<p class="status_after">비공개</p>
																</c:otherwise>
															</c:choose>
															
														</td>
													</tr>
													<tr class="view_item">
														<td></td>
														<td></td>
														<td></td>
														<td></td>
														<td colspan="2">
															<div class="table_view">
																<p class="txt">${w.scContent}</p>
																<a href="javascript:weekModify(${w.scNo});" class="view_btn">일정 수정</a>
															</div>
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
									</form>
									</tbody>
								</table>
							</div>
							<div class="btn_box">
								<a href="javascript:remove_popOpen();" style="margin-right:10px;">삭제</a>
								<a href="javascript:weekInsert();">일정 추가</a>
							</div>

							<%-- 검색결과에대한 파라미터값 저장 --%>
							<c:if test="${!empty param.sType && !empty param.sReveal}">
                          		<c:set var="searchParam" value="&sType=${param.sType}&sReveal=${param.sReveal}&searchDay=${param.searchDay}&allDay=${param.allDay}&searchName=${param.searchName}&searchValue=${param.searchValue}"/>
                          	</c:if>
							<jsp:include page="/WEB-INF/views/admin/common/paging.jsp"
								flush="false">
								<jsp:param name="listSize" value="${wList.size()}" />
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
			<h3 class="tit">주요 일정 상태 변경</h3>
			<p class="txt">총 <span class="nub">2</span>건의 일정을 비공개로 변경하시겠습니까?</p>
			<div class="btn_box">
				<a href="javascript:removeStep01();" class="ok">변경</a> 
				<a href="javascript:popHide('remove_step01');" class="cancle">취소</a>
			</div>
		</div>
	</div>

	<!-- 삭제완료 팝업 -->
	<div class="popup_wrap" id="remove_step02">
		<div class="dim"></div>
		<div class="item">
			<h3 class="tit">주요 일정 상태 변경</h3>
			<p class="txt">총 <span class="nub">2</span>건의 일정이 비공개로 변경되었습니다.</p>
			<div class="btn_box">
				<a href="javascript:weekLast();" class="ok">확인</a>
			</div>
		</div>
	</div>
	
	<!-- 팝업영역 -->
	<script>
		let check = false;
        $(function(){
            $('.tb_txt_box .txt').click(function(){
                $(this).parents('tr').toggleClass('on');
                $(this).parents('tr').next('.view_item').find('.table_view').stop().slideToggle(300);

            })
            
         // 전체조회 버튼
         check = $('input[name=allDay]').is(':checked');
            if(check){
           	 $('#searchDay').removeAttr('readonly');
           	 $('#searchDay').attr('disabled', true);
            }else{
           	 $('#searchDay').removeAttr('disabled');
           	 $('#searchDay').attr('readonly', true);
            }
         
         $('input[name=allDay]').change(function(){
        	 check = $('input[name=allDay]').is(':checked');
        	 
        	 if(check){
            	 $('#searchDay').removeAttr('readonly');
            	 $('#searchDay').attr('disabled', true);
             }else{
            	 $('#searchDay').removeAttr('disabled');
            	 $('#searchDay').attr('readonly', true);
             }	
         })
        })
        
     // 일정수정 페이지이동
     function weekModify(scNo){
     	location.href = '${contextPath}/admin/week/view?scNo=' + scNo + '&type=modify';   	
     }
        
     // 일정추가 페이지이동
     function weekInsert(){
     	location.href = '${contextPath}/admin/week/view?type=insert';   	
     }
     
     // 체크박스
     let checkboxs = [];
     $('#checkAll').change(function(){
    	 let checked = $(this).is(':checked');
    	 if(checked){
    		 $('input[name=weekCheck]').prop('checked', true);
    		 $('input[name=weekCheck]:disabled').prop('checked', false);
    	 }else{
    		 $('input[name=weekCheck]').prop('checked', false);
    	 }
    	 
    	 checkArr();
    	 
    	 
     });
     
     $('input[name=weekCheck]').change(function(){
    	// 체크박스 갯수
    	let checkSize = $('input[name=weekCheck]').length;
    	// 체크갯수
    	let checkOnSize = $('input[name=weekCheck]:checked').length;
    	// disalbled 갯수
    	let checkDis = $('input[name=weekCheck]:disabled').length;
    	
    	if(checkSize - checkDis === checkOnSize){
    		// 전체 체크됐을시
    		$('#checkAll').prop('checked', true);
    	}else{
    		// 체크가 하나라도 풀려있을시
    		$('#checkAll').prop('checked', false);
    	}
    	
    	checkArr();
    	
     });
     
     // 체크박스 배열로 변환
     function checkArr(){
    	 checkboxs = [];
    	 for(let i = 0; i < $('input[name=weekCheck]').length; i++){
    		 if($('input[name=weekCheck]').eq(i).is(':checked')){
    			 checkboxs.push($('input[name=weekCheck]').eq(i).val());
    		 }
    	 }
    	 
    	 console.log(checkboxs);
     }
     
     // 게시글 삭제
     function remove_popOpen(){
    	 let check = $('input[name=weekCheck]:checked').length;
    	 if(check > 0){
    		 $('.nub').text(check);
    		 popShow('remove_step01');
    	 }else{
    		 alert('체크박스를 1개이상 선택해주세요.');
    	 }
     }
     
     
     function removeStep01(){
    	 $.ajax({
    		 url : "${contextPath}/admin/week/remove",
    		 traditional : true,
    		 type: "post",
    		 data : {"weekCheck" : checkboxs},
    		 success : function(item){
    			 if(item >= 0){
    				 $('#remove_step02 .item .txt').text("총 " + item + "건의 일정이 비공개 변경되었습니다.");
    			 }else{
    				 $('#remove_step02 .item .txt').text("총 " + item + "건의 일정 변경에 실패하였습니다.");
    			 }
    		 }
    		 
    	 })
    	 
    	$('#remove_step01').removeClass('pop_on');
    	popShow('remove_step02');
    	
     }
     function weekLast(){
    	 location.href = "${contextPath}/admin/week/list?allDay=on"
     }

    </script>
</body>
</html>