window.onload = function() { makeCategoryTable(); }

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
        categoryBtnDel.onclick = function() { deleteCategory(categoryId); };
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
/*function makeCategoryTableSuccess(data) {
    let categoryLength = data.length;
    let categoryTableElem = document.getElementById("categoryTable");

    for (var i = 0; i < categoryLength; i++) {
        // 요소 생성
        let categoryTr = document.createElement("tr");
        let categoryTdName = document.createElement("td");
        let categoryTdDel = document.createElement("td");
        let categoryTdUp = document.createElement("td");
        let categoryTdDown = document.createElement("td");
        let categoryBtnDel = document.createElement("button");
        let categoryBtnUp = document.createElement("button");
        let categoryBtnDown = document.createElement("button");

        //div 생성
        let divRow = document.createElement("div");
        let divCol1 = document.createElement("div");
        let divColDelBtn = document.createElement("div");
        let divColUpBtn = document.createElement("div");
        divRow.setAttribute('class', 'row');
        divCol1.setAttribute('class', 'col');
        divColDelBtn.setAttribute('class', 'col');
        divColUpBtn.setAttribute('class', 'col');

        // 변수
        let categoryId = data[i].id;
        let categoryName = data[i].content;
        let idx = i;

        // 요소에 내용 추가
        categoryTdName.textContent = categoryName;
        categoryTdName.id ="categoryTdId" + i;
        categoryBtnDel.innerHTML = "삭제" + data[i].id;
        categoryBtnDel.onclick = function() { deleteCategory(categoryId); };
        categoryBtnUp.innerText = "위로";
        categoryBtnUp.onclick = function() { upCategory(categoryId); };
        categoryBtnDown.innerText = "아래로";
        categoryBtnDown.onclick = function() { downCategory(categoryId); };

        // 붙이기
        categoryTdDel.appendChild(categoryBtnDel);
        categoryTdUp.append(categoryBtnUp);
        categoryTdDown.append(categoryBtnDown);
        categoryTr.append(categoryTdName, categoryTdUp, categoryTdDown, categoryTdDel);
        categoryTableElem.appendChild(categoryTr);

        // 디자인: BootStrap 내용
        categoryBtnDel.setAttribute('class', 'btn btn-secondary btn-outline-danger');
        categoryBtnUp.setAttribute('class', 'btn btn-secondary');
        categoryBtnDown.setAttribute('class', 'btn btn-secondary');
    }
}*/

function deleteCategory(categoryId) {
    alert(categoryId);
}

function upCategory(categoryId) {
    alert(categoryId);
}

function downCategory(categoryId) {
    alert(categoryId);
}





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