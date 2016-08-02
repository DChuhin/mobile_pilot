;(function () {
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

    function userService($http, API, auth) {
        var self = this;
        self.getQuote = function () {
            return $http.get(API + '/auth/quote')
        };
        // add authentication methods here
        self.login = function (username, password) {
            return $http.post(API + '/token?username=' + username + "&password=" + password)
        };

    }

// We won't touch anything in here
    function MainCtrl(user, auth) {
        var self = this;

        function handleRequest(res) {
            var token = res.data ? res.data.token : null;
            if (token) {
                console.log('JWT:', token);
            }
            self.message = res.data.message;
        }

        self.login = function () {
            user.login(self.username, self.password)
                .then(handleRequest, handleRequest)
        };
        self.logout = function () {
            auth.logout && auth.logout()
        };
        self.isAuthed = function () {
            return auth.isAuthed ? auth.isAuthed() : false
        }
    }

    angular.module('app', [])
        .factory('authInterceptor', authInterceptor)
        .service('user', userService)
        .service('auth', authService)
        .constant('API', 'http://localhost:8080')
        .config(function ($httpProvider) {
            $httpProvider.interceptors.push('authInterceptor');
        })
        .controller('Main', MainCtrl)
})();