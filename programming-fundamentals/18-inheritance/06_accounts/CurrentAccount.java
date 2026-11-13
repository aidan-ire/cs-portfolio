/**
 * A current account can go overdrawn up to a limit, and pays a fee for
 * every month it ends overdrawn.
 */
public class CurrentAccount extends Account{
    private double overdraftLimit;
    private double overdraftFee;

    /**
     * @param overdraftLimit how far below 0 the balance can go, eg. 200
     * @param overdraftFee   charged at the end of a month that ends below 0
     */
    public CurrentAccount(String owner, double overdraftLimit, double overdraftFee){
        super(owner);
        this.overdraftLimit = overdraftLimit;
        this.overdraftFee = overdraftFee;
    }

    // replaces Account's rule: the balance may go down to -overdraftLimit instead of 0
    @Override
    public boolean withdraw(double amount){
        if(amount <= 0 || balance - amount < -overdraftLimit) return false;
        balance = balance - amount;
        return true;
    }

    @Override
    public void monthEnd(){
        if(balance < 0) balance = balance - overdraftFee;
    }

    @Override
    public String getType(){
        return "Current";
    }
}
