package database;

import java.sql.SQLException;

public class monthPatients {
	private DBConnection db;
	private patientsData pd;
	public int jen, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec;
	
	public monthPatients() throws SQLException {
		db = DBConnection.getInstance();
	}
	
	public int countMonth(String month) throws SQLException {
		int count;
		
		
		String query = "SELECT COUNT(확진일) AS Count FROM patients WHERE 확진일 like '"+month+"%';";
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		DBConnection.rs.next();
		
		count = DBConnection.rs.getInt("count");
		
		query = "SELECT COUNT(확진일) AS Count FROM patients WHERE 확진일 like '0"+month+"%';";
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		DBConnection.rs.next();
		
		count += DBConnection.rs.getInt("count");
		
		System.out.print(month + "월 " + count + "명    ");
		
		return count;
	}
	
	
}
