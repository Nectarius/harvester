angular.module('editevent', ['ngResource','event'])
    .controller('Editevent', function ($scope, $routeParams, $resource, $location) {

        var event_id = $routeParams.id;
        var eventResource = $resource('/event/:id.data');

        $scope.event = {};

        $scope.fill = function (event) {

            eventResource.get({id: event_id }, function (response) {
                event.name = response.name;
                event.description = response.description;
                event.webSite = response.webSite;
                event.path = response.path;

            }, function (error) {
                alert(error.data);
            });

        }

        $scope.fill($scope.event);


        $scope.save = function (event_) {

            var event = {};
            event.id = event_id;
            event.name = event_.name;
            event.description = event_.description;
            event.webSite = event_.webSite;
            event.path = event_.path;

            var eventResource = $resource('/event/save.data', JSON.stringify(event));

            eventResource.save(event, function (response) {
                alert('Данные сохранены');
                $location.path("/");
            }, function (error) {
                if (error.status == 400) {
                    alert('Неверно указано имя или фамилия пользователя');
                }
                else
                    alert('Произошла неизвестная ошибка');
            });
        }

    });