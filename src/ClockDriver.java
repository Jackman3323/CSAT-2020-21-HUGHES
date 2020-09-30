/**
 * ClockDriver
 * This is a driver program that assists the user in using the polymorphic MilitaryCLock class and the Clock class. The user has the ability to set the time, change the format
 * of the time (Standard > Military or Military > Standard), display the current time in their current format, and end the program. Of course, I spent way to long formatting this
 * lab so it is nice and pretty, a trustworthy place to put your personal time information.
 * Authors: Jack Hughes
 * Date: 1-22-2020
 * On My Honor: JH
 */

import java.util.Scanner;
public class ClockDriver {
    public static void main(String[] args) {
        //For formatting
        String optionList = " --------------------------\n" +
                "|Chose a Numbered Option:  |\n" +
                "|\t\t\t\t\t       |\n" +
                "|1. Set New Time\t\t   |\n" +
                "|2. Change Format\t\t   |\n" +
                "|3. Display Current Time   |\n" +
                "|4. End Program\t\t\t   |\n" +
                " --------------------------";
        //For smooth format transitioning
        Clock c;
        int hours = 0;
        int minutes = 0;
        int seconds = 0;
        String AMPM = "";
        Scanner scan = new Scanner(System.in);
        boolean isMilitary = false;
        c = new Clock();
        //Main loop
        while(true){
            //For smooth format transitioning
            boolean isAM = AMPM.equals("y");
            //Happens at the top of every iteration
            System.out.println(optionList);
            //Takes user's request
            int userInput = scan.nextInt();
            //What to do with user request
            switch(userInput){
                //Set a new time
                case 1:
                    //Set hours
                    System.out.println("Input hours(12 hour format):");
                    hours = scan.nextInt();
                    c.setHours(hours);
                    //Set minutes
                    System.out.println("Input minutes: ");
                    minutes = scan.nextInt();
                    c.setMinutes(minutes);
                    //Set seconds
                    System.out.println("Input seconds: ");
                    seconds = scan.nextInt();
                    scan.nextLine(); //Bc we need to read in not an int next
                    c.setSeconds(seconds);
                    //Set whether or not it is AM
                    System.out.println("Is it AM? (y/n):");
                    AMPM = scan.nextLine();
                    //Am/Pm setting
                    if(AMPM.equals("y")){
                        c.setAM(true);
                    }
                    else{
                        c.setAM(false);
                    }
                    break;
                //Change format
                case 2:
                    //If it's a military clock, make it a standard one at the same time the military one was set to
                    if(isMilitary){
                        c = new Clock(seconds,minutes,hours,isAM);
                        System.out.println("Format changed to 12-hour");
                        isMilitary = false;
                    }
                    //If it's a standard clock, make it a military one at the same time the standard one was set to
                    else{
                        c = new MilitaryClock(seconds,minutes,hours,isAM);
                        System.out.println("Format changed to military");
                        isMilitary = true;
                    }
                    break;
                //Return the current time in the correct format
                case 3:
                    System.out.println(c.getTime());
                    break;
                //End the program
                case 4:
                    System.out.println("Goodbye!!");
                    System.exit(0);
                    break;
            }
        }
    }

}
