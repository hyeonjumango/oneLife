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
<title>입주차량 현황</title>
<%if (session.getAttribute("msg") != null) { %>
<script>
	alert('<%= session.getAttribute("msg")%>');
</script>
<%
	session.removeAttribute("msg");

} %>
<jsp:include page="/WEB-INF/views/admin/common/link.jsp"></jsp:include>
<style>
.inVehicle {
	display: flex;
	justify-content: space-around;
	border-bottom: 1px solid black;
}

.vehicleList_head {
display: flex;
	justify-content: space-around;
}
.inVehicle li {
	display: inline-block;
}

.inVehicleLeft {
	width: 25%;
	display: flex;
}

.headRight {
width : 75%;

}

.headLeft {
width : 25%;
}

.headLeft ul {
	display: flex;
	justify-content: center;
}

.headRight ul {
	display: flex;
	justify-content: space-around;
}

.headLeft li {
width : 40px;
text-align : center;
}

.headRight li{
width : 75px;
text-align : center;
}

.inVehicleRight {
	width: 75%;
	display: flex;
	flex-direction: column;
}

.inVehicle ul {
	margin: auto auto;
}

.divLine {
	width: 100%;
}

.add:hover {
	cursor: pointer;
}

.remove:hover {
	cursor: pointer;
}

#addInput1, #addInput2, #deleteInput1, #deleteInput2 {
	width: 100px;
}

#dong, #ho, #deleteDong, #deleteHo {
	width: 30px;
	padding-left: 0;
	text-align: center;
}

.meberCarBtn {
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

#add {
	cursor: pointer;
	width: 130px;
	height: 35px;
	border: 0px;
	color: white;
	background: #00a3fe;
	margin: 10px;
	border-radius: 6px;
	box-shadow: 0px 1px 1px rgb(0 0 0/ 14%), 0px 2px 1px rgb(0 0 0/ 12%),
		0px 1px 3px rgb(0 0 0/ 20%);
}

