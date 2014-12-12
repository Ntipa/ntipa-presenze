'use strict';

ntipapresenzeApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/modelloTurno', {
                    templateUrl: 'views/modelloTurnos.html',
                    controller: 'ModelloTurnoController',
                    resolve:{
                        resolvedModelloTurno: ['ModelloTurno', function (ModelloTurno) {
                            return ModelloTurno.query().$promise;
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
