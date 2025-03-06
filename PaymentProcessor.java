

import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * The PaymentProcessor class manages a list of payments,
 *  store payment data.
 */
public class PaymentProcessor {
    private ArrayList<Payment> paymentList;

    private static final int MIN_EXPIRY_MONTH = 4;
    private static final int MAX_EXPIRY_MONTH = 12;
    private static final int MIN_EXPIRY_YEAR = 2025;
    private static final int MAX_STARS = 5;
    private static final String UCSD_EMAIL = "@ucsd.edu";
    private static final String INCREASED_1 = " (increased by ";
    private static final String INCREASED_2 = "x)";
    private static final String EXCEPT_MSG = "Uninitialized payment.";

    /**
    * Constructs a new PaymentProcessor with an empty payment list.
    */
    public PaymentProcessor() {
        this.paymentList = new ArrayList<>();
    }

    /**
     * Adds payment to the payment list.
     *
     * @param payment payment to be added.
     */
    public void addToPaymentList(Payment payment) {
        paymentList.add(payment);
    }

    /**
     * Adds payments to the payment list.
     *
     * @param payments  Payments  to be added.
     */
    public void addToPaymentList(Payment[] payments) {
        for (int i = 0; i < payments.length; i++) {
            paymentList.add(payments[i]);
        }
    }


    /**
     * Checks if a payment exists.
     *
     * @param paymentTransactionName The transaction name.
     * @return True if a payment exists, otherwise false.
     */
    public boolean hasPayment(String paymentTransactionName) {
        for (int i = 0; i < paymentList.size(); i++) {
            Payment target = paymentList.get(i);
            boolean ifExists = 
                target.getTransactionName().equals(paymentTransactionName);
            if (ifExists) {
                return true;
            }
        }
        return false;
        
    }

    /**
     * Removes a payment from the list based 
     * @param paymentTransactionName The transaction name to search for.
     * @return The removed Payment object if found, otherwise null.
     */
    public Payment getAndRemovePayment(String paymentTransactionName) {
        for (int i = 0; i < paymentList.size(); i++) {
            Payment target = paymentList.get(i);
            boolean ifExists = 
                target.getTransactionName().equals(paymentTransactionName);
            if (ifExists) {
                return paymentList.remove(i);
            }
        }
        return null;
    }

    /**
     * Compares the risk between two payments.
     *
     * @param paymentOne The first Payment object.
     * @param paymentTwo The second Payment object.
     * @return -1 means paymentOne has less risk, 0 means equal,
     *          1 if paymentOne has greater risk.
     */   
    public static int compareRisk(Payment paymentOne, Payment paymentTwo) {
        double riskOne = paymentOne.calculateRisk(); 
        double riskTwo = paymentTwo.calculateRisk(); 
        if (riskOne < riskTwo) {
            return -1;
        } else if (riskOne > riskTwo) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * Increases the amount by rate.
     * Also appends " (increased by [increaseRate]x)" to the transaction name.
     *
     * @param increaseRate The rate to increase the payment amounts.
     */
    public void applyIncrease(double increaseRate) {
        if (increaseRate < 1) {
            return;
        }
        for (int i = 0; i < paymentList.size(); i++) {
            Payment p = paymentList.get(i); 
            double newAmount = p.getAmount() * increaseRate;
            p.setAmount(newAmount);
            p.setTransactionName(p.getTransactionName() 
                        + INCREASED_1 + increaseRate + INCREASED_2);
        }

    }

    /**
    * Check whether a payment is good for processing.
    *
    * @param payment The Payment object.
    * @return True if the object is valid , otherwise false.
    */
    public static boolean processPayment(Payment payment) {
        if (payment.getAmount() < 0) {
            return false;
        }
        // CardPayment related validations
        if (payment instanceof CardPayment) {
            CardPayment cardPayment = (CardPayment) payment;
            int expiryYear = cardPayment.getExpiryYear();
            int expiryMonth = cardPayment.getExpiryMonth();
    
            if (expiryYear < MIN_EXPIRY_YEAR) {
                return false;
            }
            if (expiryYear == MIN_EXPIRY_YEAR && (expiryMonth < MIN_EXPIRY_MONTH
                                     || expiryMonth > MAX_EXPIRY_MONTH)) {
                return false;
            }
            if (expiryYear > MIN_EXPIRY_YEAR && (expiryMonth < 1 
                                        || expiryMonth > MAX_EXPIRY_MONTH)) {
                return false;
            }
        }
        //ElectronicPayment related validations
        if (payment instanceof ElectronicPayment) {
            ElectronicPayment electronicPayment = (ElectronicPayment) payment;
            String email = electronicPayment.getEmailAddress();
    
            if (email.length() < UCSD_EMAIL.length() ){
                return false;
            }
            boolean IsUcsdEmail = email.substring(email.length()
                                 - UCSD_EMAIL.length()).equals(UCSD_EMAIL);
            if (!IsUcsdEmail) {
                return false;
            }

        }
    
        // DebitPayment related validations
        if (payment instanceof DebitPayment) {
            DebitPayment debitPayment = (DebitPayment) payment;
            if (debitPayment.getBankBalance() < 0) {
                return false;
            }
        }
    
        // CreditPayment related validations
        if (payment instanceof CreditPayment) {
            CreditPayment creditPayment = (CreditPayment) payment;
            if (creditPayment.getCardBalance() < 0 ||
                creditPayment.getCreditLimit() < 0 ||
                creditPayment.getCreditLimit() 
                    < creditPayment.getCardBalance()) 
            {
                return false;
            }
        }
    
        // MobilePayment related validations
        if (payment instanceof MobilePayment) {
            MobilePayment mobilePayment = (MobilePayment) payment;
            int starRating = mobilePayment.getStarRating();
            if (starRating < 0 || starRating > MAX_STARS) {
                return false;
            }
        }
    
        // DigitalWalletPayment related validations
        if (payment instanceof DigitalWalletPayment) {
            DigitalWalletPayment digitalWalletPayment = 
                        (DigitalWalletPayment) payment;
            if (digitalWalletPayment.getPreviousNumTransactions() < 0) {
                return false;
            }
        }
    
        return true;
    }

    /**
     * Writes the string representation of a payment to a file.
     * 
     * @param fileName file name as you can tell
     * @param payment  The Payment object.
     * @throws Exception If payment is null.
     */
    
    public static void writePaymentToFile(String fileName, Payment payment)
             throws Exception {
        if (payment == null) {
            throw new Exception(EXCEPT_MSG); 
        }
        // claim the writer outside the try block so can be 
        // close it in finally block
        PrintWriter writer = null;
        
        try {
            writer = new PrintWriter(fileName); 
            writer.println(payment.toString()); 
           
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (writer != null) {
                writer.close(); //
            }
        }
    }

      


    

    /**
     * The getter method of the paymentList member variable
     * @return the paymentList of the PaymentProcessor instance
     */
    public ArrayList<Payment> getPaymentList() {
        return this.paymentList;
    }
}
