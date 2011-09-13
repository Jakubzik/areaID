
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
 *  Internal representation of answer-options to questions
 *  <pre>
 *  GENERAL INFORMATION ON CLASSES IN THIS PACKAGE:
 *  This class is the thinnest code-wrapper around table 'QuestionXResponseOption' 
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
public class QuestionXResponseOption extends shjCore{

	private static final long serialVersionUID = 1L;

//
// ------------------------------ ------------------------------
// 1.   P R I V A T E  D E C L A R A T I O N S
// ------------------------------ ------------------------------
//
	
	private boolean m_bIsDirty = false;

	private long m_lQuestionID;
	private long m_lQuestionResponseOptionID;
	private String m_sQuestionXResponseOptionCustom1;
	private String m_sQuestionXResponseOptionCustom2;
	private String m_sQuestionXResponseOptionCustom3;

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
	 * retrieves conents of column <i>tblQuestionXResponseOption.lngQuestionID</i>
	 **/
	public long getQuestionID(){
		return this.m_lQuestionID;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblQuestionXResponseOption.lngQuestionID</i>
	 **/	
	public void setQuestionID(long value){
		this.m_bIsDirty = (this.m_bIsDirty || (value != this.m_lQuestionID));
		this.m_lQuestionID=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblQuestionXResponseOption.lngQuestionResponseOptionID</i>
	 **/
	public long getQuestionResponseOptionID(){
		return this.m_lQuestionResponseOptionID;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblQuestionXResponseOption.lngQuestionResponseOptionID</i>
	 **/	
	public void setQuestionResponseOptionID(long value){
		this.m_bIsDirty = (this.m_bIsDirty || (value != this.m_lQuestionResponseOptionID));
		this.m_lQuestionResponseOptionID=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblQuestionXResponseOption.strQuestionXResponseOptionCustom1</i>
	 **/
	public String getQuestionXResponseOptionCustom1(){
		return this.m_sQuestionXResponseOptionCustom1;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblQuestionXResponseOption.strQuestionXResponseOptionCustom1</i>
	 **/	
	public void setQuestionXResponseOptionCustom1(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sQuestionXResponseOptionCustom1))));
		this.m_sQuestionXResponseOptionCustom1=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblQuestionXResponseOption.strQuestionXResponseOptionCustom2</i>
	 **/
	public String getQuestionXResponseOptionCustom2(){
		return this.m_sQuestionXResponseOptionCustom2;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblQuestionXResponseOption.strQuestionXResponseOptionCustom2</i>
	 **/	
	public void setQuestionXResponseOptionCustom2(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sQuestionXResponseOptionCustom2))));
		this.m_sQuestionXResponseOptionCustom2=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblQuestionXResponseOption.strQuestionXResponseOptionCustom3</i>
	 **/
	public String getQuestionXResponseOptionCustom3(){
		return this.m_sQuestionXResponseOptionCustom3;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblQuestionXResponseOption.strQuestionXResponseOptionCustom3</i>
	 **/	
	public void setQuestionXResponseOptionCustom3(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sQuestionXResponseOptionCustom3))));
		this.m_sQuestionXResponseOptionCustom3=value;
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

			"QuestionID=\"" + m_lQuestionID + "\"," +
			"QuestionResponseOptionID=\"" + m_lQuestionResponseOptionID + "\"," +
			"QuestionXResponseOptionCustom1=\"" + getJSONString(m_sQuestionXResponseOptionCustom1) + "\"," +
			"QuestionXResponseOptionCustom2=\"" + getJSONString(m_sQuestionXResponseOptionCustom2) + "\"," +
			"QuestionXResponseOptionCustom3=\"" + getJSONString(m_sQuestionXResponseOptionCustom3) + "\"}";
	}

	/**
	 * @return String with an JSON-representation of this object.
	 **/
	public String toXMLString(){
		return 	

			"<QuestionID>" + m_lQuestionID + "</QuestionID>"  + 
			"<QuestionResponseOptionID>" + m_lQuestionResponseOptionID + "</QuestionResponseOptionID>"  + 
			"<QuestionXResponseOptionCustom1>" + m_sQuestionXResponseOptionCustom1 + "</QuestionXResponseOptionCustom1>"  + 
			"<QuestionXResponseOptionCustom2>" + m_sQuestionXResponseOptionCustom2 + "</QuestionXResponseOptionCustom2>"  + 
			"<QuestionXResponseOptionCustom3>" + m_sQuestionXResponseOptionCustom3 + "</QuestionXResponseOptionCustom3>" ;
	}

