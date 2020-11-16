package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import database.member;
import view.Root.buttonlistener;

public class Signup extends JFrame {
	JLabel nameLabel, idLabel, pwLabelCheck, pwLabel, liveLabel, idDup, pwError, registerSuccess;
	JTextField nameText, idText, pwLabelCheckText, pwText;
	JComboBox<String> liveComboBox;
	JButton register, goBack;
	String name, id, pw, pwCheck, live; 
	ButtonListener buttonListener = new ButtonListener();

	BufferedImage img = null;
	member mb;
	final String[] gu_nameLabel = { "��ü", "������", "������", "������", "���ϱ�", "�߱�", "����", "��õ��", "������", "���빮��", "�߶���", "���ϱ�", "���Ǳ�", "���α�", "��������", "������", "���α�", "������", "��걸", "���۱�", "���ʱ�", "���ı�", "�����", "������", "��õ��", "���빮��"};
	
	public Signup () {
		mb = new member();
		
		setTitle("Test");
		setSize(1600, 900);
		
		 try {
		        img = ImageIO.read(new File("img/RootBackground.png"));
		        System.out.println("Image load Success");
		    } catch (IOException e) {
		        System.out.println("Image load Fail");
		        System.exit(0);
		    }
		 
		JPanel signupPanel = new JPanel();
		signupPanel.setBounds(0, 0, 1600, 900);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0,0,1600,900);
		layeredPane.setLayout(null);
		
		nameLabel = new JLabel("�̸�");
		nameLabel.setBounds(650, 200, 100, 100);
		layeredPane.add(nameLabel);
		
		idLabel = new JLabel("���̵�");
		idLabel.setBounds(640, 250, 100, 100);
		layeredPane.add(idLabel);
		
		liveLabel = new JLabel("������");
		liveLabel.setBounds(640, 300, 100, 100);
		layeredPane.add(liveLabel);
		
		pwLabel = new JLabel("�н�����");
		pwLabel.setBounds(630, 350, 100, 100);
		layeredPane.add(pwLabel);
		
		idDup = new JLabel("�ߺ��� ���̵��Դϴ�.");
		idDup.setBounds(700, 273, 200, 100);
		layeredPane.add(idDup);
		idDup.setVisible(false);
		
		pwError = new JLabel("�н����尡 ��ġ���� �ʽ��ϴ�.");
		pwError.setBounds(700,425,300,100);
		layeredPane.add(pwError);
		pwError.setVisible(false);
		
		registerSuccess = new JLabel("ȸ�������� �Ϸ�Ǿ����ϴ�.\n �α��� ȭ������ ���ư��ֽʽÿ�.");
		registerSuccess.setBounds(595,440,400,200);
		layeredPane.add(registerSuccess);
		registerSuccess.setVisible(false);
		
		pwLabelCheck = new JLabel("�н����� Ȯ��");
		pwLabelCheck.setBounds(605, 400, 100, 100);
		layeredPane.add(pwLabelCheck);
		
		nameText = new JTextField();
		nameText.setBounds(700, 240, 50, 20);
		layeredPane.add(nameText);
				
		idText = new JTextField();
		idText.setBounds(700, 290, 200, 20);
		layeredPane.add(idText);
		
		pwText = new JTextField();
		pwText.setBounds(700, 390, 200, 20);
		layeredPane.add(pwText);
		
		pwLabelCheckText = new JTextField();
		pwLabelCheckText.setBounds(700, 440, 200, 20);
		layeredPane.add(pwLabelCheckText);
		
		liveComboBox = new JComboBox();
		liveComboBox.setModel(new DefaultComboBoxModel<String>(gu_nameLabel));
		liveComboBox.setBounds(700, 335, 100, 30);
		
		layeredPane.add(liveComboBox);
		
		register = new JButton("ȸ������");
		register.setBounds(700, 600, 100, 100);
		layeredPane.add(register);
		register.addActionListener(buttonListener);
		
		//goBack ��ư
    	goBack = new JButton("�α��� ȭ������");
    	goBack.setBounds(1300, 750, 200, 100);
    	layeredPane.add(goBack);
    	goBack.addActionListener(buttonListener);
    	
		layeredPane.add(signupPanel);

		add(layeredPane);
		
		setVisible(true);
		
		setResizable(false); // ���α׷� ũ�� ���� �Ұ� ����
    	setLocationRelativeTo(null); // ȭ�� �߾ӿ� ������ �ϴ� ����  
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   	}
	
	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			if((JButton) e.getSource() == register) {
				idDup.setVisible(false);
				pwError.setVisible(false);
				id = idText.getText();
				pw = pwText.getText();
				name = nameText.getText();
				live = (String) liveComboBox.getSelectedItem();
				pwCheck = pwLabelCheckText.getText();
				try {
					if(mb.isId_dup(id)) {
						idDup.setVisible(true);
					}
					else if(!pw.equals(pwCheck)) {
						pwError.setVisible(true);
					}
					else {
						registerSuccess.setVisible(true);
						mb.register(id, pw, name, live);
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if((JButton) e.getSource() == goBack) {
				setVisible(false);
				Root root = new Root();
			}
		}
			
	}
}