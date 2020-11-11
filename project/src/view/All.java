package view;

import java.awt.BorderLayout;
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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.patient;
import database.patientsData;
import view.MainWindow.MainWindowPanel;
import view.MainWindow.buttonlistener;

public class All extends JFrame{ 
	BufferedImage img = null;
	JScrollPane scrollPane = new JScrollPane();
	buttonlistener buttonlistener = new buttonlistener();
	JButton goback;
	final String[] gu_name = { "강서구", "강동구", "강남구", "성북구", "중구", "은평구", "금천구", "광진구", "서대문구", "중랑구", "강북구", "관악구", "구로구", "영등포구", "마포구", "종로구", "도봉구", "용산구", "동작구", "서초구", "송파구", "노원구", "성동구", "양천구", "동대문구"};
	JButton gu[];
	private patientsData pd;
	
	public All() throws SQLException {
		
		pd = new patientsData();
		
		
		setTitle("Test");
		setSize(1600, 900);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		 try {
	            img = ImageIO.read(new File("project/img/allBackground.png"));
	            System.out.println("Image load Success");
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
		
		gu = new JButton[25];
		
		for(int i=0; i<25; i++) {
			gu[i] = new JButton(gu_name[i]);
			gu[i].setBounds(i*50, i*50, 50, 50);
			gu[i].addActionListener(buttonlistener);
			gu[i].setVisible(true);
			layeredPane.add(gu[i]);
		}
		
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
    	}
    	
    	JTable table = new JTable(tableModel);
    	scrollPane = new JScrollPane(table);
    	scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	scrollPane.setBounds(800,200,700,500);
    	scrollPane.setVisible(true);
    	layeredPane.add(scrollPane,BorderLayout.CENTER);
    	
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
	}
	
	static class Table {
		public static Vector<String> tablename(){
			Vector<String>table=new Vector<>();

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
				
				if((JButton)e.getSource() == gu[0]) {
					setVisible(false);
				}
			}
		}
}
