<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.0-rc.0/angular-animate.min.js"></script>
		<script src="https://rawgithub.com/gsklee/ngStorage/master/ngStorage.js"></script>
		<script src="https://apis.google.com/js/platform.js" async defer></script>
		<script src="<c:url value="/resources/js/app.js" />"></script>
		<script src="<c:url value="/resources/js/controllerjs/signup&in_controller.js" />"></script>
		<script src="<c:url value="/resources/js/servicejs/registerservice.js" />"></script>
		
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-social/5.0.0/bootstrap-social.css">
		<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
		
	
	   <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
		<!-- <link rel="stylesheet" type="text/css" href="css/content.css"> --><!-- changes-->
	 </head>
<body class="" style="" ng-app="app">
  	<div class=".container-fluid header">
  		<div class="col-md-6 col-lg-6 col-sm-6 col-xs-6 logo_div">
  				<a href="#"><img src="resources/images/logo.png" alt="Saptalabs" title="Saptalabs" ></a>
  		</div>
  		<div class="col-md-6 col-lg-6 col-sm-6 col-xs-6 MAR5PXAUTO">
  			<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12 login_div">
  				<ul id="menu" class="index_navigation_right">
  					<li class="MRGR15PX  "><a href="#" id="" class="PADDR15PX BORDRT1PX">Download Seller App</a></li>
					<li class="MRGR15PX "><a href="#" id="" class="PADDR15PX BORDRT1PX">Contact Seller Support</a></li>					
					<li class="MRGR15PX "><a href="#" id="login" class="PADDR15PX" data-toggle="modal" data-target="#loginDesc"><span class="glyphicon glyphicon-user " id="loginBtn" ></span> Login</a></li>
				</ul>	
  			</div>
  			<div class="col-md-12 col-lg-12 col-sm-12 col-xs-12 menu_div">
  				<ul id="menu" class="index_menu_right">
					<li ><a href="#" id="" class="index_menu_a PADDR15PX">Menu - 1</a></li>
					<li ><a href="#" id="" class="index_menu_a PADDR15PX">Menu - 2</a></li>
					<li ><a href="#" id="" class="index_menu_a PADDR15PX">Menu - 3</a></li>
					<li ><a href="#" id="" class="index_menu_a PADDR15PX">Menu - 4</a></li>
				</ul>
  			</div>
  		</div>
  		
  	</div>
  <script type="in/Login"></script>
  		<!-- START -- signup Modal Secion -->
		<section>
		
			<div class="modal fade" id="signupDesc" role="dialog" ng-controller="signupform">

				<div class="modal-dialog modal-sm" >
					<div class="modal-content">
						<div class="modal-header">
				           <button type="button" ng-click="reload()" class="close" data-dismiss="modal">&times;</button>
				           <h4 class="modal-title">Sign Up</h4>
				        </div>
				       	<div class="modal-body">
					       	<form name="signupForm" ng-submit="register(user)" novalidate>				       		
						           <div class="inner-addon left-addon" >
									    <i class="glyphicon glyphicon-user"></i>
									    <input type="text" placeholder="Username" id="Username" class="form-control" name="name" ng-model="user.name" required/>
									    <p ng-show="signupForm.name.$invalid && signupForm.name.$touched" class="COLORRED FONT12PX">Username is required.</p>

									</div>
									<div class="inner-addon left-addon">
									    <i class="glyphicon glyphicon-envelope"></i>
									    <input placeholder="Email" type="email" name="email" class="form-control" ng-model="user.email" required/>
									    <p ng-show="signupForm.email.$invalid && signupForm.email.$touched" class="COLORRED FONT12PX">Enter a valid email.</p>
									    
									</div>
									<div class="inner-addon left-addon">
									    <i class="glyphicon glyphicon-phone"></i>
									    <input placeholder="Mobile Number" type="text" name="phone" class="form-control" ng-model="user.phone" ng-pattern="phoneNumbr" maxlength="10" required/>
									    <span ng-show="signupForm.phone.$invalid && signupForm.phone.$touched" class="COLORRED FONT12PX">
									    <span class="error" ng-show="signupForm.phone.$error.required" class="COLORRED FONT12PX">Mobile Number Required!</span>
      									</span>
									</div>
									<div class="inner-addon left-addon">
									    <i class="glyphicon glyphicon-lock"></i>
									    <input type="password" placeholder="Password" class="form-control" name="password" ng-model="user.password" required />
									    <p ng-show="signupForm.password.$invalid && signupForm.password.$touched" class="COLORRED FONT12PX">Password is required.</p>
									   
									</div>
									<div class="inner-addon left-addon">
									    <i class="glyphicon glyphicon-lock"></i>
									    <input type="password" placeholder="Confirm Password" class="form-control" name="verify" ng-model="user.verify" nx-equal="user.password" required/>
									    <span ng-show="signupForm.verify.$invalid && signupForm.verify.$touched">
									    	<span ng-show="signupForm.verify.$error.required || signupForm.password.$touched" class="COLORRED FONT12PX">Confirm your Password.</span>
										</span>
									</div>	
									<div class="TXTCENTER">	
										<button type="submit" class="btn btn-primary" ng-disabled="signupForm.$invalid" >Sign Up</button>	
									</div>	
							</form>

							<div class="or">
								<span class="or_text" ng-click="togglesocialSignupmodel()">Social Sign Up
									<p><img ng-class="{true: 'rotate', false: 'rotateCounterwise'}[isActive]" src="resources/images/down-arrow.png" class="MRGRGT DWNARW" /></p></span>
							</div>

							<div class="social_login_div FONT12PX slide-toggle" ng-hide="soci_sign_model">
								<div class=" col-md-12 col-lg-12 col-sm-12 signup_model_animtn">
									<a class="btn btn-block btn-social btn-facebook PADDL40 facebookcolor" ng-click="FBlogin()">
										<span class="fa fa-facebook"></span> Sign Up with Facebook
									</a>
								</div>
								<div class=" col-md-12 col-lg-12 col-sm-12 signup_model_animtn">
									<a class="btn btn-block btn-social btn-google PADDL40 googlecolor">
										<span class="fa fa-google"></span> Sign Up with Google
									</a>
								</div>
								<div class=" col-md-12 col-lg-12 col-sm-12 signup_model_animtn">
									<a class="btn btn-block btn-social btn-linkedin PADDL40 linkedincolor" >
										<span class="fa fa-linkedin"></span> Sign Up with Linkedin

									</a>
								</div>
								<div class=" col-md-12 col-lg-12 col-sm-12 signup_model_animtn">
									<a class="btn btn-block btn-social btn-twitter PADDL40 twiitercolor">
										<span class="fa fa-twitter"></span> Sign Up with Twitter
									</a>
								</div>
							</div>
							<div class="mtext-center ">
								<section>
									<p class="grayColor FONT12PX TXTCENTER">By registering you confirm that you accept the 
									<a href="sample.html" onclick="#" class="TEXTBLUE">Terms and Conditions</a>  and <a href="#" class="TEXTBLUE">Privacy Policy</a> </p>
								</section>
							</div>
						</div>
						<div class="modal-footer">
					        <p class="FONT12PX grayColor">Already registerd user ?<a href="#" id="login" class="TEXTBLUE FONT12PX" data-toggle="modal" data-target="#loginDesc" ng-click="loginmodal()">&nbsp;Log In</a></p>
					    </div>
							

				        </div>
				    </div>
				</div>							
		</section> 
		<!-- END -- signup Modal Secion -->

		<!-- START -- login Modal Secion -->
		<section>
		
			<div class="modal fade" id="loginDesc" role="dialog" ng-controller="loginform">

				<div class="modal-dialog modal-sm" >
					<div class="modal-content">
						<div class="modal-header">
				           <button type="button" ng-click="reload()" class="close" ng-click = "reset()" data-dismiss="modal">&times;</button>
				           <h4 class="modal-title">Login</h4>
				        </div>
				       	<div class="modal-body">
					       	<form name="loginForm" ng-submit="login(user)" novalidate>				       		
						           <div class="inner-addon left-addon" >
									    <i class="glyphicon glyphicon-user"></i>
									    <input type="text" placeholder="Username" class="form-control" name="name" ng-model="user.name" required/>
									    <p ng-show="loginForm.name.$invalid && loginForm.name.$touched" class="COLORRED FONT12PX">Username is required.</p>

									</div>									
									<div class="inner-addon left-addon">
									    <i class="glyphicon glyphicon-lock"></i>
									    <input type="text" placeholder="Password" class="form-control" name="password" ng-model="user.password" required />
									    <p ng-show="loginForm.password.$invalid && loginForm.password.$touched" class="COLORRED FONT12PX">Password is required.</p>
									   
									</div>
									
									<div class="TXTCENTER">	
										<button type="submit" class="btn btn-primary" ng-disabled="loginForm.$invalid">Login</button>	
									</div>	
							</form>

							<div class="or">
								<div>
								<span class="or_text" ng-click="togglesocialloginupmodel()">Social Login
								<p><img ng-class="{true: 'rotate', false: 'rotateCounterwise'}[isActive]" src="resources/images/down-arrow.png" class="DWNARW"/></p></span>
							</div>
							</div>

							<div class="social_login_div FONT12PX slide-toggle" ng-hide="soci_login_model" >
								<div class=" col-md-12 col-lg-12 col-sm-12 signup_model_animtn">
									<a class="btn btn-block btn-social btn-facebook PADDL40 facebookcolor" ng-click="FBlogin()">
										<span class="fa fa-facebook"></span> Login with Facebook
									</a>
								</div>
								<div class=" col-md-12 col-lg-12 col-sm-12 signup_model_animtn">
									<a class="btn btn-block btn-social btn-google PADDL40 googlecolor"  id="googleSignIn" ng-click="signInWithGoogle()">
										<span class="fa fa-google"></span> Login with Google
									</a>
								</div>
								<div class=" col-md-12 col-lg-12 col-sm-12 signup_model_animtn">
									<a class="btn btn-block btn-social btn-linkedin PADDL40 linkedincolor" id="appBody" ng-click="onLinkedInLogin()">
										<span class="fa fa-linkedin"></span> Login with Linkedin
									</a>
									<script type="in/Login"></script>
								</div>
								<div class=" col-md-12 col-lg-12 col-sm-12 signup_model_animtn">
									<a class="btn btn-block btn-social btn-twitter PADDL40 twiitercolor">
										<span class="fa fa-twitter"></span> Login with Twitter
									</a>
								</div>
							</div>
						</div>	
						<div class="modal-footer">
							<span class=" forgot_pwd FLOATLEFT"> <a href="#" id="forgotpassword" class="TEXTBLUE FONT12PX" ng-click="resetmodal()">Forgot Password</a> </span>
					        <span class=""> <a href="#" id="login" class="TEXTBLUE FONT12PX FLOATRIGHT" ng-click="signmodal()">&nbsp;Sign Up</a></span>
					    </div>
							

				     </div>
				    </div>
				</div>							
		</section> 
		<!-- END -- login Modal Secion -->

		<!-- START -- forget password Modal Secion -->
		<section>
		
			<div class="modal fade" id="forgetpassDesc" role="dialog" ng-controller="resetform">

				<div class="modal-dialog modal-sm" >
					<div class="modal-content">
						<div class="modal-header">
				           <button type="button" ng-click="reload()" class="close" data-dismiss="modal">&times;</button>
				           <h4 class="modal-title">Reset your password</h4>
				        </div>
				       	<div class="modal-body">
					       	<form name="resetForm" ng-submit="submitForm(resetForm.$valid)" novalidate>
									<div class="inner-addon left-addon">
									    <i class="glyphicon glyphicon-envelope"></i>
									    <input placeholder="Email" type="email" name="email" class="form-control" ng-model="user.email" required/>
									    <p ng-show="resetForm.email.$invalid && resetForm.email.$touched" class="COLORRED FONT12PX">Enter a valid email.</p>
									    
									</div>
									<div class="TXTCENTER">	
										<button type="submit" class="btn btn-primary" ng-disabled="resetForm.$invalid">Reset Password</button>	
									</div>	
							</form>

						</div>	
						<div class="modal-footer">
							<p class="FONT12PX grayColor">Don't have an account ? <a href="#" id="login" class="TEXTBLUE FONT12PX" ng-click="loginmodal()">&nbsp;Sign Up</a></p>
					    </div>
							

				     </div>
				    </div>
				</div>							
		</section> 
		<!-- END -- forget password  Secion -->

  </body>
</html>