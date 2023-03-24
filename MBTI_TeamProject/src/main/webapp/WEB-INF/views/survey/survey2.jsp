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

#next2{
display:none;}
</style>
</head>
<body>
	
	<div class="box0" style="font-family:verdana; text-align: center"> 
      <h1>2/4</h1>
      <h3 style="font-family:courier">S or N</h3>
   </div>
   <br>
   <div class="box1" id = "boxs" style="font-family:verdana; text-align: center">
   <label for="sfile">S</label>
   <progress id="sfile" max="100" value="0"></progress>
</div>
	<div class="box2" id = "boxn" style="font-family:verdana; text-align: center">
   <label for="nfile">N</label>
   <progress id="nfile" max="100" value="0"></progress>
</div>
   <br>
	<div class="box3" style="font-family:verdana; text-align: center">
	<h3>1. 사후세계에 대한 질문이 흥미롭다고 생각한다.</h3>
	<input type="radio" name="myRadio1" value="3">매우 아니다 
	<input type="radio" name="myRadio1" value="2">아니다
	<input type="radio" name="myRadio1" value="1">그렇다
	<input type="radio" name="myRadio1" value="0">매우 그렇다
	<input type="radio" id="radio-previous1" name="myRadio1" value="-100" checked="true"/>
	</div>
	<br>
	<div class="box4" style="font-family:verdana; text-align: center">
	<h3>2. 이미 내린 결정에 대해서는 다시 생각하지 않는 편이다.</h3>
	<input type="radio" name="myRadio2" value="0">매우 아니다 
	<input type="radio" name="myRadio2" value="1">아니다
	<input type="radio" name="myRadio2" value="2">그렇다
	<input type="radio" name="myRadio2" value="3">매우 그렇다
	<input type="radio" id="radio-previous2" name="myRadio2" value="-100" checked="true"/>
	</div>
	<br>
	<div class="box5" style="font-family:verdana; text-align: center">
	<h3>3. 불안함을 느낄 때가 거의 없다.</h3>
	<input type="radio" name="myRadio3" value="0">매우 아니다 
	<input type="radio" name="myRadio3" value="1">아니다
	<input type="radio" name="myRadio3" value="2">그렇다
	<input type="radio" name="myRadio3" value="3">매우 그렇다
	<input type="radio" id="radio-previous3" name="myRadio3" value="-100" checked="true"/>
	</div>
	<br>
	<div class="box6" style="font-family:verdana; text-align: center">
	<h3>4. 자신만큼 효율적이지 못한 사람을 보면 짜증이 난다.</h3>
	<input type="radio" name="myRadio4" value="0">매우 아니다 
	<input type="radio" name="myRadio4" value="1">아니다
	<input type="radio" name="myRadio4" value="2">그렇다
	<input type="radio" name="myRadio4" value="3">매우 그렇다
	<input type="radio" id="radio-previous4" name="myRadio4" value="-100" checked="true"/>
	</div>
	<br>
	<div class="box7" style="font-family:verdana; text-align: center">
	<h3>5. 예술 작품의 다양한 해석에 대해 토론하는 일에는 크게 관심이 없다.</h3>
	<input type="radio" name="myRadio5" value="0">매우 아니다 
	<input type="radio" name="myRadio5" value="1">아니다
	<input type="radio" name="myRadio5" value="2">그렇다
	<input type="radio" name="myRadio5" value="3">매우 그렇다
	<input type="radio" id="radio-previous5" name="myRadio5" value="-100" checked="true"/>
	</div>
	<br>
	<div class="box8" style="font-family:verdana; text-align: center">
	<form action="/springmbti/survey/surveythree" method="post">
		<button id="next2" type="submit" onclick="sbutton2()">제출</button>
	</form>
	</div>	
	
