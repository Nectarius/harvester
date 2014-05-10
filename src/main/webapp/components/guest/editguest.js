angular.module('editguest', ['ngResource','guest'])
    .controller('Editguest', function ($scope, $routeParams, $resource, $location) {

        var guest_id = $routeParams.id;
        var guestResource = $resource('/guest/:id.data');

        $scope.guest = {};

        $scope.fill = function (guest) {

            guestResource.get({id: guest_id }, function (response) {
                guest.name = response.name;
                guest.surname = response.surname;

            }, function (error) {
                alert(error.data);
            });

        }

        $scope.fill($scope.guest);



        $scope.save = function (guest) {

            guest.id = guest_id;

            var guest = angular.copy(guest);
            console.log(guest.name);
            console.log(guest.surname);

            var guestResource = $resource('/guest/save.data', JSON.stringify(guest));

            guestResource.save(guest, function (response) {
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