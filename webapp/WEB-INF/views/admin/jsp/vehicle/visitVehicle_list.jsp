<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1" />
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0,minimum-scale=1.0,target-densitydpi=medium-dpi" />
<title>방문차량 예약 현황</title>


<jsp:include page="/WEB-INF/views/admin/common/link.jsp"></jsp:include>
<style>
.board_paging {
	display: flex;
	align-items: center;
	justify-content: center;
	list-style: none;
	margin-top: 50px;
	padding-left: 0;
}

.board_paging a {
	display: block;
	width: 29px;
	height: 36px;
	box-sizing: border-box;
	font-weight: bold;
	text-decoration: none;
	border-radius: 5px;
	border: 1px solid #d2d6dc;
	color: #aea9a9;
	text-align: center;
	line-height: 36px;
	margin: 0 5px;
	transition: all 0.3s;
}

.board_paging a:hover {
	border-color: #4094f7;
	color: #4094f7;
}

.board_paging a.current_page {
	border-color: #4094f7;
	color: #4094f7;
}

.status:hover {
	cursor: pointer;
}

.submitBtn {
	/* display: block;  */
	width: 160px;
	height: 40px;
	line-height: 40px;
	border-radius: 4px;
	text-align: center;
	background: #3C90F2;
	margin: 0 5px;
	color: #fff;
	box-shadow: 0 4px 4px rgba(0, 0, 0, 0.25);
	font-size: 14px;
}

#addInput1, #addInput2 {
	width: 100px;
}

#dong, #ho {
	width: 30px;
	padding-left: 0;
	text-align: center;
}