.vehicle_new .vehicle_content{width: 510px; margin-bottom: 10px; }
.vehicle_new .vehicle_content ul{display: flex;}
.vehicle_new .vehicle_content ul.v_body{border-bottom:1px dashed #bebebe;}
.vehicle_new .vehicle_content ul.v_body:last-of-type{border-bottom:1px solid #bebebe;}
.vehicle_new .vehicle_content ul li{display: block; width: 100%; line-height: 50px; text-align: center; font-size: 16px; font-weight: bold; color:#666; margin-bottom: 0;}
.vehicle_new .vehicle_content ul li:nth-child(1){width: 160px;}
.vehicle_new .vehicle_content ul li:nth-child(2){width: 175px;}
.vehicle_new .vehicle_content ul li:nth-child(3){width: 175px;}
.vehicle_new .vehicle_content ul.v_body li:nth-child(2){width: 175px; font-size: 15px; color:#3A3B3F; font-weight: 400;}
.vehicle_new .vehicle_content ul.v_body li:nth-child(3){width: 175px; font-size: 15px; color:#3A3B3F; font-weight: 400;}
.vehicle_new .vehicle_list{margin-bottom: 0; }


.board_paging {
	display: flex;
	align-items: center;
	justify-content: center;
	list-style: none;
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

.inVehicle li {
line-height : 40px;
}

.inVehicleLeft li {
width : 40px;
text-align : center;
color : #3C90F2;
}

.inVehicleRight li{
width : 75px;
text-align : center;
}

.inVehicleRight ul:not(:nth-of-type(1)) {
/* background-color : black; */
 border-top: 1px solid #e6e6e6; 
}
.memberCarClose {
    display: inline-block;
    width: 20px;
    height: 20px;
    background: url(../../resources/admin/images/ico_close.png) no-repeat 50% 50%;
    vertical-align: middle;
}

.inVehicle {
    border-bottom: 2px solid #666666;
}

.vehicleList_body {
    padding : 0 15px;
}

.searchImg {
margin-bottom : 20px;
}

.inVehicleLeft {
border-right : 1px solid #e6e6e6; 
}

.precautions_wrap {
display : flex;
align-items : center;
}
.list_nodate {
display : flex;
flex-direction : column;
}

.list_nodate > img {
width : 200px;
margin : 0 auto;
}

#nodataP {
font-size : 20px;
margin : 0 auto;
}

.content .list_nodate {
padding-bottom : 30px;
}
</style>
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
				<div class="inVehicle_wrap">
					<h2 class="title">입주차량 현황</h2>
					<div class="items bg">
						<div class="searchbox">
						<div class="searchImg">
							<img src="../../resources/admin/images/bg_vehicle.png" width="100%">
							</div>
							<form action="${contextPath}/admin/in/list" method="get">
							
							<div class="line">
								<label for="">조회세대</label>
								<div class="select">
									<select name="dong" id="searchInput1">
										<option value="all">전체</option>
									</select>
								</div>
								<label for="">동</label>
								<div class="select">
									<select name="ho" id="searchInput2">
										<option value="all">전체</option>
									</select>
								</div>
								<label for="">호</label>
							</div>
							<div class="line">
								<label for="">차량번호</label>
								<div class="input">
									<input type="text" name="carNo" id="searchCarNo" placeholder="띄어쓰기 없이 입력해주세요!" 
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
								<button class="btn">검색</button>
							</div>
							</form>
							<div class="excel_wrap">
								<a href="#" class="excel_btn" onclick="exportData()">검색 결과 엑셀로 다운받기</a>
							</div>
							<div class="precautions_wrap">
								<button type="button" id="add" onclick="openAddPopUp()">신규차량 추가하기</button>
								<div>
									<p>※주의사항 : 꼭 입금 확인 후 등록해주세요!</p>
									<p>세대별 기본 : 1대 (추가 : 10,000원/대)</p>
									<p>국민은행 kh아파트 222202-01-013452</p>
								</div>
							</div>
							
						</div>
					</div>
					<div class="items">
						<div class="vehicleList_wrap">
							<div class="vehicleList_head">
								<div class="headLeft">
								<ul>
									<li>동</li>
									<li>호</li>
									</ul>
								</div>
									<div class="headRight">
									<ul>
								
									<li>차량번호</li>
									<li>신청인</li>
									<li>비상연락처</li>
									<li>삭제</li>
								</ul>
									</div>
							</div>

							<div class="vehicleList_body">
							<c:if test="${fn:length(houseHoldCarList) eq 0}">
								<div class="list_nodate">
									<img src="/oneLife/resources/admin/images/list_nodate.png" alt="NODATE">
									<p id="nodataP">검색 결과가 존재하지 않습니다.</p>
								</div>
							</c:if>
								<!-- 한세트 -->
								<c:forEach var="m" items="${houseHoldCarList}">
									<div class="inVehicle">
										<div class="inVehicleLeft">
											<ul>
												<li>${m.dong}동</li>
												<li>${m.ho}호</li>

											</ul>
										</div>
										<div class="inVehicleRight" id="${m.dong}동${m.ho}호">
											<c:forEach var="c" items="${m.memberCarList}">
												<ul>
													<li>${c.mcNo}</li>
													<li>${c.rName}</li>
													<li>${c.cPhone}</li>
													<li> <a href="javascript:showDelete(${c.mcId}, '${m.dong}', '${m.ho}', '${c.mcNo}', '${c.rName}', '${c.cPhone}');" class="memberCarClose"></a></li>
												</ul>
												<!-- <hr class="divLine"> -->
											</c:forEach>
										</div>
									</div>

								</c:forEach>
											<!-- <div class="item">
			                                <ul>
			                                    <li>101</li>
			                                    <li>
			                                        109
			                                    </li>
			                                    <li>(1) 35라 3579</li>
			                                    <li>010-1234-1234</li>
			                                    <li>
			                                        <a href="javascript:;" class="close"></a>
			                                    </li>
			                                </ul>
			                                <ul class="inner">
			                                    <li></li>
			                                    <li></li>
			                                    <li>(2) 35라 3479</li>
			                                    <li>010-1234-1234</li>
			                                    <li> <a href="javascript:;" class="close"></a></li>
			                                </ul>
			                            </div> -->
							</div>
							<c:if test="${fn:length(houseHoldCarList) ne 0}">
							<div class="paging_wrap">
								<ul class="board_paging">
								<%-- 검색 결과 화면인 경우 넘겨줄 searchParam 정의 --%>
								<c:choose>
									<c:when test="${ empty param.dong && empty param.ho && empty param.carNo}">
									</c:when>
									<c:when test="${ !empty param.dong && !empty param.ho && !empty param.carNo}">
										<c:set var="searchParam" value="&dong=${param.dong}&ho=${param.ho }&carNo=${param.carNo}" />
									</c:when>
									<c:otherwise>
										<c:set var="searchParam" value="&dong=${param.dong}&ho=${param.ho }&carNo=all"/>
									</c:otherwise>
								</c:choose>
								
								<!-- 맨 처음으로 (<<) -->
								<li><a
									href="${contextPath }/admin/in/list?page=1${searchParam}">&lt;&lt;</a></li>
								<!-- 이전 페이지로(<) -->
								<li><c:choose>
										<c:when test="${pi.page > 1 }">
											<a
												href="${contextPath }/admin/in/list?page=${pi.page-1}${searchParam}">&lt;</a>
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
													href="${contextPath }/admin/in/list?page=${p}${searchParam}">${p}</a>
											</c:otherwise>
										</c:choose></li>
								</c:forEach>

								<!-- 다음 페이지로 (>) -->
								<li><c:choose>
										<c:when test="${pi.page < pi.maxPage }">
											<a
												href="${contextPath }/admin/in/list?page=${pi.page+1}${searchParam}">&gt;</a>
										</c:when>
										<c:otherwise>
											<a href="#">&gt;</a>
										</c:otherwise>
									</c:choose></li>

								<!-- 맨 끝으로 (>>) -->
								<li><a
									href="${contextPath }/admin/in/list?page=${pi.maxPage}${searchParam}">&gt;&gt;</a></li>
							</ul>
							</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
			</section>
		</div>
	</div>

	<!-- 팝업영역 -->
	<!-- 신규차량 등록 -->
	<div class="popup_wrap" id="addMemberCar">
		<div class="dim"></div>
		<div class="item">
			<div class="new_admin">
				<div class="title">신규차량 등록</div>
				<div class="new_item">
					<label for="">등록세대</label> 
					<select name="dong" id="addInput1"  required>
						<option value="" selected="selected">동</option>
					</select> <label id="deleteDong">동</label> <select name="ho" id="addInput2"
						required>
						<option value="" selected="selected">호</option>
					</select> <label id="deleteHo">호</label>
				</div>

				<div class="new_item">
					<label for="">차량번호</label> <input type="text" name="carNo" id="addCarNo"
						placeholder="ex) 35로 5432 띄어쓰기 없이 적어주세요." maxlength="11" required>
				</div>
				<div class="new_item">
					<label for="">신청인</label> <input type="text" name="uName" id="addRname"
						placeholder="신청인의 이름을 적어주세요." maxlength="3" required>
				</div>
				
				<div class="new_item">
					<label for="">비상연락처</label> <input type="text" name="cPhone" id="addCPhone"
						placeholder="차주의 휴대폰 번호를 입력해주세요." maxlength="15" required>
				</div>
			</div>
			<div class="new_btn_box">
				<button type="button" id="addBtn" class="meberCarBtn" onclick="addMemberCar()">등록하기</button>
				<button class="meberCarBtn" onclick="popHide('addMemberCar')"
					type="button">취소하기</button>
			</div>
		</div>
	</div>

	<!-- 입주차량 신규 등록 확인 -->
	<div class="popup_wrap" id="addMemberCarCheck">
		<div class="dim"></div>
		<div class="item">
			<div class="vehicle_new">
				<p class="vehicle_title">
					<span id="checkDong">201</span>동 <span id="checkHo">103</span>호 차량 등록 현황
				</p>
				<h3 class="vehicle_num">총 <span id="carCount">2</span>대</h3>
				<div class="vehicle_list">
					<ul class="v_head">
						<li></li>
						<li>차량번호</li>
						<li>신청인</li>
						<li>비상연락처</li>
					</ul>
				</div>
				<div class="vehicle_content">
					<ul class="v_body">
						<li>1</li>
						<li>35로 5432</li>
						<li>010-1234-1234</li>
					</ul>
					<ul class="v_body">
						<li>2</li>
						<li>35로 5433</li>
						<li>010-1234-1234</li>
					</ul>
				</div>
			</div>
			<div class="new_btn_box">
				<a href="${contextPath}/admin/in/list">확인</a>
			</div>
		</div>
	</div>

	<!-- 차량정보 삭제 -->
	<div class="popup_wrap" id="deleteMemberCar">
		<div class="dim"></div>
		<div class="item">
			<div class="new_admin">
				<div class="title type02">정말로 아래의 차량 정보를 삭제하시겟습니까?</div>
				<div class="new_item">
					<label for="">등록세대</label> 
					<input type="text" name="" id="deleteInput1" readonly>
					<label id="dong">동</label> 
					<input type="text" name="" id="deleteInput2" readonly> 
					<label id="ho">호</label>
				</div>
				<div class="new_item">
					<label for="">차량번호</label> <input type="text" name="" id="deleteCarNo" readonly>
				</div>
				<div class="new_item">
					<label for="">신청인</label> <input type="text" name="" id="deleteRName" readonly>
				</div>
				<div class="new_item">
					<label for="">비상연락처</label> <input type="text" name="" id="deleteCPhone" readonly>
				</div>
			</div>
			<div class="new_btn_box">
			<form action="${contextPath}/admin/in/delete" type="post">
				<input type="hidden" name="mcId" id="hiddenMcId">
				<button id="deleteMemberCarBtn" class="meberCarBtn">삭제</button>
			</form>
				<button class="meberCarBtn" onclick="popHide('deleteMemberCar')" type="button">취소</button>
			</div>
		</div>
	</div>
	<!-- 팝업영역 -->

	<script>
	function exportData() {
		let fileName = prompt('파일 제목을 입력해주세요.');
		if  (fileName != null ) {
			if (fileName == "") {
				alert("파일명을 올바르게 입력해주시기 바랍니다.")
			} else {
				$.ajax({
					url : "${contextPath}/admin/in/exportData",
					type : "post",
					data : {fileName : fileName},				
					dataType : "json",
					success : function(data){
						if (data != null) {
							if (data == "success") {
								alert("다운로드가 완료되었습니다.")
							} else {
								alert("다운로드를 실패하였습니다.")
							}
						}
					},
					error : function(e){
						console.log(e);
						console.log('실패');
					}
				});
			}
		} else {
			alert("다운로드를 취소하셨습니다.");
		}
		
	}
	/* function searchMemberCar() {
		let dong = $('#searchInput1').val();
		let ho = $('#searchInput2').val();
		let carNo = $('#searchCarNo').val(); 
		
		$.ajax({
			url : "${contextPath}/admin/in/list",
			type : "get",
			data : {dong : $('#searchInput1').val(), ho : $('#searchInput2').val(), carNo : $('#searchCarNo').val()},				
			dataType : "json",
			success : function(data){
				console.log(data);
				
				var html = '';
				 for (var key in data.memberCarList) {
					
				 }
				 갱신 된 댓글 목록을 다시 적용
				$(".reply_list").html(html);
				 댓글 작성 부분 리셋
				$(".reply_content").val(""); 
			},
			error : function(e){
				console.log(e);
				console.log('실패');
			}
		});
	}
	
	 */
	
	function showDelete(mcId, dong, ho, carNo, rName, cPhone) {
		$('#deleteInput1').val(dong);
		$('#deleteInput2').val(ho);
		$('#deleteCarNo').val(carNo);
		$('#deleteRName').val(rName);
		$('#deleteCPhone').val(cPhone);
		$('#hiddenMcId').val(mcId);
		
		popShow('deleteMemberCar');
	}
	
	
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
		
		  var inputDong2 = document.getElementById("searchInput1");
		  var inputHo2 = document.getElementById("searchInput2");
		  for (var a in subjectObject) {
			  inputDong2.options[inputDong2.options.length] = new Option(a, a);
		  }
		  inputDong2.onchange = function() {
		    //empty Chapters- and Topics- dropdowns
		    inputHo2.length = 1;
		    //display correct values
		    var z = subjectObject[this.value];
	  		for (var i = 0; i < z.length; i++) {
	  			inputHo2.options[inputHo2.options.length] = new Option(z[i], z[i]);
	  		}
		  }
		  
		//검색 조건 드롭박스 조건 유지
		  let dropDong = '${ param.dong }';
		  let dropHo = '${ param.ho }';
		  
		  for (var i = 0; i < inputDong2.length; i++) {
			  if (inputDong2.options[i].value == dropDong) {
				  inputDong2.options[i].selected = true;
			  }
		  } 
		  
		  if (dropDong != "" && dropHo != "") {
			  var y = subjectObject[dropDong];
			  
			  for (var i = 0; i < y.length; i++) {
				  inputHo2.options[inputHo2.options.length] = new Option(y[i], y[i]);
	    			if (inputHo2.options[i+1].value == dropHo) {
	    				inputHo2.options[i+1].selected = true;
		  			 }
	    		} 
		  }
		} 
	
	
	function openAddPopUp() { 
		 popShow('addMemberCar');
	}
	
	
	function addMemberCar() {
		if ($('#addInput1').val().length < 1 || $("#addInput2").val().length < 1 || $("#addCarNo").val().length < 1 || $('#addRname').val() < 1 || $('#addCPhone').val().length < 1) {
			alert('필수 정보를 입력해주세요.')
			return;
		}
		
		 $.ajax({
				url : "${contextPath}/admin/in/add",
				type : "post",
				data : {dong : $('#addInput1').val(), ho : $("#addInput2").val(), carNo : $("#addCarNo").val(), rName : $('#addRname').val(), cPhone : $('#addCPhone').val()},				
				dataType : "json",
				success : function(data){
					if(data != null) {
						if ( data != 0 ) {
							$('#addInput1').val("");
							$("#addInput2").val("");
							$("#addCarNo").val("");
							$('#addRname').val("");
							$('#addCPhone').val("");
							document.querySelector('#addMemberCar').classList.remove('pop_on');
							
							 $('#checkDong').html(data.dong);
							 $('#checkHo').html(data.ho);
							 let n = data.memberCarList.length;
							 $('#carCount').html(n);
							 var html = '';
							 var count = 1;
							 for (var key in data.memberCarList) {
								html += '<ul class="v_body">'; 
								html += '<li>' + count++ + '</li>';
								html += '<li>' + data.memberCarList[key].mcNo + '</li>';
								html += '<li>' + data.memberCarList[key].rName + '</li>';
								html += '<li>' + data.memberCarList[key].cPhone + '</li>';
								html += '</ul>';
							 }
							 
							 $(".vehicle_content").html(html);
							
							popShow('addMemberCarCheck');
						} else {
							alert("잘못된 입주민 정보입니다.");
							location.href = "${contextPath}/admin/in/list";
						}
					} else {
						alert("차량 신규등록에 실패하였습니다.");
					}
				},
				error : function(e){
					console.log(e);
				}
			});
	}
	</script>

</body>

</html>