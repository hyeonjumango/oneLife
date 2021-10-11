<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0,minimum-scale=1.0,target-densitydpi=medium-dpi" />
    <title>관리자 목록</title>

    <jsp:include page="/WEB-INF/views/admin/common/link.jsp"></jsp:include>

</head>
<body>
<%-- 회원가입성공하거나 실패했을시 msg값 --%>
<c:if test="${!empty sessionScope.msg}">
	<script>
		alert('${sessionScope.msg}');
	</script>
	<c:remove var="msg" scope="session"/>
</c:if>

	

    <div id="wrap">
        <%-- header 공통 적용 --%>
        <jsp:include page="/WEB-INF/views/admin/common/header.jsp"></jsp:include>
        
        <div class="container">
           <%-- menubar 공통 적용  --%>
           <jsp:include page="/WEB-INF/views/admin/common/menubar.jsp"></jsp:include>

            <secion id="content">
                <div class="content">
                    <div class="list_wrap">
                        <h2 class="sub_tit">관리자 목록</h2>
                        <div class="search_box">
	                        <form action="${contextPath}/admin/manager/list" method="get">
	                            <div class="search_bot resident clearfix">
	                                <div class="items clearfix">
	                                    <label for="reser_fac">검색조건</label>
	                                    <div class="select">
	                                        <select name="managerListSearch" id="reser_fac">
	                                        	<c:set var="reser" value="${param.managerListSearch}"/>
	                                            <option value="id" <c:if test="${reser eq 'id' }">selected</c:if>>아이디</option>
	                                            <option value="name" <c:if test="${reser eq 'name' }">selected</c:if>>이름</option>
	                                        </select>
	                                    </div>
	                                    <c:set var="managerListSearch" value="${param.managerListValue}"/>
	                                    <input type="text" name="managerListValue" class="input" value="${managerListSearch}">
	                                    <button type="submit" class="btn">검색</button>
	                                </div>
	                            </div>
                            </form>
                        </div>
                        <div class="list_content">
                            <div class="table_wrap resident_list">
                                <table class="table">
                                    <colgroup>
                                        <col width="auto">
                                        <col width="auto">
                                        <col width="auto">
                                        <col width="auto">
                                        <col width="auto">
                                        <c:if test="${loginManager.mJobcode eq 'M_CODE2'}">
                                        	<col width="auto">
                                        </c:if>
                                    </colgroup>
                                    <caption class="ir_so">입주자 명부 목록</caption>
                                    <thead>
                                        <tr>
                                            <th>관리자 번호</th>
                                            <th>아이디</th>
                                            <th>이름</th>
                                            <th>계급</th>
                                            <th>전화번호</th>
                                            <c:if test="${loginManager.mJobcode eq 'M_CODE2'}">
                                            	<th>수정</th>
                                            </c:if>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                    <c:choose>
                                    	<c:when test="${fn:length(mList) == 0}">
                                    		<%-- 검색결과가 없을시 화면출력 --%>
                                    		<tr>
                                    			<td colspan="6">
                                    				<div class="list_nodate">
	                                                    <img src="${contextPath}/resources/admin/images/list_nodate.png" alt="NODATE">
	                                                    <p>등록된 관리자가 없습니다.</p>
	                                                </div>
                                    			</td>
                                    		</tr>
                                    	</c:when>
                                    	<c:otherwise>
                                    		<c:forEach var="item" items="${mList}">
												<tr>
		                                           <td>${item.mNo}</td>
		                                           <td>${item.mId}</td>
		                                           <td>${item.mName}</td>
		                                           <td>${item.mJobName}</td>
		                                           <td>${item.mPhone}</td>
		                                           <c:if test="${loginManager.mJobcode eq 'M_CODE2'}">
			                                           <td>
			                                               <a href="javascript:popShow('managerClass');" class="tag">변경</a>
			                                               <%-- 관리자 번호 --%>
			                                               <input type="hidden" name="mNo" value="${item.mNo}"/>
			                                           </td>
		                                           </c:if>
		                                      	</tr>
                                      		</c:forEach>
                                    	</c:otherwise>
                                    </c:choose>
                                     </tbody>
                                </table>
                            </div>
                            <!-- 총관리자만 버튼보임 -->
                            <c:if test="${loginManager.mJobcode eq 'M_CODE2'}">
	                            <div class="btn_box">
	                                <a href="javascript:popShow('newManager');" class="remove">관리자 추가</a>
	                            </div>
                            </c:if>
                          
                          	<%-- 검색결과에대한 파라미터값 저장 --%>
                          	<c:if test="${!empty param.managerListSearch && !empty param.managerListValue}">
                          		<c:set var="searchParam" value="&managerListSearch=${param.managerListSearch}&managerListValue=${param.managerListValue}"/>
                          	</c:if>
                          	<jsp:include page="/WEB-INF/views/admin/common/paging.jsp"	flush="false">
								<jsp:param name="listSize" value="${mList.size()}" />
								<jsp:param name="piPage" value="${pi.page}" />
								<jsp:param name="startPage" value="${pi.startPage}" />
								<jsp:param name="endPage" value="${pi.endPage}" />
								<jsp:param name="maxPage" value="${pi.maxPage}" />
								<jsp:param name="pageUrl"
									value="${pageContext.request.requestURL}" />
								<jsp:param name="search" value="${searchParam}" />
							</jsp:include>
                            
                        </div>
                    </div>
                </div>
            </secion>
        </div>
    </div>

    <!-- 팝업영역 -->
     <!-- 신규회원등록 팝업 -->
     <c:if test="${loginManager.mJobcode eq 'M_CODE2'}">
     <div class="popup_wrap" id="newManager">
        <div class="dim"></div>
        <div class="item">
           <form name="newManagerFrm">
	           <div class="new_admin">
	               <div class="title">신규 관리자 등록</div>
	               <div class="new_item">
	                   <label for="mId">계정</label>
	                   <input type="text" name="mId" id="mId">
	                   <a href="javascript:idCheck();" class="idCheck">중복확인</a>
	               </div>
	               <div class="new_item">
	                    <label for="mPwd1">비밀번호</label>
	                    <input type="password" name="mPwd1" id="mPwd1">
	                </div>
	                <div class="new_item">
	                    <label for="mPwd2">비밀번호 확인</label>
	                    <input type="password" name="mPwd2" id="mPwd2">
	                </div>
	                <div class="new_item">
	                    <label for="mName">이름</label>
	                    <input type="text" name="mName" id="mName">
	                </div>
	                <div class="new_item">
	                    <label for="mPhone">휴대폰 번호</label>
	                    <input type="text" name="mPhone" id="mPhone">
	                </div>
	           </div>
           </form>
           <div class="new_btn_box">
               <a href="javascript:mCreate();">등록하기</a>
               <a href="javascript:popHide('newManager');">취소하기</a>
           </div>
        </div>
    </div>
    </c:if>
    <script>
    	let regCheck = false;
    	// 아이디 중복체크
    	function idCheck(){
    		let mId = $('#mId');
    		//console.log(mId.val());
    		
    		if(mId.val().length >= 1){
    			$.ajax({
        			url : "${contextPath}/admin/idCheck",
        			type : "post",
        			data : {mId : mId.val()},
        			success : function(item){
        				if(item != 0){
        					// 계정 생성불가능
        					alert(mId.val() + "는 이미 존재하는 계정입니다.");
        					mId.attr('readonly', false);
        					mId.select();
        					regCheck = false;
        				}else{
        					// 계정생성 가능
        					alert(mId.val() + "의 계정이 사용가능합니다.");
        					mId.attr('readonly', true);
        					regCheck = true;
        					
        				}
        			}
        		})
    		}else{
    			alert("계정을 입력해주세요!");
    			mId.select();
    		}
    		
    		
    	}
    	
    	// 계정생성
    	function mCreate(){
    		let mId = $('#mId');
    		let mPwd1 = $("#mPwd1");
    		let mPwd2 = $("#mPwd2");
    		let mPhone = $("#mPhone");
    		if(!regCheck){
    			// 중복확인을 하지않아 값이 false일시 계정생성 불가능
    			alert("계정 중복확인 해주세요!");
    			mId.select();
    			return;
    		}
    		
    		//비밀번호, 비밀번호 확인 값이 같은지 확인
    		if((mPwd1.val() !== mPwd2.val()) && mPwd1.val() != 0 && mPwd2.val() != 0){
    			alert("비밀번호 값을 확인해주세요!");
    			mPwd2.select();
    			return;
    		}
    		
    		// 휴대폰 번호 입력하였는지 확인
    		if(mPhone.val() < 0){
    			alert("휴대폰 번호를 입력해주세요!");
    			mPhone.select();
    			return; 
    		}
    		
    		document.forms.newManagerFrm.method = "post";
    		document.forms.newManagerFrm.action = "${contextPath}/admin/manager/create";
    		document.forms.newManagerFrm.submit();
    		
    		
    	}
    
    </script>

    <!-- 계급변경 -->
    <c:if test="${loginManager.mJobcode eq 'M_CODE2'}">
    <div class="popup_wrap" id="managerClass">
        <div class="dim"></div>
        <div class="item">
        	<form name="managerClassFrm">
	           <div class="new_admin">
	               <div class="title">관리자 직급 변경</div>
	               <input type="hidden" name="page" value="${param.page}"/>
	               <input type="hidden" name="mNo" id="classmNo"/>
	               <div class="new_item">
	                   <label for="mId">계정</label>
	                   <input type="text" name="mId" id="mId" disabled>
	               </div>
	               <div class="new_item">
	                   <label for="mName">이름</label>
	                   <input type="text" name="mName" id="mName" disabled>
	               </div>
	               <div class="new_item">
	                   <label for="mPhone">전화번호</label>
	                   <input type="text" name="mPhone" id="mPhone" disabled>
	               </div>
	                <div class="new_item">
	                    <label for="mClass">직급</label>
	                    <select name="mClass" id="mClass">
	                        <option value="M_CODE1">관리자</option>
	                        <option value="M_CODE2">총관리자</option>
	                    </select>
	                </div>
	           </div>
           </form>
           <div class="new_btn_box">
               <a href="javascript:mcFrm();">변경하기</a>
               <a href="javascript:popHide('managerClass');">취소하기</a>
           </div>
        </div>
    </div>
    <!-- 팝업영역 -->
    <script>
    	$(function(){
    		// 계급변경시 회원정보 조회
    		$('.tag').click(function(){
    			let mNo = $(this).next().val();
    			$.ajax({
    				url : "${contextPath}/admin/manager/classChange",
    				type : "get",
    				datatype : "json",
    				data : {mNo : mNo},
    				success : function(item){
    					console.log(item);
    					$('#managerClass #classmNo').val(item.mNo);
    					$('#managerClass #mName').val(item.mName);
    					$('#managerClass #mId').val(item.mId);
    					$('#managerClass #mPhone').val(item.mPhone);
    					if(item.mJobcode == 'M_CODE1'){
    						$('#managerClass #mClass option').eq(0).attr('selected', true);
    					}else if(item.mJobcode == 'M_CODE2'){
    						$('#managerClass #mClass option').eq(1).attr('selected', true);
    					}
    				},
    				
    				error : function(e) {
    					console.log(e);
    				}
    				
    			})
    			
    		})
    	})
    	
    	// 계급 변경
    	function mcFrm(){
    		let frm = document.forms.managerClassFrm;
    		frm.action = "${contextPath}/admin/manager/classChange";
    		frm.method = "post";
    		frm.submit();
    	}
    	
    	
    
    </script>
    </c:if>
    
    
</body>

</html>