window.onload = function() { getRecordsTable(); }

function getRecordsTable() {
    const url = window.location.pathname;
    let urls = url.split("/");
    makeRecordsTable(urls[3] + "/" + urls[4] + "/" + urls[5]);
}

function makeRecordsTable(dayUrl) {
    alert(dayUrl);
    $.ajax({
        url: '/behavior/records/' + dayUrl, 
        data: '',
		method: 'GET',
		dataType: 'json',
		success: function(data) {
            makeRecordsTableSuccess(data);
        }, 
        error: function() {
			alert("데이터를 가져오는 중 에러가 발생했습니다.");
		}
    })
}

function makeRecordsTableSuccess(data) {
    alert("t");
}