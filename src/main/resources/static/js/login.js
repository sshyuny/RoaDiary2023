
// 로그인 세션 만들기
function makeLoginSession() {
    $.ajax({
        url: '/trylogin', 
        data: '',
		method: 'GET',
		dataType: '',
		success: function(data) {
            window.location.href =  "/";
        }, 
        error: function() {
			alert("에러가 발생했습니다.");
		}
    })
}

function kakaoLogin() {
    window.location.href = "/login/req/kakao";
}