import java.util.*;

public class CountyWins{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        // ArrayLists since the number of different counties isn't known in advance.
        // counties.get(i) has wins.get(i) wins
        ArrayList<String> counties = new ArrayList<String>();
        ArrayList<Integer> wins = new ArrayList<Integer>();

        System.out.println("Enter the winning county of each final, one per line, then \"done\":");
        String line = sc.nextLine().trim();
        while(!line.equalsIgnoreCase("done")){
            if(line.length() > 0) addWin(counties, wins, tidy(line));
            line = sc.nextLine().trim();
        }

        if(counties.size() == 0){
            System.out.println("No results entered");
            sc.close();
            return;
        }

        sortByWins(counties, wins);

        System.out.println("Wins per county:");
        for(int i = 0; i < counties.size(); i++){
            System.out.printf("  %-10s %d%n", counties.get(i), wins.get(i));
        }

        sc.close();
    }

    // capital first letter and the rest lower case, so "kerry" and "KERRY" count as the same county
    public static String tidy(String name){
        return Character.toUpperCase(name.charAt(0)) + name.substring(1).toLowerCase();
    }

    // adds 1 to the county's wins, or adds the county with 1 win if it hasn't been seen yet
    public static void addWin(ArrayList<String> counties, ArrayList<Integer> wins, String county){
        int index = counties.indexOf(county);
        if(index == -1){
            counties.add(county);
            wins.add(1);
        }else{
            wins.set(index, wins.get(index) + 1);
        }
    }

    /*
    selection sort on the two lists together, most wins first and alphabetical on a tie.
    both lists are swapped at the same positions so each county stays matched with its count
    */
    public static void sortByWins(ArrayList<String> counties, ArrayList<Integer> wins){
        for(int i = 0; i < counties.size() - 1; i++){
            int best = i;
            for(int j = i + 1; j < counties.size(); j++){
                boolean more = wins.get(j) > wins.get(best);
                boolean tieFirst = wins.get(j).equals(wins.get(best)) && counties.get(j).compareTo(counties.get(best)) < 0;
                if(more || tieFirst) best = j;
            }

            String tempC = counties.get(i);
            counties.set(i, counties.get(best));
            counties.set(best, tempC);

            Integer tempW = wins.get(i);
            wins.set(i, wins.get(best));
            wins.set(best, tempW);
        }
    }
}
