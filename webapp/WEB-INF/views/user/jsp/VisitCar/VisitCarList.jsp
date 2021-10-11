<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>방문차량 예약목록</title>
<script src="https://kit.fontawesome.com/1be9c91961.js"
	crossorigin="anonymous"></script>
<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>
<!-- <script src="/oneLife/resources/user/js/visitCar/visit_car.js"></script> -->
<%if (session.getAttribute("msg") != null) { %>
<script>
	alert('<%= session.getAttribute("msg")%>');
</script>
<%
	session.removeAttribute("msg");

} %>
</head>
<style>
.fa-edit {
	font-size: 20px;
	margin: 0;
}

.visitCarDetail > li.VisitCheck {
color : #72c2e7;
}

.visitCarDetail li{
	overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.list_nodate {
display : flex;
flex-direction : column;
margin-top : 20px;
}

.list_nodate > img {
width : 350px;
margin : 0 auto;
}

#nodataP {
padding-top : 10px;
font-size : 30px;
margin : 0 auto;
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
  /* background-color: rgb(0, 0, 0); Fallback color */
  background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 15% auto; /* 15% from the top and centered */
  padding: 15px;
  border: 1px solid #888;
  width: 400px; /* Could be more or less, depending on screen size */
  border-radius: 10px;
  display: flex;
  flex-direction : column;
}

.modal-content > div > p, .modal-content > div > span{
display : inline-block;
font-size : 20px;
}

.modal-content > div > span {
margin-left : 130px;
cursor 
}


.modal-content > div > span:hover {
cursor : pointer;
}

.description {
margin-top : 20px;
}

