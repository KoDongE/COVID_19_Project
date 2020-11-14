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
	JLabel nameLabel, idLabel, pwLabelCheck, pwLabel, liveLabel;
	JTextField nameText, idText, pwLabelCheckText, pwText;
	JComboBox<String> liveComboBox;
	JButton register, goBack;
	String name, id, pw, pwCheck, live; 
	ButtonListener buttonlistener = new ButtonListener();
	
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
		nameLabel.setBounds(400, 100, 100, 100);
		layeredPane.add(nameLabel);
		
		idLabel = new JLabel("���̵�");
		idLabel.setBounds(500, 100, 100, 100);
		layeredPane.add(idLabel);
		
		liveLabel = new JLabel("������");
		liveLabel.setBounds(800, 100, 100, 100);
		layeredPane.add(liveLabel);
		
		pwLabel = new JLabel("�н�����");
		pwLabel.setBounds(500, 300, 100, 100);
		layeredPane.add(pwLabel);
		
		pwLabelCheck = new JLabel("�н����� Ȯ��");
		pwLabelCheck.setBounds(800, 300, 100, 100);
		layeredPane.add(pwLabelCheck);
		
		nameText = new JTextField();
		nameText.setBounds(400, 200, 50, 20);
		layeredPane.add(nameText);
		name = nameText.getSelectedText();
		
		idText = new JTextField();
		idText.setBounds(500, 200, 200, 20);
		layeredPane.add(idText);
		id = idText.getText();
		
		pwText = new JTextField();
		pwText.setBounds(500, 400, 200, 20);
		layeredPane.add(pwText);
		pw = pwText.getText();
		
		pwLabelCheckText = new JTextField();
		pwLabelCheckText.setBounds(800, 400, 200, 20);
		layeredPane.add(pwLabelCheckText);
		pwCheck = pwLabelCheckText.getText();
		
		liveComboBox = new JComboBox();
		liveComboBox.setModel(new DefaultComboBoxModel<String>(gu_nameLabel));
		liveComboBox.setBounds(800, 200, 100, 30);
		live = (String) liveComboBox.getSelectedItem();
		layeredPane.add(liveComboBox);
		
		register = new JButton("ȸ������");
		register.setBounds(650, 600, 100, 100);
		layeredPane.add(register);
		register.addActionListener(buttonlistener);
		
		//goBack ��ư
    	goBack = new JButton("�α��� ȭ������");
    	goBack.setBounds(1300, 750, 200, 100);
    	layeredPane.add(goBack);
    	goBack.addActionListener(buttonlistener);
    	
		layeredPane.add(signupPanel);

		add(layeredPane);
		
		setVisible(true);
		
		setResizable(false); // ���α׷� ũ�� ���� �Ұ� ����
    	setLocationRelativeTo(null); // ȭ�� �߾ӿ� ������ �ϴ� ����  
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   	}

	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if((JButton) e.getSource() == register)
			{
				System.out.println("����");
				try {
					mb.register(id, pw, name, live);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
			
	}
}
