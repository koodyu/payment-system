
import java.util.ArrayList;

/**
 * Class Assignment6
 * 
 * This class serves as the driver for the Payment Processing System.
 * The main() method executes unitTests() and prints whether all tests pass.
 */
public class project {

    /**
     * Print the payment array in a readable format. Optional to use
     * @param paymentArr An array of Payment instances
     */
    public static void printPaymentArray(Payment[] paymentArr) {
        System.out.println('[');
        for (int i = 0; i < paymentArr.length; i++) {
            System.out.print("  " + paymentArr[i]);
            System.out.println(',');
        }
        System.out.println(']');
    }

    /**
     * Print the payment arraylist in a readable format. Optional to use
     * @param paymentArr An arraylist of Payment instances
     */
    public static void printPaymentArray(ArrayList<Payment> paymentArr) {
        System.out.println('[');
        for (int i = 0; i < paymentArr.size(); i++) {
            System.out.print("  " + paymentArr.get(i));
            System.out.println(',');
        }
        System.out.println(']');
    }

    /**
     * Runs unit tests to verify methods in PaymentProcessor.
     * @return true if all test cases pass, false otherwise
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public static boolean unitTests() {
        // SETUP
        PaymentProcessor proc = new PaymentProcessor();

        // Add payments to a PaymentProcessor object
        MobilePayment m1 = new MobilePayment("Dinner", 10.0, "12/25", 0,
                                        "maryanne@ucsd.edu", "4445556666", 4);
        proc.addToPaymentList(m1);
        Payment[] paymentsToAdd = {
            new CreditPayment("Groceries", 42.5, "9/9",
                                "4444 5555 6666 7777", 12, 2025, 1200, 100),
            new DigitalWalletPayment("Forex", 3.0, "4/1", 1,
                                        "maryanne@ucsd.edu", "0x0002", 10),
            new DebitPayment("Pharmacy", 4.77, "2/2/2024",
                                "1111 2222 3333 4444", 1, 2027, 4000),
            

                                
       	};
        proc.addToPaymentList(paymentsToAdd);

        // feel free to add more payments to PaymentProcessor!


        // TEST CASE 1
        // We provide a test case for applyPaymentSurge
        // Save all the original amounts of the payments
        double[] originalPrices = new double[proc.getPaymentList().size()];
        for (int i = 0; i < proc.getPaymentList().size(); i++) {
            originalPrices[i] = proc.getPaymentList().get(i).getAmount();
        }

        // Apply payment increases
        double increaseRate = 1.5;
	    proc.applyIncrease(increaseRate);

        // Check paymentList amounts
        for (int i = 0; i < proc.getPaymentList().size(); i++) {
            Payment payment = proc.getPaymentList().get(i);
            if (increaseRate < 1) {
                // Check that the Payment at this index is still the same
                if (payment.getAmount() != originalPrices[i]) {
                    // Payment has changed unexpectedly
                    System.out.println("applyIncrease 1" +
                        " - Payment unexpectedly changed ");
                    System.out.println(proc.getPaymentList());
                    return false;
                }
            } else {
                // Check that the Payment has the expected discounted amount
                double actualPrice = originalPrices[i] * increaseRate;
                if (payment.getAmount() != actualPrice) {
                    System.out.println("applyIncrease 1" +
                        " - Payment does not have expected increased amount");
                    System.out.println(payment.getAmount());
                    System.out.println(actualPrice);
                    System.out.println(proc.getPaymentList());
                    return false;
                }
            }
        }
        // TEST CASE 2
        // TEST CASE 2: compareRisk()
        Payment lowRisk = 
            new DebitPayment("LowRisk",5.0,
            "1/1/2024","1111 2222 3333 4444",
            1, 2027, 1000);
        Payment highRisk = 
            new CreditPayment("HighRisk", 
            50.0, "1/1/2024",
            "1111 2222 3333 4444", 1, 
            2027, 500, 490);

        // low risk vs. high risk, should return -1
        if (PaymentProcessor.compareRisk(lowRisk, highRisk) != -1) {
            return false;
        }

        //High risk vs. low risk, should return 1
        if (PaymentProcessor.compareRisk(highRisk, lowRisk) != 1) {
            return false;
        }

        //  Same risk, should return 0
        Payment sameRisk1 = 
            new DebitPayment("SameRisk1",
            30.0,
            "1/1/2024",
            "1111 2222 3333 4444", 
            1, 2027, 
            500);
        Payment sameRisk2 = 
            new DebitPayment("SameRisk2",30.0, 
            "1/1/2024",
            "1111 2222 3333 4444", 
            1, 2027, 
            500);
        if (PaymentProcessor.compareRisk(sameRisk1, sameRisk2) != 0) {
            return false;
        }

        // TEST CASE 3：Test processPayment()
        Payment validcp = 
                        new CreditPayment("go1", 50.0, "1/1/2024",
                             "1234 5678 9012 3456", 5, 2026, 5000, 100);
        Payment validmp = 
                        new MobilePayment("go2", 30.0, "2/15", 
                             101, "valid@ucsd.edu", "9998887777", 5);
        Payment validdp =
                        new DebitPayment("go3", 40.0, "3/1/2024",
                             "1111 2222 3333 4444", 1, 2027, 5000);
                                
                             
        if (!PaymentProcessor.processPayment(validcp) ||
            !PaymentProcessor.processPayment(validmp) ||
            !PaymentProcessor.processPayment(validdp)) {
            return false;   
        }
        Payment invalidcp = 
                new CreditPayment("InvalidCredit", -20.0, "7/7",
                             "9999 8888 7777 6666", 12, 2025, 1000, 2000); 
        Payment invalidmp = 
                new MobilePayment("InvalidMobile", 20.0, "4/1",
                              102, "invalid@ucsd.com", "5554443333", -1); 
        Payment invaliddp =
                new DebitPayment("InvalidDebit", 25.0, "5/5",
                "2222 3333 4444 5555", 3, 2024, -100);
        if (PaymentProcessor.processPayment(invalidcp) ||
            PaymentProcessor.processPayment(invalidmp) ||
            PaymentProcessor.processPayment(invaliddp)) {
            return false;
        }

        // TEST CASE 4: Test writetofile()
 
        Payment testPayment = null;
        String filename = "test.txt";
        try {
            PaymentProcessor.writePaymentToFile(filename, testPayment);
        } catch (Exception e) {
            if (!e.getMessage().equals("Uninitialized payment.")){
                return false;
            }
            return true;
           

        }



        
        

        // Optional: TEST CASE 5
        
        return true;
    }
   
    
    /**
     * Main method to run unit tests
     * @param  args Command line arguments
     */
    public static void main(String[] args) {
        // Perform unitTests
        if(unitTests()) {
            System.out.println("All unit tests passed.\n");
        } else {
            System.out.println("Failed test.\n");
            return;
        }
  

        // Don't need to write code in main!
    }
}
