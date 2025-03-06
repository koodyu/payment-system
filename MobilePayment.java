

/**
 * The MobilePayment class extends ElectronicPayment 
 * with phone number and star rating.
 */
public class MobilePayment extends ElectronicPayment {
    private String phoneNumber;
    private int starRating;

    private static final double MAX_STAR_RATING = 5.0;

    /**
     * No-arg constructor for the MobilePayment class
     */
    public MobilePayment() {}

    /**
     * Constructor for MobilePayment .
     *
     * @param transactionName The name.
     * @param amount The payment amount.
     * @param timestamp The transaction timestamp.
     * @param accountId The account ID.
     * @param emailAddress The email address.
     * @param phoneNumber The phone number .
     * @param starRating The star rating .
     */
    public MobilePayment(String transactionName, double amount,
                                String timestamp, long accountId,
                                String emailAddress, String phoneNumber,
                                int starRating) {
        super(transactionName, amount, timestamp, accountId, emailAddress);
        this.phoneNumber = phoneNumber;
        this.starRating = starRating;
    }

    /**
     * Gets the phone number.
     *
     * @return The phone number.
     */
    @Override
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Gets the star rating.
     *
     * @return The star rating.
     */
    @Override
    public int getStarRating() {
        return this.starRating;
    }
    /**
     * Checks if this MobilePayment is equal to another object.
     * @param object The object to compare with.
     * @return True if the objects are equal, otherwise false.
     */
    
    @Override
    public boolean equals(Object object) {
   
    
        if (object == null) {
            return false; 
        }
        MobilePayment mobilePayment = (MobilePayment) object;
        boolean fatherEquals = super.equals(mobilePayment);

        
        return fatherEquals &&
               this.phoneNumber.equals(mobilePayment.getPhoneNumber()) &&
               this.starRating == mobilePayment.getStarRating();
    }
        

    /**
     * Calculates the risk.
     * 
     * Risk = amount / starRatingNormalized
     * starRatingNormalized = starRating / MAX_STAR_RATING.
     *
     * @return The calculated risk.
     */
    @Override
    public double calculateRisk() {

        return this.getAmount() / (this.starRating / MAX_STAR_RATING);
    }

    /**
    * Returns payment details.
    *
    * @return payment details.
    */   
    
    @Override
    public String toString() {
        return "MobilePayment (" + getTransactionName() +
            ") phoneNumber: " + getPhoneNumber() + ", starRating: " +
                getStarRating();
    }
}
