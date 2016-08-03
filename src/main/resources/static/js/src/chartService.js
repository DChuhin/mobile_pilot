var app = angular.module('app');
app.service('chart', function () {
    var self = this;

    self.buildChart = function (res) {
        $('#chart').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: 'Advertise views'
            },
            xAxis: {
                categories: res.data.categories,
                crosshair: true
            },
            yAxis: {
                min: 0,
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: res.data.series
        });
    }
});