.table td {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.table {
margin-top : 30px;
}

/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 15% auto; /* 15% from the top and centered */
  padding: 20px;
  border: 1px solid #888;
  width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button */
.close {
  color: #aaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}
</style>
<%
	if (session.getAttribute("msg") != null) {
%>
<script>
	alert('<%=session.getAttribute("msg")%>');
</script>
<%
	session.removeAttribute("msg");

}
%>
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
						<h2 class="sub_tit">방문차량 예약 현황</h2>
						<div class="search_box type02">
							<a href="javascript:popShow('onSite');" class="vehicle_btn">방문차량
								현장접수</a>
							<form action="${contextPath}/admin/visit/list" method="get">
							
							<div class="search_top clearfix">
								<div class="items clearfix">
									<label for="">예약일자</label>
									<div class="calendar clearfix">
										<input type="text" name="date" id="searchDate" class="cal" 
										<c:choose>
										<c:when test="${ param.date eq 'all' }">
										value="전체"
										</c:when>
										<c:when test="${ !empty param.date }">
										value="${param.date}"
										</c:when>
										<c:otherwise>
										value="전체"
										</c:otherwise>
										</c:choose>>
										<!-- <span>~</span> <input type="text" name="" id="" class="cal"
											readonly> -->
									</div>
									<!-- <div class="checkbox">
										<input type="checkbox" name="test" id="test"> <label
											for="test">전체조회</label>
									</div> -->

								</div>
								<div class="items clearfix">
									<label for="reser_fac">입차현황</label>
									<div class="select">
										<select name="status" id="reser_fac">
											<option value="all" <c:if test="${param.status == 'all'}"> selected</c:if>>전체</option>
											<option value="N" <c:if test="${param.status == 'N'}"> selected</c:if>>접수</option>
											<option value="Y" <c:if test="${param.status == 'Y'}"> selected</c:if>>완료</option>
										</select>
									</div>
								</div>
							</div>
							<div class="search_bot type02 clearfix">
								<div class="items clearfix">
									<label for="">차량번호</label> <input type="text" class="input"
										placeholder="띄어쓰기 없이 입력해주세요!" style="width: 244px;" name="carNo" 
										<c:choose>
										<c:when test="${ param.carNo eq 'all' }">
										value="전체"
										</c:when>
										<c:when test="${ !empty param.carNo }">
										value="${param.carNo}"
										</c:when>
										<c:otherwise>
										value="전체"
										</c:otherwise>
										</c:choose>>
								</div>
								<div class="items clearfix">
									<label for="reser_fac">방문세대</label>
									<div class="select">
										<select name="dong" id="searchDong">
											<option value="all">동</option>
											
										</select>
									</div>
									<input type="hidden" value="${param.dong}" id="dropDong">
									<input type="hidden" value="${param.ho}" id="dropHo">
									<div class="select">
										<select name="ho" id="searchHo">
											<option value="all">호</option>
											
										</select>
									</div>
									<button id="searchBtn" class="btn">검색</button>
								</div>
							</div>
						</form>
						</div>
						<!-- <h3 class="table_title">2021년 8월 20일 ~ 2021년 8월 21일</h3> -->

						<div class="list_content">
							<div class="table_wrap">
								<table class="table">
									<colgroup>
										<col width="60px">
										<col width="auto">
										<col width="auto">
										<col width="auto">
										<col width="auto">
										<col width="auto">
										<col width="auto">
										<col width="auto">
									</colgroup>
									<caption class="ir_so">방문차량 예약 현황</caption>
									<thead>
										<tr>
											<th>No.</th>
											<th>방문일</th>
											<th>차량 번호</th>
											<th>차주 연락처</th>
											<th>방문 세대</th>
											<th>방문 목적</th>
											<th>신청인</th>
											<!-- <th>신청인 연락처</th> -->
											<th>상태</th>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${fn:length(visitCarList) ne 0}">
											<c:forEach var="v" items="${visitCarList}">
											<tr>
												<td>${v.VC_ID}</td>
												<td>${v.VC_DATE}</td>
												<td>${v.VC_NO}</td>
												<td>${v.VC_PHONE}</td>
												<td>${v.r_DONG}동${v.r_HO}호</td>
												<td>${v.VC_PURPOSE}</td>
												<c:choose>
													<c:when test="${v.mName eq null}">
														<td>${v.r_NAME}</td>
													</c:when>
													<c:otherwise>
														<td>${v.mName}</td>
													</c:otherwise>
												</c:choose>
												<!-- <td>010-1234-5678</td> -->
												<c:set var="status" value="N" />
												<c:set var="current" value="<%=new java.util.Date()%>" />
												<fmt:formatDate value="${current }" type="both"
													pattern="yyyy-MM-dd" var="today" />

												<c:choose>
													<c:when test="${v.VC_STATUS eq status}">
														<td><p class="status status_before2"
																onclick="visitCheck(${v.VC_ID}, '${v.VC_DATE}')">접수</p></td>

													</c:when>
													<c:otherwise>
														<td><p class="status status_ing"
																onclick="visitCancel(${v.VC_ID}, '${v.VC_DATE}')">완료</p></td>
													</c:otherwise>
												</c:choose>
											</tr>
											<!-- 방문차량 접수완료 -->
											<form action="${contextPath}/admin/visit/check" method="post">
												<div class="popup_wrap" id="checkOk${v.VC_ID}">
													<div class="dim"></div>
													<div class="item">
														<div class="new_admin">
															<div class="title">방문차량 접수를 완료하시겠습니까?</div>
															<div class="new_item">
																<label for="">등록번호</label> <input type="text" name="vid"
																	value="${v.VC_ID}" readonly>
															</div>
															<div class="new_item">
																<label for="">방문일</label> <input type="text"
																	value="${v.VC_DATE}" readonly>
															</div>
															<div class="new_item">
																<label for="">차량번호</label> <input type="text"
																	value="${v.VC_NO}" readonly>
															</div>
															<div class="new_item">
																<label for="">방문목적</label> <input type="text"
																	value="${v.VC_PURPOSE}" readonly>
															</div>
															<div class="new_item">
																<label for="">방문세대</label> <input type="text"
																	value="${v.r_DONG}동${v.r_HO}호" readonly>
															</div>
															<div class="new_item">
																<label for="">비상연락처</label> <input type="text"
																	value="${v.VC_PHONE}" readonly>
															</div>
														</div>
														<div class="new_btn_box">
															<button class="submitBtn">확인하기</button>
															<a href="#" onclick="closeCheck(${v.VC_ID})">취소하기</a>
														</div>
													</div>
												</div>
											</form>
											<!-- 방문차량 접수취소 -->
											<form action="${contextPath}/admin/visit/checkCancel"
												method="post">
												<div class="popup_wrap" id="checkCancel${v.VC_ID}">
													<div class="dim"></div>
													<div class="item">
														<div class="new_admin">
															<div class="title">방문차량 접수를 취소하시겠습니까?</div>
															<div class="new_item">
																<label for="">등록번호</label> <input type="text" name="vid"
																	value="${v.VC_ID}" readonly>
															</div>
															<div class="new_item">
																<label for="">방문일</label> <input type="text"
																	value="${v.VC_DATE}" readonly>
															</div>
															<div class="new_item">
																<label for="">차량번호</label> <input type="text"
																	value="${v.VC_NO}" readonly>
															</div>
															<div class="new_item">
																<label for="">방문목적</label> <input type="text"
																	value="${v.VC_PURPOSE}" readonly>
															</div>
															<div class="new_item">
																<label for="">방문세대</label> <input type="text"
																	value="${v.r_DONG}동${v.r_HO}호" readonly>
															</div>
															<div class="new_item">
																<label for="">비상연락처</label> <input type="text"
																	value="${v.VC_PHONE}" readonly>
															</div>
														</div>
														<div class="new_btn_box">
															<button class="submitBtn">취소하기</button>
															<a href="#" onclick="closeCancel(${v.VC_ID})">돌아가기</a>
														</div>
													</div>
												</div>
											</form>
										</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
                                    			<td>
                                    				<div class="list_nodate">
														<img src="/oneLife/resources/admin/images/list_nodate.png" alt="NODATE">
													<p>신고된 게시글이 존재하지 않습니다.</p>
												</div>
                                    			</td>
                                    		</tr>
                                    		<script>
									    		let thSize = $('.table_wrap .table thead tr th').length;
									    		$('.content .list_nodate').parent('td').attr('colspan', thSize);
											</script>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>

							<!-- <div class="paging_wrap type02">
							<a href="javascript:;" class="btn_prev"></a> <a
								href="javascript:;" class="btn_num on">1</a> <a
								href="javascript:;" class="btn_num">2</a> <a href="javascript:;"
								class="btn_num">3</a> <a href="javascript:;" class="btn_num">4</a>
							<a href="javascript:;" class="btn_num">5</a> <a
								href="javascript:;" class="btn_next"></a>
						</div> -->
						<c:if test="${fn:length(visitCarList) ne 0}">
							<ul class="board_paging">
							
							<%-- 검색 결과 화면인 경우 넘겨줄 searchParam 정의 --%>
							<c:choose>
								<c:when test="${ empty param.dong && empty param.ho && empty param.carNo && empty param.date && empty param.status}">
								</c:when>
								<c:when test="${ !empty param.dong && !empty param.ho && !empty param.carNo && !empty param.date && !empty param.status}">
									<c:set var="searchParam" value="&dong=${param.dong}&ho=${param.ho }&carNo=${param.carNo}&date=${param.date}&status=${param.status}" />
								</c:when>
								<c:when test="${ !empty param.dong && !empty param.ho && !empty param.carNo && empty param.date && !empty param.status}">
									<c:set var="searchParam" value="&dong=${param.dong}&ho=${param.ho }&carNo=${param.carNo}&date=all&status=${param.status}" />
								</c:when>
								<c:when test="${ !empty param.dong && !empty param.ho && empty param.carNo && !empty param.date && !empty param.status}">
									<c:set var="searchParam" value="&dong=${param.dong}&ho=${param.ho }&carNo=all&date=${param.date}&status=${param.status}" />
								</c:when>
								<c:otherwise>
									<c:set var="searchParam" value="&dong=${param.dong}&ho=${param.ho }&carNo=all&date=all&status=${param.status}" />
								</c:otherwise>
							</c:choose>
								<!-- 맨 처음으로 (<<) -->
								<li><a
									href="${contextPath }/admin/visit/list?page=1${searchParam}">&lt;&lt;</a></li>
								<!-- 이전 페이지로(<) -->
								<li><c:choose>
										<c:when test="${pi.page > 1 }">
											<a
												href="${contextPath }/admin/visit/list?page=${pi.page-1}${searchParam}">&lt;</a>
										</c:when>
										<c:otherwise>
											<a href="#">&lt;</a>
										</c:otherwise>
									</c:choose></li>

								<!-- 페이지 목록 (최대 10개) -->
								<c:forEach var="p" begin="${pi.startPage }" end="${pi.endPage }">
									<li><c:choose>
											<c:when test="${p eq pi.page }">
												<a href="#" class="current_page">${p}</a>
											</c:when>
											<c:otherwise>
												<a
													href="${contextPath }/admin/visit/list?page=${p}${searchParam}">${p}</a>
											</c:otherwise>
										</c:choose></li>
								</c:forEach>

								<!-- 다음 페이지로 (>) -->
								<li><c:choose>
										<c:when test="${pi.page < pi.maxPage }">
											<a
												href="${contextPath }/admin/visit/list?page=${pi.page+1}${searchParam}">&gt;</a>
										</c:when>
										<c:otherwise>
											<a href="#">&gt;</a>
										</c:otherwise>
									</c:choose></li>

								<!-- 맨 끝으로 (>>) -->
								<li><a
									href="${contextPath }/admin/visit/list?page=${pi.maxPage}${searchParam}">&gt;&gt;</a></li>
							</ul>
							</c:if>
							<!-- <ul class="board_paging">
			<li><a href="javascript:;">&lt;</a></li>
			<li><a href="javascript:;" class="current_page">1</a></li>
			<li><a href="javascript:;">&gt;</a></li>
		</ul> -->
						</div>
						<!-- <div id="warningModal" class="modal">
		<div class="modal-content">
			<p>방문일이 지난 내역은 수정하실 수 없습니다.</p>
			<span id="warningClose" class="close">&times;</span>
		</div>
	</div> -->


					</div>
				</div>
		</div>
		</section>
	</div>


	<!-- 팝업영역 -->
	<!-- 방문차량 현장접수 -->
	<form action="${contextPath }/admin/visit/register" method="post">
		<div class="popup_wrap" id="onSite">
			<div class="dim"></div>
			<div class="item">
				<div class="new_admin">
					<div class="title">방문차량 현장접수</div>
					<div class="new_item">
					<c:set var="current" value="<%= new java.util.Date() %>"/>
					<fmt:formatDate var="elToday" value="${current }" type="both" pattern="yyyy-MM-dd"/>
						<label>방문일</label> <input type="date" name="date" id="date"
							readonly value="${elToday}">
					</div>
					<div class="new_item">
						<label>차량번호</label> <input type="text"
							placeholder="ex) 35로 5432 띄어쓰기 없이 적어주세요." name="carNo"
							maxlength="11" required>
					</div>
					<div class="new_item">
						<label>방문목적</label> <input type="text"
							placeholder="ex) 에어컨 수리 업체 방문" name="purpose" maxlength="20"
							required>
					</div>
					<div class="new_item">
						<label>방문세대</label>
						<!-- <input id="addInput1" type="text" placeholder="ex) 1" maxlength="3" name="dong" required> -->
						<select name="dong" id="addInput1" required>
							<option value="" selected="selected">동</option>
						</select> <label id="dong">동</label>
						<!-- <input  type="text" placeholder="ex) 101" maxlength="3" id="addInput2" name="ho" required> -->
						<select name="ho" id="addInput2" required>
							<option value="" selected="selected">호</option>
						</select> <label id="ho">호</label>
					</div>
					<div class="new_item">
						<label>비상연락처</label> <input type="text"
							placeholder="차주의 휴대폰 번호를 입력해주세요." name="phone" maxlength="15"
							required>
					</div>
				</div>
				<div class="new_btn_box">
					<button class="submitBtn">등록하기</button>
					<!-- <button class="submitBtn" onclick="return checkDate()">등록하기</button> -->
					<button class="submitBtn" onclick="popHide('onSite')" type="button">취소하기</button>
					<!-- <a href="javascript:popHide('onSite');">취소하기</a> -->
				</div>
			</div>
		</div>
	</form>
	<!-- 팝업영역 -->
	
	
	<script type="text/javascript">
		/* var onSite = document.getElementById("onSite");
		window.onclick = function(event) {
			if (event.target == onSite) {
				popHide('onSite');
			}
			
			if (event.target == onSite) {
				popHide('onSite');
			}
		}
		 */
		function closeCheck(no) {
			document.querySelector('#checkOk' + no).classList.remove('pop_on');
		}
		 
		function closeCancel(no) {
			document.querySelector('#checkCancel' + no).classList.remove('pop_on');
		} 
		
		 function visitCheck(vid, date) {
			 let today = new Date();
		    	today.setHours(0);
		    	today.setMinutes(0);
		    	today.setSeconds(0);
		    	today.setMilliseconds(0);
		    	let visitDate = new Date(date);
		    	visitDate.setHours(0);
		    	visitDate.setMinutes(0);
		    	visitDate.setSeconds(0);
		    	visitDate.setMilliseconds(0);
			 if (visitDate.getTime() == today.getTime()) {
			 popShow('checkOk'+vid);
			 } else {
			 alert('당일 내역만 처리할 수 있습니다.');				 
			 }
		 } 
		 
		 function visitCancel(vid, date) {
			 let today = new Date();
		    	today.setHours(0);
		    	today.setMinutes(0);
		    	today.setSeconds(0);
		    	today.setMilliseconds(0);
		    	let visitDate = new Date(date);
		    	visitDate.setHours(0);
		    	visitDate.setMinutes(0);
		    	visitDate.setSeconds(0);
		    	visitDate.setMilliseconds(0);
			 if (visitDate.getTime() == today.getTime()) {
			 popShow('checkCancel'+vid);
			 } else {
			 alert('당일 내역만 처리할 수 있습니다.');				 
			 }
		 }
		 
		 
		 function checkDate() {
			let today = new Date();
	    	today.setHours(0);
	    	today.setMinutes(0);
	       	today.setSeconds(0);
	    	today.setMilliseconds(0);
 			let date = $('#date').val();
			let visitDate = new Date(date);
			visitDate.setHours(0);
		   	visitDate.setMinutes(0);
		   	visitDate.setSeconds(0);
		   	visitDate.setMilliseconds(0);
		   	if (visitDate.getTime() != today.getTime()) {
		   		alert('당일에 해당하는 내역만 접수할 수 있습니다.');
		   		return false;
			} else {
				 return true;
				 
			 }
			}
		 
		/* function visitCheckOk(vid) {
			$.ajax({
				url : "${contextPath}/admin/visit/check",
				type : "post",
				data : {vid : vid},				
				dataType : "json",
				success : function(data){
					if (data == "success") {
						alert("입차 처리 완료!");
					} else {
						alert("완료 처리 실패!");
					}
				},
				error : function(e){
					console.log(e);
				} 
			});
		} */
		
		/* 동, 호수 드롭다운 박스 */
		 var subjectObject = {
			101: [101, 102, 103, 104, 105, 201, 202, 203, 204, 205, 301, 302, 303, 304, 305],
			102: [101, 102, 103, 104, 105, 201, 202, 203, 204, 205, 301, 302, 303, 304, 305],
			103: [101, 102, 103, 104, 105, 201, 202, 203, 204, 205, 301, 302, 303, 304, 305],
			104: [101, 102, 103, 104, 105, 201, 202, 203, 204, 205, 301, 302, 303, 304, 305],
			105: [101, 102, 103, 104, 105, 201, 202, 203, 204, 205, 301, 302, 303, 304, 305],
			106: [101, 102, 103, 104, 105, 201, 202, 203, 204, 205, 301, 302, 303, 304, 305]
		}
		
		window.onload = function() {
		  var inputDong = document.getElementById("addInput1");
		  var inputHo = document.getElementById("addInput2");
		  for (var x in subjectObject) {
			  inputDong.options[inputDong.options.length] = new Option(x, x);
		  }
		  inputDong.onchange = function() {
		    //empty Chapters- and Topics- dropdowns
		    inputHo.length = 1;
		    //display correct values
		    var z = subjectObject[this.value];
    		for (var i = 0; i < z.length; i++) {
    			inputHo.options[inputHo.options.length] = new Option(z[i], z[i]);
    		}
		  }
		  
		  var searchDong = document.getElementById("searchDong");
		  var searchHo = document.getElementById("searchHo");
		  for (var x in subjectObject) {
			  searchDong.options[searchDong.options.length] = new Option(x, x);
		  }
		  searchDong.onchange = function() {
		    //empty Chapters- and Topics- dropdowns
		    searchHo.length = 1;
		    //display correct values
		    var z = subjectObject[this.value];
    		for (var i = 0; i < z.length; i++) {
    			searchHo.options[searchHo.options.length] = new Option(z[i], z[i]);
    		} 
		  }
		  
		  //검색 조건 드롭박스 조건 유지
		  let dropDong = '${ param.dong }';
		  let dropHo = '${ param.ho }';
		  
		  for (var i = 0; i < searchDong.length; i++) {
			  if (searchDong.options[i].value == dropDong) {
				  searchDong.options[i].selected = true;
			  }
		  } 
		  
		  if (dropDong != "" && dropHo != "") {
			  var y = subjectObject[dropDong];
			  
			  for (var i = 0; i < y.length; i++) {
	    			searchHo.options[searchHo.options.length] = new Option(y[i], y[i]);
	    			if (searchHo.options[i+1].value == dropHo) {
		  				  searchHo.options[i+1].selected = true;
		  			 }
	    		} 
		  }
		 
		}
	</script>

</body>

</html>