package mypackage;

import java.sql.*;
import java.security.NoSuchAlgorithmException;
import org.jasypt.encryption.pbe.*;
import org.jasypt.iv.RandomIvGenerator;

/**
 * Class used to access or insert information related to Users, Customers, and Admins.
 */
public class UserDA {
	private static final String databaseaddress = "jdbc:mysql://localhost:3306/bookstoredatabase";
	private static final String databaseusername = "root";
	private static final String databasepassword = "Hyft7L5vP!p";
	private static final String key = "$Sx@t>1%]wR>GZm";
	
	/**
     * This method is not really used. 
     */
	public static void main(String[] args) {
	}
	
	/**
     * This method is used to test, simply prints out the word hi
     */
	public void printHi() {
		System.out.println("hi");
	}
	
	/**
     * This method is used to obtain an existing User in the database.
     * @param email The email of the user that you wish to obtain
     */
	public User getUser(String email) {
		User user = null;
		try {
			Connection myCon = DriverManager.getConnection(databaseaddress, databaseusername, databasepassword);
			
			Statement myStmt = myCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM users \r\n"
					+ "WHERE email = '" + email + "';");
			
			myRs.first();
			String decrypedpassword = null;
			int userid = Integer.parseInt(myRs.getString("userID"));
			int accounttype = Integer.parseInt(myRs.getString("accountType"));
			
			try {
				decrypedpassword = decrypt(myRs.getString("password"));
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			
			if (accounttype == 1) {
				Customer customer = new Customer(userid, myRs.getString("firstName"), myRs.getString("lastName"), decrypedpassword, myRs.getString("email"));
				user = customer;
			}
				
			if (accounttype == 2) {
				Admin admin = new Admin(userid, myRs.getString("firstName"), myRs.getString("lastName"), decrypedpassword, myRs.getString("email"));
				user = admin;
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/**
     * This method is used to insert a new user into the database. A Exception will be thrown if customer already exists in database.
     * @param userid The userID of the user that you wish to add
     * @param firstName The firstName of the user that you wish to add
     * @param lastName The lastName of the user that you wish to add
     * @param password The password of the user that you wish to add
     * @param email The email of the user that you wish to add
     * @param accounttype The account type of the user that you wish to add (1 = Customer, 2 = Admin)
     */
	public void insertNewUser(int userid, String firstName, String lastName, String password, String email, int accounttype) {
		try {
			Connection myCon = DriverManager.getConnection(databaseaddress, databaseusername, databasepassword);
			
			Statement myStmt = myCon.createStatement();

			try {
				myStmt.executeUpdate("INSERT INTO `bookstoredatabase`.`users` (`userID`, `firstName`, `lastName`, `password`, `email`, `accountType`) "
						+ "VALUES ('"+ userid + "', '" + firstName + "', '" + lastName + "', '"+ encrypt(password) +"', '" + email + "', '" + accounttype + "'); ");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
					
			if (accounttype == 1) {
				myStmt.executeUpdate("INSERT INTO `bookstoredatabase`.`customer` (`userID`, `accountState`) VALUES ('" + userid + "', '1');");
			}
			
			if (accounttype == 2) {
				myStmt.executeUpdate("INSERT INTO `bookstoredatabase`.`admin` (`userID`) VALUES ('" + userid + "');");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * This method is used to update a user's name in the database.
     * @param userid The userID of the user that you wish to update
     */
	public void updateName(int userid, String firstname, String lastname) {
		try {
			Connection myCon = DriverManager.getConnection(databaseaddress, databaseusername, databasepassword);
			
			Statement myStmt = myCon.createStatement();

			myStmt.executeUpdate("UPDATE `bookstoredatabase`.`users` SET `firstName` = '"+ firstname +"', `lastName` = '" + lastname + "' "
					+ "WHERE (`userID` = '"+ userid +"');");
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * This method is used to check if there is an existing User in the database.
     * @param email The email of the user that you wish to check
     * @return boolean True if there are is a user with that email, False otherwise
     */
	public boolean checkForExisting(String email) {
		int results;
		try {
			Connection myCon = DriverManager.getConnection(databaseaddress, databaseusername, databasepassword);
			
			Statement myStmt = myCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM users WHERE email = '" + email + "';");
			
			myRs.last(); // jump to last row of result set
			results = myRs.getRow(); // get row number of last row
			
			if (results > 0) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false; 
	}
	
	/**
     * This method is used to update the Shipping address of an existing Customer.
     * @param userid The userID of the user that you wish to update
     * @param streetAddress The streetAddress of the user that you wish to update
     * @param city The city of the user that you wish to update
     * @param shippingState The shippingState of the user that you wish to update
     * @param zipCode The zipCode of the user that you wish to update
     */
	public void updateCustomerShipping(int userid, String streetAddress, String city, String shippingState, int zipCode) {
		try {
			Connection myCon = DriverManager.getConnection(databaseaddress, databaseusername, databasepassword);
			
			Statement myStmt = myCon.createStatement();
			
			myStmt.executeUpdate("UPDATE `bookstoredatabase`.`customer` "
					+ "SET `streetaddress` = '" + streetAddress + "', `city` = '" + city + "', `shippingState` = '" + shippingState + "', `zipcode` = '" + zipCode + "' "
					+ "WHERE (`userID` = '" + userid + "');");
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * This method is used to obtain the Shipping address of an existing Customer.
     * @param customer The Customer that you wish to get the shipping info & account info of
     */
	public void getCustomerInfo(Customer customer) {
		try {
			Connection myCon = DriverManager.getConnection(databaseaddress, databaseusername, databasepassword);
			
			Statement myStmt = myCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM customer "
					+ "WHERE userID = '" + customer.getUserid() + "';");
			
			myRs.first();
			int accountstate = Integer.parseInt(myRs.getString("accountState"));
			
			customer.setShippingaddress(myRs.getString("streetaddress"), myRs.getString("city"), myRs.getString("shippingState"), myRs.getString("zipcode"));
			
			if (accountstate == 1) {
				customer.setAccountstate(AccountState.Inactive);
			}
			else if (accountstate == 2) {
				customer.setAccountstate(AccountState.Active);
			}
			else{
				customer.setAccountstate(AccountState.Suspended);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * This method is used to update the password of an existing Customer.
     * @param userid The userID of the user that you wish to update
     * @param password The new password of the user that you wish to update to
     */
	public void updateUserPassword(int userid, String password){
		try {
			Connection myCon = DriverManager.getConnection(databaseaddress, databaseusername, databasepassword);
			
			Statement myStmt = myCon.createStatement();
			
			try {
				myStmt.executeUpdate("UPDATE `bookstoredatabase`.`users` SET `password` = '" + encrypt(password) + "' WHERE (`userID` = '" + userid + "');");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * This method is used to update the accountState of an existing Customer.
     * @param userid The userID of the user that you wish to update
     * @param accountState The new accountState of the user that you wish to update to
     */
	public void updateCustomerStatus(int userid, AccountState accountState) {
		try {
			Connection myCon = DriverManager.getConnection(databaseaddress, databaseusername, databasepassword);
			
			Statement myStmt = myCon.createStatement();
			
			if (accountState == AccountState.Inactive) {
				myStmt.executeUpdate("UPDATE `bookstoredatabase`.`customer` SET `accountState` = '1' WHERE (`userID` = '" + userid + "');");
			}
			else if (accountState == AccountState.Active){
				myStmt.executeUpdate("UPDATE `bookstoredatabase`.`customer` SET `accountState` = '2' WHERE (`userID` = '" + userid + "');");
			}
			else {
				myStmt.executeUpdate("UPDATE `bookstoredatabase`.`customer` SET `accountState` = '3' WHERE (`userID` = '" + userid + "');");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * This method is used to check the number of payment cards stored for a User.
     * @param userid The userID of the user that you wish to check
     * @return int number of cards that the User has stored
     */
	public int checkForCardNum(int userid) {
		int num = 0;
		try {
			Connection myCon = DriverManager.getConnection(databaseaddress, databaseusername, databasepassword);
			
			Statement myStmt = myCon.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM paymentcard WHERE customerID = '" + userid + "';");
			
			myRs.last(); // jump to last row of result set
			num = myRs.getRow(); // get row number of last row
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return num;
	}
	
	/**
     * This method is used to add an card associated with an existing Customer. 
     * If customer already has a 3 cards stored, then nothing is added.
     * Exception is thrown if there is a card with the exact same card number stored. 
     * @param paymentcardid The payment card id of the payment card you wish to add
     * @param userid The userID of the user that you wish to associate the payment card with
     * @param paymentcardno The Card Number of the card that you wish to add (format: 16 Digits, no spaces, no dashes)
     * @param paymentcardtype The Card Type of the card that you wish to add (Ex. 1 = Visa, 2 = MasterCard)
     * @param nameoncard The Name on the card that you wish to add
     * @param expirationmonth The expiration month on the card that you wish to add
     * @param expirationyear The expiration year on the card that you wish to add
     * @param billingstreet The street of the Billing Address associated with the card that you wish to add
     * @param billingcity The city of the Billing Address associated with the card that you wish to add
     * @param billingstate The state of the Billing Address associated with the card that you wish to add
     * @param billingzipcode The zip code of the Billing Address associated with the card that you wish to add
     */
	public void addPaymentCard(int paymentcardid, int userid, String paymentcardno, CardType paymentcardtype, String nameoncard, int expirationmonth, int expirationyear, String billingstreet, String billingcity, String billingstate, String billingzipcode) {
		try {
			Connection myCon = DriverManager.getConnection(databaseaddress, databaseusername, databasepassword);
			
			Statement myStmt = myCon.createStatement();
			
			String encryptedCN = null;
			int cardtype = -1;
			
			try {
				encryptedCN = encrypt(paymentcardno);
			}catch(NoSuchAlgorithmException e){
				e.printStackTrace();
			}
			
			if (paymentcardtype == CardType.VISA) {
				cardtype = 1;
			}
			else {
				cardtype = 2;
			}
			
			if (checkForCardNum(userid) < 3) {
				myStmt.executeUpdate("INSERT INTO `bookstoredatabase`.`paymentcard` (`paymentCardID`, `paymentCardNo`, `paymentCardType`, `nameOnCard`, `expirationDate`, `customerID`, `billingStreet`, `billingCity`, `billingState`, `billingZIPCode`) "
						+ "VALUES ('" + paymentcardid + "', '" + encryptedCN + "', '" + cardtype + "', '" + nameoncard + "', '" + expirationyear + "-" + expirationmonth + "-00', '" 
						+ userid + "', '" + billingstreet + "', '" + billingcity + "', '" + billingstate + "', '" + billingzipcode + "');");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * This method is used to remove an card associated with an existing Customer. 
     * @param paymentcardid The Card id of the card that you wish to remove from the Database
     * @param customerid The associated customer of the card that you wish to remove from the Database
     */
	public void removePaymentCard(int paymentcardid, int customerid) {
		try {
			Connection myCon = DriverManager.getConnection(databaseaddress, databaseusername, databasepassword);
			
			Statement myStmt = myCon.createStatement();
			
			myStmt.executeUpdate("DELETE FROM `bookstoredatabase`.`paymentcard` WHERE (`paymentCardID` = '"+ paymentcardid +"') and (`customerID` = '"+ customerid +"');");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * This method is used to update an card's billing address. 
     * @param paymentcardid The Card id of the card that you wish to update in the Database
     * @param customerid The associated customer of the card that you wish to update in the Database
     * @param billingstreet The street of the Billing Address associated with the card that you wish to update to
     * @param billingcity The city of the Billing Address associated with the card that you wish to update to
     * @param billingstate The state of the Billing Address associated with the card that you wish to update to
     * @param billingzipcode The zip code of the Billing Address associated with the card that you wish to update to
     */
	public void updateCardBillingAddress(int paymentcardid, int customerid, String billingstreet, String billingcity, String billingstate, String billingzipcode) {
		try {
			Connection myCon = DriverManager.getConnection(databaseaddress, databaseusername, databasepassword);
			
			Statement myStmt = myCon.createStatement();
			
			myStmt.executeUpdate("UPDATE `bookstoredatabase`.`paymentcard` "
					+ "SET `billingStreet` = '" + billingstreet + "', `billingCity` = '" + billingcity + "', `billingState` = '" + billingstate + "', `billingZIPCode` = '" + billingzipcode + "' "
					+ "WHERE (`paymentCardID` = '" + paymentcardid + "') and (`customerID` = '" + customerid + "');");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
     * This method is used to retrieve cards associated with an existing Customer. 
     * @param userid The User that you want to retrieve payment cards for
     */
	public void getPaymentCards(Customer customer) {
		PaymentCard cards;
		try {
			Connection myCon = DriverManager.getConnection(databaseaddress, databaseusername, databasepassword);
			
			Statement myStmt = myCon.createStatement();
			
			ResultSet myRs = myStmt.executeQuery("SELECT * FROM paymentcard "
					+ "WHERE customerID = '" + customer.getUserid() + "';");
			
			while (myRs.next()) {
				String decryptedcardno = null;
				int expiryear = Integer.parseInt(myRs.getString("expirationDate").substring(0, 4));
				int expirmonth = Integer.parseInt(myRs.getString("expirationDate").substring(5, 7));
				int retrievedcardtype = Integer.parseInt(myRs.getString("paymentCardType"));
				CardType cardtype = null;
				
				if (retrievedcardtype == 1) {
					cardtype = CardType.VISA;
				}
				else {
					cardtype = CardType.MASTERCARD;
				}
				
				try {
					decryptedcardno = decrypt(myRs.getString("paymentCardNo"));
				} catch (NoSuchAlgorithmException e){
					e.printStackTrace();
				}
				cards = new PaymentCard(decryptedcardno, expiryear, expirmonth, myRs.getString("billingStreet"), myRs.getString("billingCity"), myRs.getString("billingState"), myRs.getString("billingZIPCode"), cardtype);
				
				int cardID = Integer.parseInt(myRs.getString("paymentCardID"));
				customer.setPaymentCards(cards, cardID - 1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to encrypt a string passed as a parameter
	 * @param rawString string that needs to be encrypted
	 */
	private String encrypt(String rawString) throws NoSuchAlgorithmException{
		//create basic text encryptor object
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		
		//set algorithm to be used
		String algorithm = "PBEWithHMACSHA256AndAES_128";
		
		//generate key for encryptor
		//String key = generateKey("AES").toString();
		
		//configure encryptor
		encryptor.setPassword(key);
		encryptor.setAlgorithm(algorithm);			
		encryptor.setIvGenerator(new RandomIvGenerator());
		
		//encrypt string
		String encryptedString = encryptor.encrypt(rawString);
		return encryptedString;
		
	}
	
	/**
	 * This method is used to decrypt a string passed as a parameter.
	 * @param rawString The string to be decrypted
	 * @throws NoSuchAlgorithmException when a encryption algorithm is requested but not found
	 */
	private String decrypt(String rawString) throws NoSuchAlgorithmException{
		//create basic text decryptor object
		StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
					
		//set algorithm to be used
		String algorithm = "PBEWithHMACSHA256AndAES_128";
					
		//generate key for decryptor
		//String key = generateKey("AES").toString();
					
		//configure decryptor
		decryptor.setPassword(key);
		decryptor.setAlgorithm(algorithm);			
		decryptor.setIvGenerator(new RandomIvGenerator());
		
					
		//decrypt string
		String decryptedString = decryptor.decrypt(rawString);
		return decryptedString;
	}

}