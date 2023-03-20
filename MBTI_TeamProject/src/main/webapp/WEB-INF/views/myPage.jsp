<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html>
<meta charset="UTF-8">

<head>
    <meta charset="UTF-8">
    <title>Title</title>
    
<script
  src="https://code.jquery.com/jquery-2.2.4.min.js"
  integrity="sha256-BbhdlvQf/xTY9gja0Dq3HiwQF8LaCRTXxZKRutelT44="
  crossorigin="anonymous"></script>
<!-- <script src="../js/mypage.js"></script> -->
<!-- <script>
   function popuptest(){
       var url = "http://localhost:8080/springmbti/resources/searchBattleUser";
       var name = "popup test";
       var option = "width = 500, height = 500, top = 100, left = 200, location = no"
       window.open(url, name, option);
   }
</script> -->

<script>
   function myFunc1(){
 
      $.ajax({
         url: '/springmbti/mypage/character',
         method: "DELETE",
         data : {
           num : 1 
         },
         success: function(){
            alert("성공성공!")
         },
         error: function(){
           
            alert("무언가 이상해요!")
         }
      })
   }
 
</script>

</head>
<body>

    <div><img src="${ myUser.userProfile }" width="50" height="50">${ myUser.userName }접속중</div>
    <div class="box1" style="font-family:verdana; text-align: left"> 
      <h2>나만의  MBTI 캐릭터 만들기</h2> <h3 style="font-family:courier">모두 덤벼라!</h3>
      <h1>MBTI 미니 대전</h1>
   </div>
    <!-- 개인 캐릭터 넣기, 없으면 물음표 , 있으면 캐릭터로 이미지 띄우고 -->
    
    <br><br>
    <!-- user email이 primary로 DB에 저장되어 있으면  토큰, 이메일, 닉네임, 프로필 이미지 가져오는데 
       db에 없으면 저장이 되는데 , 여기서 확인을 하고  -->
    
    <c:choose>
      <c:when test="${ isExist }">
          <!-- <button onclick="location.href='/springmbti/mypage/deleteCharacter'">캐릭터 삭제하기</button> -->
             <button onclick="myFunc1()">내 캐릭터 삭제하기</button>
            <a href="/springmbti/mypage/searchBattleUser"><button>대결하기</button></a>
            <form action="/springmbti/survey/surveyone" method="post"
                  disabled="disabled">
                <input type="submit" value="설문조사">
            </form>
            
      </c:when>
      
      <c:otherwise>
          <form action="/springmbti/survey/surveyone" method="post">
                <input type="submit" value="설문조사">
            </form>
            <button disabled="disabled">내 캐릭터 삭제하기</button>
            <button disabled="disabled">대결하기</button>
      </c:otherwise>
      
   </c:choose>
   
   
   
    <br><br>
	<form action="/springmbti/mypage" method="get">
		<input type="submit" value="마이페이지">
	</form>

   <!-- 모달넣어야 하고  -->
   
    <br><br>
    <a href="/springmbti/test">결과 페이지로 이동(test용)</a>
    <br><br>
   <form action="/springmbti/mypage/user">  
    <button>회원탈퇴 (test)</button></form> 
    <!-- 탈퇴 모달 넣기 -->
    <form action="/springmbti/logout">  
    <button>로그아웃</button></form>
     <a href="https://kauth.kakao.com/oauth/logout?client_id=26c4c3e8460d88a695130b78307910f4&logout_redirect_uri=http://localhost:8080/springmbti/resources/main.html">로그아웃</a>
</body>
</html>