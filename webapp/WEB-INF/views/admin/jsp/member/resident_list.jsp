<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>oneLife << 입주자명부 목록</title>

<%-- css/js 파일 --%>
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
						<h2 class="sub_tit">입주자 명부</h2>
						<form action="${contextPath}/admin/member/list" name="residentFrm" onsubmit="return regFunc()">
							<div class="search_box">
								<div class="search_top resident clearfix">
									<div class="items clearfix">
										<label for="rDong">동</label>
										<div class="select">
											<select name="rDong" id="rDong">
												<option value="all">전체</option>
												<c:forEach var="index" begin="${101}" end="${106}">
													<option value="${index}"
														<c:if test="${param.rDong != 'all' && param.rDong == index }">selected</c:if>>${index}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="items clearfix">
										<label for="rHo">호</label> <input type="number" name="rHo"
											id="rHo" class="input" value="${param.rHo}">
									</div>
									<div class="items clearfix">
										<label for="rType">거주 타입</label>
										<div class="select">
											<select name="rType" id="rType">
												<option value="all">전체</option>
												<option value="세대주"
													<c:if test="${param.rType == '세대주'}">selected</c:if>>세대주</option>
												<option value="세대원"
													<c:if test="${param.rType == '세대원'}">selected</c:if>>세대원</option>
											</select>
										</div>
									</div>
									<div class="items clearfix">
										<label for="rStatus">가입 여부</label>
										<div class="select">
											<select name="rStatus" id="rStatus">
												<option value="all">전체</option>
												<option value="Y"
													<c:if test="${param.rStatus == 'Y'}">selected</c:if>>가입</option>
												<option value="N"
													<c:if test="${param.rStatus == 'N'}">selected</c:if>>미가입</option>
											</select>
										</div>
									</div>
								</div>
								<div class="search_bot resident clearfix">
									<div class="items clearfix">
										<label for="sarchName">검색조건</label>
										<div class="select">
											<select name="searchName" id="searchName">
												<option value="name"
													<c:if test="${param.searchName == 'name'}">selected</c:if>>이름</option>
												<option value="email"
													<c:if test="${param.searchName == 'email'}">selected</c:if>>이메일</option>
											</select>
										</div>
										<input type="text" name="searchValue" id="searchValue" class="input"
											value="${param.searchValue}">
										<button type="submit" class="btn">검색</button>
									</div>
								</div>
							</div>
						</form>

						<div class="list_content">
							<div class="table_wrap resident_list">
								<table class="table">
									<colgroup>
										<%-- <col width="auto"> --%>
										<col width="auto">
										<col width="auto">
										<col width="auto">
										<col width="auto">
										<col width="auto">
										<col width="auto">
										<c:if test="${loginManager.mJobcode eq 'M_CODE2'}">
											<col width="auto">
										</c:if>
									</colgroup>
									<caption class="ir_so">입주자 명부 목록</caption>
									<thead>
										<tr>
											<!-- <th>No.</th> -->
											<th>동</th>
											<th>호수</th>
											<th>이름</th>
											<th>이메일</th>
											<th>거주타입</th>
											<th>가입여부</th>
											<c:if test="${loginManager.mJobcode eq 'M_CODE2'}">
												<th>변경</th>
											</c:if>
										</tr>
									</thead>
									<tbody>
										<c:choose>
											<c:when test="${rList.size() > 0}">
												<%-- 목록이 존재할시 --%>
												<c:forEach var="r" items="${rList}">
													<tr>
														<%-- <td>${r.rNo}</td> --%>
														<td class="dong">${r.rDong}</td>
														<td class="ho">${r.rHo}</td>
														<td>${r.rName}</td>
														<td>${r.rEmail}</td>
														<td><c:choose>
																<c:when test="${r.rType eq '세대주'}">
																	<p class="tag type01">${r.rType}</p>
																</c:when>
																<c:otherwise>
																	<p class="tag type02">${r.rType}</p>
																</c:otherwise>
															</c:choose></td>
														<td><c:choose>
																<c:when test="${r.rStatus eq 'Y'}">
																	<p class="tag type01" onclick="userInfo(${r.rNo});" style="cursor:pointer;">가입</p>
																</c:when>
																<c:otherwise>
																	<p class="tag type03">미가입</p>
																</c:otherwise>
															</c:choose></td>
															
															<c:if test="${loginManager.mJobcode eq 'M_CODE2'}">
																<td class="modify"><a
																	href="${contextPath}/admin/member/view?rDong=${r.rDong}&rHo=${r.rHo}"
																	class="tag">변경</a></td>
															</c:if>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<%-- 목록의 결과값이 0개 일시 --%>
												<tr>
													<td>
														<div class="list_nodate">
															<img
																src="/oneLife/resources/admin/images/list_nodate.png"
																alt="NODATE">
															<p>등록된 입주자 정보가 없습니다.</p>
														</div>
													</td>
												</tr>
											</c:otherwise>
										</c:choose>
									</tbody>
								</table>
							</div>


							<%-- 페이징 --%>
							<c:if test="${!empty param.rDong}">
								<c:set var="searchParam"
									value="&rDong=${param.rDong}&rHo=${param.rHo}&rType=${param.rType}&rStatus=${param.rStatus}&searchName=${param.searchName}&searchValue=${param.searchValue}" />
							</c:if>
							<jsp:include page="/WEB-INF/views/admin/common/paging.jsp"
								flush="false">
								<jsp:param name="listSize" value="${rList.size()}" />
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
	<!-- 삭제완료 팝업 -->
	<div class="popup_wrap" id="residentPop">
		<div class="dim"></div>
		<div class="item">
			<h3 class="tit">입주자 명부 변경</h3>
			<p class="txt">입주자 명부가 정상적으로 변경되었습니다.</p>
			<div class="btn_box">
				<a href="javascript:popHide('residentPop');" class="ok">확인</a>
			</div>
		</div>
	</div>
	
	<!-- 유저 회원가입 여부 -->
	<div class="popup_wrap" id="userInfoSelect">
        <div class="dim"></div>
        <div class="item">
            <h3 class="tit">회원 가입내역</h3>
            <ul>
               <li>
                    <span>회원번호 : </span>
                    <p class="txt1"></p>
               </li>
               <li>
                    <span>아이디 : </span>
                    <p class="txt2"></p>
               </li>
                <li>
                    <span>거주세대 : </span>
                    <p class="txt3"></p>
               </li>
               <li>
                    <span>닉네임 : </span>
                    <p class="txt4"></p>
                </li>
                <li>
                    <span>연락처 : </span>
                    <p class="txt5"></p>
               </li>
               <li>
                    <span>가입일 : </span>
                    <p class="txt6"></p>
               </li>
            </ul>
            <div class="btn_box">
                <a href="javascript:popHide('userInfoSelect');" class="ok">확인</a>
            </div>
        </div>
    </div>
	
	<!-- 검색 유효성 검사 -->
	<script>
		function regFunc(){
			const ho_reg = /^[0123][0][12345]$/;
			
			// 호 101~305호 검사
			if($('#rHo').val().length != 0 && !ho_reg.test($('#rHo').val())){
				alert("101 ~ 305 사이의 숫자로 입력해주세요!");
				$('#rHo').select();
				return false;
			}	
			
			const search_reg = /^[가-힣]{1,4}$/;
			const search_reg2 = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
			
			// 검색조건 검사
			if($('#searchValue').val().length != 0){
				// 이름 검색 일시
				if($('#searchName').val() == 'name'){
					if(!search_reg.test($('#searchValue').val())){
						alert("1~4자리의 한글로 입력해주세요!");
						$('#searchValue').select();
						return false;
					}
					
				}else if($('#searchName').val() == 'email'){
					// 이메일 검색 일시
					if(!search_reg2.test($('#searchValue').val())){
						alert("이메일형식으로 입력해주세요!");
						$('#searchValue').select();
						return false;
					}
				}
				
			}
		}
	</script>
	

	<!-- 홈페이지 회원가입 유저정보 -->
	<script>
		function userInfo(rNo){
			$.ajax({
				url : "${contextPath}/admin/member/userInfo",
				type : "post",
				data : { rNo : rNo  },
				success : function(item){
					$('#userInfoSelect .tit').text(item.rName + "님 회원 가입내역");
					$('.txt1').text(item.uNo);
					$('.txt2').text(item.uId);
					$('.txt3').text(item.rDong + "동 " + item.rHo + "호");
					$('.txt4').text(item.uNickName);
					$('.txt5').text(item.uPhone);
					$('.txt6').text(item.rDate);
					
					popShow('userInfoSelect');
				},
				error : function(){
					console.log('에러발생');
				}
			});
			
			
		}
	
  		// 동계산
  		let index = 0;
  		let firstDong = $('.table_wrap .table tbody tr:eq(0) td.dong').text();
  		let dong = [0];
  		for(let i = 0; i < ${rList.size()}; i++){
  			if(firstDong == $('.table_wrap .table tbody tr:eq('+i+') td.dong').text()){
  				dong[index] = dong[index] + 1;
  			}else{
  				firstDong = $('.table_wrap .table tbody tr:eq('+i+') td.dong').text();
  				dong.push(0);
  				++index;
  				dong[index] = dong[index] + 1;
  			}
  			
  		}
  		
  		// 호 계산
  		index = 0;
  		firstDong = $('.table_wrap .table tbody tr:eq(0) td.dong').text();
  		let firstHo = $('.table_wrap .table tbody tr:eq(0) td.ho').text();
  		let ho = [0];
  		for(let i = 0; i < ${rList.size()}; i++){
  			if(firstDong == $('.table_wrap .table tbody tr:eq('+i+') td.dong').text() && firstHo == $('.table_wrap .table tbody tr:eq('+i+') td.ho').text()){
  				ho[index] = ho[index] + 1;
  			}else{
  				ho.push(1);
  				index++;
  				firstDong = $('.table_wrap .table tbody tr:eq('+i+') td.dong').text();
  				firstHo = $('.table_wrap .table tbody tr:eq('+i+') td.ho').text();
  			}
  			
  		}
  		
  		// 동 적용
  		index = 0;
  		let current = 0;
  		let result = true;
  		for(let i = 0; i < dong.length; i++){
  			for(let j = 0; j < dong[i]; j++){
  				if(result){
  					$('.table_wrap .table tbody tr:eq('+ index++ +') td.dong').attr('rowspan', dong[i]);
  					result = false;
  				}else{
  					$('.table_wrap .table tbody tr:eq('+ index++ +') td.dong').remove();
  				}
  			}
  			result = true;
  			
  		}
  		
  		// 호 적용
  		index = 0;
  		result = true;
  		let number = 0;
  		for(let i = 0; i < ho.length; i++){
  			for(let j = 0; j < ho[i]; j++){
  				if(result){
  					if(i == 0){
      					$('.table_wrap .table tbody tr:eq('+ index +') td.modify').attr('rowspan', ho[i]);
      					$('.table_wrap .table tbody tr:eq('+ index++ +') td.ho').attr('rowspan', ho[i]);
  					}else{
  						$('.table_wrap .table tbody tr:eq('+ index +') td.modify').attr('rowspan', ho[i]);
   						$('.table_wrap .table tbody tr:eq('+ index++ +') td.ho').attr('rowspan', ho[i]);
  					}
  					
  					result = false;
  				}else{
  					$('.table_wrap .table tbody tr:eq('+ index +') td.modify').remove();
  					$('.table_wrap .table tbody tr:eq('+ index++ +') td:eq(0)').remove();
  				}
  			}
  			result = true;
  		}   
    		
   		// nodate 수정
   		let thSize = $('.table_wrap .table thead tr th').length;
   		//console.log(thSize);
   		$('.content .list_nodate').parent('td').attr('colspan', thSize);
    		
    	</script>
   		<%-- 세대명부 수정 여부 --%>
		<c:choose>
			<c:when test="${sessionScope.msg eq 'success'}">
				<script>
					popShow('residentPop');
				</script>
			</c:when>
			<c:when test="${sessionScope.msg eq 'fail'}">
				<script>
				alert("입주자 명부 변경에 실패하였습니다.");
				</script>
			</c:when>
		</c:choose>
		<c:remove var="msg" scope="session"/>

</body>
</html>