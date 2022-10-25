window.onload = function() {
    // 오늘의 기록을 확인할 수 있도록, href 주소 변경
    let today = new Date();
    let month = today.getMonth() + 1;
    let todayUrl = "/" + today.getFullYear() + "/" + month + "/" + today.getDate();
    document.getElementById("behaviorMain").setAttribute("href", "/behavior/main" + todayUrl);

    getUserName();
}
// 사용자 이름 가져오기
function getUserName() {
    $.ajax({
        url: '/login/name', 
        data: '',
		method: 'GET',
		dataType: '',
		success: function(data) {
            document.getElementById("welcomeMessage").innerHTML = data + "님, 안녕하세요:)";
        }, 
        error: function() {
			alert("에러가 발생했습니다.");
		}
    })
}


// 로그인 세션 제거
function logoutBtnOnclicked() {
    if (confirm("로그아웃하시겠습니까?")) {
        destroyLoginSession();
    }
}
function destroyLoginSession() {
    $.ajax({
        url: '/destroylogin', 
        data: '',
		method: 'GET',
		dataType: '',
		success: function(data) {
            alert("로그인 세션이 제거되었습니다.");
            window.location.href =  "http://localhost:8080";
        }, 
        error: function() {
			alert("에러가 발생했습니다.");
		}
    })
}