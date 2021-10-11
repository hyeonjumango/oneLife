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
    <title>oneLife 관리자 - 메인</title>
    
    <%-- css/js 파일 --%>
    <jsp:include page="/WEB-INF/views/admin/common/link.jsp"></jsp:include>
    
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
                    <div class="main_content">
                        <div class="item_box item_3st clearfix">
                            <div class="items">
                               <div class="item_head">
                                   <h2>금일 주요일정</h2>
                                   <a href="${contextPath}/main">더보기</a>
                               </div>
                               <div class="item_body">
                               		<c:choose>
                               			<c:when test="${weekList.size() > 0}">
                               				<%-- 목록이 있을시 --%>
                               				<c:forEach var="w" items="${weekList}">
		                               			<div class="item_list">
			                                      <a href="javascript:;">
			                                        <span class="type01">${w.scCateName}</span>
			                                        <p class="board_txt">${w.scTitle}</p>
			                                      </a>
			                                   </div>	
		                               		</c:forEach>
                               			</c:when>
                               			<c:otherwise>
                               				<%-- 목록이 없을시 --%>
                               				<div class="main_nodate">
		                                        <p>금일 계획된 주요일정이 없습니다.</p> 
		                                        <img src="${contextPath}/resources/admin/images/main_nodata.png" alt="nodate">
		                                    </div>
                               				
                               			</c:otherwise>
                               		
                               		</c:choose>
                               		
                               </div>
                            </div>
                            <div class="items">
                                <div class="item_head">
                                    <h2>공지사항</h2>
                                    <a href="${contextPath}/notice/list">더보기</a>
                                </div>
                                <div class="item_body">
                                	<c:choose>
                               			<c:when test="${noticeList.size() > 0}">
                               				<%-- 목록이 있을시 --%>
                               				<c:forEach var="n" items="${noticeList}">
		                                		<div class="item_list">
			                                        <a href="${contextPath}/notice/detail?n_no=${n.n_no}">
			                                            <p class="board_txt">${n.n_title}</p>
			                                            <p class="type02">
			                                                <span>${n.m_nick}</span>
			                                                ${n.enroll_date}
			                                            </p>
			                                        </a>
			                                     </div>
		                                	</c:forEach>
                               			</c:when>
                               			<c:otherwise>
                               				<%-- 목록이 없을시 --%>
                               				<div class="main_nodate">
		                                        <p>공지사항에 글이 존재하지 않습니다.</p> 
		                                        <img src="${contextPath}/resources/admin/images/main_nodata.png" alt="nodate">
		                                    </div>
                               			</c:otherwise>
                               		</c:choose>
                                	
                                </div>
                             </div>
                             <div class="items">
                                <div class="item_head">
                                    <h2>미답변 건의 현황</h2>
                                    <a href="${contextPath}/complaint/list">더보기</a>
                                </div>
                                <div class="item_body">
	                          	<c:choose>
                               			<c:when test="${comList.size() > 0}">
                               				<%-- 목록이 있을시 --%>
                               				<c:forEach var="c" items="${comList}">
		                                		<div class="item_list">
			                                        <a href="${contextPath}/complaint/detail?c_no=${c.c_no}">
			                                          <p class="board_tit clearfix">
			                                              <span class="lf">${c.r_nickName}</span>
			                                              <span class="lf">${c.r_dong}동 ${c.r_ho}호</span>
			                                              <span class="rg">${c.enroll_date}</span>
			                                          </p>
			                                          <p class="board_txt">${c.c_title}</p>
			                                        </a>
			                                     </div>
		                                	</c:forEach>
                               			</c:when>
                               			<c:otherwise>
                               				<%-- 목록이 없을시 --%>
                               				<div class="main_nodate">
		                                        <p>주민간 건의사항이 존재하지 않습니다.</p> 
		                                        <img src="${contextPath}/resources/admin/images/main_nodata.png" alt="nodate">
		                                    </div>
                               			</c:otherwise>
                               		</c:choose>
  
                                </div>
                             </div>
                        </div>

                        <div class="item_box">
                            <div class="items">
                               <div class="item_head">
                                   <h2>금일 부대시설 예약현황</h2>
                                   <a href="${contextPath}/admin/facil/list?allDay=on">더보기</a>
                               </div>
                               <div class="item_body">
                                    <div class="table_wrap">
                                        <table class="table">
                                            <colgroup>
	                                            <col width="60px">
                                                <col width="130px">
                                                <col width="8%">
                                                <col width="8%">
                                                <col width="8%">
                                                <col width="130px">
                                                <col width="200px">
                                                <col width="200px">
                                                <col width="15%">
                                                <col width="8%">
	                                        </colgroup>
                                            <caption class="ir_so">금일 부대시설 예약현황</caption>
                                            <thead>
		                                        <tr>
		                                            <th>No.</th>
		                                            <th>예약 시설</th>
		                                            <th>예약 타입(좌석)</th>
		                                            <th>아이디</th>
		                                            <th>이름</th>
		                                            <th>핸드폰 번호</th>
		                                            <th>예약 일자</th>
		                                            <th>예약 시작시간</th>
		                                            <th>예약 마감시간</th>
		                                            <th>예약 상태</th>
		                                        </tr>
	                                    </thead>
                                        </table>
                                        
                                        <div class="main_table">
                                        <c:choose>
                                         	<c:when test="${facilList.size() > 0}">
                                            <table class="table">
                                                <colgroup>
		                                            <col width="60px">
	                                                <col width="130px">
	                                                <col width="8%">
	                                                <col width="8%">
	                                                <col width="8%">
	                                                <col width="130px">
	                                                <col width="200px">
	                                                <col width="200px">
	                                                <col width="15%">
	                                                <col width="8%">
	                                            </colgroup>
                                                <tbody>
                                                	
                                              			<c:forEach var="f" items="${facilList}">
                                                		<tr>
		                                                    <td>${f.fcNo}</td>
				                                            <td>${f.fcName}</td>
				                                            <td>${f.fcSeatType} <c:if test="${f.fcName eq '독서실'}">(${f.fcSeatNo})</c:if></td>
				                                            <td>${f.uId}</td>
				                                            <td>${f.rName}</td>
				                                            <td>${f.uPhone}</td>
				                                            <td>
				                                            	<fmt:formatDate value="${f.faDate}" pattern="yyyy년 MM월 dd일 HH시mm분"/>
				                                            </td>
				                                            <td>
				                                            	<fmt:formatDate value="${f.fcStart}" pattern="yyyy년 MM월 dd일 HH시mm분"/>
				                                            </td>
				                                            <td>
				                                            	<fmt:formatDate value="${f.fcEnd}" pattern="yyyy년 MM월 dd일 HH시mm분"/>
				                                            </td>
				                                            <td>
				                                            	<c:choose>
				                                            		<c:when test="${f.fcStatus eq '예약취소'}">
				                                            			<p class="status_after">예약취소</p>
				                                            		</c:when>
				                                            		<c:when test="${f.fcStatus eq '사용전'}">
				                                            			<p class="status_before">사용전</p>
				                                            		</c:when>
				                                            		<c:when test="${f.fcStatus eq '사용중'}">
				                                            			<p class="status_ing">사용중</p>
				                                            		</c:when>
				                                            		<c:when test="${f.fcStatus eq '사용완료'}">
				                                            			<p class="status_after">사용완료</p>
				                                            		</c:when>
				                                            	</c:choose>
				                                            </td>
	                                                    </tr>
                                                	</c:forEach>
                                                </tbody>
                                            </table>
                                              </c:when>
	                                        <c:otherwise>
	                                        	<div class="main_table_nodate">
                                                <p>금일 부대시설 예약내역이 존재하지 않습니다.</p>
                                                <img src="${contextPath}/resources/admin/images/main_nodata.png" alt="nodate">
                                            </div>
	                                        </c:otherwise>
										</c:choose>
                                        </div>
                                      
                                    </div>
                               </div>
                            </div>
                        </div>

                        <div class="item_box">
                            <div class="items">
                               <div class="item_head">
                                   <h2>금일 방문차량 예약현황</h2>
                                   <a href="${contextPath}/admin/visit/list">더보기</a>
                               </div>
                               <div class="item_body">
                                    <div class="table_wrap">
                                        <table class="table">
                                           <colgroup>
												<col width="60px">
												<col width="auto">
												<col width="auto">
												<col width="auto">
												<col width="auto">
												<col width="auto">
												<col width="auto">
												<col width="auto">
											</colgroup>
                                            <caption class="ir_so">금일 부대시설 예약현황</caption>
                                            <thead>
                                                <tr>
                                                    <th>No.</th>
                                                    <th>방문일</th>
                                                    <th>차량 번호</th>
                                                    <th>차주 연락처</th>
                                                    <th>방문 세대</th>
                                                    <th>방문 목적</th>
                                                    <th>신청인</th>
                                                    <th>상태</th>
                                                </tr>
                                            </thead>
                                        </table>
                                        <div class="main_table">
                                        	<c:choose>
                                        		<c:when test="${visitcarList.size() > 0}">
                                        			<table class="table">
		                                                <colgroup>
															<col width="60px">
															<col width="auto">
															<col width="auto">
															<col width="auto">
															<col width="auto">
															<col width="auto">
															<col width="auto">
															<col width="auto">
														</colgroup>
		                                                <tbody>
		                                                	<c:forEach var="vl" items="${visitcarList}">
		                                                		<tr>
			                                                    	<td>${vl.VC_ID}</td>
			                                                    	<td>${vl.VC_DATE}</td>
			                                                    	<td>${vl.VC_NO}</td>
			                                                    	<td>${vl.VC_PHONE}</td>
			                                                    	<td>${vl.r_DONG}동 ${vl.r_HO}호</td>
			                                                    	<td>${vl.VC_PURPOSE}</td>
			                                                    	<td>${vl.r_NAME}</td>
			                                                    	<td>
			                                                    		<c:choose>
			                                                    			<c:when test="${vl.VC_STATUS eq 'N'}">
			                                                    				<p class="status_before">접수</p>
			                                                    			</c:when>
			                                                    			<c:otherwise>
			                                                    				<p class="status_ing">완료</p>
			                                                    			</c:otherwise>
			                                                    		</c:choose>
			                                                    	</td>
			                                                    </tr>
		                                                	</c:forEach>
		                                                </tbody>
		                                            </table>
                                        		</c:when>
                                        		<c:otherwise>
                                        			<div class="main_table_nodate">
		                                                <p>금일 방문차량 예약내역이 존재하지 않습니다.</p>
		                                                <img src="${contextPath}/resources/admin/images/main_nodata.png" alt="nodate">
		                                            </div>
                                        		</c:otherwise>
                                        	</c:choose>
                                        </div>
                                    </div>
                               </div>
                            </div>
                        </div>
                    </div>
                </div>
            </secion>
        </div>
    </div>
</body>
</html>