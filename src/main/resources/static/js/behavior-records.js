window.onload = function() { getRecordsTable(); }

function getRecordsTable() {
    const url = window.location.pathname;
    let urls = url.split("/");
    document.getElementById("aboveDate").textContent = urls[3] + "." + urls[4] + "." + urls[5] + ".";
    makeRecordsTable(urls[3] + "/" + urls[4] + "/" + urls[5]);
}

function makeRecordsTable(dayUrl) {
    $.ajax({
        url: '/behavior/records/' + dayUrl, 
        data: '',
		method: 'GET',
		dataType: 'json',
		success: function(data) {
            makeRowsSuccess(data);
        }, 
        error: function() {
			alert("데이터를 가져오는 중 에러가 발생했습니다.");
		}
    })
}

function makeRowsSuccess(data) {
    let records = document.getElementById("records");
    let length = data.length;
    for (let i = 0; i < length; i++) {

        // 기본 div 생성
        let divRow = document.createElement("div");
        let divColTime = document.createElement("div");
        let divColContent = document.createElement("div");
        let divColDetail = document.createElement("div");

        divRow.setAttribute('id', 'divRow' + i);
        divRow.setAttribute('class', 'row justify-content-md-center mb-3');
        divColTime.setAttribute('class', 'col-4 d-grid');
        divColContent.setAttribute('class', 'col-4 d-grid');
        divColDetail.setAttribute('class', 'col-4 d-grid');

        // Btn 생성
        let timeBtn = document.createElement("button");
        let contentBtn = document.createElement("button");
        let detailtBtn = document.createElement("button");

        timeBtn.innerHTML = data[i].startTime.substring(0, 5) + " ~ " + data[i].endTime.substring(0, 5);
        contentBtn.innerHTML = data[i].content;
        detailtBtn.innerHTML = data[i].detail;
        timeBtn.onclick = function() { timeBtnOnclick(data, i, data[i].startTime.substring(0, 5)); };
        contentBtn.onclick = function() { contentBtnOnclick(data, i); };

        timeBtn.setAttribute('id', 'timeBtn' + i);
        timeBtn.setAttribute('class', 'btn btn-secondary');
        contentBtn.setAttribute('id', 'contentBtn' + i);
        contentBtn.setAttribute('class', 'btn btn-secondary');
        detailtBtn.setAttribute('class', 'btn btn-secondary');

        // 붙이기
        divColTime.appendChild(timeBtn);
        divColContent.appendChild(contentBtn);
        divColDetail.appendChild(detailtBtn);
        divRow.append(divColTime, divColContent, divColDetail);
        records.appendChild(divRow);
    }
}

function timeBtnOnclick(data, i, preStartTime) {

    let divRow = document.getElementById("divRow" + i);

    // 기본 div 생성
    let newForm = document.createElement("form");
    newForm.setAttribute("charset", "UTF-8");
    newForm.setAttribute("method", "Post");

    let divNewRow = document.createElement("div");
    let divNewCol = document.createElement("div");
    let divNewColSubmit = document.createElement("div");
    let divNewColDel = document.createElement("div");
    divNewRow.setAttribute('class', 'row justify-content-md-center mt-3');
    divNewCol.setAttribute('class', 'col-4 d-grid');
    divNewColSubmit.setAttribute('class', 'col-2 d-grid');
    divNewColDel.setAttribute('class', 'col-2 d-grid');

    // input text, button 생성
    let hidden = document.createElement("input");
    let startInput = document.createElement("input");
    let endInput = document.createElement("input");
    let submitBtn = document.createElement("button");
    let delBtn = document.createElement("button");
    hidden.setAttribute('type', 'hidden');
    hidden.setAttribute('name', 'preStartTime');
    hidden.setAttribute('value', preStartTime);
    startInput.setAttribute('class', 'form-control');
    startInput.setAttribute('name', 'startTime');
    endInput.setAttribute('class', 'form-control');
    endInput.setAttribute('name', 'endTime');
    submitBtn.setAttribute('class', 'btn btn-primary');
    delBtn.setAttribute('class', 'btn btn-danger');
    submitBtn.innerHTML = "수정";
    delBtn.innerHTML = "삭제";
    submitBtn.onclick = function() { newForm.submit(); };

    // 붙이기
    newForm.append(startInput, endInput, hidden);
    divNewCol.append(newForm);
    divNewColSubmit.append(submitBtn);
    divNewColDel.append( delBtn);
    divNewRow.append(divNewCol, divNewColSubmit, divNewColDel);
    divRow.appendChild(divNewRow);
    
    // timeBtn 버튼 수정
    document.getElementById("timeBtn" + i).onclick = function() {
        // 다시 누르면, 방금 만든 input text 삭제
        divNewRow.remove();
        // 거기서 다시 누르면, input text 생성 function으로 다시 연결해줌
        document.getElementById("timeBtn" + i).onclick = function() { timeBtnOnclick(data, i); };
    };
}

function contentBtnOnclick(data, i) {

    let divRow = document.getElementById("divRow" + i);

    // 기본 div 생성
    let divNewRow = document.createElement("div");
    let divNewCol = document.createElement("div");
    divNewRow.setAttribute('class', 'row justify-content-md-center mt-3');
    divNewCol.setAttribute('class', 'col-6');

    // select 생성
    let select = document.createElement("select");
    select.setAttribute('class', 'form-select');
    select.setAttribute('aria-label', '.form-select');

    // 붙이기
    divNewCol.appendChild(select);
    divNewRow.appendChild(divNewCol);
    divRow.appendChild(divNewRow);
    
    // 카테고리 불러오기
    makeCategorySelect(select);

    // contentBtn 버튼 수정
    document.getElementById("contentBtn" + i).onclick = function() {
        // 다시 누르면, 방금 만든 input text 삭제
        divNewRow.remove();
        // 거기서 다시 누르면, input text 생성 function으로 다시 연결해줌
        document.getElementById("contentBtn" + i).onclick = function() { contentBtnOnclick(data, i); };
    };

}
function makeCategorySelect(select) {  //behavior.js와 동일 부분
    $.ajax({
        url: '/category/priority', 
        data: '',
		method: 'GET',
		dataType: 'json',
		success: function(data) {
            let categoryLength = data.length;
            for (var i = 0; i < categoryLength; i++) {
                let categoryOne = document.createElement("option");
                categoryOne.value = data[i].id;
                categoryOne.text = data[i].content;
                select.append(categoryOne);
            }
        }, 
        error: function() {
			alert("데이터를 가져오는 중 에러가 발생했습니다.");
		}
    })
}