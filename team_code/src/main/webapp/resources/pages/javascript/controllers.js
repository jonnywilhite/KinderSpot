
angular.module("myApp", ['ui.router', 'angularModalService', 'ui.bootstrap']); // Defining a module



angular.module("myApp")
.config(function($stateProvider, $urlRouterProvider){

	$urlRouterProvider.otherwise('/loginHome');

	$stateProvider
	.state('loginState', {
		url: '/loginHome',
		templateUrl: 'Login.html',
		controller: 'loginCtrl as loginData'
	})//ends .state 
	.state('parentHomeState', {
		url: '/parentHome',
		templateUrl: 'ParentHome.html',
		controller: 'parentHomeCtrl as parentHomeData'
	})//ends .state 
	.state('teacherHomeState', {
		url: '/teacherHome',
		templateUrl: 'TeacherHome.html',
		controller: 'teacherHomeCtrl as teacherHomeData'
	})//ends .state 
	.state('viewStudentState', {
		url: '/view-student',
		templateUrl: 'ViewStudent.html',
		controller: 'viewStudentCtrl as viewStudentData'
	});//ends .state


}); //ends angular.module("myApp").config...









//Runs the login Controller's POST method, passing in the user's form inputs as parameters. 
//The function is run when the user clicks the login button.
angular.module("myApp").controller("loginCtrl", function($http, $location, $window, $state, sharedProperties) {

	var loginData = this;

	//Gets the email and password from the user's form submit. Then runs the Login Controller's POST method.
	loginData.login = function(email, pass){
		loginData.myEmail = email;
		loginData.myPass = pass;

		$http({
			url: '/KinderSpot/home',
			method: "POST",
			data: { "email": loginData.myEmail,
				"password": loginData.myPass		},
				headers: {'Content-Type': 'application/json'}
		})
		.then(function(response) {
			//success
			//console.log("email: " + response.data.email);
			if(response.data.email === undefined && response.data.password === undefined)
			{
				//failed login
				loginData.userMessage = "Invalid login. Try again.";
			}
			else
			{
				//sucessful login
				loginData.userMessage = "Logged in.";
				sharedProperties.setProperty(response.data);
				sharedProperties.getProperty();

				//Checks user's role and sends them to appropriate login home page.
				if(response.data.userRole.name === "Parent")
					$state.go('parentHomeState');
				else
					$state.go('teacherHomeState');
			}
		}, 
		function(response) { // optional
			// failed
			console.log("Failed.");
		});

	}//ends loginData.login()

}); //ends app.controller()


//This service module is used to pass objects between controllers. Passes logged in teacher/parent object.
angular.module("myApp")
.service('sharedProperties', function () {
	var property = {firstName: 'First'};

	return {
		getProperty: function () {
			//console.log("Getting property: " + property.lastName);
			return property;
		},
		setProperty: function(value) {
			property = value;
			//console.log("Setting property: " + property);
		}
	};
});


angular.module("myApp").controller("parentHomeCtrl", function($http, sharedProperties) {

	var parentHomeData = this;
	var loggedUser = sharedProperties.getProperty();

	parentHomeData.displayUser = function() {
		//console.log("loggedUser is: " + loggedUser.firstName);
		parentHomeData.loggedInUser = loggedUser.firstName; //loggedInUser is the ng-model in ParentHome.html
	}

	parentHomeData.displayUser();

	
	parentHomeData.emailTeacher = function(subject, body) {
		parentHomeData.mySubject = subject;
		parentHomeData.myBody = body;
		
		$http({
            url: '/KinderSpot/' + loggedUser.id + '/email',
            method: "POST",
            data: { "subject": parentHomeData.mySubject,
  				"body": parentHomeData.myBody		},
            headers: {'Content-Type': 'application/json'}
        })
        .then(function(response) {
        	//success
        	//studentsList = response.data;
        	parentHomeData.myStudents = response.data;
        	console.log(response.data)
            //console.log("students: " + studentsList[0].firstname);
        }, 
        function(response) { // optional
        		console.log("Failed.");
        });

	

	}; //ends parentHomeApp.controller()

	parentHomeData.emailTeacher();
});


