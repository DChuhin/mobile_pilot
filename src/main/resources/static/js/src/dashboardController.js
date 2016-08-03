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

    self.setStartDate = function () {
        self.filterParams.startDate = self.startDate.getTime();
        self.getReport();
    };

    self.setEndDate = function () {
        self.filterParams.endDate = self.endDate.getTime();
        self.getReport();
    };

    self.getReport();

}]);
