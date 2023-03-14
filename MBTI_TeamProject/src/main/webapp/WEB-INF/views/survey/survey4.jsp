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
      <h1>4/4</h1>
      <h3 style="font-family:courier">J or P</h3>
   </div>
   <div class="box1" style="font-family:verdana; text-align: center">
   <label for="file">J</label>

<progress id="file" max="100" value="50">  </progress>
   <br>
	<div class="box2" style="font-family:verdana; text-align: center">
	<h3>1. 일이 잘못될 때를 대비해 여러 대비책을 세우는 편이다.</h3>
	<input type="radio" name="myCheckBox1" value="0">매우 아니다 
	<input type="radio" name="myCheckBox1" value="1">아니다
	<input type="radio" name="myCheckBox1" value="2">그렇다
	<input type="radio" name="myCheckBox1" value="3">매우 그렇다
	</div>
	<br>
	<div class="box3" style="font-family:verdana; text-align: center">
	<h3>2. 해야 할 일을 마지막까지 미룰 때가 많다.</h3>
	<input type="radio" name="myCheckBox2" value="0">매우 아니다 
	<input type="radio" name="myCheckBox2" value="1">아니다
	<input type="radio" name="myCheckBox2" value="2">그렇다
	<input type="radio" name="myCheckBox2" value="3">매우 그렇다
	</div>
	<br>
	<div class="box4" style="font-family:verdana; text-align: center">
	<h3>3. 단계를 건너뛰는 일 없이 절차대로 일을 완수하는 편이다.</h3>
	<input type="radio" name="myCheckBox3" value="0">매우 아니다 
	<input type="radio" name="myCheckBox3" value="1">아니다
	<input type="radio" name="myCheckBox3" value="2">그렇다
	<input type="radio" name="myCheckBox3" value="3">매우 그렇다
	</div>
	<br>
	<div class="box5" style="font-family:verdana; text-align: center">
	<h3>4. 일정이나 목록으로 계획을 세우는 일을 좋아한다.</h3>
	<input type="radio" name="myCheckBox4" value="0">매우 아니다 
	<input type="radio" name="myCheckBox4" value="1">아니다
	<input type="radio" name="myCheckBox4" value="2">그렇다
	<input type="radio" name="myCheckBox4" value="3">매우 그렇다
	</div>
	<br>
	<div class="box5" style="font-family:verdana; text-align: center">
	<h3>5. 일이 잘못될까봐 자주 걱정하는 편이다.</h3>
	<input type="radio" name="myCheckBox5" value="0">매우 아니다 
	<input type="radio" name="myCheckBox5" value="1">아니다
	<input type="radio" name="myCheckBox5" value="2">그렇다
	<input type="radio" name="myCheckBox5" value="3">매우 그렇다
	</div>
	<br>
	<div class="box5" style="font-family:verdana; text-align: center">
	<form action="" method="post">
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