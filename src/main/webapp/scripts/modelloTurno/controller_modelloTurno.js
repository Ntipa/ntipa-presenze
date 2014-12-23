'use strict';

ntipapresenzeApp.controller('ModelloTurnoController', function ($log, $scope, resolvedModelloTurno, ModelloTurno) {

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
 
        $scope.addDayToList = function (num) {
          $scope.listDay={};
          for (var i=1 ; i<=num ; i++) {
           $scope.listDay[i]=[];
           };
        };

        $scope.addRange = function (day) {
             $scope.listDay[day].push({da:null,a:null}) ;
        };

        $scope.removeRange = function (day,range) {
            $scope.listDay[day].splice($scope.listDay[day].indexOf(range,1));
        };
        
        $scope.removeDay = function (day) {
            delete  $scope.listDay[day] ;
        };

        

       

    });