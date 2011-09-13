
/*
 * Heiko Jakubzik, Mannheim, Germany: 2011
 *
 * Invented and initially created 2011 by 
 * Heiko Jakubzik and Frank Polzenhagen,
 * OpenSource Software. Redistribute under LPGL. 
 * 
 * http://www.as.uni-heidelberg.de
 * mailto:heiko.jakubzik@as.uni-heidelberg.de
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, is permitted. Please keep our names in the comments.
 *
 * Diese Freigabe erstreckt sich auf die Internetanwendung "areaID", 
 * also
 *
 * 1. das Datenschema,
 * 2. das Webarchiv  "areaID.war"
 * 3. die Resource Bundles und Interfaces,
 * 4. ggf. das Access Frontend "areaID",
 * 5. Konvertierungs-Skripte, XML und XSL Dateien, 
 *    sowie alle vbs und hta Anwendungen zu Konvertierung
 * 6. C# .NET Frontend Komponenten,
 * 7. die Webservice Spezifikation,
 * 8. die Dokumentation inkl. Bildern
 *
 * Bei Aenderungen am Datenmodell oder an Objekten _dieses_ Pakets 
 * (de.uni-heidelberg.as.areaID.data) wird besondere Sorgfalt und Umsicht empfohlen.
 *
 * THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL H. JAKUBZIK SOFTWARE-DEVELOPEMENT
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * 
 * CODE STRUCTURE:
 * 
 * 1. Private declarations (corresponding to table columns),
 * 2. Public  declarations (empty),
 *
 * 3. Private properties   (empty), 
 * 4. Public  properties   (refer to public declarations),
 * 
 * 5. Private methods	   (xml- and sql utilities, constructor-helpers),
 * 6. Public  methods	   (add, update, delete),
 * 
 * 7. Constructors
 *
 * NOTE YOUR CHANGES HERE:
 * @version 0-01-01 (auto-coded)
 * change date              change author           change description
 * ===================================================================
 * version 1-00	            
 *
 * August 30, 2011		    h. jakubzik             autoclass
 * 
 */

package de.uniheidelberg.as.areaID.data;
import java.sql.*;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;

/**
 *  Residences where a subject lived long enough to be influenced accent-wise.
 *  <pre>
 *  GENERAL INFORMATION ON CLASSES IN THIS PACKAGE:
 *  This class is the thinnest code-wrapper around table 'SubjectResidence' 
 *  Each table in this database has an object like this 
 *  attached to it. There is a property that corresponds 
 *  to each column of the table (or if there is not, see 
 *  the change-history of the source-code of this class). 
 * 
 *  Properties data-types correspond to data-types in the database.
 * 
 *  This object can be construced 
 *    (1) empty, 
 *    (2) with index-key values of this table (then, the object 
 *        is filled with properties from the table's row), 
 *    (3) with a resultset that contains columns with the 
 *        names (and fitting data-types), or, finally, 
 *    (4) by an XML-Node object containing all the properties 
 *        as subnodes (no attributes).
 *  
 *  The main methods of this class (as well as all the other classes of the 'data'-package) are "add", "update" and 
 *  "delete".
 *  To determine whether this object is (still) unchanged, there's the property 'isDirty().' It works 
 *  according to these rules:
 *  1) On creation and as default, the object is not dirty, with the exception of creation from JSON.
 *  2) After a property-set method is called that actually changes a value, the object is dirty,
 *  3) After a failed update-call, the object is dirty,
 *  4) After a failed add-call, the object is dirty,
 *  5) After calling 'delete' (failed or not), the object is dirty,
 *  6) After a successful update-call, the object is cleaned,
 *  7) After a successful add-call, the object is cleaned,
 *  8) Calling any other methods has no effect on the 'dirtiness' of the object.
 * 
 *  Please note that 'update' and 'add' are only executed if the object isDirty.
 *  Please note that this object may be unused as well as untested. It is automatically 
 *  constructed to fit the data model.
 *  </pre>
 **/
public class SubjectResidence extends shjCore{

	private static final long serialVersionUID = 1L;

//
// ------------------------------ ------------------------------
// 1.   P R I V A T E  D E C L A R A T I O N S
// ------------------------------ ------------------------------
//
	
	private boolean m_bIsDirty = false;

