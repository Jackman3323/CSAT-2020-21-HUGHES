/**
 * SimpleDriver
 * This program is, a simple driver for simple strings! It tests all the methods and constructors and things of the like,
 * as the title states, it's simple.
 * Authors: JH
 * Date: 2-20-20
 * On My Honor: JH
 */

import java.util.Scanner;

public class SimpleDriver {
    public static void main(String[] args) {
        //Initial ten letter test
        char[] chars = new char[10];
        for (int i = 0; i < 10; i++) {
            chars[i] = (char) ('a' + i);
        }
        SimpleString simStr = new SimpleString(chars);
        System.out.println("Initial test, 10 letters. This SimpleString Is The Base For All Tests:");
        System.out.println(simStr);
        //Other Constructor Test
        SimpleString constTest = new SimpleString(simStr);
        System.out.println("Should be same characters:");
        System.out.println(constTest);
        //First Substring Test
        SimpleString sub1 = simStr.substring(5);
        System.out.println("Should be everything from index 5 onwards:");
        System.out.println(sub1);
        //Second Substring Test
        SimpleString sub2 = constTest.substring(0, 5);
        System.out.println("Should be everything not included in the other substring:");
        System.out.println(sub2);
        //CharAt test
        char[] chars2 = new char[10];
        for (int i = 0; i < 10; i++) {
            chars2[i] = (char) ('a' + i);
        }
        SimpleString newMain = new SimpleString(chars2);
        char at0 = newMain.charAt(0);
        System.out.println("Should be first char:");
        System.out.println(at0);
        //IndexOf test
        Scanner scan = new Scanner(System.in);
        System.out.println("What substring would you like to search for? Enter it here: ");
        String str = scan.nextLine();
        //Um for some reason we weren't supposed to make a string constructor... had to do some obtuse coding here... literally copied and pasted SimpleString constructor...
        char[] chars3 = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            chars3[i] = str.charAt(i);
        }
        SimpleString userQuery = new SimpleString(chars3);
        System.out.println("The Resulting Index Was: " + newMain.indexOf(userQuery) + ".");
        //Concat Test
        char [] chars4 = new char[15];
        chars4[0] = 'I';
        chars4[1] = ' ';
        chars4[2] = 'W';
        chars4[3] = 'A';
        chars4[4] = 'S';
        chars4[5] = ' ';
        chars4[6] = 'C';
        chars4[7] = 'O';
        chars4[8] = 'N';
        chars4[9] = 'C';
        chars4[10] = 'A';
        chars4[11] = 'T';
        chars4[12] = 'T';
        chars4[13] = 'E';
        chars4[14] = 'D';
        SimpleString toConcat = new SimpleString(chars4);
        SimpleString resultConcat = newMain.concat(toConcat);
        System.out.println("Should be Main SimpleString and a Secret Message:");
        System.out.println(resultConcat);
        System.out.println("");
        System.out.println("ToString has already been demonstrated in this lab.");
        System.out.println("");
        System.out.println("What is the length of the example SimpleString that has been used the whole lab?");
        System.out.println(newMain.length());

    }
}