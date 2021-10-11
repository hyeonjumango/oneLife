<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 메뉴바의 on클래스를위한 변수 --%>
<c:set var="menuUrl" value="${requestScope['javax.servlet.forward.servlet_path']}" scope="application"></c:set>
<c:set var="now" value="<%=new java.util.Date()%>" />
<c:set var="sysDate"><fmt:formatDate value="${now}" pattern="yyyy-MM-dd" /></c:set>

<nav id="nav">
    <div class="nav">
        <ul class="outer">
            <li>
                <a href="javascript:;">회원정보</a>
                <ul class="inner">
                    <li class="<c:if test="${fn:contains(menuUrl, '/admin/member/')}">on</c:if>" >
                        <a href="${contextPath}/admin/member/list">입주자 명부</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">부대시설</a>
                <ul class="inner">
                    <li class="<c:if test="${fn:contains(menuUrl, '/admin/facil/')}">on</c:if>" >
                        <a href="${contextPath}/admin/facil/list?allDay=on&facilDay=${sysDate}">부대시설 예약 관리</a>
                    </li>
                    <%--독서실, 멀티코트장 예약현황 윈도우팝업으로 --%>
                    <li>
                        <a href="javascript:facilPopup('library');">독서실 예약 현황</a>
                    </li>
                    <li>
                        <a href="javascript:facilPopup('multicourt');">멀티코트장 예약 현황</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">게시판</a>
                <ul class="inner">
                    <li class="<c:if test="${fn:contains(menuUrl, '/admin/week/')}">on</c:if>">
                        <a href="${contextPath}/admin/week/list?allDay=on">주요일정 관리</a>
                    </li>
                    <li class="<c:if test="${fn:contains(menuUrl, '/admin/report/')}">on</c:if>">
                        <a href="${contextPath}/admin/report/bList">신고내역 관리</a>
                    </li>
                </ul>
            </li>
            <li>
                <a href="javascript:;">차량관리</a>
                <ul class="inner">
                    <li class="<c:if test="${fn:contains(menuUrl, '/admin/in/')}">on</c:if>">
                        <a href="${contextPath}/admin/in/list">입주 차량 등록 현황</a>
                    </li>
                    <li class="<c:if test="${fn:contains(menuUrl, '/admin/visit/')}">on</c:if>">
                        <a href="${contextPath}/admin/visit/list">방문 차량 등록 현황</a>
                    </li>
                </ul>
            </li>   
            <li>
                <a href="javascript:;">관리자 정보</a>
                <ul class="inner">
                    <li class="<c:if test="${fn:contains(menuUrl, '/admin/manager/')}">on</c:if>">
                        <a href="${contextPath}/admin/manager/list">관리자 명부</a>
                    </li>
                </ul>
            </li>   
        </ul>
    </div>
</nav>

<%-- 팝업창 스크립트 --%>
<script>
	function facilPopup(url){
		
		let popWidth = 1280;
		let popHeight = 860;
		let popX = (window.screen.width - popWidth) / 2;
		let popY = (window.screen.height - popHeight) / 2;
		let options = 'width=' + popWidth +', height=' + popHeight + ', top=' + popY  + ', left=' + popX + ', resizable=no, scrollbars=no, location=no';
		window.open("${contextPath}/admin/facil/" + url + "?day=${sysDate}","facilPop",options);
	}
</script>

