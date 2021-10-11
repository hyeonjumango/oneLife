<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String message = (String)request.getAttribute("msg");
%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 완료페이지</title>

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
		<div class="wrap">
			<div class="notice_area">
				<div class="notice_title">
					<h1>무료한 일상, <b>이웃들과 함께<br>도란도란</b> 이야기해보세요!</h1>
				</div>
				<div class="notice_content">
					<div class="subject"></div>
					<div class="notice_detail">
                        <img src="/oneLife/resources/user/images/Group 77.png">
                        <h2><%=message %></h2>
                        <div class="btn_area re">
                            <button type="button" id="re_btn" onclick="detailView(${b_no});">작성한 글 확인</button>
                            <button type="button" id="re_btn" onclick="location.href='${ contextPath }/board/list'">목록보기</button>
                        </div>
                    </div>
				</div>
				</div>
			
			</div>
		</div>
		
	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>
	
	  <script>
            function detailView(b_no){
                // nno를 쿼리스트링에 데이터로 넘김
                location.href='${contextPath}/board/detail?b_no='+b_no;
	 	    }
        </script>
	
</body>
</html>