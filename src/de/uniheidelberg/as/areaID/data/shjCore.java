/**
 * Software-Entwicklung H. Jakubzik, Lizenz Version 1.0 (Uni Heidelberg)
 *
 * Copyright (c) 2011 Heiko Jakubzik/Frank Polzenhagen.  All rights reserved.
 *
 * http://www.as.uni-heidelberg.de
 * mailto:heiko.jakubzik@as.uni-heidelberg.de
 *
 * Das Rechtemanagement zur Neukompilierung unterliegt der
 * Kontrolle der Universtitaet Heidelberg. Fuer Kompilationen,
 * die von der Lizenznehmerin durchgefuehrt wurden, besteht
 * kein Anspruch auf Nachbesserung.
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
 * code-structure
 * --------------|
 *
 * 1.  constants: error codes									line  170
 *
 * 2.  public declarations										line  230
 *
 * 3.  private/protected declarations							line  260
 *
 * [ 4.  properties												line  272 ]
 *
 * 5.  public methods: db connectivity							line  303
 *
 * 6.  public methods: db utils									line  403
 *
 * 7.  public date/time utils									line  677
 *
 * 8.  String utils												line  829
 *
 * 9.  HTTP utils												line  894
 *
 * 10  XML utils												line 1035
 *
 * 11  Teacher log-in methods									line 1081
 *
 * 12. Uninteresting code (one-liners)							line 1222
 *
 * 13. Constructor												line 1282
 *
 *
 * @todo:
 *
 * @version August 2011 (creation)
 * change date				change author			change description
 */

package de.uniheidelberg.as.areaID.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.sql.DataSource;
import de.uniheidelberg.as.areaID.util.ResultSetSHJ;

/**
 * shj Standard Main Class for work with postgres databases.
 * Bundles core functions for db-connectivity (general)
 * Contains also several utilty-functions
 *
 * specializations to SignUpWiki (c) 2006, shj
 * =========================================================================================================================================================================================================================================
 *
 */
public class shjCore implements HttpSessionBindingListener{

	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------
	// 1.   C O N S T A N T S:  E R R O R   C O D E S
	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------

	private static final long serialVersionUID = 3153961357140549366L;

	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------
	// 2.   P U B L I C   C O N S T A N T S
	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------
	public static final String PORT_SSL_NO_CLIENT_AUTH	= "9443";
	public static final String PORT_SSL_CLIENT_AUTH		= "8443";
	public static final String PORT_HTTP_INSECURE		= "9449";

	public static final byte   POLICY_NO_RESTRAINT		= 0;
	public static final byte   POLICY_HTTPS				= 1;
	public static final byte   POLICY_HTTPS_IP_IDENT	= 2;
	public static final byte   POLICY_HTTPS_DIGITAL_ID	= 3;
	public static final byte   POLICY_HTTPS_IP_AND_DIGID= 4;
	
	public static final String 	g_STRING_UNINITIALIZED	= "#";
	public static final long	g_ID_UNINITIALIZED		= -4711; 

	public static final SimpleDateFormat g_ISO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat g_GERMAN_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");
	public static final SimpleDateFormat g_TIME_FORMAT = new SimpleDateFormat("HH:mm");
	public static final SimpleDateFormat g_TIMESTAMT_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm");

	
	 /**needed for base64
	  */
	  private static final int    fillchar = '=';

	                                // 00000000001111111111222222
	                                // 01234567890123456789012345
	 /**needed for base64
	  */
	  private static final String cvt = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

	                                // 22223333333333444444444455
	                                // 67890123456789012345678901
	                                + "abcdefghijklmnopqrstuvwxyz"

	                                // 555555556666
	                                // 234567890123
	                                + "0123456789+/";

	protected static final int m_iKATEGORIE_PERSONALMITTEL = 2;
	protected static final int m_iKATEGORIE_HILFSKRAFTMITTEL = 1;
	protected static final int m_iKATEGORIE_SACHMITTEL = 3;

	public static final DecimalFormat g_CURRENCY_FORMAT = new DecimalFormat("#,##0.00");
	public static final SimpleDateFormat g_DATE_FORMAT_GERMAN = new SimpleDateFormat("dd.MM.yyyy");
	public static final SimpleDateFormat g_DATE_FORMAT_ISO = new SimpleDateFormat("yyyy-MM-dd");
	public String g_HEUTE; // = g_DATE_FORMAT_GERMAN.format(new java.util.Date());
	public String g_TODAY_ISO;//
	public java.util.Date g_TODAY;
	
	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------
	// 2.   P U B L I C   D E C L A R A T I O N S
	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------

