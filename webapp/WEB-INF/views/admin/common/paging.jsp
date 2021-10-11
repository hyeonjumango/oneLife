<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:if test="${param.listSize > 0}">
	<div class="paging_wrap">
		<c:choose>
			<c:when test="${param.piPage <= 1}">
				<a href="javascript:;" class="btn_prev"></a>
			</c:when>
			<c:otherwise>
				<a
					href="${contextPath}${menuUrl}?page=${param.piPage - 1}${param.search}"
					class="btn_prev"></a>
			</c:otherwise>
		</c:choose>

		<c:forEach var="p" begin="${param.startPage}" end="${param.endPage}">
			<a href="${contextPath}${menuUrl}?page=${p}${param.search}&allDay=${param.allDay}"
				class="btn_num <c:if test="${param.piPage == p}">on</c:if>">${p}</a>
		</c:forEach>
		<c:choose>
			<c:when test="${param.piPage == param.maxPage}">
			<a href="javascript:;" class="btn_next"></a>
			</c:when>
			<c:otherwise>
				<a
					href="${contextPath}${menuUrl}?page=${param.piPage + 1}${param.search}"
					class="btn_next"></a>
			</c:otherwise>
		</c:choose>
	</div>
</c:if>