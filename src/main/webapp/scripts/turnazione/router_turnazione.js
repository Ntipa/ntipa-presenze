'use strict';

ntipapresenzeApp
    .config(function ($routeProvider, $httpProvider, $translateProvider, USER_ROLES) {
            $routeProvider
                .when('/turnazione', {
                    templateUrl: 'views/turnaziones.html',
                    controller: 'TurnazioneController',
                    resolve:{
                        resolvedTurnazione: ['Turnazione', function (Turnazione) {
                            return Turnazione.query().$promise;
                        }]
                    },
                    access: {
                        authorizedRoles: [USER_ROLES.all]
                    }
                })
        });
