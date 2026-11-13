public class SmallestRow{
    public static void main(String[] args){
        int[][] grid = {{4, 8, 2},
                        {1, 3, 2},
                        {9, 7, 5},
                        {3, 3, 3}};

        int smallestRow = 0;
        int smallestSum = Integer.MAX_VALUE;

        for(int i = 0; i < grid.length; i++){
            int sum = 0;
            for(int j = 0; j < grid[i].length; j++){
                sum = sum + grid[i][j];
            }
            if(sum < smallestSum){
                smallestSum = sum;
                smallestRow = i;
            }
        }

        System.out.print("Row " + smallestRow + " has the smallest sum (" + smallestSum + "):");
        for(int j = 0; j < grid[smallestRow].length; j++){
            System.out.print(" " + grid[smallestRow][j]);
        }
        System.out.println();
    }
}
