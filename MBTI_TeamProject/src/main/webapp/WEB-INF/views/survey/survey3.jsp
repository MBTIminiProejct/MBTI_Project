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

#radio-previous1{
opacity:0;} 

#radio-previous2{
opacity:0;}

#radio-previous3{
opacity:0;}

#radio-previous4{
opacity:0;}

#radio-previous5{
opacity:0;}

#next3{
display:none;}
</style>
</head>
<body>
	
	<div class="box0" style="font-family:verdana; text-align: center"> 
      <h1>3/4</h1>
      <h3 style="font-family:courier">F or T</h3>
   </div>
   <br>
   <div class="box1" id = "boxf" style="font-family:verdana; text-align: center">
   <label for="ffile">F</label>
   <progress id="ffile" max="100" value="0"></progress>
</div>
	<div class="box2" id = "boxt" style="font-family:verdana; text-align: center">
   <label for="tfile">T</label>
   <progress id="tfile" max="100" value="0"></progress>
</div>
   <br>
	<div class="box3" style="font-family:verdana; text-align: center">
	<h3>1. 다른 사람이 울고 있는 모습을 보면 자신도 울고 싶어질 때가 많다.</h3>
	<input type="radio" name="myRadio1" value="0">매우 아니다 
	<input type="radio" name="myRadio1" value="1">아니다
	<input type="radio" name="myRadio1" value="2">그렇다
	<input type="radio" name="myRadio1" value="3">매우 그렇다
	<input type="radio" id="radio-previous1" name="myRadio1" value="-100" checked="true"/>
	</div>
	<br>
	<div class="box4" style="font-family:verdana; text-align: center">
	<h3>2. 자신과 배경이 완전히 다른 사람에게도 쉽게 공감할 수 있다.</h3>
	<input type="radio" name="myRadio2" value="0">매우 아니다 
	<input type="radio" name="myRadio2" value="1">아니다
	<input type="radio" name="myRadio2" value="2">그렇다
	<input type="radio" name="myRadio2" value="3">매우 그렇다
	<input type="radio" id="radio-previous2" name="myRadio2" value="-100" checked="true"/>
	</div>
	<br>
	<div class="box5" style="font-family:verdana; text-align: center">
	<h3>3. 상대방의 감정을 바로 알아차릴 수 있다.</h3>
	<input type="radio" name="myRadio3" value="0">매우 아니다 
	<input type="radio" name="myRadio3" value="1">아니다
	<input type="radio" name="myRadio3" value="2">그렇다
	<input type="radio" name="myRadio3" value="3">매우 그렇다
	<input type="radio" id="radio-previous3" name="myRadio3" value="-100" checked="true"/>
	</div>
	<br>
	<div class="box6" style="font-family:verdana; text-align: center">
	<h3>4. 감정을 조절하기보다는 감정에 휘둘리는 편이다.</h3>
	<input type="radio" name="myRadio4" value="0">매우 아니다 
	<input type="radio" name="myRadio4" value="1">아니다
	<input type="radio" name="myRadio4" value="2">그렇다
	<input type="radio" name="myRadio4" value="3">매우 그렇다
	<input type="radio" id="radio-previous4" name="myRadio4" value="-100" checked="true"/>
	</div>
	<br>
	<div class="box7" style="font-family:verdana; text-align: center">
	<h3>5. 감정보다는 이성을 따르는 편이다.</h3>
	<input type="radio" name="myRadio5" value="3">매우 아니다 
	<input type="radio" name="myRadio5" value="2">아니다
	<input type="radio" name="myRadio5" value="1">그렇다
	<input type="radio" name="myRadio5" value="0">매우 그렇다
	<input type="radio" id="radio-previous5" name="myRadio5" value="-100" checked="true"/>
	</div>
	<br>
	<div class="box8" style="font-family:verdana; text-align: center">
	<form action="/springmbti/survey/surveyfour" method="post">
		<button id="next3" type="submit" onclick="sbutton3()">제출</button>
	</form>
	</div>	
	
