<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="de.uniheidelberg.as.areaID.data.Subject"%>

<%
	// Initialisierte Objekt mir
	// den Daten der zu registrierenden
	// Person (Name, E-Mail etc.)
	Subject subject=new Subject(request);
	subject.setSubjectID(subject.getNextID("lngSubjectID", "tblSubject", ""));
	
	// Prüfe Schnittstelle:
	// (1) Passwort und Passwort REP identisch?
	String sErr="";
	if(!subject.getSubjectPassword().equals(request.getParameter("Repeat")))
		sErr = "The password and its repetition were not identical, please try again.";
	
	// (2) Ist diese E-Mail in der Datenbank schon vergeben?
	if(subject.dbCount("lngSubjectID", "tblSubject", "\"strSubjectEmail\"='" + subject.getSubjectEmail() + "'")>0)
		sErr = "You already seem to be registered. Please use the 'Contact' form to apply for a new password if you have forgotten yours";
	
	// (3) Ist Name und Geburtsdatum schon vergeben?
	if(subject.dbCount("lngSubjectID", "tblSubject", "\"strSubjectLastName\"='" + subject.getSubjectLastName() + "' and \"dtmSubjectBirthdate\"='" + subject.getSubjectBirthdate() + "'")>0)		
		sErr = "You are already registered. Please use the 'Contact' form to apply for a new password if you have forgotten yours";
	
	boolean bOK=false;
	if(sErr.length()==0)
		bOK = subject.add();
	
	if(bOK){%>
		<h2>Welcome <%=subject.getSubjectFirstName() %></h2>
		Thank you for joining us.
		There are several ways how to continue...
		<br /><br /><br /><br /><br />
		<br /><br /><br /><br /><br />
		<br /><br /><br /><br /><br />
	<%}else{%>
		<h2>Sorry, we failed to create your account</h2>
		<%=sErr.length()==0 ? "Sorry, there's a technical problem: for some reason " +  
				"we were unable to process your request. Please try again later!" : sErr %>
		<br /><br /><br /><br /><br />
		<br /><br /><br /><br /><br />
		<br /><br /><br /><br /><br />		
	<%}
%>