	private long m_lSubjectID;
	private long m_lSubjectResidenceID;
	private String m_sSubjectResidenceCountry;
	private String m_sSubjectResidenceZIP;
	private String m_sSubjectResidenceStreet;
	private String m_sSubjectResidenceCity;
	private Date m_dSubjectResidenceStart;
	private Date m_dSubjectResidenceStop;
	private String m_sSubjectResidenceCustom1;
	private String m_sSubjectResidenceCustom2;
	private String m_sSubjectResidenceCustom3;
	private String m_sSubjectResidenceVerification;

//
// ------------------------------ ------------------------------
// 2.   P U B L I C  P R O P E R T I E S
// ------------------------------ ------------------------------
//
	/**
 	 * true, if object differs from database entry.
	 * @return: indicator of identity of object and database-row.
	 **/
	public boolean isDirty(){
	  return this.m_bIsDirty;
	}


	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblSubjectResidence.lngSubjectID</i>
	 **/
	public long getSubjectID(){
		return this.m_lSubjectID;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblSubjectResidence.lngSubjectID</i>
	 **/	
	public void setSubjectID(long value){
		this.m_bIsDirty = (this.m_bIsDirty || (value != this.m_lSubjectID));
		this.m_lSubjectID=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblSubjectResidence.lngSubjectResidenceID</i>
	 **/
	public long getSubjectResidenceID(){
		return this.m_lSubjectResidenceID;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblSubjectResidence.lngSubjectResidenceID</i>
	 **/	
	public void setSubjectResidenceID(long value){
		this.m_bIsDirty = (this.m_bIsDirty || (value != this.m_lSubjectResidenceID));
		this.m_lSubjectResidenceID=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblSubjectResidence.strSubjectResidenceCountry</i>
	 **/
	public String getSubjectResidenceCountry(){
		return this.m_sSubjectResidenceCountry;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblSubjectResidence.strSubjectResidenceCountry</i>
	 **/	
	public void setSubjectResidenceCountry(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sSubjectResidenceCountry))));
		this.m_sSubjectResidenceCountry=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblSubjectResidence.strSubjectResidenceZIP</i>
	 **/
	public String getSubjectResidenceZIP(){
		return this.m_sSubjectResidenceZIP;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblSubjectResidence.strSubjectResidenceZIP</i>
	 **/	
	public void setSubjectResidenceZIP(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sSubjectResidenceZIP))));
		this.m_sSubjectResidenceZIP=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblSubjectResidence.strSubjectResidenceStreet</i>
	 **/
	public String getSubjectResidenceStreet(){
		return this.m_sSubjectResidenceStreet;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblSubjectResidence.strSubjectResidenceStreet</i>
	 **/	
	public void setSubjectResidenceStreet(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sSubjectResidenceStreet))));
		this.m_sSubjectResidenceStreet=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblSubjectResidence.strSubjectResidenceCity</i>
	 **/
	public String getSubjectResidenceCity(){
		return this.m_sSubjectResidenceCity;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblSubjectResidence.strSubjectResidenceCity</i>
	 **/	
	public void setSubjectResidenceCity(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sSubjectResidenceCity))));
		this.m_sSubjectResidenceCity=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblSubjectResidence.dtmSubjectResidenceStart</i>
	 **/
	public Date getSubjectResidenceStart(){
		return this.m_dSubjectResidenceStart;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblSubjectResidence.dtmSubjectResidenceStart</i>
	 **/	
	public void setSubjectResidenceStart(Date value){
		this.m_bIsDirty = (this.m_bIsDirty || (value != this.m_dSubjectResidenceStart));
		this.m_dSubjectResidenceStart=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblSubjectResidence.dtmSubjectResidenceStop</i>
	 **/
	public Date getSubjectResidenceStop(){
		return this.m_dSubjectResidenceStop;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblSubjectResidence.dtmSubjectResidenceStop</i>
	 **/	
	public void setSubjectResidenceStop(Date value){
		this.m_bIsDirty = (this.m_bIsDirty || (value != this.m_dSubjectResidenceStop));
		this.m_dSubjectResidenceStop=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblSubjectResidence.strSubjectResidenceCustom1</i>
	 **/
	public String getSubjectResidenceCustom1(){
		return this.m_sSubjectResidenceCustom1;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblSubjectResidence.strSubjectResidenceCustom1</i>
	 **/	
	public void setSubjectResidenceCustom1(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sSubjectResidenceCustom1))));
		this.m_sSubjectResidenceCustom1=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblSubjectResidence.strSubjectResidenceCustom2</i>
	 **/
	public String getSubjectResidenceCustom2(){
		return this.m_sSubjectResidenceCustom2;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblSubjectResidence.strSubjectResidenceCustom2</i>
	 **/	
	public void setSubjectResidenceCustom2(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sSubjectResidenceCustom2))));
		this.m_sSubjectResidenceCustom2=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblSubjectResidence.strSubjectResidenceCustom3</i>
	 **/
	public String getSubjectResidenceCustom3(){
		return this.m_sSubjectResidenceCustom3;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblSubjectResidence.strSubjectResidenceCustom3</i>
	 **/	
	public void setSubjectResidenceCustom3(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sSubjectResidenceCustom3))));
		this.m_sSubjectResidenceCustom3=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblSubjectResidence.strSubjectResidenceVerification</i>
	 **/
	public String getSubjectResidenceVerification(){
		return this.m_sSubjectResidenceVerification;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblSubjectResidence.strSubjectResidenceVerification</i>
	 **/	
	public void setSubjectResidenceVerification(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sSubjectResidenceVerification))));
		this.m_sSubjectResidenceVerification=value;
	}
	


