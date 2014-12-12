'use strict';

ntipapresenzeApp.factory('Turnazione', function ($resource) {
        return $resource('app/rest/turnaziones/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
