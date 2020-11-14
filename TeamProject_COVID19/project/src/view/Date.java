package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import lib.calInfo;
import view.All.buttonlistener;
import view.MainWindow.MainWindowPanel;

public class Date extends JFrame {
	BufferedImage img = null;
	buttonlistener buttonlistener = new buttonlistener();
	JButton previousMonth, nextMonth, goback;
	JLabel month, days[][] = new JLabel[6][7];
	String today = "8.27";
	String moveMonth;
	int todayMonth;
	int startMonth;
	int monthDays;
	String dayss[][];
	String monthDay[][];
	
	calInfo ci = new calInfo();
	
	
	public Date(String date) {
		today = date;
		setTitle("Test");
		setSize(1600, 900);
		
		try {
	           img = ImageIO.read(new File("img/MainWindowBackground.png"));
	           System.out.println("Image load Success");
	        } catch (IOException e) {
	           System.out.println("Image load Fail");
	           System.exit(0);
	        }
		
		for(int i=0; i<6; i++) {
			for(int j=0; j<7; j++) {
				days[i][j] = new JLabel();
			}
		}
		
		JLayeredPane layeredPane = new JLayeredPane();
		DatePanel panel = new DatePanel();
		
		DrawCal(date);
		
		for(int i=0; i<6; i++) {
			for(int j=0; j<7; j++) {
				layeredPane.add(days[i][j]);
			}
		}
		
		month = new JLabel(today.substring(0,2));
		month.setBounds(600, 400, 100, 100);
		layeredPane.add(month);
		
		previousMonth = new JButton("<-");
		previousMonth.setBounds(400, 400, 50, 50);
		previousMonth.addActionListener(buttonlistener);
		
		nextMonth = new JButton("->");
		nextMonth.setBounds(800, 400, 50, 50);
		nextMonth.addActionListener(buttonlistener);
		layeredPane.add(previousMonth);
		layeredPane.add(nextMonth);
		
		//goback 버튼
    	goback = new JButton("메인으로");
    	goback.setBounds(1300, 750, 200, 100);
    	layeredPane.add(goback);
    	goback.addActionListener(buttonlistener);
    	
        panel.setBounds(0, 0, 1600, 900); // 위치 설정정
        layeredPane.add(panel);
        add(layeredPane);
        setVisible(true);
        
        setLocationRelativeTo(null); // 화면 중앙에 오도록 하는 설정    
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void DrawCal(String today) {
		
		String todayMonthString = today.substring(0,2);
		String todayDayString;
		todayMonth = 0;
		int todayDay = 0;
		
		System.out.println("today : " + today);
		
		if(today.contains(".")) {
			todayMonthString = todayMonthString.substring(0,1);
			todayMonth = Integer.parseInt(todayMonthString);
		
			if(today.substring(3,4) == ".") {
				todayDayString = today.substring(2,3);
				todayDay = Integer.parseInt(todayDayString);
			}
			else {
				todayDayString = today.substring(2,4);
				todayDay = Integer.parseInt(todayDayString);
			}
		}
		
		startMonth = ci.firstdate(2020, todayMonth);
		monthDays = ci.leap_date(2020, todayMonth);
		
		int ds= 0;
		String dss;
		
		int count = 0;
		
		for(int i=0; i<6; i++) {
			while(startMonth != 7) {
				ds++;
	
				days[i][startMonth] = new JLabel("0");
				
				dss = Integer.toString(ds);
				
				days[i][startMonth].setText(dss);
				days[i][startMonth].setBounds(440 + startMonth*100, 150 + i*120, 100, 100);
				
				if(startMonth == 0) {
					days[i][startMonth].setForeground(Color.RED);
				}
				else if(startMonth == 6) {
					days[i][startMonth].setForeground(Color.BLUE);
				}
				
				ds = Integer.parseInt(dss);
				
				count++;
				startMonth++;
				
				if(count == monthDays) {
					break;
				}
			}
			if(count == monthDays) {
				break;
			}
			startMonth=0;
		}
		
		for(int i=0; i<6; i++) {
			for(int j=0; j<7; j++) {
				days[i][j].setVisible(true);
			}
		}
	}
	
	class DatePanel extends JPanel {
		public void paint(Graphics g) {
			super.paint(g);
			g.drawImage(img, 0, 0, null);
			
			for(int i=1; i<6; i++)
				g.drawLine(200, i*120, 900, i*120);
			
			for(int i=2; i<10; i++)
				g.drawLine(i*100, 120, i*100, 600);
		}
	}
	
	class buttonlistener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if((JButton) e.getSource() == previousMonth) {
				System.out.println("previousMonth Pressed");
				
				String todayMonthString = today.substring(0,2);
				
				if(today.contains(".")) {
					todayMonthString = todayMonthString.substring(0,1);
					todayMonth = Integer.parseInt(todayMonthString);
				}
				
				moveMonth = Integer.toString(todayMonth-1);
				System.out.println("move to " + moveMonth + " Month");
				System.out.println(moveMonth + ".00");
				
				setVisible(false);
				if(todayMonth-1 == 0) {
					Date date = new Date("9.00");
				}
				Date date = new Date(moveMonth + ".00");
				
			}
				if((JButton) e.getSource() == goback) {
					setVisible(false);
					try {
						MainWindow main = new MainWindow();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				
		}
	}
}
