window.onload = function() {
    getUserName();
}
// 사용자 이름 가져오기(공통 사용)
function getUserName() {
    $.ajax({
        url: '/login/name', 
        data: '',
		method: 'GET',
		dataType: '',
		success: function(data) {
            document.getElementById("nickname").innerHTML = data + "님의 회원 정보";
        }, 
        error: function() {
			alert("에러가 발생했습니다.");
		}
    })
}

function modifyNickname() {
    alert("해당 기능은 현재 개발 중입니다.");
}


function agreeAndWithdrawal() {
    if (confirm("탈퇴하시겠습니까?")) {
        withdrawal();
    }
}
function withdrawal() {
    $.ajax({
		type: "delete",
        url: "/api/member/withdrawal", 
        data: '',
        dataType: '',
		success: function(data) {
            if (data == "guestAccount") {
                alert("방문자용 계정은 탈퇴하실 수 없습니다.");
                window.location.href = "/";
            } else if (data == "success") {
                alert("탈퇴가 정상적으로 완료되었습니다.");
                window.location.href = "/";
            } else if (data == "fail") alert("알 수 없는 오류로 인해 탈퇴가 정상적으로 처리되지 못했습니다. 다시 시도해주세요.");
        }, 
        error: function() {
			alert("에러가 발생했습니다. 다시 시도해주세요.");
		}
    })
}