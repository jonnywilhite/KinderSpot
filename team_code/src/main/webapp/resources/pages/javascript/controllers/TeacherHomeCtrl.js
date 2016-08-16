angular.module("myApp").controller("teacherHomeCtrl", function($scope, $http, sharedProperties, studentProperties, $state, $uibModal, studentsService, eventTypeService, eventIdService)  {

	var teacherHomeData = this;
	var loggedUser = sharedProperties.getProperty();

	teacherHomeData.selected = {};
	teacherHomeData.myStudents = [];
	teacherHomeData.myStudentGrades = [];
	teacherHomeData.attendanceMessage = "";
	teacherHomeData.hasSubmittedAttendance = false;
	
	//Update lists
	$scope.$on("updateMeetingList",function(){
		console.log("Heard the call");
		teacherHomeData.showMeetings();
	});
	$scope.$on("updateEventList",function(){
		console.log("Heard the call");
		teacherHomeData.showEvents();
	});
	
	/*
	 * All this is for editing grade in-line
	 */
	teacherHomeData.getTemplate = function (student) {
        if (student.id === teacherHomeData.selected.id)
        	return 'edit';
        else
        	return 'display';
    };
    
    teacherHomeData.editGrade = function (student) {
        teacherHomeData.selected = angular.copy(student);
    };
    
    teacherHomeData.editComments = function (student) {
        teacherHomeData.selected = angular.copy(student);
    };
    
    teacherHomeData.saveStudent = function (idx) {
        teacherHomeData.myStudents[idx] = angular.copy(teacherHomeData.selected);
        var studentId = teacherHomeData.myStudents[idx].id;
        var newGrade = teacherHomeData.myStudents[idx].grade;
        var newComments = teacherHomeData.myStudents[idx].comments;
        teacherHomeData.updateReportCard(studentId, newGrade, newComments);
        //console.log("comments is: " + newComments);
        
        teacherHomeData.reset();
    };
    
    teacherHomeData.reset = function () {
        teacherHomeData.selected = {};
    };
    
    teacherHomeData.updateReportCard = function(studentId, newGrade, newComments) {
    	$http({
    		url: '/KinderSpot/report-cards/' + studentId,
    		method: "PUT",
    		headers: {'Content-Type': 'application/json'},
    		//data: newGrade
    		data: { "grade": newGrade, "comments": newComments }
    	})
    	.then(function(response) {
    		console.log("successfully updated grade");
    	},
    	function (response) {
    		console.log("failed to update grade");
    	})
    }
    
    //End in-line grade stuff

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
			
			teacherHomeData.myStudents = response.data;
			studentsService.setStudents(response.data);
			teacherHomeData.showGrades();
		}, 
		function(response) { // optional
			console.log("Failed.");
		});
	};//ends showStudents()
	teacherHomeData.showStudents();
	
	teacherHomeData.showGrades = function() {
		$http({
			url: '/KinderSpot/' + loggedUser.id + '/report-cards/',
			method: "GET",
			headers: {'Content-Type': 'application/json'}
		})
		.then(function(response) {
			teacherHomeData.myStudentGrades = response.data;
			for (var i = 0; i < teacherHomeData.myStudents.length; i++) {
				teacherHomeData.myStudents[i].grade = teacherHomeData.myStudentGrades[i].grade;
			}
		},
		function(response) {
			console.log("Failed to get grades");
		});
	};

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
			url: '/KinderSpot/meeting/' + loggedUser.id,
			method: "GET",
			headers: {'Content-Type': 'application/json'}
		})
		.then(function(response){	
			teacherHomeData.myMeetings = response.data;
			teacherHomeData.meetingsBadge = response.data.length; //Sets the red badge.
		},
		function(response){
			console.log("Failed.");
		});	
	}
	teacherHomeData.showMeetings();


	teacherHomeData.createMeeting = function (parent,reason,date)
	{	

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
	

	teacherHomeData.showEventTypes = function ()
	{
		$http({
			url: '/KinderSpot/event/Type',
			method: "GET",
			headers: {'Content-Type': 'application/json'}
			
		})
		.then(function(response){
			eventTypeService.setEventTypes (response.data);
		},
		function(response){
		console.log("Failed.");
	
	 });	
	}
	teacherHomeData.showEventTypes();


	teacherHomeData.createEvents = function (eventName,eventType, description, date)
	{


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

		document.getElementById(divId).style.color = "red"; //doesn't work.
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
	
	teacherHomeData.openMeetingModal = function(size) {
		
		var modalInstance = $uibModal.open({
			animation: teacherHomeData.animationsEnabled,
			templateUrl: 'NewMeetingModal.html',
			controller: 'MeetingModalCtrl',
			size: size,
			resolve: {

			}
		});

		modalInstance.result.then(function () {
			
		}, function () {
			
		});
	};

	teacherHomeData.openPhotosModal = function(event) {
		
		eventIdService.setEvent(event);

		var modalInstance = $uibModal.open({
			animation: teacherHomeData.animationsEnabled,
			templateUrl: 'PhotosModal.html',
			controller: 'PhotosModalCtrl',
			//size: size,
			resolve: {

			}
		});

		modalInstance.result.then(function () {
			
		}, function () {

		});
	}

	teacherHomeData.openEventModal = function(size) {

		var modalInstance = $uibModal.open({
			animation: teacherHomeData.animationsEnabled,
			templateUrl: 'NewEventModal.html',
			controller: 'EventModalCtrl',
			size: size,
			resolve: {

			}
		});

		modalInstance.result.then(function () {
			
		}, function () {

		});
	};

}); //ends teacherHomeCtrl