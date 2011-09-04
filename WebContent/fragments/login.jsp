<%@ page import="de.uniheidelberg.as.areaID.data.Subject,java.sql.ResultSet"%>
<%
	Subject subject=new Subject();
	// Make SQL-Injection safe:
	ResultSet rs = subject.sqlQuery("select * from \"tblSubject\" where \"strSubjectEmail\"='" + 
			request.getParameter("SubjectEmail").toLowerCase() + "' and \"strSubjectPassword\"='" + 
			request.getParameter("SubjectPassword")+ "'");
	if(rs.next()){
		subject=new Subject(rs);
	}else{
		subject=null;
	}
%>{"success":"<%=subject!=null %>"}