import java.util.ArrayList;
import java.util.ArrayList.*;
import java.util.Scanner;

class MatrixSpiralizer {

    private int[][] intMatrix;
    private int numRows;
    private int numCols;

    public MatrixSpiralizer(int rows, int cols) {
        int[][] toReturn = new int[rows][cols];
        int num = 1;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Initialize each portion of matrix to 1 through a number at the end of custom
                // sized matrix, increase by one each time you iterate.
                toReturn[r][c] = num;
                num++;
            }
        }
        this.numRows = rows;
        this.numCols = cols;
        this.intMatrix = toReturn;
    }

    public ArrayList<Integer> spiralizer() {
        // Take intMatrix and make it into intArray with order as described in lab doc
        int length = this.numRows * this.numCols; // Area of matrix
        ArrayList<Integer> intArray = new ArrayList<Integer>();
        int boundary_top = 0;
        int boundary_left = 0;
        int boundary_bottom = this.numRows -1;
        int boundary_right = this.numCols -1;
        while((boundary_bottom >= boundary_top) && (boundary_right >= boundary_left)){
            for(int curCol = boundary_left; curCol <= boundary_right; curCol++){
                intArray.add(this.intMatrix[boundary_top][curCol]);
            }
            boundary_top++;
            for(int curRow = boundary_top; curRow <= boundary_bottom; curRow++){
                intArray.add(this.intMatrix[curRow][boundary_right]);
            }
            boundary_right--;
            for(int curCol = boundary_right; curCol >= boundary_left; curCol--){
                intArray.add(this.intMatrix[boundary_bottom][curCol]);
            }
            boundary_bottom--;
            for(int curRow = boundary_bottom; curRow >= boundary_top; curRow--){
                intArray.add(this.intMatrix[curRow][boundary_left]);
            }
            boundary_left++;
        }
        return intArray;
    }

    public int[][] getIntMatrix(){
        return this.intMatrix;
    }

    public void printIntMatrix(){
        for(int r = 0; r < this.numRows; r++){
            for(int c = 0; c < this.numCols; c++){
                System.out.print(this.intMatrix[r][c]);
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("How many rows do you want? ");
        int r = s.nextInt();
        System.out.println("How many collums do you want? ");
        int c = s.nextInt();
        MatrixSpiralizer ms = new MatrixSpiralizer(r, c);
        System.out.println("\nResulting spiraled print:" + ms.spiralizer());
    }
}