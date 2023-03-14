
function myFunc(){
	// 사용자가 선택한 url을 form의 action에 설정하는 작업을 해주기 - 확보해주기  
	let url = $("#myForm > select > option:selected").text();
	
	$("#myForm").attr("action",url);
	
}

function popuptest(){
	
	alert("test")
    var url = "http://localhost:8080/springmbti/resources/searchBattle.html";
    var name = "popup test";
    var option = "width = 500, height = 500, top = 100, left = 200, location = no"
    window.open(url, name, option);
}
