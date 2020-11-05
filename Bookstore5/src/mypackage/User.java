package mypackage;

enum AccountType{
	CUSTOMER,
	ADMIN
}

public abstract class User {
	
	private int userid;
	private String firstname, lastname, password, email;
	private AccountType type;
	
	/**
     * A abstract user constructor
     */
	public User(int userid, String firstname, String lastname, String password, String email, AccountType type) {
		this.userid = userid;
		this.firstname = firstname;
		this.lastname = lastname;
		this.password = password;
		this.email = email;
		this.type = type;
	}
	
	/**
     * A copy constructor
     */
	public User(User user) {
		this.userid = user.userid;
		this.firstname = user.firstname;
		this.lastname = user.lastname;
		this.password = user.password;
		this.email = user.email;
		this.type = user.type;
	}

	/**
     * This method is not really used. 
     */
	public static void main(String[] args) {	
	}
	
	/**
     * This method is used to obtain a user's userid.
     */
	public int getUserid() {
		return userid;
	}

	/**
     * This method is used to set a user's userid.
     * @param userid the userid that you want to set
     */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
     * This method is used to get a user's first name.
     */
	public String getFirstName() {
		return firstname;
	}

	/**
     * This method is used to set a user's first name.
     * @param firstname the firstname that you want to set
     */
	public void setFirstName(String firstname) {
		this.firstname = firstname;
	}

	/**
     * This method is used to get a user's last name.
     */
	public String getLastName() {
		return lastname;
	}

	/**
     * This method is used to set a user's last name.
     * @param lastname the last name that you want to set
     */
	public void setLastName(String lastname) {
		this.lastname = lastname;
	}

	/**
     * This method is used to get a user's password.
     */
	public String getPassword() {
		return password;
	}

	/**
     * This method is used to set a user's password.
     * @param password the password that you want to set
     */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
     * This method is used to get a user's email.
     */
	public String getEmail() {
		return email;
	}

	/**
     * This method is used to set a user's email.
     * @param email the email that you want to set
     */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
     * This method is used to get a user's account type.
     */
	public AccountType getType() {
		return type;
	}

	/**
     * This method is used to set a user's account type.
     * @param type the account type that you want to set
     */
	public void setType(AccountType type) {
		this.type = type;
	}

}