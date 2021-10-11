<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>투표하기 작성페이지</title> 

	<%-- 공통css/js --%>
	<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>
	
	<style>
		.row .col2 {
			display: block;
			width: 94.5%;
			}
		.row .col1 {
			width: 11%;
		}
		.col1 P {
			position: relative;
			top: -80px;
		}
		.ment {
			justify-content: flex-start;
			font-size: 18px;
			margin-left: 20px;
		}
		
		.row .col2 {
		    width: 100%;
		}

	</style>
</head>
<body>
	<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>
	
	<div class="bottom_wrap1">
		<p></p><span>> 아파트소식 > 투표하기</span>
		<h1>투표하기</h1>
		<div class="bottom_wrap2">
		</div>
	</div>
		<div class="wrap">
			<div class="notice_area">
				<form method="POST" name="voteForm" action="${ contextPath }/vote/insert">
				<input type="hidden" name="v_no" value="${ v_no }">
				<div class="notice_content">
					<div class="subject"></div>
					<div id="table">
						<div class="row">
						<span class="cell col1">제목</span>
						<span class="cell col2">
							<input size="60" type="text" name="title" placeholder="제목을 입력해주세요" onfocus="this.placeholder=''" onblur="this.placeholder='제목을 입력해주세요'"required >
						</span>
						</div>
						<div class="row">
						<span class="cell col1" id="col_content">내용</span>
						<span class="cell col2">
							<textarea cols="110" rows="10" name="content" placeholder="내용을 입력해주세요" onfocus="this.placeholder=''" onblur="this.placeholder='내용을 입력해주세요'"
								style="resize: none;" required></textarea>
						</span>
						</div> 
						<div class="row" id="choicebox">
							<span class="cell col1">선택지</span>
							<span class="cell col2">
								<input size="60" type="text" name="choice1" placeholder="선택지 내용을 입력해주세요(최대10자)" onfocus="this.placeholder=''" onblur="this.placeholder='선택지 내용을 입력해주세요(최대10자)'" maxlength="10" required>
							</span>
							<span class="cell col2">
								<input size="60" type="text" name="choice2" placeholder="선택지 내용을 입력해주세요(최대10자)" onfocus="this.placeholder=''" onblur="this.placeholder='선택지 내용을 입력해주세요(최대10자)'" maxlength="10" required>
								<button type="button" id="add_btn" onclick="addCell()"><img src="/oneLife/resources/user/images/addIcon.png"></button>
							</span>
						</div>
						<div class="row">
							<span class="cell col1">선택</span>
							<span class="cell col2">
								<input type="radio" id="single" name="check_choice" value="radiobtn" >
								<label for="single">하나만 선택 가능</label>
								<input type="radio" id="plural" name="check_choice" value="checkbox">
								<label for="plural">복수응답 가능</label>
							</span>
						</div>
						<div class="row">
							<span class="cell col1">마감 기간</span>
							<span class="cell col2">
								<input type="date" id="date" name="dateIn" required>
							</span>
						</div>
					</div>
				</div>
				<p class="ment">※ 투표 게시글은 최종 업로드 이후 수정 불가합니다.</p>
				<div class="btn_area">
					<button type="button" id="btn4" onclick="voteCancel()">취소</button>
					<button type="button" id="btn2" onclick="date1()">글올리기</button>
				</div>
			</form>
			</div>
		</div>
	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>

		<script>
			function voteCancel(){
				if (confirm("작성중인 글쓰기를 종료하시겠습니까?")) {
					javascript:history.back();
				}
			}
			
 			let a = 3;
			const addCell = () => {
				const choicebox = document.getElementById("choicebox");
				const newP = document.createElement('p');
				if (a > 5) {
					alert("최대 다섯개까지 만들수 있습니다.");
					return;
				}
				newP.innerHTML = "<span class='cell col2'> <input size='60' type='text' name='choice" + (a++) +"' placeholder='선택지 내용을 입력해주세요(최대10자)' onfocus='this.placeholder=''' onblur='this.placeholder='선택지 내용을 입력해주세요(최대10자)'' maxlength='10' required> <button type='button' id='remove_btn' onclick='remove(this)'><img src='${contextPath}/resources/user/images/removeIcon.png'></button></span>";
				choicebox.appendChild(newP);
			}

			const remove = (obj) => {
				obj.parentNode.remove();
				a--;
			}
			
		</script>
		
		<script>
		function date1() {
		 
	        let date = document.getElementById("date").value;
		       let today = new Date();
		       today.setHours(0);
		       today.setSeconds(0);
		       today.setMilliseconds(0);
		       let visitDay = new Date(date);
		       
		       if (visitDay < today) {
		          alert("지난 날짜는 등록할 수 없습니다.");
		       } else {
		    	   if (confirm("업로드 이후 수정 불가합니다. 최종 업로드 하시겠습니까?")) {
						document.forms.voteForm.submit();
				}
		    	  
		       }
			
		}
		
		
		</script>
		
	
</body>
</html>