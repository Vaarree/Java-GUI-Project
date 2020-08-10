import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

public class formChangePW extends JInternalFrame implements ActionListener{

	JPanel mainPanel,titlePanel,fieldPanel,buttonPanel;
	JPanel opP,npP,cfP,topP,tnpP,tcfP;
	
	JLabel title,oldPass,newPass,confNew;
	
	JPasswordField txtop,txtnp,txtcf;
	
	JButton update;
	
	Connect con;
	
	public void initComp() {
		mainPanel = new JPanel(new BorderLayout());
		titlePanel = new JPanel();
		fieldPanel = new JPanel(new GridLayout(0,2));
		buttonPanel = new JPanel();
		
		opP = new JPanel(new FlowLayout(FlowLayout.LEFT,20,13));
		npP = new JPanel(new FlowLayout(FlowLayout.LEFT,20,13));
		cfP = new JPanel(new FlowLayout(FlowLayout.LEFT,20,13));
		topP = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,10));
		tnpP = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,10));
		tcfP = new JPanel(new FlowLayout(FlowLayout.RIGHT,20,10));
		
		title = new JLabel("Change Password");
		title.setFont(new Font("Calibri", Font.BOLD,32));
		oldPass = new JLabel("Enter old password");
		newPass = new JLabel("Enter new password");
		confNew = new JLabel("Confirm new password");
		
		txtop = new JPasswordField();
		txtnp = new JPasswordField();
		txtcf = new JPasswordField();
		txtop.setPreferredSize(new Dimension (200,25));
		txtnp.setPreferredSize(new Dimension (200,25));
		txtcf.setPreferredSize(new Dimension (200,25));
		
		update = new JButton("Update");
		update.addActionListener(this);
	}
	
	public void setComp() {
		opP.add(oldPass);
		npP.add(newPass);
		cfP.add(confNew);
		topP.add(txtop);
		tnpP.add(txtnp);
		tcfP.add(txtcf);
		
		
		fieldPanel.add(opP);
		fieldPanel.add(topP);
		fieldPanel.add(npP);
		fieldPanel.add(tnpP);
		fieldPanel.add(cfP);
		fieldPanel.add(tcfP);
		
		buttonPanel.add(update);
		
		titlePanel.add(title);
		
		mainPanel.add(titlePanel,"North");
		mainPanel.add(fieldPanel,"Center");
		mainPanel.add(buttonPanel,"South");
		
		this.add(mainPanel);
	}
	
	
	public formChangePW() {
	
		this.setLayout(new BorderLayout());
		this.setTitle("Setting");
		this.setSize(500, 300);
		this.setMaximizable(true);
		this.setVisible(true);
		this.setClosable(true);	
		initComp();
		setComp();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == update) {
			con = new Connect();
			
			String oldPass = String.valueOf(txtop.getPassword());
			String newPass = String.valueOf(txtnp.getPassword());
			String cfPass = String.valueOf(txtcf.getPassword());
			if(newPass.equals(cfPass)) {
			String query = "UPDATE `user` SET `password` = '"+newPass+"' WHERE `user`.`id` = '"+Main.currentUserID+"'";
			con.executeUpdate(query);
			JOptionPane.showMessageDialog(this, "Success");
			} else {
				JOptionPane.showMessageDialog(this, "new password wrong");
			}
			
		
		}
	}

}
