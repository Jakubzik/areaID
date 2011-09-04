<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<title>AreaID</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/1.css" type="text/css" media="screen,projection" />
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.10/themes/base/jquery-ui.css" type="text/css" media="all" />
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.5.1/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.10/jquery-ui.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/jquery.metadata.js"></script>
<script src="js/jquery.form.js"></script>

<script>
	$(document).ready(function() {
		// Automatische Validierung
		// fürs Formular einschalten:
		$("#fLogIn").validate();

		// Formular per Ajax absenden
		$('#fLogIn').ajaxForm({
		    dataType: 'json', 
		    success:    function(data) {
		    	if(data.success=='true'){
					   $('#fLogIn').effect("explode", function(){
						   $('#content').hide();
						   $('#content').html($('#contentHidden').html());
						   $('#content').show('explode', {}, 500);
					   });
		    	}else{
			    	$('#fLogIn').effect('shake', {}, 'fast');
		    	} 
			} 
        }); 
	});
</script>
<div xmlns:cc="http://creativecommons.org/ns#" xmlns:dct="http://purl.org/dc/terms/" about="http://www.sixshootermedia.com/licensing/"><span property="dct:title">Free web site templates</span> (<a rel="cc:attributionURL" property="cc:attributionName" href="http://www.sixshootermedia.com">Six Shooter Media</a>) / <a rel="license" href="http://creativecommons.org/licenses/by-nc/3.0/us/">CC BY-NC 3.0</a></div>
</head>
 
<body>

<div id="wrapper">
<div id="innerwrapper">

<jsp:include page="fragments/header.jsp?navHome" />
		
		<div id="sidebar">
			<jsp:include page="fragments/news.jsp" />		
		</div>
		
		<div id="sidebarright">
			<jsp:include page="fragments/links.jsp" />
		</div>
		
		<div id="content">
		
		<h2 id="Intro"><a href="#">Login</a></h2>
		<div id="formular">
			<form method="post" action="fragments/login.jsp" name="fLogIn" id="fLogIn">
				<fieldset>
				<legend>Credentials</legend>
				
				<label for="SubjectEmail">E-Mail</label><input name="SubjectEmail" id="SubjectEmail" placeholder="E-Mail Address" class="required" type="email">
				<label for="SubjectPassword">Password</label><input name="SubjectPassword" id="SubjectPassword" placeholder="Password" class="required" type="password">
				<label for="cmdLogin"></label><input name="cmdLogin" value="Log in" type="submit"><br />
				<label for="RegisterLink"></label><a id="RegisterLink" href="register.jsp">Create New Account</a>
				</fieldset>
			</form>
		</div>
		<div id="contentHidden" style="display:none">
		<h2>Welcome Back</h2>
		You are successfully logged in.
			<br /><br /><br /><br /><br /><br />
	<br /><br /><br /><br /><br /><br />
	<br /><br /><br /><br /><br /><br />
	<br /><br /><br /><br /><br /><br />
		</div>
	<br /><br /><br /><br /><br /><br />
	<br /><br /><br /><br /><br /><br />
	
						
		
	</div>
		

		
</div>
</div>


</body>
</html>