angular.module("myApp").controller("teacherHomeCtrl", function($http, sharedProperties, studentProperties, $state, ModalService) {

	var teacherHomeData = this;
	var loggedUser = sharedProperties.getProperty();
	
	
	//Displays teacher object info on View.
	teacherHomeData.displayUser = function() {
		teacherHomeData.loggedInUser = loggedUser.firstName; //loggedInUser is the ng-model in ParentHome.html
	}
	teacherHomeData.displayUser();


	teacherHomeData.showStudents = function()
	{
		$http({
			url: '/KinderSpot/' + loggedUser.id + '/students',
			method: "GET",
			headers: {'Content-Type': 'application/json'}
		})
		.then(function(response) {
			//success
			//studentsList = response.data;
			teacherHomeData.myStudents = response.data;
			console.log(response.data)
			//console.log("students: " + studentsList[0].firstname);
		}, 
		function(response) { // optional
			console.log("Failed.");
		});
	}//ends showStudents()
	teacherHomeData.showStudents();

	teacherHomeData.viewStudent = function(id) {

		$http({
			url: '/KinderSpot/students/' + id,
			method: "GET",
			headers: {'Content-Type': 'application/json'}
		})
		.then(function(response) {
			studentProperties.setStudent(response.data);
			$state.go('viewStudentState');
		},
		function(response) {
			console.log("failed");
		});
	}

	teacherHomeData.showMeetings = function()
	{
		$http({
			url: '/KinderSpot/meeting',
			method: "GET",
			headers: {'Content-Type': 'application/json'}
		})
		.then(function(response){	
			teacherHomeData.myMeetings = response.data;
			console.log(response.data)
		},
		function(response){
			console.log("Failed.");
		});	
	}
	teacherHomeData.showMeetings();

	
	teacherHomeData.createMeeting = function (reason)
	{
		teacherHomeData.meetingReason = reason; 

		$http({ 
			url:'/KinderSpot/meeting',
			method: "POST",
			data: {"reason": teacherHomeData.meetingReason },
			headers: {'Content-Type':'application/json'}
		})
		.then(function(response){
			
			teacherHomeData.createNewMeeting = response.data;
		},
		function(response){
			console.log("Failed.")
		});		
		
		
	}

	
	teacherHomeData.showEvents = function()
	{
		$http({
			url: '/KinderSpot/event',
			method: "GET",
			headers: {'Content-Type': 'application/json'}
		})
		.then(function(response){	
			teacherHomeData.myEvents = response.data;
		},
		function(response){
			console.log("Failed.");
		});	
	}
	teacherHomeData.showEvents();
	
	
	teacherHomeData.createEvents = function (eventName, description)
	{
		teacherHomeData.newEvent = event; 
		teacherHomeData.description = description;

		$http({ 
			url:'/KinderSpot/event',
			method: "POST",
			data: {"name": teacherHomeData.newEvent,
					"description": teacherHomeData.description
					},
			
			headers: {'Content-Type':'application/json'}
		})
		.then(function(response){
			
			teacherHomeData.createNewEvent = response.data;
		},
		function(response){
			console.log("Failed.")
		});		
		
		
	}
	
	
	//email stuff
	teacherHomeData.emailParent = function(recipient, subject, body) {
		teacherHomeData.myRecipient = recipient;
		teacherHomeData.mySubject = subject;
		teacherHomeData.myBody = body;
		
		$http({
            url: '/KinderSpot/' + loggedUser.id + '/emailParent',
            method: "POST",
            data: { "subject": teacherHomeData.mySubject,
  				"body": teacherHomeData.myBody, 	
  				"recipient": teacherHomeData.myRecipient},
            headers: {'Content-Type': 'application/json'}
        })
        .then(function(response) {
        	//success
        	//studentsList = response.data;
        	teacherHomeData.myStudents = response.data;
        	console.log("Hello")
            //console.log("students: " + studentsList[0].firstname);
        }, 
        function(response) { // optional
        		console.log("Failed.");
        });
	};//ends emailParent()
	
	
	//Takes in a div ID from the View, and makes it visible to user. Activates when user clicks on sidenav.
	teacherHomeData.showDisplay = function(divId){
		
		/*
		 Hides all ID's, then displays the user input. The result is that only one of the teacher
		 sections is displayed at a time. Without this, you'd have an element show up, but never leave.
		*/
		document.getElementById('studentsDiv').style.display = "none";
		document.getElementById('eventsDiv').style.display = "none";
		document.getElementById('emailDiv').style.display = "none";
		document.getElementById('meetingsDiv').style.display = "none";
		
		document.getElementById(divId).style.display = "inline";
	};
	
}); //ends teacherHomeCtrl




