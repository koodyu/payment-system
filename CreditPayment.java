

/**
 * The CreditPayment class extends CardPayment with
 *  credit limit and card balance.
 */
public class CreditPayment extends CardPayment {
    private double creditLimit;
    private double cardBalance;

    /**
     * No-arg constructor of the CreditPayment class
     */
    public CreditPayment() {}

    /**
     * Constructor for CreditPayment.
     * @param transactionName  The transaction.
     * @param amount The amount.
     * @param timestamp The timestamp
     * @param cardNumber The card number.
     * @param expiryMonth The expiration month 
     * @param expiryYear The expiration year 
     * @param creditLimit The credit limit of the card.
     * @param cardBalance The current balance.
     */

    public CreditPayment(String transactionName, double amount,
                            String timestamp, String cardNumber,
                            int expiryMonth, int expiryYear,
                            double creditLimit, double cardBalance) {
        super(transactionName, amount, timestamp,
                cardNumber, expiryMonth, expiryYear);
        this.creditLimit = creditLimit;
        this.cardBalance = cardBalance;

    }

    /**
     * Gets the credit limit.
     *
     * @return The credit limit .
     */
    @Override
    public double getCreditLimit() {
        return this.creditLimit;
    }



    /**
     * Gets the card balance.
     * @return The card balance .
     */
    @Override
    public double getCardBalance() {
        return this.cardBalance;
    }

    /**
     * Checks if this DebitPayment is equal to another object.
     * by expiryMonth, expiryYear, and bankBalance.
     *
     * @param object The object to compare with.
     * @return True if the objects are equal, otherwise false.
     */    
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false; 
        }

        CreditPayment creditPayment = (CreditPayment) object;
        
        boolean fatherEquals = super.equals(creditPayment);

        return fatherEquals && 
               this.creditLimit == creditPayment.getCreditLimit() && 
               this.cardBalance == creditPayment.getCardBalance();
    }
       

    
    /**
     * Calculates the risk .
     * 
     * Risk is = amount -  (creditLimit - cardBalance)
     *
     * @return The  risk.
     */
    @Override
    public double calculateRisk() {
       
        return this.getAmount() - (this.creditLimit - this.cardBalance);  
    }

    /**
     * Returns a descirption of the CreditPayment object.
     *
     * @return payment details.
     */ 
    @Override
    public String toString() {
        return "CreditPayment (" + getTransactionName() +
            ") creditLimit: " + getCreditLimit() + ", cardBalance: " +
                getCardBalance();
    }
}
