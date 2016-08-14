angular.module("myApp").controller("DatepickerDemoCtrl", function ($scope,$controller) {
	
	var datePickerCtrl = $scope.$new();
	
	$controller("teacherHomeCtrl",{$scope : datePickerCtrl});
	
	
	datePickerCtrl.today = function() {
		datePickerCtrl.dt = new Date();
	};
	datePickerCtrl.today();

	datePickerCtrl.clear = function() {
		datePickerCtrl.dt = null;
	};

	datePickerCtrl.options = {
			customClass: getDayClass,
			minDate: new Date(),
			showWeeks: true
	};

	// Disable weekend selection
	function disabled(data) {
		var date = data.date,
		mode = data.mode;
		return mode === 'day' && (date.getDay() === 0 || date.getDay() === 6);
	}

	datePickerCtrl.toggleMin = function() {
		datePickerCtrl.options.minDate = datePickerCtrl.options.minDate ? null : new Date();
	};

	datePickerCtrl.toggleMin();

	datePickerCtrl.setDate = function(year, month, day) {
		datePickerCtrl.dt = new Date(year, month, day);
	};

	var tomorrow = new Date();
	tomorrow.setDate(tomorrow.getDate() + 1);
	var afterTomorrow = new Date(tomorrow);
	afterTomorrow.setDate(tomorrow.getDate() + 1);
	datePickerCtrl.events = [
	                 {
	                	 date: tomorrow,
	                	 status: 'full'
	                 },
	                 {
	                	 date: afterTomorrow,
	                	 status: 'partially'
	                 }
	                 ];

	function getDayClass(data) {
		var date = data.date,
		mode = data.mode;
		if (mode === 'day') {
			var dayToCheck = new Date(date).setHours(0,0,0,0);

			for (var i = 0; i < datePickerCtrl.events.length; i++) {
				var currentDay = new Date(datePickerCtrl.events[i].date).setHours(0,0,0,0);

				if (dayToCheck === currentDay) {
					return datePickerCtrl.events[i].status;
				}
			}
		}

		return '';
	}
});