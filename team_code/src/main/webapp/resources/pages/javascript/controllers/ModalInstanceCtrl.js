angular.module('myApp').controller('ModalInstanceCtrl', ['$scope', '$uibModalInstance', 'studentsService', '$http', 'sharedProperties', function ($scope, $uibModalInstance, studentsService, $http, sharedProperties) {

	var loggedUser = sharedProperties.getProperty();
	$scope.myStudents = studentsService.getStudents();
	$scope.myStudents.forEach(function(student) {
		student.present = true;
	});
	
	$scope.date = new Date();

	$scope.ok = function () {
		$uibModalInstance.close();
		//console.log($scope.myStudents);
		var list = [];
		$scope.myStudents.forEach(function(student) {
			var item = {};
			item.student = {};
			item.student.id = student.id;
			item.present = student.present;
			list.push(item);
		});

		$http({
			url: "/KinderSpot/" + loggedUser.id + "/attendance/",
			method: "POST",
			headers: {'Content-Type': 'application/json'},
			data: list
		})
		.then(function(response) {

		},
		function(response) {

		});

	};

	$scope.cancel = function () {
		$uibModalInstance.dismiss('cancel');
	};
}]);