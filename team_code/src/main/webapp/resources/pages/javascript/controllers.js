angular.module("myApp", ['ui.router', 'angularModalService', 'ui.bootstrap']); // Defining a module


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
