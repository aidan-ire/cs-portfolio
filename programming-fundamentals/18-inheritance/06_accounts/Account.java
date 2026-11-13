/**
 * A basic account. The balance is protected so subclasses can change it directly,
 * but outside code still has to go through deposit and withdraw.
 */
public class Account{
    private String owner;
    protected double balance;

    public Account(String owner){
        this.owner = owner;
        this.balance = 0;
    }

    public String getOwner(){
        return owner;
    }

    public double getBalance(){
        return balance;
    }

    /**
     * @param amount the amount to add, must be more than 0
     * @return true if it was added
     */
    public boolean deposit(double amount){
        if(amount <= 0) return false;
        balance = balance + amount;
        return true;
    }

    /**
     * A basic account can't go below 0.
     * @param amount the amount to take out, must be more than 0
     * @return true if it was taken out, false if the amount is invalid or too large
     */
    public boolean withdraw(double amount){
        if(amount <= 0 || amount > balance) return false;
        balance = balance - amount;
        return true;
    }

    /** Runs at the end of every month. A basic account does nothing. */
    public void monthEnd(){
    }

    public String getType(){
        return "Account";
    }

    @Override
    public String toString(){
        return String.format("%-15s %-8s balance %8.2f", getType(), owner, balance);
    }
}
