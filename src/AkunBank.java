public class AkunBank {
    private String accountNumber;
    private double balance;
    public static final double MAX_WITHDRAWAL_LIMIT = 1000000.0;

    public AkunBank(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance && amount <= MAX_WITHDRAWAL_LIMIT) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(AkunBank targetAccount, double amount) {
        if (withdraw(amount)) {
            targetAccount.deposit(amount);
            return true;
        }
        return false;
    }
}