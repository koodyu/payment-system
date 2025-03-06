

/**
 * The DigitalWalletPayment class represents a digital wallet payment.
 * It extends ElectronicPayment with wallet ID , number of
 * previous transactions.
 */

public class DigitalWalletPayment extends ElectronicPayment {
    private String walletId;
    private int previousNumTransactions;

    /**
     * No-arg constructor of the DigitalWalletPayment class
     */
    public DigitalWalletPayment() {}

    /**
     * Constructor for CreditPayment.
     * @param transactionName  The transaction.
     * @param amount The amount 
     * @param timestamp The timestamp
     * @param accountId Id of the account.
     * @param emailAddress The email address.
     * @param walletId The wallet ID.
     * @param previousNumTransactions The number of previous transactions.
     */
    public DigitalWalletPayment(String transactionName, double amount,
                                String timestamp, long accountId,
                                String emailAddress, String walletId,
                                int previousNumTransactions) {
        super(transactionName, amount, timestamp, accountId, emailAddress);
        this.walletId = walletId;
        this.previousNumTransactions = previousNumTransactions;
                                    
    }

    /**
     * Gets the wallet ID .
     *
     * @return The wallet ID.
     */
    @Override
    public String getWalletId() {
        return this.walletId;
    }


    /**
     * Gets the number of previous transactions.
     *
     * @return The previous number of transactions.
     */
    @Override
    public int getPreviousNumTransactions() {
        return this.previousNumTransactions;
    }



    /**
     * Checks if this DigitalWalletPayment is equal to another object.
     * @param object The object to compare with.
     * @return True if the objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object object) {
        if (object == null ) {
            return false;
        }
        DigitalWalletPayment digitalWalletPayment = 
                (DigitalWalletPayment) object;

        
        boolean namesEqual = 
            this.getTransactionName().equals(
                    digitalWalletPayment.getTransactionName());
        boolean amountsEqual = 
            this.getAmount() == digitalWalletPayment.getAmount();
        boolean timestampsEqual = 
            this.getTimestamp().equals(digitalWalletPayment.getTimestamp());
    
       
        boolean walletIdsEqual = 
            this.walletId.equals(digitalWalletPayment.getWalletId());
        boolean transactionsEqual = 
            this.previousNumTransactions ==
             digitalWalletPayment.getPreviousNumTransactions();
    
        return namesEqual && amountsEqual && timestampsEqual 
            && walletIdsEqual && transactionsEqual;
    }


    

    /**
     * Calculates the risk associated with the digital wallet payment.
     * 
     * Risk = amount / previousNumTransactions.
     *
     * @return The calculated risk.
     */
    @Override
    public double calculateRisk() {
        return this.getAmount() / this.previousNumTransactions; 
    }

    /**
    * Returns payment details.
    *
    * @return payment details.
    */   

    @Override
    public String toString() {
        return "DigitalWalletPayment (" + getTransactionName() +
            ") walletId: " + getWalletId() + ", previousNumTransactions: " +
                getPreviousNumTransactions();
    }
}
