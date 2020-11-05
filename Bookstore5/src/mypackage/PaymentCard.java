package mypackage;

enum CardType{
	VISA,
	MASTERCARD
}

public class PaymentCard {

	private int expirationYear, expirationMonth;
	private String cardNo, billingStreet, billingCity, billingState, billingZipCode;
	private CardType type;
	
	/**
     * A Payment Card Constructor. 
     * Used to set the card number, expiration date, billing address, and card type of the Card.
     */
	public PaymentCard(String cardNo, int expirationYear, int expirationMonth, String billingStreet, String billingCity, String billingState, String billingZipCode, CardType type) {
		this.cardNo = cardNo;
		this.expirationYear = expirationYear;
		this.expirationMonth = expirationMonth;
		this.billingStreet = billingStreet;
		this.billingCity = billingCity;
		this.billingState = billingState;
		this.billingZipCode = billingZipCode;
		this.type = type;
	}
	
	/**
     * This method is not really used. 
     */
	public static void main(String[] args) {
	}

	/**
     * This method is used to get the card's number.
     */
	public String getCardNo() {
		return cardNo;
	}

	/**
     * This method is used to set the card's number.
     * @param cardNo the number that you want to set for this card
     */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	/**
     * This method is used to get the card's expiration year.
     */
	public int getExpirationYear() {
		return expirationYear;
	}

	/**
     * This method is used to set the card's expiration year.
     * @param expirationYear the expiration year that you want to set for this card
     */
	public void setExpirationYear(int expirationYear) {
		this.expirationYear = expirationYear;
	}

	/**
     * This method is used to get the card's expiration month.
     */
	public int getExpirationMonth() {
		return expirationMonth;
	}

	/**
     * This method is used to get the card's expiration month.
     * @param expirationMonth the expiration month that you want to set for this card
     */
	public void setExpirationMonth(int expirationMonth) {
		this.expirationMonth = expirationMonth;
	}

	/**
     * This method is used to set a Card's billing info.
     * @param billingStreet the street address that you want to set
     * @param billingCity the city that you want to set
     * @param billingState the state in the shipping address that you want to set
     * @param billingZipCode the zip code in the billing address that you want to set
     */
	public void setBillingAddress(String billingStreet, String billingCity, String billingState, String billingZipCode) {
		this.billingStreet = billingStreet;
		this.billingCity = billingCity;
		this.billingState = billingState;
		this.billingZipCode = billingZipCode;
	}
	
	/**
     * This method is used to get the billing street address associated with the card.
     */
	public String getBillingStreet() {
		return billingStreet;
	}

	/**
     * This method is used to get the billing city associated with the card.
     */
	public String getBillingCity() {
		return billingCity;
	}
	
	/**
     * This method is used to get the billing state associated with the card.
     */
	public String getBillingState() {
		return billingState;
	}
	
	/**
     * This method is used to get the billing ZIP Code associated with the card.
     */
	public String getBillingZipCode() {
		return billingZipCode;
	}

	/**
     * This method is used to get the Type associated with the card.
     */
	public CardType getCardType() {
		return type;
	}

	/**
     * This method is used to set the Type associated with the card.
     * @param type the type that you want to set this card to be
     */
	public void setCardType(CardType type) {
		this.type = type;
	}
}