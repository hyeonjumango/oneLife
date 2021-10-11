<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="user.member.model.vo.Member" %>
<%@page import="admin.manager.model.vo.Manager"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<% 
	// session 객체에 담긴 loginUser 정보를 변수에 담아두기
	Member loginUser = (Member)session.getAttribute("loginUser");
	Manager loginManager = (Manager)session.getAttribute("loginManager");
%>  

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>메인페이지</title>
    <link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="resources/calendar-04/css/style.css">
	
	<%-- 공통 css/js --%>
    <jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>
    <%
	if (session.getAttribute("msg") != null) {
	%>
	<script>
		alert('<%=session.getAttribute("msg")%>');
	</script>
	<%
		session.removeAttribute("msg");
	}
	%>
	<style>
	.cal_list li {
	display : inline-block;
	overflow: hidden; white-space: nowrap; -ms-text-overflow: ellipsis; -o-text-overflow: ellipsis;text-overflow: ellipsis;
	}

	.main_container .items .main_calendar .cal_list {
	margin-top : 0;
	}
	
	.cal_status {
	display : inline-block;
	}
	
	.todayBtn {
	width: 50px; 
	height: 36px; 
	line-height: 36px; background:#72C2E7; 
	color:#fff; text-align: center; border-radius:10px; font-size: 16px; 
	box-shadow: 0 1px 3px rgba(63, 63, 68, 0.15);
	
	}
	
	.dayDiv {
	display : flex;
	justify-content : space-between;
	}
	
	li {
	text-align : left;
	}
	
	.cal_list > ul > li:first-of-type {
	color : #72C2E7;
	font-weight : bold;
	}
	
	
	.noSchedule {
	margin-top : 15px;
	display : flex;
	justify-content : center;
	}
	.noSchedule > img {
	width : 25px;
	display : inline-block;
	ma
	}
	
	.noSchedule > p {
	font-size : 16px;
	font-weight : bold;
	color : gray;
	margin-left : 8px;
	display : inline-block;
	}
	
	.noScheduleLi {
	width : 100%;
	}
	</style>
