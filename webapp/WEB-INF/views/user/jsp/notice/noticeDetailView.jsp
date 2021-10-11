<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 상세페이지</title>

	<%-- 공통css/js --%>
	<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>
		<style>
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
	  padding : 10px;
	  margin: 15% auto; /* 15% from the top and centered */
	  border: 1px solid #888;
	  width: 700px; /* Could be more or less, depending on screen size */
	  border-radius : 10px;
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
	
	#weekInsertBtn {
    cursor: pointer;
    width: 100px;
    height: 35px;
    border: 0px;
    color: white;
    background: #ff7272;
    margin: 10px;
    border-radius: 6px;
    box-shadow: 0px 1px 1px rgb(0 0 0 / 14%), 0px 2px 1px rgb(0 0 0 / 12%), 0px 1px 3px rgb(0 0 0 / 20%);
	}
	
	#weekUpdateBtn {
	   cursor: pointer;
	    width: 100px;
	    height: 35px;
	    border: 0px;
	    color: white;
	    background: #ff7272;
	    margin: 10px;
	    border-radius: 6px;
	    box-shadow: 0px 1px 1px rgb(0 0 0 / 14%), 0px 2px 1px rgb(0 0 0 / 12%), 0px 1px 3px rgb(0 0 0 / 20%);
	}
	
	.modalBtn {
	   cursor: pointer;
	    width: 100px;
	    height: 35px;
	    border: 0px;
	    color: white;
	    background: #00a3fe;
	    margin: 10px;
	    border-radius: 6px;
	    box-shadow: 0px 1px 1px rgb(0 0 0 / 14%), 0px 2px 1px rgb(0 0 0 / 12%), 0px 1px 3px rgb(0 0 0 / 20%);
	}
	
	.modalBtnDiv {
	display : flex;
	justify-content : center;
	}
	
	.view_items > h1 {
	text-align : center;
	font-weight : bold;
	font-size : 30px;
	margin-top : 20px;
	margin-bottom : 20px;
	}
 /* 상세페이지 */
 
 .view_items .items{display: flex; justify-content: center; align-items: center;  background:#F6F6F6;border-bottom:1px solid #e6e6e6; }
 .view_items .items dt{width: 270px; font-size: 16px;text-align: center; font-weight: 600; }
 .view_items .items dd{width:calc(100% - 270px); box-sizing: border-box; padding: 10px; background:#fff; min-height:40px;}
 .view_items .items dd .radio{display: inline-block; margin: 7px 20px 7px 0; margin-right: 20px;}
 .view_items .items dd .radio input[type="radio"]{position: absolute; left:-9999px;}
 .view_items .items dd .radio input[type="radio"] + label{display: block; min-width:16px; height: 16px; background: url(../images/ico_radio.png) no-repeat 0  0;margin: 0 auto; padding-left: 20px; box-sizing: border-box; line-height: 16px; cursor: pointer;}
 .view_items .items dd .radio input[type="radio"]:checked + label{background-image:url(../images/ico_radio_on.png);}
 .view_items .items dd .input{}
 .view_items .items dd .input input{display: block; width: 100%; height: 29px; line-height: 29px; box-sizing: border-box; border:1px solid #000; padding: 0 10px; font-size: 12px;}
 .view_items .items dd .select{}
 .view_items .items dd .select select{display: block; height: 29px; line-height: 29px; box-sizing: border-box; font-size: 12px; padding: 0 25px 0 8px;
    border: 1px solid #000; min-width: 100px; background: #fff url(../resources/admin/images/ico_select.png) no-repeat right 8px center;}
 .view_items .items dd .textarea{}
 .view_items .items dd .textarea textarea{display: block; width: 100%; height: 285px; border:1px solid #000; resize: none; font-size: 12px; line-height: 18px; padding: 10px; box-sizing: border-box;}
 .view_btn{text-align: center; padding-top: 30px;}
 .view_btn a{display: inline-block;width: 70px; height: 28px; line-height: 28px;border-radius: 3px; text-align: center; font-size: 14px; color:#fff; font-weight: bold; background: #666; margin: 0 3px;}
 .view_btn a.ok{background: #4094F7;}
 .view_items .items dd .calendar{}
 .view_items .items dd .calendar .cal{display: block; float: left; width: 100%; max-width: 110px; height: 29px; line-height: 29px; 
    padding: 0 8px 0 25px; background: #fff url(../images/ico_cal.png) no-repeat left 8px center; box-sizing: border-box; border: 1px solid #000;
    cursor: pointer;}
    .view_items .items dd .calendar .cal_today{display: block; float: left; width: 100%; max-width: 110px; height: 29px; line-height: 29px; 
    padding: 0 8px 0 25px; background: #fff url(../resources/admin/images/ico_cal.png) no-repeat left 8px center; box-sizing: border-box; border: 1px solid #000;
    cursor: pointer;}
    
    .periodLabel {
    padding-left : 10px;
    text-align:center;
	vertical-align:middle; 
	display:table-cell;
    }
    
    #modalP, #modalDate2 {
    display : none;
    }
    
    #modalP2, #modalChangeDate2 {
    display : none;
    }
    
    #modalP2 {
    float : left;
    margin : 0 5px; 
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
				<div class="notice_title">
					<h1>KH PARK의 <b>공지 및<br>새로운 소식</b>을 전해 드립니다.</h1>
				</div>
					<div class="notice_content">
						<div class="subject"></div>
						<div id="table">
							<div class="row">
							<span class="cell col1">제목</span>
							<span class="cell col2">${ notice.n_title }</span>
							</div>
							<div class="row">
							<span class="cell col1">글쓴이</span>
							<span class="cell col2">${ notice.m_nick }</span>
							</div>
							<div class="row">
							<span class="cell col1">날짜</span>
							<span class="cell col2">${ notice.enroll_date }</span>
							</div>
				 		</div>
						 <div class="detail_content">
						<pre class="col3">${ notice.n_content }</pre>	
						</div>
					</div>
					<div class="btn_area">
						<button type="button" id="btn2" onclick="location.href='${ contextPath }/notice/list'">목록</button>
						<!-- 관리자한테만 보임 -->
						<c:if test="${ !empty loginManager }">
						<button type="button" id="btn3" onclick="updateNoticeView()">수정하기</button>
						<button type="button" id="btn3" onclick="noticeDelete()">삭제하기</button>
						<c:choose>
						 <c:when test="${check.scNo eq 0}">
						 	<button type="button" id="weekInsertBtn" onclick="openModal1()">주요일정 등록</button>
						 </c:when>
						 <c:otherwise>
						 	<button type="button" id="weekUpdateBtn">주요일정 변경</button>
						 </c:otherwise>
						</c:choose>
						<form name="noticeForm" method="post">
							<input type="hidden" name="n_no" value="${ notice.n_no }">
						</form>
						
						
						</c:if> 
					</div>
			</div>
		</div>
			
			<!-- The Modal -->
			<div id="myModal" class="modal">
			  <!-- Modal content -->
			  <div class="modal-content">
			     <form name="weekFrm">
	                        <div class="view_items">
	                        	<h1>주요일정에 등록하시겠습니까?</h1>
	                            <dl class="items">
	                                <dt>일정 예정일</dt>
	                                <dd>    
	                                    <div class="calendar">
	                                    	<c:set var="now" value="<%=new java.util.Date()%>" />
											<c:set var="sysDate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/></c:set>
					                    	<input id="modalDate" type="text" name="searchDay" class="cal_today" value="${sysDate}">
					                    	<p id="modalP"> ~ </p>  
			                    			<input id="modalDate2" type="text" name="searchDay" class="cal_today">
			                    			<input type="checkbox" id="modalCheck" ><label for="modalCheck" class="periodLabel">기간 설정</label>
	                                    
	                                    </div>
	                                </dd>
	                            </dl>
	
	                            <dl class="items">
	                                <dt>일정 타입</dt>
	                                <dd>
	                                   <div class="select">
	                                   <select name="scType" id="modalType">
				                    		   <option value="SC_CODE1">공동생활</option>
	                                           <option value="SC_CODE2">주민투표</option>
				                    	</select>
	                                   </div>
	                                </dd>
	                            </dl>
	
	                            <dl class="items">
	                                <dt>일정 제목</dt>
	                                <dd>
	                                   <div class="input">
				                    			<input id="modalTitle" type="text" name="scTitle" value="${ notice.n_title }">
	                                   </div>
	                                </dd>
	                            </dl>
	
	                            <%-- <dl class="items">
	                                <dt>일정 내용</dt>
	                                <dd>
	                                   <div class="textarea">
					                    	<textarea name="scContent" id="modalTextarea" cols="30" rows="10">${fn:substring(notice.n_content, 3, fn:length(notice.n_content)-4)}</textarea>
	                                   </div>
	                                </dd>
	                            </dl> --%>
	                        </div>
	                        <div class="modalBtnDiv">
	                        <button class="modalBtn" type="button" onclick="weekUPInsert()">등록하기</button>
	                        <button class="modalBtn" id="cancelWeekInsert" type="button">취소하기</button>
	                        </div>
                        </form>
			  </div>
			</div>
			
			<!-- 변경 및 삭제 -->
			<div id="myModal2" class="modal">
			  <div class="modal-content">
			     <form name="weekFrm">
	                        <div class="view_items">
	                        	<h1>주요일정을 변경하시겠습니까?</h1>
	                            <dl class="items">
	                                <dt>일정 예정일</dt>
	                                <dd>    
	                                    <div class="calendar">
	                                    	<c:set var="now" value="<%=new java.util.Date()%>" />
											<c:set var="sysDate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/></c:set>
					                    	<input id="modalChangeDate" type="text" name="searchDay" class="cal_today" value="${check.scOpenDate}">
					                    	<p id="modalP2"> ~ </p>  
			                    			<input id="modalChangeDate2" type="text" name="searchDay" class="cal_today" value="${check.scEndDate}">
			                    			<input type="checkbox" id="modalChangeCheck" ><label for="modalChangeCheck" class="periodLabel">기간 설정</label>
	                                    
	                                    </div>
	                                </dd>
	                            </dl>
	
	                            <dl class="items">
	                                <dt>일정 타입</dt>
	                                <dd>
	                                   <div class="select">
	                                   <select name="scType" id="modalChangeType">
				                    		   <option value="SC_CODE1" <c:if test="${check.scCateCode == 'SC_CODE1'}">selected</c:if>>공동생활</option>
	                                           <option value="SC_CODE2" <c:if test="${check.scCateCode == 'SC_CODE2'}">selected</c:if>>주민투표</option>
				                    	</select>
	                                   </div>
	                                </dd>
	                            </dl>
	
	                            <dl class="items">
	                                <dt>일정 제목</dt>
	                                <dd>
	                                   <div class="input">
				                    			<input id="modalChangeTitle" type="text" name="scTitle" value="${ check.scTitle }">
	                                   </div>
	                                </dd>
	                            </dl>
	
	                            <%-- <dl class="items">
	                                <dt>일정 내용</dt>
	                                <dd>
	                                   <div class="textarea">
					                    	<textarea name="scContent" id="modalChangeTextarea" cols="30" rows="10">${ check.scContent }</textarea>
	                                   </div>
	                                </dd>
	                            </dl> --%>
	                        </div>
	                        <div class="modalBtnDiv">
	                        <button class="modalBtn" type="button" onclick="weekUPUpdate()">변경하기</button>
	                        <button class="modalBtn" type="button" onclick="weekUPDelete()">삭제하기</button>
	                        <button class="modalBtn" id="cancelWeekChange" type="button">취소하기</button>
	                        </div>
                        </form>
			  </div>
			</div>
		<%-- 공통 footer --%>
		<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>
		<c:if test="${ !empty loginManager }">
		<script>
		disabledDays = [];
			
			$("#modalCheck").change( function(){
				  if (modalCheck.checked) {
					  modalP.style.display = "block";
					  modalDate2.style.display = "block";
				  } else {
					  modalP.style.display = "none";
					  modalDate2.style.display = "none";
					  modalDate2.value = '';
				  }
				});
			
			$("#modalChangeCheck").change( function(){
				  if (modalChangeCheck.checked) {
					  modalP2.style.display = "block";
					  modalChangeDate2.style.display = "block";
				  } else {
					  modalP2.style.display = "none";
					  modalChangeDate2.style.display = "none";
					  modalChangeDate2.value = '';
				  }
				});
			const endDate = '${check.scEndDate}';
			
			function weekUPUpdate() {
				let type = $('#modalChangeType').val();
				let title = $('#modalChangeTitle').val();
				/* let content = $('#modalChangeTextarea').text(); */
				let date1 = $('#modalChangeDate').val();
				let date2 = $('#modalChangeDate2').val();
				let nno = '${notice.n_no}';
				
				if (date2 != '') {
					if (date1 >= date2) {
						alert('기간을 다시 선택해주시기 바랍니다.');
						return;
					}
				}
				
				if(!confirm('주요일정 내역을 변경하시겠습니까?')) {
					return;
				} 
				
				$.ajax({
					url : "${contextPath}/admin/week/upUpdate",
					type : "post",
					data : {type : type, title : title, date1 : date1, date2 : date2, nno : nno},				
					dataType : "json",
					success : function(data){
						if(data != null) {
							if (data> 0) {
								alert('주요일정 변경이 완료되었습니다.');
							} else {
								alert('주요일정 변경에 실패하였습니다.');
							}
						} else {
							alert('주요일정 변경에 실패하였습니다.');
						}
						
						location.href = "${contextPath}/notice/detail?n_no=${notice.n_no}";
					},
					error : function(e){
						console.log(e);
					}
				}); 
			}
			
			function weekUPDelete() {
				
				if(!confirm('주요일정 내역을 삭제하시겠습니까?')) {
					return;
				} 
				
				let nno = '${notice.n_no}';
				
				$.ajax({
					url : "${contextPath}/admin/week/upDelete",
					type : "post",
					data : {nno : nno},				
					dataType : "json",
					success : function(data){
						if(data != null) {
							if (data > 0) {
								alert('주요일정 삭제가 완료되었습니다.');
							} else {
								alert('주요일정 삭제에 실패하였습니다.');
							}
						} else {
							alert('주요일정 삭제에 실패하였습니다.');
						}
						
						location.href = "${contextPath}/notice/detail?n_no=${notice.n_no}";
					},
					error : function(e){
						console.log(e);
					}
				}); 
			}
			
			function weekUPInsert() {
				
				if(!confirm('주요일정을 등록하시겠습니까?')) {
					return;
				} 
				
				let type = $('#modalType').val();
				let title = $('#modalTitle').val();
				let content = $('#modalTextarea').val();
				let date1 = $('#modalDate').val();
				let date2 = $('#modalDate2').val();
				let nno = '${notice.n_no}';
				
				if (date2 != '') {
					if (date1 >= date2) {
						alert('기간을 다시 선택해주시기 바랍니다.');
						return;
					}
				}
				
				$.ajax({
					url : "${contextPath}/admin/week/upInsert",
					type : "post",
					data : {type : type, title : title, date1 : date1, date2 : date2, nno : nno},				
					dataType : "json",
					success : function(data){
						if(data != null) {
							if (data > 0) {
								alert('주요일정 등록이 완료되었습니다.');
							} else {
								alert('주요일정 등록에 실패하였습니다.');
							}
						} else {
							alert('주요일정 등록에 실패하였습니다.');
						}
						
						location.href = "${contextPath}/notice/detail?n_no=${notice.n_no}";
					},
					error : function(e){
						console.log(e);
					}
				}); 
			}	
		
			function updateNoticeView(){
				document.forms.noticeForm.action = "${contextPath}/notice/updateView";
				document.forms.noticeForm.submit();
			}
	
			function noticeDelete(){
				if (confirm("게시글을 삭제 하시겠습니까?")) {
					document.forms.noticeForm.action = "${contextPath}/notice/delete";
					document.forms.noticeForm.submit();
				}
			}
			
		</script>
		</c:if>
		<script src="/oneLife/resources/user/js/weekChangeModal.js"></script>
</body>
</html>