angular.module("myApp").controller("viewStudentCtrl", function($http, sharedProperties, studentProperties) {
	var viewStudentData = this;

	viewStudentData.showStudent = function() {
		viewStudentData.currentStudent = studentProperties.getStudent();
	}

	viewStudentData.showStudent();


	viewStudentData.showGrade = function() {
		$http({
			url: "/KinderSpot/report-cards/" + viewStudentData.currentStudent.id,
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
			url: "/KinderSpot/attendance/" + viewStudentData.currentStudent.id,
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

	viewStudentData.showBadges = function() {
		$http({
			url: "/KinderSpot/badges/" + viewStudentData.currentStudent.id,
			method: "GET",
			headers: {'Content-Type': 'application/json'}
		})
		.then(function(response) {
			viewStudentData.currentBadges = response.data;
		},
		function(response) {
			console.log(response);
		});
	};
	viewStudentData.showBadges();
	
	viewStudentData.getAllBadges= function() {
		$http({
			url: "/KinderSpot/getAllBadges/",
			method: "GET",
			headers: {'Content-Type': 'application/json'}
		})
		.then(function(response) {
			viewStudentData.allBadges = response.data;
		},
		function(response) {
			console.log(response);
		});
	};
	viewStudentData.getAllBadges();
	
	viewStudentData.addBadges = function(badge) {
		viewStudentData.myBadge = badge;
		$http({
			url: "/KinderSpot/addBadge/" + viewStudentData.currentStudent.id,
			method: "POST",
			data: { "id": viewStudentData.myBadge},
			headers: {'Content-Type': 'application/json'}
		})
		.then(function(response) {
			console.log("Successfully added badge")
			viewStudentData.showBadges();
		},
		function(response) {
			console.log("Failed");
		});
	};

}); //ends viewStudentCtrl