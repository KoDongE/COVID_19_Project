package database;

import java.sql.SQLException;

public class guPatients {
	private DBConnection db;
	
	public guPatients() {
		db = DBConnection.getInstance();
	}
	
	public int count_gu(String gu_name) throws SQLException {
		int count;
		
		String query = "SELECT COUNT(지역) AS Count FROM patients WHERE 지역 like '"+gu_name+"%';";
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		DBConnection.rs.next();
		
		count = DBConnection.rs.getInt("count");
		
		return count;
	}
}
