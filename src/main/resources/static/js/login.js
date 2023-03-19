
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
    window.location.href =  "https://kauth.kakao.com/oauth/authorize?client_id=458fced8d5a842ded2c650f69075c3e8&redirect_uri=http://localhost/oauth/kakao&response_type=code";
    // window.location.href =  "https://kauth.kakao.com/oauth/authorize?client_id=458fced8d5a842ded2c650f69075c3e8&redirect_uri=http://sshyuny.com/oauth/kakao&response_type=code";
}