
/**
 * The DebitPayment class extends CardPayment and includes a bank balance.
 */

public class DebitPayment extends CardPayment {
    private double bankBalance;

    /**
     * No-arg constructor of the DebitPayment class
     */
    public DebitPayment() {}

    /**
     * Constructs a DebitPayment object with the provided details.
     *
     * @param transactionName The transaction id.
     * @param amount The payment amount.
     * @param timestamp The transaction timestamp.
     * @param cardNumber The card number.
     * @param expiryMonth The expiry month .
     * @param expiryYear The expiry year.
     * @param bankBalance The bank balance .
     */
    public DebitPayment(String transactionName, double amount,
                                String timestamp, String cardNumber,
                                int expiryMonth, int expiryYear,
                                double bankBalance) {
        super(transactionName, amount, timestamp, 
                cardNumber, expiryMonth, expiryYear);
        this.bankBalance = bankBalance;
    }

    /**
     * Gets the bank balance.
     *
     * @return The bank balance .
     */
    @Override
    public double getBankBalance() {
        return this.bankBalance;
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

        DebitPayment debitPayment = (DebitPayment) object;
        
        boolean fatherEquals = super.equals(debitPayment);

        return fatherEquals && 
            this.bankBalance == debitPayment.getBankBalance();
       
    }

    /**
     * Calculates the risk .
     * 
     * Risk = amount - bankBalance.
     *
     * @return The calculated risk.
     */
    @Override
    public double calculateRisk() {
        return this.getAmount() - this.bankBalance;
    }

    
    /**
     * Returns a payment details..
     *
     * @return payment details.
     */
    @Override
    public String toString() {
        return "DebitPayment (" + getTransactionName() +
            ") bankBalance: " + getBankBalance();
    }
}
