<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, user-scalable=no, initial-scale=1" />
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0,minimum-scale=1.0,target-densitydpi=medium-dpi" />
<title>입주자 명부 추가/수정</title>

<jsp:include page="/WEB-INF/views/admin/common/link.jsp"></jsp:include>

</head>
<body>
	<div id="wrap">
		<%-- header 공통 적용 --%>
		<jsp:include page="/WEB-INF/views/admin/common/header.jsp"></jsp:include>

		<div class="container">
			<%-- menubar 공통 적용  --%>
			<jsp:include page="/WEB-INF/views/admin/common/menubar.jsp"></jsp:include>

			<section id="content">
				<div class="content">
					<div class="view_wrap">
						<h2 class="sub_tit">입주자 명부 변경</h2>
						<form action="${contextPath}/admin/member/view" method="post" name="residentFrm">
							<div class="view_items resident">
								<dl class="items">
									<dt>동/호</dt>
									<dd class="type01">
										<div class="input">
											<input type="text" name="rDong" id="rDong"
												value="${param.rDong}" readonly>
										</div>
										동
										<div class="input">
											<input type="text" name="rHo" id="rHo" value="${param.rHo}"
												readonly>
										</div>
										호
									</dd>
								</dl>

								<dl class="items">
									<dt>세대원 정보</dt>
									<dd>
										<h4 class="tit">세대주</h4>
										<c:forEach var="hol" items="${rList}">
											<div class="res_input">
												<c:if test="${hol.rType eq '세대주'}">
													<div class="res_box res_name">
														<span>이름 : </span> <input type="text" name="holderName"
															id="" class="input" value="${hol.rName}">
													</div>
													<div class="res_box res_mail">
														<span>이메일 : </span> <input type="text" name="holderEmail"
															id="" class="input" value="${hol.rEmail}">
													</div>
												</c:if>
											</div>
										</c:forEach>
										<h4 class="tit">
											세대원 <a href="javascript:;" class="res_plus"></a>
										</h4>

										<c:forEach var="hol" items="${rList}">
											<c:if test="${hol.rType eq '세대원'}">
												<div class="res_input">
													<div class="res_box res_name">
														<span>이름 : </span>
														<input type="text" name="memberName"  id="" class="input" value="${hol.rName}" >
													</div>
													<div class="res_box res_mail">
														<span>이메일 : </span>
														<input type="text" name="memberEmail" id="" class="input"  value="${hol.rEmail}">
													</div>
													<a href="javascript:;" class="res_minus"></a>
												</div>
											</c:if>
										</c:forEach>
									</dd>
								</dl>
							</div>
							<div class="view_btn">
								<a href="javascript:residentSubmit();" class="ok">변경하기</a>
								<a href="javascript:residentCancle();">변경취소</a>
							</div>
						</form>
					</div>
				</div>
			</section>
		</div>
	</div>


	<script>
        $(function(){
        	// 세대원 input칸
        	let tag = '<div class="res_input">';
            tag += '	<div class="res_box res_name">';
            tag += '	<span>이름 : </span>';
            tag += '	<input type="text" name="memberName" id="memberName" class="input res_name">';
        	tag += '</div>';
        	tag += '<div class="res_box res_mail">';
            tag += '	<span>이메일 : </span>';
            tag += '	<input type="text" name="memberEmail" id="memberEmail" class="input res_mail">';
        	tag += '	</div>';
        	tag += '	<a href="javascript:;" class="res_minus"></a>';
   			tag += '</div>';
   			
            $(document).on("click", ".res_plus", function(){
	       		$('.view_wrap .view_items .items:last-child dd').append(tag);
            });
            
            $(document).on("click", ".res_minus", function(){
            	$(this).parents('.res_input').remove();
            });
        })
        
        function residentSubmit(){
        	// 이름 한글인지 검사
        	const regex = /^[가-힣]+$/;
        	let inputs = document.querySelectorAll('.res_name input');
        	for(let i = 0; i < inputs.length; i++){
        		if(!regex.test(inputs[i].value)){
        			alert("한글로 입력해주세요.")
        			inputs[i].select();
        			return;
        		}
        	}
        	
        	// 이메일 검사
        	const reg_email = /^([0-9a-zA-Z_\.-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
        	let mails = document.querySelectorAll('.res_mail input');
        	for(let i = 0; i < mails.length; i++){
        		if(!reg_email.test(mails[i].value)){
        			alert("이메일형식 으로  입력해주세요.")
        			mails[i].select();
        			return;
        		}
        	}
        	
        	
        	if(confirm("입주자 명부를 변경하시겠습니까?")){
        		document.forms.residentFrm.submit();
        	}
        }
        
        function residentCancle(){
        	if(confirm("입주자 명부 변경취소하고 목록 페이지로 이동하시겠습니까?")){
        		location.href="${contextPath}/admin/member/list"
        	}
        }
        
        

    </script>

</body>

</html>