//
// ------------------------------ ------------------------------
// 3.   X M L / JSON  U T I L I T I E S
// ------------------------------ ------------------------------
//
	
	/**
	 * @return String with an XML-representation of this object.
	 **/
	public String toJSON(){
		return "{" +

			"SubjectID=\"" + m_lSubjectID + "\"," +
			"SubjectResidenceID=\"" + m_lSubjectResidenceID + "\"," +
			"SubjectResidenceCountry=\"" + getJSONString(m_sSubjectResidenceCountry) + "\"," +
			"SubjectResidenceZIP=\"" + getJSONString(m_sSubjectResidenceZIP) + "\"," +
			"SubjectResidenceStreet=\"" + getJSONString(m_sSubjectResidenceStreet) + "\"," +
			"SubjectResidenceCity=\"" + getJSONString(m_sSubjectResidenceCity) + "\"," +
			"SubjectResidenceStart=\"" + m_dSubjectResidenceStart + "\"," +
			"SubjectResidenceStop=\"" + m_dSubjectResidenceStop + "\"," +
			"SubjectResidenceCustom1=\"" + getJSONString(m_sSubjectResidenceCustom1) + "\"," +
			"SubjectResidenceCustom2=\"" + getJSONString(m_sSubjectResidenceCustom2) + "\"," +
			"SubjectResidenceCustom3=\"" + getJSONString(m_sSubjectResidenceCustom3) + "\"," +
			"SubjectResidenceVerification=\"" + getJSONString(m_sSubjectResidenceVerification) + "\"}";
	}

	/**
	 * @return String with an JSON-representation of this object.
	 **/
	public String toXMLString(){
		return 	

			"<SubjectID>" + m_lSubjectID + "</SubjectID>"  + 
			"<SubjectResidenceID>" + m_lSubjectResidenceID + "</SubjectResidenceID>"  + 
			"<SubjectResidenceCountry>" + m_sSubjectResidenceCountry + "</SubjectResidenceCountry>"  + 
			"<SubjectResidenceZIP>" + m_sSubjectResidenceZIP + "</SubjectResidenceZIP>"  + 
			"<SubjectResidenceStreet>" + m_sSubjectResidenceStreet + "</SubjectResidenceStreet>"  + 
			"<SubjectResidenceCity>" + m_sSubjectResidenceCity + "</SubjectResidenceCity>"  + 
			"<SubjectResidenceStart>" + m_dSubjectResidenceStart + "</SubjectResidenceStart>"  + 
			"<SubjectResidenceStop>" + m_dSubjectResidenceStop + "</SubjectResidenceStop>"  + 
			"<SubjectResidenceCustom1>" + m_sSubjectResidenceCustom1 + "</SubjectResidenceCustom1>"  + 
			"<SubjectResidenceCustom2>" + m_sSubjectResidenceCustom2 + "</SubjectResidenceCustom2>"  + 
			"<SubjectResidenceCustom3>" + m_sSubjectResidenceCustom3 + "</SubjectResidenceCustom3>"  + 
			"<SubjectResidenceVerification>" + m_sSubjectResidenceVerification + "</SubjectResidenceVerification>" ;
	}

