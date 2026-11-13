/**
 * A bank account that can't go overdrawn. Every amount is in euro.
 */
public class BankAccount{
    private String owner;
    private String accountNumber;
    private double balance;

    /**
     * Opens an account with a balance of 0.
     * @param owner         the account holder's name
     * @param accountNumber the account number
     */
    public BankAccount(String owner, String accountNumber){
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = 0;
    }

    public String getOwner(){
        return owner;
    }

    public String getAccountNumber(){
        return accountNumber;
    }

    public double getBalance(){
        return balance;
    }

    /**
     * Adds money to the account.
     * @param amount the amount to add, must be more than 0
     * @return true if it was added, false if the amount wasn't valid (the balance is unchanged)
     */
    public boolean deposit(double amount){
        if(amount <= 0) return false;
        balance = balance + amount;
        return true;
    }

    /**
     * Takes money out of the account.
     * @param amount the amount to take out, must be more than 0 and no more than the balance
     * @return true if it was taken out, false otherwise (the balance is unchanged)
     */
    public boolean withdraw(double amount){
        if(amount <= 0 || amount > balance) return false;
        balance = balance - amount;
        return true;
    }

    public String toString(){
        return String.format("%s (%s): balance %.2f", owner, accountNumber, balance);
    }
}
