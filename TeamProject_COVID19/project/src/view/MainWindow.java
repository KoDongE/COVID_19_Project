package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import database.monthPatients;
import view.Root.RootPanel;
import view.Root.buttonlistener;

public class MainWindow extends JFrame {
	BufferedImage img = null;
	JButton seeAll; // ����� ��ü
	JButton seeArea; // ����� ������
	JButton seeDate; // ��¥��
	JButton seeConnect; // ���˷�
	JButton seeTraffic; // �̵� ���
	JButton close; // �ݱ�
	buttonlistener movelistener = new buttonlistener();
	monthPatients mp;
	public int jen, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec;
	
	public MainWindow() throws SQLException {
		setTitle("Test");
		setSize(1600, 900);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		 try {
	            img = ImageIO.read(new File("img/MainWindowBackground.png"));
	            System.out.println("Image load Success");
	        } catch (IOException e) {
	            System.out.println("Image load Fail");
	            System.exit(0);
	        }
		 
		 setGraph();
		 
		//���̾ƿ� ����
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(0,0,1600,900);
		layeredPane.setLayout(null);
		
		
        
		//�г�
		MainWindowPanel panel = new MainWindowPanel();
        panel.setBounds(0, 0, 1600, 900); // ��ġ ������
        
        GraphPanel graphPanel = new GraphPanel();
        graphPanel.setBounds(50, 50, 1000, 700);
        
        //����� ��ü Ȯ���� ���� ��ư
        seeAll = new JButton("����� ��ü Ȯ���� ��Ȳ ����");
        seeAll.setBounds(100,700,200,100);
        layeredPane.add(seeAll);
        seeAll.addActionListener(movelistener);
        
        //����� ������ ȯ���� ���� ��ư
        seeArea = new JButton("����� ������ Ȯ���� ��Ȳ ����");
        seeArea.setBounds(350,700,200,100);
        layeredPane.add(seeArea);
        seeArea.addActionListener(movelistener);
        
        //��¥�� ȯ���� ���� ��ư
        seeDate = new JButton("��¥�� Ȯ���� ��Ȳ ����");
        seeDate.setBounds(600,700,200,100);
        layeredPane.add(seeDate);
        seeDate.addActionListener(movelistener);
        
        //ȯ���� ���˷� ���� ��ư
        seeConnect = new JButton("Ȯ���� ���˷� ����");
        seeConnect.setBounds(850,700,200,100);
        layeredPane.add(seeConnect);
        seeConnect.addActionListener(movelistener);
        
        //Ȯ���� �̵���� ���� ��ư
        seeTraffic = new JButton("Ȯ���� �̵���� ����");
        seeTraffic.setBounds(1100,700,200,100);
        layeredPane.add(seeTraffic);
        seeTraffic.addActionListener(movelistener);
        
        //���� ��ư
        close = new JButton("���α׷� ����");
        close.setBounds(1350,700,200,100);
        layeredPane.add(close);
        close.addActionListener(movelistener);
        
        
		setVisible(true);
		layeredPane.add(graphPanel);
		layeredPane.add(panel);
		
        add(layeredPane);
		setResizable(false); // ���α׷� ũ�� ���� �Ұ� ����
    	setLocationRelativeTo(null); // ȭ�� �߾ӿ� ������ �ϴ� ����  
    	
	}
	
	public void setGraph() throws SQLException {
		int i=1;
		mp = new monthPatients();
		
		switch(i) {
			case 1 : jen = mp.countMonth(Integer.toString(i)); i++;
			case 2 : feb = mp.countMonth(Integer.toString(i)); i++;
			case 3 : mar = mp.countMonth(Integer.toString(i)); i++;
			case 4 : apr = mp.countMonth(Integer.toString(i)); i++;
			case 5 : may = mp.countMonth(Integer.toString(i)); i++;
			case 6 : jun = mp.countMonth(Integer.toString(i)); i++;
			case 7 : jul = mp.countMonth(Integer.toString(i)); i++;
			case 8 : aug = mp.countMonth(Integer.toString(i)); i++;
			case 9 : sep = mp.countMonth(Integer.toString(i)); i++;
			case 10 : oct = mp.countMonth(Integer.toString(i)); i++;
			case 11 : nov = mp.countMonth(Integer.toString(i)); i++;
			case 12 : dec = mp.countMonth(Integer.toString(i)); i++;
			break;
		}
	}
	
