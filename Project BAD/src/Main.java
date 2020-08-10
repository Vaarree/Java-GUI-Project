import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.SQLException;

public class Main extends JFrame implements ActionListener {
	JPanel mainPanel, mPanel;
	JButton register, signin, back, submit;
	JDesktopPane content, content1;
	JFrame frame, frame2, frame1;

	JTextField nameF, emailF;
	static JTextField emailField;

	JPasswordField cpasswordF, passwordF;
	static JPasswordField passwordField;

	JRadioButton maleG, femaleG;

	JCheckBox tAC;

	JComboBox dayCMB, monthCMB, yearCMB;

	JMenuItem ourProduct, cart, changePassword, logout, logout1, manageUser,manageItem;

	JMenu admin;

	formProduct formPRD;
	formCart formCART;
	formChangePW formCPW;
	formManageUser formMU;
	formManageItem formMI;

	Connect con = new Connect();

	String name;
	
	public static String currentUserID= "";
	
	public void loginPanel() {
		// main
		mainPanel = new JPanel(new BorderLayout());

		// north
		JPanel titlePanel = new JPanel();

		titlePanel.setBackground(Color.BLUE);
		// center
		JPanel loginPanel = new JPanel(new GridLayout(0, 2));

		// south
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
		buttonPanel.setBackground(Color.BLUE);
		// label
		JPanel labelemailP = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
		labelemailP.setBackground(Color.BLUE);
		JPanel labelpasswordP = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
		labelpasswordP.setBackground(Color.BLUE);
		// field
		JPanel fieldEmail = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 10));
		fieldEmail.setBackground(Color.BLUE);
		JPanel fieldPassword = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 10));
		fieldPassword.setBackground(Color.BLUE);

		JLabel title = new JLabel("SIGN IN");
		JLabel email = new JLabel("Email");
		JLabel password = new JLabel("Password");

		emailField = new JTextField();
		passwordField = new JPasswordField();

		register = new JButton("Register");
		signin = new JButton("Sign In");

		title.setFont(new Font("Calibri", Font.BOLD, 20));

		titlePanel.add(title);

		labelemailP.add(email);
		labelpasswordP.add(password);

		emailField.setPreferredSize(new Dimension(200, 20));
		fieldEmail.add(emailField);
		passwordField.setPreferredSize(new Dimension(200, 20));
		fieldPassword.add(passwordField);

		loginPanel.add(labelemailP);
		loginPanel.add(fieldEmail);
		loginPanel.add(labelpasswordP);
		loginPanel.add(fieldPassword);

		buttonPanel.add(register);
		register.addActionListener(this);

		buttonPanel.add(signin);
		signin.addActionListener(this);

		mainPanel.add(titlePanel, "North");
		mainPanel.add(loginPanel, "Center");
		mainPanel.add(buttonPanel, "South");

		setTitle("Cadbully Shop");
		setSize(500, 200);
		setLayout(new BorderLayout());
		this.add(mainPanel);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void createAccount() {
		Vector<String> dayV, monthV, yearV; // buat nyimpen isi combo box
		// MAIN
		mPanel = new JPanel(new BorderLayout());
		// CENTER
		JPanel cPanel = new JPanel(new GridLayout(0, 2, 5, 10));
		// comboBox
		JPanel comboBoxPanel = new JPanel(new FlowLayout());
		// gender
		JPanel gPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		// checkbox
		JPanel cbPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 0));
		// NORTH
		JPanel tPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
		// SOUTH
		JPanel buttonPanel = new JPanel(new FlowLayout());
		// label
		JPanel labelnameP = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
		JPanel labelemailP = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
		JPanel labelpasswordP = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
		JPanel labelcpasswordP = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
		JPanel labelgenderP = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
		JPanel labelbirthdayP = new JPanel(new FlowLayout(FlowLayout.LEFT, 30, 10));
		// field
		JPanel fieldNm = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 0));
		JPanel fieldEm = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 0));
		JPanel fieldpw = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 0));
		JPanel fieldcp = new JPanel(new FlowLayout(FlowLayout.RIGHT, 30, 0));

		JLabel name = new JLabel("Name");
		JLabel email = new JLabel("Email");
		JLabel password = new JLabel("Password");
		JLabel cPassword = new JLabel("Confirm password");
		JLabel gender = new JLabel("Gender");
		JLabel Birthday = new JLabel("Birthday");
		JLabel tac = new JLabel("Term and Condition");

		nameF = new JTextField();
		emailF = new JTextField();

		cpasswordF = new JPasswordField();
		passwordF = new JPasswordField();

		tAC = new JCheckBox();

		back = new JButton("Back");
		submit = new JButton("Submit");

		maleG = new JRadioButton("Male");
		femaleG = new JRadioButton("Female");
		ButtonGroup bg = new ButtonGroup();

		dayCMB = new JComboBox();
		monthCMB = new JComboBox();
		yearCMB = new JComboBox();

		JLabel title = new JLabel("Create Account");
		title.setFont(new Font("Sans", Font.BOLD, 28));

		dayV = new Vector<>();
		monthV = new Vector<>();
		yearV = new Vector<>();

		for (int i = 1; i <= 31; i++) {
			dayV.add("" + i);
		}

		for (int i = 1; i <= 12; i++) {
			monthV.add("" + i);
		}

		for (int i = 1905; i <= 2019; i++) {
			yearV.add("" + i);
		}

		dayCMB = new JComboBox(dayV);
		monthCMB = new JComboBox(monthV);
		yearCMB = new JComboBox(yearV);

		comboBoxPanel.add(dayCMB);
		comboBoxPanel.add(monthCMB);
		comboBoxPanel.add(yearCMB);

		cbPanel.add(tAC, new FlowLayout(FlowLayout.LEFT));
		cbPanel.add(tac, new FlowLayout(FlowLayout.CENTER));

		tPanel.add(title);

		bg.add(maleG);
		bg.add(femaleG);

		gPanel.add(maleG);
		gPanel.add(femaleG);

		buttonPanel.add(back);
		back.addActionListener(this);
		buttonPanel.add(submit);
		submit.addActionListener(this);

		labelnameP.add(name);
		labelemailP.add(email);
		labelpasswordP.add(password);
		labelcpasswordP.add(cPassword);
		labelgenderP.add(gender);
		labelbirthdayP.add(Birthday);

		nameF.setPreferredSize(new Dimension(200, 30));
		emailF.setPreferredSize(new Dimension(200, 30));
		fieldNm.add(nameF);
		fieldEm.add(emailF);
		passwordF.setPreferredSize(new Dimension(200, 30));
		cpasswordF.setPreferredSize(new Dimension(200, 30));
		fieldpw.add(passwordF);
		fieldcp.add(cpasswordF);

		cPanel.add(labelnameP);
		cPanel.add(fieldNm);
		cPanel.add(labelemailP);
		cPanel.add(fieldEm);
		cPanel.add(labelpasswordP);
		cPanel.add(fieldpw);
		cPanel.add(labelcpasswordP);
		cPanel.add(fieldcp);
		cPanel.add(labelgenderP);
		cPanel.add(gPanel);
		cPanel.add(labelbirthdayP);
		cPanel.add(comboBoxPanel);
		cPanel.add(cbPanel);

		mPanel.add(tPanel, "North");
		mPanel.add(cPanel, "Center");
		mPanel.add(buttonPanel, "South");

		setTitle("Cadbully Shop");
		setSize(500, 500);
		this.add(mPanel);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public void mainForm() {
		JMenuBar menubar = new JMenuBar();
		JMenu store = new JMenu("Store");
		JMenu profile = new JMenu("Profile");
		frame2 = new JFrame();

		frame2.setTitle("Cadbully Shop");
		frame2.setSize(1300, 750);
		frame2.setVisible(true);
		frame2.setResizable(true);
		frame2.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame2.setLocationRelativeTo(null);
		frame2.setLayout(new BorderLayout());
		frame2.setContentPane(new JLabel(new ImageIcon(getClass().getResource("/images/mocha.jpg"))));

		ourProduct = new JMenuItem("Our Product");
		cart = new JMenuItem("cart");
		changePassword = new JMenuItem("Change Password");
		logout = new JMenuItem("Log out");

		content = new JDesktopPane();

		menubar.add(store);
		menubar.add(profile);

		store.add(ourProduct);
		ourProduct.addActionListener(this);
		store.add(cart);
		cart.addActionListener(this);

		profile.add(changePassword);
		changePassword.addActionListener(this);
		profile.add(logout);
		logout.addActionListener(this);

		frame2.setJMenuBar(menubar);

	}

	public void mainFormAdmin() {
		JMenuBar menubar = new JMenuBar();

		frame1 = new JFrame();

		frame1.setTitle("Cadbully Shop");
		frame1.setSize(1300, 750);
		frame1.setVisible(true);
		frame1.setResizable(true);
		frame1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame1.setLocationRelativeTo(null);
		frame1.setLayout(new BorderLayout());
		frame1.setContentPane(new JLabel(new ImageIcon(getClass().getResource("/images/mocha.jpg"))));

		admin = new JMenu("Menu");

		logout1 = new JMenuItem("Logout");
		logout1.addActionListener(this);
		
		manageUser = new JMenuItem("Manage User");
		manageUser.addActionListener(this);
		content1 = new JDesktopPane();

		manageItem = new JMenuItem("Manage Item");
		manageItem.addActionListener(this);
		
		
		menubar.add(admin);
		admin.add(manageUser);
		admin.add(manageItem);
		admin.add(logout1);

		frame1.setJMenuBar(menubar);
	}

	public Main() {
		loginPanel();
	}

	public static void main(String[] args) {
		new Main();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == register) {
			remove(mainPanel);
			this.setVisible(false);
			createAccount();
		}

		if (e.getSource() == submit) {

			if (nameF.getText().length() < 5 || nameF.getText().length() > 25) {
				JOptionPane.showMessageDialog(this, "Name must be between 5 and 25");
			} else if (!emailF.getText().contains("@") || !emailF.getText().endsWith(".com")) {
				JOptionPane.showMessageDialog(this, "Email must contains @ and end with .com");
			} else if (passwordF.getPassword().length < 10) {
				JOptionPane.showMessageDialog(this, "Password must be more than 10 char");
			} else if (!Arrays.equals(passwordF.getPassword(), cpasswordF.getPassword())) {
				JOptionPane.showMessageDialog(this, "Password is not same");
			} else if (!maleG.isSelected() && !femaleG.isSelected()) {
				JOptionPane.showMessageDialog(this, "Choose your gender");
			} else if (yearCMB.getSelectedIndex() > 95) {
				JOptionPane.showMessageDialog(this, "too young");
			} else if (!tAC.isSelected()) {
				JOptionPane.showMessageDialog(this, "check term & conditon box");
			} else {
				String id = con.generateID();
				String name = nameF.getText();
				String email = emailF.getText();
				String password = new String(passwordF.getPassword());
				String gender = "";
				if (maleG.isSelected()) {
					gender = "Male";
				} else {
					gender = "Female";
				}
				String DATE = yearCMB.getSelectedItem().toString() + "-" + monthCMB.getSelectedItem().toString() + "-"
						+ dayCMB.getSelectedItem().toString();
				String status = "Active";

				String query = "INSERT INTO `user` (`id`, `name`, `email`, `password`, `birthday`, `gender`, `status`) "
						+ " VALUES ('" + id + "', '" + name + "', '" + email + "', '" + password + "', '" + DATE
						+ "', '" + gender + "', '" + status + "')";
				con.executeUpdate(query);

				JOptionPane.showMessageDialog(this, "Success");
			}

		}

		else if (e.getSource() == signin) {
			name = emailField.getText();
			String password = String.valueOf(passwordField.getPassword());
			
			String query = "SELECT * FROM `user` WHERE email ='" + name + "'AND password ='" + password + "'";
			con.executeQuery(query);

			try {
				while (con.rs.next()) {
					if (con.rs.getString(3).equals(name)) {
						if (con.rs.getString(4).equals(password)) {
							if (con.rs.getString(7).equals("Admin")) {
								JOptionPane.showMessageDialog(this, "Welcome " + name);
								remove(mainPanel);
								setVisible(false);
								mainFormAdmin();
								currentUserID = con.rs.getString(1);
							} else if (con.rs.getString(7).equals("Active")) {
								JOptionPane.showMessageDialog(this, "Welcome " + name);
								remove(mainPanel);
								setVisible(false);
								mainForm();
								currentUserID = con.rs.getString(1);
							} else if(con.rs.getString(7).contentEquals("Banned")) {
								JOptionPane.showMessageDialog(this, "user banned", "User Banned", JOptionPane.ERROR_MESSAGE);
							}
							
						} else {
							JOptionPane.showMessageDialog(this, "pass doesn't match");
						}
					} else {
						JOptionPane.showMessageDialog(this, "user doesn't exist");
					}
				
				}
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		} else if (e.getSource() == back) {
			remove(mPanel);
			this.setVisible(false);
			loginPanel();
		}

		else if (e.getSource() == ourProduct) {

			formPRD = new formProduct();
			content.add(formPRD);
			frame2.setContentPane(content);
		}

		else if (e.getSource() == cart) {

			formCART = new formCart();
			content.add(formCART);
			frame2.setContentPane(content);
		}
		
		else if (e.getSource() == changePassword) {
			formCPW = new formChangePW();
			content.add(formCPW);
			frame2.setContentPane(content);
		}
		
		else if (e.getSource() == logout) {
			remove(frame2);
			frame2.setVisible(false);
			loginPanel();
		}

		

		else if (e.getSource() == manageUser) {
			formMU = new formManageUser();
			content1.add(formMU);
			frame1.setContentPane(content1);
		} 
		
		else if(e.getSource() == manageItem) {
			formMI = new formManageItem();
			content1.add(formMI);
			frame1.setContentPane(content1);
		} 
		else if(e.getSource() == logout1) {
			remove(frame1);
			frame1.setVisible(false);
			loginPanel();
		}

	}

}
