/**
 * PowersOfTwo
 * This program is for silly people who think that it's worthwhile to spend thirty minutes using this program to save literal nanoseconds off of calculation time for
 * some lame videogame that's less fun than tetris. (LOL) This program calculates the first ten powers of two starting at 2^0 and ending at 2^9 in two methods. First,
 * * a manually calculated, hardcoded list with those values in it is created and used as a look up table whenever the "game" needs it. For our testing purposes, we
 * just loop through the list and assign the value in the list to a temporary variable. The second method was doing the mathematical work as needed. We also looped
 * through ten times here and calculated all of those powers of two. The program will return a sentence with its results when run.
 * Authors: Jack Hughes
 * Date: 3-15-20
 * On My Honor: JH
 */
public class PowersOfTwo {
    //Oops did simplestring instead
    public static void main(String[] args) {
        long startTimeArray = System.currentTimeMillis();
        int [] powersOfTwo = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512}; //Look up table initialization
        double tempVar; //Temporary variable to store values in
        for(int i = 0; i < powersOfTwo.length; i++){
            tempVar = powersOfTwo[i]; //Assignment of value to tempvar
        }
        long endTimeArray = System.currentTimeMillis();

        long startTimeMathPow = System.currentTimeMillis();
        double tempVar2;//Temporary variable to store values in
        for(int i = 0; i < 10; i++){https://kentdenver.zoom.us/my/phaynes
            tempVar2 = Math.pow(2,i); //Assignment of mathematical value to tempvar
        }
        long endTimeMathPow = System.currentTimeMillis();
        System.out.print("The array took: ");
        System.out.println(endTimeArray - startTimeArray + " seconds to complete.");
        System.out.print("The look up table took: ");
        System.out.println(endTimeMathPow - startTimeMathPow + " seconds to complete.");

        //Which was faster?
        if(endTimeArray - startTimeArray > endTimeMathPow - startTimeMathPow){
            //Array took longer than calculation
            System.out.println("Calculation was faster");
        }
        else{
            //Calculation took longer than look up table
            System.out.println("Look up table was faster");
        }
    }
}
