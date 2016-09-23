angular.module('myApp').controller('PhotosModalCtrl', ['$scope', '$uibModalInstance', 'studentsService', '$http', 'sharedProperties', 'fileUpload', 'eventIdService', function ($scope, $uibModalInstance, studentsService, $http, sharedProperties, fileUpload, eventIdService) {

	var loggedUser = sharedProperties.getProperty();
	
	$scope.myStudents = studentsService.getStudents();
	$scope.event = eventIdService.getEvent();
	

	$scope.ok = function (photo) {
		
		if (loggedUser.userRole.name == "Teacher") {
			var file = photo;
			var uploadUrl = '/ROOT/photos/' + $scope.event.id;
			fileUpload.uploadFileToUrl(file, uploadUrl);
			
			$uibModalInstance.close();
		} else {
			$uibModalInstance.close();
		}

	};

	$scope.cancel = function () {
		$uibModalInstance.dismiss('cancel');
	};
	
	
	
	$scope.showPics = function(){
		
		$http({
			url: '/ROOT/photos/' + $scope.event.id,
			method: "GET",
			headers: {'Content-Type': 'application/json'}
			
		})
		.then(function(response){
			$scope.myPhotos = response.data;
			$scope.addSlides($scope.myPhotos.length, $scope.myPhotos);
		},
		function(response){
		console.log("Failed.");
	
	 });
		
	}//ends showPics()
	
	
	
	//The below is copy/pasta from UIBoostrap to get the slideshow to work.
	$scope.myInterval = 2000;
	$scope.noWrapSlides = false;
	$scope.active = 0;
	var slides = $scope.slides = [];
	var currIndex = 0;

	$scope.randomize = function() {
		var indexes = generateIndexesArray();
		assignNewIndexesToSlides(indexes);
	};

	$scope.addSlides = function(length, photoData){
		for(var i=0; i < length; i++)
		{
			slides.push({
				image: photoData[i].photo,
				text: photoData[i].description,
				id: currIndex++
			});
		}
	}
	
	
	
	
}]); //ends controller()