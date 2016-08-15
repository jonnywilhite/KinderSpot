angular.module('myApp').controller('PhotosModalCtrl', ['$scope', '$uibModalInstance', 'studentsService', '$http', 'sharedProperties', function ($scope, $uibModalInstance, studentsService, $http, sharedProperties) {

	var loggedUser = sharedProperties.getProperty();
	
	$scope.myStudents = studentsService.getStudents();

	$scope.ok = function (photo) {

		$http({ 
			url:'/KinderSpot/meeting/' + parent ,
			method: "POST",
			data: {
				"reason": $scope.meetingReason,
				"parent": {
					"id": $scope.selectParent
				},
				"teacher":{
					"id":loggedUser.id
				},
				"date": $scope.meetingDate

			},
			headers: {'Content-Type':'application/json'}
		})
		.then(function(response){
			console.log(loggedUser.id);
			//teacherHomeData.createNewMeeting = response.data;
		},
		function(response){
			console.log("Failed.")
		});	
		
		$uibModalInstance.close();

	};

	$scope.cancel = function () {
		$uibModalInstance.dismiss('cancel');
	};
}]);