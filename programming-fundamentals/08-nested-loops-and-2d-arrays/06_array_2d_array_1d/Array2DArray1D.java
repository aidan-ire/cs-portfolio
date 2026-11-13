public class Array2DArray1D{
    public static void main(String[] args){
        int[][] grid = {{1, 2, 3, 4},
                        {5, 6, 7, 8},
                        {9, 10, 11, 12}};

        int[] flat = new int[grid.length * grid[0].length];

        // copies the grid row by row, k is the next free spot in the 1D array
        int k = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[i].length; j++){
                flat[k] = grid[i][j];
                k++;
            }
        }

        for(int i = 0; i < flat.length; i++){
            System.out.print(flat[i] + " ");
        }
        System.out.println();
    }
}
