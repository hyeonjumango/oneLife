<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아파트 민원</title>

<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>

</head>
<body>
	<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>
	
	<div class="bottom_wrap1">
		<p></p><span>> 아파트민원</span>
		<h1>아파트 민원</h1>
		<div class="bottom_wrap2">
		</div>
	</div>
	<div class="wrap">
		<div class="complaint_area">
			<div class="complaint_title">
				<h1>간편하게 <b>민원 접수하고<br>처리 결과</b>를 전해 드립니다.</h1>
			</div>
			<div class="search_area">
				<form method="get">
					<span class="input_area"> 
						<input type="checkbox" name="mylist" value="mybbs" id="mylist" checked>
						<label for="mylist">내 글만 보기</label>
					</span>
					<span class="input_area2"> 
						<input type="search" name="searchValue" placeholder="검색">
						<button type="submit" id="btn1"><img src="/oneLife/resources/user/images/Search.png"></button>
					</span>
				</form>
			</div>
			<div class="complaint_list">
				<ul class="complaint_header">
					<li class="no">번호</li>
					<li class="title">문의/답변</li>
					<li class="nick">작성자</li>
					<li class="date">작성일</li>
				</ul>
				<li class="nonelist">검색된 결과가 없습니다.</li>
					
				
			</div>
			
		</div>
		<div class="search_area">
			<form method="get">
				<button type="button" id="btn2" class="openBtn">문의하기</button>
				<div class="modal hidden">
					<div class="bg"></div>
					<div class="modalBox">
						<h2>확인해주세요</h2>
						<span>
							산업안전보건법 제41조에 의하여 비속어 
							및 폭언이 포함된 글은 사전 동의 없이
							삭제될 수 있습니다
						</span>
						<div class="container">
						<button class="closeBtn btn1">미동의</button>
						<img src="/oneLife/resources/user/images/Line 19.png">
						<button class="closeBtn btn2">동의</button>
						</div>
					</div>
				</div>
			</form>
		</div>

		<ul class="board_paging">
			<li>
				<a href="javascript:;" class="btn_prev"></a>
			</li>
			<li>
				<a href="javascript:;" class="current_page">1</a>
			</li>
			<li>
				<a href="javascript:;" class="btn_next"></a>
			</li>
		</ul>
	</div>
	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>

	<script>
		// 게시글 목록에 mouseover/mouseout 시 onmouseover 클래스 추가/제거 처리
		const complaintList = document.querySelector('.complaint_list');
		
		complaintList.addEventListener('mouseover', function(){
			
			if(event.target.classList.contains('complaint_ul'))
				event.target.classList.add('onmouseover');
			else if(event.target.parentNode.classList.contains('complaint_ul'))
				event.target.parentNode.classList.add('onmouseover');
		});
		
		complaintList.addEventListener('mouseout', function(){
			
			if(event.target.classList.contains('complaint_ul'))
				event.target.classList.remove('onmouseover');
			else if(event.target.parentNode.classList.contains('complaint_ul'))
				event.target.parentNode.classList.remove('onmouseover');
			
		});
		
   </script> 
   
   <!-- 모달 창 부분 -->
   <script>
    const open = () => {
        document.querySelector(".modal").classList.remove("hidden");
    }

    const close = () => {
        document.querySelector(".modal").classList.add("hidden");
    }

    document.querySelector(".openBtn").addEventListener("click", open);
    document.querySelector(".closeBtn").addEventListener("click", close);
    document.querySelector(".bg").addEventListener("click", close);

    </script>
	
</body>
</html>