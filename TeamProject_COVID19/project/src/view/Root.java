package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import database.member;

public class Root extends JFrame {
	BufferedImage img = null;
	JTextField loginTextField;
	JTextField passwordTextField;
	JButton loginButton;
	JButton signupButton;
	JLabel loginFail;
	boolean login;
	
	buttonlistener movelistener = new buttonlistener();
	private member mb;
	
	public Root() {
		//mb 객체 생성
		mb = new member();
		
		
		setTitle("Test");
		setSize(1600, 900);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	    //백그라운드 이미지 받아오기
	    try {
            img = ImageIO.read(new File("img/RootBackground.png"));
            System.out.println("Image load Success");
        } catch (IOException e) {
            System.out.println("Image load Fail");
            System.exit(0);
        }
        
	    //레이아웃 설정
	    JLayeredPane layeredPane = new JLayeredPane();
	    layeredPane.setBounds(0,0,1600,900);
	    layeredPane.setLayout(null);
	    
	    
	    //패널 1
        RootPanel panel = new RootPanel();
        panel.setBounds(0, 0, 1600, 900); // 위치 설정정
		
        //로그인 필드
        loginTextField = new JTextField(15);
        loginTextField.setBounds(731, 399, 280, 30);
        
        layeredPane.add(loginTextField);
        
        //패스워드 필드
        passwordTextField = new JPasswordField(15);
        passwordTextField.setBounds(731, 529, 280, 30);
        
        layeredPane.add(passwordTextField);
        
        //로그인 버튼 추가
        loginButton = new JButton("login");
        loginButton.setBounds(755, 689, 104, 48);
        layeredPane.add(loginButton);
        loginButton.addActionListener(movelistener);
        
        //회원가입 버튼 추가
        signupButton = new JButton("Resigter");
        signupButton.setBounds(755, 750, 104, 48);
        layeredPane.add(signupButton);
        signupButton.addActionListener(movelistener);
        
        loginFail = new JLabel("회원정보가 일치하지 않습니다.");
        loginFail.setBounds(755, 600, 300, 100);
        loginFail.setForeground(Color.RED);
        layeredPane.add(loginFail);
        loginFail.setVisible(false);
        
        
        //마지막 추가
        layeredPane.add(panel);
        add(layeredPane);
        setVisible(true);
        setResizable(false); // 프로그램 크기 조절 불가 설정
    	setLocationRelativeTo(null); // 화면 중앙에 오도록 하는 설정 
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class RootPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}
	
	public boolean LoginAction(ActionEvent e) throws SQLException {
		String id = loginTextField.getText();
		String pw = passwordTextField.getText();
		System.out.println("Login 버튼 눌림");
		
		if(mb.login(id, pw)) {
			setVisible(false);
			return true;
		}
		else {
			System.out.println("Login Fail");
			loginFail.setVisible(true);
			return false;
		}
	}
	
	public void signupAction(ActionEvent e) {
		
	}
	
	class buttonlistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if((JButton) e.getSource() == loginButton)
				try {
					if(LoginAction(e)) {
						MainWindow main = new MainWindow();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			else if((JButton) e.getSource() == signupButton) {
				System.out.println("Signup Button Pressed");
				setVisible(false);
				Signup signup = new Signup();
			}
		}
			
	}
}
