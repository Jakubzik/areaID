
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
 *  The answer to an item of a questionnaire
 *  <pre>
 *  GENERAL INFORMATION ON CLASSES IN THIS PACKAGE:
 *  This class is the thinnest code-wrapper around table 'Answer' 
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
public class Answer extends shjCore{

	private static final long serialVersionUID = 1L;

//
// ------------------------------ ------------------------------
// 1.   P R I V A T E  D E C L A R A T I O N S
// ------------------------------ ------------------------------
//
	
	private boolean m_bIsDirty = false;

	private long m_lSubjectID;
	private long m_lAnswerID;
	private long m_lQuestionnaireID;
	private long m_lQuestionID;
	private String m_sQuestion;
	private String m_sAnswer;
	private String m_sAnswerOptionPicked;

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
	 * Who is answering?
	 * property: 
	 * @return Who is answering?
	 * retrieves conents of column <i>tblAnswer.lngSubjectID</i>
	 **/
	public long getSubjectID(){
		return this.m_lSubjectID;
	}

	/**
	 * Who is answering?
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblAnswer.lngSubjectID</i>
	 **/	
	public void setSubjectID(long value){
		this.m_bIsDirty = (this.m_bIsDirty || (value != this.m_lSubjectID));
		this.m_lSubjectID=value;
	}
	

	/**
	 * Identifier of this record
	 * property: 
	 * @return Identifier of this record
	 * retrieves conents of column <i>tblAnswer.lngAnswerID</i>
	 **/
	public long getAnswerID(){
		return this.m_lAnswerID;
	}

	/**
	 * Identifier of this record
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblAnswer.lngAnswerID</i>
	 **/	
	public void setAnswerID(long value){
		this.m_bIsDirty = (this.m_bIsDirty || (value != this.m_lAnswerID));
		this.m_lAnswerID=value;
	}
	

	/**
	 * What questionnaire is used?
	 * property: 
	 * @return What questionnaire is used?
	 * retrieves conents of column <i>tblAnswer.lngQuestionnaireID</i>
	 **/
	public long getQuestionnaireID(){
		return this.m_lQuestionnaireID;
	}

	/**
	 * What questionnaire is used?
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblAnswer.lngQuestionnaireID</i>
	 **/	
	public void setQuestionnaireID(long value){
		this.m_bIsDirty = (this.m_bIsDirty || (value != this.m_lQuestionnaireID));
		this.m_lQuestionnaireID=value;
	}
	

	/**
	 * What question is this the answer to?
	 * property: 
	 * @return What question is this the answer to?
	 * retrieves conents of column <i>tblAnswer.lngQuestionID</i>
	 **/
	public long getQuestionID(){
		return this.m_lQuestionID;
	}

	/**
	 * What question is this the answer to?
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblAnswer.lngQuestionID</i>
	 **/	
	public void setQuestionID(long value){
		this.m_bIsDirty = (this.m_bIsDirty || (value != this.m_lQuestionID));
		this.m_lQuestionID=value;
	}
	

	/**
	 * Copy of the question that was asked
	 * property: 
	 * @return Copy of the question that was asked
	 * retrieves conents of column <i>tblAnswer.strQuestion</i>
	 **/
	public String getQuestion(){
		return this.m_sQuestion;
	}

