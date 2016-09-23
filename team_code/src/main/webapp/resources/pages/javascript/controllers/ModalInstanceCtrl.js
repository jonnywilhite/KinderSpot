angular.module('myApp').controller('ModalInstanceCtrl', ['$scope', '$uibModalInstance', 'studentsService', '$http', 'sharedProperties', 'attendanceService', function ($scope, $uibModalInstance, studentsService, $http, sharedProperties, attendanceService) {

	var loggedUser = sharedProperties.getProperty();
	
	$scope.myStudents = studentsService.getStudents();
	$scope.myStudents.forEach(function(student) {
		student.present = true;
	});
	
	$scope.date = new Date();

	$scope.ok = function () {
		
		var list = [];
		$scope.myStudents.forEach(function(student) {
			var item = {};
			item.student = {};
			item.student.id = student.id;
			item.present = student.present;
			list.push(item);
		});

		$http({
			url: "/ROOT/" + loggedUser.id + "/attendance/",
			method: "POST",
			headers: {'Content-Type': 'application/json'},
			data: list
		})
		.then(function(response) {
			attendanceService.setMessage("You've already submitted today's attendance!");
		},
		function(response) {

		});
		
		$uibModalInstance.close();

	};

	$scope.cancel = function () {
		$uibModalInstance.dismiss('cancel');
	};
}]);