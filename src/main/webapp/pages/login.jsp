<div class="col-md-6 col-md-offset-3">
	<h4 style="color: blue;">
		<span class="glyphicon glyphicon-lock"></span> Login
	</h4>
    <form class="login-form" name="loginform" ng-submit="login()">
    
		<div class="alert alert-dismissible alert-danger" ng-show="error">{{message}}</div>
		<div class="form-group">
			<label for="userName"><span class="glyphicon glyphicon-user"></span> Username</label>
			<input autofocus type="text" ng-model="user.userName" class="form-control"
				id="userName" placeholder="Enter Username" required>
		</div>
		
		<div class="form-group">
			<label for="hashedPassword"><span class="glyphicon glyphicon-eye-close"></span> Password</label>
			<input type="password" ng-model="user.hashedPassword" class="form-control" id="password" 
				placeholder="Enter password">
		</div>
		
		<button type="submit" class="btn btn-default btn-primary btn-block">
			<span class="glyphicon glyphicon-off"></span> Login</button>
	    <br>
	</form>
</div>