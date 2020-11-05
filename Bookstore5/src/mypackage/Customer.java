package mypackage;

enum AccountState {
	Inactive,
	Active,
	Suspended
}

public class Customer extends User {
	
	private String shippingstreet, shippingcity, shippingstate, shippingzipcode;
	private AccountState accountstate;
	private PaymentCard[] paymentcards = new PaymentCard[3];
	
	/**
     * A Customer Constructor. 
     * Used to set the userid, name, password, and email of the Customer
     */
	public Customer(int userid, String firstname, String lastname, String password, String email) {
		super(userid, firstname, lastname, password, email, AccountType.CUSTOMER);
	}
	
	public Customer(User user) {
		super(user);
	}
	
	/**
     * This method is not really used. 
     */
	public static void main(String[] args) {
	}
	
	/**
     * This method is used to set a Customer's shipping address all at once.
     * @param shippingstreet the street address that you want to set
     * @param city the city that you want to set
     * @param shippingstate the state in the shipping address that you want to set
     * @param shippingzipcode the zip code in the shipping address that you want to set
     */
	public void setShippingaddress(String shippingstreet, String shippingcity, String shippingstate, String shippingzipcode) {
		this.shippingstreet = shippingstreet;
		this.shippingcity = shippingcity;
		this.shippingstate = shippingstate;
		this.shippingzipcode = shippingzipcode;
	}
	
	/**
     * This method is used to get a Customer's street address.
     */
	public String getShippingstreet() {
		return shippingstreet;
	}

	/**
     * This method is used to get a Customer's city.
     */
	public String getShippingcity() {
		return shippingcity;
	}

	/**
     * This method is used to get a Customer's state in the shipping address.
     */
	public String getShippingstate() {
		return shippingstate;
	}

	/**
     * This method is used to get a Customer's zip code in their shipping address.
     */
	public String getShippingzipcode() {
		return shippingzipcode;
	}

	/**
     * This method is used to get whether a customer's account is currently active, inactive, or suspended.
     */
	public AccountState getAccountstate() {
		return accountstate;
	}

	/**
     * This method is used to set whether a customer's account is currently active, inactive, or suspended.
     * @param accountstate the account state that you want to set
     */
	public void setAccountstate(AccountState accountstate) {
		this.accountstate = accountstate;
	}

	/**
     * This method is used to get one of the customer's payment cards.
     * @param index the index location in the Payment Card array
     */
	public PaymentCard getPaymentCards(int index) {
		return paymentcards[index];
	}
	
	/**
     * This method is used to set one of the customer's payment cards.
     * @param card the card that you want to store for this customer
     * @param index the index location of where you want to store the card in the array
     */
	public void setPaymentCards(PaymentCard card, int index) {
		paymentcards[index] = card;
	}
	
	
}