'use strict';

ntipapresenzeApp.controller('Turnazione2Controller', function ($scope, resolvedTurnazione2, Turnazione2, $compile,uiCalendarConfig) {

        $scope.turnazione2s = resolvedTurnazione2;

        /*$scope.create = function () {
            Turnazione2.save($scope.turnazione2,
                function () {
                    $scope.turnazione2s = Turnazione2.query();
                    $('#saveTurnazione2Modal').modal('hide');
                    $scope.clear();
                });
        };

        $scope.update = function (id) {
            $scope.turnazione2 = Turnazione2.get({id: id});
            $('#saveTurnazione2Modal').modal('show');
        };

        $scope.delete = function (id) {
            Turnazione2.delete({id: id},
                function () {
                    $scope.turnazione2s = Turnazione2.query();
                });
        };

        $scope.clear = function () {
            $scope.turnazione2 = {turnazione2: null, id: null};
        };*/

    $scope.listDay = {};
    $scope.addDay = function (day) {
         $scope.listDay.push(day);
    };

    $scope.persone = [
            {"nome": "Giambanco", "codice": "GMBGPP82R07G273S"},
            {"nome": "Tornabene", "codice": "TRBTND76T04Y143W"},
            {"nome": "Testaverde", "codice": "TSVGVN73R08G372A"}
    ];


    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
    
    

    /* event source that contains custom events on the scope */
    $scope.events = [
      {title: 'All Day Event',start: new Date(y, m, 1)},
      {title: 'Long Event',start: new Date(y, m, d - 5),end: new Date(y, m, d - 2)},
      {title: 'Repeating Event',start: new Date(y, m, d - 3, 16, 30),allDay: false},
      {title: 'Repeating Event',start: new Date(y, m, d + 4, 16, 0),allDay: false},
      {title: 'Birthday Party',start: new Date(y, m, d + 1, 19, 0),end: new Date(y, m, d + 1, 22, 30),allDay: false}
      
    ];
    /* event source that calls a function on every view switch */
    /*$scope.eventsF = function (start, end, timezone, callback) {
      var s = new Date(start).getTime() / 1000;
      var e = new Date(end).getTime() / 1000;
      var m = new Date(start).getMonth();
      var events = [{title: 'Feed Me ' + m,start: s + (50000),end: s + (100000),allDay: false, className: ['customFeed']}];
      callback(events);
    };*/

    $scope.calEventsExt = {
       color: '#f00',
       textColor: 'yellow',
       events: [ 
          {type:'party',title: 'Lunch',start: new Date(y, m, d, 12, 0),end: new Date(y, m, d, 14, 0),allDay: false},
          {type:'party',title: 'Lunch 2',start: new Date(y, m, d, 12, 0),end: new Date(y, m, d, 14, 0),allDay: false}
          
        ]
    };
    /* alert on eventClick */
    $scope.alertOnEventClick = function(date, jsEvent, view){
        $scope.alertMessage = (date.title + ' was clicked ');
        $('#modalTitle').html(date.title);
        $('#modalBody').html('<b>Inizia il:</b> ' + date.start +' <br><b>Finisce il: </b>' + date.end);
        $('#fullCalModal').modal();
    };
    /* alert on Drop */
     /*$scope.alertOnDrop = function(event, delta, revertFunc, jsEvent, ui, view){
       $scope.alertMessage = ('Event Droped to make dayDelta ' + delta);
    };*/
    /* alert on Resize */
    /*$scope.alertOnResize = function(event, delta, revertFunc, jsEvent, ui, view ){
       $scope.alertMessage = ('Event Resized to make dayDelta ' + delta);
    };*/
    /* add and removes an event source of choice */
    
    /* add custom event*/
    $scope.addEvent = function() {
      $scope.events.push({
        title: 'Nuovo evento',
        start: new Date(y, m, 14),
        end: new Date(y, m, 15)
      });
    };
    /* remove event */
    $scope.remove = function(index) {
      $scope.events.splice(index,1);
    };
    /* Change View */
    /*$scope.changeView = function(view,calendar) {
      uiCalendarConfig.calendars[calendar].fullCalendar('changeView',view);
    };*/
    /* Change View */
    /*$scope.renderCalendar = function(calendar) {
      if(uiCalendarConfig.calendars[calendar]){
        uiCalendarConfig.calendars[calendar].fullCalendar('render');
      }
    };*/
     /* Render Tooltip */
    /*$scope.eventRender = function( event, element, view ) { 
        element.attr({'tooltip': event.title,
                     'tooltip-append-to-body': true});           <-Al momento non funziona
        $compile(element)($scope);
    };*/
    /* config object */
    $scope.uiConfig = {
      calendar:{
        height: 450,
        editable: true,
        header:{
          left: 'prev,next today',
          center: 'title',
          right: 'month,agendaWeek,agendaDay'
        },
        weekMode: 'liquid',
        firstDay: 1,
        allDaySlot : false,
        lazyFetching: false,
        monthNames:['Gennaio','Febbraio','Marzo','Aprile','Maggio','Giugno','Luglio','Agosto','Settembre','Ottobre','Novembre','Dicembre'],
        monthNamesShort: ['Gen', 'Feb', 'Mar', 'Apr', 'Mag', 'Giu', 'Lug', 'Ago', 'Set', 'Ott', 'Nov', 'Dic'],
        dayNames: ['Domenica', 'Lunedì', 'Martedì', 'Mercoledì', 'Giovedì', 'Venerdì', 'Sabato'],
        dayNamesShort: ['Dom', 'Lun', 'Mar', 'Mer', 'Gio', 'Ven', 'Sab'],
        buttonText: {
                    prev:     '&nbsp;&#9668;&nbsp;',  // left triangle
                    next:     '&nbsp;&#9658;&nbsp;',  // right triangle
                    prevYear: '&nbsp;&lt;&lt;&nbsp;', // <<
                    nextYear: '&nbsp;&gt;&gt;&nbsp;', // >>
                    today:    'Oggi',
                    month:    'Mese',
                    week:     'Settimana',
                    day:      'Giorno'
        },
        columnFormat: {
              month: 'ddd',    // Mon
              week: 'd/M ddd', // Mon 9/7
              day: 'dddd d/M'  // Monday 9/7
        },
        eventClick: $scope.alertOnEventClick,
        eventDrop: $scope.alertOnDrop,
        eventResize: $scope.alertOnResize,
        eventRender: $scope.eventRender,
        eventDurationEditable: false

      }
    };

    /* event sources array*/
    $scope.eventSources = [$scope.events, $scope.eventSource, $scope.eventsF];
    $scope.eventSources2 = [$scope.calEventsExt, $scope.eventsF, $scope.events];



});