</body>
<script>
$("input[name=myRadio1]").mouseup(function() {
	 oldnum = $('input[name=myRadio1]:checked').val();
	 
}).change(function () {
	var changenum = $('input[name=myRadio1]:checked').val();
	
	var soldValue = Number($('#sfile').attr("value"));
	
	var noldValue = Number($('#nfile').attr("value"));
	
	$.ajax({
		url : "surveytwo2",
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
				
				var sold = soldValue - 20;
				
				$('#sfile').attr("value",sold);
			} else if (data['onum'] == 2) {
				
				var sold = soldValue - 10;
				$('#sfile').attr("value",sold);	
				
				var nold = noldValue - 10;
				
				$('#nfile').attr("value",nold);
			} else if (data['onum'] == 1) {
				
				var nold = noldValue - 10;
				$('#nfile').attr("value",nold);
				var sold = soldValue - 10;
				$('#sfile').attr("value",sold);	
				
			} else if (data['onum'] == 0) {
				
				var nold = noldValue - 20;
				$('#nfile').attr("value",nold);
			} else {
				console.log('-1');
			}
			
			var sold = Number($('#sfile').attr("value"));
			
			var nold = Number($('#nfile').attr("value"));
			
			
			if(data['qnum']==3) {
				
				var number = sold + 20;
				$('#sfile').attr("value",number);
			} else if(data['qnum']==2) {
				
				var number = sold + 10;
				$('#sfile').attr("value",number);
				var number = nold + 10;
				$('#nfile').attr("value",number);
				
			} else if(data['qnum']==1) {
				
				var number = nold + 10;
				$('#nfile').attr("value",number);
				var number = sold + 10;
				$('#sfile').attr("value",number);
				
			} else if(data['qnum']==0) {
				
				var number = nold + 20;
				$('#nfile').attr("value",number);
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
				$('#next2').show();
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
	
	var soldValue = Number($('#sfile').attr("value"));
	
	var noldValue = Number($('#nfile').attr("value"));
	
	$.ajax({
		url : "surveytwo2",
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
				
				var sold = soldValue - 20;
				
				$('#sfile').attr("value",sold);
			} else if (data['onum'] == 2) {
				
				var sold = soldValue - 10;
				$('#sfile').attr("value",sold);	
				
				var nold = noldValue - 10;
				
				$('#nfile').attr("value",nold);
			} else if (data['onum'] == 1) {
				
				var nold = noldValue - 10;
				$('#nfile').attr("value",nold);
				var sold = soldValue - 10;
				$('#sfile').attr("value",sold);	
				
			} else if (data['onum'] == 0) {
				
				var nold = noldValue - 20;
				$('#nfile').attr("value",nold);
			} else {
				console.log('-1');
			}
			
			var sold = Number($('#sfile').attr("value"));
			
			var nold = Number($('#nfile').attr("value"));
			
			
			if(data['qnum']==3) {
				
				var number = sold + 20;
				$('#sfile').attr("value",number);
			} else if(data['qnum']==2) {
				
				var number = sold + 10;
				$('#sfile').attr("value",number);
				var number = nold + 10;
				$('#nfile').attr("value",number);
				
			} else if(data['qnum']==1) {
				
				var number = nold + 10;
				$('#nfile').attr("value",number);
				var number = sold + 10;
				$('#sfile').attr("value",number);
				
			} else if(data['qnum']==0) {
				
				var number = nold + 20;
				$('#nfile').attr("value",number);
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
				$('#next2').show();
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
	
	var soldValue = Number($('#sfile').attr("value"));
	
	var noldValue = Number($('#nfile').attr("value"));
	
	$.ajax({
		url : "surveytwo2",
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
				
				var sold = soldValue - 20;
				
				$('#sfile').attr("value",sold);
			} else if (data['onum'] == 2) {
				
				var sold = soldValue - 10;
				$('#sfile').attr("value",sold);	
				
				var nold = noldValue - 10;
				
				$('#nfile').attr("value",nold);
			} else if (data['onum'] == 1) {
				
				var nold = noldValue - 10;
				$('#nfile').attr("value",nold);
				var sold = soldValue - 10;
				$('#sfile').attr("value",sold);	
				
			} else if (data['onum'] == 0) {
				
				var nold = noldValue - 20;
				$('#nfile').attr("value",nold);
			} else {
				console.log('-1');
			}
			
			var sold = Number($('#sfile').attr("value"));
			
			var nold = Number($('#nfile').attr("value"));
			
			
			if(data['qnum']==3) {
				
				var number = sold + 20;
				$('#sfile').attr("value",number);
			} else if(data['qnum']==2) {
				
				var number = sold + 10;
				$('#sfile').attr("value",number);
				var number = nold + 10;
				$('#nfile').attr("value",number);
				
			} else if(data['qnum']==1) {
				
				var number = nold + 10;
				$('#nfile').attr("value",number);
				var number = sold + 10;
				$('#sfile').attr("value",number);
				
			} else if(data['qnum']==0) {
				
				var number = nold + 20;
				$('#nfile').attr("value",number);
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
				$('#next2').show();
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
	
	var soldValue = Number($('#sfile').attr("value"));
	
	var noldValue = Number($('#nfile').attr("value"));
	
	$.ajax({
		url : "surveytwo2",
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
				
				var sold = soldValue - 20;
				
				$('#sfile').attr("value",sold);
			} else if (data['onum'] == 2) {
				
				var sold = soldValue - 10;
				$('#sfile').attr("value",sold);	
				
				var nold = noldValue - 10;
				
				$('#nfile').attr("value",nold);
			} else if (data['onum'] == 1) {
				
				var nold = noldValue - 10;
				$('#nfile').attr("value",nold);
				var sold = soldValue - 10;
				$('#sfile').attr("value",sold);	
				
			} else if (data['onum'] == 0) {
				
				var nold = noldValue - 20;
				$('#nfile').attr("value",nold);
			} else {
				console.log('-1');
			}
			
			var sold = Number($('#sfile').attr("value"));
			
			var nold = Number($('#nfile').attr("value"));
			
			
			if(data['qnum']==3) {
				
				var number = sold + 20;
				$('#sfile').attr("value",number);
			} else if(data['qnum']==2) {
				
				var number = sold + 10;
				$('#sfile').attr("value",number);
				var number = nold + 10;
				$('#nfile').attr("value",number);
				
			} else if(data['qnum']==1) {
				
				var number = nold + 10;
				$('#nfile').attr("value",number);
				var number = sold + 10;
				$('#sfile').attr("value",number);
				
			} else if(data['qnum']==0) {
				
				var number = nold + 20;
				$('#nfile').attr("value",number);
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
				$('#next2').show();
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
	
	var soldValue = Number($('#sfile').attr("value"));
	
	var noldValue = Number($('#nfile').attr("value"));
	
	$.ajax({
		url : "surveytwo2",
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
				
				var sold = soldValue - 20;
				
				$('#sfile').attr("value",sold);
			} else if (data['onum'] == 2) {
				
				var sold = soldValue - 10;
				$('#sfile').attr("value",sold);	
				
				var nold = noldValue - 10;
				
				$('#nfile').attr("value",nold);
			} else if (data['onum'] == 1) {
				
				var nold = noldValue - 10;
				$('#nfile').attr("value",nold);
				var sold = soldValue - 10;
				$('#sfile').attr("value",sold);	
				
			} else if (data['onum'] == 0) {
				
				var nold = noldValue - 20;
				$('#nfile').attr("value",nold);
			} else {
				console.log('-1');
			}
			
			var sold = Number($('#sfile').attr("value"));
			
			var nold = Number($('#nfile').attr("value"));
			
			
			if(data['qnum']==3) {
				
				var number = sold + 20;
				$('#sfile').attr("value",number);
			} else if(data['qnum']==2) {
				
				var number = sold + 10;
				$('#sfile').attr("value",number);
				var number = nold + 10;
				$('#nfile').attr("value",number);
				
			} else if(data['qnum']==1) {
				
				var number = nold + 10;
				$('#nfile').attr("value",number);
				var number = sold + 10;
				$('#sfile').attr("value",number);
				
			} else if(data['qnum']==0) {
				
				var number = nold + 20;
				$('#nfile').attr("value",number);
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
				$('#next2').show();
			}
			
		},
		error: function(data) {
		alert('ajax 실패!');
		}
	});
})



function sbutton2() {
	$.ajax({
		url : "sbutton2",
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