import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class customer_App {

	private JFrame frame;
	private JTextField id;
	private JPasswordField pw;
	private JTextField name;
	private JTextField publisher;
	private JTextField booknumber;
	private JTextField author;
	private JTextField search;
	private JPanel currPanel;
	public JTextField studentname;
	public JTextField studentnumber;
	public JTextField grade;
	public JTextField phonenumber;
	public JTextField email;
	public JTextField studentid;
	public JTextField studentpw;
	public JTextArea address;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customer_App window = new customer_App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public customer_App() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Customer customer = new Customer();

		frame = new JFrame();
		frame.setSize(803,538);
		frame.getContentPane().setLayout(null);
		String[][] data = customer.getCustomers();
		String[] headers = new String[] {"Name", "Author", "BookNumber", "Publisher", "Category", "Introduction"};
			// �гο� �´� �̹��� ���
		ImagePanel ListPanel = new ImagePanel(new ImageIcon("List.jpg").getImage());
		ListPanel.setBounds(0, 0, 787, 476);
		ListPanel.setVisible(false);
		ImagePanel LoginPanel = new ImagePanel(new ImageIcon("welcome.jpg").getImage()); 
		ImagePanel mainPanel = new ImagePanel(new ImageIcon("Administrator.jpg").getImage());
		JPanel joinPanel = new JPanel();
		joinPanel.setVisible(false);
		//�г��� �̵��Ͽ� window builder���� ������ �ڲ� ������ �߻��Ͽ� Panel�� ���� ����.

		//Label setting
		joinPanel.setBounds(0, 0, 787, 476);
		frame.getContentPane().add(joinPanel);
		joinPanel.setLayout(null);

		JLabel studentnameL = new JLabel("StudentName");
		studentnameL.setFont(new Font("���� ���", Font.BOLD, 15));
		studentnameL.setBounds(12, 27, 180, 44);
		joinPanel.add(studentnameL);

		JLabel studentnumberL = new JLabel("StudentNumber");
		studentnumberL.setFont(new Font("���� ���", Font.BOLD, 15));
		studentnumberL.setBounds(12, 108, 180, 44);
		joinPanel.add(studentnumberL);

		JLabel gradeL = new JLabel("Grade\r\n");
		gradeL.setFont(new Font("���� ���", Font.BOLD, 15));
		gradeL.setBounds(12, 186, 180, 44);
		joinPanel.add(gradeL);

		JLabel phonenumberL = new JLabel("PhoneNumber");
		phonenumberL.setFont(new Font("���� ���", Font.BOLD, 15));
		phonenumberL.setBounds(372, 27, 180, 44);
		joinPanel.add(phonenumberL);

		JLabel emailL = new JLabel("Email");
		emailL.setFont(new Font("���� ���", Font.BOLD, 15));
		emailL.setBounds(372, 108, 180, 44);
		joinPanel.add(emailL);

		JLabel addressL = new JLabel("Address");
		addressL.setFont(new Font("���� ���", Font.BOLD, 15));
		addressL.setBounds(372, 186, 180, 44);
		joinPanel.add(addressL);

		//TextField setting
		studentname = new JTextField();
		studentname.setBounds(136, 31, 202, 44);
		joinPanel.add(studentname);
		studentname.setColumns(10);

		studentnumber = new JTextField();
		studentnumber.setColumns(10);
		studentnumber.setBounds(136, 112, 202, 44);
		joinPanel.add(studentnumber);

		grade = new JTextField();
		grade.setColumns(10);
		grade.setBounds(136, 186, 202, 44);
		joinPanel.add(grade);

		phonenumber = new JTextField();
		phonenumber.setColumns(10);
		phonenumber.setBounds(497, 31, 278, 44);
		joinPanel.add(phonenumber);

		email = new JTextField();
		email.setColumns(10);
		email.setBounds(497, 112, 278, 44);
		joinPanel.add(email);

		JTextArea address_1 = new JTextArea();
		address_1.setBounds(497, 186, 278, 122);
		joinPanel.add(address_1);


		//ȸ������ ��ư ����
		JButton signupbtn = new JButton("Sign up");
		signupbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					//user Ŭ�������� �ҷ�����
					User user = new User();
					user.studentName = studentname.getText();
					user.studentNumber = studentnumber.getText();
					user.grade = grade.getText();
					user.studentID = studentid.getText();
					user.studentPW = studentpw.getText();
					user.phoneNumber = phonenumber.getText();
					user.email = email.getText();
					user.address = address_1.getText();

					UserManager.getInstance().createUser(user);
					//UserManager���� TextField�� ����ֱ�
					studentname.setText("");
					studentnumber.setText("");
					grade.setText("");
					studentid.setText("");
					studentpw.setText("");
					phonenumber.setText("");
					email.setText("");
					address_1.setText("");
					JOptionPane.showMessageDialog(null, "Data Saved Succesfully");

					//ȸ������ ������ loginPanel�� 
					joinPanel.setVisible(false);
					LoginPanel.setVisible(true);
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "There was an error while writing the data"); //����
				}


			}
		});
		//Login Panel Front end
		signupbtn.setFont(new Font("���ʷҵ���", Font.BOLD, 30));
		signupbtn.setBounds(372, 357, 379, 87);
		joinPanel.add(signupbtn);

		studentid = new JTextField();
		studentid.setColumns(10);
		studentid.setBounds(136, 264, 202, 44);
		joinPanel.add(studentid);

		JLabel studentidL = new JLabel("StudentID");
		studentidL.setFont(new Font("���� ���", Font.BOLD, 15));
		studentidL.setBounds(12, 264, 180, 44);
		joinPanel.add(studentidL);

		JLabel studentpwL = new JLabel("StudentPW");
		studentpwL.setFont(new Font("���� ���", Font.BOLD, 15));
		studentpwL.setBounds(12, 343, 180, 44);
		joinPanel.add(studentpwL);

		studentpw = new JTextField();
		studentpw.setColumns(10);
		studentpw.setBounds(136, 343, 202, 44);
		joinPanel.add(studentpw);

		mainPanel.setBackground(Color.WHITE);
		mainPanel.setBounds(0, 0, 787, 476);
		frame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setVisible(false);

		//Administrator JLable
		JLabel lblNewLabel_2 = new JLabel("PKNU librarian Page");
		lblNewLabel_2.setFont(new Font("���ʷҵ���", Font.BOLD, 20));
		lblNewLabel_2.setBounds(222, 0, 301, 62);
		mainPanel.add(lblNewLabel_2);

		JLabel NameL = new JLabel("Name");
		NameL.setHorizontalAlignment(SwingConstants.CENTER);
		NameL.setFont(new Font("���ʷҵ���", Font.PLAIN, 20));
		NameL.setBounds(244, 51, 95, 51);
		mainPanel.add(NameL);

		JLabel BookNumberL = new JLabel("BookNumber");
		BookNumberL.setHorizontalAlignment(SwingConstants.CENTER);
		BookNumberL.setFont(new Font("���ʷҵ���", Font.PLAIN, 20));
		BookNumberL.setBounds(180, 112, 183, 51);
		mainPanel.add(BookNumberL);

		JLabel CategoryL = new JLabel("Category");
		CategoryL.setHorizontalAlignment(SwingConstants.CENTER);
		CategoryL.setFont(new Font("���ʷҵ���", Font.PLAIN, 20));
		CategoryL.setBounds(446, 164, 183, 51);
		mainPanel.add(CategoryL);

		JLabel PublisherL = new JLabel("Publisher");
		PublisherL.setHorizontalAlignment(SwingConstants.CENTER);
		PublisherL.setFont(new Font("���ʷҵ���", Font.PLAIN, 20));
		PublisherL.setBounds(446, 51, 183, 51);
		mainPanel.add(PublisherL);

		JLabel AuthorL = new JLabel("Author");
		AuthorL.setHorizontalAlignment(SwingConstants.CENTER);
		AuthorL.setFont(new Font("���ʷҵ���", Font.PLAIN, 20));
		AuthorL.setBounds(456, 103, 183, 51);
		mainPanel.add(AuthorL);

		JLabel IntroductionL = new JLabel("Introduction");
		IntroductionL.setHorizontalAlignment(SwingConstants.CENTER);
		IntroductionL.setFont(new Font("���ʷҵ���", Font.PLAIN, 20));
		IntroductionL.setBounds(206, 164, 183, 51);
		mainPanel.add(IntroductionL);

		//administrator textField
		name = new JTextField();
		name.setFont(new Font("�������", Font.PLAIN, 18));
		name.setBounds(335, 64, 138, 38);
		mainPanel.add(name);
		name.setColumns(10);

		publisher = new JTextField();
		publisher.setFont(new Font("�������", Font.PLAIN, 18));
		publisher.setColumns(10);
		publisher.setBounds(592, 64, 183, 38);
		mainPanel.add(publisher);

		booknumber = new JTextField();
		booknumber.setFont(new Font("�������", Font.PLAIN, 18));
		booknumber.setColumns(10);
		booknumber.setBounds(334, 116, 139, 38);
		mainPanel.add(booknumber);

		author = new JTextField();
		author.setFont(new Font("�������", Font.PLAIN, 18));
		author.setColumns(10);
		author.setBounds(592, 112, 183, 38);
		mainPanel.add(author);

		//category combobox
		JComboBox category = new JComboBox(new String[] {"humanities", "Operation", "literature", "technology", "magazine"});
		category.setFont(new Font("�������", Font.PLAIN, 18));
		category.setEditable(true);
		category.setBackground(Color.WHITE);
		category.setBounds(592, 170, 183, 45);
		mainPanel.add(category);

		JTextArea introduction = new JTextArea();
		introduction.setBounds(233, 220, 290, 214);
		introduction.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		mainPanel.add(introduction);

		//�߰��ϱ� ��ư�� ������ dB�� ������ ����
		JButton Submitbtn = new JButton("\uC800\uC7A5");
		Submitbtn.addActionListener(new ActionListener() { //��ư�� ������ �Էµ� string���� ���� ���� DB�� ����
			public void actionPerformed(ActionEvent e) {
				String nameTxt = name.getText();
				String authorTxt = author.getText();
				String booknumberTxt = booknumber.getText();
				String publisherTxt = publisher.getText();
				String categoryTxt = category.getSelectedItem().toString();
				String introductionTxt = introduction.getText();

				Customer.createCustomer(nameTxt, authorTxt, booknumberTxt, publisherTxt, categoryTxt, introductionTxt);
				JOptionPane.showMessageDialog(null, "Your data has been saved successfully");
				name.setText("");
				author.setText("");
				booknumber.setText("");
				publisher.setText("");
				introduction.setText("");
				address_1.setText("");
				mainPanel.setVisible(false);
				ListPanel.setVisible(true);
			}
		});
		Submitbtn.setBounds(572, 244, 164, 77);
		mainPanel.add(Submitbtn);

		//List btn
		JButton listbtn = new JButton("");
		listbtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		listbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				ListPanel.setVisible(true);
				currPanel = ListPanel;
				//currPanel�� ���� ��ư�� ��������, �۵��� ���ϵ��ϼ���
			}
		});

		// �гο� �´� �̹��� ���
		listbtn.setIcon(new ImageIcon("Listbtn1.jpg"));
		listbtn.setBounds(42, 31, 152, 62);
		mainPanel.add(listbtn);
		ListPanel.setLayout(null);
		JTable table = new JTable(data, headers);
		table.setRowHeight(30);
		table.setFont(new Font("Sanserif",  Font.BOLD, 15));
		table.setAlignmentX(0);
		table.setSize(600,400);
		table.setPreferredScrollableViewportSize(new Dimension(600,400));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(219, 24, 556, 305);
		ListPanel.add(scrollPane);
		frame.getContentPane().add(ListPanel);

		search = new JTextField();
		search.setHorizontalAlignment(SwingConstants.CENTER);
		search.setText("\uAC80\uC0C9");
		search.setFont(new Font("�������", Font.BOLD, 25));
		search.setBounds(219, 352, 392, 63);
		ListPanel.add(search);
		search.setColumns(10);

		//addministrator btn	
		//���� �α��εǾ� �ִ� ID�� ���� Ȯ���ϱ� 
		//�α��εǾ� �ִ� ID�� root�̸� administrator
		JButton addbtn = new JButton("");
		addbtn.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currPanel.setVisible(false);
				mainPanel.setVisible(true);
				currPanel = mainPanel;
			}
		});
		// �гο� �´� �̹��� ���
		addbtn.setIcon(new ImageIcon("Administratorbtn1.jpg"));
		addbtn.setBounds(35, 141, 172, 63);
		ListPanel.add(addbtn);

		currPanel = LoginPanel; //���� �г��� �α����гη� ����

		LoginPanel.setBounds(0, 0, 787, 476);
		frame.getContentPane().add(LoginPanel);

		JLabel lblNewLabel = new JLabel("Log In");
		lblNewLabel.setFont(new Font("�������", Font.BOLD, 53));
		lblNewLabel.setBounds(52, 0, 227, 132);
		LoginPanel.add(lblNewLabel);

		id = new JTextField();
		id.setFont(new Font("���� ���", Font.PLAIN, 24));
		id.setHorizontalAlignment(SwingConstants.LEFT);
		id.setToolTipText("");
		id.setBounds(97, 121, 158, 32);
		LoginPanel.add(id);
		id.setColumns(10);

		pw = new JPasswordField();
		pw.setBounds(97, 163, 158, 32);
		LoginPanel.add(pw);

		JLabel lblNewLabel_1 = new JLabel("ID : ");
		lblNewLabel_1.setFont(new Font("���� ���", Font.BOLD, 30));
		lblNewLabel_1.setBounds(39, 117, 70, 37);
		LoginPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("PW: ");
		lblNewLabel_1_1.setFont(new Font("���� ���", Font.BOLD, 30));
		lblNewLabel_1_1.setBounds(37, 158, 94, 37);
		LoginPanel.add(lblNewLabel_1_1);

		// �гο� �´� �̹��� ���
		//login btn
		JButton btnLogin = new JButton("");
		btnLogin.setIcon(new ImageIcon("loginbutton4.jpg"));
		btnLogin.setPressedIcon(new ImageIcon("loginbutton5.jpg"));

		//��ư�� �������� ������ �ٲ��
		//�������� ��� ����
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User user = UserManager.getInstance().getUser(id.getText());
				if (user == null) { //�ƹ��͵� �������� null���� ��ȯ�ϹǷ� csv ���Ͽ� ��ġ�ϴ� ���� ������ null������ ��
					JOptionPane.showMessageDialog(null, "ID does not Exist");
				}else {
					if(!String.valueOf(pw.getPassword()).equals(user.studentPW)) {
						JOptionPane.showMessageDialog(null, "Passwords do not match");
					}else {
						JOptionPane.showMessageDialog(null, "You success logd in");
						mainPanel.setVisible(false);
						ListPanel.setVisible(true);
					}
				}
				
				//�ʱ� CSV���� ������ �о�ͼ� �����ϱ���, ���̵� �����ؼ� �α���ID�� 
				/*	if(id.getText().equals("root")&&Arrays.equals(pw.getPassword(),"root".toCharArray())) { //id�� string�� �̹Ƿ� equal�� ��
					System.out.println("�����ڴ� �ݰ����ϴ�.");
					mainPanel.setVisible(false);
					ListPanel.setVisible(true);
					} else {
					JOptionPane.showMessageDialog(null, "You failed to logd in");
					} */
			}
		});

		btnLogin.setFont(new Font("����", Font.PLAIN, 16));
		btnLogin.setBounds(64, 205, 191, 42);
		LoginPanel.add(btnLogin);

		//joinPanel
		JButton joinbtn = new JButton("Sign Up");
		joinbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPanel.setVisible(false);
				joinPanel.setVisible(true);
			}
		});


		joinbtn.setFont(new Font("����", Font.BOLD | Font.ITALIC, 15));
		joinbtn.setBounds(64, 257, 191, 23);
		LoginPanel.add(joinbtn);


		//search��� ����
		search.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				String val = search.getText();
				TableRowSorter<TableModel> trs = new TableRowSorter<>(table.getModel());
				table.setRowSorter(trs);
				trs.setRowFilter(RowFilter.regexFilter(val));
			}
		});

		/* TableColumnModel columnModels = table.getColumnModel(); //table size ���� �ϱ�
		columnModels.getColumn(0).setPreferredWidth(100);
		columnModels.getColumn(2).setPreferredWidth(50);
		columnModels.getColumn(3).setPreferredWidth(10);  */
		//�޴��� ����
		frame.setJMenuBar(menuBar());
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		
	}
	
	//�޴��� Exit��� ����
	public JMenuBar menuBar() {
		JMenuBar bar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		JMenu aboutMenu = new JMenu("About");

		bar.add(fileMenu);
		bar.add(aboutMenu);

		JMenuItem openFile = new JMenuItem("Open");
		JMenuItem exit = new JMenuItem("Exit");
		fileMenu.add(openFile);
		fileMenu.addSeparator();
		fileMenu.add(exit);

		exit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

		});

		return bar;
	}
}
