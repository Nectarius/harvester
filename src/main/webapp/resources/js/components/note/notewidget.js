angular.module('note', ['ngResource'])
    .directive('noteWidget', function () {
        return {
            restrict: 'E',
            priority: 5,
            transclude: true,
            scope: {
                model: '=model'

            },
            templateUrl: "resources/js/components/note/notewidget.html"
        };
    });
