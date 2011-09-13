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
	// Das braucht der Validator
	// für Select-Boxen
	$.metadata.setType("attr", "validate");

	$(document).ready(function() {
		// Automatische Validierung
		// fürs Formular einschalten:
		$("#fCreateAccount").validate({
			rules:{
				Repeat: {
					required: true,
					equalTo: "#SubjectPassword"
				}
			}
		});

		// Formular per Ajax absenden
		$('#fCreateAccount').ajaxForm({
		    target:     $('#contentResponse'), 
		    success:    function() { 
			   $('#fCreateAccount').effect("explode", function(){
				   $('#content').hide();
				   $('#content').html($('#contentResponse').html())
				   $('#content').show("explode");
				   
			   });
			} 
        }); 
	});
	
	$(function() {
		$( "#SubjectBirthdate" ).datepicker({
			changeMonth: true,
			changeYear: true,
			yearRange: '-80:-12', minDate: '-80y', maxDate: '-12y',
			dateFormat: "yy-mm-dd"
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
		<div id="contentResponse" style="display:none"></div>
		<div id="content">
		
				<h2 id="Intro"><a href="#">Registration</a></h2>
				
				<div id="formular">
				<form method="post" action="fragments/register.jsp" name="fCreateAccount" id="fCreateAccount">
				<fieldset>
				<legend>Create an Account</legend>
				<label for="SubjectFemale">Mr/Ms</label>
					<select name="SubjectFemale" id="SubjectFemale" title="Please select your address" validate="required:true">
							<option selected value="">--</option>
							<option value="true">Ms.</option>
							<option value="false">Mr.</option>
					</select><br />
				<label for="SubjectFirstName">First Name</label><input name="SubjectFirstName" id="SubjectFirstName" class="required" minlength="2" placeholder="First Name" type="text" />
				<label for="SubjectLastName">Last Name</label><input name="SubjectLastName" id="SubjectLastName" class="required" minlength="2" placeholder="Last Name" type="text" />
				<label for="SubjectEmail">E-Mail</label><input name="SubjectEmail" id="SubjectEmail" class="required email" placeholder="E-Mail Address" type="email" />
				<label for="SubjectBirthdate">Date of Birth</label><input name="SubjectBirthdate" id="SubjectBirthdate" class="required date" placeholder="Birthdate" type="text" />
				<label for="SubjectPassword">Password</label><input name="SubjectPassword" id="SubjectPassword" class="required" placeholder="Password" type="password" />
				<label for="Repeat">Password Rep.</label><input name="Repeat" id="Repeat" placeholder="Password Repeated" type="password" />
				<label for="cmdCreateAccount"></label><input name="cmdCreateAccount" value="Create Account" type="submit" />

				</fieldset>
			</form>
		</div>
					<br /><br /><br /><br /><br /><br />
					<br /><br /><br /><br /><br /><br />
	
						
		
		</div>
		

		
</div>
</div>


</body>
</html>