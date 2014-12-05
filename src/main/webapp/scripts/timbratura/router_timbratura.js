'use strict';

ntipapresenzeApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/timbratura', {
                    templateUrl: 'views/timbraturas.html',
                    controller: 'TimbraturaController',
                    resolve:{
                        resolvedTimbratura: ['Timbratura', function (Timbratura) {
                            return Timbratura.query().$promise;
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
