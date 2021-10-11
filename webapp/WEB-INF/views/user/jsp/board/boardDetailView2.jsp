<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 게시글</title>
<%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>

</head>
<body>
	<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>
	
	<div class="bottom_wrap1">
		<p></p><span>> 아파트소식 > 도란도란</span>
		<h1>이웃끼리 도란도란</h1>
		<div class="bottom_wrap2">
		</div>
	</div>
	 <form method="post" name="boardForm">
	 <input type="hidden" name="b_no" value="${ board.b_no }">
		<div class="wrap">
			<div class="board_detail_area">
				<div class="board_detail_title">
					<div class="subject">
						<p>${ board.b_title }</p>
						<img src="/oneLife/resources/user/images/people.png">
						<span class="name">${ board.u_nickname }</span> 
						<span class="date"><fmt:formatDate value="${ board.b_modify_date }" pattern="yyyy.MM.dd HH:mm"/></span> 
                        <span class="count">조회</span>
                        <span class="count">${ board.b_count }</span>
					</div>
                   
					<div class="btn_area">
						<img class="comm_img" src="/oneLife/resources/user/images/message.png">
                        <span class="comment">댓글</span>
                        <span class="comment">4</span>
						<button type="button" onclick="dis()"><img src="/oneLife/resources/user/images/Icon button.png"></button>
                        <div class="dis_aera">
                      <%--  <c:choose>
                        <c:when test="${ !empty loginUser && loginUser.u_NO == board.u_no }">
                        <div id='dis'>
                            <input type="button" class="dis_btn" value="수정" onclick="updateBoardView();">
                            <input type="button" class="dis_btn" value="삭제" onclick="deleteBoard();">
                        </div>
                        </c:when>
                        <c:otherwise>
                        <div id='dis'><input type="button" class="dis_btn" value="신고" onclick="showPopup();"></div>
                        </c:otherwise>
                        </c:choose>  --%>
                        </div>
					</div>
                 
				</div>
				<div class="board_detail_content">
					<pre class="board_detail_cell">${ board.b_content }
                    </pre>
				</div>
                <div class="like_area1">
                    <ul>
                        <li class="like_img"><img src="/oneLife/resources/user/images/like.png"></li>
                        <li class="like">좋아요</li>
                        <li class="like">1 </li>
                        <li class="comm_img"><img src="/oneLife/resources/user/images/message.png"></li>
                        <li class="comment">댓글</li>
                        <li class="comment">1</li>
                    </ul>
                </div>
               
				<div class="reply_area">
                    <p>댓글</p>
                    <%-- <c:forEach items="${ board.replyList }" var="r"> --%>
                    <div class="reply_list">
                        <ul class="reply_ul">
                            <%-- <img src="/oneLife/resources/user/images/people3.png"> --%>
                            <li class="rwriter">${ r.u_nickname }</li>
                            <li class="rcontent">${ r.bc_content }</li>
                            <li class="rdate"><fmt:formatDate value="${ r.bc_modify_date }" type="both" pattern="yyyy.MM.dd HH:mm"/></li>
                        </ul>
                        <div class="reply_btn_area">
                           <%--  <button type="button" id="reply_btn" onclick="reply_dis()"><img src="/oneLife/resources/user/images/Icon button.png"></button> --%>
                            <div class="dis_aera">
                         <%--    <c:choose>
                            <c:when test="${ !empty loginUser && loginUser.u_NO == board.u_no }">	
                            <div id='reply_dis'>
                                <input type="button" class="dis_btn" value="삭제" onclick="deleteReply(${ board.bc_no },${ board.b_no });">
                            </div>
                            </c:when>
                       		<c:otherwise>
                            <div id='reply_dis'><input type="button" class="dis_btn" value="신고" onclick="showPopup();"></div> 
                             </c:otherwise>
                            </c:choose> --%>
                            </div>
                        </div>
					</div>
					<%-- </c:forEach> --%>
					<div class="reply_write">
						<textarea class="reply_content" placeholder="도란도란은 우리가 함께 만들어가는 소중한 공간입니다. 댓글 작성 시 타인에 대한 배려와 책임을 담아주세요." onfocus="this.placeholder=''" onblur="this.placeholder='도란도란은 우리가 함께 만들어가는 소중한 공간입니다. 댓글 작성 시 타인에 대한 배려와 책임을 담아주세요.'"  maxlength="600"></textarea>
						<span id="counter">0/ 600</span>
                        <button type="button" onclick="addReply(${ board.b_no });" class="reply_btn2">등록</button>
					</div>
				</div>
              
			</div>
			
				<div class="btn_area">
					<button type="button" id="btn2" onclick="location.href='${contextPath}/board/list'">목록</button>
				</div>
			</div>	
	
	   </form>	
		<%-- 공통 footer --%>
		<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>

        <!-- 댓글 -->
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

        function updateBoardView(){
	 			document.forms.boardForm.action = "${contextPath}/board/updateView";
	 			document.forms.boardForm.submit();
	 		}

        function deleteBoard() {
            if (confirm('게시글을 삭제 하시겠습니까?')) {
                document.forms.boardForm.action = "${contextPath}/board/delete";
		 		document.forms.boardForm.submit();
            }
        }

        </script>

        <!-- 작은 창 -->
        <script>
            function dis(){
                if($('#dis').css('display') == 'none'){
                $('#dis').show();
                    }else{
                    $('#dis').hide();
                }
            }  
            function reply_dis(){
                if($('#reply_dis').css('display') == 'none'){
                $('#reply_dis').show();
                    }else{
                    $('#reply_dis').hide();
                }
            }  
            // 신고 팝업창
            function showPopup(){
                window.open("popup.html", "신고 팝업창", "width=500, height=400, left=800, top=300"); 
            }  
        </script>
        
        
        <!-- 댓글 -->
        <script>
		function addReply(b_no) {
			$.ajax({
				url : "${ contextPath }/board/insertReply",
				type : "post",
				data : { b_no : b_no, content : $(".reply_content").val() },
				dataType : "json",
				success : function(data) {
					if (data != null) {
						
						var html = '';
						
						// 새로 받아온 갱신 된 댓글 목록을 for문을 통해 html에 저장
						for (var key in data) {
					          html += '<ul class="reply_ul"><img src="/oneLife/resources/user/images/people3.png">'
							      + '<li class="rwriter">'
							      + data[key].u_nickname + '</li><li class="rcontent">'
							      + data[key].bc_content + '</li><li class="rdate">'
							      + data[key].bc_modify_date + '</li></ul>'
							      + '<div class="reply_btn_area"><button type="button" id="reply_btn" onclick="reply_dis()"><img src="/oneLife/resources/user/images/Icon button.png"></button>'
							      + '<div class="dis_aera">'
							      + '<c:choose><c:when test="${ !empty loginUser && loginUser.u_NO == ' + data[key].u_no +' }"><div id="reply_dis">'
							      + '<input type="button" class="dis_btn" value="삭제" onclick="deleteReply(' + data[key].bc_no + ',' + data[key].b_no + ');"></div>'
							      + '</c:when><c:otherwise><div id="reply_dis"><input type="button" class="dis_btn" value="신고" onclick="showPopup();"></div></c:otherwise></c:choose></div>';
							      
						} 
						
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
		function deleteReply(bc_no, b_no) {
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
							      + data[key].m_no + '</li><li class="rcontent">'
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