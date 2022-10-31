window.onload = function() { 
    makeCategoryTable(); 

    // 이벤트 추가
    let categoryContentEle = document.getElementById("categoryContent");
    categoryContentEle.addEventListener("keydown", event => pressEnterToPostCategory(event));
}


// 카테고리 추가
function postCategory() {
    let categoryContent = document.getElementById("categoryContent").value;
    $.ajax({
		type: "post",
        url: "/category/priority", 
        data: categoryContent + "",
		contentType: 'text/plain',
        dataType: '',
		success: function(data) {
            statusCheck(data);
            makeCategoryTable();
        }, 
        error: function() {
			alert("에러가 발생했습니다.");
		}
    })
}
function statusCheck(resData) {
    if (resData == "over") {
        alert("카테고리 12개가 이미 다 차있기 때문에 새로 카테고리를 추가할 수 없습니다. \n"
           + "먼저 삭제를 한 뒤 추가를 해주세요.");
        location.replace("/category");
    } else if(resData == "dupli") {
        alert("이미 존재하는 카테고리입니다. 새로운 항목을 입력해주세요.");
        location.replace("/category");
    } else if(resData == "success") {
        location.replace("/category");
    } 
}
// 카테고리 추가 - 엔터 누른 경우
function pressEnterToPostCategory(event) {
    if (event.key === 'Enter') postCategory();
}

// 사용자의 priority category 가져옴
function makeCategoryTable() {
    $.ajax({
        url: '/category/priority', 
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
        divRow.setAttribute('class', 'row justify-content-md-center mb-3');
        divColIdx.setAttribute('class', 'col-1');
        divColContent.setAttribute('class', 'col-5 ');
        divColDelBtn.setAttribute('class', 'col-2');
        divColUpBtn.setAttribute('class', 'col-2');
        divColDownBtn.setAttribute('class', 'col-2');

        // 변수
        let categoryId = data[i].id;
        let categoryName = data[i].content;
        let idx = i;

        // 요소에 내용 추가
        divColIdx.textContent = idx + 1 + ".";
        divColContent.textContent = categoryName;
        divColContent.id ="categoryTdId" + i;
        categoryBtnDel.innerHTML = "❌";
        categoryBtnDel.onclick = function() { confirmDeleteCategory(categoryId); };
        categoryBtnUp.innerText = "👆";
        categoryBtnUp.onclick = function() { upCategory(categoryId); };
        categoryBtnDown.innerText = "👇";
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
        url: "/category/priority", 
        data: categoryId + "",
		contentType: 'text/plain',
        dataType: '',
		success: function(data) {
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
        url: "/category/priority", 
        data: jsonObj,
		contentType: 'application/json',
        dataType: '',
		success: function(data) {
            if (data == "0") alert("더이상 위로 올릴 수 없습니다.");
            makeCategoryTable();
        }, 
        error: function() {
			alert("에러가 발생했습니다.");
		}
    })
}

function downCategory(categoryId) {
    let obj = new Object();
    obj.categoryId = categoryId;
    obj.direction = "down";
    let jsonObj = JSON.stringify(obj);

    $.ajax({
		type: "put",
        url: "/category/priority", 
        data: jsonObj,
		contentType: 'application/json',
        dataType: '',
		success: function(data) {
            if (data == "0") alert("더이상 아래로 내릴 수 없습니다.");
            makeCategoryTable();
        }, 
        error: function() {
			alert("에러가 발생했습니다.");
		}
    })
}
