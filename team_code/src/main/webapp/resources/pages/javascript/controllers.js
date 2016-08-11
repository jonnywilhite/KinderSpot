

angular.module("myApp", ['ui.router']); // Defining a module



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

	 
}); //ends angular.module("myApp").config...



//Runs the login Controller's POST method, passing in the user's form inputs as parameters. 
//The function is run when the user clicks the login button.
angular.module("myApp").controller("loginCtrl", function($scope, $http, $location, $window, $state, sharedProperties) {
	
	var loginData = this;
	
	//Gets the email and password from the user's form submit. Then runs the Login Controller's POST method.
	$scope.login = function(email, pass){
		$scope.myEmail = email;
		$scope.myPass = pass;
		
		$http({
            url: '/KinderSpot/home',
            method: "POST",
            data: { "email": $scope.myEmail,
      				"password": $scope.myPass		},
      		headers: {'Content-Type': 'application/json'}
        })
        .then(function(response) {
        	//success
            //console.log("email: " + response.data.email);
        	if(response.data.email === undefined && response.data.password === undefined)
        	{
        		//failed login
        		$scope.userMessage = "Invalid login. Try again.";
        	}
        	else
        	{
        		//sucessful login
        		$scope.userMessage = "Logged in.";
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
		
	}//ends $scope.login()
	
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


angular.module("myApp").controller("parentHomeCtrl", function($scope, $http, sharedProperties) {
	
	var parentHomeData = this;
	var loggedUser = sharedProperties.getProperty();

	$scope.displayUser = function() {
		//console.log("loggedUser is: " + loggedUser.firstName);
		$scope.loggedInUser = loggedUser.firstName; //loggedInUser is the ng-model in ParentHome.html
	}
	
	$scope.displayUser();
	
	$scope.emailTeacher = function(subject, body) {
		$scope.mySubject = subject;
		$scope.myBody = body;
		
		$http({
            url: '/KinderSpot/' + loggedUser.id + '/email',
            method: "POST",
            data: { "subject": $scope.mySubject,
  				"body": $scope.myBody		},
            headers: {'Content-Type': 'application/json'}
        })
        .then(function(response) {
        	//success
        	//studentsList = response.data;
        	$scope.myStudents = response.data;
        	console.log(response.data)
            //console.log("students: " + studentsList[0].firstname);
        }, 
        function(response) { // optional
        		console.log("Failed.");
        });
	}
	$scope.emailTeacher();

}); //ends parentHomeApp.controller()


angular.module("myApp").controller("teacherHomeCtrl", function($scope, $http, sharedProperties) 
{
	
	var teacherHomeData = this;
	var loggedUser = sharedProperties.getProperty();
	//var studentsList = {};	
	//Displays teacher object info on View.
	$scope.displayUser = function() {
		$scope.loggedInUser = loggedUser.firstName; //loggedInUser is the ng-model in ParentHome.html
	}
	$scope.displayUser();
	
	
	$scope.showStudents = function()
	{
			$http({
            url: '/KinderSpot/' + loggedUser.id + '/students',
            method: "GET",
            headers: {'Content-Type': 'application/json'}
        })
        .then(function(response) {
        	//success
        	//studentsList = response.data;
        	$scope.myStudents = response.data;
        	console.log(response.data)
            //console.log("students: " + studentsList[0].firstname);
        }, 
        function(response) { // optional
        		console.log("Failed.");
        });
	}//ends showStudents()
	$scope.showStudents();
	
	$scope.showMeetings = function()
	{
		$http({
			url: '/KinderSpot/meeting',
			method: "GET",
            headers: {'Content-Type': 'application/json'}
		})
		.then(function(response){	
			$scope.myMeetings = response.data;
			console.log(response.data)
		},
		function(response){
			console.log("Failed.");
		});	
	}
	$scope.showMeetings();
	
	$scope.showEvents = function()
	{
		$http({
			url: '/KinderSpot/event',
			method: "GET",
            headers: {'Content-Type': 'application/json'}
		})
		.then(function(response){	
			$scope.myEvents = response.data;
			console.log(response.data)
		},
		function(response){
			console.log("Failed.");
		});	
	}
	$scope.showEvents();
	
	
	
	
});



//angular.module("myApp").service('meetingService', function($http){
//	
//	var myservice = this;
//	
//	myservice.previousMeeting = [];
//	
//	myservice.spoons = function (){
//	 var promise = $http({
//			method: "GET",
//			url: "Kinderspot/meeting"	
//		});
//		return promise;
//	};
//
//});

















