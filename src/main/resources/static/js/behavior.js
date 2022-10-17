window.onload = function() { makeCategorySelect(); addPlaceholder(); }

function makeCategorySelect() {
    $.ajax({
        url: '/category/priority', 
        data: '',
		method: 'GET',
		dataType: 'json',
		success: function(data) {
            let categoryLength = data.length;
            let categoryListSelect = document.getElementById("categoryId");
            for (var i = 0; i < categoryLength; i++) {
                let categoryOne = document.createElement("option");
                categoryOne.value = data[i].id;
                categoryOne.text = data[i].content;
                categoryListSelect.append(categoryOne);
            }
        }, 
        error: function() {
			alert("데이터를 가져오는 중 에러가 발생했습니다.");
		}
    })
}

// 날짜 placeholder 추가
function addPlaceholder() {
    let today = new Date();
    document.getElementById("startYear").setAttribute("placeholder", today.getFullYear());
    document.getElementById("startMonth").setAttribute("placeholder", today.getMonth() + 1);
    document.getElementById("startDate").setAttribute("placeholder", today.getDate());
}

// 시간 계산 버튼
function during30m() {
    let startHour = Number( document.getElementById("startHour").value ) ;
    let startMin = Number( document.getElementById("startMin").value );
    let endHour, endMin;

    if (startHour >= 23 && startMin > 25) {
        alert("시간이 다음 날로 넘어갑니다. 일상 저장 페이지에서 완료해주세요.");
        if (startMin == 0) startMin = "00";
        document.getElementById("endHour").value = startHour;
        document.getElementById("endMin").value = startMin;
        return;
    }

    if (startMin < 25) {
        endHour = startHour; endMin = startMin + 30;
    } else {
        endHour = startHour + 1; endMin = startMin - 30;
    }

    if (endMin == 0) endMin = "00";
    document.getElementById("endHour").value = endHour;
    document.getElementById("endMin").value = endMin;
}
function during1h() {
    let startHour = Number( document.getElementById("startHour").value ) ;
    let startMin = Number( document.getElementById("startMin").value );
    let endHour, endMin;

    if (startHour >= 23) {
        alert("시간이 다음 날로 넘어갑니다. 일상 저장 페이지에서 완료해주세요.");
        if (startMin == 0) startMin = "00";
        document.getElementById("endHour").value = startHour;
        document.getElementById("endMin").value = startMin;
        return;
    }

    endHour = startHour + 1; endMin = startMin;

    if (endMin == 0) endMin = "00";
    document.getElementById("endHour").value = endHour;
    document.getElementById("endMin").value = endMin;
}
function during1h30m() {
    let startHour = Number( document.getElementById("startHour").value ) ;
    let startMin = Number( document.getElementById("startMin").value );
    let endHour, endMin;

    if (startHour >= 22 && startMin > 25) {
        alert("시간이 다음 날로 넘어갑니다. 일상 저장 페이지에서 완료해주세요.");
        if (startMin == 0) startMin = "00";
        document.getElementById("endHour").value = startHour;
        document.getElementById("endMin").value = startMin;
        return;
    }

    if (startMin < 25) {
        endHour = startHour + 1; endMin = startMin + 30;
    } else {
        endHour = startHour + 2; endMin = startMin - 30;
    }

    if (endMin == 0) endMin = "00";
    document.getElementById("endHour").value = endHour;
    document.getElementById("endMin").value = endMin;
}
function during2h() {
    let startHour = Number( document.getElementById("startHour").value ) ;
    let startMin = Number( document.getElementById("startMin").value );
    let endHour, endMin;

    if (startHour >= 22) {
        alert("시간이 다음 날로 넘어갑니다. 일상 저장 페이지에서 완료해주세요.");
        if (startMin == 0) startMin = "00";
        document.getElementById("endHour").value = startHour;
        document.getElementById("endMin").value = startMin;
        return;
    }

    endHour = startHour + 2; endMin = startMin;

    if (endMin == 0) endMin = "00";
    document.getElementById("endHour").value = endHour;
    document.getElementById("endMin").value = endMin;
}
function during3h() {
    let startHour = Number( document.getElementById("startHour").value ) ;
    let startMin = Number( document.getElementById("startMin").value );
    let endHour, endMin;

    if (startHour >= 21) {
        alert("시간이 다음 날로 넘어갑니다. 일상 저장 페이지에서 완료해주세요.");
        if (startMin == 0) startMin = "00";
        document.getElementById("endHour").value = startHour;
        document.getElementById("endMin").value = startMin;
        return;
    }

    endHour = startHour + 3; endMin = startMin;

    if (endMin == 0) endMin = "00";
    document.getElementById("endHour").value = endHour;
    document.getElementById("endMin").value = endMin;
}
function during4h() {
    let startHour = Number( document.getElementById("startHour").value ) ;
    let startMin = Number( document.getElementById("startMin").value );
    let endHour, endMin;

    if (startHour >= 20) {
        alert("시간이 다음 날로 넘어갑니다. 일상 저장 페이지에서 완료해주세요.");
        if (startMin == 0) startMin = "00";
        document.getElementById("endHour").value = startHour;
        document.getElementById("endMin").value = startMin;
        return;
    }

    endHour = startHour + 4; endMin = startMin;

    if (endMin == 0) endMin = "00";
    document.getElementById("endHour").value = endHour;
    document.getElementById("endMin").value = endMin;
}