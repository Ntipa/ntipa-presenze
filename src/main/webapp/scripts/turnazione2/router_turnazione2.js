'use strict';

ntipapresenzeApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/turnazione2', {
                    templateUrl: 'views/turnazione2s.html',
                    controller: 'Turnazione2Controller',
                    resolve:{
                        resolvedTurnazione2: ['Turnazione2', function (Turnazione2) {
                            return Turnazione2.query().$promise;
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
