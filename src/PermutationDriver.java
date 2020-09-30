/**
 * PermutationDriver
 * This program is for people who just can't get enough of the same story, slightly changed, over and over again. (Hey there, fans of star wars 4, 6, 7, 8, and 9)
 * This program uses the class that was already made and creates a very intuitive and easy to use, iterative, industry standard (what???) user interface to create
 * a parent list of a custom length and then generate a certain number of permutations of that list.
 * Authors:
 */

import java.util.Scanner;
public class PermutationDriver {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        PermutationGenerator permGen; //Declaration of class

        System.out.println("Welcome! This program allows you to create a list of letters (and possibly symbols) at a custom length and then create random permutations of that list.");
        System.out.println("");
        System.out.println("");
        System.out.println("How long would you like your parent list to be? Keep in mind, invalid numbers will result in a default length of ten. Input here: ");
        int listLength = scan.nextInt();
        if(listLength < 2){
            //Invalid number, default constructor
            listLength = 10;
            permGen = new PermutationGenerator();
        }
        else{
            //Valid number, custom constructor
            permGen = new PermutationGenerator(listLength);
        }
        System.out.println("Roger that! Your parent list will be " + listLength + " characters long.");
        System.out.println("");
        System.out.println("");
        System.out.println("How many permutations would you like to see? Keep in mind, invalid numbers will result in a default amount of ten. Input here: ");
        int numPerms = scan.nextInt();
        if(numPerms < 1){
            //Invalid number, do 10 perms
            numPerms = 10;
        }
        System.out.println("");
        System.out.println("");
        System.out.println("Here are your permutations:");
        for(int i = 0; i < numPerms; i++){
            System.out.println(permGen.getPermutation());
        }

    }
}
