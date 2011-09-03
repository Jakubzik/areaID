<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<title>AreaID</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/1.css" type="text/css" media="screen,projection" />
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
			<form method="post" action="login.jsp" name="fLogIn" onsubmit="return login();">
				<fieldset>
				<legend>Credentials</legend>
				
				<label for="SubjectName">Name</label><input name="SubjectName" id="SubjectName" placeholder="Login Name" type="text">
				<label for="SubjectPassword">Password</label><input name="SubjectPassword" id="SubjectPassword" placeholder="Password" type="password">
				<label for="cmdLogin"></label><input name="cmdLogin" value="Log in" type="submit"><br />
				<label for="RegisterLink"></label><a id="RegisterLink" href="register.jsp">Create New Account</a>
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