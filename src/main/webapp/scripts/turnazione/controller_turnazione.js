'use strict';

ntipapresenzeApp.controller('TurnazioneController', function ($scope, resolvedTurnazione, Turnazione) {

        $scope.turnaziones = resolvedTurnazione;

        $scope.create = function () {
            Turnazione.save($scope.turnazione,
                function () {
                    $scope.turnaziones = Turnazione.query();
                    $('#saveTurnazioneModal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.turnazione = Turnazione.get({id: id});
            $('#saveTurnazioneModal').modal('show');
        };

        $scope.delete = function (id) {
            Turnazione.delete({id: id},
                function () {
                    $scope.turnaziones = Turnazione.query();
                });
        };

        $scope.clear = function () {
            $scope.turnazione = {turnazione: null, id: null};
        };

        $scope.persone = [
                {"nome": "Giambanco", "codice": "GMBGPP82R07G273S"},
                {"nome": "Tornabene", "codice": "TRBTND76T04Y143W"},
                
                {"nome": "Testaverde", "codice": "TSVGVN73R08G372A"}
              ];

        /*$scope.myVar = true;
        $scope.toggle = function() {
          $scope.myVar = !$scope.myVar;
        };*/
    

    });


/*

           function PersoneCtrl($scope) {
              $scope.persone = [
                {"nome": "Giambanco", "codice": "GMBGPP82rvdscvsd3"},
                {"nome": "Tornabene", "codice": "TRBTND804557dfnf8"},
                
                {"nome": "Testaverde", "codice": "TSVGVNdfjfisdifd"}
              ];
            }  

*/