</body>
<script>
$("input[name=myRadio1]").mouseup(function() {
	 oldnum = $('input[name=myRadio1]:checked').val();
	 
}).change(function () {
	var changenum = $('input[name=myRadio1]:checked').val();
	
	var foldValue = Number($('#ffile').attr("value"));
	
	var toldValue = Number($('#tfile').attr("value"));
	
	$.ajax({
		url : "surveythree3",
		type : "GET",
		async : "false",
		dataType : "json",
		data : { 
		 onum : oldnum ,		
		 qnum : changenum
		},
		success : function(data) {
			var values = Object.values(data);
			
			if (data['onum'] == 3) {
				
				var fold = foldValue - 20;
				
				$('#ffile').attr("value",fold);
			} else if (data['onum'] == 2) {
				
				var fold = foldValue - 10;
				$('#ffile').attr("value",fold);	
				
				var told = toldValue - 10;
				
				$('#tfile').attr("value",told);
			} else if (data['onum'] == 1) {
				
				var told = toldValue - 10;
				$('#tfile').attr("value",told);
				var fold = foldValue - 10;
				$('#ffile').attr("value",fold);	
				
			} else if (data['onum'] == 0) {
				
				var told = toldValue - 20;
				$('#tfile').attr("value",told);
			} else {
				console.log('-1');
			}
			
			var fold = Number($('#ffile').attr("value"));
			
			var told = Number($('#tfile').attr("value"));
			
			
			if(data['qnum']==3) {
				
				var number = fold + 20;
				$('#ffile').attr("value",number);
			} else if(data['qnum']==2) {
				
				var number = fold + 10;
				$('#ffile').attr("value",number);
				var number = told + 10;
				$('#tfile').attr("value",number);
				
			} else if(data['qnum']==1) {
				
				var number = told + 10;
				$('#tfile').attr("value",number);
				var number = fold + 10;
				$('#ffile').attr("value",number);
				
			} else if(data['qnum']==0) {
				
				var number = told + 20;
				$('#tfile').attr("value",number);
		} else {
			console.log('-1');
		}	
			
			if ($('[id=radio-previous1]').prop('checked')) {
				var r1 = Number(0);
			} else {
				var r1 = Number(1);
			}
			
			if ($('[id=radio-previous2]').prop('checked')) {
				var r2 = Number(0);
			} else {
				var r2 = Number(1);
			}
			
			if ($('[id=radio-previous3]').prop('checked')) {
				var r3 = Number(0);
			} else {
				var r3 = Number(1);
			}
			
			if ($('[id=radio-previous4]').prop('checked')) {
				var r4 = Number(0);
			} else {
				var r4 = Number(1);
			}
			
			if ($('[id=radio-previous5]').prop('checked')) {
				var r5 = Number(0);
			} else {
				var r5 = Number(1);
			}
			
			var rtotal = r1 + r2 + r3 + r4 + r5;
			
			if (rtotal == 5) {
				$('#next3').show();
			}
			
		},
		error: function(data) {
		alert('ajax 실패!');
		}
	});
})

