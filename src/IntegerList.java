import java.util.ArrayList;

public class IntegerList {
    private ArrayList<Integer> myList;

    public IntegerList(int[] a) {
        myList = new ArrayList<Integer>();
        for (int i = 0; i < a.length; i++) {
            myList.add(a[i]);
        }
    }

    public void addToFront(int a) {
        //lol gotta love ArrayList
        myList.add(0, a);
    }

    public int numGreater(int num) {
        int counter = 0;
        for (int i = 0; i < myList.size(); i++) {
            if (myList.get(i) > num) {
                counter++;
            }
        }
        return counter;
    }

    public void removeDivisible(int num) {
        ArrayList<Integer> numsToRemove = new ArrayList<Integer>();
        for (int i = 0; i < myList.size(); i++) {
            if ((myList.get(i) % num) == 0)
            {//num is divisible, remove it.
                numsToRemove.add(myList.get(i));
            }
        }
        for(int i = 0; i < numsToRemove.size(); i++){
            myList.remove(numsToRemove.get(i));
        }
    }

    public void printList() {
        System.out.println(myList);
    }
}