	/**
	 * Copy of the question that was asked
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblAnswer.strQuestion</i>
	 **/	
	public void setQuestion(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sQuestion))));
		this.m_sQuestion=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblAnswer.strAnswer</i>
	 **/
	public String getAnswer(){
		return this.m_sAnswer;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblAnswer.strAnswer</i>
	 **/	
	public void setAnswer(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sAnswer))));
		this.m_sAnswer=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblAnswer.strAnswerOptionPicked</i>
	 **/
	public String getAnswerOptionPicked(){
		return this.m_sAnswerOptionPicked;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblAnswer.strAnswerOptionPicked</i>
	 **/	
	public void setAnswerOptionPicked(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sAnswerOptionPicked))));
		this.m_sAnswerOptionPicked=value;
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
			"AnswerID=\"" + m_lAnswerID + "\"," +
			"QuestionnaireID=\"" + m_lQuestionnaireID + "\"," +
			"QuestionID=\"" + m_lQuestionID + "\"," +
			"Question=\"" + getJSONString(m_sQuestion) + "\"," +
			"Answer=\"" + getJSONString(m_sAnswer) + "\"," +
			"AnswerOptionPicked=\"" + getJSONString(m_sAnswerOptionPicked) + "\"}";
	}

	/**
	 * @return String with an JSON-representation of this object.
	 **/
	public String toXMLString(){
		return 	

			"<SubjectID>" + m_lSubjectID + "</SubjectID>"  + 
			"<AnswerID>" + m_lAnswerID + "</AnswerID>"  + 
			"<QuestionnaireID>" + m_lQuestionnaireID + "</QuestionnaireID>"  + 
			"<QuestionID>" + m_lQuestionID + "</QuestionID>"  + 
			"<Question>" + m_sQuestion + "</Question>"  + 
			"<Answer>" + m_sAnswer + "</Answer>"  + 
			"<AnswerOptionPicked>" + m_sAnswerOptionPicked + "</AnswerOptionPicked>" ;
	}

