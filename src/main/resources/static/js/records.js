window.onload = function() { getRecordsTable(); }

let curUrlYear;
let curUrlMonth;
let curUrlDate;

function getRecordsTable() {
    const url = window.location.pathname;
    let urls = url.split("/");

    curUrlYear = urls[3];
    curUrlMonth = urls[4];
    curUrlDate = urls[5];

    document.getElementById("aboveDate").textContent = curUrlYear + "." + curUrlMonth + "." + curUrlDate + ".";
    makeRecordsTable(curUrlYear + "/" + curUrlMonth + "/" + curUrlDate);
}

function makeRecordsTable(dayUrl) {
    $.ajax({
        url: '/records/manage/' + dayUrl, 
        data: '',
		method: 'GET',
		dataType: 'json',
		success: function(data) {
            if (data.length >= 1) makeRowsSuccess(data);
        }, 
        error: function() {
			alert("데이터를 가져오는 중 에러가 발생했습니다.");
		}
    })
}

function makeRowsSuccess(data) {
    let records = document.getElementById("records");
    records.innerHTML = "";
    let length = data.length;
    for (let i = 0; i < length; i++) {

        // 기본 div 생성
        let divRow = document.createElement("div");
        let divColS = document.createElement("div");
        let divColM = document.createElement("div");

        divRow.setAttribute('id', 'divRow' + i);
        divRow.setAttribute('class', 'row justify-content-md-center mb-3');
        divColS.setAttribute('class', 'col-4 d-grid');
        divColM.setAttribute('class', 'col-8 d-grid border border-light rounded');

        // Btn 생성
        let timeBtn1 = document.createElement("button");
        let timeBtn2 = document.createElement("button");
        let contentBtn = document.createElement("p");
        let detailtBtn = document.createElement("span");

        timeBtn1.innerHTML = data[i].startTime.substring(0, 5);
        timeBtn2.innerHTML = data[i].endTime.substring(0, 5);
        contentBtn.innerHTML = data[i].content;
        detailtBtn.innerHTML = data[i].detail;
        
        contentBtn.setAttribute('class', 'fs-5 fw-bold');
        
        timeBtn1.setAttribute('type', 'button');
        timeBtn1.setAttribute('data-bs-toggle', 'modal');
        timeBtn1.setAttribute('data-bs-target', '#recordModal');
        timeBtn1.setAttribute('class', 'btn btn-outline-light');
        timeBtn2.setAttribute('type', 'button');
        timeBtn2.setAttribute('data-bs-toggle', 'modal');
        timeBtn2.setAttribute('data-bs-target', '#recordModal');
        timeBtn2.setAttribute('class', 'btn btn-outline-light');

        timeBtn1.onclick = function() { makeModal(data[i]); };
        timeBtn2.onclick = function() { makeModal(data[i]); };

        // 붙이기
        divColS.append(timeBtn1, timeBtn2);
        divColM.append(contentBtn, detailtBtn);
        divRow.append(divColS, divColM);
        records.append(divRow);
    }
}

function makeModal(dataI) {

    let contentModify = dataI.content;
    let startYearModify = curUrlYear;
    let startMonthModify = curUrlMonth;
    let startDateModify = curUrlDate;
    let startHourModify = dataI.startTime.substring(0, 2);
    let startMinModify = dataI.startTime.substring(3, 5);
    let endHourModify = dataI.endTime.substring(0, 2);
    let endMinModify = dataI.endTime.substring(3, 5);
    let detailModify = dataI.detail;

    makeCategorySelect(contentModify);
    document.getElementById("startYear").value = startYearModify;
    document.getElementById("startMonth").value = startMonthModify;
    document.getElementById("startDate").value = startDateModify;
    document.getElementById("startHour").value = startHourModify;
    document.getElementById("startMin").value = startMinModify;
    document.getElementById("endHour").value = endHourModify;
    document.getElementById("endMin").value = endMinModify;
    document.getElementById("detail").value = detailModify;

    document.getElementById("modifyBtn").onclick = function() { modifyRecord(dataI.behaviorRecordsId); };
    document.getElementById("deleteBtn").onclick = function() { confirmDeleteRecord(dataI.behaviorRecordsId); };

}

