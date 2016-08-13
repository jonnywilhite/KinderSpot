angular.module("myApp").controller("parentHomeCtrl", function($http, sharedProperties) {

	var parentHomeData = this;
	var loggedUser = sharedProperties.getProperty();

	parentHomeData.displayUser = function() {
		//console.log("loggedUser is: " + loggedUser.firstName);
		parentHomeData.loggedInUser = loggedUser; //loggedInUser is the ng-model in ParentHome.html
		//console.log("parentHomeData.loggedInUser = " + parentHomeData.loggedInUser);
	}
	parentHomeData.displayUser();


	parentHomeData.getMyChild = function(){

		$http({
			url: '/KinderSpot/' + loggedUser.id + '/getchildren',
			method: "GET",
			headers: {'Content-Type': 'application/json'}
		})
		.then(function(response) {
			//success
			parentHomeData.myChild = response.data[0];
			currStudentId = response.data[0].id; //Needed to pass to function below.
			//console.log("my child: " + response.data[0].id);
			parentHomeData.showGrade(response.data[0].id);
		}, 
		function(response) { // optional
			console.log("Failed.");
		});
	}
	parentHomeData.getMyChild();


	parentHomeData.showGrade = function(myId) {
		$http({
			url: "/KinderSpot/report-cards/" + currStudentId,
			method: "GET",
			headers: {'Content-Type': 'application/json'}
		})
		.then(function(response) {
			//console.log("id passed in is: " + currStudentId);
			parentHomeData.currentReportCard = response.data;
			//console.log("currReporCard = " + parentHomeData.currentReportCard);
		},
		function(response) {
			console.log(response);
		})
	}


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
			parentHomeData.myStudents = response.data;
			//console.log("students: " + studentsList[0].firstname);
		}, 
		function(response) { // optional
			console.log("Failed.");
		});
	}; //ends email function 

});//ends parentHomeApp.controller()