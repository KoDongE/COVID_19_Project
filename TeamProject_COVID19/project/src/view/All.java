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
	JLayeredPane labelPane = new JLayeredPane(); // ȣ�� �ּ��� ���ؼ�. showPatients() �޼ҵ�
	
	final String[] gu_name = { "��ü", "������", "������", "������", "���ϱ�", "�߱�", "����", "��õ��", "������", "���빮��", "�߶���", "���ϱ�", "���Ǳ�", "���α�", "��������", "������", "���α�", "������", "��걸", "���۱�", "���ʱ�", "���ı�", "�����", "������", "��õ��", "���빮��"};

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

		//���̾ƿ� ����
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0,0,1600,900);
		layeredPane.setLayout(null);

		//�г�
		AllPanel panel = new AllPanel();
		panel.setBounds(0, 0, 1600, 900); // ��ġ ������
		
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
		
		comboSelect = new JButton("Ȯ��");
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
		
		patientsLabel[1].setBounds(150, 400 ,100,100); // ����
		patientsLabel[2].setBounds(720, 430 ,100,100); // ����
		patientsLabel[3].setBounds(555, 540 ,100,100); // ����
		patientsLabel[4].setBounds(500, 330 ,100,100); // ����
		patientsLabel[5].setBounds(455, 415 ,100,100); // �߱�
		patientsLabel[6].setBounds(340, 300 ,100,100); // ����
		patientsLabel[7].setBounds(300, 620 ,100,100); // ��õ
		patientsLabel[8].setBounds(620, 440 ,100,100); // ����
		patientsLabel[9].setBounds(360, 385 ,100,100); // ���빮
		patientsLabel[10].setBounds(630, 340 ,100,100); // �߶�
		patientsLabel[11].setBounds(485, 250 ,100,100); // ����
		patientsLabel[12].setBounds(370, 605 ,100,100); // ����
		patientsLabel[13].setBounds(190, 555 ,100,100); // ����
		patientsLabel[14].setBounds(310, 500 ,100,100); // ������
		patientsLabel[15].setBounds(310, 425 ,100,100); // ����
		patientsLabel[16].setBounds(440, 370 ,100,100); // ����
		patientsLabel[17].setBounds(530, 205 ,100,100); // ����
		patientsLabel[18].setBounds(430, 475 ,100,100); // ���
		patientsLabel[19].setBounds(370, 535 ,100,100); // ����
		patientsLabel[20].setBounds(480, 570 ,100,100); // ����
		patientsLabel[21].setBounds(665, 530 ,100,100); // ����
		patientsLabel[22].setBounds(595, 235 ,100,100); // ���
		patientsLabel[23].setBounds(535, 430 ,100,100); // ����
		patientsLabel[24].setBounds(210, 500 ,100,100); // ��õ
		patientsLabel[25].setBounds(565, 370 ,100,100); // ���빮
		
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
				g.drawString( "123��", 940, 600);
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
			    	System.out.println("comboBox : " + comboSelected + " ���� ��");
			    	
			    	if(comboSelected == "������") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[1].setVisible(true);
			    		
			    		System.out.println("������ ���� ��");
			    	}
			    	if(comboSelected == "������") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[2].setVisible(true);
			    		
			    		System.out.println("������ ���� ��");
			    	}
			    	if(comboSelected == "������") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[3].setVisible(true);
			    		
			    		System.out.println("������ ���� ��");
			    	}
			    	if(comboSelected == "���ϱ�") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[4].setVisible(true);
			    		
			    		System.out.println("���ϱ� ���� ��");
			    	}if(comboSelected == "�߱�") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[5].setVisible(true);
			    		
			    		System.out.println("�߱� ���� ��");
			    	}
			    	if(comboSelected == "����") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[6].setVisible(true);
			    		
			    		System.out.println("���� ���� ��");
			    	}
			    	if(comboSelected == "��õ��") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[7].setVisible(true);
			    		
			    		System.out.println("��õ�� ���� ��");
			    	}
			    	if(comboSelected == "������") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[8].setVisible(true);
			    		
			    		System.out.println("������ ���� ��");
			    	}
			    	if(comboSelected == "���빮��") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[9].setVisible(true);
			    		
			    		System.out.println("���빮�� ���� ��");
			    	}
			    	if(comboSelected == "�߶���") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[10].setVisible(true);
			    		
			    		System.out.println("�߶��� ���� ��");
			    	}
			    	if(comboSelected == "���ϱ�") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[11].setVisible(true);
			    		
			    		System.out.println("���ϱ� ���� ��");
			    	}
			    	if(comboSelected == "���Ǳ�") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[12].setVisible(true);
			    		
			    		System.out.println("���Ǳ� ���� ��");
			    	}
			    	if(comboSelected == "���α�") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[13].setVisible(true);
			    		
			    		System.out.println("���α� ���� ��");
			    	}if(comboSelected == "��������") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[14].setVisible(true);
			    		
			    		System.out.println("�������� ���� ��");
			    	}
			    	if(comboSelected == "������") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[15].setVisible(true);
			    		
			    		System.out.println("������ ���� ��");
			    	}
			    	if(comboSelected == "���α�") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[16].setVisible(true);
			    		
			    		System.out.println("���α� ���� ��");
			    	}
			    	if(comboSelected == "������") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[17].setVisible(true);
			    		
			    		System.out.println("������ ���� ��");
			    	}
			    	if(comboSelected == "��걸") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[18].setVisible(true);
			    		
			    		System.out.println("��걸 ���� ��");
			    	}
			    	if(comboSelected == "���۱�") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[19].setVisible(true);
			    		
			    		System.out.println("���۱� ���� ��");
			    	}
			    	if(comboSelected == "���ʱ�") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[20].setVisible(true);
			    		
			    		System.out.println("���ʱ� ���� ��");
			    	}
			    	if(comboSelected == "���ı�") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[21].setVisible(true);
			    		
			    		System.out.println("���ı� ���� ��");
			    	}
			    	if(comboSelected == "�����") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[22].setVisible(true);
			    		
			    		System.out.println("����� ���� ��");
			    	}
			    	if(comboSelected == "������") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[23].setVisible(true);
			    		
			    		System.out.println("������ ���� ��");
			    	}
			    	if(comboSelected == "��õ��") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[24].setVisible(true);
			    		
			    		System.out.println("��õ�� ���� ��");
			    	}
			    	if(comboSelected == "���빮��") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(false);
			    		
			    		patientsLabel[25].setVisible(true);
			    		
			    		System.out.println("���빮�� ���� ��");
			    	}
			    	if(comboSelected == "��ü") {
			    		for(int i=1; i<26; i++)
			    			patientsLabel[i].setVisible(true);
			    		
			    		System.out.println("��ü ���� ��");
			    	}
				}
			}
		}
}
