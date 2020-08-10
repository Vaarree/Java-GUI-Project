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
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class formManageItem extends JInternalFrame implements ActionListener,MouseListener{
	Vector<Vector<Object>> tableContent;
	Vector<Object> tableRow, tableHeader;
	
	JPanel mainPanel, titlePanel, tablePanel1, tablePanel2, centerPanel, 
			fieldPanel1, fieldPanel2, buttonPanel1, buttonPanel2;
	
	
	JPanel labelid,labelname,labelprice,labelcategory,txtidP,txtnameP,txtpriceP,txtcategoryP;
	
	JTable table = new JTable();
	DefaultTableModel dtm;
	JScrollPane scroll;
	
	JButton reset, insert, update, delete;
	
	JLabel title, idlbl, namelbl, pricelbl, categorylbl;
	JTextField txtId, txtName, txtPrice, txtcategory;
	
	Connect con = new Connect();
	
	public void addData(String id, String name, int price, String type) {
		tableRow = new Vector<>();
		tableRow.add(id);
		tableRow.add(name);
		tableRow.add(price);
		tableRow.add(type);
		
		tableContent.add(tableRow);
	}
	
	public void viewData() {
		tableHeader = new Vector<>();
		tableContent = new Vector<>();
		
		tableHeader.add("ID");
		tableHeader.add("Name");
		tableHeader.add("Price");
		tableHeader.add("Category");
		
		
		String query = "SELECT * FROM products";
		con.rs = con.executeQuery(query);
		
		try {
			while(con.rs.next()) {
				addData(con.rs.getString(1), con.rs.getString(2),con.rs.getInt(4),con.rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		dtm = new DefaultTableModel(tableContent, tableHeader){
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		table.setModel(dtm);
		
		table.setAutoCreateRowSorter(true);
		
		scroll = new JScrollPane(table);
		
		scroll.setPreferredSize(new Dimension(500,250));
	}
	
	public void initComponent() {
		viewData();
		
	
		
		mainPanel = new JPanel(new GridLayout(1, 1));
		
		titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		tablePanel1 = new JPanel();
		tablePanel2 = new JPanel(new BorderLayout());
		
		centerPanel = new JPanel(new GridLayout(1,1));
		centerPanel.setPreferredSize(new Dimension(300,250));
		
		
		
		fieldPanel1 = new JPanel(new BorderLayout());
		
		fieldPanel2 = new JPanel(new GridLayout(0,2,0,20));
		
		buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 40,0));
		buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER,12,40));
		
		labelid = new JPanel(new FlowLayout(FlowLayout.LEFT,50,0));
		labelprice = new JPanel(new FlowLayout(FlowLayout.LEFT,50,0));
		labelcategory = new JPanel(new FlowLayout(FlowLayout.LEFT,50,0));
		labelname = new JPanel(new FlowLayout(FlowLayout.LEFT,50,0));
		
		txtidP = new JPanel(new FlowLayout(FlowLayout.RIGHT,30,0));
		txtnameP = new JPanel(new FlowLayout(FlowLayout.RIGHT,30,0));
		txtpriceP = new JPanel(new FlowLayout(FlowLayout.RIGHT,30,0));
		txtcategoryP = new JPanel(new FlowLayout(FlowLayout.RIGHT,30,0));
		
		
		title = new JLabel("Our Products");
		title.setFont(new Font("Calibri", Font.BOLD, 28));
		idlbl = new JLabel("ID");
		namelbl = new JLabel("Name");
		pricelbl = new JLabel("Price");
		categorylbl = new JLabel("Category");
		
		txtId = new JTextField();
		txtId.setPreferredSize(new Dimension(200, 20));
		txtName = new JTextField();
		txtName.setPreferredSize(new Dimension(200, 20));
		txtcategory = new JTextField();
		txtcategory.setPreferredSize(new Dimension(200, 20));
		txtPrice = new JTextField();
		txtPrice.setPreferredSize(new Dimension(200, 20));
		
		
		reset = new JButton("Reset");
		reset.setPreferredSize(new Dimension(100,20));
		reset.addActionListener(this);
		insert = new JButton("Insert");
		insert.setPreferredSize(new Dimension(100,20));
		insert.addActionListener(this);
		update = new JButton("Update");
		update.setPreferredSize(new Dimension(100,20));
		update.addActionListener(this);
		delete = new JButton("Delete");
		delete.setPreferredSize(new Dimension(100,20));
		delete.addActionListener(this);
		
	}
	
	public void setComp() {
		buttonPanel2.add(reset);
		buttonPanel2.add(insert);
		buttonPanel2.add(update);
		buttonPanel2.add(delete);
		
		labelid.add(idlbl);
		labelname.add(namelbl);
		labelprice.add(pricelbl);
		labelcategory.add(categorylbl);
		
		txtidP.add(txtId);
		txtnameP.add(txtName);
		txtpriceP.add(txtPrice);
		txtcategoryP.add(txtcategory);
		
		fieldPanel2.add(labelid);
		fieldPanel2.add(txtidP);
		fieldPanel2.add(labelname);
		fieldPanel2.add(txtnameP);
		fieldPanel2.add(labelprice);
		fieldPanel2.add(txtpriceP);
		fieldPanel2.add(labelcategory);
		fieldPanel2.add(txtcategoryP);
		
		buttonPanel1.add(buttonPanel2);
		
		fieldPanel1.add(fieldPanel2,"Center");
		fieldPanel1.add(buttonPanel1,"South");
		
		
		
		
		centerPanel.add(fieldPanel1);
		
		
		tablePanel2.add(scroll, "Center");
		
		titlePanel.add(title);
		
		
		tablePanel1.add(tablePanel2);
		


		mainPanel.add(tablePanel1);
		
		this.add(titlePanel, "North");
		this.add(mainPanel,"Center");
		this.add(centerPanel, "South");
	}
	
	public void listener() {
		table.addMouseListener(this);
		this.addMouseListener(this);

	}
	
	public formManageItem() {
		setLayout(new BorderLayout());
		setTitle("Manage");
		setSize(600, 600);
		setResizable(true);
		setMaximizable(true);
		setClosable(true);
		setVisible(true);
		initComponent();
		setComp();
		listener();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == reset) {
			String query = "DELETE FROM `products`";
			con.executeUpdate(query);
			JOptionPane.showMessageDialog(this, "Success");
		} else if(e.getSource() == update) {
			String id = table.getValueAt(table.getSelectedRow(), 0).toString();
			
			if (txtName.getText().length()>50) {
				JOptionPane.showMessageDialog(this, "Name length must not more than 50");
			} else if (!txtcategory.getText().equals("Food") && !txtcategory.getText().equals("Drink")) {
				JOptionPane.showMessageDialog(this, "Category must be \"Food\" or \"Drink\"");
			} else if (!txtPrice.getText().matches("[0-9]+")) {
				JOptionPane.showMessageDialog(this, "must be number");
			
			} else {
				
				String name = txtName.getText();
				String category = txtcategory.getText();
				String price = txtPrice.getText();
				
				
				String query = "UPDATE `products` SET `name` = '"+name+"', `category` = '"+category+"', `price` = '"+price+"' "
							 + "WHERE `products`.`id` = '"+id+"'";
				
				con.executeUpdate(query);

				JOptionPane.showMessageDialog(this, "Success");
			}
			
		} else if(e.getSource() == insert) {
			if (txtId.getText().length() > 5 || !txtId.getText().startsWith("PR")) {
				JOptionPane.showMessageDialog(this, "ID length must not more than 5 and starts with\"PR\"");
			} else if (txtName.getText().length()>50) {
				JOptionPane.showMessageDialog(this, "Name length must not more than 50");
			} else if (!txtcategory.getText().equals("Food") && !txtcategory.getText().equals("Drink")) {
				JOptionPane.showMessageDialog(this, "Category must be \"Food\" or \"Drink\"");
			} else if (!txtPrice.getText().matches("[0-9]+")) {
				JOptionPane.showMessageDialog(this, "must be number");
			
			} else {
				String id = con.generateitemID();
				String name = txtName.getText();
				String category = txtcategory.getText();
				String price = txtPrice.getText();
				
				
				String query = "INSERT INTO `products` (`id`, `name`, `category`, `price`) "
							 + "VALUES ('"+id+"', '"+name+"', '"+category+"', "+price+")";
				
				con.executeUpdate(query);

				JOptionPane.showMessageDialog(this, "Success");
			}
		} else if(e.getSource() == delete) {
			String id = table.getValueAt(table.getSelectedRow(), 0).toString();
			
			String query = "DELETE FROM `products` WHERE `products`.`id` = '"+id+"'";;
			con.executeUpdate(query);
			
			JOptionPane.showMessageDialog(this, "Success");
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (table.getSelectedRow() != -1) {
			txtId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
			txtName.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
			txtPrice.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
			txtcategory.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
			
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
