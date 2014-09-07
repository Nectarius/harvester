angular.module('event', ['ngResource'])
    .directive('eventWidget', function () {
        return {
            restrict: 'E',
            priority: 5,
            transclude: true,
            scope: {
                model: '=model'

            },
            templateUrl: "resources/js/components/event/eventwidget.html"
        };
    });
