angular.module('myApp').controller('PhotosModalCtrl', ['$scope', '$uibModalInstance', 'studentsService', '$http', 'sharedProperties', 'fileUpload', 'eventIdService', function ($scope, $uibModalInstance, studentsService, $http, sharedProperties, fileUpload, eventIdService) {

	var loggedUser = sharedProperties.getProperty();
	
	$scope.myStudents = studentsService.getStudents();
	$scope.event = eventIdService.getEvent();
	

	$scope.ok = function (photo) {
		
		if (loggedUser.userRole.name == "Teacher") {
			var file = photo;
			var uploadUrl = '/KinderSpot/photos/' + $scope.event.id;
			fileUpload.uploadFileToUrl(file, uploadUrl);
			
			$uibModalInstance.close();
		} else {
			$uibModalInstance.close();
		}

	};

	$scope.cancel = function () {
		$uibModalInstance.dismiss('cancel');
	};
}]);