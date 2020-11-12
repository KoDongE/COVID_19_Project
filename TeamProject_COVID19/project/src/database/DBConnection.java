package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnection {
	protected static Connection con = null;
	protected static Statement st = null;
	protected static ResultSet rs;
	private static DBConnection db;
	
	private DBConnection() {}
		
	public static DBConnection getInstance() {
		if(db==null)//������ü�� null�̸� �����ؼ� ��ȯ
		{
			try
			{
				if(con == null) 
				{
					Class.forName("com.mysql.cj.jdbc.Driver");
					con = DriverManager.getConnection("jdbc:mysql://34.64.123.148:3306/COVID_19_Project?serverTimezone=UTC", "root", "kodong4176");
				}
				if(st == null) 
				{
					st = con.createStatement();
				}
				db = new DBConnection();
				System.out.println("�����ͺ��̽� ��ü ������");
			} catch(Exception e) 
			{
				System.out.println("Database Connection Error : " + e.getMessage());
			}
		}
		return db;
	}
}
