window.onload = function() { makeCategorySelect(); addTodayValue(); }
function reload() { location.reload(); }

function makeCategorySelect() {
    $.ajax({
        url: '/api/category/priority', 
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

// text들 오늘 날짜로 설정
function addTodayValue() {

    let today = new Date();

    addTodayValueEach(document.getElementById("startYear"), today.getFullYear());
    addTodayValueEach(document.getElementById("startMonth"), today.getMonth() + 1);
    addTodayValueEach(document.getElementById("startDate"), today.getDate());
    addTodayValueEach(document.getElementById("startHour"), today.getHours());
    addTodayValueEach(document.getElementById("startMin"), today.getMinutes());

    addTodayValueEach(document.getElementById("endYear"), today.getFullYear());
    addTodayValueEach(document.getElementById("endMonth"), today.getMonth() + 1);
    addTodayValueEach(document.getElementById("endDate"), today.getDate());
    addTodayValueEach(document.getElementById("endHour"), today.getHours());
    addTodayValueEach(document.getElementById("endMin"), today.getMinutes());
}
function addTodayValueEach(dom, todayDetail) {
    dom.setAttribute("value", todayDetail);
    dom.onclick = function(e) { e.target.value = "";};
}

function calculateDate(min) {
    let endYear = Number( document.getElementById("endYear").value );
    let endMonth = Number( document.getElementById("endMonth").value );
    let endDate = Number( document.getElementById("endDate").value );
    let endHour = Number( document.getElementById("endHour").value );
    let endMin = Number( document.getElementById("endMin").value );

    let afterDate = new Date(endYear, endMonth - 1, endDate, endHour, endMin + min);

    document.getElementById("endYear").value = afterDate.getFullYear();
    document.getElementById("endMonth").value = afterDate.getMonth() + 1;
    document.getElementById("endDate").value = afterDate.getDate();
    document.getElementById("endHour").value =afterDate.getHours();
    document.getElementById("endMin").value = afterDate.getMinutes();
}

function resetDate() {
    let startYear = Number( document.getElementById("startYear").value );
    let startMonth = Number( document.getElementById("startMonth").value );
    let startDate = Number( document.getElementById("startDate").value );
    let startHour = Number( document.getElementById("startHour").value );
    let startMin = Number( document.getElementById("startMin").value );

    let afterDate = new Date(startYear, startMonth - 1, startDate, startHour, startMin);

    document.getElementById("endYear").value = afterDate.getFullYear();
    document.getElementById("endMonth").value = afterDate.getMonth() + 1;
    document.getElementById("endDate").value = afterDate.getDate();
    document.getElementById("endHour").value =afterDate.getHours();
    document.getElementById("endMin").value = afterDate.getMinutes();
}

function saveBehavior() {
    let obj = new Object();

    let startYear = document.getElementById("startYear").value;
    let startMonth = document.getElementById("startMonth").value;
    let startDate = document.getElementById("startDate").value;
    let startHour = document.getElementById("startHour").value;
    let startMin = document.getElementById("startMin").value;
    if (isItNull(startYear)) return;
    if (isItNull(startMonth)) return;
    if (isItNull(startDate)) return;
    if (isItNull(startHour)) return;
    if (isItNull(startMin)) return;

    let endYear = document.getElementById("endYear").value;
    let endMonth = document.getElementById("endMonth").value;
    let endDate = document.getElementById("endDate").value;
    let endHour = document.getElementById("endHour").value;
    let endMin = document.getElementById("endMin").value;
    if (isItNull(endYear)) return;
    if (isItNull(endMonth)) return;
    if (isItNull(endDate)) return;
    if (isItNull(endHour)) return;
    if (isItNull(endMin)) return;

    obj.categoryId = document.getElementById("categoryId").value;
    obj.startYear = startYear;
    obj.startMonth = startMonth;
    obj.startDate = startDate;
    obj.startHour = startHour;
    obj.startMin = startMin;
    obj.endYear = endYear;
    obj.endMonth = endMonth;
    obj.endDate = endDate;
    obj.endHour = endHour;
    obj.endMin = endMin;
    obj.detail = document.getElementById("detail").value;

    let jsonObj = JSON.stringify(obj);
    saveBehaviorAjax(jsonObj);
}
function isItNull(validatedVal) {
    if (validatedVal == "" || validatedVal == null) {
        alert("비어있는 날짜 또는 시간을 채워주세요.");
        return true;
    }
}
function saveBehaviorAjax(jsonObj) {
    $.ajax({
		type: "post",
        url: "/api/behavior", 
        data: jsonObj,
		contentType: 'application/json',
        dataType: '',
		success: function(data) {
            if (data == "success") alert('저장이 완료되었습니다.');
            else if (data == "orderMiss") alert("끝 시간이 시작 시간보다 빠릅니다. 확인 후 다시 입력해주세요.");
            else alert("알 수 없는 오류가 발생했습니다. 다시 시도해주세요.");
        }, 
        error: function() {
			alert("에러가 발생했습니다.");
		}
    })
}