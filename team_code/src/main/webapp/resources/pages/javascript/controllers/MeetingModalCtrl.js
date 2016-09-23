angular.module('myApp').controller('MeetingModalCtrl', ['$rootScope', '$scope', '$uibModalInstance', 'studentsService', '$http', 'sharedProperties', function ($rootScope, $scope, $uibModalInstance, studentsService, $http, sharedProperties) {

	var loggedUser = sharedProperties.getProperty();
	
	$scope.myStudents = studentsService.getStudents();

	$scope.ok = function (parent, reason, date) {
		
		$scope.selectParent = parent;
		$scope.meetingReason = reason; 
		$scope.meetingDate = date;
		
		
		$http({ 
			url:'/ROOT/meeting/' + parent ,
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
			//teacherHomeData.createNewMeeting = response.data;
			$rootScope.$broadcast('updateMeetingList');
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