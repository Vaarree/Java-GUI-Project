import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class formManageUser extends JInternalFrame implements ActionListener, MouseListener{
	Vector<Vector<Object>> tableContent;
	Vector<Object> tableRow, tableHeader;
	
	
	JPanel mainPanel, titlePanel, tablePanel1, tablePanel2, southPanel, fieldPanel, dataPanel, buttonPanel;
	JLabel title,title1,id,name,email,status,transaction,cart;
	JLabel idL,nameL,emailL,statusL,transactionL,cartL;
	
	
	
	JButton bannedButton, unbannedButton;
	
	
	
	
	
	JTable table = new JTable();;
	
	DefaultTableModel dtm;
	
	JScrollPane scroll;
	
	Connect con = new Connect();
	
	public void addData(String id, String name, String email, 
						String birthday, String gender, String status) {
		tableRow = new Vector<>();
		tableRow.add(id);
		tableRow.add(name);
		tableRow.add(email);
		
		tableRow.add(birthday);
		tableRow.add(gender);
		tableRow.add(status);
		tableContent.add(tableRow);
	
	}
	
	public void viewData() {
		tableHeader = new Vector<>();
		tableContent = new Vector<>();
		
		tableHeader.add("ID");
		tableHeader.add("Name");
		tableHeader.add("email");
		
		tableHeader.add("birthday");
		tableHeader.add("gender");
		tableHeader.add("status");
		
		String query = "SELECT * FROM `user`";
		con.rs = con.executeQuery(query);
		
		try {
			while(con.rs.next()) {
				addData(con.rs.getString(1), con.rs.getString(2),con.rs.getString(3),
						con.rs.getString(5),con.rs.getString(6),con.rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		dtm = new DefaultTableModel(tableContent, tableHeader){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table.setModel(dtm);;
		
		table.setAutoCreateRowSorter(true);
		
		scroll = new JScrollPane(table);
		
		scroll.setPreferredSize(new Dimension(500,175));
		
	}
	
	public void initComp() {
		viewData();
		
		mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		tablePanel1 = new JPanel();
		tablePanel2 = new JPanel(new BorderLayout(0,200));
		southPanel = new JPanel(new BorderLayout());
		fieldPanel = new JPanel(new GridLayout(1,1));
		buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		dataPanel = new JPanel(new GridLayout(3, 4));
		
		title = new JLabel("Manage User");
		title1 = new JLabel("Detail User");
		
		id = new JLabel("ID");
		name = new JLabel("Name");
		email = new JLabel("Email");
		status = new JLabel("Status");
		transaction = new JLabel("Transaction");
		cart = new JLabel("Cart");
		
		idL = new JLabel("s");
		nameL = new JLabel("s");
		emailL = new JLabel("s");
		statusL = new JLabel("s");
		transactionL = new JLabel("s");
		cartL = new JLabel("s");
		
		bannedButton = new JButton("Banned");
		bannedButton.addActionListener(this);
		unbannedButton = new JButton("Unbanned");
		unbannedButton.addActionListener(this);
	}
	
	public void setComp() {
		buttonPanel.add(bannedButton);
		buttonPanel.add(unbannedButton);
		
		titlePanel.add(title1);
		
		dataPanel.add(id);
		dataPanel.add(idL);
		dataPanel.add(status);
		dataPanel.add(statusL);
		dataPanel.add(name);
		dataPanel.add(nameL);
		dataPanel.add(transaction);
		dataPanel.add(transactionL);
		dataPanel.add(email);
		dataPanel.add(emailL);
		dataPanel.add(cart);
		dataPanel.add(cartL);
		
		tablePanel2.add(scroll, "Center");
		tablePanel1.add(tablePanel2);
		
		mainPanel.add(title);
		fieldPanel.add(tablePanel1);
		
		southPanel.add(titlePanel,"North");
		southPanel.add(dataPanel,"Center");
		southPanel.add(buttonPanel,"South");
		
		this.add(mainPanel, "North");
		this.add(fieldPanel, "Center");
		this.add(southPanel, "South");
		
	}
	
	public void listener() {
		table.addMouseListener(this);
		this.addMouseListener(this);

	}
	
	public formManageUser() {
		setLayout(new BorderLayout());
		setTitle("Manage");
		setSize(600, 350);
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setVisible(true);
		initComp();
		setComp();
		listener();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String id = table.getValueAt(table.getSelectedRow(), 0).toString();
		
		if(e.getSource() == bannedButton) {
			String query = "UPDATE `user` SET `status` = 'Banned' WHERE `user`.`id` = '"+id+"'";
			con.executeUpdate(query);
			JOptionPane.showMessageDialog(this, "Success");
		} else if(e.getSource() == unbannedButton) {
			String query = "UPDATE `user` SET `status` = 'Active' WHERE `user`.`id` = '"+id+"'";
			con.executeUpdate(query);
			JOptionPane.showMessageDialog(this, "Success");
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (table.getSelectedRow() != -1) {
			idL.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
			nameL.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
			emailL.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
			statusL.setText(table.getValueAt(table.getSelectedRow(), 5).toString());
			
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
