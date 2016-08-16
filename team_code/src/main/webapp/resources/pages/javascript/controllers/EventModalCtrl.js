angular.module('myApp').controller('EventModalCtrl', ['$rootScope', '$scope', '$uibModalInstance', 'studentsService', '$http', 'sharedProperties','eventTypeService', function ($rootScope, $scope, $uibModalInstance, studentsService, $http, sharedProperties,eventTypeService) 
 {

	var loggedUser = sharedProperties.getProperty();
	
	$scope.myStudents = studentsService.getStudents();
	$scope.eventTypes = eventTypeService.getEventTypes();

	$scope.ok = function (eventName,eventType, description, date) {
		
		$scope.newEvent = eventName;
		$scope.type = eventType; 
		$scope.description = description;
		$scope.eventDate = date;
		
		
		$http({ 
			url:'/KinderSpot/event/' + eventName,
			method: "POST",
			data: {
				"name": $scope.newEvent,
			
				   "description": $scope.description,
				   "date": $scope.eventDate,
				   "eventType":{
					   "id": $scope.type
				   }
			},
	
		
			headers: {'Content-Type':'application/json'}
		})
		.then(function(response){
			$rootScope.$broadcast('updateEventList');
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