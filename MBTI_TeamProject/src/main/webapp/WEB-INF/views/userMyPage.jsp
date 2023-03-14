<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>마이 페이지</h1>
	이름 - ${ sessionScope.myUser.userName } <br>
	이메일 - ${sessionScope.myUser.userEmail } <br>
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
	
	<h3>대결 하기</h3>
	<form action="/springmbti/mypage/battleuser">
		대결상대 유저번호 : <input type="text" name="battleUserNum">
		<input type="submit" value="대결신청">
	</form>
	
	<h1>랭킹</h1>
	<table>
		<thead>
			<tr>
				<th>순위</th>
				<th>이름</th>
				<th>MBTI</th>
				<th>Point</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<th>1</th>
				<th>test</th>
				<th>entp</th>
				<th>100</th>
			</tr>
		</tbody>
	</table>
</body>
</html>