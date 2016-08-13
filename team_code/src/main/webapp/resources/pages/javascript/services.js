//This service module is used to pass objects between controllers. Passes logged in teacher/parent object.
angular.module("myApp").service('sharedProperties', function () {
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


angular.module("myApp").service('studentProperties', function () {
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


angular.module("myApp").service('studentsService', function() {

	var myStudents = [];

	return {
		getStudents: function() {
			return myStudents;
		},
		setStudents: function(value) {
			myStudents = value;
		}
	}

});

angular.module("myApp").service('attendanceService', function() {

	var attendanceMessage = "";

	return {
		getMessage: function() {
			return attendanceMessage;
		},
		setMessage: function(value) {
			attendanceMessage = value;
		}
	}

});