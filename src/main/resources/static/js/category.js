window.onload = function() { statusCheck(); makeCategoryTable(); }


// 카테고리를 추가할 경우
function statusCheck() {
    let urlParams = new URLSearchParams(location.search)
    let status = urlParams.get('status');
    if (status == "over") {
        alert("카테고리 12개가 이미 다 차있기 때문에 새로 카테고리를 추가할 수 없습니다. \n"
           + "먼저 삭제를 한 뒤 추가를 해주세요.");
        location.replace("/category");
    } else if(status == "success") {
        alert("저장이 정상적으로 완료되었습니다.");
        location.replace("/category");
    } else if(status == "dupli") {
        alert("이미 존재하는 카테고리입니다. 새로운 항목을 입력해주세요.");
        location.replace("/category");
    }
        

}

// 사용자의 priority category 가져옴
function makeCategoryTable() {
    $.ajax({
        url: '/categories', 
        data: '',
		method: 'GET',
		dataType: 'json',
		success: function(data) {
            makeCategoryTableSuccess(data);
        }, 
        error: function() {
			alert("데이터를 가져오는 중 에러가 발생했습니다.");
		}
    })
}
function makeCategoryTableSuccess(data) {
    let categoryLength = data.length;
    let categoryListElem = document.getElementById("categoryList");
    categoryListElem.innerHTML = '';

    for (var i = 0; i < categoryLength; i++) {
        // 요소 생성
        let categoryBtnDel = document.createElement("button");
        let categoryBtnUp = document.createElement("button");
        let categoryBtnDown = document.createElement("button");

        //div 생성
        let divRow = document.createElement("div");
        let divColIdx = document.createElement("div");
        let divColContent = document.createElement("div");
        let divColDelBtn = document.createElement("div");
        let divColUpBtn = document.createElement("div");
        let divColDownBtn = document.createElement("div");
        divRow.setAttribute('class', 'row mb-3');
        divColIdx.setAttribute('class', 'col-1');
        divColContent.setAttribute('class', 'col');
        divColDelBtn.setAttribute('class', 'col-2');
        divColUpBtn.setAttribute('class', 'col-2');
        divColDownBtn.setAttribute('class', 'col-2');

        // 변수
        let categoryId = data[i].id;
        let categoryName = data[i].content;
        let idx = i;

        // 요소에 내용 추가
        divColIdx.textContent = idx + 1;
        divColContent.textContent = categoryName;
        divColContent.id ="categoryTdId" + i;
        categoryBtnDel.innerHTML = "삭제" + data[i].id;
        categoryBtnDel.onclick = function() { confirmDeleteCategory(categoryId); };
        categoryBtnUp.innerText = "위로";
        categoryBtnUp.onclick = function() { upCategory(categoryId); };
        categoryBtnDown.innerText = "아래로";
        categoryBtnDown.onclick = function() { downCategory(categoryId); };

        // 붙이기
        divColDelBtn.appendChild(categoryBtnDel);
        divColUpBtn.appendChild(categoryBtnUp);
        divColDownBtn.appendChild(categoryBtnDown);
        divRow.append(divColIdx, divColContent, divColUpBtn, divColDownBtn, divColDelBtn);
        categoryListElem.appendChild(divRow);

        // 디자인: BootStrap 내용
        categoryBtnDel.setAttribute('class', 'btn btn-secondary btn-outline-danger');
        categoryBtnUp.setAttribute('class', 'btn btn-secondary');
        categoryBtnDown.setAttribute('class', 'btn btn-secondary');
    }
}

// 버튼: 카테고리 삭제
function confirmDeleteCategory(categoryId) {
    if (confirm("해당 카테고리를 삭제하시겠습니까? 카테고리 항목에서만 삭제됩니다. 이미 저장된 기록의 카테고리에는 영향을 주지 않습니다.")) {
        deleteCategory(categoryId);
    }
}
function deleteCategory(categoryId) {
    $.ajax({
		type: "delete",
        url: "/categories", 
        data: categoryId + "",
		contentType: 'text/plain',
        dataType: '',
		success: function(data) {
            alert("삭제가 완료되었습니다.");
            makeCategoryTable();
        }, 
        error: function() {
			alert("에러가 발생했습니다.");
		}
    })
}

// 버튼: 카테고리 위로
function upCategory(categoryId) {
    let obj = new Object();
    obj.categoryId = categoryId;
    obj.direction = "up";
    let jsonObj = JSON.stringify(obj);
    
    $.ajax({
		type: "put",
        url: "/categories", 
        data: jsonObj,
		contentType: 'application/json',
        dataType: '',
		success: function(data) {
            makeCategoryTable();
        }, 
        error: function() {
			alert("에러가 발생했습니다.");
		}
    })
}

function downCategory(categoryId) {
    alert(categoryId);
}





/*
// 이후 지우기
// 카테고리 이름 수정
function modifyCategory(idx, categoryName, categoryId) {
    // 테이블에 있는 카테고리 이름 가져와서, type과 id 변경
    let categoryNameDom = document.getElementById("categoryTdId" + idx);
    categoryNameDom.innerHTML = "<input type='text' id='categoryTextId" + idx + "' " 
                                + "placeholder='" + categoryName + "' " 
                                + "onkeydown='reqModifyCategory(event, "+ idx + ", " + categoryId +")' " + "'>";
}
// 카테고리 이름 수정 - 요청
function reqModifyCategory(event, idx, categoryId) {
    // 엔터 누를 경우 실행
    if (event.keyCode == 13) {
        let newCategoryName = document.getElementById("categoryTextId" + idx).value;
        alert(categoryId + " " + newCategoryName);
    }
}
*/