	public 		boolean 		isConnected				=		false;

    /**
     * Mainly for debugging services:
     * Instance variable that holds specific information
     * about the error (to aid the stack-trace :-)
     **/
  	public 		String 			strErrMsg				=		"";
	public 		String 			m_strDebug;


	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------
	// 3.   PRIVATE / PROTECTED   D E C L A R A T I O N S
	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------
  	private 	Connection 	conDB	= null;

  	private		static Context		m_ctx	= null;
  	private		static DataSource	m_DB	= null;

	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------
	// 5.   P U B L I C  M E T H O D S: D B-C O N N E C T I V I T Y
	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------
	
  	/**
	 * <pre>
	 * Used to initialize object variable conDB according to the information
	 * given in the Server-Context. 
	 * 
	 * Explicitly, this method connects to java:comp/env/jdbc/SGV
	 * </pre>
	 * @version 1-00-00
	 * @throws SQLException connection can't be established ...
	 * @return true if connection successfully established,
	 * false otherwise. If the connection was already established, <b>false is returned</b>.
	 **/
	protected boolean reConnect() throws SQLException, javax.naming.NamingException{
    	if(conDB==null || conDB.isClosed()){
    		conDB=m_DB.getConnection();
    	}else{
			try {
				conDB.close();
			} catch (SQLException e) {
				conDB=null;
			}
			conDB=m_DB.getConnection();
    	}
    	// conDB.prepareStatement("SET CLIENT_ENCODING TO '" + "SQL_ASCII" + "'").execute();
    	
    	isConnected=(conDB!=null);
    	return isConnected;
  	}//end of "connect"
	
  	/**
  	 * Close connection to database
  	 * @throws SQLException Something goes wrong ... :-)
  	 **/
  	public void disconnect() throws SQLException{
  	  if((conDB!=null) && (!conDB.isClosed())) conDB.close();
  	  conDB=null;
  	  isConnected=false;
  	}//end of "close"

  	/**
  	 * @version 1-00-00
  	 * @return connection to database SGV
  	 * @throws NamingException 
  	 * @throws SQLException 
  	 * @throws Exception
  	 */
  	protected Connection getConnection() throws SQLException, NamingException{
  		reConnect();
  		return conDB;
  	}
  	
  	
  	/**
  	 * <pre>
  	 * Construct and return ResultSet from query string.
  	 * E.g. ResultSet rTest = sqlQuery("Select * from TEST;");
  	 * </pre>
  	 * @returns a ResultSet that contains the results of the query, null if the query-string is empty.
  	 * @param strQuery: String representing a valid SQL-Select Query. 
  	 * @throws NamingException 
  	 * @throws SQLException 
  	 * Empty Query String
  	 **/
  	public ResultSet sqlQuery(String strQuery) throws SQLException, NamingException{
  		
  	  //check if query not empty:
  	  if((strQuery==null)||(strQuery.equals(""))) return null;
  	  return getConnection().createStatement().executeQuery( strQuery );
  	}//end of "sqlQuery"

  	public ResultSetSHJ sqlQuerySHJ(String sQuery) throws Exception{
  		Statement stmt=getConnection().createStatement();
  		ResultSet rTmp=stmt.executeQuery(sQuery);
  		ResultSetSHJ oTmp = new ResultSetSHJ(rTmp);
  		try{
  			rTmp.close();
  			stmt.close();
  		}catch(Exception eWhateverIgnore){}
  		return oTmp;
  	}
  	
  	/**
  	 * <pre>
  	 * For long queries: executes each statment (between ; and ;) in 
  	 * a single step, wraps them all in a transaction.
  	 * </pre>
  	 * @param strQuery
  	 * @return true for success
  	 * @throws NamingException 
  	 * @throws SQLException 
  	 * @throws Exception if autoCommit cannot be set to 'true'.
  	 */
  	public boolean sqlExeSingle(String strQuery) throws SQLException, NamingException{
  		
  		boolean blnReturn = true;
  		int intPos=0;

  		try {
  			// start transaction:
			setAutoCommit(false);
			
			String strBatch = strQuery;
			String strCmd   = "";
			intPos = strBatch.indexOf(';');
			
			// Loop through strBatch:
			while ( (intPos>=0) && blnReturn){
				strCmd = strBatch.substring(0, intPos);
				blnReturn = (blnReturn && sqlExe(strCmd));
				strBatch = strBatch.substring(intPos+1);
				intPos = strBatch.indexOf(';');
			}
			
			if(blnReturn) getConnection().commit();
			else		  getConnection().rollback();
		} catch (SQLException e) {
			blnReturn = false;
		} catch (Exception e) {
			blnReturn = false;
		}
  		
		setAutoCommit(true);
		try{
			  conDB.close();
		}catch(Exception eClo){}
		return blnReturn;
  	}
  	
