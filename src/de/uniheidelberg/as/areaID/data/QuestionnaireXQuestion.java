
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
 *  Internal representation of mapping
 *  <pre>
 *  GENERAL INFORMATION ON CLASSES IN THIS PACKAGE:
 *  This class is the thinnest code-wrapper around table 'QuestionnaireXQuestion' 
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
public class QuestionnaireXQuestion extends shjCore{

	private static final long serialVersionUID = 1L;

//
// ------------------------------ ------------------------------
// 1.   P R I V A T E  D E C L A R A T I O N S
// ------------------------------ ------------------------------
//
	
	private boolean m_bIsDirty = false;

	private long m_lQuestionnaireID;
	private long m_lQuestionID;
	private String m_sQuestionnaireXQuestionCustom1;
	private String m_sQuestionnaireXQuestionCustom2;
	private String m_sQuestionnaireXQuestionCustom3;
	private long m_lQuestionnaireXQuestionSequence;

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
	 * retrieves conents of column <i>tblQuestionnaireXQuestion.lngQuestionnaireID</i>
	 **/
	public long getQuestionnaireID(){
		return this.m_lQuestionnaireID;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblQuestionnaireXQuestion.lngQuestionnaireID</i>
	 **/	
	public void setQuestionnaireID(long value){
		this.m_bIsDirty = (this.m_bIsDirty || (value != this.m_lQuestionnaireID));
		this.m_lQuestionnaireID=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblQuestionnaireXQuestion.lngQuestionID</i>
	 **/
	public long getQuestionID(){
		return this.m_lQuestionID;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblQuestionnaireXQuestion.lngQuestionID</i>
	 **/	
	public void setQuestionID(long value){
		this.m_bIsDirty = (this.m_bIsDirty || (value != this.m_lQuestionID));
		this.m_lQuestionID=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblQuestionnaireXQuestion.strQuestionnaireXQuestionCustom1</i>
	 **/
	public String getQuestionnaireXQuestionCustom1(){
		return this.m_sQuestionnaireXQuestionCustom1;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblQuestionnaireXQuestion.strQuestionnaireXQuestionCustom1</i>
	 **/	
	public void setQuestionnaireXQuestionCustom1(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sQuestionnaireXQuestionCustom1))));
		this.m_sQuestionnaireXQuestionCustom1=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblQuestionnaireXQuestion.strQuestionnaireXQuestionCustom2</i>
	 **/
	public String getQuestionnaireXQuestionCustom2(){
		return this.m_sQuestionnaireXQuestionCustom2;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblQuestionnaireXQuestion.strQuestionnaireXQuestionCustom2</i>
	 **/	
	public void setQuestionnaireXQuestionCustom2(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sQuestionnaireXQuestionCustom2))));
		this.m_sQuestionnaireXQuestionCustom2=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblQuestionnaireXQuestion.strQuestionnaireXQuestionCustom3</i>
	 **/
	public String getQuestionnaireXQuestionCustom3(){
		return this.m_sQuestionnaireXQuestionCustom3;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblQuestionnaireXQuestion.strQuestionnaireXQuestionCustom3</i>
	 **/	
	public void setQuestionnaireXQuestionCustom3(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sQuestionnaireXQuestionCustom3))));
		this.m_sQuestionnaireXQuestionCustom3=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblQuestionnaireXQuestion.lngQuestionnaireXQuestionSequence</i>
	 **/
	public long getQuestionnaireXQuestionSequence(){
		return this.m_lQuestionnaireXQuestionSequence;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblQuestionnaireXQuestion.lngQuestionnaireXQuestionSequence</i>
	 **/	
	public void setQuestionnaireXQuestionSequence(long value){
		this.m_bIsDirty = (this.m_bIsDirty || (value != this.m_lQuestionnaireXQuestionSequence));
		this.m_lQuestionnaireXQuestionSequence=value;
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

			"QuestionnaireID=\"" + m_lQuestionnaireID + "\"," +
			"QuestionID=\"" + m_lQuestionID + "\"," +
			"QuestionnaireXQuestionCustom1=\"" + getJSONString(m_sQuestionnaireXQuestionCustom1) + "\"," +
			"QuestionnaireXQuestionCustom2=\"" + getJSONString(m_sQuestionnaireXQuestionCustom2) + "\"," +
			"QuestionnaireXQuestionCustom3=\"" + getJSONString(m_sQuestionnaireXQuestionCustom3) + "\"," +
			"QuestionnaireXQuestionSequence=\"" + m_lQuestionnaireXQuestionSequence + "\"}";
	}

	/**
	 * @return String with an JSON-representation of this object.
	 **/
	public String toXMLString(){
		return 	

			"<QuestionnaireID>" + m_lQuestionnaireID + "</QuestionnaireID>"  + 
			"<QuestionID>" + m_lQuestionID + "</QuestionID>"  + 
			"<QuestionnaireXQuestionCustom1>" + m_sQuestionnaireXQuestionCustom1 + "</QuestionnaireXQuestionCustom1>"  + 
			"<QuestionnaireXQuestionCustom2>" + m_sQuestionnaireXQuestionCustom2 + "</QuestionnaireXQuestionCustom2>"  + 
			"<QuestionnaireXQuestionCustom3>" + m_sQuestionnaireXQuestionCustom3 + "</QuestionnaireXQuestionCustom3>"  + 
			"<QuestionnaireXQuestionSequence>" + m_lQuestionnaireXQuestionSequence + "</QuestionnaireXQuestionSequence>" ;
	}

//
// ------------------------------ ------------------------------
// 4.   S Q L  U T I L I T I E S
// ------------------------------ ------------------------------
//


	/**
	 * Where-clause for table 'tblQuestionnaireXQuestion.' Returns single row.
	 * @return String (SQL-code, postgres-specific)
	 **/
	private String getSQLWhereClause(){
		return 
			"\"lngQuestionnaireID\"=? AND " + 
			"\"lngQuestionID\"=?";
	}

	/**
	 * Get sql-code to delete this row in table 'tblQuestionnaireXQuestion.'
	 * @return String (SQL-code, postgres-specific, to delete this row;
	 **/
	private String toDBDeleteString(){
		return "delete from \"tblQuestionnaireXQuestion\" where ( " + this.getSQLWhereClause() + ");";
	}

	/**
 	 * Calls ps.setVALUE() for all wildcards in the .getSQLWhere-Statement.
	 * ii indicates the parameter count, and is inclusive.
	 * @throws SQLException 
	 **/
	private void pokeWhere(int ii, PreparedStatement ps) throws SQLException{
		
		ps.setLong(ii++, m_lQuestionnaireID);
		ps.setLong(ii++, m_lQuestionID);
	}

	/**
  	 * Fills ps with the values of this object
	 * @throws SQLException
	 **/
	private void pokeStatement(PreparedStatement ps) throws SQLException{
		
		ps.setLong(1, m_lQuestionnaireID);
		ps.setLong(2, m_lQuestionID);
		ps.setString(3, m_sQuestionnaireXQuestionCustom1);
		ps.setString(4, m_sQuestionnaireXQuestionCustom2);
		ps.setString(5, m_sQuestionnaireXQuestionCustom3);
		ps.setLong(6, m_lQuestionnaireXQuestionSequence);

	}

	/**
	 * Updates row in table 'tblQuestionnaireXQuestion.'
	 * @return PreparedStatemtent, which then needs to be poked and executed.
	 **/
	private String toDBUpdateString(){
		return "update \"tblQuestionnaireXQuestion\" set " +
			"\"lngQuestionnaireID\"=?, " +
			"\"lngQuestionID\"=?, " +
			"\"strQuestionnaireXQuestionCustom1\"=?, " +
			"\"strQuestionnaireXQuestionCustom2\"=?, " +
			"\"strQuestionnaireXQuestionCustom3\"=?, " +
			"\"lngQuestionnaireXQuestionSequence\"=?" +
			" where (" + this.getSQLWhereClause() + ");";
	}

	/**
	 * Get sql-code to add this row to table 'tblQuestionnaireXQuestion.'
	 * @return String (SQL-code, postgres-specific, to add this row).
	 **/
	private String toDBAddString(){
		return "insert into \"tblQuestionnaireXQuestion\" ( " +
			"\"lngQuestionnaireID\", \"lngQuestionID\", \"strQuestionnaireXQuestionCustom1\", \"strQuestionnaireXQuestionCustom2\", \"strQuestionnaireXQuestionCustom3\", \"lngQuestionnaireXQuestionSequence\" ) VALUES ( ?,?,?,?,?,?);";
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
	private void init(long lngQuestionnaireID, long lngQuestionID) throws SQLException, NamingException{

		this.m_lQuestionnaireID=lngQuestionnaireID;

		this.m_lQuestionID=lngQuestionID;

		
		PreparedStatement pstm = prepareStatement("select * from \"tblQuestionnaireXQuestion\" where (" + this.getSQLWhereClause() + ");");
		pokeWhere(1,pstm);

		ResultSet rst=pstm.executeQuery();
		if(rst.next()) this.initByRst(rst);
		rst.close();
		this.disconnect();
		rst=null;
	}	
	
	/**
	 * Constructor with suitable ResultSet.
	 * param rst ResultSet containing all fields of table 'tblQuestionnaireXQuestion'
	 * @throws SQLException 
	 **/
	private void initByRst(ResultSet rst) throws SQLException{
		this.m_lQuestionnaireID=rst.getLong("lngQuestionnaireID");
		this.m_lQuestionID=rst.getLong("lngQuestionID");
		this.m_sQuestionnaireXQuestionCustom1=rst.getString("strQuestionnaireXQuestionCustom1");
		this.m_sQuestionnaireXQuestionCustom2=rst.getString("strQuestionnaireXQuestionCustom2");
		this.m_sQuestionnaireXQuestionCustom3=rst.getString("strQuestionnaireXQuestionCustom3");
		this.m_lQuestionnaireXQuestionSequence=rst.getLong("lngQuestionnaireXQuestionSequence");	
	}

	/**
	* Loads object with values from JSON
	* Properties that the JSON does not 
	* contain remain unchanged
	* Date formats need to be ISO (yyyy-MM-dd)
	**/
	public void mergeByJSON(HttpServletRequest r) throws ParseException{

		if(r.getParameter("QuestionnaireID")!=null) this.m_lQuestionnaireID=Long.parseLong(r.getParameter("QuestionnaireID"));
		if(r.getParameter("QuestionID")!=null) this.m_lQuestionID=Long.parseLong(r.getParameter("QuestionID"));
		if(r.getParameter("QuestionnaireXQuestionCustom1")!=null) this.m_sQuestionnaireXQuestionCustom1=(r.getParameter("QuestionnaireXQuestionCustom1"));
		if(r.getParameter("QuestionnaireXQuestionCustom2")!=null) this.m_sQuestionnaireXQuestionCustom2=(r.getParameter("QuestionnaireXQuestionCustom2"));
		if(r.getParameter("QuestionnaireXQuestionCustom3")!=null) this.m_sQuestionnaireXQuestionCustom3=(r.getParameter("QuestionnaireXQuestionCustom3"));
		if(r.getParameter("QuestionnaireXQuestionSequence")!=null) this.m_lQuestionnaireXQuestionSequence=Long.parseLong(r.getParameter("QuestionnaireXQuestionSequence"));

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
		pokeWhere(7,pstm);
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
	public QuestionnaireXQuestion(){}	
	
	/**
	 * Instantiate object from db with unique key.
	 * @throws NamingException 
	 * @throws SQLException 
	 **/
	public QuestionnaireXQuestion(long lngQuestionnaireID, long lngQuestionID) throws SQLException, NamingException{
		this.init(lngQuestionnaireID, lngQuestionID);
		this.m_bIsDirty = false;
	}	
	
	/**
	 * Instantiate object from db with opened ResultSet.
	 * The resultset's '.next()' - method must have been called prior to this method.
	 * @throws SQLException 
	 **/
	public QuestionnaireXQuestion(ResultSet rst) throws SQLException{
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
	public QuestionnaireXQuestion(HttpServletRequest request)  throws ParseException{
		this.mergeByJSON(request);
		this.m_bIsDirty = true;
	}

  }//end class
