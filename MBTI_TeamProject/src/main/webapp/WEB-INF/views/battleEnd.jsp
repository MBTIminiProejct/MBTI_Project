<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="resources/css/flex2.css"></link>
</head>
<body>
	<div class="temp-flex">
	<div>
		<h1>나</h1>
		이름 - ${ sessionScope.myUser.userName } <br>
		MBTI - ${ sessionScope.myUser.userMBTI } <br>
		내 유저 번호 - ${ sessionScope.myUser.userNum } <br>
		<h3>내 캐릭터 정보</h3>
		HP - ${ sessionScope.myCharacter.characterHP } <br>
		물리공격력 - ${ sessionScope.myCharacter.characterAD } <br>
		마법공격력 - ${ sessionScope.myCharacter.characterAP } <br>
		물리방어력 - ${ sessionScope.myCharacter.characterADDefence } <br>
		마법방어력 - ${ sessionScope.myCharacter.characterAPDefence } <br>
	   	속도 - ${ sessionScope.myCharacter.characterSpeed } <br>
		명중률 - ${ sessionScope.myCharacter.characterHitRate } <br>
		회피율 - ${ sessionScope.myCharacter.characterAvoidanceRate } <br>
		크리티컬 확률 - ${ sessionScope.myCharacter.characterCritical } <br>
		기본추가공격력 - ${ sessionScope.myCharacter.characterAdditionalDmg } <br>
	</div>
	<div>
		<h1>상대</h1>
		상대 이름 - ${ sessionScope.battleUser.userName } <br>
		상대 MBTI - ${ sessionScope.battleUser.userMBTI } <br>
		상대 유저 번호 - ${ sessionScope.battleUser.userNum } <br>
		<h3>상대 캐릭터 정보</h3>
		HP - ${ sessionScope.battleCharacter.characterHP } <br>
		물리공격력 - ${ sessionScope.battleCharacter.characterAD } <br>
		마법공격력 - ${ sessionScope.battleCharacter.characterAP } <br>
		물리방어력 - ${ sessionScope.battleCharacter.characterADDefence } <br>
		마법방어력 - ${ sessionScope.battleCharacter.characterAPDefence } <br>
	   	속도 - ${ sessionScope.battleCharacter.characterSpeed } <br>
		명중률 - ${ sessionScope.battleCharacter.characterHitRate } <br>
		회피율 - ${ sessionScope.battleCharacter.characterAvoidanceRate } <br>
		크리티컬 확률 - ${ sessionScope.battleCharacter.characterCritical } <br>
		기본추가공격력 - ${ sessionScope.battleCharacter.characterAdditionalDmg } <br>
	</div>
	</div>
	
	
	<br><br>
	<form action="/springmbti/battle" method="get">
		장소를 고르세요
		<select name="battleField">
			<option value="0">없음</option>
			<option value="1">놀이동산</option>
			<option value="2">계획없이 떠나게된 여행</option>
			<option value="3">도서관</option>
		</select>
		<input type="submit" value="싸우자!">
	</form>
	
</body>
</html>