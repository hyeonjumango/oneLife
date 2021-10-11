<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>단지 전경</title>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>
<style>

.viewWrap {
	width: 700px;
	margin: 0 auto;
	margin-top: 30px;
	box-sizing: border-box;
	font-family: "SpoqaHanSansKr";
	color: var(--font--color--);
}

.addressTable, .contactTable {
	margin: 0 auto;
	margin-bottom: 40px;
	width: 100%;
	border-top: 2px solid gray;
	text-align: center;
}

#map {
	margin-bottom: 20px;
}

.addressTable td, .contactTable td {
	padding: 5px;
	border-bottom: 1px solid lightgray;
}

.wrap {
	width: 700px;
	display: flex;
	flex-direction: column;
	margin: 0 auto;
}

.apt_img {
	width: 700px;
	height: 400px;
	object-fit: cover;
	margin: 0 auto;
	margin-bottom: 10px;
}

.greeting_title {
	background-color: var(--main-color--);
	width: 80%;
	border-radius: 30px;
	height: 10px;
	margin: 0 auto;
	text-align: center;
	display: table;
	padding: 8px;
}

.title {
	color: white;
	margin: 0;
	display: table-cell;
	vertical-align: middle;
	font-weight: 500;
	font-size: 18pt;
}

.titleEmp {
	font-weight: 700;
	color: white;
	margin: 0;
}

.greetingContent {
	padding-top: 20px;
	text-align: center;
	line-height: 1.5rem;
}

.contentEmp {
	color: var(--main-color--);
	font-weight: 700;
}

/* ----view---- */
.view_table {
	display: flex;
	justify-content: center;
	margin-top: 15px;
	margin-bottom: 35px;
}

.view_table>table {
	border-bottom: 2px solid var(--main-color--);
	border-top: 2px solid var(--main-color--);
}

.table_left {
	width: 30%;
	font-weight: 700;
}

.table_right {
	width: 70%;
	margin-left: 20px;
}

td {
	border-top: 1px solid lightgray;
	padding-top: 5px;
	padding-bottom: 5px;
}

.no_line {
	border-top: 0;
}

.green {
	width: 100%;
}

.viewWrap h3 {
	font-size: 25px;
	font-weight: 700;
}

.viewWrap li {
	text-align: left;
}

.view_table {
	color : 
}

.intro_info {
	margin-top: 0;
	margin-bottom: 20px;
}

.greenDiv {
	object-fit: cover;
}

.green_li li::before {
	content: "\2022";
	/* Add content: \2022 is the CSS Code/unicode for a bullet */
	color: var(--main-color--); /* Change the color */
	font-weight: bold; /* If you want it to be bold */
	display: inline-block;
	/* Needed to add space between the bullet and the text */
	width: 1em; /* Also needed for space (tweak if needed) */
}

.green_li {
	margin-top: 30px;
	margin-bottom: 50px;
	border-top: 2px solid var(--main-color--);
	border-bottom: 2px solid var(--main-color--);
}

.green_li span {
	font-size: 23px;
	color: var(--main-color--);
}

.green_li h3 {
	margin-top: 5px;
	margin-bottom: 10px;
	font-size: 18px;
}

.green_li div {
	margin-top: 25px;
	margin-bottom: 25px;
}
.green_li li {
	margin-top: 5px;
	margin-bottom: 5px;
	font-size : 15px;
}

.view_table td{
color : var(--font--color--);
}

.img1 {
width : 100%;
}
</style>
</head>
<body>
	<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>

	<div class="viewWrap">
		<h3 class="intro_info">단지 정보</h3>
		<img class="img1" src="/oneLife/resources/user/images/단지 전경.png" alt="단지 전경" />
		<div class="view_table">
			<table class="table_left">
				<tr>
					<td class="no_line">&nbsp&nbsp&nbsp총 세대수</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp총 동수</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp준공년월</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp건설사명</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp최저/최고층</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp세대당주차대수</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp난방방식</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp난방연료</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp건폐율</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp용적률</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp면적</td>
				</tr>
			</table>
			<table class="table_right">
				<tr>
					<td class="no_line">&nbsp&nbsp&nbsp200세대</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp6개동</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp2021년 10월</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp롯데건설(주)</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp18층/21층</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp1.09대</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp개별난방</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp도시가스</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp499%</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp59%</td>
				</tr>
				<tr>
					<td>&nbsp&nbsp&nbsp84Bm², 84B-1m², 85Am², 85A-1m²</td>
				</tr>
			</table>
		</div>
		<div class="greenDiv">
			<h3 class="intro_info">단지 전경</h3>
			<img class="green" src="/oneLife/resources/user/images/친환경 녹색단지.png"
				alt="" />
			<div class="green_li">
				<div class="green_first">
					<h3>
						<span>01</span> 중앙광장
					</h3>
					<li>폭 40m 이상의 중정형 중앙광장 계획</li>
					<li>이벤트 공간, 청량감 있는 수경시설, 휴게시설로 다양한 활동이 가능한 야외공간</li>
					<li>서측 테라스형 판매시설, 동측 마트/판매시설 등과 연계</li>
				</div>
				<div class="green_second">
					<h3>
						<span>02</span> 옥상정원
					</h3>
					<li>지상부 및 옥상에 각각 4천여m²의 녹지 조성</li>
					<li>판매시설동 상부(지상 4층)에 옥상정원 조성</li>
					<li>아이들의 놀이터와 단지 안팎을 조망하며 산책할 수 있는 입주민 전용 녹지공간</li>
				</div>
				<div class="green_third">
					<h3>
						<span>03</span> 공개공지
					</h3>
					<li>단지 남측 2개소에 휴게공간이 있는 공개공지 조성</li>
					<li>주 출입구 양측(남서측 모서리, 남동측 모서리) 휴게시설 및 조경화 함께 쾌적한 도시공간 형성</li>
				</div>
				<div class="green_fourth">
					<h3>
						<span>04</span> 휴게시설 및 놀이시설
					</h3>
					<li>아파트 최저층에 필로티공간 설계(일부)</li>
					<li>어린이 놀이터 4개소 조성</li>
				</div>
			</div>
		</div>
	</div>
	<%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>

</body>
</html>