</head>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath }" scope="application"/>
<body class="main">
   <div id="wrap">
        <header id="header">
            <div class="header">
                <h1 class=logo>
                    <a href="${contextPath }/main"><span class="ir_so">로고</span></a>
                </h1>
	                <div class="top_wrap">
				        <% if(loginManager != null) { %>
				        	<%-- 관리자 계정으로 로그인했을시 --%>
				        	<p class="login_status"><span>${loginManager.mNick}</span>님 반갑습니다.</p>
				            <ul>
				                <li><a href="<%= request.getContextPath() %>/userLogout">로그아웃</a></li>
				            </ul>
				        <% } else if(loginUser != null) { %>
				        	<%-- 사용자 계정으로 로그인했을시 --%>
				            <p class="login_status">${ loginUser.getR_DONG() }동 ${ loginUser.getR_HO() }호 <span>${ loginUser.getR_NAME() }</span>님 반갑습니다.</p>
				            <ul>
				                <li><a href="<%= request.getContextPath() %>/userModify">회원정보</a></li>
				                <li><a href="<%= request.getContextPath() %>/userLogout">로그아웃</a></li>
				            </ul>
				         <% } else {
				        	 request.getSession().removeAttribute("loginUser");
				        	 request.getSession().removeAttribute("loginManager");
				          %> 
				        	 <script>
								alert('로그인 먼저 진행해주세요! 로그인페이지로 이동합니다.');
								location.href = '${contextPath}/';
							</script>
				         <%} %>
				  </div>
            </div>
        </header>
        <section id="main_container">
            <div class="main_container">
                <div class="items">
                    <h2>우리 아파트 이야기</h2>
                    <ul class="myApt">
                        <li><a href="${contextPath}/vote/list">투표</a></li>
                        <li><a href="${contextPath}/visitCar">방문차량</a></li>
                        <li><a href="${contextPath}/complaint/list">아파트 민원</a></li>
                        <li><a href="${contextPath}/board/list">도란도란</a></li>
                        <li><a href="${contextPath}/srIntro">부대시설 예약</a></li>
                        <li><a href="${contextPath}/greeting">아파트 소개</a></li>
                    </ul>
                </div>
                <div class="items"> 
                    <h2><a href="${contextPath}/notice/list">공지사항</a></h2>
                    <div class="main_notice_wrap">
                        <ul class="main_notice_list notice_header">
                            <li class="title">제목</li>
                            <li class="writer">작성자</li>
                            <li class="date">작성일</li>
                        </ul>
                        <div class="main_scroll">
                          <c:forEach var="n" items="${noticeList}">
                          <ul class="main_notice_list notice_body" onclick="detailView(${ n.n_no })">
                              <li class="title">${ n.n_title }</li>
                              <li class="writer">${ n.m_nick }</li>
                              <li class="date">${ n.modify_date }</li>
                          </ul>
                          </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="items">
                    <h2>우리 아파트 주요 일정</h2>
                    <div class="main_calendar" id="main_cal">
                        <!-- <div class="cal_head">
                            <a href="javascript:;">&lt;</a>
                            <p>2021.08</p>
                            <a href="javascript:;">&gt;</a>
                        </div> -->
                        <div class="cal_body">
                            <!-- <ul class="cal_week">
                                <li>일</li>
                                <li>월</li>
                                <li>화</li>
                                <li>수</li>
                                <li>목</li>
                                <li>금</li>
                                <li>토</li>
                            </ul>
                            <ul class="cal_day">
                              <li><a href="javascript:;">1</a></li>
                              <li><a href="javascript:;">2</a></li>
                              <li><a href="javascript:;">3</a></li>
                              <li><a href="javascript:;">4</a></li>
                              <li><a href="javascript:;">5</a></li>
                              <li class="on"><a href="javascript:;">6</a></li>
                              <li><a href="javascript:;">7</a></li>
                              <li><a href="javascript:;">8</a></li>
                              <li><a href="javascript:;">9</a></li>
                              <li><a href="javascript:;">10</a></li>
                              <li><a href="javascript:;">11</a></li>
                              <li><a href="javascript:;">12</a></li>
                              <li><a href="javascript:;">13</a></li>
                              <li><a href="javascript:;">14</a></li>
                              <li><a href="javascript:;">15</a></li>
                              <li><a href="javascript:;">16</a></li>
                              <li><a href="javascript:;">17</a></li>
                              <li><a href="javascript:;">18</a></li>
                              <li><a href="javascript:;">19</a></li>
                              <li><a href="javascript:;">20</a></li>
                              <li><a href="javascript:;">21</a></li>
                              <li><a href="javascript:;">22</a></li>
                              <li><a href="javascript:;">23</a></li>
                              <li><a href="javascript:;">24</a></li>
                              <li><a href="javascript:;">25</a></li>
                              <li><a href="javascript:;">26</a></li>
                              <li><a href="javascript:;">27</a></li>
                              <li><a href="javascript:;">28</a></li>
                              <li><a href="javascript:;">29</a></li>
                              <li><a href="javascript:;">30</a></li>
                              <li><a href="javascript:;">31</a></li>
                              <li class="hide"><a href="javascript:;">1</a></li>
                              <li class="hide"><a href="javascript:;">2</a></li>
                              <li class="hide"><a href="javascript:;">3</a></li>
                              <li class="hide"><a href="javascript:;">4</a></li>
                            </ul> -->
							<section class="ftco-section">
										    <div class="calendar-container">
										      <div class="calendar"> 
										        <div class="year-header"> 
										          <span class="left-button fa fa-chevron-left" id="prev"> </span> 
										          <span class="year" id="label"></span> 
										          <span class="right-button fa fa-chevron-right" id="next"> </span>
										        </div> 
										        <table class="months-table w-100"> 
										          <tbody>
										            <tr class="months-row">
										              <td class="month">1월</td> 
										              <td class="month">2월</td> 
										              <td class="month">3월</td> 
										              <td class="month">4월</td> 
										              <td class="month">5월</td> 
										              <td class="month">6월</td> 
										              <td class="month">7월</td>
										              <td class="month">8월</td> 
										              <td class="month">9월</td> 
										              <td class="month">10월</td>          
										              <td class="month">11월</td>
										              <td class="month">12월</td>
										            </tr>
										          </tbody>
										        </table> 
										        
										        <table class="days-table w-100"> 
										          <td class="day">일</td> 
										          <td class="day">월</td> 
										          <td class="day">화</td> 
										          <td class="day">수</td> 
										          <td class="day">목</td> 
										          <td class="day">금</td> 
										          <td class="day">토</td>
										        </table> 
										        <div class="frame"> 
										          <table class="dates-table w-100"> 
									              <tbody class="tbody">             
									              </tbody> 
										          </table>
										        </div> 
										      </div>
									</div>
							</section>
							<div class="dayDiv">
                            <p class="cal_status">
                                08.07 토요일
                            </p>
                            <button class="todayBtn" type="button" onclick="scToday()">오늘</button>
							</div>
                        </div>
						
                        <ul class="cal_list">
                        		<li class="noScheduleLi"><div class="noSchedule"><img src="/oneLife/resources/admin/images/ico_cal.png"><p>오늘 예정된 일정은 없습니다.</p></div></li>
                        </ul>
                    </div>
                </div>
            </div>
        </section>
   </div>
   
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
      
      <script src="resources/calendar-04/js/popper.js"></script>
	  <script src="resources/calendar-04/js/bootstrap.min.js"></script>
	  <script src="resources/calendar-04/js/main.js"></script>
	  <script>
	  function scToday() {
		  let today = new Date();
		  let year = today.getFullYear();
		  let month = today.getMonth();
		  let day = today.getDate();
		  
		  $('#label').text(year);
		  $(".active-date").removeClass("active-date");
		  $(".active-month").removeClass("active-month");
		  
		  init_calendar(today);
		  
		  $(".month").each(function(){
			  let a = $(this).text();
			  let num = month + 1;
				if (a == num + '월') {
					$(this).addClass("active-month");
				}
		  });
		  
          show_events(year, months[month], day);
	  }
  
  function detailView(n_no){
		location.href='${contextPath}/notice/detail?n_no='+n_no;
	}

  </script>
  
</body>
</html>