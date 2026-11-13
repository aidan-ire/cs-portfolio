import java.util.*;

public class TestBankAccount{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        BankAccount acc = new BankAccount("Sean Murphy", "IE-1001");
        System.out.println("Opened " + acc);

        String choice = "";
        while(!choice.equals("q")){
            System.out.print("d = deposit, w = withdraw, b = balance, q = quit: ");
            choice = sc.next().toLowerCase();

            if(choice.equals("d")){
                System.out.print("Amount to deposit: ");
                double amount = sc.nextDouble();
                if(acc.deposit(amount)){
                    System.out.printf("Deposited %.2f%n", amount);
                }else{
                    System.out.println("A deposit must be more than 0");
                }
            }else if(choice.equals("w")){
                System.out.print("Amount to withdraw: ");
                double amount = sc.nextDouble();
                if(acc.withdraw(amount)){
                    System.out.printf("Withdrew %.2f%n", amount);
                }else{
                    System.out.printf("Can't withdraw %.2f, the balance is %.2f%n", amount, acc.getBalance());
                }
            }else if(choice.equals("b")){
                System.out.println(acc);
            }else if(!choice.equals("q")){
                System.out.println("Unknown option");
            }
        }

        System.out.println("Closing: " + acc);

        sc.close();
    }
}
