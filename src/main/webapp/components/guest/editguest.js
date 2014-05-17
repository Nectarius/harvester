angular.module('editguest', ['ngResource','guest'])
    .controller('Editguest', function ($scope, $routeParams, $resource, $location) {

        var guest_id = $routeParams.id;
        var guestResource = $resource('/guest/:id.data');

        $scope.guest = {};

        $scope.guest.statusList = [
            {name:'Видимо да', value:'PROMISED'},
            {name:'Не определился', value:'NOT_YET_DECIDED'},
            {name:'Маловероятно', value:'UNLIKELY'}
        ];

        function findStatus(value){
            for(var i = 0; i < $scope.guest.statusList.length; i++){
                if($scope.guest.statusList[i].value === value){
                    return $scope.guest.statusList[i];
                }
            }
        }

        $scope.fill = function (guest) {

            guestResource.get({id: guest_id }, function (response) {
                guest.name = response.name;
                guest.surname = response.surname;
                guest.status = findStatus(response.status);
                guest.description = response.description;
                guest.brief = response.brief;
                guest.byWhomWasAdded = response.byWhomWasAdded;
                guest.transport = response.transport;


            }, function (error) {
                alert(error.data);
            });

        }

        $scope.fill($scope.guest);


        $scope.save = function (guest_) {

            var guest = {};
            guest.id = guest_id;
            guest.status = guest_.status.value;
            guest.name = guest_.name;
            guest.surname = guest_.surname;
            guest.description = guest_.description;

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