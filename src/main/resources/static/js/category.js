window.onload = function() { makeCategoryTable(); }

function makeCategoryTable() {
    $.ajax({
        url: '/categories', 
        data: '',
		method: 'GET',
		dataType: 'json',
		success: function(data) {
            let categoryLength = data.length;
            let categoryTableElem = document.getElementById("categoryTable");
            for (var i = 0; i < categoryLength; i++) {
                // 요소 생성
                let categoryTr = document.createElement("tr");
                let categoryTdName = document.createElement("td");
                let categoryTdDel = document.createElement("td");
                let categoryTdDelButton = document.createElement("button");

                // 요소에 내용 추가
                let categoryId = data[i].id;
                categoryTdName.textContent = data[i].content;
                categoryTdDelButton.innerHTML = "삭제" + data[i].id;
                categoryTdDelButton.onclick = function() {
                    deleteCategory(categoryId);
                };

                // 붙이기
                categoryTdDel.appendChild(categoryTdDelButton);
                categoryTr.append(categoryTdName, categoryTdDel);
                categoryTableElem.append(categoryTr);
            }
        }, 
        error: function() {
			alert("데이터를 가져오는 중 에러가 발생했습니다.");
		}
    })
}

function deleteCategory(categoryId) {
    alert(categoryId);
}