$("input[name=myRadio2]").mouseup(function() {
	 oldnum = $('input[name=myRadio2]:checked').val();
	 
}).change(function () {
	var changenum = $('input[name=myRadio2]:checked').val();
	
	var foldValue = Number($('#ffile').attr("value"));
	
	var toldValue = Number($('#tfile').attr("value"));
	
	$.ajax({
		url : "surveythree3",
		type : "GET",
		async : "false",
		dataType : "json",
		data : { 
		 onum : oldnum ,		
		 qnum : changenum
		},
		success : function(data) {
			var values = Object.values(data);
			
			if (data['onum'] == 3) {
				
				var fold = foldValue - 20;
				
				$('#ffile').attr("value",fold);
			} else if (data['onum'] == 2) {
				
				var fold = foldValue - 10;
				$('#ffile').attr("value",fold);	
				
				var told = toldValue - 10;
				
				$('#tfile').attr("value",told);
			} else if (data['onum'] == 1) {
				
				var told = toldValue - 10;
				$('#tfile').attr("value",told);
				var fold = foldValue - 10;
				$('#ffile').attr("value",fold);	
				
			} else if (data['onum'] == 0) {
				
				var told = toldValue - 20;
				$('#tfile').attr("value",told);
			} else {
				console.log('-1');
			}
			
			var fold = Number($('#ffile').attr("value"));
			
			var told = Number($('#tfile').attr("value"));
			
			
			if(data['qnum']==3) {
				
				var number = fold + 20;
				$('#ffile').attr("value",number);
			} else if(data['qnum']==2) {
				
				var number = fold + 10;
				$('#ffile').attr("value",number);
				var number = told + 10;
				$('#tfile').attr("value",number);
				
			} else if(data['qnum']==1) {
				
				var number = told + 10;
				$('#tfile').attr("value",number);
				var number = fold + 10;
				$('#ffile').attr("value",number);
				
			} else if(data['qnum']==0) {
				
				var number = told + 20;
				$('#tfile').attr("value",number);
		} else {
			console.log('-1');
		}	
			
			if ($('[id=radio-previous1]').prop('checked')) {
				var r1 = Number(0);
			} else {
				var r1 = Number(1);
			}
			
			if ($('[id=radio-previous2]').prop('checked')) {
				var r2 = Number(0);
			} else {
				var r2 = Number(1);
			}
			
			if ($('[id=radio-previous3]').prop('checked')) {
				var r3 = Number(0);
			} else {
				var r3 = Number(1);
			}
			
			if ($('[id=radio-previous4]').prop('checked')) {
				var r4 = Number(0);
			} else {
				var r4 = Number(1);
			}
			
			if ($('[id=radio-previous5]').prop('checked')) {
				var r5 = Number(0);
			} else {
				var r5 = Number(1);
			}
			
			var rtotal = r1 + r2 + r3 + r4 + r5;
			
			if (rtotal == 5) {
				$('#next3').show();
			}
			
		},
		error: function(data) {
		alert('ajax 실패!');
		}
	});
})

$("input[name=myRadio3]").mouseup(function() {
	 oldnum = $('input[name=myRadio3]:checked').val();
	 
}).change(function () {
	var changenum = $('input[name=myRadio3]:checked').val();
	
	var foldValue = Number($('#ffile').attr("value"));
	
	var toldValue = Number($('#tfile').attr("value"));
	
	$.ajax({
		url : "surveythree3",
		type : "GET",
		async : "false",
		dataType : "json",
		data : { 
		 onum : oldnum ,		
		 qnum : changenum
		},
		success : function(data) {
			var values = Object.values(data);
			
			if (data['onum'] == 3) {
				
				var fold = foldValue - 20;
				
				$('#ffile').attr("value",fold);
			} else if (data['onum'] == 2) {
				
				var fold = foldValue - 10;
				$('#ffile').attr("value",fold);	
				
				var told = toldValue - 10;
				
				$('#tfile').attr("value",told);
			} else if (data['onum'] == 1) {
				
				var told = toldValue - 10;
				$('#tfile').attr("value",told);
				var fold = foldValue - 10;
				$('#ffile').attr("value",fold);	
				
			} else if (data['onum'] == 0) {
				
				var told = toldValue - 20;
				$('#tfile').attr("value",told);
			} else {
				console.log('-1');
			}
			
			var fold = Number($('#ffile').attr("value"));
			
			var told = Number($('#tfile').attr("value"));
			
			
			if(data['qnum']==3) {
				
				var number = fold + 20;
				$('#ffile').attr("value",number);
			} else if(data['qnum']==2) {
				
				var number = fold + 10;
				$('#ffile').attr("value",number);
				var number = told + 10;
				$('#tfile').attr("value",number);
				
			} else if(data['qnum']==1) {
				
				var number = told + 10;
				$('#tfile').attr("value",number);
				var number = fold + 10;
				$('#ffile').attr("value",number);
				
			} else if(data['qnum']==0) {
				
				var number = told + 20;
				$('#tfile').attr("value",number);
		} else {
			console.log('-1');
		}	
			
			if ($('[id=radio-previous1]').prop('checked')) {
				var r1 = Number(0);
			} else {
				var r1 = Number(1);
			}
			
			if ($('[id=radio-previous2]').prop('checked')) {
				var r2 = Number(0);
			} else {
				var r2 = Number(1);
			}
			
			if ($('[id=radio-previous3]').prop('checked')) {
				var r3 = Number(0);
			} else {
				var r3 = Number(1);
			}
			
			if ($('[id=radio-previous4]').prop('checked')) {
				var r4 = Number(0);
			} else {
				var r4 = Number(1);
			}
			
			if ($('[id=radio-previous5]').prop('checked')) {
				var r5 = Number(0);
			} else {
				var r5 = Number(1);
			}
			
			var rtotal = r1 + r2 + r3 + r4 + r5;
			
			if (rtotal == 5) {
				$('#next3').show();
			}
			
		},
		error: function(data) {
		alert('ajax 실패!');
		}
	});
})

