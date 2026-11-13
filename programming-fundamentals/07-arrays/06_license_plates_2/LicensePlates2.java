import java.util.*;

public class LicensePlates2{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Please input a license plate (eg. 191-D-12345): ");
        String plate = sc.next().toUpperCase();

        int first = plate.indexOf('-');
        int second = plate.lastIndexOf('-');

        if(first != 3 || second <= first + 1 || second == plate.length() - 1){
            System.out.println("Invalid license plate");
        }else{
            String code = plate.substring(first + 1, second);
            String county = countyName(code);

            if(county == null){
                System.out.println("Unknown county code: " + code);
            }else{
                System.out.println("Year: 20" + plate.substring(0, 2));
                System.out.println("County: " + county);
            }
        }

        sc.close();
    }

    // the two arrays line up, so a code at position i belongs to the county at position i
    public static String countyName(String code){
        String[] codes = {"C", "CE", "CN", "D", "DL", "G", "KE", "KK", "KY", "L", "LD", "LH", "LM",
                          "LS", "MH", "MN", "MO", "OY", "RN", "SO", "T", "W", "WH", "WX", "WW"};
        String[] counties = {"Cork", "Clare", "Cavan", "Dublin", "Donegal", "Galway", "Kildare", "Kilkenny", "Kerry",
                             "Limerick", "Longford", "Louth", "Leitrim", "Laois", "Meath", "Monaghan", "Mayo",
                             "Offaly", "Roscommon", "Sligo", "Tipperary", "Waterford", "Westmeath", "Wexford", "Wicklow"};

        for(int i = 0; i < codes.length; i++){
            if(codes[i].equals(code)) return counties[i];
        }
        return null;
    }
}