.description > ul > li {
text-align : left;
font-size : 15px;
}
</style>
<body>
	<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>

	<div class="bottom_wrap1">
		<p></p>
		<span>> 방문차량 > 방문예약 목록</span>
		<h1>방문차량 목록</h1>
		<div class="bottom_wrap2"></div>
	</div>

	<div class="wrap">
		<div>
			<div class="visitCar_title">
				<h1>방문차량 예약내역을 확인하실 수 있습니다!</h1>
			</div>
			<div class="search_area">
				<form method="get" action="${contextPath}/visitCarList" >
					<label>방문일</label>
					<span class="input_area">
					<input type="date" name="date"
						<c:if test="${ !empty param.date }">
						value="${param.date}"
						</c:if>>
					</span>
					<label>차량번호</label>
					<span class="input_area">
					<input type="search" name="carNo" placeholder="띄어쓰기 없이 적어주세요"
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
					</span>
					<label>신청인</label>
					<span class="input_area">
					<input type="search" name="applicant" placeholder="신청인" 
					<c:choose>
						<c:when test="${ param.applicant eq 'all' }">
						value="전체"
						</c:when>
						<c:when test="${ !empty param.applicant }">
						value="${param.applicant}"
						</c:when>
						<c:otherwise>
						value="전체"
						</c:otherwise>
						</c:choose>/>
					</span>
					<button id="sear_btn">
							<img src="/oneLife/resources/user/images/Search.png" />
					</button>
				</form>
			</div>
			<div class="vistCarList">
				<ul class="vistCarList_header">
					<li class="no">등록번호</li>
					<li class="date">방문일</li>
					<li class="carNo">차량번호</li>
					<li class="visitPurpose">방문목적</li>
					<li class="phone">비상연락처</li>
					<li class="applicant">신청인</li>
					<li class="date">수정일</li>
					<li class="date">방문현황</li>
					<li class="edit">수정</li>
				</ul>
				<c:choose>
					<c:when test="${fn:length(visitCarList) ne 0}">
						<c:forEach var="v" items="${visitCarList}">
							<ul class="visitCarDetail">
								<li class="no">${v.VC_ID}</li>
								<li class="date">${v.VC_DATE}</li>
								<li class="carNo">${v.VC_NO}</li>
								<li class="visitPurpose">${v.VC_PURPOSE}</li>
								<li class="phone">${v.VC_PHONE}</li>
								<li class="applicant">
								<c:choose>
									<c:when test="${v.mName eq null}">
										${v.r_NAME}
									</c:when>
									<c:otherwise>
										관리자
									</c:otherwise>
								</c:choose>
								</li>
								<li class="date">${v.VC_MODIFYDATE}</li>
								<c:set var="status" value="N" />
								<c:choose>
									<c:when test="${v.VC_STATUS eq status }">
									<li class="date">접수</li>
									</c:when>
									<c:otherwise>
								<li class="date VisitCheck">완료</li>
									</c:otherwise>
								</c:choose>
								<c:set var="current" value="<%=new java.util.Date()%>" />
								<fmt:formatDate value="${current }" type="both"
									pattern="yyyy-MM-dd" var="today" />
								<c:choose>
									<c:when
										test="${v.u_NO == loginUser.u_NO && v.VC_STATUS eq status && v.VC_DATE >= today}">
										<li class="edit" onclick="fix(${v.VC_ID})"><i class="far fa-edit"></i></li>
									</c:when>
									<c:otherwise>
										<li class="edit" id="editBtn" onclick="fixWarning()"><i class="far fa-edit"></i></li>
									</c:otherwise>
								</c:choose>
							</ul>
						</c:forEach>
					</c:when>
					<c:otherwise>
					<div class="list_nodate">
						<img src="/oneLife/resources/admin/images/list_nodate.png" alt="NODATE">
						<p id="nodataP">검색 결과가 존재하지 않습니다.</p>
					</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<div class="search_area">
			<form method="get">
				<button type="button" id="add" onclick="goRegister()">추가하기</button>
			</form>
		</div>
		<c:if test="${fn:length(visitCarList) ne 0}">
							
		<ul class="board_paging">
		<c:choose>
			<c:when test="${ empty param.date }">
				<c:set var="paramDate" value="all" />
			</c:when>
			<c:otherwise>
				<c:set var="paramDate" value="${ param.date }" />
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${ empty param.carNo }">
				<c:set var="paramCarNo" value="all" />
			</c:when>
			<c:otherwise>
				<c:set var="paramCarNo" value="${ param.carNo }" />
			</c:otherwise>
		</c:choose>
		<c:choose>
			<c:when test="${ empty param.applicant }">
				<c:set var="paramApplicant" value="all" />
			</c:when>
			<c:when test="${ param.applicant eq '관리자'}">
				<c:set var="paramApplicant" value="관리자" />
			</c:when>
			<c:otherwise>
				<c:set var="paramApplicant" value="${ param.applicant }" />
			</c:otherwise>
		</c:choose>
		
			<c:set var="searchParam" value="&date=${ paramDate }&carNo=${paramCarNo }&applicant=${paramApplicant}" />
			<!-- 맨 처음으로 (<<) -->
			<li><a href="${contextPath }/visitCarList?page=1${searchParam}">&lt;&lt;</a></li>
			<!-- 이전 페이지로(<) -->
			<li><c:choose>
					<c:when test="${pi.page > 1 }">
						<a
							href="${contextPath }/visitCarList?page=${pi.page-1}${searchParam}">&lt;</a>
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
							<a href="${contextPath }/visitCarList?page=${p}${searchParam}">${p}</a>
						</c:otherwise>
					</c:choose></li>
			</c:forEach>

			<!-- 다음 페이지로 (>) -->
			<li><c:choose>
					<c:when test="${pi.page < pi.maxPage }">
						<a
							href="${contextPath }/visitCarList?page=${pi.page+1}${searchParam}">&gt;</a>
					</c:when>
					<c:otherwise>
						<a href="#">&gt;</a>
					</c:otherwise>
				</c:choose></li>

			<!-- 맨 끝으로 (>>) -->
			<li><a
				href="${contextPath }/visitCarList?page=${pi.maxPage}${searchParam}">&gt;&gt;</a></li>
		</ul>
		</c:if>
		<!-- <ul class="board_paging">
			<li><a href="javascript:;">&lt;</a></li>
			<li><a href="javascript:;" class="current_page">1</a></li>
			<li><a href="javascript:;">&gt;</a></li>
		</ul> -->
	</div>
	
	<div id="cantFixModal" class="modal">
		<div class="modal-content">
			<div>
				<p>수정할 수 없는 내역입니다.</p>
			<span id="warningClose" class="close">&times;</span>
			</div>
			<div class="description">
			<ul>
				<li>1. 신청인이 다를 경우</li>
				<li>2. 방문일이 지났을 경우</li>
				<li>3. 방문을 완료했을 경우</li>
			</ul>
			</div>
		</div>
	</div>
	


	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>


	

	<script>
     	 // 게시글 목록에 mouseover/mouseout 시 onmouseover 클래스 추가/제서 처리
     	 const noticeList = document.querySelector(".vistCarList");

     	 noticeList.addEventListener("mouseover", function () {
     	  if (event.target.classList.contains("visitCarDetail"))
      	    event.target.classList.add("onmouseover");
      	  else if (event.target.parentNode.classList.contains("visitCarDetail"))
      	    event.target.parentNode.classList.add("onmouseover");
     	 });

     	 noticeList.addEventListener("mouseout", function () {
     	   if (event.target.classList.contains("visitCarDetail"))
     	     event.target.classList.remove("onmouseover");
    	    else if (event.target.parentNode.classList.contains("visitCarDetail"))
    	      event.target.parentNode.classList.remove("onmouseover");
    	  });
	
     	function fixWarning() {
     		 //모달
        	var cantFixModal = document.getElementById("cantFixModal");
        	var warningClose = document.getElementById("warningClose");

            	cantFixModal.style.display = "block";

          	warningClose.onclick = function () {
          		cantFixModal.style.display = "none";
          	};

          	window.onclick = function (event) {
            	if (event.target == cantFixModal) {
            		cantFixModal.style.display = "none";
            	}
         	};
     	}
       
     	 function goRegister() {
			location.href = "${contextPath}/visitCarRegister";
		};
		
		function fix(vid){
			location.href = '${contextPath}/visitCarFix?vid=' + vid;
		}
    </script>
</body>
</html>
