package database;

import java.sql.SQLException;

public class member {
	private DBConnection db;
	
	public member() {
		db = DBConnection.getInstance();
	}
	
	public void register(String id, String password, String name) throws SQLException {
		String query = "INSERT INTO members(id, password, name) VALUES('"+id+"','"+password+"','"+name+"');";
		System.out.println(query);
		
		if(DBConnection.st.executeUpdate(query)>0) 
		{
			System.out.println("inserted.");
		}
	}
	
	public boolean login(String id, String password) throws SQLException {
		String query = "SELECT * FROM members "
				+ "WHERE id = '"+id+"' AND password ='"+password+"';";
		System.out.println(query);
		
		if(DBConnection.st.execute(query)) {
			DBConnection.rs = DBConnection.st.getResultSet();
		}
		
		while(DBConnection.rs.next()) {
			String _id = DBConnection.rs.getString("id");
			String _pw = DBConnection.rs.getString("password");
			
			if(_id.equals(id) && _pw.equals(password)) {
				System.out.println("Login Success");
				return true;
			}
		}
		System.out.println("Login Fail");
		return false;
	}
	
}
