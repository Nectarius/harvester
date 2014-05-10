angular.module('guest', ['ngResource'])
    .directive('guestWidget', function () {
        return {
            restrict: 'E',
            priority: 5,
            transclude: true,
            scope: {
                model: '=model'

            },
            templateUrl: "components/guest/guestwidget.html"
        };
    });
