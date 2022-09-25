window.onload = function() { makeCategorySelect(); }

function makeCategorySelect() {
    $.ajax({
        url: '/categories', 
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
