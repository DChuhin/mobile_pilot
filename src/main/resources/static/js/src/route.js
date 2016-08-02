var app = angular.module('app');
app.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("/dashboard")

    $stateProvider
        .state('dashboard', {
            url: "/dashboard",
            templateUrl: "/dashboard"
        })
        .state('logs', {
            url: "/logs",
            templateUrl: "/logs"
        })
});