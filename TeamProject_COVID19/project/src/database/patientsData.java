package database;

import java.sql.SQLException;
import java.util.Vector;

public class patientsData {
	private patient[] pati;
	private DBConnection db;
	
	//Database ���� Ȯ���� �����͸� �޾� ��ü�迭�� �޾ƿ´�.
	public patientsData() throws SQLException {
		db = DBConnection.getInstance();
		setPatientsData();
	}
	
	public static int numberOfPatients() throws SQLException {
		int count;
		String query = "SELECT COUNT(����) AS count FROM patients; ";
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		DBConnection.rs.next();
		
		count = DBConnection.rs.getInt("count");
		System.out.println("Ȯ���� �� : " + count);
		return count;
	}
	
	public static Vector<patient>setPatientsData() throws SQLException {
		
		Vector<patient> pati = new Vector<>();
		String query = "SELECT * FROM patients;";
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		
		
		while(DBConnection.rs.next())
		{ 
			int _num = (Integer.parseInt(DBConnection.rs.getString("����")));
			String _Date = (DBConnection.rs.getString("Ȯ����"));
			String _Patient = (DBConnection.rs.getString("ȯ�ڹ�ȣ"));
			String _Country = (DBConnection.rs.getString("����"));
			String _Patient_info = (DBConnection.rs.getString("ȯ������"));
			String _Area = (DBConnection.rs.getString("����"));
			String _Travel = (DBConnection.rs.getString("�����"));
			String _Connect = (DBConnection.rs.getString("���˷�"));
			String _Notice = (DBConnection.rs.getString("��ġ����"));
			String _State = (DBConnection.rs.getString("����"));
			String _Traffic = (DBConnection.rs.getString("�̵����"));
			String _AddDate = (DBConnection.rs.getString("�����"));
			String _UpdateDate = (DBConnection.rs.getString("������"));
			String _Expose = (DBConnection.rs.getString("���⿩��"));
			
			pati.add(new patient(_num, _Date, _Patient, _Country, _Patient_info, _Area, _Travel, _Connect, _Notice, _State, _Traffic, _AddDate, _UpdateDate, _Expose));
		}
		return pati;
	}
	
	public int NumberOfDatePatients(String month, String day) throws SQLException {
		int count;
		String query = "SELECT COUNT(����) As countCal FROM patients WHERE Ȯ���� = "+month+"."+day+"; ";
		System.out.println(query);
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		DBConnection.rs.next();
		
		count = DBConnection.rs.getInt("countCal");
		System.out.println("Ȯ���� �� : " + count);
		return count;
	}
}
