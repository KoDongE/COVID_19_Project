package database;

import java.sql.SQLException;
import java.util.Vector;

public class patientsData {
	private patient[] pati;
	private DBConnection db;
	
	//Database 에서 확진자 데이터를 받아 객체배열에 받아온다.
	public patientsData() throws SQLException {
		db = DBConnection.getInstance();
		setPatientsData();
	}
	
	public static int numberOfPatients() throws SQLException {
		int count;
		String query = "SELECT COUNT(연번) AS count FROM patients; ";
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		DBConnection.rs.next();
		
		count = DBConnection.rs.getInt("count");
		System.out.println("확진사 수 : " + count);
		return count;
	}
	
	public static Vector<patient>setPatientsData() throws SQLException {
		
		Vector<patient> pati = new Vector<>();
		String query = "SELECT * FROM patients;";
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		
		
		while(DBConnection.rs.next())
		{ 
			int _num = (Integer.parseInt(DBConnection.rs.getString("연번")));
			String _Date = (DBConnection.rs.getString("확진일"));
			String _Patient = (DBConnection.rs.getString("환자번호"));
			String _Country = (DBConnection.rs.getString("국적"));
			String _Patient_info = (DBConnection.rs.getString("환자정보"));
			String _Area = (DBConnection.rs.getString("지역"));
			String _Travel = (DBConnection.rs.getString("여행력"));
			String _Connect = (DBConnection.rs.getString("접촉력"));
			String _Notice = (DBConnection.rs.getString("조치사항"));
			String _State = (DBConnection.rs.getString("상태"));
			String _Traffic = (DBConnection.rs.getString("이동경로"));
			String _AddDate = (DBConnection.rs.getString("등록일"));
			String _UpdateDate = (DBConnection.rs.getString("수정일"));
			String _Expose = (DBConnection.rs.getString("노출여부"));
			
			pati.add(new patient(_num, _Date, _Patient, _Country, _Patient_info, _Area, _Travel, _Connect, _Notice, _State, _Traffic, _AddDate, _UpdateDate, _Expose));
		}
		return pati;
	}
	
	public int NumberOfDatePatients(String month, String day) throws SQLException {
		int count;
		String query = "SELECT COUNT(연번) As countCal FROM patients WHERE 확진일 = "+month+"."+day+"; ";
		System.out.println(query);
		DBConnection.st.execute(query);
		DBConnection.rs = DBConnection.st.getResultSet();
		DBConnection.rs.next();
		
		count = DBConnection.rs.getInt("countCal");
		System.out.println("확진사 수 : " + count);
		return count;
	}
}
