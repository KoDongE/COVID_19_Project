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
		
		
		String query = "SELECT COUNT(Ȯ����) AS Count FROM patients WHERE Ȯ���� like '"+month+"%';";
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		DBConnection.rs.next();
		
		count = DBConnection.rs.getInt("count");
		
		query = "SELECT COUNT(Ȯ����) AS Count FROM patients WHERE Ȯ���� like '0"+month+"%';";
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		DBConnection.rs.next();
		
		count += DBConnection.rs.getInt("count");
		
		System.out.print(month + "�� " + count + "��    ");
		
		return count;
	}
	
	
}
