var app = angular.module('app');
app.filter('cmdate', [
    '$filter', function ($filter) {
        return function (input) {
            return $filter('date')(new Date(input).toLocaleDateString());
        };
    }
]);
app.controller('logController', ['$scope', '$http', 'API', function ($scope, $http, API) {
    var self = this;

    function assignLogs(res) {
        self.logs = res.data;
    }

    self.getLogs = function () {
        $http.get(API + '/api/logs').then(assignLogs, assignLogs)
    }
    self.getLogs();
}]);
