
/**
 * Implementation of the electronic payment class,
 * which extends the Payment class
 * Bugs: None known.
 *
 * @author Haoran Yu
 */

public class ElectronicPayment extends Payment {
    private long accountId;
    private String emailAddress;

    /**
     * No-arg constructor of the ElectronicPayment class
     */
    public ElectronicPayment() {}

    

    /**
     * Constructs an ElectronicPayment object.
     *
     * @param transactionName The name of the transaction.
     * @param amount The amount of the transaction.
     * @param timestamp The timestamp of the transaction.
     * @param accountId The account ID number.
     * @param emailAddress The email address linked to payment.
     */

    public ElectronicPayment(String transactionName, double amount,
                             String timestamp, long accountId,
                             String emailAddress) {
        super(transactionName, amount, timestamp);
        this.accountId = accountId;
        this.emailAddress = emailAddress;
            
    }

    /**
     * Gets the account ID.
     *
     * @return The account ID
     */

    @Override
    public long getAccountId() {
        return this.accountId;
    }


    /**
     * Gets the email address 
     *
     * @return The email address 
     */

    @Override
    public String getEmailAddress() {
        return this.emailAddress;
    }   
    
    /**
    * Checks if this ElectronicPayment is equal to another object.
    * @param object The object to compare with.
    * @return True if the objects are equal, otherwise false.
    */
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        ElectronicPayment electronicPayment = (ElectronicPayment) object;
        boolean fatherEquals = super.equals(electronicPayment);
        boolean accountIdEqual = 
                this.accountId == electronicPayment.getAccountId();
        boolean emailAddressEqual = 
                this.emailAddress.equals(electronicPayment.getEmailAddress());
        return fatherEquals && accountIdEqual && emailAddressEqual;
       
    }


    /**
     * Returns a descirption of the ElectronicPayment object.
     *
     * @return payment details.
     */   
    @Override
    public String toString() {
        return "ElectronicPayment (" + getTransactionName() +
            ") accountId: " + getAccountId() + ", emailAddress: " +
                getEmailAddress();
    }

}
