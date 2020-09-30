/**
 * MilitaryClock
 * This is a Clock subclass. It is a clock, the only difference is a new getTime that instead of displaying PM, adds 12 to the current hours and displays it. This allows
 * a smooth transition between clock types in the driver because the instance data is always 12 hour format no matter which type of clock you have.
 * Authors: Jack Hughes
 * Date: 1-22-2020
 * On My Honor: JH
 */
public class MilitaryClock extends Clock {


    public MilitaryClock(){
        super();//Same as Clock
    }
    public MilitaryClock(int seconds, int minutes, int hours, boolean isAM){
        super(seconds, minutes, hours, isAM);//Same as Clock
    }
    @Override
    public String getTime() {
        //For formatting
        String formattedHours;
        String formattedMinutes;
        String formattedSeconds;

        //If the hours are less than ten add a 0 to the front to keep it pretty
        if(this.hours < 10){
            formattedHours = "0" + this.hours;
        }
        else{
            formattedHours = "" + this.hours;
        }
        //If the minutes are less than ten add a 0 to the front to keep it pretty
        if(this.minutes < 10){
            formattedMinutes = "0" + this.minutes;
        }
        else{
            formattedMinutes = "" + this.minutes;
        }
        //If the seconds are less than ten add a 0 to the front to keep it pretty
        if(this.seconds < 10){
            formattedSeconds = "0" + this.seconds;
        }
        else{
            formattedSeconds = "" + this.seconds;
        }
        //If it's AM, add 12 to the hours and then string-a-tize it so that it's military time not AM/PM
        if(!isAM){
            formattedHours = this.hours + 12 + "";
        }
        //Display hours(adjusted):minutes:seconds
        return formattedHours + ":" + formattedMinutes + ":" + formattedSeconds;
    }
}
