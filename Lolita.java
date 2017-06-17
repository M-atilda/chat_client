/*
 * file       : Lolita.java
 * author     : Nakagawa Kohei
 * brief      : Chat system "Lolita"s Client application
 */

import javax.swing.*;
import java.awt.Container;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

class Lolita extends JFrame implements ActionListener,WindowListener{

  static String URL = "192.168.8.105";
  static int PORT = 1707;
  static int WIDTH = 1200;
  static int HEIGHT = 1200;
  static JTextField text_edit_name = new JTextField(10);
  static JButton btn_edit_name = new JButton("OK");
  static JLabel label_edit_name = new JLabel("Imput your name.");
  static JTextField text_edit_picture = new JTextField(10);
  static JButton btn1_edit_picture = new JButton("file...");
  static JButton btn2_edit_picture = new JButton("OK");
  static JLabel label_edit_picture = new JLabel("Select jpg-file.");
  static JTextArea sendarea;
  static JPanel printpanel;
  static JScrollPane scrollpane1;
//  static JTextArea printarea;
  static JPanel p111;
  static JPanel p121;
  static JPanel p131;
  static JPanel p141;
  static JPanel p151;
  static JPanel p161;
  static JPanel p171;
  static JLabel label12;
  static JLabel label22;
  static JLabel label32;
  static JLabel label42;
  static JLabel label52;
  static JLabel label62;
  static JLabel label72;
  static JLabel yearlab1;
  static JLabel yearlab2;
  static JLabel yearlab3;
  static JLabel yearlab4;
  static JLabel yearlab5;
  static JLabel yearlab6;
  static JLabel yearlab7;
  static JButton btn1;
  static JButton btn2;
  static JButton btn3;
  static JButton btn4;
  static JButton btn5;
  static JButton btn6;
  static JButton btn7;
  static String[] name = {"岩本", "窪田", "中川", "早川", "平野", "藤田", "前田"};
  static String mynum;
  static HttpClient hc = new HttpClient(URL, PORT);
  static String getdata;