//angular.module('datepickerBasicUsage',
//	    ['ngMaterial', 'ngMessages']).controller('AppCtrl', function($scope) {
//	  $scope.myDate = new Date();
//	  $scope.minDate = new Date(
//	      $scope.myDate.getFullYear(),
//	      $scope.myDate.getMonth() - 2,
//	      $scope.myDate.getDate());
//	  $scope.maxDate = new Date(
//	      $scope.myDate.getFullYear(),
//	      $scope.myDate.getMonth() + 2,
//	      $scope.myDate.getDate());
//	  $scope.onlyWeekendsPredicate = function(date) {
//	    var day = date.getDay();
//	    return day === 0 || day === 6;
//	  }
//	});



//angular.module("myApp").service('meetingService', function($http){

//var myservice = this;

//myservice.previousMeeting = [];

//myservice.spoons = function (){
//var promise = $http({
//method: "GET",
//url: "Kinderspot/meeting"	
//});
//return promise;
//};

//});


angular.module("myApp").controller("viewStudentCtrl", function($http, sharedProperties, studentProperties) {
	var viewStudentData = this;
	
	viewStudentData.showStudent = function() {
		viewStudentData.currentStudent = studentProperties.getStudent();
	}
	
	viewStudentData.showStudent();
	
	viewStudentData.showGrade = function() {
		$http({
			url: "http://localhost:8085/KinderSpot/report-cards/" + viewStudentData.currentStudent.id,
			method: "GET",
			headers: {'Content-Type': 'application/json'}
		})
		.then(function(response) {
			viewStudentData.currentReportCard = response.data;
		},
		function(response) {
			console.log(response);
		})
	}
	
	viewStudentData.showGrade();
	
	viewStudentData.showAttendance = function() {
		$http({
			url: "http://localhost:8085/KinderSpot/attendance/" + viewStudentData.currentStudent.id,
			method: "GET",
			headers: {'Content-Type': 'application/json'}
		})
		.then(function(response) {
			viewStudentData.currentAttendance = response.data;
		},
		function(response) {
			console.log(response);
		});
	};
	
	viewStudentData.showAttendance();
	
}); //ends viewStudentCtrl

/*angular.module("myApp").service("MyService", function($http) {
	var myService = this;
	myService.student = {};
});*/

angular.module("myApp")
.service('studentProperties', function () {
	var student = {};

	return {
		getStudent: function () {
			return student;
		},
		setStudent: function(value) {
			student = value;
		}
	};
});


/*angular.module("myApp").controller('ModalController', function($scope, close) {
	  
	$scope.close = function(result) {
		close(result, 500); // close, but give 500ms for bootstrap to animate
	};

});*/

angular.module('myApp').controller('ModalDemoCtrl', function ($scope, $uibModal, $log) {

	  $scope.items = ['item1', 'item2', 'item3'];

	  $scope.animationsEnabled = true;

	  $scope.open = function (size) {

	    var modalInstance = $uibModal.open({
	      animation: $scope.animationsEnabled,
	      templateUrl: 'myModalContent.html',
	      controller: 'ModalInstanceCtrl',
	      size: size,
	      resolve: {
	        items: function () {
	          return $scope.items;
	        }
	      }
	    });

	    modalInstance.result.then(function (selectedItem) {
	      $scope.selected = selectedItem;
	    }, function () {
	      $log.info('Modal dismissed at: ' + new Date());
	    });
	  };

	  $scope.toggleAnimation = function () {
	    $scope.animationsEnabled = !$scope.animationsEnabled;
	  };

	});

	// Please note that $uibModalInstance represents a modal window (instance) dependency.
	// It is not the same as the $uibModal service used above.

	angular.module('myApp').controller('ModalInstanceCtrl', function ($scope, $uibModalInstance, items) {

	  $scope.items = items;
	  $scope.selected = {
	    item: $scope.items[0]
	  };

	  $scope.ok = function () {
	    $uibModalInstance.close($scope.selected.item);
	  };

	  $scope.cancel = function () {
	    $uibModalInstance.dismiss('cancel');
	  };
	});
