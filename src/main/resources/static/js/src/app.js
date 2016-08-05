(function () {
    function authInterceptor(API, auth) {
        return {
            // automatically attach Authorization header
            request: function (config) {
                var token = auth.getToken();
                if (config.url.indexOf(API) === 0 && token) {
                    config.headers['X-Pilot-Token'] = token;
                }

                return config;
            },

            // If a token was sent back, save it
            response: function (res) {
                if (res.config.url.indexOf(API) === 0 && res.data.token) {
                    auth.saveToken(res.data.token);
                }
                if (res.data === "Token expired date error") {
                    alert("Session expired. Login again");
                    auth.logout();
                }
                return res;
            }
        }
    }

    function authService($window) {
        var self = this;


        self.saveToken = function (token) {
            $window.localStorage['jwtToken'] = token;
        };

        self.getToken = function () {
            return $window.localStorage['jwtToken'];
        };

        self.isAuthed = function () {
            return self.getToken();
        };

        self.logout = function () {
            $window.localStorage.removeItem('jwtToken');
        }
    }

    function userService($http, API) {
        var self = this;
        self.login = function (username, password) {
            return $http.post(API + '/token?username=' + username + "&password=" + password)
        };

    }

    var app = angular.module('app', ['ui.router'])
        .factory('authInterceptor', authInterceptor)
        .service('user', userService)
        .service('auth', authService)
        .constant('API', 'http://localhost:8080')
        .config(function ($httpProvider) {
            $httpProvider.interceptors.push('authInterceptor');
        })
})();