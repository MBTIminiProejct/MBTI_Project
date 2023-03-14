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

#next4{
display:none;}
</style>
</head>
<body>
	
	<div class="box0" style="font-family:verdana; text-align: center"> 
      <h1>4/4</h1>
      <h3 style="font-family:courier">J or P</h3>
   </div>
   <br>
   <div class="box1" id = "boxj" style="font-family:verdana; text-align: center">
   <label for="jfile">J</label>
   <progress id="jfile" max="100" value="0"></progress>
</div>
	<div class="box2" id = "boxp" style="font-family:verdana; text-align: center">
   <label for="pfile">P</label>
   <progress id="pfile" max="100" value="0"></progress>
</div>
   <br>
	<div class="box3" style="font-family:verdana; text-align: center">
	<h3>1. 일이 잘못될 때를 대비해 여러 대비책을 세우는 편이다.</h3>
	<input type="radio" name="myRadio1" value="0">매우 아니다 
	<input type="radio" name="myRadio1" value="1">아니다
	<input type="radio" name="myRadio1" value="2">그렇다
	<input type="radio" name="myRadio1" value="3">매우 그렇다
	<input type="radio" id="radio-previous1" name="myRadio1" value="-100" checked="true"/>
	</div>
	<br>
	<div class="box4" style="font-family:verdana; text-align: center">
	<h3>2. 단계를 건너뛰는 일 없이 절차대로 일을 완수하는 편이다.</h3>
	<input type="radio" name="myRadio2" value="0">매우 아니다 
	<input type="radio" name="myRadio2" value="1">아니다
	<input type="radio" name="myRadio2" value="2">그렇다
	<input type="radio" name="myRadio2" value="3">매우 그렇다
	<input type="radio" id="radio-previous2" name="myRadio2" value="-100" checked="true"/>
	</div>
	<br>
	<div class="box5" style="font-family:verdana; text-align: center">
	<h3>3. 일이 잘못될까봐 자주 걱정하는 편이다.</h3>
	<input type="radio" name="myRadio3" value="0">매우 아니다 
	<input type="radio" name="myRadio3" value="1">아니다
	<input type="radio" name="myRadio3" value="2">그렇다
	<input type="radio" name="myRadio3" value="3">매우 그렇다
	<input type="radio" id="radio-previous3" name="myRadio3" value="-100" checked="true"/>
	</div>
	<br>
	<div class="box6" style="font-family:verdana; text-align: center">
	<h3>4. 해야 할 일을 마지막까지 미룰 때가 많다.</h3>
	<input type="radio" name="myRadio4" value="3">매우 아니다 
	<input type="radio" name="myRadio4" value="2">아니다
	<input type="radio" name="myRadio4" value="1">그렇다
	<input type="radio" name="myRadio4" value="0">매우 그렇다
	<input type="radio" id="radio-previous4" name="myRadio4" value="-100" checked="true"/>
	</div>
	<br>
	<div class="box7" style="font-family:verdana; text-align: center">
	<h3>5. 휴식을 취하기 전에 집안일을 먼저 끝내기를 원한다.</h3>
	<input type="radio" name="myRadio5" value="0">매우 아니다 
	<input type="radio" name="myRadio5" value="1">아니다
	<input type="radio" name="myRadio5" value="2">그렇다
	<input type="radio" name="myRadio5" value="3">매우 그렇다
	<input type="radio" id="radio-previous5" name="myRadio5" value="-100" checked="true"/>
	</div>
	<br>
	<div class="box8" style="font-family:verdana; text-align: center">
	<form action="/springmbti/survey/surveyfive" method="post">
		<button id="next4" type="submit" onclick="sbutton4()">제출</button>
	</form>
	</div>	
