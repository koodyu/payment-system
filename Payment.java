

/**
 * parent class for different types of payments.
 */
public class Payment {
    private String transactionName;
    private double amount;
    private String timestamp;

    /**
     * No-arg constructor of the Payment class
     */
    public Payment() {}

    /**
     * Constructor for Payment class.
     * @param transactionName  The transaction.
     * @param amount The amount 
     * @param timestamp The timestamp
     */    
    public Payment(String transactionName, double amount, String timestamp) {
        this.transactionName = transactionName;
        this.amount = amount;
        this.timestamp = timestamp;
    }
    
    /**
     * Gets the transaction name.
     * @return The transaction name .
     */
    public String getTransactionName() {
        return this.transactionName;
    }

    /**
     * Gets the transaction amount.
     *
     * @return The amount 
     */ 
    public double getAmount() {
        return this.amount;
    }

    /**
     * Gets the transaction timestamp.
     *
     * @return The timestamp 
     */
    public String getTimestamp() {
        return this.timestamp;
    }

    /**
     * Sets the transaction amount.
     *
     * @param amount The new amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Sets the transaction name.
     *
     * @param transactionName The new transaction name.
     */    
    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    /**
     * Checks if this Payment object is equal to another object.
     *
     * This method overrides the default equals() method from the Object class.
     * The default equals() method in Object only checks if two references point
     * to the same object, but we override it to compare the contents of the 
     * Payment object.
     *
     * @param object The object to compare with.
     * @return True if both objects have the same info.
     */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        Payment payment = (Payment) object;

        boolean namesEqual = this.transactionName
                                        .equals(payment.getTransactionName());
        boolean amountsEqual = this.amount == payment.getAmount();
        boolean timestampsEqual = this.timestamp.equals(payment.getTimestamp());

        return namesEqual && amountsEqual && timestampsEqual;
    }

    /**
     * The default toString() method in Object only prints the memory 
     * address of the object.
     * This allows us to print Payment objects when calling
     * System.out.println(payment)
     *
     * @return A formatted string containing 
     * the transaction name, amount, and timestamp.
     */
    @Override
    public String toString() {
        return "Payment (" + getTransactionName() +
            ") amount: " + getAmount() + ", timestamp: " + getTimestamp();
    }

    /**
     * Card number of a CardPayment instance
     * @return the String representation of the card number
     */
    public String getCardNumber() {
        return null;
    }

    /**
     * Expiry month of a card of a CardPayment instance
     * @return the month of the card's expiration date
     */
    public int getExpiryMonth() {
        return 0;
    }

    /**
     * Expiry year of a card of a CardPayment instance
     * @return the year of the card's expiration date
     */
    public int getExpiryYear() {
        return 0;
    }

    /**
     * Account ID of an ElectronicPayment instance
     * @return the long representation of the account ID
     */
    public long getAccountId() {
        return 0;
    }

    /**
     * Email address of an ElectronicPayment instance
     * @return the String representation of the email
     */
    public String getEmailAddress() {
        return null;
    }

    /**
     * Bank balance of a bank account linked to a DebitPayment
     * @return the double representation of the bank balance
     */
    public double getBankBalance() {
        return 0;
    }

    /**
     * Credit limit of a card linked to a CreditPayment
     * @return the double representation of the credit limit
     */
    public double getCreditLimit() {
        return 0;
    }

    /**
     * Outstanding balance of a card linked to a CreditPayment
     * @return the double representation of the outstanding balance
     */
    public double getCardBalance() {
        return 0;
    }

    /**
     * Phone number linked to a MobilePayment
     * @return the String representation of the phone number
     */
    public String getPhoneNumber() {
        return null;
    }

    /**
     * From 1 to 5 stars, the star rating of the user of the MobilePayment
     * @return the integer representation of the star rating
     */
    public int getStarRating() {
        return 0;
    }

    /**
     * The digital wallet ID linked to the DigitalWalletPayment
     * @return the String representation of the wallet ID
     */
    public String getWalletId() {
        return null;
    }

    /**
     * The number of previous transactions made by the digital wallet
     * of the DigitalWalletPayment
     * @return the integer representation of the number of previous transactions
     */
    public int getPreviousNumTransactions() {
        return 0;
    }

    /**
     * The risk associated with an instance of a Payment
     * @return the risk represented as a double
     */
    public double calculateRisk() {
        return 0;
    }
}
