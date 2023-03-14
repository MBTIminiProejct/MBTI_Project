<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<style>
label {
    padding-right: 10px;
    font-size: 1rem;
}


</style>
</head>
<body>
	<div class="box0" style="font-family:verdana; text-align: center"> 
      <h1>2/4</h1>
      <h3 style="font-family:courier">S or N</h3>
   </div>
   <div class="box1" style="font-family:verdana; text-align: center">
   <label for="file">S</label>

<progress id="file" max="100" value="50"> </progress>
   <br>
	<div class="box2" style="font-family:verdana; text-align: center">
	<h3>1. 사후세계에 대한 질문이 흥미롭다고 생각한다.</h3>
	<input type="radio" name="myCheckBox1" value="0">매우 아니다 
	<input type="radio" name="myCheckBox1" value="1">아니다
	<input type="radio" name="myCheckBox1" value="2">그렇다
	<input type="radio" name="myCheckBox1" value="3">매우 그렇다
	</div>
	<br>
	<div class="box3" style="font-family:verdana; text-align: center">
	<h3>2. 이론 중심의 토론에는 관심이 없으며 원론적인 이야기는 지루하다고 생각한다.</h3>
	<input type="radio" name="myCheckBox2" value="0">매우 아니다 
	<input type="radio" name="myCheckBox2" value="1">아니다
	<input type="radio" name="myCheckBox2" value="2">그렇다
	<input type="radio" name="myCheckBox2" value="3">매우 그렇다
	</div>
	<br>
	<div class="box4" style="font-family:verdana; text-align: center">
	<h3>3. 관심사가 너무 많아 다음에 어떤 일을 해야 할지 모를 때가 있다.</h3>
	<input type="radio" name="myCheckBox3" value="0">매우 아니다 
	<input type="radio" name="myCheckBox3" value="1">아니다
	<input type="radio" name="myCheckBox3" value="2">그렇다
	<input type="radio" name="myCheckBox3" value="3">매우 그렇다
	</div>
	<br>
	<div class="box5" style="font-family:verdana; text-align: center">
	<h3>4. 자신만큼 효율적이지 못한 사람을 보면 짜증이 난다.</h3>
	<input type="radio" name="myCheckBox4" value="0">매우 아니다 
	<input type="radio" name="myCheckBox4" value="1">아니다
	<input type="radio" name="myCheckBox4" value="2">그렇다
	<input type="radio" name="myCheckBox4" value="3">매우 그렇다
	</div>
	<br>
	<div class="box5" style="font-family:verdana; text-align: center">
	<h3>5. 철학적인 질문에 대해 깊게 생각하는 일은 시간 낭비라고 생각한다.</h3>
	<input type="radio" name="myCheckBox5" value="0">매우 아니다 
	<input type="radio" name="myCheckBox5" value="1">아니다
	<input type="radio" name="myCheckBox5" value="2">그렇다
	<input type="radio" name="myCheckBox5" value="3">매우 그렇다
	</div>
	<br>
	<div class="box5" style="font-family:verdana; text-align: center">
	<form action="surveythree" method="post">
		<input type="submit" value="제출">
	</form>
	</div>	
</body>
<script>
function surveyone1() {
	$.ajax({
		url : "surveytwo1",
		type : "POST",
		async : "false",
		dataType : "json",
		data : { myid: $("#myid").val(),
				 otherid: $("#otherid").val(),
				 bnum: $("#boardnum").val(),
				 likenum: $("#likenum").val()
				},
		success : function(data) {
			
			var values = Object.values(data);
			let liketr = $("<tr></tr>");
			
			if(data['ajaxresult'] == 7){
			console.log('좋아요 성공하셨습니다!');
			$('#likecount').text(data['likeresult']);
			} else if(data['ajaxresult'] == 4) {
				alert('당신이 작성한 글입니다!');
			} else {
				alert('이미 좋아요를 하셨습니다!');
			}
		},
		error: function(data) {
			alert('ajax 실패!');
		}
			
	});
}

function surveyone2() {
	$.ajax({
		url : "likeajax",
		type : "POST",
		async : "false",
		dataType : "json",
		data : { myid: $("#myid").val(),
				 otherid: $("#otherid").val(),
				 bnum: $("#boardnum").val(),
				 likenum: $("#likenum").val()
				},
		success : function(data) {
			
			var values = Object.values(data);
			let liketr = $("<tr></tr>");
			
			if(data['ajaxresult'] == 7){
			console.log('좋아요 성공하셨습니다!');
			$('#likecount').text(data['likeresult']);
			} else if(data['ajaxresult'] == 4) {
				alert('당신이 작성한 글입니다!');
			} else {
				alert('이미 좋아요를 하셨습니다!');
			}
		},
		error: function(data) {
			alert('ajax 실패!');
		}
			
	});
}

function surveyone3() {
	$.ajax({
		url : "likeajax",
		type : "POST",
		async : "false",
		dataType : "json",
		data : { myid: $("#myid").val(),
				 otherid: $("#otherid").val(),
				 bnum: $("#boardnum").val(),
				 likenum: $("#likenum").val()
				},
		success : function(data) {
			
			var values = Object.values(data);
			let liketr = $("<tr></tr>");
			
			if(data['ajaxresult'] == 7){
			console.log('좋아요 성공하셨습니다!');
			$('#likecount').text(data['likeresult']);
			} else if(data['ajaxresult'] == 4) {
				alert('당신이 작성한 글입니다!');
			} else {
				alert('이미 좋아요를 하셨습니다!');
			}
		},
		error: function(data) {
			alert('ajax 실패!');
		}
			
	});
}

function surveyone4() {
	$.ajax({
		url : "likeajax",
		type : "POST",
		async : "false",
		dataType : "json",
		data : { myid: $("#myid").val(),
				 otherid: $("#otherid").val(),
				 bnum: $("#boardnum").val(),
				 likenum: $("#likenum").val()
				},
		success : function(data) {
			
			var values = Object.values(data);
			let liketr = $("<tr></tr>");
			
			if(data['ajaxresult'] == 7){
			console.log('좋아요 성공하셨습니다!');
			$('#likecount').text(data['likeresult']);
			} else if(data['ajaxresult'] == 4) {
				alert('당신이 작성한 글입니다!');
			} else {
				alert('이미 좋아요를 하셨습니다!');
			}
		},
		error: function(data) {
			alert('ajax 실패!');
		}
			
	});
}

function surveyone5() {
	$.ajax({
		url : "likeajax",
		type : "POST",
		async : "false",
		dataType : "json",
		data : { myid: $("#myid").val(),
				 otherid: $("#otherid").val(),
				 bnum: $("#boardnum").val(),
				 likenum: $("#likenum").val()
				},
		success : function(data) {
			
			var values = Object.values(data);
			let liketr = $("<tr></tr>");
			
			if(data['ajaxresult'] == 7){
			console.log('좋아요 성공하셨습니다!');
			$('#likecount').text(data['likeresult']);
			} else if(data['ajaxresult'] == 4) {
				alert('당신이 작성한 글입니다!');
			} else {
				alert('이미 좋아요를 하셨습니다!');
			}
		},
		error: function(data) {
			alert('ajax 실패!');
		}
			
	});
}
</script>
</html>