// Modal에서 카테고리 가져오기 
function makeCategorySelect(selectedCategory) {  //behavior.js와 일부 동일 부분
    let select = document.getElementById("categoryId");
    $.ajax({
        url: '/category/priority', 
        data: '',
		method: 'GET',
		dataType: 'json',
		success: function(data) {
            let categoryLength = data.length;
            for (let i = 0; i < categoryLength; i++) {
                let categoryOne = document.createElement("option");
                categoryOne.value = data[i].id;
                categoryOne.text = data[i].content;
                select.append(categoryOne);

                if (categoryOne.text == selectedCategory) categoryOne.selected = true;
            }
        }, 
        error: function() {
			alert("데이터를 가져오는 중 에러가 발생했습니다.");
		}
    })
}

// 수정된 기록 JSON으로 만들기
function modifyRecord(behaviorRecordsId) {

    let jsonObj = makeJson(behaviorRecordsId);
    
    $.ajax({
		type: "put",
        url: "/records/manage/" + curUrlYear + "/" + curUrlMonth + "/" + curUrlDate, 
        data: jsonObj,
		contentType: 'application/json',
        dataType: '',
		success: function(data) {
            alert("수정이 완료되었습니다.");
            document.getElementById("records").innerHTML = "";
            getRecordsTable();
        }, 
        error: function() {
			alert("에러가 발생했습니다.");
		}
    })

}
function makeJson(behaviorRecordsId) {

    let obj = new Object();
    obj.behaviorRecordsId = behaviorRecordsId;
    obj.behaviorCategoryId = document.getElementById("categoryId").value;
    obj.startYear = document.getElementById("startYear").value;
    obj.startMonth = document.getElementById("startMonth").value;
    obj.startDate = document.getElementById("startDate").value;
    obj.startHour = document.getElementById("startHour").value;
    obj.startMin = document.getElementById("startMin").value;
    obj.endHour = document.getElementById("endHour").value;
    obj.endMin = document.getElementById("endMin").value;
    obj.detail = document.getElementById("detail").value;

    return JSON.stringify(obj);
}

// 기록 삭제
function confirmDeleteRecord(behaviorRecordsId) {
    if (confirm("이 기록을 삭제하시겠습니까?")) {
        deleteRecord(behaviorRecordsId);
    }
}
function deleteRecord(behaviorRecordsId) {
    $.ajax({
		type: "delete",
        url:  "/records/manage/" + curUrlYear + "/" + curUrlMonth + "/" + curUrlDate, 
        data: behaviorRecordsId + "",
		contentType: 'text/plain',
        dataType: '',
		success: function(data) {
            alert("삭제가 완료되었습니다.");
            document.getElementById("modalClose").click();
            document.getElementById("records").innerHTML = "";
            getRecordsTable();
        }, 
        error: function() {
			alert("에러가 발생했습니다.");
		}
    })
}

// 어제 버튼
function toYesterday() {
    let yesterdayTime = new Date(curUrlYear, curUrlMonth - 1, curUrlDate).getTime() - 86400000;
    let yesterday = new Date(yesterdayTime);
    let month = yesterday.getMonth() + 1;
    location.replace("/records/main/" + 
        yesterday.getFullYear() + "/" + 
        month + "/" + 
        yesterday.getDate());
}

// 내일 버튼
function toTomorrow() {
    let tomorrowTime = new Date(curUrlYear, curUrlMonth - 1, curUrlDate).getTime() + 86400000;
    let tomorrow = new Date(tomorrowTime);
    let month = tomorrow.getMonth() + 1;
    location.replace("/records/main/" + 
        tomorrow.getFullYear() + "/" + 
        month + "/" + 
        tomorrow.getDate());
}