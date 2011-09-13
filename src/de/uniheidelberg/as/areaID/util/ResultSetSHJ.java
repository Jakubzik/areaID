/**
 * 
 */
package de.uniheidelberg.as.areaID.util;

import java.sql.Date;
import de.uniheidelberg.as.areaID.data.shjCore;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Iterator;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.RowSetDynaClass;

/**
 * @author jakubzik
 * 
 * Hülle um RowSetDynaClass, die diese einem 
 * ResultSet-Objekt ähnlicher machen soll.
 * 
 * Insbes. kann man die Methoden .next(), 
 * .getString(), .getFloat() etc. wie 
 * beim ResultSet verwenden.
 *
 */
public class ResultSetSHJ extends RowSetDynaClass {

	private Iterator  m_iRows;
	private DynaBean  m_oRow;
	private static final long serialVersionUID = 1L;
	
	public boolean next(){
		if(m_iRows.hasNext()){
			m_oRow = (DynaBean) m_iRows.next();
			return true;
		}
		return false;
	}
	
	public String getString(String sColName){
		Object oTmp = m_oRow.get(sColName.toLowerCase());
		if(oTmp instanceof String) {return (String)oTmp;}
		else {return String.valueOf(oTmp);}
	}
	
	public float getFloat(String sColName){
		try{
			return ((Float)m_oRow.get(sColName.toLowerCase())).floatValue();
		}catch(Exception eNoFloat){
			return Float.parseFloat(getString(sColName));
		}
	}
	
	public int getInt(String sColName){
		try{
			return ((Integer)m_oRow.get(sColName.toLowerCase())).intValue();
		}catch(Exception eNoInt){
			return Integer.parseInt(getString(sColName));
		}
	}
	
	public boolean getBoolean(String sColName){
		try{
			return ((Boolean)m_oRow.get(sColName.toLowerCase())).booleanValue();
		}catch(Exception eNoBool){
			return Boolean.parseBoolean(getString(sColName));
		}
	}
	
	public Date getDate(String sColName) throws ParseException{
		return new Date(shjCore.g_ISO_DATE_FORMAT.parse(getString(sColName)).getTime());
	}
	
	/**
	 * @param resultSet
	 * @throws SQLException
	 */
	public ResultSetSHJ(ResultSet resultSet) throws SQLException {
		super(resultSet);
		m_iRows = this.getRows().iterator();
	}

	public long getLong(String sColName) {
		return Long.parseLong(getString(sColName));
	}
}
