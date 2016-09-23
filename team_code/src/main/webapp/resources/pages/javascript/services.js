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

angular.module("myApp").service('eventTypeService', function() {

	var eventTypes = [];

	return {
		getEventTypes: function() {
			return eventTypes;
		},
		setEventTypes: function(value) {
			eventTypes = value;
		}
	}

});

//angular.module("myApp").service("datePicker", function () {
//	
//	var datePickerCtrl ={};
//	
//	
//	datePickerCtrl.today = function() {
//		datePickerCtrl.dt = new Date();
//	};
//	datePickerCtrl.today();
//
//	datePickerCtrl.clear = function() {
//		datePickerCtrl.dt = null;
//	};
//
//	datePickerCtrl.options = {
//			customClass: getDayClass,
//			minDate: new Date(),
//			showWeeks: true
//	};
//
//	// Disable weekend selection
//	function disabled(data) {
//		var date = data.date,
//		mode = data.mode;
//		return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
//	}
//
//	datePickerCtrl.toggleMin = function() {
//		datePickerCtrl.options.minDate = datePickerCtrl.options.minDate ? null : new Date();
//	};
//
//	datePickerCtrl.toggleMin();
//
//	datePickerCtrl.setDate = function(year, month, day) {
//		datePickerCtrl.dt = new Date(year, month, day);
//	};
//
//	var tomorrow = new Date();
//	tomorrow.setDate(tomorrow.getDate() + 1);
//	var afterTomorrow = new Date(tomorrow);
//	afterTomorrow.setDate(tomorrow.getDate() + 1);
//	datePickerCtrl.events = [
//	                 {
//	                	 date: tomorrow,
//	                	 status: 'full'
//	                 },
//	                 {
//	                	 date: afterTomorrow,
//	                	 status: 'partially'
//	                 }
//	                 ];
//
//	function getDayClass(data) {
//		var date = data.date,
//		mode = data.mode;
//		if (mode === 'day') {
//			var dayToCheck = new Date(date).setHours(0,0,0,0);
//
//			for (var i = 0; i < datePickerCtrl.events.length; i++) {
//				var currentDay = new Date(datePickerCtrl.events[i].date).setHours(0,0,0,0);
//
//				if (dayToCheck === currentDay) {
//					return datePickerCtrl.events[i].status;
//				}
//			}
//		}
//
//		return '';
//	}
//});


angular.module("myApp").service('fileUpload', ['$http', function ($http) {
    this.uploadFileToUrl = function(file, uploadUrl){
        var fd = new FormData();
        fd.append('key', file);
        $http.post(uploadUrl, fd, {
            transformRequest: angular.identity,
            headers: {'Content-Type': undefined}
        })
        .success(function(){
        })
        .error(function(){
        });
    }
}]);



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

angular.module("myApp").service('eventIdService', function () {
	var event = {};

	return {
		getEvent: function () {
			return event;
		},
		setEvent: function(value) {
			event = value;
		}
	};
});