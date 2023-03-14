<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>대결하기</h1>
	내 아이디 : ${ sessionScope.myUser.userName }
	<form action= "/springmbti/prepareBattle" method="GET">
		<input type="text" name="defenceUserId">
		<input type="submit" value="대결 신청!">
	</form>
</body>
</html>