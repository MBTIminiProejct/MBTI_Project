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
      <h1>3/4</h1>
      <h3 style="font-family:courier">T or F</h3>
   </div>
   <div class="box1" style="font-family:verdana; text-align: center">
   <label for="file">T</label>

<progress id="file" max="100" value="50">  </progress>
   
   <br>
	<div class="box2" style="font-family:verdana; text-align: center">
	<h3>1. 감정보다는 이성을 따르는 편이다.</h3>
	<input type="radio" name="myCheckBox1" value="0">매우 아니다 
	<input type="radio" name="myCheckBox1" value="1">아니다
	<input type="radio" name="myCheckBox1" value="2">그렇다
	<input type="radio" name="myCheckBox1" value="3">매우 그렇다
	</div>
	<br>
	<div class="box3" style="font-family:verdana; text-align: center">
	<h3>2. 다른 사람이 울고 있는 모습을 보면 자신도 울고 싶어질 때가 많다.</h3>
	<input type="radio" name="myCheckBox2" value="0">매우 아니다 
	<input type="radio" name="myCheckBox2" value="1">아니다
	<input type="radio" name="myCheckBox2" value="2">그렇다
	<input type="radio" name="myCheckBox2" value="3">매우 그렇다
	</div>
	<br>
	<div class="box4" style="font-family:verdana; text-align: center">
	<h3>3. 자신보다는 남의 성공을 돕는 일에서 더 큰 만족감을 느낀다.</h3>
	<input type="radio" name="myCheckBox3" value="0">매우 아니다 
	<input type="radio" name="myCheckBox3" value="1">아니다
	<input type="radio" name="myCheckBox3" value="2">그렇다
	<input type="radio" name="myCheckBox3" value="3">매우 그렇다
	</div>
	<br>
	<div class="box5" style="font-family:verdana; text-align: center">
	<h3>4. 작은 실수로도 자신의 능력이나 지식을 의심하곤 한다.</h3>
	<input type="radio" name="myCheckBox4" value="0">매우 아니다 
	<input type="radio" name="myCheckBox4" value="1">아니다
	<input type="radio" name="myCheckBox4" value="2">그렇다
	<input type="radio" name="myCheckBox4" value="3">매우 그렇다
	</div>
	<br>
	<div class="box5" style="font-family:verdana; text-align: center">
	<h3>5. 다른 사람의 논쟁을 바라보는 일이 즐겁다.</h3>
	<input type="radio" name="myCheckBox5" value="0">매우 아니다 
	<input type="radio" name="myCheckBox5" value="1">아니다
	<input type="radio" name="myCheckBox5" value="2">그렇다
	<input type="radio" name="myCheckBox5" value="3">매우 그렇다
	</div>
	<br>
	<div class="box5" style="font-family:verdana; text-align: center">
	<form action="surveyfour" method="post">
		<input type="submit" value="제출">
	</form>
	</div>	
</body>
<script>
function surveyone1() {
	$.ajax({
		url : "surveyone1",
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