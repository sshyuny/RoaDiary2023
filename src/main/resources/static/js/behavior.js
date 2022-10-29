window.onload = function() { makeCategorySelect(); addPlaceholder(); }
function reload() { location.reload(); }

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
    
    let startYear = document.getElementById("startYear");
    let startMonth = document.getElementById("startMonth");
    let startDate = document.getElementById("startDate");
    let startHour = document.getElementById("startHour");
    let startMin = document.getElementById("startMin");

    let endYear = document.getElementById("endYear");
    let endMonth = document.getElementById("endMonth");
    let endDate = document.getElementById("endDate");
    let endHour = document.getElementById("endHour");
    let endMin = document.getElementById("endMin");

    repeateAddPlaceholder(startYear, today.getFullYear());
    repeateAddPlaceholder(startMonth, today.getMonth() + 1);
    repeateAddPlaceholder(startDate, today.getDate());
    repeateAddPlaceholder(startHour, today.getHours());
    repeateAddPlaceholder(startMin, today.getMinutes());

    repeateAddPlaceholder(endYear, today.getFullYear());
    repeateAddPlaceholder(endMonth, today.getMonth() + 1);
    repeateAddPlaceholder(endDate, today.getDate());
    repeateAddPlaceholder(endHour, today.getHours());
    repeateAddPlaceholder(endMin, today.getMinutes());
}
function repeateAddPlaceholder(dom, todayDetail) {
    dom.setAttribute("placeholder", todayDetail);
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
    obj.categoryId = document.getElementById("categoryId").value;
    obj.startYear = document.getElementById("startYear").value;
    obj.startMonth = document.getElementById("startMonth").value;
    obj.startDate = document.getElementById("startDate").value;
    obj.startHour = document.getElementById("startHour").value;
    obj.startMin = document.getElementById("startMin").value;
    obj.endYear = document.getElementById("endYear").value;
    obj.endMonth = document.getElementById("endMonth").value;
    obj.endDate = document.getElementById("endDate").value;
    obj.endHour = document.getElementById("endHour").value;
    obj.endMin = document.getElementById("endMin").value;
    obj.detail = document.getElementById("detail").value;

    let jsonObj = JSON.stringify(obj);
    saveBehaviorAjax(jsonObj);
}
function saveBehaviorAjax(jsonObj) {
    $.ajax({
		type: "post",
        url: "/behavior", 
        data: jsonObj,
		contentType: 'application/json',
        dataType: '',
		success: function(data) {
            alert('s');
        }, 
        error: function() {
			alert("에러가 발생했습니다.");
		}
    })
}