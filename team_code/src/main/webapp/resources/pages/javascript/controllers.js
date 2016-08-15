angular.module("myApp", ['ui.router', 'angularModalService', 'ui.bootstrap', 'ng-file-model']); // Defining a module


angular.module("myApp").config(function($stateProvider, $urlRouterProvider){

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

angular.module("myApp").directive("fileread", [function () {
	return {
		scope: {
			fileread: "="
		},
		link: function (scope, element, attributes) {
			element.bind("change", function (changeEvent) {
				var reader = new FileReader();
				reader.onload = function (loadEvent) {
					scope.$apply(function () {
						scope.fileread = loadEvent.target.result;
					});
				}
				reader.readAsDataURL(changeEvent.target.files[0]);
			});
		}
	}
}]);

angular.module("myApp").directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);