	class MainWindowPanel extends JPanel {
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(img, 0, 0, null);
		}
	}
	
	class GraphPanel extends JPanel {
	
		public void paint(Graphics g) {
			super.paint(g);
			
			g.clearRect(0,0,getWidth(), getHeight());
			g.drawLine(50, 550, 1000, 550); // ��
			g.drawLine(50, 50, 50, 550); // ��
			g.drawLine(999, 50, 999, 550); // ��
			g.drawLine(50, 50, 1000, 50); // ��
			
			for(int i=0; i<26; i++) {
				g.drawString(i*100 + "", 20, 555-(20*i));
				g.drawLine(50, 550-(20*i), 1000, 550-(20*i));
			}
			
		
			
			int xpos=110;
			
			for(int i=1; i<13; i++) {
				
				g.drawString(Integer.toString(i), xpos, 570);
				xpos = xpos+75;
			
			}
			
			g.setColor(Color.RED);
			
			if(jen > 0) {
				g.fillRect(110, (int)(550-jen*0.2), 10, (int)(jen*0.2));
				g.drawString(Integer.toString(jen) + "��", 105, 600);
			}
			if(feb > 0) {
				g.fillRect(185, (int)(550-feb*0.2), 10, (int)(feb*0.2));
				g.drawString(Integer.toString(feb) + "��", 180, 600);
			}
			if(mar > 0) {
				g.fillRect(260, (int)(550-mar*0.2), 10, (int)(mar*0.2));
				g.drawString(Integer.toString(mar) + "��", 250, 600);
			}
			if(apr > 0) {
				g.fillRect(335, (int)(550-apr*0.2), 10, (int)(apr*0.2));
				g.drawString(Integer.toString(apr) + "��", 325, 600);
			}
			if(may > 0) {
				g.fillRect(410, (int)(550-may*0.2), 10, (int)(may*0.2));
				g.drawString(Integer.toString(may) + "��", 400, 600);
			}
			if(jun > 0) {
				g.fillRect(485, (int)(550-jun*0.2), 10, (int)(jun*0.2));
				g.drawString(Integer.toString(jun) + "��", 470, 600);
			}
			if(jul > 0) {
				g.fillRect(560, (int)(550-jul*0.2), 10, (int)(jul*0.2));
				g.drawString(Integer.toString(jul) + "��", 550, 600);
			}
			if(aug > 0) { 
				g.fillRect(635, (int)(550-aug*0.2), 10, (int)(aug*0.2));
				g.drawString(Integer.toString(aug) + "��", 620, 600);
			}
			if(sep > 0) {
				g.fillRect(710, (int)(550-sep*0.2), 10, (int)(sep*0.2));
				g.drawString(Integer.toString(sep) + "��", 700, 600);
			}
			if(oct > 0) {
				g.fillRect(785, (int)(550-oct*0.2), 10, (int)(oct*0.2));
				g.drawString(Integer.toString(oct) + "��", 790, 600);
			}
			if(nov > 0) {
				g.fillRect(860, (int)(550-nov*0.2), 10, (int)(nov*0.2));
				g.drawString(Integer.toString(nov) + "��", 865, 600);
			}
			if(dec > 0) {
				g.fillRect(935, (int)(550-dec*0.2), 10, (int)(dec*0.2));
				g.drawString(Integer.toString(dec) + "��", 940, 600);
			}
		}
	}
	
	class buttonlistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if((JButton) e.getSource() == seeAll) {
				setVisible(false);
				try {
					All all = new All();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		}
	}
	}
}
	
