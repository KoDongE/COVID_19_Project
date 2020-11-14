package database;

import java.sql.SQLException;

public class member {
	private DBConnection db;
	
	public member() {
		db = DBConnection.getInstance();
	}
	
	public void register(String id, String password, String name, String live) throws SQLException {
		String query = "INSERT INTO members(id, password, name, live) VALUES('"+id+"','"+password+"','"+name+"', '"+live+"');";
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
	
	public boolean isId_dup(String id) throws SQLException {
		String query = "SELECT * FROM members "
				+ "WHERE id = '"+id+"';";
		System.out.println(query);
		if(DBConnection.st.execute(query)) {
			DBConnection.rs = DBConnection.st.getResultSet();
		}
		
		while(DBConnection.rs.next()) {
			String _id = DBConnection.rs.getString("id");
			//id == query true일 경우 겹치는 상황
			if(_id.equals(id)) {
				System.out.println("ID is dup");
				return true;
			}
		}
		return false;
	}
	
}
