package mypackage;

import java.sql.DriverManager;
import java.sql.*;

public class Driver {

	public static void main(String[] args) {
		try {
			
			Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstoredatabase", "root", "tempPASS1");
			
			Statement myStmt = myCon.createStatement();
			
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM books");
			
			while (myRs.next()) {
					System.out.println(myRs.getString("booktitle") + ", " + myRs.getString("ISBN"));
			}
		} catch (SQLException e){
			e.printStackTrace();
		}
		
		UserDA test = new UserDA();
		test.insertNewUser(6, "Jack", "Pearon", "jp", "nope@thisisus.com", 1);
		
		System.out.println("\nFind Customer Test:");
		//test.getCustomerInfo("nope@thisisus.com");
		
	}

}