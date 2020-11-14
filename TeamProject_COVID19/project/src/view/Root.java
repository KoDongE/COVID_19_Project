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
		//mb ��ü ����
		mb = new member();
		
		
		setTitle("Test");
		setSize(1600, 900);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
	    //��׶��� �̹��� �޾ƿ���
	    try {
            img = ImageIO.read(new File("img/RootBackground.png"));
            System.out.println("Image load Success");
        } catch (IOException e) {
            System.out.println("Image load Fail");
            System.exit(0);
        }
        
	    //���̾ƿ� ����
	    JLayeredPane layeredPane = new JLayeredPane();
	    layeredPane.setBounds(0,0,1600,900);
	    layeredPane.setLayout(null);
	    
	    
	    //�г� 1
        RootPanel panel = new RootPanel();
        panel.setBounds(0, 0, 1600, 900); // ��ġ ������
		
        //�α��� �ʵ�
        loginTextField = new JTextField(15);
        loginTextField.setBounds(731, 399, 280, 30);
        
        layeredPane.add(loginTextField);
        
        //�н����� �ʵ�
        passwordTextField = new JPasswordField(15);
        passwordTextField.setBounds(731, 529, 280, 30);
        
        layeredPane.add(passwordTextField);
        
        //�α��� ��ư �߰�
        loginButton = new JButton("login");
        loginButton.setBounds(755, 689, 104, 48);
        layeredPane.add(loginButton);
        loginButton.addActionListener(movelistener);
        
        //ȸ������ ��ư �߰�
        signupButton = new JButton("Resigter");
        signupButton.setBounds(755, 750, 104, 48);
        layeredPane.add(signupButton);
        signupButton.addActionListener(movelistener);
        
        loginFail = new JLabel("ȸ�������� ��ġ���� �ʽ��ϴ�.");
        loginFail.setBounds(755, 600, 300, 100);
        loginFail.setForeground(Color.RED);
        layeredPane.add(loginFail);
        loginFail.setVisible(false);
        
        
        //������ �߰�
        layeredPane.add(panel);
        add(layeredPane);
        setVisible(true);
        setResizable(false); // ���α׷� ũ�� ���� �Ұ� ����
    	setLocationRelativeTo(null); // ȭ�� �߾ӿ� ������ �ϴ� ���� 
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
		System.out.println("Login ��ư ����");
		
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
