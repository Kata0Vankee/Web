
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
	<meta charset="utf-8">
	<title>Sign Up</title>
	<!-- Mobile Specific Metas -->
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<!-- Font-->
	<link rel="stylesheet" type="text/css" href="css/opensans-font.css">
	<link rel="stylesheet" type="text/css" href="fonts/line-awesome/css/line-awesome.min.css">
	<!-- Jquery -->
	<link rel="stylesheet" href="https://jqueryvalidation.org/files/demo/site-demos.css">
	<!-- Main Style Css -->
        <link rel="stylesheet" href="css/style.css"/>
        <style type="text/css">
            body{
                background-image: url("./images/bg-01.jpg");
                background-size: cover;
            }
        </style>
</head>
<body>
    <div class="input-text">
	<div class="page-content">
		<div class="form-v4-content">
			<div class="form-left">
				<h2>Kata Vankee's Chat</h2>
				<p class="text-1">Chào mừng bạn đến với Kata Vankee's chat</p>
                                <p class="text-1">Hãy điền thông tin vào form để đến với chúng tôi</p>
				<p class="text-2"><span>Đảm bảo </span>thông tin của bạn sẽ được giữ an toàn.</p>
                                <form action="index.html">   
                                    <div class="form-left-last">
                                            <input type="submit" name="account" class="account" value="Have An Account">
                                    </div>
                                </form>
			</div>
			<form class="form-detail" action="SignUp" method="post" id="myform">
				<h2>REGISTER</h2>
				<div class="form-group">
					<div class="form-row form-row-1">
						<label for="first_name">First Name</label>
						<input type="text" name="firstname" id="first_name" class="input-text">
					</div>
					<div class="form-row form-row-1">
						<label for="last_name">Last Name</label>
						<input type="text" name="lastname" id="last_name" class="input-text">
					</div>
				</div>
				<div class="form-row">
					<label for="your_email">Your Email</label>
					<input type="text" name="email" id="your_email" class="input-text" required pattern="[^@]+@[^@]+.[a-zA-Z]{2,6}">
				</div>
                                <div >
					<label for="your_email">Username  <span id="user-result"></span></label> 
					<input type="text" name="username" id="username" class="input-text">
                                        
				</div>
				<div class="form-group">
					<div class="form-row form-row-1 ">
						<label for="password">Password</label>
						<input type="password" name="password" id="password" class="input-text" required>
					</div>
					<div class="form-row form-row-1">
						<label for="comfirm-password">Comfirm Password</label>
						<input type="password" name="comfirm_password" id="comfirm_password" class="input-text" required>
					</div>
				</div>
				
				<div class="form-row-last">
					<input type="submit" name="register" class="register" value="Register">
				</div>
			</form>
		</div>
        </div>
    </div>
	<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
	<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
	<script>
		$( "#myform" ).validate({
		  	rules: {
			    password: "required",
		    	comfirm_password: {
		      		equalTo: "#password"
		    	}
		  	},
		  	messages: {
		  		first_name: {
		  			required: "Please enter a firstname"
		  		},
		  		last_name: {
		  			required: "Please enter a lastname"
		  		},
		  		your_email: {
		  			required: "Please provide an email"
		  		},
		  		password: {
	  				required: "Please enter a password"
		  		},
		  		comfirm_password: {
		  			required: "Please enter a password",
		      		equalTo: "Wrong Password"
		    	}
		  	}
		});
	</script>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js" type="text/javascript"></script>
        <script type="text/javascript">
              $(document).ready(function () {
                   var x_timer;
                   $("#username").keyup(function (e) {
                        clearTimeout(x_timer);
                        var user_name = $(this).val();
                        x_timer = setTimeout(function () {
                            check_username_ajax(user_name);
                        }, 1000);
                        });

                   function check_username_ajax(username) {
                        
                        $.post('CheckEmailServlet', {'username': username}, function (data) {
                            $("#user-result").html(data);
                         });
                   }
               });
        </script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>