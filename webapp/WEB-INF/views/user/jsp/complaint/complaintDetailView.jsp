<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아파트민원 게시판</title>
<style>
.reply_ul .rcontent {
   font-weight: 300;
}
.type02 {
	left: 190px;
    bottom: 53px;
    top: -40px;
}

</style>
<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>
</head>
<body>
	<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>
	
	<div class="bottom_wrap1">
		<p></p><span>> 아파트 민원</span>
		<h1>아파트 민원</h1>
		<div class="bottom_wrap2">
		</div>
	</div>
	<form method="post" name="complaintForm">
	<input type="hidden" name="c_no" value="${ complaint.c_no }">
		<div class="wrap">
			<div class="com_detail_area">
				<div class="com_detail_title">
					<div class="subject">
						<p>${ complaint.c_title }</p>
						<img src="/oneLife/resources/user/images/people.png">
						<span class="name">${ complaint.r_name }</span> 
						<span class="dong">${ complaint.r_dong }동 ${ complaint.r_ho }호</span>
						<span class="date">
						<fmt:formatDate value="${ complaint.enroll_date }" pattern="yyyy.MM.dd HH:mm:ss"/>
						</span>
					</div>
					<div class="btn_area">
						<c:if test="${ !empty loginUser && loginUser.u_NO == complaint.u_no }"> 
						<button type="button" onclick="updateComplaintView();">수정하기</button><span>|</span>
						<button type="button" onclick="deleteComplaint();">삭제하기</button>
						</c:if> 
						<c:if test="${ !empty loginManager }">
                        <!-- 관리자는 삭제하기 버튼만 보여짐 -->
                        <button type="button" onclick="deleteComplaint();">삭제하기</button>
                        </c:if>
					</div>
				</div>
				<div class="com_detail_content">
					<pre class="com_detail_cell">${ complaint.c_content }</pre>
				</div>
				<h4>답글</h4>
				<div class="reply_area">
					<div class="reply_list">
                    <c:forEach items="${ complaint.replyList }" var="r">
                     <ul class="reply_ul">
                     		<img src="/oneLife/resources/user/images/people2.png">
                            <li class="rwriter">${ r.m_nick }</li>
                            <li class="rcontent">${ r.cm_content }</li>
                            <li class="rdate">
                            <fmt:formatDate value="${ r.cm_modify_date }" type="both" pattern="yyyy.MM.dd HH:mm:ss"/>
                            </li>
                        </ul> 
                       <c:if test="${ !empty loginManager }">
                       <div class="reply_btn_area type02">
                            <!-- <button type="button" onclick="updateBoardView();">수정하기</button><span>|</span> -->
                            <button type="button" onclick="deleteReply(${ r.cm_no },${ r.c_no });">삭제하기</button>
                        </div> 
                        </c:if>
                         </c:forEach>
                        </div>
                    
					<c:if test="${ !empty loginManager }">
                    <!-- 관리자만 댓글 작성 가능 -->
					<div class="reply_write">
						<textarea class="reply_content" placeholder="댓글을 작성해 주세요" onfocus="this.placeholder=''" onblur="this.placeholder='댓글을 작성해 주세요'"  maxlength="600"></textarea>
						<span id="counter">0/ 600</span>
                        <button type="button" onclick="addReply(${ complaint.c_no });">등록</button>
					</div>
					</c:if>
				</div>
			</div>
			
				<div class="btn_area">
					<button type="button" id="btn2" onclick="location.href='${contextPath}/complaint/list'">목록</button>
				</div>
				
			</div>	
	</form>	
	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>
	
        <script>
        $(document).ready(function() {
            $('.reply_content').on('keyup', function() {
                $('#counter').html($(this).val().length+"/ 600");
        
                if($(this).val().length > 600) {
                    alert("최대 600자까지 입력 가능합니다.");
                    $(this).val($(this).val().substring(0, 600));
                    $('#counter').html("600 / 600");
                }
            });
        });

        function updateComplaintView(){
	 			document.forms.complaintForm.action = "${contextPath}/complaint/updateView";
	 			document.forms.complaintForm.submit();
	 		}

        function deleteComplaint() {
            if (confirm('게시글을 삭제 하시겠습니까?')) {
                document.forms.complaintForm.action = "${contextPath}/complaint/delete";
		 		document.forms.complaintForm.submit();
            }
        }

        </script>
        
        <!-- 댓글 -->
        <script>
		// 댓글 달기 버튼 클릭 시 탯글 저장(insert) 기능 수행 후
		// 비동기적으로 새로 갱신 된 replyList를 테이블에 적용 시키는 ajax 통신
		function addReply(c_no) {
			$.ajax({
				url : "${ contextPath }/complaint/insertReply",
				type : "post",
				data : { c_no : c_no, content : $(".reply_content").val() },
				dataType : "json",
				success : function(data) {
					if (data != null) {
						
						var html = '';
						
						// 새로 받아온 갱신 된 댓글 목록을 for문을 통해 html에 저장
						for (var key in data) {
							html += '<ul class="reply_ul"><img src="/oneLife/resources/user/images/people2.png">'
							      + '<li class="rwriter">'
							      + data[key].m_nick + '</li><li class="rcontent">'
							      + data[key].cm_content + '</li><li class="rdate">'
							      + data[key].cm_modify_date + '</li></ul>'
							      + '<div class="reply_btn_area type02">'
							      + '<button type="button" onclick="deleteReply(' + data[key].cm_no + ',' + data[key].c_no + ');">삭제하기</button></div>';
						
						}
						console.log(html);
						console.log(data);
						// 갱신 된 댓글 목록을 다시 적용
						$(".reply_list").html(html);
						// 댓글 작성 부분 리셋
						$(".reply_content").val("");
						
						
					} else {
						alert('댓글 입력 실패!');
					}
					
				},
				error : function(e) {
					console.log(e);
				}
			});
		}	
	</script>
	
	 <script>
		function deleteReply(cm_no, c_no) {
			$.ajax({
				url : "${ contextPath }/complaint/deleteReply",
				type : "post",
				data : { cm_no : cm_no,  c_no : c_no },
				dataType : "json",
				success : function(data) {
					    alert('댓글 삭제 되었습니다.');
						if (data != null) {
						
						var html = '';
						
						// 새로 받아온 갱신 된 댓글 목록을 for문을 통해 html에 저장
						for (var key in data) {
							html += '<ul class="reply_ul"><img src="/oneLife/resources/user/images/people2.png">'
							      + '<li class="rwriter">'
							      + data[key].m_nick + '</li><li class="rcontent">'
							      + data[key].cm_content + '</li><li class="rdate">'
							      + data[key].cm_modify_date + '</li></ul>'
							      + '<div class="reply_btn_area type02">'
							      + '<button type="button" onclick="deleteReply(' + data[key].cm_no + ',' + data[key].c_no + ');">삭제하기</button></div>';
							     
						
						}
						
						// 갱신 된 댓글 목록을 다시 적용
						$(".reply_list").html(html);
						
					} else {
						alert('댓글 삭제 실패!');
					}
					
				},
				error : function(e) {
					console.log(e);
				}
			});
		}	
	</script>
	
</body>
</html>