# payment-system
# 💳 PaymentProcessor

This project focusing on **Inheritance, Polymorphism, and Exceptions**. It implements a **Payment Processing System** that supports various payment types and operations.

## 📜 **Project Overview**
This system manages different types of payments and performs operations such as:
- Adding and retrieving payments
- Processing payments based on validation rules
- Comparing risks between payments
- Increasing transaction amounts
- Writing payment details to a file

## 📂 **Project Structure**
📦 PaymentProcessor
┣ 📜 project.java                 # Main driver class, runs unit tests
┣ 📜 Payment.java                      # Parent class for different payment types
┣ 📜 CardPayment.java                  # Base class for card payments
┣ 📜 CreditPayment.java                # Handles credit card transactions
┣ 📜 DebitPayment.java                 # Handles debit card transactions
┣ 📜 ElectronicPayment.java            # Parent class for electronic payments
┣ 📜 MobilePayment.java                # Handles mobile-based payments
┣ 📜 DigitalWalletPayment.java         # Handles digital wallet payments
┣ 📜 PaymentProcessor.java             # Manages all payments
┣ 📜 README.md                         # Project documentation

## ⚡ **Features**
✅ Supports multiple payment types: **Credit, Debit, Mobile, Digital Wallet**  
✅ Implements **polymorphism** for handling different payment processing rules  
✅ Compares transaction **risks** based on custom calculations  
✅ Implements **file handling** to save payment records  
✅ **Unit tests** included to verify correctness  

## 🔧 **How to Compile & Run**
To compile all Java files:
```sh
javac *.java


🚀 Contributing

// Create a PaymentProcessor
PaymentProcessor proc = new PaymentProcessor();

// Add payments
proc.addToPaymentList(new CreditPayment("Groceries", 42.5, "9/9",
                "4444 5555 6666 7777", 12, 2025, 1200, 100));

// Apply an increase of 1.5x
proc.applyIncrease(1.5);

// Compare risks
int result = PaymentProcessor.compareRisk(payment1, payment2);
System.out.println("Risk Comparison: " + result);

Feel free to fork this repository and make improvements!
If you find any bugs, please open an issue.