import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Connect {

	public  Statement stmt;
	public  ResultSet rs;
	public  Connection con;


	public Connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			// Nanti prk nya diubah sesuai dengan nama db yang mau di connect
			con = DriverManager.getConnection("jdbc:mysql://localhost/project", "root", "");
			stmt = con.createStatement();

			System.out.println("Connected to the database..");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public ResultSet executeQuery(String query) {
		try {
			rs = stmt.executeQuery(query);
		} catch (Exception e) {
			System.out.println(e);
		}

		return rs;
	}

	public void executeUpdate(String query) {
		try {
			stmt.executeUpdate(query);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String generateID() {
		String genID = "";
		String query = "SELECT RIGHT (id,3) AS id FROM `user` ORDER BY id DESC limit 1";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("id")) + 1;
				if (id < 10) {
					genID = "US00" + Integer.toString(id);
				} else if (id < 100 && id > 9) {
					genID = "US0" + Integer.toString(id);
				} else if (id < 1000 && id > 99) {
					genID = "US" + Integer.toString(id);
				} else if (id > 999) {
					System.out.println("Full");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return genID;
	}

	public String generateitemID() {
		String genID = "";
		String query = "SELECT RIGHT (id,3) AS id FROM `products` ORDER BY id DESC limit 1";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("id")) + 1;
				if (id < 10) {
					genID = "PR00" + Integer.toString(id);
				} else if (id < 100 && id > 9) {
					genID = "PR0" + Integer.toString(id);
				} else if (id < 1000 && id > 99) {
					genID = "PR" + Integer.toString(id);
				} else if (id > 999) {
					System.out.println("Full");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return genID;
	
	}

	public String generatetransacID() {
		String genID = "";
		String query = "SELECT RIGHT (transactionid,3) AS transactionid FROM `headertransaction` ORDER BY transactionid DESC limit 1";
		try {
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				int id = Integer.parseInt(rs.getString("transactionid")) + 1;
				if (id < 10) {
					genID = "TI00" + Integer.toString(id);
				} else if (id < 100 && id > 9) {
					genID = "TI0" + Integer.toString(id);
				} else if (id < 1000 && id > 99) {
					genID = "TI" + Integer.toString(id);
				} else if (id > 999) {
					System.out.println("Full");
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return genID;
	
	}

}