/**
 * Clock
 * Ever ask what time it is only to have your friends laugh and say time to get a watch?? Fear not for at last, those days of depression and social anxiety are over. Never ask the time
 * again with Jack's Clock class!! It allows the user to set the hours, minutes, seconds, and AM/PM status individually!! Unfortunatley, automatic timekeeping has not yet been implemented,
 * so if you desire utter accuracy the rest of your life will be devoted to constantly updating the seconds. But who cares!! The best part of this system is that the time is WHATEVER
 * YOU SAY IT IS!! So come on down to your local gamestop and buy your Clock class today so that you can have the power to control time!! (Not responsible for missed meetings and events)
 * Authors: Jack Hughes
 * Date: 1-22-2020
 * On My Honor: JH
 */

public class Clock {
    //instance data
    public int seconds;
    public int minutes;
    public int hours;
    public boolean isAM;

    //Default constructor, sets time to 00:00:00 AM
    public Clock(){
        seconds = 0;
        minutes = 0;
        hours = 0;
        isAM = true;
    }
    //Main constructor, sets time to inputtedHours:inputtedMinutes:inputtedSeconds inputtedAM/PMStatus
    public Clock(int seconds, int minutes, int hours, boolean isAM){
        this.seconds = seconds;
        this.minutes = minutes;
        this.hours = hours;
        this.isAM = isAM;
    }
    //Seconds mutator
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
    //Hours mutator
    public void setHours(int hours) {
        this.hours = hours;
    }
    //Minutes mutator
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }
    //AM/PM status mutator
    public void setAM(boolean AM) {
        isAM = AM;
    }
    //Time accessor (IDK why we weren't supposed to call it toString but whatevs)
    public String getTime(){
        //For formatting
        String formattedHours;
        String formattedMinutes;
        String formattedSeconds;
        String formattedAM;

        //If hours is less than ten add a 0 to keep it pretty
        if(this.hours < 10){
            formattedHours = "0" + this.hours;
        }
        else{
            formattedHours = "" + this.hours;
        }
        //If minutes is less than ten add a 0 to keep it pretty
        if(this.minutes < 10){
            formattedMinutes = "0" + this.minutes;
        }
        else{
            formattedMinutes = "" + this.minutes;
        }
        //If seconds is less than 10 add a 0 to keep it pretty
        if(this.seconds < 10){
            formattedSeconds = "0" + this.seconds;
        }
        else{
            formattedSeconds = "" + this.seconds;
        }
        //For formatting
        if(isAM){
            formattedAM = " AM";
        }
        else{
            formattedAM = " PM";
        }
        //Return hours:minutes:seconds: AM/PM
        return formattedHours + ":" + formattedMinutes + ":" + formattedSeconds + formattedAM;
    }
}
