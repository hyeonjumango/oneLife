<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 	
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>투표하기 상세페이지</title>	
<style>
.modal {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal .bg {
  width: 100%;
  height: 100%;
  background-color: rgba(115, 115, 116, 0.27);
}

.modalBox {
  position: absolute;
  background-color: #fff;
  width: 550px;
  padding: 30px;
  border: 1px solid rgba(63, 63, 68, 0.005);
  box-shadow: 0px 1px 0px rgba(63, 63, 68, 0.05),
    0px 1px 3px rgba(63, 63, 68, 0.15);
  border-radius: 10px;
}

.modalBox h2 {
  font-weight: bold;
  font-size: 28px;
  line-height: 24px;
  justify-content: center;
  text-align: left;
  letter-spacing: 0.15px;
  position: relative;
  top: 8%;
  left: 1%;
}

.modalBox span {
  font-size: 21px;
  line-height: 35px;
  letter-spacing: 0.15px;
  color: #5b5b5b;
  position: relative;
  top: 20%;
  left: 1%;
  right: 100px;
}

.closeBtn {
  background: #797979;
  padding: 15px;
  margin-top: 30px;
  border-radius: 4px;
  cursor: pointer;
  position: relative;
  font-weight: bold;
  font-size: 24px;
  line-height: 24px;
  justify-content: center;
  letter-spacing: 1px;
  color: #fff;
  width: 120px;
  left: 180px;
}

.container .btn2 {
  color: #137af3;
}

.container img {
  height: 50px;
  margin-top: 5%;
}

.hidden {
  display: none;
}

.modalBox h2 {
  border-bottom: 2px solid rgb(218, 218, 218);
  padding-bottom: 30px;
  margin-top: 10px;
}

.bar_area {
  padding-top: 30px;
  padding-bottom: 10px;
  margin: 0;
}
.bar_area .vote_val,
.bar_area .vote_per,
.bar_area .vote_count {
  display: inline-block;
  color: #1d498b;
  font-weight: bold;
  font-size: 22px;
}
.bar_area .vote_val {
  color: #1d498b;
  width: 60%;
  text-align: left;
}
.bar_area .vote_count {
  color: rgb(173, 173, 173);
  font-size: 18px;
  width: 100px;
  text-align: center;
}
.bar_area .vote_per {
  color: #ff726d;
  font-size: 25px;
  text-align: right;
  width: 80px;
}

.zt-skill-bar {
  color: #fff;
  font-size: 11px;
  line-height: 25px;
  background-color: rgba(0, 0, 0, 0.1);
  border-radius: 40px;
  height: 20px;
}

.zt-skill-bar * {
  -webkit-transition: all 1s ease;
  -moz-transition: all 1s ease;
  -ms-transition: all 1s ease;
  -o-transition: all 1s ease;
  transition: all 1s ease;
}

.zt-skill-bar div {
  background-color: #00a8ff;
  position: relative;
  padding-left: 25px;
  width: 0;
  border-radius: 40px;
  height: 20px;
}

.radio_area,
.checkbox_area {
  /* display: inline-block; */
  margin-left: 80px;
}

.radio_area > div > ul,
.checkbox_area > div > ul {
  height: 60px;
  line-height: 40px;
  display: flex;
  margin: 0;
  margin-left: 10px;
}

.radio_area label,
.checkbox_area label {
  font-size: 18px;
  margin-left: 30px;
  cursor: pointer;
}

</style>
     <%-- 공통css/js --%>
	<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>
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
	<form method="post" name="voteForm">
		<div class="wrap">
			<div class="vote_area">
				<div class="vote_title">
					<h1>비대면으로 안전하게, <b>언제 어디서든<br>투표</b>를 진행해보세요!</h1>
				</div>
		
				<input type="hidden" name="v_no" value="${ vote.v_no }">
				<input type="hidden" name="ve_no" value="${ vote.ve_no }">
				<input type="hidden" name="v_choice" value="${ vote.v_choice }">
					<div class="notice_content">
						<div class="subject"></div>
						<div id="table">
							<div class="row">
							<span class="cell col1">제목</span>
							<span class="cell col2">${ vote.v_title }</span>
							</div>
							<div class="row">
							<span class="cell col1">글쓴이</span>
							<span class="cell col2">${ vote.m_nick }</span>
							</div>
							<div class="row">
							<span class="cell col1">기간</span>
							<span class="cell col2">
							<fmt:parseDate value='${vote.v_enroll_date}' var='enroll_day' pattern='yyyy-MM-dd' scope="page"/>
							<fmt:formatDate value="${enroll_day}" pattern="yyyy-MM-dd"/> ~
							<fmt:parseDate value='${vote.v_modify_date}' var='modify_day' pattern='yyyy-MM-dd' scope="page"/>
							<fmt:formatDate value="${modify_day}" pattern="yyyy-MM-dd" var="deadline"/> ${ deadline }
							<fmt:formatDate value="<%= new java.util.Date() %>" pattern="yyyy-MM-dd" var="today"/>
							</span>
							</div>
				 		</div>
						 <div class="detail_content">
						<pre class="col3">${ vote.v_content }</pre>
						</div>
						<span class="ment">※ 투표완료 후 선택 수정 불가함으로 신중한 투표 부탁드립니다.</span>
						<span class="ment">※ 투표는 세대주만 1투표권 행사 가능합니다.</span>
					</div>
					<c:if test="${  today <= deadline  }"> 
						<c:choose>
						<c:when test="${ vote.v_choice == 'radiobtn' }">
						<div class="radio_area">
							<c:if test="${ empty vote.ve_choice1 }">
							<c:set value="style='display : none'" var="nonesty1"/></c:if>
							<div ${ nonesty1 }>
							<ul><li>
							<input type="radio" id="val1" name="radio_val" value="val1" required>
							<label for="val1">${ vote.ve_choice1 }</label>
							</li></ul></div>
							<c:if test="${ empty vote.ve_choice2 }">
							<c:set value="style='display : none'" var="nonesty2"/></c:if>
							<div ${ nonesty2 }>
							<ul><li>
							<input type="radio" id="val2" name="radio_val" value="val2">
							<label for="val2">${ vote.ve_choice2 }</label>
							</li></ul></div>
							<c:if test="${ empty vote.ve_choice3 }">
							<c:set value="style='display : none'" var="nonesty3"/></c:if>
							<div ${ nonesty3 }>
							<ul><li>
							<input type="radio" id="val3" name="radio_val" value="val3">
							<label for="val3">${ vote.ve_choice3 }</label>
							</li></ul></div>
							<c:if test="${ empty vote.ve_choice4 }">
							<c:set value="style='display : none'" var="nonesty4"/></c:if>
							<div ${ nonesty4 }>
							<ul><li>
							<input type="radio" id="val4" name="radio_val" value="val4" >
							<label for="val4">${ vote.ve_choice4 }</label>
							</li></ul></div>
							<c:if test="${ empty vote.ve_choice5 }">
							<c:set value="style='display : none'" var="nonesty5"/></c:if>
							<div ${ nonesty5 }>
							<ul><li>
							<input type="radio" id="val5" name="radio_val" value="val5">
							<label for="val5">${ vote.ve_choice5 }</label>
							</li></ul></div>
						</div>
						</c:when>
						<c:otherwise>
						<div class="checkbox_area">
							<c:if test="${ empty vote.ve_choice1 }">
							<c:set value="style='display : none'" var="nonesty1"/></c:if>
							<div ${ nonesty1 }>
							<ul><li>
							<input type="checkbox" id="val6" name="checkbox_val1" value="val1" >
							<label for="val6">${ vote.ve_choice1 }</label>
							</li></ul>
							</div>
							<c:if test="${ empty vote.ve_choice2 }">
							<c:set value="style='display : none'" var="nonesty2"/></c:if>
							<div ${ nonesty2 }>
							<ul><li>
							<input type="checkbox" id="val7" name="checkbox_val2" value="val2">
							<label for="val7">${ vote.ve_choice2 }</label>
							</li></ul></div>
							<c:if test="${ empty vote.ve_choice3 }">
							<c:set value="style='display : none'" var="nonesty3"/></c:if>
							<div ${ nonesty3 }>
							<ul><li>
							<input type="checkbox" id="val8" name="checkbox_val3" value="val3">
							<label for="val8">${ vote.ve_choice3 }</label>
							</li></ul></div>
							<c:if test="${ empty vote.ve_choice4 }">
							<c:set value="style='display : none'" var="nonesty4"/></c:if>
							<div ${ nonesty4 }>
							<ul><li>
							<input type="checkbox" id="val9" name="checkbox_val4" value="val4">
							<label for="val9">${ vote.ve_choice4 }</label>
							</li></ul></div>
							<c:if test="${ empty vote.ve_choice5 }">
							<c:set value="style='display : none'" var="nonesty5"/></c:if>
							<div ${ nonesty5 }>
							<ul><li>
							<input type="checkbox" id="val10" name="checkbox_val5" value="val5">
							<label for="val10">${ vote.ve_choice5 }</label>
							</li></ul></div>
						</div>
						</c:otherwise>
						</c:choose>
					</c:if> 
						
					<!-- 기간 동안 보일 버튼 투표하기 / 결과보기 -->
					<c:choose>
						<c:when test="${  today <= deadline  }">
						<c:if test="${ !empty loginUser }">
						<div class="btn_area_vote"> 
							<input type="button" value="투표하기" id="btn2" onclick="votesub()">
						</div>
						</c:if>
						</c:when>
						<c:otherwise> 
						<div class="btn_area_vote">  
							<button type="button" id="btn3" class="openBtn">결과보기</button>
							<div class="modal hidden">
								<div class="bg"></div>
								<div class="modalBox">
									<h2>투표결과</h2>
									<c:set value="${ 100 / (voteval.val1+voteval.val2+voteval.val3+voteval.val4+voteval.val5) }" var="valSum" />
									<c:if test="${ voteval.val1 == 0}">
									<c:set value="style='display : none'" var="val1"/></c:if>
									<div class="bar_area" ${ val1 }>
										<span class="vote_val">${ vote.ve_choice1 }</span>
										<span class="vote_count">${ voteval.val1 }명</span>
										<span class="vote_per"><fmt:formatNumber type="number" var="value1" maxFractionDigits="0"  value="${ valSum * voteval.val1 }" />
										${ value1 }%</span>
										<div class="zt-skill-bar"><div data-width="${ value1 }"></div></div>
									</div>
									<c:if test="${ voteval.val2 == 0}">
									<c:set value="style='display : none'" var="val2"/></c:if>
									<div class="bar_area" ${ val2 }>
										<span class="vote_val">${ vote.ve_choice2 }</span>
										<span class="vote_count">${ voteval.val2 }명</span>
										<span class="vote_per"><fmt:formatNumber type="number" var="value2" maxFractionDigits="0"  value="${ valSum * voteval.val2 }" />
										${ value2}%</span>
										<div class="zt-skill-bar"><div data-width="${ value2}"></div></div>
									</div>
									<c:if test="${ voteval.val3 == 0}">
									<c:set value="style='display : none'" var="val3"/></c:if>
									<div class="bar_area" ${ val3 }>
										<span class="vote_val">${ vote.ve_choice3 }</span>
										<span class="vote_count">${ voteval.val3 }명</span>
										<span class="vote_per"><fmt:formatNumber type="number" var="value3" maxFractionDigits="0"  value="${ valSum * voteval.val3 }" />
										${ value3 }%</span>
										<div class="zt-skill-bar"><div data-width="${ value3 }"></div></div>
									</div>
									<c:if test="${ voteval.val4 == 0}">
									<c:set value="style='display : none'" var="val4"/></c:if>
									<div class="bar_area" ${ val4 }>
										<span class="vote_val">${ vote.ve_choice4 }</span>
										<span class="vote_count">${ voteval.val4 }명</span>
										<span class="vote_per"><fmt:formatNumber type="number" var="value4" maxFractionDigits="0"  value="${ valSum * voteval.val4 }" />
										${ value4 }%</span>
										<div class="zt-skill-bar"><div data-width="${ value4 }"></div></div>
									</div>
									<c:if test="${ voteval.val5 == 0}">
									<c:set value="style='display : none'" var="val5"/></c:if>
									<div class="bar_area" ${ val5 }>
										<span class="vote_val">${ vote.ve_choice5 }</span>
										<span class="vote_count">${ voteval.val5 }명</span>
										<span class="vote_per"><fmt:formatNumber type="number" var="value5" maxFractionDigits="0"  value="${ valSum * voteval.val5 }" />
										${ value5 }%</span>
										<div class="zt-skill-bar"><div data-width="${ value5 }"></div></div>
									</div>
									<button type="button" class="closeBtn">확인</button>
									</div>
								</div>
							</div>
						</c:otherwise>
						</c:choose> 
					</div>
						
					<!-- 관리자한테만 보임 -->
					<c:if test="${ !empty loginManager }">
					<div class="btn_area">
						<button type="button" id="btn3" onclick="voteDelete()">삭제하기</button>
					</div>
					</c:if>
				
			  </div>
			</form>
	
	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>
	
		<script>
			function voteDelete(){
				if (confirm("게시글을 삭제 하시겠습니까?")) {
	                document.forms.voteForm.action = "${contextPath}/vote/delete";
			 		document.forms.voteForm.submit();
				}
			}
			
			var u_nocount = "${ vote.u_nocount}";
			var rtype = "${ vote.r_type}";
			var rty = "세대주";
			function votesub(){
				
				let len = $('.checkbox_area div ul li input:checked').length;
	            let len2 = $('.radio_area div ul li input:checked').length;
			
	            if (len == 0 && len2 ==0) {
	               alert('보기를 선택해주세요!')
	               return;
	            }
				
				if (u_nocount > 0 ) {
					alert('이미 투표 했습니다. \n※ 1인 1투표권 행사 가능합니다.');
					return;

				}
				
				if (rtype == rty) {
		               document.forms.voteForm.action = "${contextPath}/vote/votefinish";
		               document.forms.voteForm.submit();
		            } else {
		               alert('세대주가 아니므로 투표 불가합니다.\n※ 세대주만 투표 가능합니다.');
		            } 
				
			}	

			
				
				
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
		<!-- 막대 그래프 -->
		 <script>
			 document.querySelector(".openBtn").addEventListener("click", bar);
			 function bar() {
						$(".zt-skill-bar > div ").each(function (i) {
							var $this  = $(this);
							var	skills = $this.data('width');

							$this.css({'width' : skills + '%'});

						});
					}
        </script>
	
	

</body>
</html>