	/**
	 * Executes SQL Command that does not return any values (insert, update).
	 * @param sQuery SQL-Query-String.
	 * @return true if database understood the command,
	 * false on error.
	 **/
	public boolean sqlExe(String sQuery){
		return (sqlExeCount(sQuery) != -1);
	}//end of "sqlExe"

	/**
	 * Executes SQL Command that does not return any values (insert, update).
	 * @param sQuery SQL-Query-String.
	 * @throws Exception No Connection to Database 
	 * @return Update Count, or 0 for DDL queries, or -1 for an error, or if sQuery is empty.
	 **/
	public int sqlExeCount(String sQuery){

		int intReturn=-1;

		//check if query not empty:
		if((sQuery==null)||(sQuery.equals(""))) return -1;

		try{
			intReturn = getConnection().createStatement().executeUpdate(sQuery);
		}catch(Exception e3){}
		
		// April 11 2006
		try{
		  conDB.close();
		}catch(Exception eClo){}
		return intReturn;
	}//end of "sqlExe"
	
	
	/**
	 * Unused in this context.
	 * @throws SQLException DB error
	 **/
	public void commit() throws SQLException{
		if (conDB!=null) conDB.commit();
  	}//end of "commit"

  	/**
  	 * SignUp (version XP 1-00-00) uses no transactions.
  	 * Rolls back a transaction - provided the db supports transactions.
  	 * @throws SQLException db doesn't support transactions,
  	 * or no transaction was started.
  	 **/
  	public void rollback() throws SQLException{
  	  if (conDB!=null) conDB.rollback();
  	}//end of "rollback"


	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------
	// 6.   P U B L I C  M E T H O D S: D B-U T I L S
	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------

  	/**
  	 * Db specific boolean representation
  	 * @return String representing boolean input;
  	 * with postgres, "true" is "'t'" and "false" is "'f'" (single quotes included).
  	 * @param bln_IN boolean to be transferred to db-specific string.
  	 **/
  	public String getDBBoolRepresentation(boolean bln_IN){
	  return ((bln_IN) ? "'t'" : "'f'");
  	}

