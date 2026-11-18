import java.util.Scanner;

class InvalidAmountException extends Exception {
    InvalidAmountException(String message) { super(message); }
}
class InsufficientBalanceException extends Exception {
    InsufficientBalanceException(String message) { super(message); }
}
public class ATMWithdrawal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double balance = 5000;
        try {
            System.out.print("Enter withdrawal amount: ");
            double amount = sc.nextDouble();
            if (amount <= 0) throw new InvalidAmountException("Amount must be positive.");
            if (amount > balance) throw new InsufficientBalanceException("Insufficient balance.");
            balance -= amount;
            System.out.println("Withdrawal successful.");
            System.out.println("Remaining balance: " + balance);
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: Enter a numeric amount.");
        } catch (InvalidAmountException | InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("ATM transaction completed.");
            sc.close();
        }
    }
}
