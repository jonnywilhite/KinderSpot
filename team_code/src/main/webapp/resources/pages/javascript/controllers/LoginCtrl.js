//Runs the login Controller's POST method, passing in the user's form inputs as parameters. 
//The function is run when the user clicks the login button.
angular.module("myApp").controller("loginCtrl", function($http, $location, $window, $state, sharedProperties) {

	var loginData = this;

	//Gets the email and password from the user's form submit. Then runs the Login Controller's POST method.
	loginData.login = function(email, pass){
		loginData.myEmail = email;
		loginData.myPass = pass;

		$http({
			url: '/KinderSpot/home',
			method: "POST",
			data: { "email": loginData.myEmail,
				"password": loginData.myPass		},
				headers: {'Content-Type': 'application/json'}
		})
		.then(function(response) {
			//success
			//console.log("email: " + response.data.email);
			if(response.data.email === undefined && response.data.password === undefined)
			{
				//failed login
				loginData.userMessage = "Invalid login. Try again.";
			}
			else
			{
				//sucessful login
				loginData.userMessage = "Logged in.";
				sharedProperties.setProperty(response.data);
				sharedProperties.getProperty();

				//Checks user's role and sends them to appropriate login home page.
				if(response.data.userRole.name === "Parent")
					$state.go('parentHomeState');
				else
					$state.go('teacherHomeState');
			}
		}, 
		function(response) { // optional
			// failed
			console.log("Failed.");
		});

	}//ends loginData.login()

}); //ends app.controller()