//
// ------------------------------ ------------------------------
// 4.   S Q L  U T I L I T I E S
// ------------------------------ ------------------------------
//


	/**
	 * Where-clause for table 'tblSubjectResidence.' Returns single row.
	 * @return String (SQL-code, postgres-specific)
	 **/
	private String getSQLWhereClause(){
		return 
			"\"lngSubjectID\"=? AND " + 
			"\"lngSubjectResidenceID\"=?";
	}

	/**
	 * Get sql-code to delete this row in table 'tblSubjectResidence.'
	 * @return String (SQL-code, postgres-specific, to delete this row;
	 **/
	private String toDBDeleteString(){
		return "delete from \"tblSubjectResidence\" where ( " + this.getSQLWhereClause() + ");";
	}

	/**
 	 * Calls ps.setVALUE() for all wildcards in the .getSQLWhere-Statement.
	 * ii indicates the parameter count, and is inclusive.
	 * @throws SQLException 
	 **/
	private void pokeWhere(int ii, PreparedStatement ps) throws SQLException{
		
		ps.setLong(ii++, m_lSubjectID);
		ps.setLong(ii++, m_lSubjectResidenceID);
	}

	/**
  	 * Fills ps with the values of this object
	 * @throws SQLException
	 **/
	private void pokeStatement(PreparedStatement ps) throws SQLException{
		
		ps.setLong(1, m_lSubjectID);
		ps.setLong(2, m_lSubjectResidenceID);
		ps.setString(3, m_sSubjectResidenceCountry);
		ps.setString(4, m_sSubjectResidenceZIP);
		ps.setString(5, m_sSubjectResidenceStreet);
		ps.setString(6, m_sSubjectResidenceCity);
		ps.setDate(7, m_dSubjectResidenceStart);
		ps.setDate(8, m_dSubjectResidenceStop);
		ps.setString(9, m_sSubjectResidenceCustom1);
		ps.setString(10, m_sSubjectResidenceCustom2);
		ps.setString(11, m_sSubjectResidenceCustom3);
		ps.setString(12, m_sSubjectResidenceVerification);

	}

	/**
	 * Updates row in table 'tblSubjectResidence.'
	 * @return PreparedStatemtent, which then needs to be poked and executed.
	 **/
	private String toDBUpdateString(){
		return "update \"tblSubjectResidence\" set " +
			"\"lngSubjectID\"=?, " +
			"\"lngSubjectResidenceID\"=?, " +
			"\"strSubjectResidenceCountry\"=?, " +
			"\"strSubjectResidenceZIP\"=?, " +
			"\"strSubjectResidenceStreet\"=?, " +
			"\"strSubjectResidenceCity\"=?, " +
			"\"dtmSubjectResidenceStart\"=?, " +
			"\"dtmSubjectResidenceStop\"=?, " +
			"\"strSubjectResidenceCustom1\"=?, " +
			"\"strSubjectResidenceCustom2\"=?, " +
			"\"strSubjectResidenceCustom3\"=?, " +
			"\"strSubjectResidenceVerification\"=?" +
			" where (" + this.getSQLWhereClause() + ");";
	}

	/**
	 * Get sql-code to add this row to table 'tblSubjectResidence.'
	 * @return String (SQL-code, postgres-specific, to add this row).
	 **/
	private String toDBAddString(){
		return "insert into \"tblSubjectResidence\" ( " +
			"\"lngSubjectID\", \"lngSubjectResidenceID\", \"strSubjectResidenceCountry\", \"strSubjectResidenceZIP\", \"strSubjectResidenceStreet\", \"strSubjectResidenceCity\", \"dtmSubjectResidenceStart\", \"dtmSubjectResidenceStop\", \"strSubjectResidenceCustom1\", \"strSubjectResidenceCustom2\", \"strSubjectResidenceCustom3\", \"strSubjectResidenceVerification\" ) VALUES ( ?,?,?,?,?,?,?,?,?,?,?,?);";
	}
	
	
