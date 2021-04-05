<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Image Management Utility SignUp</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<div class="container col-md-8 col-md-offset-2"
	style="overflow: auto; padding-top: .5em; background-color: #ccccff;">
	<div class="jumbotron text-center head-text"
		style="background-color: #809fff; color: white; font-size: 25px; font-weight: bold; height: 70px; padding: 1em;">
		<p>SignUp</p>
	</div>
	<form action="<%=request.getContextPath()%>/signup" method="post"
		data-toggle="validator">
		<div class="form-group row">
			<label for="uname" class="col-md-3 col-sm-3 col-form-label">
				First Name: </label>
			<div class="col-md-9 col-sm-8">
				<input type="text" autofocus class="form-control" id="firstname"
					placeholder="Enter First name" name="firstname"
					data-error="Enter your First name." required /> <small>
					<div class="help-block with-errors"></div>
				</small>
			</div>
		</div>
		<div class="form-group row">
			<label for="uname" class="col-md-3 col-sm-3 col-form-label">
				Last Name: </label>
			<div class="col-md-9 col-sm-8">
				<input type="text" autofocus class="form-control" id="lastname"
					placeholder="Enter Last name" name="lastname"
					data-error="Enter your last name." required /> <small>
					<div class="help-block with-errors"></div>
				</small>
			</div>
		</div>
		<div class="form-group row">
			<label for="uname" class="col-md-3 col-sm-3 col-form-label">
				User Name: </label>
			<div class="col-md-9 col-sm-8">
				<input type="text" autofocus class="form-control" id="username"
					placeholder="Enter user name" name="username"
					data-error="Enter your user name." required /> <small>
					<div class="help-block with-errors"></div>
				</small>
			</div>
		</div>
		<div class="form-group row">
			<label for="uname" class="col-md-3 col-sm-3 col-form-label">
				Password: </label>
			<div class="col-md-9 col-sm-8">
				<input type="password" autofocus class="form-control" id="password"
					placeholder="Enter your password" name="password"
					data-error="Enter your password." required /> <small>
					<div class="help-block with-errors"></div>
				</small>
			</div>
		</div>
		<div class="form-group row">
			<div class="col-md-6 col-sm-6 col-xs-6 col-offset-2 form-group">
				<a href="./login.jsp" style="font-size: 15px">&laquo;back</a>
			</div>
			<div class="col-md-3 col-sm-3 col-xs-3 form-group">
				<button type="submit" class="btn btn-primary" onclick="signUp()">SignUp</button>
			</div>

		</div>
	</form>
	<footer>
		<div class="container-fluid"
			style="background-color: #809fff; color: white">
			<div class="text-center">
				<p>Copyright 2020-2021 by Fresher Training</p>
			</div>
		</div>
	</footer>
</div>
<script>
	function signUp() {
		alert('Successful signup..');
	}
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.9/validator.min.js"></script>

</body>
</html>