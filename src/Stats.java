import java.util.ArrayList;

public class Stats {
    private ArrayList<ScoreInfo> scoreList;
    // listed in increasing score order; no two ScoreInfo objects contain the same score

    /**
     * Records a score in the database, keeping the database in increasing score order. If no other
     * ScoreInfo object represents score, a new ScoreInfo object representing score
     * is added to the database; otherwise, the frequency in the ScoreInfo object representing
     * score is incremented.
     *
     * @param score a score to be recorded in the list
     * @return true if a new ScoreInfo object representing score was added to the list;
     * false otherwise
     */
    public boolean record(int score) {
        /* to be implemented in part (a) */
        boolean containsScore = false;
        int scoreIndex = -1;
        for(int i = 0; i < scoreList.size(); i++){
            if(scoreList.get(i).getScore() == score){
                containsScore = true;
                scoreIndex = i;
            }
        }
        if(containsScore){
            //Don't add a new scoreInfo, we've already got this score in the scoreList. Instead, increment it.
            scoreList.get(scoreIndex).increment();
            return false;
        }
        //We didn't already have it, find the right spot and then make a new one
        for(int i = 0; i < scoreList.size(); i++){
            if(scoreList.get(i).getScore() < score){
                //Insert here!
                scoreList.add(new ScoreInfo(score));
                break;
            }
            else if(i == scoreList.size()){
                //Insert here!
                scoreList.add(new ScoreInfo(score));
                break;
            }
        }
        return true;
    }

    /**
     * Records all scores in stuScores in the database, keeping the database in increasing score order
     *
     * @param stuScores an array of student test scores
     */
    public void recordScores(int[] stuScores) {
        /* to be implemented in part (b) */
        for(int currScore:stuScores){
            this.record(currScore);
        }
    }
    // There may be instance variables, constructors, and methods that are not shown.
}
