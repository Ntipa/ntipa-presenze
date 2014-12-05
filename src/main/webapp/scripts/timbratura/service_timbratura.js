'use strict';

ntipapresenzeApp.factory('Timbratura', function ($resource) {
        return $resource('app/rest/timbraturas/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