</body>
<script>
$("input[name=myRadio1]").mouseup(function() {
	 oldnum = $('input[name=myRadio1]:checked').val();
	 
}).change(function () {
	var changenum = $('input[name=myRadio1]:checked').val();
	
	var joldValue = Number($('#jfile').attr("value"));
	
	var poldValue = Number($('#pfile').attr("value"));
	
	$.ajax({
		url : "surveyfour4",
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
				//console.log('3');
				var jold = joldValue - 20;
				
				$('#jfile').attr("value",jold);
			} else if (data['onum'] == 2) {
				//console.log('2');
				var jold = joldValue - 10;
				$('#jfile').attr("value",jold);	
				
				var pold = poldValue - 10;
				
				$('#pfile').attr("value",pold);
			} else if (data['onum'] == 1) {
				//console.log('1');
				var pold = poldValue - 10;
				$('#pfile').attr("value",pold);
				var jold = joldValue - 10;
				$('#jfile').attr("value",jold);	
				
			} else if (data['onum'] == 0) {
				//console.log('0');
				var pold = poldValue - 20;
				$('#pfile').attr("value",pold);
			} else {
				console.log('-1');
			}
			
			var jold = Number($('#jfile').attr("value"));
			
			var pold = Number($('#pfile').attr("value"));
			
			
			if(data['qnum']==3) {
				//console.log('3');
				var number = jold + 20;
				$('#jfile').attr("value",number);
			} else if(data['qnum']==2) {
				//console.log('2');
				var number = jold + 10;
				$('#jfile').attr("value",number);
				var number = pold + 10;
				$('#pfile').attr("value",number);
				
			} else if(data['qnum']==1) {
				//console.log('1');
				var number = pold + 10;
				$('#pfile').attr("value",number);
				var number = jold + 10;
				$('#jfile').attr("value",number);
				
			} else if(data['qnum']==0) {
				//console.log('0');
				var number = pold + 20;
				$('#pfile').attr("value",number);
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
				$('#next4').show();
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
	
	var joldValue = Number($('#jfile').attr("value"));
	
	var poldValue = Number($('#pfile').attr("value"));
	
	$.ajax({
		url : "surveyfour4",
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
				//console.log('3');
				var jold = joldValue - 20;
				
				$('#jfile').attr("value",jold);
			} else if (data['onum'] == 2) {
				//console.log('2');
				var jold = joldValue - 10;
				$('#jfile').attr("value",jold);	
				
				var pold = poldValue - 10;
				
				$('#pfile').attr("value",pold);
			} else if (data['onum'] == 1) {
				//console.log('1');
				var pold = poldValue - 10;
				$('#pfile').attr("value",pold);
				var jold = joldValue - 10;
				$('#jfile').attr("value",jold);	
				
			} else if (data['onum'] == 0) {
				//console.log('0');
				var pold = poldValue - 20;
				$('#pfile').attr("value",pold);
			} else {
				console.log('-1');
			}
			
			var jold = Number($('#jfile').attr("value"));
			
			var pold = Number($('#pfile').attr("value"));
			
			
			if(data['qnum']==3) {
				//console.log('3');
				var number = jold + 20;
				$('#jfile').attr("value",number);
			} else if(data['qnum']==2) {
				//console.log('2');
				var number = jold + 10;
				$('#jfile').attr("value",number);
				var number = pold + 10;
				$('#pfile').attr("value",number);
				
			} else if(data['qnum']==1) {
				//console.log('1');
				var number = pold + 10;
				$('#pfile').attr("value",number);
				var number = jold + 10;
				$('#jfile').attr("value",number);
				
			} else if(data['qnum']==0) {
				//console.log('0');
				var number = pold + 20;
				$('#pfile').attr("value",number);
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
				$('#next4').show();
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
	
	var joldValue = Number($('#jfile').attr("value"));
	
	var poldValue = Number($('#pfile').attr("value"));
	
	$.ajax({
		url : "surveyfour4",
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
				//console.log('3');
				var jold = joldValue - 20;
				
				$('#jfile').attr("value",jold);
			} else if (data['onum'] == 2) {
				//console.log('2');
				var jold = joldValue - 10;
				$('#jfile').attr("value",jold);	
				
				var pold = poldValue - 10;
				
				$('#pfile').attr("value",pold);
			} else if (data['onum'] == 1) {
				//console.log('1');
				var pold = poldValue - 10;
				$('#pfile').attr("value",pold);
				var jold = joldValue - 10;
				$('#jfile').attr("value",jold);	
				
			} else if (data['onum'] == 0) {
				//console.log('0');
				var pold = poldValue - 20;
				$('#pfile').attr("value",pold);
			} else {
				console.log('-1');
			}
			
			var jold = Number($('#jfile').attr("value"));
			
			var pold = Number($('#pfile').attr("value"));
			
			
			if(data['qnum']==3) {
				//console.log('3');
				var number = jold + 20;
				$('#jfile').attr("value",number);
			} else if(data['qnum']==2) {
				//console.log('2');
				var number = jold + 10;
				$('#jfile').attr("value",number);
				var number = pold + 10;
				$('#pfile').attr("value",number);
				
			} else if(data['qnum']==1) {
				//console.log('1');
				var number = pold + 10;
				$('#pfile').attr("value",number);
				var number = jold + 10;
				$('#jfile').attr("value",number);
				
			} else if(data['qnum']==0) {
				//console.log('0');
				var number = pold + 20;
				$('#pfile').attr("value",number);
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
				$('#next4').show();
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
	
	var joldValue = Number($('#jfile').attr("value"));
	
	var poldValue = Number($('#pfile').attr("value"));
	
	$.ajax({
		url : "surveyfour4",
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
				//console.log('3');
				var jold = joldValue - 20;
				
				$('#jfile').attr("value",jold);
			} else if (data['onum'] == 2) {
				//console.log('2');
				var jold = joldValue - 10;
				$('#jfile').attr("value",jold);	
				
				var pold = poldValue - 10;
				
				$('#pfile').attr("value",pold);
			} else if (data['onum'] == 1) {
				//console.log('1');
				var pold = poldValue - 10;
				$('#pfile').attr("value",pold);
				var jold = joldValue - 10;
				$('#jfile').attr("value",jold);	
				
			} else if (data['onum'] == 0) {
				//console.log('0');
				var pold = poldValue - 20;
				$('#pfile').attr("value",pold);
			} else {
				console.log('-1');
			}
			
			var jold = Number($('#jfile').attr("value"));
			
			var pold = Number($('#pfile').attr("value"));
			
			
			if(data['qnum']==3) {
				//console.log('3');
				var number = jold + 20;
				$('#jfile').attr("value",number);
			} else if(data['qnum']==2) {
				//console.log('2');
				var number = jold + 10;
				$('#jfile').attr("value",number);
				var number = pold + 10;
				$('#pfile').attr("value",number);
				
			} else if(data['qnum']==1) {
				//console.log('1');
				var number = pold + 10;
				$('#pfile').attr("value",number);
				var number = jold + 10;
				$('#jfile').attr("value",number);
				
			} else if(data['qnum']==0) {
				//console.log('0');
				var number = pold + 20;
				$('#pfile').attr("value",number);
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
				$('#next4').show();
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
	
	var joldValue = Number($('#jfile').attr("value"));
	
	var poldValue = Number($('#pfile').attr("value"));
	
	$.ajax({
		url : "surveyfour4",
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
				//console.log('3');
				var jold = joldValue - 20;
				
				$('#jfile').attr("value",jold);
			} else if (data['onum'] == 2) {
				//console.log('2');
				var jold = joldValue - 10;
				$('#jfile').attr("value",jold);	
				
				var pold = poldValue - 10;
				
				$('#pfile').attr("value",pold);
			} else if (data['onum'] == 1) {
				//console.log('1');
				var pold = poldValue - 10;
				$('#pfile').attr("value",pold);
				var jold = joldValue - 10;
				$('#jfile').attr("value",jold);	
				
			} else if (data['onum'] == 0) {
				//console.log('0');
				var pold = poldValue - 20;
				$('#pfile').attr("value",pold);
			} else {
				console.log('-1');
			}
			
			var jold = Number($('#jfile').attr("value"));
			
			var pold = Number($('#pfile').attr("value"));
			
			
			if(data['qnum']==3) {
				//console.log('3');
				var number = jold + 20;
				$('#jfile').attr("value",number);
			} else if(data['qnum']==2) {
				//console.log('2');
				var number = jold + 10;
				$('#jfile').attr("value",number);
				var number = pold + 10;
				$('#pfile').attr("value",number);
				
			} else if(data['qnum']==1) {
				//console.log('1');
				var number = pold + 10;
				$('#pfile').attr("value",number);
				var number = jold + 10;
				$('#jfile').attr("value",number);
				
			} else if(data['qnum']==0) {
				//console.log('0');
				var number = pold + 20;
				$('#pfile').attr("value",number);
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
				$('#next4').show();
			}
			
		},
		error: function(data) {
		alert('ajax 실패!');
		}
	});
})


function sbutton4() {
	$.ajax({
		url : "sbutton4",
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
			
			//var values = Object.values(data);
			
			
			//if(data['ajaxresult'] == 7){
			
			console.log('성공!!');
			//} else {
				
			//}
		},
		error: function(data) {
			alert('ajax 실패!');
		}
			
	});
}

</script>
</html>