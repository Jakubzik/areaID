<%@ page language="java" contentType="text/html; charset=iso-8859-1"
    pageEncoding="iso-8859-1" import="javax.sql.DataSource,javax.naming.*,java.io.*,java.sql.*,de.uniheidelberg.as.areaID.data.shjCore"%>

<%!
/**
* Creates and returns a {@link java.lang.String} from t's stacktrace 
* @param t Throwable whose stack trace is required
* @return String representing the stack trace of the exception
*/
public String getStackTrace(Throwable t) {
   StringWriter stringWritter = new StringWriter();
   PrintWriter printWritter = new PrintWriter(stringWritter, true);
   t.printStackTrace(printWritter);
   printWritter.flush();
   stringWritter.flush(); 

  return stringWritter.toString();
}   

%>
<%!
String getDiagnose(){
	try{
		Class.forName("org.postgresql.Driver");
	}catch(Exception eWhat){
		return "<b>Der Treiber wurde nicht gefunden</b><br />" + 
				"Postgres benötigt einen JDBC-Treiber, der im Classpath auftauchen muss.";
	}
	
	try{
		Connection c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/template1",
            "postgres", "");
	}catch(Exception e){
		return "<b>DB-Verbindung kann nicht hergestellt werden.</b><br />" + 
				"Der Treiber ist zwar vorhanden, aber der Postgresql-Server meldet sich nicht.<br />" + 
				"Die Ursachen können vielfältig sein (ist PostgreSQL überhaupt installiert?<br />" +
				"Läuft der Postgres-Server? Läuft er auch mit der Option '-i'? Hat der Prozess des Servletcontainers<br />" + 
				"die notwendigen Rechte, um auf die Datenbanken zuzugreifen?)<br />" +
				"Erst wenn Sie diesen Schritt geklärt haben, kann die Diagnosefunktion bei der<br /> " + 
				"weiteren Installation helfen &mdash; sorry.<br /><br />" + 
				"Zur Fehlersuche hier die komplette Fehlermeldung beim Versuch, die Verbindung mit<br />" + 
				"dem Postgres-Server herzustellen:<br /><br />" +
				getStackTrace(e);
	}
	
	try{
		Connection c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/areaID",
            "postgres", "");
	}catch(Exception e){
		return "<b>DB-Verbindung zu areaID kann nicht hergestellt werden.</b><br />" + 
				"Soll die SGV-Datenbank jetzt neu eingerichtet werden (ist dies z.B. eine " +
						"Neuinstallation)?<br />" + 
						"<a href=\"install.jsp\">Klicken Sie hier zum einrichten der Datenbank</a>.";
	}
	
	try{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");

		// Look up our data source
		DataSource ds = (DataSource)  envCtx.lookup("jdbc/areaID");

		// Allocate and use a connection from the pool
		Connection conn = ds.getConnection();
		conn.close();
	}catch(Exception e){
		return "<b>Context nicht gefunden</b><br />" + getStackTrace(e);
	}
	return "<font color='green'>in Ordnung</font><br /><br />Vielleicht war es ein vorübergehendes Problem?";
}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@page %><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Studiengebührenverwaltung: Diagnose/Neue Installation</title>

</head>
<body>

<div id="content">
<div id="margin">

<h1 style="text-align: center;">Studiengebührenverwaltung: Diagnose/Neue Installation</h1>
<div align="left"><small><a href="index.jsp">Startseite</a> &raquo; &raquo; Diagnose</small></div>

<div class="formcenter">
<div class="formmargin">
<h2>Datenbankverbindung</h2>
<%=getDiagnose() %>
</div>
</div>
</div>
<br />
</body>

</html>