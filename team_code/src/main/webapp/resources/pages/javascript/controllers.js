

 /*angular.module("MyApp", []); // Defining a module
*/
//Runs the login Controller's POST method, passing in the user's form inputs as parameters. 
//The function is run when the user clicks the login button.
angular.module("myApp", []).controller("loginCtrl", function($scope, $http, $location, $window) {
	
	var loginData = this;
	
	//Gets the email and password from the user's form submit. Then runs the Login Controller's POST method.
	$scope.login = function(email, pass){
		$scope.myEmail = email;
		$scope.myPass = pass;
		
		$http({
            url: '/KinderSpot/home',
            method: "POST",
            data: { "email": $scope.myEmail,
      				"password": $scope.myPass		},
      		headers: {'Content-Type': 'application/json'}
        })
        .then(function(response) {
        	//success
            //console.log("email: " + response.data.email);
        	//console.log("password: " + response.data.password);
        	if(response.data.email === undefined && response.data.password === undefined)
        	{
        		//failed login
        		$scope.userMessage = "Invalid login. Try again.";
        	}
        	else
        	{
        		//sucessful login
        		$scope.userMessage = "Logging in...";
        		
        		//Checks user's role and sends them to appropriate login home page.
        		if(response.data.userRole.name === "Parent")
        			$window.location.href = '/KinderSpot/resources/pages/ParentHome.html';
        		else
        			$window.location.href = '/KinderSpot/resources/pages/TeacherHome.html';
        	}
        }, 
        function(response) { // optional
                // failed
        		console.log("Failed.");
        });
		
	}//ends $scope.login()
}); //ends app.controller()


/*angular.module("myApp").controller("parentHomeCtrl", function($scope, $http, $location, $window) {
	
	var parentHomeData = this;

	$scope.displayUser = function() {
		
		console.log("Hello user!");
	}
	
	$scope.displayUser();

}); //ends parentHomeApp.controller()
*/


