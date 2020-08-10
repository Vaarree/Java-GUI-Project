import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyVetoException;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.*;

public class formProduct extends JInternalFrame implements ActionListener, MouseListener {
	Vector<Vector<Object>> tableContent;
	Vector<Object> tableRow, tableHeader;
	
	JPanel mainPanel, titlePanel, tablePanel1, tablePanel2, centerPanel, 
			fieldPanel1, fieldPanel2, buttonPanel1, buttonPanel2, imagePanel;
	
	 String id,name;
	 int price,spin;
	
	
	JPanel labelid,labelname,labelprice,labelquantity,txtidP,txtnameP,txtpriceP,txtqtyP;
	
	JTable table = new JTable();
	DefaultTableModel dtm;
	JScrollPane scroll;
	
	SpinnerNumberModel sm;
	
	JSpinner spinquantity;
	
	JButton reset, cart, contoh;
	
	JLabel title, idlbl, namelbl, pricelbl, quantitylbl;
	JLabel txtId, txtName, txtPrice, txtQty;
	
	Connect con = new Connect();
	
	
	public void addData(String id, String name, String type, int price) {
		tableRow = new Vector<>();
		tableRow.add(id);
		tableRow.add(name);
		tableRow.add(type);
		tableRow.add(price);
		tableContent.add(tableRow);
	}
	
	public void viewData() {
		tableHeader = new Vector<>();
		tableContent = new Vector<>();
		
		tableHeader.add("ID");
		tableHeader.add("Name");
		tableHeader.add("Type");
		tableHeader.add("Price");
		
		String query = "SELECT * FROM products";
		con.rs = con.executeQuery(query);
		
		try {
			while(con.rs.next()) {
				addData(con.rs.getString(1), con.rs.getString(2),con.rs.getString(3),con.rs.getInt(4));
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
		
		imagePanel = new JPanel();
		
		fieldPanel1 = new JPanel(new BorderLayout());
		
		fieldPanel2 = new JPanel(new GridLayout(0,2,0,20));
		
		buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT, 40,0));
		buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.CENTER,12,40));
		
		labelid = new JPanel(new FlowLayout(FlowLayout.LEFT,50,0));
		labelprice = new JPanel(new FlowLayout(FlowLayout.LEFT,50,0));
		labelquantity = new JPanel(new FlowLayout(FlowLayout.LEFT,50,0));
		labelname = new JPanel(new FlowLayout(FlowLayout.LEFT,50,0));
		
		txtidP = new JPanel(new FlowLayout(FlowLayout.RIGHT,30,0));
		txtnameP = new JPanel(new FlowLayout(FlowLayout.RIGHT,30,0));
		txtpriceP = new JPanel(new FlowLayout(FlowLayout.RIGHT,30,0));
		txtqtyP = new JPanel(new FlowLayout(FlowLayout.RIGHT,30,0));
		
		
		title = new JLabel("Our Products");
		title.setFont(new Font("Calibri", Font.BOLD, 28));
		idlbl = new JLabel("ID");
		namelbl = new JLabel("Name");
		pricelbl = new JLabel("Price");
		quantitylbl = new JLabel("Quantity");
		
		txtId = new JLabel();
		
		txtName = new JLabel();
		
		txtPrice = new JLabel();
		
		
		
		reset = new JButton("Reset");
		reset.setPreferredSize(new Dimension(100,20));
		reset.addActionListener(this);
		cart = new JButton("Add to cart");
		cart.setPreferredSize(new Dimension(100,20));
		cart.addActionListener(this);
		contoh = new JButton("gambar");
		contoh.setPreferredSize(new Dimension(100,100));
		
		sm = new SpinnerNumberModel(0,0,10,1);
		spinquantity = new JSpinner(sm);
		spinquantity.setPreferredSize(new Dimension(100,20));
		
	}
	
	public void setComp() {
		buttonPanel2.add(reset);
		buttonPanel2.add(cart);
		
		labelid.add(idlbl);
		labelname.add(namelbl);
		labelprice.add(pricelbl);
		labelquantity.add(quantitylbl);
		
		txtidP.add(txtId);
		txtnameP.add(txtName);
		txtpriceP.add(txtPrice);
		txtqtyP.add(spinquantity);
		
		fieldPanel2.add(labelid);
		fieldPanel2.add(txtidP);
		fieldPanel2.add(labelname);
		fieldPanel2.add(txtnameP);
		fieldPanel2.add(labelprice);
		fieldPanel2.add(txtpriceP);
		fieldPanel2.add(labelquantity);
		fieldPanel2.add(txtqtyP);
		
		buttonPanel1.add(buttonPanel2);
		
		fieldPanel1.add(fieldPanel2,"Center");
		fieldPanel1.add(buttonPanel1,"South");
		
		imagePanel.add(contoh);
		
		centerPanel.add(imagePanel);
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
	
	public formProduct() {
		this.setLayout(new BorderLayout());
		this.setTitle("product");
		this.setSize(new Dimension(600,600));
		this.setMaximizable(true);
		this.setVisible(true);
		this.setClosable(true);
		initComponent();
		setComp();
		listener();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (table.getSelectedRow() != -1) {
			txtId.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
			txtName.setText(table.getValueAt(table.getSelectedRow(), 1).toString());
			txtPrice.setText(table.getValueAt(table.getSelectedRow(), 3).toString());
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == reset) {
			txtId.setText("");
			txtName.setText("");
			txtPrice.setText("");
			
			
		} else if (e.getSource() == cart) {
			spin = (int) spinquantity.getValue();
			
			
			if(txtId.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please choose product");
				if(spin < 1) {
					JOptionPane.showMessageDialog(this, "more than 1 plss");
				} 
			} else {
				id = txtId.getText();
				name = txtName.getText();
				price = Integer.parseInt(txtPrice.getText());
				
				String query = "INSERT INTO `mycart` (`userid`, `productid`, `productquantity`) "
							 + "VALUES ('"+Main.currentUserID+"', '"+id+"', '"+spin+"')";
				con.executeUpdate(query);
			}
			
			
		
		}
	}
}
