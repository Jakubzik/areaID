
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
 *  An incentive to speak: either a text to read, or a picture to describe etc.
 *  <pre>
 *  GENERAL INFORMATION ON CLASSES IN THIS PACKAGE:
 *  This class is the thinnest code-wrapper around table 'AudioPrompt' 
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
public class AudioPrompt extends shjCore{

	private static final long serialVersionUID = 1L;

//
// ------------------------------ ------------------------------
// 1.   P R I V A T E  D E C L A R A T I O N S
// ------------------------------ ------------------------------
//
	
	private boolean m_bIsDirty = false;

	private long m_lAudioPromptID;
	private String m_sAudioPromptName;
	private long m_lAudioPromptType;
	private String m_sAudioPromptDescription;
	private String m_sAudioPromptText;
	private String m_sAudioPromptMediaPath;

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
	 * Identifier
	 * property: 
	 * @return Identifier
	 * retrieves conents of column <i>tblAudioPrompt.lngAudioPromptID</i>
	 **/
	public long getAudioPromptID(){
		return this.m_lAudioPromptID;
	}

	/**
	 * Identifier
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblAudioPrompt.lngAudioPromptID</i>
	 **/	
	public void setAudioPromptID(long value){
		this.m_bIsDirty = (this.m_bIsDirty || (value != this.m_lAudioPromptID));
		this.m_lAudioPromptID=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblAudioPrompt.strAudioPromptName</i>
	 **/
	public String getAudioPromptName(){
		return this.m_sAudioPromptName;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblAudioPrompt.strAudioPromptName</i>
	 **/	
	public void setAudioPromptName(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sAudioPromptName))));
		this.m_sAudioPromptName=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblAudioPrompt.intAudioPromptType</i>
	 **/
	public long getAudioPromptType(){
		return this.m_lAudioPromptType;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblAudioPrompt.intAudioPromptType</i>
	 **/	
	public void setAudioPromptType(long value){
		this.m_bIsDirty = (this.m_bIsDirty || (value != this.m_lAudioPromptType));
		this.m_lAudioPromptType=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblAudioPrompt.strAudioPromptDescription</i>
	 **/
	public String getAudioPromptDescription(){
		return this.m_sAudioPromptDescription;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblAudioPrompt.strAudioPromptDescription</i>
	 **/	
	public void setAudioPromptDescription(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sAudioPromptDescription))));
		this.m_sAudioPromptDescription=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblAudioPrompt.strAudioPromptText</i>
	 **/
	public String getAudioPromptText(){
		return this.m_sAudioPromptText;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblAudioPrompt.strAudioPromptText</i>
	 **/	
	public void setAudioPromptText(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sAudioPromptText))));
		this.m_sAudioPromptText=value;
	}
	

	/**
	 * 
	 * property: 
	 * @return 
	 * retrieves conents of column <i>tblAudioPrompt.strAudioPromptMediaPath</i>
	 **/
	public String getAudioPromptMediaPath(){
		return this.m_sAudioPromptMediaPath;
	}

	/**
	 * 
	 * Setter -- also sets 'dirty'-flag to true, if value differs from current value.
	 * @param value: sets db-column <i>tblAudioPrompt.strAudioPromptMediaPath</i>
	 **/	
	public void setAudioPromptMediaPath(String value){
		this.m_bIsDirty = ((this.m_bIsDirty)||(!(value.equals(this.m_sAudioPromptMediaPath))));
		this.m_sAudioPromptMediaPath=value;
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

			"AudioPromptID=\"" + m_lAudioPromptID + "\"," +
			"AudioPromptName=\"" + getJSONString(m_sAudioPromptName) + "\"," +
			"AudioPromptType=\"" + m_lAudioPromptType + "\"," +
			"AudioPromptDescription=\"" + getJSONString(m_sAudioPromptDescription) + "\"," +
			"AudioPromptText=\"" + getJSONString(m_sAudioPromptText) + "\"," +
			"AudioPromptMediaPath=\"" + getJSONString(m_sAudioPromptMediaPath) + "\"}";
	}

	/**
	 * @return String with an JSON-representation of this object.
	 **/
	public String toXMLString(){
		return 	

			"<AudioPromptID>" + m_lAudioPromptID + "</AudioPromptID>"  + 
			"<AudioPromptName>" + m_sAudioPromptName + "</AudioPromptName>"  + 
			"<AudioPromptType>" + m_lAudioPromptType + "</AudioPromptType>"  + 
			"<AudioPromptDescription>" + m_sAudioPromptDescription + "</AudioPromptDescription>"  + 
			"<AudioPromptText>" + m_sAudioPromptText + "</AudioPromptText>"  + 
			"<AudioPromptMediaPath>" + m_sAudioPromptMediaPath + "</AudioPromptMediaPath>" ;
	}

