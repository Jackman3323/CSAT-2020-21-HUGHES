/**
 * TreasureHuntProximity
 * This program is the main file for the proximity tracker version of TreasureHunt®. It functions almost exactly the same as
 * TreasureHunt®, except for -REDACTED-. It's definitely more exciting than the other version, although due to company-wide
 * Intellectual Property classifications, nobody really knows for sure if these two games are really any different. Upon
 * "hacking the mainframe" it was determined that missed guesses are marked with a rough estimate of how far you were from
 * the treasure in this version, whereas the other marked it with an X. But I mean, come on. Who would chose to play this
 * game instead of MineSweeper®?? Who is even going to read this comment? Likely nobody. Anyways, enjoy your "fun" game of
 * TreasureHunt: Proximity Edition®
 * Authors: Jack Hughes
 * Date: 4-10-20
 * On My Honor: JH
 */
public class Hughes_TreasureHuntProximity extends Hughes_TreasureHunt {
    //only need to override checkToEnd--this will allow printing of distance numbers
    public boolean isGold(int r, int c){
        return this.mapArr[r][c] == 'G';
    }
    @Override
    public boolean checkToEnd(int r, int c) {
        boolean stop;
        numGuesses++;//Increment numGuesses
        if(r > 2 || c > 4){
            //Out of bounds, we have shenanigans on our hands!
            stop = false; //Trflase? lol
        }
        else{
            //Otherwise, calculate accuracy of guess normally
            stop = this.isGold(r,c);
        }

        if(!stop){
            //Calculate distance from gold and print that number
            int totalDistanceFromGold = Math.abs((r-goldRow) + (c-goldCol));
            mapArr[r][c] = (char)(totalDistanceFromGold + '0'); //Place missed guess indicator
        }

        //Return whether or not to stop the game
        return stop;
    }
}
