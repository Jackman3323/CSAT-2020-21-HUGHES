import javafx.scene.chart.Chart;

import java.util.ArrayList;
import java.util.Collections;

public class ChartBuilder {
    private ArrayList<Integer> columnWidths;
    private final ArrayList<ArrayList<String>> dataMatrix;

    //-CONSTRUCTORS-
    //Constructor for columns of uniform width, input how many columns, data matrix, and desired uniform widdth
    public ChartBuilder(int uniformColumnWidth, int columns, ArrayList<ArrayList<String>> dataMatrix) {
        this.columnWidths = new ArrayList<Integer>(columns);
        Collections.fill(this.columnWidths, uniformColumnWidth);
        this.dataMatrix = dataMatrix;
    }

    //Constructor for columns of custom varying width, number of columns is length of columnWidths arrayList, also takes in the data matrix
    public ChartBuilder(ArrayList<Integer> columnWidths, ArrayList<ArrayList<String>> dataMatrix) {
        this.columnWidths = columnWidths;
        this.dataMatrix = dataMatrix;
    }

    //Constructor for people who want to use the methods for generating the chart matrix, the one that'll actually get used lmao
    public ChartBuilder() {
        this.dataMatrix = new ArrayList<ArrayList<String>>();
        this.columnWidths = new ArrayList<Integer>();
    }

    //Set up auto-column length list
    public void setColumnWidths() {
        this.columnWidths = new ArrayList<Integer>(dataMatrix.get(0).size()); //Width
        //Loop that iterates top down, left one column then top down, etc through the matrix
        for (int curCol = 0; curCol < dataMatrix.get(0).size(); curCol++) {
            int curLongest = 0;
            for (int curRow = 0; curRow < dataMatrix.size(); curRow++) {
                String curEntry = dataMatrix.get(curRow).get(curCol); //Gets current cell
                if (curLongest < curEntry.length()) {
                    //Update curLongest
                    curLongest = curEntry.length();
                }
            }
            this.columnWidths.set(curCol, curLongest);
        }
    }
    public void overrideColumnWidth(int col, int width){
        this.columnWidths.set(col, width);
    }

    private String formatEntry(String entry, int col) {
        int entryLength = entry.length();
        int difference = this.columnWidths.get(col) - entryLength; //Num of spaces needed, total
        StringBuilder toReturn = new StringBuilder();
        //Even num of spaces needed, split fifty-fifty
        int numFrontSpaces = difference / 2;
        int numBackSpaces;
        if (difference % 2 == 0) {
            //even num of spaces
            numBackSpaces = numFrontSpaces;
        } else {
            //odd num of spaces
            numBackSpaces = numFrontSpaces + 1;
        }
        int appendBack = this.columnWidths.get(col) - numBackSpaces;
        for (int i = 0; i < this.columnWidths.get(col); i++) {
            if (i < numFrontSpaces) {
                //Append a space to the front of the string
                toReturn.append(" ");
            } else if (i >= numFrontSpaces && i <= appendBack) {
                toReturn.append(entry.charAt(i));
            } else {
                toReturn.append(" ");
            }
        }
        toReturn.append("|");
        return toReturn.toString();
    }

    private String generateRow(int row) {
        StringBuilder toReturn = new StringBuilder();
        for (int col = 0; col < this.dataMatrix.get(0).size(); col++) {
            String curData = this.dataMatrix.get(row).get(col);
            toReturn.append(this.formatEntry(curData, col));
        }
        return toReturn.toString();
    }

    public String generateChart() {
        StringBuilder toReturn = new StringBuilder();
        for (int row = 0; row < this.dataMatrix.size(); row++) {
            toReturn.append(this.generateRow(row));
            toReturn.append("\n");
        }
        return toReturn.toString();
    }

    //-OVERLOADED METHOD-
    //This version takes a row, a column, and an object (the data) of some type
    public void enterData(int row, int col, Object data) {
        this.dataMatrix.get(row).set(col, data.toString());
    }

    //This version takes a row and an ArrayList<String> of data
    public void enterData(int row, ArrayList<Object> data) {
        for (int col = 0; col < this.dataMatrix.get(row).size(); col++) {
            Object curData = data.get(col);
            this.dataMatrix.get(row).set(col, curData.toString());
        }
    }

    //This version takes in the entire matrix
    public void enterData(ArrayList<ArrayList<Object>> inputMatrix) {
        for (int r = 0; r < inputMatrix.size(); r++) {
            for (int c = 0; c < inputMatrix.get(r).size(); c++) {
                Object curData = inputMatrix.get(r).get(c);
                this.dataMatrix.get(r).set(c, curData.toString());
            }
        }
    }
}