  	/**
  	 * Ersetzt \n und \r und \t jeweils durch ein Leerzeichen
  	 * und den Doublequote durch Singlequote.
  	 * @todo bessere JSON Implementierung integrieren.
  	 * @param s
  	 * @return Input aus JSON
  	 */
  	public String getJSONString(String s){
  		return s.replace('\n', ' ').replace('\r', ' ').replace('\t', ' ').replace('\"', '\''); 
  	}
	/**
	 * <pre>
	 *  Method LookUp as in all db utils: 
	 *  look up a field in a table in the row 
	 *  specified through the criteria.
	 *  
	 *  Currently, for Postgres, the constructed SQL 
	 *  looks like this:
	 *  
	 *  SELECT "strField_IN" FROM "strTable_IN" WHERE strCrit_IN.
	 *  
	 *  Note that the field and table names are wrapped up in 
	 *  quotes, while fields in the 'strCrit_IN'-clause
	 *  have to be wrapped in quotes before calling this method.
	 *  
	 *  In case of error, #Error or #Error: [specification] are returned.
	 *  
	 *  If the criterion yields more than one result, 
	 *  the first one is returned.
	 *  
	 *  An example call would look like this:
	 *  
	 *  <Code>String sName = shjCore.lookUp("strStudentNachname", "tblBdStudent", "\"strMatrkelnummer\"='1488258'");</Code>
	 *  </pre>
	 *  @return String with the value of the field in the specified table-row of the database.
	 *  If the db-connection cannot be established, "#Error" is returned. If query-execution
	 *  triggers an Exception, "#Error: Couldn't execute query" is returned. If the criteria does not return
	 *  a result, "#NO_RESULT" is returned.
	 *  @param strField_IN: column-name whose content to return.
	 *  @param strTable_IN: name of table that holds the column.
	 *  @param strCrit_IN: where-clause to specify row(s) in table. Here, with postgres, field-names must be put in quotes.
	 **/
	public String lookUp(String strField_IN, String strTable_IN, String strCrit_IN){

		String 	strReturn	= "";
		String 	strSQL		= "";
		ResultSet rst;
		
		if(strCrit_IN.equals("")){
			strSQL = "SELECT \"" + strField_IN + "\" FROM \"" + strTable_IN +
				"\";";
		}else{
			strSQL = "SELECT \"" + strField_IN + "\" FROM \"" + strTable_IN +
			"\" WHERE (" + strCrit_IN + ");";
		}
		try{
			
			rst=sqlQuery(strSQL);
			if(rst.next()){
				strReturn = rst.getString(strField_IN);
			}else{
				strReturn = "#NO_RESULT";
			}
			
			rst.close();
		}catch(Exception eLookUp){
			strReturn = "#Error: Couldn't execute query: " + eLookUp.toString();
		}
		
		try {
			conDB.close();
		} catch (Exception e) {}
		return strReturn;
	}

	
	/**
	 * <pre>
	 *  Look up maximum value.
	 *  Currently, for Postgres, the constructed SQL looks like this:
	 *  
	 *  <Code>SELECT max("strField_IN") FROM "strTable_IN" [WHERE strCrit_IN];</Code>.
	 *  
	 *  So the field and table names are wrapped up in quotes, 
	 *  while fields in the 'strCrit_IN'-clause 
	 *  have to be wrapped in quotes before calling this method.
	 *  
	 *  In case of error, "" is returned
	 *  An example call would look like this:
	 *  
	 *  <Code>String sMaxMatrikel = shjCore.dbMax("strMatrikelnummer", "tblBdStudent", "\"strStudentNachname\"='M?ller'")</Code>
	 *  </pre>
	 *  @return Maximum value.
	 *  @param strField_IN: column-name whose content to return.
	 *  @param strTable_IN: name of table that holds the column.
	 *  @param strCrit_IN: Optional. Where-clause to specify row(s) in table. Here, with postgres, field-names must be put in quotes.
	 **/ 	
	public String dbMax(String strField_IN, String strTable_IN, String strCrit_IN){
		String 	strSQL		= "";
		ResultSet rst;
		String	strReturn	= "";
				
		strSQL = "SELECT max(\"" + strField_IN + "\") as maximum FROM \"" + strTable_IN + "\"";
		if(!strCrit_IN.equals("")) strSQL += " WHERE (" + strCrit_IN + ")";
		strSQL+=";";

		try{
			rst=sqlQuery(strSQL);
			if(rst.next()){
				strReturn = rst.getString("maximum");
			}else{
				strReturn = "";
			}
			
			rst.close();
		}catch(Exception eLookUp){
			return strReturn;
		}
		try {
			conDB.close();
		} catch (Exception e) {}
		return strReturn;
		
	}
	
	/**
	 * Falls das Datum null ist, oder sonst nicht 
	 * umwandelbar, wird #DATUM FEHLT# zurückgegeben.
	 * @param dtm
	 * @return dd.MM.yyyy-format
	 */
	public static String getGermanDate(Date dtm){
		try{
			return g_DATE_FORMAT_GERMAN.format(dtm);
		}catch(Exception eNoParsableDate){
			return "#DATUM FEHLT#";
		}
	}
	
	/**
	 * Falls das Datum null ist, oder sonst nicht 
	 * umwandelbar, wird #DATUM FEHLT# zurückgegeben.	 * 
	 * @param dtm
	 * @return
	 */
	public static String getISODate(Date dtm){
		try{
			return g_DATE_FORMAT_ISO.format(dtm);
		}catch(Exception eNoParsableDate){
			return "#DATUM FEHLT#";
		}
	}
	
