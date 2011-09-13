<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.ResultSet,de.uniheidelberg.as.areaID.data.Subject, de.uniheidelberg.as.areaID.data.SubjectResidence"%>
<jsp:useBean id="subject" scope="session" class="de.uniheidelberg.as.areaID.data.Subject"></jsp:useBean>
<ul>    
<%
	ResultSet rst = subject.sqlQuery("select * from \"tblSubjectResidence\" where " + 
			"\"lngSubjectID\"=" + subject.getSubjectID() + 
			" order by \"dtmSubjectResidenceStart\" asc;");

	String sYearFrom = "";
	String sYearTo   = "";
	while(rst.next()){
		sYearFrom = rst.getString("dtmSubjectResidenceStart").substring(0,4);
		sYearTo = rst.getString("dtmSubjectResidenceStop").substring(0,4);
%>
	<li class="clickable" id="residenceEntry-<%=rst.getString("lngSubjectResidenceID") %>" onclick="residenceEdit('<%=rst.getString("strSubjectResidenceCity") %>', '<%=rst.getString("strSubjectResidenceCustom1") %>',<%=rst.getString("lngSubjectResidenceID") %>,<%=sYearFrom %>, <%=sYearTo %>)"><%=sYearFrom %>-<%=sYearTo %>: <%=rst.getString("strSubjectResidenceCity") %></li>
<%}rst.close(); rst=null;%>
</ul>