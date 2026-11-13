import java.util.*;

public class ModuleCodes{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a module code: ");
        String code = sc.next();

        if(isModuleCode(code)){
            System.out.println(code + " is a valid module code");
            System.out.println("Department: " + code.substring(0, 2));
            System.out.println("Year: " + code.charAt(2));
        }else{
            System.out.println(code + " is not a valid module code");
        }

        sc.close();
    }

    // a valid code is two capital letters followed by three digits, eg. CS101
    public static boolean isModuleCode(String code){
        if(code.length() != 5) return false;

        for(int i = 0; i < 2; i++){
            if(code.charAt(i) < 'A' || code.charAt(i) > 'Z') return false;
        }
        for(int i = 2; i < 5; i++){
            if(code.charAt(i) < '0' || code.charAt(i) > '9') return false;
        }
        return true;
    }
}
