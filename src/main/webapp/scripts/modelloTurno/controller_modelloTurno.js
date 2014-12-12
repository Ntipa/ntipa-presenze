'use strict';

ntipapresenzeApp.controller('ModelloTurnoController', function ($scope, resolvedModelloTurno, ModelloTurno) {

        $scope.modelloTurnos = resolvedModelloTurno;

        $scope.create = function () {
            ModelloTurno.save($scope.modelloTurno,
                function () {
                    $scope.modelloTurnos = ModelloTurno.query();
                    $('#saveModelloTurnoModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.modelloTurno = ModelloTurno.get({id: id});
            $('#saveModelloTurnoModal').modal('show');
        };

        $scope.delete = function (id) {
            ModelloTurno.delete({id: id},
                function () {
                    $scope.modelloTurnos = ModelloTurno.query();
                });
        };

        $scope.clear = function () {
            $scope.modelloTurno = {nome: null, descrizione: null, id: null};
        };
    });
