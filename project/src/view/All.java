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
	final String[] gu_name = { "������", "������", "������", "���ϱ�", "�߱�", "����", "��õ��", "������", "���빮��", "�߶���", "���ϱ�", "���Ǳ�", "���α�", "��������", "������", "���α�", "������", "��걸", "���۱�", "���ʱ�", "���ı�", "�����", "������", "��õ��", "���빮��"};
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

		//���̾ƿ� ����
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0,0,1600,900);
		layeredPane.setLayout(null);
				
		//�г�
		AllPanel panel = new AllPanel();
		panel.setBounds(0, 0, 1600, 900); // ��ġ ������
		
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
    	
    	//goback ��ư
    	goback = new JButton("��������");
    	goback.setBounds(1300, 750, 200, 100);
    	layeredPane.add(goback);
    	goback.addActionListener(buttonlistener);
    	
    	setVisible(true);
		
    	layeredPane.add(panel);
        add(layeredPane);
		//setResizable(false); // ���α׷� ũ�� ���� �Ұ� ����
    	setLocationRelativeTo(null); // ȭ�� �߾ӿ� ������ �ϴ� ����    
    	System.out.println("����� ���� Ȯ���� �ҷ���");
	}
	
	static class Table {
		public static Vector<String> tablename(){
			Vector<String>table=new Vector<>();

			table.add("����");
			table.add("Ȯ����");
			table.add("ȯ�ڹ�ȣ");
			table.add("����");
			table.add("ȯ������");
			table.add("����");
			table.add("�����");
			table.add("���˷�");
			table.add("��ġ����");
			table.add("����");
			table.add("�̵����");
			table.add("�����");
			table.add("������");
			table.add("��������");
			
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
