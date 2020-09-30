/**
 * FlashCards
 * Ever been to school, Jimmy??? Well if you have, you've probably seen the concept of flashcards. Helpful but primitive,
 * those pieces helped many an archaic grandpa get their college degree. But they're so old and crusty and paper-y that
 * nobody uses them nowadays. Most of us would rather score twenty percent lower than get caught by our friends using
 * FLASHCARDS, oh the horror and the shame!! But it does not have to be this way! Allow me to introduce: FlashCards.java,
 * the latest in advanced flash card technology. It's so advanced that it's literally just a .txt file with some words
 * in it, completely unformatted and obtuse and not user intuitive to set up, these electronic paperless flashcards really
 * allow you to get the most out of your studying! Buy your copy of the software now!!!!! (Not responsible for lower grades)
 *
 * NOTE: Yes, I'm reading the labs carefully: Banana bonanza
 *
 * Authors: Jack Hughes
 * Date: 2/24/20
 * On My Honor: JH
  */

public class FlashCards {
	 protected String[] myQuestions;
	 protected String[] myAnswers;
	 protected int numCards;

	 /* initialize instance data: arrays with a length of 1, and numCards to 0 */
	 public FlashCards() {
		 myQuestions = new String[1];
		 myAnswers = new String[1];
	 }

	 /* helper method that doubles the two arrays (myQuestions and myAnswers) in size to hold more data */
	 public void doubleArrayLengths() {
		 String[] newQuestions = new String[myQuestions.length * 2];
		 for (int i = 0; i < myQuestions.length; i++) {
			 newQuestions[i] = myQuestions[i];
		 }
		 myQuestions = newQuestions;
		 //Ok, I did the pattern once but I want to save time:
		 String[] newAnswers = new String[myAnswers.length * 2];
		 System.arraycopy(myAnswers, 0, newAnswers, 0, myAnswers.length);
		 myAnswers = newAnswers;
	 }

	 /* Adds the question and answer as a flashcard
	 Hint: remember to check to see if the arrays need to double in size */
	 public void addCard(String question, String answer) {
		 if (numCards == myQuestions.length) {
			 doubleArrayLengths();
		 }
		 myQuestions[numCards] = question;
		 myAnswers[numCards] = answer;
		 numCards++;
	 }

	 /* returns the question at the given index */
	 public String getQuestion(int i) {
		return myQuestions[i];
	 }

	 /* returns the answer at the given index */
	 public String getAnswer(int i) {
	 	return myAnswers[i];
	 }

	 /* returns the number of flash cards */
	 public int getNumCards() {
		return myQuestions.length;
	 }

	 /* prints all the flashcards, you may want to use this method for testing!!*/
	 public void printFlashCards() {
		 for (int i = 0; i < numCards; i++) {
			 System.out.println("Q: " + getQuestion(i));
			 System.out.println("A: " + getAnswer(i));
		 }
	 }
 }
