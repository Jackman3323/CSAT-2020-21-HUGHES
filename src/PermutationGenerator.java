/**
 * PermutationGenerator
 * This program is for people who just can't get enough of the same story, slightly changed, over and over again. (Hey there, fans of star wars 4, 6, 7, 8, and 9)
 * It is a class that holds a String ArrayList that can then be used to generate random permutations of this parent list. It has two constructors to allow greater
 * flexibility and parent lists of custom length.
 * Authors: Jack Hughes
 * On My Honor: JH
 * Date: 4-1-20
 */

import java.util.ArrayList;
import java.util.Random;

public class PermutationGenerator {
    private ArrayList<String> parent;
    private int parentSize;
    private Random r;

    //Default Constructor--Initializes parent and Random and parentSize
    public PermutationGenerator() {
        parentSize = 10;
        parent = new ArrayList<String>(parentSize);
        for (int i = 0; i < parentSize; i++) {
            char currChar = (char) ('A' + i);
            String toAdd = currChar + "";
            parent.add(i, toAdd);
        }
        r = new Random();
    }
    //Custom size constructor
    public PermutationGenerator(int size) {
        parentSize = size; //Custom size variable
        parent = new ArrayList<String>(parentSize);
        for (int i = 0; i < parentSize; i++) {
            char currChar = (char) ('A' + i);
            String toAdd = currChar + "";
            parent.add(i, toAdd);
        }
        r = new Random();
    }
    //Makes permutations
    public ArrayList<String> getPermutation() {
        ArrayList<String> tempParent = new ArrayList<String>(parent); //Placeholder to remove values during a permutation so that actual parent is not effected
        ArrayList<String> permutation = new ArrayList<String>(parentSize); //Declares + initializes local variable to be returned at the end of the function which will contain a permutation of parent
        for (int i = 0; i < parentSize; i++) {
            //Find a random element index
            int randElemID = r.nextInt(parentSize-i);
            //Add that string to the permutation arraylist
            permutation.add(i,tempParent.get(randElemID));
            //Remove that element from the temporary placeholder that makes this permutation
            tempParent.remove(randElemID);
        }
        return permutation; //Return the permutation
    }
}
