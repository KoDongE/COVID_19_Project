package database;

public class patient {
	private int num;
	private String date;
	private String patient;
	private String country;
	private String patient_info;
	private String area;
	private String travel;
	private String connect;
	private String notice;
	private String state;
	private String traffic;
	private String addDate;
	private String updateDate;
	private String expose;
	
	public patient(int _num, String _Date, String _Patient, String _Country, String _Patient_info, String _Area,
			String _Travel, String _Connect, String _Notice, String _State, String _Traffic, String _AddDate,
			String _UpdateDate, String _Expose) {
		this.num = _num;
		this.date = _Date;
		this.patient = _Patient;
		this.country = _Country;
		this.patient_info = _Patient_info;
		this.area = _Area;
		this.travel = _Travel;
		this.connect = _Connect;
		this.notice = _Notice;
		this.state = _State;
		this.traffic = _Traffic;
		this.addDate = _AddDate;
		this.updateDate = _UpdateDate;
		this.expose = _Expose;
		
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPatient() {
		return patient;
	}
	public void setPatient(String patient) {
		this.patient = patient;
	}
	public String getPatient_info() {
		return patient_info;
	}
	public void setPatient_info(String patient_info) {
		this.patient_info = patient_info;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getTravel() {
		return travel;
	}
	public void setTravel(String travel) {
		this.travel = travel;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getConnect() {
		return connect;
	}
	public void setConnect(String connect) {
		this.connect = connect;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getTraffic() {
		return traffic;
	}
	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getExpose() {
		return expose;
	}
	public void setExpose(String expose) {
		this.expose = expose;
	}
	
}