//
// ------------------------------ ------------------------------
// 4.   S Q L  U T I L I T I E S
// ------------------------------ ------------------------------
//


	/**
	 * Where-clause for table 'tblQuestionXResponseOption.' Returns single row.
	 * @return String (SQL-code, postgres-specific)
	 **/
	private String getSQLWhereClause(){
		return 
			"\"lngQuestionID\"=? AND " + 
			"\"lngQuestionResponseOptionID\"=?";
	}

	/**
	 * Get sql-code to delete this row in table 'tblQuestionXResponseOption.'
	 * @return String (SQL-code, postgres-specific, to delete this row;
	 **/
	private String toDBDeleteString(){
		return "delete from \"tblQuestionXResponseOption\" where ( " + this.getSQLWhereClause() + ");";
	}

	/**
 	 * Calls ps.setVALUE() for all wildcards in the .getSQLWhere-Statement.
	 * ii indicates the parameter count, and is inclusive.
	 * @throws SQLException 
	 **/
	private void pokeWhere(int ii, PreparedStatement ps) throws SQLException{
		
		ps.setLong(ii++, m_lQuestionID);
		ps.setLong(ii++, m_lQuestionResponseOptionID);
	}

	/**
  	 * Fills ps with the values of this object
	 * @throws SQLException
	 **/
	private void pokeStatement(PreparedStatement ps) throws SQLException{
		
		ps.setLong(1, m_lQuestionID);
		ps.setLong(2, m_lQuestionResponseOptionID);
		ps.setString(3, m_sQuestionXResponseOptionCustom1);
		ps.setString(4, m_sQuestionXResponseOptionCustom2);
		ps.setString(5, m_sQuestionXResponseOptionCustom3);

	}

	/**
	 * Updates row in table 'tblQuestionXResponseOption.'
	 * @return PreparedStatemtent, which then needs to be poked and executed.
	 **/
	private String toDBUpdateString(){
		return "update \"tblQuestionXResponseOption\" set " +
			"\"lngQuestionID\"=?, " +
			"\"lngQuestionResponseOptionID\"=?, " +
			"\"strQuestionXResponseOptionCustom1\"=?, " +
			"\"strQuestionXResponseOptionCustom2\"=?, " +
			"\"strQuestionXResponseOptionCustom3\"=?" +
			" where (" + this.getSQLWhereClause() + ");";
	}

	/**
	 * Get sql-code to add this row to table 'tblQuestionXResponseOption.'
	 * @return String (SQL-code, postgres-specific, to add this row).
	 **/
	private String toDBAddString(){
		return "insert into \"tblQuestionXResponseOption\" ( " +
			"\"lngQuestionID\", \"lngQuestionResponseOptionID\", \"strQuestionXResponseOptionCustom1\", \"strQuestionXResponseOptionCustom2\", \"strQuestionXResponseOptionCustom3\" ) VALUES ( ?,?,?,?,?);";
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
	private void init(long lngQuestionID, long lngQuestionResponseOptionID) throws SQLException, NamingException{

		this.m_lQuestionID=lngQuestionID;

		this.m_lQuestionResponseOptionID=lngQuestionResponseOptionID;

		
		PreparedStatement pstm = prepareStatement("select * from \"tblQuestionXResponseOption\" where (" + this.getSQLWhereClause() + ");");
		pokeWhere(1,pstm);

		ResultSet rst=pstm.executeQuery();
		if(rst.next()) this.initByRst(rst);
		rst.close();
		this.disconnect();
		rst=null;
	}	
	
	/**
	 * Constructor with suitable ResultSet.
	 * param rst ResultSet containing all fields of table 'tblQuestionXResponseOption'
	 * @throws SQLException 
	 **/
	private void initByRst(ResultSet rst) throws SQLException{
		this.m_lQuestionID=rst.getLong("lngQuestionID");
		this.m_lQuestionResponseOptionID=rst.getLong("lngQuestionResponseOptionID");
		this.m_sQuestionXResponseOptionCustom1=rst.getString("strQuestionXResponseOptionCustom1");
		this.m_sQuestionXResponseOptionCustom2=rst.getString("strQuestionXResponseOptionCustom2");
		this.m_sQuestionXResponseOptionCustom3=rst.getString("strQuestionXResponseOptionCustom3");	
	}

	/**
	* Loads object with values from JSON
	* Properties that the JSON does not 
	* contain remain unchanged
	* Date formats need to be ISO (yyyy-MM-dd)
	**/
	public void mergeByJSON(HttpServletRequest r) throws ParseException{

		if(r.getParameter("QuestionID")!=null) this.m_lQuestionID=Long.parseLong(r.getParameter("QuestionID"));
		if(r.getParameter("QuestionResponseOptionID")!=null) this.m_lQuestionResponseOptionID=Long.parseLong(r.getParameter("QuestionResponseOptionID"));
		if(r.getParameter("QuestionXResponseOptionCustom1")!=null) this.m_sQuestionXResponseOptionCustom1=(r.getParameter("QuestionXResponseOptionCustom1"));
		if(r.getParameter("QuestionXResponseOptionCustom2")!=null) this.m_sQuestionXResponseOptionCustom2=(r.getParameter("QuestionXResponseOptionCustom2"));
		if(r.getParameter("QuestionXResponseOptionCustom3")!=null) this.m_sQuestionXResponseOptionCustom3=(r.getParameter("QuestionXResponseOptionCustom3"));

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
		pokeWhere(6,pstm);
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
	public QuestionXResponseOption(){}	
	
	/**
	 * Instantiate object from db with unique key.
	 * @throws NamingException 
	 * @throws SQLException 
	 **/
	public QuestionXResponseOption(long lngQuestionID, long lngQuestionResponseOptionID) throws SQLException, NamingException{
		this.init(lngQuestionID, lngQuestionResponseOptionID);
		this.m_bIsDirty = false;
	}	
	
	/**
	 * Instantiate object from db with opened ResultSet.
	 * The resultset's '.next()' - method must have been called prior to this method.
	 * @throws SQLException 
	 **/
	public QuestionXResponseOption(ResultSet rst) throws SQLException{
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
	public QuestionXResponseOption(HttpServletRequest request)  throws ParseException{
		this.mergeByJSON(request);
		this.m_bIsDirty = true;
	}

  }//end class
