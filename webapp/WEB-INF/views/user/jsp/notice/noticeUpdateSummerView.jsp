<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성페이지</title> 
	<%-- 공통css/js --%>
	<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>

	<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
	<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>
	<script src="/oneLife/resources/admin/js/summer.js"></script>

	<style>
		.bottom_wrap1 > h1 {
			top: 10px;
		}
		.row:after {
			content: none;
		}
		.row:before {
			content: none;
		} 
		.col1 {
		  vertical-align: middle;
		}
	</style>
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
				<form method="post" action="${ contextPath }/notice/update">
				<input type="hidden" name="nno" value="${ notice.n_no }">
				<div class="notice_content">
					<div class="subject"></div>
					<div id="table">
						<div class="row">
						<span class="cell col1">제목</span>
						<span class="cell col2">
							<input size="60" type="text" name="title" placeholder="제목을 입력해주세요" onfocus="this.placeholder=''" onblur="this.placeholder='제목을 입력해주세요'" value="${ notice.n_title }" required >
						</span>
						</div>
						<div class="row">
						<span class="cell col1" id="col_content">내용</span>
						<span class="cell col2">
							<textarea id="summernote" name="content" required>${ notice.n_content }</textarea>
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