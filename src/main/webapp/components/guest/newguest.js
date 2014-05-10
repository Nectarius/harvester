angular.module('newguest', ['ngResource','guest'])
    .controller('Newguest', function ($scope, $resource, $resource, $location) {

        $scope.guest = {};

        $scope.fill = function (guest) {
            guest.name = "";
            guest.surname = "";
        }

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

        $scope.fill($scope.guest);


        $scope.save = function (guest_) {

            var guest = {};
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
                    alert('Введены некорректные данные');
                }
                else
                    alert('Произошла неизвестная ошибка');
            });
        }

    });
