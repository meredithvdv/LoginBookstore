package mypackage;

public class Admin extends User {

	/**
     * A Admin Constructor. 
     * Used to set the userid, name, password, and email of the Admin
     */
	public Admin(int userid, String firstname, String lastname, String password, String email) {
		super(userid, firstname, lastname, password, email, AccountType.ADMIN);
	}
	
	public Admin(User user) {
		super(user);
	}
	
	/**
     * This method is not really used. 
     */
	public static void main(String[] args) {

	}

	/*
	 * ManageBooks()
	 * ManageCustomers()
	 * ManagePromos()
	 */
}