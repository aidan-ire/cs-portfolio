import java.util.*;

public class Genomes{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a DNA strand: ");
        String dna = sc.nextLine().trim().toUpperCase();

        while(!isValidDNA(dna)){
            System.out.println("Invalid strand, it can only contain the bases A, C, G and T");
            System.out.print("Please input a DNA strand: ");
            dna = sc.nextLine().trim().toUpperCase();
        }

        System.out.println("Strand:     " + dna);
        System.out.println("Complement: " + complement(dna));

        sc.close();
    }

    public static boolean isValidDNA(String dna){
        if(dna.length() == 0) return false;

        for(int i = 0; i < dna.length(); i++){
            char c = dna.charAt(i);
            if(c != 'A' && c != 'C' && c != 'G' && c != 'T') return false;
        }

        return true;
    }

    // A pairs with T and C pairs with G, so each base is swapped for its partner
    public static String complement(String dna){
        String res = "";

        for(int i = 0; i < dna.length(); i++){
            char c = dna.charAt(i);
            if(c == 'A') res = res + 'T';
            else if(c == 'T') res = res + 'A';
            else if(c == 'C') res = res + 'G';
            else res = res + 'C';
        }

        return res;
    }
}
