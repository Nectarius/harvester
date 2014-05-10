angular.module('newguest', ['ngResource','guest'])
    .controller('Newguest', function ($scope, $resource, $resource, $location) {

        $scope.guest = {};

        $scope.fill = function (guest) {
            guest.name = "";
            guest.surname = "";
            guest.salary = "0";
        }

        $scope.fill($scope.guest);


        $scope.save = function (guest) {

            var guest = angular.copy(guest);
            console.log(guest.name);
            console.log(guest.surname);
            console.log(guest.salary);

            var guestResource = $resource('/guest/save.data', JSON.stringify(guest));

            guestResource.save(guest, function (response) {
                alert('Данные сохранены');
                $location.path("/");
            }, function (error) {
                if (error.status == 400) {
                    alert('Введены некорректные данные');
                }
                else
                    alert('Произошла неизвестная ошибка');
            });
        }

    });
