<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<footer id="footer">
            <div class="footer">
                <ul>
                    <li>(주)KH아파트</li>
                    <li>대표 우별림</li>
                    <li>서울 강남구 테헤란로10길 9, 그랑프리빌딩 7층 L</li>
                    <li>FAX 02-6008-6879</li>
                    <li class="line">TEL 1600-312</li>
                </ul>
                <ul>
                    <li><a href="javascript:;">이용약관</a></li>
                    <li><a href="javascript:;">개인정보 이용약관</a></li>
                    <li>사업자등록번호 104-86-59942 통신판매업신고번호 2021-부산해운대-0753</li>
                    <li>Copyright KHApt inc. All right reserved</li>
                </ul>
            </div>
        </footer>
        
   <%-- 관리자 페이지 이동버튼 --%>
    <c:if test="${!empty loginManager}">
       	<style>
        	.page_wrap{position:fixed; right:20px; bottom:20px;}
        	.page_wrap a{display:block; width:60px; height:60px; border-radius:50%; line-height:60px; background:#fff; box-sizing:border-box; text-align:center; border:1px solid #333; transition: all .3s;}
        	.page_wrap a:hover{background:#3c90f2; color:#fff; border-color:#3c90f2;}
        </style>
        <div class="page_wrap">
        	<a href="${contextPath}/admin/">
        		관리자
        	</a>
        </div>
      </c:if>
