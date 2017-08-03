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
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

class Lolita extends JFrame implements ActionListener,WindowListener{

  static String URL = "14.3.2.39";
  static int PORT = 1707;
  static int WIDTH;
  static int HEIGHT;
  static JLabel idlabel = new JLabel("id");
  static JLabel passlabel = new JLabel("pass");
  static JTextField idtext = new JTextField(5);
  static JTextField passtext = new JTextField(10);
  static JButton loginbtn = new JButton("login");
  static JLabel loginCommentLabel = new JLabel("");
  static volatile boolean loginok = false;
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
  static int id = -1;
  static byte[] pass_b;
//  static HttpClient hc = new HttpClient(URL, PORT);
//  static String getdata;
  static Protocol prot = new Protocol(URL, PORT);
  static PacketMaker pm = new PacketMaker();
  static PacketReader pr = new PacketReader();
  static Packet sendpacket;
  static Packet recipacket;
  static String[] talk;
  static int doyousend = 0;

  public static void main(String[] args){
		if(args.length == 2){
			WIDTH = Integer.parseInt(args[0]);
			HEIGHT = Integer.parseInt(args[1]);
		} else {
			WIDTH = 1000;
			HEIGHT = 1000;
		}
		
/*	try {
		File file_config = new File(".\\setting\\config.txt");
		BufferedReader br_config = new BufferedReader(new FileReader(file_config));
		id = Integer.parseInt(br_config.readLine());
		br_config.close();
	}catch(FileNotFoundException E) {
		System.out.println(E);
	}catch(IOException E) {
		System.out.println(E);
	}*/

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
	try{
		File historyfile = new File(".\\setting\\talkhistory.txt");
		BufferedReader br = new BufferedReader(new FileReader(historyfile));
		String str;
		int mycomment = 0;
  		while((str = br.readLine()) != null){
   			if(str.endsWith("roliroli")){
				JLabel blanclabel = new JLabel(" ");
				blanclabel.setFont(new Font("MSゴシック", Font.BOLD, 25));
				printpanel.add(blanclabel);
				JLabel namelabel = new JLabel("[" + name[Integer.parseInt(str.substring(0, 1))] + "]");
				namelabel.setFont(new Font("MSゴシック", Font.ITALIC, 15));
				printpanel.add(namelabel);
				if(Integer.parseInt(str.substring(0, 1)) == id){
					mycomment = 1;
				}else{
					mycomment = 0;
				}
			} else {
				JLabel printlabel = new JLabel(str);
				printlabel.setFont(new Font("MSゴシック", Font.BOLD, 25));
				if(mycomment == 0){
					printlabel.setBackground(Color.GREEN);
				}else{
					printlabel.setBackground(Color.CYAN);
				}
				printlabel.setOpaque(true);
				printpanel.add(printlabel);
			}
			printpanel.revalidate();
 		}
		JLabel blanclabel = new JLabel(" ");
		blanclabel.setFont(new Font("MSゴシック", Font.BOLD, 25));
		printpanel.add(blanclabel);
		printpanel.revalidate();

	}catch(IOException ioe){
		System.out.println(ioe);
	}
	while (true) {
//		getdata = hc.request(mynum + "\n");
//		getdata = "TFTTFTF5roli留年した。roli3roliwwwroli2roliざまあ";
//		String[] talk = talkdata(getdata);
		if(doyousend == 0){
			sendpacket = pm.make_1(id, pass_b, "");
			recipacket = prot.request(sendpacket);
			talk = pr.read_0(recipacket.getData());
		}else{
			doyousend = 0;
		}
//		for(int i = 0; i < talk.length; i++){System.out.println(talk[i]);}
		boolean[] online = onlinedata(talk[0]);
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
		for (int i = 1; i <= (talk.length-1)/2; i++) {
		  if(Integer.parseInt(talk[2*i-1]) != -1){
//			printarea.append("[" + name[Integer.parseInt(talk[2*i-1])] + "] " + talk[2*i] + "\n\n");
			JLabel namelabel = new JLabel("[" + name[Integer.parseInt(talk[2*i-1])] + "]");
			namelabel.setFont(new Font("MSゴシック", Font.ITALIC, 15));
			printpanel.add(namelabel);
			JLabel printlabel = new JLabel(talk[2*i]);
			printlabel.setFont(new Font("MSゴシック", Font.BOLD, 25));
			try {
				File historyfile = new File(".\\setting\\talkhistory.txt");
				FileWriter fw = new FileWriter(historyfile, true);
				fw.write(talk[2*i-1] + "roliroli\n");
				fw.write(talk[2*i] + "\n");
				fw.close();
			}catch(IOException ioe){
				System.out.println(ioe);
			}
		  if (Integer.parseInt(talk[2*i-1]) == id) {
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
		}
		label12.setText(name[0]);
		label22.setText(name[1]);
		label32.setText(name[2]);
		label42.setText(name[3]);
		label52.setText(name[4]);
		label62.setText(name[5]);
		label72.setText(name[6]);
		try {
			Thread.sleep(3000);
		}catch(InterruptedException e){}
	}
  }

  Lolita(String title){

		JFrame loginframe = new JFrame("Login");
		loginframe.setBounds(WIDTH/5, HEIGHT/5, 2*WIDTH/5, HEIGHT/5);
		loginframe.setVisible(true);
		JPanel loginpanel = new JPanel();
		loginpanel.add(idlabel);
		loginpanel.add(idtext);
		loginpanel.add(passlabel);
		loginpanel.add(passtext);
		loginbtn.addActionListener(this);
		loginbtn.setActionCommand("login");
		loginpanel.add(loginbtn);
		loginframe.add(loginpanel);
		while(!(loginok)){}
		pass_b = recipacket.getPass();
		loginframe.setVisible(false);
		for(int i = 0; i < 7; i++){
			sendpacket = pm.make_3(id, pass_b, "icon" + i + ".jpg");
			recipacket = prot.request(sendpacket);
			BufferedImage img = pr.read_2(recipacket.getData());
			try{
				ImageIO.write(img, "jpg", new File("./setting/picture/icon" + i + ".jpg"));
			} catch(IOException ioe){
				System.out.println(ioe);
			}
		}

    setTitle(title);
    setSize(WIDTH, HEIGHT);
	setLocationRelativeTo(null);
    addWindowListener(this);
	ImageIcon frameicon = new ImageIcon("./setting/picture/catan.jpg");
	setIconImage(frameicon.getImage());

	JMenuBar menubar = new JMenuBar();
	JMenu menu0 = new JMenu("File");
	JMenu menu1 = new JMenu("Edit");
	JMenu menu2 = new JMenu("Help");
	JMenuItem item11 = new JMenuItem("name");
	item11.addActionListener(this);
	item11.setActionCommand("edit_name");
	JMenuItem item12 = new JMenuItem("picture");
	item12.addActionListener(this);
	item12.setActionCommand("edit_picture");
	JMenuItem item21 = new JMenuItem("About Lolita");
	item21.addActionListener(this);
	item21.setActionCommand("about Lolita");
	menu1.add(item11);
	menu1.add(item12);
	menu2.add(item21);
	menubar.add(menu0);
	menubar.add(menu1);
	menubar.add(menu2);
	setJMenuBar(menubar);
	menu0.setFont(new Font("Arial", Font.BOLD, 20));
	menu1.setFont(new Font("Arial", Font.BOLD, 20));
	menu2.setFont(new Font("Arial", Font.BOLD, 20));
	item11.setFont(new Font("Arial", Font.PLAIN, 18));
	item12.setFont(new Font("Arial", Font.PLAIN, 18));
	item21.setFont(new Font("Arial", Font.PLAIN, 18));
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

	JPanel mainpanel = new JPanel();
	mainpanel.setPreferredSize(new Dimension(1000, 1000));
	mainpanel.setLayout(new BorderLayout());

	JPanel p1 = new JPanel();
	p1.setPreferredSize(new Dimension(WIDTH/4, 9*HEIGHT/10));
	p1.setBackground(Color.BLUE);


	JPanel p11 = new JPanel();
	p11.setPreferredSize(new Dimension(23*WIDTH/100, HEIGHT/8));
	p11.setLayout(new BorderLayout());
	p111 = new JPanel();
	p111.setPreferredSize(new Dimension(12*WIDTH/100, 12*HEIGHT/100));
	ImageIcon icon1 = new ImageIcon("./setting/picture/icon0.jpg");
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
	ImageIcon icon2 = new ImageIcon("./setting/picture/icon1.jpg");
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
	ImageIcon icon3 = new ImageIcon("./setting/picture/icon2.jpg");
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
	ImageIcon icon4 = new ImageIcon("./setting/picture/icon3.jpg");
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
	ImageIcon icon5 = new ImageIcon("./setting/picture/icon4.jpg");
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
	ImageIcon icon6 = new ImageIcon("./setting/picture/icon5.jpg");
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
	ImageIcon icon7 = new ImageIcon("./setting/picture/icon6.jpg");
	JLabel label71 = new JLabel(icon7);
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

	mainpanel.add(p1, BorderLayout.EAST);

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
	mainpanel.add(p2, BorderLayout.CENTER);
	JScrollPane mainScroll = new JScrollPane(mainpanel);
	getContentPane().add(mainScroll);

  }

  public void actionPerformed(ActionEvent e){
	String cmd = e.getActionCommand();

	if (cmd.equals("send")) {
		String text = sendarea.getText();
		if (!(text.isEmpty())) {
			sendpacket = pm.make_1(id, pass_b, text);
			recipacket = prot.request(sendpacket);
			talk = pr.read_0(recipacket.getData());
//			hc.request(senddata);
//			System.out.println(senddata);
			sendarea.setText("");
			doyousend = 1;
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
			name[id] = newname;
			label_edit_name.setText("Wow, so cool name.");
			text_edit_name.setText("");
		}
	} else if (cmd.equals("button1_edit_picture")) {
		JFileChooser fc_edit_picture = new JFileChooser(".\\setting\\picture");
		int selected = fc_edit_picture.showOpenDialog(this);
		if (selected == JFileChooser.APPROVE_OPTION) {
			File file = fc_edit_picture.getSelectedFile();
			text_edit_picture.setText(file.getName());
		}
	} else if (cmd.equals("button2_edit_picture")) {
		String filename = text_edit_picture.getText();
		if (!(filename.isEmpty())) {
			if (filename.endsWith(".jpg")) {
				ArrayList<Byte> result_buffer = new ArrayList<Byte>();
				try{
					FileInputStream fis = new FileInputStream(new File("./setting/picture/" + filename));
					int readlength;
					byte[] buffer = new byte[1024];
					do{
						readlength = fis.read(buffer);
						for(int i = 0; i < readlength; i++){
							result_buffer.add(buffer[i]);
						}
					}while(readlength == 1024);
					fis.close();
				}catch(IOException ioe){
					System.out.println(ioe);
				}
				byte[] imgdata = new byte[result_buffer.size()];
				for(int i = 0; i < result_buffer.size(); i++){
					imgdata[i] = result_buffer.get(i);
				}
				sendpacket = pm.make_5(id, pass_b, imgdata);
				recipacket = prot.request(sendpacket);
				if(pr.read_4(recipacket.getData()) == 'T') {
					System.out.println("送れた");
				}else{
					System.out.println("だめ");
				}

				label_edit_picture.setText("Wow, so cool picture.");
			} else {
				label_edit_picture.setText("Fuck. It's not jpf-file.");
			}
		}
	} else if (cmd.equals("about Lolita")) {
		JFrame frame_about_Lolita = new JFrame("About Lolita");
		frame_about_Lolita.setSize(2*WIDTH/3, 2*HEIGHT/3);
		frame_about_Lolita.setLocationRelativeTo(null);
		frame_about_Lolita.setVisible(true);
		JPanel panel_about_Lolita = new JPanel();
		JTextArea area_about_Lolita = new JTextArea(15, 15);
		area_about_Lolita.setFont(new Font("MSゴシック", Font.PLAIN, 30));
		area_about_Lolita.setText("<<Lolitaについて>>\nWBAのメンバーのみ使用が許される\n最も便利なチャットツールである。\nLolita最高。\n(なお、アイコンは100*100ピクセル程度で頼む)\n\n<<Staff>>\nIwamoto Yuma\nNakagawa Kohei\nMaeda Tomonori");
		panel_about_Lolita.add(area_about_Lolita);
		frame_about_Lolita.add(panel_about_Lolita);
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
/*		JPanel panel_detail3 = new JPanel();
		panel_detail3.add(new JLabel(new ImageIcon("./setting/picture/Mayoi.jpg")));
		JScrollPane sp_detail3 = new JScrollPane(panel_detail3);
		frame_detail3.add(sp_detail3);*/
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
	} else if (cmd.equals("login")) {
		String id_s = idtext.getText();
		String pass = passtext.getText();
		if(!(id_s.isEmpty() || pass.isEmpty())) {
			id = Integer.parseInt(id_s);
			sendpacket = pm.make_255(id, pass);
			recipacket = prot.request(sendpacket);
			char torf = pr.read_254(recipacket.getData());
//			System.out.println("1");
			if(torf == 'T') {
				loginok = true;
//				pass_b = recipacket.getPass();

//				System.out.println("2");
			}else{
				loginCommentLabel.setText("id or pass is wrong.");
//				System.out.println("3");
			}
		}
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