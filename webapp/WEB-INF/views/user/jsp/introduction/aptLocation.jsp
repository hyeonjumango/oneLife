<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>아파트 정보</title>
    <script
      type="text/javascript"
      src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=ryntvaz6ho"
    ></script>
     <%-- 공통css/js --%>
<jsp:include page="/WEB-INF/views/user/common/link.jsp"></jsp:include>
    <style type="text/css">


* {
  box-sizing: border-box;
  font-family: "SpoqaHanSansKr";
  color: var(--font--color--);
}

.addressTable,
.contactTable {
  margin: 0 auto;
  width: 100%;
  border-top: 2px solid gray;
  text-align: center;
}

.intro_info {
  margin-top: 0;
  margin-bottom: 20px;
  font-size: 25px;
	font-weight: 700;
	width : 100%;
	}

#map {
  margin-bottom: 20px;
  z-index : -10000;
}

.addressTable td,
.contactTable td {
  padding: 5px;
  border-bottom: 1px solid lightgray;
}

.wrap {
  width: 700px;
  display: flex;
  flex-direction: column;
  margin: 0 auto;
  min-height: calc(100vh - 284px);
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

    </style>
  </head>
  <body>
 
<%-- 공통 menuBar.jsp --%>
	<jsp:include page="/WEB-INF/views/user/common/menuBar.jsp"></jsp:include>
    <div class="wrap">
      <h3 class="intro_info">위치정보</h3>
      <div id="map" style="width: 700px; height: 300px"></div>

      <table class="addressTable">
        <colgroup>
          <col width="20%" />
          <col width="80%" />
        </colgroup>
        <tbody>
          <tr>
            <td><b>지번주소</b></td>
            <td>서울 강남구 역삼동 823-42</td>
          </tr>
          <tr>
            <td><b>도로명주소</b></td>
            <td>서울 강남구 테헤란로10길 9</td>
          </tr>
        </tbody>
      </table>

      <h3 class="intro_info">연락처정보</h3>
      <table class="contactTable">
        <colgroup>
          <col width="20%" />
          <col width="80%" />
        </colgroup>
        <tbody>
          <tr>
            <td><b>관리사무소</b></td>
            <td>02-805-6876</td>
          </tr>
          <tr>
            <td><b>방재실</b></td>
            <td>02-806-6876</td>
          </tr>
          <tr>
            <td><b>중앙초소</b></td>
            <td>02-808-6876</td>
          </tr>
        </tbody>
      </table>
    </div>

    <script>
      var mapOptions = {
        center: new naver.maps.LatLng(37.4985127012389, 127.03260006350932),
        zoom: 19,
      };

      var map = new naver.maps.Map("map", mapOptions);

      var markerOptions = {
        position: new naver.maps.LatLng(37.4985127012389, 127.03260006350932),
        map: map,
      };

      var marker = new naver.maps.Marker(markerOptions);
    </script>
    <%-- 공통 footer --%>
	<jsp:include page="/WEB-INF/views/user/common/footer.jsp"></jsp:include>
  </body>
</html>