	/**
	 * <pre>
	 * Die Methode versteht nur eine eng begrenzte 
	 * Zahl von Darstellungen des Datums.
	 * 
	 * Enthält das übergebende Datum ein "-", wird 
	 * der übergebene String nach ISO formatiert (oder 
	 * ein Fehler ausgelöst, wenn das nicht geht).
	 * 
	 * Enthält das übergebene Datum ein "/", wird 
	 * es überall durch ein "." ersetzt, d.h. 
	 * "1/4/09" wird zunächst umgewandelt in 
	 * "1.4.09".
	 * 
	 * Es wird dann versucht, das deutsche Format auszulesen.
	 * 
	 * Die Methode dient z.B. dazu, per
	 * <Code>String s = sISO_or_GERMAN_String;
	 * String iso = getISODate(getDate(sISO_or_GERMAN_String));
	 * </Code>
	 * ein übergenenes Datum tolerant zu interpretieren. 
	 * </pre>
	 * @param s Datum als String, z.B. "2009-1-1", oder "28.2.2009", oder "1/4/09"
	 * @return Date Objekt des übergebenen Datums (falls verständlich)
	 * @throws ParseException
	 */
	public static Date getDate(String s) throws ParseException{
		
		// Assuming its ISO:
		if(s.contains("-")){
			return new Date(g_DATE_FORMAT_ISO.parse(s).getTime());
		}
		
		if(s.contains("/")) s.replaceAll("/", ".");
		
		return new Date(g_DATE_FORMAT_GERMAN.parse(s).getTime());
	}
	
	/**
	 * <pre>
	 *  @TODO SQL Injection problem, program properly with statements.
	 *  Count records.
	 *  
	 *  Currently, for Postgres, the constructed SQL looks like this:
	 *  
	 *  <Code>SELECT count("strField_IN") FROM "strTable_IN" WHERE strCrit_IN.</Code>
	 *  
	 *  So the field and table names are wrapped up in quotes, 
	 *  while fields in the 'strCrit_IN'-clause
	 *  have to be wrapped in quotes before calling this method.
	 *  
	 *  In case of error, -3 is returned
	 *  
	 *  An example call would look like this:
	 *  
	 *  <Code>long lngCount = shjCore.dbCount("strMatrikelnummer", "tblBdStudent", "\"strStudentNachname\"='M?ller'");</Code>
	 *  
	 *  @return Count.
	 *  @param strField_IN: column-name whose content to return.
	 *  @param strTable_IN: name of table that holds the column.
	 *  @param strCrit_IN: where-clause to specify row(s) in table. Here, with postgres, field-names must be put in quotes.
	 **/
	public long dbCount(String strField_IN, String strTable_IN, String strCrit_IN){

	  long 		lngReturn = -3;
	  String 	strSQL		= "";
	  ResultSet rst;

	  if(!normalize(strCrit_IN).equals("")){
		  strSQL = "SELECT count(\"" + strField_IN + "\") as count FROM \"" + strTable_IN +
		  "\" WHERE (" + strCrit_IN + ");";
	  }else{
		  strSQL = "SELECT count(\"" + strField_IN + "\") as count FROM \"" + strTable_IN +
		  "\";";		  
	  }
	  try{
		  rst=sqlQuery(strSQL);
		  if(rst.next()){
			  lngReturn = rst.getLong("count");
		  }else{
			  lngReturn = 0;
		  }
		  
		  rst.close();
	  }catch(Exception eLookUp){
		  return lngReturn;
	  }
	  try {
		  conDB.close();
	  } catch (Exception e) {}
	  return lngReturn;
	}
	
	protected void finalize(){
		try {
			this.disconnect();
		} catch (SQLException e) {}
		try {
			super.finalize();
		} catch (Throwable e1) {}
	}
	
	/**
	 * <pre>
	 * Generic ID-Broker for Database.
	 * 
	 * Currently, for Postgres, the constructed SQL looks like this:
	 * 
	 * <Code>SELECT max("strField_IN")+1 FROM "strTable_IN" WHERE strCrit_IN.</Code>
	 * 
	 * So the field and table names are wrapped up in quotes, 
	 * while fields in the 'strCrit_IN'-clause
	 * have to be wrapped in quotes before calling this method.
	 * 
	 * In case of error, Exceptions are thrown (in contrast to 'lookUp(),' 
	 * which works similarly, but uses return-type String to hand back 
	 * error information.
	 * 
	 * An example call would look like this:
	 * <Code>long lngNext = shjCore.getNextID("lngDozentID", "tblSdDozent", "\"lngSdSeminarID\"=2")</Code>
	 *  @return long value next id given the sql-criterion.
	 *  If the db-connection cannot be established, an Exception is thrown. If query-execution
	 *  @param strField_IN: column-name whose content to return.
	 *  @param strTable_IN: name of table that holds the column.
	 *  @param strCrit_IN: where-clause to specify row(s) in table. Here, with postgres, 
	 * field-names must be put in quotes. If left empty, no criteria are regarded.
	 * @throws Exception 
	 **/
	public long getNextID(String strField_IN, String strTable_IN, String strCrit_IN) throws Exception{

	  long 		lngReturn	= 0;
	  String 	strSQL		= "";
	  ResultSet rst;
	  
	  if(normalize(strCrit_IN).equals(""))
		strSQL = "SELECT max(\"" + strField_IN + "\")+1 FROM \"" + strTable_IN + "\";";
	  else
	  	strSQL = "SELECT max(\"" + strField_IN + "\")+1 FROM \"" + strTable_IN +
			   "\" WHERE (" + strCrit_IN + ");";

		rst=sqlQuery(strSQL);

		if(rst.next()){
			lngReturn = rst.getLong(1);
		}else{
			throw new Exception ("#NO_RESULT");
		}

		
		try {
			rst.close();
			conDB.close();
		} catch (Exception e) {}
	  return lngReturn;
	}
	
