angular.module('myApp').controller('PhotosModalCtrl', ['$scope', '$uibModalInstance', 'studentsService', '$http', 'sharedProperties', 'fileUpload', 'eventIdService', function ($scope, $uibModalInstance, studentsService, $http, sharedProperties, fileUpload, eventIdService) {

	var loggedUser = sharedProperties.getProperty();
	
	$scope.myStudents = studentsService.getStudents();
	
	

	$scope.ok = function (photo) {
		
		console.log(eventIdService.getEventId());
		
		var file = photo;
		var uploadUrl = '/KinderSpot/photos/' + eventIdService.getEventId();
		fileUpload.uploadFileToUrl(file, uploadUrl);
		
		/*var uploadUrl = '/KinderSpot/photos';
	    fileUpload.uploadFileToUrl(photo.data, uploadUrl, {}).then(function(resp) {
	        console.log("woo");
	    });*/

		/*$http({ 
			url:'/KinderSpot/photos/' + parent ,
			method: "POST",
			data: {
				key: photo
			},
			headers: {'Content-Type':'application/json'}
		})
		.then(function(response){
			console.log("photo uploaded");
		},
		function(response){
			console.log("Failed.");
		});	*/
		
		$uibModalInstance.close();

	};

	$scope.cancel = function () {
		$uibModalInstance.dismiss('cancel');
	};
}]);