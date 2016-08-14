angular.module('myApp').controller('EditModalInstanceCtrl', ['$scope', '$uibModalInstance', 'studentsService', '$http', 'sharedProperties', 'attendanceService', function ($scope, $uibModalInstance, studentsService, $http, sharedProperties, attendanceService) {

	var loggedUser = sharedProperties.getProperty();
	$scope.date = new Date();

	$scope.myStudents = studentsService.getStudents();
	
	$scope.myAttendanceStudents = {};
	var start = new Date().setHours(0,0,0,0);
	
	$http({
		url: "/KinderSpot/" + loggedUser.id + "/attendance/" + start,
		method: "GET",
		headers: {'Content-Type': 'application/json'}
	})
	.then(function(response) {
		$scope.myAttendanceStudents = response.data;
		$scope.myAttendanceStudents.forEach(function(item, index) {
			$scope.myStudents[index].present = item.present;
		})
		console.log($scope.myStudents);
	},
	function(response) {
		console.log("Failed");
	})

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
			url: "/KinderSpot/" + loggedUser.id + "/attendance/" + start,
			method: "PUT",
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