  public static void main(String args[]){
		mynum = "-1";
	try {
		File file_config = new File(".\\setting\\config.txt");
		BufferedReader br_config = new BufferedReader(new FileReader(file_config));
		mynum = br_config.readLine();
		br_config.close();
	}catch(FileNotFoundException E) {
		System.out.println(E);
	}catch(IOException E) {
		System.out.println(E);
	}

    Lolita frame = new Lolita("World Boardgame Association");
    frame.setVisible(true);
/*	try{
		File file_history = new File(".\\setting\\talkhistory.txt");
		BufferedReader br_history = new BufferedReader(new InputStreamReader(new FileInputStream(file_history), "UTF-8"));
		String history = br_history.readLine();
		while (history != null) {
//			printarea.append(history+"\n");
			printpanel.add(new JLabel(history));
			history = br_history.readLine();
		}
//			printarea.append("\n");
		br_history.close();
	}catch(FileNotFoundException E) {
		System.out.println(E);
	}catch(IOException E) {
		System.out.println(E);
	} */
	while (true) {
//		getdata = hc.request(mynum + "\n");
		getdata = "TFTTFTF5roli留年した。roli3roliwwwroli2roliざまあ";
		String[] talk = talkdata(getdata);
		boolean[] online = onlinedata(getdata);
		if (online[0]) {
			p111.setBackground(Color.RED);
		} else {
			p111.setBackground(Color.GRAY);
		}
		if (online[1]) {
			p121.setBackground(Color.RED);
		} else {
			p121.setBackground(Color.GRAY);
		}
		if (online[2]) {
			p131.setBackground(Color.RED);
		} else {
			p131.setBackground(Color.GRAY);
		}
		if (online[3]) {
			p141.setBackground(Color.RED);
		} else {
			p141.setBackground(Color.GRAY);
		}
		if (online[4]) {
			p151.setBackground(Color.RED);
		} else {
			p151.setBackground(Color.GRAY);
		}
		if (online[5]) {
			p161.setBackground(Color.RED);
		} else {
			p161.setBackground(Color.GRAY);
		}
		if (online[6]) {
			p171.setBackground(Color.RED);
		} else {
			p171.setBackground(Color.GRAY);
		}
		for (int i = 0; i < talk.length/2; i++) {
//			printarea.append("[" + name[Integer.parseInt(talk[2*i])] + "] " + talk[2*i+1] + "\n\n");
			JLabel namelabel = new JLabel("[" + name[Integer.parseInt(talk[2*i])] + "]");
			namelabel.setFont(new Font("MSゴシック", Font.ITALIC, 15));
			printpanel.add(namelabel);
			JLabel printlabel = new JLabel(talk[2*i+1]);
			printlabel.setFont(new Font("MSゴシック", Font.BOLD, 25));
		  if (talk[2*i].equals(mynum)) {
			printlabel.setBackground(Color.CYAN);
		  } else {
			printlabel.setBackground(Color.GREEN);
		  }
			printlabel.setOpaque(true);
			printpanel.add(printlabel);
			JLabel blanclabel = new JLabel(" ");
			blanclabel.setFont(new Font("MSゴシック", Font.BOLD, 25));
			printpanel.add(blanclabel);
			printpanel.revalidate();
		}
		label12.setText(name[0]);
		label22.setText(name[1]);
		label32.setText(name[2]);
		label42.setText(name[3]);
		label52.setText(name[4]);
		label62.setText(name[5]);
		label72.setText(name[6]);
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e){}
	}
  }

  Lolita(String title){
    setTitle(title);
    setSize(WIDTH, HEIGHT);
	setLocationRelativeTo(null);
    addWindowListener(this);
	ImageIcon frameicon = new ImageIcon("./setting/picture/catan.jpg");
	setIconImage(frameicon.getImage());

	JMenuBar menubar = new JMenuBar();
	JMenu menu0 = new JMenu("File");
	JMenu menu1 = new JMenu("Edit");
	JMenuItem item11 = new JMenuItem("name");
	item11.addActionListener(this);
	item11.setActionCommand("edit_name");
	JMenuItem item12 = new JMenuItem("picture");
	item12.addActionListener(this);
	item12.setActionCommand("edit_picture");
	menu1.add(item11);
	menu1.add(item12);
	menubar.add(menu0);
	menubar.add(menu1);
	setJMenuBar(menubar);
	menu0.setFont(new Font("Arial", Font.BOLD, 20));
	menu1.setFont(new Font("Arial", Font.BOLD, 20));
	item11.setFont(new Font("Arial", Font.PLAIN, 18));
	item12.setFont(new Font("Arial", Font.PLAIN, 18));
	text_edit_name.setFont(new Font("MSゴシック", Font.PLAIN, 25));
	btn_edit_name.addActionListener(this);
	btn_edit_name.setActionCommand("button_edit_name");
	label_edit_name.setFont(new Font("Arial", Font.PLAIN, 20));
	text_edit_picture.setFont(new Font("Arial", Font.PLAIN, 25));
	btn1_edit_picture.setFont(new Font("Arial", Font.PLAIN, 20));
	btn1_edit_picture.addActionListener(this);
	btn1_edit_picture.setActionCommand("button1_edit_picture");
	btn2_edit_picture.setFont(new Font("Arial", Font.PLAIN, 20));
	btn2_edit_picture.addActionListener(this);
	btn2_edit_picture.setActionCommand("button2_edit_picture");
	label_edit_picture.setFont(new Font("Arial", Font.PLAIN, 20));

	JPanel p1 = new JPanel();
	p1.setPreferredSize(new Dimension(WIDTH/4, 9*HEIGHT/10));
	p1.setBackground(Color.BLUE);


	JPanel p11 = new JPanel();
	p11.setPreferredSize(new Dimension(23*WIDTH/100, HEIGHT/8));
	p11.setLayout(new BorderLayout());
	p111 = new JPanel();
	p111.setPreferredSize(new Dimension(12*WIDTH/100, 12*HEIGHT/100));
	ImageIcon icon1 = new ImageIcon("./setting/picture/sample.jpg");
	JLabel label11 = new JLabel(icon1);
	p111.add(label11);
	JPanel p112 = new JPanel();
	label12 = new JLabel(name[0]);
	label12.setFont(new Font("MSゴシック", Font.PLAIN, 25));
	yearlab1 = new JLabel("univ.Tokyo 3rd");
	yearlab1.setFont(new Font("Arial", Font.ITALIC, 15));
	btn1 = new JButton("detail");
	btn1.setFont(new Font("Arial", Font.PLAIN, 18));
	btn1.setBackground(Color.WHITE);
	btn1.addActionListener(this);
	btn1.setActionCommand("detail1");
	p112.add(label12);
	p112.add(yearlab1);
	p112.add(btn1);
	p11.add(p111, BorderLayout.WEST);
	p11.add(p112, BorderLayout.CENTER);
	p1.add(p11);

	JPanel p12 = new JPanel();
	p12.setPreferredSize(new Dimension(23*WIDTH/100, HEIGHT/8));
	p12.setLayout(new BorderLayout());
	p121 = new JPanel();
	p121.setPreferredSize(new Dimension(12*WIDTH/100, 12*HEIGHT/100));
	ImageIcon icon2 = new ImageIcon("./setting/picture/sample.jpg");
	JLabel label21 = new JLabel(icon2);
	p121.add(label21);
	JPanel p122 = new JPanel();
	label22 = new JLabel(name[1]);
	label22.setFont(new Font("MSゴシック", Font.PLAIN, 25));
	yearlab2 = new JLabel("univ.Tokyo 3rd");
	yearlab2.setFont(new Font("Arial", Font.ITALIC, 15));
	btn2 = new JButton("detail");
	btn2.setFont(new Font("Arial", Font.PLAIN, 18));
	btn2.setBackground(Color.WHITE);
	btn2.addActionListener(this);
	btn2.setActionCommand("detail2");
	p122.add(label22);
	p122.add(yearlab2);
	p122.add(btn2);
	p12.add(p121, BorderLayout.WEST);
	p12.add(p122, BorderLayout.CENTER);
	p1.add(p12);

	JPanel p13 = new JPanel();
	p13.setPreferredSize(new Dimension(23*WIDTH/100, HEIGHT/8));
	p13.setLayout(new BorderLayout());
	p131 = new JPanel();
	p131.setPreferredSize(new Dimension(12*WIDTH/100, 12*HEIGHT/100));
	ImageIcon icon3 = new ImageIcon("./setting/picture/icon3.jpg");
	JLabel label31 = new JLabel(icon3);
	p131.add(label31);
	JPanel p132 = new JPanel();
	label32 = new JLabel(name[2]);
	label32.setFont(new Font("MSゴシック", Font.PLAIN, 25));
	yearlab3 = new JLabel("univ.Tokyo 3rd");
	yearlab3.setFont(new Font("Arial", Font.ITALIC, 15));
	btn3 = new JButton("detail");
	btn3.setFont(new Font("Arial", Font.PLAIN, 18));
	btn3.setBackground(Color.WHITE);
	btn3.addActionListener(this);
	btn3.setActionCommand("detail3");
	p132.add(label32);
	p132.add(yearlab3);
	p132.add(btn3);
	p13.add(p131, BorderLayout.WEST);
	p13.add(p132, BorderLayout.CENTER);
	p1.add(p13);

	JPanel p14 = new JPanel();
	p14.setPreferredSize(new Dimension(23*WIDTH/100, HEIGHT/8));
	p14.setLayout(new BorderLayout());
	p141 = new JPanel();
	p141.setPreferredSize(new Dimension(12*WIDTH/100, 12*HEIGHT/100));
	ImageIcon icon4 = new ImageIcon("./setting/picture/sample.jpg");
	JLabel label41 = new JLabel(icon4);
	p141.add(label41);
	JPanel p142 = new JPanel();
	label42 = new JLabel(name[3]);
	label42.setFont(new Font("MSゴシック", Font.PLAIN, 25));
	yearlab4 = new JLabel("univ.Tokyo 3rd");
	yearlab4.setFont(new Font("Arial", Font.ITALIC, 15));
	btn4 = new JButton("detail");
	btn4.setFont(new Font("Arial", Font.PLAIN, 18));
	btn4.setBackground(Color.WHITE);
	btn4.addActionListener(this);
	btn4.setActionCommand("detail4");
	p142.add(label42);
	p142.add(yearlab4);
	p142.add(btn4);
	p14.add(p141, BorderLayout.WEST);
	p14.add(p142, BorderLayout.CENTER);
	p1.add(p14);

	JPanel p15 = new JPanel();
	p15.setPreferredSize(new Dimension(23*WIDTH/100, HEIGHT/8));
	p15.setLayout(new BorderLayout());
	p151 = new JPanel();
	p151.setPreferredSize(new Dimension(12*WIDTH/100, 12*HEIGHT/100));
	ImageIcon icon5 = new ImageIcon("./setting/picture/sample.jpg");
	JLabel label51 = new JLabel(icon5);
	p151.add(label51);
	JPanel p152 = new JPanel();
	label52 = new JLabel(name[4]);
	label52.setFont(new Font("MSゴシック", Font.PLAIN, 25));
	yearlab5 = new JLabel("univ.Tokyo 3rd");
	yearlab5.setFont(new Font("Arial", Font.ITALIC, 15));
	btn5 = new JButton("detail");
	btn5.setFont(new Font("Arial", Font.PLAIN, 18));
	btn5.setBackground(Color.WHITE);
	btn5.addActionListener(this);
	btn5.setActionCommand("detail5");
	p152.add(label52);
	p152.add(yearlab5);
	p152.add(btn5);
	p15.add(p151, BorderLayout.WEST);
	p15.add(p152, BorderLayout.CENTER);
	p1.add(p15);

	JPanel p16 = new JPanel();
	p16.setPreferredSize(new Dimension(23*WIDTH/100, HEIGHT/8));
	p16.setLayout(new BorderLayout());
	p161 = new JPanel();
	p161.setPreferredSize(new Dimension(12*WIDTH/100, 12*HEIGHT/100));
	ImageIcon icon6 = new ImageIcon("./setting/picture/sample.jpg");
	JLabel label61 = new JLabel(icon6);
	p161.add(label61);
	JPanel p162 = new JPanel();
	label62 = new JLabel(name[5]);
	label62.setFont(new Font("MSゴシック", Font.PLAIN, 25));
	yearlab6 = new JLabel("univ.Tokyo 2nd");
	yearlab6.setFont(new Font("Arial", Font.ITALIC, 15));
	btn6 = new JButton("detail");
	btn6.setFont(new Font("Arial", Font.PLAIN, 18));
	btn6.setBackground(Color.WHITE);
	btn6.addActionListener(this);
	btn6.setActionCommand("detail6");
	p162.add(label62);
	p162.add(yearlab6);
	p162.add(btn6);
	p16.add(p161, BorderLayout.WEST);
	p16.add(p162, BorderLayout.CENTER);
	p1.add(p16);

	JPanel p17 = new JPanel();
	p17.setPreferredSize(new Dimension(23*WIDTH/100, HEIGHT/8));
	p17.setLayout(new BorderLayout());
	p171 = new JPanel();
	p171.setPreferredSize(new Dimension(12*WIDTH/100, 12*HEIGHT/100));
	ImageIcon icon7 = new ImageIcon("./setting/picture/sample.jpg");
	JLabel label71 = new JLabel(icon5);
	p171.add(label71);
	JPanel p172 = new JPanel();
	label72 = new JLabel(name[6]);
	label72.setFont(new Font("MSゴシック", Font.PLAIN, 25));
	yearlab7 = new JLabel("univ.Tokyo 3rd");
	yearlab7.setFont(new Font("Arial", Font.ITALIC, 15));
	btn7 = new JButton("detail");
	btn7.setFont(new Font("Arial", Font.PLAIN, 18));
	btn7.setBackground(Color.WHITE);
	btn7.addActionListener(this);
	btn7.setActionCommand("detail7");
	p172.add(label72);
	p172.add(yearlab7);
	p172.add(btn7);
	p17.add(p171, BorderLayout.WEST);
	p17.add(p172, BorderLayout.CENTER);
	p1.add(p17);

	getContentPane().add(p1, BorderLayout.EAST);

	JPanel p2 = new JPanel();
	p2.setBackground(Color.ORANGE);

//	printarea = new JTextArea(3*WIDTH/10, 4*HEIGHT/10);
//	printarea.setFont(new Font("MSゴシック", Font.BOLD, 20));
//	printarea.setForeground(Color.BLACK);
//	printarea.setEditable(false);
//	JScrollPane scrollpane1 = new JScrollPane(printarea);
	printpanel = new JPanel();
//	printpanel.setPreferredSize(new Dimension(3*WIDTH/5, 4*HEIGHT/5));
	printpanel.setLayout(new BoxLayout(printpanel, BoxLayout.PAGE_AXIS));
	scrollpane1 = new JScrollPane(printpanel);
	scrollpane1.setPreferredSize(new Dimension(3*WIDTH/5, 4*HEIGHT/5));
	scrollpane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	p2.add(scrollpane1, BorderLayout.CENTER);

	JPanel p22 = new JPanel();
	sendarea = new JTextArea(WIDTH/500, HEIGHT/40);
	sendarea.setFont(new Font("MSゴシック", Font.PLAIN, 30));
	JButton btn = new JButton("send");
	btn.setFont(new Font("MSゴシック", Font.PLAIN, 18));
	btn.setPreferredSize(new Dimension(8*WIDTH/100, 8*HEIGHT/100));
	btn.addActionListener(this);
	btn.setActionCommand("send");
	JScrollPane scrollpane2 = new JScrollPane(sendarea);
	p22.add(scrollpane2);
	p22.add(btn);
	p2.add(p22, BorderLayout.SOUTH);
	getContentPane().add(p2, BorderLayout.CENTER);

  }

  public void actionPerformed(ActionEvent e){
	String cmd = e.getActionCommand();

	if (cmd.equals("send")) {
		String text = sendarea.getText();
		if (!(text.isEmpty())) {
			String senddata = mynum + "\n" + text + "\n";
//			hc.request(senddata);
			System.out.println(senddata);
			sendarea.setText("");
		}
	} else if (cmd.equals("edit_name")) {
		System.out.println("Edit_name pushed.");
		JFrame frame_edit_name = new JFrame("Edit name");
		frame_edit_name.setBounds(WIDTH/5, HEIGHT/5, 2*WIDTH/5, HEIGHT/5);
		frame_edit_name.setVisible(true);
		JPanel panel_edit_name = new JPanel();
		panel_edit_name.add(text_edit_name);
		panel_edit_name.add(btn_edit_name);
		panel_edit_name.add(label_edit_name);
		frame_edit_name.add(panel_edit_name);
	} else if (cmd.equals("edit_picture")) {
		System.out.println("Edit_picture pushed.");
		JFrame frame_edit_picture = new JFrame("Edit picture");
		frame_edit_picture.setBounds(WIDTH/5, HEIGHT/5, 2*WIDTH/5, HEIGHT/5);
		frame_edit_picture.setVisible(true);
		JPanel panel_edit_picture = new JPanel();
		JPanel panelN_edit_picture = new JPanel();
		JPanel panelC_edit_picture = new JPanel();
		JPanel panelS_edit_picture = new JPanel();
		panelN_edit_picture.add(text_edit_picture);
		panelN_edit_picture.add(btn1_edit_picture);
		panelC_edit_picture.add(btn2_edit_picture);
		panelS_edit_picture.add(label_edit_picture);
		panel_edit_picture.setLayout(new BorderLayout());
		panel_edit_picture.add(panelN_edit_picture, BorderLayout.NORTH);
		panel_edit_picture.add(panelC_edit_picture, BorderLayout.CENTER);
		panel_edit_picture.add(panelS_edit_picture, BorderLayout.SOUTH);
		frame_edit_picture.add(panel_edit_picture);
	} else if (cmd.equals("button_edit_name")) {
		String newname = text_edit_name.getText();
		if (!(newname.isEmpty())) {
			name[Integer.parseInt(mynum)] = newname;
			label_edit_name.setText("Wow, so cool name.");
			text_edit_name.setText("");
		}
	} else if (cmd.equals("button1_edit_picture")) {
		JFileChooser fc_edit_picture = new JFileChooser(".\\setting");
		int selected = fc_edit_picture.showOpenDialog(this);
		if (selected == JFileChooser.APPROVE_OPTION) {
			File file = fc_edit_picture.getSelectedFile();
			text_edit_picture.setText(file.getName());
		}
	} else if (cmd.equals("button2_edit_picture")) {
		String filename = text_edit_picture.getText();
		if (!(filename.isEmpty())) {
			if (filename.endsWith(".jpg")) {
				label_edit_picture.setText("Wow, so cool picture.");
			} else {
				label_edit_picture.setText("Fuck. It's not jpf-file.");
			}
		}
	} else if (cmd.equals("detail1")) {
		JFrame frame_detail1 = new JFrame("About " + name[0]);
		frame_detail1.setBounds(WIDTH/100, HEIGHT/100, 8*WIDTH/5, 6*HEIGHT/5);
		frame_detail1.setVisible(true);
	} else if (cmd.equals("detail2")) {
		JFrame frame_detail2 = new JFrame("About " + name[1]);
		frame_detail2.setBounds(WIDTH/100, HEIGHT/100, 8*WIDTH/5, 6*HEIGHT/5);
		frame_detail2.setVisible(true);
	} else if (cmd.equals("detail3")) {
		JFrame frame_detail3 = new JFrame("About " + name[2]);
		frame_detail3.setBounds(WIDTH/100, HEIGHT/100, 8*WIDTH/5, 6*HEIGHT/5);
		frame_detail3.setVisible(true);
		JPanel panel_detail3 = new JPanel();
		panel_detail3.add(new JLabel(new ImageIcon("./setting/picture/Mayoi.jpg")));
		JScrollPane sp_detail3 = new JScrollPane(panel_detail3);
		frame_detail3.add(sp_detail3);
	} else if (cmd.equals("detail4")) {
		JFrame frame_detail4 = new JFrame("About " + name[3]);
		frame_detail4.setBounds(WIDTH/100, HEIGHT/100, 8*WIDTH/5, 6*HEIGHT/5);
		frame_detail4.setVisible(true);
	} else if (cmd.equals("detail5")) {
		JFrame frame_detail5 = new JFrame("About " + name[4]);
		frame_detail5.setBounds(WIDTH/100, HEIGHT/100, 8*WIDTH/5, 6*HEIGHT/5);
		frame_detail5.setVisible(true);
	} else if (cmd.equals("detail6")) {
		JFrame frame_detail6 = new JFrame("About " + name[5]);
		frame_detail6.setBounds(WIDTH/100, HEIGHT/100, 8*WIDTH/5, 6*HEIGHT/5);
		frame_detail6.setVisible(true);
	} else if (cmd.equals("detail7")) {
		JFrame frame_detail7 = new JFrame("About " + name[6]);
		frame_detail7.setBounds(WIDTH/100, HEIGHT/100, 8*WIDTH/5, 6*HEIGHT/5);
		frame_detail7.setVisible(true);
	}
 }

 public void windowClosing(WindowEvent e) {
/*	try{
		File file_history = new File(".\\setting\\talkhistory.txt");
		BufferedWriter bw_history = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file_history), "UTF-8"));
		String history = printarea.getText();
		String[] historys = history.split("\n");
		StringBuilder sb = new StringBuilder();
		for (int i = Math.max(0, historys.length-400); i < historys.length; i++) {
			sb.append(historys[i]);
			sb.append("\n");
		}
		String cuttedhistory = sb.toString();
		bw_history.write(cuttedhistory);
		bw_history.close();
		System.out.print(cuttedhistory);
	}catch(IOException ioe) {
		System.out.println(ioe);
	}*/
	System.exit(0);
 }

 public void windowClosed(WindowEvent e) {}
 public void windowOpened(WindowEvent e) {}
 public void windowIconified(WindowEvent e) {}
 public void windowDeiconified(WindowEvent e) {}
 public void windowActivated(WindowEvent e) {}
 public void windowDeactivated(WindowEvent e) {}
 

 private static String[] talkdata(String s) {
	String talk = s.substring(7);
	String[] a = talk.split("roli", 0);
	return a;
 }

 private static boolean[] onlinedata(String s) {
	boolean[] b = new boolean[7];
	for (int i = 0; i < 7; i++) {
		if ((s.substring(i, i+1)).equals("T")) {
			b[i] = true;
		} else {
			b[i] = false;
		}
	}
	return b;
 }

}