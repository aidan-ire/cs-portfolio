/**
 * A savings account earns interest every month.
 */
public class SavingsAccount extends Account{
    private double yearlyRate;

    /**
     * @param yearlyRate the interest rate as a percentage per year, eg. 2.4
     */
    public SavingsAccount(String owner, double yearlyRate){
        super(owner);
        this.yearlyRate = yearlyRate;
    }

    // a twelfth of the yearly rate is added each month
    @Override
    public void monthEnd(){
        balance = balance + balance * yearlyRate / 100 / 12;
    }

    @Override
    public String getType(){
        return "Savings";
    }
}
