public class TestAccounts{
    public static void main(String[] args){
        Account basic = new Account("Liam");
        SavingsAccount savings = new SavingsAccount("Emma", 2.4);
        CurrentAccount current = new CurrentAccount("Jack", 200, 5);

        Account[] accounts = {basic, savings, current};
        for(int i = 0; i < accounts.length; i++){
            accounts[i].deposit(1000);
        }
        System.out.println("After depositing 1000 into each:");
        printAll(accounts);

        // the same call behaves differently depending on the kind of account
        System.out.println("Withdrawing 1100 from each:");
        for(int i = 0; i < accounts.length; i++){
            boolean ok = accounts[i].withdraw(1100);
            System.out.println("  " + accounts[i].getType() + ": " + (ok ? "allowed" : "refused"));
        }

        for(int month = 1; month <= 3; month++){
            for(int i = 0; i < accounts.length; i++){
                accounts[i].monthEnd();
            }
        }
        System.out.println("After 3 month ends (interest for savings, overdraft fees for current):");
        printAll(accounts);
    }

    public static void printAll(Account[] accounts){
        for(int i = 0; i < accounts.length; i++){
            System.out.println("  " + accounts[i]);
        }
    }
}
