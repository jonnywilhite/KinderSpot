angular.module("myApp").controller("parentHomeCtrl", function($http, sharedProperties, eventIdService, $uibModal) {

	var parentHomeData = this;
	var loggedUser = sharedProperties.getProperty();

	parentHomeData.displayUser = function() {
		parentHomeData.loggedInUser = loggedUser; //loggedInUser is the ng-model in ParentHome.html
		//console.log("parentHomeData.loggedInUser = " + parentHomeData.loggedInUser);
	}
	parentHomeData.displayUser();


	//Takes in a div ID from the View, and makes it visible to user. Activates when user clicks on sidenav.
	parentHomeData.showDisplay = function(divId){

		/*
		 Hides all ID's, then displays the user input. The result is that only one of the teacher
		 sections is displayed at a time. Without this, you'd have an element show up, but never leave.
		 */
		document.getElementById('infoDiv').style.display = "none";
		document.getElementById('meetingsDiv').style.display = "none";
		document.getElementById('eventsDiv').style.display = "none";
		document.getElementById('emailDiv').style.display = "none";
		document.getElementById('badgesDiv').style.display = "none";

		document.getElementById(divId).style.display = "inline";
	};


	//After getting child, we run several of the below functions inside this one once we have the student ID.
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

			//Below runs the methods that require the student ID (we only have the parent object at first)
			parentHomeData.showGrade(currStudentId);
			parentHomeData.showBadges(currStudentId);
			parentHomeData.showEvents(currStudentId);
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
		});
	}
	parentHomeData.showBadges = function(myId) {
		$http({
			url: "/KinderSpot/badges/" + currStudentId,
			method: "GET",
			headers: {'Content-Type': 'application/json'}
		})
		.then(function(response) {
			parentHomeData.currentBadges = response.data;
			parentHomeData.badgesCount = response.data.length;
		},
		function(response) {
			console.log("Failed.");
		});
	};

	parentHomeData.getMeetings = function(){

		$http({
			url: '/KinderSpot/parentMeeting/' + loggedUser.id,
			method: "GET",
			headers: {'Content-Type': 'application/json'}
		})
		.then(function(response) {
			//success
			parentHomeData.meeting = response.data;
			parentHomeData.meetingCount = response.data.length;
			//console.log("meeting: " + response.data);
		}, 
		function(response) { // optional
			console.log("Failed.");
		});

	}
	parentHomeData.getMeetings();


	parentHomeData.showEvents = function(studentId){

		$http({
			url: '/KinderSpot/' + studentId + '/studentevent',
			method: "GET",
			headers: {'Content-Type': 'application/json'}
		})
		.then(function(response) {
			//success
			parentHomeData.events = response.data;
			//console.log("events: " + response.data);
		}, 
		function(response) { // optional
			console.log("Failed.");
		});

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

	parentHomeData.emailConfirm = function(){
		alert("your email has been sent.");
	}

	parentHomeData.openPhotosModal = function(event) {

		eventIdService.setEvent(event);

		var modalInstance = $uibModal.open({
			animation: parentHomeData.animationsEnabled,
			templateUrl: 'PhotosModalParent.html',
			controller: 'PhotosModalCtrl',
			//size: size,
			resolve: {

			}
		});

		modalInstance.result.then(function () {

		}, function () {

		});
	}



});//ends parentHomeApp.controller()