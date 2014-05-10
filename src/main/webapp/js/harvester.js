
angular.module('guestpage',
    ['ngResource', 'newguest', 'editguest' , 'guestlist'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.when('/guestlist', {templateUrl: 'components/guestlist/guestlist.html', controller: 'guestlist'});
        $routeProvider.when('/newguest', {templateUrl: 'components/guest/newguest.html', controller: 'Newguest'});
        $routeProvider.when('/editguest/:id', {templateUrl: 'components/guest/editguest.html', controller: 'Editguest'});
        $routeProvider.otherwise({redirectTo: '/guestlist'});
    }]);

