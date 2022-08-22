
// 로그인 세션 만들기
function makeLoginSession() {
    $.ajax({
        url: '/trylogin', 
        data: '',
		method: 'GET',
		dataType: '',
		success: function(data) {
            alert("로그인 세션이 생성되었습니다.");
            window.location.href =  "http://localhost:8080" + data;
        }, 
        error: function() {
			alert("에러가 발생했습니다.");
		}
    })
}

// 로그인 세션 제거
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