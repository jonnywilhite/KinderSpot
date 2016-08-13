angular.module("myApp").controller("teacherHomeCtrl", function($http, sharedProperties, studentProperties, $state, $uibModal, studentsService)  {

	var teacherHomeData = this;
	var loggedUser = sharedProperties.getProperty();

	teacherHomeData.attendanceMessage = "";
	teacherHomeData.hasSubmittedAttendance = false;

	teacherHomeData.displayAttendanceMessage = function() {
		$http({
			url: '/KinderSpot/' + loggedUser.id + '/attendance',
			method: "GET",
			headers: {'Content-Type': 'application/json'}
		})
		.then(function(response) {
			var thisDate = new Date(response.data[0].date);
			var thatDate = new Date();
			var thisDateString = thisDate.getMonth() + "-" + thisDate.getDate();
			var thatDateString = thatDate.getMonth() + "-" + thatDate.getDate();
			
			if (thisDateString == thatDateString) {
				teacherHomeData.attendanceMessage = "You've submitted today's attendance!";
				teacherHomeData.hasSubmittedAttendance = true;
			} else {
				teacherHomeData.attendanceMessage = "You haven't submitted today's attendance yet";
				teacherHomeData.hasSubmittedAttendance = false;
			}
		},
		function(response) {
			
		});
	}
	teacherHomeData.displayAttendanceMessage();


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
			studentsService.setStudents(response.data);
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
		},
		function(response){
			console.log("Failed.");
		});	
	}
	teacherHomeData.showMeetings();


	teacherHomeData.createMeeting = function (parent,reason)
	{
		teacherHomeData.selectParent = parent;
		teacherHomeData.meetingReason = reason; 
		console.log(parent);
		$http({ 
			url:'/KinderSpot/meeting/' + parent ,
			method: "POST",
			data: { "reason": teacherHomeData.meetingReason,
				"parent":{
					"id": teacherHomeData.selectParent
				}

			},
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
		teacherHomeData.newEvent = eventName; 
		teacherHomeData.description = description;
		console.log(eventName);
		console.log(description);

		$http({ 
			url:'/KinderSpot/event/' + eventName,
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

	teacherHomeData.animationsEnabled = true;

	teacherHomeData.open = function (size) {
		
		if (!teacherHomeData.hasSubmittedAttendance) {

			var modalInstance = $uibModal.open({
				animation: teacherHomeData.animationsEnabled,
				templateUrl: 'myModalContent.html',
				controller: 'ModalInstanceCtrl',
				size: size,
				resolve: {
	
				}
			});
	
			modalInstance.result.then(function () {
				teacherHomeData.attendanceMessage = "You've submitted today's attendance!";
				teacherHomeData.hasSubmittedAttendance = true;
			}, function () {
				
			});
		
		} else {
			
			var editModalInstance = $uibModal.open({
				animation: teacherHomeData.animationsEnabled,
				templateUrl: 'EditAttendanceModalContent.html',
				controller: 'EditModalInstanceCtrl',
				size: size,
				resolve: {
	
				}
			});
	
			editModalInstance.result.then(function () {
				teacherHomeData.attendanceMessage = "You've submitted today's attendance!";
				teacherHomeData.hasSubmittedAttendance = true;
			}, function () {
				
			});
		}
	};

}); //ends teacherHomeCtrl