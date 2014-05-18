angular.module('guest', ['ngResource'])
    .directive('guestWidget', function () {
        return {
            restrict: 'E',
            priority: 5,
            transclude: true,
            scope: {
                model: '=model'

            },
            templateUrl: "resources/js/components/guest/guestwidget.html"
        };
    });