$("input[name=myRadio4]").mouseup(function() {
	 oldnum = $('input[name=myRadio4]:checked').val();
	 
}).change(function () {
	var changenum = $('input[name=myRadio4]:checked').val();
	
	var foldValue = Number($('#ffile').attr("value"));
	
	var toldValue = Number($('#tfile').attr("value"));
	
	$.ajax({
		url : "surveythree3",
		type : "GET",
		async : "false",
		dataType : "json",
		data : { 
		 onum : oldnum ,		
		 qnum : changenum
		},
		success : function(data) {
			var values = Object.values(data);
			
			if (data['onum'] == 3) {
				
				var fold = foldValue - 20;
				
				$('#ffile').attr("value",fold);
			} else if (data['onum'] == 2) {
				
				var fold = foldValue - 10;
				$('#ffile').attr("value",fold);	
				
				var told = toldValue - 10;
				
				$('#tfile').attr("value",told);
			} else if (data['onum'] == 1) {
				
				var told = toldValue - 10;
				$('#tfile').attr("value",told);
				var fold = foldValue - 10;
				$('#ffile').attr("value",fold);	
				
			} else if (data['onum'] == 0) {
				
				var told = toldValue - 20;
				$('#tfile').attr("value",told);
			} else {
				console.log('-1');
			}
			
			var fold = Number($('#ffile').attr("value"));
			
			var told = Number($('#tfile').attr("value"));
			
			
			if(data['qnum']==3) {
				
				var number = fold + 20;
				$('#ffile').attr("value",number);
			} else if(data['qnum']==2) {
				
				var number = fold + 10;
				$('#ffile').attr("value",number);
				var number = told + 10;
				$('#tfile').attr("value",number);
				
			} else if(data['qnum']==1) {
				
				var number = told + 10;
				$('#tfile').attr("value",number);
				var number = fold + 10;
				$('#ffile').attr("value",number);
				
			} else if(data['qnum']==0) {
				
				var number = told + 20;
				$('#tfile').attr("value",number);
		} else {
			console.log('-1');
		}	
			
			if ($('[id=radio-previous1]').prop('checked')) {
				var r1 = Number(0);
			} else {
				var r1 = Number(1);
			}
			
			if ($('[id=radio-previous2]').prop('checked')) {
				var r2 = Number(0);
			} else {
				var r2 = Number(1);
			}
			
			if ($('[id=radio-previous3]').prop('checked')) {
				var r3 = Number(0);
			} else {
				var r3 = Number(1);
			}
			
			if ($('[id=radio-previous4]').prop('checked')) {
				var r4 = Number(0);
			} else {
				var r4 = Number(1);
			}
			
			if ($('[id=radio-previous5]').prop('checked')) {
				var r5 = Number(0);
			} else {
				var r5 = Number(1);
			}
			
			var rtotal = r1 + r2 + r3 + r4 + r5;
			
			if (rtotal == 5) {
				$('#next3').show();
			}
			
		},
		error: function(data) {
		alert('ajax 실패!');
		}
	});
})

