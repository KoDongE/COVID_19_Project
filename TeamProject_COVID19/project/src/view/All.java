package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.patient;
import database.patientsData;
import database.guPatients;
import view.MainWindow.MainWindowPanel;
import view.MainWindow.buttonlistener;

public class All extends JFrame{ 
	BufferedImage img = null;
	JScrollPane scrollPane = new JScrollPane();
	buttonlistener buttonlistener = new buttonlistener();
	JButton goback, comboSelect;
	JLabel[] patientsLabel = new JLabel[26];
	JLayeredPane labelPane = new JLayeredPane(); // 호출 최소하 위해서. showPatients() 메소드
	
	final String[] gu_name = { "전체", "강서구", "강동구", "강남구", "성북구", "중구", "은평구", "금천구", "광진구", "서대문구", "중랑구", "강북구", "관악구", "구로구", "영등포구", "마포구", "종로구", "도봉구", "용산구", "동작구", "서초구", "송파구", "노원구", "성동구", "양천구", "동대문구"};

	private JComboBox<String> comboBox;
	private patientsData pd;
	private guPatients gp  = new guPatients();
	
	String comboSelected;
	
	public All() throws SQLException {
		
		pd = new patientsData();
		
		showTable();
		showComboBox();
		showPatients();
		
		setTitle("Test");
		setSize(1600, 900);
		
		 try {
	            img = ImageIO.read(new File("project/img/allBackground.png"));
	            System.out.println("\nImage load Success");
	        } catch (IOException e) {
	            System.out.println("Image load Fail");
	            System.exit(0);
	        }

		//레이아웃 설정
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0,0,1600,900);
		layeredPane.setLayout(null);

		//패널
		AllPanel panel = new AllPanel();
		panel.setBounds(0, 0, 1600, 900); // 위치 설정정
		
    	//goback 버튼
    	goback = new JButton("메인으로");
    	goback.setBounds(1300, 750, 200, 100);
    	layeredPane.add(goback);
    	goback.addActionListener(buttonlistener);
    	
    	setVisible(true);
    	
