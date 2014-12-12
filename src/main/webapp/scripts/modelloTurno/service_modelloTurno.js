'use strict';

ntipapresenzeApp.factory('ModelloTurno', function ($resource) {
        return $resource('app/rest/modelloTurnos/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
