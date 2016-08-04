var app = angular.module('app');
app.controller('dashboardController', ['$scope', '$http', 'API', 'chart', function ($scope, $http, API, chart) {
    var self = this;

    self.filterParams = {
        startDate: null,
        endDate: null,
        advertises: [],
        channels: [],
        mode: 'DAY'
    };

    function setAdvertises(res) {
        self.advertises = res.data;
    }

    $http.get(API + '/api/advertises').then(setAdvertises, setAdvertises);

    function assignChartData(res) {
        self.chartData = res.data;
    }

    self.getReport = function () {
        $http({
            url: API + '/api/logs/chart',
            method: 'GET',
            params: self.filterParams
        }).then(chart.buildChart, chart.buildChart);
    };

    self.toggleAdvertise = function (advertise) {
        var idx = self.filterParams.advertises.indexOf(advertise);
        idx > -1 ? self.filterParams.advertises.splice(idx, 1) : self.filterParams.advertises.push(advertise);
        self.getReport();
    };

    function convertToUtcMillis(date) {
        var offset = date.getTimezoneOffset() * 60000;
        return date.getTime() - offset;
    }

    self.setStartDate = function () {
        self.filterParams.startDate = convertToUtcMillis(self.startDate);
        self.getReport();
    };

    self.setEndDate = function () {
        var date = self.endDate;
        date.setDate(date.getDate() + 1);
        date.setMilliseconds(date.getMilliseconds() - 1);
        self.filterParams.endDate = convertToUtcMillis(date);
        self.getReport();
    };

    self.getReport();

}]);
