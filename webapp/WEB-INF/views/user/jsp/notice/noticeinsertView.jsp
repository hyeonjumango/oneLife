<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성페이지</title> 

<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>

</head>
<body>
	<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>
	
	<div class="bottom_wrap1">
		<p></p><span>> 아파트소식 > 공지사항</span>
		<h1>공지사항</h1>
		<div class="bottom_wrap2">
		</div>
	</div>
		<div class="wrap">
			<div class="notice_area">
				<form method="POST" action="${ contextPath }/notice/insert">
				<div class="notice_content">
					<div class="subject"></div>
					<div id="table">
						<div class="row">
						<span class="cell col1">제목</span>
						<span class="cell col2">
							<input size="60" type="text" name="title" placeholder="제목을 입력해주세요" onfocus="this.placeholder=''" onblur="this.placeholder='제목을 입력해주세요'" required >
						</span>
						</div>
						<div class="row">
						<span class="cell col1" id="col_content"><p>내용</p></span>
						<span class="cell col2">
							<textarea cols="110" rows="30" name="content" placeholder="내용을 입력해주세요" onfocus="this.placeholder=''" onblur="this.placeholder='내용을 입력해주세요'"
								style="resize: none;" required></textarea>
						</span>
						</div> 
						<div class="row">
						<span class="cell col1">첨부<br>파일</span>
						<span class="cell col2">
							<input type="file" name="uploadfile"><span>*파일 첨부(최대 1개)</span>
						</span>
						</div>
					</div>
				</div>
				<div class="btn_area">
					<button type="button" id="btn4" onclick="noticeCancel()">취소</button>
					<button type="submit" id="btn2">글올리기</button>
				</div>
			</form>
			</div>
		</div>

	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>

		<script>
			function noticeCancel(){
				if (confirm("작성중인 글쓰기를 종료하시겠습니까?")) {
					javascript:history.back();
				}
			}
		</script>

	
</body>
</html>