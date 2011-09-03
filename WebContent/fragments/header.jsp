<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
		<div id="header">
		
				<form action="">
				<input value="Search" />
				</form>
				
				<h1><a href="#">AreaID</a></h1>
				
				<h2>
				
						A Sociolinguistic Project, <a href="http://www.as.uni-heidelberg.de" target="_blank">English Dept.</a>, <a href="http://www.uni-heidelberg.de" target="_blank">Heidelberg University</a>
				
				</h2>
				
				<ul id="nav">
				
						<li><a href="index.html" <%if(request.getParameter("navHome")!=null) out.write("class='active'"); %>>Home</li>
						
						<li><a href="#Intro"><em>M</em>y Data</a></li>
											
						<li><a href="#Intro"><em>T</em>our</a></li>
						
						<li><a href="#About"><em>D</em>ata</a></li>
						
						<li><a href="#Examples"><em>P</em>apers</a></li>
						
						<li><a href="#Examples"><em>C</em>ontact</a></li>
				
				</ul>
				
				<!-- 
				<ul id="subnav">
				
						<li>Subnav:</li>
				
						<li><a href="index.html" accesskey="3" class="active"><em>3</em> Columns</a></li>

						
						<li><a href="#Intro"><em>I</em>ntro</a></li>
						
						<li><a href="#About"><em>A</em>bout</a></li>
						
						<li><a href="#Examples"><em>E</em>xamples</a></li>
						
						<li><a href="#Examples"><em>C</em>ontact</a></li>
				
				</ul>
		 		-->
		</div>