    	layeredPane.add(panel);
        add(layeredPane);
		//setResizable(false); // 프로그램 크기 조절 불가 설정
    	setLocationRelativeTo(null); // 화면 중앙에 오도록 하는 설정   
    	System.out.println("서울시 전제 확진자 불러옴");
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
	}
	
	public void showComboBox() {
		
		JLayeredPane comboBoxPane = new JLayeredPane();
		comboBoxPane.setBounds(0,0,1600,900);
		comboBoxPane.setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel<String>(gu_name));
		comboBox.setBounds(850, 160, 100, 30);
		comboBoxPane.add(comboBox);
		
		comboSelect = new JButton("확인");
    	comboSelect.setBounds(970, 160, 70, 30);
    	comboBoxPane.add(comboSelect);
    	comboSelect.addActionListener(buttonlistener);
    	
    	add(comboBoxPane);
	}
	
	public void showPatients() throws SQLException {
	
		labelPane.setBounds(0,0,1600,900);
		labelPane.setLayout(null);
		
		for(int gu_num=1; gu_num<26; gu_num++) {
			patientsLabel[gu_num] = new JLabel(Integer.toString(gp.count_gu(gu_name[gu_num])));
			patientsLabel[gu_num].setForeground(Color.RED);
			labelPane.add(patientsLabel[gu_num]);
		}
		
		patientsLabel[1].setBounds(150, 400 ,100,100); // 강서
		patientsLabel[2].setBounds(720, 430 ,100,100); // 강동
		patientsLabel[3].setBounds(555, 540 ,100,100); // 강남
		patientsLabel[4].setBounds(500, 330 ,100,100); // 성북
		patientsLabel[5].setBounds(455, 415 ,100,100); // 중구
		patientsLabel[6].setBounds(340, 300 ,100,100); // 은평
		patientsLabel[7].setBounds(300, 620 ,100,100); // 금천
		patientsLabel[8].setBounds(620, 440 ,100,100); // 광진
		patientsLabel[9].setBounds(360, 385 ,100,100); // 서대문
		patientsLabel[10].setBounds(630, 340 ,100,100); // 중랑
		patientsLabel[11].setBounds(485, 250 ,100,100); // 강북
		patientsLabel[12].setBounds(370, 605 ,100,100); // 관악
		patientsLabel[13].setBounds(190, 555 ,100,100); // 구로
		patientsLabel[14].setBounds(310, 500 ,100,100); // 영등포
		patientsLabel[15].setBounds(310, 425 ,100,100); // 마포
		patientsLabel[16].setBounds(440, 370 ,100,100); // 종로
		patientsLabel[17].setBounds(530, 205 ,100,100); // 도봉
		patientsLabel[18].setBounds(430, 475 ,100,100); // 용산
		patientsLabel[19].setBounds(370, 535 ,100,100); // 동작
		patientsLabel[20].setBounds(480, 570 ,100,100); // 서초
		patientsLabel[21].setBounds(665, 530 ,100,100); // 송파
		patientsLabel[22].setBounds(595, 235 ,100,100); // 노원
		patientsLabel[23].setBounds(535, 430 ,100,100); // 성동
		patientsLabel[24].setBounds(210, 500 ,100,100); // 양천
		patientsLabel[25].setBounds(565, 370 ,100,100); // 동대문
		
		add(labelPane);
		
	}
	
	public void showTable() throws SQLException {
		JLayeredPane tablePane = new JLayeredPane();
		tablePane.setBounds(0,0,1600,900);
		tablePane.setLayout(null);
		
		Vector<String> tablename = Table.tablename();
		Vector<patient> pati = patientsData.setPatientsData();
		DefaultTableModel tableModel = new DefaultTableModel(tablename,0);
    	for(int i=0; i<pati.size(); i++) {
    		Vector<Object>row = new Vector<>();
    		row.addElement(pati.get(i).getNum());
    		row.addElement(pati.get(i).getDate());
    		row.addElement(pati.get(i).getPatient());
    		row.addElement(pati.get(i).getCountry());
    		row.addElement(pati.get(i).getPatient_info());
    		row.addElement(pati.get(i).getArea());
    		row.addElement(pati.get(i).getTravel());
    		row.addElement(pati.get(i).getConnect());
    		row.addElement(pati.get(i).getNotice());
    		row.addElement(pati.get(i).getState());
    		row.addElement(pati.get(i).getTraffic());
    		row.addElement(pati.get(i).getAddDate());
    		row.addElement(pati.get(i).getUpdateDate());
    		row.addElement(pati.get(i).getExpose());
    		tableModel.addRow(row);
    		
    		add(tablePane);
    	}
    	
    	JTable table = new JTable(tableModel);
    	scrollPane = new JScrollPane(table);
    	scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	scrollPane.setBounds(850,200,650,500);
    	scrollPane.setVisible(true);
    	tablePane.add(scrollPane,BorderLayout.CENTER);
		
	}
	
	static class Table {
		public static Vector<String> tablename(){
			Vector<String>table = new Vector<>();

			table.add("연번");
			table.add("확진일");
			table.add("환자번호");
			table.add("국적");
			table.add("환자정보");
			table.add("지역");
			table.add("여행력");
			table.add("접촉력");
			table.add("조치사항");
			table.add("상태");
			table.add("이동경로");
			table.add("등록일");
			table.add("수정일");
			table.add("노출정도");
			
			return table;
		}
	}
	
	
	class AllPanel extends JPanel {
			public void paint(Graphics g) {
				super.paint(g);
				g.drawImage(img, 0, 0, null);
				g.drawString( "123명", 940, 600);
			}
			
		}
		
	class buttonlistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				if((JButton) e.getSource() == goback) {
					setVisible(false);
					try {
						MainWindow main = new MainWindow();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
				if((JButton) e.getSource() == comboSelect) {
					comboSelected = (String) comboBox.getSelectedItem();
			    	System.out.println("comboBox : " + comboSelected + " 선택 됨");
			    	
			    	if(comboSelected == "강서구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[1].setVisible(true);
			    		
			    		System.out.println("강서구 선턱 됨");
			    	}
			    	if(comboSelected == "강동구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[2].setVisible(true);
			    		
			    		System.out.println("강동구 선턱 됨");
			    	}
			    	if(comboSelected == "강남구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[3].setVisible(true);
			    		
			    		System.out.println("강남구 선턱 됨");
			    	}
			    	if(comboSelected == "성북구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[4].setVisible(true);
			    		
			    		System.out.println("성북구 선턱 됨");
			    	}if(comboSelected == "중구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[5].setVisible(true);
			    		
			    		System.out.println("중구 선턱 됨");
			    	}
			    	if(comboSelected == "은평구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[6].setVisible(true);
			    		
			    		System.out.println("은평구 선턱 됨");
			    	}
			    	if(comboSelected == "금천구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[7].setVisible(true);
			    		
			    		System.out.println("금천구 선턱 됨");
			    	}
			    	if(comboSelected == "광진구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[8].setVisible(true);
			    		
			    		System.out.println("광진구 선턱 됨");
			    	}
			    	if(comboSelected == "서대문구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[9].setVisible(true);
			    		
			    		System.out.println("서대문구 선턱 됨");
			    	}
			    	if(comboSelected == "중랑구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[10].setVisible(true);
			    		
			    		System.out.println("중랑구 선턱 됨");
			    	}
			    	if(comboSelected == "강북구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[11].setVisible(true);
			    		
			    		System.out.println("강북구 선턱 됨");
			    	}
			    	if(comboSelected == "관악구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[12].setVisible(true);
			    		
			    		System.out.println("관악구 선턱 됨");
			    	}
			    	if(comboSelected == "구로구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[13].setVisible(true);
			    		
			    		System.out.println("구로구 선턱 됨");
			    	}if(comboSelected == "영등포구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[14].setVisible(true);
			    		
			    		System.out.println("영등포구 선턱 됨");
			    	}
			    	if(comboSelected == "마포구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[15].setVisible(true);
			    		
			    		System.out.println("마포구 선턱 됨");
			    	}
			    	if(comboSelected == "종로구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[16].setVisible(true);
			    		
			    		System.out.println("종로구 선턱 됨");
			    	}
			    	if(comboSelected == "도봉구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[17].setVisible(true);
			    		
			    		System.out.println("도봉구 선턱 됨");
			    	}
			    	if(comboSelected == "용산구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[18].setVisible(true);
			    		
			    		System.out.println("용산구 선턱 됨");
			    	}
			    	if(comboSelected == "동작구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[19].setVisible(true);
			    		
			    		System.out.println("동작구 선턱 됨");
			    	}
			    	if(comboSelected == "서초구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[20].setVisible(true);
			    		
			    		System.out.println("서초구 선턱 됨");
			    	}
			    	if(comboSelected == "송파구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[21].setVisible(true);
			    		
			    		System.out.println("송파구 선턱 됨");
			    	}
			    	if(comboSelected == "노원구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[22].setVisible(true);
			    		
			    		System.out.println("노원구 선턱 됨");
			    	}
			    	if(comboSelected == "성동구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[23].setVisible(true);
			    		
			    		System.out.println("성동구 선턱 됨");
			    	}
			    	if(comboSelected == "양천구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[24].setVisible(true);
			    		
			    		System.out.println("양천구 선턱 됨");
			    	}
			    	if(comboSelected == "동대문구") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[25].setVisible(true);
			    		
			    		System.out.println("동대문구 선턱 됨");
			    	}
			    	if(comboSelected == "전체") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(true);
			    		
			    		System.out.println("전체 선턱 됨");
			    	}
				}
			}
		}
}
