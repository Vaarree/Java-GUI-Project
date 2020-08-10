import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class formCart extends JInternalFrame implements ActionListener, MouseListener{
	Vector<Vector<Object>> tableContent;
	Vector<Object> tableRow, tableHeader;
	
	
	JPanel mainPanel, titlePanel, tablePanel1, tablePanel2, southPanel, fieldPanel, buttonPanel;
	JPanel nameLabelP,priceLabelP,quantityLabelP,txtnameP,txtpriceP,spinqtyP,deleteP,checkoP;
	
	JLabel titleLabel, nameLabel, quantityLabel, priceLabel;
	
	JLabel nametxt, pricetxt;
	
	JButton deleteButton, checkOButton;
	
	JSpinner spinner;
	
	SpinnerNumberModel spm;
	
	JTable table = new JTable();;
	
	DefaultTableModel dtm;
	
	JScrollPane scroll;
	
	Connect con = new Connect();
	
	public void addData(String id, String name, int price, int quantity) {
		tableRow = new Vector<>();
		tableRow.add(id);
		tableRow.add(name);
		tableRow.add(price);
		tableRow.add(quantity);
		tableContent.add(tableRow);
	
	}
	
	public  void viewData() {
		tableHeader = new Vector<>();
		tableContent = new Vector<>();
		
		tableHeader.add("ID");
		tableHeader.add("Name");
		tableHeader.add("Price");
		tableHeader.add("Quantity");
		
		String query = "SELECT id,name,price,productquantity FROM mycart mc JOIN products p ON mc.productid = p.id"
					 + " WHERE userid = '"+Main.currentUserID+"'";
		con.rs = con.executeQuery(query);
		
		try {
			while(con.rs.next()) {
				addData(con.rs.getString(1),con.rs.getString(2),con.rs.getInt(3),con.rs.getInt(4));
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
		
		mainPanel = new JPanel(new GridLayout());
		titlePanel = new JPanel();
		tablePanel1 = new JPanel();
		tablePanel2 = new JPanel(new BorderLayout(0,200));
		southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 60,10));
		fieldPanel = new JPanel(new GridLayout(0,2));
		buttonPanel = new JPanel(new GridLayout(2,1,0,10));
		
		nameLabelP = new JPanel(new FlowLayout(FlowLayout.LEFT,0,20));
		priceLabelP = new JPanel(new FlowLayout(FlowLayout.LEFT,0,20));
		quantityLabelP = new JPanel(new FlowLayout(FlowLayout.LEFT,0,20));
		
		txtnameP = new JPanel(new FlowLayout(FlowLayout.LEFT,30,20));
		txtpriceP = new JPanel(new FlowLayout(FlowLayout.LEFT,30,20));
		spinqtyP = new JPanel(new FlowLayout(FlowLayout.LEFT,30,20));
		
		
		titleLabel = new JLabel("Cart");
		titleLabel.setFont(new Font("Calibri", Font.BOLD, 28));
		nameLabel = new JLabel("Name");
		priceLabel = new JLabel("Price");
		quantityLabel = new JLabel("Quantity");
		nametxt = new JLabel();
		pricetxt = new JLabel();
		
		spm = new SpinnerNumberModel(0,0,9,1);
		spinner = new JSpinner(spm);
		spinner.setPreferredSize(new Dimension(100,25));
		
		deleteButton = new JButton("Delete");
		deleteButton.setPreferredSize(new Dimension(100,60));
		deleteButton.addActionListener(this);
		checkOButton = new JButton("Check Out");
		checkOButton.setPreferredSize(new Dimension(100,60));
		checkOButton.addActionListener(this);
	}
	
	public void setComp() {
		buttonPanel.add(deleteButton);
		buttonPanel.add(checkOButton);
		
		nameLabelP.add(nameLabel);
		priceLabelP.add(priceLabel);
		quantityLabelP.add(quantityLabel);
		
		txtnameP.add(nametxt);
		txtpriceP.add(pricetxt);
		spinqtyP.add(spinner);
		
		fieldPanel.add(nameLabelP);
		fieldPanel.add(txtnameP);
		fieldPanel.add(quantityLabelP);
		fieldPanel.add(spinqtyP);
		fieldPanel.add(priceLabelP);
		fieldPanel.add(txtpriceP);
		
		southPanel.add(fieldPanel);
		southPanel.add(buttonPanel);
		
		tablePanel2.add(scroll, "Center");
		tablePanel1.add(tablePanel2);
		
		mainPanel.add(tablePanel1);
		
		titlePanel.add(titleLabel);
		
		this.add(titlePanel, "North");
		this.add(mainPanel, "Center");
		this.add(southPanel, "South");
		
	}
	
	public void listener() {
		table.addMouseListener(this);
		this.addMouseListener(this);
	}
	
	public formCart() {
		
		this.setLayout(new BorderLayout());
		this.setTitle("Your cart");
		this.setSize(600, 480);
		this.setMaximizable(true);
		this.setVisible(true);
		this.setClosable(true);	
		initComp();
		setComp();
		listener();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == deleteButton) {
			String name = table.getValueAt(table.getSelectedRow(), 1).toString();
			
			String query = "DELETE FROM `mycart` WHERE productid IN(SELECT id FROM products WHERE name = '"+name+"')";
			con.executeUpdate(query);
			JOptionPane.showMessageDialog(this, "Success");
		} else if (e.getSource()==checkOButton) {
			int result = JOptionPane.showConfirmDialog(this, "are u sure?","confirm", JOptionPane.YES_NO_OPTION);
			if(result==JOptionPane.YES_OPTION) {
				String id = con.generatetransacID();
				String prodid = table.getValueAt(table.getSelectedRow(), 0).toString();
				int quan = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 4).toString());
				
				String query = "INSERT INTO `headertransaction` "
						+ "(`transactionid`, `userid`) VALUES ('"+id+"', '"+Main.currentUserID+"')";
				con.executeQuery(query);
				
				String query1 = "INSERT INTO `detailtransaction` "
						+ "(`transactionid`, `productid`, `quantity`) VALUES ('"+id+"', '"+prodid+"', '"+quan+"')";
				con.executeQuery(query1);
			}
			
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (table.getSelectedRow() != -1) {
			nametxt.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
			pricetxt.setText(table.getValueAt(table.getSelectedRow(), 2).toString());
			
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
