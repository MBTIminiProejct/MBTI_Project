<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ page import="team.spring.springmbti.user.vo.Survey, java.util.List" %>
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
      <h1>1/4</h1>
      <h3 style="font-family:courier">E or I</h3>
   </div>
   <br>
   <div class="box1" id = "boxe" style="font-family:verdana; text-align: center">
   <label for="file">E</label>
   <progress id="file" max="100" value="80"></progress>
</div>
	<div class="box2" id = "boxi" style="font-family:verdana; text-align: center">
   <label for="file">I</label>
   <progress id="file" max="100" value="80"></progress>
</div>
   <br>
	<div class="box3" style="font-family:verdana; text-align: center">
	<h3>1. 친구에게 먼저 만나자고 연락하는 편이다.</h3>
	<input type="radio" name="myRadio1" value="0">매우 아니다 
	<input type="radio" name="myRadio1" value="1">아니다
	<input type="radio" name="myRadio1" value="2">그렇다
	<input type="radio" name="myRadio1" value="3">매우 그렇다
	</div>
	<br>
	<div class="box4" style="font-family:verdana; text-align: center">
	<h3>2. 대부분의 시간을 혼자서 일할 수 있는 직업을 원한다.</h3>
	<input type="radio" name="myRadio2" value="0">매우 아니다 
	<input type="radio" name="myRadio2" value="1">아니다
	<input type="radio" name="myRadio2" value="2">그렇다
	<input type="radio" name="myRadio2" value="3">매우 그렇다
	</div>
	<br>
	<div class="box5" style="font-family:verdana; text-align: center">
	<h3>3. 관심이 가는 사람에게 다가가서 대화를 시작하기가 어렵지 않다.</h3>
	<input type="radio" name="myRadio3" value="0">매우 아니다 
	<input type="radio" name="myRadio3" value="1">아니다
	<input type="radio" name="myRadio3" value="2">그렇다
	<input type="radio" name="myRadio3" value="3">매우 그렇다
	</div>
	<br>
	<div class="box6" style="font-family:verdana; text-align: center">
	<h3>4. 단체 활동에 참여하는 일을 즐긴다.</h3>
	<input type="radio" name="myRadio4" value="0">매우 아니다 
	<input type="radio" name="myRadio4" value="1">아니다
	<input type="radio" name="myRadio4" value="2">그렇다
	<input type="radio" name="myRadio4" value="3">매우 그렇다
	</div>
	<br>
	<div class="box7" style="font-family:verdana; text-align: center">
	<h3>5. 혼자보다는 다른 사람과 시간을 보내고 싶어한다.</h3>
	<input type="radio" name="myRadio5" value="0">매우 아니다 
	<input type="radio" name="myRadio5" value="1">아니다
	<input type="radio" name="myRadio5" value="2">그렇다
	<input type="radio" name="myRadio5" value="3">매우 그렇다
	</div>
	<br>
	<div class="box8" style="font-family:verdana; text-align: center">
	<form action="/springmbti/surveytwo" method="post">
		<input type="submit" value="제출">
	</form>
	</div>	
</body>
<script>
$("input[name='myRadio1']:radio").change(function () {
	var changenum = this.value;
	var very = 20;
	var nvery = 10;
	$.ajax({
		url : "surveyone1",
		type : "POST",
		async : "false",
		dataType : "json",
		data : { 
		 qnum : changenum,
		 vnum : very,
		 nvnum : nvery
		},
		success : function(data) {
			var values = Object.values(data);
			 
			if(data['qnum']==1) {
				console.log('1');
				$('#boxi *').remove();
				var number = data['qnum'];
				var numberi = String(number);
				var ibar = '<label for="file">I</label><progress id="file" max="100" value="'+numbere+'"></progress>';
				$('#boxi').append(ibar);
			} else if(data['qnum']==2) {
				console.log('2');
			} else if(data['qnum']==3) {
				console.log('3');
				$('#boxe *').remove();
				var number = data['qnum'];
				var numbere = String(number);
				var ebar = '<label for="file">E</label><progress id="file" max="100" value="'+numbere+'"></progress>';
				$('#boxe').append(ebar);
			} else {
				console.log('0');
		}	
			$.ajax({
                async: true,
                url: "https://dapi.kakao.com/v2/search/image",
                type: 'GET',
                headers: {
                    Authorization: 'KakaoAK e54b078ee9b628c624b264f5de012d33'
                },
                data:{
                    query : item.movieNm + ' 포스터'
                },
                success: function(data) {
                    console.log('검색 성공!');
                    let imgurl = data.documents[0].thumbnail_url;
                    posterImg.attr('src', imgurl);
                },
                // 클로져
                error: function() {
                    alert('경고!!');
                }
            });
		
		},
		error: function(data) {
		alert('ajax 실패!');
		}
			
	});
	
})



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