//
// ------------------------------ ------------------------------
// 4.   S Q L  U T I L I T I E S
// ------------------------------ ------------------------------
//


	/**
	 * Where-clause for table 'tblAudioPrompt.' Returns single row.
	 * @return String (SQL-code, postgres-specific)
	 **/
	private String getSQLWhereClause(){
		return 
			"\"lngAudioPromptID\"=?";
	}

	/**
	 * Get sql-code to delete this row in table 'tblAudioPrompt.'
	 * @return String (SQL-code, postgres-specific, to delete this row;
	 **/
	private String toDBDeleteString(){
		return "delete from \"tblAudioPrompt\" where ( " + this.getSQLWhereClause() + ");";
	}

	/**
 	 * Calls ps.setVALUE() for all wildcards in the .getSQLWhere-Statement.
	 * ii indicates the parameter count, and is inclusive.
	 * @throws SQLException 
	 **/
	private void pokeWhere(int ii, PreparedStatement ps) throws SQLException{
		
		ps.setLong(ii++, m_lAudioPromptID);
	}

	/**
  	 * Fills ps with the values of this object
	 * @throws SQLException
	 **/
	private void pokeStatement(PreparedStatement ps) throws SQLException{
		
		ps.setLong(1, m_lAudioPromptID);
		ps.setString(2, m_sAudioPromptName);
		ps.setLong(3, m_lAudioPromptType);
		ps.setString(4, m_sAudioPromptDescription);
		ps.setString(5, m_sAudioPromptText);
		ps.setString(6, m_sAudioPromptMediaPath);

	}

	/**
	 * Updates row in table 'tblAudioPrompt.'
	 * @return PreparedStatemtent, which then needs to be poked and executed.
	 **/
	private String toDBUpdateString(){
		return "update \"tblAudioPrompt\" set " +
			"\"lngAudioPromptID\"=?, " +
			"\"strAudioPromptName\"=?, " +
			"\"intAudioPromptType\"=?, " +
			"\"strAudioPromptDescription\"=?, " +
			"\"strAudioPromptText\"=?, " +
			"\"strAudioPromptMediaPath\"=?" +
			" where (" + this.getSQLWhereClause() + ");";
	}

	/**
	 * Get sql-code to add this row to table 'tblAudioPrompt.'
	 * @return String (SQL-code, postgres-specific, to add this row).
	 **/
	private String toDBAddString(){
		return "insert into \"tblAudioPrompt\" ( " +
			"\"lngAudioPromptID\", \"strAudioPromptName\", \"intAudioPromptType\", \"strAudioPromptDescription\", \"strAudioPromptText\", \"strAudioPromptMediaPath\" ) VALUES ( ?,?,?,?,?,?);";
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
	private void init(long lngAudioPromptID) throws SQLException, NamingException{

		this.m_lAudioPromptID=lngAudioPromptID;

		
		PreparedStatement pstm = prepareStatement("select * from \"tblAudioPrompt\" where (" + this.getSQLWhereClause() + ");");
		pokeWhere(1,pstm);

		ResultSet rst=pstm.executeQuery();
		if(rst.next()) this.initByRst(rst);
		rst.close();
		this.disconnect();
		rst=null;
	}	
	
	/**
	 * Constructor with suitable ResultSet.
	 * param rst ResultSet containing all fields of table 'tblAudioPrompt'
	 * @throws SQLException 
	 **/
	private void initByRst(ResultSet rst) throws SQLException{
		this.m_lAudioPromptID=rst.getLong("lngAudioPromptID");
		this.m_sAudioPromptName=rst.getString("strAudioPromptName");
		this.m_lAudioPromptType=rst.getLong("intAudioPromptType");
		this.m_sAudioPromptDescription=rst.getString("strAudioPromptDescription");
		this.m_sAudioPromptText=rst.getString("strAudioPromptText");
		this.m_sAudioPromptMediaPath=rst.getString("strAudioPromptMediaPath");	
	}

	/**
	* Loads object with values from JSON
	* Properties that the JSON does not 
	* contain remain unchanged
	* Date formats need to be ISO (yyyy-MM-dd)
	**/
	public void mergeByJSON(HttpServletRequest r) throws ParseException{

		if(r.getParameter("AudioPromptID")!=null) this.m_lAudioPromptID=Long.parseLong(r.getParameter("AudioPromptID"));
		if(r.getParameter("AudioPromptName")!=null) this.m_sAudioPromptName=(r.getParameter("AudioPromptName"));
		if(r.getParameter("AudioPromptType")!=null) this.m_lAudioPromptType=Long.parseLong(r.getParameter("AudioPromptType"));
		if(r.getParameter("AudioPromptDescription")!=null) this.m_sAudioPromptDescription=(r.getParameter("AudioPromptDescription"));
		if(r.getParameter("AudioPromptText")!=null) this.m_sAudioPromptText=(r.getParameter("AudioPromptText"));
		if(r.getParameter("AudioPromptMediaPath")!=null) this.m_sAudioPromptMediaPath=(r.getParameter("AudioPromptMediaPath"));

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
	public AudioPrompt(){}	
	
	/**
	 * Instantiate object from db with unique key.
	 * @throws NamingException 
	 * @throws SQLException 
	 **/
	public AudioPrompt(long lngAudioPromptID) throws SQLException, NamingException{
		this.init(lngAudioPromptID);
		this.m_bIsDirty = false;
	}	
	
	/**
	 * Instantiate object from db with opened ResultSet.
	 * The resultset's '.next()' - method must have been called prior to this method.
	 * @throws SQLException 
	 **/
	public AudioPrompt(ResultSet rst) throws SQLException{
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
	public AudioPrompt(HttpServletRequest request)  throws ParseException{
		this.mergeByJSON(request);
		this.m_bIsDirty = true;
	}

  }//end class