//
// ------------------------------ ------------------------------
// 4.   S Q L  U T I L I T I E S
// ------------------------------ ------------------------------
//


	/**
	 * Where-clause for table 'tblAnswer.' Returns single row.
	 * @return String (SQL-code, postgres-specific)
	 **/
	private String getSQLWhereClause(){
		return 
			"\"lngAnswerID\"=? AND " + 
			"\"lngQuestionnaireID\"=? AND " + 
			"\"lngQuestionID\"=?";
	}

	/**
	 * Get sql-code to delete this row in table 'tblAnswer.'
	 * @return String (SQL-code, postgres-specific, to delete this row;
	 **/
	private String toDBDeleteString(){
		return "delete from \"tblAnswer\" where ( " + this.getSQLWhereClause() + ");";
	}

	/**
 	 * Calls ps.setVALUE() for all wildcards in the .getSQLWhere-Statement.
	 * ii indicates the parameter count, and is inclusive.
	 * @throws SQLException 
	 **/
	private void pokeWhere(int ii, PreparedStatement ps) throws SQLException{
		
		ps.setLong(ii++, m_lAnswerID);
		ps.setLong(ii++, m_lQuestionnaireID);
		ps.setLong(ii++, m_lQuestionID);
	}

	/**
  	 * Fills ps with the values of this object
	 * @throws SQLException
	 **/
	private void pokeStatement(PreparedStatement ps) throws SQLException{
		
		ps.setLong(1, m_lSubjectID);
		ps.setLong(2, m_lAnswerID);
		ps.setLong(3, m_lQuestionnaireID);
		ps.setLong(4, m_lQuestionID);
		ps.setString(5, m_sQuestion);
		ps.setString(6, m_sAnswer);
		ps.setString(7, m_sAnswerOptionPicked);

	}

	/**
	 * Updates row in table 'tblAnswer.'
	 * @return PreparedStatemtent, which then needs to be poked and executed.
	 **/
	private String toDBUpdateString(){
		return "update \"tblAnswer\" set " +
			"\"lngSubjectID\"=?, " +
			"\"lngAnswerID\"=?, " +
			"\"lngQuestionnaireID\"=?, " +
			"\"lngQuestionID\"=?, " +
			"\"strQuestion\"=?, " +
			"\"strAnswer\"=?, " +
			"\"strAnswerOptionPicked\"=?" +
			" where (" + this.getSQLWhereClause() + ");";
	}

	/**
	 * Get sql-code to add this row to table 'tblAnswer.'
	 * @return String (SQL-code, postgres-specific, to add this row).
	 **/
	private String toDBAddString(){
		return "insert into \"tblAnswer\" ( " +
			"\"lngSubjectID\", \"lngAnswerID\", \"lngQuestionnaireID\", \"lngQuestionID\", \"strQuestion\", \"strAnswer\", \"strAnswerOptionPicked\" ) VALUES ( ?,?,?,?,?,?,?);";
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
	private void init(long lngAnswerID, long lngQuestionnaireID, long lngQuestionID) throws SQLException, NamingException{

		this.m_lAnswerID=lngAnswerID;

		this.m_lQuestionnaireID=lngQuestionnaireID;

		this.m_lQuestionID=lngQuestionID;

		
		PreparedStatement pstm = prepareStatement("select * from \"tblAnswer\" where (" + this.getSQLWhereClause() + ");");
		pokeWhere(1,pstm);

		ResultSet rst=pstm.executeQuery();
		if(rst.next()) this.initByRst(rst);
		rst.close();
		this.disconnect();
		rst=null;
	}	
	
	/**
	 * Constructor with suitable ResultSet.
	 * param rst ResultSet containing all fields of table 'tblAnswer'
	 * @throws SQLException 
	 **/
	private void initByRst(ResultSet rst) throws SQLException{
		this.m_lSubjectID=rst.getLong("lngSubjectID");
		this.m_lAnswerID=rst.getLong("lngAnswerID");
		this.m_lQuestionnaireID=rst.getLong("lngQuestionnaireID");
		this.m_lQuestionID=rst.getLong("lngQuestionID");
		this.m_sQuestion=rst.getString("strQuestion");
		this.m_sAnswer=rst.getString("strAnswer");
		this.m_sAnswerOptionPicked=rst.getString("strAnswerOptionPicked");	
	}

	/**
	* Loads object with values from JSON
	* Properties that the JSON does not 
	* contain remain unchanged
	* Date formats need to be ISO (yyyy-MM-dd)
	**/
	public void mergeByJSON(HttpServletRequest r) throws ParseException{

		if(r.getParameter("SubjectID")!=null) this.m_lSubjectID=Long.parseLong(r.getParameter("SubjectID"));
		if(r.getParameter("AnswerID")!=null) this.m_lAnswerID=Long.parseLong(r.getParameter("AnswerID"));
		if(r.getParameter("QuestionnaireID")!=null) this.m_lQuestionnaireID=Long.parseLong(r.getParameter("QuestionnaireID"));
		if(r.getParameter("QuestionID")!=null) this.m_lQuestionID=Long.parseLong(r.getParameter("QuestionID"));
		if(r.getParameter("Question")!=null) this.m_sQuestion=(r.getParameter("Question"));
		if(r.getParameter("Answer")!=null) this.m_sAnswer=(r.getParameter("Answer"));
		if(r.getParameter("AnswerOptionPicked")!=null) this.m_sAnswerOptionPicked=(r.getParameter("AnswerOptionPicked"));

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
		pokeWhere(8,pstm);
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
	public Answer(){}	
	
	/**
	 * Instantiate object from db with unique key.
	 * @throws NamingException 
	 * @throws SQLException 
	 **/
	public Answer(long lngAnswerID, long lngQuestionnaireID, long lngQuestionID) throws SQLException, NamingException{
		this.init(lngAnswerID, lngQuestionnaireID, lngQuestionID);
		this.m_bIsDirty = false;
	}	
	
	/**
	 * Instantiate object from db with opened ResultSet.
	 * The resultset's '.next()' - method must have been called prior to this method.
	 * @throws SQLException 
	 **/
	public Answer(ResultSet rst) throws SQLException{
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
	public Answer(HttpServletRequest request)  throws ParseException{
		this.mergeByJSON(request);
		this.m_bIsDirty = true;
	}

  }//end class
