

/**
 * Implementation of the CardPayment class,
 * which extends the Payment class
 * Bugs: None known.
 *
 * @author Haoran Yu
 */


public class CardPayment extends Payment {
    private String cardNumber;
    private int expiryMonth;
    private int expiryYear;

    /**
     * No-arg constructor of the CardPayment class
     */
    public CardPayment() {}


    /**
     * Constructor for Payment class.
     * @param transactionName  The transaction.
     * @param amount The amount 
     * @param timestamp The timestamp
     * @param cardNumber The card number.
     * @param expiryMonth The expiration month 
     * @param expiryYear The expiration year 
     */

    public CardPayment(String transactionName, double amount,
                             String timestamp, String cardNumber,
                             int expiryMonth, int expiryYear) {
        super(transactionName, amount, timestamp);
        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
                            
    }


    /**
     * Gets the card number associated with this CardPayment.
     *
     * @return The card number as a String.
     */
    @Override
    public String getCardNumber() {
        return this.cardNumber;
    }

    /**
     * Gets the expiry month 
     *
     * @return The expiry month
     */
    @Override
    public int getExpiryMonth() {
        return this.expiryMonth;
    }


    /**
     * Gets the expiry Year 
     *
     * @return The expiry year
     */    
    @Override
    public int getExpiryYear() {
        return this.expiryYear;
    }

    /**
     * Checks if this CardPayment object is equal to another object.
     * @param object The object to compare with.
     * @return True if the objects are equal, otherwise false.
     */
    
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        CardPayment cardPayment = (CardPayment) object;
        boolean fatherEquals = super.equals(cardPayment);

        boolean cardNumberEqual = 
                    this.cardNumber.equals(cardPayment.getCardNumber());
        boolean expiryMonthEqual = 
                    this.expiryMonth == cardPayment.getExpiryMonth();
        boolean expiryYearEqual = 
                    this.expiryYear == cardPayment.getExpiryYear();

        return fatherEquals && cardNumberEqual && expiryMonthEqual 
                && expiryYearEqual;


    }
    
    /**
     * Returns payment details.
     *
     * @return payment details.
     */       
    @Override
    public String toString() {
        return "CardPayment (" + getTransactionName() +
            ") cardNumber: " + getCardNumber() + ", expiryMonth: " +
                getExpiryMonth() + ", expiryYear: " + getExpiryYear();
    }
}