	/**
	 * <pre>
	 * Executes a stored procedure that returns no results.
	 * 
	 * The syntax for calling this method is:
	 * <Code>.execProc("[StoredProcName]", "ParamType1", "ParamValue1", "ParamType2", "ParamValue2", ..., "ParamTypeN", "ParamValueN")</Code>, where 
	 * 
	 * - "name" is the name of the stored procedure,
	 * - "ParamType[i]" is either "int", "long", "String", or "boolean" and 
	 * - "ParamValue[i]" the value of this parameter as a String.
	 * 
	 * For example you can call the stored procedure
	 *    <Code>FUNCTION setStatus(lFeeUsageUniqueID bigint, iStatus integer, sInfo varchar, sUserID varchar)</Code>
	 *    
	 * in the following way:
	 * 
	 * <Code>.execProc("setStatus", "long", "1045", "int", "23", "String", "Neuer Status gesetzt", "String", "g96");</Code>
	 * 
	 * Fehlgeschlagene Typenkonvertierungen (<Code>"long", "23pp"</Code>) oder unbekannte Typen (<Code>"Time", "10:22"</Code>) führen zu Fehlern.
	 * </pre>
	 * @param sCommandInfo parameter list
	 * @throws SQLException
	 * @throws NamingException
	 * @throws ParseException 
	 */
	public void execProc(String...sCommandInfo) throws SQLException, NamingException, ParseException{
		int ij=1;
		CallableStatement cstm = getConnection().prepareCall("{call " + sCommandInfo[0] + "(" + getArgList((sCommandInfo.length-1)/2) + ")}");
		for(int ii=1;ii<sCommandInfo.length;ii++){
			if(sCommandInfo[ii].equals("int"))
				cstm.setInt(ij++, Integer.parseInt(sCommandInfo[++ii]));
			else if(sCommandInfo[ii].equals("long"))
				cstm.setLong(ij++, Long.parseLong(sCommandInfo[++ii]));
			else if(sCommandInfo[ii].equals("String"))
				cstm.setString(ij++, sCommandInfo[++ii]);
			else if(sCommandInfo[ii].equals("boolean"))
				cstm.setBoolean(ij++, Boolean.parseBoolean(sCommandInfo[++ii]));
			else if(sCommandInfo[ii].equals("Date")){
				cstm.setDate(ij++, new java.sql.Date(g_DATE_FORMAT_ISO.parse(sCommandInfo[++ii]).getTime()));
				System.err.println("Datum gesetzt auf: " + sCommandInfo[ii]);}
			else
				throw new SQLException("Der Typ '" + sCommandInfo[ii] + "' wird von der Methode 'execProc' nicht unterstützt.");
		}
		cstm.execute();
	}

	/**
	 * Example:
	 * <Code>getArgList(4)</Code> will return: "?,?,?,?"
	 * @param length
	 * @return String consisting of "?," exactly <Code>length</Code> times.
	 */
	private String getArgList(int length) {
		String sReturn = "";
		for(int ii=0;ii<length;ii++)
			sReturn += "?,";
		return sReturn.substring(0, sReturn.length()-1);
	}

	/**
	 * <pre>
	 * Returns the String that is handed 
	 * in, or an empty String, if <Code>null</Code> 
	 * is handed in.
	 * 
	 * Utility to avoid a <Code>NullPointerException</Code>
	 * </pre>
	 * @return Empty String if str_IN==null, or return the String itself otherwise.
	 * @param str_IN: String to be normalized.
	 **/
	public static String normalize(String str_IN){
	  return (str_IN==null) ? "" : str_IN;
	}

