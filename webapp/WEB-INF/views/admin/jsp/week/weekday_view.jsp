<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1"/>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,maximum-scale=1.0,minimum-scale=1.0,target-densitydpi=medium-dpi" />
    <title>주요일정 관리 추가</title>

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
                    	<c:choose>
                    		<c:when test="${param.type == 'insert'}">
                    			<h2 class="sub_tit">주요일정 추가</h2>
                    		</c:when>
                    		<c:otherwise>
                    			<h2 class="sub_tit">주요일정 변경</h2>
                    		</c:otherwise>
                    	</c:choose>
                    	
                    	
                    	
                        
                        <form name="weekFrm">
                        	<input type="hidden" name="scNo" value="${weekOne.scNo}"/>
                        	<input type="hidden" name="type" value="${param.type}"/>
	                        <div class="view_items">
	                            <dl class="items">
	                                <dt>공개 여부</dt>
	                                <dd>
	                                <c:choose>
			                    		<c:when test="${param.type == 'insert'}">
			                    		<div class="radio">
	                                        <input type="radio" name="sc_status" id="sc_y" value="Y" checked>
	                                        <label for="sc_y">공개</label>
	                                    </div>
	                                    <div class="radio">
	                                        <input type="radio" name="sc_status" id="sc_n" value="N">
	                                        <label for="sc_n">비공개</label>
	                                    </div>
			                    		</c:when>
			                    		<c:otherwise>
			                    		<div class="radio">
	                                        <input type="radio" name="sc_status" id="sc_y" value="Y" <c:if test="${weekOne.scStatus eq 'Y'.charAt(0)}">checked</c:if>>
	                                        <label for="sc_y">공개</label>
	                                    </div>
	                                    <div class="radio">
	                                        <input type="radio" name="sc_status" id="sc_n" value="N" <c:if test="${weekOne.scStatus eq 'N'.charAt(0)}">checked</c:if>>
	                                        <label for="sc_n">비공개</label>
	                                    </div>
			                    		</c:otherwise>
			                    	</c:choose>
	                                </dd>
	                            </dl>
	
	                            <dl class="items">
	                                <dt>일정 예정일</dt>
	                                <dd>    
	                                    <div class="calendar">
	                                    	<c:set var="now" value="<%=new java.util.Date()%>" />
											<c:set var="sysDate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></c:set>
	                                    	<c:choose>
					                    		<c:when test="${param.type == 'insert'}">
					                    			<input type="text" name="searchDay" class="cal_today" value="${sysDate}" readonly>
					                    		</c:when>
					                    		<c:otherwise>
					                    			<input type="text" name="searchDay" class="cal_today" value="${weekOne.scOpenDate}" readonly>
					                    		</c:otherwise>
					                    	</c:choose>
	                                    </div>
	                                </dd>
	                            </dl>
	
	                            <dl class="items">
	                                <dt>일정 타입</dt>
	                                <dd>
	                                   <div class="select">
	                                   <select name="scType" id="">
	                                   	<c:choose>
				                    		<c:when test="${param.type == 'insert'}">
				                    		   <option value="SC_CODE1">공동생활</option>
	                                           <option value="SC_CODE2">주민투표</option>
				                    		</c:when>
				                    		<c:otherwise>
				                    		   <option value="SC_CODE1" <c:if test="${weekOne.scCateCode eq 'SC_CODE1'}">selected</c:if>>공동생활</option>
	                                           <option value="SC_CODE2" <c:if test="${weekOne.scCateCode eq 'SC_CODE2'}">selected</c:if>>주민투표</option>
				                    		</c:otherwise>
				                    	</c:choose>
				                    	</select>
	                                   </div>
	                                </dd>
	                            </dl>
	
	                            <dl class="items">
	                                <dt>일정 제목</dt>
	                                <dd>
	                                   <div class="input">
		                                   	<c:choose>
					                    		<c:when test="${param.type == 'insert'}">
					                    			<input type="text" name="scTitle">
					                    		</c:when>
					                    		<c:otherwise>
					                    			<input type="text" name="scTitle" value="${weekOne.scTitle}">
					                    		</c:otherwise>
					                    	</c:choose>
	                                   </div>
	                                </dd>
	                            </dl>
	
	                            <dl class="items">
	                                <dt>일정 내용</dt>
	                                <dd>
	                                   <div class="textarea">
	                                   	  	<c:choose>
					                    		<c:when test="${param.type == 'insert'}">
					                    			<textarea name="scContent" id="" cols="30" rows="10">${weekOne.scContent}</textarea>
					                    		</c:when>
					                    		<c:otherwise>
					                    			<textarea name="scContent" id="" cols="30" rows="10">${weekOne.scContent}</textarea>
					                    		</c:otherwise>
					                    	</c:choose>
	                                   </div>
	                                </dd>
	                            </dl>
	                        </div>
                        </form>

                        <div class="view_btn">
                        	<c:choose>
	                    		<c:when test="${param.type == 'insert'}">
	                    			<a href="javascript:insertWeek();" class="ok">등록하기</a>
	                    		</c:when>
	                    		<c:otherwise>
	                    			<a href="javascript:insertWeek();" class="ok">변경하기</a>
	                    		</c:otherwise>
	                    	</c:choose>
                            <a href="javascript:cancleWeek();">등록취소</a>
                        </div>
                    </div>
                </div>
            </section>
        </div>
    </div>
    <script>
    	let frm = document.forms.weekFrm;
    	function insertWeek(){
    		frm.action = "${contextPath}/admin/week/view";
    		frm.method = "post";
    		frm.submit();
    	}
    	
    	function cancleWeek(){
    		if(confirm("목록 페이지로 이동하시겠습니까?")){
    			location.href = "${contextPath}/admin/week/list";
    		}
    	}
    	
    	
    </script>

</body>

</html>