window.onload = function() { getCategory(); }

function getCategory() {
    $.ajax({
        url: '/categories', 
        data: '',
		method: 'GET',
		dataType: 'json',
		success: function(data) {
            let categoryLength = data.length;
            let categoryListSelect = document.getElementById("categoryList");
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

function saveBehaviorRecord() {
    let categoryListSelect = document.getElementById("categoryList");
    let whichCategory = categoryListSelect.value;
    alert(whichCategory);

    // 내용도 같이 받아서 서버로 전송하는 부분 추가
}