//
// ------------------------------ ------------------------------
// 5.   C O N S T R U C T O R - H E L P E R S
// ------------------------------ ------------------------------
//
	
	/**
	 * Initialize class from ResultSet with minimal information.
	 * @param param (loaded from this column in db).
	 * @throws NamingException 
	 * @throws SQLException 
	 **/
	private void init(long lngSubjectID, long lngSubjectResidenceID) throws SQLException, NamingException{

		this.m_lSubjectID=lngSubjectID;

		this.m_lSubjectResidenceID=lngSubjectResidenceID;

		
		PreparedStatement pstm = prepareStatement("select * from \"tblSubjectResidence\" where (" + this.getSQLWhereClause() + ");");
		pokeWhere(1,pstm);

		ResultSet rst=pstm.executeQuery();
		if(rst.next()) this.initByRst(rst);
		rst.close();
		this.disconnect();
		rst=null;
	}	
	
	/**
	 * Constructor with suitable ResultSet.
	 * param rst ResultSet containing all fields of table 'tblSubjectResidence'
	 * @throws SQLException 
	 **/
	private void initByRst(ResultSet rst) throws SQLException{
		this.m_lSubjectID=rst.getLong("lngSubjectID");
		this.m_lSubjectResidenceID=rst.getLong("lngSubjectResidenceID");
		this.m_sSubjectResidenceCountry=rst.getString("strSubjectResidenceCountry");
		this.m_sSubjectResidenceZIP=rst.getString("strSubjectResidenceZIP");
		this.m_sSubjectResidenceStreet=rst.getString("strSubjectResidenceStreet");
		this.m_sSubjectResidenceCity=rst.getString("strSubjectResidenceCity");
		this.m_dSubjectResidenceStart=rst.getDate("dtmSubjectResidenceStart");
		this.m_dSubjectResidenceStop=rst.getDate("dtmSubjectResidenceStop");
		this.m_sSubjectResidenceCustom1=rst.getString("strSubjectResidenceCustom1");
		this.m_sSubjectResidenceCustom2=rst.getString("strSubjectResidenceCustom2");
		this.m_sSubjectResidenceCustom3=rst.getString("strSubjectResidenceCustom3");
		this.m_sSubjectResidenceVerification=rst.getString("strSubjectResidenceVerification");	
	}

	/**
	* Loads object with values from JSON
	* Properties that the JSON does not 
	* contain remain unchanged
	* Date formats need to be ISO (yyyy-MM-dd)
	**/
	public void mergeByJSON(HttpServletRequest r) throws ParseException{

		if(r.getParameter("SubjectID")!=null) this.m_lSubjectID=Long.parseLong(r.getParameter("SubjectID"));
		if(r.getParameter("SubjectResidenceID")!=null) this.m_lSubjectResidenceID=Long.parseLong(r.getParameter("SubjectResidenceID"));
		if(r.getParameter("SubjectResidenceCountry")!=null) this.m_sSubjectResidenceCountry=(r.getParameter("SubjectResidenceCountry"));
		if(r.getParameter("SubjectResidenceZIP")!=null) this.m_sSubjectResidenceZIP=(r.getParameter("SubjectResidenceZIP"));
		if(r.getParameter("SubjectResidenceStreet")!=null) this.m_sSubjectResidenceStreet=(r.getParameter("SubjectResidenceStreet"));
		if(r.getParameter("SubjectResidenceCity")!=null) this.m_sSubjectResidenceCity=(r.getParameter("SubjectResidenceCity"));
		if(r.getParameter("SubjectResidenceStart")!=null) this.m_dSubjectResidenceStart=(Date) (g_DATE_FORMAT_ISO.parse(r.getParameter("SubjectResidenceStart")));
		if(r.getParameter("SubjectResidenceStop")!=null) this.m_dSubjectResidenceStop=(Date) (g_DATE_FORMAT_ISO.parse(r.getParameter("SubjectResidenceStop")));
		if(r.getParameter("SubjectResidenceCustom1")!=null) this.m_sSubjectResidenceCustom1=(r.getParameter("SubjectResidenceCustom1"));
		if(r.getParameter("SubjectResidenceCustom2")!=null) this.m_sSubjectResidenceCustom2=(r.getParameter("SubjectResidenceCustom2"));
		if(r.getParameter("SubjectResidenceCustom3")!=null) this.m_sSubjectResidenceCustom3=(r.getParameter("SubjectResidenceCustom3"));
		if(r.getParameter("SubjectResidenceVerification")!=null) this.m_sSubjectResidenceVerification=(r.getParameter("SubjectResidenceVerification"));

	}
	
	
