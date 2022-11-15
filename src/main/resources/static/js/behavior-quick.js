window.onload = function() { makeCategorySelect(); fillDate(); }
function reload() { location.reload(); }

function fillDate() {

    let today = new Date();
    let todayMon = today.getMonth() + 1;
    let todayStr = toCalendarFormat(today.getFullYear(), todayMon, today.getDate());
    let todayHour = today.getHours();
    let todayMin = parseInt(today.getMinutes() / 10) * 10;

    document.getElementById("startCalendar").value = todayStr;
    document.getElementById("endCalendar").value = todayStr;
    document.getElementById("startHour").value = todayHour;
    document.getElementById("endHour").value = todayHour;
    document.getElementById("startMin").value = todayMin;
    document.getElementById("endMin").value = todayMin;
}

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

function toCalendarFormat(year, month, day) {

    let monthStr = String(month);
    let dayStr = String(day);

    if (month <= 9) monthStr = "0" + month;
    if (day <= 9) dayStr = "0" + day;

    return year + "-" + monthStr + "-" + dayStr;
}

function calculateDate(min) {
    
    let endHourEle = document.getElementById("endHour");
    let endMinEle = document.getElementById("endMin");
    let endCalendarEle = document.getElementById("endCalendar");
    let endCalendarDates = endCalendarEle.value.split("-");

    let endYear = Number( endCalendarDates[0] );
    let endMonth = Number( endCalendarDates[1] );
    let endDate = Number( endCalendarDates[2] );
    let endHour = Number( endHourEle.value );
    let endMin = Number( endMinEle.value );

    let afterDate = new Date(endYear, endMonth - 1, endDate, endHour, endMin + min);
    let newEndMonth = afterDate.getMonth() + 1;
    let newEndCalendarVal = toCalendarFormat(afterDate.getFullYear(), newEndMonth, afterDate.getDate());

    endCalendarEle.value = newEndCalendarVal;
    endHourEle.value =afterDate.getHours();
    endMinEle.value = afterDate.getMinutes();
}

function resetDate() {

    let startCalendarDates = document.getElementById("startCalendar").value.split("-");

    let startYear = Number( startCalendarDates[0] );
    let startMonth = Number( startCalendarDates[1] );
    let startDate = Number( startCalendarDates[2] );
    let startHour = Number( document.getElementById("startHour").value );
    let startMinStr = document.getElementById("startMin").value;
    let startMin = Number( startMinStr );
    
    document.getElementById("endCalendar").value = toCalendarFormat(startYear, startMonth, startDate);
    document.getElementById("endHour").value = startHour;
    document.getElementById("endMin").value = startMin;
}

function saveBehavior() {
    let obj = new Object();

    let endCalendarEle = document.getElementById("endCalendar");
    let endCalendarDates = endCalendarEle.value.split("-");
    let endYear = Number( endCalendarDates[0] );
    let endMonth = Number( endCalendarDates[1] );
    let endDate = Number( endCalendarDates[2] );

    let startCalendarEle = document.getElementById("startCalendar");
    let startCalendarDates = startCalendarEle.value.split("-");
    let startYear = Number( startCalendarDates[0] );
    let startMonth = Number( startCalendarDates[1] );
    let startDate = Number( startCalendarDates[2] );

    obj.categoryId = document.getElementById("categoryId").value;
    obj.startYear = startYear;
    obj.startMonth = startMonth;
    obj.startDate = startDate;
    obj.startHour = document.getElementById("startHour").value;
    obj.startMin = document.getElementById("startMin").value;
    obj.endYear = endYear;
    obj.endMonth = endMonth;
    obj.endDate = endDate;
    obj.endHour = document.getElementById("endHour").value;
    obj.endMin = document.getElementById("endMin").value;
    obj.detail = document.getElementById("detail").value;

    let nextUrl = "/records/main/" + startYear + "/" +  startMonth + "/" + startDate;
    let jsonObj = JSON.stringify(obj);
    saveBehaviorAjax(jsonObj, nextUrl);
}
function saveBehaviorAjax(jsonObj, nextUrl) {  // behavior.js와 동일 부분
    $.ajax({
		type: "post",
        url: "/api/behavior", 
        data: jsonObj,
		contentType: 'application/json',
        dataType: '',
		success: function(data) {
            if (data == "success") {
                alert('저장이 완료되었습니다.');
                window.location.href = nextUrl;
            }
            else if (data == "orderMiss") alert("끝 시간이 시작 시간보다 빠릅니다. 확인 후 다시 입력해주세요.");
            else alert("알 수 없는 오류가 발생했습니다. 다시 시도해주세요.");
        }, 
        error: function() {
			alert("에러가 발생했습니다.");
		}
    })
}