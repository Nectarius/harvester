
angular.module('guestpage',
    ['ngResource', 'newguest', 'editguest' , 'guestlist' , 'newevent', 'editevent' , 'eventlist'])
    .config(['$routeProvider', function ($routeProvider) {

        $routeProvider.when('/guestlist/event:eventId', {templateUrl: 'components/guestlist/guestlist.html', controller: 'guestlist'});
        $routeProvider.when('/newguest/:eventId', {templateUrl: 'components/guest/newguest.html', controller: 'Newguest'});
        $routeProvider.when('/editguest/:id/event:eventId', {templateUrl: 'components/guest/editguest.html', controller: 'Editguest'});

        $routeProvider.when('/eventlist', {templateUrl: 'components/eventlist/eventlist.html', controller: 'eventlist'});
        $routeProvider.when('/newevent', {templateUrl: 'components/event/newevent.html', controller: 'Newevent'});
        $routeProvider.when('/editevent/:id', {templateUrl: 'components/event/editevent.html', controller: 'editevent'});

        $routeProvider.otherwise({redirectTo: '/guestlist/event0'});
    }]);

