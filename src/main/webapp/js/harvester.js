
angular.module('guestpage',
    ['ngResource', 'newguest', 'editguest' , 'guestlist'])
    .config(['$routeProvider', function ($routeProvider) {

        $routeProvider.when('/guestlist', {templateUrl: 'components/guestlist/guestlist.html', controller: 'guestlist'});
        $routeProvider.when('/newguest', {templateUrl: 'components/guest/newguest.html', controller: 'Newguest'});
        $routeProvider.when('/editguest/:id', {templateUrl: 'components/guest/editguest.html', controller: 'Editguest'});

        $routeProvider.when('/eventlist', {templateUrl: 'components/eventlist/eventlist.html', controller: 'eventlist'});
        $routeProvider.when('/newevent', {templateUrl: 'components/event/newevent.html', controller: 'Newevent'});
        $routeProvider.when('/editevent/:id', {templateUrl: 'components/event/editevent.html', controller: 'Editevent'});

        $routeProvider.otherwise({redirectTo: '/guestlist'});
    }]);

