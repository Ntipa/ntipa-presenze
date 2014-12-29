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

        $scope.listDay = {};
        $scope.addDay = function (day) {
             $scope.listDay.push(day);
        };

        $scope.persone = [
                {"nome": "Giambanco", "codice": "GMBGPP82R07G273S"},
                {"nome": "Tornabene", "codice": "TRBTND76T04Y143W"},
                {"nome": "Testaverde", "codice": "TSVGVN73R08G372A"}
        ];

       /* $scope.eventSources = {};

        $scope.uiConfig = {
          calendar:{
          height: 300,
          editable: true,
          droppable: true, // this allows things to be dropped onto the calendar
              drop: function() {
                // is the "remove after drop" checkbox checked?
                if ($('#drop-remove').is(':checked')) {
                  // if so, remove the element from the "Draggable Events" list
                  $(this).remove();
                }
              }

          
          header:{
            left: 'month basicWeek basicDay agendaWeek agendaDay',
            center: 'title',
            right: 'today prev,next'
          },

          dayClick: $scope.alertEventOnClick,
          eventDrop: $scope.alertOnDrop,
          eventResize: $scope.alertOnResize
          }
        };

        $scope.extraEventSignature = function(event) {
            $scope.eventSources[1]=[];
            $scope.eventSources[1].push({
                events: [
                    {
                        title: 'Event1',
                        start: '2014-12-30'
                    },
                    {
                        title: 'Event2',
                        start: '2014-12-31'
                    }
                    // etc...
                ],
                color: 'yellow',   // an option!
                textColor: 'black' // an option!
                });
          


        };*/


 

     
        
    

    });

