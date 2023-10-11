<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- https://www.highcharts.com/demo/highcharts/live-data -->
<html>
<head>
<style type="text/css">
.highcharts-figure,
.highcharts-data-table table {
    min-width: 360px;
    max-width: 800px;
    margin: 1em auto;
}

.highcharts-data-table table {
    font-family: Verdana, sans-serif;
    border-collapse: collapse;
    border: 1px solid #ebebeb;
    margin: 10px auto;
    text-align: center;
    width: 100%;
    max-width: 500px;
}

.highcharts-data-table caption {
    padding: 1em 0;
    font-size: 1.2em;
    color: #555;
}

.highcharts-data-table th {
    font-weight: 600;
    padding: 0.5em;
}

.highcharts-data-table td,
.highcharts-data-table th,
.highcharts-data-table caption {
    padding: 0.5em;
}

.highcharts-data-table thead tr,
.highcharts-data-table tr:nth-child(even) {
    background: #f8f8f8;
}

.highcharts-data-table tr:hover {
    background: #f1f7ff;
}

.ld-label {
    width: 200px;
    display: inline-block;
}

.ld-url-input {
    width: 500px;
}

.ld-time-input {
    width: 40px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
오늘의 리플 개수 : <span id="count"></span> <br>
<button id="ss">확인</button>
<div id="list"></div>
</body>
<!-- jQuery -->
<script src="/resources/vendor/jquery/jquery.min.js"></script>
<script>
$(function(){
	$("#ss").on("click",function(){
		setInterval(function(){
		$.ajax({
			type:"get",
			url:"/replies/today.json",
			success:function(result){
					console.log(result);
					$('#count').text(result);
				}			
		})
		
		$.ajax({
			type:"get",
			url:"/replies/todaylist",
			//contentType:"application/json; charset=utf-8",
			dataType:"JSON",
			success:function(result){
				console.log(result);
				var htmlStr="";
				for(var i=0;i<result.length;i++){
					htmlStr+=(i+1)+". "+result[i].reply+"("+result[i].replyer+")<br>"	
					console.log("뭐임",htmlStr);
				}
				//var aa=JSON.stringify(result)
				console.log("됐냐",htmlStr);
				$("#list").html(htmlStr);
			}
		})	
		},1000);
	});
});
</script>
<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/data.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>

<figure class="highcharts-figure">
    <div id="container"></div>
    <p class="highcharts-description">
		Datasets formatted in CSV or JSON can be fetched remotely using the
		<code>data</code> module. This chart shows how a chart can also be
		configured to poll against the remote source.
    </p>
</figure>

<div class="ld-row">
	<label class="ld-label">
		Enable Polling
	</label>
	<input type="checkbox" checked="checked" id="enablePolling"/>
</div>
<div class="ld-row">
	<label class="ld-label">
		Polling Time (Seconds)
	</label>
	<input class="ld-time-input" type="number" value="2" id="pollingTime"/>
</div>
<div class="ld-row">
	<label class="ld-label">
		CSV URL
	</label>
	<input class="ld-url-input" type="text" id="fetchURL"/>
</div>

<script>
var defaultData = 'https://demo-live-data.highcharts.com/time-data.csv';
var urlInput = document.getElementById('fetchURL');
var pollingCheckbox = document.getElementById('enablePolling');
var pollingInput = document.getElementById('pollingTime');

function createChart() {
    Highcharts.chart('container', {
        chart: {
            type: 'spline'
        },
        title: {
            text: 'Live Data'
        },
        accessibility: {
            announceNewData: {
                enabled: true,
                minAnnounceInterval: 15000,
                announcementFormatter: function (allSeries, newSeries, newPoint) {
                    if (newPoint) {
                        return 'New point added. Value: ' + newPoint.y;
                    }
                    return false;
                }
            }
        },
        data: {
            csvURL: urlInput.value,
            enablePolling: pollingCheckbox.checked === true,
            dataRefreshRate: parseInt(pollingInput.value, 10)
        }
    });

    if (pollingInput.value < 1 || !pollingInput.value) {
        pollingInput.value = 1;
    }
}

urlInput.value = defaultData;

// We recreate instead of using chart update to make sure the loaded CSV
// and such is completely gone.
pollingCheckbox.onchange = urlInput.onchange = pollingInput.onchange = createChart;

// Create the chart
createChart();

</script>


</html>