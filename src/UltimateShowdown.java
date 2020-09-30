/**
 * UltimateShowdown
 * A truly ULTIMATE showdown between list and arraylist. Although the times never break 0 seconds because we don't really do much,
 * you certainly feel some epic adrenaline while you watch the epic brawl play out
 * Authors: JH, EW, DS
 * Date: 4/24
 * On My Honor: JH
 */
public class UltimateShowdown {
    public static void main(String[] args) {
        //Test default constructor
        ArrayVsArrayList arrayVsArrayList = new ArrayVsArrayList();
        char winner;
        System.out.println("Round one: Default Constructor");
        System.out.println("");
        System.out.println("Add test: add a 3 to the beginning, a four to the next index, a five after it, and so on up to seven.");
        arrayVsArrayList.addAndPrintTime(0,3);
        arrayVsArrayList.addAndPrintTime(1,4);
        arrayVsArrayList.addAndPrintTime(2,5);
        arrayVsArrayList.addAndPrintTime(3,6);
        arrayVsArrayList.addAndPrintTime(4,7);
        System.out.println("");
        System.out.println("Remove test: remove the third element, a five.");
        arrayVsArrayList.removeAndPrintTime(2);
        System.out.println("");

        //Test add to front constructor
        System.out.println("Round two: Add to front Constructor");
        arrayVsArrayList = new ArrayVsArrayList(3);
        System.out.println("Add test: leave the 3 already at the beginning, a four to the next index, a five after it, and so on up to seven.");
        arrayVsArrayList.addAndPrintTime(1,4);
        arrayVsArrayList.addAndPrintTime(2,5);
        arrayVsArrayList.addAndPrintTime(3,6);
        arrayVsArrayList.addAndPrintTime(4,7);
        System.out.println("");
        System.out.println("Remove test: remove the third element, a five.");
        arrayVsArrayList.removeAndPrintTime(2);
        System.out.println("");

        //Test initialize to an array constructor
        System.out.println("Round three: Initialize to an array Constructor");
        arrayVsArrayList = new ArrayVsArrayList(new Integer[]{3,4,6,7});
        System.out.println("Add test: add a 5 to the middle, as the rest was done in the constructor.");
        arrayVsArrayList.addAndPrintTime(2,5);
        System.out.println("");
        System.out.println("Remove test: remove the third element, a five.");
        arrayVsArrayList.removeAndPrintTime(2);
        System.out.println("");


    }
}