//
// ------------------------------ ------------------------------
//   P U B L I C   M E T H O D S
// ------------------------------ ------------------------------
//

//
// ------------------------------ ------------------------------
// 6.   S Q L  U T I L I T I E S
// ------------------------------ ------------------------------
//
	/**
	 * Add this data (the current object) to database. 
	 * @return true for success (which includes that nothing was done because the object wasn't dirty), 
	 * false otherwise. If there's serious trouble, errors are thrown.
	 * If the addition is successful, the object is not 'dirty.' If the addition failed, the object is 'dirty.'
	 * @throws NamingException 
	 * @throws SQLException 
	 **/
	public boolean add() throws SQLException, NamingException{
		if( !(isDirty()) ) return true;
		boolean bReturn = false;
		
		// initialize prepared statement
		PreparedStatement pstm = prepareStatement(toDBAddString());
		
		// load object-values into the prepared statement
		// and execute it.
		pokeStatement(pstm);
		try {
			pstm.execute();
			bReturn=true;
		} catch (SQLException e) {}
		this.m_bIsDirty = !(bReturn);
		return bReturn;
	}

	/**
	 * Update this row (the current data) in database (row is identified through unique key). 
	 * @return true for success (which includes that nothing was done because the object wasn't dirty), 
	 * false otherwise. If there's serious trouble, errors are thrown.
	 * If the update is successful, the object is not 'dirty.' If the update failes, the object is 'dirty.'	 
	 * @throws NamingException 
	 * @throws SQLException 
         **/
	public boolean update() throws SQLException, NamingException{
		if( !(isDirty()) ) return true;
		boolean bReturn = false;

		// initialize prepared statement
		PreparedStatement pstm = prepareStatement(toDBUpdateString());

		// load object-values into the prepared statement
		pokeStatement(pstm);

		// load the values of the where-clause into the prepared statement
		// and execute it.
		pokeWhere(13,pstm);
		bReturn	= pstm.execute();
		try {
			pstm.execute();
			bReturn=true;
		} catch (SQLException e) {}
		return bReturn;
	}

	/**
	 * Delete row from database (row is identified through unique key). 
	 * Calling this method automatically sets the dirty-flag to true.
	 * @return true for success, false otherwise. If there's serious trouble, errors are thrown.
	 * @throws NamingException 
	 * @throws SQLException 
         **/
	public boolean delete() throws SQLException, NamingException{
		PreparedStatement pstm = prepareStatement(toDBDeleteString());
		pokeWhere(1,pstm);
		this.m_bIsDirty = true;
		try {
			pstm.execute();
			return true;
		} catch (SQLException e) {}
		return false;
	}	
	
//
// ------------------------------ ------------------------------
// 6.   C O N S T R U C T O R S
// ------------------------------ ------------------------------
//

	/**
	 * Instantiate empty object.
	 **/
	public SubjectResidence(){}	
	
	/**
	 * Instantiate object from db with unique key.
	 * @throws NamingException 
	 * @throws SQLException 
	 **/
	public SubjectResidence(long lngSubjectID, long lngSubjectResidenceID) throws SQLException, NamingException{
		this.init(lngSubjectID, lngSubjectResidenceID);
		this.m_bIsDirty = false;
	}	
	
	/**
	 * Instantiate object from db with opened ResultSet.
	 * The resultset's '.next()' - method must have been called prior to this method.
	 * @throws SQLException 
	 **/
	public SubjectResidence(ResultSet rst) throws SQLException{
		this.initByRst(rst);
		this.m_bIsDirty = false;
	}

	/**
	 * Instantiate object from JSON object in request. 
	 * In contrast to all other constructors,
	 * this one sets .isDirty to true (because an 
	 * instantiation from JSON probably means that 
	 * the values are different from the ones in the 
	 * database.	  
	 * @throws ParseException, if a date can't be read.
	 **/
	public SubjectResidence(HttpServletRequest request)  throws ParseException{
		this.mergeByJSON(request);
		this.m_bIsDirty = true;
	}

  }//end class
