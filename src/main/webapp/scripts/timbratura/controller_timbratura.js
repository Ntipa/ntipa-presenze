'use strict';

ntipapresenzeApp.controller('TimbraturaController', function ($scope, resolvedTimbratura, Timbratura) {

        $scope.timbraturas = resolvedTimbratura;

        $scope.create = function () {
            Timbratura.save($scope.timbratura,
                function () {
                    $scope.timbraturas = Timbratura.query();
                    $('#saveTimbraturaModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.timbratura = Timbratura.get({id: id});
            $('#saveTimbraturaModal').modal('show');
        };

        $scope.delete = function (id) {
            Timbratura.delete({id: id},
                function () {
                    $scope.timbraturas = Timbratura.query();
                });
        };

        $scope.clear = function () {
            $scope.timbratura = {username: null, timbraturaDate: null, tipologia: null, info: null, id: null};
        };
    });
