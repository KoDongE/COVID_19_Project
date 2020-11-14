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
	final String[] gu_nameLabel = { "전체", "강서구", "강동구", "강남구", "성북구", "중구", "은평구", "금천구", "광진구", "서대문구", "중랑구", "강북구", "관악구", "구로구", "영등포구", "마포구", "종로구", "도봉구", "용산구", "동작구", "서초구", "송파구", "노원구", "성동구", "양천구", "동대문구"};
	
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
		
		
		nameLabel = new JLabel("이름");
		nameLabel.setBounds(400, 100, 100, 100);
		layeredPane.add(nameLabel);
		
		idLabel = new JLabel("아이디");
		idLabel.setBounds(500, 100, 100, 100);
		layeredPane.add(idLabel);
		
		liveLabel = new JLabel("거주지");
		liveLabel.setBounds(800, 100, 100, 100);
		layeredPane.add(liveLabel);
		
		pwLabel = new JLabel("패스워드");
		pwLabel.setBounds(500, 300, 100, 100);
		layeredPane.add(pwLabel);
		
		pwLabelCheck = new JLabel("패스워드 확인");
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
		
		register = new JButton("회원가입");
		register.setBounds(650, 600, 100, 100);
		layeredPane.add(register);
		register.addActionListener(buttonlistener);
		
		//goBack 버튼
    	goBack = new JButton("로그인 화면으로");
    	goBack.setBounds(1300, 750, 200, 100);
    	layeredPane.add(goBack);
    	goBack.addActionListener(buttonlistener);
    	
		layeredPane.add(signupPanel);

		add(layeredPane);
		
		setVisible(true);
		
		setResizable(false); // 프로그램 크기 조절 불가 설정
    	setLocationRelativeTo(null); // 화면 중앙에 오도록 하는 설정  
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	   	}

	class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if((JButton) e.getSource() == register)
			{
				System.out.println("들어옴");
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