	/**
	 * <pre>
	 * Is this String null or empty? 
	 * 
	 * Utility for HttpServletRequest-parameters where 
	 * both checks are needed for form-validation).
	 * </pre>
	 * @return true if [what] is either null or an empty String ("").
	 **/
	public boolean isEmpty(String what){
		if(what==null) return true;
		if(what.equals("")) return true;
		return false;
	}
	
	/**<pre>
	 * Utility for readability of code.
	 * 
	 * Returns true, if the String equals @link #g_STRING_UNINITIALIZED; 
	 * false if it doesn't.
	 * 
	 * This implies also that false is returned if the String is null 
	 * or empty.
	 * </pre>
	 * @param what
	 * @return true, if what is equal to @link #g_STRING_UNINITIALIZED
	 */
	public boolean isUninitialized(String what){
		if(what==null) return false;
		return what.endsWith(g_STRING_UNINITIALIZED);
	}
	
	/**<pre>
	 * Utility for readability of code.
	 * @param what
	 * @return true, if what==@link #g_ID_UNINITIALIZED; false otherwise.
	 */
	public boolean isUninitialized(long what){
		return (what==g_ID_UNINITIALIZED);
	}
	

	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------
	// 9.   H T T P - U T I L S
	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------

	/**
	 * <pre>
	 * Utility to switch to secure SSL-connection.
	 * 
	 * Based on the ServletRequest, a https address 
	 * is constructed the following way: 
	 * 
	 * <Code>https://ServerName[:PORT]/requestURI</Code>
	 * 
	 * (Where <Code>[:PORT]</Code> is a local const.)
	 * 
	 * This method replaces the deprecated 'getHTTPSBase'
	 * 
	 * If blnClientAuth is set to 'true,' the server will 
	 * ask the client for an SSL certificate (digital id).
	 * </pre>
	 * @see #getHTTPSBase(HttpServletRequest, String)
	 * @param r: request to determine from where to form the SSL-address.
	 * @param blnClientAuth: boolean value indicating whether ssl should ask for client-identification or not.
	 * @return https-address to current folder of request.
	 **/
	public String getSSLBase(HttpServletRequest r, boolean blnClientAuth){
		String 		strGetSSLBase			 =			"";
		int 		intPosLastSlash			 =			0;
		String		strPort 				 = 	( blnClientAuth ) ? (":" + PORT_SSL_CLIENT_AUTH ) : (":" + PORT_SSL_NO_CLIENT_AUTH);
		strGetSSLBase						 = "https://" + r.getServerName() + strPort;
		strGetSSLBase						+=	r.getRequestURI();
		intPosLastSlash						 =	strGetSSLBase.lastIndexOf((int)'/');
		strGetSSLBase						 =  strGetSSLBase.substring(0,intPosLastSlash);

		return strGetSSLBase;
	}

	/**
	 * <pre>
	 * Utility to switch from secure SSL-connection.
	 * 
	 * Based on the ServletRequest and the Port handed over, 
	 * a http address is constructed the following way: 
	 * 
	 * <Code>http://ServerName[:PORT]/requestURI</Code>
	 * 
	 * </pre>
	 * @param r: request to determine from where to form the Http-address.
	 * @return http-address to current folder of request.
	 **/
	public String getHTTPBase(HttpServletRequest r){
		String 		strGetBase				 =			"";
		int 		intPosLastSlash			 =			0;
		strGetBase							 =	"http://" + r.getServerName() + ":" + PORT_HTTP_INSECURE;
		strGetBase							+=	r.getRequestURI();
		intPosLastSlash						 =	strGetBase.lastIndexOf((int)'/');
		strGetBase							 =	strGetBase.substring(0,intPosLastSlash);

		return strGetBase;
	}

