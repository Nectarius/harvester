
angular.module('guestpage',
    ['ngResource', 'newguest', 'editguest' , 'guestlist' , 'newevent', 'editevent' , 'eventlist'])
    .config(['$routeProvider', function ($routeProvider) {

        $routeProvider.when('/guestlist/event:eventId', {templateUrl: 'resources/js/components/guestlist/guestlist.html', controller: 'guestlist'});
        $routeProvider.when('/newguest/:eventId', {templateUrl: 'resources/js/components/guest/newguest.html', controller: 'Newguest'});
        $routeProvider.when('/editguest/:id/event:eventId', {templateUrl: 'resources/js/components/guest/editguest.html', controller: 'Editguest'});

        $routeProvider.when('/eventlist', {templateUrl: 'resources/js/components/eventlist/eventlist.html', controller: 'eventlist'});
        $routeProvider.when('/newevent', {templateUrl: 'resources/js/components/event/newevent.html', controller: 'Newevent'});
        $routeProvider.when('/editevent/:id', {templateUrl: 'resources/js/components/event/editevent.html', controller: 'editevent'});

        $routeProvider.otherwise({redirectTo: '/guestlist/event0'});
    }]);