$("input[name=myRadio5]").mouseup(function() {
	 oldnum = $('input[name=myRadio5]:checked').val();
	 
}).change(function () {
	var changenum = $('input[name=myRadio5]:checked').val();
	
	var foldValue = Number($('#ffile').attr("value"));
	
	var toldValue = Number($('#tfile').attr("value"));
	
	$.ajax({
		url : "surveythree3",
		type : "GET",
		async : "false",
		dataType : "json",
		data : { 
		 onum : oldnum ,		
		 qnum : changenum
		},
		success : function(data) {
			var values = Object.values(data);
			
			if (data['onum'] == 3) {
				
				var fold = foldValue - 20;
				
				$('#ffile').attr("value",fold);
			} else if (data['onum'] == 2) {
				
				var fold = foldValue - 10;
				$('#ffile').attr("value",fold);	
				
				var told = toldValue - 10;
				
				$('#tfile').attr("value",told);
			} else if (data['onum'] == 1) {
				
				var told = toldValue - 10;
				$('#tfile').attr("value",told);
				var fold = foldValue - 10;
				$('#ffile').attr("value",fold);	
				
			} else if (data['onum'] == 0) {
				
				var told = toldValue - 20;
				$('#tfile').attr("value",told);
			} else {
				console.log('-1');
			}
			
			var fold = Number($('#ffile').attr("value"));
			
			var told = Number($('#tfile').attr("value"));
			
			
			if(data['qnum']==3) {
				
				var number = fold + 20;
				$('#ffile').attr("value",number);
			} else if(data['qnum']==2) {
				
				var number = fold + 10;
				$('#ffile').attr("value",number);
				var number = told + 10;
				$('#tfile').attr("value",number);
				
			} else if(data['qnum']==1) {
				
				var number = told + 10;
				$('#tfile').attr("value",number);
				var number = fold + 10;
				$('#ffile').attr("value",number);
				
			} else if(data['qnum']==0) {
				
				var number = told + 20;
				$('#tfile').attr("value",number);
		} else {
			console.log('-1');
		}	
			
			if ($('[id=radio-previous1]').prop('checked')) {
				var r1 = Number(0);
			} else {
				var r1 = Number(1);
			}
			
			if ($('[id=radio-previous2]').prop('checked')) {
				var r2 = Number(0);
			} else {
				var r2 = Number(1);
			}
			
			if ($('[id=radio-previous3]').prop('checked')) {
				var r3 = Number(0);
			} else {
				var r3 = Number(1);
			}
			
			if ($('[id=radio-previous4]').prop('checked')) {
				var r4 = Number(0);
			} else {
				var r4 = Number(1);
			}
			
			if ($('[id=radio-previous5]').prop('checked')) {
				var r5 = Number(0);
			} else {
				var r5 = Number(1);
			}
			
			var rtotal = r1 + r2 + r3 + r4 + r5;
			
			if (rtotal == 5) {
				$('#next3').show();
			}
			
		},
		error: function(data) {
		alert('ajax 실패!');
		}
	});
})



function sbutton3() {
	$.ajax({
		url : "sbutton3",
		type : "PUT",
		async : "false",
		dataType : "json",
		data : { qone: $('input[name=myRadio1]:checked').val(),
				 qtwo: $('input[name=myRadio2]:checked').val(),
				 qthree: $('input[name=myRadio3]:checked').val(),
				 qfour: $('input[name=myRadio4]:checked').val(),
				 qfive: $('input[name=myRadio5]:checked').val()
				},
		success : function(data) {
			
			
			
			console.log('성공!!');
			
		},
		error: function(data) {
			alert('ajax 실패!');
		}
			
	});
}

</script>
</html>