<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0,minimum-scale=1.0,target-densitydpi=medium-dpi" />
    <title>신고내역 목록</title>

    <jsp:include page="/WEB-INF/views/admin/common/link.jsp"></jsp:include>
	<style>
	#searchDiv{
	width : 100%;
	margin-top : 0;
	}
	
	#searchDiv .select {
	margin-right : 15px;
	}
	</style>
</head>
<body>
    <div id="wrap">
        <%-- header 공통 적용 --%>
        <jsp:include page="/WEB-INF/views/admin/common/header.jsp"></jsp:include>
        
        <div class="container">
            <%-- menubar 공통 적용  --%>
        	<jsp:include page="/WEB-INF/views/admin/common/menubar.jsp"></jsp:include>

            <secion id="content">
                <div class="content">
                    <div class="list_wrap">
                        <h2 class="sub_tit">신고 내역 관리</h2>
                        <form>
	                        <div class="search_box">
	                            <!-- <div class="search_top report clearfix"> -->
	                                <!-- <div class="items clearfix">
	                                    
	                                </div> -->
	                                <%-- <div class="items clearfix">
	                                    <label for="reportNum">신고누적</label>
	                                    <div class="select">
	                                        <select name="reportNum" id="reportNum">
	                                            <option value="all">전체</option>
	                                            <option value="reportDown" <c:if test="${param.reportNum eq 'reportDown'}">selected</c:if>>0~4</option>
	                                            <option value="reportUp" <c:if test="${param.reportNum eq 'reportUp'}">selected</c:if>>5이상</option>
	                                        </select>
	                                    </div>
	                                </div> --%>
	                            <!-- </div> -->
	                            <div class="search_bot report clearfix" id="searchDiv">
	                            	
	                                <div class="items clearfix">
	                                <label for="reser_fac">게시 여부</label>
	                                    <div class="select">
	                                        <select name="status" id="reser_fac">
	                                            <option value="all">전체</option>
	                                            <option value="Y" <c:if test="${param.status eq 'Y'}">selected</c:if>>Y</option>
	                                            <option value="N" <c:if test="${param.status eq 'N'}">selected</c:if>>N</option>
	                                        </select>
	                                    </div>
	                                    <label for="">검색조건</label>
	                                    <div class="select">
	                                        <select name="searchName" id="reser_fac">
	                                            <option value="title" <c:if test="${param.searchName eq 'title'}">selected</c:if>>게시글 제목</option>
	                                             <option value="nick" <c:if test="${param.searchName eq 'nick'}">selected</c:if>>닉네임</option>
	                                        </select>
	                                    </div>
	                                    <input type="text" class="input" name="searchValue" value="${param.searchValue}">
	                                    <button type="submit" class="btn">검색</button>
	                                </div>
	                            </div>
	                        </div>
                        </form>

                        <div class="list_content">
                            <div class="report_wrap">
                                <a href="${contextPath}/admin/report/bList" class="on">게시글</a>
                                <a href="${contextPath}/admin/report/rList">댓글</a>
                            </div>
                            <div class="table_wrap">
                                <table class="table">
                                    <colgroup>
                                        <col width="60px">
                                        <col width="80px">
                                        <col width="auto">
                                        <col width="100px">
                                        <col width="180px">
                                        <col width="100px">
                                        <col width="100px">
                                        <col width="80px">
                                        
                                    </colgroup>
                                    <caption class="ir_so">게시글 신고 내역 관리</caption>
                                    <thead>
                                        <tr>
                                            <th>
                                                <div class="checkbox">
                                                    <input type="checkbox" name="checkAll" id="checkAll">
                                                    <label for="checkAll"></label>
                                                </div>
                                            </th>
                                            <th>게시글 번호</th>
                                            <th>게시글 제목</th>
                                            <th>작성자(닉네임)</th>
                                            <th>작성일</th>
                                            <th>조회수</th>
                                            <th>신고누적</th>
                                            <th>게시 여부</th>
                                        </tr>
                                    </thead>
                                    <!-- 게시글일시 -->
                                    <tbody>
                                    	<c:choose>
                                    		<c:when test="${rList.size() > 0}">
                                    			<c:forEach var="r" items="${rList}">
                                    				<%-- 경고횟수가 5회이상일시 강조 --%>
                                    				<c:choose>
                                    					<c:when test="${r.report >= 5 && r.status eq 'Y'}">
	                                    				<%-- 경고횟수가 5회이상이고 공개상태일시 --%>
                                    						<tr class="warning">
                                    					</c:when>
                                    					<c:when test="${r.status eq 'N'}">
                                    					<%-- 비공개상태일시 --%>
                                    						<tr class="w_hide">
                                    					</c:when>
                                    					<c:otherwise>
                                    						<tr>
                                    					</c:otherwise>
                                    				</c:choose>
			                                            <td>
			                                                <div class="checkbox">
			                                                    <input type="checkbox" name="reportCheck" id="${r.bNo}" value="${r.bNo}" <c:if test="${r.status eq 'N'}">disabled</c:if>>
			                                                    <label for="${r.bNo}"></label>
			                                                </div>
			                                            </td>
			                                            <td>
			                                                ${r.bNo}
			                                            </td>
			                                            <td>
			                                                <a href="${contextPath}/board/Mandetail?b_no=${r.bNo}" class="report_txt">${r.bTtitle}</a>
			                                            </td>
			                                            <td>
			                                                <p>${r.uNickName}</p> 
			                                            </td>
			                                            <td>
			                                                <p><fmt:formatDate value="${r.enrollDate}" pattern="yyyy.MM.dd HH:mm"/></p>
			                                            </td>
			                                            <td>
			                                                <p>${r.bCount}</p>
			                                            </td>
			                                            <td>
			                                                <p>${r.report}</p>
			                                            </td>
			                                            <td>
			                                                ${r.status}
			                                            </td>
			                                        </tr>
		                                    	</c:forEach>
                                    		</c:when>
                                    		<c:otherwise>
                                    		<tr>
                                    			<td>
                                    				<div class="list_nodate">
														<img src="/oneLife/resources/admin/images/list_nodate.png" alt="NODATE">
													<p>신고된 게시글이 존재하지 않습니다.</p>
												</div>
                                    			</td>
                                    		</tr>
                                    		<script>
									    		let thSize = $('.table_wrap .table thead tr th').length;
									    		$('.content .list_nodate').parent('td').attr('colspan', thSize);
											</script>
                                    			
                                    		</c:otherwise>
                                    	</c:choose>
                                    </tbody>
                                   <!-- 게시글 일시 -->
                                </table>
                            </div>
                            <div class="btn_box">
                                <a href="javascript:remove_popOpen('reportPop1');" class="remove">삭제</a>
                            </div>
                           	
                           	<%-- 검색결과에대한 파라미터값 저장 --%>
							<c:if test="${!empty param.status && !empty param.reportNum}">
                          		<c:set var="searchParam" value="&status=${param.status}&reportNum=${param.reportNum}&searchName=${param.searchName}&searchValue=${param.searchValue}"/>
                          	</c:if>
							<jsp:include page="/WEB-INF/views/admin/common/paging.jsp"
								flush="false">
								<jsp:param name="listSize" value="${rList.size()}" />
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
    <!-- 삭제 팝업 -->
    <div class="popup_wrap" id="reportPop1">
        <div class="dim"></div>
        <div class="item">
            <h3 class="tit">게시글 삭제</h3>
            <p class="txt">총 <span class="nub">2</span>건의 게시글을 삭제하시겠습니까?</p>
            <div class="btn_box">
                <a href="javascript:remove_popOpen2();" class="ok">삭제</a>
                <a href="javascript:popHide('reportPop1');" class="cancle">취소</a>
            </div>
        </div>
    </div>

     <!-- 삭제완료 팝업 -->
     <div class="popup_wrap" id="reportPop2">
        <div class="dim"></div>
        <div class="item">
            <h3 class="tit">게시글 삭제</h3>
            <p class="txt">총 <span class="nub">2</span>건의 게시글을 삭제되었습니다.</p>
            <div class="btn_box">
                <a href="javascript:reportLast();" class="ok">확인</a>
            </div>
        </div>
    </div>
    <!-- 팝업영역 -->

    <script>
    	let checkboxs = [];
       $(function(){
    	// 체크박스
    	     let checkboxs = [];
    	     $('#checkAll').change(function(){
    	    	 let checked = $(this).is(':checked');
    	    	 if(checked){
    	    		 $('input[name=reportCheck]').prop('checked', true);
    	    		 $('input[name=reportCheck]:disabled').prop('checked', false);
    	    	 }else{
    	    		 $('input[name=reportCheck]').prop('checked', false);
    	    	 }
    	    	 
    	    	 checkArr();
    	    	 
    	    	 
    	     });
    	     
    	     $('input[name=reportCheck]').change(function(){
    	    	// 체크박스 갯수
    	    	let checkSize = $('input[name=reportCheck]').length;
    	    	// 체크갯수
    	    	let checkOnSize = $('input[name=reportCheck]:checked').length;
    	    	// disalbled 갯수
       	    	let checkDis = $('input[name=reportCheck]:disabled').length;
    	    	
    	    	if(checkSize - checkDis === checkOnSize){
    	    		// 전체 체크됐을시
    	    		$('#checkAll').prop('checked', true);
    	    	}else{
    	    		// 체크가 하나라도 풀려있을시
    	    		$('#checkAll').prop('checked', false);
    	    	}
    	    	
    	    	checkArr();
    	    	
    	     });
    	   
    	   
    	   
       })
       
       // 체크박스 배열로 변환
	     function checkArr(){
	    	 checkboxs = [];
	    	 for(let i = 0; i < $('input[name=reportCheck]').length; i++){
	    		 if($('input[name=reportCheck]').eq(i).is(':checked')){
	    			 checkboxs.push($('input[name=reportCheck]').eq(i).val());
	    		 }
	    	 }
	    	 
	    	 // console.log(checkboxs);
	     }
       
        // 게시글 삭제
     	function remove_popOpen(){
    	 let check = $('input[name=reportCheck]:checked').length;
    	 if(check > 0){
    		 $('.nub').text(check);
    		 popShow('reportPop1');
    	 }else{
    		 alert('체크박스를 1개이상 선택해주세요.');
    	 }
     }
        // 게시글 삭제 2단계
     	function remove_popOpen2(){
       	 $.ajax({
       		 url : "${contextPath}/admin/report/remove",
       		 traditional : true,
       		 type: "post",
       		 data : {"reportChecks" : checkboxs},
       		 success : function(item){
       			 // console.log(item)
       			 if(item >= 0){
       				 $('#reportPop2 .item .txt').text("총 " + item + "건의 게시글이 삭제되었습니다.");
       			 }else{
       				 $('#reportPop2 .item .txt').text("총 " + item + "건의 게시글 삭제를 실패하였습니다.");
       			 }
       		 }
       		 
       	 })
       	 
       	$('#reportPop1').removeClass('pop_on');
       	popShow('reportPop2');
       	
        }
        
     	function reportLast(){
       	 location.href = "${contextPath}/admin/report/bList"
        }

    </script>
</body>

</html>