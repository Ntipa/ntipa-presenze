'use strict';

ntipapresenzeApp.factory('Turnazione2', function ($resource) {
        return $resource('app/rest/turnazione2s/:id', {}, {
            'query': { method: 'GET', isArray: true},
            'get': { method: 'GET'}
        });
    });