	/**
	 * <pre>
	 * Little helper for checkboxes:
	 * 
	 * To find out whether the checkbox 'chkWhat' was checked
	 * in the request 'req', call <Code>requestContains(req, "chkWhat")</Code>
	 * 
	 * </pre>
	 * @param HttpServletRequest r, the request to be skimmed
	 * String strParam_IN, the string that r is skimmed for.
	 * @return 'true', if strParam_IN is part of r,<br>
	 * 'false' otherwise.
	 * @version > 1.7i
	 **/
	public boolean requestContains(HttpServletRequest r, String strParam_IN){

		boolean 	blnReturn	= false;
		Enumeration pn	= r.getParameterNames();

		//check if parameter strParam_IN is part of r
		for(;pn.hasMoreElements();){
			if( ( (String) pn.nextElement() ).equals(strParam_IN) ){
				blnReturn		= true;
				break;
			}
		}
		return blnReturn;
  	}
	/**
	 * <pre>
	 * This method is used when dynamically created links 
	 * with many params end up in jsp-forms.
	 * Provides html-String ("&lt;input type="hidden" name="[name]" value="[value]" /&gt;") 
	 * with all params handed
	 * over in the request.
	 * </pre>
	 * @return Html-String to put into a form, containing "&lt;input type="hidden" ... /&gt;" elements.
	 * @param r: the request with form-data.
	 **/
	public static String getFormData(HttpServletRequest r){

		String 		strReturn		 =				"";
		String 		strTmp			 =				"";
		Enumeration en				 =				r.getParameterNames();

		for(;en.hasMoreElements();){
			strTmp					 = ( String ) 	en.nextElement();
			strReturn				+= "<input type=\"hidden\" name=\"" +
										strTmp + "\" value=\"" +
										r.getParameter(strTmp).replaceAll("\"", "&quot;") +
										"\" />\n";
		}

		return strReturn;
	}

	/**
	 * <pre>
	 * Method returns a query String (just like 
	 * HttpServletRequest.getQueryString()),
	 * but it also includes POST-sent form-data. 
	 * 
	 * " characters are substituted with %22 characters.
	 * </pre>
	 * @return query String such as mode=1&session=34
	 * @param r: the request with form-data.
	 **/
	public String getShjQueryString(HttpServletRequest r){

		String 		strReturn		 =				"";
		String 		strTmp			 =				"";
		Enumeration en				 =				r.getParameterNames();

		for(;en.hasMoreElements();){
			strTmp					 = ( String ) 	en.nextElement();
			strReturn				+= strTmp + "=" + r.getParameter(strTmp).replaceAll("\"", "%22")+ "&";
		}

		return strReturn.substring(0, strReturn.length()-1);
	}


	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------
	// 10.   H T M L - Utile
	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------

	
	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------
	// 11.   DB
	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------


	/**
	 * (Session-management via Cookies: unused)
	 * @param event Session started
	 **/
	 public void valueBound(HttpSessionBindingEvent event){
	 }

	/**
	 * (Session-management via Cookies: unused, tries a 'close connection' nevertheless.)
	 * @param event Session stopped
	 **/
	public void valueUnbound(HttpSessionBindingEvent event){
      try{disconnect();}
	  catch (SQLException e){}
    }//end of "valueUnbound"

	/**
	 * @param blnAutoCommit Flag indicating whether db should be in auto-commit-
	 * mode or whether an explicit commit is needed.
	 * @throws NamingException 
	 * @throws SQLException 
	 * @throws SQLException DB-error
	 **/
	public void setAutoCommit(boolean blnAutoCommit) throws SQLException, NamingException{
   	 getConnection().setAutoCommit(blnAutoCommit);
 	}//end of "setAutoCommit"

  	/** Unused in this context
  	 * @param intLevel Transaction Level to be set.
  	 * @throws NamingException 
  	 * @throws SQLException 
  	 * @throws SQLException db error
  	 */
  	public void setTransactionIsolation(int intLevel) throws SQLException, NamingException{
  		getConnection().setTransactionIsolation(intLevel);
  	}//end of "setTransactionIsolation"

	/**
	 * Unused in this context
	 * @param strSQL Statement to prepare
	 * @throws SQLException db error
	 * @return Flag indicating whether preparation was successful.
	 * @throws NamingException 
	 * @throws SQLException 
	 */
	public PreparedStatement prepareStatement(String strSQL) throws SQLException, NamingException{
		return getConnection().prepareStatement(strSQL);
  	}//end of "prepareStatement"
	

	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------
	// 13.	C O N S T R U C T O R
	// ------------------------------ ------------------------------
	// ------------------------------ ------------------------------
	
	/**
	 * Empty constructor.
 	 **/
 	public shjCore(){
 			try {
				m_ctx = new InitialContext();
				m_DB  = (DataSource)m_ctx.lookup("java:comp/env/jdbc/areaID");
				g_TODAY 	= new java.util.Date();
				g_HEUTE		= g_DATE_FORMAT_GERMAN.format(new java.util.Date());
				g_TODAY_ISO = g_DATE_FORMAT_ISO.format(new java.util.Date());
			} catch (NamingException e) {
				m_strDebug += e.toString();
			}

  	}
}
