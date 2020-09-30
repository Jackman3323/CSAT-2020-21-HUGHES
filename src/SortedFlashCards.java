/**
 * SortedFC
 * This program is just as great as Jimmy's last flashcard party, except this time it's sorted so that he can memorize them in order!
 * Authors: Jack Hughes
 * Date 4/8/20
 * On My Honor: JH
 */
public class SortedFlashCards extends FlashCards {
    //Default constructor assumed, java will be nice and do it for us

    public void addCard(String question, String answer) {
        int insertIndex = numCards;
        if (numCards == myQuestions.length) {
            doubleArrayLengths();
        }

        for(int i = 0; i < numCards; i++){

           if(question.compareTo(myQuestions[i]) < 0){
               insertIndex = i;
               break;
           }
        }
        //System.out.println(question + " at index " + insertIndex);
        for (int i = numCards-1; i >=insertIndex; i--) {

                //Before insert time, make some room
                myQuestions[i + 1] = myQuestions[i];
                myAnswers[i+1] = myAnswers[i];

        }
        myQuestions[insertIndex] = question;
        myAnswers[insertIndex] = answer;
        numCards++;
    }
}